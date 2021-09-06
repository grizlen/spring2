package ru.geekbrains;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;

public class BlogReceiver {
    private final Connection connection;
    private final HashMap<String, BlogChannel> channels;

    public BlogReceiver() throws Exception {
        ConnectionFactory factory;
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
        channels = new HashMap<>();
    }

    public void subscribe(String tag){
        BlogChannel channel = channels.get(tag);
        if (channel == null) {
            try {
                channel = new BlogChannel(tag);
                channels.put(tag, channel);
                channel.start();
                System.out.println("subscribe to " + tag);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void unSubscribe(String tag) {
        BlogChannel channel = channels.get(tag);
        if (channel != null) {
            channel.close();
            channels.remove(tag);
            System.out.println("unsubscribe from " + tag);
        }
    }

    public void listTags() {
        String list = channels.entrySet().stream()
                .map(e -> e.getKey())
                .collect(Collectors.joining(", ", "", ";"));
        System.out.println("Subscribed to: " + list);
    }


    private class BlogChannel {
        private String tag;
        private Channel channel;
        String queueName;

        public BlogChannel(String tag) throws IOException {
            this.tag = tag;
            channel = connection.createChannel();
            channel.exchangeDeclare(Client.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, Client.EXCHANGE_NAME, tag);
        }

        private DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.printf("Received tag[%s] message: '%s'%n", tag, message);
        };

        public void start () throws IOException {
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
        }

        public void close() {
            try {
                channel.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
