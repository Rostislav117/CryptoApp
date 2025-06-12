//Реализация интерфейса
package ru.guu.data;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL; 
import java.util.List; 
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import ru.guu.domain.Crypto;

public class CryptoApiImpl implements CryptoApi {
    private static final String API_URL = "https://api.coincap.io/v2/assets";

    @Override
    public List<Crypto> fetchCryptoRates() {
        try {
            // Устанавливаем соединение с АРІ
            HttpURLConnection connection = (HttpURLConnection) new URL(API_URL).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Проверяем код ответа
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Failed to fetch data: HTTP " + responseCode); 
                return List.of();
            }

            // Читаем данные
            Scanner scanner = new Scanner(connection.getInputStream());
            StringBuilder jsonResponse = new StringBuilder();
            while (scanner.hasNext()) {
                jsonResponse.append(scanner.nextLine());
            }
            scanner. close();

            // Парсим JSON с помощью Gson
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(jsonResponse.toString(), JsonObject.class);
            String data = jsonObject.getAsJsonArray("data").toString();
            //JsonArray dataArray = jsonObject.getAsJsonArray("data");


            System.out.println("Data successfully uploaded" + responseCode);
            
            // Преобразуем JSON в список объектов Crypto
            return gson.fromJson(data, new TypeToken<List<Crypto>>()  {}.getType());
        } catch (IOException e) {
            System.out.println("Error fetching data: " + e.getMessage());
        }

        return List.of();
    }
}
