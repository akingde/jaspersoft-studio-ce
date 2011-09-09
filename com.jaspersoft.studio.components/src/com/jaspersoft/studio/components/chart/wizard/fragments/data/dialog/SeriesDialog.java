package com.jaspersoft.studio.components.chart.wizard.fragments.data.dialog;

import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.FormDialog;
import org.eclipse.ui.forms.IManagedForm;

import com.jaspersoft.studio.components.chart.wizard.fragments.data.series.ISeriesFactory;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionCellEditor;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.swt.widgets.table.NewButton;

public class SeriesDialog extends FormDialog {
	private final class TLabelProvider extends LabelProvider implements
			ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			return serie.getColumnText(element, columnIndex);
		}
	}

	private Table table;
	private TableViewer tableViewer;
	private ISeriesFactory<?> serie;

	public SeriesDialog(Shell parentShellProvider, ISeriesFactory<?> serie) {
		super(parentShellProvider);
		this.serie = serie;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Series");
	}

	@Override
	protected void createFormContent(IManagedForm mform) {
		mform.getForm().setText("Chart Dataset Series");

		mform.getForm().getBody().setLayout(new GridLayout(2, false));

		buildTable(mform.getForm().getBody());

		Composite bGroup = new Composite(mform.getForm().getBody(), SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		bGroup.setBackground(mform.getForm().getBody().getBackground());

		new NewButton().createNewButtons(bGroup, tableViewer,
				new INewElement() {

					public Object newElement(List<?> input) {
						return serie.createSerie();
					}

				});

		new DeleteButton().createDeleteButton(bGroup, tableViewer);

		new ListOrderButtons().createOrderButtons(bGroup, tableViewer);

		fillTable(table);
	}

	private void buildTable(Composite composite) {
		table = new Table(composite, SWT.BORDER | SWT.SINGLE
				| SWT.FULL_SELECTION);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 350;
		gd.widthHint = 300;
		table.setLayoutData(gd);
		table.setToolTipText(""); //$NON-NLS-1$
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		tableViewer = new TableViewer(table);
		attachContentProvider(tableViewer);
		attachLabelProvider(tableViewer);
		attachCellEditors(tableViewer, table);

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(100));
		table.setLayout(tlayout);

		TableColumn[] column = new TableColumn[1];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText("Name expression");

		for (int i = 0, n = column.length; i < n; i++)
			column[i].pack();
	}

	private void attachLabelProvider(TableViewer viewer) {
		viewer.setLabelProvider(new TLabelProvider());
	}

	private void attachContentProvider(TableViewer viewer) {
		viewer.setContentProvider(new ListContentProvider());
	}

	private void attachCellEditors(final TableViewer viewer, Composite parent) {
		viewer.setCellModifier(new ICellModifier() {
			public boolean canModify(Object element, String property) {
				if (property.equals("NAME")) //$NON-NLS-1$
					return true;
				return false;
			}

			public Object getValue(Object element, String property) {
				return serie.getValue(element, property);
			}

			public void modify(Object element, String property, Object value) {
				serie.modify(element, property, value);
				tableViewer.update(element, new String[] { property });
				tableViewer.refresh();
			}
		});

		viewer.setCellEditors(new CellEditor[] { new JRExpressionCellEditor(
				parent) });
		viewer.setColumnProperties(new String[] { "NAME" }); //$NON-NLS-1$  
	}

	private void fillTable(Table table) {
		tableViewer.setInput(serie.getList());
	}
}
