package com.company.andrewblinets.version_1.entity;

import java.util.ArrayList;
import java.util.List;


public class ImageClassByPixcel {

    private List<Integer> listX;
    private List<Integer> listY;

    public ImageClassByPixcel() {
        listX = new ArrayList<>();
        listY = new ArrayList<>();
    }

    public ImageClassByPixcel(List<Integer> listX, List<Integer> listY) {
        this.listX = listX;
        this.listY = listY;
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
