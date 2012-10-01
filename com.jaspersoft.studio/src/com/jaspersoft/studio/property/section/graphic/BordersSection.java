/*******************************************************************************
 * ---------------------------------------------------------------------
 * Copyright (C) 2005 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
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
 * ---------------------------------------------------------------------
 ******************************************************************************/
package com.jaspersoft.studio.property.section.graphic;

import net.sf.jasperreports.engine.base.JRBaseLineBox;
import net.sf.jasperreports.engine.base.JRBasePen;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.ColumnLayout;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.java2d.J2DLightweightSystem;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElementLineBox;
import com.jaspersoft.studio.model.MLineBox;
import com.jaspersoft.studio.model.MLinePen;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.descriptor.color.ColorLabelProvider;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.graphic.LineBoxDrawer.Location;
import com.jaspersoft.studio.property.section.widgets.SPLineStyleEnum;
import com.jaspersoft.studio.swt.widgets.ColorStyledText;
import com.jaspersoft.studio.utils.UIUtils;

/**
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav & Orlandin Marco
 */
public class BordersSection extends AbstractSection {

	/**
	 * Toolbutton to set all the borders
	 */
	private ToolItem allBorder;
	
	/**
	 * Toolbutton to remove all the borders
	 */
	private ToolItem noneBorder;
	
	/**
	 * Toolbutton to set the up and bottom borders
	 */
	private ToolItem upDownBorder;
	
	/**
	 * Toolbutton to set the left and right borders
	 */
	private ToolItem leftRightBorder;
	
	/**
	 * Drawer for the borders linebox
	 */
	private LineBoxDrawer bd;
	
	/**
	 * RGB value of the last color selected
	 */
	private RGB lastColorSelected;
	
	/**
	 * The filed used to set\show the lineWidth
	 */
	private Spinner lineWidth;
	
	/**
	 * The Combo popup used to set\show the linestyle
	 */
	private SPLineStyleEnum lineStyle;
	
	/**
	 * The box used to set\show the line color
	 */
	//private ToolItem lineColor;
	
	/**
	 * Checkbox to know if all the padding are at the same value
	 */
	private Button checkBoxPadding;
	
	/**
	 * Spinner for the left padding or for the general padding when 
	 * the checkbox is selected
	 */
	private Spinner paddingLeft;
	
	/**
	 * Spinner for the right padding
	 */
	private Spinner paddingRight;
	
	/**
	 * Spinner for the bottom padding
	 */
	private Spinner paddingBottom;
	
	/**
	 * Spinner for the top padding
	 */
	private Spinner paddingTop;
	
	/**
	 * Provider to convert RGB color into image
	 */
	private ColorLabelProvider colorLabelProvider = new ColorLabelProvider(null);
	
	/**
	 * Border figure rectangle
	 */
	private RectangleFigure borderPreview;
	
	/**
	 * Border figure canvas
	 */
	private Canvas square;
	
	private ColorStyledText lineColor2;

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		
		Composite mainLayout = new Composite(parent, SWT.NONE);
		mainLayout.setBackground(mainLayout.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		mainLayout.setLayout(new GridLayout(1,true));
		mainLayout.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
				
		createPaddingPanel(mainLayout);
		
		Group rightPanel = new Group(mainLayout, SWT.NONE);
		rightPanel.setText(Messages.BordersSection_border);
		rightPanel.setBackground(mainLayout.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		rightPanel.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
		rightPanel.setLayout(new GridLayout(2,false));
				
		createBorderPreview(rightPanel);
		
		createStyle(rightPanel, JRBaseLineBox.PROPERTY_PADDING);
		
		Composite toolBarLayout = new Composite(rightPanel, SWT.NONE);
		toolBarLayout.setLayout(new ColumnLayout());
		GridData toolBardGridData = new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false);
		toolBardGridData.horizontalSpan = 2;
		toolBardGridData.widthHint = 200;
		toolBardGridData.horizontalIndent = 5;
		toolBarLayout.setLayoutData(toolBardGridData);
		Label textLabel = new Label(toolBarLayout,SWT.NONE);
		textLabel.setText(Messages.BordersSection_Default_Label);
		ToolBar toolBar = new ToolBar(toolBarLayout, SWT.FLAT | SWT.WRAP);
		toolBar.setBackground(rightPanel.getBackground());
		createButtons(toolBar);

		allBorder.setSelection(false);
		noneBorder.setSelection(false);
		leftRightBorder.setSelection(false);
		upDownBorder.setSelection(false);
	}

	/**
	 * Create the canvas box where the borders will be represented and could be edited
	 * @param composite the composite where the canvas will be placed
	 */
	private void createBorderPreview(Composite composite) {
		square = new Canvas(composite, SWT.BORDER | SWT.NO_REDRAW_RESIZE | SWT.NO_BACKGROUND);
		//The mouse down may select a border and the mouse up refresh the painting area
		square.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				updateRightPanel();
			}
			
			@Override
			public void mouseDown(MouseEvent e) {}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {}
		});
		
		GridData gd = new GridData(GridData.FILL_VERTICAL | GridData.HORIZONTAL_ALIGN_CENTER
				| GridData.VERTICAL_ALIGN_CENTER);
		gd.widthHint = 120;
		gd.heightHint = 120;
		square.setLayoutData(gd);

		LightweightSystem lws = new J2DLightweightSystem();
		lws.setControl(square);
		bd = new LineBoxDrawer(jasperReportsContext, square);
		borderPreview = new LineBoxRectangle(bd,this);

		lws.setContents(borderPreview);
	}
	
	/**
	 * Enable or disable the spinner for the directional padding when the checkbox 
	 * to set the same value in every spinner is selected
	 */
	private void checkBoxValueChange(){
		if (checkBoxPadding.getSelection()){
			paddingRight.setEnabled(false);
			paddingTop.setEnabled(false);
			paddingBottom.setEnabled(false);
		} else {
			paddingRight.setEnabled(true);
			paddingTop.setEnabled(true);
			paddingBottom.setEnabled(true);						
		}
	}
	
	/**
	 * When the checkbox is selected or deselected this method perform immediately an action to set 
	 * the general padding to a value or null respectively
	 */
	private void uniformAfterCheck(){
		CompoundCommand cc = new CompoundCommand("Change padding");  //$NON-NLS-1$
		if (checkBoxPadding.getSelection()){
			//I've selected to use the same value in all the padding area
			//so i generate immediately the command
			for (APropertyNode m : getElements()) {
				Command c = null;
				MLineBox lb = (MLineBox) m.getPropertyValue(MGraphicElementLineBox.LINE_BOX);
				c = changeProperty(JRBaseLineBox.PROPERTY_PADDING, new Integer(paddingLeft.getSelection()), lb);
				if (c != null)
							cc.add(c);
			}
		} else {
			//The box was deselected so i need to set immediately the padding for all to null
			for (APropertyNode m : getElements()) {
				Command c = null;
				MLineBox lb = (MLineBox) m.getPropertyValue(MGraphicElementLineBox.LINE_BOX);
				c = changeProperty(JRBaseLineBox.PROPERTY_PADDING, null, lb);
				if (c != null)
							cc.add(c);
			}
		}
		CommandStack cs = getEditDomain().getCommandStack();
		cs.execute(cc);
	}
	
	/**
	 * Create the padding group and the control in it
	 * @param parent the composite where the group will be placed
	 */
	private void createPaddingPanel(Composite parent){
		Group composite = new Group(parent, SWT.NONE);
		composite.setText(Messages.BordersSection_Padding_Box_Title);
		
		composite.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		GridLayout layout = new GridLayout(4, false);
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
		Composite rowComp = new Composite(composite, SWT.NONE);
		RowLayout checkLayout = new RowLayout();
		checkLayout.marginLeft = 0;
		checkLayout.center = true;
		rowComp.setLayout(checkLayout);
		GridData gd = new GridData();
		gd.horizontalSpan = 4;
		rowComp.setLayoutData(gd);
		
		checkBoxPadding = new Button(rowComp, SWT.CHECK);
		checkBoxPadding.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				checkBoxValueChange();
				uniformAfterCheck();
			}
		});
		
		getWidgetFactory().createCLabel(rowComp, Messages.BordersSection_Same_Padding_Value_Check, SWT.RIGHT);
		getWidgetFactory().createCLabel(composite, Messages.BordersSection_Left_Label, SWT.RIGHT); 
		
		paddingLeft = new Spinner(composite, SWT.BORDER | SWT.FLAT);
		paddingLeft.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);
		paddingLeft.setToolTipText(Messages.BordersSection_padding_tool_tip);
		paddingLeft.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changePropertyPadding(JRBaseLineBox.PROPERTY_LEFT_PADDING, new Integer(paddingLeft.getSelection()));
			}
		});
		
		getWidgetFactory().createCLabel(composite, Messages.BordersSection_Right_Label, SWT.RIGHT); 
		
		paddingRight = new Spinner(composite, SWT.BORDER | SWT.FLAT);
		paddingRight.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);
		paddingRight.setToolTipText(Messages.BordersSection_padding_tool_tip);
		paddingRight.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changePropertyPadding(JRBaseLineBox.PROPERTY_RIGHT_PADDING, new Integer(paddingRight.getSelection()));
			}
		});
		
		getWidgetFactory().createCLabel(composite, Messages.BordersSection_Top_Label, SWT.RIGHT); 
		
		paddingTop = new Spinner(composite, SWT.BORDER | SWT.FLAT);
		paddingTop.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);
		paddingTop.setToolTipText(Messages.BordersSection_padding_tool_tip);
		paddingTop.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changePropertyPadding(JRBaseLineBox.PROPERTY_TOP_PADDING, new Integer(paddingTop.getSelection()));
			}
		});
		
		getWidgetFactory().createCLabel(composite, Messages.BordersSection_Bottom_Label, SWT.RIGHT); 
		
		paddingBottom = new Spinner(composite, SWT.BORDER | SWT.FLAT);
		paddingBottom.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);
		paddingBottom.setToolTipText(Messages.BordersSection_padding_tool_tip);
		paddingBottom.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changePropertyPadding(JRBaseLineBox.PROPERTY_BOTTOM_PADDING, new Integer(paddingBottom.getSelection()));
			}
		});
	}

	private Control createStyle(Composite parent, final String property) {
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);

		//ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.WRAP | SWT.LEFT);
		//toolBar.setBackground(composite.getBackground());

		lastColorSelected = new RGB(0, 0, 0);
		getWidgetFactory().createCLabel(composite, Messages.common_pen_color + ":", SWT.RIGHT); //$NON-NLS-1$
		lineColor2 = new ColorStyledText(composite);
		lineColor2.setColor(lastColorSelected);
		lineColor2.addListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				changeProperty(JRBasePen.PROPERTY_LINE_COLOR, lineColor2.getColor());	
			}
		});
		/*
		getWidgetFactory().createCLabel(composite, Messages.common_pen_color + ":", SWT.RIGHT); //$NON-NLS-1$
		lineColor = new ToolItem(toolBar, SWT.RADIO);
		lineColor.setImage(colorLabelProvider.getImage(lastColorSelected));
		lineColor.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ColorDialog cd = new ColorDialog(composite.getShell());
				cd.setText(Messages.common_line_color);
				cd.setRGB((RGB) getElement().getPropertyValue(JRBasePen.PROPERTY_LINE_COLOR));
				RGB newColor = cd.open();
				lineColor.setSelection(false);
				lastColorSelected = newColor;
				lineColor.setImage(colorLabelProvider.getImage(lastColorSelected));
				changeProperty(JRBasePen.PROPERTY_LINE_COLOR, newColor);
			}
		});*/

		getWidgetFactory().createCLabel(composite, Messages.common_pen_style + ":"); //$NON-NLS-1$

		createLineStyle(property, composite);

		getWidgetFactory().createCLabel(composite, Messages.common_pen_width + ":", SWT.RIGHT); //$NON-NLS-1$

		lineWidth = new Spinner(composite, SWT.BORDER | SWT.FLAT);
		lineWidth.setValues(10, 0, 5000, 1, 1, 1);
		lineWidth.setToolTipText(Messages.BordersSection_width_tool_tip);
		lineWidth.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int selection = lineWidth.getSelection();
				int digits = lineWidth.getDigits();
				Float newValue = new Float(selection / Math.pow(10, digits));
				changeProperty(JRBasePen.PROPERTY_LINE_WIDTH, newValue);
			}
		});
		

		return composite;
	}
	

	/**
	 * Create the line style combo popup
	 * @param prop properties associated to the combo popup
	 * @param composite parent where the combo will be placed
	 */
	private void createLineStyle(final String prop, final Composite composite) {
		lineStyle = new SPLineStyleEnum(composite, this, prop) {
			@Override
			public void propertyChange(AbstractSection section, String property, Integer value) {
				((BordersSection) section).changeProperty(property, value);
			}
		};
		lineStyle.setData(1);
	}

	/**
	 * Print the toolbar button and add the listener to them
	 * @param toolBar
	 */
	private void createButtons(ToolBar toolBar) {
		
		noneBorder = new ToolItem(toolBar, SWT.PUSH);
		noneBorder.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				RGB beforeSelectionColor = lastColorSelected;
				int selection = lineWidth.getSelection();
				Object beforeSelectionStyle = lineStyle.getSelectedValue();
				bd.setBorderSelected(Location.LEFT);
				bd.setBorderSelected(Location.RIGHT);
				bd.setBorderSelected(Location.TOP);
				bd.setBorderSelected(Location.BOTTOM);
				RGB color = new RGB(0,0,0);
				Float newValue = new Float(0);
				changeProperty(JRBasePen.PROPERTY_LINE_STYLE, 1);
				changeProperty(JRBasePen.PROPERTY_LINE_COLOR, color);
				changeProperty(JRBasePen.PROPERTY_LINE_WIDTH, newValue);
				bd.unselectAll();
				//The selection action change the displayed values, so i need to restore them after the unselect
				lineStyle.setData((Integer)beforeSelectionStyle);
				lineWidth.setSelection(selection);
				lineColor2.setColor(beforeSelectionColor);
			}
		});
		noneBorder.setImage(JaspersoftStudioPlugin.getImage("icons/resources/border.png")); //$NON-NLS-1$
		noneBorder.setToolTipText(Messages.BordersSection_No_Borders);
		
		allBorder = new ToolItem(toolBar, SWT.PUSH);
		allBorder.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				bd.setBorderSelected(Location.LEFT);
				bd.setBorderSelected(Location.RIGHT);
				bd.setBorderSelected(Location.TOP);
				bd.setBorderSelected(Location.BOTTOM);
				int selection = lineWidth.getSelection();
				int digits = lineWidth.getDigits();
				Float newValue = new Float(selection / Math.pow(10, digits));
				RGB color = lastColorSelected;
				Object style = lineStyle.getSelectedValue();
				changeProperty(JRBasePen.PROPERTY_LINE_STYLE, style);
				changeProperty(JRBasePen.PROPERTY_LINE_COLOR, color);
				changeProperty(JRBasePen.PROPERTY_LINE_WIDTH, newValue);
				bd.unselectAll();
			}
		});
		allBorder.setImage(JaspersoftStudioPlugin.getImage("icons/resources/border-outside.png")); //$NON-NLS-1$
		allBorder.setToolTipText(Messages.BordersSection_all_borders_tool_tip);
		
		leftRightBorder = new ToolItem(toolBar, SWT.PUSH);
		leftRightBorder.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				RGB beforeSelectionColor = lastColorSelected;
				int selection = lineWidth.getSelection();
				int digits = lineWidth.getDigits();
				Float beforeSelectionWidth = new Float(selection / Math.pow(10, digits));
				Object beforeSelectionStyle = lineStyle.getSelectedValue();
				bd.setBorderSelected(Location.LEFT,false);
				bd.setBorderSelected(Location.RIGHT,false);
				bd.setBorderSelected(Location.TOP);
				bd.setBorderSelected(Location.BOTTOM);
				RGB color = new RGB(0,0,0);
				Float newValue = new Float(0);
				changeProperty(JRBasePen.PROPERTY_LINE_STYLE, 1);
				changeProperty(JRBasePen.PROPERTY_LINE_COLOR, color);
				changeProperty(JRBasePen.PROPERTY_LINE_WIDTH, newValue);
				bd.setBorderSelected(Location.LEFT);
				bd.setBorderSelected(Location.RIGHT);
				bd.setBorderSelected(Location.TOP,false);
				bd.setBorderSelected(Location.BOTTOM,false);
				changeProperty(JRBasePen.PROPERTY_LINE_STYLE, beforeSelectionStyle);
				changeProperty(JRBasePen.PROPERTY_LINE_COLOR, beforeSelectionColor);
				changeProperty(JRBasePen.PROPERTY_LINE_WIDTH, beforeSelectionWidth);
				bd.unselectAll();
				lineColor2.setColor(beforeSelectionColor);
				lineWidth.setSelection(selection);
				lineStyle.setData((Integer)beforeSelectionStyle);
			}
		});
		leftRightBorder.setImage(JaspersoftStudioPlugin.getImage("icons/resources/border-right-left.png")); //$NON-NLS-1$
		leftRightBorder.setToolTipText(Messages.BordersSection_Left_Right_Borders);
		
		upDownBorder = new ToolItem(toolBar, SWT.PUSH);
		upDownBorder.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				RGB beforeSelectionColor = lastColorSelected;
				int selection = lineWidth.getSelection();
				int digits = lineWidth.getDigits();
				Float beforeSelectionWidth = new Float(selection / Math.pow(10, digits));
				Object beforeSelectionStyle = lineStyle.getSelectedValue();
				bd.setBorderSelected(Location.LEFT);
				bd.setBorderSelected(Location.RIGHT);
				bd.setBorderSelected(Location.TOP,false);
				bd.setBorderSelected(Location.BOTTOM,false);
				RGB color = new RGB(0,0,0);
				Float newValue = new Float(0);
				changeProperty(JRBasePen.PROPERTY_LINE_STYLE, 1);
				changeProperty(JRBasePen.PROPERTY_LINE_COLOR, color);
				changeProperty(JRBasePen.PROPERTY_LINE_WIDTH, newValue);
				bd.setBorderSelected(Location.LEFT,false);
				bd.setBorderSelected(Location.RIGHT,false);
				bd.setBorderSelected(Location.TOP);
				bd.setBorderSelected(Location.BOTTOM);;
				changeProperty(JRBasePen.PROPERTY_LINE_STYLE, beforeSelectionStyle);
				changeProperty(JRBasePen.PROPERTY_LINE_COLOR, beforeSelectionColor);
				changeProperty(JRBasePen.PROPERTY_LINE_WIDTH, beforeSelectionWidth);
				bd.unselectAll();
				lineColor2.setColor(beforeSelectionColor);
				lineWidth.setSelection(selection);
				lineStyle.setData((Integer)beforeSelectionStyle);
			}
		});
		upDownBorder.setImage(JaspersoftStudioPlugin.getImage("icons/resources/border-top-bottom.png")); //$NON-NLS-1$
		upDownBorder.setToolTipText(Messages.BordersSection_Top_Bottom_Borders);
	}
	
	/**
	 * Change the padding property of a linebox
	 * @param property the property name
	 * @param newValue the new value
	 */
	public void changePropertyPadding(String property, Object newValue){
		if (!isRefreshing) {
			CompoundCommand cc = new CompoundCommand("Change padding"); //$NON-NLS-1$
			for (APropertyNode m : getElements()) {
				Command c = null;
				MLineBox lb = (MLineBox) m.getPropertyValue(MGraphicElementLineBox.LINE_BOX);
				//If the checkbox is set i need to set the general padding, otherwise it must be null
				if (checkBoxPadding.getSelection()){
					c = changeProperty(JRBaseLineBox.PROPERTY_PADDING, newValue, lb);
				} else {
					c = changeProperty(JRBaseLineBox.PROPERTY_PADDING, null, lb);
					if (c != null)
						cc.add(c);
					c = changeProperty(property, newValue, lb);
				}
				if (c != null)
					cc.add(c);
			}
			CommandStack cs = getEditDomain().getCommandStack();
			cs.execute(cc);
		}
	}

	
	/**
	 * Change the property of a linepen
	 * @param property the property name
	 * @param newValue the new value
	 */
	public void changeProperty(String property, Object newValue) {
		if (!isRefreshing) {
			CompoundCommand cc = new CompoundCommand("Change border"); //$NON-NLS-1$
			for (APropertyNode m : getElements()) {
				Command c = null;
				MLineBox lb = (MLineBox) m.getPropertyValue(MGraphicElementLineBox.LINE_BOX);
				//it's a change of a border attribute
				if (bd.isBottomSelected()){
					MLinePen lp = (MLinePen) lb.getPropertyValue(MLineBox.LINE_PEN_BOTTOM);
					c = changeProperty(property, newValue, lp);
					if (c != null)
						cc.add(c);
				}
				if(bd.isTopSelected()){
					MLinePen lp = (MLinePen) lb.getPropertyValue(MLineBox.LINE_PEN_TOP);
					c = changeProperty(property, newValue, lp);
					if (c != null)
						cc.add(c);
				}
				if(bd.isLeftSelected()){
					MLinePen lp = (MLinePen) lb.getPropertyValue(MLineBox.LINE_PEN_LEFT);
					c = changeProperty(property, newValue, lp);
					if (c != null)
						cc.add(c);
				}
				if(bd.isRightSelected()){
					MLinePen lp = (MLinePen) lb.getPropertyValue(MLineBox.LINE_PEN_RIGHT);
					c = changeProperty(property, newValue, lp);
					if (c != null)
						cc.add(c);
				}
			}
			CommandStack cs = getEditDomain().getCommandStack();
			cs.execute(cc);
			bd.refresh();
		}
	}
	
	
	/**
	 * Convert a border location to the corresponding line pen location
	 * @param loc location of a border
	 * @return a line pen location
	 */
	private String locationToLine(Location loc){
		if (loc == Location.TOP) return MLineBox.LINE_PEN_TOP;
		else if (loc == Location.BOTTOM) return MLineBox.LINE_PEN_BOTTOM;
		else if (loc == Location.RIGHT) return MLineBox.LINE_PEN_RIGHT;
		else return MLineBox.LINE_PEN_LEFT;
	}
	
	/**
	 * Update the value on the right panel with the selected line data
	 */
	private void updateRightPanel(){
		APropertyNode m = getElement();
		if (m != null) {
			MLineBox lb = (MLineBox) m.getPropertyActualValue(MGraphicElementLineBox.LINE_BOX);
			if (bd.getLastSelected() != null && bd.getLastSelected().getSelected()) {
				refreshLinePen(lb, locationToLine(bd.getLastSelected().getLocation()));
			}
			else if (bd.isTopSelected()) {
				refreshLinePen(lb, MLineBox.LINE_PEN_TOP);
			}
			else if (bd.isBottomSelected()) {
				refreshLinePen(lb, MLineBox.LINE_PEN_BOTTOM);
			}
			else if (bd.isLeftSelected()) {
				refreshLinePen(lb, MLineBox.LINE_PEN_LEFT);
			}
			else if (bd.isRightSelected()) {
				refreshLinePen(lb, MLineBox.LINE_PEN_RIGHT);
			}
			else {
				//No border is selected, set the control to the default value
				lastColorSelected = new RGB(0, 0, 0);
				lineColor2.setColor(lastColorSelected);
				lineWidth.setValues(10, 0, 5000, 1, 1, 1);
				lineStyle.setData(1);
			}
		}
	}
	
	/**
	 * Refresh the right panel with the values of the selected border. If more than one border is selected 
	 * will be shown the values of one of them, choose as follow:
	 * -The last border selected
	 * -If the last border selected was deselected the first from the other SELECTED borders will be choose. The border
	 * examination order is TOP,BOTTOM,LEFT,RIGHT
	 * -If no border is selected will default values will be used
	 */
	public void refresh() {
		isRefreshing = true;
		APropertyNode m = getElement();
		if (m != null) {
			MLineBox lb = (MLineBox) m.getPropertyActualValue(MGraphicElementLineBox.LINE_BOX);
			refreshPadding(lb);
		}
		if (square != null)
			square.redraw();
		isRefreshing = false;
	}

	/**
	 * Return the padding value
	 * @param padding and object representing the padding value, could be null
	 * @return and integer version  of the padding value if it isn't null, otherwise false
	 */
	private Integer getPaddingValue(Object padding){
		return (padding != null ? (Integer)padding : 0);
	}
	
	/**
	 * Refresh the padding information, actually it's not used
	 */
	public void refreshPadding(MLineBox lb) {
		if (lb != null) {
			Object propertyPadding = lb.getPropertyValue(JRBaseLineBox.PROPERTY_PADDING);
			if (propertyPadding != null){
				checkBoxPadding.setSelection(true);
				Integer value = (Integer)propertyPadding;
				paddingTop.setSelection(value);
				paddingBottom.setSelection(value);
				paddingLeft.setSelection(value);
				paddingRight.setSelection(value);
			} else {
				checkBoxPadding.setSelection(false);
				paddingTop.setSelection(getPaddingValue(lb.getPropertyValue(JRBaseLineBox.PROPERTY_TOP_PADDING)));
				paddingBottom.setSelection(getPaddingValue(lb.getPropertyValue(JRBaseLineBox.PROPERTY_BOTTOM_PADDING)));
				paddingLeft.setSelection(getPaddingValue(lb.getPropertyValue(JRBaseLineBox.PROPERTY_LEFT_PADDING)));
				paddingRight.setSelection(getPaddingValue(lb.getPropertyValue(JRBaseLineBox.PROPERTY_RIGHT_PADDING)));
			}
			checkBoxValueChange();
		}
	}


	/**
	 * Update the right panel with the value of a linepen, but only if it's visible
	 * @param lb
	 * @param property
	 */
	public void refreshLinePen(MLineBox lb, String property) {
		if (lb != null) {
			MLinePen lp = (MLinePen) lb.getPropertyActualValue(property);
			Float propertyValue = (Float) lp.getPropertyActualValue(JRBasePen.PROPERTY_LINE_WIDTH);
			if (propertyValue>0){
				//Set the border data only if it is visible
				if (lineWidth != null && !lineWidth.isDisposed()) {
					UIUtils.setSpinnerSelection(lineWidth, null, (int) ((propertyValue == null) ? 0 : propertyValue.doubleValue()
							* Math.pow(10, 1)));
				}
	
				if (lineStyle != null && !isDisposed()) {
					int ls = ((Integer) lp.getPropertyActualValue(JRBasePen.PROPERTY_LINE_STYLE)).intValue();
					lineStyle.setData(ls);
				}
	
				RGB backcolor = (RGB) lp.getPropertyActualValue(JRBasePen.PROPERTY_LINE_COLOR);
				if (lineColor2 != null){
					lastColorSelected = backcolor;
					lineColor2.setColor(backcolor);
				}
			}
		}
	}

}
