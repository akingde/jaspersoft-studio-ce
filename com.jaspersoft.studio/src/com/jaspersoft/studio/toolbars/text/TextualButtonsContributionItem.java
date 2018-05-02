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
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.model.text.MTextElement;
import com.jaspersoft.studio.property.ResetValueCommand;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.toolbars.CommonToolbarHandler;

import net.sf.jasperreports.engine.base.JRBaseFont;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.VerticalTextAlignEnum;

/**
 * Controls to change the textual attributes of the selected elements, like 
 * bold, italic and the text alignment. Since this is a complex toolitem (because
 * the created toolitem contains a composite with other controls) this need to
 * be put in a toolbar where at least a static simple item is present. In the context
 * of the textual toolbar this is done using the {@link DecrementFontSizeButtonContributionItem} and
 * the {@link IncrementFontSizeButtonContributionItem}
 * 
 * @author Orlandin Marco
 *
 */
public class TextualButtonsContributionItem extends CommonToolbarHandler {
	
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

	//Controls for the font size buttons
	
	/**
	 * Toolitem to increment the text size
	 */
	//private ToolItem incrementButton;
	
	/**
	 * % factor for the increment\decrement
	 */
	public static Integer factor = 10;
	
	//Controls for the font style
	
	/**
	 * Toolitem to set the text to bold
	 */
	private ToolItem bold;
	
	/**
	 * Toolitem to set the text to italic
	 */
	private ToolItem italic;
	
	/**
	 * Toolbar for the bold button, keep a toolbar for each group of button to have its own contextual 
	 */
	private ToolBar boldToolbar;

	/**
	 * Toolbar for the italic button, keep a toolbar for each group of button to have its own contextual 
	 */
	private ToolBar italicToolbar;
	
	/**
	 * Toolbar for the horizontal alignment buttons, keep a toolbar for each group of button to have its own contextual 
	 */
	private ToolBar hAlignToolbar;

	/**
	 * Toolbar for the vertical alignment buttons, keep a toolbar for each group of button to have its own contextual 
	 */
	private ToolBar vAlignToolbar;
	
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
			if (boldToolbar == null || boldToolbar.isDisposed())
				return;

			refreshing = true;
			List<Object> selection = getSelectionForType(MTextElement.class);
			if (selection.size() == 1) {
				APropertyNode node = (APropertyNode) selection.get(0);
				if (evt.getPropertyName().equals(JRDesignStyle.PROPERTY_ITALIC)) {
					italic.setSelection((Boolean) node.getPropertyActualValue(JRDesignStyle.PROPERTY_ITALIC));
				} else if (evt.getPropertyName().equals(JRDesignStyle.PROPERTY_BOLD)) {
					bold.setSelection((Boolean) node.getPropertyActualValue(JRDesignStyle.PROPERTY_BOLD));
				}
			} else {
				italic.setSelection(false);
				bold.setSelection(false);
				if (showedNode != null) {
					showedNode.getPropertyChangeSupport().removePropertyChangeListener(nodeChangeListener);
					showedNode = null;
				}
			}
			refreshing = false;
		}
	};
	
	/**
	 * Listener called when the on of the fontsize buttons is pressed
	 */
	/*private SelectionAdapter fontSizeButtonSelect = new SelectionAdapter() {

		@Override
		public void widgetSelected(SelectionEvent e) {
			if (!refreshing){
				List<Object> selection = getSelectionForType(MTextElement.class);
				if (selection.isEmpty())
					return;
				JSSCompoundCommand cc = null;
				if (e.widget == incrementButton){
					cc = createIncremenrtsCommand(true, selection);
				} else {
					cc = createIncremenrtsCommand(false, selection);
				} 				
				getCommandStack().execute(cc);
			}
		}

	};*/
	
	/**
	 * Listener called when the bold or italic button is pressed
	 */
	private SelectionAdapter booleanButtonSelected = new SelectionAdapter() {
		
		public void widgetSelected(SelectionEvent e) {
			if (!refreshing){
				List<Object> selection = getSelectionForType(MTextElement.class);
				if (selection.isEmpty())
					return;
				Object value =	((ToolItem)e.widget).getSelection();
				Object property = e.widget.getData(WIDGET_DATA_KEY);
				JSSCompoundCommand cc = new JSSCompoundCommand(null);
				for(Object textElement : selection){
					Command changeValueCmd = createCommand(textElement, value, property);
					if (changeValueCmd != null) {
						cc.add(changeValueCmd);
						cc.setReferenceNodeIfNull(textElement);
					}
				}
				getCommandStack().execute(cc);
			}
		};
	};
	
	/**
	 * Selection listener that create the right command when a button is pushed
	 */
	private SelectionAdapter pushButtonPressed = new SelectionAdapter() {
		
	
		public void widgetSelected(SelectionEvent e) {
			if (!refreshing){
					List<Object> selection = getSelectionForType(MTextElement.class);
					if (selection.isEmpty())
						return;
					
					JSSCompoundCommand changeSizeCommands = new JSSCompoundCommand(null);
					String property = "";
					Object data = e.widget.getData(WIDGET_DATA_KEY);
					if (data instanceof VerticalTextAlignEnum) property = JRBaseStyle.PROPERTY_VERTICAL_TEXT_ALIGNMENT;
					else if (data instanceof HorizontalTextAlignEnum) property = JRBaseStyle.PROPERTY_HORIZONTAL_TEXT_ALIGNMENT;
					else return;
					for (Object textElement : selection){	
						changeSizeCommands.setReferenceNodeIfNull(textElement);
						Command c = createCommand(textElement, data, property);
						if (c != null) {
							changeSizeCommands.add(c);
						}
					}
					CommandStack cs = getCommandStack();
					cs.execute(changeSizeCommands);
			}
		}
	};
	

	@Override
	protected Control createControl(Composite parent) {
		controlsArea = new Composite(parent, SWT.NONE);
		RowLayout layout = new RowLayout();
		layout.marginBottom = 0;
		layout.marginTop = 0;
		layout.marginLeft = 0;
		layout.marginRight = 0;
		controlsArea.setLayout(layout);
		
		
		//ToolBar sizeButtons = new ToolBar(controlsArea, SWT.FLAT | SWT.WRAP);
		//incrementButton = createFontSizeButton(true, sizeButtons);
		//createFontSizeButton(false, sizeButtons);
		
		//Italic and bold button

		boldToolbar = new ToolBar(controlsArea, SWT.FLAT | SWT.WRAP);
		bold = new ToolItem(boldToolbar, SWT.CHECK);
		bold.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/font-bold.gif"));		
		bold.setToolTipText("Bold");
		bold.setData(WIDGET_DATA_KEY, JRDesignStyle.PROPERTY_BOLD);
		bold.addSelectionListener(booleanButtonSelected);
		bold.setWidth(25);
		
		italicToolbar = new ToolBar(controlsArea, SWT.FLAT | SWT.WRAP);
		italic = new ToolItem(italicToolbar, SWT.CHECK);
		italic.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/font-italic.gif"));		
		italic.setToolTipText("Italic");
		italic.setData(WIDGET_DATA_KEY, JRDesignStyle.PROPERTY_ITALIC);
		italic.addSelectionListener(booleanButtonSelected);
		italic.setWidth(25);
		
		//Buttons to set the text alignment
		
		hAlignToolbar = new ToolBar(controlsArea, SWT.FLAT | SWT.WRAP);
		ToolItem alignButton = new ToolItem(hAlignToolbar, SWT.PUSH);
		alignButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/left_align.gif"));
		alignButton.setData(WIDGET_DATA_KEY, HorizontalTextAlignEnum.LEFT);
		alignButton.addSelectionListener(pushButtonPressed);
		
		alignButton = new ToolItem(hAlignToolbar, SWT.PUSH);
		alignButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/center_align.gif"));
		alignButton.setData(WIDGET_DATA_KEY, HorizontalTextAlignEnum.CENTER);
		alignButton.addSelectionListener(pushButtonPressed);
		
		alignButton = new ToolItem(hAlignToolbar, SWT.PUSH);
		alignButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/right_align.gif"));
		alignButton.setData(WIDGET_DATA_KEY, HorizontalTextAlignEnum.RIGHT);
		alignButton.addSelectionListener(pushButtonPressed);
		
		alignButton = new ToolItem(hAlignToolbar, SWT.PUSH);
		alignButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/justified_align.gif"));
		alignButton.setData(WIDGET_DATA_KEY, HorizontalTextAlignEnum.JUSTIFIED);
		alignButton.addSelectionListener(pushButtonPressed);
		
		new ToolItem(hAlignToolbar, SWT.SEPARATOR);
		
		vAlignToolbar = new ToolBar(controlsArea, SWT.FLAT | SWT.WRAP);
		alignButton = new ToolItem(vAlignToolbar, SWT.PUSH);
		alignButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/formatting/edit-vertical-alignment-top.png"));
		alignButton.setData(WIDGET_DATA_KEY, VerticalTextAlignEnum.TOP);
		alignButton.addSelectionListener(pushButtonPressed);
		
		alignButton = new ToolItem(vAlignToolbar, SWT.PUSH);
		alignButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/formatting/edit-vertical-alignment-middle.png"));
		alignButton.setData(WIDGET_DATA_KEY, VerticalTextAlignEnum.MIDDLE);
		alignButton.addSelectionListener(pushButtonPressed);
		
		alignButton = new ToolItem(vAlignToolbar, SWT.PUSH);
		alignButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/formatting/edit-vertical-alignment.png"));
		alignButton.setData(WIDGET_DATA_KEY, VerticalTextAlignEnum.BOTTOM);
		alignButton.addSelectionListener(pushButtonPressed);
		
		setAllControlsData();

		return controlsArea;
	}

	
	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		//it need to create only one child for the passed parent so we create a sub toolbar
		ToolItem tiToolabrs = new ToolItem(parent,SWT.SEPARATOR);
		Composite toolItemContainer = new Composite(parent, SWT.NONE);
		RowLayout toolItemContainerLayout = new RowLayout();
		toolItemContainerLayout.marginTop = 0;
		toolItemContainerLayout.marginBottom = 0;
		toolItemContainerLayout.marginLeft = 0;
		toolItemContainerLayout.marginRight = 0;
		toolItemContainerLayout.spacing = 0;
		toolItemContainer.setLayout(toolItemContainerLayout);
		tiToolabrs.setControl(toolItemContainer);
		getToolItems().add(tiToolabrs);
		
		//create increment and decrtement buttons
		
		//ToolBar incrementToolbar = new ToolBar(toolItemContainer, SWT.FLAT | SWT.WRAP);
		//incrementButton = createFontSizeButton(true, incrementToolbar);
		//createFontSizeButton(false, incrementToolbar);
		
		//Italic and bold button
		boldToolbar = new ToolBar(toolItemContainer, SWT.FLAT | SWT.WRAP);
		bold = new ToolItem(boldToolbar, SWT.CHECK);
		bold.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/font-bold.gif"));		
		bold.setToolTipText("Bold");
		bold.setData(WIDGET_DATA_KEY, JRDesignStyle.PROPERTY_BOLD);
		bold.addSelectionListener(booleanButtonSelected);
		bold.setWidth(25);
		
		italicToolbar = new ToolBar(toolItemContainer, SWT.FLAT | SWT.WRAP);
		italic = new ToolItem(italicToolbar, SWT.CHECK);
		italic.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/font-italic.gif"));		
		italic.setToolTipText("Italic");
		italic.setData(WIDGET_DATA_KEY, JRDesignStyle.PROPERTY_ITALIC);
		italic.addSelectionListener(booleanButtonSelected);
		italic.setWidth(25);
		
		//Buttons to set the text alignment
		hAlignToolbar = new ToolBar(toolItemContainer, SWT.FLAT | SWT.WRAP);
		ToolItem alignLeftButton = new ToolItem(hAlignToolbar, SWT.PUSH);
		alignLeftButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/left_align.gif"));
		alignLeftButton.setData(WIDGET_DATA_KEY, HorizontalTextAlignEnum.LEFT);
		alignLeftButton.addSelectionListener(pushButtonPressed);
		
		ToolItem alignCenterButton = new ToolItem(hAlignToolbar, SWT.PUSH);
		alignCenterButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/center_align.gif"));
		alignCenterButton.setData(WIDGET_DATA_KEY, HorizontalTextAlignEnum.CENTER);
		alignCenterButton.addSelectionListener(pushButtonPressed);
		
		ToolItem alignRightButton = new ToolItem(hAlignToolbar, SWT.PUSH);
		alignRightButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/right_align.gif"));
		alignRightButton.setData(WIDGET_DATA_KEY, HorizontalTextAlignEnum.RIGHT);
		alignRightButton.addSelectionListener(pushButtonPressed);
		
		ToolItem alignJustifiedButton = new ToolItem(hAlignToolbar, SWT.PUSH);
		alignJustifiedButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/justified_align.gif"));
		alignJustifiedButton.setData(WIDGET_DATA_KEY, HorizontalTextAlignEnum.JUSTIFIED);
		alignJustifiedButton.addSelectionListener(pushButtonPressed);
		
		new ToolItem(hAlignToolbar, SWT.SEPARATOR);
		
		vAlignToolbar = new ToolBar(toolItemContainer, SWT.FLAT | SWT.WRAP);
		ToolItem alignTopButton = new ToolItem(vAlignToolbar, SWT.PUSH);
		alignTopButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/formatting/edit-vertical-alignment-top.png"));
		alignTopButton.setData(WIDGET_DATA_KEY, VerticalTextAlignEnum.TOP);
		alignTopButton.addSelectionListener(pushButtonPressed);
		
		ToolItem alignMiddleButton = new ToolItem(vAlignToolbar, SWT.PUSH);
		alignMiddleButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/formatting/edit-vertical-alignment-middle.png"));
		alignMiddleButton.setData(WIDGET_DATA_KEY, VerticalTextAlignEnum.MIDDLE);
		alignMiddleButton.addSelectionListener(pushButtonPressed);
		getToolItems().add(alignMiddleButton);
		
		ToolItem alignBottomButton = new ToolItem(vAlignToolbar, SWT.PUSH);
		alignBottomButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/formatting/edit-vertical-alignment.png"));
		alignBottomButton.setData(WIDGET_DATA_KEY, VerticalTextAlignEnum.BOTTOM);
		alignBottomButton.addSelectionListener(pushButtonPressed);
		
		tiToolabrs.setWidth(toolItemContainer.computeSize(SWT.DEFAULT, SWT.DEFAULT).x);
		
		setAllControlsData();
		
		return true;
	}

	
	/**
	 * Create a single button into the toolbar
	 * 
	 * @param increment true if the button should be used for increment, false otherwise
	 */
	/*private ToolItem createFontSizeButton(final boolean increment, ToolBar buttons){
		Image imageValue;
		String message;
		if (increment){
			imageValue = JaspersoftStudioPlugin.getInstance().getImage("/icons/resources/edit-size-up.png");
			message = MessageFormat.format(Messages.SPButon_Size_Increment, new Object[]{factor.toString()});
		} else {
			imageValue = JaspersoftStudioPlugin.getInstance().getImage("/icons/resources/edit-size-down.png");
			message = MessageFormat.format(Messages.SPButon_Size_Decrement, new Object[]{factor.toString()});
		}
		ToolItem button = new ToolItem(buttons, SWT.PUSH);
		button.setImage(imageValue);
		button.setToolTipText(message);		
		button.setData(WIDGET_DATA_KEY, JRDesignStyle.PROPERTY_FONT_SIZE);
		button.setWidth(25);
		button.addSelectionListener(fontSizeButtonSelect);
		return button;
	}*/

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
	 * Create the command to change the font size
	 * 
	 * @param increment true if you want to increment the font, false otherwise
	 */
	protected JSSCompoundCommand createIncremenrtsCommand(boolean increment, List<Object> models){
		JSSCompoundCommand changeSizeCommands = new JSSCompoundCommand(null);
		for (Object model : models){
			Object fontSize  = ((APropertyNode)model).getPropertyActualValue(JRBaseFont.PROPERTY_FONT_SIZE);
			Float newValue = 2.0f;
			if (fontSize != null && fontSize.toString().length()>0){
				newValue = Float.valueOf(fontSize.toString());
				Integer plus = null;
				if (increment) plus = Math.round((new Float(newValue) / 100)*factor)+1;
				else plus =  Math.round((new Float(newValue) / 100)*-factor)-1;
				if ((newValue+plus)>99) newValue = 99.0f;
				else if ((newValue+plus)>0) newValue += plus;

				Command c = createCommand(model, newValue, JRBaseFont.PROPERTY_FONT_SIZE );
				changeSizeCommands.setReferenceNodeIfNull(model);
				if (c != null) {
					changeSizeCommands.add(c);
				}
			}
		}
		return changeSizeCommands;
	}

	/**
	 * Initialize all the controls that show the state of the object with the value of the
	 * selected element
	 */
	protected void setAllControlsData(){
		if (boldToolbar == null || boldToolbar.isDisposed()) return;
		refreshing = true;
		List<Object> selection = getSelectionForType(MTextElement.class);
		if (selection.size() == 1){
			APropertyNode node = (APropertyNode)selection.get(0);
			
			italic.setSelection((Boolean) node.getPropertyActualValue(JRDesignStyle.PROPERTY_ITALIC));
			createContextualMenu(node, italicToolbar, JRDesignStyle.PROPERTY_ITALIC);
			
			bold.setSelection((Boolean) node.getPropertyActualValue(JRDesignStyle.PROPERTY_BOLD));
			createContextualMenu(node, boldToolbar, JRDesignStyle.PROPERTY_BOLD);
			
			createContextualMenu(node, vAlignToolbar, JRBaseStyle.PROPERTY_VERTICAL_TEXT_ALIGNMENT);
			createContextualMenu(node, hAlignToolbar, JRBaseStyle.PROPERTY_HORIZONTAL_TEXT_ALIGNMENT);
			
			if (showedNode != null) showedNode.getPropertyChangeSupport().removePropertyChangeListener(nodeChangeListener);
			showedNode = node;
			showedNode.getPropertyChangeSupport().addPropertyChangeListener(nodeChangeListener);
			
		} else {
			italic.setSelection(false);
			bold.setSelection(false);
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
		bold = null;
		italic = null;
		boldToolbar = null;
		factor = 10;
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
