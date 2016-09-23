/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.toolbar;

import java.io.File;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.dialogs.PreferencesUtil;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.MDataAdapters;
import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
import com.jaspersoft.studio.data.widget.DataAdapterAction;
import com.jaspersoft.studio.data.widget.IDataAdapterRunnable;
import com.jaspersoft.studio.editor.preview.PreviewContainer;
import com.jaspersoft.studio.editor.preview.actions.RunStopAction;
import com.jaspersoft.studio.editor.preview.view.control.ReportControler;
import com.jaspersoft.studio.preferences.DesignerPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.cache.ColumnDataCacheHandler;
import net.sf.jasperreports.data.cache.DataCacheHandler;
import net.sf.jasperreports.data.cache.DataSnapshot;
import net.sf.jasperreports.data.cache.PopulatedSnapshotCacheHandler;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRQuery;
import net.sf.jasperreports.engine.SimpleReportContext;
import net.sf.jasperreports.engine.util.JRLoader;

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

	class IconAction extends Action implements IMenuCreator {
		public IconAction() {
			super();
			setId("iconAction");
			setEnabled(true);
			setImageDescriptor(MDataAdapters.getIconDescriptor().getIcon16());
			setDisabledImageDescriptor(MDataAdapters.getIconDescriptor().getIcon16());
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

		@Override
		public void runWithEvent(Event event) {
			Point point = ((ToolItem) event.widget).getParent().toDisplay(new Point(event.x, event.y));
			menu = getMenu(((ToolItem) event.widget).getParent());
			menu.setLocation(point.x, point.y);
			menu.setVisible(true);
		}

		private Menu menu;

		@Override
		public void dispose() {
			if (menu != null)
				menu.dispose();
		}

		@Override
		public Menu getMenu(final Control parent) {
			if (menu == null) {
				menu = new Menu(parent);
				final MenuItem itemCache = new MenuItem(menu, SWT.CHECK);
				itemCache.setText("Cache Data In Memory");
				itemCache.addSelectionListener(new SelectionAdapter() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						setupCacheInMemory(itemCache);
					}
				});
				final MenuItem itemSave = new MenuItem(menu, SWT.CHECK);
				itemSave.setText("Save Data To File");
				itemSave.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						FileDialog fd = new FileDialog(parent.getShell(), SWT.SAVE);
						fd.setText("Select where to save the snapshot");
						fd.setFileName("snapshot.jrds");
						fd.setOverwrite(true);
						fd.setFilterExtensions(new String[] { "*.jrds", "*.*" });
						String fname = fd.open();
						if (fname != null) {
							itemCache.setSelection(true);
							setupCacheInMemory(itemCache);
							Map<String, Object> hm = container.getJrContext().getJRParameters();
							SimpleReportContext reportContext = (SimpleReportContext) hm.get(JRParameter.REPORT_CONTEXT);
							if (itemSave.getSelection())
								reportContext.setParameterValue(ReportControler.SAVE_SNAPSHOT, fname);
							else
								reportContext.getParameterValues().remove(ReportControler.SAVE_SNAPSHOT);
						}
					}
				});

				final MenuItem itemLoad = new MenuItem(menu, SWT.PUSH);
				itemLoad.setText("Load Data From File");
				itemLoad.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						FileDialog fd = new FileDialog(parent.getShell(), SWT.OPEN);
						fd.setText("Select data snapshot to use when running report");
						fd.setFilterExtensions(new String[] { "*.jrds", "*.*" });
						String fname = fd.open();
						if (fname != null) {
							itemCache.setSelection(true);
							setupCacheInMemory(itemCache);

							Map<String, Object> hm = container.getJrContext().getJRParameters();
							SimpleReportContext reportContext = (SimpleReportContext) hm.get(JRParameter.REPORT_CONTEXT);

							try {
								reportContext.setParameterValue(DataCacheHandler.PARAMETER_DATA_CACHE_HANDLER,
										new PopulatedSnapshotCacheHandler((DataSnapshot) JRLoader.loadObject(new File(fname))));
							} catch (JRException e1) {
								UIUtils.showError(e1);
							}
						}
					}
				});

				new MenuItem(menu, SWT.SEPARATOR);

				MenuItem item = new MenuItem(menu, SWT.PUSH);
				item.setText("Preferences");
				item.addSelectionListener(new SelectionAdapter() {

					@Override
					public void widgetSelected(SelectionEvent e) {
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
				});

			}
			return menu;
		}

		@Override
		public Menu getMenu(Menu parent) {
			return null;
		}

		protected void setupCacheInMemory(final MenuItem itemCache) {
			Map<String, Object> hm = container.getJrContext().getJRParameters();
			if (itemCache.getSelection()) {
				SimpleReportContext reportContext = new SimpleReportContext();
				ColumnDataCacheHandler cacheHandler = new ColumnDataCacheHandler();
				reportContext.setParameterValue(DataCacheHandler.PARAMETER_DATA_CACHE_HANDLER, cacheHandler);
				hm.put(JRParameter.REPORT_CONTEXT, reportContext);
			} else
				hm.remove(JRParameter.REPORT_CONTEXT);
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
