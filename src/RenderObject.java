import processing.core.PApplet;
import processing.core.PImage;

import java.util.HashMap;

public class RenderObject {
    float x, y;
    int mode;
    HashMap<Integer, PImage[]> renderImages = new HashMap<>();
    private PApplet pApplet;

    RenderObject(PApplet p) {
        this.pApplet = p;
    }

    public void allocMode(int mode, String resourceName, int[] indecies) {

        PImage[] resource = new PImage[indecies.length];

        for (int i = 0; i < indecies.length; i++) {
            resource[i] = ResourceManager.getImage(resourceName, indecies[i]);
        }

        renderImages.put(mode, resource);
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public void update(){}

    int tick = 0;
    public void render() {
        tick++;
        PImage[] images = renderImages.get(this.mode);
        int frame = tick++ / 10 % images.length;
        pApplet.image(images[frame], x, y);
    }
}