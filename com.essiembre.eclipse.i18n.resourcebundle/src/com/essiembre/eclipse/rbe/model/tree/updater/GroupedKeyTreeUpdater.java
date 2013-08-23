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
package com.essiembre.eclipse.rbe.model.tree.updater;

import java.util.Map;
import java.util.StringTokenizer;

import com.essiembre.eclipse.rbe.model.tree.KeyTree;
import com.essiembre.eclipse.rbe.model.tree.KeyTreeItem;

/**
 * Contains update instructions on how to update a "grouped" key tree.
 * @author Pascal Essiembre (essiembre@users.sourceforge.net)
 * @version $Author: essiembre $ $Revision: 1.2 $ $Date: 2005/07/30 22:10:56 $
 */
public class GroupedKeyTreeUpdater extends KeyTreeUpdater {

    /** Key group separator. */
    private String separator;
    
    /**
     * Constructor.
     * @param keyGroupSeparator key group separator
     */
    public GroupedKeyTreeUpdater(String keyGroupSeparator) {
        super();
        this.separator = keyGroupSeparator;
    }
    
    /**
     * @see com.essiembre.eclipse.rbe.model.tree.updater.KeyTreeUpdater#addKey(
     *         com.essiembre.eclipse.rbe.model.tree.KeyTree, java.lang.String)
     */
    public void addKey(KeyTree keyTree, String key) {
        Map keyCache = keyTree.getKeyItemsCache();
        if (!keyCache.containsKey(key)) {
            StringBuffer idBuf = new StringBuffer();
            Object parent = keyTree;
            for (StringTokenizer tokens = new StringTokenizer(key, separator);
                    tokens.hasMoreTokens();) {
                String name = tokens.nextToken();
                if (!(parent instanceof KeyTree)) {
                    idBuf.append(separator);
                }
                idBuf.append(name);
                String id = idBuf.toString();
                if (!keyCache.containsKey(id)) {
                    KeyTreeItem item = new KeyTreeItem(keyTree, id, name);
                    item.setParent(parent);
                    if (parent instanceof KeyTree) {
                        keyTree.getRootKeyItems().add(item);
                    } else {
                        ((KeyTreeItem) parent).addChildren(item);
                    }
                    keyCache.put(id, item);
                    parent = item;
                } else {
                    parent = keyCache.get(id);
                }
            }
        }
    }

    /**
     * Gets the key group separator.
     * @return key group separator.
     */
    public String getSeparator() {
        return separator;
    }
    /**
     * Sets the key group separator.
     * @param separator key group separator
     */
    public void setSeparator(String separator) {
        this.separator = separator;
    }
}
