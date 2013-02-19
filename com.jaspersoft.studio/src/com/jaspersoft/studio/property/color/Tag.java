package com.jaspersoft.studio.property.color;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author gtoffoli
 */
public class Tag {
    
    private Object value;
    private String name = "";
    
    public Tag(Object value, String name) {
        setName( name );
        setValue(value);
    }
    
    public Tag(String value) {
        setName( value );
        setValue(value);
    }
    
    public Tag(Object value) {
        setName( value+"");
        setValue(value);
    }
    
    public Tag(int value, String name) {
        setName( name );
        setValue(value);
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    
    public void setValue(int value) {
        this.value = ""+value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String toString()
    {
        return getName();
    }
    
    /**
     * Look for the first tag with the specified name.
     */
    public static final Tag findTagByName(String name, Collection c)
    {
            if (c == null) return null;
            Iterator i = c.iterator();
            while (i.hasNext())
            {
                Tag t = (Tag)i.next();
                if ( (name == null && t.getName() == null) ||
                     (t.getName() != null && t.getName().equals(name)) )
                {
                    return t;
                }
            }
            return null;
    }
    
    /**
     * Look for the first tag with the specified name.
     */
    public static final Tag findTagByValue(Object value, Collection c)
    {
            if (c == null) return null;
            Iterator i = c.iterator();
            while (i.hasNext())
            {
                Tag t = (Tag)i.next();
                if ( (value == null && t.getValue() == value) ||
                     (t.getValue() != null && t.getValue().equals(value)) )
                {
                    return t;
                }
            }
            return null;
    }
    
}