/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.toolbar;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.MDataAdapters;
import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
import com.jaspersoft.studio.data.widget.DataAdapterAction;
import com.jaspersoft.studio.data.widget.IDataAdapterRunnable;
import com.jaspersoft.studio.editor.preview.PreviewContainer;
import com.jaspersoft.studio.editor.preview.actions.RunStopAction;
import com.jaspersoft.studio.editor.preview.datasnapshot.DataSnapshotManager;
import com.jaspersoft.studio.editor.preview.datasnapshot.JSSColumnDataCacheHandler;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.DesignerPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.cache.DataCacheHandler;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRQuery;
import net.sf.jasperreports.engine.SimpleReportContext;

public class PreviewTopToolBarManager extends ATopToolBarManager {
	private ADataAdapterStorage[] adapters;

	public PreviewTopToolBarManager(PreviewContainer container, Composite parent, ADataAdapterStorage[] adapters) {
		super(container, parent);
		dataSourceWidget.setDataAdapterStorages(adapters);
		this.adapters = adapters;

	}

	public void dispose() {
		JaspersoftStudioPlugin.getInstance().removePreferenceListener(prefListener);
	}

	private DataAdapterAction dataSourceWidget;
	private RunStopAction vexecAction;
	private Action iconAction;
	private IPropertyChangeListener prefListener = null;

	protected void fillToolbar(IToolBarManager tbManager) {
		PreviewContainer pvcont = (PreviewContainer) container;

		if (iconAction == null) {
			iconAction = new IconAction();
		}
		tbManager.add(iconAction);
		if (dataSourceWidget == null) {
			dataSourceWidget = new DataAdapterAction((IDataAdapterRunnable) container, adapters);
			if (prefListener == null)
				prefListener = new IPropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent event) {
						if (event.getProperty().equals(DesignerPreferencePage.P_DAFILTER))
							refreshDataAdapters();
					}
				};
			JaspersoftStudioPlugin.getInstance().addPreferenceListener(prefListener);
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
		private MenuItem itemCache;
		private MenuItem itemSave;
		private MenuItem itemFile;
		private MenuItem itemFilter;

		@Override
		public void dispose() {
			if (menu != null)
				menu.dispose();
		}

		@Override
		public Menu getMenu(final Control parent) {
			if (menu == null) {
				menu = new Menu(parent);
				itemCache = new MenuItem(menu, SWT.CHECK);
				itemCache.setText(Messages.PreviewTopToolBarManager_1);

				new MenuItem(menu, SWT.SEPARATOR);

				itemSave = new MenuItem(menu, SWT.CHECK);
				itemSave.setText(Messages.PreviewTopToolBarManager_2);
				itemSave.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						JasperReportsConfiguration jContext = container.getJrContext();
						JasperReportsConfiguration jrContext = jContext;
						setupItemSaveMenu(menu.getShell());
						if (itemSave.getSelection()) {
							SimpleReportContext reportContext = (SimpleReportContext) jContext.getJRParameters()
									.get(JRParameter.REPORT_CONTEXT);
							if (reportContext != null) {
								String fname = (String) jrContext.getMap().get(DataSnapshotManager.SAVE_SNAPSHOT);
								if (fname != null) {
									reportContext.setParameterValue(DataSnapshotManager.SAVE_SNAPSHOT, fname);
									jContext.getMap().put(DataSnapshotManager.SAVE_SNAPSHOT, fname);
									jContext.getJasperDesign().setProperty(DataSnapshotManager.SAVE_SNAPSHOT, fname);
									UIUtils.showInformation("Data will be saved to:\n" + fname);
								} else
									doSelectDataSnapshotFile(menu.getShell());
							}
						} else {
							DataSnapshotManager.removeSnapshotFile(jrContext.getJRParameters());
							jContext.getJasperDesign().removeProperty(DataSnapshotManager.SAVE_SNAPSHOT);
							itemFile.dispose();
						}

					}
				});

				itemCache.addSelectionListener(new SelectionAdapter() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						boolean on = itemCache.getSelection();
						JasperReportsConfiguration jrContext = container.getJrContext();
						DataSnapshotManager.setCaching(jrContext.getJRParameters(), on);
						Map<String, Object> hm = jrContext.getJRParameters();
						SimpleReportContext reportContext = (SimpleReportContext) hm.get(JRParameter.REPORT_CONTEXT);
						if (reportContext != null) {
							if (on) {
								String fname = (String) jrContext.getMap().get(DataSnapshotManager.SAVE_SNAPSHOT);
								reportContext.setParameterValue(DataSnapshotManager.SAVE_SNAPSHOT, fname);
							} else {
								Map<String, Object> pv = reportContext.getParameterValues();
								String fname = (String) pv.get(DataSnapshotManager.SAVE_SNAPSHOT);
								if (fname != null)
									jrContext.getMap().put(DataSnapshotManager.SAVE_SNAPSHOT, fname);
								pv.remove(DataSnapshotManager.SAVE_SNAPSHOT);
							}
						}
						if (!on)
							container.getJrContext().getJasperDesign()
									.removeProperty(DataSnapshotManager.SAVE_SNAPSHOT);
					}
				});

				new MenuItem(menu, SWT.SEPARATOR);

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
								DataSnapshotManager.loadSnapshot(container.getJrContext(), fname);
								container.getJrContext().getJasperDesign()
										.setProperty(DataSnapshotManager.SAVE_SNAPSHOT, fname);
								vexecAction.run();
							} catch (Exception e1) {
								UIUtils.showError(e1);
							}
						}
					}
				});

				new MenuItem(menu, SWT.SEPARATOR);

				itemFilter = new MenuItem(menu, SWT.CHECK);
				itemFilter.setText("Filter Data Adapters By Report Language");
				itemFilter.addSelectionListener(new SelectionAdapter() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						try {
							JasperReportsConfiguration jrContext = container.getJrContext();
							jrContext.getPrefStore().setValue(DesignerPreferencePage.P_DAFILTER,
									itemFilter.getSelection() ? "da" : "all");
							jrContext.getPrefStore().save();
						} catch (IOException e1) {
							UIUtils.showError(e1);
						}
						// IFile f = (IFile) jrContext.get(FileUtils.KEY_FILE);
						// if (f != null) {
						// PreferenceDialog pref =
						// PreferencesUtil.createPropertyDialogOn(UIUtils.getShell(), f,
						// DesignerPreferencePage.PAGE_ID, null, null);
						// if (pref != null && pref.open() == Dialog.OK) {
						// refreshDataAdapters();
						// }
						// }
					}
				});

			}
			JasperReportsConfiguration jrContext = container.getJrContext();
			String daFilter = jrContext.getPrefStore().getString(DesignerPreferencePage.P_DAFILTER);
			itemFilter.setSelection(daFilter != null && daFilter.equals("da"));

			itemCache.setSelection(DataSnapshotManager.snapshotExists(container.getJrContext().getJRParameters()));
			itemSave.setSelection(itemCache.getSelection()
					&& DataSnapshotManager.snapshotFileExists(container.getJrContext().getJRParameters()));
			setupItemSaveMenu(menu.getShell());

			return menu;
		}

		private void setupItemSaveMenu(final Shell shell) {
			if (itemSave.getSelection()) {
				itemSave.setText(Messages.PreviewTopToolBarManager_2);

				if (itemFile == null || itemFile.isDisposed()) {
					itemFile = new MenuItem(menu, SWT.PUSH, ArrayUtils.indexOf(menu.getItems(), itemSave) + 1);
					itemFile.setText("Select Data Snapshot File ...");
					itemFile.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							doSelectDataSnapshotFile(shell);
						}
					});
				}
			} else {
				if (itemFile != null && !itemFile.isDisposed())
					itemFile.dispose();
				itemSave.setText(Messages.PreviewTopToolBarManager_2 + " ...");
			}
		}

		@Override
		public Menu getMenu(Menu parent) {
			return null;
		}

		protected void doSelectDataSnapshotFile(final Shell shell) {
			FileDialog fd = new FileDialog(shell, SWT.SAVE);
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
						creationTimestamp = ((JSSColumnDataCacheHandler) cacheHandler).getCreationTimestamp();
					DataSnapshotManager.saveSnapshot(fname, creationTimestamp, cacheHandler.getDataSnapshot());
				}
				SimpleReportContext reportContext = (SimpleReportContext) hm.get(JRParameter.REPORT_CONTEXT);
				reportContext.setParameterValue(DataSnapshotManager.SAVE_SNAPSHOT, fname);
				container.getJrContext().getMap().put(DataSnapshotManager.SAVE_SNAPSHOT, fname);
				container.getJrContext().getJasperDesign().setProperty(DataSnapshotManager.SAVE_SNAPSHOT, fname);
				itemCache.setSelection(true);
			} else
				itemSave.setSelection(DataSnapshotManager.snapshotExists(hm));
		}
	}

	public void refreshDataAdapters() {
		JasperReportsConfiguration jrContext = container.getJrContext();
		String filter = jrContext.getProperty(DesignerPreferencePage.P_DAFILTER);
		if (filter != null && filter.equals("da") && jrContext.getJasperDesign() != null) { //$NON-NLS-1$
			JRQuery q = jrContext.getJasperDesign().getQuery();
			dataSourceWidget.setLanguage(q != null ? q.getLanguage() : null);
		} else
			dataSourceWidget.setLanguage(null);
		if (!getTopToolBar().isDisposed())
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
