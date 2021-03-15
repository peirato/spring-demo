package com.peirato.learning.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @Classname MyClientHandler
 * @Description TODO
 * @Date 2021/3/15 3:42 下午
 * @Created by yangzeqi
 */
public class MyClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 发送消息到服务器
        ctx.writeAndFlush(Unpooled.copiedBuffer("abcd", CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 接受服务器发送的消息
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("收到服务端" + ctx.channel().remoteAddress() + "的消息："+ byteBuf.toString(CharsetUtil.UTF_8));
    }
}
