package com.mallock.messiiitd.fragments.menu;

/**
 * Created by Mallock on 07-10-2016.
 */

public class DailyMenu {
    Meal meals[];

    public Meal getBreakfast() {
        return meals[0];
    }

    public Meal getLunch() {
        return meals[1];
    }

    public Meal getEveningSnacks() {
        return meals[2];
    }

    public Meal getDinner() {
        return meals[3];
    }
}
