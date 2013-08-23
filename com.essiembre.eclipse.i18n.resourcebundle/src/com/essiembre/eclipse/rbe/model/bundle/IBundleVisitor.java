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
package com.essiembre.eclipse.rbe.model.bundle;

/**
 * Objects implementing this interface can act as a visitor to any 
 * bundle-related resource implementing <code>IBundleVisitable</code>.
 * @author Pascal Essiembre (essiembre@users.sourceforge.net)
 * @version $Author: nl_carnage $ $Revision: 1.3 $ $Date: 2007/09/11 16:11:10 $
 */
public interface IBundleVisitor {
    /**
     * Visits a bundle group.
     * @param group bundle group
     * @param passAlongArgument an optional argument
     */
    public void visitBundleGroup(BundleGroup group, Object passAlongArgument);
    /**
     * Visits a bundle.
     * @param bundle bundle
     * @param passAlongArgument an optional argument
     */
    public void visitBundle(Bundle bundle, Object passAlongArgument);
    /**
     * Visits a bundle entry.
     * @param entry bundle entry
     * @param passAlongArgument an optional argument
     */
    public void visitBundleEntry(BundleEntry entry, Object passAlongArgument);
}
