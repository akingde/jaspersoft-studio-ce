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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;

import com.jaspersoft.studio.data.IFieldSetter;
import com.jaspersoft.studio.data.IMappingTool;

public class DataMappingFactory {
	private Map<Class<? extends IMappingTool>, IMappingTool> classmap = new HashMap<Class<? extends IMappingTool>, IMappingTool>();

	public DataMappingFactory(CTabFolder tabFolder, IFieldSetter fsetter) {

		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(
				"com.jaspersoft.studio", "mappingTool"); //$NON-NLS-1$ //$NON-NLS-2$
		for (IConfigurationElement e : config) {
			try {
				IMappingTool qd = (IMappingTool) e.createExecutableExtension("MappingToolClass"); //$NON-NLS-1$

				CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
				bptab.setText(qd.getName());

				addDesigner(qd);

				bptab.setControl(qd.createControl(tabFolder));
				qd.setFields(fsetter);
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	public void dispose() {
		for (IMappingTool mt : classmap.values())
			mt.dispose();
	}

	public Collection<IMappingTool> getMappingTools() {
		return classmap.values();
	}

	private IMappingTool addDesigner(IMappingTool qd) {
		IMappingTool iqd = classmap.get(qd.getClass());
		if (iqd == null) {
			iqd = qd;

			classmap.put(qd.getClass(), iqd);
		}
		return iqd;
	}
}
