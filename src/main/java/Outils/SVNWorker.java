/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Outils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nrochas
 */
public class SVNWorker {

    /**
     * permet de commiter un dossier deja versioné
     *
     * @param comment le commentaire du commit
     * @param path le dossier versioné en adresse local
     */
    public void commitSVN(String comment, String path) {

        //ne pas oublié les guillements pour pas planter en powershell
        String command = "powershell.exe svn commit  -m'";

        try {

            command = command + comment + "' '" + path + "'"; // 'D:\\project\\CAIN457M2301\\Settings\\Labels\\AF_ZA'"; // $PSVersionTable.PSVersion";

            // Executing the command
            Process powerShellProcess = Runtime.getRuntime().exec(command);
            // Getting the results
            powerShellProcess.getOutputStream().close();
            String line;
            System.out.println("Standard Output:");
            try (BufferedReader stdout = new BufferedReader(new InputStreamReader(
                    powerShellProcess.getInputStream()))) {
                while ((line = stdout.readLine()) != null) {
                    System.out.println(line);
                }
            }
            System.out.println("Standard Error:");
            try (BufferedReader stderr = new BufferedReader(new InputStreamReader(
                    powerShellProcess.getErrorStream()))) {
                while ((line = stderr.readLine()) != null) {
                    System.out.println(line);
                }
            }
            System.out.println("Done ");
        } catch (IOException ex) {
            System.out.println("catch dans le svn " + ex.getLocalizedMessage());
            Logger.getLogger(SVNWorker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * list les fichiers dans un dossier svn sur le depot filtrer par
     * l'extention
     *
     * @param URL l'adresse du depot svn// ..
     * @param ext l'extention cible ( .txt, .pdf, ect..)
     * @return la liste des fichiers trouvées en string
     */
    public ArrayList listSVNByExt(String URL, String ext) {
        ArrayList list = new ArrayList();
        try {

            //dans le commande ne pas oublié les '' en plus, pour que ce soit reconnu dans Powershell
            String command = "powershell.exe svn list  -v '" + URL + "'";//'svn://svn.kayentis.fr:14000/Kayentis/testeclient/teststudy'";
            // Executing the command
            Process powerShellProcess = Runtime.getRuntime().exec(command);
            // Getting the results
            powerShellProcess.getOutputStream().close();
            String line;
            System.out.println("Standard Output:");
            BufferedReader stdout = new BufferedReader(new InputStreamReader(
                    powerShellProcess.getInputStream()));
            while ((line = stdout.readLine()) != null) {
                String str = extractName(line, ext);
                if (str != null && str.endsWith(ext)) {
                    list.add(str);
                    System.out.println("en plus sur la liste: " + str);
                }

            }
            stdout.close();
            System.out.println("Standard Error:");
            BufferedReader stderr = new BufferedReader(new InputStreamReader(
                    powerShellProcess.getErrorStream()));
            while ((line = stderr.readLine()) != null) {
                System.out.println(line);
            }
            stderr.close();
            System.out.println("Done :");

        } catch (IOException ex) {
            System.out.println("catch du listsVN " + ex.getLocalizedMessage());
            Logger.getLogger(SVNWorker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    /**
     * methode pour parser les lignes recuperé de sVn pour n'avoir que le nom
     * @param line
     * @param ext
     * @return 
     */
    private String extractName(String line, String ext) {
        String str = null;
        if (line.contains(":") && line.contains(ext)) {
            String[] tab = line.split(":");
            str = tab[1];
            str = str.substring(3);

        }
        return str;
    }
}
