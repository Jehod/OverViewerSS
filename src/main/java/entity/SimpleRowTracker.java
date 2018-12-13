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
public class SimpleRowTracker implements RowTracker {

    final String traceNone = "None";


    private String version;
    private String formulaire;
    private String dateVers;
    private String screenDone;
    private String sendToExt;
    private String finalized;
    private String certified;

    /**
     * 
    
     * @param version
     * @param formulaire
     * @param dateVers
     * @param screenDone
     * @param sendToExt
     * @param finalized
     * @param certified 
     */
    public SimpleRowTracker( String version, String formulaire, String dateVers, String screenDone, String sendToExt, String finalized, String certified) {
        
        this.version = version;
        this.formulaire = formulaire;
        this.dateVers = dateVers;
        this.screenDone = screenDone;
        this.sendToExt = sendToExt;
        this.finalized = finalized;
        this.certified = certified;
    }

    public SimpleRowTracker() {
        
        version = "A.A.A";
        formulaire = "VoidRowTracker";
        dateVers = "00/00/00";
        screenDone = traceNone;
        sendToExt = traceNone;
        finalized = traceNone;
        certified = traceNone;
    }

    public SimpleRowTracker( String version, String formulaire, String date) {
        
        this.version = version;
        this.formulaire = formulaire;
        this.dateVers = date;
        screenDone = traceNone;
        sendToExt = traceNone;
        finalized = traceNone;
        certified = traceNone;
    }

  
    public void setVersion(String version) {
        this.version = version;
    }

    public void setFormulaire(String formulaire) {
        this.formulaire = formulaire;
    }

    public void setDateVers(String dateVers) {
        this.dateVers = dateVers;
    }

    public void setScreenDone(String screenDone) {
        this.screenDone = screenDone;
    }

    public void setSendToExt(String sendToExt) {
        this.sendToExt = sendToExt;
    }

    public void setFinalized(String finalized) {
        this.finalized = finalized;
    }

    public void setCertified(String certified) {
        this.certified = certified;
    }

    @Override
    public String getFormulaire() {
        return formulaire;
    }


    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return  " formulaire: " + formulaire + " version: " + version
                +" dateversion: "+dateVers+" screenDone?: "+screenDone+" sendToExt?: "+sendToExt
                +" finalized?: "+finalized+" certified?: "+certified;
    }

    @Override
    public String getDateVers() {
        return dateVers;
    }

    @Override
    public String getScreenDone() {
        return screenDone;
    }

    @Override
    public String getSendtoExt() {
        return sendToExt;
    }

    @Override
    public String getFinalized() {
        return finalized;
    }

    @Override
    public String getCertified() {
        return certified;
    }

}
