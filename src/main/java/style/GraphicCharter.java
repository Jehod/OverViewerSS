/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package style;

import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author nik
 */
public class GraphicCharter
{
     public static Color colorBack;
    public static Color colorFont;
    public static Color colorBlue;
    public static Color colorFront;
    public static Color colorYellow;
    public static Color colorGreen;
    public static Font fontCorps;
    public static Font fontButton;
    
    //titre3 est le + petit
    public static Font titre3;
    public static Font titre2;
    //titre 1 est le plus grand
    public static Font titre1;
    public static Font fontCorpsReduit;
    

    private GraphicCharter()
    {
        colorBack = new Color(0, 156, 180);
        colorFront = new Color(251, 187, 0);
        colorFont = new Color(76, 79, 83);
        colorGreen = new Color(68,185,81);
        colorYellow = new Color(251, 187, 0);
        colorBlue = new Color(0, 156, 180);
        fontCorps = new Font("Tahoma", Font.PLAIN, 12);
        titre3 = fontCorps.deriveFont(Font.BOLD, 13);

        fontButton = fontCorps.deriveFont(13);
        titre2 = fontCorps.deriveFont(Font.BOLD,15);
        titre1 = fontCorps.deriveFont(Font.BOLD,18);
        fontCorpsReduit = fontCorps.deriveFont(7);
    }
    
    public static GraphicCharter getInstance()
    {
        return GraphicCharterHolder.INSTANCE;
    }
    
    private static class GraphicCharterHolder
    {

        private static final GraphicCharter INSTANCE = new GraphicCharter();
    }
}
