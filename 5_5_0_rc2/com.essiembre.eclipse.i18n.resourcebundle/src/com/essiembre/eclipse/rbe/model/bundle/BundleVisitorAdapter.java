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
 * Convenience implementation of <code>IBundleVisitor</code> allowing to 
 * override only required methods.
 * @author Pascal Essiembre
 * @version $Author: essiembre $ $Revision: 1.2 $ $Date: 2005/07/30 22:10:55 $
 */
public class BundleVisitorAdapter implements IBundleVisitor {

    /**
     * Constructor.
     */
    public BundleVisitorAdapter() {
        super();
    }

    /**
     * @see com.essiembre.eclipse.rbe.model.bundle.IBundleVisitor
     *         #visitBundleGroup(
     *                 com.essiembre.eclipse.rbe.model.bundle.BundleGroup, 
     *                 java.lang.Object)
     */
    public void visitBundleGroup(BundleGroup group, Object passAlongArgument) {
    }

    /**
     * @see com.essiembre.eclipse.rbe.model.bundle.IBundleVisitor#visitBundle(
     *         com.essiembre.eclipse.rbe.model.bundle.Bundle, java.lang.Object)
     */
    public void visitBundle(Bundle bundle, Object passAlongArgument) {
    }

    /**
     * @see com.essiembre.eclipse.rbe.model.bundle.IBundleVisitor
     *         #visitBundleEntry(
     *                 com.essiembre.eclipse.rbe.model.bundle.BundleEntry,
     *                 java.lang.Object)
     */
    public void visitBundleEntry(BundleEntry entry, Object passAlongArgument) {
    }
}
