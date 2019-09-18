package si.samgres.api.services;


import org.springframework.stereotype.Service;
import si.samgres.api.helpers.GsonHelper;
import si.samgres.api.helpers.ParameterStringBuilder;
import si.samgres.api.managers.authentication.DARSTokenManager;
import si.samgres.api.managers.PropertiesManager;
import si.samgres.api.models.DARS.authentication.Token;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Service
public class DARSService {
    private String DARS_URL;

    //accessible methods
    public si.samgres.api.models.DARS.events.FeatureCollection getEvents() {
        initialize();

        //create http request
        HttpURLConnection urlConnection = null;
        try {
            //create url object
            URL address = new URL(DARS_URL + "/dc/b2b.dogodki.geojson");
            urlConnection = (HttpURLConnection) address.openConnection();

            //settings
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);

            //add headers
            urlConnection.setRequestProperty("Authorization", getToken().getToken_type() + " " + getToken().getAccess_token());

            //read response
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            //convert
            si.samgres.api.models.DARS.events.FeatureCollection featureCollection = GsonHelper.fromJson(content.toString(), si.samgres.api.models.DARS.events.FeatureCollection.class);

            //return response
            return featureCollection;
        } catch (Exception e) {
            e.printStackTrace();
        }

        //fail
        return null;
    }

    public si.samgres.api.models.DARS.borderDelays.FeatureCollection getBorderDelays() {
        initialize();

        //create http request
        HttpURLConnection urlConnection = null;
        try {
            //create url object
            URL address = new URL(DARS_URL + "/dc/b2b.borderdelays.geojson");
            urlConnection = (HttpURLConnection) address.openConnection();

            //settings
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);

            //add headers
            urlConnection.setRequestProperty("Authorization", getToken().getToken_type() + " " + getToken().getAccess_token());

            //read response
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            //convert
            si.samgres.api.models.DARS.borderDelays.FeatureCollection featureCollection = GsonHelper.fromJson(content.toString(), si.samgres.api.models.DARS.borderDelays.FeatureCollection.class);

            //return response
            return featureCollection;
        } catch (Exception e) {
            e.printStackTrace();
        }

        //fail
        return null;
    }

    public si.samgres.api.models.DARS.storms.FeatureCollection getStorms() {
        initialize();

        //create http request
        HttpURLConnection urlConnection = null;
        try {
            //create url object
            URL address = new URL(DARS_URL + "/dc/b2b.burja.geojson");
            urlConnection = (HttpURLConnection) address.openConnection();

            //settings
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);

            //add headers
            urlConnection.setRequestProperty("Authorization", getToken().getToken_type() + " " + getToken().getAccess_token());

            //read response
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            //convert
            si.samgres.api.models.DARS.storms.FeatureCollection featureCollection = GsonHelper.fromJson(content.toString(), si.samgres.api.models.DARS.storms.FeatureCollection.class);

            //return response
            return featureCollection;
        } catch (Exception e) {
            e.printStackTrace();
        }

        //fail
        return null;
    }

    //private methods
    private Token getToken() {
        if (DARSTokenManager.getCurrentDARSToken() == null) { //flag (if token has never been created in this instance)
            //get new token
            String tokenJson = authenticate(PropertiesManager.getProperty("dars.api.username"), PropertiesManager.getProperty("dars.api.password"));

            //convert and set object
            DARSTokenManager.setCurrentDARSToken(GsonHelper.fromJson(tokenJson, Token.class));
        }
        else if (!DARSTokenManager.isTokenStillValid()) { //flag (token is no longer valid and needs a refresh)
            //refresh token
            String tokenJson = refreshToken(DARSTokenManager.getCurrentDARSToken());

            //convert and set object
            DARSTokenManager.setCurrentDARSToken(GsonHelper.fromJson(tokenJson, Token.class));
        }

        return DARSTokenManager.getCurrentDARSToken();
    }

    private void initialize() {
        if (DARS_URL == null || DARS_URL == "") { //ensure dars url variable is initialized
            DARS_URL = PropertiesManager.getProperty("dars.api.url");
        }
    }

    private String authenticate(String username, String password) {
        initialize();

        //flags
        if (username == "" || username == null) {
            return null;
        }
        if (password == "" || password == null) {
            return null;
        }

        //create http request
        HttpURLConnection urlConnection = null;
        try {
            //create url object
            URL address = new URL(DARS_URL + "/uc/user/token");
            urlConnection = (HttpURLConnection) address.openConnection();

            //settings
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);

            //add headers
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");


            //create body
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("grant_type", "password");
            parameters.put("username", username);
            parameters.put("password", password);

            //convert body to bytes and add it to request
            DataOutputStream out = new DataOutputStream(urlConnection.getOutputStream());
            out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
            out.flush();
            out.close();

            //read response
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            //return response
            return content.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //fail
        return null;
    }

    private String refreshToken(Token currentToken) {
        initialize();

        if (currentToken == null) { //flag
            return null;
        }

        //create http request
        HttpURLConnection urlConnection = null;
        try {
            //create url object
            URL address = new URL(DARS_URL + "/uc/user/token");
            urlConnection = (HttpURLConnection) address.openConnection();

            //settings
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);

            //add headers
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");


            //create body
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("grant_type", "refresh_token");
            parameters.put("refresh_token", currentToken.getRefresh_token());

            //convert body to bytes and add it to request
            DataOutputStream out = new DataOutputStream(urlConnection.getOutputStream());
            out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
            out.flush();
            out.close();

            //read response
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            //return response
            return content.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //fail
        return null;
    }
}
