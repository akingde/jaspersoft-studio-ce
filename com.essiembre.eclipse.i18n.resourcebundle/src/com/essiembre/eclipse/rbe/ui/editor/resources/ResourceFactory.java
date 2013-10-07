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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.ITextEditor;

import com.essiembre.eclipse.rbe.model.workbench.files.PropertiesFileCreator;
import com.essiembre.eclipse.rbe.ui.UIUtils;

/**
 * Responsible for creating resources related to a given file structure.
 * <p>
 * This class is also the abstract base class for implementations of 
 * a {@link ResourceFactory} as well as static entry point to access
 * the responsible one.
 * </p>
 * @author Pascal Essiembre (essiembre@users.sourceforge.net)
 * @version $Author: cuhiodtick $ $Revision: 1.9 $ $Date: 2012/07/29 17:20:35 $
 */
public abstract class ResourceFactory implements IResourceFactory {

    /** Class name of Properties file editor (Eclipse 3.1). */
    protected static final String PROPERTIES_EDITOR_CLASS_NAME = 
            "org.eclipse.jdt.internal.ui.propertiesfileeditor." //$NON-NLS-1$
          + "PropertiesFileEditor"; //$NON-NLS-1$

    /** Token to replace in a regular expression with a bundle name. */
    private static final String TOKEN_BUNDLE_NAME = "BUNDLENAME"; //$NON-NLS-1$
    /** Token to replace in a regular expression with a file extension. */
    private static final String TOKEN_FILE_EXTENSION = 
            "FILEEXTENSION"; //$NON-NLS-1$
    /** Regex to match a properties file. */
    private static final String PROPERTIES_FILE_REGEX = 
            "^(" + TOKEN_BUNDLE_NAME + ")"  //$NON-NLS-1$//$NON-NLS-2$
          + "((_[a-z]{2,3})|(_[a-z]{2,3}_[A-Z]{2})" //$NON-NLS-1$
          + "|(_[a-z]{2,3}_[A-Z]{2}_\\w*))?(\\." //$NON-NLS-1$
          + TOKEN_FILE_EXTENSION + ")$"; //$NON-NLS-1$
    

    /*
     * Common members of ResourceFactories
     */
    
    /**
     * A sorted map of {@link SourceEditor}s.
     * Sorted by key (Locale).
     */
    private Map<Locale,SourceEditor> sourceEditors = new TreeMap<Locale,SourceEditor>(new Comparator<Locale>() {
		@Override
		public int compare(Locale obj1, Locale obj2) {
               String displayName1 = UIUtils.getDisplayName(obj1);
               String displayName2 = UIUtils.getDisplayName(obj2);
               return displayName1.compareToIgnoreCase(displayName2);
		}
    });
    	
    /**
     * The {@link PropertiesFileCreator} used to create new files.
     */
    private PropertiesFileCreator propertiesFileCreator;
    /**
     * The associated editor site.
     */
    private IEditorSite site;
    /**
     * The displayname
     */
    private String displayName;
    
    /* (non-Javadoc)
	 * @see com.essiembre.eclipse.rbe.ui.editor.resources.IResourceFactory#getEditorDisplayName()
	 */
    public String getEditorDisplayName() {
    	return displayName;
    }
    /**
     * Sets the editor display name of this factory.
     * @param displayName The display name to set.
     * @see #getEditorDisplayName()
     */
    protected void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
    
    /* (non-Javadoc)
	 * @see com.essiembre.eclipse.rbe.ui.editor.resources.IResourceFactory#getSourceEditors()
	 */
    public SourceEditor[] getSourceEditors() {
    	SourceEditor[] editors = new SourceEditor[sourceEditors.values().size()];
    	int i = 0;
    	for (Iterator<SourceEditor> it = sourceEditors.values().iterator(); it.hasNext();) {
			SourceEditor obj = it.next();
			editors[i] = obj;
			i++;
		}
        return editors;
    }

    /* (non-Javadoc)
	 * @see com.essiembre.eclipse.rbe.ui.editor.resources.IResourceFactory#addResource(org.eclipse.core.resources.IResource, java.util.Locale)
	 */
    public SourceEditor addResource(IResource resource, Locale locale) throws PartInitException {
        if (sourceEditors.containsKey(locale))
            throw new IllegalArgumentException("ResourceFactory already contains a resource for locale "+locale);
        SourceEditor editor = createEditor(site, resource, locale);
        addSourceEditor(editor.getLocale(), editor);
        return editor;
    }
    
	protected void addSourceEditor(Locale locale, SourceEditor sourceEditor) {
		sourceEditors.put(locale, sourceEditor);
	}

	protected void setSite(IEditorSite site) {
		this.site = site;
	}
	protected IEditorSite getSite() {
		return site;
	}
    /* (non-Javadoc)
	 * @see com.essiembre.eclipse.rbe.ui.editor.resources.IResourceFactory#getPropertiesFileCreator()
	 */
    public PropertiesFileCreator getPropertiesFileCreator() {
    	return propertiesFileCreator;
    }
    protected void setPropertiesFileCreator(PropertiesFileCreator fileCreator) {
    	this.propertiesFileCreator = fileCreator;
    }
    
    
    /* (non-Javadoc)
	 * @see com.essiembre.eclipse.rbe.ui.editor.resources.IResourceFactory#isResponsible(org.eclipse.core.resources.IFile)
	 */
    public abstract boolean isResponsible(IFile file) throws CoreException;
    
    /* (non-Javadoc)
	 * @see com.essiembre.eclipse.rbe.ui.editor.resources.IResourceFactory#init(org.eclipse.ui.IEditorSite, org.eclipse.core.resources.IFile)
	 */
    public abstract void init(IEditorSite site, IFile file) throws CoreException;
    
    
//    /**
//     * Creates a resource factory based on given arguments.
//     * @param site eclipse editor site
//     * @param file file used to create factory
//     * @return resource factory
//     * @throws CoreException problem creating factory
//     */
//    public static ResourceFactory createFactory(IEditorSite site, IFile file)
//            throws CoreException {
//        if (isNLResource(file)) {
//            return new NLResourceFactory(site, file);
//        }
//        return new StandardResourceFactory(site, file);
//    }
//
    
    
    /**
     * Creates a resource factory based on given arguments.
     * @param site eclipse editor site
     * @param file file used to create factory
     * @return An initialized resource factory, or <code>null</code> if no responsible one could be found
     * @throws CoreException problem creating factory
     */
    public static IResourceFactory createFactory(IEditorSite site, IFile file)
            throws CoreException {
    	IResourceFactory[] factories = ResourceFactoryDescriptor.getContributedResourceFactories();
    	for (int i = 0; i < factories.length; i++) {
    		IResourceFactory factory = factories[i];
			if (factory.isResponsible(file)) {
				factory.init(site, file);
				return factory;
			}
		}
        return null;
    }
    
    /**
     * Creates a resource factory based on given arguments and excluding
     * factories of the given class.
     * <p>
     * This might be used to get the {@link SourceEditor}s from 
     * other factories while initializing an other factory.
     * </p>
     * @param site eclipse editor site
     * @param file file used to create factory
     * @param childFactoryClass The class of factory to exclude.
     * @return An initialized resource factory, or <code>null</code> if no responsible one could be found
     * @throws CoreException problem creating factory
     */
    public static IResourceFactory createParentFactory(IEditorSite site, IFile file, Class<?> childFactoryClass)
            throws CoreException {
    	IResourceFactory[] factories = ResourceFactoryDescriptor.getContributedResourceFactories();
    	for (int i = 0; i < factories.length; i++) {
    		IResourceFactory factory = factories[i];
			if (!factory.getClass().equals(childFactoryClass) && factory.isResponsible(file)) {
				factory.init(site, file);
				return factory;
			}
		}
        return null;
    }
    
	/**
     * Parses the specified bundle name and returns the locale.
     * @param resource the resource
     * @return the locale or null if none
     */
	protected static Locale parseBundleName(IResource resource) {
        // Build local title
        String regex = ResourceFactory.getPropertiesFileRegEx(resource);
        String localeText = resource.getName().replaceFirst(regex, "$2"); //$NON-NLS-1$
        StringTokenizer tokens = new StringTokenizer(localeText, "_"); //$NON-NLS-1$
		List<String> localeSections = new ArrayList<String>();
		while (tokens.hasMoreTokens()) {
		    localeSections.add(tokens.nextToken());
		}
		Locale locale = null;
		switch (localeSections.size()) {
		case 1:
		    locale = new Locale(localeSections.get(0));
		    break;
		case 2:
		    locale = new Locale(localeSections.get(0), localeSections.get(1));
		    break;
		case 3:
		    locale = new Locale(localeSections.get(0), localeSections.get(1), localeSections.get(2));
		    break;
		default:
		    break;
		}
		return locale;
	}
    
    protected SourceEditor createEditor(IEditorSite site, IResource resource, Locale locale) throws PartInitException {
        ITextEditor textEditor = null;
        if (getSite() == null) setSite(site);
        if (resource != null && resource instanceof IFile) {
            IEditorInput newEditorInput = new FileEditorInput((IFile) resource);
            try {
                // Use PropertiesFileEditor if available
                textEditor = (TextEditor) Class.forName(PROPERTIES_EDITOR_CLASS_NAME).newInstance();
            } catch (Exception e) {
                // Use default editor otherwise
                textEditor = new TextEditor();
            }
            textEditor.init(site, newEditorInput);
            
            try {
               /* ugly fix for a memory leak: 
                * ITextEditor.init(.) Javadoc states: "Clients must not call this method."
                * but we do in ResourceFactory.createEditor(.), and the way we set-up everything, we have to.
                * Since duplicate calls to init(.) create a memory leak, due to a zombie ActivationListener registered in 
                * AbstractTextEditor, we dispose the first ActivationListener we just unintentionally created */
               Field field = AbstractTextEditor.class.getDeclaredField("fActivationListener");
               field.setAccessible(true); // enable access to the method - ...hackity hack
               Object activationListener = field.get(textEditor);
               Method disposeMethod = activationListener.getClass().getMethod("dispose");
               disposeMethod.setAccessible(true);
               disposeMethod.invoke(activationListener);
            }
            catch(Exception e) {
               System.err.println("Failed to apply memory leak work around");
            }
        }
        if (textEditor != null) {
            return new SourceEditor(textEditor, locale, (IFile) resource);
        }
        return null;
    }
    
    protected static String getBundleName(IResource file) {
        String name = file.getName();
        String regex = "^(.*?)" //$NON-NLS-1$
                + "((_[a-z]{2,3})|(_[a-z]{2,3}_[A-Z]{2})" //$NON-NLS-1$
                + "|(_[a-z]{2,3}_[A-Z]{2}_\\w*))?(\\." //$NON-NLS-1$
                + file.getFileExtension() + ")$"; //$NON-NLS-1$
        return name.replaceFirst(regex, "$1"); //$NON-NLS-1$
    }
    
    protected static String getDisplayName(IResource file) {
    	if (file instanceof IFile)
    		return getBundleName(file) + "[...]." + file.getFileExtension();
    	else
    		return getBundleName(file);
    }
    
    protected static String getPropertiesFileRegEx(IResource file) {
        String bundleName = getBundleName(file);
        return PROPERTIES_FILE_REGEX.replaceFirst(
                TOKEN_BUNDLE_NAME, bundleName).replaceFirst(
                        TOKEN_FILE_EXTENSION, file.getFileExtension());
    }

	/**
	 * Returns the resource bundle file resources that match the specified file name.
	 *  
	 * @param file the file to match
	 * @return array of file resources, empty if none matches
	 * @throws CoreException
	 */
	protected static IFile[] getResources(IFile file) throws CoreException {
	    
	    String regex = ResourceFactory.getPropertiesFileRegEx(file);
	    IResource[] resources = file.getParent().members();
	    Collection<IFile> validResources = new ArrayList<IFile>();
	    for (int i = 0; i < resources.length; i++) {
	        IResource resource = resources[i];
	        String resourceName = resource.getName();
	        if (resource instanceof IFile && resourceName.matches(regex)) {
	            validResources.add((IFile)resource);
	        }
	    }
	    return validResources.toArray(new IFile[validResources.size()]);
	}
	
}
