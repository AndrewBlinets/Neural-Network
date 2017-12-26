package com.company.andrewblinets.version_1.template;

import com.company.andrewblinets.version_1.entity.ImageClassByPixcel;
import com.company.andrewblinets.version_1.filework.JSONClasss;

import java.util.Random;

/**
 * Created by Blinec_A on 26.12.2017.
 */
public class CreateTemplate {
    private JSONClasss jsonClasss;
    private ImageClassByPixcel imageClassByPixcel;
    private Random random;

    public CreateTemplate() {
        this.jsonClasss = new JSONClasss();
        this.imageClassByPixcel = new ImageClassByPixcel();
        this.random = new Random();
    }

    public JSONClasss getJsonClasss() {
        return jsonClasss;
    }

    public void setJsonClasss(JSONClasss jsonClasss) {
        this.jsonClasss = jsonClasss;
    }

    public void createTemplateJsonformat(int count) {
        for (int i = 0; i < 10; i++)
        {
            this.imageClassByPixcel = new JSONClasss("").readJsonFile("templateImage/" + i + ".json");
            int sizeListElement = this.imageClassByPixcel.getListX().size();
            int countPixelGenerate = (int) (random.nextInt(sizeListElement) + sizeListElement * 0.3);
            for(int j = 0; j < count; j++)
            {


            }
        }
    }
}
