package com.linuxmaker.workingdays;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by @author Andreas Günther, IT-LINUXMAKER on 15.05.16.
 */
public class Eastern {
    private String eastern;
    /**
     * @param year, index
     * The parameter year is given as an integer and must be greater than 1593.
     * The parameter index is an integer for Good Friday (1), Easter Monday (2),
     * Ascension (3), Whit Monday (4) and Corpus Christi (5).
     * Return value is a string with the date of the selected holiday.     *
     */
    public String calculate(int year, int index) {
        int y = year;
        int g = y % 19;
        int c = y / 100;
        int h = (c - c / 4 - (8 * c + 13) / 25 + 19 * g + 15) % 30;
        int i = h - (h / 28) * (1 - (29 / (h + 1)) * ((21 - g) / 11));
        int j = (y +y / 4 + i + 2 - c + c / 4) % 7;
        int l = i - j;
        int monat = 3 + (l + 40) / 44;
        int day = l + 28 - 31 * (monat / 4);
        int difference = 0;
        switch (index) {
            case 1:
                difference = -2;
                break;
            case 2:
                difference = 1;
                break;
            case 3:
                difference = 39;
                break;
            case 4:
                difference = 50;
                break;
            case 5:
                difference = 60;
                break;
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar easternSunday = new GregorianCalendar();
        easternSunday.set(y, monat-1, day);
        easternSunday.add(Calendar.DAY_OF_MONTH, difference);
        eastern = dateFormat.format(easternSunday.getTime());
        return eastern;
    }
}
