package com.decoder.decoder;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//public class UDPClient {
//
//    public static void main(String[] args) {
//        String message = "@LEN:0149;STB:00001;TM:14/02/2023,13:35:00;V:3.45;D:1;T:06;C:04;P01:-99999999|-99999999|-99999999|-99999999|000014834|000014834;K01:99|99|99|99|00|00;4C#";
//        try (DatagramSocket socket = new DatagramSocket()) {
//            byte[] data = message.getBytes();
//            InetAddress address = InetAddress.getByName("localhost"); // Use the appropriate IP address
//            int port = 8050; // Use the appropriate port
//            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
//            socket.send(packet);
//            System.out.println("Message sent successfully.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}

//TESTING THE UDPMESSAGELISTNER

public class UDPClient {

    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getLocalHost();
            int port = 8055;

            String[] messages = {
                    "@LEN:0084;STA:00001;TM:10/02/2023,11:15:25;V:3.38;D:2;T:01;C:01;P01:000010001;K01:00;99#",
                    "@LEN:0267;STB:00001;TM:16/03/2023,18:30:00;V:7.30;D:3;T:04;C:95;A02:00000|00000|00000|00000;A04:7.304|7.306|7.307|7.307;P01:000001058|000001058|000001058|000001058;P02:000000007|000000007|000000007|000000007;P03:000001058|000001058|000001058|000001058;K01:00|00|00;3E#"
            };

            try (DatagramSocket socket = new DatagramSocket()) {

                for (String message : messages) {
                    byte[] data = message.getBytes();

                    DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
                    socket.send(packet);

                    System.out.println("Message sent successfully: " + message);

                    Thread.sleep(1000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
