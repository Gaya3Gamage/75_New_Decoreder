package com.decoder.decoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
//netty server client
public class UDPMessageSender {

    private static final String HOST = "localhost"; // Change to the appropriate host
    private static final int PORT = 8050; // Change to the appropriate port

    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .handler(new ChannelInitializer<NioDatagramChannel>() {
                        @Override
                        protected void initChannel(NioDatagramChannel ch) {
                            // No need to add any handlers for the sender
                        }
                    });

            ChannelFuture future = b.bind(0).sync();
            InetSocketAddress recipient = new InetSocketAddress(HOST, PORT);
            String message = "@LEN:0084;STA:00001;TM:10/02/2023,11:15:25;V:3.38;D:2;T:01;C:01;P01:000010001;K01:00;99#"; // Replace with your message

            // Encode the message as a DatagramPacket and send it
            future.channel().writeAndFlush(new DatagramPacket(
                            io.netty.buffer.Unpooled.copiedBuffer(message, CharsetUtil.UTF_8), recipient))
                    .sync();

            System.out.println("Message sent successfully.");

        } finally {
            group.shutdownGracefully();
        }
    }
}
