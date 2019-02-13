/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Outils.SVNWorker;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author nrochas
 */
public class CertifFilesDAO implements CertificationsFilesDAO {

    private final String pathCertifs;
    private final String langue;
    private final Outils.SVNWorker svn = new SVNWorker();
    private final ArrayList list;

    public CertifFilesDAO(String pathCertifs, String langue) {
        this.pathCertifs = pathCertifs + "/" + langue;
        this.langue = langue;
         list = svn.listSVNByExt(this.pathCertifs, ".pdf");
    }

    @Override
    public Boolean checkCertif(String formulaire, String version) {
        Boolean bob;
       
        String cible = formulaire + "_" + langue + "-certification.pdf";

        bob = Outils.Check.checkIsIn(cible, list);

        if (bob) {
            System.out.println("+++++++++ " + pathCertifs +  "/" + formulaire + "_" + langue + "-certification.pdf" + " a ete trouve++++");
        } else {
            System.out.println("+++++++++ " + pathCertifs +  "/" + formulaire + "_" + langue + "-certification.pdf" + " InTROUVABLE++++");
        }
        
        return bob;
    }

}
