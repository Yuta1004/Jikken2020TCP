package src;

public class SimpleMain {

    /**
     * Mainメソッド
     *
     * @param args
     */
    public static void main(String[] args) {
        // サーバ初期化 -> 起動
        Server server = new Server(6000);
        server.start();
    }

}