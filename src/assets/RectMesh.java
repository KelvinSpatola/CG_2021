package assets;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;
/**
 *
 * @author kelvi
 */
public class RectMesh  implements PConstants {
    PApplet p;
    PShape tile, mesh;
    float xlength, ylength;
    int detail;
    
    public RectMesh(PApplet p, float xlength, float ylength, int detail) {
        this.p = p;
        this.xlength = xlength;
        this.ylength = ylength;
        this.detail = detail;
        
        tile = p.createShape();
        tile.beginShape();
        tile.textureMode(NORMAL);
        tile.texture(p.loadImage("tile2.jpg"));
        
        float w = xlength/detail;
        float h = ylength/detail;
        
        tile.vertex(0, 0, 0, 0);
        tile.vertex(0,  h, 0, 1);
        tile.vertex(w,  h, 1, 1);
        tile.vertex(w, 0, 1, 0);
        tile.endShape();
    }
    
    public void display() {
        float w = xlength/detail;
        float h = ylength/detail;
        for (float i = 0; i < detail; i++) {
            for (float j = 0; j < detail; j++) {
                p.push();
                p.translate(-xlength/2, -ylength/2);
                p.translate(i * w, j * h);
                p.shape(tile);
                p.pop();
            }
        }
    }
}
