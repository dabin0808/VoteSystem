package xyz.peter666.action;

import com.opensymphony.xwork2.Action;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


@Controller("code")
public class CheckCodeServlet implements Action {
    public void genreateCode() throws ServletException, IOException {

        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        // 服务器通知浏览器不要缓存
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");

        int width = 110;
        int height = 35;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();
        g.setColor(Color.gray);
        g.fillRect(0,0,width,height);

        String code = getCheckCode();
        request.getSession().setAttribute("validateCode",code);
        g.setColor(Color.blue);
        g.setFont(new Font("黑体",Font.BOLD,24));
        g.drawString(code,30,26);

        ImageIO.write(image,"PNG",response.getOutputStream());
    }

    public String getCheckCode() {
        String base = "0123456789abcdefghijkmnlopqrstuvwxyzABCDEFGHIJKMNLOPQRSTUVWXYZ";
        int size = base.length();
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<4; i++) {
            int index = r.nextInt(size);
            char ch = base.charAt(index);
            sb.append(ch);
        }
        return sb.toString();
    }


    @Override
    public String execute() throws Exception {


        System.out.println("进入了方法");
        return null;
    }
}
