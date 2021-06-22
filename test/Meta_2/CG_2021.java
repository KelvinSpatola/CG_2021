package Meta_2;

import processing.core.*;
import camera.Camera;
import assets.World;
import java.util.Locale;
import kelvinclark.utils.SpriteSheet;
import kelvinclark.utils.Toggle;
import furniture.*;
import geometry.*;


/**
 *
 * @author Kelvin Spátola
 */
public class CG_2021 extends PApplet {
    
    public static void main(String[] args) {
        PApplet.main(CG_2021.class);
    }
    
    SpriteSheet sp;
    Toggle toggle;
    
    World world;
    PFont hudFont;
    
    Camera cam;
    Furniture armario, mesaCabeceira;
    Solid candle;
    
    int pointRed  = 1;
    int pointGreen= 1;
    int pointBlue = 1;
    
    boolean grounded;
    
    public void settings(){
        fullScreen(P3D);
        //size((int)(displayWidth * 0.6), (int)(displayHeight * 0.6), P3D);
    }
    
    public void setup(){
        hudFont  = createFont("Consolas", 20);
        
        armario = new Armario(this, 0, 0, 0);
        mesaCabeceira = new MesaCabeceira(this, 0, 250, 0);
        candle = new Cylinder(this, 2, 30, 30)/*.applyMaterial(Materials.WHITE_PLASTIC)*/.stroke(false);
        
        
        PVector center = PVector.lerp(armario.getCenterMass(), mesaCabeceira.getCenterMass(), 0.5f);
        cam = new Camera(this, 300)
                //.clampPolar(DEG_TO_RAD, PI - DEG_TO_RAD)
                .setLookAt(center)
                .setCameraView(Camera.View.FRONT);
        
        world = new World(this, cam);
        
        sp = new SpriteSheet(this, "candle_spritesheet.png", 3, 8);
        sp.resizeSprites(210, 0);
        sp.setFrameRate(4);
        
        setupToggle();
    }
    
    public void draw(){
        background(0);
        
        PVector p = mesaCabeceira.getPosition();
        //h = map(mouseX, 0, width, 0, 500);
        
        //lightSpecular(1f, 1f, 1f);
        toggle.get("sun");
        toggle.get("skybox");
        pointLight(pointRed*255, pointGreen*255, pointBlue*255, p.x, p.y, p.z + mesaCabeceira.height + h);
        
        world.ground();
        armario.update();
        armario.draw();
        candle();
        mesaCabeceira.draw();
        
        checkGroundHit();
        if(keyPressed) {
            if (key == CODED && keyCode == UP) h += 5;
            if (key == CODED && keyCode == DOWN) h -= 5;
        }
    }
    
    void candle(){
        push();
        PVector p = mesaCabeceira.getPosition();
        translate(p.x, p.y, p.z + mesaCabeceira.height + candle.height/2 + h - 65);
        
        emissive(255);
        candle.draw();
        if(!(pointRed==0 & pointGreen==0 & pointBlue==0)){
            translate(0, 0, candle.height);
            
            cam.beginFacingScreen();
            sp.animateSpriteSheet();
            
            beginShape();
            noStroke();
            texture(sp.render());
            textureMode(NORMAL);
            
            float size = 20;
            vertex(-size/2, -size/2, 0, 0);
            vertex(-size/2, size/2, 0, 1);
            vertex( size/2, size/2, 1, 1);
            vertex( size/2, -size/2, 1, 0);
            endShape(CLOSE);
            cam.endFacingScreen();
        }
        pop();
        
        emissive(0);
        //lightSpecular(0f, 0f, 0f);
        //toggle.get("sun");
    }
    
    float h = 65;
    public void keyPressed(){
        Armario a = (Armario) armario;
        if(key == '1') a.interact(1); // porta esquerda
        if(key == '2') a.interact(2); // portas do centro
        if(key == '3') a.interact(3); // portda direita
        if(key == '4') a.interact(4); // gavetas

        if ((key == 'r') || (key == 'R')) pointRed   = (pointRed + 1) % 2;
        if ((key == 'g') || (key == 'G')) pointGreen = (pointGreen + 1) % 2;
        if ((key == 'b') || (key == 'B')) pointBlue  = (pointBlue + 1) % 2;
    }
    
    public void drawHUD(){
        String screenText
                = "fps:         %.1f\n"
                + "polar:      % .1f\n"
                + "azimuth:    % .1f\n"
                + "view:        %s\n"
                + "FOV:         %.1f\n"
                + "camRadius:   %.1f\n"
                + "lookAt:     % .1f, %.1f, %.1f\n"
                + "view height:% .1f\n\n"
                + "PRESS '1' open\\close left door\n"
                + "PRESS '3' open\\close right door\n"
                + "PRESS '2' open\\close central doors\n"
                + "PRESS '4' open\\close drawers after\n"
                + "PRESS 'r' to reset\n"
                + "PRESS 'h' to disable HUD\n"
                + "PRESS 's' to stop sun rotation"
                ;
        
        String result = String.format(Locale.ENGLISH, screenText,
                frameRate,
                cam.getPolar() * RAD_TO_DEG,
                cam.getAzimuth() * RAD_TO_DEG,
                cam.getView(),
                cam.getFov() * RAD_TO_DEG,
                cam.getRadius(),
                cam.getLookAt().x, cam.getLookAt().y, cam.getLookAt().z,
                cam.getPosition().z
        );
        
        push();
        lights();
        translate(cam.getLookAt().x, cam.getLookAt().y, cam.getLookAt().z);
        fill(255, 255, 0, 100);
        noStroke();
        sphere(10);
        pop();
        
        cam.beginHUD();
        noStroke();
        fill(0, 200);
        rect(0, 0, 430, 450);
        textFont(hudFont);
        fill(255, 0, 0);
        text(result, 0, 30);
        cam.endHUD();
    }
    
    void checkGroundHit(){
        float distToGround = cam.getPosition().z;
        float minDist = map(cam.camRadius, 1f, cam.far_clipping_plane, 10f, 50f);
        
        if(distToGround <= minDist) {
            cam.getPosition().z = minDist;
            
            float currentPolar = cam.getPolar();
            cam.clampPolar(DEG_TO_RAD, currentPolar);
        } else {
            cam.clampPolar(DEG_TO_RAD, PI - DEG_TO_RAD);
        }
    }
    
    boolean firstTime = true;
    void setupToggle(){
        if(firstTime) {
            firstTime = false;
            
            toggle = new Toggle(this)
                    .set('c', world, "skybox", false)
                    .set('S', world, "stopSun", true)
                    .set('s', world, "sun", false)
                    //.set('G', world, "ground", true)
                    //.set('g', world, "floor", false)
                    .set('a', world, "axis", true)
                    .set('h', "drawHUD", false)
                    //.set('n', armario, "noStroke")
                    //.set('N', armario, "noFill")
                    ;
        }
    }
}