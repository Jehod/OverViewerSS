/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Outils.DateManager;
import Outils.SVNWorker;
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

    private String fileName;

    public PoiStudyTrackerDAO(String fileName) {
        super();
        this.fileName = fileName;
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
        boolean bob = false;
        Workbook wb = null;
        Sheet sheet = null;
        String date = new DateManager().getSimpleCurrentDate();
        File file;
        Outils.SVNWorker svn = new SVNWorker();

        //creer le workbook et la sheet
        try {

            System.out.println("trace: " + sst.toString());
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
                PoiTrackerDAO ptk = new PoiTrackerDAO(fileName);
                rows = sheet.createRow(i);
                ptk.addRowTracker(lang, row, rows);
                i++;

            }

        }

        //apply decoration
        style.pairStyle();

        String fileStudy = "STUDYTRACKER " + date + ".xlsx";
        String pathFile = fileName + "\\" + fileStudy;
        try {

            file = new File(pathFile);
            FileOutputStream outFile = new FileOutputStream(file);
            wb.write(outFile);
            //outFile.close();
            bob = true;
        } catch (IOException ex) {
            bob = false;
            System.out.println("catch de la sauvegarde " + ex.getLocalizedMessage());
            Logger.getLogger(PoiTrackerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        //exemple d'autocommit'
        // Outils.SVNWorker.commitSVN("autoCommit","D:\\project\\CAIN457M2301\\Settings\\Labels\\TR_TR" );
        String url = "svn://document.kayentis.fr:15000/kayentis/Documentation/Projets/Santé/Novartis/CAIN457M2301-M2302/3- Functional scope/2- Forms/2- Kayentis design/1 - Screenshots/";
        String out; 
        
        if (svn.CheckExistInSVN(url, fileStudy)) {
            out = svn.commitSVN("AutCommit", pathFile);
        } else {
             out = svn.mountInSvn(pathFile, url + fileStudy);
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

}
