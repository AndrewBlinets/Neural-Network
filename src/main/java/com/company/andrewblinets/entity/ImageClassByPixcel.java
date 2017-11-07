package com.company.andrewblinets.entity;

import com.company.andrewblinets.exception.ResourseException;
import com.company.andrewblinets.filework.ImageClass;
import com.company.andrewblinets.filework.JSONClasss;
import com.google.gson.Gson;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 30.10.2017.
 */
public class ImageClassByPixcel {
    private List<Integer> listX;
    private List<Integer> listY;

    public ImageClassByPixcel() {
        listX = new ArrayList<>();
        listY = new ArrayList<>();
    }

    public List<Integer> getListX() {
        return listX;
    }

    public void setListX(List<Integer> listX) {
        this.listX = listX;
    }

    public List<Integer> getListY() {
        return listY;
    }

    public void setListY(List<Integer> listY) {
        this.listY = listY;
    }

}
