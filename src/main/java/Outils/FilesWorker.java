/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Outils;

import com.JehodFactory.overviewerss.Params;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nik
 */
public class FilesWorker {

    /**
     * list des fichiers par le debut du nom avec un filtre starWith
     *
     * @param path path vers le dossier a lister
     * @param filtre string se trouvant en debut des fichiers a lister
     * @return
     * @renvoie un string[] contenant les noms des fichiers
     */
    public static List ListerFilesByStart(String path, String filtre) {
        File f = new File(path);
        List list ;

        FilenameFilter filter = (File dir, String name) -> (name.toLowerCase().startsWith(filtre));

        System.out.println("Fichiers commencant par: " + filtre);
        String[] noms = f.list(filter);
        list = Arrays.asList(noms);
        return list;
    }

    /**
     * list des fichiers par la fin du nom ou par l'extention avec un filtre
     * endsWith
     *
     * @param path path vers le dossier a lister
     * @param filtre string se trouvant en fin des fichiers a lister
     * @return
     * @renvoie un string[] contenant les noms des fichiers
     */
    public static List ListerFilesByExt(String path, String filtre) {
        File f = new File(path);
        List list = new ArrayList();

        FilenameFilter filter = (File dir, String name) -> (name.toLowerCase().endsWith(filtre));

        String[] noms = f.list(filter);
        if (noms != null) {
            list = Arrays.asList(noms);
            System.out.println("list de" + filtre + list.toString());
        }
        return list;
    }

    /**
     * list les dossiers ou fichier suivant un matcher
     *
     * @param path path vers le dossier a lister
     * @param form regex pour le match
     * @return
     * @renvoie un string[] contenant les noms des fichiers
     */
    public static List ListerDirByform(String path, String form) {
        File f = new File(path);
        List list = new ArrayList();

        FilenameFilter filter = (File dir, String name) -> (name.toLowerCase().matches(form));
        System.out.println("dossiers de forme:" + form);
        String[] noms = f.list(filter);
        if (noms != null) {
            list = Arrays.asList(noms);

            String str = "";

            for (int i = 0; list != null && i < list.size(); i++) {
                str = str + " " + list.get(i);
            }
            System.out.println(str);
        }
        return list;
    }

    /**
     * va ecrire le fichier cible dans le fichier file.
     *
     * @param file
     * @param cible
     * @return
     */
    public static boolean editFiles(String file, File cible) {
        boolean bob = false;

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(cible));
            // normalement si le fichier n'existe pas, il est crée à la racine du projet
            writer.write(file);
            bob = true;
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
            bob = false;
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(ex.getMessage());
                bob = false;
            }
        }
        return bob;
    }

    /**
     * renvoie true si il trouve un fichier du path/nom donné
     *
     * @param string
     * @return
     */
    public static boolean findFile(String pathNom) {

        File file = new File(pathNom);

        return file.exists();
    }

    /**
     * list les fichiers du dossier pointé par le path, par la forme du debut et
     * la forme de fin
     *
     * @param path path pour ciblé le dossier a traiter
     * @param fiStart String du debut trié par startsWith
     * @param fiExt String dde fin trié par endsWith
     * @return retourne une liste de string de chaque fichier trouvé
     */
    public static List ListerFilesByExtAndStart(String path, String fiStart, String fiExt) {
        File f = new File(path);
        List list = new ArrayList();

        //on ajoute lowerCase because le .xlsx est parfois ecrit .XLSX
        FilenameFilter filter = (File dir, String name) -> (name.toLowerCase().endsWith(fiExt));

        String[] noms = f.list(filter);
        if (noms != null) {
            for (String nom : noms) {
                if (nom.startsWith(fiStart)) {
                    list.add(nom);
                }

            }
        }

        System.out.println("list de" + list.toString());
        return list;
    }

    /**
     * list les fichiers d'une extention ext et contenant la chaine de caractere
     * cible
     *
     * @param path le chemin du dossier cilbe
     * @param cible la chaine de caractrre recherché (le nom du fichier est
     * lowercasé donc mettre la cible en minuscule)
     * @param ext l'extention des fichiers que l'on cherche
     * @return la liste des fichiers
     */
    public static List ListerFilesByContainsAndExt(String path, String cible, String ext) {

        
        File f = new File(path);
        List list = new ArrayList();

        //on ajoute lowerCase because le .xlsx est parfois ecrit .XLSX, idem pour .PDF
        FilenameFilter filter = (File dir, String name) -> (name.toLowerCase().endsWith(ext));

        String[] noms = f.list(filter);

        if (noms != null) {
            for (String nom : noms) {             
                if (nom.toLowerCase().contains(cible)) {
                    list.add(nom);
                }
            }
        }

        System.out.println("list de" + list.toString());
        return list;
    }

    /**
     * methode pour vider les fichiers d'un dossier puis detruit le dossier vidé
     * attention ne fonctionne pas si contient d'autre dossiers remplis
     * @param fi 
     */
    public static void clearDir(File fi, String path) {
        /*String[] li = fi.list();
        for (String st : li) {
            System.out.println("clean: "+st);
            new File(path +st).delete();
        }
        fi.delete();
    */
    path = Params.getInstance().getPathTEMP();
        

        //dans le commande ne pas oublié les '' en plus, pour que ce soit reconnu dans Powershell
        String command = "powershell.exe Remove-Item -Recurse -force '" + path  + "'";//'svn://svn.kayentis.fr:14000/Kayentis/testeclient/teststudy'";

        try {

            // Executing the command
            Process powerShellProcess = Runtime.getRuntime().exec(command);
            // Getting the results
            powerShellProcess.getOutputStream().close();
            String line;
            System.out.print("Standard Output: ");
            try (BufferedReader stdout = new BufferedReader(new InputStreamReader(
                    powerShellProcess.getInputStream()))) {
              
                System.out.println("test: "+path +" = path removed");
               
            }
      
            BufferedReader stderr = new BufferedReader(new InputStreamReader(
                    powerShellProcess.getErrorStream()));
            while ((line = stderr.readLine()) != null) {
                System.out.println("Error "+line);
                
            }
            stderr.close();

        } catch (IOException ex) {
            System.out.println("catch du check in SVN " + ex.getLocalizedMessage());
            Logger.getLogger(SVNWorker.class.getName()).log(Level.SEVERE, null, ex);
        }

       
    
    }
        
        
}
