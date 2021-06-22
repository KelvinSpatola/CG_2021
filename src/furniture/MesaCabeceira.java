package furniture;

import geometry.Solid;
import geometry.Box;
import material.Materials;
import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author Kelvin Spátola
 */
public class MesaCabeceira extends Furniture {
    private final Solid perna, tampo;
    
    
    // CONSTRUCTOR
    public MesaCabeceira(PApplet p, float x, float y, float z){
        super(p, x, y, z);
        //                 X   Y   Z
        perna = new Box(p, 5, 5, 100);
        tampo = new Box(p, 180, 120, 5);
        
        width  = tampo.width;
        height = perna.height + tampo.height;
        depth  = tampo.depth;
        
        PImage woodTex = p.loadImage("wood.jpg");
        woodTex.resize(500, 0);
        
        glue(perna, tampo);
        setTexture(woodTex ,width);
        setMaterial(Materials.WOOD);
    }
    
    @Override
    protected void buildFurniture(){
        assemble(perna, -tampo.width/2 + perna.width, -tampo.depth/2 + perna.depth, 0);
        assemble(perna, +tampo.width/2 - perna.width, -tampo.depth/2 + perna.depth, 0);
        assemble(perna, -tampo.width/2 + perna.width, +tampo.depth/2 - perna.depth, 0);
        assemble(perna, +tampo.width/2 - perna.width, +tampo.depth/2 - perna.depth, 0);
        assemble(tampo, 0, 0, perna.height);
    }
}
