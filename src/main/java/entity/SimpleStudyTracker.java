/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import entity.interfaceEntity.StudyTracker;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nik
 */
public class SimpleStudyTracker implements StudyTracker
{
    private final ArrayList<SimpleTracker> allTrackers;


    public SimpleStudyTracker(ArrayList<SimpleTracker> allTrackers)//, String studyPath, String labelsPath, String screenshotsPath)
    {
        this.allTrackers = allTrackers;
       
    }
    

    
    
    
    @Override
    public List<SimpleTracker> getAllTrackers()
    {
        return allTrackers;
    }


    @Override
    public String toString()
    {
        return "StudyTracker: Number of Trackers: "+allTrackers.size(); //+ " Path: "+studyPath+" LabelPath: "+labelsPath+" ScreenShotPath: "+screenshotsPath ;
    }
    
    
    
}
