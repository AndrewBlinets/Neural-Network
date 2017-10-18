package com.company.andrewblinets.filework;

import com.company.andrewblinets.exception.ValuePixcelExseption;

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
    private Color[][] result;
    private BufferedImage image;
    private List<List> libra;

    public ImageClass() {
        libra = new ArrayList<>();
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void getNewImageWithNewPixsel(int value) throws Exception {
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


}
