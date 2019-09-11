
import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import com.google.gson.*;

public class TestContainers {


    public HttpResponse setUp(String urlString) throws ClientProtocolException, IOException {
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("user", "user");
        provider.setCredentials(AuthScope.ANY, credentials);

        HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
        HttpGet request = new HttpGet(urlString);
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpResponse response = client.execute(request);
        return response;
    }

    @Test
    public void isContainerRunning() throws ClientProtocolException, IOException {
        HttpResponse response = setUp("http://localhost:8090/rest/server/containers");
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(response);
        
        assertEquals(statusCode, HttpStatus.SC_OK);
        assertEquals("application/json", response.getHeaders("Content-Type:"));
        // Gson gson = new Gson();
        // String json = gson.fromJson(bj);

    }

}