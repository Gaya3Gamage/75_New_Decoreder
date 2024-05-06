package com.decoder.decoder;




import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.DatagramPacket;
import java.nio.charset.StandardCharsets;

public class UDPMessageHandler extends ChannelInboundHandlerAdapter {

    private final Decoder decoder;

    public UDPMessageHandler(Decoder decoder) {
        this.decoder = decoder;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        DatagramPacket packet = (DatagramPacket) msg;
        String message = packet.content().toString(StandardCharsets.UTF_8);
        System.out.println("Received message: " + message);
        // Decode the message using the provided decoder
        decoder.decode(message);
        // You can add your message processing logic here
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
