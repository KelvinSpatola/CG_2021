package assets;

import static processing.core.PApplet.lerp;
import static processing.core.PApplet.map;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import processing.core.PFont;
import camera.Camera;


/**
 *
 * @author Kelvin Spátola
 */

public class World implements PConstants{
    private final PApplet p;
    private final Camera cam;
    PFont axisFont;
    float world;
    public boolean rotateSun = true;
    RectMesh floor;
    
    public World(PApplet p, Camera cam){
        this.p = p;
        this.cam = cam;
        axisFont = p.createFont("Consolas bold", 100);
        p.registerMethod("draw", this);
        floor = new RectMesh(p, 1000, 1000, 20);
    }
    
    public void draw(){
        world = PVector.dist(cam.getPosition(), new PVector());
        if(world < 500f) world = 500;
    }
    
    public void skybox(){
        //p.colorMode(RGB, 1);
        p.push();
        PVector c = PVector.lerp(cam.getPosition(), new PVector(0, 0, 0), 0.5f);
        p.translate(c.x, c.y, c.z);
        p.scale(1, -1);
        p.fill(255);
        p.noStroke();
        p.sphereDetail(30);
        p.sphere(world * 2);
        p.pop();
        //p.colorMode(RGB, 255);
    }
    
    float step;
    public void sun() {
        //p.colorMode(RGB, 1);
        p.ambientLight(64, 64, 64);
        
        p.push();
        float worldRotatio = map(step % 1000, 0, 1000, 0f, TWO_PI);
        p.rotateX(worldRotatio);
        p.directionalLight(255, 255, 255, 0, 0, -1);
        p.lightSpecular(255, 255, 255);
        p.pop();
        //p.colorMode(RGB, 255);
        
        step += rotateSun ? 0.75 : 0;
        rotateSun = true;
    }
    
    public void stopSun(){
        rotateSun = false;
    }
    
    /*
    public void ground(){
    p.colorMode(RGB, 1);
    p.push();
    p.fill(0.5f);
    p.rectMode(CENTER);
    p.noStroke();
    p.square(0, 0, world * 4);
    p.pop();
    p.colorMode(RGB, 255);
    }
    */
    
    public void ground(){
        floor.display();
    }
    
    public void floor() {
        p.colorMode(RGB, 1);
        p.push();
        p.strokeWeight(0.5f);
        p.stroke(1);
        
        int unit = 20;
        int total = 30;
        float w = unit * total;
        
        for (int i = 0; i <= total; i++) {
            if(i == total/2) continue;
            p.line(-w/2, -w/2 + unit * i, 0, w/2, -w/2 + unit * i, 0); // paralelas ao eixo X
            p.line(-w/2 + unit * i, -w/2, 0, -w/2 + unit * i, w/2, 0); // paralelas ao eixo Y
        }
        p.pop();
        p.colorMode(RGB, 255);
    }
    
    public void axis() {
        float length = 300;
        int numSegments = 20;
        //p.colorMode(RGB, 255);
        p.push();
        p.textFont(axisFont);
        p.textSize(30);
        p.textAlign(CENTER, CENTER);
        p.strokeWeight(2f);
        p.noLights();
        
        p.stroke(255, 0, 0);
        p.line(0, 0, 0, length, 0, 0);
        dashedLine(-length, 0, 0, 0, 0, 0, numSegments);
        p.fill(255, 100, 100);
        axisTag('X', length + 20, 0, 0);
        
        p.stroke(0, 255, 0);
        p.line(0, 0, 0, 0, length, 0);
        dashedLine(0, -length, 0, 0, 0, 0, numSegments);
        p.fill(150, 255, 150);
        axisTag('Y', 0, length + 20, 0);
        
        p.stroke(50, 50, 255);
        p.line(0, 0, 0, 0, 0, length);
        dashedLine(0, 0, -length, 0, 0, 0, numSegments);
        p.fill(100, 100, 255);
        axisTag('Z', 0, 0, length + 20);
        p.pop();
        //p.colorMode(RGB, 1);
    }
    
    private void axisTag(char axis, float x, float y, float z){
        p.hint(DISABLE_DEPTH_TEST);
        p.push();
        p.translate(x, y, z);
        p.textSize(map(cam.camRadius, 10, 30000, 30, 300));
        cam.beginFacingScreen();
        p.text(axis, 0, 0, 0);
        cam.endFacingScreen();
        p.pop();
        p.hint(ENABLE_DEPTH_TEST);
    }
    
    private void dashedLine(float x1, float y1, float z1, float x2, float y2, float z2, int numSegments) {
        int total = numSegments * 2;
        float[]x = new float[total];
        float[]y = new float[total];
        float[]z = new float[total];
        
        for (int i = 0; i < total; i++) {
            x[i] = lerp(x1, x2, i / (float) total);
            y[i] = lerp(y1, y2, i / (float) total);
            z[i] = lerp(z1, z2, i / (float) total);
        }
        
        p.pushStyle();
        p.strokeCap(PROJECT);
        for (int i = 1; i < total; i += 2)
            p.line(x[i-1], y[i-1], z[i-1], x[i], y[i], z[i]);
        p.popStyle();
    }
}
