/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.evoluti.etiquetas;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;
import info.evoluti.etiquetas.utils.EtiquetasProp;
import info.evoluti.etiquetas.utils.jaspersoft.FileTypeFilter;
import info.evoluti.etiquetas.views.Main;
import info.evoluti.etiquetas.views.SplashScreen;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Rodrigo
 */
public class Etiquetas {

    public static EtiquetasProp EProp;

    public static void main(String[] args) throws InterruptedException {
        EProp = new EtiquetasProp();
        String theme = EProp.prop().getString("tema", "Light");

        switch (theme) {
            case "Dark":
                FlatOneDarkIJTheme.install();
                break;
            case "Light":
                FlatIntelliJLaf.install();
                break;
            default:
                FlatIntelliJLaf.install();
                break;
        }

        SplashScreen screen = new SplashScreen();
        screen.setVisible(true);
        Thread.sleep(1000);
        validateLocalCfg(screen);
        java.awt.EventQueue.invokeLater(() -> {
            screen.jlStatus.setText("Carregando tela principal, aguarde...");
            Main main = new Main();
            screen.jlStatus.setText("Abrindo janelas, aguarde...");
            screen.setVisible(false);
            main.setVisible(true);
        });
    }

    public static void validateLocalCfg(SplashScreen screen) throws InterruptedException {
        screen.jlStatus.setText("Verificando arquivo LOCAL.cfg, aguarde...");
        Thread.sleep(1000);
        File local = new File(System.getProperty("user.dir") + File.separator + "LOCAL.cfg");
        if (local.exists()) {
            try (Scanner sc = new Scanner(local)) {
                String line = sc.nextLine();
                String[] parts = line.split(":");
                if (parts.length >= 3) {
                    screen.jlStatus.setText("Atualizando informações com LOCAL.cfg, aguarde...");
                    EProp.setProp("host.name", parts[0]);
                    EProp.setProp("host.database", parts[1] + ":" + parts[2]);
                    Thread.sleep(500);
                    screen.jlStatus.setText("LOCAL.cfg carregado!");
                }
            } catch (FileNotFoundException ex) {
            }
        } else {
            screen.jlStatus.setText("LOCAL.cfg não existe!");

            File FDB = new File(EProp.prop().getString("host.database"));
            if (!FDB.exists()) {
                JFileChooser fileChooser = new JFileChooser(".");
                FileFilter fdbFilter = new FileTypeFilter(".FDB", "Firebird Database");
                fileChooser.addChoosableFileFilter(fdbFilter);
                fileChooser.setFileFilter(fdbFilter);
                int status = fileChooser.showOpenDialog(null);
                if (status == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    EProp.setProp("host.database", selectedFile.getPath());
                } else {
                    System.exit(0);
                }
            }
        }
    }

}
