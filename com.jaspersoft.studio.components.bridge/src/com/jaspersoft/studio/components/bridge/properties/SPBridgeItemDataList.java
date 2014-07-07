/*******************************************************************************
 * Copyright (C) 2010 - 2014 Jaspersoft Corporation. All rights reserved.
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
 * 	Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.bridge.properties;

import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.jasperreports.components.bridge.BridgeItemData;
import com.jaspersoft.jasperreports.components.bridge.design.BridgeDesignComponent;
import com.jaspersoft.studio.components.bridge.messages.Messages;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.swt.widgets.NumberedLabelProvider;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * Widget to modify the {@link BridgeDesignComponent#PROPERTY_ITEM_DATA}
 * property in the dedicated Property section.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class SPBridgeItemDataList extends ASPropertyWidget {

	private TableViewer itemDataTV;
	private Button btnAddItemData;
	private Button btnModifyItemData;
	private Button btnRemoveItemData;
	private Group itemDataGrp;
	private List<BridgeItemData> itemDataElements;
	private NumberedLabelProvider lblProv;
	
	public SPBridgeItemDataList(Composite parent, AbstractSection section,
			IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	protected void createComponent(Composite parent) {
		itemDataGrp = new Group(parent, SWT.NONE);
		itemDataGrp.setLayout(new GridLayout(2,false));
		itemDataGrp.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		
		itemDataTV = createItemDataTable(itemDataGrp);
		itemDataTV.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				modifyItemDataBtnPressed();
			}
		});
		
		btnAddItemData = new Button(itemDataGrp, SWT.PUSH);
		btnAddItemData.setText(Messages.SPBridgeItemDataList_Add);
		btnAddItemData.setLayoutData(new GridData(SWT.FILL,SWT.TOP,false,false));
		btnAddItemData.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addNewItemDataBtnPressed();
			}
		});

		btnModifyItemData = new Button(itemDataGrp, SWT.PUSH);
		btnModifyItemData.setText(Messages.SPBridgeItemDataList_Edit);
		btnModifyItemData.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		btnModifyItemData.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modifyItemDataBtnPressed();
			}
		});
		
		btnRemoveItemData = new Button(itemDataGrp, SWT.PUSH);
		btnRemoveItemData.setText(Messages.SPBridgeItemDataList_Remove);
		btnRemoveItemData.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		btnRemoveItemData.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				removeItemDataBtnPressed();
			}
		});

	}

	private TableViewer createItemDataTable(Group parent) {
		TableViewer itemDataTV = new TableViewer(parent, SWT.BORDER | SWT.V_SCROLL | SWT.SINGLE);
		itemDataTV.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 3));
		lblProv = new NumberedLabelProvider(Messages.SPBridgeItemDataList_LblProviderPrefix);
		itemDataTV.setLabelProvider(lblProv);
		itemDataTV.setContentProvider(new ArrayContentProvider());
		return itemDataTV;
	}
	
	private void addNewItemDataBtnPressed() {
		BridgeItemDataDialog dialog = new BridgeItemDataDialog(UIUtils.getShell(), null, section.getElement().getJasperConfiguration());
		dialog.setDefaultExpressionContext(getExpressionContext());
		if(dialog.open()==Window.OK) {
			BridgeItemData bridgeItemData = dialog.getBridgeItemData();
			itemDataElements.add(bridgeItemData);
			section.changeProperty(BridgeDesignComponent.PROPERTY_ITEM_DATA, itemDataElements);
		}
	}
	
	private void modifyItemDataBtnPressed() {
		BridgeItemData i = getCurrentSelectedItemData();
		if(i != null) {
			BridgeItemData clonedItemData = (BridgeItemData) i.clone();
			BridgeItemDataDialog dialog = new BridgeItemDataDialog(UIUtils.getShell(), clonedItemData, section.getElement().getJasperConfiguration());
			dialog.setDefaultExpressionContext(getExpressionContext());
			if(dialog.open()==Window.OK) {
				BridgeItemData bridgeItemData = dialog.getBridgeItemData();
				int indexOf = itemDataElements.indexOf(i);
				itemDataElements.remove(indexOf);
				itemDataElements.add(indexOf,bridgeItemData);
				section.changeProperty(BridgeDesignComponent.PROPERTY_ITEM_DATA, itemDataElements);
			}
		}
	}
	
	private void removeItemDataBtnPressed() {
		BridgeItemData i = getCurrentSelectedItemData();
		if (i!=null) {
			itemDataElements.remove(i);
			section.changeProperty(BridgeDesignComponent.PROPERTY_ITEM_DATA, itemDataElements);
		}		
	}
	
	private BridgeItemData getCurrentSelectedItemData() {
		Object selEl = ((IStructuredSelection) itemDataTV.getSelection()).getFirstElement();
		if(selEl instanceof BridgeItemData) {
			return (BridgeItemData) selEl;
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void setData(APropertyNode pnode, Object value) {
		itemDataElements = (List<BridgeItemData>) value;
		itemDataTV.setInput(itemDataElements);
		lblProv.resetIndex();
	}

	@Override
	public Control getControl() {
		return itemDataGrp;
	}
	
	private ExpressionContext getExpressionContext() {
		return ModelUtils.getElementExpressionContext(null, section.getElement());
	}

}
