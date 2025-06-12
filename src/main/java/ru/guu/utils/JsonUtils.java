//Утилита для работы с JSON
package ru.guu.utils;

import java. io.BufferedReader; 
import java. io. InputStreamReader; 
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonUtils {
    
    public static String getCryptoDataFromApi(String apiUrl) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET") ;
            conn.setRequestProperty("Accept", "application/json");
            
            if (conn.getResponseCode() == 200) { // Проверка на успешный ответ
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream())) ;
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                rd.close();
            } else {
                System.err.println("Error: HTTP response code" + conn.getResponseCode());
            }
        } catch (Exception e) {
            System.err.println("Error during API request: " + e.getMessage());
        }

        return result.length() > 0 ? result.toString() : null;
    }
}
