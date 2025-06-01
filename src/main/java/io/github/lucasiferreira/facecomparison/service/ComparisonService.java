package io.github.lucasiferreira.facecomparison.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;

import java.security.InvalidParameterException;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ComparisonService {

    @Autowired
    private RekognitionClient client;


    public float compareTwoPhotos(String photo1, String photo2) throws Exception {
        float result = 0f;
        try {


            SdkBytes sourceBytes = SdkBytes.fromByteArray(Base64
                    .getDecoder()
                    .decode(photo1.substring(photo1.indexOf(",") + 1)));
            SdkBytes targetBytes = SdkBytes.fromByteArray(Base64
                    .getDecoder()
                    .decode(photo2.substring(photo2.indexOf(",") + 1)));

            //cria uma objeto image do sourceImage
            Image sourceImage = Image.builder()
                    .bytes(sourceBytes)
                    .build();

            Image targetImage = Image.builder()
                    .bytes(targetBytes)
                    .build();

            CompareFacesRequest facesRequest = CompareFacesRequest
                    .builder()
                    .sourceImage(sourceImage)
                    .targetImage(targetImage)
                    .similarityThreshold(10F)
                    .build();

            CompareFacesResponse compareFacesResult = client.compareFaces(facesRequest);
            System.out.println(compareFacesResult.toString());

            List<CompareFacesMatch> faceDetails = compareFacesResult.faceMatches();

            for (CompareFacesMatch match: faceDetails) {
                ComparedFace face = match.face();
                BoundingBox position = face.boundingBox();

                System.out.println("Face at " + position.left().toString()
                        + " x:" + position.top()
                        + " y:" + position.left()
                        + " matches with " + face.confidence().toString()
                        + "% confidence.");
            }
            List<ComparedFace> uncompared = compareFacesResult.unmatchedFaces();

            System.out.println("There was " + uncompared.size() + "face(s) that did not match");
            System.out.println("Source image rotation: " + compareFacesResult.sourceImageOrientationCorrection());
            System.out.println("Target image rotation: " + compareFacesResult.targetImageOrientationCorrection());

            Optional<CompareFacesMatch> resultMatchFaceMatch = faceDetails.stream().max(Comparator.comparing(a -> a.similarity().floatValue()));
            result = resultMatchFaceMatch.isPresent() ? resultMatchFaceMatch.get().similarity().floatValue() : 0f;


        } catch (InvalidParameterException e) {
//            throw new InvalidParameterException();
        }


        return result;
    }
}
