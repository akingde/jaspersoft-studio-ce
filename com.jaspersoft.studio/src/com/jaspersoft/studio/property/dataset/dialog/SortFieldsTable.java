package com.jaspersoft.studio.property.dataset.dialog;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRSortField;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignSortField;
import net.sf.jasperreports.engine.type.SortFieldTypeEnum;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.swt.widgets.table.NewButton;
import com.jaspersoft.studio.utils.EnumHelper;
import com.jaspersoft.studio.utils.ModelUtils;

public class SortFieldsTable {
	private TableViewer tviewer;
	private Table wtable;
	private Composite composite;
	private JRDesignDataset dataset;

	public SortFieldsTable(Composite parent, JRDesignDataset dataset) {
		this.dataset = dataset;
		createControl(parent);
	}

	public Composite getControl() {
		return composite;
	}

	private void createControl(Composite parent) {
		composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setBackground(parent.getBackground());

		wtable = new Table(composite, SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 100;
		wtable.setLayoutData(gd);
		wtable.setHeaderVisible(true);

		TableColumn[] col = new TableColumn[3];
		col[0] = new TableColumn(wtable, SWT.NONE);
		col[0].setText("Field Name");
		col[0].pack();

		col[1] = new TableColumn(wtable, SWT.NONE);
		col[1].setText("Type");
		col[1].pack();

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(100, false));
		tlayout.addColumnData(new ColumnWeightData(100, false));
		wtable.setLayout(tlayout);

		tviewer = new TableViewer(wtable);
		tviewer.setContentProvider(new ListContentProvider());
		tviewer.setLabelProvider(new TLabelProvider());
		attachCellEditors(tviewer, wtable);

		Composite bGroup = new Composite(composite, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		bGroup.setBackground(parent.getBackground());

		new NewButton().createNewButtons(bGroup, tviewer, new INewElement() {

			public Object newElement(List<?> input) {
				JRDesignSortField f = new JRDesignSortField();
				f.setName(getName());
				f.setType(SortFieldTypeEnum.FIELD);
				return f;
			}

			private String getName() {
				List<JRDesignSortField> list = (List<JRDesignSortField>) tviewer.getInput();
				String name = "Field";
				boolean match = false;
				String tmp = name;
				for (int i = 1; i < 100000; i++) {
					tmp = ModelUtils.getNameFormat(name, i);

					for (JRDesignSortField f : list) {
						match = f.getName().equals(tmp);
						if (match)
							break;
					}
					if (!match)
						break;
				}
				return tmp;
			}
		});
		new DeleteButton().createDeleteButton(bGroup, tviewer);

		new ListOrderButtons().createOrderButtons(bGroup, tviewer);

		List<JRSortField> fields = dataset.getSortFieldsList();
		if (fields == null)
			fields = new ArrayList<JRSortField>();
		tviewer.setInput(fields);
	}

	private void attachCellEditors(final TableViewer viewer, Composite parent) {
		viewer.setCellModifier(new ICellModifier() {
			public boolean canModify(Object element, String property) {
				if (property.equals("NAME")) //$NON-NLS-1$
					return true;
				if (property.equals("TYPE")) //$NON-NLS-1$
					return true;
				return false;
			}

			public Object getValue(Object element, String property) {
				JRDesignSortField prop = (JRDesignSortField) element;
				if ("NAME".equals(property)) //$NON-NLS-1$
					return prop.getName();
				if ("TYPE".equals(property)) //$NON-NLS-1$
					return EnumHelper.getValue(prop.getType(), 0, false);

				return ""; //$NON-NLS-1$
			}

			public void modify(Object element, String property, Object value) {
				TableItem tableItem = (TableItem) element;
				JRDesignSortField field = (JRDesignSortField) tableItem.getData();
				if ("NAME".equals(property)) { //$NON-NLS-1$
					field.setName((String) value);
				} else if ("TYPE".equals(property)) { //$NON-NLS-1$
					field.setType((SortFieldTypeEnum) EnumHelper.getSetValue(SortFieldTypeEnum.values(), value, 0, false));
				}
				tviewer.update(element, new String[] { property });
				tviewer.refresh();
			}
		});

		viewer.setCellEditors(new CellEditor[] { new TextCellEditor(parent),
				new ComboBoxCellEditor(parent, EnumHelper.getEnumNames(SortFieldTypeEnum.values(), NullEnum.NOTNULL)) });
		viewer.setColumnProperties(new String[] { "NAME", "TYPE" }); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private final class TLabelProvider extends LabelProvider implements ITableLabelProvider {

		public String getColumnText(Object element, int columnIndex) {
			JRDesignSortField field = (JRDesignSortField) element;
			switch (columnIndex) {
			case 0:
				return field.getName();
			case 1:
				return field.getType().getName();
			}
			return ""; //$NON-NLS-1$
		}

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
	}
}
