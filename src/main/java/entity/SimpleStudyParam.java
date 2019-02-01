/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.HashMap;

/**
 *
 * @author nik
 */
public class SimpleStudyParam implements StudyParam
{
    
    private String studyPath;
    private String trad;
    private String pathLabels;
    private String PathScreens;
    private HashMap<String,String> map;
    

    public SimpleStudyParam(String studyPath, String trad, String pathLabels, String PathScreens, HashMap<String, String> map)
    {
        this.studyPath = studyPath;
        this.trad = trad;
        this.pathLabels = pathLabels;
        this.PathScreens = PathScreens;
        this.map = map;
    }
    
    

    @Override
    public String getStudyPath()
    {
        return studyPath;
    }

    @Override
    public String getTrad()
    {
        return trad;
    }

    @Override
    public HashMap<String, String> getMap()
    {
        return map;
    }

    

    @Override
    public String getPathlabels()
    {
        return pathLabels;
    }

    @Override
    public String getPathScreens()
    {
        return PathScreens;
    }
    
}
