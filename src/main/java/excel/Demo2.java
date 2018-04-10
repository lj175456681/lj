package excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Title: Demo2
 * Description:
 * author: liujie
 * date: 2017-09-14 下午2:53
 */
public class Demo2 {

    public static final String path = "/Users/lj_free/Documents/test2.xlsx";

    public static void main(String[]args) throws IOException {
        Workbook wb = new XSSFWorkbook(); //or new HSSFWorkbook();
        CreationHelper creationHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");
        sheet.setDisplayGridlines(false);
        // Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow((short)0);
        // Create a cell and put a value in it.
        Cell cell = row.createCell((short)0);
        cell.setCellValue(1);

        //numeric value
        row.createCell(1).setCellValue(1.2);

        //plain string value
        row.createCell(2).setCellValue("This is a string cell");

        //rich text string
        RichTextString str = creationHelper.createRichTextString("Apache");
        Font font = wb.createFont();
        font.setItalic(true);
        font.setUnderline(Font.U_SINGLE);
        str.applyFont(font);
        row.createCell(3).setCellValue(str);

        //boolean value
        row.createCell(4).setCellValue(true);

        //formula
        row.createCell(5).setCellFormula("SUM(A1:B1)");

        //date
        CellStyle style = wb.createCellStyle();
        style.setDataFormat(creationHelper.createDataFormat().getFormat("m/d/yy h:mm"));
        cell = row.createCell(6);
        cell.setCellValue(new Date());
        cell.setCellStyle(style);

        //hyperlink
        row.createCell(7).setCellFormula("SUM(A1:B1)");
        cell.setCellFormula("HYPERLINK(\"http://google.com\",\"Google\")");


        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(path);
        wb.write(fileOut);
        fileOut.close();
        wb.close();
    }



}
