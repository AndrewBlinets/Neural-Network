package com.company.andrewblinets.version_1.userinterfase;

import com.company.andrewblinets.version_0.filework.ImageClass;
import com.company.andrewblinets.version_0.utils.KeyboardWork;
import com.company.andrewblinets.version_1.template.CreateTemplate;

public class UserInterfase {

    private static final String MENU = "\tMenu\n0 - Study by JSON File\n" +
            "1 - Study by Image\n" +
            "2 - Make\n" +
            "3 - EXIT\n" +
            "4 - коэф по яркости\n5 - коэф по summa\n6 - generate template\n" +
            "7 - generete JSON file by pixcel\n";
    private static final String ENTER_URL = "Enter URL root\n";
    private static final String ENTER_VALUE_PIXCEL = "Enter value pixcel(0 - 255)  ";
    private static final String ENTER_ROOT_TEMPLATE = "Enter URL template\n";

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

    public void main() {
        int exit = 0;
        do {
            switch (keyboardWork.readInt(MENU)) {
                case 1: {
                    generateJsonTemplate();
                    break;
                }
            }
        }
        while (exit == 0);
    }

    private void generateJsonTemplate() {
        new CreateTemplate().createTemplateJsonformat(keyboardWork.readInt("Enter kol template"));
    }

}
