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

    final String path;
    final String pathLabels;
    final String pathScreenshot = "\\Scripts\\Screenshots";
    final String pathScreenshotSVN = "svn://svn.kayentis.fr:14000/Kayentis/Novartis/CAIN457M2301/trunk/Scripts/Screenshots";
    final String pathCertifs = Params.getInstance().studyParam.getPathCertifs();

    public OverView(String path, String pathLabels) {
        this.path = path;
        this.pathLabels = pathLabels;

    }

    /**
     * methode pour creer les trackers par langue et le studyTracker il
     * instancie les DAO et parcourt les fichiers de langue
     */
    public void overview() {

        //on liste les dossiers de langues
        List<String> listLang = new ArrayList();
        LabelFileDAO lbf = new LabelFileDAO(path + pathLabels);

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
                PoiTrackerDAO ptk = new PoiTrackerDAO(path + pathLabels + dir);

                //on pointe l'url des certifs
                CertifFilesDAO ctf = new CertifFilesDAO(pathCertifs, dir);
                //on pointe le dossier de screenshot
                ScreenFilesDAO scf = new ScreenFilesDAO(pathScreenshotSVN, dir);//("svn://svn.kayentis.fr:14000/Kayentis/Novartis/CAIN457M2301/trunk");//(path + pathScreenshot);

                SimpleTracker smt = ptk.createTrackerFromLabel(dir);

                //comme le rowtracker ont été créé depuis les labels il manque plusieurs infos
                //on fait ici l'ajout de la verif de screenshot et de certif
                if (new File(path + pathLabels + "/" + dir).exists()) {
                    //pour chaque questionnaire de la langue
                    for (SimpleRowTracker rt : smt.getAllRowTracker()) {
                        System.out.println("pathcertif "+pathCertifs);
                        System.out.println("patscreen "+ path+pathScreenshot);

                        if (rt.getFormulaire().contains("Training")) {
                            rt.setScreenDone(scf.searchTrainingPDF(dir, rt.getFormulaire(), rt.getVersion()));
                        } else {

                            if (scf.checkExistingPDF(dir, rt.getFormulaire(), rt.getVersion())) {

                                rt.setScreenDone("Yes");//scf.getDateLastModifPDF(dir, rt.getFormulaire(), rt.getVersion()));
                            }

                            if (rt.getVersion().contains(".0.0") && rt.getScreenDone().equals("Yes") && ctf.checkCertif( rt.getFormulaire(), rt.getVersion())) {
                                rt.setCertified("Yes");
                            }
                        }

                    }
                }
                listTrackers.add(smt);
                ptk.svgTracker(smt);

            }

            //on creer le studytracker avec tout ce qu'on a recuperé
            SimpleStudyTracker sst = new SimpleStudyTracker((ArrayList<SimpleTracker>) listTrackers, path, pathLabels, path);
            PoiStudyTrackerDAO pstk = new PoiStudyTrackerDAO(path + pathLabels);
            pstk.svgStudyTracker(sst);

            FenEnd fe = new FenEnd();
            fe.setVisible(true);
        }
    }

}
