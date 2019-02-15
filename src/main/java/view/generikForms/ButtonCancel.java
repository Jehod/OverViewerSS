/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.generikForms;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author nrochas
 */
public class ButtonCancel extends JButton
{


    public ButtonCancel()
    {
        super.setSize(140, 25);
        super.setForeground(style.GraphicCharter.colorFont);
        super.setFont(style.GraphicCharter.fontButton);
        super.setText("CANCEL");
        super.setToolTipText("Annule les modificatiosn non svg et retourne à la fenetre precedente)");
    }

}
