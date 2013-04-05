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
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.dialog.CrosstabStyle;
import com.jaspersoft.studio.components.crosstab.model.dialog.CrosstabStylePreview;
import com.jaspersoft.studio.editor.style.TemplateStyle;
import com.jaspersoft.studio.property.color.ColorSchemaGenerator;
import com.jaspersoft.studio.property.color.Tag;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

public class CrosstabWizardLayoutPage extends JSSHelpWizardPage {
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
	 * Checkbox for the grid of color white
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
	private CrosstabStyle lastGeneratedStyle = null;
	
	/**
	 * Checkbox to show or hide the grid
	 */
	private Button showGrid;
	
	/**
	 * the textfield of the title
	 */
	private Text titleText = null;
	
	/**
	 * True if also the title area is created, false otherwise
	 */
	private boolean createTitle;
	
	/**
	 * Template used to initialize the data of the dialog with the 
	 * same of the template, useful for edit
	 */
	private TemplateStyle templateToOpen;
	
	
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

	protected CrosstabWizardLayoutPage(boolean createTitle) {
		super("crosstablayoutpage");  
		setTitle(Messages.CrosstabWizardLayoutPage_layout);
		setImageDescriptor(
				Activator.getDefault().getImageDescriptor("icons/wizard_preview.png")); //$NON-NLS-1$
		setDescription(Messages.CrosstabWizardLayoutPage_description);
		this.createTitle = createTitle;
		this.templateToOpen = null;
	}
	
	protected CrosstabWizardLayoutPage() {
		this(false);
	}
	
	/**
	 * Create the controls to decide the color schema of the cells
	 * 
	 * @param parent
	 */
	private void createColorGroup(Composite parent){
		Group group = new Group(parent, SWT.NONE);
		group.setText(Messages.CrosstabWizardLayoutPage_cell_color_group);
		group.setLayout(new GridLayout(2,false));
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label firstLabel = new Label(group,SWT.NONE);
		firstLabel.setText(Messages.CrosstabWizardLayoutPage_color_schema_combo);
		
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
		secondLabel.setText(Messages.CrosstabWizardLayoutPage_variations_combo);
		
		variations = new Combo(group,SWT.READ_ONLY);
		variations.setItems(getVariantsName());
		variations.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		variations.select(0);
		
		whiteGrid = new Button(group, SWT.CHECK);
		whiteGrid.setText(Messages.CrosstabWizardLayoutPage_white_grid_check);
		GridData checkBoxData = new GridData(GridData.FILL_HORIZONTAL);
		checkBoxData.horizontalSpan = 2;
		whiteGrid.setLayoutData(checkBoxData);
		
		showGrid = new Button(group, SWT.CHECK);
		showGrid.setText(Messages.CrosstabWizardLayoutPage_noGrid_label);
		GridData showGridData = new GridData(GridData.FILL_HORIZONTAL);
		showGridData.horizontalSpan = 2;
		showGrid.setLayoutData(showGridData);
		showGrid.setSelection(true);
		
		variations.addModifyListener(modifyListener);
		whiteGrid.addSelectionListener(selectionListener);
		showGrid.addSelectionListener(selectionListener);
		colorScheme.addSelectionListener(selectionListener);
	}
	
	private void setColor(String colorName){
		for(int i=0; i<colorScheme.getItemCount(); i++){
			if (colorScheme.getItem(i).equals(colorName)){
				colorScheme.select(i);
				return;
			}
		}
	}
	
	private void setVariations(ColorSchemaGenerator.SCHEMAS schema){
		for(int i=0; i<variants.size(); i++){
			if (variants.get(i).getValue().equals(schema)){
				variations.select(i);
				return;
			}
		}
	}
	
	private void setData(){
		if (templateToOpen instanceof CrosstabStyle){
			CrosstabStyle cStyle = (CrosstabStyle)templateToOpen;
			String colorName = ColorSchemaGenerator.getName(cStyle.getBaseColor());
			setColor(colorName);
			setVariations(cStyle.getVariation());
			whiteGrid.setSelection(cStyle.getWhiteGrid());
			showGrid.setSelection(cStyle.isShowGrid());
			if (titleText != null) titleText.setText(cStyle.getDescription());		
		}
	}
	
	/**
	 * Create the checkbox to decide to show or not the total bands
	 * 
	 * @param parent
	 */
	private void createSectionsGroup(Composite parent){
		Group group = new Group(parent, SWT.NONE);
		group.setText(Messages.CrosstabWizardLayoutPage_visible_sections_group);
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
		
		//FIXME: This option are not taken because these properties are read and used when the dialog 
		//is created instead of when the wizard is completed.
		group.setVisible(false);
	}
	
	/**
	 * Create the control ad the left of the preview, so the cell color and visible sections
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
	 * Set a template that will be used to initialize (if possible) the 
	 * control of the wizard with the value of the template
	 * 
	 * @param template template used to initialize the value
	 */
	public void setTemplateToOpen(TemplateStyle template){
		this.templateToOpen = template;
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
		group.setText(Messages.CrosstabWizardLayoutPage_style_preview_group);
		group.setLayout(new GridLayout(1,false));
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		preview = new CrosstabStylePreview(group, SWT.NONE);
		preview.setLayoutData(new GridData(GridData.FILL_BOTH));
	}
	
	/**
	 * Called when some property change, rebuild the crosstab style with the actual state of the control
	 * and request the redraw of the preview
	 */
	private void notifyChange(){
		String colorName = colorScheme.getItem(colorScheme.getSelectionIndex());
		Color color = ColorSchemaGenerator.getColor(colorName);
		ColorSchemaGenerator.SCHEMAS variantKey = (ColorSchemaGenerator.SCHEMAS)variants.get(variations.getSelectionIndex()).getValue();
		lastGeneratedStyle = new CrosstabStyle(new RGB(color.getRed(), color.getGreen(), color.getBlue()),variantKey, whiteGrid.getSelection());
		lastGeneratedStyle.setShowGrid(showGrid.getSelection());
		if (titleText != null) lastGeneratedStyle.setDescription(titleText.getText());
		preview.setTableStyle(lastGeneratedStyle);
	}
	
	/**
	 * Return the last generated style for the crosstab, that is the effective one when 
	 * the dialog was closed. If the last generated style is null (maybe because the wizard 
	 * was finished without reach the last step), a default one is provided
	 * 
	 * @return the style to apply to the table
	 */
	public CrosstabStyle getSelectedStyle(){
		if (lastGeneratedStyle == null) {
			String firstColor = ColorSchemaGenerator.getColors().get(0);
			Color color = ColorSchemaGenerator.getColor(firstColor);
			RGB rgbColor = new RGB(color.getRed(), color.getGreen(), color.getBlue());
			lastGeneratedStyle = new CrosstabStyle(rgbColor, ColorSchemaGenerator.SCHEMAS.DEFAULT, false);
		}
		return lastGeneratedStyle;
	}
	
	private void createTitleLabel(Composite parent){
		Composite titleComposite = new Composite(parent, SWT.NONE);
		titleComposite.setLayout(new GridLayout(2,false));
		GridData titleCompositeData = new GridData();
		titleCompositeData.horizontalSpan = 2;
		titleCompositeData.grabExcessHorizontalSpace=true;
		titleCompositeData.horizontalAlignment = SWT.FILL;
		titleComposite.setLayoutData(titleCompositeData);
		Label descriptionLabel = new Label(titleComposite, SWT.NONE);
		descriptionLabel.setText("Name of the style");
		titleText = new Text(titleComposite, SWT.BORDER);
		titleText.addModifyListener(modifyListener);
		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = true;
		textData.horizontalAlignment = SWT.FILL;
		titleText.setLayoutData(textData);
	}

	public void createControl(Composite parent) {
		Composite dialog = new Composite(parent, SWT.NONE);
		GridLayout generalLayout = new GridLayout(2,false);
		dialog.setLayout(generalLayout);
		setControl(dialog);
		//Create the title
		if (createTitle) createTitleLabel(dialog);
		createLeftCol(dialog);
		createPreview(dialog);
		if (templateToOpen != null) setData();
		notifyChange();
		
	}

	/**
	 * return the ID of the contextual help to show
	 */
	
	@Override
	protected String getContextName() {
		return ContextHelpIDs.CROSSTAB_STYLES;
	}

}
