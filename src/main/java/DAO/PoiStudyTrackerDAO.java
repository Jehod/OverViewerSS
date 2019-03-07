/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.interfaceDAO.StudyTrackerDAO;
import Outils.DateManager;
import Outils.FilesWorker;
import Outils.SVNWorker;
import com.JehodFactory.overviewerss.Params;
import entity.SimpleRowTracker;
import entity.SimpleStudyTracker;
import entity.SimpleTracker;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import style.StylePoi;

/**
 *
 * @author nik
 */
public class PoiStudyTrackerDAO implements StudyTrackerDAO {

    private final String pathFileLabels;
    private final boolean local;
    private final String pathSvnDoc = Params.getInstance().studyParam.getPathSvnDoc();
    private final String pathTEMP = Params.getInstance().getPathTEMP();
    private Workbook wb = null;
    private File file;
    private boolean bob;

    public PoiStudyTrackerDAO(String fileName, Boolean local) {
        super();
        this.pathFileLabels = fileName;
        this.local = local;
    }

    @Override
    public boolean fillStudyTracker(ArrayList<SimpleTracker> allTracker) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addTrackerToStudyTracker(SimpleTracker smt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean svgStudyTracker(SimpleStudyTracker sst) {
         

        Sheet sheet = null;
        String date = new DateManager().getSimpleCurrentDate();

        //creer le workbook et la sheet
        try {

            
            wb = new XSSFWorkbook();
//wb = WorkbookFactory.create(file);

            sheet = wb.createSheet("STUDYTRACKER" + "_" + date);

        } catch (Exception ex) {
            System.out.println("catch du svgTracker " + ex.getLocalizedMessage());
        }

        // creer les cellules du titre
        style.StylePoi style = new StylePoi(wb, sheet);
        style.createTitle("STUDYTRACKER");
        //createTitleStudyTracker(wb, sheet);

        Row rows;
        int i = 1;
        for (SimpleTracker track : sst.getAllTrackers()) {
            //rows = sheet.createRow(i);
            //addTrackerLanguage(rows,);
            style.createBlocTitle(i, track.getName());
            String lang = track.getName();
            i++;

            //rows = sheet.createRow(i);
            style.createHeader(i, 1);
            i++;
            for (SimpleRowTracker row : track.getAllRowTracker()) {
                PoiTrackerDAO ptk = new PoiTrackerDAO(pathFileLabels);
                if (sheet != null) {
                    rows = sheet.createRow(i);
                    ptk.addRowTracker(lang, row, rows);
                }
                i++;

            }

        }

        //apply decoration
        style.pairStyle();
        
        String TrackerName = "STUDYTRACKER.xlsx";

        if (local) {
            bob = svgLocal(TrackerName);
        } else {
            bob = svgSvn(TrackerName);

        }

        return bob;
    }

    /**
     * ajoute une cellule de titre de langue et la decoration de la ligne
     *
     * @param rows
     * @param name
     */
    private void addTrackerLanguage(Row rows, String name) {
        rows.createCell(0).setCellValue(name);
        //ajouter la decoration ici

    }

    /**
     * sauvegarde en local sur le poste du dev
     *
     * @return
     */
    private boolean svgLocal(String fileStudy) {
         bob = false;

        try {

            file = new File(pathFileLabels + fileStudy);
            FileOutputStream outFile = new FileOutputStream(file);
            wb.write(outFile);
            //outFile.close();
            bob = true;
        } catch (IOException ex) {
           
            System.out.println("catch de la sauvegarde " + ex.getLocalizedMessage());
            Logger.getLogger(PoiTrackerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bob;
    }

    /**
     * sauvegarde sur svnDoc et svn Del en detruisant avant si il existe deja et
     * en passant par un fichier temporaire ( aucun lien avec les working copy
     * locales
     *
     * @return
     */
    private boolean svgSvn(String studyTracker) {
         bob = false;
        String out;
        
        Outils.SVNWorker svn = new SVNWorker();

        //creation d'un fichier temporaire (on part du principe que l'adresse local n'est pas connu.
        try {

            file = new File(pathTEMP + studyTracker);
            FileOutputStream outFile = new FileOutputStream(file);
            wb.write(outFile);
            
            bob = true;
        } catch (IOException ex) {
            
            System.out.println("catch de la sauvegarde " + ex.getLocalizedMessage());
            Logger.getLogger(PoiTrackerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        //exemple d'autocommit'
        // Outils.SVNWorker.commitSVN("autoCommit","D:\\project\\CAIN457M2301\\Settings\\Labels\\TR_TR" );
        //url = "svn://document.kayentis.fr:15000/kayentis/Documentation/Projets/Santé/Novartis/CAIN457M2301-M2302/3- Functional scope/2- Forms/2- Kayentis design/1 - Screenshots/";
        
        //pour svnDel 1400
        //si il existe on le detruit    
        if (svn.CheckExistInSVN(pathFileLabels, studyTracker)) {
            svn.deleteInSVN(pathFileLabels, studyTracker);
        }

        out = svn.mountInSvn(pathTEMP + studyTracker, pathFileLabels + studyTracker);

        //out = svn.commitSVN("AutCommit", pathFile);
        
        // pour svnDoc 1500
        if (svn.CheckExistInSVN(pathSvnDoc, studyTracker)) {
            svn.deleteInSVN(pathSvnDoc, studyTracker);
        }

        out = svn.mountInSvn(pathTEMP + studyTracker, pathSvnDoc + studyTracker);

        
        //on detruit le fichier temp car on sort
         file.deleteOnExit();
         
        
        return bob;
    }

}
