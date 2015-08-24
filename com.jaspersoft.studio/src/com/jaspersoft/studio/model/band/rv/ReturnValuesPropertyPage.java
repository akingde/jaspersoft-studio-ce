/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.model.band.rv;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.ExpressionReturnValue;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.DesignExpressionReturnValue;
import net.sf.jasperreports.engine.design.JRDesignVariable;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.band.JRBandDTO;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.EditButton;
import com.jaspersoft.studio.swt.widgets.table.IEditElement;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.swt.widgets.table.NewButton;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

/**
 * Return values configuration page for a element
 * 
 * @author veaceslav chicu
 * 
 */
public class ReturnValuesPropertyPage extends JSSHelpWizardPage {

	private final class TLabelProvider extends LabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			ExpressionReturnValue val = (ExpressionReturnValue) element;
			switch (columnIndex) {
			case 0:
				if (val.getExpression() == null)
					return "";
				return val.getExpression().getText();
			case 1:
				return Misc.nvl(val.getToVariable());
			case 2:
				if (val.getCalculation() == null)
					return "";
				return val.getCalculation().getName();
			case 3:
				return Misc.nvl(val.getIncrementerFactoryClassName());
			}
			return ""; //$NON-NLS-1$
		}
	}

	private JRBandDTO dto;
	protected Table table;
	protected TableViewer tableViewer;

	protected ReturnValuesPropertyPage() {
		super("bandreturnvalues"); //$NON-NLS-1$
		setTitle(Messages.ReturnValuesPropertyPage_1);
		setDescription(Messages.ReturnValuesPropertyPage_2);
	}

	/**
	 * Return the actual dto with the updated return values taken from the dialog
	 * 
	 * @return a dto with the last valid return values
	 */
	public JRBandDTO getDto() {
		return dto;
	}

	/**
	 * Set the current dto and initialize the table showing the return values with the one read from the dto
	 * 
	 * @param dto
	 *          a not null dto
	 */
	public void setDto(JRBandDTO dto) {
		this.dto = dto;
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);
		setControl(composite);

		buildTable(composite);

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 300;
		gd.minimumWidth = 500;
		gd.widthHint = 500;
		table.setLayoutData(gd);

		Composite bGroup = new Composite(composite, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		createButtons(bGroup);
	}

	private void buildTable(Composite composite) {
		table = new Table(composite, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION | SWT.V_SCROLL);
		table.setHeaderVisible(true);

		tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new ListContentProvider());
		tableViewer.setLabelProvider(new TLabelProvider());
		attachCellEditors(tableViewer, table);

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(20, 55, true));
		tlayout.addColumnData(new ColumnWeightData(15, 55, true));
		tlayout.addColumnData(new ColumnWeightData(20, 70, true));
		tlayout.addColumnData(new ColumnWeightData(45, 100, true));
		table.setLayout(tlayout);

		TableColumn[] column = new TableColumn[4];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText(Messages.common_expression);

		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText(Messages.RVPropertyPage_to_variable);

		column[2] = new TableColumn(table, SWT.NONE);
		column[2].setText(Messages.RVPropertyPage_calculation_type);

		column[3] = new TableColumn(table, SWT.NONE);
		column[3].setText(Messages.RVPropertyPage_incrementer_factory_class);

		fillTable();
		for (int i = 0, n = column.length; i < n; i++)
			column[i].pack();
	}

	protected void fillTable() {
		tableViewer.setInput(dto.getValue());
	}

	private void attachCellEditors(final TableViewer viewer, Composite parent) {

		viewer
				.setColumnProperties(new String[] { "EXPRESSION", "TOVARIABLE", "CALCULATIONTYPE", "INCREMENTERFACTORYCLASS" });
	}

	private void createButtons(Composite bGroup) {
		NewButton nb = new NewButton() {
			@Override
			protected void afterElementAdded(Object selement) {
				super.afterElementAdded(selement);
				setEnabled(!getAlreadyUsedToVariables().isEmpty());
			}
		};
		nb.createNewButtons(bGroup, tableViewer, new INewElement() {

			@Override
			public Object newElement(List<?> input, int pos) {
				BandReturnValueDialog d = new BandReturnValueDialog(UIUtils.getShell(), dto.getjConfig(),
						new DesignExpressionReturnValue(), getToVariablesNames());
				if (d.open() == Dialog.OK)
					return d.getValue();
				return null;
			}

		});
		nb.setEnabled(!getAlreadyUsedToVariables().isEmpty());

		EditButton<ExpressionReturnValue> editButton = new EditButton<ExpressionReturnValue>();
		editButton.createEditButtons(bGroup, tableViewer, new IEditElement<ExpressionReturnValue>() {

			@Override
			public void editElement(List<ExpressionReturnValue> input, int pos) {
				DesignExpressionReturnValue value = (DesignExpressionReturnValue) input.get(pos);
				BandReturnValueDialog d = new BandReturnValueDialog(UIUtils.getShell(), dto.getjConfig(),
						(DesignExpressionReturnValue) value.clone(), getToVariablesNames());
				if (d.open() == Dialog.OK)
					input.set(pos, d.getValue());
			}
		});
		editButton.editOnDoubleClick();
		new DeleteButton().createDeleteButton(bGroup, tableViewer);

		ListOrderButtons upDownButtons = new ListOrderButtons();
		upDownButtons.createOrderButtons(bGroup, tableViewer);
	}

	/**
	 * Return an hashset of the variable names that are already used as to variables
	 * 
	 * @return not null hashset of variables already used as a to variable
	 */
	private HashSet<String> getAlreadyUsedToVariables() {
		HashSet<String> result = new HashSet<String>();
		for (ExpressionReturnValue value : dto.getValue())
			result.add(value.getToVariable());
		return result;
	}

	protected String[] toVariables;

	protected String[] getToVariablesNames() {
		if (toVariables == null) {
			List<String> res = new ArrayList<String>();
			HashSet<String> usedVariables = getAlreadyUsedToVariables();
			JRVariable[] vlist = dto.getjConfig().getJasperDesign().getMainDataset().getVariables();
			for (JRVariable o : vlist) {
				JRDesignVariable jdVar = (JRDesignVariable) o;
				if (!jdVar.isSystemDefined() && !usedVariables.contains(jdVar.getName()))
					res.add(jdVar.getName());
			}
			return res.toArray(new String[res.size()]);
		}
		return toVariables;
	}

	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_RETURN_VALUE;
	}

}
