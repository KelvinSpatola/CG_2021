package assets;

import processing.core.PApplet;
import static processing.core.PConstants.NORMAL;
import static processing.core.PConstants.REPEAT;
import processing.core.PImage;
import processing.core.PVector;

/**
 *
 * @author Kelvin Spátola
 */
public class SkyBox {
    private PApplet p;
    private PImage front, right, back, left, top, bottom;
    private float size;
    
    private final PVector a, b, c, d;
    private final PVector e, f, g, h;
    
    public SkyBox(PApplet p, float size){
        this.p = p;
        this.size = size;
        
        a = new PVector(-size/2, -size/2, size/2);
        b = new PVector(-size/2,  size/2, size/2);
        c = new PVector( size/2,  size/2, size/2);
        d = new PVector( size/2, -size/2, size/2);
        
        e = new PVector( size/2, -size/2, -size/2);
        f = new PVector( size/2,  size/2, -size/2);
        g = new PVector(-size/2,  size/2, -size/2);
        h = new PVector(-size/2, -size/2, -size/2);
        
    }
    
    public void draw(){
        p.push();
        p.textureMode(NORMAL);
        //p.textureWrap(REPEAT);
        
        //p.texture(texImg);
        
        
        p.pop();
    }
    
    public SkyBox setFront(String texPath) {
        front = p.loadImage(texPath);
        return this;
    }
    
    public SkyBox setRight(String texPath) {
        right = p.loadImage(texPath);
        return this;
    }
    
    public SkyBox setBack(String texPath) {
        back = p.loadImage(texPath);
        return this;
    }
    
    public SkyBox setLeft(String texPath) {
        left = p.loadImage(texPath);
        return this;
    }
    
    public SkyBox setTop(String texPath) {
        top = p.loadImage(texPath);
        return this;
    }
    
    public SkyBox setBottom(String texPath) {
        bottom = p.loadImage(texPath);
        return this;
    }
    
}
