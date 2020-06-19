package src;

import java.util.HashMap;
import java.util.function.Function;

public class RequestHandler extends Thread {

    SocketIO sio;
    HashMap<String, Function<String, String>> events;

    /**
     * RequestHandlerのコンストラクタ
     *
     * @param sio SocketIO
     */
    public RequestHandler(SocketIO sio, HashMap<String, Function<String, String>> events) {
        this.sio = sio;
        this.events = events;
    }

    /**
     * Thread.run
     */
    public void run() {
        while(true) {
            String rcvMsg = sio.read();
            if(rcvMsg == null || rcvMsg.equals("bye")) {      // byeコマンドだけは特別に定義する
                sio.close();
                break;
            }
            execCommand(rcvMsg);
        }
    }

    /**
     * クライアントから受信した文字列をもとに処理を行う
     */
    private void execCommand(String command) {
        Function<String, String> event = events.get(command);
        if(event == null) {
            sio.write("Your message length is " + command.length());
        } else {
            sio.write(event.apply(command));
        }
    }

}
