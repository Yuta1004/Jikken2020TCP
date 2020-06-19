package src;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
import java.util.HashMap;
import java.util.function.Function;

public class Server extends Thread {

    /* サーバ情報 */
    public final ServerSocket ssocket;

    /* イベント情報 */
    private HashMap<String, Function<String, String>> events;

    /**
     * Serverのコンストラクタ
     *
     * @param port ポート番号
     */
    public Server(int port) {
        ServerSocket tmp = null;
        try {
            tmp = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("[Error] new ServerSocket");
            System.err.println(e);
            System.exit(0);
        }
        ssocket = tmp;
        events = new HashMap<String, Function<String, String>>();
    }

    /**
     * Thread.run
     */
    public void run() {
        while(true) {
            RequestHandler handler = new RequestHandler(waitRequest(), events);
            handler.start();
        }
    }

    /**
     *  イベント追加
     */
    public void addEvent(String name, Function<String, String> func) {
        events.put(name, func);
    }

    /**
     * 登録済みイベントの一覧を返す
     *
     * @return String[]
     */
    public String[] getEventList() {
        return events.keySet().toArray(new String[events.size()]);
    }

    /**
     * 接続要求を待つ
     */
    private SocketIO waitRequest() {
        Socket sc;
        try {
            sc = ssocket.accept();
        } catch (IOException e) {
            System.err.println("[Error] ssocket.accept");
            System.err.println(e);
            return null;
        }
        return new SocketIO(sc);    // SocketIOでラップして返す
    }

}
