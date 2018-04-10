package bean;

import com.itextpdf.text.Font;
import lombok.Data;

/**
 * Title: BasePdfTemplate
 * Description:
 * author: liujie
 * date: 2017-05-22 上午11:30
 */
@Data
public class BasePdfTemplate {

    /**
     * 标题
     */
    private String title;

    /**
     * 标题样式
     */
    private Font titleFont;

    /**
     * 打印日期时间 yyyy-MM-dd hh:mm:ss
     */
    private String printDtm;


}
