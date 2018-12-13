/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/** le modifTrack represente une ligne de tracage dans le xls de label
 * un fichier a donc un modifTrack par version qu'il a
 *
 * @author nik
 */
public class SimpleModifTrack implements ModifTrack
{
    private String formulaire;
    private String date;
    private String contributor;
    private String version;
    private String action;

    public SimpleModifTrack(String formulaire, String date, String contributor, String version, String action)
    {
        this.formulaire = formulaire;
        this.date = date;
        this.contributor = contributor;
        this.version = version;
        this.action = action;
    }

    public SimpleModifTrack()
    {
    }

    
    
    public void setFormulaire(String formulaire) {
        this.formulaire = formulaire;
    }
    

    
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
        return "ligne: formulaire: "+ formulaire+ "date: "+date+" contributor: "+contributor+" version: "+version+" action: "+action;
    }

    @Override
    public String getAction()
    {
        return action;
    }

    @Override
    public String getFormulaire() {
       return formulaire;
    }
    
    
    
       
       
    
}
