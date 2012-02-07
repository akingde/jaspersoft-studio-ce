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
package com.jaspersoft.studio.property.dataset.dialog;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.DataAdapterServiceUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRQuery;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignQuery;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.IFieldSetter;
import com.jaspersoft.studio.data.IQueryDesigner;
import com.jaspersoft.studio.data.actions.CreateDataAdapterAction;
import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.data.widget.DatasourceComboItem;
import com.jaspersoft.studio.data.widget.IDataAdapterRunnable;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.SelectionHelper;
import com.jaspersoft.studio.utils.UIUtils;

public abstract class DataQueryAdapters {
	private JRDesignDataset newdataset;
	private Color background;
	private IFile file;

	public DataQueryAdapters(Composite parent, JRDesignDataset newdataset, Color background, IFile file) {
		this.file = file;
		// createToolbar(parent);
		this.newdataset = newdataset;
		if (background != null)
			this.background = background;
		// else
		this.background = parent.getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND);
	}

	public void dispose() {
		qdfactory.dispose();
		dmfactory.dispose();
	}

	private Composite composite;
	private DatasourceComboItem dscombo;
	private Action gFields;
	private Combo langCombo;
	private String[] languages;
	private Composite langComposite;
	private StackLayout langLayout;
	private QDesignerFactory qdfactory;
	private CTabFolder tabFolder;

	public Composite getControl() {
		return composite;
	}

	public Composite getQueryControl() {
		return tabFolder;
	}

	public void setFile(IFile file) {
		this.file = file;
		dscombo.setDataAdapterStorages(DataAdapterManager.getDataAdapter(file));
	}

	public CTabFolder createTop(Composite parent, IFieldSetter fsetter) {
		tabFolder = new CTabFolder(parent, SWT.TOP);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 150;
		tabFolder.setLayoutData(gd);
		// tabFolder.setBackground(background);

		createQuery(tabFolder);
		createMappingTools(tabFolder, fsetter);

		tabFolder.setSelection(0);
		return tabFolder;
	}

	private void createMappingTools(CTabFolder tabFolder, IFieldSetter fsetter) {
		dmfactory = new DataMappingFactory(tabFolder, fsetter);
	}

	private void createQuery(CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText(Messages.DataQueryAdapters_querytab);

		Composite sectionClient = new Composite(tabFolder, SWT.NONE);
		sectionClient.setLayout(new GridLayout(2, false));
		sectionClient.setBackground(background);

		Label label = new Label(sectionClient, SWT.NONE);
		label.setText(Messages.DataQueryAdapters_languagetitle);
		label.setBackground(background);

		langCombo = new Combo(sectionClient, SWT.SINGLE | SWT.BORDER);
		languages = ModelUtils.getQueryLanguages();
		langCombo.setItems(languages);
		langCombo.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				changeLanguage();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		langCombo.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				String lang = langCombo.getText();
				int index = Misc.indexOf(languages, lang);
				if (index < 0) {
					languages[0] = lang;
					langCombo.setItem(0, lang);
					langCombo.select(0);
					changeLanguage();
				}
			}
		});

		langComposite = new Composite(sectionClient, SWT.NONE);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		langComposite.setLayoutData(gd);
		langLayout = new StackLayout();
		langComposite.setLayout(langLayout);
		langComposite.setBackground(background);

		qdfactory = new QDesignerFactory(langComposite);
		for (String lang : languages)
			qdfactory.getDesigner(lang);

		bptab.setControl(sectionClient);
	}

	IQueryDesigner currentDesigner = null;
	private DataMappingFactory dmfactory;

	private void changeLanguage() {
		IQueryDesigner designer = qdfactory.getDesigner(langCombo.getText());
		langLayout.topControl = designer.getControl();
		langComposite.layout();
		if (currentDesigner != null)
			designer.setQuery(currentDesigner.getQuery());
		currentDesigner = designer;
	}

	public Composite createToolbar(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new GridLayout(2, false));

		Label lbl = new Label(comp, SWT.NONE);
		lbl.setText(Messages.DataQueryAdapters_actionname);

		ToolBar tb = new ToolBar(comp, SWT.FLAT | SWT.RIGHT);
		tb.setBackground(parent.getBackground());
		// tb.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		ToolBarManager manager = new ToolBarManager(tb);
		IDataAdapterRunnable adapterRunReport = new IDataAdapterRunnable() {

			public void runReport(DataAdapterDescriptor da) {
				gFields.setEnabled(false);
				if (da instanceof IFieldsProvider && ((IFieldsProvider) da).supportsGetFieldsOperation()) {
					gFields.setEnabled(true);
					// TODO if auto gfields.run;
				}
			}

			public boolean isNotRunning() {
				// TODO Auto-generated method stub
				return true;
			}
		};
		dscombo = new DatasourceComboItem(adapterRunReport, DataAdapterManager.getDataAdapter(file));

		Action newDA = new Action(Messages.DataQueryAdapters_newaction) {
			@Override
			public void run() {
				CreateDataAdapterAction createDAAction = new CreateDataAdapterAction(null);
				createDAAction.run();
				dscombo.setSelected(createDAAction.getNewDataAdapter());
			}
		};

		gFields = new Action(Messages.DataQueryAdapters_getfields) {
			@Override
			public void run() {

				final String lang = langCombo.getText();
				final DataAdapterDescriptor da = dscombo.getSelected();
				if (da != null && da instanceof IFieldsProvider && ((IFieldsProvider) da).supportsGetFieldsOperation()) {
					final String query = qdfactory.getDesigner(lang).getQuery();
					ProgressMonitorDialog dialog = new ProgressMonitorDialog(Display.getDefault().getActiveShell());
					try {
						dialog.run(true, true, new IRunnableWithProgress() {
							public void run(IProgressMonitor monitor) {
								monitor.beginTask(Messages.DataQueryAdapters_jobname, -1);
								SelectionHelper.setClassLoader(file, monitor);

								JRDesignQuery jdq = new JRDesignQuery();
								jdq.setLanguage(lang);
								jdq.setText(query);
								newdataset.setQuery(jdq);

								DataAdapterService das = DataAdapterServiceUtil.getDataAdapterService(da.getDataAdapter());
								try {
									final List<JRDesignField> fields = ((IFieldsProvider) da).getFields(das, newdataset);
									if (fields != null) {
										monitor.setTaskName("Setting Fields");
										Display.getDefault().syncExec(new Runnable() {

											public void run() {
												setFields(fields);
											}
										});
										monitor.setTaskName("Fields set");
									}
								} catch (UnsupportedOperationException e) {
									UIUtils.showError(e);
								} catch (JRException e) {
									UIUtils.showError(e);
								} finally {
									das.dispose();
								}
								monitor.done();
							}
						});

					} catch (InvocationTargetException e) {
						UIUtils.showError(e.getTargetException());
					} catch (InterruptedException e) {
						UIUtils.showError(e);
					}
				}
			}
		};
		gFields.setEnabled(false);

		manager.add(dscombo);
		manager.add(newDA);
		manager.add(new Separator());
		manager.add(gFields);

		manager.update(true);
		tb.pack();

		return comp;
	}

	public void getFields() {
		gFields.run();
	}

	public void setDataset(JRDesignDataset ds) {
		JRQuery query = ds.getQuery();
		if (query != null) {
			int langindex = Misc.indexOf(languages, query.getLanguage());
			if (langindex >= 0)
				langCombo.select(langindex);
			else
				langCombo.setItem(0, Misc.nvl(query.getLanguage()));
			changeLanguage();
			currentDesigner.setQuery(query.getText());
		}
	}

	public String getLanguage() {
		int langind = langCombo.getSelectionIndex();
		if (langind < 0 || langind > languages.length)
			langind = 0;
		return languages[langind];

	}

	public String getQuery() {
		return qdfactory.getDesigner(langCombo.getText()).getQuery();
	}

	public abstract void setFields(List<JRDesignField> fields);

	public void setDefaultDataAdapter(MReport mreport) {
		if (mreport != null) {
			Object obj = mreport.getParameter(MReport.DEFAULT_DATAADAPTER);
			if (obj != null && obj instanceof DataAdapterDescriptor) {
				dscombo.setSelected((DataAdapterDescriptor) obj);
				if (obj instanceof IFieldsProvider)
					gFields.setEnabled(true);
			}
		}
	}

	public DataAdapterDescriptor getDataAdapter() {
		return dscombo.getSelected();
	}
}
