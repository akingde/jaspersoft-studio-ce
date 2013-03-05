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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.dialog.TableStyle;
import com.jaspersoft.studio.components.table.model.dialog.TableStyle.BorderStyleEnum;
import com.jaspersoft.studio.components.table.model.dialog.TableStylePreview;
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
	
	protected TableWizardLayoutPage() {
		super("tablepage"); 
		setTitle(Messages.TableWizardLayoutPage_layout);
		setDescription(Messages.TableWizardLayoutPage_description);
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
		
		final ToolItem buttonFull = new ToolItem (toolBar, SWT.RADIO);
		buttonFull.setImage (Activator.getDefault().getImage("icons/full_borders.png")); //$NON-NLS-1$
		
		final ToolItem buttonHorizontal1 = new ToolItem (toolBar, SWT.RADIO);
		buttonHorizontal1.setImage (Activator.getDefault().getImage("icons/horizontal_borders.png")); //$NON-NLS-1$
		
		final ToolItem buttonHorizontal2 = new ToolItem (toolBar, SWT.RADIO);
		buttonHorizontal2.setImage (Activator.getDefault().getImage("icons/horizontal_borders2.png")); //$NON-NLS-1$
		
		toolBar.pack ();
		buttonFull.setSelection(true);
		borderStyle = BorderStyleEnum.FULL;
		
		borderColor.addListener(modifyListener);
		
		buttonFull.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
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
	 * Create the bottom area of the dialog, where there are the option on the section 
	 * that will be created
	 * 
	 * @param parent parent of the bottom area
	 */
	private void createBottom(Composite parent){
		Composite composite = new Composite(parent, SWT.NONE);
		GridData bottomData = new GridData(GridData.FILL_HORIZONTAL);
		bottomData.horizontalSpan = 2;
		composite.setLayoutData(bottomData);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		composite.setLayout(layout);
		

		final Button addTHeader = new Button(composite, SWT.CHECK);
		addTHeader.setText(Messages.TableWizardLayoutPage_add_table_header);
		addTHeader.setSelection(isTableHeader);
		addTHeader.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				isTableHeader = addTHeader.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		final Button addCHeader = new Button(composite, SWT.CHECK);
		addCHeader.setText(Messages.TableWizardLayoutPage_add_column_header);
		addCHeader.setSelection(isColumnHeader);
		addCHeader.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				isColumnHeader = addCHeader.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		final Button addGHeader = new Button(composite, SWT.CHECK);
		addGHeader.setText(Messages.TableWizardLayoutPage_add_group_header);
		addGHeader.setSelection(isGroupHeader);
		addGHeader.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				isGroupHeader = addGHeader.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		final Button addGFooter = new Button(composite, SWT.CHECK);
		addGFooter.setText(Messages.TableWizardLayoutPage_add_group_footer);
		addGFooter.setSelection(isGroupFooter);
		addGFooter.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				isGroupFooter = addGFooter.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		final Button addCFooter = new Button(composite, SWT.CHECK);
		addCFooter.setText(Messages.TableWizardLayoutPage_add_column_footer);
		addCFooter.setSelection(isColumnFooter);
		addCFooter.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				isColumnFooter = addCFooter.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		final Button addTFooter = new Button(composite, SWT.CHECK);
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
	
	/**
	 * Return the last generated style for the table, that is the effective one when 
	 * the dialog was closed. If the last generated style is null (maybe because the wizard 
	 * was finished without reach the last step), the default one is provided
	 * 
	 * @return the style to apply to the table
	 */
	public TableStyle getSelectedStyle(){
		if (lastGeneratedStyle == null) {
			String firstColor = ColorSchemaGenerator.getColors().get(0);
			Color color = ColorSchemaGenerator.getColor(firstColor);
			RGB rgbColor = new RGB(color.getRed(), color.getGreen(), color.getBlue());
			lastGeneratedStyle = new TableStyle(rgbColor, ColorSchemaGenerator.SCHEMAS.DEFAULT, BorderStyleEnum.FULL, ColorConstants.black.getRGB(), false);
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

	/**
	 * Create all the controls of the dialog
	 */
	public void createControl(Composite parent) {
		
		Composite dialog = new Composite(parent, SWT.NONE);
		GridLayout generalLayout = new GridLayout(2,false);
		dialog.setLayout(generalLayout);
		setControl(dialog);
		
		//Creating the left col
		createLeftCol(dialog);
		
		//Creating the right preview col
		createPreview(dialog);
		
		//Create the bottom band
		createBottom(dialog);
		
		notifyChange();
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
