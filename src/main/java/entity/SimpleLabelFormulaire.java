/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import entity.interfaceEntity.LabelFormulaire;
import entity.interfaceEntity.ModifTrack;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nik
 */
public class SimpleLabelFormulaire implements LabelFormulaire
{
    
    private ArrayList<ModifTrack> modifTrackFromLabel;
    private String lastVersion;
    private String lastDate;
    private String lastContributor;

    @Override
    public List<ModifTrack> getModifTrackFromLabel()
    {
        return modifTrackFromLabel;
    }

    @Override
    public String getLastVersion()
    {
       return lastVersion;
    }

    @Override
    public String getLastDate()
    {
       return lastDate;
    }

    @Override
    public String getLastContributor()
    {
        return lastContributor;
    }
    
}
