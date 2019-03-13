/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import DAO.SvnCertifFilesDAO;
import DAO.LabelFileDAO;
import DAO.LabelFileDAOExt;
import DAO.PoiStudyTrackerDAO;
import DAO.PoiTrackerDAO;
import DAO.ScreenFilesDAO;
import DAO.ScreenFilesDAOExt;
import DAO.SvnLabelFileDAO;
import DAO.SvnScreenFilesDAO;
import Outils.Check;
import com.JehodFactory.overviewerss.Params;
import entity.SimpleRowTracker;
import entity.SimpleStudyTracker;
import entity.SimpleTracker;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import view.FenEnd;

/**
 * Classe maitresse qui conduit le workflow de l'appli
 *
 * @author nrochas
 */
public class OverView {

    entity.SimpleStudyParam params = Params.getInstance().getStudyParam();
    final String path; //egal a svnDEL ou a chemin local suivant mode de track
    final String pathSvnDoc;
    final String pathLabels = params.getPathLabels();
    final String pathScreens = params.getPathScreens();
    final String pathFinalsScreens = params.getPathFinalsScreens();
    final String pathCertifs = params.getPathCertifs();
    final Boolean local; //c'est le marqueur pour mode local (true) ou mode svn (false)

    /**
     * constructeur pour tt online
     *
     * @param path chemin vers DEL
     * @param pathSvnDoc chemin vers DOC
     * @param local boolean local (a false ici)
     */
    public OverView(String path, String pathSvnDoc, boolean local) {
        this.path = path;
        this.local = local;
        this.pathSvnDoc = pathSvnDoc;

    }

    /**
     * constructeur offline svnDoc est mis a null
     *
     * @param path path local
     * @param local boolean local (a true ici)
     */
    public OverView(String path, boolean local) {
        this.path = path;
        this.local = local;
        this.pathSvnDoc = null;

    }

    /**
     * methode pour creer les trackers par langue et le studyTracker il
     * instancie les DAO et parcourt les fichiers de langue
     *
     * @param progress
     */
    public void overview(JFrame progress) {

        progress.setVisible(true);
        //on teste les chemins
        String str = Check.TestNeededPath(local, path, pathSvnDoc);
        System.out.println("le test des path:" + str);
        if (!str.equals("ok")) {
            System.out.println(str);
            JOptionPane.showMessageDialog(progress, str, "path Error", ERROR_MESSAGE);
            System.exit(ERROR_MESSAGE);
        }

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

                System.out.println("");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++Nouvelle langue+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("");

                //on pointe les dossier de screenshots et labels path est soit svn1400 soit local
                ptk = new PoiTrackerDAO(path + pathLabels + dir);
                ///scf = new ScreenFilesDAO(path + pathScreens, dir);

                SimpleTracker smt = null;

                ///le simpleTracker n'ayant que les infos du labels , un second TT ajoute les screenshot (et finaux et certif si svn)
                if (local) {

                    smt = localTraitement(dir, new ScreenFilesDAO(path + pathScreens, dir), ptk);
                } else {
                    smt = svnTraitement(dir, new SvnScreenFilesDAO(path + pathScreens, dir), ptk);

                }
                listTrackers.add(smt);

            }

            //on creer le studytracker avec tout ce qu'on a recuperé et on le svg          
            PoiStudyTrackerDAO pstk = new PoiStudyTrackerDAO(path + pathLabels, local);
            pstk.svgStudyTracker(new SimpleStudyTracker((ArrayList<SimpleTracker>) listTrackers));

            //on affiche la dernier fenetre
            FenEnd fe = new FenEnd();
            progress.dispose();
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

        //comme le rowtracker ont été créé depuis les labels il manque plusieurs infos
        //on fait ici l'ajout de la verif de screenshot et de certif si !local
        if (new File(path + pathLabels + dir).exists()) {
            //pour chaque questionnaire de la langue
            for (SimpleRowTracker rt : smt.getAllRowTracker()) {

                if (rt.getFormulaire().contains("Training")) {
                    System.out.println("test train");
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

        ArrayList<SimpleRowTracker> li = smt.getAllRowTracker();

        SvnCertifFilesDAO ctf = new SvnCertifFilesDAO(pathSvnDoc + pathCertifs, dir);
        SvnScreenFilesDAO scfFinals = new SvnScreenFilesDAO(pathSvnDoc + pathFinalsScreens, dir);

        //pour chaque questionnaire de la langue
        li.forEach((rt) -> {
            if (rt.getFormulaire().contains("Training")) {
                rt.setScreenDone(scf.searchTrainingPDF(dir, rt.getFormulaire(), rt.getVersion()));
            } else {

                //si ce n'est pas un label final on TraiTe normal sinon TTT en screenfinal
                if (scf.checkExistingPDF(dir, rt.getFormulaire(), rt.getVersion())) {
                    rt.setScreenDone("Yes");

                    //check supplementaire des versions finals et des certifs
                    if (rt.getVersion().endsWith(".0.0") && scfFinals.checkExistingPDF(dir, rt.getFormulaire(), rt.getVersion())) {

                        rt.setScreenDone("Yes");
                        if (ctf.checkCertif(rt.getFormulaire(), rt.getVersion())) {
                            rt.setCertified("Yes");
                        }
                    }
                }
            }
        });

        return smt;
    }
}
