/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Outils.DateManager;
import Outils.FilesWorker;
import entity.SimpleModifTrack;
import entity.SimpleRowTracker;
import entity.SimpleTracker;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author nik
 */
public class PoiTrackerDAO implements TrackerDAO
{

    final private String fileName;

    /**
     *
     * @param fileName
     *
     */
    public PoiTrackerDAO(String fileName)
    {
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
            final Sheet sheet = wb.createSheet("Track_" + date);
            createTitle(wb, sheet);

            int i = 1;
            for (SimpleRowTracker rtk : tck.getAllRowTracker())
            {
                System.out.println("dans la boucle: " + rtk.toString());
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
    public SimpleTracker createTrackerFromLabel(String dir)
    {

        SimpleTracker stk = new SimpleTracker();
        List<SimpleRowTracker> allMdT = new ArrayList<SimpleRowTracker>();

        //on list les excels de label presents 
        List<String> listXls = new ArrayList<>();
        System.out.println("filename: " + fileName);
        listXls = FilesWorker.ListerFilesByExtAndStart(fileName, "Label", ".xlsx");
        // PoiRowTrackerDAO prtk = new PoiRowTrackerDAO(path + pathLabels + dir + "\\Tracker_" + dir + ".xlsx");

        //pour chaque xls de label
        for (String xls : listXls)
        {
            PoiModifTrackDAO pmtk = new PoiModifTrackDAO(fileName + "\\" + xls);

            SimpleModifTrack smtk;
            smtk = pmtk.getLastModifTrack();
            allMdT.add(modifTrackToRowTracker(smtk));


            /* on list les lignes de tracker du fichiers excel
                List<SimpleModifTrack> allModifTrack = new ArrayList();
                allModifTrack = pmtk.findAllModifTrack();
             */
            System.out.println("xls traité: " + xls.toString() + " size: " + listXls.size());
        }

        stk = createTracker((ArrayList<SimpleRowTracker>) allMdT);

        return stk;
    }

    @Override
    public SimpleRowTracker modifTrackToRowTracker(SimpleModifTrack smtk)
    {
        SimpleRowTracker srtk = new SimpleRowTracker(smtk.getVersion(), smtk.getFormulaire(), smtk.getDate());
        System.out.println("RowTracker: "+srtk.toString());

        return srtk;
    }

    /**
     *
     * @param srtk
     * @param sheet
     * @param row
     */
    public void addRowTracker(SimpleRowTracker srtk, Row row)
    {

        row.createCell(0).setCellValue(srtk.getFormulaire());;
        row.createCell(1).setCellValue(srtk.getVersion());
        row.createCell(2).setCellValue(srtk.getDateVers());
        row.createCell(3).setCellValue(srtk.getScreenDone());
        row.createCell(4).setCellValue(srtk.getSendtoExt());
        row.createCell(5).setCellValue(srtk.getFinalized());
        row.createCell(6).setCellValue(srtk.getCertified());

    }

    @Override
    public SimpleTracker createTracker(ArrayList<SimpleRowTracker> allMdT)
    {
        SimpleTracker stk = new SimpleTracker();

        stk.setDate(new DateManager().getSimpleCurrentDate());
        stk.setName(fileName.substring(fileName.length() - 5, fileName.length()));
        stk.setAllRowTracker(allMdT);

        return stk;
    }

    @Override
    public boolean svgTracker(SimpleTracker stk)
    {
        boolean bob = false;
        Workbook wb = null;
        Sheet sheet = null;
        
        File file =null;

        //creer le workbook et la sheet
        try
        {
            
            System.out.println("trace: "+stk.toString());
            wb = new XSSFWorkbook();
//wb = WorkbookFactory.create(file);
           
            sheet = wb.createSheet(stk.getName() + "_" + stk.getDate());
            

        } catch (Exception ex)
        {
            System.out.println("catch du svgTracker " + ex.getLocalizedMessage());
        }

        // creer les cellules
        createTitle(wb, sheet);

        Row rows;
        int i = 1;
        for (SimpleRowTracker row : stk.getAllRowTracker())
        {
            rows = sheet.createRow(i);
            addRowTracker(row, rows);
            i++;
        }

        try
        {
            file = new File(fileName + "\\tracker.xlsx");
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

}
