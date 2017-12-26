package com.company.andrewblinets.version_0.entity;

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
