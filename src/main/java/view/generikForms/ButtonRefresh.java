/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.generikForms;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;

/**
 *
 * @author nrochas
 */
public class ButtonRefresh extends ButtonGenerik{

    public ButtonRefresh() {
        
        super.setIcon(new ImageIcon("/refresh1.png"));
          super.setSize(27, 27);
          super.setPreferredSize(new Dimension(27, 27));
        super.setBackground(style.GraphicCharter.colorGreen);
    }

   
    
    
    
}
