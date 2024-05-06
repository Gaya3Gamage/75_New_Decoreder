package com.decoder.decoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@Component
public class UDPMessageListener {

    private static final int PORT = 8055;

    private final MessageDecoder decoder;

    @Autowired
    public UDPMessageListener(MessageDecoder decoder) {
        this.decoder = decoder;
    }

    public void startListening() {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String message = new String(packet.getData(), 0, packet.getLength());


                decoder.decode(message);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


////FOR NETTY SERVER

//public class UDPMessageListener {
//
//    private static final int PORT = 8050;
//    private final Decoder decoder;
//
//    public UDPMessageListener(Decoder decoder) {
//        this.decoder = decoder;
//    }
//
//    public void startListening() {
//        EventLoopGroup group = new NioEventLoopGroup();
//        try {
//            Bootstrap b = new Bootstrap();
//            b.group(group)
//                    .channel(NioDatagramChannel.class)
//                    .handler(new ChannelInitializer<NioDatagramChannel>() {
//                        @Override
//                        protected void initChannel(NioDatagramChannel ch) {
//                            ch.pipeline().addLast(new UDPMessageHandler(decoder));
//                        }
//                    });
//
//            b.bind(PORT).sync().channel().closeFuture().sync();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            group.shutdownGracefully();
//        }
//    }
//}
