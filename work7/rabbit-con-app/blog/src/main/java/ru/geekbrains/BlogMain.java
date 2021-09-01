package ru.geekbrains;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BlogMain {
    private static final String EXCHANGE_NAME = "directExchanger";

    public static void main(String[] argv) throws Exception {
        new Producer().randomSend();
    }
    private static class Producer {

        Channel channel;

        int msgId = 1;
        String[] tags = {"php", "java", "pyton"};
        Random random = new Random();

        public Producer() {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            try {
                Connection connection = factory.newConnection();
                channel = connection.createChannel();
                channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            } catch (Exception e) {
                e.printStackTrace();
                channel = null;
            }
        }

        public void randomSend() {
            if (channel == null) {
                return;
            }
            try {
                while (true) {
                    String tag = tags[random.nextInt(tags.length)];
                    String message = String.format("message %d: Hello World!", msgId++);
                    send(tag, message);
                    TimeUnit.SECONDS.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void send(String tag, String message) {
            if (channel == null) {
                return;
            }
            try {
                channel.basicPublish(EXCHANGE_NAME, tag, null, message.getBytes("UTF-8"));
                System.out.printf("Sent tag[%s] '%s'%n", tag, message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
