package Utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class EmailConfigReader {

    public static String mailServer;
    public static String from;
    public static String password;
    public static String[] to;

    // Getters
    public static String getMailServer() {
        try {
            // Load the JSON file from resources
            JSONParser parser = new JSONParser();

            try (FileReader reader = new FileReader("src/test/resources/EmailCredentials.json")) {
                Object obj = parser.parse(reader);
                JSONObject jsonObject = (JSONObject) obj;

                // Get email object
                JSONObject emailObj = (JSONObject) jsonObject.get("email");

                // Extract values
                mailServer = (String) emailObj.get("mailServer");

            } catch (Exception e) {
                throw new RuntimeException("Failed to read email configuration", e);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return mailServer;
    }

    public static String getFrom() {try {
        // Load the JSON file from resources
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("src/test/resources/EmailCredentials.json")) {
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;

            // Get email object
            JSONObject emailObj = (JSONObject) jsonObject.get("email");

            // Extract values
            from = (String) emailObj.get("from");

        } catch (Exception e) {
            throw new RuntimeException("Failed to read email configuration", e);
        }
    } catch (RuntimeException e) {
        throw new RuntimeException(e);
    }
        return from;
    }

    public static String getPassword() {try {
        // Load the JSON file from resources
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("src/test/resources/EmailCredentials.json")) {
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;

            // Get email object
            JSONObject emailObj = (JSONObject) jsonObject.get("email");

            // Extract values
           password = (String) emailObj.get("password");

        } catch (Exception e) {
            throw new RuntimeException("Failed to read email configuration", e);
        }
    } catch (RuntimeException e) {
        throw new RuntimeException(e);
    }
        return password;
    }

    public static String[] getTo() {try {
        // Load the JSON file from resources
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("src/test/resources/EmailCredentials.json")) {
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;

            // Get email object
            JSONObject emailObj = (JSONObject) jsonObject.get("email");

            // Extract values
            JSONArray toArray = (JSONArray) emailObj.get("to");
            to = new String[toArray.size()];

            for (int i = 0; i < toArray.size(); i++) {
                to[i] = (String) toArray.get(i);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to read email configuration", e);
        }
    } catch (RuntimeException e) {
        throw new RuntimeException(e);
    }
        return to;
    }

    // Usage example
    public static void main(String[] args) {

        System.out.println("Mail Server: " + getMailServer());
        System.out.println("From: " + getFrom());
        System.out.println("Password: " + getPassword());
        String[] recipients = getTo();
        for (String recipient : recipients) {
            System.out.println("Recipient: " + recipient);
        }
    }
}