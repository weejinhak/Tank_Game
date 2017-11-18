import processing.core.PApplet;

public class Map extends RenderObject {

    final static int BREAKABLE = 1;
    final static int HOLLOW = 0;

    Map(int posX, int posY, int mode, PApplet pApplet) {
        super(pApplet);
        this.x = posX;
        this.y = posY;
        this.setMode(mode);
        this.allocMode(HOLLOW, "tank", new int[]{0});
        this.allocMode(BREAKABLE, "tank", new int[]{25});
    }
}