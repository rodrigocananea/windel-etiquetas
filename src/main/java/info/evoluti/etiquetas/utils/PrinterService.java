/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.evoluti.etiquetas.utils;

import static info.evoluti.etiquetas.Etiquetas.EProp;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;

/**
 *
 * @author Rodrigo
 */
public class PrinterService {

    public static void impressaoMatricial(JasperPrint jpPrint)
            throws JRException, IOException, PrintException {
        
        PrintService impressora = null;
        String impressoraPadrao = "";
        if (impressoraPadrao.equals("")) {
            JasperPrintManager.printReport(jpPrint, true);
        } else {
            PrintService[] impressoras = getImpressoras();
            for (PrintService ps : impressoras) {
                if (ps.getName().trim().equals(impressoraPadrao)) {
                    impressora = ps;
                }
            }

            if (impressora == null) {
                impressora = getImpressoraPadrao();
            } else if (impressora == null) {
                JasperPrintManager.printReport(jpPrint, true);
            } else {

                // Open the image file
                String testData = "Hello World !\f";
                try (InputStream is = new ByteArrayInputStream(testData.getBytes())) {
                    DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
                    // Create the print job
                    DocPrintJob job = impressora.createPrintJob();
                    Doc doc = new SimpleDoc(is, flavor, null);
                    // Monitor print job events; for the implementation of PrintJobWatcher,
                    PrinterService.PrintJobWatcher pjDone = new PrinterService.PrintJobWatcher(job);
                    // Print it
                    job.print(doc, null);
                    // Wait for the print job to be done
                    pjDone.waitForDone();
                    // It is now safe to close the input stream
                }

            }
        }
    }

    static class PrintJobWatcher {

        // true iff it is safe to close the print job's input stream
        boolean done = false;

        PrintJobWatcher(DocPrintJob job) {
            // Add a listener to the print job
            job.addPrintJobListener(new PrintJobAdapter() {
                @Override
                public void printJobCanceled(PrintJobEvent pje) {
                    allDone();
                }

                @Override
                public void printJobCompleted(PrintJobEvent pje) {
                    allDone();
                }

                @Override
                public void printJobFailed(PrintJobEvent pje) {
                    allDone();
                }

                @Override
                public void printJobNoMoreEvents(PrintJobEvent pje) {
                    allDone();
                }

                void allDone() {
                    synchronized (PrintJobWatcher.this) {
                        done = true;
                        PrintJobWatcher.this.notify();
                    }
                }
            });
        }

        public synchronized void waitForDone() {
            try {
                while (!done) {
                    wait();
                }
            } catch (InterruptedException e) {
            }
        }
    }

    public static void impressaoDireta(JasperPrint jpPrint) throws JRException {
        PrintService impressora = null;
        String impressoraPadrao = EProp.prop().getString("etiquetas.impressora", "<selecione>");
        if (impressoraPadrao.equals("<selecione>")) {
            JasperPrintManager.printReport(jpPrint, true);
        } else {
            PrintService[] impressoras = getImpressoras();
            for (PrintService ps : impressoras) {
                if (ps.getName().trim().equals(impressoraPadrao)) {
                    impressora = ps;
                }
            }

            if (impressora == null) {
                impressora = getImpressoraPadrao();
            } else if (impressora == null) {
                JasperPrintManager.printReport(jpPrint, true);
            } else {
                JRExporter exporter = new JRPrintServiceExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jpPrint);
                exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, impressora.getAttributes());
                exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
                exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
                exporter.exportReport();
            }
        }
    }

    public static void impressaoDireta(JasperPrint jpPrint, PrintService ps) throws JRException {
        if (ps == null) {
            //JOptionPane.showMessageDialog(null, "Impressora não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
            JasperPrintManager.printReport(jpPrint, true); //TRUE abre jDialog para seleção da impressora.
        } else {
            JRExporter exporter = new JRPrintServiceExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jpPrint);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, ps.getAttributes());
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
            exporter.exportReport();

            // Configurar tamanho da folha para impressão
//            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
//            printRequestAttributeSet.add(MediaSizeName.ISO_A4);
//            JRPrintServiceExporter exporter = new JRPrintServiceExporter();
//            exporter.setExporterInput(new SimpleExporterInput(jpPrint));
//
//            SimplePrintServiceExporterConfiguration configuration = new SimplePrintServiceExporterConfiguration();
////              Configurar tamanho da folha para impressão
////            configuration.setPrintRequestAttributeSet(printRequestAttributeSet);
//            configuration.setPrintServiceAttributeSet(ps.getAttributes());
//            configuration.setDisplayPageDialog(false);
//            configuration.setDisplayPrintDialog(true);
//            
//            exporter.setConfiguration(configuration);
//            exporter.exportReport();
        }
    }

    public void print() throws JRException {
        long start = System.currentTimeMillis();

        System.err.println("Printing time : " + (System.currentTimeMillis() - start));
    }

    public static PrintService getImpressoraPadrao() {
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();
        if (service == null) {
            JOptionPane.showMessageDialog(null, "Nenhuma impressora padrão foi configurada no computador, verifique!", "Atenção", JOptionPane.WARNING_MESSAGE);
        }
        return service;
    }

    public static PrintService[] getImpressoras() {
        PrintService[] impressoras = PrintServiceLookup.lookupPrintServices(null, null);

        return impressoras;
    }
}
