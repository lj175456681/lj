package pdf;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Title: PdfCreateTest
 * Description:
 * author: liujie
 * date: 2017-05-19 下午3:00
 */
public class PdfCreateTest {

    public static final String DEST = "/Users/lj_free/Documents/test.pdf";
    public static final String SONG_TI = "/Users/lj_free/Documents/data/qy/pdf/font/simsun.ttf";
    public static final String NEW_ROMAN = "/Users/lj_free/Documents/data/qy/pdf/font/TimesNewRoman.ttf";


    public static final String[] BANK_DETAIL = new String[]{
            "银" + "\n" + "行" + "\n" + "账" + "\n" + "号",
            "4000 0405 2920 0414 049",
            "省/市",
            "广东省/深圳市",
            "银行户名",
            "上海华信证券有限责任公司销售专户",
            "开户银行",
            "中国工商银行",
            "支行名称",
            "中国工商银行股份有限公司深圳星河支行",
            "联行号",
            "102584004055"
    };

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new PdfCreateTest().createPdf(DEST);

//        new PdfCreateTest().create2();
    }


    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4, 50, 50, 10, 50);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        // 添加中文字体
        BaseFont song = BaseFont.createFont(SONG_TI, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        // 华文新魏
        // Times New Roman
        BaseFont baseFTE = BaseFont.createFont(NEW_ROMAN, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        BaseFont bf = BaseFont.createFont(BaseFont.COURIER, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);

        Font firsetTitleFont = new Font(song, 18, Font.BOLD);
        Font textFont = new Font(song, 10, Font.NORMAL); //正常
        Font romanFont = new Font(baseFTE, 10, Font.NORMAL); //正常
        Font under = new Font(bf,10,Font.UNDERLINE);
        Font under2 = new Font(song,10,Font.UNDERLINE);
        Font bold = new Font(song, 10, Font.BOLD); //正常


        //图片
        PdfPTable tableimg = new PdfPTable(1);
        tableimg.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableimg.setSpacingAfter(100);
        tableimg.setWidthPercentage(40);
        Image img = Image.getInstance("/Users/lj_free/Documents/data/qy/pdf/img/etplogo.jpeg");
        img.setScaleToFitHeight(true);
        img.setAlignment(Element.ALIGN_LEFT);
        img.scalePercent(40);
        PdfPCell cellimg = new PdfPCell(img, true);
        cellimg.setHorizontalAlignment(Element.ALIGN_LEFT);
        cellimg.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellimg.setUseAscender(true);
        cellimg.setBorder(Rectangle.NO_BORDER);
        tableimg.addCell(cellimg);
        document.add(tableimg);


        // 大标题
        Paragraph p1 = new Paragraph("交易业务申请表 - 机构投资者", firsetTitleFont);
        p1.setLeading(20);
        p1.setAlignment(Element.ALIGN_CENTER);
        document.add(p1);

        document.add(Chunk.NEWLINE);

        Paragraph p2 = new Paragraph();
        p2.setSpacingBefore(20);
        p2.setSpacingAfter(5);
        p2.setAlignment(Element.ALIGN_LEFT);
        Chunk c1 = new Chunk("打印时间：",textFont);
        Chunk c2 = new Chunk("2017-08-03 18:35:12",romanFont);
        p2.add(c1);
        p2.add(c2);
        document.add(p2);


        Chunk glue = new Chunk(new VerticalPositionMark());

        Paragraph p3 = new Paragraph();
        p3.setSpacingBefore(5);
        p3.setAlignment(Element.ALIGN_LEFT);
        Chunk c3 = new Chunk("申请时间：",textFont);
        Chunk c4 = new Chunk("2017-08-03 18:35:12",under);
        Chunk c5 = new Chunk("流水号：",textFont);
        Chunk c6 = new Chunk("QYTD201705231121459659",under);
        p3.add(c3);
        p3.add(c4);
        p3.add(glue);
        p3.add(c5);
        p3.add(c6);
        document.add(p3);


        PdfPTable table = new PdfPTable(1);
        table.setSpacingAfter(10);
        table.setSpacingBefore(20);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        //设置为无边框
        table.setWidthPercentage(100);


        PdfPCell cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setUseAscender(true);
        cell.setPaddingBottom(10);
        cell.setPaddingTop(10);
        cell.setPaddingLeft(10);
        cell.setPaddingRight(10);

        Paragraph p4 = new Paragraph();
        p4.setSpacingAfter(10);
        p4.add(new Chunk(" \u25c6 申请人基本信息",bold));
        p4.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(p4);

        PdfPTable table2 = new PdfPTable(2);
        table2.setWidths(new int[]{55,45});
        table2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.setWidthPercentage(100);

        Phrase p12 = new Phrase();
        p12.add(new Chunk("客户名称  ",textFont));
        Chunk cs = new Chunk("上海华信证券",under2);
        p12.add(cs);
        PdfPCell cell2 = createCell(p12);
        table2.addCell(cell2);

        Phrase p13 = new Phrase();
        p13.add(new Chunk("客户证件号码 ",textFont));
        p13.add(new Chunk("398750023嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻",under2));
        PdfPCell cell3 = createCell(p13);
        table2.addCell(cell3);

        Phrase p14 = new Phrase();
        p14.add(new Chunk("基金账号  ",textFont));
        p14.add(new Chunk("100008030112",under));
        PdfPCell cell4 = createCell(p14);
        table2.addCell(cell4);

        Phrase p15 = new Phrase();
        p15.add(new Chunk("交易账号 ",textFont));
        p15.add(new Chunk("0008030112",under));
        PdfPCell cell5 = createCell(p15);
        table2.addCell(cell5);

        Phrase p16 = new Phrase();
        p16.add(new Chunk("管理员姓名 ",textFont));
        p16.add(new Chunk("刘杰",under2));
        PdfPCell cell6 = createCell(p16);
        table2.addCell(cell6);

        Phrase p17 = new Phrase();
        p17.add(new Chunk("管理员证件号码 ",textFont));
        p17.add(new Chunk("420801198708030112",under));
        PdfPCell cell7 = createCell(p17);
        table2.addCell(cell7);

        cell.addElement(table2);

        table.addCell(cell);
        document.add(table);

        //申购的表格
        PdfPTable table3 = new PdfPTable(15);
        table3.setWidths(new int[]{3,6,3,1,1,1,1,1,1,1,1,1,1,1,1});

        table3.setSpacingAfter(10);
        table3.setSpacingBefore(10);
        table3.setHorizontalAlignment(Element.ALIGN_LEFT);
        //设置为无边框
        table3.setWidthPercentage(100);

        PdfPCell cell31 = new PdfPCell();
        cell31.setRowspan(4);
        cell31.setPaddingTop(10);
        cell31.setPaddingBottom(10);
        cell31.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell31.setHorizontalAlignment(Element.ALIGN_CENTER);

        Paragraph p31 = new Paragraph();
        p31.setAlignment(Element.ALIGN_CENTER);
        p31.add(new Chunk(" \u25c6 认 购",bold));
        p31.add(Chunk.NEWLINE);
        p31.add(Chunk.NEWLINE);
        p31.add(new Chunk(" \u25c6 申 购",bold));
        cell31.addElement(p31);
        table3.addCell(cell31);

        PdfPCell cell32 = new PdfPCell();
        cell32.setRowspan(4);
        cell32.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell32.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell32.setPaddingBottom(10);
        Paragraph p32 = new Paragraph();
        p32.setAlignment(Element.ALIGN_LEFT);
        p32.add(new Chunk("资产管理计划名称:",textFont));
        p32.add(Chunk.NEWLINE);
        p32.add(new Chunk("现金管理一号理财方案呵呵呵",textFont));
        p32.add(Chunk.NEWLINE);
        p32.add(new Chunk("代码:",textFont));
        p32.add(Chunk.NEWLINE);
        p32.add(new Chunk("448220",textFont));
        cell32.addElement(p32);
        table3.addCell(cell32);

        PdfPCell cell33 = new PdfPCell();
        cell33.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell33.setRowspan(2);
        cell33.setPaddingBottom(10);
        Paragraph p33 = new Paragraph();
        p33.setAlignment(Element.ALIGN_CENTER);
        p33.add(new Chunk("金额",textFont));
        p33.add(Chunk.NEWLINE);
        p33.add(new Chunk("（大写）",textFont));
        cell33.addElement(p33);
        table3.addCell(cell33);

        PdfPCell cell34 = new PdfPCell();
        cell34.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell34.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell34.setRowspan(2);
        cell34.setColspan(12);
        Paragraph p34 = new Paragraph();
        p34.setAlignment(Element.ALIGN_CENTER);
        p34.add(new Chunk("壹拾捌万捌仟捌佰贰拾壹圆贰角叁分",textFont));
        cell34.addElement(p34);
        table3.addCell(cell34);

        PdfPCell cell35 = new PdfPCell();
        cell35.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell35.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell35.setRowspan(2);
        Paragraph p35 = new Paragraph();
        p35.setAlignment(Element.ALIGN_CENTER);
        p35.add(new Chunk("金 额",textFont));
        p35.add(Chunk.NEWLINE);
        p35.add(new Chunk("（小写）",textFont));
        cell35.addElement(p35);
        table3.addCell(cell35);

        String[] s1 = new String[]{"十","亿","千","百","十","万","千","百","十","元","角","分"};
        String[] s2 = new String[]{"0","0","1","2","0","2","5","2","0","0","0","0"};
        createDigital(s1,table3,textFont);
        createDigital(s2,table3,textFont);
        document.add(table3);


        Paragraph p100 = new Paragraph();
        p100.setAlignment(Element.ALIGN_LEFT);
        p100.setSpacingAfter(10);
        p100.add(new Chunk("请使用您已绑定的银行账户向华信机构销售专用账户转账汇款，信息如下：",textFont));
        p100.add(Chunk.NEWLINE);
        document.add(p100);

        PdfPTable table100 = new PdfPTable(4);
        table100.setWidths(new int[]{1,3,1,2});
        table100.setHorizontalAlignment(Element.ALIGN_LEFT);
        table100.setWidthPercentage(100);

        for(int i = 0 ; i < BANK_DETAIL.length ; i++){
            PdfPCell cell100 = new PdfPCell();
            cell100.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell100.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell100.setPaddingTop(0);
            cell100.addElement(new Chunk(BANK_DETAIL[i],textFont));
            table100.addCell(cell100);
        }
        document.add(table100);

        document.close();
    }



    private void createDigital(String s[],PdfPTable table,Font font){
        for(int i= 0 ; i<12 ;i++){
            PdfPCell cell = new PdfPCell();
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPaddingBottom(10);
            cell.setColspan(1);
            cell.setRowspan(1);
            Paragraph p = new Paragraph();
            p.setAlignment(Element.ALIGN_CENTER);
            p.add(new Chunk(s[i],font));
            cell.addElement(p);
            table.addCell(cell);
        }
    }

    private PdfPCell createCell(Phrase p){
        PdfPCell cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setUseAscender(true);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.addElement(p);
        cell.setMinimumHeight(25);
        return cell;
    }
}
