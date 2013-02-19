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
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.dialog.TableStyle;
import com.jaspersoft.studio.components.table.model.dialog.TableStylePreview;
import com.jaspersoft.studio.property.color.ColorSchemaGenerator;
import com.jaspersoft.studio.property.color.Tag;
import com.jaspersoft.studio.swt.widgets.ColorStyledText;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

public class TableWizardLayoutPage extends JSSHelpWizardPage {
	private MTable table = new MTable();

	private boolean isTableHeader = true;
	private boolean isTableFooter = true;
	private boolean isColumnHeader = true;
	private boolean isColumnFooter = true;
	private boolean isGroupHeader = true;
	private boolean isGroupFooter = true;
	
	private TableCombo colorScheme;
	
	private Combo variations;
	
	private Button alternateColor;

	private ColorStyledText borderColor;
	
	private List<Tag> variants;

	private int borderStyle;
	
	private TableStylePreview preview;
	
	private ModifyListener modifyListener = new ModifyListener() {
		
		@Override
		public void modifyText(ModifyEvent e) {
			notifyChange();	
		}
	};
	
	private SelectionAdapter selectionListener = new SelectionAdapter() {
		
		@Override
		public void widgetSelected(SelectionEvent e) {
			notifyChange();
		}
	};
	
	public MTable getTable() {
		return table;
	}

	protected TableWizardLayoutPage() {
		super("tablepage"); //$NON-NLS-1$
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
	
	private String[] getVariantsName(){
		variants = ColorSchemaGenerator.getVariants();
		String[] variantsName = new String[variants.size()];
		for(int i=0; i<variants.size(); i++)
			variantsName[i] = variants.get(i).getName();
		return variantsName;
	}
	
	
	private void createCellColors(Composite parent){
		Group group = new Group(parent, SWT.NONE);
		group.setText("Cell colors");
		group.setLayout(new GridLayout(2,false));
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label firstLabel = new Label(group,SWT.NONE);
		firstLabel.setText("Color scheme");
		
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
		
		alternateColor = new Button(group, SWT.CHECK);
		alternateColor.setText("Use alternated detail rows background");
		GridData checkBoxData = new GridData(GridData.FILL_HORIZONTAL);
		checkBoxData.horizontalSpan = 2;
		alternateColor.setLayoutData(checkBoxData);
		
		variations.addModifyListener(modifyListener);
		alternateColor.addSelectionListener(selectionListener);
		//colorScheme.addModifyListener(modifyListener);
		colorScheme.addSelectionListener(selectionListener);
	}
	
	private void createCellBorders(Composite parent){
		Group group = new Group(parent, SWT.NONE);
		group.setText("Cell borders");
		group.setLayout(new GridLayout(2,false));
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label firstLabel = new Label(group,SWT.NONE);
		firstLabel.setText("Borders color");
		Label secondLabel = new Label(group,SWT.NONE);
		secondLabel.setText("Borders style");
		
		borderColor = new ColorStyledText(group);
		borderColor.setColor(ColorConstants.black);
		GridData borderColorData = new GridData();
		borderColorData.minimumWidth = 50;
		borderColor.setLayoutData(borderColorData);
		borderColor.setBackground(ColorConstants.white);
		
		ToolBar toolBar = new ToolBar (group, SWT.FLAT);
		
		final ToolItem buttonFull = new ToolItem (toolBar, SWT.RADIO);
		buttonFull.setImage (Activator.getDefault().getImage("icons/full_borders.png"));
		
		final ToolItem buttonHorizontal1 = new ToolItem (toolBar, SWT.RADIO);
		buttonHorizontal1.setImage (Activator.getDefault().getImage("icons/horizontal_borders.png"));
		
		final ToolItem buttonHorizontal2 = new ToolItem (toolBar, SWT.RADIO);
		buttonHorizontal2.setImage (Activator.getDefault().getImage("icons/horizontal_borders2.png"));
		
		toolBar.pack ();
		buttonFull.setSelection(true);
		borderStyle = 0;
		
		borderColor.addListener(modifyListener);
		
		buttonFull.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!buttonFull.getSelection()){ 
					borderStyle = 0;
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
					borderStyle = 1;
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
					borderStyle = 2;
					buttonHorizontal2.setSelection(true);
					buttonFull.setSelection(false);
					buttonHorizontal1.setSelection(false);
					notifyChange();
				}
			}
		});
	}
	
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
	
	private void notifyChange(){
		colorScheme.getItem(5);
		String colorName =colorScheme.getItem(colorScheme.getSelectionIndex());
		Color color = ColorSchemaGenerator.getColor(colorName);
		String variantKey = variants.get(variations.getSelectionIndex()).getValue().toString();
		TableStyle tableStyle = new TableStyle(new RGB(color.getRed(), color.getGreen(), color.getBlue()),variantKey, borderStyle, borderColor.getColor(), alternateColor.getSelection());
		preview.setTableStyle(tableStyle);
	}
	
	private void createPreview(Composite parent){
		Group group = new Group(parent, SWT.NONE);
		group.setText("Style preview");
		group.setLayout(new GridLayout(1,false));
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		preview = new TableStylePreview(group, SWT.NONE);
		preview.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

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

	public boolean isTableHeader() {
		return isTableHeader;
	}

	public boolean isTableFooter() {
		return isTableFooter;
	}

	public boolean isColumnHeader() {
		return isColumnHeader;
	}

	public boolean isColumnFooter() {
		return isColumnFooter;
	}

	public boolean isGroupHeader() {
		return isGroupHeader;
	}

	public boolean isGroupFooter() {
		return isGroupFooter;
	}
}
