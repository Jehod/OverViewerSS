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
public class SimpleRowTracker implements RowTracker
{
    
    private String langue;
    private String version;
    private String formulaire;

    public void setLangue(String langue)
    {
        this.langue = langue;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public void setFormulaire(String formulaire)
    {
        this.formulaire = formulaire;
    }
   
          

    

    @Override
    public String getFormulaire()
    {
        return formulaire;
    }

    @Override
    public String getLangue()
    {
        return langue;
    }

    @Override
    public String getVersion()
    {
       return version;
    }

    @Override
    public String toString()
    {
        return "Langue: "+langue+" formulaire: "+formulaire+" version: "+version;
    }
    
    
    
}
