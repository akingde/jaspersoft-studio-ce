/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.propexpr.dialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.wb.swt.SWTResourceManager;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptor.properties.dialog.PropertyDTO;
import com.jaspersoft.studio.property.infoList.ElementDescription;
import com.jaspersoft.studio.property.infoList.SelectableComposite;

import net.sf.jasperreports.eclipse.util.Misc;

/**
 * 
 * This dialog offer the methods to define a property in the form key and value
 *
 */
public class JRPropertyDialog extends Dialog {

	/**
	 * Object that represent the properties
	 */
	protected PropertyDTO value;

	/**
	 * Composite where the control for the value definition are placed
	 */
	protected Composite vcmp;

	/**
	 * text where the property value is typed
	 */
	protected Text tvalue;

	/**
	 * Combobox where the user can type the property key or choose a previously
	 * selected one
	 */
	protected Combo cprop;

	/**
	 * List of special properties that the user can easily select
	 */
	protected List<ElementDescription> hints;
	protected boolean showPropertyName = true;
	/**
	 * Composite with a stack layout where the value control is placed, other
	 * controls can be placed here and hidden using the layout
	 */
	protected Composite stackComposite;

	/**
	 * Layout used by the stack composite
	 */
	protected StackLayout stackLayout;

	protected SelectableComposite propertiesSuggestions;

	public JRPropertyDialog(Shell parentShell) {
		super(parentShell);
	}

	public void setShowPropertyName(boolean showPropertyName) {
		this.showPropertyName = showPropertyName;
	}

	private String title = Messages.JRPropertyDialog_shellTitle;

	public void setTitle(String title) {
		this.title = title;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(title);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	/**
	 * Return the modify listener used when the user change the property name
	 * 
	 * @return a not null modify listener
	 */
	protected ModifyListener getModifyListener() {
		return e -> {
			String newtext = cprop.getText();
			value.setName(newtext);
			if (propertiesSuggestions != null)
				propertiesSuggestions.showOnlyElement(newtext);
		};
	}

	/**
	 * Place holder used to create additional controls after the properties name
	 * 
	 * @param parent
	 *            composite where the controls can be created (by default its a grid
	 *            layout with two columns)
	 */
	protected void createAdditionalControls(Composite parent) {
		// no additional controls
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setLayout(new GridLayout(2, false));

		List<ElementDescription> h = null;
		if (showPropertyName) {
			Label label = new Label(composite, SWT.NONE);
			label.setText(Messages.JRPropertyDialog_propName);

			h = getHints();

			cprop = new Combo(composite, SWT.BORDER);
			GridData gd = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL);
			gd.widthHint = 300;
			cprop.setLayoutData(gd);

			if (h != null) {
				List<String> comboItems = new ArrayList<>();
				for (ElementDescription hint : h)
					comboItems.add(hint.getName());
				cprop.setItems(comboItems.toArray(new String[comboItems.size()]));
			}
			cprop.addModifyListener(getModifyListener());
		}

		createAdditionalControls(composite);

		stackComposite = new Composite(composite, SWT.NONE);
		stackLayout = new StackLayout();
		stackComposite.setLayout(stackLayout);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		stackComposite.setLayoutData(gd);

		vcmp = createValueControl(stackComposite);
		stackLayout.topControl = vcmp;

		fillValue(value);
		if (showPropertyName && !Misc.isNullOrEmpty(h))
			createSpecialProperties(composite);
		return composite;
	}

	/**
	 * Generate the properties hints
	 */
	protected void initializeHints() {
		hints = HintsPropertiesList.getElementProperties(value.getJrElement(), value.geteContext());
		Collections.sort(hints);
	}

	/**
	 * Load in a lazy way the properties hints, cache and return them
	 * 
	 * @return the hints
	 */
	protected List<ElementDescription> getHints() {
		if (hints == null)
			initializeHints();
		return hints;
	}

	/**
	 * Create the special properties section
	 * 
	 * @param cmp
	 *            composite where the section will be placed
	 * @return a scrollable composite containing the properties
	 */
	protected SelectableComposite createSpecialProperties(Composite cmp) {
		Section expandableSection = new Section(cmp, Section.TREE_NODE);
		expandableSection.setText(Messages.JRPropertyDialog_spacialProperties);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		expandableSection.setLayoutData(data);
		expandableSection.titleBarTextMarginWidth = 0;
		expandableSection.setFont(SWTResourceManager.getBoldFont(expandableSection.getFont()));
		expandableSection.setSeparatorControl(new Label(expandableSection, SWT.SEPARATOR | SWT.HORIZONTAL));

		propertiesSuggestions = new SelectableComposite(expandableSection);
		GridData compData = new GridData(GridData.FILL_BOTH);
		compData.heightHint = 200;
		propertiesSuggestions.setLayoutData(compData);
		propertiesSuggestions.SetDoubleClickListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String selectedProp = ((ElementDescription) e.data).getName();
				cprop.setText(selectedProp);
			}
		});

		expandableSection.setClient(propertiesSuggestions);
		expandableSection.addExpansionListener(new ExpansionAdapter() {

			private Point oldSize = null;

			@Override
			public void expansionStateChanging(ExpansionEvent e) {
				if (e.getState())
					oldSize = getShell().getSize();
			}

			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				if (!propertiesSuggestions.isItemSetted() && e.getState())
					propertiesSuggestions.setItems(getHints());
				if (e.getState()) {
					Point actualSize = getShell().getSize();
					getShell().setSize(actualSize.x, actualSize.y + 200);
				} else
					getShell().setSize(oldSize);
			}
		});
		return propertiesSuggestions;
	}

	/**
	 * Create the controls for the value input
	 * 
	 * @param cmp
	 *            where the control will be placed
	 * @return composite containing the control
	 */
	protected Composite createValueControl(Composite cmp) {
		Composite composite = new Composite(cmp, SWT.NONE);
		composite.setLayout(new GridLayout());

		tvalue = new Text(composite, SWT.BORDER);
		tvalue.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		tvalue.setText(Messages.JRPropertyDialog_valuePlaceHolder);
		tvalue.addModifyListener(e -> value.setValue(tvalue.getText()));
		return composite;
	}

	public void setValue(PropertyDTO value) {
		this.value = value;
	}

	private void fillValue(PropertyDTO value) {
		if (cprop != null)
			cprop.setText(Misc.nvl(value.getName()));
		tvalue.setText(getValueText(value.getValue()));
	}

	protected String getValueText(Object value) {
		if (value instanceof String) {
			// here we care only about strings
			return (String) value;
		}
		return "";
	}

}
