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
 * that is commented out.
 * @author Pascal Essiembre (essiembre@users.sourceforge.net)
 * @version $Author: essiembre $ $Revision: 1.2 $ $Date: 2005/07/30 04:30:53 $
 */
public class IsCommentedVisitor extends KeyTreeVisitorAdapter {

    /** Whether corresponding bundle entries have one commented. */
    boolean hasOneCommented = false;
    /** Whether corresponding bundle entries are all commented. */
    boolean areAllCommented = false;
    
    /**
     * Constructor.
     */
    public IsCommentedVisitor() {
        super();
    }

    /**
     * @see com.essiembre.eclipse.rbe.model.tree.IKeyTreeVisitor
     *         #visitKeyTreeItem(
     *                 com.essiembre.eclipse.rbe.model.tree.KeyTreeItem,
     *                 java.lang.Object)
     */
    public void visitKeyTreeItem(KeyTreeItem item, Object passAlongArgument) {
        String key = item.getId();
        BundleGroup bundleGroup = item.getKeyTree().getBundleGroup();
        if (bundleGroup.isKey(key)) {
            Collection entries = bundleGroup.getBundleEntries(key);
            int commentedCount = 0;
            for (Iterator iter = entries.iterator(); iter.hasNext();) {
                BundleEntry entry = (BundleEntry) iter.next();
                if (entry != null && entry.isCommented()) {
                    hasOneCommented = true;
                    commentedCount++;
                }
            }
            if (commentedCount == entries.size()) {
                areAllCommented = true;
            }
        }
    }
    
    /**
     * Gets the "areAllCommented" attribute.
     * @return Returns the areAllCommented.
     */
    public boolean areAllCommented() {
        return areAllCommented;
    }
    /**
     * Gets the "hasOneCommented" attribute.
     * @return Returns the hasOneCommented.
     */
    public boolean hasOneCommented() {
        return hasOneCommented;
    }
}
