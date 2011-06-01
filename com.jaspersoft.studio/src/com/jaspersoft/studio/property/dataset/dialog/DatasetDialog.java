package com.jaspersoft.studio.property.dataset.dialog;

import java.util.List;

import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRQuery;
import net.sf.jasperreports.engine.JRSortField;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JRDesignSortField;

import org.eclipse.core.resources.IFile;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.forms.FormDialog;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.data.widget.DatasourceComboItem;
import com.jaspersoft.studio.data.widget.IDataAdapterRunnable;
import com.jaspersoft.studio.model.MQuery;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.field.command.CreateFieldCommand;
import com.jaspersoft.studio.model.field.command.DeleteFieldCommand;
import com.jaspersoft.studio.model.sortfield.command.CreateSortFieldCommand;
import com.jaspersoft.studio.model.sortfield.command.DeleteSortFieldCommand;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.swt.widgets.CSashForm;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.UIUtils;

final class DatasetDialog extends FormDialog {
	private MDataset mdataset;
	private IFile file;

	DatasetDialog(Shell shell, MDataset node, IFile file) {
		super(shell);
		super.configureShell(shell);
		shell.setText("Dataset & Query Dialog");
		mdataset = node;
		this.file = file;
		newdataset = (JRDesignDataset) ((JRDesignDataset) mdataset.getValue()).clone();
	}

	public boolean close() {
		createCommand();
		return super.close();
	}

	protected void createFormContent(final IManagedForm mform) {
		FormToolkit toolkit = mform.getToolkit();
		mform.getForm().getBody().setLayout(new GridLayout(1, true));

		createToolbar(mform.getForm().getBody());

		SashForm sf = new CSashForm(mform.getForm().getBody(), SWT.VERTICAL);

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 700;
		gd.widthHint = 800;
		sf.setLayoutData(gd);
		sf.setLayout(new GridLayout());
		background = mform.getForm().getBody().getBackground();

		createTop(sf, toolkit);

		createBottom(sf, toolkit);
		sf.setWeights(new int[] { 450, 150 });

		setDataset((JRDesignDataset) mdataset.getValue());
	}

	private void createToolbar(Composite parent) {
		ToolBar tb = new ToolBar(parent, SWT.FLAT | SWT.RIGHT);
		tb.setBackground(parent.getBackground());
		// tb.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		ToolBarManager manager = new ToolBarManager(tb);
		dscombo = new DatasourceComboItem(new IDataAdapterRunnable() {

			public void runReport(DataAdapterDescriptor da) {
				gFields.setEnabled(false);
				if (da instanceof IFieldsProvider) {
					gFields.setEnabled(true);
					// TODO if auto gfields.run;
				}
			}

			public boolean isNotRunning() {
				// TODO Auto-generated method stub
				return true;
			}
		});
		gFields = new Action("Get &Fields") {
			// TODO run inside a job, modal with progress bar
			public void run() {
				DataAdapterDescriptor da = dscombo.getSelected();
				try {
					// JRDesignQuery jrDesignQuery = (JRDesignQuery) newdataset.getQuery();
					// jrDesignQuery.setLanguage(langCombo.getText());
					// jrDesignQuery.setText(qdfactory.getDesigner(langCombo.getText()).getQuery());
					// jrDesignQuery.getText();

					JRDesignQuery jdq = new JRDesignQuery();
					jdq.setLanguage(langCombo.getText());
					jdq.setText(qdfactory.getDesigner(langCombo.getText()).getQuery());
					newdataset.setQuery(jdq);

					List<JRDesignField> fields = ((IFieldsProvider) da).getFields(da.getDataAdapterService(), newdataset);
					if (fields != null)
						ftable.setFields(fields);
				} catch (UnsupportedOperationException e) {
					UIUtils.showError(e);
				} catch (Exception e) {
					UIUtils.showError(e);
				}
			}
		};
		gFields.setEnabled(false);

		manager.add(dscombo);
		manager.add(gFields);

		manager.update(true);
		tb.pack();
	}

	private void createTop(Composite parent, FormToolkit toolkit) {
		CTabFolder tabFolder = new CTabFolder(parent, SWT.TOP);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 150;
		tabFolder.setLayoutData(gd);
		tabFolder.setBackground(background);

		createQuery(toolkit, tabFolder);

		tabFolder.setSelection(0);
	}

	private void createQuery(FormToolkit toolkit, CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText("Query");

		Composite sectionClient = toolkit.createComposite(tabFolder);
		sectionClient.setLayout(new GridLayout(2, false));

		toolkit.createLabel(sectionClient, "Language");

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

		langComposite = toolkit.createComposite(sectionClient);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		langComposite.setLayoutData(gd);
		langLayout = new StackLayout();
		langComposite.setLayout(langLayout);

		qdfactory = new QDesignerFactory(langComposite);
		for (String lang : languages)
			qdfactory.getDesigner(lang);

		bptab.setControl(sectionClient);
	}

	private void changeLanguage() {
		langLayout.topControl = qdfactory.getDesigner(langCombo.getText()).getControl();
		langComposite.layout();
	}

	private void createBottom(Composite parent, FormToolkit toolkit) {
		CTabFolder tabFolder = new CTabFolder(parent, SWT.BOTTOM);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 150;
		tabFolder.setLayoutData(gd);
		tabFolder.setBackground(background);

		createFields(toolkit, tabFolder);
		createSortFields(toolkit, tabFolder);
		// createDataPreview(toolkit, tabFolder);

		tabFolder.setSelection(0);
	}

	private void createFields(FormToolkit toolkit, CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText("Fields");

		ftable = new FieldsTable(tabFolder, newdataset);

		bptab.setControl(ftable.getControl());
	}

	private void createSortFields(FormToolkit toolkit, CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText("Sorting");

		Composite sectionClient = toolkit.createComposite(tabFolder);
		sectionClient.setLayout(new GridLayout(2, false));

		sftable = new SortFieldsTable(tabFolder, newdataset);

		bptab.setControl(sftable.getControl());
	}

	private void createDataPreview(FormToolkit toolkit, CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText("Data Preview");

		Composite sectionClient = toolkit.createComposite(tabFolder);
		sectionClient.setLayout(new GridLayout(2, false));

		bptab.setControl(sectionClient);
	}

	private CompoundCommand command;
	private Color background;
	private String[] languages;
	private Combo langCombo;

	public CompoundCommand getCommand() {
		return command;
	}

	private JRDesignDataset newdataset;
	private FieldsTable ftable;
	private SortFieldsTable sftable;
	private DatasourceComboItem dscombo;
	private StackLayout langLayout;
	private Composite langComposite;
	private QDesignerFactory qdfactory;
	private Action gFields;

	public void setDataset(JRDesignDataset ds) {
		JRQuery query = ds.getQuery();
		if (query != null) {
			int langindex = Misc.indexOf(languages, query.getLanguage());
			if (langindex >= 0)
				langCombo.select(langindex);
			else
				langCombo.setItem(0, query.getLanguage());
			changeLanguage();
			qdfactory.getDesigner(query.getLanguage()).setQuery(query.getText());
		}
	}

	@SuppressWarnings("unchecked")
	public void createCommand() {
		JRDesignDataset ds = (JRDesignDataset) mdataset.getValue();
		command = new CompoundCommand();
		IPropertySource mquery = (IPropertySource) mdataset.getPropertyValue(JRDesignDataset.PROPERTY_QUERY);
		int langind = langCombo.getSelectionIndex();
		if (langind >= 0 && langind < languages.length) {
			String lang = languages[langind];
			if (ds.getQuery() == null) {
				mquery = new MQuery(new JRDesignQuery());
				command.add(setValueCommand(JRDesignDataset.PROPERTY_QUERY, mquery, mdataset));
			}

			if (ds.getQuery().getLanguage().equals(lang))
				command.add(setValueCommand(JRDesignQuery.PROPERTY_LANGUAGE, lang, mquery));
			String qtext = qdfactory.getDesigner(langCombo.getText()).getQuery();
			if (ds.getQuery().getText().equals(qtext))
				command.add(setValueCommand(JRDesignQuery.PROPERTY_TEXT, qtext, mquery));
		}

		List<JRField> dsfields = ds.getFieldsList();
		List<JRDesignField> fields = ftable.getFields();
		for (JRField f : dsfields)
			command.add(new DeleteFieldCommand(ds, f));
		for (JRDesignField newf : fields)
			command.add(new CreateFieldCommand(ds, newf, -1));

		List<JRSortField> dssfields = ds.getSortFieldsList();
		List<JRDesignSortField> sfields = sftable.getFields();
		for (JRSortField f : dssfields)
			command.add(new DeleteSortFieldCommand(ds, f));
		for (JRDesignSortField newf : sfields)
			command.add(new CreateSortFieldCommand(ds, newf, -1));
	}

	private Command setValueCommand(String property, Object value, IPropertySource target) {
		SetValueCommand cmd = new SetValueCommand();
		cmd.setTarget(target);
		cmd.setPropertyId(property);
		cmd.setPropertyValue(value);
		return cmd;
	}
}