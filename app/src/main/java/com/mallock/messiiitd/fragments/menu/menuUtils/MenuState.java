package com.mallock.messiiitd.fragments.menu.menuUtils;

import com.mallock.messiiitd.models.Menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mallock on 11/28/2016.
 */

public class MenuState implements Serializable {

    public MenuState(String date) {
        this.date = date;
        items = new ArrayList<>();
    }

    String date;

    ArrayList<Item> items;

    public void addItem(Item item) {
        items.add(item);
    }

    public static class Item implements Serializable {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isUp() {
            return up;
        }

        public void setUp(boolean up) {
            this.up = up;
        }

        public boolean isDown() {
            return down;
        }

        public void setDown(boolean down) {
            this.down = down;
        }

        public Item(String name) {
            this.name = name;
            up = false;
            down = false;

        }

        String name;
        boolean up;
        boolean down;
    }

    public List<Item> getItems() {
        return items;
    }
}
