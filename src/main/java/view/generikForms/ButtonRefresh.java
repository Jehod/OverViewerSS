/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.generikForms;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author nrochas
 */
public class ButtonRefresh extends ButtonGenerik{

    public ButtonRefresh() {
        
        super.setIcon(new ImageIcon("/refresh.png"));
          super.setSize(20, 20);
        super.setBackground(Color.green);
    }

    
    
    
    
}
