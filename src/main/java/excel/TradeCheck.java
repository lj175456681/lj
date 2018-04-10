package excel;

import com.google.common.collect.Lists;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Title: TradeCheck
 * Description:
 * author: liujie
 * date: 2017-09-14 下午5:43
 */
public class TradeCheck {

    public static final String path = "/Users/lj_free/Documents/tradeCheck2.xlsx";

    public static final String img = "/Users/lj_free/Documents/data/qy/pdf/img/tradelogo.jpeg";

    public static final List<String> titleList = Lists.newArrayList(
        "申请日期",
        "确认日期",
        "销售机构",
        "源产品",
        "交易类型",
        "目标产品",
        "申请金额/份额",
        "单位净值(元)",
        "确认份额/金额",
        "交易费用",
        "服务费用",
        "确认"
    );

    public void create(File file) throws IOException, InvalidFormatException {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("对账单");
        //去除网格线
        sheet.setDisplayGridlines(false);
        setImg(wb,sheet);
        setAddress(wb,sheet);
        setTable(wb,sheet);

        FileOutputStream fileOut = new FileOutputStream(file);
        wb.write(fileOut);
        fileOut.close();
        wb.close();

    }


    private void setTable(Workbook wb, Sheet sheet) {
        Row row = sheet.createRow(10);
        Cell cell = null;

        CellStyle titleStyle = wb.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setFillForegroundColor(IndexedColors.TAN.getIndex());
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        titleStyle.setBorderBottom(BorderStyle.THIN);
        titleStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        titleStyle.setBorderLeft(BorderStyle.THIN);
        titleStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        titleStyle.setBorderRight(BorderStyle.THIN);
        titleStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        titleStyle.setBorderTop(BorderStyle.THIN);
        titleStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        for(int i = 0; i < titleList.size(); i++){
            cell = row.createCell(i);
            cell.setCellValue(titleList.get(i));
            cell.setCellStyle(titleStyle);
        }

        CellStyle cellStyle = wb.createCellStyle();
        //自动换行属性
        cellStyle.setWrapText(true);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

        int rowNum = 11;
        for(int i = 0; i<10; i++){
            row = sheet.createRow(rowNum);
            addCell(row,0,"20170915",cellStyle);
            addCell(row,1,"20170916",cellStyle);
            addCell(row,2,"上海华信证券有限责任公司",cellStyle);
            addCell(row,3,"资金账户",cellStyle);
            addCell(row,4,"申购",cellStyle);
            addCell(row,5,"现金管理一号",cellStyle);
            addCell(row,6,"10,000,000,000.00",cellStyle);
            addCell(row,7,"1.0000",cellStyle);
            addCell(row,8,"10,000,000,000.00",cellStyle);
            addCell(row,9,"976,123,12",cellStyle);
            addCell(row,10,"12,333,00",cellStyle);
            addCell(row,11,"成功",cellStyle);
            rowNum ++;
        }
        row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue("温馨提示：本账单记录您在报告期间的产品持有份额及基金交易明细，具体信息以产品注册登记机构的记录为准。");

    }

    private void addCell(Row row,int column,Object value,CellStyle style){
        Cell cell = row.createCell(column);
        cell.setCellValue(value.toString());
        cell.setCellType(CellType.STRING);
        cell.setCellStyle(style);
    }

    private void setAddress(Workbook wb, Sheet sheet) {
        sheet.setDefaultColumnWidth(10);
        Row row = null;
        Cell cell = null;
        CellRangeAddress cra = null;


        for(int i = 2 ;i <= 8 ;i++){
            sheet.setColumnWidth(i, (short) 4000);
        }


        row = sheet.createRow(0);
        cell = row.createCell(9);
        cell.setCellValue("公司地址：上海市黄浦区南京西路399号明天广场22楼");

        CreationHelper createHelper = wb.getCreationHelper();
        Hyperlink link = createHelper.createHyperlink(HyperlinkType.URL);
        CellStyle hlink_style = wb.createCellStyle();
        Font hlink_font = wb.createFont();
        hlink_font.setUnderline(Font.U_SINGLE);
        hlink_font.setColor(IndexedColors.BLUE.getIndex());
        hlink_style.setFont(hlink_font);

        row = sheet.createRow(1);
        cell = row.createCell(9);
        link.setAddress("http://www.shhxzq.com/");
        cell.setHyperlink(link);
        cell.setCellStyle(hlink_style);
        cell.setCellValue("官网网址：www.shhxzq.com");


        row = sheet.createRow(2);
        cell = row.createCell(9);
        cell.setCellValue("Email Link");
        link = createHelper.createHyperlink(HyperlinkType.EMAIL);
        link.setAddress("mailto:service@shhxzq.com");
        cell.setHyperlink(link);
        cell.setCellStyle(hlink_style);
        cell.setCellValue("电子邮箱：service@shhxzq.com");

        row = sheet.createRow(3);
        cell = row.createCell(9);
        cell.setCellValue("客户服务热线：400-820-5999（免长途话费）");


        Font font0 = wb.createFont();
        font0.setFontHeightInPoints((short)18);
        font0.setBold(true);
        CellStyle style0 = wb.createCellStyle();
        style0.setFont(font0);
        style0.setAlignment(HorizontalAlignment.CENTER);

        Cell title = sheet.createRow(5).createCell(0);
        title.setCellValue("本期交易明细");
        title.setCellStyle(style0);
        cra = new CellRangeAddress(5, 5, 0, 11);
        sheet.addMergedRegion(cra);

        Row row6 = sheet.createRow(6);
        row6.createCell(0).setCellValue("投资者名称：");
        row6.createCell(1).setCellValue("上海华信证券有限责任公司");

        Row row7 = sheet.createRow(7);
        row7.createCell(0).setCellValue("证件类型：");
        row7.createCell(1).setCellValue("营业执照");

        Row row8 = sheet.createRow(8);
        row8.createCell(0).setCellValue("证件号码：");
        row8.createCell(1).setCellValue("913000000000000J");

        Row row9 = sheet.createRow(9);
        row9.createCell(0).setCellValue("报告日期：");
        row9.createCell(1).setCellValue("20170801-20170809");


    }


    public void setImg( Workbook wb,Sheet sheet) throws IOException {
        CreationHelper helper = wb.getCreationHelper();
        InputStream is = new FileInputStream(img);
        byte[] bytes = IOUtils.toByteArray(is);
        is.close();
        int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
        Drawing<?> drawing = sheet.createDrawingPatriarch();
        ClientAnchor anchor = helper.createClientAnchor();
        //AnchorType.MOVE_DONT_RESIZE 这里设置为2可以实现移动单元格，但是图片大小不变。默认是随着单元格自适应的
        anchor.setAnchorType(2);
        Picture pict = drawing.createPicture(anchor, pictureIdx);
        pict.resize(3,4);
    }

    public static void main(String[] args) throws IOException, InvalidFormatException {
        TradeCheck check = new TradeCheck();
        File file = new File(path);
        check.create(file);
    }
}
