package ru.geekbrains;

import java.util.Scanner;

public class Client {

    static final String EXCHANGE_NAME = "directExchanger";

    public static void main(String[] argv) throws Exception {
        printHelp();
        BlogReceiver blogReceiver = new BlogReceiver();
        blogReceiver.subscribe("php");
//        blogReceiver.subscribe("java");
//        blogReceiver.unSubscribe("java");
        System.out.println("Waiting for messages");
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String[] cmd = scanner.nextLine().split(" ", 2);
                if (cmd.length == 2) {
                    if (cmd[0].equals("subs") || cmd[0].equals("s")) {
                        blogReceiver.subscribe(cmd[1]);
                    } else if (cmd[0].equals("unsubs") || cmd[0].equals("u")) {
                        blogReceiver.unSubscribe(cmd[1]);
                    } else {
                        printHelp();
                    }
                } else if (cmd.length == 1) {
                    if (cmd[0].equals("tags") || cmd[0].equals("t")) {
                        blogReceiver.listTags();
                    } else {
                        printHelp();
                    }
                }
            }
        }).start();
    }

    private static void printHelp() {
        System.out.println("commands:");
        System.out.println("\tsubs tag | s tag");
        System.out.println("\tunsubs tag | u tag");
        System.out.println("\ttags | t");
    }
}
