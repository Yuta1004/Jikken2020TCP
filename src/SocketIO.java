package src;

import java.net.Socket;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class SocketIO {

    /* ソケット情報ほか */
    private Socket sc;
    private BufferedReader reader;
    private PrintWriter writer;

    /**
     * SocketWriterのコンストラクタ
     */
    public SocketIO(Socket sc) {
        this.sc = sc;
        try {
            reader = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sc.getOutputStream())));
        } catch (IOException e) {
            System.err.println("[Error] new BufferedReader, PrintWriter");
            System.err.println(e);
        }
    }

    /**
     * 読み
     *
     * @return String
     */
    public String read() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.err.println("[Error] reader.readLine");
            System.err.println(e);
        }
        return "";
    }

    /**
     * 書き
     *
     * @param msg メッセージ
     */
    public void write(String msg) {
        writer.println(msg);
        writer.flush();
    }

    /**
     * ソケットを閉じる
     */
    public void close() {
        try {
            sc.close();
        } catch (IOException e){
            System.err.println("[Error] sc.close");
            System.err.println(e);
        }
    }

}