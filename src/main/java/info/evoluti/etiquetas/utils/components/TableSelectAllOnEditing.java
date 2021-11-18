/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.evoluti.etiquetas.utils.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Rodrigo
 */
public class TableSelectAllOnEditing extends JTable {

    boolean isSelectAll = true;
    
    @Override
    public boolean editCellAt(int row, int column, EventObject e) {
        boolean result = super.editCellAt(row, column, e);
        selectAll(e);
        return result;
    }

    private void selectAll(EventObject e) {
        final Component editor = getEditorComponent();
        if (editor == null
                || !(editor instanceof JTextComponent)) {
            return;
        }

        if (e == null) {
            ((JTextComponent) editor).selectAll();
            ((JTextComponent) editor).setSelectionColor(new Color(29, 127, 207));
            ((JTextComponent) editor).setBorder(javax.swing.BorderFactory.createLineBorder(new Color(77, 164, 235), 1));
            return;
        }

        //  Modo de edição ao pressionar qualquer tecla
        if (e instanceof KeyEvent && isSelectAll) {
            ((JTextComponent) editor).selectAll();
            ((JTextComponent) editor).setSelectionColor(new Color(29, 127, 207));
            ((JTextComponent) editor).setBorder(javax.swing.BorderFactory.createLineBorder(new Color(77, 164, 235), 1));
            return;
        }

        //  Modo de edição ao pressionar F2
        if (e instanceof ActionEvent && isSelectAll) {
            ((JTextComponent) editor).selectAll();
            ((JTextComponent) editor).setSelectionColor(new Color(29, 127, 207));
            ((JTextComponent) editor).setBorder(javax.swing.BorderFactory.createLineBorder(new Color(77, 164, 235), 1));
            return;
        }

        //   Modo de edição ao pressionar ao dar 2 cliques com o mouse
        if (e instanceof MouseEvent && isSelectAll) {
            SwingUtilities.invokeLater(() -> {
                ((JTextComponent) editor).selectAll();
                ((JTextComponent) editor).setSelectionColor(new Color(29, 127, 207));
                ((JTextComponent) editor).setBorder(javax.swing.BorderFactory.createLineBorder(new Color(77, 164, 235), 1));
            });
        }

    }

    /*
     *  habilitar/desabilitar modo de edição para selecionar tudo ao entrar na célula
     */
    public void setSelectAllForEdit(boolean isSelectAll) {
        this.isSelectAll = isSelectAll;
    }
}
