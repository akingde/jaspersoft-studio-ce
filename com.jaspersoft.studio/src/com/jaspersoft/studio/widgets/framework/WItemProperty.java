/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.ExpressionEditorSupportUtil;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.property.descriptor.expression.dialog.JRExpressionEditor;
import com.jaspersoft.studio.property.itemproperty.desc.ItemPropertyBaseLabelProvider;
import com.jaspersoft.studio.property.itemproperty.event.ItemPropertyModifiedEvent;
import com.jaspersoft.studio.property.itemproperty.event.ItemPropertyModifiedListener;
import com.jaspersoft.studio.swt.events.ExpressionModifiedListener;
import com.jaspersoft.studio.utils.UIUtil;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.dialog.ItemPropertyElementDialog;
import com.jaspersoft.studio.widgets.framework.ui.menu.IMenuProvider;
import com.jaspersoft.studio.widgets.framework.ui.menu.StandardContextualMenu;
import com.jaspersoft.studio.widgets.framework.ui.providers.BaseLabelProvider;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

/**
 * 
 */
public class WItemProperty extends Composite implements IExpressionContextSetter, IWItemProperty {

	/** Number of lines for the text expression widget */
	public static final int TEXT_LINE_NUMBERS = 3;

	public static final String BUTTON_ICON_PATH = "icons/resources/expressionedit-16.png"; //$NON-NLS-1$
	
	private int customTextLinesNumber = -1;
	
	private ExpressionContext expContext;

	// Widgets
	private Control textExpression;
	
	private Button btnEditExpression;

	private BaseLabelProvider lprovider = null;
	
	private Composite controlsCmp;
	
	private Label expressionEditLabel;
	
	private boolean isRefresh = false;
	
	private boolean isUpdating = false;

	private ItemPropertyDescription<?> ipDesc;
	
	/**
	 * Expression modify listeners
	 */
	private List<ItemPropertyModifiedListener> listeners = new ArrayList<ItemPropertyModifiedListener>();
	
	/**
	 * Create the widget 
	 * 
	 * @param parent the parent of the widget
	 * @param style the style of the main composite for this element
	 * @param linesNum the number of line of the expression widget
	 * @param widgetDescriptor the descriptor of the value control
	 */
	public WItemProperty(Composite parent, int style, int linesNum, ItemPropertyDescription<?> widgetDescriptor) {
		super(parent, style);
		this.ipDesc = widgetDescriptor;
		this.customTextLinesNumber = linesNum;
		GridLayout mainLayout = new GridLayout(2, false); 
		mainLayout.marginHeight = 0;
		mainLayout.marginWidth = 0;
		mainLayout.horizontalSpacing = 0;
		mainLayout.verticalSpacing = 0;
		setLayout(mainLayout);

		controlsCmp = new Composite(this, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 5;
		layout.verticalSpacing = 0;
		controlsCmp.setLayout(layout);

		//Create the expression label
		expressionEditLabel = new Label(controlsCmp, SWT.NONE);
		GridData gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_CENTER);
		gd.widthHint = 24;
		gd.heightHint = 24;
		expressionEditLabel.setLayoutData(gd);
		expressionEditLabel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseDown(MouseEvent e) {
				if (!ExpressionEditorSupportUtil.isExpressionEditorDialogOpen()) {
					JRExpressionEditor wizard = new JRExpressionEditor();
					wizard.setValue((JRDesignExpression)getEditor().getPropertyValueExpression(ipDesc.getName()));
					wizard.setExpressionContext(expContext);
					WizardDialog dialog = ExpressionEditorSupportUtil.getExpressionEditorWizardDialog(Display.getDefault().getActiveShell(), wizard);
					if (dialog.open() == Dialog.OK) {
						JRDesignExpression exprTmp = wizard.getValue();
						setValue(null, exprTmp);
					}
				}
			}

		});

		//Create the simple control
		textExpression = ipDesc.createControl(this, controlsCmp);

		//Create the edit expression button
		btnEditExpression = new Button(this, SWT.FLAT);
		btnEditExpression.setImage(JaspersoftStudioPlugin.getInstance().getImage(BUTTON_ICON_PATH));
		btnEditExpression.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				handleEditButton();
			}

		});

		configureWidgetsLayoutData();
		if (widgetDescriptor != null) {
			String tt = widgetDescriptor.getToolTip();
			expressionEditLabel.setToolTipText(tt);
			textExpression.setToolTipText(widgetDescriptor.getToolTip());
			btnEditExpression.setToolTipText(widgetDescriptor.getToolTip());
		}
	}

	@Override
	public void setRefresh(boolean refreshing) {
		this.isRefresh = refreshing;
	}

	/**
	 * The section is refreshing when a setValue or an update method are called
	 */
	@Override
	public boolean isRefresh() {
		return isRefresh || isUpdating;
	}

	/**
	 * Sets the layout data information for the custom widget controls.
	 */
	private void configureWidgetsLayoutData() {
		int heightHint = Math.max(UIUtil.getCharHeight(textExpression), 24);
		// Standard configuration
		GridData controlsCmpLayoutData = new GridData(GridData.FILL_HORIZONTAL);
		controlsCmpLayoutData.heightHint = heightHint;
		controlsCmp.setLayoutData(controlsCmpLayoutData);
		
		addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				layout(true, true);
			}
		});
	}

	/**
	 * Set a value both on the widget and on the element
	 * 
	 * @param staticValue the new static value
	 * @param expressionValue the new expression value
	 */
	public void setValue(String staticValue, JRExpression expressionValue) {
		setRefresh(true);
		try {
			getEditor().createUpdateProperty(ipDesc.getName(), staticValue, expressionValue);
			updateWidget();
			
			// Notifies the listeners of the new expression
			fireModifyEvent(staticValue, expressionValue);
		} finally {
			setRefresh(false);
		}
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		this.textExpression.setEnabled(enabled);
		this.btnEditExpression.setEnabled(enabled);
	}

	/**
	 * Gets the currently set number of lines for the widget. This value is used for the calculation of the text
	 * expression height hint. Default value is {@value #TEXT_LINE_NUMBERS}.
	 * 
	 * @return the number of lines
	 */
	protected int getTextLinesNumber() {
		if (customTextLinesNumber > 0)
			return customTextLinesNumber;
		return TEXT_LINE_NUMBERS;
	}

	/**
	 * Return the current label provider
	 * 
	 * @return a not null label provider, if no one is set it return a defaul
	 * {@link ItemPropertyBaseLabelProvider}
	 */
	public BaseLabelProvider getLabelProvider() {
		if (lprovider == null){
			return BaseLabelProvider.INSTANCE;
		}
		return lprovider;
	}
	
	/**
	 * Set the label provider for the element
	 * 
	 * @param lprovider the new label provider, it can be null
	 */
	public void setLabelProvider(BaseLabelProvider lprovider){
		this.lprovider = lprovider;
	}

	/**
	 * Set the expression context for the expression editor
	 */
	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
	}

	/**
	 * Return the expression context
	 * 
	 * @return the expression context currently set, <code>null</code> if none
	 */
	public ExpressionContext getExpressionContext() {
		return this.expContext;
	}

	/**
	 * Adds a new listener that will be notified of any expression change/notification.
	 * 
	 * @param ml
	 *          the new {@link ExpressionModifiedListener} to add
	 */
	public void addModifyListener(ItemPropertyModifiedListener ml) {
		listeners.add(ml);
	}

	/**
	 * Removes an {@link ExpressionModifiedListener} instance.
	 * 
	 * @param ml
	 *          the {@link ExpressionModifiedListener} instance to be removed
	 */
	public void removeModifyListener(ItemPropertyModifiedListener ml) {
		listeners.remove(ml);
	}

	/**
	 * 	Return the control widget
	 */
	public Control getControl() {
		return textExpression;
	}

	/**
	 * Notifies the listeners of the expression change.
	 */
	private void fireModifyEvent(String staticValue, JRExpression expressionValue) {
		ItemPropertyModifiedEvent event = new ItemPropertyModifiedEvent(this);
		event.staticValue = staticValue;
		event.expressionValue = expressionValue;
		event.propertyName = ipDesc.getName();
		for (ItemPropertyModifiedListener ml : listeners)
			ml.itemModified(event);
	}

	@Override
	public void dispose() {
		// Remove modify listeners
		Object[] listenersArray = listeners.toArray();
		for (Object l : listenersArray) {
			removeModifyListener((ItemPropertyModifiedListener) l);
		}
		listeners.clear();
		listeners = null;
		super.dispose();
	}

	/**
	 * Open the dialog to switch between expression and static value
	 */
	private void handleEditButton() {
		String staticValue = getEditor().getPropertyValue(ipDesc.getName());
		JRExpression expressionValue = getEditor().getPropertyValueExpression(ipDesc.getName());
		ItemPropertyElementDialog dialog = new ItemPropertyElementDialog(UIUtils.getShell(), staticValue, expressionValue, ipDesc);
		dialog.setExpressionContext(expContext);
		if (dialog.open() == Dialog.OK) {
			setValue(dialog.getStaticValue(), dialog.getExpressionValue());
		}
	}

	/**
	 * Return the property editor associated with the internal widget descriptor
	 * 
	 * @return a not null property editor
	 */
	public IPropertyEditor getEditor(){
		return ipDesc.getPropertyEditor();
	}

	@Override
	public String getStaticValue() {
		return getEditor().getPropertyValue(ipDesc.getName());
	}

	@Override
	public JRExpression getExpressionValue() {
		return getEditor().getPropertyValueExpression(ipDesc.getName());
	}

	@Override
	public String getPropertyName() {
		return ipDesc.getName();
	}

	/**
	 * Return if the current property is in expression mode, by default a property 
	 * is in expression mode if there is an expression defined for it. It can be
	 * overridden to provide a different behavior
	 */
	@Override
	public boolean isExpressionMode() {
		return getEditor().getPropertyValueExpression(ipDesc.getName()) != null;
	}

	/**
	 * Return an instance of the standard menu provider, that handle the action "set to default"
	 * and reset to null"
	 */
	@Override
	public IMenuProvider getContextualMenuProvider() {
		return StandardContextualMenu.INSTANCE;
	}

	/**
	 * Update the widget, avoid to re-trigger the set value trough the isUpdating flag
	 */
	@Override
	public void updateWidget() {
		isUpdating = true;
		try{
			ipDesc.update(textExpression, this);
			//show or hide the expression label
			if (isExpressionMode()){
				expressionEditLabel.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/functions_icon.png"));
				expressionEditLabel.setVisible(true);
				((GridData)expressionEditLabel.getLayoutData()).exclude = false;
				layout(true, true);
			} else {
				expressionEditLabel.setImage(null);
				expressionEditLabel.setVisible(false);
				((GridData)expressionEditLabel.getLayoutData()).exclude = true;
				layout(true, true);
			}
		} finally {
			isUpdating = false;
		}
	}
}
