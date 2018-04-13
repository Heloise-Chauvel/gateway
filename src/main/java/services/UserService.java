package services;

import com.google.gson.Gson;
import entities.Utilisateur;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;

public class UserService {

    //Incription
    public String createUser(Utilisateur user, HttpURLConnection connection){
        Gson gson = new Gson();
        //ClientBuilder.newClient().target("").request().post()
        System.out.println("----user----");
        System.out.println(user);

        //Serialiser user
        String jsonInString = gson.toJson(user);
        System.out.println("jsonInString : " + jsonInString);
        //email : email etc...

        try {
            //Create connection
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/json");
            connection.setDoOutput(true);
            //Send request
            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream());
            wr.writeBytes(jsonInString);
            wr.close();

            //Headers
            /*Enumeration headerNames = connection.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String key = (String) headerNames.nextElement();
                String value = request.getHeader(key);
                map.put(key, value);
            }*/

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    //Connexion
    public String authUser(String email, String mdp, HttpURLConnection connection){
        //ClientBuilder.newClient().target("").request().post()

        //Transformer en JSON email mdp
        Gson gson = new Gson();
        String[] values = { email, mdp };
        String jsonInString = gson.toJson(values);
        System.out.println("jsonInString2 : " + jsonInString);

        //email : email password : password
        //on récupère un token. Si on en récupère un il est authentifié, sinon non

        try {
            String targetURL="";
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/json");

            //Send request
            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream());
            wr.writeBytes(jsonInString);
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public String getUser(String token, HttpURLConnection connection){
        //ClientBuilder.newClient().target("").request().post()

        Gson gson = new Gson();

        String jsonInString = gson.toJson(token);
        System.out.println("jsonInString3 : " + jsonInString);

        //On envoie le token récupéré de méthode précédente
        //On récupère un user complet + PaymentInfo

        try {
            String targetURL="";
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/json");

            //Send request
            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream());
            wr.writeBytes(jsonInString);
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
