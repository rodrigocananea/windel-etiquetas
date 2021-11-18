/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.evoluti.etiquetas.utils.jaspersoft;

import net.sf.jasperreports.view.JRSaveContributor;
import net.sf.jasperreports.view.save.JRCsvSaveContributor;
import net.sf.jasperreports.view.save.JRDocxSaveContributor;
import net.sf.jasperreports.view.save.JREmbeddedImagesXmlSaveContributor;
import net.sf.jasperreports.view.save.JRHtmlSaveContributor;
import net.sf.jasperreports.view.save.JRMultipleSheetsXlsSaveContributor;
import net.sf.jasperreports.view.save.JROdtSaveContributor;
import net.sf.jasperreports.view.save.JRPdfSaveContributor;
import net.sf.jasperreports.view.save.JRPrintSaveContributor;
import net.sf.jasperreports.view.save.JRRtfSaveContributor;
import net.sf.jasperreports.view.save.JRSingleSheetXlsSaveContributor;
import net.sf.jasperreports.view.save.JRXmlSaveContributor;

public enum Extension {
    PDF(JRPdfSaveContributor.class),
    RTF(JRRtfSaveContributor.class),
    SINGLE_SHEET_XLS(JRSingleSheetXlsSaveContributor.class),
    MULTIPLE_SHEET_XLS(JRMultipleSheetsXlsSaveContributor.class),
    DOCX(JRDocxSaveContributor.class),
    ODT(JROdtSaveContributor.class),
    HTML(JRHtmlSaveContributor.class),
    XML(JRXmlSaveContributor.class),
    CSV(JRCsvSaveContributor.class),
    PRINT(JRPrintSaveContributor.class),
    EMBEDDED_IMAGES_XML(JREmbeddedImagesXmlSaveContributor.class);

    private Class<? extends JRSaveContributor> clazz;

    Extension(Class<? extends JRSaveContributor> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends JRSaveContributor> getClazz() {
        return clazz;
    }
}