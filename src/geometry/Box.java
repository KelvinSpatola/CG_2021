package geometry;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import static processing.core.PApplet.map;

/**
 *
 * @author Kelvin Spátola
 */

public final class Box extends Solid {
    public final PVector a, b, c, d;
    public final PVector e, f, g, h;
    
    
    // CONSTRUCTOR
    public Box(PApplet p, float width, float depth, float height) {
        super(p, width, depth, height);
        tag("box");
        
        a = new PVector(-width/2, -depth/2, height/2);
        b = new PVector(-width/2,  depth/2, height/2);
        c = new PVector( width/2,  depth/2, height/2);
        d = new PVector( width/2, -depth/2, height/2);
        
        e = new PVector( width/2, -depth/2, -height/2);
        f = new PVector( width/2,  depth/2, -height/2);
        g = new PVector(-width/2,  depth/2, -height/2);
        h = new PVector(-width/2, -depth/2, -height/2);
        
        buildShape();
    }
    
    @Override
    protected void buildShape(){
        theShape = p.createShape();
        theShape.beginShape(QUADS);
        face(b, c, d, a); // top (a > b > c > d)
        face(f, g, h, e); // (bottom e > f > g > h)
        face(b, g, f, c); // front
        face(d, e, h, a); // back
        face(a, h, g, b); // left
        face(c, f, e, d); // right
        theShape.endShape(CLOSE);
    }
    
    @Override
    public Box applyTexture(final float sizeReference, final PImage... texImg) {
        
        final float normalizedUV = (texImg[0].width / sizeReference);
        //float utb = PApplet.map( width, 0, maxSizeReference, 0, normalizedUV); // top & bottom
        //float vtb = PApplet.map( depth, 0, maxSizeReference, 0, normalizedUV); // top & bottom
        float utb = map( depth, 0, sizeReference, 0, normalizedUV); // top & bottom
        float vtb = map( width, 0, sizeReference, 0, normalizedUV); // top & bottom
        float ufb = map( width, 0, sizeReference, 0, normalizedUV); // front & back
        float vfb = map(height, 0, sizeReference, 0, normalizedUV); // front & back
        float ulr = map( depth, 0, sizeReference, 0, normalizedUV); // left & right
        float vlr = map(height, 0, sizeReference, 0, normalizedUV); // left & right
        
        theShape.setTexture(texImg[0]);
        theShape.setTextureMode(NORMAL);
        p.textureWrap(REPEAT);

        // top
        theShape.setTextureUV(0, 0, 0);
        theShape.setTextureUV(1, 0, vtb);
        theShape.setTextureUV(2, utb, vtb);
        theShape.setTextureUV(3, utb, 0);
        // bottom
        theShape.setTextureUV(4, 0, 0);
        theShape.setTextureUV(5, 0, vtb);
        theShape.setTextureUV(6, utb, vtb);
        theShape.setTextureUV(7, utb, 0);
        // front
        theShape.setTextureUV(8, 0, 0);
        theShape.setTextureUV(9, 0, vfb);
        theShape.setTextureUV(10, ufb, vfb);
        theShape.setTextureUV(11, ufb, 0);
        // back
        theShape.setTextureUV(12, 0, 0);
        theShape.setTextureUV(13, 0, vfb);
        theShape.setTextureUV(14, ufb, vfb);
        theShape.setTextureUV(15, ufb, 0);
        // left
        theShape.setTextureUV(16, 0, 0);
        theShape.setTextureUV(17, 0, vlr);
        theShape.setTextureUV(18, ulr, vlr);
        theShape.setTextureUV(19, ulr, 0);
        // right
        theShape.setTextureUV(20, 0, 0);
        theShape.setTextureUV(21, 0, vlr);
        theShape.setTextureUV(22, ulr, vlr);
        theShape.setTextureUV(23, ulr, 0);
        
        return this;
    }
}