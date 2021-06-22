package light;

import processing.core.PApplet;
import processing.core.PVector;

/**
 *
 * @author Kelvin Spátola
 */
public class Flashlight extends Light {
    private final float angle = (float) Math.PI/4;
    private final float concentration = 500;
    private PVector direction = new PVector();
    
    public Flashlight(PApplet p, int color){
        super(p, color);
    }

    @Override
    public void render() {
        p.spotLight(r, g, b, pos.x, pos.y, pos.y, direction.x, direction.y, direction.z, angle, concentration);
    }
    
    @Override
    public Light setDirection(PVector target){
        direction.set(target);
        return this;
    }
    
}
