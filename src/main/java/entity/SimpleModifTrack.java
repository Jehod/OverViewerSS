/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author nik
 */
public class SimpleModifTrack implements ModifTrack
{
    
    private String date;
    private String contributor;
    private String version;
    private String action;

    
    
    public void setAction(String action)
    {
        this.action = action;
    }

    
    
    @Override
    public String getDate()
    {
       return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setContributor(String contributor)
    {
        this.contributor = contributor;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    @Override
    public String getContributor()
    {
        return contributor;
    }

    @Override
    public String getVersion()
    {
       return version;}

    @Override
    public String toString()
    {
        return "ligne: date: "+date+" contributor: "+contributor+" version: "+version+" action: "+action;
    }

    @Override
    public String getAction()
    {
        return action;
    }
    
    
    
       
       
    
}
