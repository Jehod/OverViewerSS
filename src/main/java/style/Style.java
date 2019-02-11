/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package style;

/**
 *
 * @author nik
 */
public interface Style
{
    /**
     * creer le header du tracker y compris les header intercallé dans le study tracker
     * @param index  a quel ligne doit etre ecrit le header
     * @param dec   de combien doit on pousser le tracker ( avec dec =0. le tracker commence colonne 0, ect..)
     */
    void createHeader(int index, int dec);
    
    /**
     * creer le titre intercallé entre les tracker dans le study tracker
     * @param index
     * @param title 
     */
    void createBlocTitle(int index, String title);
    
    /**
     * colorie une ligne sur deux
     */
    void pairStyle();
    
    /**
     * pas utilisé pour l'instant
     */
    void blocStyle();
    
    /**
     * creer le titre du studytracker ligne 0
     * @param title 
     */
    void createTitle(String title);
    
    /**
     * colorie les cellules suivant leur contenu ( pour ameliorer la lecture des points importants)
     */
    void colorCell(); 
    
    
}
