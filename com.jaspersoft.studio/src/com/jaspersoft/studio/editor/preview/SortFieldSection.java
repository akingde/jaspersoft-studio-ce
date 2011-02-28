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
package com.jaspersoft.studio.editor.preview;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRSortField;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignSortField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.SortFieldTypeEnum;
import net.sf.jasperreports.engine.type.SortOrderEnum;

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.variable.MVariable;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.swt.widgets.table.MoveT2TButtons;

public class SortFieldSection {
	private List<JRSortField> inFields;
	private List<JRSortField> outFields;

	private List<JRDesignParameter> prompts;
	private Map<String, Object> params;
	private JasperDesign jDesign;
	private Table rightTable;
	private Table leftTable;
	private TableViewer rightTView;
	private TableViewer leftTView;

	public SortFieldSection(List<JRDesignParameter> prompts, Map<String, Object> params, JasperDesign jDesign) {
		super();
		this.prompts = prompts;
		this.params = params;
		this.jDesign = jDesign;
	}

	public void createSortField(FormToolkit toolkit, CTabFolder tabFolder) {
		for (JRDesignParameter p : prompts)
			if (p.getName().equals("SORT_FIELDS")) {//$NON-NLS-1$
				CTabItem sftab = new CTabItem(tabFolder, SWT.NONE);
				sftab.setText(Messages.SortFieldSection_sort_fields);

				Composite composite = toolkit.createComposite(tabFolder);
				composite.setLayout(new GridLayout(4, false));

				leftTable = new Table(composite, SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
				leftTable.setBackground(tabFolder.getBackground());
				GridData gd = new GridData(GridData.FILL_VERTICAL);
				gd.widthHint = 250;
				leftTable.setLayoutData(gd);
				leftTable.setHeaderVisible(true);

				TableColumn[] col = new TableColumn[1];
				col[0] = new TableColumn(leftTable, SWT.NONE);
				col[0].setText(Messages.SortFieldSection_report_objects);
				col[0].pack();

				TableLayout tlayout = new TableLayout();
				tlayout.addColumnData(new ColumnWeightData(100, false));
				leftTable.setLayout(tlayout);

				leftTView = new TableViewer(leftTable);
				leftTView.setContentProvider(new ListContentProvider());
				leftTView.setLabelProvider(new TLabelProvider());

				Composite bGroup = new Composite(composite, SWT.NONE);
				bGroup.setBackground(tabFolder.getBackground());
				bGroup.setLayout(new GridLayout(1, false));
				bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));

				// -----------------------------------
				rightTable = new Table(composite, SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
				rightTable.setBackground(tabFolder.getBackground());
				rightTable.setLayoutData(new GridData(GridData.FILL_BOTH));
				rightTable.setHeaderVisible(true);

				col = new TableColumn[2];
				col[0] = new TableColumn(rightTable, SWT.NONE);
				col[0].setText(Messages.SortFieldSection_sort_field);
				col[0].pack();

				col[0] = new TableColumn(rightTable, SWT.NONE);
				col[0].setText(Messages.SortFieldSection_sort_order);
				col[0].pack();

				tlayout = new TableLayout();
				tlayout.addColumnData(new ColumnWeightData(65, true));
				tlayout.addColumnData(new ColumnWeightData(35, true));
				rightTable.setLayout(tlayout);

				rightTView = new TableViewer(rightTable);
				rightTView.setContentProvider(new ListContentProvider());
				rightTView.setLabelProvider(new TLabelProvider());

				createOrderButtons(tabFolder, composite);

				new MoveT2TButtons().createButtons(bGroup, leftTView, rightTView);

				sftab.setControl(composite);

				fillTable();

				break;
			}
	}

	private final class TLabelProvider extends LabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			switch (columnIndex) {
			case 0:
				if (((JRSortField) element).getType().equals(SortFieldTypeEnum.FIELD))
					return JaspersoftStudioPlugin.getImage(MField.getIconDescriptor().getIcon16());
				return JaspersoftStudioPlugin.getImage(MVariable.getIconDescriptor().getIcon16());
			}
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return ((JRSortField) element).getName();
			case 1:
				return ((JRSortField) element).getOrderValue().getName();
			}
			return ""; //$NON-NLS-1$
		}
	}

	private void createOrderButtons(CTabFolder tabFolder, Composite composite) {
		Composite bGroup = new Composite(composite, SWT.NONE);
		bGroup.setBackground(tabFolder.getBackground());
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		new ListOrderButtons().createOrderButtons(bGroup, rightTView);
	}

	@SuppressWarnings("unchecked")
	public void fillTable() {
		inFields = new ArrayList<JRSortField>();
		List<JRField> flist = jDesign.getFieldsList();
		for (JRField f : flist) {
			inFields.add(new JRDesignSortField(f.getName(), SortFieldTypeEnum.FIELD, SortOrderEnum.ASCENDING));
		}
		List<JRVariable> vlist = jDesign.getVariablesList();
		for (JRVariable f : vlist) {
			inFields.add(new JRDesignSortField(f.getName(), SortFieldTypeEnum.VARIABLE, SortOrderEnum.ASCENDING));
		}
		leftTView.setInput(inFields);

		Object obj = params.get("SORT_FIELDS");//$NON-NLS-1$
		if (obj == null || !(obj instanceof List)) {
			outFields = new ArrayList<JRSortField>();

			params.put("SORT_FIELDS", outFields);//$NON-NLS-1$
		} else
			outFields = (List<JRSortField>) obj;

		// check if fields exists in the report
		List<JRSortField> dlist = new ArrayList<JRSortField>();
		for (JRSortField f : outFields) {
			if (f.getType().equals(SortFieldTypeEnum.FIELD) && jDesign.getFieldsMap().get(f.getName()) == null)
				dlist.add(f);
			if (f.getType().equals(SortFieldTypeEnum.VARIABLE) && jDesign.getVariablesMap().get(f.getName()) == null)
				dlist.add(f);
		}
		outFields.removeAll(dlist);

		rightTView.setInput(outFields);
	}
}
