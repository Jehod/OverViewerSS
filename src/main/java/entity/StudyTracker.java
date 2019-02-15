/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;

/**objet representant le studytracker, qui a l'agregat de tout les trackers
 *
 * @author nik
 */
public interface StudyTracker extends Serializable
{
    List<SimpleTracker> getAllTrackers();
    //String getStudyPath();
    //String getLabelsPath();
    //String getScreenShotsPath();
    
    
}
