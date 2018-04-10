package pdf;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.BaseFont;

/**
 * Title: TableTest
 * Description:
 * author: liujie
 * date: 2017-05-22 上午11:04
 */
public class TableTest {

    private String path = "/Users/lj_free/Documents/font";
    private Font songTi;
    private Font timesNewRoman;


    private void init(){
        FontFactory.register(path);
        songTi = FontFactory.getFont("times-roman", BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
        timesNewRoman = FontFactory.getFont("经典宋体简", BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
    }


    public static void main(String[] args) {
        TableTest t = new TableTest();
    }



}
