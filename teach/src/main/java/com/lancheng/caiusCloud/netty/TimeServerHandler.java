package com.lancheng.caiusCloud.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 本节要实现的协议是TIME协议。它与前面的例子不同的是，它在不接收任何请求的情况下发送一条包含32位整数的消息，
 * 并在消息发送后关闭连接。在这个例子中，你将学习如何构造和发送消息，并在完成后关闭连接。
 * 因为我们将忽略任何接收到的数据，而是在建立连接后立即发送消息，所以这次我们不能使用 channelRead() 方法。
 * 相反，我们应该覆盖channelActive()方法。下面是实现方法。
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    //正如解释过的，当连接建立并准备好产生流量时，将调用channelActive()方法。让我们在这个方法中写一个代表当前时间的32位整数。
    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        // 要发送一个新的消息，我们需要分配一个新的缓冲区，它将包含消息。我们将写入一个32位的整数，
        // 因此我们需要一个容量至少为4字节的ByteBuf。通过ChannelHandlerContext.alloc()获取当前的ByteBufAllocator，并分配一个新的缓冲区。
        final ByteBuf time = ctx.alloc().buffer(4);
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
        // 像往常一样，我们写出构造的信息，但是，我们以前在NIO中发送消息之前不是要调用java.nio.ByteBuffer.flip()吗？ByteBuf没有这样的方法，
        // 因为它有两个指针，一个用于读操作，另一个用于写操作。当你向ByteBuf写东西的时候，写者索引会增加，而读者索引不会改变。
        // 读取者索引和写入者索引分别代表消息的开始和结束位置。
        // 相比之下，NIO buffer并没有提供一个干净的方法来弄清消息内容的开始和结束位置，而不调用flip方法。当你忘记翻转缓冲区时，你将会遇到麻烦，
        // 因为什么也不会发送或发送错误的数据。这样的错误在Netty中不会发生，因为我们对不同的操作类型有不同的指针。当你习惯了它，
        // 你会发现它让你的生活变得更加轻松--没有翻转的生活!另一点需要注意的是，
        // ChannelHandlerContext.write()（和writeAndFlush()）方法会返回一个ChannelFuture。
        // 一个ChannelFuture代表一个尚未发生的I/O操作。这意味着，任何请求的操作可能还没有被执行，
        // 因为在Netty中所有的操作都是异步的。可能在消息发送之前就关闭了连接。
        // 因此，你需要在ChannelFuture完成后调用close()方法，它是由write()方法返回的，
        // 当写操作完成后，它会通知其监听器。请注意，close()也可能不会立即关闭连接，它返回的是一个ChannelFuture。
        final ChannelFuture f = ctx.writeAndFlush(time);
        // 那么当一个写请求完成后，我们如何得到通知呢？这很简单，只要在返回的ChannelFuture中添加一个ChannelFutureListener即可。
        // 在这里，我们创建了一个新的匿名ChannelFutureListener，当操作完成后，它就会关闭Channel。
        // 另外，你也可以使用一个预先定义的监听器来简化代码。
        f.addListener((ChannelFutureListener) future -> {
            assert f == future;
            ctx.close();
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
