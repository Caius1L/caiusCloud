package com.lancheng.caiusCloud.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * 1.DiscardServerHandler扩展了ChannelInboundHandlerAdapter，它是ChannelInboundHandler的一个实现。
 * ChannelInboundHandler提供了各种事件处理方法，你可以覆盖这些方法。目前，只需要扩展ChannelInboundHandlerAdapter就可以了，
 * 而不是自己去实现处理程序接口。
 * 2.我们在这里重写channelRead()事件处理方法。每当从客户端接收到新的数据时，这个方法就会和接收到的消息一起被调用。在这个例子中，
 * 接收到的消息的类型是ByteBuf。
 * 3.为了实现DISCARD协议，处理程序必须忽略收到的消息。ByteBuf是一个引用计数的对象，必须通过release()方法显式释放。请记住，
 * 释放任何传递给处理程序的引用计数对象是处理程序的责任。通常，channelRead()处理程序方法的实现方式如下。
 * 4.当Netty由于I/O错误而引发异常，或者由于处理事件时抛出的异常而被处理程序执行时，会用Throwable调用exceptionCaught()事件处理方法。
 * 在大多数情况下，捕捉到的异常应该被记录下来，并且在这里关闭它的相关通道，尽管这个方法的实现可以是不同的，这取决于你想做什么来处理特殊情况。
 * 例如，你可能想在关闭连接之前发送一条带有错误代码的响应消息。
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * ChannelHandlerContext对象提供了各种操作，使你能够触发各种I/O事件和操作。在这里，我们调用write(Object)将收到的消息逐字逐句地写入。
     * 请注意，我们并没有像在DISCARD例子中那样释放收到的消息。这是因为Netty在将其写出到线上时，会为你释放。
     * ctx.write(Object)并没有把消息写到线上。它在内部被缓冲，然后由ctx.flush()冲出到线上。或者，为了简洁起见，你可以调用ctx.writeAndFlush(msg)。
     * 如果你再次运行telnet命令，你会看到服务器将你发送的任何内容发回。
     * 回音服务器的完整源代码位于发行版的io.netty.example.echo包中。
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {// (1)
        ctx.write(msg); // (1)
        ctx.flush(); // (2)
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // (4)
        // 当有异常被捕获的时候关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
