/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;

/**
 *object representant le fichier de lable d'un questionnaire 
 * @author nik
 */
public interface LabelFormulaire extends Serializable    
{
    List<ModifTrack>getModifTrackFromLabel();
    String getLastVersion();
    String getLastDate();
    String getLastContributor();
    
    
    
}
