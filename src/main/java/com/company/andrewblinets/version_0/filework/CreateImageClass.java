package com.company.andrewblinets.version_0.filework;

import com.company.andrewblinets.version_0.entity.ImageClassByPixcel;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 30.10.2017.
 */
public class CreateImageClass {
    private String urlTemplate;
    List<List<Integer>> myImage;

    public CreateImageClass() {
        myImage = new ArrayList<>();
        for (int i = 0; i < 30; i++)
        {
            myImage.add(new ArrayList<>());
            for (int j = 0; j < 19; j++)
            {
                myImage.get(i).add(0);
            }
        }
    }

    public void generateImage(int i, int kof) throws IOException {
        ImageClassByPixcel imageClassByPixcel = new JSONClasss("").readJsonFile("templateImage/" + i + ".json");
        int colPixcelInImage = imageClassByPixcel.getListX().size()/kof;
        for (int j = 0; j < 1000; j++)
        {
            int count = rnd((int)colPixcelInImage/2, colPixcelInImage);
            do {
                int numberPosition = rnd(0,colPixcelInImage - 1);
                int positionX = imageClassByPixcel.getListX().get(numberPosition);
                int positionY = imageClassByPixcel.getListY().get(numberPosition);
                //System.out.println(numberPosition);
                //if(myImage.get(positionY).get(positionX) == 0) {
                    myImage.get(positionY).set(positionX, 1);
            }
            while (count != 0);
            new JSONClasss("templateImageInJSONType/" + i + "/" + j + ".json").writeJsonFile(new Gson().toJson(myImage));
            //ImageIO.write(image, "jpg", new File("createImage/" + i  + "/" + j + ".jpg"));
        }
    }

    private int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
