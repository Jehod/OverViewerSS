/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Outils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author nik
 */
public class JsonWorker
{

    private InputStream input = null;
    private Scanner scanner;
    private JSONObject jo;
    
    //https://www.cyril-rabat.fr/articles/index.php?article=50

    public JsonWorker(String file)
    {
        this.jo = readJsonFromFile(file);
    }

    /**
     * renvoie une liste des noms dans le tableau de l'element cible (premier niveau)
     * @param element element a lister
     * @return list des elements par leur nom
     */
    public ArrayList getJsonTableau(String element)
    {
        ArrayList list = new ArrayList();
        JSONArray tab = null;

        if (jo != null)
        {
            tab = jo.getJSONArray(element);
            System.out.println("liste de: " + tab.length()+" "+ element +" trouvé");
        }

        for (Iterator it = tab.iterator(); it.hasNext();)
        {
            JSONObject next = (JSONObject) it.next();
            list.add(next.get("name"));
            
        }
        System.out.println("list: "+list.toString());

        return list;
    }

    /**
     * return la valeur derriere le noeud element ne marche que pour les valeurs
     * strings unique
     *
     * @param element le noeud cible
     * @return la value
     */
    public String getValueString(String element)
    {
        String str = "vide";

        return str;
    }

    /**
     * lit un fichier Json et le transforme en jsonObject
     *
     * @param path le chemin du fichier json a parser
     * @return un jsonObject
     */
    private JSONObject readJsonFromFile(String path)
    {
        String json = new String();

        File file = new File(path);

        try
        {
            input = new FileInputStream(file);

        } catch (FileNotFoundException ex)
        {
            System.out.println("jsonworker inputstream: " + ex.getMessage());
            Logger.getLogger(JsonWorker.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Récupération de la chaîne JSON depuis le fichier
        scanner = new Scanner(input);
        while (scanner.hasNext())
        {
            json += scanner.nextLine();
        }
        scanner.close();
        json = json.replaceAll("[\t ]", "");

        //fermeture du fichier
        try
        {
            input.close();
        } catch (IOException ex)
        {
            System.out.println("fermeture du json: " + ex.getMessage());
            Logger.getLogger(JsonWorker.class.getName()).log(Level.SEVERE, null, ex);
        }

        jo = new JSONObject(json);

        return jo;
    }

}
