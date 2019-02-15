/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import DAO.CertifFilesDAO;
import DAO.LabelFileDAO;
import DAO.PoiStudyTrackerDAO;
import DAO.PoiTrackerDAO;
import DAO.ScreenFilesDAO;
import com.JehodFactory.overviewerss.Params;
import entity.SimpleRowTracker;
import entity.SimpleStudyTracker;
import entity.SimpleTracker;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import view.FenEnd;

/**
 * Classe maitresse qui conduit le workflow de l'appli
 *
 * @author nrochas
 */
public class OverView {
    
    entity.SimpleStudyParam params = Params.getInstance().studyParam;
    final String path;
    final String pathSvnDoc;
    final String pathLabels = params.getPathLabels();
    final String pathScreens = params.getPathScreens();
    final String pathFinalsScreens = params.getPathFinalsScreens();
    final String pathCertifs = params.getPathCertifs();
    final Boolean local;
    
    public OverView(String path, String pathSvnDoc, boolean local) {
        this.path = path;
        this.local = local;
        this.pathSvnDoc = pathSvnDoc;
        
    }
    
    public OverView(String path, boolean local) {
        this.path = path;
        this.local = local;
        this.pathSvnDoc = null;
        
    }

    /**
     * methode pour creer les trackers par langue et le studyTracker il
     * instancie les DAO et parcourt les fichiers de langue
     */
    public void overview() {

        //on liste les dossiers de langues
        List<String> listLang;
        LabelFileDAO lbf = new LabelFileDAO(path + pathLabels);
        
        CertifFilesDAO ctf = null;
        PoiTrackerDAO ptk;
        ScreenFilesDAO scf;
        ScreenFilesDAO scfFinals = null;

        //on pointe le dossier de screenshot
        // ScreenFilesDAO scf = new ScreenFilesDAO(path + pathScreenshot);//("svn://svn.kayentis.fr:14000/Kayentis/Novartis/CAIN457M2301/trunk");//(path + pathScreenshot);
        listLang = lbf.getAllLabelsFiles();
        List<SimpleTracker> listTrackers = new ArrayList<>();

        //on alerte si pas de dossier de langue
        if (listLang.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucun dossier de langue trouvé. Track end", "Warning", JOptionPane.ERROR_MESSAGE, new ImageIcon("C:\\Users\\nik\\Documents\\NetBeansProjects\\OverViewerSS\\src\\main\\resources\\rugissment.png"));
        } else {

            //pour chaque dossier de langue
            for (String dir : listLang) {

                //on pointe les dossier de screenshots et labels path est soit svn1400 soit local
                ptk = new PoiTrackerDAO(path + pathLabels + dir);
                scf = new ScreenFilesDAO(path + pathScreens, dir);//("svn://svn.kayentis.fr:14000/Kayentis/Novartis/CAIN457M2301/trunk");//(path + pathScreenshot);

                //on pointe l'url des certifs et des screens finaux si on n'est pas local
                if (!local) {
                    ctf = new CertifFilesDAO(pathSvnDoc + pathCertifs, dir);
                    scfFinals = new ScreenFilesDAO(pathSvnDoc + pathFinalsScreens, dir);
                }
                
                SimpleTracker smt = ptk.createTrackerFromLabel(dir);

                //comme le rowtracker ont été créé depuis les labels il manque plusieurs infos
                //on fait ici l'ajout de la verif de screenshot et de certif si !local
                if (new File(path + pathLabels + dir).exists()) {
                    //pour chaque questionnaire de la langue
                    for (SimpleRowTracker rt : smt.getAllRowTracker()) {
                        System.out.println("pathcertif " + path + pathCertifs);
                        System.out.println("patscreen " + path + pathScreens);
                        
                        if (rt.getFormulaire().contains("Training")) {
                            rt.setScreenDone(scf.searchTrainingPDF(dir, rt.getFormulaire(), rt.getVersion()));
                        } else {
                            
                            if (scf.checkExistingPDF(dir, rt.getFormulaire(), rt.getVersion())) {
                                rt.setScreenDone("Yes");//scf.getDateLastModifPDF(dir, rt.getFormulaire(), rt.getVersion()));
                            }
                            
                            //lance les controle sur svn sur on n'est pas local
                            if (!local) {
                                
                                if (scfFinals.checkExistingPDF(dir, rt.getFormulaire(), rt.getVersion())) {
                                    rt.setScreenDone("Yes");
                                } else {
                                    rt.setScreenDone("No");
                                }                                
                                
                                if (rt.getVersion().contains(".0.0") && rt.getScreenDone().equals("Yes") && ctf.checkCertif(rt.getFormulaire(), rt.getVersion())) {
                                    rt.setCertified("Yes");
                                }
                            }
                            
                        }
                    }
                    
                }

                //on agrege la liste de tout les trackers et on ecrit le tracker de label
                listTrackers.add(smt);
                ptk.svgTracker(smt);
                
            }

            //on creer le studytracker avec tout ce qu'on a recuperé et on le svg          
            PoiStudyTrackerDAO pstk = new PoiStudyTrackerDAO(path + pathLabels, local);
            pstk.svgStudyTracker(new SimpleStudyTracker((ArrayList<SimpleTracker>) listTrackers));
            
            FenEnd fe = new FenEnd();
            fe.setVisible(true);
        }
    }
    
}
