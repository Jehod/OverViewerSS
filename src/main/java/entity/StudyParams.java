/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**objet reprenant toutes les infos relative a l'etude ( tiré du Json de config)
 *
 * @author nrochas
 */
interface StudyParams extends Serializable{

    
    public String getTrad();
    
    public HashMap<String, String> getMap();

    public String getPathScreens() ;
    
    public String getPathLabels() ;
    
    public String getTabModel() ;
    
    public ArrayList<String> getListStudyPath() ;
    
    public String getPathCertifs();
    
    public boolean getFontSamsung();
}
