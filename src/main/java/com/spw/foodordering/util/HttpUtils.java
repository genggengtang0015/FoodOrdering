package com.spw.foodordering.util;

import com.google.gson.Gson;

import org.apache.http.params.CoreConnectionPNames;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.conn.ssl.SSLSocketFactory;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpDelete;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.client.methods.HttpPut;
import cz.msebera.android.httpclient.conn.scheme.Scheme;
import cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.protocol.HTTP;

public class HttpUtils {
    /**
     * get,，默认超时120秒
     *
     * @param host
     * @param path
     * @param headers
     * @param querys
     * @return
     */
    public static HttpResponse doGet(String host, String path, Map<String, String> headers, Map<String, String> querys)
            throws Exception {
        return doGetwithTimeout(host, path, headers, querys, 120000);
    }

    public static HttpResponse doGetwithTimeout(String host, String path, Map<String, String> headers,
                                                Map<String, String> querys, int timeout) throws Exception {
        HttpClient httpClient = wrapClient(host);

        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout); // 请求超时
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout);// 读取超时

        HttpGet request = new HttpGet(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        return httpClient.execute(request);
    }

    /**
     * post form
     *
     * @param host
     * @param path
     * @param headers
     * @param querys
     * @param bodys
     * @return
     */
    public static HttpResponse doPost(String host, String path, Map<String, String> headers, Map<String, String> querys,
                                      Map<String, String> bodys) throws Exception {
        HttpClient httpClient = wrapClient(host);
        HttpPost request = new HttpPost(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        if (bodys != null) {
            List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
            for (String key : bodys.keySet()) {
                nameValuePairList.add(new BasicNameValuePair(key, bodys.get(key)));
            }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, "utf-8");
            // formEntity.setContentType("application/x-www-form-urlencoded;
            // charset=UTF-8");
            formEntity.setContentType("application/json; charset=UTF-8");
            request.setEntity(formEntity);
        }
        return httpClient.execute(request);
    }

    /**
     * Post String
     *
     * @param host
     * @param path
     * @param headers
     * @param querys
     * @param body
     * @return
     */
    public static HttpResponse doPost(String host, String path, Map<String, String> headers, Map<String, String> querys,
                                      String body) throws Exception {
        HttpClient httpClient = wrapClient(host);
        HttpPost request = new HttpPost(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        if (Util.isTrimEmpty(body)) {
            StringEntity entity = new StringEntity(body, "utf-8");
            entity.setContentType("application/json");
            request.setEntity(entity);
        }
        String str = "";
        Gson jsonResult = null;
        HttpResponse result = httpClient.execute(request);
        /** 请求发送成功，并得到响应 **/
        // if (result.getStatusLine().getStatusCode() == 200) {
        // try {
        // /**读取服务器返回过来的json字符串数据**/
        // str = EntityUtils.toString(result.getEntity());
        //
        // /**把json字符串转换成json对象**/
        // jsonResult = (JSON) JSON.parse(str);
        // } catch (Exception e) {
        //
        // }
        // }
        return result;
    }

    /**
     * Post stream
     *
     * @param host
     * @param path
     * @param headers
     * @param querys
     * @param body
     * @return
     */
    public static HttpResponse doPost(String host, String path, Map<String, String> headers, Map<String, String> querys,
                                      byte[] body) throws Exception {
        HttpClient httpClient = wrapClient(host);
        HttpPost request = new HttpPost(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        if (body != null) {
            request.setEntity(new ByteArrayEntity(body));
        }
        return httpClient.execute(request);
    }

    /**
     * Put String
     *
     * @param host
     * @param path
     * @param method
     * @param headers
     * @param querys
     * @param body
     * @return
     */
    public static HttpResponse doPut(String host, String path, String method, Map<String, String> headers,
                                     Map<String, String> querys, String body) throws Exception {
        HttpClient httpClient = wrapClient(host);
        HttpPut request = new HttpPut(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        if (Util.isTrimEmpty(body)) {
            request.setEntity(new StringEntity(body, "utf-8"));
        }
        return httpClient.execute(request);
    }

    /**
     * Put stream
     *
     * @param host
     * @param path
     * @param method
     * @param headers
     * @param querys
     * @param body
     * @return
     */
    public static HttpResponse doPut(String host, String path, String method, Map<String, String> headers,
                                     Map<String, String> querys, byte[] body) throws Exception {
        HttpClient httpClient = wrapClient(host);
        HttpPut request = new HttpPut(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        if (body != null) {
            request.setEntity(new ByteArrayEntity(body));
        }
        return httpClient.execute(request);
    }

    /**
     * Delete
     *
     * @param host
     * @param path
     * @param method
     * @param headers
     * @param querys
     * @return
     */
    public static HttpResponse doDelete(String host, String path, String method, Map<String, String> headers,
                                        Map<String, String> querys) throws Exception {
        HttpClient httpClient = wrapClient(host);
        HttpDelete request = new HttpDelete(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        return httpClient.execute(request);
    }

    private static String buildUrl(String host, String path, Map<String, String> querys)
            throws UnsupportedEncodingException {
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(host);
        if (!Util.isTrimEmpty(path)) {
            sbUrl.append(path);
        }
        if (null != querys) {
            StringBuilder sbQuery = new StringBuilder();
            for (Map.Entry<String, String> query : querys.entrySet()) {
                if (0 < sbQuery.length()) {
                    sbQuery.append("&");
                }
                if (Util.isTrimEmpty(query.getKey()) && !Util.isTrimEmpty(query.getValue())) {
                    sbQuery.append(query.getValue());
                }
                if (!Util.isTrimEmpty(query.getKey())) {
                    sbQuery.append(query.getKey());
                    if (!Util.isTrimEmpty(query.getValue())) {
                        sbQuery.append("=");
                        sbQuery.append(URLEncoder.encode(query.getValue(), "utf-8"));
                    }
                }
            }
            if (0 < sbQuery.length()) {
                sbUrl.append("?").append(sbQuery);
            }
        }
        return sbUrl.toString();
    }

    private static HttpClient wrapClient(String host) {
        HttpClient httpClient = new DefaultHttpClient();
//         if (host.startsWith("https://")) {
//            sslClient(httpClient);
//         }
        return httpClient;
    }


    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, Gson param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 发送POST请求，参数为JSON格式
     * @param json
     * @param url
     * @return
     * @throws IOException
     */
    public static String doPost(JSONObject json, String url) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        post.setHeader("Content-Type", "application/json");
        String result = "";
        InputStream inStream = null;

        try {

            StringEntity s = new StringEntity(json.toString(), "utf-8");
            s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            post.setEntity(s);

            // 发送请求
            HttpResponse httpResponse = client.execute(post);

            // 获取响应输入流
            inStream = httpResponse.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
            StringBuilder strber = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
                strber.append(line + "\n");

            result = strber.toString();

        } finally {
            if (null != inStream)
                inStream.close();
        }

        return result;
    }
}
