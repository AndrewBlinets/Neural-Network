package com.company.andrewblinets.filework;

import com.company.andrewblinets.exception.ImageExeption;
import com.company.andrewblinets.exception.ResourseException;
import com.company.andrewblinets.exception.ValuePixcelExseption;
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
public class ImageClass {
    private String imageURL;
    private List<List<Integer>> libra0;
    private List<List<Integer>> libra1;
    private List<List<Integer>> libra2;
    private List<List<Integer>> libra3;
    private List<List<Integer>> libra4;
    private List<List<Integer>> libra5;
    private List<List<Integer>> libra6;
    private List<List<Integer>> libra7;
    private List<List<Integer>> libra8;
    private List<List<Integer>> libra9;
    private List<List<Integer>> imageMatrix;
    private Color[][] result;
    private BufferedImage image;
    private int globalCountError;

    public ImageClass() {
        globalCountError = 0;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void getNewImageWithNewPixselByRedColor(int value) throws Exception {
        if(value > 256 || value <= 0)
        {
            throw new ValuePixcelExseption("Неверное значени (value must -1 < value < 256)");
        }
        File[] list = new File(imageURL).listFiles();
        imageURL += "(1)";
        new File(imageURL).mkdir();
        for (File aList : list) {
            image = ImageIO.read(new File(aList.getPath()));
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    if (new Color(image.getRGB(j, i)).getRed() > value) {
                        Color myWhite = new Color(255, 255, 255);
                        int rgb = myWhite.getRGB();
                        image.setRGB(j, i, rgb);
                    }
                }
            }
            ImageIO.write(image, "jpg", new File(imageURL  + "/" + aList.getName()));
        }
    }

    public void getNewImageWithNewPixselWith3Color(int value) throws Exception {
        if(value > 256 || value <= 0)
        {
            throw new ValuePixcelExseption("Неверное значени (value must -1 < value < 256)");
        }
        File[] list = new File(imageURL).listFiles();
        imageURL += "(2)";
        new File(imageURL).mkdir();
        for (File aList : list) {
            image = ImageIO.read(new File(aList.getPath()));
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    if ((new Color(image.getRGB(j, i)).getRed() * 0.299 +
                            new Color(image.getRGB(j, i)).getGreen() * 0.587 +
                            new Color(image.getRGB(j, i)).getBlue() * 0.114) > value) {
                        Color myWhite = new Color(255, 255, 255);
                        int rgb = myWhite.getRGB();
                        image.setRGB(j, i, rgb);
                    }
                    else
                    {
                        Color myBlack = new Color(0, 0, 0);
                        int rgb = myBlack.getRGB();
                        image.setRGB(j, i, rgb);
                    }
                }
            }
            ImageIO.write(image, "jpg", new File(imageURL  + "/" + aList.getName()));
        }
    }

    private void getMatrixImage() throws IOException {
        imageMatrix = new ArrayList<>();

        image = ImageIO.read(new File(imageURL));
        for (int i = 0; i < image.getHeight(); i++)
        {
            imageMatrix.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                if ((new Color(image.getRGB(j, i)).getRed() * 0.299 +
                        new Color(image.getRGB(j, i)).getGreen() * 0.587 +
                        new Color(image.getRGB(j, i)).getBlue() * 0.114) > 240)
                {
                    imageMatrix.get(i).add(1);
                }
                else
                    imageMatrix.get(i).add(0);
            }
        }

    }

    public void chekingImage() throws IOException, ResourseException, ImageExeption {
        readLibra();
        File[] list = new File("template").listFiles();
        if (list.length == 0)
        {
            throw new ResourseException("not found packeg");
        }
        do {
            globalCountError = 0;
            for (int i = 0; i < 10; i++) {
                File[] listImage = new File(list[i].getPath()).listFiles();
                if (listImage.length == 0)
                {
                    writeLibra();
                    throw new ImageExeption("not found image by - " + i);
                }
                for (File bList : listImage)
                {
                    imageURL = bList.getPath();
                    getMatrixImage();
                    makeWithLibra0(i);
                    makeWithLibra1(i);
                    makeWithLibra2(i);
                    makeWithLibra3(i);
                    makeWithLibra4(i);
                    makeWithLibra5(i);
                    makeWithLibra6(i);
                    makeWithLibra7(i);
                    makeWithLibra8(i);
                    makeWithLibra9(i);
                    removeMatrixImage();
                }
            }
            System.out.println("kol globalError " + globalCountError);
        }
        while (globalCountError != 0);
        writeLibra();
    }

    private void removeMatrixImage() {
        for (int i = imageMatrix.size() - 1; i > -1 ; i--)
        {
            for (int j = imageMatrix.get(0).size() - 1; j > -1; j--)
            {
                imageMatrix.get(i).remove(j);
            }
            imageMatrix.remove(i);
        }
    }

    private void makeWithLibra1(int i) {
        if(i == 1)
            doMake(true,libra1);
        else
            doMake(false,libra1);
    }

    private void doMake(boolean flag, List<List<Integer>> libra)
    {
        int count = 0;
        do {
            int result = compositionLibraAndImage(libra);
            if (result > 30000 && !flag) {
                difference(libra);
                count++;
                globalCountError++;
            }
            if (result < 30000 && flag) {
                {
                    summa(libra);
                    count++;
                    globalCountError++;
                }
            }
            if(count == 0)
                break;
            else
                count = 0;
        }
        while (true);
    }

    private void makeWithLibra0(int i) {
        if(i == 0)
            doMake(true,libra0);
        else
            doMake(false,libra0);
    }

    private void makeWithLibra2(int i) {
        if(i == 2)
            doMake(true,libra2);
        else
            doMake(false,libra2);
    }

    private void makeWithLibra3(int i) {
        if(i == 3)
            doMake(true,libra3);
        else
            doMake(false,libra3);
    }

    private void makeWithLibra4(int i) {
        if(i == 4)
            doMake(true,libra4);
        else
            doMake(false,libra4);
    }

    private void makeWithLibra5(int i) {
        if(i == 5)
            doMake(true,libra5);
        else
            doMake(false,libra5);
    }

    private void makeWithLibra6(int i) {
        if(i == 6)
            doMake(true,libra6);
        else
            doMake(false,libra6);
    }

    private void makeWithLibra7(int i) {
        if(i == 7)
            doMake(true,libra7);
        else
            doMake(false,libra7);
    }

    private void makeWithLibra8(int i) {
        if(i == 8)
            doMake(true,libra8);
        else
            doMake(false,libra8);
    }

    private void makeWithLibra9(int i) {
        if(i == 9)
            doMake(true,libra9);
        else
            doMake(false,libra9);
    }

    private void writeLibra() {
        Gson gson = new Gson();
         new JSONClasss("Libra/0.json").writeJsonFile(gson.toJson(libra0));
         new JSONClasss("Libra/1.json").writeJsonFile(gson.toJson(libra1));
         new JSONClasss("Libra/2.json").writeJsonFile(gson.toJson(libra2));
         new JSONClasss("Libra/3.json").writeJsonFile(gson.toJson(libra3));
         new JSONClasss("Libra/4.json").writeJsonFile(gson.toJson(libra4));
         new JSONClasss("Libra/5.json").writeJsonFile(gson.toJson(libra5));
         new JSONClasss("Libra/6.json").writeJsonFile(gson.toJson(libra6));
         new JSONClasss("Libra/7.json").writeJsonFile(gson.toJson(libra7));
         new JSONClasss("Libra/8.json").writeJsonFile(gson.toJson(libra8));
         new JSONClasss("Libra/9.json").writeJsonFile(gson.toJson(libra9));
    }

    private void summa(List<List<Integer>> libra) {
        for (int i = 0; i < imageMatrix.size(); i++)
        {
            try{
                for (int j = 0; j < imageMatrix.get(0).size(); j++)
                {
                    try
                    {
                        libra.get(i).set(j,libra.get(i).get(j) + imageMatrix.get(i).get(j));
                    }
                    catch (ArrayIndexOutOfBoundsException e)
                    {
                        libra.get(i).add(0);
                        libra.get(i).set(j,libra.get(i).get(j) + imageMatrix.get(i).get(j));
                    }
                }
            }catch (Exception e)
            {
                System.out.println(i);
                System.out.println(imageURL);
                e.printStackTrace();
            }
        }
     //   return libra;
    }

    private void difference(List<List<Integer>> libra) {
        for (int i = 0; i < imageMatrix.size(); i++)
        {
            for (int j = 0; j < imageMatrix.get(0).size(); j++)
            {
                try
                {
                    libra.get(i).set(j,libra.get(i).get(j) - imageMatrix.get(i).get(j));
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    libra.get(i).add(0);
                    libra.get(i).set(j,libra.get(i).get(j) - imageMatrix.get(i).get(j));
                }
            }
        }
       // return libra;
    }

    private int compositionLibraAndImage(List<List<Integer>> libra) {
        int res = 0;
        for (int i = 0; i < imageMatrix.size(); i++)
        {
         for (int j = 0; j < imageMatrix.get(0).size(); j++)
         {
             try
             {
                 res += libra.get(i).get(j) * imageMatrix.get(i).get(j);
             }
             catch (IndexOutOfBoundsException e)
             {
                 libra.get(i).add(0);
             }
         }
        }
        return res;
    }

    private void readLibra()
    {
        libra0 = new JSONClasss("Libra/0.json").readJsonFile();
        libra1 = new JSONClasss("Libra/1.json").readJsonFile();
        libra2 = new JSONClasss("Libra/2.json").readJsonFile();
        libra3 = new JSONClasss("Libra/3.json").readJsonFile();
        libra4 = new JSONClasss("Libra/4.json").readJsonFile();
        libra5 = new JSONClasss("Libra/5.json").readJsonFile();
        libra6 = new JSONClasss("Libra/6.json").readJsonFile();
        libra7 = new JSONClasss("Libra/7.json").readJsonFile();
        libra8 = new JSONClasss("Libra/8.json").readJsonFile();
        libra9 = new JSONClasss("Libra/9.json").readJsonFile();

    }
}
