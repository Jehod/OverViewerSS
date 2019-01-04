/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.File;

/**
 *
 * @author nrochas
 */
public class ScreenFilesDAO implements ScreenshotFilesDAO {

    private String fileName;

    public ScreenFilesDAO(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean checkExisting(String langue,String formulaire, String version) {
        boolean bob = false;

        if (new File(fileName+"/"+langue+"/"+formulaire+"_"+langue+"_v"+version+".pdf").exists()) {
            bob = true;
            System.out.println("+++++++++ "+fileName+"/"+langue+"/"+formulaire+"_"+langue+"_v"+version+".pdf"+ " a ete trouve++++");
        }

        return bob;

    }

}
