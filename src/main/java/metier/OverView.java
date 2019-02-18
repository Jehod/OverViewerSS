/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import DAO.CertifFilesDAO;
import DAO.LabelFileDAO;
import DAO.LabelFileDAOExt;
import DAO.PoiStudyTrackerDAO;
import DAO.PoiTrackerDAO;
import DAO.ScreenFilesDAO;
import DAO.ScreenFilesDAOExt;
import DAO.SvnLabelFileDAO;
import DAO.SvnScreenFilesDAO;
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

        //dao des output excel
        PoiTrackerDAO ptk;
        //dao des screenshots instancié dans les methodes en local ou en svn
        ScreenFilesDAOExt scf;
        
        //on prepare la liste des trackers qui construira le study tracker
        List<SimpleTracker> listTrackers = new ArrayList<>();

        
        LabelFileDAOExt lbf;
        //on liste les dossiers de langues
        List<String> listLang;
        //une version label local et une version svn
        if (local) {
            System.out.println("pathLocal des labels: " + path + pathLabels);
            lbf = new LabelFileDAO(path + pathLabels);
        } else {
            lbf = new SvnLabelFileDAO(path + pathLabels);
        }
        //on recupere les dossiers de lables
        listLang = lbf.getAllLabelsFiles();
        System.out.println("listlang: " + listLang.size());

        //on alerte si pas de dossier de langue
        if (listLang.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucun dossier de langue trouvé. Track end", "Warning", JOptionPane.ERROR_MESSAGE, new ImageIcon("C:\\Users\\nik\\Documents\\NetBeansProjects\\OverViewerSS\\src\\main\\resources\\rugissment.png"));
        } else {

            //pour chaque dossier de langue
            for (String dir : listLang) {

                //on pointe les dossier de screenshots et labels path est soit svn1400 soit local
                ptk = new PoiTrackerDAO(path + pathLabels + dir);
                scf = new ScreenFilesDAO(path + pathScreens, dir);//("svn://svn.kayentis.fr:14000/Kayentis/Novartis/CAIN457M2301/trunk");//(path + pathScreenshot);

                
                SimpleTracker smt = null;

                ///le simpleTracker n'ayant que les infos du labels , un second TT ajoute les screenshot (et finaux et certif si svn)
                if (local) {
                    
                    smt = localTraitement(dir, scf, ptk);
                } else {
                    smt = svnTraitement(dir, scf, ptk);

                }
                listTrackers.add(smt);
            }

            //on creer le studytracker avec tout ce qu'on a recuperé et on le svg          
            PoiStudyTrackerDAO pstk = new PoiStudyTrackerDAO(path + pathLabels, local);
            pstk.svgStudyTracker(new SimpleStudyTracker((ArrayList<SimpleTracker>) listTrackers));

            FenEnd fe = new FenEnd();
            fe.setVisible(true);

        }
    }

    /**
     * Traitement en local pour l'iteration de la boucle d'un fichier de langue
     *
     * @param dir la langue
     * @param smt le tracker dédié a cette langue
     * @param scf le dossier de screenshot de cette langue
     * @param ptk le tracker POI pour l'output
     * @return renvoie le Simpletracker completé
     */
    private SimpleTracker localTraitement(String dir, ScreenFilesDAOExt scf, PoiTrackerDAO ptk) {

        SimpleTracker smt = ptk.createTrackerFromLabel(dir);
        // ArrayList list = new ArrayList();
        scf = new ScreenFilesDAO(path + pathScreens, dir);
        //comme le rowtracker ont été créé depuis les labels il manque plusieurs infos
        //on fait ici l'ajout de la verif de screenshot et de certif si !local
        if (new File(path + pathLabels + dir).exists()) {
            //pour chaque questionnaire de la langue
            for (SimpleRowTracker rt : smt.getAllRowTracker()) {
                System.out.println("pathLabels " + path + pathLabels);
                System.out.println("patscreen " + path + pathScreens);

                if (rt.getFormulaire().contains("Training")) {
                    rt.setScreenDone(scf.searchTrainingPDF(dir, rt.getFormulaire(), rt.getVersion()));
                } else {

                    if (scf.checkExistingPDF(dir, rt.getFormulaire(), rt.getVersion())) {
                        rt.setScreenDone("Yes");
                        //pour avoir la date //scf.getDateLastModifPDF(dir, rt.getFormulaire(), rt.getVersion()));
                    }

                }
                //pas de certif en appli local
                rt.setCertified("Local");
            }

        }

        //en local on svg dans chaque dossier de langue
        ptk.svgTracker(smt);

        return smt;
    }

    private SimpleTracker svnTraitement(String dir, ScreenFilesDAOExt scf, PoiTrackerDAO ptk) {
       
         SimpleTracker smt = ptk.createTrackerFromSVNLabel(dir);
        
        CertifFilesDAO ctf = new CertifFilesDAO(pathSvnDoc + pathCertifs, dir);
        SvnScreenFilesDAO scfFinals = new SvnScreenFilesDAO(pathSvnDoc + pathFinalsScreens, dir);
        scf = new SvnScreenFilesDAO(path + pathScreens, dir);

      
        if (new File(path + pathLabels + dir).exists()) {
            //pour chaque questionnaire de la langue
            for (SimpleRowTracker rt : smt.getAllRowTracker()) {
                System.out.println("pathLabels"+path+pathLabels);
                System.out.println("pathcertif " + path + pathCertifs);
                System.out.println("patscreen " + path + pathScreens);

                if (rt.getFormulaire().contains("Training")) {
                    rt.setScreenDone(scf.searchTrainingPDF(dir, rt.getFormulaire(), rt.getVersion()));
                } else {

                    //si ce n'est pas un label final on TraiTe normal sinon TTT en screenfinal
                    if (!rt.getVersion().endsWith(".0.0")) {

                        if (scf.checkExistingPDF(dir, rt.getFormulaire(), rt.getVersion())) {rt.setScreenDone("Yes");}
                            
                        
                    } else {

                        //check supplementaire des versions finals et des certifs
                        if (scfFinals.checkExistingPDF(dir, rt.getFormulaire(), rt.getVersion())) {
                           
                            rt.setScreenDone("Yes");
                            if (ctf.checkCertif(rt.getFormulaire(), rt.getVersion())) { rt.setCertified("Yes");}

                        }
                    }

                }

               
            } 
        }return smt;
    }
}
