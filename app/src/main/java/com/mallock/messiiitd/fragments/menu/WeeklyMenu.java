package com.mallock.messiiitd.fragments.menu;

/**
 * Created by Mallock on 07-10-2016.
 */

public class WeeklyMenu {
    DailyMenu menu[] = new DailyMenu[7];

    public DailyMenu getMondayMeal() {
        return menu[0];
    }

    public DailyMenu getTuesdayMeal() {
        return menu[1];
    }

    public DailyMenu getWednesdayMeal() {
        return menu[2];
    }

    public DailyMenu getThursdayMeal() {
        return menu[3];
    }

    public DailyMenu getFridayMeal() {
        return menu[4];
    }

    public DailyMenu getSaturdayMeal() {
        return menu[5];
    }

    public DailyMenu getSundayMeal() {
        return menu[6];
    }
}

