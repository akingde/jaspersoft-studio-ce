/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.layout.grid;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.sf.jasperreports.engine.JRCommonElement;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.layout.FreeLayout;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.ILayoutUIProvider;
import com.jaspersoft.studio.editor.layout.LayoutCommand;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.editor.layout.SetLayoutProperty;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.IGraphicElementContainer;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.property.section.AbstractSection;

/**
 * Build and handle the UI to configure the properties of the children of a JSSGridBagLayout
 * 
 * @author Orlandin Marco
 */
public class JSSGridBagUIProvider implements ILayoutUIProvider{

	/**
	 * The maximum number of row and the maximum row span value
	 */
	protected static final int MAX_ROW_NUMBER = 1000;

	/**
	 * The maximum number of column and the maximum column span value
	 */
	protected static final int MAX_COL_NUMBER = 250;
	
	/**
	 * The listener used to store the changes and update the control status
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class ValidationModifyListener extends KeyAdapter implements FocusListener{
		
		@Override
		public void keyPressed(KeyEvent e) {
			//when enter is pressed validate the control an save the changes
			if (e.keyCode == SWT.CR){
				//If it is a new value in the combo update the hints
				if (e.widget == columnPosition || e.widget == rowPosition){
					updateComboHints((Combo)e.widget);
				}
				updateElement();
			}
		}

		@Override
		public void focusGained(FocusEvent e) {
		}

		@Override
		public void focusLost(FocusEvent e) {
			//If it is a new value in the combo update the hints
			if (e.widget == columnPosition || e.widget == rowPosition){
				updateComboHints((Combo)e.widget);
			}
			updateElement();
		}
	}
	
	/**
	 * Selection listener used when a position from the combo is selected. It doesn't update
	 * the hints list since the element was already in the combo items
	 */
	private SelectionListener comboSelectionListener = new SelectionAdapter() {
  	
  	@Override
  	public void widgetSelected(SelectionEvent e) {
  		updateElement();
  	}
	};
	
	/**
	 * Verify listener that doesn't allow to insert an invalid weight.
	 * It allow only digits and one single .
	 */
	private VerifyListener weightVerify = new VerifyListener() {
		
		@Override
		public void verifyText(VerifyEvent e) {
			if (e.text.isEmpty()) return;
			if (!e.text.matches("[0-9\\.]+")){
				e.doit = false;
				return;
			}
			String currentText = ((Text)e.widget).getText();
			if (e.text.contains(".")){
				if (currentText.contains(".")) {
					e.doit = false;
					return;
				}
			}
			try{
				String firstPart = currentText.substring(0, e.start);
				String secondPart = currentText.substring(e.end, currentText.length());
				double value = Double.parseDouble(firstPart + e.text + secondPart);
				if (value < 0d){
					e.doit = false;
					return;
				}
			} catch (Exception ex){
				e.doit = false;
				return;
			}
		}
	};
	
	/**
	 * Verify listener for the position, it allows positive digits
	 */
	private VerifyListener positionVerify = new VerifyListener() {
		
		@Override
		public void verifyText(VerifyEvent e) {
			if (e.text.isEmpty() && e.keyCode == SWT.BS){
				//Combo combo = (Combo)e.widget;
				//String currentText = combo.getText();
				//if (currentText.matches("[a-zA-Z]+")){
				//	combo.setText("");
				//}
				return;
			}
			try{
				String currentText = ((Combo)e.widget).getText();
				String firstPart = currentText.substring(0, e.start);
				String secondPart = currentText.substring(e.end, currentText.length());
				String newText = firstPart + e.text + secondPart;
				if (!newText.trim().equals(Messages.JSSGridBagLayout_relativeString)){
					int value = Integer.parseInt(newText);
					int maxNumber = e.widget == columnPosition ? MAX_COL_NUMBER : MAX_ROW_NUMBER;
					if (value < 0 || value > maxNumber){
						e.doit = false;
						return;
					}
				}
			} catch (Exception ex){
				e.doit = false;
				return;
			}
		}
	};
	
	/**
	 * Verify listener for the span, it allows positive digits
	 */
	private Listener spanVerify = new Listener() {
		
		@Override
		public void handleEvent(Event e){
			if (e.text.isEmpty()) return;
			
			try{
	      Spinner spinner = (Spinner)e.widget;
				String currentText = spinner.getText();
				String firstPart = currentText.substring(0, e.start);
				String secondPart = currentText.substring(e.end, currentText.length());
				String newText = firstPart + e.text + secondPart;
	      int value = Integer.parseInt(newText);
	      if (value < 1 || value > spinner.getMaximum()){
	      	e.doit = false;
	      }
	    } catch (NumberFormatException ex) {
	      e.doit = false;
	    }
		}
	};
	
	Comparator<String> hintsComparator = new Comparator<String>() {

		@Override
		public int compare(String o1, String o2) {
			if (Messages.JSSGridBagLayout_relativeString.equals(o1)){
				if (Messages.JSSGridBagLayout_relativeString.equals(o2)){
					return 0;
				} else return -1; 
			} else if (Messages.JSSGridBagLayout_relativeString.equals(o2)){
				if (Messages.JSSGridBagLayout_relativeString.equals(o1)){
					return 0;
				} else return 1; 
			} else if (o1 == null){
				if (o2 == null) return 0;
				else return -1;
			} else if (o2 == null){
				//at this point o1 is not null for sure
				return 1;
			}
			int hint1 = Integer.parseInt(o1);
			int hint2 = Integer.parseInt(o2);
			return (hint1 < hint2) ? -1 : ((hint1 == hint2) ? 0 : 1);
		}
	};

	/**
	 * The combo where the user can set the column position or select the relative value
	 */
	private Combo columnPosition;
	
	/**
	 * The combo where the user can set the row position or select the relative value
	 */
	private Combo rowPosition;
	
	/**
	 * The text where the user can insert the row weight
	 */
	private Text columnWeight;
	
	/**
	 * The text where the user can insert the column weight
	 */
	private Text rowWeight;
	
	/**
	 * The text where the user can insert the row row
	 */
	private Spinner rowSpan;
	
	/**
	 * The text where the user can insert the column span
	 */
	private Spinner columnSpan;
	
	/**
	 * The currently selected element
	 */
	private ANode selectedElement;
	
	/**
	 * The current section
	 */
	private AbstractSection section;
	
	/**
	 * Combo used to select if an element has a fixed size or no
	 */
	private Combo fixedSizeCombo;
	
	/**
	 * Guard used to avoid the modify listener to be fired when setting the value of the fields
	 */
	private boolean modifyGuard = false;
	
	/**
	 * A single instance of the listener is used
	 */
	private ValidationModifyListener listener = new ValidationModifyListener();
	
	/**
	 * Hints list used on the row position combo, the first value must be always the relative one
	 */
	private List<String> rowHint = new ArrayList<String>(Arrays.asList(new String[]{Messages.JSSGridBagLayout_relativeString, "0", "1","2","3","4"}));
	
	/**
	 * Hints list used in the column position combo, the first value must be always the relative one
	 */
	private List<String> colHint = new ArrayList<String>(Arrays.asList(new String[]{Messages.JSSGridBagLayout_relativeString, "0", "1","2","3","4"}));
	
	
	/**
	 * Apply the current properties to all the selected elements, only if their properties
	 * are different from the inserted one, and then if at least an element is changed relayout 
	 * the container
	 */
	protected void updateElement(){
		if (!modifyGuard){
			modifyGuard = true;	
			JSSCompoundCommand cmd = new JSSCompoundCommand(selectedElement);
			CommandStack cs = section.getEditDomain().getCommandStack();	
			SetLayoutProperty setCommand = new SetLayoutProperty();	
			for(APropertyNode node : section.getElements()){
				JRPropertiesHolder currentElement = LayoutManager.getPropertyHolder(node);
				if (currentElement != null && hasValueChanged(node)){
					JRPropertiesMap clone = (JRPropertiesMap)currentElement.getPropertiesMap().clone();
					
					clone.setProperty(JSSGridBagLayout.PROPERTY_X, String.valueOf(getComboValue(columnPosition)));
					clone.setProperty(JSSGridBagLayout.PROPERTY_Y, String.valueOf(getComboValue(rowPosition)));
					
					clone.setProperty(JSSGridBagLayout.PROPERTY_WEIGHT_COLUMN, columnWeight.getText());
					clone.setProperty(JSSGridBagLayout.PROPERTY_WEIGHT_ROW, rowWeight.getText());
					
					clone.setProperty(JSSGridBagLayout.PROPERTY_ROWSPAN, rowSpan.getText());
					clone.setProperty(JSSGridBagLayout.PROPERTY_COLSPAN, columnSpan.getText());
					
					clone.setProperty(JSSGridBagLayout.PROPERTY_IS_FIXED, String.valueOf(isFixedSize()));
					SetValueCommand setMapCommand = new SetValueCommand("Layout Settings"); //$NON-NLS-1$
					setMapCommand.setTarget(node);
					setMapCommand.setPropertyId(MGraphicElement.PROPERTY_MAP);
					setMapCommand.setPropertyValue(clone);
					setCommand.addSetValueCommand(setMapCommand);
				}
			}
			//Relayout the container if an element is changed
			if (!setCommand.isEmpty()){
				setCommand.setLayoutCommand(creteRelayoutCommand());
				cmd.add(setCommand);
				cs.execute(cmd);
			}
			modifyGuard = false;
		}
	}
	
	/**
	 * Update the hints of the combo adding the current combo value to 
	 * the hints if it was not already present. The first value, relative,
	 * is always left untouched
	 * 
	 * @param widget the combo to update.
	 */
	private void updateComboHints(Combo widget){
		List<String> hints = null;
		String text = getComboTextValue(widget);
		if (!text.trim().isEmpty()){
			if (widget == rowPosition){
				hints = rowHint;
			} else {
				hints = colHint;
			}
			if (hints != null){
				int index = hints.indexOf(text);
				if (index == -1){
					hints.add(text);
					Collections.sort(hints, hintsComparator); 
					widget.removeSelectionListener(comboSelectionListener);
					widget.setItems(hints.toArray(new String[hints.size()]));
					int elementIndex = hints.indexOf(text);
					Point caretLocation = widget.getCaretLocation();
					widget.select(elementIndex);
					widget.setSelection(caretLocation);
					widget.addSelectionListener(comboSelectionListener);
				}
			}
		}
	}
	
	/**
	 * Check if the properties of the passed element are different from the input on
	 * 
	 * @param element the element to check, must be not null
	 * @return true if the provided property are valid and different from the one of the element,
	 * false otherwise
	 */
	private boolean hasValueChanged(ANode element){
		
		//the property are valid, check if they are different also
		JRPropertiesHolder currentElement = LayoutManager.getPropertyHolder(element);
		if (currentElement != null){
			
			Object prop = currentElement.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_X);
			prop = prop != null ? prop.toString() : String.valueOf(GridBagConstraints.RELATIVE);
			//If the text is empty then restore the current value
			if(columnPosition.getText().trim().isEmpty()){
				if (prop.equals(String.valueOf(GridBagConstraints.RELATIVE))){
					columnPosition.select(0);
					columnPosition.setText(columnPosition.getItem(0));
				} else {
					columnPosition.setText(parsePosition(prop));
				}
			}
			if (!prop.equals(String.valueOf(getComboValue(columnPosition)))) return true;
				
			prop = currentElement.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_Y);
			prop = prop != null ? prop.toString() : String.valueOf(GridBagConstraints.RELATIVE);
			//If the text is empty then restore the current value
			if(rowPosition.getText().trim().isEmpty()){
				if (prop.equals(String.valueOf(GridBagConstraints.RELATIVE))){
					rowPosition.select(0);
					rowPosition.setText(columnPosition.getItem(0));
				} else {
					rowPosition.setText(parsePosition(prop));
				}
			}
			if (!prop.equals(String.valueOf(getComboValue(rowPosition)))) return true;
			
			prop = currentElement.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_WEIGHT_COLUMN);
			prop = prop != null ? prop.toString() : String.valueOf(1.0);
			columnWeight.removeVerifyListener(weightVerify);
			//If the text is empty then restore the current value
			if (columnWeight.getText().trim().isEmpty()){
				columnWeight.setText(parseWeight(prop));
			} else {
				columnWeight.setText(parseWeight(columnWeight.getText()));
			}
			columnWeight.addVerifyListener(weightVerify);
			if (!prop.equals(columnWeight.getText())) return true;
			
			prop = currentElement.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_WEIGHT_ROW);
			prop = prop != null ? prop.toString() : String.valueOf(1.0);
			rowWeight.removeVerifyListener(weightVerify);
			//If the text is empty then restore the current value
			if (rowWeight.getText().trim().isEmpty()){
				rowWeight.setText(parseWeight(prop));
			} else {
				rowWeight.setText(parseWeight(rowWeight.getText()));
			}
			rowWeight.addVerifyListener(weightVerify);
			if (!prop.equals(rowWeight.getText())) return true;
		
			prop = currentElement.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_ROWSPAN);
			prop = prop != null ? prop.toString() : String.valueOf(1);
			if (!prop.equals(rowSpan.getText())) return true;		

			prop = currentElement.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_COLSPAN);
			prop = prop != null ? prop.toString() : String.valueOf(1);
			if (!prop.equals(columnSpan.getText())) return true;
		
			prop = currentElement.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_IS_FIXED);
			boolean value = prop != null ? Boolean.parseBoolean(prop.toString()) : false;
			int selectionIndex = value ? 1 : 0;
			if (fixedSizeCombo.getSelectionIndex() != selectionIndex) return true;
		}
		return false;
	}
	
	/**
	 * Create the command to relayout the container of the selected element
	 * 
	 * @return A {@link LayoutCommand} or null if it can't be generated
	 */
	protected LayoutCommand creteRelayoutCommand(){
		ANode parent = selectedElement.getParent();
		Object jrElement = parent.getValue();
		
		//Search the parent group
		JRElementGroup jrGroup = null;
		if (parent instanceof IGroupElement)
			jrGroup = ((IGroupElement) parent).getJRElementGroup();
		else if (parent.getValue() instanceof JRElementGroup)
			jrGroup = (JRElementGroup) parent.getValue();
		
		//search the size of the parent
		Dimension d = new Dimension();
		if (parent instanceof IGraphicElementContainer){
			//d = ((IGraphicElementContainer) parent).getSize();
			d = LayoutManager.getPaddedSize((IGraphicElementContainer)parent);
		}
		if (jrElement instanceof JRCommonElement) {
			//JRCommonElement jce = (JRCommonElement) jrElement;
			//d.setSize(new Dimension(jce.getWidth(), jce.getHeight()));
			d = LayoutManager.getPaddedSize((JRCommonElement)jrElement);
		} else if (jrElement instanceof JRDesignBand) {
			JasperDesign jDesign = parent.getJasperDesign();
			int w = jDesign.getPageWidth() - jDesign.getLeftMargin() - jDesign.getRightMargin();
			d.setSize(new Dimension(w, ((JRDesignBand) jrElement).getHeight()));
		}
		
		//get the properties of the parent
		JRPropertiesMap pholder = LayoutManager.getPropertyMap(parent);
		if (pholder != null && jrGroup != null) {
			String str = pholder.getProperty(ILayout.KEY);
			if (str == null){
				str = FreeLayout.class.getName();
			}
			ILayout parentLayout = LayoutManager.getLayout(str);		
			return new LayoutCommand(parent.getJasperDesign(), jrGroup, parentLayout, d);
		}
		return null;
	}
	
	/**
	 * Get the value inserted in the combo for the row or column position.
	 * If the value is the string to identify the relative value then the 
	 * relative numeric value is returned
	 * 
	 * @param widget the combo
	 * @return a valid column or row position
	 */
	private int getComboValue(Combo widget){
		if (widget.getText().trim().equalsIgnoreCase(Messages.JSSGridBagLayout_relativeString)){
			return GridBagConstraints.RELATIVE; 
		} else {
			try {
				int value = Integer.parseInt(widget.getText());
				return value;
			} catch (Exception ex){
				widget.setText(Messages.JSSGridBagLayout_relativeString);
				return GridBagConstraints.RELATIVE;
			}
		}
	}
	
	/**
	 * Get the value inserted in the combo for the row or column position.
	 * If the value is the string to identify the relative value then the 
	 * relative numeric value is returned
	 * 
	 * @param widget the combo
	 * @return a valid column or row position
	 */
	private String getComboTextValue(Combo widget){
		if (widget.getText().trim().equalsIgnoreCase(Messages.JSSGridBagLayout_relativeString)){
			return Messages.JSSGridBagLayout_relativeString; 
		} else {
			try {
				int value = Integer.parseInt(widget.getText());
				return String.valueOf(value);
			} catch (Exception ex){
				widget.setText(Messages.JSSGridBagLayout_relativeString);
				return Messages.JSSGridBagLayout_relativeString;
			}
		}
	}
	
	
	/**
	 * Enable or disable the weight widgets if the size
	 * is marked as fixed or not
	 */
	protected void refreshWeightWidgets(){
		columnWeight.setEnabled(!isFixedSize());
		rowWeight.setEnabled(!isFixedSize());
	}
	
	/**
	 * Return if in the fixed size combo is selected 
	 * the entry for the fixed size or not
	 * 
	 * @return true if the size of the element is set as fixed
	 * false otherwise
	 */
	protected boolean isFixedSize(){
		return fixedSizeCombo.getSelectionIndex() == 1;
	}
	
	@Override
	public void createControls(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(4, false));
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Label rowPositionLabel = new Label(container, SWT.NONE);
		rowPositionLabel.setText(Messages.JSSGridBagLayout_rowLabel);
		String rowTooltip = MessageFormat.format(Messages.JSSGridBagUIProvider_rowPositionTooltip, new Object[]{String.valueOf(MAX_ROW_NUMBER)});
		rowPositionLabel.setToolTipText(rowTooltip);
		rowPosition= new Combo(container, SWT.BORDER);
		rowPosition.setItems(rowHint.toArray(new String[rowHint.size()]));
		rowPosition.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		rowPosition.addSelectionListener(comboSelectionListener);
	  rowPosition.addKeyListener(listener);
	  rowPosition.addVerifyListener(positionVerify);
	  rowPosition.addFocusListener(listener);
	  rowPosition.setToolTipText(rowTooltip);
		
		Label columnPositionLabel = new Label(container, SWT.NONE);
		columnPositionLabel.setText(Messages.JSSGridBagLayout_columnLabel);
		String columnTooltip = MessageFormat.format(Messages.JSSGridBagUIProvider_columnPositionToolTip, new Object[]{String.valueOf(MAX_COL_NUMBER)});
		columnPositionLabel.setToolTipText(columnTooltip);
		columnPosition = new Combo(container, SWT.BORDER);
		columnPosition.setItems(colHint.toArray(new String[colHint.size()]));
		columnPosition.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	  columnPosition.addSelectionListener(comboSelectionListener);
	  columnPosition.addKeyListener(listener);
	  columnPosition.addVerifyListener(positionVerify);
	  columnPosition.addFocusListener(listener);
	  columnPosition.setToolTipText(columnTooltip);  
		
	  Label rowSpanLabel = new Label(container, SWT.NONE);
	  rowSpanLabel.setText(Messages.JSSGridBagLayout_rowSpanLabel);
	  rowSpanLabel.setToolTipText(Messages.JSSGridBagUIProvider_rowSpanToolTip);
		rowSpan = new Spinner(container, SWT.BORDER);
		rowSpan.setMinimum(1);
		rowSpan.setMaximum(MAX_ROW_NUMBER);
		rowSpan.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		rowSpan.addKeyListener(listener);
		rowSpan.addListener(SWT.Verify, spanVerify);
		rowSpan.addFocusListener(listener);
	  rowSpan.addSelectionListener(new SelectionAdapter() {
	  	
	  	@Override
	  	public void widgetSelected(SelectionEvent e) {
	  		updateElement();
	  	}
		});
		rowSpan.setToolTipText(Messages.JSSGridBagUIProvider_rowSpanToolTip);
		
		Label columnSpanLabel = new Label(container, SWT.NONE);
		columnSpanLabel.setText(Messages.JSSGridBagLayout_columnSpanLabel);
		columnSpanLabel.setToolTipText(Messages.JSSGridBagUIProvider_columnSpanToolTip);
		columnSpan = new Spinner(container, SWT.BORDER); 
		columnSpan.setMinimum(1);
		columnSpan.setMaximum(MAX_COL_NUMBER);
		columnSpan.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		columnSpan.addKeyListener(listener);
		columnSpan.addListener(SWT.Verify, spanVerify);
		columnSpan.addFocusListener(listener);
	  columnSpan.addSelectionListener(new SelectionAdapter() {
	  	
	  	@Override
	  	public void widgetSelected(SelectionEvent e) {
	  		updateElement();
	  	}
		});
		columnSpan.setToolTipText(Messages.JSSGridBagUIProvider_columnSpanToolTip);
		
		Label fixedSizeLabel = new Label(container, SWT.NONE);
		fixedSizeLabel.setText(Messages.JSSGridBagLayout_labelFixedSize);
		fixedSizeLabel.setToolTipText(Messages.JSSGridBagUIProvider_fixedSizeTooltip);
		fixedSizeCombo = new Combo(container, SWT.READ_ONLY);
		fixedSizeCombo.setItems(new String[]{Messages.SP3Boolean_False_Value, Messages.SP3Boolean_True_Value});
		GridData fixedComboData = new GridData();
		fixedComboData.horizontalSpan = 3;
		fixedSizeCombo.setLayoutData(fixedComboData);
		fixedSizeCombo.select(0);
		fixedSizeCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				refreshWeightWidgets();
				updateElement();
			}
		});
		fixedSizeCombo.setToolTipText(Messages.JSSGridBagUIProvider_fixedSizeTooltip);
		
		Label rowWeightLabel = new Label(container, SWT.NONE);
		rowWeightLabel.setText(Messages.JSSGridBagLayout_rowWeightLabel);
		rowWeightLabel.setToolTipText(Messages.JSSGridBagUIProvider_rowWeightTooltip);
		rowWeight = new Text(container, SWT.BORDER);
		rowWeight.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		rowWeight.addKeyListener(listener);
		rowWeight.addFocusListener(listener);
		rowWeight.addVerifyListener(weightVerify);
		rowWeight.setToolTipText(Messages.JSSGridBagUIProvider_rowWeightTooltip);
		
		Label columnWeightLabel = new Label(container, SWT.NONE);
		columnWeightLabel.setText(Messages.JSSGridBagLayout_columnWeightLabel);
		columnWeightLabel.setToolTipText(Messages.JSSGridBagUIProvider_columnWeightTooltip);
		columnWeight = new Text(container, SWT.BORDER);
		columnWeight.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		columnWeight.addKeyListener(listener);
		columnWeight.addFocusListener(listener);
		columnWeight.addVerifyListener(weightVerify);
		columnWeight.setToolTipText(Messages.JSSGridBagUIProvider_columnWeightTooltip);
	}
	
  /**
   * Parse the weight property and return a valid value to
   * be used on the layout. If the property contains a valid value
   * (double >= 0) then it is returned, otherwise it uses the default value
   * 1.0. Also since the value is returned as text if it ends with . or there 
   * isn't a decimal part then it is modified to end like .0
   * 
   * @param prop the value of the property
   * @return a valid weight value, as text
   */
	private String parseWeight(Object prop){
		if(prop != null){
			String value = prop.toString().trim();
			try{
				double result = Double.parseDouble(value);
				if (result >= 0d){
					if (value.endsWith(".")){
						value += "0";
					} else if (!value.contains(".")){
						value += ".0";
					}
					return value;
				}
			} catch (Exception ex){
			}
		}
		return "1.0";
	}
	
  /**
   * Parse the span property and return a valid value to
   * be used on the layout. If the property contains a valid value
   * (int >= 0) then it is returned, otherwise it uses the default value
   * 1
   * 
   * @param prop the value of the property
   * @return a valid span value
   */
	private Integer parseSpan(Object prop){
		if(prop != null){
			String value = prop.toString().trim();
			try{
				int result = Integer.parseInt(value);
				if (result >= 1){
					return result;
				}
			} catch (Exception ex){
			}
		}
		return 1;
	}
	
  /**
   * Parse the position property and return a valid value to
   * be used on the layout. If the property contains a valid value
   * (int >= 0) then it is returned, otherwise it uses the default value
   * GridBagConstraints.RELATIVE
   * 
   * @param prop the value of the property
   * @return a valid weight position
   */
	private String parsePosition(Object prop){
		if(prop != null){
			String value = prop.toString().trim();
			if (!value.equals(Messages.JSSGridBagLayout_relativeString)){
				try{
					int result = Integer.parseInt(value);
					if (result >= 0){
						return String.valueOf(result);
					}
				} catch (Exception ex){
				}
			}
		}
		return Messages.JSSGridBagLayout_relativeString;
	}
	
	@Override
	public void setData(ANode selectedElement, AbstractSection section) {
		this.selectedElement = selectedElement;
		this.section = section;
		JRPropertiesHolder currentElement = LayoutManager.getPropertyHolder(selectedElement);
		if (currentElement != null){
			modifyGuard = true;
		
			columnPosition.removeVerifyListener(positionVerify);
			Object prop = currentElement.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_X);
			prop = prop != null ? prop.toString() : String.valueOf(GridBagConstraints.RELATIVE);
			if (prop.equals(String.valueOf(GridBagConstraints.RELATIVE))){
				columnPosition.select(0);
				columnPosition.setText(columnPosition.getItem(0));
			} else {
				columnPosition.setText(parsePosition(prop));
			}
			columnPosition.addVerifyListener(positionVerify);
			
			rowPosition.removeVerifyListener(positionVerify);
			prop = currentElement.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_Y);
			prop = prop != null ? prop.toString() : String.valueOf(GridBagConstraints.RELATIVE);
			if (prop.equals(String.valueOf(GridBagConstraints.RELATIVE))){
				rowPosition.select(0);
				rowPosition.setText(rowPosition.getItem(0));
			} else {
				rowPosition.setText(parsePosition(prop));
			}
			rowPosition.addVerifyListener(positionVerify);
			
			columnWeight.removeVerifyListener(weightVerify);
			prop = currentElement.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_WEIGHT_COLUMN);
			columnWeight.setText(parseWeight(prop));
			columnWeight.addVerifyListener(weightVerify);
			
			rowWeight.removeVerifyListener(weightVerify);
			prop = currentElement.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_WEIGHT_ROW);
			rowWeight.setText(parseWeight(prop));
			rowWeight.addVerifyListener(weightVerify);
			
			prop = currentElement.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_ROWSPAN);
			rowSpan.setSelection(parseSpan(prop));
			
			prop = currentElement.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_COLSPAN);
			columnSpan.setSelection(parseSpan(prop));
			
			prop = currentElement.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_IS_FIXED);
			boolean value = prop != null ? Boolean.parseBoolean(prop.toString()) : false;
			fixedSizeCombo.select(value ? 1 : 0);
			refreshWeightWidgets();
			
			modifyGuard = false;
		}
	}

	@Override
	public void createLayoutControls(Composite parent) {
	
	}
}
