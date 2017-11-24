import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


public class HttpSnakeClient {

    private final String URL = "http://10.0.9.167:9101";

    public HttpSnakeClient() {

    }

    // HTTP GET request
    public void sendGet() throws Exception {

        String url = URL + "/simple-commands";

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        HttpResponse response = client.execute(request);

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result.toString());
    }

    // HTTP PUT request
    public void sendPut(String command) throws Exception {

        String url = URL + "/commands";

        HttpClient client = new DefaultHttpClient();
        HttpPut put = new HttpPut(url);
        StringEntity params =new StringEntity("{\"id\":\"id1\",\"pwd\":\"password\",\"cmd\":\""+command+"\"} ");

        put.addHeader("content-type", "application/json");
        put.addHeader("Accept","application/json");
        put.setEntity(params);

        HttpResponse response = client.execute(put);
        System.out.println("\nSending 'PUT' request to URL : " + url);
        System.out.println("Put parameters : " + put.getEntity());
        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(result.toString());
    }
}

