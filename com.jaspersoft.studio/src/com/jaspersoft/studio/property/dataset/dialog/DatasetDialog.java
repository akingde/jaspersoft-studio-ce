package com.jaspersoft.studio.property.dataset.dialog;

import java.util.List;

import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRSortField;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JRDesignSortField;

import org.eclipse.core.resources.IFile;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.FormDialog;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.model.MQuery;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.field.command.CreateFieldCommand;
import com.jaspersoft.studio.model.field.command.DeleteFieldCommand;
import com.jaspersoft.studio.model.sortfield.command.CreateSortFieldCommand;
import com.jaspersoft.studio.model.sortfield.command.DeleteSortFieldCommand;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.swt.widgets.CSashForm;

final class DatasetDialog extends FormDialog {
	private MDataset mdataset;
	private MReport mreport;
	private IFile file;

	public DatasetDialog(Shell shell, MDataset mdataset, MReport mreport, IFile file) {
		super(shell);
		super.configureShell(shell);
		shell.setText("Dataset & Query Dialog");
		this.mdataset = mdataset;
		this.mreport = mreport;
		this.file = file;
		newdataset = (JRDesignDataset) ((JRDesignDataset) mdataset.getValue()).clone();
	}

	public boolean close() {
		createCommand();
		return super.close();
	}

	protected void createFormContent(final IManagedForm mform) {
		FormToolkit toolkit = mform.getToolkit();
		Composite body = mform.getForm().getBody();
		body.setLayout(new GridLayout(1, true));
		body.setBackground(body.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		background = body.getBackground();

		dataquery = new DataQueryAdapters(mform.getForm().getBody(), newdataset, background) {

			@Override
			public void setFields(List<JRDesignField> fields) {
				DatasetDialog.this.setFields(fields);
			}
		};

		SashForm sf = new CSashForm(body, SWT.VERTICAL);

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 700;
		gd.widthHint = 800;
		sf.setLayoutData(gd);
		sf.setLayout(new GridLayout());

		dataquery.createTop(sf);
		dataquery.setDefaultDataAdapter(mreport);

		createBottom(sf, toolkit);
		sf.setWeights(new int[] { 450, 150 });

		setDataset((JRDesignDataset) mdataset.getValue());
	}

	private void setFields(List<JRDesignField> fields) {
		ftable.setFields(fields);
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

	public CompoundCommand getCommand() {
		return command;
	}

	private JRDesignDataset newdataset;
	private FieldsTable ftable;
	private SortFieldsTable sftable;
	private DataQueryAdapters dataquery;

	public void setDataset(JRDesignDataset ds) {
		dataquery.setDataset(ds);
	}

	public void createCommand() {
		JRDesignDataset ds = (JRDesignDataset) (mdataset.getParent() == null ? mreport.getJasperDesign()
				.getMainDesignDataset() : mdataset.getValue());
		command = new CompoundCommand();
		IPropertySource mquery = (IPropertySource) mdataset.getPropertyValue(JRDesignDataset.PROPERTY_QUERY);
		String lang = dataquery.getLanguage();
		String qtext = dataquery.getQuery();
		if (ds.getQuery() == null) {
			JRDesignQuery jrQuery = new JRDesignQuery();
			jrQuery.setLanguage(lang);
			jrQuery.setText(qtext);
			mquery = new MQuery(jrQuery);
			command.add(setValueCommand(JRDesignDataset.PROPERTY_QUERY, mquery, mdataset));
		} else {
			if (!ds.getQuery().getLanguage().equals(lang))
				command.add(setValueCommand(JRDesignQuery.PROPERTY_LANGUAGE, lang, mquery));
			if (!ds.getQuery().getText().equals(qtext))
				command.add(setValueCommand(JRDesignQuery.PROPERTY_TEXT, qtext, mquery));
		}

		List<JRField> dsfields = ds.getFieldsList();
		List<JRDesignField> fields = ftable.getFields();
		for (JRField f : dsfields)
			command.add(new DeleteFieldCommand(ds, (JRDesignField) f));
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