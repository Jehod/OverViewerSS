/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author nik
 */
public class ComboModel extends AbstractListModel
{

    private ArrayList<String> list;

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
    
}
