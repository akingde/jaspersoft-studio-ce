package com.jaspersoft.studio.editor.preview.input.array;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.jaspersoft.studio.editor.preview.input.IParameter;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.swt.widgets.table.NewButton;

public class TableDialog<T> extends Dialog {

	private final class TLabelProvider extends LabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			if (element != null)
				if (element instanceof Date)
					return new SimpleDateFormat().format(element);
				else
					return element.toString();
			return "";
		}
	}

	private IParameter prm;
	private Table table;
	private TableViewer tableViewer;

	private T value;

	@Override
	public boolean close() {
		value = (T) tableViewer.getInput();
		return super.close();
	}

	public T getValue() {
		return value;
	}

	public TableDialog(Shell parentShell, T value, IParameter prm) {
		super(parentShell);
		this.value = value;
		this.prm = prm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Parameter: " + prm.getName());
	}

	@Override
	protected Control createDialogArea(final Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);
		((GridLayout) cmp.getLayout()).numColumns = 2;
		((GridLayout) cmp.getLayout()).makeColumnsEqualWidth = false;

		Label lbl = new Label(cmp, SWT.WRAP);
		lbl.setText(prm.getDescription());
		lbl.setToolTipText(prm.getDescription());
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		lbl.setLayoutData(gd);

		buildTable(cmp);

		Composite bGroup = new Composite(cmp, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		new NewButton().createNewButtons(bGroup, tableViewer, new INewElement() {

			public Object newElement(List<?> input, int pos) {
				ElementDialog d = new ElementDialog(parent.getShell(), prm);
				if (d.open() == Dialog.OK)
					return d.getValue();
				return null;
			}

		});

		new DeleteButton().createDeleteButton(bGroup, tableViewer);
		if (prm.getValueClass().isArray() || prm.getValueClass().isAssignableFrom(List.class))
			new ListOrderButtons().createOrderButtons(bGroup, tableViewer);
		return cmp;
	}

	private void buildTable(Composite composite) {
		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 350;
		gd.widthHint = 300;
		table.setLayoutData(gd);
		table.setHeaderVisible(true);

		tableViewer = new TableViewer(table);
		tableViewer.setLabelProvider(new TLabelProvider());
		tableViewer.setContentProvider(new ListContentProvider());

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(100));
		table.setLayout(tlayout);

		TableColumn column = new TableColumn(table, SWT.NONE);
		column.setText("Value");
		column.pack();

		tableViewer.setInput(value);
	}

}
