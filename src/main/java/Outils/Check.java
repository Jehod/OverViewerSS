/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Outils;

/**
 *
 * @author nrochas
 */
public class Check {

    /**
     * check si not null et not ""
     *
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
}
