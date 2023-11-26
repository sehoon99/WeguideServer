package com.example.weguide.service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("jsonvaildator")
public class JsonValidator {

    public static boolean isValidJson(String jsonString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(jsonString);
            return true; // JSON 형식이 유효한 경우
        } catch (JsonParseException e) {
            // JSON 파싱 오류 발생 시
            return false;
        } catch (IOException e) {
            // 그 외의 IO 오류 발생 시
            return false;
        }
    }
    
    public static String readFileToString(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readString(path);
    }
    

    public static void main(String[] args) {
        String validJson = "{\"key\": \"value\"}";
        String invalidJson = "{\"key\": \"value\",}";

        boolean isValid = isValidJson(validJson);
        System.out.println("isValidJson(validJson): " + isValid);

        isValid = isValidJson(invalidJson);
        System.out.println("isValidJson(invalidJson): " + isValid);
    }
}
