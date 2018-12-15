/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import DAO.LabelFileDAO;
import DAO.PoiStudyTracker;
import DAO.PoiTrackerDAO;
import entity.SimpleStudyTracker;
import entity.SimpleTracker;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nrochas
 */
public class OverView {

    final String path;
    final String pathLabels;

    public OverView(String path, String pathLabels) {
        this.path = path;
        this.pathLabels = pathLabels;
    }

    public void overview() {

        //on liste les dossiers de langues
        List<String> listLang = new ArrayList();
        LabelFileDAO lbf = new LabelFileDAO(path + pathLabels);
        listLang = lbf.getAllLabelsFiles();
        List<SimpleTracker> listTrackers = new ArrayList<>();

        //pour chaque dossier de langue
        for (String dir : listLang) {
            PoiTrackerDAO ptk = new PoiTrackerDAO(path + pathLabels + dir );
            SimpleTracker smt = ptk.createTrackerFromLabel(dir);
            listTrackers.add(smt);
            ptk.svgTracker(smt);

        }
        
        //on creer le studytracker avec tout ce qu'on a recuperé
        SimpleStudyTracker sst = new SimpleStudyTracker((ArrayList<SimpleTracker>) listTrackers, path, pathLabels, path);
        PoiStudyTracker pstk = new PoiStudyTracker(path+pathLabels);
        pstk.svgStudyTracker(sst);

    }

}
