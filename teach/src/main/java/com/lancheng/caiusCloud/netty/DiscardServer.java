package com.lancheng.caiusCloud.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 丢弃任何收到的数据
 */
public class DiscardServer {

    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // NioEventLoopGroup是一个处理I/O操作的多线程事件循环。Netty为不同类型的传输提供了各种EventLoopGroup的实现。
        // 我们在这个例子中实现了服务器端的应用程序,
        // 因此将使用两个NioEventLoopGroup。第一个，通常被称为 "Boss" 接受一个传入的连接。第二种，通常被称为 "Worker"。
        // 一旦Boss接受了这个连接，就会处理接受的连接的流量。并将接受的连接注册到该Worker
        // 使用多少个线程以及它们如何被映射到创建的通道取决于EventLoopGroup的实现，甚至可以通过一个构造函数来配置。.
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //ServerBootstrap是一个设置服务器的辅助类
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    //这里，我们指定使用NioServerSocketChannel类，该类用于实例化一个新的Channel来接受传入的连接。
                    .channel(NioServerSocketChannel.class)
                    // 这里指定的处理程序将总是被新接受的Channel评估。ChannelInitializer是一个特殊的处理程序，目的
                    // 是帮助用户配置一个新的Channel。很可能你想通过添加一些处理程序来配置新Channel的ChannelPipeline，
                    // 比如DiscardServerHandler来实现你的网络应用。随着应用的复杂化，你很可能会在管道中添加更多的
                    // 处理程序，并最终将这个匿名类提取为一个顶层类。
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new DiscardServerHandler());
                        }
                    })
                    // 你也可以设置通道实现中特有的参数。我们正在编写一个TCP/IP服务器，所以我们可以设置诸如tcpNoDelay和keepAlive
                    // 这样的套接字选项。请参考ChannelOption的apidocs和具体的ChannelConfig实现来了解支持的ChannelOptions。
                    // option()是指接受传入连接的NioServerSocketChannel。
                    // childOption()是指父ServerChannel接受的Channel，在本例中是NioSocketChannel。
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

            // 绑定并且开始接收输入连接
            // 我们现在已经准备好了。剩下的就是绑定到端口和启动服务器了。在这里，我们绑定到机器中所有的网卡（网卡）的8080端口。
            // 现在你可以随意调用bind()方法（使用不同的绑定地址）。
            ChannelFuture f = b.bind(port).sync();
            // 等到服务器socket关闭.
            // 在这个例子中，这种情况并不会发生，但是你可以做到优雅的
            // 关掉你的服务.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        new DiscardServer(port).run();
    }

}
