package geometry;

import material.Materials;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PShape;
import processing.core.PVector;

/**
 *
 * @author Kelvin Spátola
 */
public abstract class Solid implements PConstants {
    protected final PApplet p;
    protected PShape theShape;
    protected int material = -1;
    protected String tag = "solid";
    public float width, height, depth;
    
    
    // CONSTRUCTOR
    public Solid(PApplet p, float width, float depth, float height) {
        this.p = p;
        this.width  = width;
        this.depth  = depth;
        this.height = height;
    }
    
    protected abstract void buildShape();
    
    public abstract Solid applyTexture(float sizeReference, PImage... texImg);
    
    
    public void draw(){
        setLightMaterial();
        p.shape(theShape);
    }
    
    protected void vertex(float x, float y, float z){
        theShape.vertex(x, y, z);
    }
    
    protected void vertex(PVector pv){
        theShape.vertex(pv.x, pv.y, pv.z);
    }
    
    protected void vertex(PVector pv, float u, float v){
        theShape.vertex(pv.x, pv.y, pv.z, u, v);
    }
    
    protected void face(PVector... vertices){
        for(PVector v : vertices) theShape.vertex(v.x, v.y, v.z);
    }
    
    public float getMax(){
        return Math.max(width, Math.max(depth, height));
    }
    
    public Solid tag(String tag){
        this.tag = tag;
        return this;
    }
    
    public String tag(){
        return tag;
    }
    
    public Solid stroke(boolean hasStroke) {
        theShape.setStroke(hasStroke);
        return this;
    }
    
    public void stroke(int color) {
        theShape.setStroke(color);
    }
    
    public void fill(boolean hasFill) {
        theShape.setFill(hasFill);
    }
    
    public void fill(int color) {
        theShape.setFill(color);
    }
    
    public PShape getShape(){
        return theShape;
    }
    
    public Solid applyMaterial(int material){
        this.material = material;
        return this;
    }
    
//    public Solid applyMaterial(Materials customMaterial){
//        this.material = material;
//        return this;
//    }
    
    private void setLightMaterial(){
        if(material == -1) return;
        
        specular();
        shininess();
        diffuse();
        ambient();
    }
    
    private void ambient() {
        float[] val = Materials.getValues(material, Materials.Type.AMBIENT);
        theShape.setAmbient(p.color(val[0], val[1], val[2]));
    }
    
    private void diffuse() {
        float[] val = Materials.getValues(material, Materials.Type.DIFFUSE);
        theShape.setFill(p.color(val[0], val[1], val[2]));
    }
    
    private void specular() {
        float[] val = Materials.getValues(material, Materials.Type.SPECULAR);
        theShape.setSpecular(p.color(val[0], val[1], val[2]));
    }
    
    private void shininess() {
        float val = Materials.getValues(material, Materials.Type.SHININESS)[0];
        theShape.setShininess(val);
    }
    
    @Override
    public String toString(){
        return String.format("%s - width:%4.0f   depth:%4.0f   height:%4.0f   material: %s", 
                tag, width, depth, height, (material == -1) ? "none" : Materials.list()[material]);
    }
}
