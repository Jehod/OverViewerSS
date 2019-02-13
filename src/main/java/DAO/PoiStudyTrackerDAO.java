/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Outils.DateManager;
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
public class PoiStudyTrackerDAO implements StudyTrackerDAO
{
    private String fileName;
    

    public PoiStudyTrackerDAO(String fileName)
    {
        super();
        this.fileName = fileName;
    }
    
    

    @Override
    public boolean fillStudyTracker(ArrayList<SimpleTracker> allTracker)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addTrackerToStudyTracker(SimpleTracker smt)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean svgStudyTracker(SimpleStudyTracker sst)
    {
       boolean bob = false;
        Workbook wb = null;
        Sheet sheet = null;
        String date = new DateManager().getSimpleCurrentDate();
        File file;

        //creer le workbook et la sheet
        try
        {
            
            System.out.println("trace: "+sst.toString());
            wb = new XSSFWorkbook();
//wb = WorkbookFactory.create(file);
           
            sheet = wb.createSheet("STUDYTRACKER" + "_" + date);
            

        } catch (Exception ex)
        {
            System.out.println("catch du svgTracker " + ex.getLocalizedMessage());
        }

        // creer les cellules du titre
        style.StylePoi style = new StylePoi(wb, sheet);
        style.createTitle("STUDYTRACKER");
        //createTitleStudyTracker(wb, sheet);

        Row rows;
        int i = 1;
        for (SimpleTracker track : sst.getAllTrackers())
        {
            //rows = sheet.createRow(i);
            //addTrackerLanguage(rows,);
            style.createBlocTitle(i,track.getName());
            String lang = track.getName();
            i++;
            
            //rows = sheet.createRow(i);
            style.createHeader(i,1);
            i++;
            for (SimpleRowTracker row : track.getAllRowTracker())
            {
                PoiTrackerDAO ptk = new PoiTrackerDAO(fileName);
                rows = sheet.createRow(i);
                ptk.addRowTracker(lang, row, rows);
                i++;
                
            }
 
        }
        
        //apply decoration
        style.pairStyle();

        String pathFile =fileName + "\\STUDYTRACKER "+date+".xlsx";
        try
        {
            
            file = new File(pathFile);
            FileOutputStream outFile = new FileOutputStream(file);
            wb.write(outFile);
            //outFile.close();
            bob = true;
        } catch (IOException ex)
        {
            Logger.getLogger(PoiTrackerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        //exemple d'autocommit'
       // Outils.SVNWorker.commitSVN("autoCommit","D:\\project\\CAIN457M2301\\Settings\\Labels\\TR_TR" );
        
        return bob;
    }

    /**
     * ajoute une cellule de titre de langue et la decoration de la ligne
     * @param rows
     * @param name 
     */
    private void addTrackerLanguage(Row rows, String name)
    {
        rows.createCell(0).setCellValue(name);
        //ajouter la decoration ici
        
    }
    
}
