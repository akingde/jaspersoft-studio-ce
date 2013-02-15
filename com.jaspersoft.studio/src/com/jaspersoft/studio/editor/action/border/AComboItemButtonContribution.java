/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
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
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.border;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.base.JRBasePen;
import net.sf.jasperreports.engine.type.LineStyleEnum;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.internal.forms.widgets.FormUtil;

import com.jaspersoft.studio.editor.toolitems.ISelectionContributionItem;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElementLineBox;
import com.jaspersoft.studio.model.MLineBox;
import com.jaspersoft.studio.model.MLinePen;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.property.combomenu.ComboItem;
import com.jaspersoft.studio.property.combomenu.ComboItemAction;
import com.jaspersoft.studio.property.combomenu.ComboMenuViewer;

public class AComboItemButtonContribution extends ContributionItem implements ISelectionContributionItem, Listener {

	private ToolItem toolitem;
	
	private ComboMenuViewer combo;
	
	private IWorkbenchPart workbenchPart;
	
	private ISelection selection;
	
	private List<TemplateBorder> exampleImages;
	
	protected MGraphicElementLineBox model;
	
	private ModelListener modelListener = new ModelListener();
	
	private class ModelListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			TemplateBorder actualBorder = getElementAttributes();
			//int index = exampleImages.indexOf(actualBorder);
			//if (index != -1) combo.select(index);
			//else combo.select(exampleImages.size());
		}
	}
	
		/**
		* Constructs the action by specifying the report viewer to associate with the item.
		* 
		* @param viewer
		*          the report viewer
		*/
		public AComboItemButtonContribution() {
			super("comboitem_test");
			exampleImages = new ArrayList<TemplateBorder>();
			exampleImages.add(new TemplateBorder(2f, LineStyleEnum.DASHED));
			exampleImages.add(new TemplateBorder(2f, LineStyleEnum.DOTTED));
			exampleImages.add(new TemplateBorder(2f, LineStyleEnum.DOUBLE));
			exampleImages.add(new TemplateBorder(2f, LineStyleEnum.SOLID));
		}
		
		protected Control createControl(Composite parent) {
			combo = new ComboMenuViewer(parent, SWT.NORMAL, "Double line");
			combo.addSelectionListener(new ComboItemAction() {
				/**
				 * The action to execute when an entry is selected
				 */
				@Override
				public void exec() {
					changeProperty();
				}
			});
			if (selection != null) {
				StructuredSelection ss = (StructuredSelection) selection;
				for (Iterator<Object> it = ss.iterator(); it.hasNext();) {
					Object obj = it.next();
					if (obj instanceof EditPart)
						obj = ((EditPart) obj).getModel();
					if (obj instanceof MGraphicElementLineBox) {
						model = (MGraphicElementLineBox) obj;
						model.getPropertyChangeSupport().addPropertyChangeListener(modelListener);
					}
				}
			}
			loadImages();
			return combo.getControl();
		}
		
		private void loadImages() {
			List<ComboItem> itemsList = new ArrayList<ComboItem>();
			int order = 0;
			for (TemplateBorder border: exampleImages) {
				itemsList.add(new ComboItem("", true, border.getImage(), order, border, order));
				order++;
			}
			itemsList.add(new ComboItem("", true, null, order, null, order));
			combo.setItems(itemsList);
		}		
		
		protected Command getChangePropertyCommand(Object property, Object newValue, APropertyNode n) {
		//	Object oldValue = n.getPropertyValue(property);
	//		if (((oldValue == null && newValue != null) || (oldValue != null && newValue == null) || (newValue != null && !newValue
		//			.equals(oldValue))) && workbenchPart!= null) {
				SetValueCommand setCommand = new SetValueCommand(n.getDisplayText());
				setCommand.setTarget(n);
				setCommand.setPropertyId(property);
				setCommand.setPropertyValue(newValue);
				return setCommand;
		//	}
		//	return null;
		}
		
		
		private void changeAllProperties(CompoundCommand cc, TemplateBorder selectedElement, MLinePen lp){
			Command c = getChangePropertyCommand(JRBasePen.PROPERTY_LINE_COLOR, selectedElement.getColor(), lp);
			if (c != null) cc.add(c);
			c = getChangePropertyCommand(JRBasePen.PROPERTY_LINE_STYLE, selectedElement.getStyle(), lp);
			if (c != null) cc.add(c);
			c = getChangePropertyCommand(JRBasePen.PROPERTY_LINE_STYLE, selectedElement.getLineWidth(), lp);
			if (c != null) cc.add(c);
		}
		
		private TemplateBorder getElementAttributes(){
				MLineBox lb = (MLineBox) model.getPropertyValue(MGraphicElementLineBox.LINE_BOX); 
				MLinePen lp = (MLinePen) lb.getPropertyValue(MLineBox.LINE_PEN_TOP);
				LineStyleEnum lineStyle = LineStyleEnum.getByValue(((Integer)lp.getPropertyValue(JRBasePen.PROPERTY_LINE_STYLE)).byteValue());
				Float lineWidth = (Float)lp.getPropertyValue(JRBasePen.PROPERTY_LINE_WIDTH);
				RGB lineColor = (RGB)lp.getPropertyValue(JRBasePen.PROPERTY_LINE_COLOR);
				TemplateBorder result =  new TemplateBorder(lineWidth, lineStyle, lineColor);
				return result;
		}
		
		
		/**
		 * Change the property of a linepen
		 * @param property the property name
		 * @param newValue the new value
		 */
		private void changeProperty() {
				TemplateBorder selectedElement = exampleImages.get(combo.getSelectionIndex());
				CompoundCommand cc = new CompoundCommand("Change border");
				MLineBox lb = (MLineBox) model.getPropertyValue(MGraphicElementLineBox.LINE_BOX);
				
				MLinePen lp = (MLinePen) lb.getPropertyValue(MLineBox.LINE_PEN_BOTTOM);
				changeAllProperties(cc,selectedElement,lp);
				
				lp = (MLinePen) lb.getPropertyValue(MLineBox.LINE_PEN_LEFT);
				changeAllProperties(cc,selectedElement,lp);
				
				lp = (MLinePen) lb.getPropertyValue(MLineBox.LINE_PEN_RIGHT);
				changeAllProperties(cc,selectedElement,lp);
				
				lp = (MLinePen) lb.getPropertyValue(MLineBox.LINE_PEN_TOP);
				changeAllProperties(cc,selectedElement,lp);

				CommandStack cs = getCommandStack();
				cs.execute(cc);
		}
		
		/**
		 * Returns the editor's command stack. This is done by asking the workbench part for its CommandStack via
		 * {@link org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)}.
		 * 
		 * @return the command stack
		 */
		protected CommandStack getCommandStack() {
			return (CommandStack) workbenchPart.getAdapter(CommandStack.class);
		}
		
		/**
		* @see org.eclipse.jface.action.ContributionItem#dispose()
		*/
		public void dispose() {
		combo = null;
		}
		
		/**
		* @see org.eclipse.jface.action.IContributionItem#fill(org.eclipse.swt.widgets.Composite)
		*/
		public final void fill(Composite parent) {
			createControl(parent);
		}
		
		/**
		* @see org.eclipse.jface.action.IContributionItem#fill(org.eclipse.swt.widgets.Menu, int)
		*/
		public final void fill(Menu parent, int index) {
			Assert.isTrue(false, "Can not add to menu"); //$NON-NLS-1$
		}
		
		/**
		* @see org.eclipse.jface.action.IContributionItem#fill(org.eclipse.swt.widgets.ToolBar, int)
		*/
		public void fill(ToolBar parent, int index) {
			toolitem = new ToolItem(parent, SWT.SEPARATOR, index);
			Control control = createControl(parent);
			toolitem.setWidth(FormUtil.computeMaximumWidth(combo.getControl(), true));
			toolitem.setControl(control);
		}
		
		private void onSelection() {
			if (selection.isEmpty())
				return;
			changeProperty();
		}


		public void handleEvent(Event event) {
			switch (event.type) {
			case SWT.FocusIn:
				break;
			case SWT.Selection:
			case SWT.DefaultSelection:
				//onSelection();
				break;
			}
		}
		
		@Override
		public void setSelection(ISelection selection) {
			this.selection = selection;
			if (model != null) {
				model.getPropertyChangeSupport().removePropertyChangeListener(modelListener);
				model = null;
			}
		}
		
		@Override
			public void setWorkbenchPart(IWorkbenchPart workbenchPart) {
			this.workbenchPart = workbenchPart;
		}

}
