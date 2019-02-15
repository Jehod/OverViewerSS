/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.SimpleRowTracker;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author nik
 */
public class PoiRowTrackerDAO implements RowTrackerDAO {

    private final String fileName;

    public PoiRowTrackerDAO(String fileName) {
        super();
        this.fileName = fileName;
    }

    @Override
    public List<SimpleRowTracker> findAllRowTracker() {

        final File file = new File(fileName);
        final List<SimpleRowTracker> allRow = new ArrayList<>();

        if (file.exists()) {

            try {
                final Workbook wb = WorkbookFactory.create(file);
                final Sheet sheet = wb.getSheetAt(0);

                int index = 1;
                Row row = sheet.getRow(index++);

                while (row != null) {
                    final SimpleRowTracker rtk = rowToRowTracker(row);
                    if (rtk != null){
                        allRow.add(rtk);
                    }
                    row = sheet.getRow(index++);
                }

            } catch (IOException e) {
                System.out.println("findAllRowTracker catch: " + e.getMessage());
            } catch (InvalidFormatException | EncryptedDocumentException ex) {
                System.out.println("findAllRowTracker catch: " + ex.getMessage());
                Logger.getLogger(PoiRowTrackerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Le tracker n'existe pas!!");
            allRow.add(new SimpleRowTracker());
        }

        return allRow;
    }

    private SimpleRowTracker rowToRowTracker(final Row row) {
        final SimpleRowTracker rtk = new SimpleRowTracker();

        final String langue = row.getCell(0).getStringCellValue().trim();
        //  rtk.setLangue(langue);
        final String formulaire = row.getCell(1).getStringCellValue();
        rtk.setFormulaire(formulaire.trim());
        final String version = row.getCell(2).getStringCellValue();
        rtk.setVersion(version.trim());

        return rtk;
    }

}
