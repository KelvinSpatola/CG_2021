package light;

import processing.core.PApplet;
import processing.core.PVector;

/**
 *
 * @author Kelvin Spátola
 */

public abstract class Light {
    protected PApplet p;
    protected PVector pos = new PVector();
    public int r, g, b;
    
    public Light(PApplet p, int color){
        this.p = p;
        this.r = (color >> 16) & 0xFF;
        this.g = (color >>  8) & 0xFF;
        this.b =  color & 0xFF;
    }
    
    public Light setPosition(PVector where){
        pos.set(where);
        return this;
    }
    
    public Light setPosition(float x, float y, float z){
        pos.set(x, y, z);
        return this;
    }
    
    public abstract void render();
    
    public abstract Light setDirection(PVector target);
}
