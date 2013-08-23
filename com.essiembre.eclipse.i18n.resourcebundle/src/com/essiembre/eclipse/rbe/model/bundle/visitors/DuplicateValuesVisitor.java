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
package com.essiembre.eclipse.rbe.model.bundle.visitors;

import java.util.ArrayList;
import java.util.Collection;

import com.essiembre.eclipse.rbe.model.bundle.BundleEntry;
import com.essiembre.eclipse.rbe.model.bundle.BundleVisitorAdapter;

/**
 * Finds bundle entries having values identical to the bundle entry given
 * as the pass-along argument.
 * @author Pascal Essiembre (essiembre@users.sourceforge.net)
 * @version $Author: essiembre $ $Revision: 1.1 $ $Date: 2005/07/30 04:30:16 $
 */
public class DuplicateValuesVisitor extends BundleVisitorAdapter {

    /** Holder for bundle entries having duplicate values. */
    private final Collection duplicates = new ArrayList();
    
    /**
     * Constructor.
     */
    public DuplicateValuesVisitor() {
        super();
    }

    /**
     * @see com.essiembre.eclipse.rbe.model.bundle.IBundleVisitor
     *         #visitBundleEntry(
     *                 com.essiembre.eclipse.rbe.model.bundle.BundleEntry,
     *                 java.lang.Object)
     */
    public void visitBundleEntry(BundleEntry entry, Object passAlongArgument) {
        
        BundleEntry entryToMatch = (BundleEntry) passAlongArgument;
        if (entry != entryToMatch
                && entry != null && entryToMatch != null
                && entry.getValue().length() > 0
                && entry.getValue().equals(entryToMatch.getValue())) {
            duplicates.add(entry);
        }
    }
    
    /**
     * Gets a collection of duplicate <code>BundleEntry</code> instance.
     * @return bundle entries with duplicate values
     */
    public Collection getDuplicates() {
        return duplicates;
    }
    
    /**
     * Clears the list of duplicate values.
     */
    public void clear() {
        duplicates.clear();
    }
    
}