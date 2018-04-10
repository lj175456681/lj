package excel;

import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Title: Demo3
 * Description: 图片的
 * author: liujie
 * date: 2017-09-14 下午2:55
 */
public class Demo3 {

    public static final String path = "/Users/lj_free/Documents/test.xlsx";

    public static final String img = "/Users/lj_free/Documents/data/qy/pdf/img/tradelogo.jpeg";

    public static void main(String[] args) throws IOException {

        //create a new workbook
        Workbook wb = new XSSFWorkbook(); //or new HSSFWorkbook();
        try {
            CreationHelper helper = wb.getCreationHelper();

            //add a picture in this workbook.
            InputStream is = new FileInputStream(img);
            byte[] bytes = IOUtils.toByteArray(is);
            is.close();
            int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);

            //create sheet
            Sheet sheet = wb.createSheet();

            //create drawing
            Drawing<?> drawing = sheet.createDrawingPatriarch();

            //add a picture shape
            ClientAnchor anchor = helper.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(0);
            Picture pict = drawing.createPicture(anchor, pictureIdx);

            //auto-size picture
            pict.resize(5);

            OutputStream fileOut = new FileOutputStream(path);
            try {
                wb.write(fileOut);
            } finally {
                fileOut.close();
            }
        } finally {
            wb.close();
        }
    }
}
