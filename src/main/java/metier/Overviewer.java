/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import Outils.DateManager;
import Outils.FilesWorker;
import Outils.XlsManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nrochas
 */
public class Overviewer {
        
        XlsManager xm = new XlsManager();

    /**
     * va lister les fichiers de langue format iso du dossier cible, puis dans
     * chaque dossier prendre les excels les tracker et creer un tracket dans
     * chaque dossier
     *
     * @param path path vers le dossier ou se trouve les dossier langue iso et
     * les excel
     */
    public void tracker(String path) throws IOException {
      
      
        
        List listDirLang = FilesWorker.ListerDirByform(path, ".._..");
       
        //on liste les dossiers de langues presents
        for (Object dir : listDirLang) {
            
              ArrayList<String> listVersion = new ArrayList();
            //on cree  le tracker de cette langue si il n'y en a pas
            if (FilesWorker.findFile(path + dir+"/"+"Tracker_"+dir+".xlsx")) {
                System.out.println("Tracker trouve: "+ path + dir+"/"+"Tracker_"+dir+".xlsx");
            } else 
            {
                System.out.println("Tracker non trouvé: Creation lancée");
                xm.createXLSXTracker(path + dir+"/", (String) dir );
            }
            
            //on list les excels de label presents 
            List listXls = FilesWorker.ListerFilesByExtAndStart(path + dir.toString(), "Label",".xlsx");
            

            for (Object xls : listXls) {
                String version = xm.VersionCatcher(path + dir + "/" + xls.toString());
                System.out.println("version de " + xls + ": " + version);
                // la listVersion contient dans en split$(0) la langue, split $(1) le QI et en split $ (2) la version
                listVersion.add(dir+"#"+xls+"#"+version);

            }
            
            System.out.println("listversion: "+listVersion);
            //xm.fillTodayTrackerSheet(path + dir+"/"+"Tracker_"+dir+".xlsx" ,new DateManager().getSimpleCurrentDate(), listVersion);
          xm.fillTodayTrackerSheet(path + dir+"/"+"Tracker_"+dir+".txt" ,new DateManager().getSimpleCurrentDate(), listVersion);

        }

    }

    

}
