import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.HashMap;

public class ResourceManager {

    private static PApplet pApplet;
    private static HashMap<String, ArrayList<PImage>> images = new HashMap<>();

    public ResourceManager(PApplet p) {
        pApplet = p;
    }

    static void init(PApplet p) {
        pApplet = p;
    }

    static void allocImages(String resourceName, String path, int x, int y, int width, int height) {
        ArrayList<PImage> imageList = new ArrayList<>();
        PImage pImage = pApplet.loadImage(path);
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                imageList.add(pImage.get(width * i, height * j, width, height));
            }
        }
        images.put(resourceName, imageList);
    }

    static PImage getImage(String resourceName, int index) {
        return images.get(resourceName).get(index);
    }

}
