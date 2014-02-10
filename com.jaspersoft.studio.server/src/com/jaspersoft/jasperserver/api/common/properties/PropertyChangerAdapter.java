package com.jaspersoft.jasperserver.api.common.properties;

/**
 * PropertyChanger
 *
 * This helper class is used to avoid implementation of methods that are not used
 *
 * @author scubar
 */
public abstract class PropertyChangerAdapter implements PropertyChanger {
    @Override
    public void setProperty(String key, String val) { }

    @Override
    public String getProperty(String key) { return null; }

    @Override
    public void removeProperty(String key, String val) {}
}
