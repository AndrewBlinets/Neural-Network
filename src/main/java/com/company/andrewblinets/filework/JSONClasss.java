package com.company.andrewblinets.filework;

import com.company.andrewblinets.entity.ImageClassByPixcel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 18.10.2017.
 */
public class JSONClasss {

    private String urlJsonFile;

    public JSONClasss(String urlJsonFile) {
        this.urlJsonFile = urlJsonFile;
    }

    public boolean writeJsonFile(String json)
    {
        try (FileWriter file = new FileWriter(urlJsonFile)) {
            file.write(json);
           // System.out.println("Successfully Copied JSON Object to File " + urlJsonFile + "...");
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

    public String getUrlJsonFile() {
        return urlJsonFile;
    }

    public void setUrlJsonFile(String urlJsonFile) {
        this.urlJsonFile = urlJsonFile;
    }

    /* public static void main(String[] args)
    {
        Gson gson = new Gson();
        List<List<Integer>> ints = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(0);
        list.add(0);
        ints.add(list);
        ints.add(list);
        ints.add(list);

        String json = gson.toJson(ints);  // ==> json is [1,2,3,4,5]

        try (FileWriter file = new FileWriter("Libra/0.json")) {
            file.write(json);
            System.out.println("Successfully Copied JSON Object to File...");
            //System.out.println("\nJSON Object: " + obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<List<Integer>> yourList;
        Type listType = new TypeToken<List<List<Integer>>>() {}.getType();
        try (FileReader fileReader = new FileReader("Libra/0.json"))
        {
           // JsonReader jsonReader =
           yourList = new Gson().fromJson(new JsonReader(fileReader), listType);
           System.out.println(yourList.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

}
