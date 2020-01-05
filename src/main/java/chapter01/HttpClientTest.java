package chapter01;


import org.apache.commons.io.FileUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class HttpClientTest {
    public static void main(String[] args) throws Exception {

        test04();
    }


    private static void test01() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                FileUtils.writeStringToFile(new File("E:\\MYJAVA\\WebStudy\\Indepth_analysis_of_Java_Web_technology_insider\\baidu.html"), content, "UTF-8");
                System.out.println("内容长度" + content.length());
            }
        } finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }
    }

    private static void test02() throws Exception{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        URI uri = new URIBuilder("http://www.baidu.com/s").setParameter("wd", "java").build();
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                FileUtils.writeStringToFile(new File("E:\\MYJAVA\\WebStudy\\Indepth_analysis_of_Java_Web_technology_insider\\baidu-param.html"), content, "UTF-8");
                System.out.println("内容长度" + content.length());
            }
        }finally {
            if (response != null) {
                response.close();
            }
            httpclient.close();
        }
    }

    private static void test03() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://www.oschina.net/");
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                FileUtils.writeStringToFile(new File("E:\\MYJAVA\\WebStudy\\Indepth_analysis_of_Java_Web_technology_insider\\oschina.html"), content, "UTF-8");
                System.out.println("内容长度" + content.length());

            }
        }finally {
            if (response != null) {
                response.close();
            }
            httpclient.close();

        }
    }

    private static void test04() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://www.oschina.net/search");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
        parameters.add(new BasicNameValuePair("scope", "project"));
        parameters.add(new BasicNameValuePair("q", "java"));
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters);
        httpPost.setEntity(formEntity);
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity());
                FileUtils.writeStringToFile(new File("E:\\MYJAVA\\WebStudy\\Indepth_analysis_of_Java_Web_technology_insider\\oschina-param.html"), content, "UTF-8");
                System.out.println("内容长度" + content.length());
            }
        }finally {
            if (response != null) {
                response.close();
            }
            httpclient.close();
        }
    }
}
