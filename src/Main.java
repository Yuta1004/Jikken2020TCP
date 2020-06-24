package src;

import java.util.Scanner;

public class Main {

    private static Server server;

    /**
     * Mainメソッド
     *
     * @param args
     */
    public static void main(String[] args) {
        // サーバ初期化 -> 起動
        server = new Server(50000);
        server.addEvent("rabbit", (msg) -> { return "Is the order a rabbit?"; });
        server.addEvent("nityc", (msg) -> { return "National Institute of Technology, Yuge College"; });
        server.start();

        // CUI
        try(Scanner scanner = new Scanner(System.in)) {
            while(true) {
                System.out.print("> ");
                execCommand(scanner.nextLine().split(" "));
                System.out.println();
            }
        }
    }

    /**
     * コマンドを実行する
     *
     * @param command
     */
    private static void execCommand(String command[]) {
        // status: ステータス表示
        if(command[0].equals("status")) {
            System.out.println("- Listen Address : " + server.ssocket.getInetAddress());
            System.out.println("- Listen port    : " + server.ssocket.getLocalPort());
            System.out.println("- Socket Status  : " + !server.ssocket.isClosed());
            return;
        }

        // event: イベント操作系コマンド
        if(command[0].equals("event")) {
            // add <trigger> <response> イベント追加
            if(command[1].equals("add")) {
                if(command.length >= 4) {
                    server.addEvent(command[2], (msg) -> { return command[3]; });
                    System.out.println("Successfuly added!");
                } else {
                    System.out.println("usage: event add <trigger> <response>");
                }
                return;
            }

            // list: 登録済みイベント表示
            if(command[1].equals("list")) {
                for(String trigger: server.getEventList()) {
                    System.out.println("- " + trigger);
                }
                return;
            }
        }

        // bye: 終了
        if(command[0].equals("bye")) {
            System.out.println("bye!");
            System.exit(0);
            return;
        }

        // not found
        System.out.println("command not found...");
    }

}