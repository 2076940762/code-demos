package verification;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VerificationCodeServlet
 */
public class VerificationCodeServlet extends HttpServlet
    {
        private static final long serialVersionUID = 1L;

        /**
         * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
         *      response)
         */
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            // 使用java图形界面技术绘制一张图片

            int charNum = 4;
            int width = 30 * 4;
            int height = 30;

            // 1. 创建一张内存图片
//            Constructs a BufferedImage of one of the predefinedimage types. The ColorSpace for the image is thedefault sRGB space.
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            // 2.获得绘图对象
            Graphics graphics = bufferedImage.getGraphics();

            // 3、绘制背景颜色
            graphics.setColor(Color.YELLOW);
            graphics.fillRect(0, 0, width, height);

            // 4、绘制图片边框
            graphics.setColor(Color.BLUE);
            graphics.drawRect(0, 0, width - 1, height - 1);

            // 5、输出验证码内容
            graphics.setColor(Color.RED);
            graphics.setFont(new Font("宋体", Font.BOLD, 20));

            // 随机输出4个字符
            Graphics2D graphics2d = (Graphics2D) graphics;
            String s = "ABCDEFGHGKLMNPQRSTUVWXYZ23456789";
            Random random = new Random();
            // session中要用到
            String msg = "";
            int x = 5;
            for (int i = 0; i < 4; i++) {
                int index = random.nextInt(32);
                String content = String.valueOf(s.charAt(index));
                msg += content;
                double theta = random.nextInt(45) * Math.PI / 180;
                // 让字体扭曲
                graphics2d.rotate(theta, x, 18);
                graphics2d.drawString(content, x, 18);
                graphics2d.rotate(-theta, x, 18);
                x += 30;
            }

            // 6、绘制干扰线
            graphics.setColor(Color.GRAY);
            for (int i = 0; i < 5; i++) {
                int x1 = random.nextInt(width);
                int x2 = random.nextInt(width);

                int y1 = random.nextInt(height);
                int y2 = random.nextInt(height);
                graphics.drawLine(x1, y1, x2, y2);
            }

            // 释放资源
            graphics.dispose();

            // 图片输出 ImageIO
//            The current cache settings from getUseCacheand getCacheDirectory will be used to control caching.
//            Parameters:im a RenderedImage to be written.formatName a String containing the informalname of the format.output an OutputStream to be written to.Returns:false if no appropriate writer is found.
            ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
        }

        /**
         * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
         *      response)
         */
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

    }
