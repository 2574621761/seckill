package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
*@author:yqy
*@date:2021/10/5 12:02
*@description:
* TCP通信的客户端：向服务器发起连接请求，给服务器发送数据，读取服务器回写的数据
* Socket：
*   构造方法：
*       Socket(String host,int port)创建一个套接字并将其连接到指定主机上的指定端口号
*    成员方法：
*     OutputStream getOutputStream()返回此套接字的输出法
*     InputStream()返回此套接字的输入类
*     void close()关闭此套接字
*    实现步骤：
*      1.创建一个客户端对象Socket,构造方法中绑定ip和端口号
*      2.使用Socket对象中的方法getOutputStream获取网络字节输出流OutputStream对象
*      3.使用网络字节输出流对象OutputStream()对象中的write，给服务器发送数据
*      4.使用Socket对象中的方法getInputStream()获取网络字节输入流InputStream对象
*      5.使用网络字节输入流InputStream对象中的方法read，读取服务器回写的数据
*      6.释放资源
 * @param null
*@return:
*/
public class TcpClient {
    public void tcp(String host,int port) throws IOException {
        System.out.println("服务器启动");
        Socket socket=new Socket(host,port);
        OutputStream os=socket.getOutputStream();
        os.write("你好服务器".getBytes());
        InputStream inputStream=socket.getInputStream();
        byte[] bytes=new byte[1024];
        int len=inputStream.read(bytes);
        System.out.println(len);
        OutputStream outputStream=socket.getOutputStream();
        socket.close();
    }
    public void tcps(int port) throws IOException {
        ServerSocket serverSocket=new ServerSocket(port);
        Socket socket=serverSocket.accept();
        InputStream inputStream=socket.getInputStream();
        byte[] bytes=new byte[1024];
        int len=inputStream.read(bytes);
        System.out.println(len);
        OutputStream outputStream=socket.getOutputStream();
        outputStream.write("收到！".getBytes());
        socket.close();
        serverSocket.close();
    }
    public static void main(String[] args) throws IOException {
//        Socket socket=new Socket("127.0.0.1", 8089);
//        OutputStream os=socket.getOutputStream();
//        os.write("你好服务器".getBytes());
//        InputStream inputStream=socket.getInputStream();
//        byte[] bytes=new byte[1024];
//        int len=inputStream.read(bytes);
//        OutputStream outputStream=socket.getOutputStream();
//        socket.close();
        TcpClient c=new TcpClient();
        c.tcp("127.0.0.1",8080);
        c.tcps(8080);


    }
}
