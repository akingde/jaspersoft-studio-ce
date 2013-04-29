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
package com.jaspersoft.studio.components.table.model.table.command.wizard;



import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
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
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.dialog.TableStyle;
import com.jaspersoft.studio.components.table.model.dialog.TableStyle.BorderStyleEnum;
import com.jaspersoft.studio.components.table.model.dialog.TableStylePreview;
import com.jaspersoft.studio.editor.style.TemplateStyle;
import com.jaspersoft.studio.property.color.ColorSchemaGenerator;
import com.jaspersoft.studio.property.color.Tag;
import com.jaspersoft.studio.swt.widgets.ColorStyledText;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

/**
 * Step of the wizard where you can define style of the table
 * 
 * 
 * @author Orlandin Marco
 *
 */
public class TableWizardLayoutPage extends JSSHelpWizardPage {
	
	/**
	 * Table section option
	 */
	private boolean isTableHeader = true;
	private boolean isTableFooter = true;
	private boolean isColumnHeader = true;
	private boolean isColumnFooter = true;
	private boolean isGroupHeader = true;
	private boolean isGroupFooter = true;
	
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
	private Button alternateColor;
	
	/**
	 * the textfield of the title
	 */
	private Text titleText = null;

	/**
	 * Widget for the border color
	 */
	private ColorStyledText borderColor;
	
	/**
	 * List of the available schemes for the variations
	 */
	private List<Tag> variants;

	/**
	 * Style of the border of the table
	 */
	private BorderStyleEnum borderStyle;
	
	/**
	 * Table preview widget
	 */
	private TableStylePreview preview;
	
	/**
	 * Last style generated
	 */
	private TableStyle lastGeneratedStyle = null;
	
	/**
	 * Composite of the bottom area (the table section)
	 */
	private Composite bottomComposite;
	
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
	 * List of the buttons that represent the border style
	 */
	private List<ToolItem> borderStyleButtons = new ArrayList<ToolItem>();
	
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
	
	protected TableWizardLayoutPage(boolean createTitle) {
		super("tablepage"); 
		setTitle(Messages.TableWizardLayoutPage_layout);
		setDescription(Messages.TableWizardLayoutPage_description);
		this.createTitle = createTitle;
	}
	
	protected TableWizardLayoutPage(){
		this(false);
	}
	
	
	/**
	 * Return the context name for the help of this page
	 */
	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_LAYOUT_PAGE;
	}

	@Override
	public void dispose() {
		super.dispose();
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
	 * Create the group with the controls for the cell colors
	 * 
	 * @param parent parent composite of the group 
	 */
	private void createCellColors(Composite parent){
		Group group = new Group(parent, SWT.NONE);
		group.setText(Messages.TableWizardLayoutPage_cell_colors_group);
		group.setLayout(new GridLayout(2,false));
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label firstLabel = new Label(group,SWT.NONE);
		firstLabel.setText(Messages.TableWizardLayoutPage_color_schema_label);
		
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
		secondLabel.setText(Messages.TableWizardLayoutPage_variations_label);
		
		variations = new Combo(group,SWT.READ_ONLY);
		variations.setItems(getVariantsName());
		variations.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		variations.select(0);
		
		alternateColor = new Button(group, SWT.CHECK);
		alternateColor.setText(Messages.TableWizardLayoutPage_alternated_rows_label);
		GridData checkBoxData = new GridData(GridData.FILL_HORIZONTAL);
		checkBoxData.horizontalSpan = 2;
		alternateColor.setLayoutData(checkBoxData);
		
		variations.addModifyListener(modifyListener);
		alternateColor.addSelectionListener(selectionListener);
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
	
	private void setBorderButtons(BorderStyleEnum loadedStyle){
		borderStyle = loadedStyle;
		ToolItem buttonFull = borderStyleButtons.get(0);
		ToolItem buttonHorizontal1 = borderStyleButtons.get(1);
		ToolItem buttonHorizontal2 = borderStyleButtons.get(2);
		buttonFull.setSelection(false);
		buttonHorizontal1.setSelection(false);
		buttonHorizontal2.setSelection(false);
		if (borderStyle.equals(BorderStyleEnum.FULL)) buttonFull.setSelection(true);
		else if (borderStyle.equals(BorderStyleEnum.PARTIAL_VERTICAL)) buttonHorizontal1.setSelection(true);
		else buttonHorizontal2.setSelection(true);
	}
	
	private void setData(){
		if (templateToOpen instanceof TableStyle){
			TableStyle cStyle = (TableStyle)templateToOpen;
			String colorName = ColorSchemaGenerator.getName(cStyle.getBaseColor());
			setColor(colorName);
			setVariations(cStyle.getVariation());
			alternateColor.setSelection(cStyle.hasAlternateColor());
			borderColor.setColor(cStyle.getRGBBorderColor());
			setBorderButtons(cStyle.getBorderStyle());
			if (titleText != null) titleText.setText(cStyle.getDescription());		
		}
	}
	
	/**
	 * Return a class that identify which are the visible section of the table
	 * 
	 * @return a TemplateSections class, that contains a series of boolean flags used to know 
	 * which sections of the table are visible.
	 */
	public TableSections getVisibileSections(){
		return new TableSections(isTableHeader, isTableFooter, isColumnHeader, isColumnFooter, isGroupHeader, isGroupFooter);
	}
	
	/**
	 * Create the group with the controls for the cell borders
	 * 
	 * @param parent parent composite of the group 
	 */
	private void createCellBorders(Composite parent){
		Group group = new Group(parent, SWT.NONE);
		group.setText(Messages.TableWizardLayoutPage_cell_border_group);
		group.setLayout(new GridLayout(2,false));
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label firstLabel = new Label(group,SWT.NONE);
		firstLabel.setText(Messages.TableWizardLayoutPage_borders_color_label);
		Label secondLabel = new Label(group,SWT.NONE);
		secondLabel.setText(Messages.TableWizardLayoutPage_borders_style_label);
		
		borderColor = new ColorStyledText(group);
		borderColor.setColor(ColorConstants.black);
		GridData borderColorData = new GridData();
		borderColorData.minimumWidth = 50;
		borderColor.setLayoutData(borderColorData);
		borderColor.setBackground(ColorConstants.white);
		
		ToolBar toolBar = new ToolBar (group, SWT.FLAT);
		
		ToolItem buttonFull = new ToolItem (toolBar, SWT.RADIO);
		buttonFull.setImage (Activator.getDefault().getImage("icons/full_borders.png")); 
		borderStyleButtons.add(buttonFull);
		
		ToolItem buttonHorizontal1 = new ToolItem (toolBar, SWT.RADIO);
		buttonHorizontal1.setImage (Activator.getDefault().getImage("icons/horizontal_borders.png")); 
		borderStyleButtons.add(buttonHorizontal1);
		
		ToolItem buttonHorizontal2 = new ToolItem (toolBar, SWT.RADIO);
		buttonHorizontal2.setImage (Activator.getDefault().getImage("icons/horizontal_borders2.png"));
		borderStyleButtons.add(buttonHorizontal2);
		
		toolBar.pack ();
		buttonFull.setSelection(true);
		borderStyle = BorderStyleEnum.FULL;
		
		borderColor.addListener(modifyListener);
		
		buttonFull.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ToolItem buttonFull = borderStyleButtons.get(0);
				ToolItem buttonHorizontal1 = borderStyleButtons.get(1);
				ToolItem buttonHorizontal2 = borderStyleButtons.get(2);
				if (!buttonFull.getSelection()){ 
					borderStyle = BorderStyleEnum.FULL;
					buttonFull.setSelection(true);
					buttonHorizontal1.setSelection(false);
					buttonHorizontal2.setSelection(false);
					notifyChange();
				}
			}
		});
		
		buttonHorizontal1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ToolItem buttonFull = borderStyleButtons.get(0);
				ToolItem buttonHorizontal1 = borderStyleButtons.get(1);
				ToolItem buttonHorizontal2 = borderStyleButtons.get(2);
				if (!buttonHorizontal1.getSelection()){ 
					borderStyle = BorderStyleEnum.PARTIAL_VERTICAL;
					buttonHorizontal1.setSelection(true);
					buttonFull.setSelection(false);
					buttonHorizontal2.setSelection(false);
					notifyChange();
				}
			}
		});
		
		buttonHorizontal2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ToolItem buttonFull = borderStyleButtons.get(0);
				ToolItem buttonHorizontal1 = borderStyleButtons.get(1);
				ToolItem buttonHorizontal2 = borderStyleButtons.get(2);
				if (!buttonHorizontal2.getSelection()){ 
					borderStyle = BorderStyleEnum.ONLY_HORIZONTAL;
					buttonHorizontal2.setSelection(true);
					buttonFull.setSelection(false);
					buttonHorizontal1.setSelection(false);
					notifyChange();
				}
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
		createCellColors(leftCol);
		createCellBorders(leftCol);
		GridData leftPanelData = new GridData(GridData.FILL_VERTICAL);
		leftPanelData.widthHint = 300;
		leftPanelData.minimumHeight= 200;
		leftCol.setLayoutData(leftPanelData);
	}
	
	/**
	 * Recursive method to change the enable state of a control, if the control
	 * is a composite it will drill down to disable its children
	 * 
	 * @param ctrl the actual control
	 * @param enabled true if the control should be enabled, false otherwise
	 */
	public void recursiveSetEnabled(Control ctrl, boolean enabled) {
		   if (ctrl instanceof Composite) {
		      Composite comp = (Composite) ctrl;
		      for (Control c : comp.getChildren())
		         recursiveSetEnabled(c, enabled);
		   } else {
		      ctrl.setEnabled(enabled);
		   }
		}
	
	/**
	 * Enable or disable the bottom composite where are the table sections
	 * 
	 * @param enabled true if it is enabled, false otherwise
	 */
	public void setEnabledBottomPanel(boolean enabled){
		for (Control c : bottomComposite.getChildren())
	         recursiveSetEnabled(c, enabled);
	}
	
	/**
	 * Create the bottom area of the dialog, where there are the option on the section 
	 * that will be created
	 * 
	 * @param parent parent of the bottom area
	 */
	private void createBottom(Composite parent){
		bottomComposite = new Composite(parent, SWT.NONE);
		GridData bottomData = new GridData(GridData.FILL_HORIZONTAL);
		bottomData.horizontalSpan = 2;
		bottomComposite.setLayoutData(bottomData);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		bottomComposite.setLayout(layout);
		

		final Button addTHeader = new Button(bottomComposite, SWT.CHECK);
		addTHeader.setText(Messages.TableWizardLayoutPage_add_table_header);
		addTHeader.setSelection(isTableHeader);
		addTHeader.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				isTableHeader = addTHeader.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		final Button addCHeader = new Button(bottomComposite, SWT.CHECK);
		addCHeader.setText(Messages.TableWizardLayoutPage_add_column_header);
		addCHeader.setSelection(isColumnHeader);
		addCHeader.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				isColumnHeader = addCHeader.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		final Button addGHeader = new Button(bottomComposite, SWT.CHECK);
		addGHeader.setText(Messages.TableWizardLayoutPage_add_group_header);
		addGHeader.setSelection(isGroupHeader);
		addGHeader.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				isGroupHeader = addGHeader.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		final Button addGFooter = new Button(bottomComposite, SWT.CHECK);
		addGFooter.setText(Messages.TableWizardLayoutPage_add_group_footer);
		addGFooter.setSelection(isGroupFooter);
		addGFooter.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				isGroupFooter = addGFooter.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		final Button addCFooter = new Button(bottomComposite, SWT.CHECK);
		addCFooter.setText(Messages.TableWizardLayoutPage_add_column_footer);
		addCFooter.setSelection(isColumnFooter);
		addCFooter.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				isColumnFooter = addCFooter.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		final Button addTFooter = new Button(bottomComposite, SWT.CHECK);
		addTFooter.setText(Messages.TableWizardLayoutPage_add_table_footer);
		addTFooter.setSelection(isTableFooter);
		addTFooter.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				isTableFooter = addTFooter.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
	}
	
	
	public static TableStyle getDefaultStyle(){
		String firstColor = ColorSchemaGenerator.getColors().get(0);
		Color color = ColorSchemaGenerator.getColor(firstColor);
		RGB rgbColor = new RGB(color.getRed(), color.getGreen(), color.getBlue());
		return new TableStyle(rgbColor, ColorSchemaGenerator.SCHEMAS.DEFAULT, BorderStyleEnum.FULL, ColorConstants.black.getRGB(), false);
	}
	
	public static TableSections getDefaultSection(){
		return new TableSections(true, true, true, true, true, true); 
	}
	
	/**
	 * Return the last generated style for the table, that is the effective one when 
	 * the dialog was closed. If the last generated style is null (maybe because the wizard 
	 * was finished without reach the last step), the default one is provided
	 * 
	 * @return the style to apply to the table
	 */
	public TableStyle getSelectedStyle(){
		if (lastGeneratedStyle == null) {
			
			lastGeneratedStyle = getDefaultStyle();
		}
		return lastGeneratedStyle;
	}
	
	/**
	 * Called when some property change, rebuild the table style with the actual state of the control
	 * and request the redraw of the preview
	 */
	private void notifyChange(){
		String colorName =colorScheme.getItem(colorScheme.getSelectionIndex());
		Color color = ColorSchemaGenerator.getColor(colorName);
		ColorSchemaGenerator.SCHEMAS variantKey = (ColorSchemaGenerator.SCHEMAS)variants.get(variations.getSelectionIndex()).getValue();
		lastGeneratedStyle = new TableStyle(new RGB(color.getRed(), color.getGreen(), color.getBlue()),variantKey, borderStyle, borderColor.getColor(), alternateColor.getSelection());
		if (titleText != null) lastGeneratedStyle.setDescription(titleText.getText());
		preview.setTableStyle(lastGeneratedStyle);

	}
	
	/**
	 * Generate the preview area
	 * 
	 * @param parent
	 */
	private void createPreview(Composite parent){
		Group group = new Group(parent, SWT.NONE);
		group.setText(Messages.TableWizardLayoutPage_style_preview_group);
		group.setLayout(new GridLayout(1,false));
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		preview = new TableStylePreview(group, SWT.NONE);
		preview.setLayoutData(new GridData(GridData.FILL_BOTH));
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

	/**
	 * Create all the controls of the dialog
	 */
	public void createControl(Composite parent) {
		
		Composite dialog = new Composite(parent, SWT.NONE);
		GridLayout generalLayout = new GridLayout(2,false);
		dialog.setLayout(generalLayout);
		setControl(dialog);
		
		//Create the title
		if (createTitle) createTitleLabel(dialog);
		
		//Creating the left col
		createLeftCol(dialog);
		
		//Creating the right preview col
		createPreview(dialog);
		
		//Create the bottom band
		createBottom(dialog);
		if (templateToOpen != null) setData();
		notifyChange();
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
	 * Return if the table has a table header
	 * 
	 * @return true if the table has a table header, false otherwise
	 */
	public boolean isTableHeader() {
		return isTableHeader;
	}

	/**
	 * Return if the table has a table footer
	 * 
	 * @return true if the table has a table footer, false otherwise
	 */
	public boolean isTableFooter() {
		return isTableFooter;
	}

	/**
	 * Return if the table has a column header
	 * 
	 * @return true if the table has a column header, false otherwise
	 */
	public boolean isColumnHeader() {
		return isColumnHeader;
	}

	/**
	 * Return if the table has a column footer
	 * 
	 * @return true if the table has a column footer, false otherwise
	 */
	public boolean isColumnFooter() {
		return isColumnFooter;
	}

	/**
	 * Return if the table has a group header
	 * 
	 * @return true if the table has a group header, false otherwise
	 */
	public boolean isGroupHeader() {
		return isGroupHeader;
	}

	/**
	 * Return if the table has a group footer
	 * 
	 * @return true if the table has a group footer, false otherwise
	 */
	public boolean isGroupFooter() {
		return isGroupFooter;
	}
}
