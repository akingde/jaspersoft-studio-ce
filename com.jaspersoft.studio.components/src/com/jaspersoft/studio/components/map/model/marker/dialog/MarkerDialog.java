package com.jaspersoft.studio.components.map.model.marker.dialog;

import net.sf.jasperreports.components.map.Marker;
import net.sf.jasperreports.components.map.MarkerProperty;
import net.sf.jasperreports.components.map.StandardMarker;
import net.sf.jasperreports.components.map.StandardMarkerProperty;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.property.descriptor.expression.dialog.JRExpressionEditor;
import com.jaspersoft.studio.swt.widgets.WTextExpression;

public class MarkerDialog extends Dialog {
	private StandardMarker value;

	private Text title;
	private Text longit;
	private Text latit;

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

		// here a table starts
		fillValue(value);
		return composite;
	}

	private ExpressionContext expContext;

	public void setValue(StandardMarker value, ExpressionContext expContext) {
		this.value = value;
		this.expContext = expContext;
	}

	private void fillValue(StandardMarker value) {
		for (MarkerProperty smp : value.getProperties()) {
			if (smp.getName().equals(Marker.PROPERTY_title))
				title.setText(smp.getValue());
			else if (smp.getName().equals(Marker.PROPERTY_longitude))
				longit.setText(smp.getValue());
			else if (smp.getName().equals(Marker.PROPERTY_latitude))
				latit.setText(smp.getValue());
		}
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
