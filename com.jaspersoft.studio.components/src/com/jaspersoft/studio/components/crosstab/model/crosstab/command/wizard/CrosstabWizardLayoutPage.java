/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.crosstab.command.wizard;

import java.awt.Color;
import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.nebula.widgets.tablecombo.TableCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.dialog.CrosstabStyle;
import com.jaspersoft.studio.components.crosstab.model.dialog.CrosstabStylePreview;
import com.jaspersoft.studio.property.color.ColorSchemaGenerator;
import com.jaspersoft.studio.property.color.Tag;

public class CrosstabWizardLayoutPage extends WizardPage {
	private MCrosstab crosstab;
	private boolean isAddRowTotal = true;
	private boolean isAddColTotal = true;
	
	/**
	 * Base color of the table
	 */
	private TableCombo colorScheme;
	
	/**
	 * Variations scheme for the color
	 */
	private Combo variations;
	
	/**
	 * Checkbox for the rows alternated color
	 */
	private Button whiteGrid;
	
	/**
	 * List of the available schemes for the variations
	 */
	private List<Tag> variants;
	
	/**
	 * Table preview widget
	 */
	private CrosstabStylePreview preview;
	
	/**
	 * Last style generated
	 */
	private CrosstabStyle lastGeneratedStyle;
	
	/**
	 * Listener called when a control is modified, cause the regeneration of the 
	 * lastGeneratedStyle and the update of the preview
	 */
	private ModifyListener modifyListener = new ModifyListener() {
		
		@Override
		public void modifyText(ModifyEvent e) {
			notifyChange();	
		}
	};
	
	/**
	 * Listener called when a control get a selection event, cause the regeneration of the 
	 * lastGeneratedStyle and the update of the preview
	 */
	private SelectionAdapter selectionListener = new SelectionAdapter() {
		
		@Override
		public void widgetSelected(SelectionEvent e) {
			notifyChange();
		}
	};
	

	public boolean isAddRowTotal() {
		return isAddRowTotal;
	}

	public boolean isAddColTotal() {
		return isAddColTotal;
	}

	public void setCrosstab(MCrosstab crosstab) {
		this.crosstab = crosstab;
	}

	public MCrosstab getCrosstab() {
		return crosstab;
	}

	protected CrosstabWizardLayoutPage() {
		super("crosstablayoutpage"); //$NON-NLS-1$
		setTitle(Messages.CrosstabWizardLayoutPage_layout);
		setImageDescriptor(
				Activator.getDefault().getImageDescriptor("icons/wizard_preview.png"));//$NON-NLS-1$
		setDescription(Messages.CrosstabWizardLayoutPage_description);
	}
	
	private void createColorGroup(Composite parent){
		Group group = new Group(parent, SWT.NONE);
		group.setText("Cell colors");
		group.setLayout(new GridLayout(2,false));
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label firstLabel = new Label(group,SWT.NONE);
		firstLabel.setText("Color schema");
		
		colorScheme = new TableCombo(group, SWT.BORDER);
		List<String> colors = ColorSchemaGenerator.getColors();
		for(String color : colors){
			TableItem item = new TableItem(colorScheme.getTable(), SWT.READ_ONLY);
			item.setImage(ColorSchemaGenerator.getImagePreview(color));
			item.setText(color);
		}
		
		colorScheme.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		colorScheme.select(0);
		colorScheme.setEditable(false);
		
		Label secondLabel = new Label(group,SWT.NONE);
		secondLabel.setText("Variations");
		
		variations = new Combo(group,SWT.NONE);
		variations.setItems(getVariantsName());
		variations.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		variations.select(0);
		
		whiteGrid = new Button(group, SWT.CHECK);
		whiteGrid.setText("Use white grid");
		GridData checkBoxData = new GridData(GridData.FILL_HORIZONTAL);
		checkBoxData.horizontalSpan = 2;
		whiteGrid.setLayoutData(checkBoxData);
		
		variations.addModifyListener(modifyListener);
		whiteGrid.addSelectionListener(selectionListener);
		colorScheme.addSelectionListener(selectionListener);
	}
	
	private void createSectionsGroup(Composite parent){
		Group group = new Group(parent, SWT.NONE);
		group.setText("Visible sections");
		group.setLayout(new GridLayout(1,false));
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		final Button addRowTotals = new Button(group, SWT.CHECK);
		addRowTotals.setText(Messages.CrosstabWizardLayoutPage_add_row_group_totals);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		addRowTotals.setLayoutData(gd);
		addRowTotals.setSelection(true);
		addRowTotals.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				isAddRowTotal = addRowTotals.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		final Button addColumnTotals = new Button(group, SWT.CHECK);
		addColumnTotals.setText(Messages.CrosstabWizardLayoutPage_add_column_group_totals);
		gd = new GridData();
		gd.horizontalSpan = 2;
		addColumnTotals.setLayoutData(gd);
		addColumnTotals.setSelection(true);
		addColumnTotals.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				isAddColTotal = addColumnTotals.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
	}
	
	/**
	 * Create the control ad the left of the preview, so the cell color and 
	 * border groups
	 * 
	 * @param parent parent of the composite
	 */
	private void createLeftCol(Composite parent){
		Composite leftCol = new Composite(parent, SWT.NONE);
		leftCol.setLayout(new GridLayout(1,false));
		createColorGroup(leftCol);
		createSectionsGroup(leftCol);
		GridData leftPanelData = new GridData(GridData.FILL_VERTICAL);
		leftPanelData.widthHint = 300;
		leftPanelData.minimumHeight= 200;
		leftCol.setLayoutData(leftPanelData);
	}
	
	
	/**
	 * Return an array of string that represents the human name of the 
	 * color variations
	 * 
	 * @return array of the color variations name
	 */
	private String[] getVariantsName(){
		variants = ColorSchemaGenerator.getVariants();
		String[] variantsName = new String[variants.size()];
		for(int i=0; i<variants.size(); i++)
			variantsName[i] = variants.get(i).getName();
		return variantsName;
	}
	
	/**
	 * Generate the preview area
	 * 
	 * @param parent
	 */
	private void createPreview(Composite parent){
		Group group = new Group(parent, SWT.NONE);
		group.setText("Style preview");
		group.setLayout(new GridLayout(1,false));
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		preview = new CrosstabStylePreview(group, SWT.NONE);
		preview.setLayoutData(new GridData(GridData.FILL_BOTH));
	}
	
	/**
	 * Called when some property change, rebuild the table style with the actual state of the control
	 * and request the redraw of the preview
	 */
	private void notifyChange(){
		String colorName =colorScheme.getItem(colorScheme.getSelectionIndex());
		Color color = ColorSchemaGenerator.getColor(colorName);
		ColorSchemaGenerator.SCHEMAS variantKey = (ColorSchemaGenerator.SCHEMAS)variants.get(variations.getSelectionIndex()).getValue();
		lastGeneratedStyle = new CrosstabStyle(new RGB(color.getRed(), color.getGreen(), color.getBlue()),variantKey, whiteGrid.getSelection());
		preview.setTableStyle(lastGeneratedStyle);
	}
	
	/**
	 * Return the last generated style for the table, that is the effective one when 
	 * the dialog was closed
	 * 
	 * @return the style to apply to the table
	 */
	public CrosstabStyle getSelectedStyle(){
		return lastGeneratedStyle;
	}

	public void createControl(Composite parent) {
		Composite dialog = new Composite(parent, SWT.NONE);
		GridLayout generalLayout = new GridLayout(2,false);
		dialog.setLayout(generalLayout);
		setControl(dialog);
		
		createLeftCol(dialog);
		createPreview(dialog);
		notifyChange();
		// Label lbl = new Label(composite, SWT.NONE);
		// lbl.setText("Color scheme");
		//
		// CCombo colorScheme = new CCombo(composite, SWT.BORDER);
		// // colorScheme.setItems(ModelUtils.getDataSources(jasperDesign));
		// // colorScheme.select(0);
		//
		// lbl = new Label(composite, SWT.NONE);
		// lbl.setText("Variations");
		//
		// CCombo variations = new CCombo(composite, SWT.BORDER);

		// Button useWhiteGrid = new Button(composite, SWT.CHECK);
		// useWhiteGrid.setText("Use white grid");
		// GridData gd = new GridData();
		// gd.horizontalSpan = 2;
		// useWhiteGrid.setLayoutData(gd);
		//
		// Button showGridLines = new Button(composite, SWT.CHECK);
		// showGridLines.setText("Show grid lines (adding cell border)");
		// gd = new GridData();
		// gd.horizontalSpan = 2;
		// showGridLines.setLayoutData(gd);


		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), "Jaspersoft.wizard");

	}

}
