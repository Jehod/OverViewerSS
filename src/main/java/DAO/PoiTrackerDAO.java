/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.interfaceDAO.TrackerDAO;
import Outils.DateManager;
import Outils.FilesWorker;
import Outils.SVNWorker;
import com.JehodFactory.overviewerss.Params;
import entity.SimpleModifTrack;
import entity.SimpleRowTracker;
import entity.SimpleTracker;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import style.StylePoi;

/**
 *
 * @author nik
 */
public class PoiTrackerDAO implements TrackerDAO {
    
    final private String pathTEMP = Params.getInstance().getPathTEMP();
    final private String fileName;
    private boolean bob;

    /**
     *
     * @param fileName
     *
     */
    public PoiTrackerDAO(String fileName) {

        this.fileName = fileName;

    }

    @Override
    public List<SimpleTracker> readAllTracker() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SimpleTracker getLastTracker() {
        SimpleTracker tck = new SimpleTracker();

        PoiRowTrackerDAO prtk = new PoiRowTrackerDAO(fileName);
        tck.setAllRowTracker((ArrayList<SimpleRowTracker>) prtk.findAllRowTracker());
        tck.setName(getLastTrackerName());
        tck.setDate("TestDate");

        return tck;
    }

    @Override
    public String getLastTrackerDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLastTrackerName() {
        final File file = new File(fileName);
        Workbook wb;
        Sheet sheet = null;
        try {
            wb = WorkbookFactory.create(file);
            sheet = wb.getSheetAt(0);

        } catch (IOException | InvalidFormatException | EncryptedDocumentException ex) {
            Logger.getLogger(PoiTrackerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sheet.getSheetName();

    }

    @Override
    public SimpleTracker createTrackerFromLabel(String dir) {

        SimpleTracker stk;
        List<SimpleRowTracker> allMdT = new ArrayList<>();
        Boolean findTrain = false;

        //on list les excels de label presents 
        List<String> listXls = new ArrayList<>();
        System.out.println("filename: " + fileName);
        listXls = FilesWorker.ListerFilesByExtAndStart(fileName, "Label", ".xlsx");
        // PoiRowTrackerDAO prtk = new PoiRowTrackerDAO(path + pathLabels + dir + "\\Tracker_" + dir + ".xlsx");

        //pour chaque xls de label
        for (String xls : listXls) {
            // PoiModifTrackDAO pmtk = new PoiModifTrackDAO(fileName + "\\" + xls);

            PoiModifTrackDAO pmtk = new PoiModifTrackDAO(fileName, xls, new File(fileName + "\\" + xls));

            //on note l'existence d'un training.
            if (xls.toLowerCase().contains("train")) {
                findTrain = true;
            }

            //creation de la ligne
            SimpleModifTrack smtk;
            smtk = pmtk.getLastModifTrack();
            
            if (smtk.getFormulaire().equals("PARAM") || smtk.getFormulaire().contains("PFT")) {
                System.out.println("les pft et le param ne sont pas pris");
            } else {
                allMdT.add(modifTrackToRowTracker(smtk));
            }

            System.out.println("xls traité: " + xls + " size: " + listXls.size());
        }

        //on ajoute un train si il ne l'a pas trouvé
        if (!findTrain) {
            SimpleModifTrack smtk;
            smtk = new SimpleModifTrack("Training", new DateManager().getSimpleCurrentDate(), "Kayentis", "1.0.0", "Auto-creation");
            allMdT.add(modifTrackToRowTracker(smtk));
        }

        stk = createTracker((ArrayList<SimpleRowTracker>) allMdT);

        return stk;
    }

    @Override
    public SimpleRowTracker modifTrackToRowTracker(SimpleModifTrack smtk) {
        SimpleRowTracker srtk = new SimpleRowTracker(smtk.getVersion(), smtk.getFormulaire(), smtk.getDate());
        System.out.println("RowTracker: " + srtk.toString());

        return srtk;
    }

    /**
     *
     * @param lang langue du tracker ne sert que dans le studytracker
     * @param srtk la rowtracker
     * @param row la ligne cible
     */
    public void addRowTracker(String lang, SimpleRowTracker srtk, Row row) {
        //la numerotation est en variable pour pouvoir bouger plus rapidement si modif 
        int colNum = 0;
        if (lang != null) {
            row.createCell(0).setCellValue(lang);
            colNum++;
        }

        row.createCell(colNum).setCellValue(srtk.getFormulaire());
        row.createCell(colNum + 1).setCellValue(srtk.getVersion());
        row.createCell(colNum + 2).setCellValue(srtk.getDateVers());
        row.createCell(colNum + 3).setCellValue(srtk.getScreenDone());
        row.createCell(colNum + 4).setCellValue(srtk.getCertified());

    }

    @Override
    public SimpleTracker createTracker(ArrayList<SimpleRowTracker> allMdT) {
        SimpleTracker stk = new SimpleTracker();

        stk.setDate(new DateManager().getSimpleCurrentDate());
        stk.setName(fileName.substring(fileName.length() - 5, fileName.length()));
        stk.setAllRowTracker(allMdT);

        return stk;
    }

    @Override
    public boolean svgTracker(SimpleTracker stk) {
        bob = false;
        Workbook wb = null;
        Sheet sheet = null;

        File file = null;

        //creer le workbook et la sheet
        try {

            System.out.println("trace: " + stk.toString());
            wb = new XSSFWorkbook();
//wb = WorkbookFactory.create(file);

            sheet = wb.createSheet(stk.getName() + "_" + stk.getDate());

        } catch (Exception ex) {
            System.out.println("catch du svgTracker " + ex.getLocalizedMessage());
        }

        // creer les cellules
        style.StylePoi style = new StylePoi(wb, sheet);
        style.createHeader(0, 0);

//createTitleTracker(wb, sheet);
        Row rows;
        int i = 1;
        for (SimpleRowTracker row : stk.getAllRowTracker()) {
            rows = sheet.createRow(i);
            addRowTracker(null, row, rows);
            i++;
        }

        //apply decoration
        System.out.println("apply decoration");
        style.pairStyle();
        style.colorCell();

        try {
            file = new File(fileName + "\\TRACKER.xlsx");
            FileOutputStream outFile = new FileOutputStream(file);
            wb.write(outFile);
            //outFile.close();
            bob = true;
        } catch (Exception ex) {
            Logger.getLogger(PoiTrackerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bob;

    }

    public SimpleTracker createTrackerFromSVNLabel(String dir) {
        SimpleTracker stk;
        List<SimpleRowTracker> allMdT = new ArrayList<>();
        Boolean findTrain = false;
        SVNWorker svn = new SVNWorker();
       

        //on list les excels de label presents 
        List<String> listXls;
        System.out.println("filename: " + fileName);

       
        listXls = svn.listSVNByExtAndStart(fileName, "Label", ".xlsx");

        //pour chaque xls de label
        for (String xls : listXls) {
            
            File file = svn.copyInTempLocal(fileName, xls, pathTEMP);
            
            PoiModifTrackDAO pmtk = new PoiModifTrackDAO(pathTEMP, xls, file);

            //on note l'existence d'un training.
            if (xls.toLowerCase().contains("train")) {
                findTrain = true;
            }

            //creation de la ligne
            SimpleModifTrack smtk;
            smtk = pmtk.getLastModifTrack();
            if (smtk.getFormulaire().equals("PARAM") || smtk.getFormulaire().contains("PFT")) {
                System.out.println("les pft et le param ne sont pas pris");
            } else {
                allMdT.add(modifTrackToRowTracker(smtk));
            }

            file.deleteOnExit();
            
            System.out.println("xls traité: " + xls.toString() + " size: " + listXls.size());
        }

        //on ajoute un train si il ne l'a pas trouvé
        if (!findTrain) {
            SimpleModifTrack smtk;
            smtk = new SimpleModifTrack("Training", new DateManager().getSimpleCurrentDate(), "Kayentis", "1.0.0", "Auto-creation");
            allMdT.add(modifTrackToRowTracker(smtk));
        }

        stk = createTracker((ArrayList<SimpleRowTracker>) allMdT);

        return stk;
    }

}
