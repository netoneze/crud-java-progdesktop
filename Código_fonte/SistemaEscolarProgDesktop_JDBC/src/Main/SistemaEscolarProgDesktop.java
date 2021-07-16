/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Views.TelaPrincipal;
import java.awt.BorderLayout;

/**
 *
 * @author Nelson Toneze
 */
public class SistemaEscolarProgDesktop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TelaPrincipal telaPrincipal = new TelaPrincipal();
        telaPrincipal.setLayout(new BorderLayout());
        telaPrincipal.setVisible(true);
    }
    
}
