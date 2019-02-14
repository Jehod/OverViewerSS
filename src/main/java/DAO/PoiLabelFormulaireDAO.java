/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.SimpleModifTrack;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nrochas
 */
public class PoiLabelFormulaireDAO implements LabelFormulaireDAO{
    
    private String fileName;

   /* public PoiLabelFormulaireDAO(String fileName) {
        super();
        this.fileName = fileName;
    }*/
    
////a changer toutes ses parties
    @Override
    public List<SimpleModifTrack> findAllModifTrack() {
        PoiModifTrackDAO pmtk = new PoiModifTrackDAO(fileName);
        return null;// pmtk.findAllModifTrack();
    }

    @Override
    public String getLastVersion() {
        PoiModifTrackDAO pmtk = new PoiModifTrackDAO(fileName);
        final List<SimpleModifTrack> allModifTrack = new ArrayList<>();
        
         return null; // allModifTrack.get(allModifTrack.size()).getVersion();
        
    }

    @Override
    public String getLastContributor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLastDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
