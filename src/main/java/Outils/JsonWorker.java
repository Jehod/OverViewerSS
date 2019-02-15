/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Outils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author nik
 */
public class JsonWorker {

    private InputStream input = null;
    private Scanner scanner;
    private JSONObject jo;
    private FileWriter fw = null;
    private String fileConfig;

    //https://www.cyril-rabat.fr/articles/index.php?article=50
    //http://jsonviewer.stack.hu/ 
    // pour visualiser les json
    public JsonWorker(String file) {
        fileConfig = file;
        this.jo = readJsonFromFile(file);

    }

    /**
     * renvoie une liste des noms dans le tableau de l'element cible (premier
     * niveau)
     *
     * @param element element a lister
     * @return list des elements par leur nom
     */
    public ArrayList getJsonTableau(String element) {
        ArrayList list = new ArrayList();
        JSONArray tab = null;

        if (jo != null) {
            tab = jo.getJSONArray(element);
            System.out.println("liste de: " + tab.length() + " " + element + " trouvé");
        }

        for (Iterator it = tab.iterator(); it.hasNext();) {
            JSONObject next = (JSONObject) it.next();
            list.add(next.get("Name"));

        }
        System.out.println("list: " + list.toString());

        return list;
    }

    /**
     * return la valeur d'une study et suivant leur nom (unique pour les
     * strings)
     *
     * @param studyName le nom de l'etude
     * @param cible le nom de l'attribut cible
     * @return la value
     */
    public String getValueCibleOfStudy(String studyName, String cible) {
        String str = "vide";

        JSONArray tab = null;

        if (jo != null) {
            tab = jo.getJSONArray("studies");

        }

        for (Iterator it = tab.iterator(); it.hasNext();) {
            JSONObject next = (JSONObject) it.next();

            if (next.get("Name").equals(studyName)) {
                if (next.has(cible) && next.get(cible).getClass().getSimpleName().equals("String")) {
                    str = (String) next.get(cible);
                } else {
                    System.out.println("la cible n'est pas presente ou n'est pas de classe String ");
                }
            }

        }

        
        return str;
    }

    /**
     * return la valeur d'une study et suivant leur nom (unique pour les
     * strings)
     *
     * @param studyName le nom de l'etude
     * @param cible le nom de l'attribut cible
     * @return la value
     */
    public ArrayList getListcibleOfStudy(String studyName, String cible) {
        ArrayList list = new ArrayList<>();
        JSONArray tabCible = null;
        JSONArray tab = null;

        if (jo != null) {
            tab = jo.getJSONArray("studies");
        }

        for (Iterator it = tab.iterator(); it.hasNext();) {
            JSONObject next = (JSONObject) it.next();

            if (next.get("Name").equals(studyName)) {

                if (next.has(cible) && next.get(cible).getClass().getSimpleName().equals("JSONArray")) {

                    tabCible = next.getJSONArray(cible);
                } else {
                    System.out.println("la cible n'est pas presente ou n'est pas de classe JSONArray  ");
                }
            }

        }
        if (tabCible != null) {
            for (Object ob : tabCible) {
                list.add(ob);
            }
        }

        return list;
    }

    /**
     * lit un fichier Json et le transforme en jsonObject
     *
     * @param path le chemin du fichier json a parser
     * @return un jsonObject
     */
    private JSONObject readJsonFromFile(String path) {
        String json = new String();

        File file = new File(path);

        try {
            input = new FileInputStream(file);

        } catch (FileNotFoundException ex) {
            System.out.println("jsonworker inputstream: " + ex.getMessage());
            Logger
                    .getLogger(JsonWorker.class
                            .getName()).log(Level.SEVERE, null, ex);
        }

        // Récupération de la chaîne JSON depuis le fichier
        scanner = new Scanner(input);
        while (scanner.hasNext()) {
            json += scanner.nextLine();
        }
        scanner.close();
        //json = json.replaceAll("[\t ]", "");

        //fermeture du fichier
        try {
            input.close();
        } catch (IOException ex) {
            System.out.println("fermeture du json: " + ex.getMessage());
            Logger
                    .getLogger(JsonWorker.class
                            .getName()).log(Level.SEVERE, null, ex);
        }

        jo = new JSONObject(json);

        return jo;
    }

    /**
     * recupere une entrée booleenne du nom de la cible dans la study de
     * studyName
     *
     * @param studyName
     * @param cible
     * @return
     */
    public Boolean getBooleanCibleOfStudy(String studyName, String cible) {
        Boolean bob = false;

        JSONArray tab = null;

        if (jo != null) {
            tab = jo.getJSONArray("studies");

        }

        for (Iterator it = tab.iterator(); it.hasNext();) {
            JSONObject next = (JSONObject) it.next();

            if (next.get("Name").equals(studyName)) {
                if (next.has(cible) && next.get(cible).getClass().getSimpleName().equals("Boolean")) {

                    bob = (Boolean) next.get(cible);
                } else {
                    System.out.println("la cible n'est pas presente ou n'est pas de classe JSONArray ");
                }
            }

        }

        return bob;
    }

    /**
     * creer une entrée dans le fichier de json avec le nom de l'etude et rien
     * d'autre
     *
     * @param studyName
     * @return
     */
    public boolean setNewStudyName(String studyName) {

        boolean bob = false;

        if (Check.isGood(studyName)) {

            try {
                //obj.put("studies",  "{"+studyName+"}");
                jo.append("studies", new JSONObject().put("Name", studyName));
                bob = true;
            } catch (JSONException ex) {
                bob = false;
                System.out.println("error to put study name in Json " + ex.getLocalizedMessage());
            }

            bob = writeOnJson();
        }
        return bob;
    }

    /**
     * va remplir l'objet etude visée avec les parametres données
     *
     * @param studyName
     * @param trad
     * @param tabModel
     * @param fontSamsung
     * @param listStudyPath
     * @param pathLabels
     * @param pathScreens
     * @param pathCertifs
     * @param map
     */
    public boolean fillStudy(String studyName, String trad, String tabModel, boolean fontSamsung,
            ArrayList<String> listStudyPath, String pathSvnDoc, String pathSvnDel, HashMap<String, String> map) {

        boolean bob = false;
        JSONArray tab = null;

        if (jo != null) {
            tab = jo.getJSONArray("studies");

        }

        for (Iterator it = tab.iterator(); it.hasNext();) {
            JSONObject next = (JSONObject) it.next();

            if (next.get("Name").equals(studyName)) {
/*
                next.put("Trad", trad);
                next.put("Tablet", tabModel);
                next.put("Font", fontSamsung);
                next.put("path", listStudyPath);
                next.put("pathLabels", pathLabels);
                next.put("pathScreens", pathScreens);
                next.put("pathCertifs", pathCertifs);
                next.put("map", map);
*/
                next.put("Trad", trad);
                next.put("Tablet", tabModel);
                next.put("Font", fontSamsung);
                next.put("path", listStudyPath);
                next.put("pathSvnDel", pathSvnDel);
                next.put("pathSvnDoc", pathSvnDoc);
                next.put("map", map);

                bob = true;
            }
            bob = writeOnJson();
        }
        return bob;
    }

    public boolean setListInStudy(String studyName, ArrayList listPath) {

        JSONArray tab = null;

        if (jo != null) {
            tab = jo.getJSONArray("studies");

        }

        for (Iterator it = tab.iterator(); it.hasNext();) {
            JSONObject next = (JSONObject) it.next();

            if (next.get("Name").equals(studyName)) {

                next.put("path", listPath);
            }
        }

        return writeOnJson();

    }

    /**
     * ecrit tout l'objet dans le json
     *
     * @return
     */
    private boolean writeOnJson() {
        boolean bob = true;
        try {
            fw = new FileWriter(fileConfig);
        } catch (IOException ex) {
            bob = false;
            System.out.println("creat fileWriter " + ex.getLocalizedMessage());
        }
        try {
            jo.write(fw);
            fw.flush();
        } catch (IOException ex) {
            bob = false;
            System.out.println("svg de la file " + ex.getLocalizedMessage());
        }
        try {
            fw.close();
        } catch (Exception ex) {
            bob = false;
            System.out.println("close " + ex.getLocalizedMessage());
        }

        System.out.println("Ecriture du Json reussi");
        return bob;
    }

}
