/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.dialog;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.dialogs.PreferencesUtil;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.IFieldSetter;
import com.jaspersoft.studio.data.IQueryDesigner;
import com.jaspersoft.studio.data.MDataAdapters;
import com.jaspersoft.studio.data.designer.AQueryDesignerContainer;
import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.data.widget.DataAdapterAction;
import com.jaspersoft.studio.data.widget.IDataAdapterRunnable;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.DesignerPreferencePage;
import com.jaspersoft.studio.property.dataset.da.DataAdapterUI;
import com.jaspersoft.studio.property.metadata.PropertyMetadataRegistry;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.annotations.properties.PropertyScope;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.DataAdapterServiceUtil;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRQuery;
import net.sf.jasperreports.engine.ParameterContributorContext;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.query.JRJdbcQueryExecuterFactory;
import net.sf.jasperreports.properties.PropertyMetadata;
import net.sf.jasperreports.properties.StandardPropertyMetadata;

public abstract class DataQueryAdapters extends AQueryDesignerContainer {

	/** Property to save a default data adapter to select */
	public static final String DEFAULT_DATAADAPTER = "com.jaspersoft.studio.data.defaultdataadapter"; //$NON-NLS-1$

	private JRDesignDataset newdataset;

	private JasperDesign jDesign;

	private Color background;

	private IFile file;

	public static void initMetadata() {
		List<PropertyMetadata> pm = new ArrayList<PropertyMetadata>();

		StandardPropertyMetadata spm = new StandardPropertyMetadata();
		spm.setName(DEFAULT_DATAADAPTER);
		spm.setLabel("Data Adapter");
		spm.setDescription("Last Data Adapter Used.");
		spm.setValueType("jssDA");
		List<PropertyScope> scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.DATASET);
		spm.setScopes(scopes);
		spm.setCategory("net.sf.jasperreports.metadata.property.category:data.source");
		pm.add(spm);

		PropertyMetadataRegistry.addMetadata(pm);
	}

	public DataQueryAdapters(Composite parent, JasperReportsConfiguration jConfig, JRDesignDataset newdataset,
			Color background, IRunnableContext runner) {
		setRunnableContext(runner);
		if (jConfig != null) {
			this.file = (IFile) jConfig.get(FileUtils.KEY_FILE);
			this.jDesign = jConfig.getJasperDesign();
		}
		this.newdataset = newdataset;
		this.jConfig = jConfig;
		if (background != null)
			this.background = background;
		// else
		this.background = parent.getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND);
	}

	public DataQueryAdapters(Composite parent, JasperReportsConfiguration jConfig, JRDesignDataset newdataset,
			Color background) {
		this(parent, jConfig, newdataset, background, null);
	}

	public void dispose() {
		qdfactory.dispose();
		dmfactory.dispose();
	}

	private Composite composite;
	private DataAdapterAction dscombo;
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

	public void setFile(JasperReportsConfiguration jConfig) {
		this.file = (IFile) jConfig.get(FileUtils.KEY_FILE);
		this.jDesign = jConfig.getJasperDesign();
		dscombo.setDataAdapterStorages(DataAdapterManager.getDataAdapter(file, jConfig));
		setDataset(jDesign, newdataset);
	}

	public CTabFolder createTop(Composite parent, IFieldSetter fsetter) {
		tabFolder = new CTabFolder(parent, SWT.TOP | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 150;
		tabFolder.setLayoutData(gd);

		createQuery(tabFolder);
		createMappingTools(tabFolder, fsetter);
		createDataAdapterTab(tabFolder);

		tabFolder.setSelection(0);
		return tabFolder;
	}

	private DataAdapterUI daUI;

	private void createDataAdapterTab(final CTabFolder tabFolder) {
		daUI = new DataAdapterUI();
		daUI.refreshDaUI(tabFolder, background, jDesign, newdataset, jConfig);
		newdataset.getPropertiesMap().getEventSupport().addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				String pname = evt.getPropertyName();
				if (pname.equals(DataQueryAdapters.DEFAULT_DATAADAPTER)
						|| pname.equals(DataQueryAdapters.DEFAULT_DATAADAPTER)) {
					daUI.refreshDaUI(tabFolder, background, jDesign, newdataset, jConfig);
				}
			}
		});
	}

	private void createMappingTools(CTabFolder tabFolder, IFieldSetter fsetter) {
		dmfactory = new DataMappingFactory(tabFolder, fsetter, this);
	}

	private void createQuery(CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText(Messages.DataQueryAdapters_querytab);

		Composite sectionClient = new Composite(tabFolder, SWT.NONE);
		sectionClient.setLayout(new GridLayout(3, false));
		sectionClient.setBackground(background);
		sectionClient.setBackgroundMode(SWT.INHERIT_FORCE);

		Label label = new Label(sectionClient, SWT.NONE);
		label.setText(Messages.DataQueryAdapters_languagetitle);

		langCombo = new Combo(sectionClient, SWT.SINGLE | SWT.BORDER);
		languages = ModelUtils.getQueryLanguages(jConfig);
		langCombo.setItems(languages);
		GridData gd = new GridData();
		gd.widthHint = 200;
		langCombo.setLayoutData(gd);
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
					Point oldSelection = langCombo.getSelection();
					languages[0] = lang;
					langCombo.setItem(0, lang);
					langCombo.select(0);
					// On windows the selection of an entry select also all the
					// text inside the combo, so we need to restore the old selection
					langCombo.setSelection(oldSelection);
				} else if (index > 0 && !languages[0].isEmpty()) {
					// if the input language is a known language and there was before an
					// entry for a not recognized language then remove it
					languages[0] = ""; //$NON-NLS-1$
					langCombo.setItem(0, ""); //$NON-NLS-1$
				}
				changeLanguage();
			}
		});

		tbCompo = new Composite(sectionClient, SWT.NONE);
		tbCompo.setBackgroundMode(SWT.INHERIT_FORCE);
		tbLayout = new StackLayout();
		tbCompo.setLayout(tbLayout);
		tbCompo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		langComposite = new Composite(sectionClient, SWT.NONE);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		langComposite.setLayoutData(gd);
		langLayout = new StackLayout();
		langLayout.marginWidth = 0;
		langLayout.marginWidth = 0;
		langComposite.setLayout(langLayout);
		langComposite.setBackground(background);

		qdfactory = new QDesignerFactory(langComposite, tbCompo, this);
		// for (String lang : languages)
		// qdfactory.getDesigner(lang);

		bptab.setControl(sectionClient);
	}

	private IQueryDesigner currentDesigner = null;
	private DataMappingFactory dmfactory;

	private void changeLanguage() {
		if (!isRefresh) {
			qStatus.showInfo(""); //$NON-NLS-1$
			String lang = langCombo.getText();
			if (Misc.isNullOrEmpty(lang)) {
				lang = "SQL"; //$NON-NLS-1$
				langCombo.setText("SQL"); //$NON-NLS-1$
			}
			langCombo.setToolTipText(lang);
			((JRDesignQuery) newdataset.getQuery()).setLanguage(lang);
			final IQueryDesigner designer = qdfactory.getDesigner(lang);
			langLayout.topControl = designer.getControl();
			tbLayout.topControl = designer.getToolbarControl();
			tbCompo.layout();
			langComposite.layout();
			currentDesigner = designer;
			currentDesigner.setJasperConfiguration(jConfig);
			UIUtils.getDisplay().asyncExec(new Runnable() {

				@Override
				public void run() {
					currentDesigner.setQuery(jDesign, newdataset, jConfig);
					currentDesigner.setDataAdapter(dscombo.getSelected());
				}
			});
			refreshDsCombo();
		}
	}

	protected void refreshDsCombo() {
		String filter = jConfig.getProperty(DesignerPreferencePage.P_DAFILTER);
		if (filter != null && filter.equals("da"))
			dscombo.setLanguage(langCombo.getText());
		else
			dscombo.setLanguage(null);
		dscombo.getMenu(tb);
	}

	public String getContextHelpId() {
		return currentDesigner.getContextHelpId();
	}

	public Composite createToolbar(Composite parent) {
		final Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new GridLayout(6, false));
		comp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		comp.setBackgroundMode(SWT.INHERIT_FORCE);

		final Label lbl = new Label(comp, SWT.NONE);
		lbl.setImage(JaspersoftStudioPlugin.getInstance().getImage(MDataAdapters.getIconDescriptor().getIcon16()));
		lbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				IFile f = (IFile) jConfig.get(FileUtils.KEY_FILE);
				if (f != null) {
					PreferenceDialog pref = PreferencesUtil.createPropertyDialogOn(UIUtils.getShell(), f.getProject(),
							DesignerPreferencePage.PAGE_ID, null, null);
					if (pref != null && pref.open() == Dialog.OK)
						refreshDsCombo();
				}
			}
		});

		tb = new ToolBar(comp, SWT.FLAT | SWT.RIGHT);
		tb.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		final ToolBarManager manager = new ToolBarManager(tb);
		IDataAdapterRunnable adapterRunReport = new IDataAdapterRunnable() {

			public void runReport(DataAdapterDescriptor da) {
				if (da != null) {
					newdataset.setProperty(DEFAULT_DATAADAPTER, da.getName());
				} else {
					newdataset.getPropertiesMap().removeProperty(DEFAULT_DATAADAPTER);
				}
				currentDesigner.setDataAdapter(da);
				qStatus.showInfo(""); //$NON-NLS-1$

				String filter = jConfig.getProperty(DesignerPreferencePage.P_DAFILTER);
				if (filter != null && filter.equals("lang")) {
					String[] langs = da.getLanguages();
					langCombo.removeAll();
					if (Misc.isNullOrEmpty(langs) || ArrayUtils.contains(langs, "*")) {
						langCombo.setItems(languages);
						return;
					}
					String lang = langCombo.getText();
					for (String l : langs) {
						langCombo.add(l);
						if (l.equals(lang))
							return;
					}
					langCombo.setText(langs[0]);
					changeLanguage();
				}
				dscombo.getMenu(tb);
			}

			public boolean isNotRunning() {
				return true;
			}

			@Override
			public JasperReportsConfiguration getConfiguration() {
				return jConfig;
			}

			@Override
			public void runReport(DataAdapterDescriptor myDataAdapter, boolean prmDirty) {
				runReport(myDataAdapter);
			}
		};
		dscombo = new DataAdapterAction(adapterRunReport, DataAdapterManager.getDataAdapter(file, jConfig), newdataset);

		manager.add(dscombo);

		manager.update(true);
		tb.pack();

		createStatusBar(comp);

		createProgressBar(comp);

		return comp;
	}

	private IRunnableContext runner;

	public void setRunnableContext(IRunnableContext runner) {
		this.runner = runner;
	}

	protected void createProgressBar(final Composite comp) {
		if (runner == null)
			runner = new RunWithProgressBar(comp);
	}

	public void run(boolean fork, boolean cancelable, IRunnableWithProgress runnable)
			throws InvocationTargetException, InterruptedException {
		runner.run(fork, cancelable, runnable);
	}

	public void getFields(IProgressMonitor monitor) {
		doGetFields(monitor);
	}

	private boolean isRefresh = false;
	private StackLayout tbLayout;
	private Composite tbCompo;

	private ToolBar tb;

	public void setDataset(JasperDesign jDesign, JRDesignDataset ds) {
		newdataset = ds;
		JRQuery query = newdataset.getQuery();
		if (query == null) {
			query = new JRDesignQuery();
			((JRDesignQuery) query).setLanguage(JRJdbcQueryExecuterFactory.QUERY_LANGUAGE_SQL);
			((JRDesignQuery) query).setText(""); //$NON-NLS-1$
			newdataset.setQuery((JRDesignQuery) query);
		}
		isRefresh = true;
		try {
			int langindex = Misc.indexOf(languages, query.getLanguage());
			if (langindex >= 0)
				langCombo.select(langindex);
			else {
				langCombo.setItem(0, Misc.nvl(query.getLanguage()));
				langCombo.select(0);
			}
		} finally {
			isRefresh = false;
		}

		changeLanguage();

		if (jDesign != null) {
			// Try to find the default data adapter for the specified dataset
			String defaultAdapter = ds.getPropertiesMap().getProperty(DEFAULT_DATAADAPTER);
			if (defaultAdapter == null && ds.isMainDataset()) {
				// if none available get the default for the main report
				defaultAdapter = jDesign.getProperty(DEFAULT_DATAADAPTER);
			}
			dscombo.setSelected(defaultAdapter);
			// NOTE: temporary commenting this
			// It appears it prevents double invocation of the
			// #setDataAdapter in the designers
			// currentDesigner.setDataAdapter(dscombo.getSelected());
		}
	}

	public String getLanguage() {
		int langind = langCombo.getSelectionIndex();
		if (langind < 0 || langind > languages.length)
			langind = 0;
		return languages[langind];

	}

	public String getQuery() {
		return qdfactory.getDesigner(newdataset.getQuery().getLanguage()).getQuery();
	}

	public DataAdapterDescriptor getDataAdapter() {
		return dscombo.getSelected();
	}

	@Override
	protected void doGetFields(IProgressMonitor monitor) {
		final DataAdapterDescriptor da = dscombo.getSelected();
		if (da != null && da instanceof IFieldsProvider && ((IFieldsProvider) da).supportsGetFieldsOperation(jConfig)) {
			qStatus.showInfo(""); //$NON-NLS-1$

			monitor.beginTask(Messages.DataQueryAdapters_jobname, -1);

			ClassLoader oldClassloader = Thread.currentThread().getContextClassLoader();
			Thread.currentThread().setContextClassLoader(jConfig.getClassLoader());

			DataAdapterService das = DataAdapterServiceUtil
					.getInstance(new ParameterContributorContext(jConfig, newdataset, jConfig.getJRParameters()))
					.getService(da.getDataAdapter());
			try {
				final List<JRDesignField> fields = ((IFieldsProvider) da).getFields(das, jConfig, newdataset);
				if (fields != null) {
					monitor.setTaskName(Messages.DataQueryAdapters_9);
					Display.getDefault().syncExec(new Runnable() {

						public void run() {
							setFields(fields);
						}
					});
					monitor.setTaskName(Messages.DataQueryAdapters_10);
				}
			} catch (Exception e) {
				if (e.getCause() != null)
					qStatus.showError(e.getCause().getMessage(), e);
				else
					qStatus.showError(e);
			} finally {
				Thread.currentThread().setContextClassLoader(oldClassloader);
				das.dispose();
				monitor.done();
			}
		}
	}

}
