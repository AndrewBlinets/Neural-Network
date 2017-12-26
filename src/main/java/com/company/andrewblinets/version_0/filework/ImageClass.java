package com.company.andrewblinets.version_0.filework;

import com.company.andrewblinets.version_0.exception.ImageExeption;
import com.company.andrewblinets.version_0.exception.ResourseException;
import com.company.andrewblinets.version_0.exception.ValuePixcelExseption;
import com.google.gson.Gson;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
   // private Color[][] result;
    private BufferedImage image;
    private int globalCountError;
    private int valueCompositionLibraOnImage = 60000;

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
            if (result > valueCompositionLibraOnImage && !flag) {
                difference(libra);
                count++;
                globalCountError++;
            }
            if (result < valueCompositionLibraOnImage && flag) {
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
                    catch (IndexOutOfBoundsException e1)
                    {
                        libra.get(i).add(0);
                        libra.get(i).set(j,libra.get(i).get(j) + imageMatrix.get(i).get(j));
                    }
                }
            }catch (Exception e)
            {
                //System.out.println(i);
                //System.out.println(imageURL);
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
                catch (IndexOutOfBoundsException e1)
                {
                    libra.get(i).add(0);
                    libra.get(i).set(j,libra.get(i).get(j) + imageMatrix.get(i).get(j));
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
              //   System.out.println(imageURL);
                try{
                    libra.get(i).add(0);
                }
                catch (IndexOutOfBoundsException e1)
                {
                    libra.add(new ArrayList<>());
                  //  System.out.println();
                }
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

    public String getTextFromImage(int value, String url) throws IOException {
        List<List<Integer>> imageMatrixThisMetod = new ArrayList<>();
        String textFromImage = "";
            readLibra();
            image = ImageIO.read(new File(url));
            if (image.getHeight() == 30)
            {
                for (int i = 0; i < image.getHeight(); i++) {
                    imageMatrixThisMetod.add(new ArrayList<>());
                    for (int j = 0; j < image.getWidth(); j++) {
                        if (new Color(image.getRGB(j, i)).getRed() > value) {
                            imageMatrixThisMetod.get(i).add(1);
                        } else {
                            imageMatrixThisMetod.get(i).add(0);
                        }
                    }
                }
                int heightImage = image.getHeight();
                int widthImage = image.getWidth();
                int summa = 0;
                /*for (int i = heightImage - 1; i > -1; i--)
                {
                    for (int j = widthImage - 1; j > -1; j--)
                    {
                        summa += imageMatrixThisMetod.get(i).get(j);
                    }
                    if (summa < 10)
                        imageMatrixThisMetod.remove(i);
                    summa = 0;
                }*/
//                for(int i = 0; i < imageMatrixThisMetod.size(); i++)
//                {
//                    for (int j = 0; j < imageMatrixThisMetod.get(i).size(); j++)
//                    {
//                        System.out.print(imageMatrixThisMetod.get(i).get(j));
//                    }
//                    System.out.println();
//                }

                int sizeTextFromImage = textFromImage.length();
                int xStartPosSearchText = 0;
                int xEndPosSearchText = 19;
                boolean flag5 = false, flag7 = false;
                do {
                    List<List<Integer>> partOfImage = getPartImage(xStartPosSearchText, xEndPosSearchText, imageMatrixThisMetod);
                    textFromImage += resultCompositionLibraAndPartImage(partOfImage);
                    if (sizeTextFromImage == textFromImage.length()) {
                        xEndPosSearchText++;
                    }

                    if (sizeTextFromImage == textFromImage.length() - 1) {
                        //xStartPosSearchText = xEndPosSearchText;
                        if (textFromImage.length() - 1 == 0) {
                            xStartPosSearchText = 21;
                            xEndPosSearchText = xStartPosSearchText + 19;
                            sizeTextFromImage = textFromImage.length();
                        } else {
                            xStartPosSearchText = textFromImage.length() * 19 + (textFromImage.length() - 1) * 7;
                            xEndPosSearchText = xStartPosSearchText + 19;
                            sizeTextFromImage = textFromImage.length();
                        }
                    }
                    if (textFromImage.length() == 5 && !flag5) {
                        xStartPosSearchText += 26;
                        xEndPosSearchText = xStartPosSearchText + 19;
                        flag5 = true;
                        textFromImage += " ";
                        sizeTextFromImage = textFromImage.length();
                        //xStartPosSearchText++;
                        //xStartPosSearchText++;
                    }
                    if (textFromImage.length() == 8 && !flag7) {
                        xStartPosSearchText += 26;
                        xEndPosSearchText = xStartPosSearchText + 19;
                        flag7 = true;
                        textFromImage += " ";
                        sizeTextFromImage = textFromImage.length();
                    }
                    if(xEndPosSearchText - xStartPosSearchText > 24)
                    {
                        textFromImage += "|";
                        if (textFromImage.length() - 1 == 0) {
                            xStartPosSearchText = 21;
                            xEndPosSearchText = xStartPosSearchText + 19;
                            sizeTextFromImage = textFromImage.length();
                        } else {
                            xStartPosSearchText = textFromImage.length() * 19 + (textFromImage.length() - 1) * 7;
                            xEndPosSearchText = xStartPosSearchText + 19;
                            sizeTextFromImage = textFromImage.length();
                        }
                    }
                    if (sizeTextFromImage <= textFromImage.length() - 2) {
                        textFromImage += " Errors";
                        xEndPosSearchText = imageMatrixThisMetod.get(0).size();
                    }
                }
                while (xEndPosSearchText < imageMatrixThisMetod.get(0).size());
               // System.out.println("result - " + textFromImage);
            }
        //return "qwe";
        return textFromImage;
    }

    private String resultCompositionLibraAndPartImage(List<List<Integer>> partOfImage) {
        Map<Integer,Integer> map = new HashMap<>();
        //System.out.print(0);
        if(compositionLibraAndPartImage(partOfImage,libra0) > valueCompositionLibraOnImage)
            map.put(0,compositionLibraAndPartImage(partOfImage,libra0));//return String.valueOf(0);
       // System.out.print(1);
        if(compositionLibraAndPartImage(partOfImage,libra1) > valueCompositionLibraOnImage)
            map.put(1,compositionLibraAndPartImage(partOfImage,libra1));;//return String.valueOf(1);
      //  System.out.print(2);
        if(compositionLibraAndPartImage(partOfImage,libra2) > valueCompositionLibraOnImage)
            map.put(2,compositionLibraAndPartImage(partOfImage,libra2));;//return String.valueOf(2);
      //  System.out.print(3);
        if(compositionLibraAndPartImage(partOfImage,libra3) > valueCompositionLibraOnImage)
            map.put(3,compositionLibraAndPartImage(partOfImage,libra3));//return String.valueOf(3);
      //  System.out.print(4);
        if(compositionLibraAndPartImage(partOfImage,libra4) > valueCompositionLibraOnImage)
            map.put(4,compositionLibraAndPartImage(partOfImage,libra4));// return String.valueOf(4);
      //  System.out.print(5);
        if(compositionLibraAndPartImage(partOfImage,libra5) > valueCompositionLibraOnImage)
            map.put(5,compositionLibraAndPartImage(partOfImage,libra5));//return String.valueOf(5);
      //  System.out.print(6);
        if(compositionLibraAndPartImage(partOfImage,libra6) > valueCompositionLibraOnImage)
            map.put(6,compositionLibraAndPartImage(partOfImage,libra6));//return String.valueOf(6);
      //  System.out.print(7);
        if(compositionLibraAndPartImage(partOfImage,libra7) > valueCompositionLibraOnImage)
            map.put(7,compositionLibraAndPartImage(partOfImage,libra7));//return String.valueOf(7);
      //  System.out.print(8);
        if(compositionLibraAndPartImage(partOfImage,libra8) > valueCompositionLibraOnImage)
            map.put(8,compositionLibraAndPartImage(partOfImage,libra8));// return String.valueOf(8);
      //  System.out.print(9);
        if(compositionLibraAndPartImage(partOfImage,libra9) > valueCompositionLibraOnImage)
            map.put(9,compositionLibraAndPartImage(partOfImage,libra9));//return String.valueOf(9);
//        else
//            return "";
        if(map.size() == 0)
            return "";
        if(map.size() == 1)
        {
            for (Integer key : map.keySet()) {
                return key + "";
            }
            return "";
        }
        else
        {
            int maxValue = 0;
            int keyReturn = 0;
            for (Integer key : map.keySet()) {
                if(maxValue < map.get(key)) {
                    maxValue = map.get(key);
                    keyReturn = key;
                }
            }
            return keyReturn + "";
        }
    }

    private int compositionLibraAndPartImage(List<List<Integer>> partOfImage, List<List<Integer>> libra) {
        int res = 0;
        for (int i = 0; i < partOfImage.size(); i++)
        {
            for (int j = 0; j < partOfImage.get(0).size(); j++)
            {
                try
                {
                    res += libra.get(i).get(j) * partOfImage.get(i).get(j);
                }
                catch (Exception e)
                {
                  //  e.printStackTrace();
                }

            }
        }
       // System.out.println("result = " + res);
        return res;
    }

    private List<List<Integer>> getPartImage(int xStartPositionText, int xEndPosSearchText, List<List<Integer>> imageMatrixThisMetod) {
        List<List<Integer>> lists = new ArrayList<>();
        try {
            for (int i = 0; i < imageMatrixThisMetod.size(); i++)
            {
                lists.add(new ArrayList<>());
                for (int j = xStartPositionText; j < xEndPosSearchText + 1; j++)
                {
                    //System.out.print(imageMatrixThisMetod.get(i).get(j));
                    lists.get(i).add(imageMatrixThisMetod.get(i).get(j));
                }
               // System.out.print("\n");
            }
        }
        catch (Exception e)
        {
            int a = 0;
            a++;
        }
        return lists;
    }

    public void stydyByJson() throws ResourseException, ImageExeption {
        readLibra();
        File[] list = new File("templateImageInJSONType").listFiles();
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
                   // getMatrixImage();
                    imageMatrix = new JSONClasss(imageURL).readJsonFile();
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
}
