package org.jpcl.util;

import com.alibaba.fastjson.JSON;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 发送http请求
 * @author Administrator
 */
public class JcOkHttpUtils {
    private static OkHttpClient client = new OkHttpClient.Builder()
            //设置连接超时时间
            .connectTimeout(60, TimeUnit.SECONDS)
            //设置读取超时时间
            .readTimeout(60, TimeUnit.SECONDS)
            .build();;

    /**
     * 发送get请求
     * @param url
     * @return
     * @throws IOException
     */
    public static String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        final Call call = client.newCall(request);
        Response response = call.execute();
        response.header("Content-Type");
        return response.body().string();
    }


    /**
     * 发送post请求
     * @param url
     * @param parameter
     * @return
     * @throws IOException
     */
    public static String post(String url, Map<String, Object> parameter) throws IOException {
        String urlParameter;
        if (parameter == null || parameter.size() == 0) {
            urlParameter = "";
        } else {
            urlParameter = String.valueOf(JSON.toJSON(parameter));
        }
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                urlParameter);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        final Call call = client.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }


    public void testPut() throws IOException {
        /*String api = "/api/user";
        String url = String.format("%s%s", BASE_URL, api);
        //请求参数
        UserVO userVO = UserVO.builder().name("h2t").id(11L).build();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                JSONObject.toJSONString(userVO));
        Request request = new Request.Builder()
                .url(url)
                .put(requestBody)
                .build();
        final Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response.body().string());*/
    }


    public void testUpload() throws IOException {
        /*String api = "/api/files/1";
        String url = String.format("%s%s", BASE_URL, api);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "docker_practice.pdf",
                        RequestBody.create(MediaType.parse("multipart/form-data"),
                                new File("C:/Users/hetiantian/Desktop/学习/docker_practice.pdf")))
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)  //默认为GET请求，可以不写
                .build();
        final Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response.body().string());*/
    }

    public void testDelete() throws IOException {
        /*String url = String.format("%s%s", BASE_URL, api);
        //请求参数
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();
        final Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response.body().string());*/
    }

    public void testCancelSysnc() throws IOException {
        /*String api = "/api/files/1";
        String url = String.format("%s%s", BASE_URL, api);
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        final Call call = client.newCall(request);
        Response response = call.execute();
        long start = System.currentTimeMillis();
        //测试连接的取消
        while (true) {
            //1分钟获取不到结果就取消请求
            if (System.currentTimeMillis() - start > 1000) {
                call.cancel();
                System.out.println("task canceled");
                break;
            }
        }

        System.out.println(response.body().string());*/
    }

    public static void main(String[] args) throws IOException {
        System.out.println(post("http://www.baidu.com", null));
    }
}
