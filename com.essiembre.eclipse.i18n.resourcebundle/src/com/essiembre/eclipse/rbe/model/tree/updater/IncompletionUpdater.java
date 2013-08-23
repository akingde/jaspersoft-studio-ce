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


import com.essiembre.eclipse.rbe.model.bundle.BundleEntry;
import com.essiembre.eclipse.rbe.model.bundle.BundleGroup;
import com.essiembre.eclipse.rbe.model.tree.KeyTree;

import java.util.Collection;
import java.util.Iterator;


/**
 * An update which filters entries where at least one isn't available.
 */
public class IncompletionUpdater extends KeyTreeUpdater {

    
    private KeyTreeUpdater   delegation  ;
    private BundleGroup      bundlegroup ;
    

    /**
     * Initialises this BundleGroup instance which allows to access
     * the bundles keeping the i18n information. 
     * 
     * @param group     A container for the i18n information.
     * @param delegate  The update which will be used for delegation. Mainly
     *                  intended for structural information.
     */
    public IncompletionUpdater(BundleGroup group, KeyTreeUpdater delegate) {
        delegation  = delegate;
        bundlegroup = group;
    }
    

    /**
     * {@inheritDoc}
     */
    public void addKey(KeyTree keytree, String key) {
        Collection entries  = bundlegroup.getBundleEntries(key);
        int        count    = 0;
        Iterator   iterator = entries.iterator();
        while(iterator.hasNext()) {
            Object val = iterator.next();
            if(val instanceof BundleEntry) {
                BundleEntry entry = (BundleEntry) val;
                String      value = entry.getValue();
                if((value != null) && (value.length() > 0)) {
                    count++;
                }
            }
        }
        // we only delegate entries in case there are some incomplete ones
        if (count < bundlegroup.getBundleCount()) {
            delegation.addKey(keytree, key);
        }
    }
    
    
} /* ENDCLASS */
