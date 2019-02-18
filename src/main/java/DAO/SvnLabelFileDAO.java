/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Outils.SVNWorker;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nrochas
 */
public class SvnLabelFileDAO extends LabelFileDAOExt{

   final String pathLabels;
   final Outils.SVNWorker svn = new SVNWorker();

   
   public SvnLabelFileDAO(String pathLabels) {
       
        this.pathLabels = pathLabels;
    }

    @Override
    public List<String> getAllLabelsFiles() {

        ArrayList list ;
        list = svn.listSVNByForm(pathLabels, ".._..");
     
        System.out.println("list "+list.toString());
        
        return list;

    }
    
}
