package furniture;

import java.util.ArrayList;
import java.util.Collections;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import geometry.Solid;


/**
 *
 * @author Kelvin Spátola
 */

public abstract class Furniture {
    protected final PApplet p;
    protected final ArrayList<Solid> parts = new ArrayList();
    protected PVector centerMass, location;
    public float width, depth, height;
    
    
    
    // CONSTRUCTOR
    public Furniture(PApplet p, float x, float y, float z){
        this.p = p;
        this.location = new PVector(x, y, z);
    }
    
    protected abstract void buildFurniture();
    
    
    public void draw(){
        p.push();
        p.translate(location.x, location.y, location.z);
        buildFurniture();
        p.pop();
    }
    
    public void update(){ }
    
    protected void assemble(Solid solid, float x, float y, float z){
        p.push();
        p.translate(x, y, solid.height/2 + z);
        solid.draw();
        p.pop();
    }
    
    protected final void glue(Solid... pieces){
        Collections.addAll(parts, pieces);
    }
    
    protected final void setTexture(PImage tex, float sizeReference){
        parts.forEach(s -> s.applyTexture(sizeReference, tex));
    }
    
    protected final void setMaterial(int material){
        parts.forEach(s -> s.applyMaterial(material));
    }
    
    protected void noTexture(){ }
    
    public void wireframe(){
        noTexture();
        parts.forEach(s -> s.fill(false));
    }
    
    public void stroke(boolean state){
        parts.forEach(s -> s.stroke(state));
    }
    
    public PVector getPosition(){
        return location;
    }
    
    public PVector getCenterMass(){
        return new PVector(location.x, location.y, location.z + height/2);
    }
}
