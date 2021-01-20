package com.lancheng.caiusCloud.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 与DISCARD和ECHO服务器不同，我们需要一个客户端来处理TIME协议，
 * 因为人类无法将一个32位的二进制数据翻译成日历上的日期。在本节中，我们将讨论如何确保服务器正确工作，并学习如何用Netty编写客户端。
 * Netty中服务器和客户端最大的也是唯一的区别就是使用了不同的Bootstrap和Channel实现。请看下面的代码。
 */
public class TimeClient {

    public static void main(String[] args) throws Exception {
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //Bootstrap与ServerBootstrap类似，只是它是针对非服务器渠道，如客户端或无连接渠道。
            Bootstrap b = new Bootstrap(); // (1)
            //如果你只指定了一个EventLoopGroup，它将同时被用作WorkGroup和BossGroup。不过，BossGroup不用于客户端。
            b.group(workerGroup);
            //NioSocketChannel代替NioServerSocketChannel，被用来创建一个客户端Channel。
            b.channel(NioSocketChannel.class);
            //与ServerBootstrap不同，我们在这里没有使用childOption()，因为客户端的SocketChannel没有父节点。
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new TimeClientHandler());
                }
            });

            // 请注意，与ServerBootstrap不同，我们在这里没有使用childOption()，因为客户端的SocketChannel没有父节点。
            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
