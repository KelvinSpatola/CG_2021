package Testes;

/**
 *
 * @author Kelvin Spátola (Ov3rM1nD_)
 */

import processing.core.*;
import camera.Camera;
import assets.World;
import geometry.*;
import light.*;
import material.*;
import java.util.ArrayList;


public class Testes extends PApplet{
    
    public static void main(String[] args) {
        PApplet.main(Testes.class);
    }
    
    public void settings() {
        //size((int)(displayWidth * 0.6), (int)(displayHeight * 0.6), P3D);
        fullScreen(P3D);
    }
    
    Camera cam;
    World world;
    Solid box, cuboid, cylinder;
    ArrayList<Solid> solids = new ArrayList();
    PImage tex1, tex2, tex3, tex4;
    Flashlight flashlight;
    
    public void setup(){
        colorMode(RGB, 1);
        
        cam = new Camera(this, 300);
        world = new World(this, cam);
        tex1 = loadImage("mesh.jpg");
        tex1.resize(500, 0);
        tex2 = loadImage("wood4.jpg");
        tex2.resize(500, 0);
        tex3 = loadImage("steel.jpg");
        tex3.resize(500, 0);
        tex4 = loadImage("steel_circle.jpg");
        tex4.resize(500, 0);
        
        box = new Box(this, 100, 300, 100).applyMaterial(Materials.WOOD);
        cuboid = new RoundCuboid(this, 100, 10, 300, 5, 12);//.applyMaterial(Materials.COPPER);
        cylinder = new Cylinder(this, 75, 200, 30).applyMaterial(Materials.GOLD);
                
        solids.add(box);
        solids.add(cuboid);
        solids.add(cylinder);
        //setTexture(tex2);
        solids.forEach(s -> println(s));
       // exit();
        
        flashlight = new Flashlight(this, color(1));
        
        //printArray(Materials.list());
    }
    
    public void draw() {
        background(0);
        
        world.axis();
        //cam.draw();
        //PVector p = PVector.sub(cam.getLookAt(), cam.getPosition());
        //flashlight.setPosition(cam.getPosition()).setDirection(p).render();
        
        float a = frameCount;
        //float z = a * 2;
        
        //spotLight(255, 0, 0, -300, 0, z, 0, 0, -1, QUARTER_PI, 500);
        //spotLight(0, 255, 0,    0, 0, z, 0, 0, -1, QUARTER_PI, 500);
        //spotLight(0, 0, 255,  300, 0, z, 0, 0, -1, QUARTER_PI, 500);
        
        ambientLight(0.2f, 0.2f, 0.2f);
        
        //pointLight(1, 1, 1, 0, 0, 300);
        directionalLight(1, 1, 1, 0, 0, -1);
        lightSpecular(1, 1, 1);
        //pointLight(1, 1, 1, 0, 0, 300);
        directionalLight(1, 1, 1, 0, 0, -1);
        lightSpecular(1, 1, 1);

        
        push();
        translate(-300, 0, 0);
        rotate(a * -0.001f, a * 0.001f, a * -0.002f);
        box.draw();
        pop();
        
        push();
        translate(0, 0, 0);
        rotate(a * 0.002f, a * -0.001f, a * 0.001f);
        cuboid.draw();
        pop();
        
        push();
        translate(300, 0, 0);
        rotate(a * 0.001f, a * -0.002f, a * -0.001f);
        cylinder.draw();
        pop();
    }
    
    public void keyPressed(){
        if(key == '1') noTex();
        if(key == '2') setTexture(tex1);
        if(key == '3') setTexture(tex2);
        if(key == '4') setTexture(tex3);
        if(key == 's') toggleStroke();
        if(key == 'f') toggleFill();
        if(key == 'w') wireframe();
    }
    
    public void noTex(){
        solids.clear();
        solids = new ArrayList();
        
        box = new Box(this, 100, 300, 100).applyMaterial(Materials.WOOD);
        cuboid = new RoundCuboid(this, 200, 100, 200, 40, 16).applyMaterial(Materials.WOOD);
        cylinder = new Cylinder(this, 75, 200, 30).applyMaterial(Materials.WOOD);
        
        solids.add(box);
        solids.add(cuboid);
        solids.add(cylinder);
    }
    
    public void setTexture(PImage tex){
        solids.forEach(s -> s.applyTexture(250, tex));
    }
    
//    public void setTexture(PImage tex1, PImage tex2){
//        solids.forEach(s -> s.applyTexture(250, tex1, tex2));
//    }
    
    boolean stroke, fill;
    public void toggleStroke(){
        stroke = !stroke;
        solids.forEach(s -> s.stroke(stroke));
    }
    
    public void toggleFill(){
        fill = !fill;
        solids.forEach(s -> s.fill(fill));
    }
    
    public void wireframe(){
        noTex();
        solids.forEach(s -> s.fill(false));
    }
    
    void rotate(float x, float y, float z){
        rotateX(x);
        rotateY(y);
        rotateZ(z);
    }
}
