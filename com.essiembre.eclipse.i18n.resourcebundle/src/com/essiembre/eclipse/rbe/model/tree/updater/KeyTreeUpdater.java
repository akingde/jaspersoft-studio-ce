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

import com.essiembre.eclipse.rbe.model.tree.KeyTree;
import com.essiembre.eclipse.rbe.model.tree.KeyTreeItem;

/**
 * Contains update instructions on how to update a key tree.
 * @author Pascal Essiembre (essiembre@users.sourceforge.net)
 * @version $Author: essiembre $ $Revision: 1.2 $ $Date: 2005/07/11 03:02:29 $
 */
public abstract class KeyTreeUpdater {

    /**
     * Constructor.
     */
    public KeyTreeUpdater() {
        super();
    }
    
    /**
     * Adds a key to the key tree.
     * @param keyTree key tree on which to add the key
     * @param key key to add
     */
    public abstract void addKey(KeyTree keyTree, String key);

    /**
     * Removes a key from the key tree.
     * @param keyTree key tree from which to remove the key
     * @param key key to remove
     */
    public void removeKey(KeyTree keyTree, String key) {
        Map keyCache = keyTree.getKeyItemsCache();
        KeyTreeItem item = (KeyTreeItem) keyCache.get(key);
        if (item != null) {
            Object parent = item.getParent();
            if (parent instanceof KeyTree) {
                ((KeyTree) parent).getRootKeyItems().remove(item);
            } else {
                ((KeyTreeItem) parent).removeChildren(item);
            }
            keyCache.remove(key);

            // remove parents with no children having invalid keys. 
            if (parent instanceof KeyTreeItem) {
                KeyTreeItem parentItem = (KeyTreeItem) parent;
                boolean isKey = 
                        keyTree.getBundleGroup().isKey(parentItem.getId());
                boolean hasChildren = parentItem.getChildren().size() > 0;
                if (!isKey && ! hasChildren) {
                    removeKey(keyTree, parentItem.getId());
                }
            }
        }
    }
}
