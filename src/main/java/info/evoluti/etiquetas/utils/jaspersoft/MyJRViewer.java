/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.evoluti.etiquetas.utils.jaspersoft;
import java.lang.reflect.Constructor;
import java.util.Locale;
import java.util.ResourceBundle;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.swing.JRViewerToolbar;
import net.sf.jasperreports.view.JRSaveContributor;

public class MyJRViewer extends JRViewer {

    private static final Extension[] extensions;

    static {
        //HERE YOU CAN ADD WHATEVER EXTENSION YOU WANT
        extensions = new Extension[]{Extension.PDF, Extension.RTF, Extension.DOCX, Extension.ODT, Extension.HTML};
        //ADD THIS IF YOU WANT ALL
        //extensions = Extension.values();
    }


    public MyJRViewer(JasperPrint jasperPrint) {
        super(jasperPrint);

    }

    @Override
    protected JRViewerToolbar createToolbar() {
        JRViewerToolbar toolbar = super.createToolbar();
        Locale locale = viewerContext.getLocale();
        ResourceBundle resBundle = viewerContext.getResourceBundle();

        JRSaveContributor[] jrsc = new JRSaveContributor[extensions.length];
        Class[] type = {Locale.class, ResourceBundle.class};
        Object[] obj = {locale, resBundle};
        for (int i = 0; i < extensions.length; i++) {
            try {
                Constructor cons = extensions[i].getClazz().getConstructor(type);
                jrsc[i] = (JRSaveContributor) cons.newInstance(obj);
            } catch (Exception x) {
                x.printStackTrace();
            }
        }

        toolbar.setSaveContributors(jrsc);
        return toolbar;
    }
}