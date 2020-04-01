package com.facerecognition.services;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import com.amazonaws.util.IOUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

public class CompareFaces {


    public static CompareFacesResult compareTwoImages(String targetImage, String sourceImage) {
        String clientRegion = "us-east-2";
        AmazonRekognition client = AmazonRekognitionClientBuilder.standard().withRegion(clientRegion).withCredentials(new ProfileCredentialsProvider()).build();
        CompareFacesRequest request = new CompareFacesRequest()
                .withSourceImage(new Image().withS3Object(new S3Object().withBucket("facething").withName(targetImage)))
                .withTargetImage(new Image().withS3Object(new S3Object().withBucket("facething").withName(sourceImage))).withSimilarityThreshold(0f);
        CompareFacesResult response = client.compareFaces(request);
        showSimilarity(response,targetImage,sourceImage);
        return response;
    }

    public static void showSimilarity(CompareFacesResult response, String target,String source){
        List<CompareFacesMatch> lists = response.getFaceMatches();
        if (!lists.isEmpty()) {
            for (CompareFacesMatch label : lists) {
               String output = label.getSimilarity().toString();
                System.out.println("------------");
                //System.out.println(label.getFace() + ": Similarity is " + label.getSimilarity().toString());
                System.out.println("Photo 1 " + target + " and photo 2 " + source + " have a similarity score of " + label.getSimilarity().toString());
            }
        } else {
            System.out.println("Faces Does not match");
        }
}



}