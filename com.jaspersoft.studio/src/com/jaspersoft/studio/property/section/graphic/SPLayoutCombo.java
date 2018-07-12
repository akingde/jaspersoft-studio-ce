/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.graphic;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.layout.ChangeLayoutCommand;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutCommand;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.IContainerLayout;
import com.jaspersoft.studio.model.IGraphicElementContainer;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;

import net.sf.jasperreports.eclipse.ui.JSSTableCombo;
import net.sf.jasperreports.eclipse.ui.ValueChangedEvent;
import net.sf.jasperreports.eclipse.ui.ValueChangedListener;
import net.sf.jasperreports.engine.JRCommonElement;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.base.JRBaseElement;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * Layout combo used the select the layout strategy of a container
 *
 */
public class SPLayoutCombo extends ASPropertyWidget<IPropertyDescriptor>  {

	private APropertyNode pnode;

	private int index = 0;

	private ILayout[] layouts;
	
	private boolean refreshing = false;
	
	private JSSTableCombo combo;

	private String longestName = null;
	
	public SPLayoutCombo(Composite parent, LayoutSection section, IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	protected void handlePropertyChange() {
		if (refreshing) return;
		int ind = combo.getSelectionIndex();
		if (ind == index && index != 1)
			return;
		ILayout selectedLayout = (ILayout)combo.getTable().getItem(ind).getData();
		CommandStack cs = section.getEditDomain().getCommandStack();
		ChangeLayoutCommand changeLayoutCommand = new ChangeLayoutCommand(pnode, selectedLayout);
		JSSCompoundCommand container = new JSSCompoundCommand(pnode);
		container.add(changeLayoutCommand);
		Object destValue = pnode.getValue();
		if (pnode instanceof IGroupElement)
			destValue = ((IGroupElement) pnode).getJRElementGroup();
		if (destValue instanceof JRElementGroup) {
			Dimension d = new Dimension(0, 0);
			if (pnode instanceof IGraphicElementContainer) {
				// d = ((IGraphicElementContainer) pnode).getSize();
				d = LayoutManager.getPaddedSize((IGraphicElementContainer) pnode);
			}
			if (destValue instanceof JRCommonElement) {
				// JRCommonElement jce = (JRCommonElement) destValue;
				// Commented for back-compatibility in 3.6.
				// Replaced with the following line.
				// d.setSize(jce.getWidth(), jce.getHeight());
				// d.setSize(new Dimension(jce.getWidth(), jce.getHeight()));
				d = LayoutManager.getPaddedSize((JRCommonElement) destValue);
			}
			if (destValue instanceof JRDesignBand) {
				JasperDesign jDesign = pnode.getJasperDesign();
				int w = jDesign.getPageWidth() - jDesign.getLeftMargin() - jDesign.getRightMargin();
				// Commented for back-compatibility in 3.6.
				// Replaced with the following line.
				// d.setSize(w, ((JRDesignBand) destValue).getHeight());
				d.setSize(new Dimension(w, ((JRDesignBand) destValue).getHeight()));
			}
			container.add(new LayoutCommand(pnode.getJasperDesign(), (JRElementGroup) destValue, selectedLayout, d));
		}
		cs.execute(container);
	}

	@Override
	public void setData(APropertyNode pnode, Object b) {
		refreshing = true;
		try{
			this.pnode = pnode;
			index = -1;
			Object obj = pnode.getValue();
			combo.setEnabled(true);
			String layoutName = null;
			if (b instanceof JRPropertiesMap) {
				layoutName = getLayoutName(null, (JRPropertiesMap) b);
			} else if (obj != null && obj instanceof JRPropertiesHolder) {
				layoutName = getLayoutName((JRPropertiesHolder) obj, null);
			} else if (obj instanceof JRBaseElement) {
				JasperDesign jDesign = pnode.getJasperDesign();
				layoutName = getLayoutName(jDesign, ((JRBaseElement) obj).getUUID().toString());
			}
			if (layoutName != null){
				index = getIndex(layoutName, combo.getTable());
				if (index == -1){
					ILayout resolvedLayout = resoveLayout(layoutName);
					if (resolvedLayout != null){
						combo.setText(resolvedLayout.getName());
						return;
					} else {
						index = 0;
						combo.select(index);
					}
				} else {
					combo.select(index);
				}
			} else  {
				if (pnode != null && pnode instanceof IContainerLayout) {
					IContainerLayout layoutContainer = (IContainerLayout) pnode;
					ILayout defaultLayout = layoutContainer.getDefaultLayout();
					if (defaultLayout != null) {
						index = getIndex(defaultLayout.getClass().getName(), combo.getTable());
					}
				}
				if (index == -1){
					index = 0;
				}
				combo.select(index);
			}
		} finally {
			refreshing = false;
		}
	}

	private String getLayoutName(JRPropertiesHolder pholder, String uuid) {
		return getLayoutName(uuid, pholder.getPropertiesMap());
	}

	private String getLayoutName(String uuid, JRPropertiesMap pmap) {
		String key = ILayout.KEY;
		if (uuid != null)
			key += "." + uuid; //$NON-NLS-1$
		String str = pmap.getProperty(key);
		return str;
	}

	/**
	 * Get the layout index in the combo from its classname
	 * 
	 * @param the
	 *          classname of the layout
	 * @return the index of the layout in the combo or -1 if it can't be found
	 */
	private int getIndex(String layoutName, Control combo) {
		if (layoutName != null) {
			ILayout[] visibleLayouts = (ILayout[])combo.getData();
			for (int i = 0; i < visibleLayouts.length; i++) {
				if (visibleLayouts[i].getClass().getName().equals(layoutName)) {
					return i;
				}
			}
		}
		return -1;
	}
	
	private ILayout resoveLayout(String layoutName) {
		if (layoutName != null) {
			for (int i = 0; i < layouts.length; i++) {
				if (layouts[i].getClass().getName().equals(layoutName)) {
					return layouts[i];
				}
			}
		}
		return null;
	}


	@Override
	protected void createComponent(Composite parent) {
		layouts = LayoutManager.getAllLayouts();
		Composite container = section.getWidgetFactory().createComposite(parent);
		container.setLayout(new GridLayout(1, false));
		combo = new JSSTableCombo(container, SWT.READ_ONLY){
			protected void setTableData(Table table) {
				refreshTableItems(table);
			};
			
			@Override
			protected void dropDown(boolean drop) {
				//if opening force the refresh of the items
				if (drop) refreshItems();
				super.dropDown(drop);
			}
			
			@Override
			protected String getLongestText() {
				return computeLongestName();
			}
		};

		combo.addModifyListener(new ValueChangedListener() {
			
			@Override
			public void valueChanged(ValueChangedEvent e) {
				handlePropertyChange();
			}
		});

		combo.setToolTipText(pDescriptor.getDescription());
	}

	@Override
	public Control getControl() {
		return combo;
	}
	
	private void refreshTableItems(Table table){
		table.clearAll();
		List<ILayout> visibleLayouts = new ArrayList<ILayout>();
		for (int i = 0; i < layouts.length; i++){
			if (layouts[i].isSelectable(section.getElement())){
				visibleLayouts.add(layouts[i]);
			}
		}
		for(ILayout item : visibleLayouts){
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(0, item.getName());
			tableItem.setData(item);
		}
		table.setData(visibleLayouts.toArray(new ILayout[visibleLayouts.size()]));
	}
	
	private String computeLongestName() {
		if (longestName == null) {
			longestName = "";
			for(ILayout item : layouts){
				String currentText = item.getName();
				if (longestName.length() < currentText.length()) {
					longestName = currentText;
				}
			}
		}
		return longestName;
	}
	
}
