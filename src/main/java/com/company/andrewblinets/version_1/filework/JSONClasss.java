package com.company.andrewblinets.version_1.filework;


import com.company.andrewblinets.version_1.entity.ImageClassByPixcel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JSONClasss {

    private String urlJsonFile;

    public JSONClasss(String urlJsonFile) {
        this.urlJsonFile = urlJsonFile;
    }

    public JSONClasss() {
    }

    public String getUrlJsonFile() {
        return urlJsonFile;
    }

    public void setUrlJsonFile(String urlJsonFile) {
        this.urlJsonFile = urlJsonFile;
    }

    public boolean writeJsonFile(String json)
    {
        try (FileWriter file = new FileWriter(urlJsonFile)) {
            file.write(json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<List<Integer>> readJsonFile()
    {
        Type listType = new TypeToken<List<List<Integer>>>() {}.getType();
        try (FileReader fileReader = new FileReader(urlJsonFile))
        {
            return new Gson().fromJson(new JsonReader(fileReader), listType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ImageClassByPixcel readJsonFile(String urlTemplate)
    {
        Type listType = new TypeToken<ImageClassByPixcel>() {}.getType();
        try (FileReader fileReader = new FileReader(urlTemplate))
        {
            return new Gson().fromJson(new JsonReader(fileReader), listType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
