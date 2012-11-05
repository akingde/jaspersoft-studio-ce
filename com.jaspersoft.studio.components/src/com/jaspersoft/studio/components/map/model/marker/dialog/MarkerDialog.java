package com.jaspersoft.studio.components.map.model.marker.dialog;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.components.map.Marker;
import net.sf.jasperreports.components.map.MarkerProperty;
import net.sf.jasperreports.components.map.StandardMarker;
import net.sf.jasperreports.components.map.StandardMarkerProperty;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.property.descriptor.expression.dialog.JRExpressionEditor;
import com.jaspersoft.studio.swt.widgets.WTextExpression;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.EditButton;
import com.jaspersoft.studio.swt.widgets.table.IEditElement;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.swt.widgets.table.NewButton;

public class MarkerDialog extends Dialog {
	private final class EditElement implements IEditElement<MarkerProperty> {
		@Override
		public void editElement(List<MarkerProperty> input, int pos) {
			StandardMarkerProperty v = (StandardMarkerProperty) input.get(pos);
			if (v == null)
				return;
			v = (StandardMarkerProperty) v.clone();
			MarkerPropertyDialog dialog = new MarkerPropertyDialog(Display
					.getDefault().getActiveShell());

			dialog.setValue((StandardMarkerProperty) v, expContext);
			if (dialog.open() == Window.OK)
				input.set(pos, v);
		}
	}

	private StandardMarker value;

	private Text title;
	private Text longit;
	private Text latit;
	private Table table;
	private TableViewer tableViewer;
	private EditButton<MarkerProperty> editButton;

	protected MarkerDialog(Shell parentShell) {
		super(parentShell);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets
	 * .Shell)
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Marker Dialog");
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	@Override
	public boolean close() {
		List<MarkerProperty> prps = new ArrayList<MarkerProperty>();
		for (MarkerProperty mp : value.getProperties()) {
			String name = mp.getName();
			if (name.equals(Marker.PROPERTY_title)
					|| name.equals(Marker.PROPERTY_latitude)
					|| name.equals(Marker.PROPERTY_longitude))
				prps.add(mp);
		}
		value.getProperties().clear();
		value.getProperties().addAll(prps);
		List<MarkerProperty> in = (List<MarkerProperty>) tableViewer.getInput();
		value.getProperties().addAll(in);
		return super.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets
	 * .Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setLayout(new GridLayout(3, false));
		Label label = new Label(composite, SWT.NONE);
		label.setText("Title");

		title = new Text(composite, SWT.BORDER);
		title.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		title.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				for (MarkerProperty smp : value.getProperties()) {
					if (smp.getName().equals(Marker.PROPERTY_title))
						((StandardMarkerProperty) smp).setValue(title.getText());
				}
			}
		});

		addExpression(composite, Marker.PROPERTY_title);

		label = new Label(composite, SWT.NONE);
		label.setText("Longitude");

		longit = new Text(composite, SWT.BORDER);
		longit.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		longit.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				for (MarkerProperty smp : value.getProperties()) {
					if (smp.getName().equals(Marker.PROPERTY_longitude)) {
						String text = longit.getText();
						try {
							new Double(text);
							((StandardMarkerProperty) smp).setValue(text);
						} catch (NumberFormatException nfe) {
							longit.setText(smp.getValue());
						}
					}
				}
			}
		});
		addExpression(composite, Marker.PROPERTY_longitude);

		label = new Label(composite, SWT.NONE);
		label.setText("Latitude");

		latit = new Text(composite, SWT.BORDER);
		latit.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		latit.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				for (MarkerProperty smp : value.getProperties()) {
					if (smp.getName().equals(Marker.PROPERTY_latitude)) {
						String text = latit.getText();
						try {
							new Double(text);
							((StandardMarkerProperty) smp).setValue(text);
						} catch (NumberFormatException nfe) {
							latit.setText(smp.getValue());
						}
					}
				}
			}
		});
		addExpression(composite, Marker.PROPERTY_latitude);

		Composite cmp = new Composite(composite, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		cmp.setLayoutData(gd);

		buildTable(cmp);

		gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 200;
		gd.widthHint = 500;
		table.setLayoutData(gd);

		Composite bGroup = new Composite(cmp, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		new NewButton().createNewButtons(bGroup, tableViewer,
				new INewElement() {

					public Object newElement(List<?> input, int pos) {
						StandardMarkerProperty v = new StandardMarkerProperty(
								"property", "value", null);

						MarkerPropertyDialog dialog = new MarkerPropertyDialog(
								Display.getDefault().getActiveShell());
						dialog.setValue(v, expContext);
						if (dialog.open() == Window.OK)
							return v;
						return null;
					}
				});

		editButton = new EditButton<MarkerProperty>();
		editButton.createEditButtons(bGroup, tableViewer, new EditElement());
		new DeleteButton().createDeleteButton(bGroup, tableViewer);
		new ListOrderButtons().createOrderButtons(bGroup, tableViewer);
		// here a table starts
		fillValue(value);
		return composite;
	}

	private void buildTable(Composite composite) {
		table = new Table(composite, SWT.BORDER | SWT.SINGLE
				| SWT.FULL_SELECTION | SWT.V_SCROLL);
		table.setHeaderVisible(true);
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
			}

			@Override
			public void mouseDown(MouseEvent e) {
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				editButton.push();
			}
		});

		tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new ListContentProvider());
		tableViewer.setLabelProvider(new TMarkerPropertyLabelProvider());

		TableColumn[] column = new TableColumn[3];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText("Name");

		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText("Value");

		column[2] = new TableColumn(table, SWT.NONE);
		column[2].setText("Expression");

		for (int i = 0, n = column.length; i < n; i++)
			column[i].pack();

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(30, true));
		tlayout.addColumnData(new ColumnWeightData(35, true));
		tlayout.addColumnData(new ColumnWeightData(35, true));
		table.setLayout(tlayout);
	}

	private ExpressionContext expContext;

	public void setValue(StandardMarker value, ExpressionContext expContext) {
		this.value = value;
		this.expContext = expContext;
	}

	private void fillValue(StandardMarker value) {
		List<MarkerProperty> mprops = new ArrayList<MarkerProperty>();
		for (MarkerProperty smp : value.getProperties()) {
			if (smp.getName().equals(Marker.PROPERTY_title))
				title.setText(smp.getValue());
			else if (smp.getName().equals(Marker.PROPERTY_longitude))
				longit.setText(smp.getValue());
			else if (smp.getName().equals(Marker.PROPERTY_latitude))
				latit.setText(smp.getValue());
			else
				mprops.add(smp);
		}
		tableViewer.setInput(mprops);
	}

	private void addExpression(Composite composite, final String property) {
		Button btnEditExpression = new Button(composite, SWT.FLAT);
		btnEditExpression.setImage(JaspersoftStudioPlugin
				.getImage(WTextExpression.BUTTON_ICON_PATH));
		btnEditExpression.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				for (MarkerProperty smp : value.getProperties()) {
					if (smp.getName().equals(property)) {
						JRDesignExpression expression = (JRDesignExpression) smp
								.getValueExpression();

						JRExpressionEditor wizard = new JRExpressionEditor();
						wizard.setValue(expression);
						wizard.setExpressionContext(expContext);
						WizardDialog dialog = new WizardDialog(Display
								.getDefault().getActiveShell(), wizard);
						dialog.create();
						if (dialog.open() == Dialog.OK) {
							JRDesignExpression value = wizard.getValue();
							((StandardMarkerProperty) smp)
									.setValueExpression(value);
						}

					}
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}
}
