/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Outils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author nrochas
 */
public class DateManager
{

    public String getSimpleCurrentDate()
    {
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String dayDate = dateFormat.format(currentDate);

        return dayDate;

    }

    public String getSimpleDate(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String dayDate = dateFormat.format(date);

        return dayDate;
    }
}
