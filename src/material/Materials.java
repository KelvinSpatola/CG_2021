package material;

import java.lang.reflect.Field;

/**
 *
 * @author Kelvin Spátola
 */

public class Materials {
    public static final int
            BRASS  = 0,
            BRONZE = 1,
            POLISHED_BRONZE = 2,
            CHROME = 3,
            COPPER = 4,
            POLISHED_COPPER = 5,
            GOLD = 6,
            POLISHED_GOLD = 7,
            TIN = 8,
            SILVER = 9,
            POLISHED_SILVER = 10,
            EMERALD = 11,
            JADE = 12,
            OBSIDIAN = 13,
            PERL = 14,
            RUBY = 15,
            TURQUOISE  = 16,
            BLACK_PLASTIC = 17,
            CYAN_PLASTIC = 18,
            GREEN_PLASTIC = 19,
            RED_PLASTIC = 20,
            WHITE_PLASTIC = 21,
            YELLOW_PLASTIC = 22,
            BLACK_RUBBER = 23,
            CYAN_RUBBER = 24,
            GREEN_RUBBER = 25,
            RED_RUBBER = 26,
            WHITE_RUBBER = 27,
            YELLOW_RUBBER = 28,
            BRIGHT_WHITE = 29,
            LESS_BRIGHT_WHITE = 30,
            WARM_WHITE = 31,
            WOOD = 32;
    
    private static final float[] ambient = {
        0.329412f, 0.223529f, 0.027451f, 1.0f, //BRASS = 0;
        0.2125f, 0.1275f, 0.054f, 1.0f, //BRONZE = 1;
        0.25f, 0.148f, 0.06475f, 1.0f, //POLISHED_BRONZE = 2;
        0.25f, 0.25f, 0.25f, 1.0f, //CHROME = 3;
        0.19125f, 0.0735f, 0.0225f, 1.0f, //CUPPER = 4;
        0.2295f, 0.08825f, 0.0275f, 1.0f, //POLISHED_CUPPER = 5;
        0.24725f, 0.1995f, 0.0745f, 1.0f, //GOLD = 6;
        0.24725f, 0.2245f, 0.0645f, 1.0f, //POLISHED_GOLD = 7;
        0.105882f, 0.058824f, 0.113725f, 1.0f, //TIN = 8;
        0.19225f, 0.19225f, 0.19225f, 1.0f, //SILVER = 9;
        0.23125f, 0.23125f, 0.23125f, 1.0f, //POLISHED_SILVER = 10;
        0.0215f, 0.1745f, 0.0215f, 0.55f, //EMERALD = 11;
        0.135f, 0.2225f, 0.1575f, 0.95f, //JADE = 12;
        0.05375f, 0.05f, 0.06625f, 0.82f, //OBSIDIAN = 13;
        0.25f, 0.20725f, 0.20725f, 0.922f, //PERL = 14;
        0.1745f, 0.01175f, 0.01175f, 0.55f, //RUBY = 15;
        0.1f, 0.18725f, 0.1745f, 0.8f, //TURQUOISE = 16;
        0.0f, 0.0f, 0.0f, 1.0f, //BLACK_PLASTIC = 17;
        0.0f, 0.1f, 0.06f, 1.0f, //CYAN_PLASTIC = 18;
        0.0f, 0.0f, 0.0f, 1.0f, //GREEN_PLASTIC = 19;
        0.0f, 0.0f, 0.0f, 1.0f, //RED_PLASTIC = 20;
        0.0f, 0.0f, 0.0f, 1.0f, //WHITE_PLASTIC = 21;
        0.0f, 0.0f, 0.0f, 1.0f, //YELLOW_PLASTIC = 22;
        0.02f, 0.02f, 0.02f, 1.0f, //BLACK_RUBBER = 23;
        0.0f, 0.05f, 0.05f, 1.0f, //CYAN_RUBBER = 24;
        0.0f, 0.05f, 0.0f, 1.0f, //GREEN_RUBBER = 25;
        0.05f, 0.0f, 0.0f, 1.0f, //RED_RUBBER = 26;
        0.05f, 0.05f, 0.05f, 1.0f, //WHITE_RUBBER = 27;
        0.05f, 0.05f, 0.0f, 1.0f, //YELLOW_RUBBER = 28;
        0.2f, 0.2f, 0.2f, 1.0f, //BRIGHT_WHITE = 29;
        0.2f, 0.2f, 0.2f, 1.0f, //LESS_BRIGHT_WHITE = 30;
        0.3f, 0.2f, 0.2f, 1.0f, //WARM_WHITE = 31;
        0.86f, 0.65f, 0.29f, 1.0f //WOOD = 32
        
    };
    
    private static final float[] diffuse = {
        0.780392f, 0.568627f, 0.113725f, 1.0f, //BRASS = 0;
        0.714f, 0.4284f, 0.18144f, 1.0f, //BRONZE = 1;
        0.4f, 0.2368f, 0.1036f, 1.0f, //POLISHED_BRONZE = 2;
        0.4f, 0.4f, 0.4f, 1.0f, //CHROME = 3;
        0.7038f, 0.27048f, 0.0828f, 1.0f, //COPPER = 4;
        0.5508f, 0.2118f, 0.066f, 1.0f, //POLISHED_COPPER = 5;
        0.75164f, 0.60648f, 0.22648f, 1.0f, //GOLD = 6;
        0.34615f, 0.3143f, 0.0903f, 1.0f, //POLISHED_GOLD = 7;
        0.427451f, 0.470588f, 0.541176f, 1.0f, //TIN = 8;
        0.50754f, 0.50754f, 0.50754f, 1.0f, //SILVER = 9;
        0.2775f, 0.2775f, 0.2775f, 1.0f, //POLISHED_SILVER = 10;
        0.07568f, 0.61424f, 0.07568f, 0.55f, //EMERALD = 11;
        0.54f, 0.89f, 0.63f, 0.95f, //JADE = 12;
        0.18275f, 0.17f, 0.22525f, 0.82f, //OBSIDIAN = 13;
        1.0f, 0.829f, 0.829f, 0.922f, //PERL = 14;
        0.61424f, 0.04136f, 0.04136f, 0.55f, //RUBY = 15;
        0.396f, 0.74151f, 0.69102f, 0.8f, //TURQUOISE = 16;
        0.01f, 0.01f, 0.01f, 1.0f, //BLACK_PLASTIC = 17;
        0.0f, 0.50980392f, 0.50980392f, 1.0f, //CYAN_PLASTIC = 18;
        0.1f, 0.35f, 0.1f, 1.0f, //GREEN_PLASTIC = 19;
        0.5f, 0.0f, 0.0f, 1.0f, //RED_PLASTIC = 20;
        0.55f, 0.55f, 0.55f, 1.0f, //WHITE_PLASTIC = 21;
        0.5f, 0.5f, 0.0f, 1.0f, //YELLOW_PLASTIC = 22;
        0.01f, 0.01f, 0.01f, 1.0f, //BLACK_RUBBER = 23;
        0.4f, 0.5f, 0.5f, 1.0f, //CYAN_RUBBER = 24;
        0.4f, 0.5f, 0.4f, 1.0f, //GREEN_RUBBER = 25;
        0.5f, 0.4f, 0.4f, 1.0f, //RED_RUBBER = 26;
        0.5f, 0.5f, 0.5f, 1.0f, //WHITE_RUBBER = 27;
        0.5f, 0.5f, 0.4f, 1.0f, //YELLOW_RUBBER = 28;
        1.0f, 1.0f, 1.0f, 1.0f, //BRIGHT_WHITE = 29;
        0.8f, 0.8f, 0.8f, 1.0f, //LESS_BRIGHT_WHITE = 30;
        1.0f, 0.9f, 0.8f, 1.0f, //WARM_WHITE = 31;
        0.29f, 0.33f, 0.41f, 1.0f //WOOD = 32
    };
    
    private static final float[] specular = {
        0.992157f, 0.941176f, 0.807843f, 1.0f, //BRASS = 0;
        0.393548f, 0.271906f, 0.166721f, 1.0f, //BRONZE = 1;
        0.774597f, 0.458561f, 0.200621f, 1.0f, //POLISHED_BRONZE = 2;
        0.774597f, 0.774597f, 0.774597f, 1.0f, //CHROME = 3;
        0.256777f, 0.137622f, 0.086014f, 1.0f, //CUPPER = 4;
        0.580594f, 0.223257f, 0.0695701f, 1.0f, //POLISHED_CUPPER = 5;
        0.628281f, 0.555802f, 0.366065f, 1.0f, //GOLD = 6;
        0.797357f, 0.723991f, 0.208006f, 1.0f, //POLISHED_GOLD = 7;
        0.333333f, 0.333333f, 0.521569f, 1.0f, //TIN = 8;
        0.508273f, 0.508273f, 0.508273f, 1.0f, //SILVER = 9;
        0.773911f, 0.773911f, 0.773911f, 1.0f, //POLISHED_SILVER = 10;
        0.633f, 0.727811f, 0.633f, 0.55f, //EMERALD = 11;
        0.316228f, 0.316228f, 0.316228f, 0.95f, //JADE = 12;
        0.332741f, 0.328634f, 0.346435f, 0.82f, //OBSIDIAN = 13;
        0.296648f, 0.296648f, 0.296648f, 0.922f, //PERL = 14;
        0.727811f, 0.626959f, 0.626959f, 0.55f, //RUBY = 15;
        0.297254f, 0.30829f, 0.306678f, 0.8f, //TURQUOISE = 16;
        0.50f, 0.50f, 0.50f, 1.0f, //BLACK_PLASTIC = 17;
        0.50196078f, 0.50196078f, 0.50196078f, 1.0f, //CYAN_PLASTIC = 18;
        0.45f, 0.55f, 0.45f, 1.0f, //GREEN_PLASTIC = 19;
        0.7f, 0.6f, 0.6f, 1.0f, //RED_PLASTIC = 20;
        0.70f, 0.70f, 0.70f, 1.0f, //WHITE_PLASTIC = 21;
        0.60f, 0.60f, 0.50f, 1.0f, //YELLOW_PLASTIC = 22;
        0.4f, 0.4f, 0.4f, 1.0f, //BLACK_RUBBER = 23;
        0.04f, 0.7f, 0.7f, 1.0f, //CYAN_RUBBER = 24;
        0.04f, 0.7f, 0.04f, 1.0f, //GREEN_RUBBER = 25;
        0.7f, 0.04f, 0.04f, 1.0f, //RED_RUBBER = 26;
        0.7f, 0.7f, 0.7f, 1.0f, //WHITE_RUBBER = 27;
        0.7f, 0.7f, 0.04f, 1.0f, //YELLOW_RUBBER = 28;
        0.8f, 0.8f, 0.8f, 1.0f, //BRIGHT_WHITE = 29;
        0.5f, 0.5f, 0.5f, 1.0f, //LESS_BRIGHT_WHITE = 30;
        0.2f, 0.2f, 0.2f, 1.0f, //WARM_WHITE = 31;
        0.08f,0.11f,0.13f,1.0f //WOOD = 32
    };
    
    private static final float[] shine = {
        27.8974f, //BRASS = 0;
        25.6f, //BRONZE = 1;
        76.8f, //POLISHED_BRONZE = 2;
        76.8f, //CHROME = 3;
        12.8f, //CUPPER = 4;
        51.2f, //POLISHED_CUPPER = 5;
        51.2f, //GOLD = 6;
        83.2f, //POLISHED_GOLD = 7;
        9.84615f, //TIN = 8;
        51.2f, //SILVER = 9;
        89.6f, //POLISHED_SILVER = 10;
        76.8f, //EMERALD = 11;
        12.8f, //JADE = 12;
        38.4f, //OBSIDIAN = 13;
        11.264f, //PERL = 14;
        76.8f, //RUBY = 15;
        12.8f, //TURQUOISE = 16;
        32.0f, //BLACK_PLASTIC = 17;
        32.0f, //CYAN_PLASTIC = 18;
        32.0f, //GREEN_PLASTIC = 19;
        32.0f, //RED_PLASTIC = 20;
        32.0f, //WHITE_PLASTIC = 21;
        32.0f, //YELLOW_PLASTIC = 22;
        10.0f, //BLACK_RUBBER = 23;
        10.0f, //CYAN_RUBBER = 24;
        10.0f, //GREEN_RUBBER = 25;
        10.0f, //RED_RUBBER = 26;
        10.0f, //WHITE_RUBBER = 27;
        10.0f, //YELLOW_RUBBER = 28;
        0.4f, //BRIGHT_WHITE = 29;
        0.35f, //LESS_BRIGHT_WHITE = 30;
        0.35f, //WARM_WHITE = 31;
        23f //WOOD = 32
    };
    
    public enum Type {
        AMBIENT, DIFFUSE, SPECULAR, SHININESS
    };
    
    /**
     * Return the spec for a material
     * @param mat The material we want
     * @param type The spec, must be AMBIENT, DIFFUSE, SPECULAR, SHININESS
     * @return an array of floats, or null if noe material found or wrong spec
     */
    static public float[] getValues(int mat, Type type) {
        switch(type) {
            case AMBIENT:
                return new float[]{ ambient[mat*4], ambient[mat*4+1], ambient[mat*4+2], ambient[mat*4+3] };
            case DIFFUSE:
                return new float[]{ diffuse[mat*4], diffuse[mat*4+1], diffuse[mat*4+2], diffuse[mat*4+3] };
            case SPECULAR:
                return new float[]{ specular[mat*4], specular[mat*4+1], specular[mat*4+2], specular[mat*4+3] };
            case SHININESS:
                return new float[]{ shine[mat] };
        }
        return null;
    }
    
    public static String[] list() {
        String[] materialNames = null;
        try {
            Class thisClass = Class.forName(Materials.class.getName());
            Field[] fields = thisClass.getFields();
            materialNames = new String[fields.length];
            
            for (int i = 0; i < fields.length; i++)
                materialNames[i] = fields[i].getName();
        }
        catch(ClassNotFoundException | SecurityException e) {
            System.err.println(e);
        }
        return materialNames;
    }
    
    /*
    private float[] customAmb, customDiff, customSpec, customShine;
    
    private Materials(){
        customAmb = new float[3];
        customDiff = new float[3];
        customSpec = new float[3];
        customShine = new float[1];
    }
    
    public Materials setAmbient(float r, float g, float b){
        customDiff[0] = r;
        customDiff[1] = g;
        customDiff[2] = b;
        return this;
    }
    
    public Materials setDiffuse(float r, float g, float b){
        customSpec[0] = r;
        customSpec[1] = g;
        customSpec[2] = b;
        return this;
    }
    
    public Materials setSpecular(float r, float g, float b){
        customAmb[0] = r;
        customAmb[1] = g;
        customAmb[2] = b;
        return this;
    }
    
    public Materials setShininess(float val){
        customShine[0] = val;
        return this;
    }
    
    public static Materials make(){
        return new Materials();
    }
*/
}
