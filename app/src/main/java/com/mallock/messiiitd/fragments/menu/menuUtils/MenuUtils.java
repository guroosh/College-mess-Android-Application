package com.mallock.messiiitd.fragments.menu.menuUtils;

import android.content.Context;
import android.view.MenuItem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Mallock on 11/28/2016.
 */

public class MenuUtils {
    static MenuState state;

    public static boolean isUpvoted(String itemName, Context context) {
        readFile(context);
        for (MenuState.Item item : state.getItems()) {
            if (item.getName().equals(itemName)) {
                return item.isUp();
            }
        }
        return false;
    }

    public static boolean isDownvoted(String itemName, Context context) {
        readFile(context);
        for (MenuState.Item item : state.getItems()) {
            if (item.getName().equals(itemName)) {
                return item.isDown();
            }
        }
        return false;
    }

    public static boolean toggleUpvoted(String itemName, Context context) {
        readFile(context);
        for (MenuState.Item item : state.getItems()) {
            if (item.getName().equals(itemName)) {
                item.setUp(!item.isUp());
                writeFile(context);
                return item.isUp();
            }
        }
        MenuState.Item item = new MenuState.Item(itemName);
        item.setUp(true);
        state.addItem(item);
        writeFile(context);
        return item.isUp();
    }

    public static boolean toggleDownvoted(String itemName, Context context) {
        readFile(context);
        for (MenuState.Item item : state.getItems()) {
            if (item.getName().equals(itemName)) {
                item.setDown(!item.isDown());
                writeFile(context);
                return item.isDown();
            }
        }
        MenuState.Item item = new MenuState.Item(itemName);
        item.setDown(true);
        state.addItem(item);
        writeFile(context);
        return item.isDown();
    }

    private static void readFile(Context context) {
        try {
            FileInputStream fis = context.openFileInput("data");
            ObjectInputStream ois = new ObjectInputStream(fis);
            state = (MenuState) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (state == null) {
            refresh(context);
        }
    }

    private static void writeFile(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput("data", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(state);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void refresh(Context context) {
        String date = getDate(context);
        state = new MenuState(date);
        writeFile(context);
    }

    private static String getDate(Context context) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        return df.format(c.getTime());

    }
}
