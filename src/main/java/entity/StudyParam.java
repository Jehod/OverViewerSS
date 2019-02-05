/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author nik
 */
public interface StudyParam extends Serializable
{
   
   String getPath();
   String getTrad();
   HashMap<String, String> getMap();
   String getFdefOfExcel(String excel);
   
    
}
