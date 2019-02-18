/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.interfaceDAO;

import java.io.Serializable;

/**
 *
 * @author nrochas
 */
public interface CertificationsFilesDAO extends Serializable{
    
    /**
     * renvoie true si il trouve un certif correspondant au formulaire et la version a l'url donné
     * @param formulaire
     * @param version
     * @return 
     */
    public Boolean checkCertif(String formulaire,String version);
    
}
