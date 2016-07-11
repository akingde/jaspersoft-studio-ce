/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.toolbar;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.PreferencesUtil;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.MDataAdapters;
import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
import com.jaspersoft.studio.data.widget.DataAdapterAction;
import com.jaspersoft.studio.data.widget.IDataAdapterRunnable;
import com.jaspersoft.studio.editor.preview.PreviewContainer;
import com.jaspersoft.studio.editor.preview.actions.RunStopAction;
import com.jaspersoft.studio.preferences.DesignerPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRQuery;

public class PreviewTopToolBarManager extends ATopToolBarManager {
	private ADataAdapterStorage[] adapters;

	public PreviewTopToolBarManager(PreviewContainer container, Composite parent, ADataAdapterStorage[] adapters) {
		super(container, parent);
		dataSourceWidget.setDataAdapterStorages(adapters);
		this.adapters = adapters;
	}

	private DataAdapterAction dataSourceWidget;
	private RunStopAction vexecAction;
	private Action iconAction;

	protected void fillToolbar(IToolBarManager tbManager) {
		PreviewContainer pvcont = (PreviewContainer) container;

		if (iconAction == null) {
			iconAction = new IconAction();
		}
		tbManager.add(iconAction);
		if (dataSourceWidget == null) {
			dataSourceWidget = new DataAdapterAction((IDataAdapterRunnable) container, adapters);
		}
		tbManager.add(dataSourceWidget);

		if (vexecAction == null)
			vexecAction = new RunStopAction(pvcont);
		tbManager.add(vexecAction);

	}

	class IconAction extends Action {
		public IconAction() {
			super();
			setEnabled(true);
			setImageDescriptor(MDataAdapters.getIconDescriptor().getIcon16());
			setDisabledImageDescriptor(MDataAdapters.getIconDescriptor().getIcon16());
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

		@Override
		public void run() {
			JasperReportsConfiguration jrContext = container.getJrContext();
			IFile f = (IFile) jrContext.get(FileUtils.KEY_FILE);
			if (f != null) {
				PreferenceDialog pref = PreferencesUtil.createPropertyDialogOn(UIUtils.getShell(), f.getProject(),
						DesignerPreferencePage.PAGE_ID, null, null);
				if (pref != null && pref.open() == Dialog.OK) {
					refreshDataAdapters();
				}
			}
		}
	}

	public void refreshDataAdapters() {
		JasperReportsConfiguration jrContext = container.getJrContext();
		String filter = jrContext.getProperty(DesignerPreferencePage.P_DAFILTER);
		if (filter != null && filter.equals("da")) {
			JRQuery q = jrContext.getJasperDesign().getQuery();
			dataSourceWidget.setLanguage(q != null ? q.getLanguage() : null);
		} else
			dataSourceWidget.setLanguage(null);
		dataSourceWidget.getMenu(getTopToolBar());
	}

	public DataAdapterAction getDataSourceWidget() {
		return dataSourceWidget;
	}

	public void setDataAdapters(String daName) {
		for (ADataAdapterStorage da : adapters) {
			DataAdapterDescriptor descriptor = da.findDataAdapter(daName);
			if (descriptor != null) {
				dataSourceWidget.setSelected(descriptor);
				break;
			}
		}
	}
}
