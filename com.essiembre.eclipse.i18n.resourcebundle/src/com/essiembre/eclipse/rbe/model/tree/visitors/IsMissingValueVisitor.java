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
package com.essiembre.eclipse.rbe.model.tree.visitors;

import java.util.Collection;
import java.util.Iterator;

import com.essiembre.eclipse.rbe.model.bundle.BundleEntry;
import com.essiembre.eclipse.rbe.model.bundle.BundleGroup;
import com.essiembre.eclipse.rbe.model.tree.KeyTreeItem;
import com.essiembre.eclipse.rbe.model.tree.KeyTreeVisitorAdapter;

/**
 * Visitor for finding if a key has at least one corresponding bundle entry
 * with a missing value.
 * @author Pascal Essiembre (essiembre@users.sourceforge.net)
 * @version $Author: essiembre $ $Revision: 1.3 $ $Date: 2005/07/30 22:10:54 $
 */
public class IsMissingValueVisitor extends KeyTreeVisitorAdapter {

    /** Whether corresponding bundle entries are missing a value. */
    boolean isMissingValue = false;
    /** Whether a corresponding bundle entries children are missing a value. */
    boolean isMissingChildValueOnly = false;
    
    /**
     * Constructor.
     */
    public IsMissingValueVisitor() {
        super();
    }

    /**
     * @see com.essiembre.eclipse.rbe.model.tree.IKeyTreeVisitor
     *         #visitKeyTreeItem(
     *                 com.essiembre.eclipse.rbe.model.tree.KeyTreeItem,
     *                 java.lang.Object)
     */
    public void visitKeyTreeItem(KeyTreeItem item, Object passAlongArgument) {
        // passed item
        isMissingValue = isItemMissingValue(item);
        
        // chidren items
        if (!isMissingValue) {
            for (Iterator iter = item.getNestedChildren().iterator(); 
                    iter.hasNext();) {
                KeyTreeItem childItem = (KeyTreeItem) iter.next();
                isMissingChildValueOnly = isItemMissingValue(childItem);
                if (isMissingChildValueOnly) {
                    return;
                }
            }
        }
    }

    /**
     * Checks whether the corresponding entries do not miss any values, but have
     * at least one child missing a value.
     * @return <code>true</code> if child missing a value
     */
    public boolean isMissingChildValueOnly() {
        return isMissingChildValueOnly;
    }
    /**
     * Sets whether the corresponding entries do not miss any values, but have
     * at least one child missing a value.
     * @param isMissingChildValueOnly <code>true</code> if child missing value
     */
    public void setMissingChildValueOnly(boolean isMissingChildValueOnly) {
        this.isMissingChildValueOnly = isMissingChildValueOnly;
    }

    /**
     * Checks whether the corresponding entries are missing any values.
     * @return <code>true</code> if missing a value
     */
    public boolean isMissingValue() {
        return isMissingValue;
    }
    /**
     * Sets whether the corresponding entries are missing any values.
     * @param isMissingValue <code>true</code> if missing a value
     */
    public void setMissingValue(boolean isMissingValue) {
        this.isMissingValue = isMissingValue;
    }
    
    /**
     * Checks if the given item is missing a value.
     * @param item the item to check
     * @return <code>true</code> if item is missing a value
     */
    private boolean isItemMissingValue(KeyTreeItem item) {
        String key = item.getId();
        BundleGroup bundleGroup = item.getKeyTree().getBundleGroup();
        if (bundleGroup.isKey(key)) {
            Collection entries = bundleGroup.getBundleEntries(key);
            if (entries.size() != bundleGroup.getSize()) {
                return true;
            }
            for (Iterator iter = entries.iterator(); iter.hasNext();) {
                BundleEntry entry = (BundleEntry) iter.next();
                if (entry == null || entry.getValue().length() == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
