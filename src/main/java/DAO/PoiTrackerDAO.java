/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.RowTracker;
import entity.SimpleRowTracker;
import entity.SimpleTracker;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author nik
 */
public class PoiTrackerDAO implements TrackerDAO
{

    private String fileName;

    public PoiTrackerDAO(String fileName)
    {
        super();
        this.fileName = fileName;
    }

    @Override
    public List<SimpleTracker> readAllTracker()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean addTracker(SimpleTracker tck)
    {
        boolean bob = false;
        String date = new Outils.DateManager().getSimpleCurrentDate();
        
         final File fileTemp = new File("HR_HR/TrackerTemp.xlsx");
        if (fileTemp.exists())
        {
            fileTemp.delete();
        }

        final File file = new File(fileName);

        Workbook wb = null;
        try
        {
            wb = WorkbookFactory.create(file);
            final Sheet sheet = wb.createSheet("Track_"+date);
            createTitle(wb, sheet);

            int i = 1;
            for (RowTracker rtk : tck.getAllRowTracker())
            {
                System.out.println("dans la boucle: "+rtk.toString());
                Row row = sheet.createRow(i);
                row.createCell(0).setCellValue(rtk.getFormulaire());
                row.createCell(1).setCellValue(rtk.getVersion());
                i++;
            }
            bob = true;

        } catch (IOException ex)
        {

            Logger.getLogger(PoiTrackerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex)
        {
            Logger.getLogger(PoiTrackerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EncryptedDocumentException ex)
        {
            Logger.getLogger(PoiTrackerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

       
        try
        {
            final FileOutputStream fos = new FileOutputStream(fileTemp);
            wb.write(fos);
            fos.close();
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(PoiTrackerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(PoiTrackerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        return bob;

    }

    @Override
    public SimpleTracker getLastTracker()
    {
        SimpleTracker tck = new SimpleTracker();

        PoiRowTrackerDAO prtk = new PoiRowTrackerDAO(fileName);
        tck.setAllRowTracker((ArrayList<SimpleRowTracker>) prtk.findAllRowTracker());
        tck.setName(getLastTrackerName());
        tck.setDate("TestDate");

        return tck;
    }

    @Override
    public String getLastTrackerDate()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void createTitle(Workbook wb, Sheet sheet)
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
        cell.setCellValue("Screenshot");
        cell.setCellStyle(style);
        // send
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("SendToExt");
        cell.setCellStyle(style);
        //final 
        cell = row.createCell(5, CellType.BOOLEAN);
        cell.setCellValue("Final");
        cell.setCellStyle(style);
        //certified 
        cell = row.createCell(5, CellType.BOOLEAN);
        cell.setCellValue("Certified");
        cell.setCellStyle(style);

    }

    @Override
    public String getLastTrackerName()
    {
        final File file = new File(fileName);
        Workbook wb;
        Sheet sheet = null;
        try
        {
            wb = WorkbookFactory.create(file);
            sheet = wb.getSheetAt(0);

        } catch (IOException ex)
        {
            Logger.getLogger(PoiTrackerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex)
        {
            Logger.getLogger(PoiTrackerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EncryptedDocumentException ex)
        {
            Logger.getLogger(PoiTrackerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sheet.getSheetName();

    }

    @Override
    public boolean createTracker() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
