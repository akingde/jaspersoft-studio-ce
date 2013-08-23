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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.essiembre.eclipse.rbe.model.Model;


/**
 * Representation of a properties file, specific to ResourceBundle editor.
 * @author Pascal Essiembre (essiembre@users.sourceforge.net)
 * @version $Author: nl_carnage $ $Revision: 1.5 $ $Date: 2007/09/11 16:19:05 $
 */
public class Bundle extends Model implements IBundleVisitable {

    /** Bundle head comment. */
    private String comment;
    /** Bundle locale. */
    private Locale locale;
    /** Bundle entries (key=key value=BundleEntry). */
    private final Map entries = new HashMap();
    /** Bundle group (parent). */
    private BundleGroup bundleGroup;
    
    /**
     * Constructor.
     */
    public Bundle() {
        super();
    }
    
    /**
     * @see IBundleVisitable#accept(IBundleVisitor, Object)
     */
    public void accept(IBundleVisitor visitor, Object passAlongArgument) {
        for (Iterator iter = entries.values().iterator(); iter.hasNext();) {
            visitor.visitBundleEntry(
                    (BundleEntry) iter.next(), passAlongArgument);
        }
        visitor.visitBundle(this, passAlongArgument);
        visitor.visitBundleGroup(bundleGroup, passAlongArgument);
    }
    
    /**
     * Gets the "comment" attribute.
     * @return Returns the comment.
     */
    public String getComment() {
        return comment;
    }
    /**
     * Sets the "comment" attribute.
     * @param comment The comment to set.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets the "locale" attribute.
     * @return Returns the locale.
     */
    public Locale getLocale() {
        return locale;
    }
    /**
     * Sets the "locale" attribute.
     * @param locale The locale to set.
     */
    protected void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    /**
     * Gets the bundle entry matcing the given key.
     * @param key a bundle entry key
     * @return the matching bundle entry, or <code>null</code>
     */
    public BundleEntry getEntry(String key) {
        return (BundleEntry) entries.get(key);    
    }
    
    /**
     * Adds a bundle entry to this bundle.
     * @param entry the bundle entry to add
     */
    protected void addEntry(BundleEntry entry) {
        BundleEntry oldEntry = (BundleEntry) entries.get(entry.getKey());
        if (oldEntry != null) {
            if (!oldEntry.equals(entry)) {
                entries.put(entry.getKey(), entry);
                entry.setBundle(this);
                entry.setLocale(locale);
                fireModify(oldEntry);
            }
        } else if (entry.getKey().trim().length() > 0) {
            entries.put(entry.getKey(), entry);
            entry.setBundle(this);
            entry.setLocale(locale);
            fireAdd(entry);
        }
    }
    
    /**
     * Removes a bundle entry from this bundle.
     * @param entry the bundle entry to remove
     */
    protected void removeEntry(BundleEntry entry) {
        BundleEntry removedEntry = (BundleEntry) entries.get(entry.getKey());
        entries.remove(entry.getKey());
        fireRemove(removedEntry);
    }
    
    /**
     * Renames a bundle entry key.
     * @param oldKey the bundle entry key to rename
     * @param newKey the new name for the bundle entry
     */
    protected void renameKey(String oldKey, String newKey) {
        BundleEntry oldEntry = (BundleEntry) entries.get(oldKey);
        if (oldEntry != null) {
            BundleEntry newEntry = new BundleEntry(
                    newKey, oldEntry.getValue(), oldEntry.getComment());
            removeEntry(oldEntry);
            addEntry(newEntry);
        }
    }

    /**
     * Comments a bundle entry.
     * @param key key of bundle entry to be commented
     */
    protected void commentKey(String key) {
        BundleEntry entry = (BundleEntry) entries.get(key);
        if (entry != null) {
            BundleEntry newEntry = new BundleEntry(
                    key, entry.getValue(), entry.getComment(), true);
            addEntry(newEntry);
        }
    }
    /**
     * Uncomments a bundle entry.
     * @param key key of bundle entry to be uncommented
     */
    protected void uncommentKey(String key) {
        BundleEntry entry = (BundleEntry) entries.get(key);
        if (entry != null) {
            BundleEntry newEntry = new BundleEntry(
                    key, entry.getValue(), entry.getComment(), false);
            addEntry(newEntry);
        }
    }
    
    /**
     * Copies a bundle entry under a different key.
     * @param origKey key of bundle entry to be copied
     * @param newKey key for the copied bundle entry
     */
    protected void copyKey(String origKey, String newKey) {
        BundleEntry origEntry = (BundleEntry) entries.get(origKey);
        if (origEntry != null) {
            BundleEntry newEntry = new BundleEntry(
                    newKey, origEntry.getValue(), origEntry.getComment());
            addEntry(newEntry);
        }
    }
    
    /**
     * Iterates through the <code>BundleEntry</code> objects in this bundle.
     * @return an iterator
     */
    public Iterator iterator() {
        return entries.values().iterator();
    }

    /**
     * Gets the bundle group (parent) associated with this bundle.
     * @return a bundle group
     */
    public BundleGroup getBundleGroup() {
        return bundleGroup;
    }
    /**
     * Sets the bundle group (parent) associated with this bundle.
     * @param bundleGroup a bundle group
     */
    protected void setBundleGroup(BundleGroup bundleGroup) {
        this.bundleGroup = bundleGroup;
    }
    
    /**
     * Gets sorted resource bundle keys for this bundle.
     * @return resource bundle keys
     */
    public Set<String> getKeys() {
        Set<String> keys = new TreeSet<String>();
        keys.addAll(entries.keySet());
        return keys;
        //        return Collections.unmodifiableSet(keys);
    }

    /**
     * Copies values from given bundle into this bundle.
     * @param bundle bundle to copy to this bundle.
     */    
    protected void copyFrom(Bundle bundle) {
        setComment(bundle.getComment());
        // Remove deleted entries
        synchronized (entries) {
            List entriesToRemove = new ArrayList();
            for (Iterator iter = iterator(); iter.hasNext();) {
                BundleEntry localEntry = (BundleEntry) iter.next();
                if (bundle.getEntry(localEntry.getKey()) == null) {
                    entriesToRemove.add(localEntry);
                }
            }    
            for (Iterator iter = entriesToRemove.iterator(); iter.hasNext();) {
                BundleEntry entry = (BundleEntry) iter.next();
                removeEntry(entry);
            }        
        }
        
        // Add existing/new entries
        for (Iterator iter = bundle.iterator(); iter.hasNext();) {
            BundleEntry entry = (BundleEntry) iter.next();
            addEntry(entry);
        }
    }
}