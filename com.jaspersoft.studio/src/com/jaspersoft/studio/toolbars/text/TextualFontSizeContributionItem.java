/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.toolbars.text;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.util.Util;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.model.text.MTextElement;
import com.jaspersoft.studio.property.ResetValueCommand;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.swt.widgets.NumericTableCombo;
import com.jaspersoft.studio.toolbars.CommonToolbarHandler;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.eclipse.ui.JSSTableCombo;
import net.sf.jasperreports.engine.design.JRDesignStyle;

/**
 * Combo to change the font size of the selected items. Since this is a complex toolitem (because
 * the created toolitem contains a composite with other controls) this need to
 * be put in a toolbar where at least a static simple item is present. In the context
 * of the textual toolbar this is done using the {@link DecrementFontSizeButtonContributionItem} and
 * the {@link IncrementFontSizeButtonContributionItem}
 * 
 * @author Orlandin Marco
 *
 */
public class TextualFontSizeContributionItem extends CommonToolbarHandler {
	
	/**
	 * On MacOS seems the contextual menu is not opened on combo, this
	 * lister will force it to open when a right click is found
	 */
	protected static MouseAdapter macComboMenuOpener = new MouseAdapter() {
		
		@Override
		public void mouseUp(MouseEvent e) {	
			if (e.button == 3 && ((Control)e.widget).getMenu() != null){
				Menu menu = ((Control)e.widget).getMenu();
				if (!menu.isDisposed() && !menu.isVisible()){
	        		Point location = e.widget.getDisplay().getCursorLocation();
					menu.setLocation(location.x, location.y);
					menu.setVisible(true);
				}
			}
		}
	};
	
	
	/**
	 * The node actually selected which attributes are shown in the controls
	 */
	private APropertyNode showedNode = null;

	/**
	 * Main container of the controls
	 */
	private Composite controlsArea;
	
	/**
	 * Combo with the font sizes
	 */
	private NumericTableCombo fontSize;

	/**
	 * Flag to ignore the change listeners when the state is refreshing 
	 */
	private boolean refreshing = false;
	
	//Used listener
	
	/**
	 * 
	 * Listener used to update the status of the Font name, size bold and italic
	 * when they change inside the the element selected (maybe because they are 
	 * changed from the properties view)
	 */
	private PropertyChangeListener nodeChangeListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if (fontSize == null || fontSize.isDisposed())
				return;

			refreshing = true;
			List<Object> selection = getSelectionForType(MTextElement.class);
			if (selection.size() == 1) {
				APropertyNode node = (APropertyNode) selection.get(0);
				Object actaulValue = node.getPropertyActualValue(JRDesignStyle.PROPERTY_FONT_SIZE);
				Object ownValue = node.getPropertyValue(JRDesignStyle.PROPERTY_FONT_SIZE);
				setFontSizeComboText(actaulValue, ownValue);
			} else {
				setFontSizeComboText(null, null);
				if (showedNode != null) {
					showedNode.getPropertyChangeSupport().removePropertyChangeListener(nodeChangeListener);
					showedNode = null;
				}
			}
			refreshing = false;
		}
	};
	
	/**
	 * Change the font size of one or more elements
	 */
	private SelectionAdapter fontSizeComboModify = new SelectionAdapter() {
		
		@Override
		public void widgetSelected(SelectionEvent e) {
			if (!refreshing){
				List<Object> selection = getSelectionForType(MTextElement.class);
				if (selection.isEmpty())
					return;
				JSSCompoundCommand cc = new JSSCompoundCommand(null);
				Float value = fontSize.getValueAsFloat();
				for (Object obj : selection) {
					Command changeValueCmd = createCommand(obj, value, JRDesignStyle.PROPERTY_FONT_SIZE);
					if (changeValueCmd != null) {
						cc.add(changeValueCmd);
						cc.setReferenceNodeIfNull(obj);
					}
				}		
				CommandStack cs = getCommandStack();
				if (cs != null) getCommandStack().execute(cc);
			}
		}
	};
	
	/**
	 * Build the font size combo with a fixed size
	 * 
	 * @param parent the parent of the combo
	 * @return a not null {@link NumericTableCombo}
	 */
	protected NumericTableCombo getFontSizeCombo(Composite parent){
		NumericTableCombo result = new NumericTableCombo(parent, JSSTableCombo.STRIGHT_CORNER, 0, 6){
			
			@Override
			protected Point computeSize(Composite container, int wHint, int hHint) {
				int width = wHint;
				int height = hHint;
				Point defaultSize = getDefaultComboSize();
				if (wHint == SWT.DEFAULT){
					width = defaultSize != null ? defaultSize.x : 50;
				}
				if (hHint == SWT.DEFAULT){
					height = defaultSize != null ? defaultSize.y : 23;
				}
				return new Point(width, height);
			};
		};
		result.setMaximum(new Double(Float.MAX_VALUE));
		result.setData(WIDGET_DATA_KEY, JRDesignStyle.PROPERTY_FONT_SIZE);
		result.setItems(ModelUtils.FONT_SIZES);
		return result;
	}

	@Override
	protected Control createControl(Composite parent) {
		controlsArea = new Composite(parent, SWT.NONE);
		RowLayout layout = new RowLayout();
		layout.marginBottom = 0;
		layout.marginTop = 0;
		layout.marginLeft = 0;
		layout.marginRight = 0;
		controlsArea.setLayout(layout);
		
		fontSize = getFontSizeCombo(controlsArea);
		fontSize.addSelectionListener(fontSizeComboModify);

		RowData data = new RowData();
		data.width = 80;
		fontSize.setLayoutData(data);
		
		setAllControlsData();

		return controlsArea;
	}

	
	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		ToolItem tiFontSizeCombo = new ToolItem(parent,SWT.SEPARATOR);
		fontSize = getFontSizeCombo(parent);
		fontSize.addSelectionListener(fontSizeComboModify);
		fontSize.pack();
		tiFontSizeCombo.setWidth(65);
		tiFontSizeCombo.setControl(fontSize);
		getToolItems().add(tiFontSizeCombo);
		
		setAllControlsData();
		
		return true;
	}

	/**
	 * Create a command to change the property of the element
	 * 
	 * @param model the element
	 * @param value the new value
	 * @param property the property
	 * @return the command to change the property
	 */
	protected Command createCommand(Object model, Object value, Object property) {
		if (!(model instanceof IPropertySource))
			return null;
		SetValueCommand cmd = new SetValueCommand();
		cmd.setTarget((IPropertySource) model);
		cmd.setPropertyId(property);
		cmd.setPropertyValue(value);
		return cmd;
	}

	@Override
	public boolean isVisible() {
		if (!super.isVisible()) {
			return false;
		}
		
		List<Object> selection = getSelectionForType(MTextElement.class);
		boolean selectionValid = selection.size() > 0;
		if (selectionValid){
			setAllControlsData();
		} else if (showedNode != null) {
			showedNode.getPropertyChangeSupport().removePropertyChangeListener(nodeChangeListener);
			showedNode = null;
		}
		return selectionValid;
	}

	/**
	 * Set the font size on the combo. It set the value to know if the number is 
	 * inherited or not.
	 * 
	 * @param resolvedValue the value of the font size resolved considering also the hirarchy
	 * @param elementValue the font size value on the element itself
	 */
	protected void setFontSizeComboText(Object resolvedValue, Object elementValue) {
		if (fontSize == null || fontSize.isDisposed())
			return;
		if (resolvedValue != null) {
			int oldpos = fontSize.getCaretPosition();
			if (elementValue == null) {
				fontSize.setDefaultValue((Number)resolvedValue);
			}
			fontSize.setValue((Number)elementValue);
			if (fontSize.getText().length() >= oldpos){
				fontSize.setSelection(new Point(oldpos, oldpos));
			}
		} else if (elementValue != null){
			int oldpos = fontSize.getCaretPosition();
			fontSize.setValue((Number)elementValue);
			if (fontSize.getText().length() >= oldpos){
				fontSize.setSelection(new Point(oldpos, oldpos));
			}
		} else {
			fontSize.setValue(null);
		}
	}
	
	/**
	 * Initialize all the controls that show the state of the object with the value of the
	 * selected element
	 */
	protected void setAllControlsData(){
		if (fontSize == null || fontSize.isDisposed()) return;
		refreshing = true;
		List<Object> selection = getSelectionForType(MTextElement.class);
		if (selection.size() == 1){
			APropertyNode node = (APropertyNode)selection.get(0);
			
			Object actaulSizeValue = node.getPropertyActualValue(JRDesignStyle.PROPERTY_FONT_SIZE);
			Object ownSizeValue = node.getPropertyValue(JRDesignStyle.PROPERTY_FONT_SIZE);
			setFontSizeComboText(actaulSizeValue, ownSizeValue);
			createContextualMenu(node, fontSize, JRDesignStyle.PROPERTY_FONT_SIZE);
			
			if (showedNode != null) showedNode.getPropertyChangeSupport().removePropertyChangeListener(nodeChangeListener);
			showedNode = node;
			showedNode.getPropertyChangeSupport().addPropertyChangeListener(nodeChangeListener);
			
		} else {
			setFontSizeComboText(null, null);
			if (showedNode != null) {
				showedNode.getPropertyChangeSupport().removePropertyChangeListener(nodeChangeListener);	
				showedNode = null;	
			}
		}
		refreshing = false;
	}
	
	@Override
	public void dispose() {
		super.dispose();
		if (showedNode != null) {
			showedNode.getPropertyChangeSupport().removePropertyChangeListener(nodeChangeListener);
			showedNode = null;
		}
		if (controlsArea != null) {
			controlsArea.dispose();
			controlsArea = null;
		}
		fontSize = null;
		refreshing = false;
	}
	
	/**
	 * Create a contextual menu for the passed control. This contextual menu
	 * will contains the action to reset the value of a property if the property
	 * has default value inside the node. Also it will contain the action to set the
	 * value to null if the operation is allowed.
	 * 
	 * Since on mac the combo item doens't have a contextual menu it add a special listneer
	 * for them as workaround to the problem
	 * 
	 * @param node node where the the command will be executed and from where the default map is extracted
	 * @param control control where the contextual menu will be set
	 * @param propertyID id of the property to set
	 */
	protected void createContextualMenu(final APropertyNode node, final Control control, final String propertyID){
		if (node != null && control != null && !control.isDisposed()){
		
			//MacOS fix, the combo on MacOS doesn't have a contextual menu, so we need to handle this listener manually
			boolean handleComboListener = Util.isMac() && control.getClass() == Combo.class;
			if (handleComboListener){
				control.removeMouseListener(macComboMenuOpener);
			}
			
			boolean entryCreated = false;
			Map<String, DefaultValue> defaultMap = node.getDefaultsPropertiesMap();
			if (defaultMap != null){
				DefaultValue defaultEntry = defaultMap.get(propertyID);
				if (defaultEntry != null && (defaultEntry.isNullable() || defaultEntry.hasDefault())){
					Menu controlMenu = new Menu(control);
					
					//Create the reset entry if necessary
					if (defaultEntry.hasDefault()){
						MenuItem resetItem = new MenuItem(controlMenu, SWT.NONE);
						entryCreated = true;
						resetItem.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent e) {
								ResetValueCommand cmd = new ResetValueCommand();
								cmd.setPropertyId(propertyID);
								cmd.setTarget(node);
								CommandStack cs = getCommandStack();
								cs.execute(cmd);
								control.setFocus();
							}
						});
				    resetItem.setText(Messages.ASPropertyWidget_0);
					}
					
					//Create the null entry if necessary
					if (defaultEntry.isNullable()){
						MenuItem nullItem = new MenuItem(controlMenu, SWT.NONE);
						entryCreated = true;
						nullItem.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent e) {
								SetValueCommand cmd = new SetValueCommand();
								cmd.setPropertyId(propertyID);
								cmd.setTarget(node);
								cmd.setPropertyValue(null);
								CommandStack cs = getCommandStack();
								cs.execute(cmd);
								control.setFocus();
							}
						});
				    nullItem.setText(Messages.ASPropertyWidget_1);
					}
					
					//if the control already have a menu dispose it first, since it is a swt widget
					//it is not disposed automatically by the garbage collector
					if (control.getMenu() != null){
						control.getMenu().dispose();
					}
					
					//set the new menu
					control.setMenu(controlMenu);
					if (handleComboListener){
						control.addMouseListener(macComboMenuOpener);
					}
				}
			}
			if (!entryCreated) {
				//if no entry was created remove the contextual menu, but first dispose
				//the old one
				if (control.getMenu() != null){
					control.getMenu().dispose();
				}
				control.setMenu(null);
			}
		}
		
	}
}
