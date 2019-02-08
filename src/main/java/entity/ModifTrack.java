/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *objet qui represente une ligne du cartouche de suivi du label du questionnaire
 * @author nik
 */
public interface ModifTrack extends Serializable
        
{
    String getFormulaire();
    String getDate();
    String getContributor();
    String getVersion();
    String getAction();
    
}
