import processing.core.PApplet;
import processing.event.KeyEvent;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;


public class Program extends PApplet implements ReaderListener {

    private ArrayList<Tank> myTank = new ArrayList<>();
    private ArrayList<Tank> otherTank = new ArrayList<>();
    private ArrayList<RenderObject> mapList = new ArrayList<>();
    private Client client;
    private Map map;
    private Tank tank;

    @Override
    public void settings() {
        this.size(800, 800);
        ResourceManager.init(this);
        ResourceManager.allocImages("tank", "src/images/tanks_image.png", 8, 4, 85, 85);
        client = new Client();
        try {
            client.connected(this);
            client.setUserId("Jinhak");
            client.getMapPosition("MAP");
        } catch (IOException e) {
            e.printStackTrace();
            exit();
        }
    }

    @Override
    public void setup() {
        super.setup();
        tank = new Tank(this);
        myTank.add(tank);
    }


    public static void main(String[] args) {
        PApplet.main("Program");
    }


    @Override
    public void onUpdate(String uid, float x, float y, String direction, int team) {
        Tank tank = new Tank(uid, this, x, y);
        tank.setMode(tank.getDirectionToInt(direction));
        tank.setTeam(team);
        otherTank.add(tank);
    }

    @Override
    public void drawMap(String mapString) {
        String[] mapSplit = mapString.split("\n");
        for (int i = 0; i < mapSplit.length; i++) {
            for (int j = 0; j < mapSplit[i].length(); j++) {
                map = new Map(j * 40, i * 40, Character.getNumericValue(mapSplit[i].charAt(j)), this);
                mapList.add(map);
            }
        }
    }


    @Override
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case 37:
                    tank.setMode(Tank.TANK_LEFT);
                    tank.update();
                try {
                    client.setMovePosition("LEFT");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 38:
                tank.setMode(Tank.TANK_UP);
                tank.update();
                try {
                    client.setMovePosition("UP");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 39:
                tank.setMode(Tank.TANK_RIGHT);
                tank.update();
                try {
                    client.setMovePosition("RIGHT");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 40:
                tank.setMode(Tank.TANK_DOWN);
                tank.update();
                try {
                    client.setMovePosition("DOWN");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent event) {
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case 37:
                tank.setMode(Tank.TANK_STOP);
                tank.update();
                try {
                    client.setStop("STOP");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 38:
                tank.setMode(Tank.TANK_STOP);
                tank.update();
                try {
                    client.setStop("STOP");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 39:
                tank.setMode(Tank.TANK_STOP);
                tank.update();
                try {
                    client.setStop("STOP");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 40:
                tank.setMode(Tank.TANK_STOP);
                tank.update();
                try {
                    client.setStop("STOP");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

        }

    }

    @Override
    public void draw() {
        background(00, 99, 66);

        for (RenderObject m : mapList) {
            m.render();
        }
        System.out.println(mapList);

        for (RenderObject r : myTank) {
            r.update();
        }
        for (RenderObject r : myTank) {
            r.render();
        }

/*
        for (RenderObject r : otherTank) {
            r.update();
        }
        for (RenderObject r : otherTank) {
            r.render();
        }*/

    }


}

