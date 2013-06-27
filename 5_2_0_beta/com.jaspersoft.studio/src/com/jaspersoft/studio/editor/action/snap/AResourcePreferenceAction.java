/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.snap;

import net.sf.jasperreports.eclipse.viewer.IEditorContributor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class AResourcePreferenceAction extends Action {

	protected JasperReportsConfiguration jrConfig;
	protected ScopedPreferenceStore store;
	protected final static String pageID = JaspersoftStudioPlugin.getUniqueIdentifier();
	protected IFile file;

	/**
	 * Constructor
	 * 
	 * @param diagramViewer
	 *          the GraphicalViewer whose grid enablement and visibility properties are to be toggled
	 */
	public AResourcePreferenceAction(JasperReportsConfiguration jrConfig) {
		super();
		this.jrConfig = jrConfig;
		getStore();
	}

	public AResourcePreferenceAction(String text, JasperReportsConfiguration jrConfig, int style) {
		super(text, style);
		this.jrConfig = jrConfig;
		getStore();
	}

	protected ScopedPreferenceStore getStore() {
		file = (IFile) jrConfig.get(IEditorContributor.KEY_FILE);
		store = JaspersoftStudioPlugin.getInstance().getPreferenceStore(file, pageID);
		return store;
	}

	/**
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		try {
			getStore();
			file.setPersistentProperty(new QualifiedName(pageID, FieldEditorOverlayPage.USERESOURCESETTINGS),
					FieldEditorOverlayPage.RESOURCE);
			doRun();

			store.save();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected abstract void doRun() throws Exception;
}
