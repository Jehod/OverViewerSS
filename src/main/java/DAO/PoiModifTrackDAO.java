/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Outils.DateManager;
import entity.SimpleModifTrack;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author nik
 */
public class PoiModifTrackDAO implements ModifTrackDAO {

    private String fileName;

    public PoiModifTrackDAO(String fileName) {
        super();
        this.fileName = fileName;
    }

    @Override
    public List<SimpleModifTrack> findAllModifTrack() {
        final File file = new File(fileName);
        final List<SimpleModifTrack> allRow = new ArrayList<SimpleModifTrack>();

        try {
            final Workbook wb = WorkbookFactory.create(file);
            final Sheet sheet = wb.getSheetAt(0);

            int index = 1;
            Row row = sheet.getRow(index++);

            
            // on teste l'existence de la ligne, puis l'existence de la cellule puis le remplissage de la cellule et enfin son type 
            // Attention en POI les lignes et les cellules n'existent pas sans type et feront planté si on tente d'y acceder 
            while (row != null && row.getCell(0) != null && row.getCell(0).getCellTypeEnum() != CellType.BLANK && row.getCell(0).getCellTypeEnum() == CellType.STRING) {

                final SimpleModifTrack mtk = rowToModifTrack(row);

                allRow.add(mtk);

                row = sheet.getRow(index++);

            }

        } catch (IOException e) {
            System.out.println("findAllModifTracker catch: " + e.getMessage());
        } catch (InvalidFormatException ex) {
            System.out.println("catch finallModifTacker2 : " + ex.getMessage());
            Logger.getLogger(PoiRowTrackerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EncryptedDocumentException ex) {
            System.out.println("catch finallModifTacker3 : " + ex.getMessage());
            Logger.getLogger(PoiRowTrackerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return allRow;
    }

    private SimpleModifTrack rowToModifTrack(Row row) {
        final SimpleModifTrack rtk = new SimpleModifTrack();

        final String date = new DateManager().getSimpleDate(row.getCell(3).getDateCellValue());
        rtk.setDate(date);
        final String contributor = row.getCell(1).getStringCellValue();
        rtk.setContributor(contributor);
        final String version = row.getCell(0).getStringCellValue();
        rtk.setVersion(version);
        final String action = row.getCell(2).getStringCellValue();
        rtk.setAction(action);

        return rtk;
    }

    @Override
    public SimpleModifTrack getLastModifTrack() {
        final File file = new File(fileName);
        SimpleModifTrack mtk = null;

        try {
            final Workbook wb = WorkbookFactory.create(file);
            final Sheet sheet = wb.getSheetAt(0);

            int index = 1;
            Row row = sheet.getRow(index++);

            // test l'existence de la ligne, puis de la cell, puis sont type
            while (row != null && row.getCell(0) != null && row.getCell(0).getCellTypeEnum() == CellType.STRING) {

                mtk = rowToModifTrack(row);

                row = sheet.getRow(index++);
            }

        } catch (IOException e) {
            System.out.println("findAllRowTracker catch: " + e.getMessage());
        } catch (InvalidFormatException | EncryptedDocumentException ex) {
            System.out.println("mutlicatch getlastmidtr: "+ ex.getLocalizedMessage());
        }

        return mtk;
    }

    @Override
    public String getLastVersion() {
        return getLastModifTrack().getVersion();
    }

    @Override
    public String getLastDate() {
        return getLastModifTrack().getDate();
    }

    @Override
    public String getLastContributor() {
        return getLastModifTrack().getContributor();
    }

    @Override
    public String getLastAction() {
        return getLastModifTrack().getAction();
    }

}
