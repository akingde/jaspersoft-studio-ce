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

import java.util.List;

import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRSortField;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JRDesignSortField;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.FormDialog;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.data.IFieldSetter;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.ExpressionEditorSupportUtil;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MQuery;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.field.command.CreateFieldCommand;
import com.jaspersoft.studio.model.field.command.DeleteFieldCommand;
import com.jaspersoft.studio.model.parameter.command.CreateParameterCommand;
import com.jaspersoft.studio.model.parameter.command.DeleteParameterCommand;
import com.jaspersoft.studio.model.sortfield.command.CreateSortFieldCommand;
import com.jaspersoft.studio.model.sortfield.command.DeleteSortFieldCommand;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.dialog.JRExpressionEditor;
import com.jaspersoft.studio.swt.widgets.CSashForm;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class DatasetDialog extends FormDialog implements IFieldSetter {
	private MDataset mdataset;
	private MReport mreport;
	private JasperReportsConfiguration jConfig;

	public DatasetDialog(Shell shell, MDataset mdataset, MReport mreport, JasperReportsConfiguration jConfig) {
		super(shell);
		this.mdataset = mdataset;
		this.mreport = mreport;
		this.jConfig = jConfig;
		newdataset = (JRDesignDataset) ((JRDesignDataset) mdataset.getValue()).clone();
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(Messages.DatasetDialog_title);
		setShellStyle(getShellStyle() | SWT.MIN | SWT.MAX | SWT.RESIZE);
	}

	@Override
	public boolean close() {
		createCommand();
		dataquery.dispose();
		return super.close();
	}

	@Override
	protected void createFormContent(final IManagedForm mform) {
		FormToolkit toolkit = mform.getToolkit();
		Composite body = mform.getForm().getBody();
		body.setLayout(new GridLayout(1, true));
		background = body.getBackground();
		body.setBackground(body.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));

		dataquery = new DataQueryAdapters(mform.getForm().getBody(), jConfig, newdataset, background) {

			@Override
			public void setFields(List<JRDesignField> fields) {
				DatasetDialog.this.setFields(fields);
			}
			
			@Override
			public List<JRDesignField> getCurrentFields() {
				return DatasetDialog.this.getCurrentFields();
			}

			@Override
			public void setParameters(List<JRDesignParameter> params) {

			}
		};

		dataquery.createToolbar(body);

		SashForm sf = new CSashForm(body, SWT.VERTICAL);

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 600;
		gd.minimumHeight = 400;
		gd.widthHint = 800;
		sf.setLayoutData(gd);
		sf.setLayout(new GridLayout());

		dataquery.createTop(sf, this);

		// int tabHeight = c.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
		// tabHeight = Math.max(tabHeight, ctf.getTabHeight());
		// ctf.setTabHeight(tabHeight);
		//
		// ctf.setTopRight(c);

		dataquery.setDefaultDataAdapter(mreport);

		createBottom(sf, toolkit);
		sf.setWeights(new int[] { 450, 250 });

		setDataset(mreport.getJasperDesign(), newdataset);
	}

	public void setFields(List<JRDesignField> fields) {
		ftable.setFields(fields);
	}
	
	public List<JRDesignField> getCurrentFields(){
		return ftable.getFields();
	}

	private void createBottom(Composite parent, FormToolkit toolkit) {
		CTabFolder tabFolder = new CTabFolder(parent, SWT.BOTTOM);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 250;
		tabFolder.setLayoutData(gd);

		createFields(toolkit, tabFolder);
		createParameters(toolkit, tabFolder);
		createSortFields(toolkit, tabFolder);
		createFilterExpression(toolkit, tabFolder);

		// createDataPreview(toolkit, tabFolder);

		tabFolder.setSelection(0);
	}

	private void createParameters(FormToolkit toolkit, CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText("Parameters");

		ptable = new ParametersTable(tabFolder, newdataset, background);

		bptab.setControl(ptable.getControl());
	}

	private void createFields(FormToolkit toolkit, CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText(Messages.DatasetDialog_fieldstab);

		ftable = new FieldsTable(tabFolder, newdataset, background);

		bptab.setControl(ftable.getControl());
	}

	private void createSortFields(FormToolkit toolkit, CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText(Messages.DatasetDialog_sortingtab);

		sftable = new SortFieldsTable(tabFolder, newdataset, background);

		bptab.setControl(sftable.getControl());
	}

	private void createFilterExpression(FormToolkit toolkit, CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText(Messages.DatasetDialog_filterexpression);

		Composite sectionClient = toolkit.createComposite(tabFolder);
		sectionClient.setLayout(new GridLayout(2, false));

		filterExpression = new Text(sectionClient, SWT.MULTI | SWT.BORDER);
		filterExpression.setLayoutData(new GridData(GridData.FILL_BOTH));

		Button but = new Button(sectionClient, SWT.NONE);
		but.setText(Messages.DatasetDialog_selecttitle);
		but.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JRExpressionEditor wizard = new JRExpressionEditor();
				wizard.setValue(ExprUtil.setValues(new JRDesignExpression(), filterExpression.getText(), null));
				JRDesignDataset designDataset = mdataset.getValue();
				if (designDataset != null) {
					wizard.setExpressionContext(new ExpressionContext(designDataset, mreport.getJasperConfiguration()));
				} else {
					wizard.setExpressionContext(ExpressionEditorSupportUtil.getReportExpressionContext());
				}
				WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					filterExpression.setText(wizard.getValue().getText());
				}
			}
		});
		but.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

		bptab.setControl(sectionClient);

		if (newdataset.getFilterExpression() != null)
			filterExpression.setText(Misc.nvl(newdataset.getFilterExpression().getText(), "")); //$NON-NLS-1$
	}

	// private void createDataPreview(FormToolkit toolkit, CTabFolder tabFolder) {
	// CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
	// bptab.setText(Messages.DatasetDialog_datapreviewtab);
	//
	// Composite sectionClient = toolkit.createComposite(tabFolder);
	// sectionClient.setLayout(new GridLayout(2, false));
	//
	// bptab.setControl(sectionClient);
	// }

	private CompoundCommand command;
	private Color background;

	public CompoundCommand getCommand() {
		return command;
	}

	private JRDesignDataset newdataset;
	private FieldsTable ftable;
	private ParametersTable ptable;
	private SortFieldsTable sftable;
	private DataQueryAdapters dataquery;
	private Text filterExpression;

	public void setDataset(JasperDesign jDesign, JRDesignDataset ds) {
		dataquery.setDataset(jDesign, ds);
	}

	public void createCommand() {
		JRDesignDataset ds = (JRDesignDataset) (mdataset.getParent() == null ? mreport.getJasperDesign()
				.getMainDesignDataset() : mdataset.getValue());
		command = new CompoundCommand();

		String lang = newdataset.getQuery().getLanguage();
		String qtext = newdataset.getQuery().getText();
		if (ds.getQuery() == null) {
			JRDesignQuery jrQuery = new JRDesignQuery();
			jrQuery.setLanguage(lang);
			jrQuery.setText(qtext);
			command.add(setValueCommand(JRDesignDataset.PROPERTY_QUERY, new MQuery(jrQuery, mdataset), mdataset));
		} else {
			IPropertySource mquery = (IPropertySource) mdataset.getPropertyValue(JRDesignDataset.PROPERTY_QUERY);
			if (ds.getQuery().getLanguage() == null || !ds.getQuery().getLanguage().equals(lang))
				command.add(setValueCommand(JRDesignQuery.PROPERTY_LANGUAGE, lang, mquery));
			if (!ds.getQuery().getText().equals(qtext))
				command.add(setValueCommand(JRDesignQuery.PROPERTY_TEXT, qtext, mquery));
		}
		String fexprtext = filterExpression.getText();
		if (fexprtext.trim().equals(""))
			fexprtext = null;
		command.add(setValueCommand(JRDesignDataset.PROPERTY_FILTER_EXPRESSION, fexprtext, mdataset));
		command.add(setValueCommand(MDataset.PROPERTY_MAP, newdataset.getPropertiesMap(), mdataset));

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

		List<JRParameter> dssparameters = ds.getParametersList();
		List<JRParameter> sparams = newdataset.getParametersList();
		for (JRParameter f : dssparameters)
			command.add(new DeleteParameterCommand(ds, f));
		for (JRParameter newf : sparams)
			command.add(new CreateParameterCommand(ds, newf, -1));
	}

	private Command setValueCommand(String property, Object value, IPropertySource target) {
		SetValueCommand cmd = new SetValueCommand();
		cmd.setTarget(target);
		cmd.setPropertyId(property);
		cmd.setPropertyValue(value);
		return cmd;
	}
}