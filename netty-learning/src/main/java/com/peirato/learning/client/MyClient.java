package com.peirato.learning.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Classname MyClient
 * @Description TODO
 * @Date 2021/3/15 3:33 下午
 * @Created by yangzeqi
 */
public class MyClient {

    public static void main(String[] args) throws InterruptedException {

        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap
                    // 配置线程组
                    .group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    // 设置通道实现类型
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new MyClientHandler());
                        }
                    });
            System.out.println("客户端准备就绪");

            // 连接服务器
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1",8888).sync();
            // 对通道关闭进行监听
            channelFuture.channel().closeFuture().sync();

        } finally {
            // 关闭线程组
            eventExecutors.shutdownGracefully();

        }


    }
}
