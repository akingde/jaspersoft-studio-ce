/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.section.graphic;

import net.sf.jasperreports.engine.JRCommonElement;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.base.JRBaseElement;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutCommand;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.IContainerLayout;
import com.jaspersoft.studio.model.IGraphicElementContainer;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.property.section.widgets.SPReadCombo;

/**
 * Layout combo used the select the layout strategy of a container
 *
 */
public class SPLayoutCombo extends SPReadCombo<IPropertyDescriptor> {
	
	private APropertyNode pnode;
	
	private int index = 0;

	private ILayout[] layouts;
	
	public SPLayoutCombo(Composite parent, LayoutSection section, IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	protected void handlePropertyChange() {
		int ind = combo.getSelectionIndex();
		if (ind == index)
			return;
		if (pnode.getValue() instanceof JRPropertiesHolder) {
			JRPropertiesMap pmap = (JRPropertiesMap) pnode.getPropertyValue(MGraphicElement.PROPERTY_MAP);
			pmap = (JRPropertiesMap) pmap.clone();
			pmap.setProperty(ILayout.KEY, layouts[ind].getClass().getName());
			section.changeProperty(MGraphicElement.PROPERTY_MAP, pmap);
		} else if (pnode.getValue() instanceof JRBaseElement) {
			String uuid = ((JRBaseElement) pnode.getValue()).getUUID().toString();
			INode n = pnode.getRoot();
			if (n != null && n instanceof MReport) {
				MReport mrep = (MReport) n;
				JRPropertiesMap pmap = (JRPropertiesMap) mrep.getPropertyValue(MGraphicElement.PROPERTY_MAP);
				pmap = (JRPropertiesMap) pmap.clone();
				pmap.setProperty(ILayout.KEY + "." + uuid, layouts[ind].getClass().getName()); //$NON-NLS-1$
				section.changePropertyOn(MGraphicElement.PROPERTY_MAP, pmap, mrep);
			}
		}
		CommandStack cs = section.getEditDomain().getCommandStack();
		Object destValue = pnode.getValue();
		if (pnode instanceof IGroupElement)
			destValue = ((IGroupElement) pnode).getJRElementGroup();
		if (destValue instanceof JRElementGroup) {
			Dimension d = new Dimension(0, 0);
			if (pnode instanceof IGraphicElementContainer){
				//d = ((IGraphicElementContainer) pnode).getSize();
				d = LayoutManager.getPaddedSize((IGraphicElementContainer)pnode);
			}if (destValue instanceof JRCommonElement) {
				//JRCommonElement jce = (JRCommonElement) destValue;
				// Commented for back-compatibility in 3.6.
				// Replaced with the following line.
				// d.setSize(jce.getWidth(), jce.getHeight());
				//d.setSize(new Dimension(jce.getWidth(), jce.getHeight()));
				d = LayoutManager.getPaddedSize((JRCommonElement)destValue);
			}
			if (destValue instanceof JRDesignBand) {
				JasperDesign jDesign = pnode.getJasperDesign();
				int w = jDesign.getPageWidth() - jDesign.getLeftMargin() - jDesign.getRightMargin();
				// Commented for back-compatibility in 3.6.
				// Replaced with the following line.
				// d.setSize(w, ((JRDesignBand) destValue).getHeight());
				d.setSize(new Dimension(w, ((JRDesignBand) destValue).getHeight()));
			}
			cs.execute(new LayoutCommand((JRElementGroup) destValue, layouts[ind], d));
		}
	}

	@Override
	public void setData(APropertyNode pnode, Object b) {
		this.pnode = pnode;
		index = -1;
		Object obj = pnode.getValue();
		if (b instanceof JRPropertiesMap) {
			index = getIndex(null, (JRPropertiesMap) b);
		} else if (obj != null && obj instanceof JRPropertiesHolder) {
			index = getIndex((JRPropertiesHolder) obj, null);
		} else if (obj instanceof JRBaseElement) {
			JasperDesign jDesign = pnode.getJasperDesign();
			index = getIndex(jDesign, ((JRBaseElement) obj).getUUID().toString());
		}
		if (index == -1){
			if (pnode != null && pnode instanceof IContainerLayout){
				IContainerLayout layoutContainer = (IContainerLayout)pnode;
				ILayout defaultLayout = layoutContainer.getDefaultLayout();
				if (defaultLayout != null){
					index = getIndex(defaultLayout.getClass().getName());
				} 
			}
			if (index == -1) index = 0;
		}
		combo.select(index);
	}

	private int getIndex(JRPropertiesHolder pholder, String uuid) {
		return getIndex(uuid, pholder.getPropertiesMap());
	}

	private int getIndex(String uuid, JRPropertiesMap pmap) {
		String key = ILayout.KEY;
		if (uuid != null)
			key += "." + uuid; //$NON-NLS-1$
		String str = pmap.getProperty(key);
		return getIndex(str);
	}
	
	/**
	 * Get the layout index in the combo from its classname
	 * 
	 * @param the classname of the layout
	 * @return the index of the layout in the combo or -1 if it can't be found
	 */
	private int getIndex(String layoutName) {
		if (layoutName != null) {
			for (int i = 0; i < layouts.length; i++) {
				if (layouts[i].getClass().getName().equals(layoutName)) {
					return i;
				}
			}
		}
		return -1;
	}
	
	@Override
	protected void createComponent(Composite parent) {	
		Composite container = section.getWidgetFactory().createComposite(parent);	
		container.setLayout(new GridLayout(1, false));
		combo = section.getWidgetFactory().createCombo(container, SWT.READ_ONLY);
		layouts = LayoutManager.getAllLayouts();
		String[] labels = new String[layouts.length];
		for (int i = 0; i < layouts.length; i++)
			labels[i] = layouts[i].getName();
		combo.setItems(labels);
		
		combo.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				handlePropertyChange();
			}
		});
		combo.setToolTipText(pDescriptor.getDescription());
	}
}
