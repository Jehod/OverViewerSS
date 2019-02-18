/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Outils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nrochas
 */
public class SVNWorker {

    boolean bob;
    /**
     * permet de commiter un dossier deja versioné
     *
     * @param comment le commentaire du commit
     * @param path le dossier versioné en adresse local
     * @return
     */
    public String commitSVN(String comment, String path) {

        String str = null;
        //ne pas oublié les guillements pour pas planter en powershell
        String command = "powershell.exe svn commit  -m'" + comment + "' '" + path + "'";

        //command = command + comment + "' '" + path + "'"; // 'D:\\project\\CAIN457M2301\\Settings\\Labels\\AF_ZA'"; // $PSVersionTable.PSVersion";
        try {
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
                    str = str + line;
                }
            }
            System.out.println("Standard Error:");
            try (BufferedReader stderr = new BufferedReader(new InputStreamReader(
                    powerShellProcess.getErrorStream()))) {
                while ((line = stderr.readLine()) != null) {
                    System.out.println(line);
                    str = str + line;
                }
            }
            System.out.println("Done ");
            str = str + "Done";
        } catch (IOException ex) {
            System.out.println("catch dans le svn " + ex.getLocalizedMessage());
            Logger.getLogger(SVNWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str;
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

        //dans le commande ne pas oublié les '' en plus, pour que ce soit reconnu dans Powershell
        String command = "powershell.exe svn list '" + URL + "'";//'svn://svn.kayentis.fr:14000/Kayentis/testeclient/teststudy'";

        try {

            // Executing the command
            Process powerShellProcess = Runtime.getRuntime().exec(command);
            // Getting the results
            powerShellProcess.getOutputStream().close();
            String line;
            System.out.println("Standard Output:");
            BufferedReader stdout = new BufferedReader(new InputStreamReader(
                    powerShellProcess.getInputStream()));
            while ((line = stdout.readLine()) != null) {
                String str = line; //extractName(line, ext);
                if (str != null && str.endsWith(ext)) {
                    list.add(str);
                    System.out.println(" sur la liste: " + str);
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
     * (obsolete) utile quand on tire la liste svn en verbose (ajouter -v à la
     * ligne de commande)
     *
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

    /**
     * ajoute un fichier non versionné dans svn
     *
     * @param pathFile adress du fichier a deposer
     * @param url adresse du depot
     * @return la string de output le verbiage bon ou mauvais de la commande
     */
    public String mountInSvn(String pathFile, String url) {
        String str = null;
        String command = "powershell.exe svn import  -m'AutoImport' '" + pathFile + "' '" + url + "'";

        try {
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
                    str = str + line;
                }
            }
            System.out.println("Standard Error:");
            try (BufferedReader stderr = new BufferedReader(new InputStreamReader(
                    powerShellProcess.getErrorStream()))) {
                while ((line = stderr.readLine()) != null) {
                    System.out.println(line);
                    str = str + line;
                }
            }
            System.out.println("Done");
            str = str + " Done";
        } catch (IOException ex) {
            System.out.println("catch dans le svn " + ex.getLocalizedMessage());
            Logger.getLogger(SVNWorker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return str;
    }

    /**
     * renvoie true si il trouve un fichier du nom cible dans le dossier pointé
     * par url
     *
     * @param URL
     * @param cible
     * @return
     */
    public boolean CheckExistInSVN(String URL, String cible) {

         bob = false;

        //dans le commande ne pas oublié les '' en plus, pour que ce soit reconnu dans Powershell
        String command = "powershell.exe svn list '" + URL + cible + "'";//'svn://svn.kayentis.fr:14000/Kayentis/testeclient/teststudy'";

        try {

            // Executing the command
            Process powerShellProcess = Runtime.getRuntime().exec(command);
            // Getting the results
            powerShellProcess.getOutputStream().close();
            String line;
            System.out.println("Standard Output:");
            try (BufferedReader stdout = new BufferedReader(new InputStreamReader(
                    powerShellProcess.getInputStream()))) {
                while ((line = stdout.readLine()) != null) {
                    bob = true;
                    System.out.println("trouve " + line);

                }
            }
            System.out.println("Standard Error:");
            BufferedReader stderr = new BufferedReader(new InputStreamReader(
                    powerShellProcess.getErrorStream()));
            while ((line = stderr.readLine()) != null) {
                System.out.println(line);
            }
            stderr.close();

        } catch (IOException ex) {
            System.out.println("catch du check in SVN " + ex.getLocalizedMessage());
            Logger.getLogger(SVNWorker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bob;
    }

    /**
     * detruit un fichier cible present dans l'adresse svn filePath
     *
     * @param filePath
     * @param cible
     * @return
     */
    public boolean deleteInSVN(String filePath, String cible) {

         bob = false;

        //dans le commande ne pas oublié les '' en plus, pour que ce soit reconnu dans Powershell
        String command = "powershell.exe svn delete -m'Temporary destruction' '" + filePath + cible + "'";//'svn://svn.kayentis.fr:14000/Kayentis/testeclient/teststudy'";

        try {

            // Executing the command
            Process powerShellProcess = Runtime.getRuntime().exec(command);
            // Getting the results
            powerShellProcess.getOutputStream().close();
            String line;
            System.out.println("Standard Output:");
            try (BufferedReader stdout = new BufferedReader(new InputStreamReader(
                    powerShellProcess.getInputStream()))) {
                while ((line = stdout.readLine()) != null) {
                    bob = true;
                    System.out.println("delete " + line);

                }
            }
            System.out.println("Standard Error:");
            BufferedReader stderr = new BufferedReader(new InputStreamReader(
                    powerShellProcess.getErrorStream()));
            while ((line = stderr.readLine()) != null) {
                System.out.println(line);
            }
            stderr.close();

        } catch (IOException ex) {
            System.out.println("catch du delete in SVN " + ex.getLocalizedMessage());
            Logger.getLogger(SVNWorker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bob;
    }

    public ArrayList listSVNByForm(String pathLabels, String form) {
        ArrayList<String> list = new ArrayList();

        //dans le commande ne pas oublié les '' en plus, pour que ce soit reconnu dans Powershell
        String command = "powershell.exe svn list '" + pathLabels + "'";//'svn://svn.kayentis.fr:14000/Kayentis/testeclient/teststudy'";

        try {

            // Executing the command
            Process powerShellProcess = Runtime.getRuntime().exec(command);
            // Getting the results
            powerShellProcess.getOutputStream().close();
            String line;
            System.out.println("Standard Output:");
            BufferedReader stdout = new BufferedReader(new InputStreamReader(
                    powerShellProcess.getInputStream()));
            while ((line = stdout.readLine()) != null) {
                String str = line; //extractName(line, ext);
                if (str != null && str.matches(form + "/")) {
                    list.add(str);

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

    public List<String> listSVNByExtAndStart(String URL, String prefix, String ends) {
         ArrayList list = new ArrayList();

        //dans le commande ne pas oublié les '' en plus, pour que ce soit reconnu dans Powershell
        String command = "powershell.exe svn list '" + URL + "'";//'svn://svn.kayentis.fr:14000/Kayentis/testeclient/teststudy'";

        try {

            // Executing the command
            Process powerShellProcess = Runtime.getRuntime().exec(command);
            // Getting the results
            powerShellProcess.getOutputStream().close();
            String line;
            System.out.println("Standard Output:");
            BufferedReader stdout = new BufferedReader(new InputStreamReader(
                    powerShellProcess.getInputStream()));
            while ((line = stdout.readLine()) != null) {
                String str = line; //extractName(line, ext);
                if (str != null && str.endsWith(ends) && str.startsWith(prefix) ) {
                    list.add(str);
                    System.out.println(" sur la liste: " + str);
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

    public File copyInTempLocal(String URL, String cible, String pathTEMP) {
       
        File file =null;
        
         String command = "powershell.exe svn export --force '" + URL +cible+ "' '"+pathTEMP+"'";

        try {

            // Executing the command
            Process powerShellProcess = Runtime.getRuntime().exec(command);
            // Getting the results
            powerShellProcess.getOutputStream().close();
            String line;
            System.out.println("Standard Output:");
            BufferedReader stdout = new BufferedReader(new InputStreamReader(
                   
                    powerShellProcess.getInputStream()));
            while ((line = stdout.readLine()) != null) {
                String str = line; 
                System.out.println(str );
                file = new File(pathTEMP+cible);
         
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

        return file;
    }

    
}
