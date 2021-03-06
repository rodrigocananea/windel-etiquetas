/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.evoluti.etiquetas.utils;

import static info.evoluti.etiquetas.Etiquetas.EProp;
import info.evoluti.etiquetas.models.ModelEtqProd;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Rodrigo
 */
public class Util {

    final static Logger logger = Logger.getLogger("utils/SIProp");
    
    public static JasperReport loadReport(final String fileName) throws IOException, JRException {
        try (InputStream in = Util.class.getClass().getResourceAsStream("/reports/" + fileName)) {
            return (JasperReport) JRLoader.loadObject(in);
        }
    }

    public static JasperPrint getJasperPrintEtq(List<ModelEtqProd> lista) throws IOException, JRException {
        String reportLocal = EProp.prop().getString("etiquetas.arquivo.jasper", "");
        if (StringUtils.isBlank(reportLocal)
                || !(new File(reportLocal)).exists()) {
            logger.info("Utilizando arquivo jasper interno.");
            return JasperFillManager.fillReport(loadReport("etq_produtos.jasper"), null, new JRBeanCollectionDataSource(lista));
        } else {
            logger.info("Utilizando arquivo jasper local: " + reportLocal);
            return JasperFillManager.fillReport(reportLocal, null, new JRBeanCollectionDataSource(lista));
        }
    }

    public static String currency(double value) {
        return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(value);
    }

    public static String currency(double value, String format) {
        if (format == null
                || format.equals("")) {
            format = "#,##0.00";
        }
        return new DecimalFormat(format).format(value);
    }

    public static String currency(BigDecimal value, String format) {
        if (format == null
                || format.equals("")) {
            format = "#,##0.00";
        }
        return new DecimalFormat(format).format(value);
    }

    public static double currency(String value) {
        double formated = 0.00;
        try {
            if (StringUtils.isNotBlank(value)) {
                value = value.replaceAll("[a-zA-Z]+\\.?", "");
                value = value.replace("\t", "").trim();
                value = value.replace(" ", "").trim();
                value = value.replace("$", "").trim();
                value = value.replace("%", "").trim();
                NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);
                BigDecimal bd = new BigDecimal(nf.parse(value).doubleValue())
                        .setScale(2, RoundingMode.HALF_UP);

                formated = bd.doubleValue();
            }
        } catch (ParseException ex) {
            formated = 0.00;
        }
        return formated;
    }

    public static double currency(String value, String scale) {
        if (!StringUtils.isNotBlank(value)) {
            return 0.00;
        } else {
            try {
                if (!StringUtils.isNotBlank(scale)) {
                    scale = "2";
                }
                value = value.replace("\t", "").trim();
                value = value.replace(" ", "").trim();
                value = value.replace("R$", "").trim();
                value = value.replace("%", "").trim();
                value = value.replace("%", "").trim();
                NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);
                BigDecimal bd = new BigDecimal(nf.parse(value).doubleValue())
                        .setScale(Integer.valueOf(scale), RoundingMode.HALF_UP);

                return bd.doubleValue();
            } catch (ParseException ex) {
                return 0.00;
            }
        }
    }

    public static String currency(BigDecimal value) {
        if (value == null) {
            value = BigDecimal.ZERO;
        }
        return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(value);
    }

    public static boolean indexExists(final List list, final int index) {
        return index >= 0 && index < list.size();
    }

    public static String normalizer(String text) {
        text = text.replace('??', 'S');
        text = text.replace('??', 's');
        text = text.replace('??', 'Z');
        text = text.replace('??', 'z');
        text = text.replace('??', 'A');
        text = text.replace('??', 'A');
        text = text.replace('??', 'A');
        text = text.replace('??', 'A');
        text = text.replace('??', 'A');
        text = text.replace('??', 'A');
        text = text.replace('??', 'A');
        text = text.replace('??', 'C');
        text = text.replace('??', 'E');
        text = text.replace('??', 'E');
        text = text.replace('??', 'E');
        text = text.replace('??', 'E');
        text = text.replace('??', 'I');
        text = text.replace('??', 'I');
        text = text.replace('??', 'I');
        text = text.replace('??', 'I');
        text = text.replace('??', 'N');
        text = text.replace('??', 'O');
        text = text.replace('??', 'O');
        text = text.replace('??', 'O');
        text = text.replace('??', 'O');
        text = text.replace('??', 'O');
        text = text.replace('??', 'O');
        text = text.replace('??', 'U');
        text = text.replace('??', 'U');
        text = text.replace('??', 'U');
        text = text.replace('??', 'U');
        text = text.replace('??', 'Y');
        text = text.replace('??', 'B');
        text = text.replace('??', 'a');
        text = text.replace('??', 'a');
        text = text.replace('??', 'a');
        text = text.replace('??', 'a');
        text = text.replace('??', 'a');
        text = text.replace('??', 'a');
        text = text.replace('??', 'a');
        text = text.replace('??', 'c');
        text = text.replace('??', 'e');
        text = text.replace('??', 'e');
        text = text.replace('??', 'e');
        text = text.replace('??', 'e');
        text = text.replace('??', 'i');
        text = text.replace('??', 'i');
        text = text.replace('??', 'i');
        text = text.replace('??', 'i');
        text = text.replace('??', 'o');
        text = text.replace('??', 'n');
        text = text.replace('??', 'o');
        text = text.replace('??', 'o');
        text = text.replace('??', 'o');
        text = text.replace('??', 'o');
        text = text.replace('??', 'o');
        text = text.replace('??', 'o');
        text = text.replace('??', 'u');
        text = text.replace('??', 'u');
        text = text.replace('??', 'u');
        text = text.replace('??', 'y');
        text = text.replace('??', 'y');
        text = text.replace('??', 'b');
        text = text.replace('??', 'y');

        return text;
    }

    public static Date asDate(String date) {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(date);
        return asDate(zonedDateTime.toLocalDateTime());
    }

    public static Date asDate(String date, String parser) {
        Date dateParsed = null;
        if (date == null) {
            return null;
        }

        try {
            if (parser == null) {
                parser = "yyyy-MM-dd HH:mm:ss";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(parser);
            dateParsed = sdf.parse(date);
        } catch (ParseException ex) {
            dateParsed = null;
        }
        return dateParsed;
    }

    public static String asDate(Date date) {
        String dateFormated = "";
        if (date != null) {
            dateFormated = new SimpleDateFormat("dd/MM/yyyy").format(date);
        }
        return dateFormated;
    }

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String asDate(LocalDateTime localDateTime, String format) {
        String date = "";
        DateTimeFormatter dtFormatter = null;

        if (format == null) {
            dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        } else {
            dtFormatter = DateTimeFormatter.ofPattern(format);
        }

        if (localDateTime != null) {
            date = localDateTime.format(dtFormatter);
        }

        return date;
    }

    public static String asDate(Date date, String format) {
        if (date == null) {
            date = new Date();
        }
        return new SimpleDateFormat(format).format(date);
    }

    public static String asDate(LocalDate localDate, String format) {
        String date = null;
        DateTimeFormatter dtFormatter = null;

        if (StringUtils.isNotBlank(format)) {
            dtFormatter = DateTimeFormatter.ofPattern(format);
        } else {
            dtFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        }

        if (localDate != null) {
            date = localDate.format(dtFormatter);
        }

        return date;
    }

    public static String asDate(LocalDate localDate, DateTimeFormatter format) {
        String date = "";
        DateTimeFormatter dtFormatter;

        if (format == null) {
            dtFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        } else {
            dtFormatter = format;
        }

        if (localDate != null) {
            date = localDate.format(dtFormatter);
        }

        return date;
    }
}
