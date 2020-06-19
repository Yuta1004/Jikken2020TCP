package src;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class Client {

    public static void main(String args[]) throws IOException {
        // 接続情報入力受け付け
        String hostname = "localhost";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Host-name (localhost): ");
        hostname = scanner.nextLine();

        // コネクション確立
        Socket socket = new Socket(hostname, 6000);

        // Reader, Write初期化
        BufferedReader brsrv = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pwsrv = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

        // 通信
        while(true){
            System.out.print("> ");
            String inp = scanner.nextLine();
            if(inp.equals("bye")) {
                break;
            }
            pwsrv.println(inp);
            pwsrv.flush();
            System.out.println(brsrv.readLine());
        }

        // 各種close処理
        pwsrv.close();
        brsrv.close();
        socket.close();
        scanner.close();
    }

}