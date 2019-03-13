/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.JehodFactory.overviewerss.Params;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.AbstractListModel;

/**
 *
 * @author nrochas
 */
public class ListMapModel extends AbstractListModel{

     protected ArrayList<String> list;
     String inter;

    public ListMapModel(String inter)
        {
          this.inter = inter;  
        list = listify(Params.getInstance().studyParam.getMap());
        
        }

    public void add(String pa)
        {
        if (!list.contains(pa)) {
            this.list.add(pa);
            fireIntervalAdded(this.list, this.list.size() - 1, this.list.size() - 1);
        }
        }

    public void remove(String pa)
        {
        int i = 0;
        while (!this.list.get(i).equals(pa) || i>list.size())
        {
            i++;
        }
        this.list.remove(i);
        fireIntervalRemoved(this.list, i, i);
        }
    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public Object getElementAt(int index) {
        return list.get(index);
    }

    private ArrayList<String> listify(HashMap<String, String> map) {
        ArrayList li = new ArrayList();
        
        map.entrySet().forEach((entry) -> {
            li.add(entry.getKey()+inter+entry.getValue());
         });
        
        return li;
    }
    
}
