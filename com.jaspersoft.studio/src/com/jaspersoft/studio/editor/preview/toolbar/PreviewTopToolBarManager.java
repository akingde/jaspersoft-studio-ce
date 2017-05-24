/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.toolbar;

import java.io.File;
import java.util.Date;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
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
import com.jaspersoft.studio.editor.preview.datasnapshot.DataSnapshotManager;
import com.jaspersoft.studio.editor.preview.datasnapshot.JSSColumnDataCacheHandler;
import com.jaspersoft.studio.editor.preview.datasnapshot.JssDataSnapshot;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.DesignerPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.cache.DataCacheHandler;
import net.sf.jasperreports.data.cache.DataSnapshot;
import net.sf.jasperreports.data.cache.PopulatedSnapshotCacheHandler;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRQuery;
import net.sf.jasperreports.engine.ReportContext;
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
			setId("iconAction"); //$NON-NLS-1$
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
				itemCache.setText(Messages.PreviewTopToolBarManager_1);

				final MenuItem itemSave = new MenuItem(menu, SWT.CHECK);
				itemSave.setText(Messages.PreviewTopToolBarManager_2);

				itemCache.addSelectionListener(new SelectionAdapter() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						boolean on = itemCache.getSelection();
						DataSnapshotManager.setCaching(container.getJrContext().getJRParameters(), on);
						if (!on) {
							Map<String, Object> hm = container.getJrContext().getJRParameters();
							SimpleReportContext reportContext = (SimpleReportContext) hm
									.get(JRParameter.REPORT_CONTEXT);
							if (reportContext != null)
								reportContext.getParameterValues().remove(DataSnapshotManager.SAVE_SNAPSHOT);
						}
					}
				});
				itemSave.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						FileDialog fd = new FileDialog(parent.getShell(), SWT.SAVE);
						fd.setText(Messages.PreviewTopToolBarManager_3);
						String sname = "snapshot.jrds"; //$NON-NLS-1$
						IFile f = (IFile) container.getJrContext().get(FileUtils.KEY_FILE);
						if (f != null)
							sname = FilenameUtils.getBaseName(f.getName()) + ".jrds"; //$NON-NLS-1$
						fd.setFilterPath(f.getParent().getLocation().toOSString());
						fd.setFileName(sname);
						fd.setOverwrite(true);
						fd.setFilterExtensions(new String[] { "*.jrds", "*.*" }); //$NON-NLS-1$ //$NON-NLS-2$
						final String fname = fd.open();
						Map<String, Object> hm = container.getJrContext().getJRParameters();
						if (fname != null) {
							itemCache.setSelection(true);
							DataCacheHandler cacheHandler = DataSnapshotManager.setDataSnapshot(hm, false);
							if (cacheHandler.getDataSnapshot() != null) {
								Date creationTimestamp = new Date();
								if (cacheHandler instanceof JSSColumnDataCacheHandler)
									creationTimestamp = ((JSSColumnDataCacheHandler) cacheHandler)
											.getCreationTimestamp();
								DataSnapshotManager.saveSnapshot(fname, creationTimestamp,
										cacheHandler.getDataSnapshot());
							}
							SimpleReportContext reportContext = (SimpleReportContext) hm
									.get(JRParameter.REPORT_CONTEXT);
							reportContext.setParameterValue(DataSnapshotManager.SAVE_SNAPSHOT, fname);
							itemCache.setSelection(true);
						} else
							itemSave.setSelection(DataSnapshotManager.snapshotExists(hm));
					}
				});

				final MenuItem itemLoad = new MenuItem(menu, SWT.PUSH);
				itemLoad.setText(Messages.PreviewTopToolBarManager_8);
				itemLoad.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						FileDialog fd = new FileDialog(parent.getShell(), SWT.OPEN);
						fd.setText(Messages.PreviewTopToolBarManager_9);
						fd.setFilterExtensions(new String[] { "*.jrds", "*.*" }); //$NON-NLS-1$ //$NON-NLS-2$
						String fname = fd.open();
						if (fname != null) {
							itemCache.setSelection(true);
							try {
								Map<String, Object> hm = container.getJrContext().getJRParameters();
								Object obj = JRLoader.loadObject(new File(fname));
								if (obj instanceof JssDataSnapshot) {
									JssDataSnapshot snapshot = (JssDataSnapshot) obj;
									DataSnapshotManager.setDataSnapshot(hm,
											new JSSColumnDataCacheHandler(((JssDataSnapshot) snapshot).getSnapshot(),
													((JssDataSnapshot) snapshot).getCreationTimestamp()),
											false);
								} else if (obj instanceof DataSnapshot)
									DataSnapshotManager.setDataSnapshot(hm,
											new PopulatedSnapshotCacheHandler((DataSnapshot) obj), false);
								SimpleReportContext reportContext = (SimpleReportContext) hm
										.get(JRParameter.REPORT_CONTEXT);
								reportContext.setParameterValue(DataSnapshotManager.SAVE_SNAPSHOT, fname);
								vexecAction.run();
							} catch (Exception e1) {
								UIUtils.showError(e1);
							}
						}
					}
				});

				new MenuItem(menu, SWT.SEPARATOR);

				MenuItem item = new MenuItem(menu, SWT.PUSH);
				item.setText(Messages.PreviewTopToolBarManager_12);
				item.addSelectionListener(new SelectionAdapter() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						JasperReportsConfiguration jrContext = container.getJrContext();
						IFile f = (IFile) jrContext.get(FileUtils.KEY_FILE);
						if (f != null) {
							PreferenceDialog pref = PreferencesUtil.createPropertyDialogOn(UIUtils.getShell(), f,
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

	}

	public void refreshDataAdapters() {
		JasperReportsConfiguration jrContext = container.getJrContext();
		String filter = jrContext.getProperty(DesignerPreferencePage.P_DAFILTER);
		if (filter != null && filter.equals("da")) { //$NON-NLS-1$
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
