package com.jaspersoft.studio.property.dataset.dialog;

import net.sf.jasperreports.engine.JRQuery;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignQuery;

import org.eclipse.core.resources.IFile;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.forms.FormDialog;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.widget.DatasourceComboItem;
import com.jaspersoft.studio.data.widget.IDataAdapterRunnable;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.ModelUtils;

final class DatasetDialog extends FormDialog {
	private MDataset mdataset;
	private IFile file;

	DatasetDialog(Shell shell, MDataset node, IFile file) {
		super(shell);
		shell.setText("Dataset & Query Dialog");
		mdataset = node;
		this.file = file;
		newdataset = (JRDesignDataset) ((JRDesignDataset) mdataset.getValue()).clone();
	}

	@Override
	protected void createFormContent(final IManagedForm mform) {
		// mform.getForm().setText("Dataset && Query Dialog");

		FormToolkit toolkit = mform.getToolkit();

		mform.getForm().getBody().setLayout(new GridLayout(1, true));

		createToolbar(mform.getForm().getBody());

		SashForm sf = new SashForm(mform.getForm().getBody(), SWT.VERTICAL);
		sf.setLayoutData(new GridData(GridData.FILL_BOTH));
		sf.setLayout(new GridLayout());
		background = mform.getForm().getBody().getBackground();

		createTop(sf, toolkit);

		createBottom(sf, toolkit);

		setDataset((JRDesignDataset) mdataset.getValue());

	}

	private void createToolbar(Composite parent) {
		ToolBar tb = new ToolBar(parent, SWT.FLAT | SWT.RIGHT);
		ToolBarManager manager = new ToolBarManager(tb);
		DatasourceComboItem dscombo = new DatasourceComboItem(new IDataAdapterRunnable() {

			public void runReport(DataAdapter myDataAdapter) {
				// TODO Auto-generated method stub

			}

			public boolean isNotRunning() {
				// TODO Auto-generated method stub
				return true;
			}
		});
		final Action actionForward = new Action("Get &Fields") {
			public void run() {
				System.out.println("FORWARD");
			}
		};

		manager.add(dscombo);
		manager.add(actionForward);

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
		languages = ModelUtils.getQueryLanguagesOnly();
		langCombo.setItems(languages);

		Label lbl = new Label(sectionClient, SWT.NONE);
		lbl.setText("QUERY DESIGNER HERE");
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		lbl.setLayoutData(gd);

		bptab.setControl(sectionClient);
	}

	private void createBottom(Composite parent, FormToolkit toolkit) {
		CTabFolder tabFolder = new CTabFolder(parent, SWT.BOTTOM);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 150;
		tabFolder.setLayoutData(gd);
		tabFolder.setBackground(background);

		createFields(toolkit, tabFolder);
		createSortFields(toolkit, tabFolder);
		createDataPreview(toolkit, tabFolder);

		tabFolder.setSelection(0);
	}

	private void createFields(FormToolkit toolkit, CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText("Fields");

		FieldsTable ftable = new FieldsTable(tabFolder, newdataset);

		bptab.setControl(ftable.getControl());
	}

	private void createSortFields(FormToolkit toolkit, CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText("Sorting");

		Composite sectionClient = toolkit.createComposite(tabFolder);
		sectionClient.setLayout(new GridLayout(2, false));

		SortFieldsTable ftable = new SortFieldsTable(tabFolder, newdataset);

		bptab.setControl(ftable.getControl());
	}

	private void createDataPreview(FormToolkit toolkit, CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText("Data Preview");

		Composite sectionClient = toolkit.createComposite(tabFolder);
		sectionClient.setLayout(new GridLayout(2, false));

		bptab.setControl(sectionClient);
	}

	@Override
	public boolean close() {
		createCommand();
		return super.close();
	}

	private CompoundCommand command;
	private Color background;
	private String[] languages;
	private Combo langCombo;

	public CompoundCommand getCommand() {
		return command;
	}

	private JRDesignDataset newdataset;

	public void setDataset(JRDesignDataset ds) {
		JRQuery query = ds.getQuery();
		if (query != null)
			langCombo.select(Misc.indexOf(languages, query.getLanguage()));

	}

	public void createCommand() {
		JRDesignDataset ds = (JRDesignDataset) mdataset.getValue();
		command = new CompoundCommand();
		IPropertySource mquery = (IPropertySource) mdataset.getPropertyValue(JRDesignDataset.PROPERTY_QUERY);
		int langind = langCombo.getSelectionIndex();
		if (langind >= 0 && langind < languages.length && ds.getQuery() != null) {
			String lang = languages[langind];
			if (ds.getQuery().getLanguage().equals(lang))
				command.add(createCommand(JRDesignQuery.PROPERTY_LANGUAGE, lang, mquery));
			String qtext = "";
			if (ds.getQuery().getText().equals(qtext))
				command.add(createCommand(JRDesignQuery.PROPERTY_TEXT, lang, mquery));
		}
		// read dataset, if not exists in dataset, delete it
		// read list, if not exists in dataset, add it

		// CreateFieldCommand;
		// DeleteFieldCommand
		//
		//
		// CreateSortFieldCommand;
		// DeleteSortFieldCommand;

	}

	private Command createCommand(String property, Object value, IPropertySource target) {
		SetValueCommand cmd = new SetValueCommand();
		cmd.setTarget(target);
		cmd.setPropertyId(property);
		cmd.setPropertyValue(value);
		return cmd;
	}
}