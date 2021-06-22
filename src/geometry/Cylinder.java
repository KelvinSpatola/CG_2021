package geometry;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;
import static processing.core.PApplet.map;

/**
 *
 * @author Kelvin Spátola
 */

public final class Cylinder extends Solid {
    private PShape top, bottom, body;
    private final int detail;
    private int vertexCount;
    
    public Cylinder(PApplet p, float radius, float height, int detail){
        super(p, radius, radius, height);
        this.detail = detail;
        tag("cylinder");
        
        buildShape();
    }
    
    
    @Override
    protected void buildShape() {
        theShape = p.createShape(GROUP);
        top      = p.createShape();
        bottom   = p.createShape();
        body     = p.createShape();
        
        top.beginShape();
        bottom.beginShape();
        body.beginShape(QUAD_STRIP);
        
        final float angle = TWO_PI / detail;
        
        for (int i = 0; i <= detail; i++) {
            float x = width * PApplet.cos(i * angle);
            float y = depth * PApplet.sin(i * angle);
            
            top.vertex   (x, y, +height/2);
            bottom.vertex(x, y, -height/2);
            
            body.vertex(x, y, +height/2);
            body.vertex(x, y, -height/2);
            vertexCount++;
        }
        
        top.endShape();
        bottom.endShape();
        body.endShape();
        
        top.width = width * 2;
        top.height = height * 2;
        
        theShape.addChild(top);
        theShape.addChild(bottom);
        theShape.addChild(body);
    }
    
    @Override
    public Solid applyTexture(final float sizeReference, final PImage... texImg) {
        theShape.setTextureMode(NORMAL);
        p.textureWrap(REPEAT);
        
        final float normalizedUV = texImg[0].width / sizeReference;
        float u, v, maxU, maxV;
        
        /*
        * TOP & BOTTOM
        */
        top.setTexture(texImg[0]);
        bottom.setTexture(texImg[0]);
        
        maxU = map(width, 0, sizeReference, 0, normalizedUV);
        maxV = map(depth, 0, sizeReference, 0, normalizedUV);
        
        for (int i = 0; i < vertexCount; i++) {
            u = map(top.getVertexX(i), -width/2, width/2, 0, maxU); // we can use top or bottom shape here...
            v = map(top.getVertexY(i), -depth/2, depth/2, 0, maxV);
            top.setTextureUV(i, u, v);
            bottom.setTextureUV(i, u, v);
        }
        
        /*
        * BODY
        */
        body.setTexture(texImg[0]); // [1]
        
        final float perimeter = TWO_PI * width;
        maxU = map(perimeter, 0, sizeReference, 0, normalizedUV);
        maxV = map(   height, 0, sizeReference, 0, normalizedUV);
        
        for (int i = 0; i < vertexCount * 2; i += 2) {
            u = map(i, 0, vertexCount * 2, maxU, 0); // this must inverted in order to map correctly
            body.setTextureUV(  i, u, 0);
            body.setTextureUV(i+1, u, maxV);
        }
        return this;
    }
}