/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author nik
 */
public interface Tracker extends Serializable
{
    ArrayList<RowTracker> getAllRowTracker();
    String getName();
    String getDate();
    
}
