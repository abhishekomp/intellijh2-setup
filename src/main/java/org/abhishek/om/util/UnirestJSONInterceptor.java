package org.abhishek.om.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import kong.unirest.*;

/**
 * Created by sca820 on 24 aug., 2022
 * Log pure JSON requests and responses.
 */
public class UnirestJSONInterceptor implements kong.unirest.Interceptor {

    @Override
    public void onRequest(HttpRequest<?> request, Config config) {
        System.out.println(">>>>>>>>>>>>>>>> onRequest method was invoked");
        String s = request.getBody().isPresent() ? request.getBody().get().uniPart().toString() : "";

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement jsonElement = JsonParser.parseString(s);
        String prettyJsonString = gson.toJson(jsonElement);
        System.out.println("REQUEST = " + request.getUrl() + "\n" + prettyJsonString + "\n");
    }

    @Override
    public void onResponse(HttpResponse<?> response, HttpRequestSummary request, Config config) {
        try {
            System.out.println(">>>>>>>>>>>>>>>> onResponse method was invoked");
            JsonNode body = (JsonNode) response.getBody();
            System.out.println("RESPONSE(" + response.getStatus() + ") = " + "\n" + body.toPrettyString() + "\n");
        } catch (Exception e) {
            System.out.println("Error parsing json body, body was: " + response.getBody() + ", error was" + e);
        }
    }
}
