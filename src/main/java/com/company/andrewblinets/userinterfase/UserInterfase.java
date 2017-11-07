package com.company.andrewblinets.userinterfase;

import com.company.andrewblinets.entity.ImageClassByPixcel;
import com.company.andrewblinets.exception.ImageExeption;
import com.company.andrewblinets.exception.ResourseException;
import com.company.andrewblinets.filework.CreateImageClass;
import com.company.andrewblinets.filework.ImageClass;
import com.company.andrewblinets.filework.JSONClasss;
import com.company.andrewblinets.utils.KeyboardWork;
import com.google.gson.Gson;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 18.10.2017.
 */
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

    public static void main() {
        int exit = 0;
        do {
                switch (keyboardWork.readInt(MENU)) {
                    case 0:{
                        for(int i = 0; i < 1000; i++) {
                            System.out.println("Generete and stuty   " + i);
                            userInterfase.generate();
                            userInterfase.studyByJSON();
                        }
                        break;
                    }
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
                        userInterfase.transportBy1Color();
                        break;
                    }
                    case 5:
                    {
                        userInterfase.transportBy3Color();
                        break;
                    }
                    case 6:
                    {
                        try {
                            userInterfase.generateImage();
                        } catch (ResourseException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 7:
                    {
                        userInterfase.generate();
                    }
                }
        }
        while (exit == 0);
    }

    private void studyByJSON() {
        try {
            new ImageClass().stydyByJson();
        } catch (ResourseException e) {
            e.printStackTrace();
        } catch (ImageExeption imageExeption) {
            imageExeption.printStackTrace();
        }
    }

    private void generate() {
        for (int i = 0; i < 10; i ++) {
            CreateImageClass createImageClass = new CreateImageClass();
            try {
                createImageClass.generateImage(i,1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void generateImage() throws ResourseException, IOException {
        //ImageClassByPixcel imageClassByPixcel = new ImageClassByPixcel();
        String url = keyboardWork.readString("Url template\n");
        File[] list = new File(url).listFiles();
        if (list.length == 0) {
            throw new ResourseException("not found packeg");
        }
        for (File aList : list) {
            BufferedImage image = ImageIO.read(new File(aList.getPath()));
            ImageClassByPixcel imageClassByPixcel = new ImageClassByPixcel();
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    if ((new Color(image.getRGB(j, i)).getRed() * 0.299 +
                            new Color(image.getRGB(j, i)).getGreen() * 0.587 +
                            new Color(image.getRGB(j, i)).getBlue() * 0.114) < 50)
                    {
                        imageClassByPixcel.getListX().add(j);
                        imageClassByPixcel.getListY().add(i);
                    }
                }
            }
            System.out.println(imageClassByPixcel.getListX().size());
            new JSONClasss("templateImage/" + aList.getName().substring(0,1) + ".json").writeJsonFile(
                    new Gson().toJson(imageClassByPixcel));
        }
    }

    private void transportBy3Color() {
        imageClass.setImageURL(keyboardWork.readString(ENTER_URL));
        try {
            imageClass.getNewImageWithNewPixselWith3Color(keyboardWork.readInt(ENTER_VALUE_PIXCEL));
            System.out.println("CREATE ALL IMAGE");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void transportBy1Color() {
        imageClass.setImageURL(keyboardWork.readString(ENTER_URL));
        try {
            imageClass.getNewImageWithNewPixselByRedColor(keyboardWork.readInt(ENTER_VALUE_PIXCEL));
            System.out.println("CREATE ALL IMAGE");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void make() {
        String urlImage = keyboardWork.readString("Enter URL folder\n");
        ImageClass imageClass = new ImageClass();
        imageClass.setImageURL(urlImage);
        try {
            System.out.println(imageClass.getTextFromImage(75));
        }
        catch (NullPointerException e)
        {}
        catch (IOException e) {
           // e.printStackTrace();
        }
    }

    private void study() {
        try {
            new ImageClass().chekingImage();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ResourseException e) {
            e.printStackTrace();
        } catch (ImageExeption imageExeption) {
            imageExeption.printStackTrace();
        }
    }
}
