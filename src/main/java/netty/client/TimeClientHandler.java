package netty.client;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Title: TimeClientHandler
 * Description:
 * author: liujie
 * date: 2017-08-17 上午10:17
 */
public class TimeClientHandler extends ChannelHandlerAdapter {

    private int count = 0;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause);
        ctx.close();
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        byte[] msg = ("hello world" + "\n").getBytes();
        ByteBuf buf = null ;
        for(int i = 0 ;i<50 ;i++){
            buf = Unpooled.buffer(msg.length);
            buf.writeBytes(msg);
            ctx.writeAndFlush(buf);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("NOW IS :" + body + ";客户端count = " + ++count);
    }
}
