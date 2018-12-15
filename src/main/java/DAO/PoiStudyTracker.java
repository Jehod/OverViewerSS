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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author nik
 */
public class PoiStudyTracker implements StudyTrackerDAO
{
    private String fileName;
    

    public PoiStudyTracker(String fileName)
    {
        super();
        this.fileName = fileName;
    }
    
    /**
     *  creer le header pour le xlsx de studyTracker 
     * @param wb
     * @param sheet 
     */
     public void createTitleStudyTracker(Workbook wb, Sheet sheet)
    {
        if (wb != null || sheet != null)
        {
            Cell cell;
            Row row;
            Font font = wb.createFont();
            font.setBold(true);
            CellStyle style = wb.createCellStyle();
            style.setFont(font);
            int rownum = 0;

            row = sheet.createRow(rownum);

           // EmpName
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Questionnary");
            cell.setCellStyle(style);
            // version
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Version");
            cell.setCellStyle(style);
            // date version
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("DateOfUpdate");
            cell.setCellStyle(style);
            //screenshot
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("ScreenDone");
            cell.setCellStyle(style);
            // send
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("SendToExt");
            cell.setCellStyle(style);
            //final 
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Final");
            cell.setCellStyle(style);
            //certified 
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Certified");
            cell.setCellStyle(style);
        } else
        {
            System.out.println("le create title ne peut marcher car le workbook ou la sheet est nul");
        }

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
        
        File file =null;

        //creer le workbook et la sheet
        try
        {
            
            System.out.println("trace: "+sst.toString());
            wb = new XSSFWorkbook();
//wb = WorkbookFactory.create(file);
           
            sheet = wb.createSheet("STUDYTRACKER" + "_" + new DateManager().getSimpleCurrentDate());
            

        } catch (Exception ex)
        {
            System.out.println("catch du svgTracker " + ex.getLocalizedMessage());
        }

        // creer les cellules
        createTitleStudyTracker(wb, sheet);

        Row rows;
        int i = 1;
        for (SimpleTracker track : sst.getAllTrackers())
        {
            rows = sheet.createRow(i);
            addTrackerLanguage(rows,track.getName());
            i++;
            for (SimpleRowTracker row : track.getAllRowTracker())
            {
                PoiTrackerDAO ptk = new PoiTrackerDAO(fileName);
                rows = sheet.createRow(i);
                ptk.addRowTracker(row, rows);
                i++;
                
            }
 
        }

        try
        {
            file = new File(fileName + "\\STUDYTRACKER.xlsx");
            FileOutputStream outFile = new FileOutputStream(file);
            wb.write(outFile);
            //outFile.close();
            bob = true;
        } catch (Exception ex)
        {
            Logger.getLogger(PoiTrackerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

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
