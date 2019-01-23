/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import DAO.LabelFileDAO;
import DAO.PoiStudyTrackerDAO;
import DAO.PoiTrackerDAO;
import DAO.ScreenFilesDAO;
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
 *
 * @author nrochas
 */
public class OverView {

    final String path;
    final String pathLabels;
    final String pathScreenshot = "\\Scripts\\Screenshots";

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
        listLang = lbf.getAllLabelsFiles();
        List<SimpleTracker> listTrackers = new ArrayList<>();

        //on pointe le dossier de screenshot
        ScreenFilesDAO scf = new ScreenFilesDAO(path + pathScreenshot);

        //on alerte si pas de dossier de langue
        if (listLang.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucun dossier de langue trouvé. Track end", "Warning", JOptionPane.ERROR_MESSAGE, new ImageIcon("C:\\Users\\nik\\Documents\\NetBeansProjects\\OverViewerSS\\src\\main\\resources\\rugissment.png"));
        } else {

            //pour chaque dossier de langue
            for (String dir : listLang) {
                PoiTrackerDAO ptk = new PoiTrackerDAO(path + pathLabels + dir);
                SimpleTracker smt = ptk.createTrackerFromLabel(dir);
                //ajout de la verif de screenshot
                if (new File(path + pathLabels + "/" + dir).exists()) {
                    for (SimpleRowTracker rt : smt.getAllRowTracker()) {

                        switch (rt.getFormulaire()) {
                            case "Training":
                                rt.setScreenDone(scf.searchTrainingPDF(dir, rt.getFormulaire(), rt.getVersion()));
                                break;
                            case "PARAM":
                                rt.setScreenDone("Done");
                                break;
                            case "PFT":
                                rt.setScreenDone("Done");
                            default:
                                if (scf.checkExistingPDF(dir, rt.getFormulaire(), rt.getVersion())) {
                                    rt.setScreenDone(scf.getDateLastModifPDF(dir, rt.getFormulaire(), rt.getVersion()));
                                }
                        }
                        /* if (rt.getFormulaire().equals("Training")) {
                            rt.setScreenDone(scf.searchTrainingPDF(dir, rt.getFormulaire(), rt.getVersion()));
                        }*/
                        if (rt.getVersion().endsWith("0.0")) {
                            rt.setFinalized("ok");
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
