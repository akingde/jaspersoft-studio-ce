package com.jaspersoft.studio.property.color;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.type.ColorEnum;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.wb.swt.ResourceManager;

/**
 *
 * @author gtoffoli
 */
public class ColorSchemaGenerator {

    public final static String SCHEMA_DEFAULT = "default";
    public final static String SCHEMA_PASTEL = "pastel";
    public final static String SCHEMA_SOFT = "soft";
    public final static String SCHEMA_HARD = "hard";
    public final static String SCHEMA_LIGHT = "light";
    public final static String SCHEMA_PALE = "pale";
    
    private static float[] schema_default = new float[]{ -1f,-1f, 1f,-0.7f, 0.25f,1f, 0.5f,1f };
    private static float[] schema_pastel = new float[]{ 0.5f,-0.9f, 0.5f,0.5f, 0.1f,0.9f, 0.75f,0.75f };
    private static float[] schema_soft = new float[]{ 0.3f,-0.8f, 0.3f,0.5f, 0.1f,0.9f, 0.5f,0.75f };
    private static float[] schema_hard = new float[]{ 1f,-1f, 1f,-0.6f, 0.1f,1f, 0.6f,1f };
    private static float[] schema_light = new float[]{ 0.25f,1f, 0.5f,0.75f, 0.1f,1f, 0.5f,1f };
    private static float[] schema_pale = new float[]{ 0.1f,-0.85f, 0.1f,0.5f, 0.1f,1f, 0.1f,0.75f };
    
    private static java.util.Map<String, float[]> schemas = new HashMap<String, float[]>();
    
    static {
        
        schemas.put(SCHEMA_DEFAULT, schema_default);
        schemas.put(SCHEMA_PASTEL, schema_pastel);
        schemas.put(SCHEMA_SOFT, schema_soft);
        schemas.put(SCHEMA_HARD, schema_hard);
        schemas.put(SCHEMA_LIGHT, schema_light);
        schemas.put(SCHEMA_PALE, schema_pale);
        
    }
    
    /**
     * Create the schema color.
     * 
     * @param base
     * @param i (a color between 0 and 3)
     * @param schemaName
     * @return
     */
    public static Color createColor(Color base, int i, String schemaName)
    {
        
        i = Math.abs(i %= 3); 
        if (schemaName == null) schemaName = SCHEMA_SOFT;
        float[] schema = schemas.get(schemaName);
        
        float[] components = Color.RGBtoHSB(base.getRed(), base.getGreen(), base.getBlue(), null);
	
        components[1] = (schema[i*2] < 0) ? -schema[i*2] * components[1] : schema[i*2];
        if (components[1] > 1) components[1] = 1.0f;
        if (components[1] < 0) components[1] = 0;
        
        
        components[2] = (schema[i*2+1] < 0) ? -schema[i*2+1] * components[2] : schema[i*2+1];
        if (components[2] > 1) components[2] = 1.0f;
        if (components[2] < 0) components[2] = 0;
        
        return new Color( Color.HSBtoRGB(components[0], components[1], components[2]));
    }
    
    public static RGB createColor(RGB base, int i, String schemaName){
    		Color createdColor =  createColor(new Color(base.red, base.green, base.blue), i, schemaName);
    		return new RGB(createdColor.getRed(), createdColor.getGreen(), createdColor.getBlue());
    }
    
    public static List<String> getColors()
    {
        if (colorsList == null)
        {
            colorsList = new ArrayList<String>();
            colorsMap = new HashMap<String,String>();
            
            for (int i=0; i<colors.length/2; ++i)
            {
                colorsList.add( colors[i*2] );
                colorsMap.put(colors[i*2], colors[(i*2)+1]);
            }
        }
        
        return colorsList;
    }
    
  	public static Image getImagePreview(String colorName){
			//The images are cached and disposed at the end
			String key = "colorPreview_"+colorName;
			Image image = ResourceManager.getImage(key);
			if (image == null){
				if (colorsMap == null) getColors();
				String colorCode = colorsMap.get(colorName);
				if (colorCode != null){
					Color color = decodeColor("#"+colorCode);
					RGB colorRGB = new RGB(color.getRed(), color.getGreen(), color.getBlue());
					ImageData data = new ImageData(20, 10, 1, new PaletteData(new RGB[]{colorRGB}));
					image = new Image(null,data); 
					GC graphics = new GC(image);
					graphics.setForeground(ColorConstants.black);
					graphics.drawRectangle(0,0,19,9);
					graphics.dispose();
					ResourceManager.addImage(key, image);
				} else return null;
			}
			return image;
  	}
				
    
    public static String[] getColorsArray()
    {
        return colors.clone();
    }
    
    
    public static Color getColor(String name)
    {
        if (colorsMap == null)
        {
            getColors();
        }
        String rgb = colorsMap.get(name);
        return decodeColor("#"+rgb);
    }
    
    
    public static java.awt.Color decodeColor(String colorString)
    {
        java.awt.Color color = null;
        char firstChar = colorString.charAt(0);
        if (firstChar == '#')
        {
               color = new java.awt.Color(Integer.parseInt(colorString.substring(1), 16));
        }
        else if ('0' <= firstChar && firstChar <= '9')
        {
               color = new java.awt.Color(Integer.parseInt(colorString));
        }
        else
        {
                if (ColorEnum.getByName(colorString) != null)
                {
                        color = ColorEnum.getByName(colorString).getColor();
                }
                else
                {
                        color = java.awt.Color.black;
                }
        }
        return color;

    }
    
    static public List<Tag> getVariants()
    {
        List<Tag> variants = new ArrayList<Tag>();
        variants.add(new Tag(SCHEMA_DEFAULT, "Default"));
        variants.add(new Tag(SCHEMA_PASTEL, "Pastel"));
        variants.add(new Tag(SCHEMA_SOFT, "Dark Pastel"));
        variants.add(new Tag(SCHEMA_HARD, "Light Pastel"));
        variants.add(new Tag(SCHEMA_LIGHT, "Contrast"));
        variants.add(new Tag(SCHEMA_PALE, "Pale"));
        return variants;
    }

    /**
     * Saturates the color and return  a new color.
     * @param colorString
     * @param value (0-1.0)
     * @return
     */
    public static java.awt.Color desaturate(java.awt.Color color, float value)
    {

         float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
         hsb[1] -= hsb[1]*value;
         if (hsb[1] < 0f) hsb[1] = 0f;
         return new Color( Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
    }

    /**
     * Saturates the color and return  a new color.
     * @param colorString
     * @param value (0-1.0)
     * @return
     */
    public static java.awt.Color bright(java.awt.Color color, float value)
    {

         float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
         hsb[2] += hsb[2]*value;
         if (hsb[2] > 1.0f) hsb[2] = 1.0f;
         return new Color( Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
    }


    /**
     * Saturates the color and return  a new color.
     * @param colorString
     * @param value (0-1.0)
     * @return
     */
    public static java.awt.Color overlayWhite(java.awt.Color color)
    {

         return new Color((color.getRed()+255)/2, (color.getGreen()+255)/2, (color.getBlue()+255)/2);
    }
    
    static private List<String> colorsList = null;
    static private HashMap<String, String> colorsMap = null;
    
    static private String[] colors = new String[]{
            "Aliceblue","F0F8FF",
            "Antiquewhite","FAEBD7",
            "Aqua","00FFFF",
            "Aquamarine","7FFFD4",
            "Azure","F0FFFF",
            "Beige","F5F5DC",
            "Bisque","FFE4C4",
            "Black","000000",
            "Blanchedalmond","FFEBCD",
            "Blue","0000FF",
            "Blueviolet","8A2BE2",
            "Brown","A52A2A",
            "Burlywood","DEB887",
            "Cadetblue","5F9EA0",
            "Chartreuse","7FFF00",
            "Chocolate","D2691E",
            "Coral","FF7F50",
            "Cornflowerblue","6495ED",
            "Cornsilk","FFF8DC",
            "Crimson","DC143C",
            "Cyan","00FFFF",
            "Darkblue","00008B",
            "Darkcyan","008B8B",
            "Darkgoldenrod","B8860B",
            "Darkgray","A9A9A9",
            "Darkgreen","006400",
            "Darkkhaki","BDB76B",
            "Darkmagenta","8B008B",
            "Darkolivegreen","556B2F",
            "Darkorange","FF8C00",
            "Darkorchid","9932CC",
            "Darkred","8B0000",
            "Darksalmon","E9967A",
            "Darkseagreen","8FBC8F",
            "Darkslateblue","483D8B",
            "Darkturqoise","00CED1",
            "Darkslategray","2F4F4F",
            "Darkviolet","9400D3",
            "Deeppink","FF1493",
            "Deepskyblue","00BFFF",
            "Dimgray","696969",
            "Dodgerblue","1E90FF",
            "Firebrick","B22222",
            "Floralwhite","FFFAF0",
            "Forestgreen","228B22",
            "Fuchsia","FF00FF",
            "Gainsboro","DCDCDC",
            "Ghostwhite","F8F8FF",
            "Gold","FFD700",
            "Goldenrod","DAA520",
            "Gray","808080",
            "Green","008000",
            "Greenyellow","ADFF2F",
            "Honeydew","F0FFF0",
            "Hotpink","FF69B4",
            "Indianred","CD5C5C",
            "Indigo","4B0082",
            "Ivory","FFFFF0",
            "Khaki","F0E68C",
            "Lavender","E6E6FA",
            "Lavenderblush","FFF0F5",
            "Lawngreen","7CFC00",
            "Lemonchiffon","FFFACD",
            "Lightblue","ADD8E6",
            "Lightcoral","F08080",
            "Lightcyan","E0FFFF",
            "Lightgoldenrodyellow","FAFAD2",
            "Lightgreen","90EE90",
            "Lightgrey","D3D3D3",
            "Lightpink","FFB6C1",
            "Lightsalmon","FFA07A",
            "Lightseagreen","20B2AA",
            "Lightskyblue","87CEFA",
            "Lightslategray","778899",
            "Lisghtsteelblue","B0C4DE",
            "Lightyellow","FFFFE0",
            "Lime","00FF00",
            "Limegreen","32CD32",
            "Linen","FAF0E6",
            "Magenta","FF00FF",
            "Maroon","800000",
            "Mediumaquamarine","66CDAA",
            "Mediumblue","0000CD",
            "Mediumorchid","BA55D3",
            "Mediumpurple","9370DB",
            "Mediumseagreen","3CB371",
            "Mediumslateblue","7B68EE",
            "Mediumspringgreen","00FA9A",
            "Mediumturquoise","48D1CC",
            "Mediumvioletred","C71585",
            "Midnightblue","191970",
            "Mintcream","F5FFFA",
            "Mistyrose","FFE4E1",
            "Moccasin","FFE4B5",
            "Navajowhite","FFDEAD",
            "Navy","000080",
            "Navyblue","9FAFDF",
            "Oldlace","FDF5E6",
            "Olive","808000",
            "Olivedrab","6B8E23",
            "Orange","FFA500",
            "Orangered","FF4500",
            "Orchid","DA70D6",
            "Palegoldenrod","EEE8AA",
            "Palegreen","98FB98",
            "Paleturquoise","AFEEEE",
            "Palevioletred","DB7093",
            "Papayawhip","FFEFD5",
            "Peachpuff","FFDAB9",
            "Peru","CD853F",
            "Pink","FFC0CB",
            "Plum","DDA0DD",
            "Powderblue","B0E0E6",
            "Purple","800080",
            "Red","FF0000",
            "Rosybrown","BC8F8F",
            "Royalblue","4169E1",
            "Saddlebrown","8B4513",
            "Salmon","FA8072",
            "Sandybrown","F4A460",
            "Seagreen","2E8B57",
            "Seashell","FFF5EE",
            "Sienna","A0522D",
            "Silver","C0C0C0",
            "Skyblue","87CEEB",
            "Slateblue","6A5ACD",
            "Snow","FFFAFA",
            "Springgreen","00FF7F",
            "Steelblue","4682B4",
            "Tan","D2B48C",
            "Teal","008080",
            "Thistle","D8BFD8",
            "Tomato","FF6347",
            "Turquoise","40E0D0",
            "Violet","EE82EE",
            "Wheat","F5DEB3",
            "White","FFFFFF",
            "Whitesmoke","F5F5F5",
            "Yellow","FFFF00",
            "Yellowgreen","9ACD32"};
    
}
