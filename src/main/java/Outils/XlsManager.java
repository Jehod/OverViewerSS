/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Outils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
 * @author nrochas
 */
public class XlsManager {

    /**
     * Creer un style bold pour le titre
     *
     * @param workbook
     * @return
     */
    private CellStyle createStyleForTitle(Workbook workbook) {
        Font font = workbook.createFont();
        font.setBold(true);
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    /**
     *
     * @param wb
     * @param dayDate
     * @return
     */
    public Sheet createEmptyTrackSheet(Workbook wb, String dayDate) {
        Sheet sheet = wb.createSheet("Track " + dayDate);

        int rownum = 0;
        Cell cell;
        Row row;
        boolean bob = false;

        CellStyle style = createStyleForTitle(wb);

        row = sheet.createRow(rownum);

        // EmpName
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Questionnary");
        cell.setCellStyle(style);
        // Salary
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Version");
        cell.setCellStyle(style);
        // Grade
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("DateOfTrack");
        cell.setCellStyle(style);
        // Bonus
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("SendToRws");
        cell.setCellStyle(style);

        return sheet;
    }

    /**
     * creer un fichier de tracker en xlsx avec une sheet(0) appelé Track + la
     * date donnée en param
     *
     * @param path attention a ajouter le "/" dans l'appel du parametre
     * @param titre titre du fichier
     *
     * @return true si la creation a reussi
     */
    public boolean createXLSXTracker(String path, String titre) throws IOException {
        Workbook wb;
        boolean bob = false;
        String fileName = path + "Tracker_" + titre + ".xlsx";

        File file = new File(fileName);
//        file.getParentFile().mkdirs();

        FileOutputStream outFile = null;
        
        try { 
             bob =FilesWorker.editFiles(fileName, file);
            wb = WorkbookFactory.create(file);
            createEmptyTrackSheet(wb, "date");
            
            //outFile = new FileOutputStream(file);
           
           
            
            
            bob = true;
            System.out.println("Created file: " + file.getAbsolutePath());
            //outFile.close();

        } catch (IOException ex) {
            bob = false;
            System.out.println(ex +" dans "+ XlsManager.class.getName());
        } catch (InvalidFormatException ex)
        {
            Logger.getLogger(XlsManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EncryptedDocumentException ex)
        {
            Logger.getLogger(XlsManager.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return bob;

    }

    public String VersionCatcher(String fileName) throws FileNotFoundException, IOException {
        String version = "error";

        Workbook wb = null;
        //File file = new File(fileName);
        FileInputStream file = new FileInputStream(fileName);
        System.out.println("file is: " + file.toString());

        try {
            //FileInputStream inputStream = new FileInputStream(file);
            //wb = new XSSFWorkbook(inputStream);
            wb = WorkbookFactory.create(file);
            System.out.println("factory Ok for " + file.toString());
            file.close();

        } catch (FileNotFoundException ex) {
            System.out.println("probleme pour trouver le excel " + ex);
        } catch (Exception ex) {
            System.out.println("probleme de Workbook " + ex);
        } finally {
            file.close();
        }

        // Get first sheet from the workbook
        Sheet sheet = wb.getSheetAt(0);

        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = sheet.iterator();
        Row row = sheet.getRow(0);

        //modif here
        while (row.getCell(0) != null && row.getCell(0).getCellTypeEnum() == CellType.STRING) {

            version = row.getCell(0).getStringCellValue();
            row = rowIterator.next();

        }

        return version;

    }

    public void updateExcel(String pathName) throws IOException {

        File file = new File(pathName);
        Workbook wb = null;
        // Read XSL file
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            // Get the workbook instance for XLS file
            wb = WorkbookFactory.create(inputStream);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XlsManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XlsManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex)
        {
            Logger.getLogger(XlsManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EncryptedDocumentException ex)
        {
            Logger.getLogger(XlsManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Get first sheet from the workbook
        Sheet sheet = wb.getSheetAt(0);

        Cell cell = sheet.getRow(1).getCell(2);
        cell.setCellValue(cell.getNumericCellValue() * 2);

        cell = sheet.getRow(2).getCell(2);
        cell.setCellValue(cell.getNumericCellValue() * 2);

        cell = sheet.getRow(3).getCell(2);
        cell.setCellValue(cell.getNumericCellValue() * 2);

        try {
            inputStream.close();

        } catch (IOException ex) {
            Logger.getLogger(XlsManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Write File
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            wb.write(out);
            out.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XlsManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XlsManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }

    }

    /**
     * rempli une sheet date et ecrase si une existe deja sur le meme jour donne
     * le qi et la version. a vocation a etre present 1 par langue
     *
     * @param pathName
     * @param date
     * @param listVersion
     * @return
     * @throws java.io.IOException
     */
    public boolean fillTodayTrackerSheet(String pathName, String date, ArrayList<String> listVersion) throws IOException {

        boolean bob = false;
        Workbook wb = null;
        Sheet sheet;
           String str="";
       // File file = new File(pathName);
        //.out.println("file to fill is: " + file.getName());

       /* FileInputStream ins = null;
        try {

            ins = new FileInputStream(pathName);
            //wb = new XSSFWorkbook(inputStream);
            wb = WorkbookFactory.create(ins);
            System.out.println("factory Ok for ");

            ins.close();

        } catch (FileNotFoundException ex) {

            System.out.println("probleme pour trouver le tracker " + ex);
        } catch (Exception ex) {
            System.out.println("probleme de Workbook " + ex);
        } finally {
            ins.close();
        }

        if (wb.getSheet("Track " + date) == null) {
            sheet = createEmptyTrackSheet(wb, date);
        } else {
            sheet = wb.getSheet("Track " + date);
        }*/

        int i = 1;
        for (String qiVersion : listVersion) {
            System.out.println("QiVersion: " + qiVersion);
      
         String[] parts = qiVersion.split("#");
         str = str + " langue: "+parts[0]+ " QI: "+parts[1]+ " Version: "+parts[2]+ " Date: "+date;
         str = str +"\r";
        
        }
         File ff = new File(pathName);
         ff.createNewFile();
        try (FileWriter ffw = new FileWriter(ff)) {
            ffw.write(str);
            ffw.close();
        }
        
            
/*  Parti a reprendre en excel apres
            //preparation du remplissage
            Row row = sheet.createRow(i);
            Cell cellQi = row.createCell(0);
            Cell cellVers = row.createCell(1);
            Cell cellDat = row.createCell(2);
            //split la concatenation recupere de la liste
            String[] parts = qiVersion.split("#");
            System.out.println("list: " + parts[0]);//+" "+split[1]+" "+split[2]);
            cellQi.setCellValue(parts[1]);
            cellVers.setCellValue(parts[2]);
            cellDat.setCellValue(date);
            i++;
        }

        FileOutputStream outFile = null;
        try {
            outFile = new FileOutputStream(pathName);

            wb.write(outFile);
            bob = true;
            //System.out.println("Created file: " + outFile.toString());

            outFile.close();
        } catch (IOException ex) {

            System.out.println(ex + XlsManager.class.getName());
        } finally {
            outFile.close();
        }*/

        return bob;

    }
}
