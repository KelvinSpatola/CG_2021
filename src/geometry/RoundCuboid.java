package geometry;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;
import static processing.core.PApplet.map;

/**
 *
 * @author Kelvin Spátola
 */

public final class RoundCuboid extends Solid {
    private PShape top, bottom, body;
    private final int radius, detail;
    private int vertexCount;
    
    public RoundCuboid(PApplet p, float width, float depth, float height, int radius, int detail) {
        super(p, width, depth, height);
        this.radius = radius;
        this.detail = detail;
        tag("round cuboid");
        
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
        
        float woff = +(width/2 - radius);
        float doff = -(depth/2 - radius);
        float hoff =  height/2;
        float angle = TWO_PI / detail;
        
        for (int i = 0; i <= detail; i++) {
            float x = radius * PApplet.cos(i * angle - HALF_PI) + woff;
            float y = radius * PApplet.sin(i * angle - HALF_PI) + doff;
            
            top.vertex(x, y, hoff);
            bottom.vertex(x, y, -hoff);
            body.vertex(x, y, +hoff);
            body.vertex(x, y, -hoff);
            vertexCount++;
            
            if (i != 0) {
                if (i % (detail/2) == 0) {
                    woff = -woff;
                    
                    top.vertex(x + woff*2, y, hoff);
                    bottom.vertex(x + woff*2, y, -hoff);
                    body.vertex(x + woff*2, y, +hoff);
                    body.vertex(x + woff*2, y, -hoff);
                    vertexCount++;
                } else if (i % (detail/4) == 0) {
                    doff = -doff;
                    
                    top.vertex(x, y + doff*2, hoff);
                    bottom.vertex(x, y + doff*2, -hoff);
                    body.vertex(x, y + doff*2, +hoff);
                    body.vertex(x, y + doff*2, -hoff);
                    vertexCount++;
                }
            }
        }
        top.endShape(CLOSE);
        bottom.endShape(CLOSE);
        body.endShape();
        
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
            u = map(top.getVertexX(i), -width/2, width/2, 0, maxU);
            v = map(top.getVertexY(i), -depth/2, depth/2, 0, maxV);
            top.setTextureUV(i, u, v);
            
            u = map(bottom.getVertexX(i), -width/2, width/2, 0, maxU);
            v = map(bottom.getVertexY(i), -depth/2, depth/2, 0, maxV);
            bottom.setTextureUV(i, u, v);
        }
        
        /*
        * BODY
        */
        body.setTexture(texImg[0]); // [1]
        
        maxU = map(Math.max(width, depth), 0, sizeReference, 0, normalizedUV);
        maxV = map(height, 0, sizeReference, 0, normalizedUV);
        u = 0;
        
        float cx, cy, px, py; // current vertice, previous vertice
        float len = 0; // the distance between the current and the previous vertice
        
        for (int i = 0; i < vertexCount * 2; i += 2) {
            if(i != 0) {
                cx = body.getVertexX(i);
                cy = body.getVertexY(i);
                px = body.getVertexX(i-2);
                py = body.getVertexY(i-2);
                len = PApplet.dist(cx, cy, px, py);
            }
            u += map(len, 0, sizeReference, 0, maxU);
            body.setTextureUV(  i, -u, 0); // this must inverted in order to map correctly
            body.setTextureUV(i+1, -u, maxV); // this must inverted in order to map correctly
        }
        return this;
    }
}