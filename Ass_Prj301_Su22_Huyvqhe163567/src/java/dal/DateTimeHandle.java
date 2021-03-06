/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.Week;

/**
 *
 * @author win
 */
public class DateTimeHandle {

    public ArrayList<Integer> getYear() {
        int year = LocalDate.now().getYear();
        ArrayList<Integer> years = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            years.add(year + i);
        }
        return years;

    }

    public ArrayList<Week> getWeeksOfYear() {
        ArrayList<Week> weeks = new ArrayList<>();
        LocalDate startDate = LocalDate.parse("03-01-2022", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        for (int i = 0; i < 365; i += 7) {
            LocalDate endDate = startDate.plusDays(6);
            Week week = new Week();
            week.setStartDate(startDate);
            week.setEndDate(endDate);
            weeks.add(week);
            startDate = endDate.plusDays(1);
        }
        return weeks;
    }

    public Week getWeekByDate(ArrayList<Week> weeks, LocalDate date) {
        Week currentWeek = new Week();
        for (Week w : weeks) {
            for (int i = 0; i < 7; i++) {
                if (w.getStartDate().plusDays(i).equals(date)) {
                    currentWeek = w;
                    break;
                }
            }
        }
        return currentWeek;
    }
}
