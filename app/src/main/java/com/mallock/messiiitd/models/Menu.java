package com.mallock.messiiitd.models;

import java.util.ArrayList;

/**
 * Created by Guroosh Chaudhary on 17-11-2016.
 */

public class Menu {
    int index;
    ArrayList<Item> breakfast = new ArrayList<>();
    ArrayList<Item> lunch = new ArrayList<>();
    ArrayList<Item> snacks = new ArrayList<>();
    ArrayList<Item> dinner = new ArrayList<>();
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ArrayList<Item> getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(ArrayList<Item> breakfast) {
        this.breakfast = breakfast;
    }

    public ArrayList<Item> getLunch() {
        return lunch;
    }

    public void setLunch(ArrayList<Item> lunch) {
        this.lunch = lunch;
    }

    public ArrayList<Item> getSnacks() {
        return snacks;
    }

    public void setSnacks(ArrayList<Item> snacks) {
        this.snacks = snacks;
    }

    public ArrayList<Item> getDinner() {
        return dinner;
    }

    public void setDinner(ArrayList<Item> dinner) {
        this.dinner = dinner;
    }


    public static class Item {
        String name;
        int up;
        int down;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getUpVote() {
            return up;
        }

        public void setUpVote(int upVote) {
            this.up = upVote;
        }

        public int getDownVote() {
            return down;
        }

        public void setDownVote(int downVote) {
            this.down = downVote;
        }
    }

}
