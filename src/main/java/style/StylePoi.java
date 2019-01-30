/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package style;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author nik
 */
public class StylePoi implements Style {

    final Workbook wb;
    final Sheet sheet;

    public StylePoi(Workbook wb, Sheet sheet) {
        super();
        this.wb = wb;
        this.sheet = sheet;
    }

    @Override
    public void createHeader(int index) {
        if (wb != null || sheet != null) {
            Cell cell;
            Row row;
            Font font = wb.createFont();
            font.setBold(true);
            CellStyle style = wb.createCellStyle();
            style.setFont(font);
            style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            int rownum = index;

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

            setAutoSizeCol(index);

        } else {
            System.out.println("le create title ne peut marcher car le workbook ou la sheet est nul");
        }

    }

    @Override
    public void pairStyle() {
        // Style

        final CellStyle pairStyle = wb.createCellStyle();
        pairStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        pairStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        Row row;
        int i = 1;

        row = sheet.getRow(i);
        while (row != null) {
            row = sheet.getRow(i);

            // Coloriage d'une ligne sur deux
            //attention a protéger contre les lignes vides 
            if (row != null && i % 2 == 0) {
                //on colorie chaque cellule blanche. les "non blanche" etant deja pointées pour une autre raison
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    if (row.getCell(j) != null && row.getCell(j).getCellStyle().getFillForegroundColor() == 64) {
                        row.getCell(j).setCellStyle(pairStyle);
                    }
                }
            }
            i++;
        }
    }

    @Override
    public void blocStyle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createBlocTitle(int index, String title) {
        final CellStyle style = wb.createCellStyle();

        Font font = wb.createFont();
        font.setBold(true);
        font.setFontHeight((short) 260);
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        Row row = sheet.createRow(index);
        Cell cell = row.createCell(0);
        sheet.addMergedRegion(new CellRangeAddress(index, index, 0, 6));
        cell.setCellValue(title);
        cell.setCellStyle(style);
    }

    @Override
    public void createTitle(String title) {
        final CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBold(true);
        font.setFontHeight((short) 280);
        style.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
        cell.setCellValue(title);
        cell.setCellStyle(style);
    }

    public void setAutoSizeCol(int index) {
        for (int i = 0; i < sheet.getRow(index).getLastCellNum(); i++) {
            sheet.autoSizeColumn(i);
        }

    }

    @Override
    public void colorCell() {
        // Style

        final CellStyle OkStyle = wb.createCellStyle();
        OkStyle.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex());
        OkStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        final CellStyle NoneStyle = wb.createCellStyle();
        NoneStyle.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
        NoneStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

       
        Row row;
        Row rowHead = sheet.getRow(0);
        int i = 1;

        row = sheet.getRow(i);
        while (row != null) {
            row = sheet.getRow(i);
           

            // Coloriage des cell dans les colonnes qui nous interresse
            //attention a protéger contre les lignes vides 
            if (row != null ) {
                //on colorie les cellules ok en couleur ok et les cellues none en style none.

                if (row.getCell(3) != null && row.getCell(3).getStringCellValue().equals("None")) {
                    row.getCell(3).setCellStyle(NoneStyle);
                } else if (row.getCell(3) != null && row.getCell(3).getStringCellValue().equals("ok")) {
                    row.getCell(3).setCellStyle(OkStyle);
                }
                

                i++;
            }
        }

    }

}
