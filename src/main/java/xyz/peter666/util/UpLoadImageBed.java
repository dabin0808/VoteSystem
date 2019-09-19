package xyz.peter666.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 上传图片到服务器，会返回一个字符串地址，将地址保存到数据库即可
 */
public class UpLoadImageBed {
    /**
     *
     * @param is  图片的输入流
     * @param name   图片名称，必须包含后缀名
     * @return  返回服务器图片地址
     */
    public static String up(InputStream is,String name){

        URL url = null;
        String rs="";
        try {
            url = new URL("http://www.peter666.xyz/uploadimgtopeter666/saveImg?name="+name);

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setDoOutput(true);
        conn.setUseCaches(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "text/html");
        conn.setRequestProperty("Cache-Control", "no-cache");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.connect();
        conn.setConnectTimeout(10000);
        OutputStream os = conn.getOutputStream();

        byte[] bytes = new byte[10240];
        int len = -1;
        while((len=is.read(bytes))!=-1){
            os.write(bytes,0,len);
        }
        os.flush();
        os.close();
        is.close();


        InputStream inputStream = conn.getInputStream();
        len = -1;


        if(((len=inputStream.read(bytes))!=-1)){
            rs = new String(bytes,0,len);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }



        return rs;
    }
}
