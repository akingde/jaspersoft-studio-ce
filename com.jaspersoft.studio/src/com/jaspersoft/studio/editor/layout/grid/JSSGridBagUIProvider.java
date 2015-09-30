package com.jaspersoft.studio.editor.layout.grid;

import net.sf.jasperreports.engine.JRCommonElement;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.util.Util;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

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
	 * The listener used to store the changes and update the control status
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class ValidationModifyListener extends KeyAdapter implements ModifyListener, FocusListener{
		
		@Override
		public void modifyText(ModifyEvent e) {
			//on the modify only validate the controls
			validateControl(e.widget);
		}	
		
		@Override
		public void keyPressed(KeyEvent e) {
			//when enter is pressed validate the control an save the changes
			if (e.keyCode == SWT.CR){
				validateControl(e.widget);
				updateElement();
			}
		}

		@Override
		public void focusGained(FocusEvent e) {
		}

		@Override
		public void focusLost(FocusEvent e) {
			updateElement();
		}
	}
	
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
	private Text rowSpan;
	
	/**
	 * The text where the user can insert the column span
	 */
	private Text columnSpan;
	
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
					clone.setProperty(JSSGridBagLayout.PROPERTY_WEIGHTX, columnWeight.getText());
					clone.setProperty(JSSGridBagLayout.PROPERTY_WEIGHTY, rowWeight.getText());
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
	 * Check if the properties of the passed element are different from the input one, or if
	 * there are some validation error on the widgets
	 * 
	 * @param element the element to check, must be not null
	 * @return true if the provided property are valid and different from the one of the element,
	 * false otherwise
	 */
	private boolean hasValueChanged(ANode element){
		//check if the widgets values are valid
		if (columnWeight.getToolTipText() != null || 
					rowWeight.getToolTipText() != null ||
						rowSpan.getToolTipText() != null ||
								columnSpan.getToolTipText() != null ||
									columnPosition.getToolTipText() != null ||
										rowPosition.getToolTipText() != null){
			return false;
		}
		
		//the property are valid, check if they are different also
		JRPropertiesHolder currentElement = LayoutManager.getPropertyHolder(element);
		if (currentElement != null){
			Object prop = currentElement.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_X);
			prop = prop != null ? prop.toString() : String.valueOf(GridBagConstraints.RELATIVE);
			if (!prop.equals(String.valueOf(getComboValue(columnPosition)))) return true;
			
			prop = currentElement.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_Y);
			prop = prop != null ? prop.toString() : String.valueOf(GridBagConstraints.RELATIVE);
			if (!prop.equals(String.valueOf(getComboValue(rowPosition)))) return true;
			
			prop = currentElement.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_WEIGHTX);
			prop = prop != null ? prop.toString() : String.valueOf(1.0);
			if (!prop.equals(columnWeight.getText())) return true;
			
			prop = currentElement.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_WEIGHTY);
			prop = prop != null ? prop.toString() : String.valueOf(1.0);
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
			d = ((IGraphicElementContainer) parent).getSize();
		}
		if (jrElement instanceof JRCommonElement) {
			JRCommonElement jce = (JRCommonElement) jrElement;
			d.setSize(new Dimension(jce.getWidth(), jce.getHeight()));
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
			return new LayoutCommand(jrGroup, parentLayout, d);
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
			int value = Integer.parseInt(widget.getText());
			return value;
		}
	}
	
	/**
	 * Set an error message on an element and make its background
	 * red, or remove it and set the background to the default.
	 * On macosx the element is also forced to lost and regain 
	 * the focus to avoid an SWT glitch
	 * 
	 * @param message the error message, it is shown as tooltip. Use null
	 * to remove a previously set error message
	 * @param widget the widget where the error is set\removed
	 */
	private void setError(String message, Control widget){
		boolean changed = false;
		if (message == null){
			if (widget.getBackground() != null){
				widget.setBackground(null);
				widget.setToolTipText(null);
				changed = true;
			}
		} else {
			if (widget.getBackground() != ColorConstants.red){
				widget.setBackground(ColorConstants.red);
				widget.setToolTipText(message);
				changed = true;
			}
		}
		//if the platform is mac and the status of the widget is changed
		//then use the focus trick to force the refresh
		if (changed && Util.isMac()){
			if (widget instanceof Combo){
				controlRefresh((Combo)widget);
			} else {
				controlRefresh((Text)widget);
			}
		}
	}
	
	/**
	 * Force the visual refresh of a combo control on make, make 
	 * it lose and regain the focus. The position of the caret is
	 * saved before to loose the focus and then restored. The flag to
	 * store a property is disable to avoid the focus event when it is
	 * only a refresh
	 * 
	 * @param widget the combo widget, must be not null
	 */
	private void controlRefresh(Combo widget){
		modifyGuard = true;
		int caretPosition = widget.getCaretPosition();
		widget.getParent().setFocus();
		widget.setFocus();
		widget.setSelection(new Point(caretPosition, caretPosition));
		modifyGuard = false;
	}
	
	/**
	 * Force the visual refresh of a text control on make, make 
	 * it lose and regain the focus. The position of the caret is
	 * saved before to loose the focus and then restored. The flag to
	 * store a property is disable to avoid the focus event when it is
	 * only a refresh
	 * 
	 * @param widget the text widget, must be not null
	 */
	private void controlRefresh(Text widget){
		modifyGuard = true;
		int caretPosition = widget.getCaretPosition();
		widget.getParent().setFocus();
		widget.setFocus();
		widget.setSelection(new Point(caretPosition, caretPosition));
		modifyGuard = false;
	}
	
	/**
	 * Check if the value of the row\column position combo is valid.
	 * The value is valid is is a positive integer or the relative string
	 * If it is not valid the error is set on the combo
	 * 
	 * @param widget the combo to check, must be not null
	 */
	protected void validateCombo(Combo widget){
		String text = widget.getText();
		if (text.trim().isEmpty()) {
			setError(Messages.JSSGridBagLayout_emptyValue, widget);
			return;
		} 
		try {
			int value = getComboValue(widget);
			if (value < 0 && value != GridBagConstraints.RELATIVE){
				setError(Messages.JSSGridBagLayout_notNegativeValue, widget);
				return;						
			}
		} catch (Exception ex) {
			setError(Messages.JSSGridBagLayout_validNumberValue, widget);
			return;
		}
		setError(null, widget);
	}	
	
	/**
	 * Check if the value of a text input area is valid.
	 * The value is valid is is a positive number or the relative string,
	 * also the type of the number can be set trough a parameter to check if
	 * it is a valid integer or double
	 * If it is not valid the error is set on the text
	 * 
	 * @param widget the Text to check, must be not null
	 * @param the type of the value expected in the text, must be integer or double
	 */
	protected void validateText(Text widget, Class<?> type){
		String text = widget.getText();
		if (text.trim().isEmpty()) {
			setError(Messages.JSSGridBagLayout_emptyValue, widget);
			return;
		} 
		if (type == Integer.class){
			try {
				int value = Integer.parseInt(text);
				if (value < 0){
					setError(Messages.JSSGridBagLayout_notNegativeValue, widget);
					return;						
				}
			} catch (Exception ex) {
				setError(Messages.JSSGridBagLayout_validNumberValue, widget);
				return;
			}
		} else if (type == Double.class){
			try {
				double value = Double.parseDouble(text);
				if (value < 0d){
					setError(Messages.JSSGridBagLayout_notNegativeValue, widget);
					return;						
				}
			} catch (Exception ex) {
				setError(Messages.JSSGridBagLayout_validNumberValue, widget);
				return;
			}
		}
		setError(null, widget);
	}
	
	/**
	 * Enable or disable the weight widgets if the size
	 * is marked as fiexd or not
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
	
	/**
	 * Validate a generic control and set or remove the error from it
	 * 
	 * @param widget the control
	 */
	protected void validateControl(Widget widget){
		if (!modifyGuard){
			if (widget == rowPosition || widget == columnPosition){
				validateCombo((Combo)widget);
			} else if (widget == rowSpan || widget == columnSpan){
				validateText((Text)widget, Integer.class);
			} else if (widget == rowWeight || widget == columnWeight){
				validateText((Text)widget, Double.class);
			}
		}
	}
	


	@Override
	public void createControls(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(4, false));
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		new Label(container, SWT.NONE).setText(Messages.JSSGridBagLayout_rowLabel);
		rowPosition= new Combo(container, SWT.BORDER);
		rowPosition.setItems(new String[]{Messages.JSSGridBagLayout_relativeString});
		rowPosition.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	  rowPosition.addSelectionListener(new SelectionAdapter() {
	  	
	  	@Override
	  	public void widgetSelected(SelectionEvent e) {
	  		validateCombo(rowPosition);
	  		updateElement();
	  	}
		});
	  rowPosition.addKeyListener(listener);
	  rowPosition.addModifyListener(listener);
	  rowPosition.addFocusListener(listener);
		
		new Label(container, SWT.NONE).setText(Messages.JSSGridBagLayout_columnLabel);
		columnPosition = new Combo(container, SWT.BORDER);
		columnPosition.setItems(new String[]{Messages.JSSGridBagLayout_relativeString});
		columnPosition.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	  columnPosition.addSelectionListener(new SelectionAdapter() {
	  	
	  	@Override
	  	public void widgetSelected(SelectionEvent e) {
	  		validateCombo(columnPosition);
	  		updateElement();
	  	}
		});
	  columnPosition.addKeyListener(listener);
	  columnPosition.addModifyListener(listener);
	  columnPosition.addFocusListener(listener);
	  
		new Label(container, SWT.NONE).setText(Messages.JSSGridBagLayout_rowSpanLabel);
		rowSpan = new Text(container, SWT.BORDER);
		rowSpan.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		rowSpan.addKeyListener(listener);
		rowSpan.addModifyListener(listener);
		rowSpan.addFocusListener(listener);
	  
		
		new Label(container, SWT.NONE).setText(Messages.JSSGridBagLayout_columnSpanLabel);
		columnSpan = new Text(container, SWT.BORDER);
		columnSpan.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		columnSpan.addKeyListener(listener);
		columnSpan.addModifyListener(listener);
		columnSpan.addFocusListener(listener);
		
		new Label(container, SWT.NONE).setText(Messages.JSSGridBagLayout_labelFixedSize);
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
		
		new Label(container, SWT.NONE).setText(Messages.JSSGridBagLayout_rowWeightLabel);
		columnWeight = new Text(container, SWT.BORDER);
		columnWeight.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		columnWeight.addKeyListener(listener);
		columnWeight.addModifyListener(listener);
		columnWeight.addFocusListener(listener);
		
		new Label(container, SWT.NONE).setText(Messages.JSSGridBagLayout_columnWeightLabel);
		rowWeight = new Text(container, SWT.BORDER);
		rowWeight.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		rowWeight.addKeyListener(listener);
		rowWeight.addModifyListener(listener);
		rowWeight.addFocusListener(listener);
	}
	
	@Override
	public void setData(ANode selectedElement, AbstractSection section) {
		this.selectedElement = selectedElement;
		this.section = section;
		JRPropertiesHolder currentElement = LayoutManager.getPropertyHolder(selectedElement);
		if (currentElement != null){
			modifyGuard = true;
		
			Object prop = currentElement.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_X);
			prop = prop != null ? prop.toString() : String.valueOf(GridBagConstraints.RELATIVE);
			if (prop.equals(String.valueOf(GridBagConstraints.RELATIVE))){
				columnPosition.select(0);
				columnPosition.setText(columnPosition.getItem(0));
			} else {
				columnPosition.setText(prop.toString());
			}
			
			prop = currentElement.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_Y);
			prop = prop != null ? prop.toString() : String.valueOf(GridBagConstraints.RELATIVE);
			if (prop.equals(String.valueOf(GridBagConstraints.RELATIVE))){
				rowPosition.select(0);
				rowPosition.setText(rowPosition.getItem(0));
			} else {
				rowPosition.setText(prop.toString());
			}
			
			prop = currentElement.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_WEIGHTX);
			columnWeight.setText(prop != null ? prop.toString() : String.valueOf(1.0));
			
			prop = currentElement.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_WEIGHTY);
			rowWeight.setText(prop != null ? prop.toString() : String.valueOf(1.0));
			
			prop = currentElement.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_ROWSPAN);
			rowSpan.setText(prop != null ? prop.toString() : String.valueOf(1));
			
			prop = currentElement.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_COLSPAN);
			columnSpan.setText(prop != null ? prop.toString() : String.valueOf(1));
			
			prop = currentElement.getPropertiesMap().getProperty(JSSGridBagLayout.PROPERTY_IS_FIXED);
			boolean value = prop != null ? Boolean.parseBoolean(prop.toString()) : false;
			fixedSizeCombo.select(value ? 1 : 0);
			refreshWeightWidgets();
			
			modifyGuard = false;
		}
	}
}
