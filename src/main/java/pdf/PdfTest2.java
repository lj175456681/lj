package pdf;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEvent;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Title: PdfTest2
 * Description:
 * author: liujie
 * date: 2017-05-31 下午6:58
 */
public class PdfTest2 {

    public static final String DEST = "/Users/lj_free/Documents/test2.pdf";
    public static final String SONG_TI = "/Users/lj_free/Documents/font/simsun.ttf";
    public static final String NEW_ROMAN = "/Users/lj_free/Documents/font/TimesNewRoman.ttf";
    public static final String XIN_WEI = "/Users/lj_free/Documents/font/STXINWEI.TTF";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new PdfTest2().createPdf(DEST);
    }


    /**
     * 全部cell带虚线
     */
    class DottedCells implements PdfPTableEvent {

        public void tableLayout(PdfPTable table, float[][] widths,
                                float[] heights, int headerRows, int rowStart,
                                PdfContentByte[] canvases) {
            PdfContentByte canvas = canvases[PdfPTable.LINECANVAS];
            canvas.setLineWidth(2.5f);
            float llx = widths[0][0];
            float urx = widths[0][widths.length];
            canvas.moveTo(llx, heights[0]);
            canvas.lineTo(urx, heights[0]);
            canvas.moveTo(llx, heights[heights.length-1]);
            canvas.lineTo(urx, heights[heights.length-1]);
            canvas.stroke();
        }
    }


    class DottedCells2 implements PdfPTableEvent {

        public void tableLayout(PdfPTable table, float[][] widths,
                                float[] heights, int headerRows, int rowStart,
                                PdfContentByte[] canvases) {
            PdfContentByte canvas = canvases[PdfPTable.LINECANVAS];
            canvas.setLineWidth(0.5f);
            float llx = widths[0][0];
            float urx = widths[0][widths.length];
            canvas.moveTo(llx, heights[0]);
            canvas.lineTo(urx, heights[0]);
            canvas.moveTo(llx, heights[heights.length-1]);
            canvas.lineTo(urx, heights[heights.length-1]);
            canvas.stroke();
        }
    }


    /**
     * 单个cell带虚线
     */
    class DottedCell implements PdfPCellEvent {
        public void cellLayout(PdfPCell cell, Rectangle position,
                               PdfContentByte[] canvases) {
            PdfContentByte canvas = canvases[PdfPTable.LINECANVAS];
            canvas.setLineDash(3f, 3f);
            canvas.moveTo(position.getLeft(), position.getTop());
            canvas.lineTo(position.getRight(), position.getTop());
            canvas.moveTo(position.getLeft(), position.getBottom());
            canvas.lineTo(position.getRight(), position.getBottom());
            canvas.stroke();
        }
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

        Font firsetTitleFont = new Font(song, 22, Font.BOLD);
        Font textFont = new Font(song, 12, Font.NORMAL); //正常
        Font romanFont = new Font(baseFTE, 12, Font.NORMAL); //正常
        Font under = new Font(bf, 12, Font.UNDERLINE);
        Font under2 = new Font(song, 12, Font.UNDERLINE);
        Font bold = new Font(song, 12, Font.BOLD); //正常



        Paragraph p1 = new Paragraph();
        p1.setAlignment(Element.ALIGN_LEFT);
        p1.setSpacingAfter(60);
        p1.add(new Chunk("机构名称：",textFont));
        p1.add(new Chunk("上海华信证券",textFont));
        document.add(p1);

        Paragraph p2 = new Paragraph();
        p2.setSpacingAfter(40);
        p2.setAlignment(Element.ALIGN_CENTER);
        p2.add(new Chunk("认购确认书",firsetTitleFont));
        document.add(p2);

        Paragraph p3 = new Paragraph();
        p3.setAlignment(Element.ALIGN_LEFT);
        p3.add(new Chunk("尊敬的客户，您申请的业务, 确认结果如下:",textFont));
        p3.add(Chunk.NEWLINE);
        p3.add(Chunk.NEWLINE);
        document.add(p3);

        Paragraph p4 = new Paragraph();
        p4.setSpacingAfter(5);
        p4.setAlignment(Element.ALIGN_LEFT);
        p4.add(new Chunk("打印日期:",textFont));
        p4.add(new Chunk("2017-05-31 19:06:23",romanFont));
        p4.add(Chunk.NEWLINE);
        document.add(p4);

        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setTableEvent(new DottedCells());
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.getDefaultCell().setCellEvent(new DottedCell());

        PdfPCell cell = new PdfPCell();
        cell.setPaddingTop(3);
        cell.setPaddingBottom(3);
        cell.setPaddingLeft(0);
        cell.setPaddingRight(0);
        cell.setBorder(Rectangle.NO_BORDER);
        PdfPTable table2 = new PdfPTable(1);
        table2.setWidthPercentage(100);
        table2.setTableEvent(new DottedCells2());
        table2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);


        PdfPCell celltable2 = new PdfPCell();
        celltable2.setHorizontalAlignment(Element.ALIGN_LEFT);
        celltable2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celltable2.setPaddingTop(1);
        celltable2.setPaddingLeft(0);
        celltable2.setPaddingRight(0);
        celltable2.setPaddingBottom(1);

        PdfPTable table1 = new PdfPTable(2);
        table1.setWidths(new int[]{1,1});
        table1.setWidthPercentage(100);
        table1.setSpacingAfter(20);
        table1.setSpacingBefore(1);
        table1.getDefaultCell().setCellEvent(new DottedCell());
        PdfPCell cell11 = new PdfPCell();
        cell11.setBorder(Rectangle.NO_BORDER);
        cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell11.addElement(new Chunk("申请日期",textFont));
        table1.addCell(cell11);

        PdfPCell cell12 = new PdfPCell();
        cell12.setBorder(Rectangle.NO_BORDER);
        cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell12.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell12.addElement(new Chunk("2017-06-01",romanFont));
        table1.addCell(cell12);

        PdfPCell cell21 = new PdfPCell();
        cell21.setBorder(Rectangle.NO_BORDER);
        cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell21.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell21.addElement(new Chunk("确认日期",textFont));
        table1.addCell(cell21);

        PdfPCell cell22 = new PdfPCell();
        cell22.setBorder(Rectangle.NO_BORDER);
        cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell22.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell22.addElement(new Chunk("2017-06-01",romanFont));
        table1.addCell(cell22);

        PdfPCell cell31 = new PdfPCell();
        cell31.setBorder(Rectangle.NO_BORDER);
        cell31.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell31.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell31.addElement(new Chunk("投资人姓名",textFont));
        table1.addCell(cell31);


        PdfPCell cell32 = new PdfPCell();
        cell32.setBorder(Rectangle.NO_BORDER);
        cell32.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell32.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell32.addElement(new Chunk("刘杰",textFont));
        table1.addCell(cell32);


        celltable2.addElement(table1);

        PdfPTable tableTest = new PdfPTable(1);
        tableTest.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        tableTest.getDefaultCell().setCellEvent(new DottedCell());
        tableTest.addCell("A");
        tableTest.addCell("B");
        tableTest.addCell("C");
        tableTest.addCell("D");
        table2.addCell(tableTest);

        cell.addElement(table2);
        table.addCell(cell);
        document.add(table);
        document.close();

    }
}
