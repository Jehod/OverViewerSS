/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.interfaceDAO.ModifTrackDAO;
import Outils.DateManager;
import Outils.SVNWorker;
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
 *le modif track est l'image d'une ligne du cartouche de suivi de l'excel label
 * Nous utilisons que la derniere ligne du chartouche, c'est a dire la plus recente.
 * @author nik
 */
public class PoiModifTrackDAO implements ModifTrackDAO {

    private final String fileName;
    private final String formulaire;
    private  File file;

  /*  public PoiModifTrackDAO(String fileName) {
        super();
        this.fileName = fileName;
        formulaire = recupNomForm(this.fileName);

    }*/
    
    public PoiModifTrackDAO(String path, String fileXls, File file)
    {
        super();
        fileName = path+fileXls;
         formulaire = recupNomForm(this.fileName);
         this.file = file;
    }

    @Override
    public List<SimpleModifTrack> findAllModifTrack() {
       // final File file = new File(fileName);
        
        final List<SimpleModifTrack> allRow = new ArrayList<>();

        try {
            final Workbook wb = WorkbookFactory.create(file);
            final Sheet sheet = wb.getSheetAt(0);

            int index = 1;
            Row row = sheet.getRow(index++);

            // on teste l'existence de la ligne, puis l'existence de la cellule puis le remplissage de la cellule et enfin son type 
            // Attention en POI les lignes et les cellules n'existent pas sans type et feront planté si on tente d'y acceder 
            while (row != null && row.getCell(0) != null && row.getCell(0).getCellTypeEnum() != CellType.BLANK && row.getCell(0).getCellTypeEnum() == CellType.STRING) {

                final SimpleModifTrack mtk = rowToModifTrack(row, formulaire);

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

    private SimpleModifTrack rowToModifTrack(Row row, String formulaire) {
        final SimpleModifTrack rtk = new SimpleModifTrack();
        String date = "88/88/2019";
        String action = "action illisible";
        String version = "0.0.0";
        String contributor = "contributeur illisible";

        rtk.setFormulaire(formulaire);

        System.out.println("plooop");
        // recup de la date.  avec une protection suplementaire pour format de cellule string ou Date
        if (row.getCell(3) != null || null != row.getCell(3).getCellTypeEnum()) {
            switch (row.getCell(3).getCellTypeEnum()) {
                case NUMERIC: {
                    date = new DateManager().getSimpleDate(row.getCell(3).getDateCellValue());
                    break;
                }
                case STRING: {
                    date = new DateManager().getConvertDate(row.getCell(3).getStringCellValue());
                    break;
                }
                default:
                    date = "99/99/2019";
                    break;
            }
        }
        rtk.setDate(date.trim());

        ///recup du contributeur
        if (row.getCell(1) != null || row.getCell(1).getStringCellValue() != null) {
            contributor = row.getCell(1).getStringCellValue();
        }
        rtk.setContributor(contributor.trim());

        //recup de la version
        if (row.getCell(0)!= null || row.getCell(0).getStringCellValue() != null) {
            version = row.getCell(0).getStringCellValue();
        }
        rtk.setVersion(version.trim());

        // recup de l'action
        if (row.getCell(2) != null && row.getCell(2).getStringCellValue() != null) {
             action = row.getCell(2).getStringCellValue();
        }
        rtk.setAction(action.trim());

        return rtk;
    }

    @Override
    public SimpleModifTrack getLastModifTrack() {
       // final File file = new File(fileName);
         
        SimpleModifTrack mtk = null;

        try {
            
            final Workbook wb = WorkbookFactory.create(file);
            final Sheet sheet = wb.getSheetAt(0);
            
            int index = 1;
            Row row = sheet.getRow(index++);

            // test l'existence de la ligne, puis de la cell, puis sont type
            while (row != null && row.getCell(0) != null && row.getCell(0).getCellTypeEnum() == CellType.STRING) {

                mtk = rowToModifTrack(row, formulaire);

                row = sheet.getRow(index++);
            }

        } catch (IOException e) {
            System.out.println("findAllRowTracker catch: " + e.getMessage());
        } catch (InvalidFormatException | EncryptedDocumentException ex) {
            System.out.println("mutlicatch getlastmidtr: " + ex.getLocalizedMessage());
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

    /**
     * triture le path pour en tirer le nom du questionnaire contenu dans le nom
     * du fichier excel
     *
     * @param fileName le path
     * @return le nom du qestionnaire
     */
    private String recupNomForm(String fileName) {
        String form = "erreur";
        String[] split;
        if (fileName.contains("Label")) {
            split = fileName.split("Label_");
            form = split[1].substring(0, split[1].length() - 11);
        } else if (fileName.contains("Labels")) {
            split = fileName.split("Labels_");
            form = split[1].substring(0, split[1].length() - 11);
        }

        return form;

    }

}
