/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.dialog;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.ExpressionContext.Visibility;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.properties.layout.StackLayout;
import com.jaspersoft.studio.property.infoList.ElementDescription;
import com.jaspersoft.studio.property.infoList.SelectableComposite;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.swt.events.ExpressionModifiedEvent;
import com.jaspersoft.studio.swt.events.ExpressionModifiedListener;
import com.jaspersoft.studio.swt.widgets.WTextExpression;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.PropertyEditorAdapter;
import com.jaspersoft.studio.widgets.framework.manager.WidgetFactory;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.TextPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.menu.IMenuProvider;
import com.jaspersoft.studio.widgets.framework.ui.menu.StandardContextualMenu;

import net.sf.jasperreports.components.items.ItemProperty;
import net.sf.jasperreports.components.items.StandardItemProperty;
import net.sf.jasperreports.eclipse.ui.util.PersistentLocationTitleAreaDialog;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

/**
 * Dialog that allows editing the information associated to a {@link ItemProperty} element.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class ItemPropertyDialog extends PersistentLocationTitleAreaDialog implements IExpressionContextSetter, IWItemProperty {

	private Composite dialogArea;
	private Text propertyName;
	private Button useExpressionCheckbox;
	private Control propertyValue;
	private WTextExpression propertyValueExpression;
	private ExpressionContext expContext;
	private StandardItemProperty itemProperty;
	private ADescriptor descriptor;
	private ItemPropertyDescription<?> ipDesc;
	private boolean refresh = false;
	private Composite editorContainer;
	private StackLayout stackLayout;

	private IPropertyEditor internalEditor = new PropertyEditorAdapter() {

		@Override
		public JRExpression getPropertyValueExpression(String propertyName) {
			return getExpressionValue();
		}

		@Override
		public String getPropertyValue(String propertyName) {
			return getStaticValue();
		}
	};

	public ItemPropertyDialog(Shell parentShell, ItemProperty itemProperty, ADescriptor descriptor) {
		super(parentShell);
		setSaveSettings(false);
		setDefaultSize(450, 400);
		this.itemProperty = (StandardItemProperty) itemProperty;
		if (this.itemProperty == null)
			this.itemProperty = new StandardItemProperty("", "", null); //$NON-NLS-1$ //$NON-NLS-2$
		this.descriptor = descriptor;
		ItemPropertyDescription<?> ipDesc = descriptor.getDescription(itemProperty.getName());
		if (ipDesc == null)
			this.ipDesc = new TextPropertyDescription<String>(itemProperty.getName(), "", false);
		else
			this.ipDesc = ipDesc.clone();
	}

	@Override
	public boolean close() {
		descriptor.setOldItemProperty(null);
		return super.close();
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle(Messages.ItemPropertyDialog_EditItemProperty);
		setMessage("Define the value of the new property");
		dialogArea = (Composite) super.createDialogArea(parent);
		GridLayout layout = new GridLayout(1, false);
		layout.marginWidth = 10;
		layout.marginHeight = 10;
		dialogArea.setLayout(layout);

		Label lblPropertyName = new Label(dialogArea, SWT.NONE);
		lblPropertyName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		lblPropertyName.setText(Messages.ItemPropertyDialog_PropertyName);
		propertyName = new Text(dialogArea, SWT.BORDER);
		propertyName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		useExpressionCheckbox = new Button(dialogArea, SWT.CHECK);
		useExpressionCheckbox.setText(Messages.ItemPropertyDialog_UseExpression);
		useExpressionCheckbox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		Label lblPropertyValue = new Label(dialogArea, SWT.NONE);
		lblPropertyValue.setText(Messages.ItemPropertyDialog_PropertyValue);
		lblPropertyValue.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		final Composite cmp = new Composite(dialogArea, SWT.NONE);
		stackLayout = new StackLayout();
		cmp.setLayout(stackLayout);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 70;
		cmp.setLayoutData(gd);

		propertyValueExpression = new WTextExpression(cmp, SWT.NONE);
		propertyValueExpression.setExpressionContext(this.expContext);
		if (descriptor.getItemData() != null && descriptor.getItemData().getDataset() == null)
			expContext.setVisibilities(EnumSet.noneOf(Visibility.class));

		
		editorContainer = new Composite(cmp, SWT.NONE);
		editorContainer.setLayout(WidgetFactory.getNoPadLayout(1));
		editorContainer.setLayoutData(new GridData(GridData.FILL_BOTH));
		propertyValue = ipDesc.createControl(this, editorContainer);
		propertyValue.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		List<ElementDescription> hints = getPropertiesInformation();
		if (!hints.isEmpty()){
			final SelectableComposite infoPanel = new SelectableComposite(dialogArea);
			infoPanel.setItems(hints);
			GridData infoGD = new GridData(SWT.FILL, SWT.FILL, true, true);
			infoGD.heightHint = 200;
			infoGD.verticalIndent = 5;
			infoPanel.setLayoutData(infoGD);
			infoPanel.SetDoubleClickListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					String newname = infoPanel.getSelectedElement().getName();
					ItemPropertyDescription<?> ipDescNew = descriptor.getDescription(newname);
					if (ipDescNew != null) {
						itemProperty.setName(newname);
						itemProperty.setValue(ipDescNew.getDefaultValueString());
						itemProperty.setValueExpression(null);
	
						propertyValue.dispose();
	
						ItemPropertyDescription<?> ipDesc = descriptor.getDescription(itemProperty.getName());
						if (ipDesc == null) {
							ItemPropertyDialog.this.ipDesc = new TextPropertyDescription<String>(itemProperty.getName(), "", false);
						} else {
							ItemPropertyDialog.this.ipDesc = ipDesc.clone();
						}
						propertyValue = ipDesc.createControl(ItemPropertyDialog.this, editorContainer);
						editorContainer.layout();
	
						setValue(itemProperty.getValue(), itemProperty.getValueExpression());
					}
				}
			});
		}

		setValue(getStaticValue(), getExpressionValue());
		addListeners();

		return dialogArea;
	}

	private List<ElementDescription> getPropertiesInformation() {
		List<ElementDescription> descriptions = new ArrayList<ElementDescription>();
		for (ItemPropertyDescription<?> ipd : descriptor.getItemPropertyDescriptors())
			descriptions.add(new ElementDescription(ipd.getName(), ipd.getDescription(), false));
		return descriptions;
	}

	private void addListeners() {
		propertyName.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if (refresh)
					return;
				Point p = propertyName.getSelection();

				itemProperty.setName(propertyName.getText());
				
				propertyValue.dispose();

				ItemPropertyDescription<?> ipDesc = descriptor.getDescription(itemProperty.getName());
				if (ipDesc == null) {
					ItemPropertyDialog.this.ipDesc = new TextPropertyDescription<String>(itemProperty.getName(), "", false);
				} else {
					ItemPropertyDialog.this.ipDesc = ipDesc.clone();
				}
				propertyValue = ItemPropertyDialog.this.ipDesc.createControl(ItemPropertyDialog.this, editorContainer);
				propertyValue.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
				editorContainer.layout();

				setValue(itemProperty.getValue(), itemProperty.getValueExpression());

				propertyName.setSelection(p);
			}
		});
		propertyValueExpression.addModifyListener(new ExpressionModifiedListener() {
			@Override
			public void expressionModified(ExpressionModifiedEvent event) {
				itemProperty.setValueExpression(event.modifiedExpression);
				validateDialog();
			}
		});

		useExpressionCheckbox.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (isRefresh())
					return;
				if (useExpressionCheckbox.getSelection()) {
					itemProperty.setValueExpression(new JRDesignExpression());
					itemProperty.setValue(null);
				} else {
					itemProperty.setValueExpression(null);
				}
				setValue(itemProperty.getValue(), itemProperty.getValueExpression());
				validateDialog();
				dialogArea.layout();
			}
		});
	}

	private void validateDialog() {
		Button ok = getButton(IDialogConstants.OK_ID);
		String str = null;
		try {
			descriptor.validateItem(itemProperty);
		} catch (Exception e) {
			str = e.getMessage();
		}
		if (Misc.isNullOrEmpty(str)){
			setErrorMessage(null);
			if (ok != null){
				ok.setEnabled(true);
			}
		} else {
			setErrorMessage(str);
			if (ok != null){
				ok.setEnabled(false);
			}
		}
		
	}

	@Override
	protected void setShellStyle(int newShellStyle) {
		super.setShellStyle(newShellStyle | SWT.RESIZE | SWT.PRIMARY_MODAL);
		setBlockOnOpen(true);
	}

	@Override
	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
	}

	public StandardItemProperty getValue() {
		return this.itemProperty;
	}

	@Override
	public void setRefresh(boolean refreshing) {
		this.refresh = refreshing;
	}

	@Override
	public boolean isRefresh() {
		return refresh;
	}

	@Override
	public void setValue(String staticValue, JRExpression expressionValue) {
		if (isRefresh())
			return;
		setRefresh(true);
		try {
			propertyName.setText(getPropertyName());
			for (ItemPropertyDescription<?> ipd : descriptor.getItemPropertyDescriptors()) {
				if (ipd.getName().equals(itemProperty.getName()) && ipd.isMandatory()) {
					if (propertyName.isEnabled())
						propertyName.setEnabled(false);
					break;
				}
			}
			boolean isExpression = getExpressionValue() != null;
			if (isExpression) {
				itemProperty.setValue(null);
				itemProperty.setValueExpression(expressionValue);
				stackLayout.setTopControl(propertyValueExpression);
			} else {
				itemProperty.setValue(staticValue);
				itemProperty.setValueExpression(null);
				stackLayout.setTopControl(editorContainer);
			}
			updateWidget();
		} finally {
			setRefresh(false);
		}
	}

	@Override
	public Control getControl() {
		return propertyValue;
	}

	@Override
	public String getStaticValue() {
		return itemProperty.getValue();
	}

	@Override
	public JRExpression getExpressionValue() {
		return itemProperty.getValueExpression();
	}

	@Override
	public String getPropertyName() {
		return ipDesc.getName();
	}

	@Override
	public boolean isExpressionMode() {
		return false;
	}

	@Override
	public IMenuProvider getContextualMenuProvider() {
		return StandardContextualMenu.INSTANCE;
	}

	@Override
	public void updateWidget() {
		setRefresh(true);
		try {
			boolean isExpression = getExpressionValue() != null;
			if (isExpression){
				propertyValueExpression.setExpression((JRDesignExpression)getExpressionValue());
			} else {
				ipDesc.update(propertyValue, this);	
			}
			if (useExpressionCheckbox.getSelection() != isExpression){
				useExpressionCheckbox.setSelection(isExpression);
			}
			validateDialog();
		} finally {
			setRefresh(false);
		}
	}

	@Override
	public IPropertyEditor getPropertyEditor() {
		return internalEditor;
	}

	@Override
	public Object getFallbackValue() {
		return ipDesc.getFallbackValue();
	}
}
