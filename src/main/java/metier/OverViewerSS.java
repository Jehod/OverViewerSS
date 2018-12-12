/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import DAO.LabelFileDAO;
import DAO.PoiModifTrackDAO;
import DAO.PoiRowTrackerDAO;
import DAO.PoiTrackerDAO;
import Outils.FilesWorker;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nrochas
 */
public class OverViewerSS {

    String langue;
    final String path;
    final String pathLabels;

    public OverViewerSS(String path, String pathLabels) {
        this.path = path;
        this.pathLabels = pathLabels;
    }

    public void overview() {
        List<String> listLang = new ArrayList();

        LabelFileDAO lbf = new LabelFileDAO(path + pathLabels);
        listLang = lbf.getAllLabelsFiles();

        for (String dir : listLang) {

            //on list les excels de label presents 
            List<String> listXls = new ArrayList<>();
            listXls = FilesWorker.ListerFilesByExtAndStart(path + pathLabels + dir.toString(), "Label", ".xlsx");
            PoiRowTrackerDAO prtk = new PoiRowTrackerDAO(path + pathLabels + dir + "\\Tracker_" + dir + ".xlsx");
            
            for (String xls : listXls) {
                PoiModifTrackDAO pmtk = new PoiModifTrackDAO(path + pathLabels + dir + "\\"+xls);
                 System.out.println("pmtk: "+pmtk.toString());
                

            }

        }

        //
        //System.out.println("liste recuperer par la DAO: \r"+ prtk.findAllRowTracker().get(0).toString()+ "\r"+prtk.findAllRowTracker().get(1).toString());
        //
        //
        //PoiTrackerDAO ptk = new PoiTrackerDAO(path + pathLabels + langue + "\\Tracker_" + langue + ".xlsx");
        //ptk.addTracker(ptk.getLastTracker());
    }
}
