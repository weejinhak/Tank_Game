
import java.io.*;
import java.net.Socket;

interface ReaderListener {
    void onUpdate(String uid, float x, float y, String direction, int team);
    void drawMap(String strings);
}

public class Client {

    private Socket socket;
    private OutputStream os;
    private DataOutputStream dos;
    private ReaderThread readerThread;
    private byte[] message;

    public void connected(ReaderListener readerListener) throws IOException {
        this.socket = new Socket("192.168.11.3", 5000);
        this.os = socket.getOutputStream();
        this.dos = new DataOutputStream(os);
        this.readerThread = new ReaderThread(socket.getInputStream(), readerListener);
        readerThread.start();
        System.out.println("connected");
    }

    public void setUserId(String uid) throws IOException {
        message = ("SET#" + uid).getBytes();
        dos.writeInt(message.length);
        for (int j = 0; j < message.length; ++j) {
            dos.write(message[j]);
        }
    }

    public void setMovePosition(String position) throws IOException {
        message = ("MOVE#" + position).getBytes();
        dos.writeInt(message.length);
        for (int j = 0; j < message.length; ++j) {
            dos.write(message[j]);
        }
    }

    public void getMapPosition(String map) throws IOException {
        message = map.getBytes();
        dos.writeInt(message.length);
        for (int j = 0; j < message.length; ++j) {
            dos.write(message[j]);
        }
    }

    public void setStop(String string) throws IOException {
        message = string.getBytes();
        dos.writeInt(message.length);
        for (int j = 0; j < message.length; ++j) {
            dos.write(message[j]);
        }
    }

}

class ReaderThread extends Thread {

    public static void readn(DataInputStream is, byte[] data, int size) throws IOException {
        int left = size;
        int offset = 0;

        while (left > 0) {
            int len = is.read(data, offset, left);
            left -= len;
            offset += len;
        }
    }

    private ReaderListener listener;
    private DataInputStream dis;

    public ReaderThread(InputStream inputStream, ReaderListener listener) {
        this.listener = listener;
        this.dis = new DataInputStream(inputStream);
    }

    @Override
    public void run() {
        byte[] data = new byte[8192];
        while (true) {
            try {
                int packetLen = dis.readInt();
                readn(dis, data, packetLen);
                String message = new String(data, 0, packetLen);
                String[] token = message.split("#");
                switch (token[0]) {
                    case "UPDATE":
                        String uid = token[1];
                        float x = Float.parseFloat(token[2]);
                        float y = Float.parseFloat(token[3]);
                        String direction = token[4];
                        int team = Integer.parseInt(token[5]);
                        listener.onUpdate(uid, x, y, direction, team);
                        break;
                    case "MAP":
                        String mapString = message.replace("MAP#", "");
                        listener.drawMap(mapString);
                        break;
                    case "GEN":

                        break;
                    case "MOVE":

                        break;
                    case "BULLET":

                        break;
                    case "DESTORY":

                        break;
                    case "DISCONNECT":

                        break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
