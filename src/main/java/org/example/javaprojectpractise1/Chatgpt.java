package org.example.javaprojectpractise1;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Chatgpt {

    private static final String OPENAI_API_URL = "\"https://api.openai.com/v1/chat/completions\""; // Using text-davinci-002 engine
    private static final String API_KEY = "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"; // Replace with your OpenAI API key

    public static void main(String[] args) {
        try {
            String prompt = "Translate the following English text to French: 'Hello, how are you?'";
            String response = sendPostRequest(prompt);
            System.out.println("GPT-3 Response:\n" + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String sendPostRequest(String prompt) throws Exception {
        URL url = new URL(OPENAI_API_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // Set the request method and headers
        con.setRequestMethod("POST");
        con.setRequestProperty("Authorization", "Bearer " + API_KEY);
        con.setRequestProperty("Content-Type", "application/json");

        // Enable output and set the prompt
        con.setDoOutput(true);
        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            String jsonInputString = "{\"prompt\": \"" + prompt + "\", \"max_tokens\": 150}";
            wr.write(jsonInputString.getBytes(StandardCharsets.UTF_8));
            wr.flush();
        }

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Read the response
            StringBuilder response;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            return response.toString();
        } else {
            throw new Exception("Failed to get response from OpenAI API. HTTP error code: " + responseCode);
        }
    }
}
