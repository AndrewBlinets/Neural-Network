package com.company.andrewblinets.userinterfase;

import com.company.andrewblinets.filework.ImageClass;
import com.company.andrewblinets.utils.KeyboardWork;

import java.io.File;
import java.io.IOException;

/**
 * Created by Андрей on 18.10.2017.
 */
public class UserInterfase {

    private static final String MENU = "\tMenu\n1 - Study\n2 - Make\n3 - EXIT\n4 - коэф по яркости\n";
    private static final String ENTER_URL = "Enter URL root\n";
    private static final String ENTER_VALUE_PIXCEL = "Enter value pixcel(0 - 255)";

    private static UserInterfase userInterfase = new UserInterfase();
    private ImageClass imageClass;
    private static KeyboardWork keyboardWork;

    public UserInterfase() {
        this.imageClass = new ImageClass();
        try {
            keyboardWork = new KeyboardWork();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public static void main() {
        int exit = 0;
        do {
                switch (keyboardWork.readInt(MENU)) {
                    case 1: {
                        userInterfase.study();
                        break;
                    }
                    case 2: {
                        userInterfase.make();
                        break;
                    }
                    case 3: {
                        exit = 1;
                        break;
                    }
                    case 4:
                    {
                        userInterfase.transport();
                    }
                }
        }
        while (exit == 0);
    }

    private void transport() {
        imageClass.setImageURL(keyboardWork.readString(ENTER_URL));
        try {
            imageClass.getNewImageWithNewPixsel(keyboardWork.readInt(ENTER_VALUE_PIXCEL));
            System.out.println("CREATE ALL IMAGE");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void make() {

    }

    private void study() {

    }
}
