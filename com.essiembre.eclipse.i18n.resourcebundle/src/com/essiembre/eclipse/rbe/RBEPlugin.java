/*
 * Copyright (C) 2003, 2004  Pascal Essiembre, Essiembre Consultant Inc.
 * 
 * This file is part of Essiembre ResourceBundle Editor.
 * 
 * Essiembre ResourceBundle Editor is free software; you can redistribute it 
 * and/or modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * Essiembre ResourceBundle Editor is distributed in the hope that it will be 
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with Essiembre ResourceBundle Editor; if not, write to the 
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330, 
 * Boston, MA  02111-1307  USA
 */
package com.essiembre.eclipse.rbe;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main resource bundle editor plugin class to be used in the desktop.
 */
public class RBEPlugin extends AbstractUIPlugin {

    /** Plugin unique id. */
    public static final String ID = 
            "com.essiembre.eclipse.i18n.resourcebundle"; //$NON-NLS-1$
    
    //The shared instance.
    private static RBEPlugin plugin;
    //Resource bundle.
    private ResourceBundle resourceBundle;
    
    /**
     * The constructor.
     */
    public RBEPlugin() {
        super();
        plugin = this;
    }
    
    /**
     * This method is called upon plug-in activation
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        try {
            URL messagesUrl = 
                    find(new Path("$nl$/messages.properties")); //$NON-NLS-1$
            if(messagesUrl != null) {
                resourceBundle = new PropertyResourceBundle(
                        messagesUrl.openStream());
            }
        } catch (IOException x) {
            resourceBundle = null;
        }
    }

    /**
     * This method is called when the plug-in is stopped
     */
    public void stop(BundleContext context) throws Exception {
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     * @return this plugin
     */
    public static RBEPlugin getDefault() {
        return plugin;
    }

    /**
     * Returns the string from the plugin's resource bundle,
     * or 'key' if not found.
     * @param key the key for which to fetch a localized text
     * @return localized string corresponding to key
     */
    public static String getString(String key) {
        ResourceBundle bundle = 
                RBEPlugin.getDefault().getResourceBundle();
        try {
            return (bundle != null) ? bundle.getString(key) : key;
        } catch (MissingResourceException e) {
            return key;
        }
    }

    /**
     * Returns the string from the plugin's resource bundle,
     * or 'key' if not found.
     * @param key the key for which to fetch a localized text
     * @param arg1 runtime argument to replace in key value 
     * @return localized string corresponding to key
     */
    public static String getString(String key, String arg1) {
        return MessageFormat.format(getString(key), new String[]{arg1});
    }
    /**
     * Returns the string from the plugin's resource bundle,
     * or 'key' if not found.
     * @param key the key for which to fetch a localized text
     * @param arg1 runtime first argument to replace in key value
     * @param arg2 runtime second argument to replace in key value
     * @return localized string corresponding to key
     */
    public static String getString(String key, String arg1, String arg2) {
        return MessageFormat.format(
                getString(key), new String[]{arg1, arg2});
    }
    /**
     * Returns the string from the plugin's resource bundle,
     * or 'key' if not found.
     * @param key the key for which to fetch a localized text
     * @param arg1 runtime argument to replace in key value 
     * @param arg2 runtime second argument to replace in key value
     * @param arg3 runtime third argument to replace in key value
     * @return localized string corresponding to key
     */
    public static String getString(
            String key, String arg1, String arg2, String arg3) {
        return MessageFormat.format(
                getString(key), new String[]{arg1, arg2, arg3});
    }
    
    /**
     * Returns the plugin's resource bundle.
     * @return resource bundle
     */
    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }
    
    /**
     * Gets an image descriptor.
     * @param name image name
     * @return image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String name) {
        String iconPath = "icons/"; //$NON-NLS-1$
        try {
            URL installURL = RBEPlugin.getDefault().getBundle().getEntry(
                    "/"); //$NON-NLS-1$
            URL url = new URL(installURL, iconPath + name);
            return ImageDescriptor.createFromURL(url);
        } catch (MalformedURLException e) {
            // should not happen
            return ImageDescriptor.getMissingImageDescriptor();
        }
    }
    
}
