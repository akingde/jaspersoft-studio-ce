/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.toolbars.border;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.nebula.widgets.tablecombo.TableCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.action.border.TemplateBorder;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElementLineBox;
import com.jaspersoft.studio.model.MLineBox;
import com.jaspersoft.studio.model.MLinePen;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.toolbars.CommonToolbarHandler;
import com.jaspersoft.studio.utils.AlfaRGB;

import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.base.JRBasePen;
import net.sf.jasperreports.engine.type.LineStyleEnum;

/**
 * Component that represent a combo with inside the preview of some border presets. This component should 
 * be placed in the toolbar when an element that can have borders is selected. It should also be placed along
 * with the {@link BorderLabelContributionItem} because the toolbar is unable to set the height (handled natively
 * by the os) without a static element. In this way the label will provide the height for the toolbar.
 * 
 * @author Orlandin Marco
 *
 */
public class BorderContributionItem extends CommonToolbarHandler {
		/**
		 * The composite that will displayed in the toolbar, it contains a label and the combo
		 */	 
		private Composite control;
		
		/**
		 * The combo with the border presets inside
		 */
		private TableCombo combo;

		/**
		 * The list of available presets
		 */
		private static List<TemplateBorder> exampleImages;
		
		/**
		 * Create some presets
		 */
		static{
			exampleImages = new ArrayList<TemplateBorder>();
			exampleImages.add(new TemplateBorder(null, LineStyleEnum.SOLID));
			exampleImages.add(new TemplateBorder(1f, LineStyleEnum.SOLID));
			exampleImages.add(new TemplateBorder(1f, LineStyleEnum.DASHED));
			exampleImages.add(new TemplateBorder(1f, LineStyleEnum.DOTTED));
			exampleImages.add(new TemplateBorder(1f, LineStyleEnum.DOUBLE));
			exampleImages.add(new TemplateBorder(2f, LineStyleEnum.SOLID));
			exampleImages.add(new TemplateBorder(2f, LineStyleEnum.DASHED));
			exampleImages.add(new TemplateBorder(2f, LineStyleEnum.DOTTED));
			exampleImages.add(new TemplateBorder(2f, LineStyleEnum.DOUBLE));
			exampleImages.add(new TemplateBorder(4f, LineStyleEnum.SOLID));
			exampleImages.add(new TemplateBorder(4f, LineStyleEnum.DASHED));
			exampleImages.add(new TemplateBorder(4f, LineStyleEnum.DOTTED));
			exampleImages.add(new TemplateBorder(4f, LineStyleEnum.DOUBLE));
		};
		
		/**
		 * A listener to uniform in the toolbar change done by the property tab
		 */
		private ModelListener modelListener = new ModelListener();
		
		private APropertyNode showedNode = null;
		
		private class ModelListener implements PropertyChangeListener {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				setCorrectValue();
			}
		}
		
		/**
		 * When an element is selected if it use a preset it will be automatically 
		 * selected in the comb. If no preset is used for the element the Custom 
		 * image will be used
		 */
		protected void setCorrectValue(){
			if (combo != null && !combo.isDisposed()){
				TemplateBorder actualBorder = getElementAttributes();
				int index = exampleImages.indexOf(actualBorder);
				if (index != -1) combo.select(index);
				else combo.select(exampleImages.size());
			}
		}
			
		/**
		 * Create a command to change a property. The change is done if the new value of the property
		 * is different from its previous value
		 * @param property the property to change
		 * @param newValue the new value for the property
		 * @param n the element to modify
		 * @return the command
		 */
		protected Command getChangePropertyCommand(Object property, Object newValue, APropertyNode n) {
			Object oldValue = n.getPropertyValue(property);
			if (((oldValue == null && newValue != null) || (oldValue != null && newValue == null) || (newValue != null && !newValue
					.equals(oldValue))) ) {
				SetValueCommand setCommand = new SetValueCommand(n.getDisplayText());
				setCommand.setTarget(n);
				setCommand.setPropertyId(property);
				setCommand.setPropertyValue(newValue);
				return setCommand;
			}
			return null;
		}
		
		/**
		 * For an element change all the properties related to the border: color, style and width
		 * @param cc CommandCompound where all the command to change a single property are put
		 * @param selectedElement selected preset
		 * @param lp element to change
		 */
		private void changeAllProperties(JSSCompoundCommand cc, TemplateBorder selectedElement, MLinePen lp){
			Command c = getChangePropertyCommand(JRBasePen.PROPERTY_LINE_COLOR, new AlfaRGB(selectedElement.getColor(),255), lp);
			if (c != null) cc.add(c);
			c = getChangePropertyCommand(JRBasePen.PROPERTY_LINE_STYLE, selectedElement.getStyle(), lp);
			if (c != null) cc.add(c);
			c = getChangePropertyCommand(JRBasePen.PROPERTY_LINE_WIDTH, selectedElement.getLineWidth(), lp);
			if (c != null) cc.add(c);
		} 
		
		private TemplateBorder getElementAttribute(String position, MLineBox lb){
			MLinePen lp = (MLinePen) lb.getPropertyValue(position);
			JRPen pen = (JRPen)lp.getValue();
			LineStyleEnum lineStyle =  (LineStyleEnum)pen.getLineStyleValue();
			Float lineWidth = (Float)lp.getPropertyValue(JRBasePen.PROPERTY_LINE_WIDTH);
			AlfaRGB lineColor = (AlfaRGB)lp.getPropertyValue(JRBasePen.PROPERTY_LINE_COLOR);
			TemplateBorder result =  new TemplateBorder(lineWidth, lineStyle, lineColor != null ? lineColor.getRgb() : null);
			return result;
		}
		
		/**
		 * Return a TemplateBorder that represent the border of the element selected. If the figure has not
		 * an unique border this method return null
		 * @return
		 */
		private TemplateBorder getElementAttributes(){
			List<Object> selection = getSelectionForType(MGraphicElementLineBox.class);
			if (selection.size() > 0){
				MGraphicElementLineBox model = (MGraphicElementLineBox)selection.get(0);
				MLineBox lb = (MLineBox) model.getPropertyValue(MGraphicElementLineBox.LINE_BOX); 
				TemplateBorder top = getElementAttribute(MLineBox.LINE_PEN_TOP, lb);
				TemplateBorder left = getElementAttribute(MLineBox.LINE_PEN_RIGHT, lb);
				if (!top.equals(left)) return null;
				TemplateBorder right = getElementAttribute(MLineBox.LINE_PEN_RIGHT, lb);
				if (!top.equals(right)) return null;
				TemplateBorder bottom = getElementAttribute(MLineBox.LINE_PEN_BOTTOM, lb);
				if (!top.equals(bottom)) return null;
				return top;
			} else return null;
	}
		
		
		/**
		 * Change the property of all the linepen of an element
		 */
		private void changeProperty() {
			  if (combo.getSelectionIndex()<exampleImages.size()){
					List<Object> selection = getSelectionForType(MGraphicElementLineBox.class);
					JSSCompoundCommand cc = new JSSCompoundCommand("Change border", selection.isEmpty() ? null : (APropertyNode)selection.get(0)); //$NON-NLS-1$
					for(Object obj : selection){
						MGraphicElementLineBox model = (MGraphicElementLineBox)obj;
						TemplateBorder selectedElement = exampleImages.get(combo.getSelectionIndex());
						MLineBox lb = (MLineBox) model.getPropertyValue(MGraphicElementLineBox.LINE_BOX);
						
						MLinePen lp = (MLinePen) lb.getPropertyValue(MLineBox.LINE_PEN_BOTTOM);
						changeAllProperties(cc,selectedElement,lp);
						
						lp = (MLinePen) lb.getPropertyValue(MLineBox.LINE_PEN_LEFT);
						changeAllProperties(cc,selectedElement,lp);
						
						lp = (MLinePen) lb.getPropertyValue(MLineBox.LINE_PEN_RIGHT);
						changeAllProperties(cc,selectedElement,lp);
						
						lp = (MLinePen) lb.getPropertyValue(MLineBox.LINE_PEN_TOP);
						changeAllProperties(cc,selectedElement,lp);
			  	}
					CommandStack cs = getCommandStack();
					cs.execute(cc);
			  }
			  setCorrectValue();
		}

		@Override
		protected boolean fillWithToolItems(ToolBar parent) {
			ToolItem tiBorderCombo = new ToolItem(parent,SWT.SEPARATOR);
			combo = new TableCombo(parent, SWT.BORDER | SWT.READ_ONLY) {
				protected Table createTable(Composite parent) {
					Table table = super.createTable(parent);
					//Load the image for every preset and insert them into the combo element
					TemplateBorder.setWidth(100);
					for (TemplateBorder border: exampleImages) {
						TableItem ti = new TableItem(table, SWT.READ_ONLY);
						ti.setImage(border.getImage());
					}
					TableItem ti = new TableItem(table, SWT.NONE);
					ti.setImage(TemplateBorder.getCustomImage());
					return table;
				};
			};
			combo.setEditable(false);
			combo.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						changeProperty();
					}
			});
			setAllControlsData();
			combo.pack();
			tiBorderCombo.setWidth(130);
			tiBorderCombo.setControl(combo);
			getToolItems().add(tiBorderCombo);
			
			return true;
		}
		
		/**
		 * Set the state of the combo according to the borders of the first element
		 * inside the selection
		 */
		protected void setAllControlsData(){
			if (combo == null || combo.isDisposed()) return;
			List<Object> selection = getSelectionForType(MGraphicElementLineBox.class);
			if (selection.size() == 1){
				APropertyNode node = (APropertyNode)selection.get(0);
				setCorrectValue();
				if (showedNode != null) showedNode.getPropertyChangeSupport().removePropertyChangeListener(modelListener);
				showedNode = node;
				showedNode.getPropertyChangeSupport().addPropertyChangeListener(modelListener);
				
			} 
		}

		
		@Override
		public boolean isVisible() {
			if (!super.isVisible()) return false;
			List<Object> selection = getSelectionForType(MGraphicElementLineBox.class);
			return !selection.isEmpty();
		}
		
		@Override
		public void dispose() {
			super.dispose();
			if (combo != null){
				combo.dispose();
				combo = null;
			}
			if (control != null){
				control.dispose();
				control = null;
			}
			if (showedNode != null) {
				showedNode.getPropertyChangeSupport().removePropertyChangeListener(modelListener);
				showedNode = null;
			}
		}
}
