/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.evoluti.etiquetas.controllers;

import info.evoluti.etiquetas.models.ModelEmpresa;
import info.evoluti.etiquetas.utils.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

/**
 *
 * @author Rodrigo
 */
public class ControllerData {

    final static Logger logger = Logger.getLogger("");
    
    public List<ModelEmpresa> getEmpresas() {
        List<ModelEmpresa> empresas = new ArrayList<>();

        try (Connection conn = new Database().getConnection();
                PreparedStatement pst = conn.prepareStatement("SELECT IDEMPRESA, NOME, FANTASIA, FONE FROM EMPRESAS")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs != null && rs.next()) {
                    ModelEmpresa empresa = new ModelEmpresa();
                    empresa.setId(rs.getInt("IDEMPRESA"));
                    empresa.setNome(rs.getString("NOME"));
                    empresa.setFantasia(rs.getString("FANTASIA"));
                    empresa.setTelefone(rs.getString("FONE"));
                    empresas.add(empresa);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return empresas;
    }


}
