package pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSmartCopy;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Title: PdfTemplate
 * Description:
 * author: liujie
 * date: 2017-06-01 下午4:54
 */
public class PdfTemplate {

    public static void main(String[] args) {

    }

    public static void copy(String from ,String to) throws IOException, DocumentException {

        PdfReader reader = null;
        ByteArrayOutputStream bos = null;
        PdfStamper ps = null;
        PdfSmartCopy pdfCopy=null;
        try {
            reader = new PdfReader(from);
            bos = new ByteArrayOutputStream();
            ps = new PdfStamper(reader, bos);
            AcroFields fields = ps.getAcroFields();
            ps.setFormFlattening(true);
        } finally {
            if(null != reader){
                reader.close();
            }
            if(null != bos){
                bos.close();
            }
            if(null != ps){
                ps.close();
            }
            if(null != pdfCopy){
                pdfCopy.close();
            }
        }

    }
}
