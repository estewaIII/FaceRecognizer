package com.facerecognition;


import com.facerecognition.services.CompareFaces;
import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FaceRecognitionApplication {
    public static void main(String[] args) {
        SpringApplication.run(FaceRecognitionApplication.class,args);

        String target = "honestAbe.jpg";
        String source = "honestAbe2.jpg";
        CompareFaces.compareTwoImages(target,source);
        //System.out.println(CompareFaces.compareTwoImages(target,source));

    }
}
