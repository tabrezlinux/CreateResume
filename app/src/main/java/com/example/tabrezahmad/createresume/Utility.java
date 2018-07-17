package com.example.tabrezahmad.createresume;

import java.util.ArrayList;
import java.util.List;

public class Utility {

    static String [] imageurl = {"http://farm8.staticflickr.com/7452/27782542462_12e206359b_m.jpg",
            "http://farm8.staticflickr.com/7311/27782539412_1e1cece561_m.jpg",
            "http://farm8.staticflickr.com/7452/27782542462_12e206359b_m.jpg",
            "http://farm8.staticflickr.com/7326/27605634010_917553d601_m.jpg",
            "http://farm8.staticflickr.com/7452/27782542462_12e206359b_m.jpg",
            "http://farm8.staticflickr.com/7311/27782539412_1e1cece561_m.jpg",
            "http://farm8.staticflickr.com/7326/27605634010_917553d601_m.jpg",
            "http://farm8.staticflickr.com/7311/27782539412_1e1cece561_m.jpg",
            "http://farm8.staticflickr.com/7311/27782539412_1e1cece561_m.jpg",
            "http://farm8.staticflickr.com/7311/27782539412_1e1cece561_m.jpg",
            "http://farm8.staticflickr.com/7311/27782539412_1e1cece561_m.jpg",
            "http://farm8.staticflickr.com/7311/27782539412_1e1cece561_m.jpg",
            "http://farm8.staticflickr.com/7311/27782539412_1e1cece561_m.jpg",
            "http://farm8.staticflickr.com/7311/27782539412_1e1cece561_m.jpg"};

    static String names[]={"Nogoit",
            "Marshmallow",
            "Lollipop",
            "JellyBean",
            "Kitkat",
            "IcecreamSandwitch",
            "Froyo",
            "IOs",
            "Tabrez",
            "Musharraf",
            "Thank You",
            "Welcome",
            "Bye",
            "Say Hello"

    };

    public static List<ResumeListModels> getListPerson(){
        List<ResumeListModels> models = new ArrayList<>();
        models.add(new ResumeListModels( names[0], imageurl[0]));
        models.add(new ResumeListModels( names[1], imageurl[1]));
        models.add(new ResumeListModels( names[2], imageurl[2]));
        models.add(new ResumeListModels( names[3], imageurl[3]));
        models.add(new ResumeListModels( names[4], imageurl[4]));
        models.add(new ResumeListModels( names[5], imageurl[5]));
        models.add(new ResumeListModels( names[6], imageurl[6]));
        models.add(new ResumeListModels( names[7], imageurl[7]));
        models.add(new ResumeListModels( names[8], imageurl[8]));
        models.add(new ResumeListModels( names[9], imageurl[9]));
        models.add(new ResumeListModels( names[10], imageurl[10]));
        models.add(new ResumeListModels( names[11], imageurl[11]));
        models.add(new ResumeListModels( names[12], imageurl[12]));
        models.add(new ResumeListModels( names[13], imageurl[13]));
        return models;
    }
}
