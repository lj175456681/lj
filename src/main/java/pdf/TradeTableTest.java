package pdf;

import com.google.common.collect.Lists;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Title: TradeTableTest
 * Description:
 * author: liujie
 * date: 2017-06-30 上午10:27
 */
public class TradeTableTest {

    private static final String SONG = "/Users/lj_free/Documents/data/qy/pdf/font/simsun.ttf";
    private static final String ROMAN = "/Users/lj_free/Documents/data/qy/pdf/font/TimesNewRoman.ttf";
    private static final String YAHEI = "/Users/lj_free/Documents/data/qy/pdf/font/vista.ttf";
    private static final String IMG_PATH = "/Users/lj_free/Documents/data/qy/pdf/img/tradelogo.jpeg";
    private static final String WATER_MARK = "/Users/lj_free/Documents/data/qy/pdf/img/chapter.png";
    private static final String DEST = "/Users/lj_free/Documents/test.pdf";
    private static final String DEST2 = "/Users/lj_free/Documents/test2.pdf";
    private static final String SONG_NAME = "宋体";
    private static final String TIMES_NEW_ROMAN_NMAE = "timesnewromanpsmt";
    private Font title1;
    private Font title2;
    private Font zhengwen1;
    private Font zhengwen2;
    private Font roman;
    private Font link;



    public TradeTableTest() throws IOException, DocumentException {
        // 添加中文字体
        BaseFont bfChinese = BaseFont.createFont(SONG, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        // 华文新魏
        BaseFont baseFT = BaseFont.createFont(ROMAN, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        // 华文新魏
        BaseFont yh = BaseFont.createFont(YAHEI, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        title1 = new Font(yh,15,Font.BOLD,null);
        title2 = new Font(bfChinese,12,Font.BOLD,null);
        zhengwen1 = new Font(bfChinese,8,Font.NORMAL,null);
        zhengwen2 = new Font(bfChinese,8,Font.NORMAL, BaseColor.RED);
        roman =  new Font(baseFT,8,Font.NORMAL,null);
        link = new Font(baseFT,8,Font.UNDERLINE,BaseColor.BLUE);
    }

    private BaseFont getBaseFontByName(String fontName){
        Font font = FontFactory.getFont(fontName, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        return font.getBaseFont();
    }



    public void createPdf() throws IOException, DocumentException {
        Document document = new Document(PageSize.A4, 10f, 10f, 10f, 10f);
        PdfWriter.getInstance(document, new FileOutputStream(DEST));
        document.open();
        //设置图片
        setHeader(document);
        //设置title
        setTitle(document);
        //静态份额
        setAssetTable(document);
        //设置交易记录
        setTradeTable(document);

        document.close();

        setWaterMark(DEST,DEST2);
    }

    /**
     *
     * @param sourceFilePath
     * @return
     */
    private String setWaterMark(String sourceFilePath,String outFilePath) throws IOException, DocumentException {
        PdfReader reader = null;
        PdfStamper stamper = null;
        try {
            reader = new PdfReader(sourceFilePath);
            File outFile = new File(outFilePath);
            if(!outFile.exists()){
                outFile.getParentFile().mkdirs();
            }
            stamper = new PdfStamper(reader, new FileOutputStream(outFile));
            Image image = Image.getInstance(WATER_MARK);
            image.setAbsolutePosition(300,300);
            PdfGState gs = new PdfGState();
            gs.setFillOpacity(0.1f);
            PdfContentByte under;
            int pages = reader.getNumberOfPages();
            for(int i = 1 ;i<=pages;i++){
                under = stamper.getUnderContent(i);
                // 添加水印图片
                under.addImage(image);
                under.setGState(gs);
                under.stroke();
            }
            return outFilePath;
        } finally {
            if(null != stamper){
                try {
                    stamper.close();
                } catch (DocumentException | IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != reader){
                reader.close();
            }
        }
    }

    private void setTradeTable(Document document) throws DocumentException {
        Paragraph title = new Paragraph();
        title.setSpacingBefore(15);
        title.setSpacingAfter(0);
        title.setAlignment(Element.ALIGN_CENTER);
        title.add(new Chunk("本 期 交 易 明 细",title2));
        document.add(title);

        Paragraph time = new Paragraph();
        time.setAlignment(Element.ALIGN_LEFT);
        time.add(new Chunk("报告日期：20170501-20170531",zhengwen1));
        document.add(time);

        PdfPTable table = new PdfPTable(10);
        table.setSpacingBefore(5);
        table.setWidthPercentage(100);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setWidths(new int[]{8,8,16,16,10,10,8,10,8,6});
        List<String> t = Lists.newArrayList(
                "申请日期",
                "确认日期",
                "销售机构",
                "产品名称",
                "交易类型",
                "申请金额/份额",
                "单位净值（元）",
                "确认份额/金额",
                "交易费用",
                "确认"
        );
        //标题
        for(String name : t){
            PdfPCell cell = new PdfPCell();
            cell.setPaddingTop(0);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBackgroundColor(BaseColor.ORANGE);
            Paragraph p = new Paragraph();
            p.setLeading(0,1);
            p.setAlignment(Element.ALIGN_CENTER);
            p.add(new Chunk(name,zhengwen1));
            cell.addElement(p);
            table.addCell(cell);
        }

        // 数据
        List<String> data = Lists.newArrayList(
                "20170424",
                "20170522",
                "华信证券",
                "工银瑞信瑞利两年封闭式债券型证券投资基金",
                "买入（认购）",
                "20000000",
                "1.0000",
                "0",
                "0",
                "失败"
        );
        for(int i = 0;i<3;i++){
            for(String content : data){
                PdfPCell cell = new PdfPCell();
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.addElement(new Chunk(content,zhengwen1));
                table.addCell(cell);
            }
        }

        document.add(table);

        Paragraph bottom = new Paragraph();
        bottom.setAlignment(Element.ALIGN_LEFT);
        bottom.add(new Chunk("温馨提示：",zhengwen2));
        bottom.add(Chunk.NEWLINE);
        bottom.add(new Chunk("本账单记录您在报告期间的产品持有份额及基金交易明细，具体信息以产品注册登记机构的记录为准。",zhengwen1));

        document.add(bottom);

    }

    private void setAssetTable(Document document) throws DocumentException {

        Paragraph title = new Paragraph();
        title.setSpacingBefore(10);
        title.setSpacingAfter(0);
        title.setAlignment(Element.ALIGN_CENTER);
        title.add(new Chunk("账 户 余 额",title2));
        document.add(title);

        PdfPTable table = new PdfPTable(9);
        table.setSpacingBefore(10);
        table.setWidthPercentage(100);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setWidths(new int[]{10,20,9,20,7,9,9,9,7});
        List<String> t = Lists.newArrayList(
                "理财账号",
                "销售机构",
                "交易账号",
                "产品名称",
                "产品代码",
                "产品份额（份）",
                "最新净值（元）",
                "参考市值（元）",
                "分红方式"
        );
        //标题
        for(String name : t){
            PdfPCell cell = new PdfPCell();
            cell.setPaddingTop(0);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBackgroundColor(BaseColor.ORANGE);
            Paragraph p = new Paragraph();
            p.setLeading(0,1);
            p.setAlignment(Element.ALIGN_CENTER);
            p.add(new Chunk(name,zhengwen1));
            cell.addElement(p);
            table.addCell(cell);
        }
        // 数据
        List<String> data = Lists.newArrayList(
                "F91100006034",
                "华信证券",
                "0000040512",
                "工银瑞信瑞利两年封闭式债券型证券投资基金",
                "F90019",
                "89000000111.1234",
                "1.0000",
                "89000000",
                "红利再投"
        );
        for(int i = 0;i<3;i++){
            for(String content : data){
                PdfPCell cell = new PdfPCell();
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.addElement(new Chunk(content,zhengwen1));
                table.addCell(cell);
            }
        }

        //求和
        PdfPCell cell1 = new PdfPCell();
        cell1.setColspan(7);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        Paragraph p1 = new Paragraph();
        p1.setLeading(0,1);
        p1.setAlignment(Element.ALIGN_CENTER);
        p1.add(new Chunk("总市值",zhengwen1));
        cell1.addElement(p1);
        table.addCell(cell1);

        PdfPCell cell2 = new PdfPCell();
        cell2.setColspan(2);
        cell2.setVerticalAlignment(Element.ALIGN_CENTER);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        Paragraph p2= new Paragraph();
        p2.setLeading(0,1);
        p2.setAlignment(Element.ALIGN_LEFT);
        p2.add(new Chunk("50009899.1123",zhengwen1));
        cell2.addElement(p2);
        table.addCell(cell2);

        document.add(table);

        Paragraph bottom = new Paragraph();
        bottom.setAlignment(Element.ALIGN_LEFT);
        bottom.add(new Chunk("*注：货币基金的收益按日分配，每日转结，理论收益=期末资产总值+未分配收益-定投本金",zhengwen1));

        document.add(bottom);
    }

    private void setTitle(Document document) throws DocumentException {
        Paragraph title = new Paragraph();
        title.setSpacingBefore(10);
        title.setSpacingAfter(20);
        title.setAlignment(Element.ALIGN_CENTER);
        title.add(new Chunk("交 易 颋 账 单",title1));
        document.add(title);

        Paragraph p = new Paragraph();
        p.setAlignment(Element.ALIGN_LEFT);
        p.add(new Chunk("投资者名称：上海华信证券有限责任公司",zhengwen1));
        p.add(Chunk.NEWLINE);
        p.add(new Chunk("证件类型：营业执照",zhengwen1));
        p.add(Chunk.NEWLINE);
        p.add(new Chunk("证件号码：",zhengwen1));
        p.add(new Chunk("91310000710936313J",roman));
        p.add(Chunk.NEWLINE);
        p.add(new Chunk("打印日期：2017年06月14日",zhengwen1));
        document.add(p);

    }

    private void setHeader(Document document) throws IOException, DocumentException {
        //图片
        PdfPTable table = new PdfPTable(3);
        table.setWidths(new int[]{30,30,40});
        table.setWidthPercentage(100);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        Image img = Image.getInstance(IMG_PATH);
        img.setScaleToFitHeight(false);
        img.setAlignment(Element.ALIGN_LEFT);
        PdfPCell cell = new PdfPCell(img, true);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setUseAscender(true);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        PdfPCell cell2 = new PdfPCell();
        cell2.setBorder(Rectangle.NO_BORDER);
        cell2.addElement(new Chunk(""));
        table.addCell(cell2);


        PdfPCell cell3 = new PdfPCell();
        cell3.setBorder(Rectangle.NO_BORDER);
        cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
        Paragraph p = new Paragraph();
        p.add(new Chunk("公司地址：上海市黄浦区南京西路399号明天广场22楼",zhengwen1));
        p.add(Chunk.NEWLINE);
        p.add(new Chunk("官网网址：",zhengwen1));
        p.add(new Chunk("http://www.shhxzq.com",link));
        p.add(Chunk.NEWLINE);
        p.add(new Chunk("电子邮箱：",zhengwen1));
        p.add(new Chunk("service@shhxzq.com",link));
        p.add(Chunk.NEWLINE);
        p.add(new Chunk("客户服务热线：400-820-5999（免长途话费）",zhengwen1));
        cell3.addElement(p);
        table.addCell(cell3);

        document.add(table);


    }


    public static void main(String[] args) throws IOException, DocumentException {
        new TradeTableTest().createPdf();
    }



}
