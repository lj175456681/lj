package excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Title: Demo4
 * Description:
 * author: liujie
 * date: 2017-09-14 下午5:41
 */
public class Demo4 {

    public static final String path = "/Users/lj_free/Documents/test6.xlsx";

    public static void main(String[] args) throws IOException {
        Workbook wb = new XSSFWorkbook(); //or new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");

        Row row = sheet.createRow((short) 1);
        Cell cell = row.createCell((short) 1);
        cell.setCellValue(new XSSFRichTextString("This is a test of merging"));

        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 2));

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(path);
        wb.write(fileOut);
        fileOut.close();
        wb.close();
    }
}
