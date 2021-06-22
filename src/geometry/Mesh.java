package geometry;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;

/**
 *
 * @author Kelvin Spátola
 */
public class Mesh implements PConstants{
    private final PApplet p;
    private final float MIN_SIZE = 50f;
    private final float width, height;
    private PShape tile;
    
    public Mesh(PApplet p, float width, float height, int type){
        this.p = p;
        this.width = width;
        this.height = height;
        
        
        //Coordinate coord = new Coordinate(type, x, y);
        
        
        //vertex(coord.x, coord.y, coord.z);
        
        //shape = p.createShape();
        //shape.beginShape(QUADS);
        //for(float x = -width/2; x < width/2; x += MIN_SIZE){
        //    for(float y = -height/2; y < height/2; y += MIN_SIZE){
        //        shape.vertex(x, y, 0);
        //        shape.vertex(x, y + MIN_SIZE, 0);
        //        shape.vertex(x + MIN_SIZE, y + MIN_SIZE, 0);
        //        shape.vertex(x + MIN_SIZE, y, 0);
        //    }
        //}
        //shape.endShape();
        //PApplet.println(shape.getVertexCount());
    }
    
    public void draw(){
        p.beginShape(QUADS);
        for(float x = -width/2; x < width/2; x += MIN_SIZE){
            for(float y = -height/2; y < height/2; y += MIN_SIZE){
                p.vertex(x, y, 0);
                p.vertex(x, y + MIN_SIZE, 0);
                p.vertex(x + MIN_SIZE, y + MIN_SIZE, 0);
                p.vertex(x + MIN_SIZE, y, 0);
            }
        }
        p.endShape();
    }
    
    //public void draw(){
    //    p.shape(shape);
    //}
    
    private class Coordinate {
        public float c1, c2, c3;
        private float xx, yy, zz;
        int type;
        
        Coordinate(int type, float c1, float c2){
            
        }
    }
}