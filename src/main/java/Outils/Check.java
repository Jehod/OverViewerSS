/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Outils;

import com.JehodFactory.overviewerss.Params;
import entity.SimpleStudyParam;
import java.io.File;
import java.util.List;

/**
 *
 * @author nrochas
 */
public class Check {

    private static boolean bob;

    /**
     * check si not null et not "" renvoie true si le mot est correct
     *
     * @param str
     * @return
     */
    public static boolean isGood(String str) {
        bob = false;
        if (str != null && !str.trim().equals("")) {
            bob = true;
        }
        return bob;
    }

    /**
     * verifie si une string est presente dans un tableau de string
     *
     * @param cible
     * @param list
     * @return true si il trouve la cible dans la list
     */
    public static boolean checkIsIn(String cible, List<String> list) {
        bob = false;

        if (cible != null && list != null && !cible.equals("")) {
            for (String str : list) {
                if (str.equals(cible)) {
                    bob = true;
                }
            }
        }

        return bob;
    }

    /**
     * Enleve les underscore , les tirets, les espaces et passe tout en
     * minuscule
     *
     * @param formulaire la sequence a standardiser
     * @return la sequence transformée
     */
    public static String standardise(String formulaire) {
        String std;
        std = formulaire.replace("_", "");
        std = std.replace("-", "");
        std = std.replace(" ", "");
        std = std.toLowerCase();

        return std;
    }

    /**
     * verifie la qualité de l'architecture necessaire au deroulement de l'appli
     *
     * @param local boolean true en local et false en svn
     * @param path path local ou svn1400
     * @param pathSvnDoc path svn 1500
     * @return
     */
    public static String TestNeededPath(Boolean local, String path, String pathSvnDoc) {
        String str = "ok";

        SimpleStudyParam ssp = Params.getInstance().getStudyParam();
        File fileTest;

        if (local) {
            fileTest = new File(path + ssp.getPathLabels());
            if (fileTest.exists()) {
                fileTest = new File(path + ssp.getPathScreens());

            } else {
                str = "Local path Labels non conforme";
            }
            if (!fileTest.exists()) {
                str = "Local path Screenshots non conforme";
            }
        } else {

            SVNWorker svn = new SVNWorker();

            if (svn.CheckExistInSVN(path + ssp.getPathLabels(), "")) {
                bob = svn.CheckExistInSVN(path + ssp.getPathScreens(), "");
            } else {
                System.out.println("Svn path ");
            }
        }
        return str;
    }
}
