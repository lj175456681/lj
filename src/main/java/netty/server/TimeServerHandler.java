package netty.server;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Title: TimeServerHandler
 * Description:
 * author: liujie
 * date: 2017-08-16 下午5:23
 */
public class TimeServerHandler extends ChannelHandlerAdapter {

    private int count = 0 ;

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause);
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("服务端count = " + ++count);
        System.out.println("服务端接收到的消息为：" + body);
        String retMsg = "service return msg" + System.getProperty("line.separator");
        ByteBuf response = Unpooled.copiedBuffer(retMsg.getBytes());
        ctx.writeAndFlush(response);
    }
}
