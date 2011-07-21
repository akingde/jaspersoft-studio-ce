/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.dataset.dialog;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.IQueryDesigner;
import com.jaspersoft.studio.data.QueryDesigner;

public class QDesignerFactory {
	private Composite parent;
	private Map<String, IQueryDesigner> languageMap = new HashMap<String, IQueryDesigner>();
	private Map<Class<? extends IQueryDesigner>, IQueryDesigner> classmap = new HashMap<Class<? extends IQueryDesigner>, IQueryDesigner>();

	public QDesignerFactory(Composite parent) {
		this.parent = parent;
		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(
				"com.jaspersoft.studio", "queryDesigner"); //$NON-NLS-1$ //$NON-NLS-2$
		for (IConfigurationElement e : config) {
			try {
				String lang = e.getAttribute("language");//$NON-NLS-1$
				IQueryDesigner qd = (IQueryDesigner) e.createExecutableExtension("QueryDesignerClass"); //$NON-NLS-1$
				addDesigner(lang, qd);
			} catch (CoreException ex) {
				ex.printStackTrace();
				System.out.println(ex.getMessage());
			}
		}
	}

	public void dispose() {
		for (IQueryDesigner qd : languageMap.values())
			qd.dispose();
	}

	public IQueryDesigner getDesigner(String lang) {
		IQueryDesigner qd = languageMap.get(lang);
		if (qd == null)
			qd = addDesigner(lang, new QueryDesigner());
		return qd;
	}

	private IQueryDesigner addDesigner(String lang, IQueryDesigner qd) {
		IQueryDesigner iqd = classmap.get(qd.getClass());
		if (iqd == null) {
			iqd = qd;
			try {

				iqd.createControl(parent);
			} catch (Exception e) {
				e.printStackTrace();
				addDesigner(lang, new QueryDesigner());
			}
			classmap.put(qd.getClass(), iqd);
		}
		languageMap.put(lang, iqd);
		return iqd;
	}
}
