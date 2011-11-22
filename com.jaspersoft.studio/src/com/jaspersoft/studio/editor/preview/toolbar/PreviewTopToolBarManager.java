/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.preview.toolbar;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.widget.DatasourceComboItem;
import com.jaspersoft.studio.data.widget.IDataAdapterRunnable;
import com.jaspersoft.studio.editor.preview.PreviewContainer;
import com.jaspersoft.studio.editor.preview.actions.RunStopAction;

public class PreviewTopToolBarManager extends ATopToolBarManager {

	public PreviewTopToolBarManager(PreviewContainer container, Composite parent) {
		super(container, parent);
	}

	private DatasourceComboItem dataSourceWidget;
	private RunStopAction vexecAction;

	protected void fillToolbar(IToolBarManager tbManager) {
		PreviewContainer pvcont = (PreviewContainer) container;

		if (dataSourceWidget == null)
			dataSourceWidget = new DatasourceComboItem((IDataAdapterRunnable) container);
		tbManager.add(dataSourceWidget);

		if (vexecAction == null)
			vexecAction = new RunStopAction(pvcont);
		tbManager.add(vexecAction);

	}

	public DatasourceComboItem getDataSourceWidget() {
		return dataSourceWidget;
	}

	public void setDataAdapters(String strda) {
		dataSourceWidget.setSelected(DataAdapterManager.findDataAdapter(strda));
	}
}
