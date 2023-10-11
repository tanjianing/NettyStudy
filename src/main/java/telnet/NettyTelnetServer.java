package telnet;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.ServerSocket;
import java.util.EventListener;

public class NettyTelnetServer {

    //文章地址https://www.cnblogs.com/sanshengshui/p/9726306.html
    private static final  int port = 8888;
    private ServerBootstrap serverBootstrap;

    //Configure the server
    //创建两个EventLoopGroup对象
    //创建boss线程组 用于服务端接受客户端的连接
    private EventLoopGroup bossGroup = new NioEventLoopGroup();
    // 创建 worker 线程组 用于进行 SocketChannel 的数据读写
    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    public void open(){
        // 创建 ServerBootstrap 对象
        serverBootstrap =  new ServerBootstrap();
        serverBootstrap.option(ChannelOption.SO_BACKLOG,1024);
        serverBootstrap.group(bossGroup,workerGroup)
                //设置要被实例化的为 NioServerSocketChannel 类
                .channel(NioServerSocketChannel.class)
                // 设置 NioServerSocketChannel 的处理器
                .handler(new LoggingHandler(LogLevel.INFO))
                // 设置连入服务端的 Client 的 SocketChannel 的处理器
                .childHandler(new NettyTelnetInitializer());
    }
}
