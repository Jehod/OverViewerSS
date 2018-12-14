/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import DAO.LabelFileDAO;
import DAO.PoiTrackerDAO;
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

        //pour chaque dossier de langue
        for (String dir : listLang) {
            PoiTrackerDAO ptk = new PoiTrackerDAO(path + pathLabels + dir );
            
            ptk.svgTracker(ptk.createTrackerFromLabel(dir));

        }

    }

}
