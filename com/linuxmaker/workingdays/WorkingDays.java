package com.linuxmaker.workingdays;

import org.joda.time.Days;
import org.joda.time.LocalDate;

/**
 * Created by @author Andreas Günther, IT-LINUXMAKER on 15.05.16.
 */
public class WorkingDays {
    /**
     * @param startDate, endDate
     */
    public int calculateDays(String startDate, String endDate, Boolean saturdayWork, String state, String capacity) {
        DateDisassembler d = new DateDisassembler();
        LocalDate date1 = new LocalDate(d.disassemble(startDate)[2], d.disassemble(startDate)[1], d.disassemble(startDate)[0]);
        LocalDate date2 = new LocalDate(d.disassemble(endDate)[2], d.disassemble(endDate)[1], d.disassemble(endDate)[0]);
        Eastern easterHolidays = new Eastern();
        int weekend = 0;
        int holidays = 0;
        Integer utilization = Integer.parseInt(capacity.substring(0, capacity.length()-1));
        /*
    	 * Calculates the weekend days and the German holidays
    	 */
        for (LocalDate date = date1; date.isBefore(date2) || date.isEqual(date2); date = date.plusDays(1)) {
            if (date.getDayOfWeek() == 6 || date.getDayOfWeek() == 7) {
                weekend++;
            } else {
                int year = date.getYear();
                // New Year's Day
                if (date.getMonthOfYear() == 1 && date.getDayOfMonth() == 1 && !(date.getDayOfWeek() == 6 || date.getDayOfWeek() == 7)) {
                    holidays++;
                }
                // Epiphany
                if (state.equals("Baden-Württemberg") || state.equals("Bayern") || state.equals("Sachsen-Anhalt")) {
                    if (date.getMonthOfYear() == 1 && date.getDayOfMonth() == 6 && !(date.getDayOfWeek() == 6 || date.getDayOfWeek() == 7)) {
                        holidays++;
                    }
                }

                // Good Friday
                if (date.toString().equals(easterHolidays.calculate(year, 1))) {
                    holidays++;
                }
                // Easter Monday
                if (date.toString().equals(easterHolidays.calculate(year, 2))) {
                    holidays++;
                }
                // Ascension Day
                if (date.toString().equals(easterHolidays.calculate(year, 3))) {
                    holidays++;
                }
                // Whit Monday
                if (date.toString().equals(easterHolidays.calculate(year, 4))) {
                    holidays++;
                }
                // Corpus Christi
                if (state.equals("Baden-Württemberg") || state.equals("Bayern") || state.equals("Hessen") || state.equals("Nordrhein-Westfalen") || state.equals("Rheinland-Pfalz") || state.equals("Saarland")) {
                    if (date.toString().equals(easterHolidays.calculate(year, 5))) {
                        holidays++;
                    }
                }
                // Labor Day
                if (date.getMonthOfYear() == 5 && date.getDayOfMonth() == 1 && !(date.getDayOfWeek() == 6 || date.getDayOfWeek() == 7)) {
                    holidays++;
                }
                // Assumption Day
                if (state.equals("Saarland")) {
                    if (date.getMonthOfYear() == 8 && date.getDayOfMonth() == 15 && !(date.getDayOfWeek() == 6 || date.getDayOfWeek() == 7)) {
                        holidays++;
                    }
                }
                // German Unity Day
                if (date.getMonthOfYear() == 10 && date.getDayOfMonth() == 3 && !(date.getDayOfWeek() == 6 || date.getDayOfWeek() == 7)) {
                    holidays++;
                }
                // Reformation Day
                if (state.equals("Brandenburg") || state.equals("Mecklenburg-Vorpommern") || state.equals("Sachsen") || state.equals("Sachsen-Anhalt") || state.equals("Thüringen")) {
                    if (date.getMonthOfYear() == 10 && date.getDayOfMonth() == 31 && !(date.getDayOfWeek() == 6 || date.getDayOfWeek() == 7)) {
                        holidays++;
                    }
                }
                // All Saints
                if (state.equals("Baden-Württemberg") || state.equals("Bayern") || state.equals("Nordrhein-Westfalen") || state.equals("Rheinland-Pfalz") || state.equals("Saarland")) {
                    if (date.getMonthOfYear() == 11 && date.getDayOfMonth() == 1 && !(date.getDayOfWeek() == 6 || date.getDayOfWeek() == 7)) {
                        holidays++;
                    }
                }
                // Christmas Days
                if (date.getMonthOfYear() == 12 && date.getDayOfMonth() == 25 && !(date.getDayOfWeek() == 6 || date.getDayOfWeek() == 7)) {
                    holidays++;
                }
                if (date.getMonthOfYear() == 12 && date.getDayOfMonth() == 26 && !(date.getDayOfWeek() == 6 || date.getDayOfWeek() == 7)) {
                    holidays++;
                }
            }
        }
        if (saturdayWork) {
            weekend = weekend/2;
        }
        Days days = Days.daysBetween(date1, date2);
        int diffDays = 1+days.getDays();
        return utilization * (diffDays - weekend - holidays) / 100;
    }
}
