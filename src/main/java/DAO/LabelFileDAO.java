/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;

/**pour gerer le dossier de labels (version local)
 *
 * @author nrochas
 */
public class LabelFileDAO extends LabelFileDAOExt{

    final String pathLabels;

   public LabelFileDAO(String pathLabels) {
        super();
        this.pathLabels = pathLabels;
    }

    @Override
    public List<String> getAllLabelsFiles() {

        List list ;
        
        list = Outils.FilesWorker.ListerDirByform(pathLabels, ".._..|..._..|.._...|..._...");
        return list;

    }

}
