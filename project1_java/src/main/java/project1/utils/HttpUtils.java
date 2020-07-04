package project1.utils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @param
 * @return
 */
public class HttpUtils {
    public static String getRequestBody(HttpServletRequest request) throws IOException {
        //取出请求参数
        ServletInputStream inputStream = request.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes=new byte[1024];
        int lentgth=0;
        while ((lentgth=inputStream.read(bytes))!=-1){
            outputStream.write(bytes,0,lentgth);
        }
        //转换：json字符串--->java对象
         return outputStream.toString("utf-8");
    }
}
