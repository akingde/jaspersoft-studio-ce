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
package com.essiembre.eclipse.rbe.ui.editor.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.ITextEditor;

import com.essiembre.eclipse.rbe.model.DeltaEvent;
import com.essiembre.eclipse.rbe.model.IDeltaListener;
import com.essiembre.eclipse.rbe.model.bundle.Bundle;
import com.essiembre.eclipse.rbe.model.bundle.BundleGroup;
import com.essiembre.eclipse.rbe.model.bundle.PropertiesGenerator;
import com.essiembre.eclipse.rbe.model.bundle.PropertiesParser;
import com.essiembre.eclipse.rbe.model.tree.KeyTree;
import com.essiembre.eclipse.rbe.model.tree.updater.FlatKeyTreeUpdater;
import com.essiembre.eclipse.rbe.model.tree.updater.GroupedKeyTreeUpdater;
import com.essiembre.eclipse.rbe.model.tree.updater.KeyTreeUpdater;
import com.essiembre.eclipse.rbe.model.workbench.RBEPreferences;

/**
 * Mediator holding instances of commonly used items, dealing with 
 * important interactions within themselves.
 * @author Pascal Essiembre (essiembre@users.sourceforge.net)
 * @version $Author: fleque $ $Revision: 1.13 $ $Date: 2007/09/15 15:14:02 $
 */
public class ResourceManager {

    private IResourceFactory resourcesFactory;
    private final BundleGroup bundleGroup;
    private final KeyTree keyTree;
    /** key=Locale;value=SourceEditor */
    /*default*/ final Map sourceEditors = new HashMap();
    private final Collection locales = new ArrayList();
    
    /**
     * Constructor.
     * @param site eclipse editor site
     * @param file file used to create manager
     * @throws CoreException problem creating resource manager
     */
    public ResourceManager(final IEditorSite site, final IFile file)
            throws CoreException {
        super();
        resourcesFactory = ResourceFactory.createFactory(site, file);
        bundleGroup = new BundleGroup();
        SourceEditor[] editors = resourcesFactory.getSourceEditors();
        for (int i = 0; i < editors.length; i++) {
            SourceEditor sourceEditor = editors[i];
            Locale locale = sourceEditor.getLocale();
            sourceEditors.put(locale, sourceEditor);
            locales.add(locale);
            bundleGroup.addBundle(
                    locale, PropertiesParser.parse(sourceEditor.getContent()));			
        }
        bundleGroup.addListener(new IDeltaListener() {
            public void add(DeltaEvent event) {}    // do nothing
            public void remove(DeltaEvent event) {} // do nothing
            public void modify(DeltaEvent event) {
                final Bundle bundle = (Bundle) event.receiver();
                final SourceEditor editor = 
                        (SourceEditor) sourceEditors.get(bundle.getLocale());
                String editorContent = PropertiesGenerator.generate(bundle);
                editor.setContent(editorContent);
            }
            public void select(DeltaEvent event) {
            }
        });
        
        KeyTreeUpdater treeUpdater = null;
        if (RBEPreferences.getKeyTreeHierarchical()) {
            treeUpdater = new GroupedKeyTreeUpdater(
                    RBEPreferences.getKeyGroupSeparator());
        } else {
            treeUpdater = new FlatKeyTreeUpdater();
        }
        this.keyTree = new KeyTree(bundleGroup, treeUpdater);
    }

    /**
     * Gets a bundle group.
     * @return bundle group
     */
    public BundleGroup getBundleGroup() {
        return bundleGroup;
    }
    /**
     * Gets all locales in this bundle.
     * @return locales
     */
    public Collection getLocales() {
        return locales;
    }
    /**
     * Gets the key tree for this bundle.
     * @return key tree
     */
    public KeyTree getKeyTree() {
        return keyTree;
    }
    /**
     * Gets the source editors.
     * @return source editors.
     */
    public SourceEditor[] getSourceEditors() {
        return resourcesFactory.getSourceEditors();
    }
    
    /**
     * Save all dirty editors.
     * @param monitor progress monitor
     */
    public void save(IProgressMonitor monitor) {
        SourceEditor[] editors = resourcesFactory.getSourceEditors();
        for (int i = 0; i < editors.length; i++) {
            editors[i].getEditor().doSave(monitor);
        }
    }
        
    /**
     * Gets the multi-editor display name.
     * @return display name
     */
    public String getEditorDisplayName() {
        return resourcesFactory.getEditorDisplayName();
    }

    /**
     * Returns whether a given file is known to the resource manager (i.e.,
     * if it is part of a resource bundle).
     * @param file file to test
     * @return <code>true</code> if a known resource
     */
    public boolean isResource(IFile file) {
        SourceEditor[] editors = resourcesFactory.getSourceEditors();
        for (int i = 0; i < editors.length; i++) {
            if (editors[i].getFile().equals(file)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Creates a properties file.
     * @param locale a locale
     * @return the newly created file
     * @throws CoreException problem creating file
     * @throws IOException problem creating file
     */
    public IFile createPropertiesFile(Locale locale) 
            throws CoreException, IOException {
        return resourcesFactory.getPropertiesFileCreator().createPropertiesFile(
                locale);
    }
    
    /**
     * Gets the source editor matching the given locale.
     * @param locale locale matching requested source editor
     * @return source editor or <code>null</code> if no match
     */
    public SourceEditor getSourceEditor(Locale locale) {
        return (SourceEditor) sourceEditors.get(locale);
    }
    
    public SourceEditor addSourceEditor(IFile resource, Locale locale) throws PartInitException {
        SourceEditor sourceEditor = resourcesFactory.addResource(resource, locale);
        sourceEditors.put(sourceEditor.getLocale(), sourceEditor);
        locales.add(locale);
        bundleGroup.addBundle(
                locale, PropertiesParser.parse(sourceEditor.getContent())); 
        return sourceEditor;
    }
    /**
     * Reloads the properties files (parse them).
     */
    public void reloadProperties() {
        SourceEditor[] editors = resourcesFactory.getSourceEditors();
        for (int i = 0; i < editors.length; i++) {
            SourceEditor editor = editors[i];
            if (editor.isCacheDirty()) {
                bundleGroup.addBundle(
                        editor.getLocale(),
                        PropertiesParser.parse(editor.getContent()));
                editor.resetCache();
            }
        }
    }

}
