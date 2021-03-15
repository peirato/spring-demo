package com.peirato.learning.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Classname NettyBootstrap
 * @Description TODO
 * @Date 2021/3/15 2:12 下午
 * @Created by yangzeqi
 */
public class MyServer {


    public static void main(String[] args) throws InterruptedException {

        // 监听客户端请求
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        // 处理每条链接数据的读写
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();

        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    // 配置线程组
                    .group(bossGroup, workerGroup)
                    // 配置服务端的IO模型
                    .channel(NioServerSocketChannel.class)
                    // 设置线程队列的连接个数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 初始化通道对象 处理连接的读写逻辑
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                            nioSocketChannel.pipeline().addLast(new MyServerHandler() {
                            });
                        }
                    })
                    // 处理服务器启动中的逻辑
                    .handler(new ChannelInitializer<NioServerSocketChannel>() {
                        protected void initChannel(NioServerSocketChannel nioServerSocketChannel) throws Exception {
                            System.out.println("服务器启动中");
                        }
                    })
            ;

            // 绑定端口号，启动服务端
            ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();

            // 对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            // 关闭线程组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }


}
