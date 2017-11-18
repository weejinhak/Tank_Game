import processing.core.PApplet;

public class Bullet extends RenderObject {

    String bulletID;

    Bullet(PApplet pApplet){
        super(pApplet);

    }
    Bullet(String bulletID, int posX, int posY, String direction, PApplet pApplet){
        super(pApplet);
        setBulletID(bulletID);
    }

    public String getBulletID() {
        return bulletID;
    }

    public void setBulletID(String bulletID) {
        this.bulletID = bulletID;
    }

    @Override
    public void update(){
    }

    @Override
    public void render(){
    }

}
