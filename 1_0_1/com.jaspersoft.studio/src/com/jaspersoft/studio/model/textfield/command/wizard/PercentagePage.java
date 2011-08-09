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
/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model.textfield.command.wizard;

import java.util.ArrayList;

import net.sf.jasperreports.engine.JRCloneable;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.ResetTypeEnum;

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.model.textfield.MPercentage;
import com.jaspersoft.studio.model.textfield.command.CreatePercentageCommand;
import com.jaspersoft.studio.property.dataset.TLabelProvider;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;

public class PercentagePage extends WizardPage {
	private static final String GROUP2 = "(Group) ";
	private JRCloneable field;
	private ResetTypeEnum rtype;
	private JRGroup group;
	private JasperDesign jDesign;
	private Combo rtypeList;
	private TableViewer fieldsView;
	private Table fieldsTable;
	private java.util.List<String> rtypes;

	public JRCloneable getField() {
		return field;
	}

	public ResetTypeEnum getResetType() {
		return rtype;
	}

	public JRGroup getGroup() {
		return group;
	}

	public void init(JasperDesign jDesign) {
		this.jDesign = jDesign;
	}

	public PercentagePage() {
		super("pecentage"); //$NON-NLS-1$
		setTitle("Percentage Configuration Wizard");
		setDescription("Please, select a field for wich you want percentage.");
		setImageDescriptor(MPercentage.getIconDescriptor().getIcon32());
	}

	@Override
	public void dispose() {
		int sel = rtypeList.getSelectionIndex();
		switch (sel) {
		case 0:
			rtype = ResetTypeEnum.REPORT;
			break;
		case 1:
			rtype = ResetTypeEnum.COLUMN;
			break;
		case 2:
			rtype = ResetTypeEnum.PAGE;
			break;
		default:
			if (sel > 2) {
				String groupname = rtypes.get(sel).substring(GROUP2.length());
				group = jDesign.getGroupsMap().get(groupname);
			}
		}

		StructuredSelection s = (StructuredSelection) fieldsView.getSelection();
		if (!s.isEmpty())
			field = (JRCloneable) s.getFirstElement();

		super.dispose();
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		setControl(composite);
		composite.setLayout(new GridLayout(1, false));

		Label lblResetType = new Label(composite, SWT.NONE);
		lblResetType.setText("Reset Type");

		rtypeList = new Combo(composite, SWT.BORDER);
		rtypeList.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label lblFields = new Label(composite, SWT.NONE);
		lblFields.setText("Fields");

		fieldsTable = new Table(composite, SWT.V_SCROLL | SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 300;
		fieldsTable.setLayoutData(gd);
		fieldsTable.setHeaderVisible(true);

		TableColumn[] col = new TableColumn[1];
		col[0] = new TableColumn(fieldsTable, SWT.NONE);
		col[0].setText("Dataset Objects");
		col[0].pack();

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(100, false));
		fieldsTable.setLayout(tlayout);

		fieldsView = new TableViewer(fieldsTable);
		fieldsView.setContentProvider(new ListContentProvider());
		fieldsView.setLabelProvider(new TLabelProvider());

		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), "Jaspersoft.wizard");//$NON-NLS-1$

		fillData();
	}

	private void fillData() {
		java.util.List<JRCloneable> flist = new ArrayList<JRCloneable>();
		for (JRField f : jDesign.getFieldsList()) {
			if (CreatePercentageCommand.isNumber(f.getValueClass())) {
				flist.add(f);
			}
		}
		for (JRVariable f : jDesign.getVariablesList()) {
			if (CreatePercentageCommand.isNumber(f.getValueClass())) {
				flist.add(f);
			}
		}
		fieldsView.setInput(flist);
		fieldsTable.select(0);

		rtypes = new ArrayList<String>();
		rtypes.add(ResetTypeEnum.REPORT.getName());
		rtypes.add(ResetTypeEnum.COLUMN.getName());
		rtypes.add(ResetTypeEnum.PAGE.getName());
		for (JRGroup group : jDesign.getGroupsList()) {
			rtypes.add(GROUP2 + group.getName());
		}

		rtypeList.setItems(rtypes.toArray(new String[rtypes.size()]));
		rtypeList.select(0);
	}
}
