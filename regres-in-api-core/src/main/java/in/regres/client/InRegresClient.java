package in.regres.client;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

public class InRegresClient {
    public static final String BASE_URL = "https://reqres.in";

    public <T> T sendGet(String endPoint, Class<T> clazz) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault();
             CloseableHttpResponse response = httpclient.execute(new HttpGet(BASE_URL + endPoint))) {
            HttpEntity entity = response.getEntity();
            String responseMessage;
            response.getStatusLine().getStatusCode();
            if (Objects.nonNull(entity)) {
                responseMessage = IOUtils.toString(entity.getContent(), StandardCharsets.UTF_8);
                return jsonToObject(responseMessage, clazz);
            }
            throw new RuntimeException("Ответ не должен быть пустым");
        } catch (IOException ioException) {
            throw new RuntimeException("Ошибка выполнения запроса", ioException);
        }
    }

    public <T> T sendPost(String endPoint, List<NameValuePair> params, Class<T> clazz) {
        HttpPost httpPost = new HttpPost(BASE_URL + endPoint);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Ошибка добавления параметров в запрос", e);
        }
        try (CloseableHttpClient httpclient = HttpClients.createDefault();
             CloseableHttpResponse response = httpclient.execute(httpPost)) {
            HttpEntity entity = response.getEntity();
            String responseMessage;
            response.getStatusLine().getStatusCode();

            if (Objects.nonNull(entity)) {
                responseMessage = IOUtils.toString(entity.getContent(), StandardCharsets.UTF_8);
                return jsonToObject(responseMessage, clazz);
            }
            throw new RuntimeException("Ответ не должен быть пустым");
        } catch (IOException ioException) {
            throw new RuntimeException("Ошибка выполнения запроса", ioException);
        }
    }

    public <T> T sendPut(String endPoint, List<NameValuePair> params, Class<T> clazz) {
        HttpPut httpPut = new HttpPut(BASE_URL + endPoint);
        try {
            httpPut.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Ошибка добавления параметров в запрос", e);
        }
        try (CloseableHttpClient httpclient = HttpClients.createDefault();
             CloseableHttpResponse response = httpclient.execute(httpPut)) {
            HttpEntity entity = response.getEntity();
            String responseMessage;
            response.getStatusLine().getStatusCode();

            if (Objects.nonNull(entity)) {
                responseMessage = IOUtils.toString(entity.getContent(), StandardCharsets.UTF_8);
                return jsonToObject(responseMessage, clazz);
            }
            throw new RuntimeException("Ответ не должен быть пустым");
        } catch (IOException ioException) {
            throw new RuntimeException("Ошибка выполнения запроса", ioException);
        }
    }

    public <T> T jsonToObject(String responseMessage, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(responseMessage, clazz);
    }
}
