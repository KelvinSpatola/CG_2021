package furniture;

import geometry.Solid;
import geometry.Box;
import material.Materials;
import processing.core.*;
import static processing.core.PConstants.DEG_TO_RAD;

/**
 *
 * @author Kelvin Spátola
 */

public class Armario extends Furniture {
    private final Solid base, side, back, top,partition, shelfSmall;
    private final Solid door, doorHandle;
    private final Solid drawerBottom, drawerSide, drawerFront, drawerHandle;
    
    private float angLeft, angRight, angCenter;
    private boolean openDoorLeft, openDoorCenter, openDoorRight;
    private float vel1, vel2, vel3, vel4;
    private boolean openDrawers;
    
    
    // CONSTRUCTOR
    public Armario(PApplet p, float x, float y, float z){
        super(p, x, y, z);
        //                         X   Y   Z
        base         = new Box(p, 240, 70, 12);
        side         = new Box(p, 3, base.depth, 208);
        top          = new Box(p, base.width + side.width * 2, base.depth, 3);
        back         = new Box(p, base.width + side.width * 2, 3, base.height/2 + side.height + top.height);
        door         = new Box(p, base.width/4, 3, side.height - base.height/2);
        doorHandle   = new Box(p, 3, 3, 20);
        partition    = new Box(p, 3, base.depth - door.depth/2, side.height);
        shelfSmall   = new Box(p, base.width/4 - partition.width/2, base.depth - door.depth, 3);
        drawerBottom = new Box(p, base.width/4 - partition.width/2, base.depth - door.depth * 4, 3);
        drawerSide   = new Box(p, 3, drawerBottom.depth, 12);
        drawerFront  = new Box(p, drawerBottom.width, 3, drawerBottom.height + drawerSide.height);
        drawerHandle = new Box(p, 10, 2, 3);
        
        width  = back.width;
        height = back.height;
        depth  = base.depth + back.depth;
        
        PImage woodTex = p.loadImage("wood4.jpg");
        woodTex.resize(500, 0);
        
        glue(base, side, top, back, door, doorHandle, partition, shelfSmall, drawerBottom,
                drawerSide, drawerFront, drawerHandle);
        setTexture(woodTex ,width);
        setMaterial(Materials.WOOD);
    }
    
    
    @Override
    protected void buildFurniture(){
        assemble(base, 0, 0, 0);
        assemble(side, -base.width/2 - side.width/2, 0, base.height/2);
        assemble(side, base.width/2 + side.width/2, 0, base.height/2);
        assemble(top, 0, 0, side.height + top.height * 2);
        assemble(back, 0, -base.depth/2 - back.depth/2, 0);
        
        assemble(partition, -base.width/4, -door.depth/2, base.height/2);
        assemble(partition, +base.width/4, -door.depth/2, base.height/2);
        
        hingedDoor       (-base.width/2 + door.width/2, base.depth/2 - door.depth/2, base.height,  angLeft, false);
        rotoTranslateDoor(-base.width/4, base.depth/2 - door.depth/2, base.height, angCenter, false);
        rotoTranslateDoor( base.width/4, base.depth/2 - door.depth/2, base.height, angCenter, true);
        hingedDoor       ( base.width/4 + door.width/2, base.depth/2 - door.depth/2, base.height, -angRight, true);
        
        // LEFT SIDE
        drawer(-base.width/2 + drawerBottom.width/2, vel1, base.height);
        drawer(-base.width/2 + drawerBottom.width/2, vel2, base.height + drawerFront.height);
        drawer(-base.width/2 + drawerBottom.width/2, vel3, base.height + drawerFront.height * 2);
        drawer(-base.width/2 + drawerBottom.width/2, vel4, base.height + drawerFront.height * 3);
        
        assemble(shelfSmall, -base.width/2 + drawerBottom.width/2, 0, base.height + drawerFront.height * 4);
        assemble(shelfSmall, -base.width/2 + drawerBottom.width/2, 0, base.height + drawerFront.height * 7);
        assemble(shelfSmall, -base.width/2 + drawerBottom.width/2, 0, base.height + drawerFront.height * 10);
        
        // RIGHT SIDE
        drawer(+base.width/2 - drawerBottom.width/2, vel1, base.height);
        drawer(+base.width/2 - drawerBottom.width/2, vel2, base.height + drawerFront.height);
        drawer(+base.width/2 - drawerBottom.width/2, vel3, base.height + drawerFront.height * 2);
        drawer(+base.width/2 - drawerBottom.width/2, vel4, base.height + drawerFront.height * 3);
        
        assemble(shelfSmall, +base.width/2 - drawerBottom.width/2, 0, base.height + drawerFront.height * 4);
        assemble(shelfSmall, +base.width/2 - drawerBottom.width/2, 0, base.height + drawerFront.height * 7);
        assemble(shelfSmall, +base.width/2 - drawerBottom.width/2, 0, base.height + drawerFront.height * 10);
    }
    
    private void hingedDoor(float x, float y, float z, float angle, boolean invert){
        float offset = invert ? -door.width/2 : door.width/2;
        float handleOffset = invert ? -door.width/3 : door.width/3;
        
        p.push();
        p.translate(x - offset, y + door.depth/2, z);
        p.rotate(angle);
        assemble(door, offset, -door.depth/2, 0);
        assemble(doorHandle, offset + handleOffset, -door.depth/2 + doorHandle.depth, door.height/2);
        p.pop();
    }
    
    private void rotoTranslateDoor(float x, float y, float z, float angle, boolean invert){
        float invOffset = invert ? 1f : -1f;
        
        p.push();
        {
            p.translate(x, y + door.depth/2, z); // eixo 1 - canto da porta
            p.rotate(invert ? PApplet.PI - angle : angle); // rotação do eixo 1 - angulo = teta
            p.translate(door.width/4, 0, 0); // eixo 2 - 1/4 da largura da porta
            p.rotate(angle * 2f * invOffset); // rotação do eixo 2 - angulo = -teta * 2
            
            // "empurra" a porta mais 1/4 em direção do eixo 2, de forma a que o meio da largura coincida com o eixo 1
            p.translate(door.width/4, door.depth/2 * -invOffset, door.height/2);
            
            p.push();
            p.scale(1f, -invOffset, -invOffset);
            door.draw();
            assemble(doorHandle, door.width/3, doorHandle.depth, invert ? -doorHandle.height : 0);
            p.pop();
        }
        p.pop();
    }
    
    private void drawer(float x, float y, float z){
        p.push();
        p.translate(x, y, z);
        assemble(drawerBottom, 0, 0, 0);
        assemble(drawerSide, -drawerBottom.width/2 + drawerSide.width/2, 0, drawerBottom.height);
        assemble(drawerSide, +drawerBottom.width/2 - drawerSide.width/2, 0, drawerBottom.height);
        assemble(drawerFront, 0, -drawerBottom.depth/2 - drawerFront.depth/2, 0);
        assemble(drawerFront, 0, +drawerBottom.depth/2 + drawerFront.depth/2, 0);
        assemble(drawerHandle, 0, drawerBottom.depth/2 + drawerFront.depth + drawerHandle.depth/2, drawerFront.height/2);
        p.pop();
    }
    
    float doorVel1, doorVel2, doorVel3;
    
    public void update(){
        final float duration = 4f;
        final float maxAng1 = PApplet.radians(110);
        final float maxAng2 = PApplet.radians( 90);
        
        doorVel1 += 0.01f * (openDoorLeft ? 1f : -1f);
        doorVel2 += 0.01f * (openDoorCenter ? 1f : -1f);
        doorVel3 += 0.01f * (openDoorRight ? 1f : -1f);
        
        angLeft   += easeInOut(doorVel1, 0, maxAng1, duration);
        angCenter += easeInOut(doorVel2, 0, maxAng2, duration);
        angRight  += easeInOut(doorVel3, 0, maxAng1, duration);
        angLeft   = clamp(angLeft,   0, maxAng1);
        angCenter = clamp(angCenter, 0, maxAng2);
        angRight  = clamp(angRight,  0, maxAng1);
        
        float drawerSpeed = 0.75f;
        vel1 += drawerSpeed * (openDrawers ? 1f : -1f) * 1.0;
        vel2 += drawerSpeed * (openDrawers ? 1f : -1f) * 0.75;
        vel3 += drawerSpeed * (openDrawers ? 1f : -1f) * 0.5;
        vel4 += drawerSpeed * (openDrawers ? 1f : -1f) * 0.4;
        vel1 = clamp(vel1, 0, drawerBottom.depth);
        vel2 = clamp(vel2, 0, drawerBottom.depth);
        vel3 = clamp(vel3, 0, drawerBottom.depth);
        vel4 = clamp(vel4, 0, drawerBottom.depth);
    }
    
    public void interact(int door){
        switch(door){
            case 1:
                if(vel4 == 0) {
                    openDoorLeft = !openDoorLeft;
                    doorVel1 = 0;
                }
                break;
            case 2:
                openDoorCenter = !openDoorCenter;
                doorVel2 = 0;
                break;
            case 3:
                if(vel4 == 0) {
                    openDoorRight = !openDoorRight;
                    doorVel3 = 0;
                }
                break;
            case 4:
                if(angLeft == 110 * DEG_TO_RAD && angRight == 110 * DEG_TO_RAD){
                    openDrawers = !openDrawers;
                }
                break;
        }
    }
    
    private float clamp(float val, float min, float max) {
        if (val > max) return max;
        if (val < min) return min;
        return val;
    }
    
    private static float easeInOut (float t,float b , float c, float d) {
        if ((t/=d/2) < 1) return c/2*t*t*t*t*t + b;
        return c/2*((t-=2)*t*t*t*t + 2) + b;
    }
}