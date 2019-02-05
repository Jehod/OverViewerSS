/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.JehodFactory.overviewerss.Params;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author nik
 */
public class ComboModel implements ComboBoxModel 
{

    private ArrayList<String> list;
    private String studyName;

    public ComboModel(ArrayList<String> list)
    {
        this.list = list;
    }
    
    
    
    @Override
    public int getSize()
    {
       return list.size();
    }

    @Override
    public String getElementAt(int index)
    {
        return list.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem)
    {
        studyName = (String) anItem;
        
    }

    @Override
    public Object getSelectedItem()
    {
        return studyName;
    }

    @Override
    public void addListDataListener(ListDataListener l)
    {
        
    }

    @Override
    public void removeListDataListener(ListDataListener l)
    {
       
    }
    
}
