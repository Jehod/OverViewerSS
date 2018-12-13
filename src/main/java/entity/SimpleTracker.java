/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author nik
 */
public class SimpleTracker implements Tracker
        
{

    private  String date;
    private  ArrayList<SimpleRowTracker> allRowTracker;
    private  String name;

    public SimpleTracker(String date, ArrayList<SimpleRowTracker> allRowTracker, String name)
    {
        this.date = date;
        this.allRowTracker = allRowTracker;
        this.name = name;
    }

    
    public SimpleTracker()
    {
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setAllRowTracker(ArrayList<SimpleRowTracker> allRowTracker)
    {
        this.allRowTracker = allRowTracker;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    

    
    @Override
    public ArrayList<SimpleRowTracker> getAllRowTracker()
    {
        return allRowTracker;
    }

    @Override
    public String getName()
    {
       return name;
    }

    @Override
    public String getDate()
    {
        return date;
    }

  
}
