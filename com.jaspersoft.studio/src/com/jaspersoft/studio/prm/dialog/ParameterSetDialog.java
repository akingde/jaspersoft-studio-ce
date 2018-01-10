/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.prm.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.MReportRoot;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.model.util.ReportFactory;
import com.jaspersoft.studio.prm.ParameterSet;
import com.jaspersoft.studio.property.dataset.fields.PropertiesDialog;
import com.jaspersoft.studio.property.dataset.fields.table.TColumn;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.EditButton;
import com.jaspersoft.studio.swt.widgets.table.IEditElement;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.swt.widgets.table.NewButton;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.ParameterEvaluationTimeEnum;

public class ParameterSetDialog extends ATitledDialog {
	private int indx = -1;
	private ParameterSet prmSet;
	private Text text;
	private Table table;
	private EditButton<JRDesignParameter> eBtn;

	public ParameterSetDialog(Shell parentShell, Table table) {
		this(parentShell, -1, new ParameterSet(), table);
	}

	public ParameterSetDialog(Shell parentShell, int indx, ParameterSet prmSet, Table table) {
		super(parentShell);
		this.prmSet = prmSet;
		this.indx = indx;
		this.table = table;
		setTitle("Parameter Set");
		setDescription("");
		setDefaultSize(600, 600);
	}

	@Override
	public boolean close() {
		jrConfig.dispose();
		return super.close();
	}

	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setLayout(new GridLayout(2, false));
		Label label = new Label(composite, SWT.NONE);
		label.setText(Messages.common_name);

		int style = SWT.BORDER;
		if (prmSet.isBuiltIn())
			style = style | SWT.READ_ONLY;
		text = new Text(composite, style);
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		text.setText(Misc.nvl(prmSet.getName())); // $NON-NLS-1$
		UIUtils.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				setName();
			}
		});

		if (!prmSet.isBuiltIn())
			text.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent e) {
					setName();
				}
			});

		createTable(composite);

		applyDialogFont(composite);
		return composite;
	}

	protected void createTable(final Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		cmp.setLayoutData(gd);

		Label lbl = new Label(cmp, SWT.NONE);
		lbl.setText("Parameters");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		lbl.setLayoutData(gd);

		final TableViewer viewer = new TableViewer(cmp,
				SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

		// create the columns
		// not yet implemented
		createColumns(viewer);

		// make lines and header visible
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setInput(prmSet.getParameters());

		viewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				eBtn.push();
			}
		});

		gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan = 5;
		gd.grabExcessHorizontalSpace = true;
		table.setLayoutData(gd);

		cmp = new Composite(cmp, SWT.NONE);
		cmp.setLayout(new GridLayout());
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 80;
		cmp.setLayoutData(gd);

		new NewButton().createNewButtons(cmp, viewer, new INewElement() {

			@Override
			public Object newElement(List<?> input, int pos) {
				PropertiesDialog<JRDesignParameter> d = new PropertiesDialog<JRDesignParameter>(parent.getShell(),
						new JRDesignParameter(), tcolumns, MParameter.getIconDescriptor().getDescription(), jrConfig);
				if (d.open() == Dialog.OK)
					return d.getElement();
				return null;
			}
		});
		new ListOrderButtons().createOrderButtons(cmp, viewer);
		eBtn = new EditButton<JRDesignParameter>();
		eBtn.createEditButtons(cmp, viewer, new IEditElement<JRDesignParameter>() {

			@Override
			public void editElement(List<JRDesignParameter> input, int pos) {
				JRDesignParameter prm = input.get(pos);
				PropertiesDialog<JRDesignParameter> d = new PropertiesDialog<JRDesignParameter>(parent.getShell(),
						(JRDesignParameter) prm.clone(), tcolumns, MParameter.getIconDescriptor().getDescription(),
						jrConfig);
				if (d.open() == Dialog.OK)
					input.set(pos, d.getElement());
			}
		});
		new DeleteButton().createDeleteButton(cmp, viewer);
	}

	private void createColumns(TableViewer viewer) {
		TableViewerColumn colFirstName = new TableViewerColumn(viewer, SWT.NONE);
		colFirstName.getColumn().setWidth(200);
		colFirstName.getColumn().setText("Name");
		colFirstName.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				JRDesignParameter p = (JRDesignParameter) element;
				return p.getName();
			}
		});

		colFirstName = new TableViewerColumn(viewer, SWT.NONE);
		colFirstName.getColumn().setWidth(200);
		colFirstName.getColumn().setText("Description");
		colFirstName.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				JRDesignParameter p = (JRDesignParameter) element;
				return p.getDescription();
			}
		});

		JasperDesign jd = new JasperDesign();
		jrConfig = JasperReportsConfiguration.getDefaultJRConfig();
		jrConfig.setJasperDesign(jd);
		ANode node = new MReportRoot(jrConfig, jd);
		ANode report = new MReport(node, jrConfig);
		mdataset = new MDataset(report, jd.getMainDesignDataset(), -1);
		ReportFactory.createDataset(mdataset, jd.getMainDesignDataset(), true);

		createNameColumn();
		createIsForPrompt();
		createTypeColumn();
		createNestedTypeColumn();
		createDescriptionColumn();
		createDefaultExpression();
		createEvaluationTime();
		createPropertiesColumn();

	}

	private void setValidationError(String message) {
		getButton(IDialogConstants.OK_ID).setEnabled(message == null);
		setDescription(message);
	}

	public ParameterSet getPValue() {
		return this.prmSet;
	}

	protected void setName() {
		setValidationError(null);
		String pname = text.getText();
		if (pname.isEmpty()) {
			setValidationError("Name can't be empty");
			return;
		}
		for (int i = 0; i < table.getItemCount(); i++) {
			TableItem ti = table.getItem(i);
			if (ti.getText(0).equals(pname) && i != indx) {
				setValidationError("This name already exists, please select another one");
				return;
			}
		}
		prmSet.setName(pname);
	}

	private MDataset mdataset;

	private List<TColumn> tcolumns = new ArrayList<TColumn>();
	private JasperReportsConfiguration jrConfig;

	private void createNameColumn() {
		TColumn c = new TColumn();
		c.setPropertyName("name");
		c.setLabel(Messages.ParametersTable_name);
		c.setDescription(Messages.MParameterSystem_name_description);
		c.setValue(mdataset);
		tcolumns.add(c);
	}

	private void createIsForPrompt() {
		TColumn c = new TColumn();
		c.setPropertyName("forPrompting");
		c.setLabel(Messages.ParametersTable_isForPrompt);
		c.setDescription(Messages.MParameter_is_for_prompting_description);
		c.setPropertyType(boolean.class.getName());
		tcolumns.add(c);
	}

	private void createTypeColumn() {
		TColumn c = new TColumn();
		c.setPropertyName("valueClassName");
		c.setLabel(Messages.ParametersTable_class);
		c.setDescription(Messages.MParameterSystem_class_description);
		c.setPropertyType(Class.class.getName());
		tcolumns.add(c);
	}

	private void createNestedTypeColumn() {
		TColumn c = new TColumn();
		c.setPropertyName("nestedTypeName");
		c.setLabel(Messages.MParameter_nested_type_name);
		c.setDescription(Messages.MParameter_nested_type_name_description);
		c.setPropertyType(Class.class.getName());
		tcolumns.add(c);
	}

	private void createDefaultExpression() {
		TColumn c = new TColumn();
		c.setPropertyName("defaultValueExpression");
		c.setLabel(Messages.MParameter_default_value_expression);
		c.setDescription(Messages.MParameter_default_value_expression_description);
		c.setPropertyType(JRDesignExpression.class.getName());
		c.setValue(mdataset);
		tcolumns.add(c);
	}

	private void createDescriptionColumn() {
		TColumn c = new TColumn();
		c.setPropertyName("description");
		c.setLabel(Messages.ParametersTable_description);
		c.setDescription(Messages.MParameter_description_description);
		c.setValue(mdataset.getValue());
		tcolumns.add(c);
	}

	private void createEvaluationTime() {
		TColumn c = new TColumn();
		c.setPropertyName("evaluationTime");
		c.setLabel(Messages.common_evaluation_time);
		c.setDescription(Messages.MParameter_3);
		c.setValue(mdataset.getValue());
		c.setPropertyType(ParameterEvaluationTimeEnum.class.getName());
		tcolumns.add(c);
	}

	private void createPropertiesColumn() {
		TColumn c = new TColumn();
		c.setPropertyName("properties");
		c.setLabel(Messages.common_properties);
		c.setDescription(Messages.MParameter_properties_description);
		c.setPropertyType(JRPropertiesMap.class.getName());
		c.setType("properties");
		c.setValue(mdataset);
		tcolumns.add(c);
	}
}
