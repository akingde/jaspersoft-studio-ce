/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.property.dataset.dialog;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.IQueryDesigner;
import com.jaspersoft.studio.data.designer.QueryDesigner;
import com.jaspersoft.studio.utils.UIUtils;

public class QDesignerFactory {
	private Composite parent;
	private Composite toolbar;
	private Map<String, IQueryDesigner> languageMap = new HashMap<String, IQueryDesigner>();
	private Map<Class<? extends IQueryDesigner>, IQueryDesigner> classmap = new HashMap<Class<? extends IQueryDesigner>, IQueryDesigner>();
	private DataQueryAdapters dqa;

	public QDesignerFactory(Composite parent, Composite toolbar, DataQueryAdapters dqa) {
		this.parent = parent;
		this.toolbar = toolbar;
		this.dqa = dqa;
		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(
				"com.jaspersoft.studio", "queryDesigner"); //$NON-NLS-1$ //$NON-NLS-2$
		for (IConfigurationElement e : config) {
			try {
				String lang = e.getAttribute("language");//$NON-NLS-1$
				IQueryDesigner qd = (IQueryDesigner) e.createExecutableExtension("QueryDesignerClass"); //$NON-NLS-1$
				qd.setParentContainer(dqa);
				addDesigner(lang, qd);
			} catch (CoreException ex) {
				UIUtils.showError(ex);
			}
		}
	}

	public void dispose() {
		for (IQueryDesigner qd : languageMap.values())
			qd.dispose();
	}

	public IQueryDesigner getDesigner(String lang) {
		IQueryDesigner qd = languageMap.get(lang.toLowerCase());
		if (qd == null) {
			qd = addDesigner(lang, getDefaultDesigner());
		}
		return qd;
	}

	protected QueryDesigner getDefaultDesigner() {
		QueryDesigner defaultDesigner = new QueryDesigner();
		defaultDesigner.setParentContainer(dqa);
		return defaultDesigner;
	}

	private IQueryDesigner addDesigner(String lang, IQueryDesigner qd) {
		IQueryDesigner iqd = classmap.get(qd.getClass());
		if (iqd == null) {
			iqd = qd;
			try {
				iqd.createToolbar(toolbar);
				iqd.createControl(parent);
			} catch (Exception e) {
				e.printStackTrace();
				addDesigner(lang, getDefaultDesigner());
			}
			classmap.put(qd.getClass(), iqd);
		}
		languageMap.put(lang.toLowerCase(), iqd);
		return iqd;
	}
}
