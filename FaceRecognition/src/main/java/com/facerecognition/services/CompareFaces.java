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


    public static CompareFacesResult compareThreeImages(String targetImage, String sourceImage, String sourceImage1) {
        String clientRegion = "us-east-2";
        AmazonRekognition client = AmazonRekognitionClientBuilder.standard().withRegion(clientRegion).withCredentials(new ProfileCredentialsProvider()).build();
        CompareFacesRequest request = new CompareFacesRequest()
                .withSourceImage(new Image().withS3Object(new S3Object().withBucket("facething").withName(targetImage)))
                .withTargetImage(new Image().withS3Object(new S3Object().withBucket("facething").withName(sourceImage))).withSimilarityThreshold(0f);
        CompareFacesResult response = client.compareFaces(request);
        AmazonRekognition client1 = AmazonRekognitionClientBuilder.standard().withRegion(clientRegion).withCredentials(new ProfileCredentialsProvider()).build();
        CompareFacesRequest request1 = new CompareFacesRequest()
                .withSourceImage(new Image().withS3Object(new S3Object().withBucket("facething").withName(targetImage)))
                .withTargetImage(new Image().withS3Object(new S3Object().withBucket("facething").withName(sourceImage1))).withSimilarityThreshold(0f);
        CompareFacesResult response1 = client1.compareFaces(request1);
        System.out.println(response1);
        showSimilarity(response,targetImage,sourceImage,response1, sourceImage1);
        return response;
    }

    public static void showSimilarity(CompareFacesResult response, String target,String source,CompareFacesResult response1, String source1){
        List<CompareFacesMatch> lists = response.getFaceMatches();
        List<CompareFacesMatch> lists1 = response1.getFaceMatches();
        if (!lists.isEmpty()) {
            for (CompareFacesMatch label : lists) {
               String similarity = label.getSimilarity().toString();
                System.out.println("------------");
                System.out.println("Photo 1 " + target + " and photo 2 " + source + " have a similarity score of " + similarity);

            }
        } else {
            System.out.println("There was an error with the images");
        }
        if (!lists1.isEmpty()) {
            for (CompareFacesMatch label1 : lists1) {
                String similarity1 = label1.getSimilarity().toString();
                System.out.println("------------");
                System.out.println("Photo 1 " + target + " and photo 3 " + source1 + " have a similarity score of " + similarity1);
            }
        } else {
            System.out.println("There was an error with the images");
        }
}



}