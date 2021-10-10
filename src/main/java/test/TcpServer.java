package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
*@author:yqy
*@date:2021/10/5 12:32
*@description:
* 1.创建ServerSocket对象和指定端口号
* 2.使用ServerSocket对象中的accept，获取到请求的Socket对象
* 3.使用Socket对象中的getInputStream()获取网络字节输入流
* 4.使用网络字节输入流InputStream对象中的read（）方法，读取客户端发送的数据
* 5.使用Socket对象中的getoutputStream（）获取网络字节输出流
* 6.使用网络字节输出流OutputStream对象中的方法write，给客户端回写数据
 * @param null
*@return:
*/
public class TcpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(8089);
        Socket socket=serverSocket.accept();
        InputStream inputStream=socket.getInputStream();
        byte[] bytes=new byte[1024];
        int len=inputStream.read(bytes);
        OutputStream outputStream=socket.getOutputStream();
        outputStream.write("收到！".getBytes());
        socket.close();
        serverSocket.close();

    }
}
