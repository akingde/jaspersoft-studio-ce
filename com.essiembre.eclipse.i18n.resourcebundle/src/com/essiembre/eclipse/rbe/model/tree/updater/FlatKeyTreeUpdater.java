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
 * Contains update instructions on how to update a "flat" key tree
 * (no children).
 * @author Pascal Essiembre (essiembre@users.sourceforge.net)
 * @version $Author: essiembre $ $Revision: 1.1 $ $Date: 2005/06/13 01:43:30 $
 */
public class FlatKeyTreeUpdater extends KeyTreeUpdater {

    /**
     * Constructor.
     */
    public FlatKeyTreeUpdater() {
        super();
    }
    
    /**
     * @see com.essiembre.eclipse.rbe.model.tree.updater.KeyTreeUpdater#addKey(
     *         com.essiembre.eclipse.rbe.model.tree.KeyTree, java.lang.String)
     */
    public void addKey(KeyTree keyTree, String key) {
        Map keyCache = keyTree.getKeyItemsCache();
        if (!keyCache.containsKey(key)) {
            KeyTreeItem item = new KeyTreeItem(keyTree, key, key);
            item.setParent(keyTree);
            keyTree.getRootKeyItems().add(item);
            keyCache.put(key, item);
        }
    }
}
