/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Outils;

import java.util.ArrayList;

/**
 *
 * @author nrochas
 */
public class Check {

    /**
     * check si not null et not ""
     * renvoie true si le mot est correct
     * @param str
     * @return
     */
    public static boolean isGood(String str) {
        boolean bob = false;
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
    public static boolean checkIsIn(String cible, ArrayList<String> list) {
        boolean bob = false;

        if (cible != null && list != null && !cible.equals("")) {
            for (String str : list) {
                if (str.equals(cible)) {
                    bob = true;
                }
            }
        }

        return bob;
    }
}
