import processing.core.PApplet;

public class Tank extends RenderObject {

    public final static int TANK_LEFT = 0;
    public final static int TANK_RIGHT = 1;
    public final static int TANK_UP = 2;
    public final static int TANK_DOWN = 3;
    public final static int TANK_STOP = 4;

    public final static String LEFT = "LEFT";
    public final static String UP = "UP";
    public final static String RIGHT = "RIGHT";
    public final static String DOWN = "DOWN";
    public final static String STOP = "STOP";

    private String uid;
    private int team;

    Tank(PApplet pApplet) {
        super(pApplet);
        allocMode(TANK_UP, "tank", new int[]{8, 7, 6, 5, 4, 3, 2, 1});
        allocMode(TANK_DOWN, "tank", new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        allocMode(TANK_STOP, "tank", new int[]{1});
        setMode(TANK_STOP);
    }

    Tank(String playerID, PApplet pApplet, float x, float y) {
        super(pApplet);
        allocMode(TANK_UP, "tank", new int[]{8, 7, 6, 5, 4, 3, 2, 1});
        allocMode(TANK_DOWN, "tank", new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        allocMode(TANK_STOP, "tank", new int[]{1});
        this.setMode(TANK_STOP);
        this.uid = playerID;
        this.x = x;
        this.y = y;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }


    @Override
    public void setMode(int mode) {
        super.setMode(mode);
    }

    @Override
    public void update() {

    }

    public int getDirectionToInt(String direction) {
        if (direction.equals("UP")) {
            return TANK_UP;
        } else if (direction.equals("DOWN")) {
            return TANK_DOWN;
        } else if (direction.equals("LEFT")) {
            return TANK_LEFT;
        } else
            return TANK_RIGHT;
    }

}

