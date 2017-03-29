/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.Tools.signup;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Moses
 */
public class SignupV2ex {

    BasicCookieStore cookieStore = new BasicCookieStore();
    CloseableHttpClient httpclient = HttpClients.custom()
            .setDefaultCookieStore(cookieStore)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:30.0) Gecko/20100101 Firefox/30.0")
            .build();
    List<NameValuePair> params = new ArrayList<NameValuePair>();

    public void getLoginForm() throws IOException {

        
        HttpGet httpget = new HttpGet("http://v2ex.com/signin");
        CloseableHttpResponse response1 = httpclient.execute(httpget);
        try {
            HttpEntity entity = response1.getEntity();
            //获取once值
            String content = EntityUtils.toString(entity);
//                System.out.println(content);
            System.out.println("--------------");
            System.out.println("--------------");
            Document doc = Jsoup.parse(content);
//                Elements inputs = doc.select("input[type=text]");
            Elements inputs = doc.select("input[type=hidden]");
            for (int i = 0; i < inputs.size(); i++) {
                Element element = inputs.get(i);
                params.add(new BasicNameValuePair(element.attr("name"), element.attr("value")));
//                    params.put(element.attr("name"), element.attr("value"));
                System.out.println(element.toString());
                System.out.println(element.attr("name"));
                System.out.println(element.attr("value"));

            }

            System.out.println("--------------");
            System.out.println("--------------");

            System.out.println("--------------");
            System.out.println("--------------");
            System.out.println("Login form get: " + response1.getStatusLine());
            EntityUtils.consume(entity);

            System.out.println("Initial set of cookies:");
            List<Cookie> cookies = cookieStore.getCookies();
            if (cookies.isEmpty()) {
                System.out.println("None");
            } else {
                for (int i = 0; i < cookies.size(); i++) {
                    System.out.println("- " + cookies.get(i).toString());
                }
            }
        } finally {
            response1.close();
        }

//            HttpUriRequest login = RequestBuilder.post()
//                    .setUri(new URI("http://v2ex.com/signin"))
//                    .addParameter("u", "mosliu")
//                    .addParameter("p", "mosesmoses")
//                    .build();
//            CloseableHttpResponse response2 = httpclient.execute(login);
//            try {
//                HttpEntity entity = response2.getEntity();
//
//                System.out.println("Login form get: " + response2.getStatusLine());
//                
//                EntityUtils.consume(entity);
//
//                System.out.println("Post logon cookies:");
//                List<Cookie> cookies = cookieStore.getCookies();
//                if (cookies.isEmpty()) {
//                    System.out.println("None");
//                } else {
//                    for (int i = 0; i < cookies.size(); i++) {
//                        System.out.println("- " + cookies.get(i).toString());
//                    }
//                }
//                
//                
//                
//            } finally {
//                response2.close();
//            }
//            
//            
//            httpget = new HttpGet("http://v2ex.com/signin");
//            response1 = httpclient.execute(httpget);
//            try {
//                HttpEntity entity = response1.getEntity();
//                String content = EntityUtils.toString(entity);
//                System.out.println("-----------------content---------------------");
//                System.out.println(content);
//                
//                EntityUtils.consume(entity);
//            } finally {
//                response1.close();
//            }
//            
//            
    }

    public void setUserAndPass() {
        params.add(new BasicNameValuePair("u", "mosliu"));
        params.add(new BasicNameValuePair("p", "mosesmoses"));
    }

    public void doLoginAction() throws IOException, URISyntaxException {
        RequestBuilder builder = RequestBuilder.post()
                .setUri(new URI("http://v2ex.com/signin"))
                .addParameter("u", "mosliu")
                .addParameter("p", "mosesmoses");
        for (Iterator<NameValuePair> it = params.iterator(); it.hasNext();) {
            NameValuePair nameValuePair = it.next();
            builder.addParameter(nameValuePair);
        }
         HttpUriRequest login =builder.build();
//        HttpUriRequest login = RequestBuilder.post()
//                .setUri(new URI("http://v2ex.com/signin"))
//                .addParameter("u", "mosliu")
//                .addParameter("p", "mosesmoses")
//                .build();
        CloseableHttpResponse response = httpclient.execute(login);
//        HttpPost httppost = new HttpPost("http://v2ex.com/signin");
//        UrlEncodedFormEntity uefEntity;//发送用
//        setUserAndPass();
//        uefEntity = new UrlEncodedFormEntity(params, "utf-8");
//        httppost.setEntity(uefEntity);
//        CloseableHttpResponse response;
//
//        response = httpclient.execute(httppost);

        try {
            HttpEntity entity = response.getEntity();

            System.out.println("Login form get: " + response.getStatusLine());

            EntityUtils.consume(entity);

            System.out.println("Post logon cookies:");
            List<Cookie> cookies = cookieStore.getCookies();
            if (cookies.isEmpty()) {
                System.out.println("None");
            } else {
                for (int i = 0; i < cookies.size(); i++) {
                    System.out.println("- " + cookies.get(i).toString());
                }
            }

        } finally {
            response.close();
        }
    }

    public void check() throws IOException{
        HttpGet httpget = new HttpGet("http://v2ex.com/");
        CloseableHttpResponse response1 = httpclient.execute(httpget);
        try {
            HttpEntity entity = response1.getEntity();
            String content = EntityUtils.toString(entity);
             List<Cookie> cookies = cookieStore.getCookies();
            if (cookies.isEmpty()) {
                System.out.println("None");
            } else {
                for (int i = 0; i < cookies.size(); i++) {
                    System.out.println("- " + cookies.get(i).toString());
                }
            }
            System.out.println("-----------------content---------------------");
            System.out.println(content);

            EntityUtils.consume(entity);
        } finally {
            response1.close();
        }
    }
    
    /**
     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
     */
    public String post(String url, String reqEncoding, String respEncoding, List<NameValuePair> param) {
        String resStr = "";
        // 创建httppost
        HttpPost httppost = new HttpPost(
                url);
        // 创建参数队列
        List<NameValuePair> formparams = param;
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, reqEncoding);
            httppost.setEntity(uefEntity);
            HttpResponse response;
            response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                resStr = EntityUtils.toString(entity, respEncoding);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            // httpclient.getConnectionManager().shutdown();
        }
        return resStr;
    }

    /**
     * 发送 get请求
     */
    public String get(String url) {
        //httpclient = new DefaultHttpClient();
        String resStr = "";
        try {
            // 创建httpget.
            HttpGet httpget = new HttpGet(url);
            // 执行get请求.
            HttpResponse response = httpclient.execute(httpget);
            // 获取响应实体
            HttpEntity entity = response.getEntity();
            // 打印响应状态
            System.out.println(response.getStatusLine());
            if (entity != null) {
                // 打印响应内容长度
//                System.out.println("Response content length: "
//                        + entity.getContentLength());
                // 打印响应内容
//                System.out.println("Response content: "
//                        + EntityUtils.toString(entity));
                resStr = EntityUtils.toString(entity);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            //httpclient.getConnectionManager().shutdown();
        }
        return resStr;
    }

    public static void main(String[] args) {
        try {
            SignupV2ex sign = new SignupV2ex();
            sign.getLoginForm();
            sign.doLoginAction();
            System.out.println("------------check---------------");
            sign.check();
        } catch (IOException ex) {
            ex.printStackTrace();
//            Logger.getLogger(SignupV2ex.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            ex.printStackTrace();;
//            Logger.getLogger(SignupV2ex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
