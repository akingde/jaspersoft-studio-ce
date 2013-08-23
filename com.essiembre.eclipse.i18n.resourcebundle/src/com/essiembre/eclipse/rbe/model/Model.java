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
package com.essiembre.eclipse.rbe.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import org.eclipse.core.internal.runtime.ListenerList; >= Eclipse 3.2
//import org.eclipse.core.runtime.ListenerList;           < Eclipse 3.2

/**
 * Base class for core model objects.
 * @author Pascal Essiembre (essiembre@users.sourceforge.net)
 * @version $Author: nl_carnage $ $Revision: 1.4 $ $Date: 2007/09/11 16:11:08 $
 */
public abstract class Model {

    /* The holder for listeners was changed from ListenerList to ArrayList
     * to support both Eclipse 3.1 and 3.2.  The ListenerList location changed
     * from 3.1 to 3.2 as described here:
     * https://bugs.eclipse.org/bugs/show_bug.cgi?format=multiple&id=94156
     */
    /** Listeners for this object. */
    private final List listeners = new ArrayList();
    
    /**
     * Fires an "add" event.
     * @param added object added
     */
    protected void fireAdd(Object added) {
        for (Iterator iter = listeners.iterator(); iter.hasNext();) {
            IDeltaListener listener = (IDeltaListener) iter.next();
            listener.add(new DeltaEvent(added));
        }
    }

    /**
     * Fires a "remove" event.
     * @param removed object removed
     */
    protected void fireRemove(Object removed) {
        for (Iterator iter = listeners.iterator(); iter.hasNext();) {
            IDeltaListener listener = (IDeltaListener) iter.next();
            listener.remove(new DeltaEvent(removed));
        }
    }
    
    /**
     * Fires a "modify" event.
     * @param modified object modified
     */
    protected void fireModify(Object modified) {
        for (Iterator iter = listeners.iterator(); iter.hasNext();) {
            IDeltaListener listener = (IDeltaListener) iter.next();
            listener.modify(new DeltaEvent(modified));
        }
    }
    
    /**
     * Fires a "select" event.
     * @param selected the selected object.
     */
    protected void fireSelect(Object selected) {
        for (Iterator iter = listeners.iterator(); iter.hasNext();) {
            IDeltaListener listener = (IDeltaListener) iter.next();
            listener.select(new DeltaEvent(selected));
        }
    }

    /**
     * Adds a listener to this instance.
     * @param listener listener to add
     */
    public void addListener(IDeltaListener listener) {
        listeners.add(0, listener);
    }
    /**
     * Removes a listener from this instance.
     * @param listener listener to remove
     */
    public void removeListener(IDeltaListener listener) {
        listeners.remove(listener);
    }
}
