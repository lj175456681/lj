package pdf;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Title: TradeAppPdfMaker
 * Description:
 * author: liujie
 * date: 2017-05-23 下午4:59
 */
public class TradeAppPdfMaker {

    public static final String DEST = "/Users/lj_free/Documents/test.pdf";
    public static final String SONG_TI = "/Users/lj_free/Documents/font/songti.TTF";
    public static final String NEW_ROMAN = "/Users/lj_free/Documents/font/TimesNewRoman.ttf";
    public static final String XIN_WEI = "/Users/lj_free/Documents/font/STXINWEI.TTF";

    private BaseFont song;
    private BaseFont baseFTE;
    private BaseFont bf;
    private Font firsetTitleFont;
    private Font textFont;
    private Font romanFont;
    private Font under;
    private Font under2;
    private Font bold;

    public TradeAppPdfMaker() throws IOException, DocumentException {
        // 添加中文字体
         song = BaseFont.createFont(SONG_TI, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        // 华文新魏
        // Times New Roman
        baseFTE = BaseFont.createFont(NEW_ROMAN, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        bf = BaseFont.createFont(BaseFont.COURIER, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
         firsetTitleFont = new Font(song, 22, Font.BOLD);
         textFont = new Font(song, 12, Font.NORMAL); //正常
         romanFont = new Font(baseFTE, 12, Font.NORMAL); //正常
         under = new Font(bf,12,Font.UNDERLINE);
         under2 = new Font(song,12,Font.UNDERLINE);
         bold = new Font(song, 12, Font.BOLD); //正常
    }






    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new PdfCreateTest().createPdf(DEST);
    }



    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();

        Paragraph p1 = new Paragraph("交易业务申请表 - 机构投资者", firsetTitleFont);
        p1.setAlignment(Element.ALIGN_CENTER);
        document.add(p1);
        document.add(Chunk.NEWLINE);

        Paragraph p2 = new Paragraph();
        p2.setAlignment(Element.ALIGN_LEFT);
        Chunk c1 = new Chunk("打印时间：",textFont);
        Chunk c2 = new Chunk("2017-08-03 18:35:12",romanFont);
        p2.add(c1);
        p2.add(c2);
        document.add(p2);
        document.add(Chunk.NEWLINE);



    }


}
