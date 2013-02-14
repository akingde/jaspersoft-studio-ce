package com.jaspersoft.studio.editor.action.border;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.base.JRBasePen;
import net.sf.jasperreports.engine.type.LineStyleEnum;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.nebula.widgets.tablecombo.TableCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.editor.toolitems.ISelectionContributionItem;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElementLineBox;
import com.jaspersoft.studio.model.MLineBox;
import com.jaspersoft.studio.model.MLinePen;
import com.jaspersoft.studio.property.SetValueCommand;

public class ATableComboContribution extends ContributionItem implements ISelectionContributionItem, Listener {
		
		private ToolItem toolitem;
		
		private Composite control;
		
		private TableCombo combo;
		
		private IWorkbenchPart workbenchPart;
		
		private ISelection selection;
		
		private List<TemplateBorder> exampleImages;
		
		protected MGraphicElementLineBox model;
		
		private ModelListener modelListener = new ModelListener();
		
		private class ModelListener implements PropertyChangeListener {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				setCorrectValue();
			}
		}
		
		public ATableComboContribution(){
			super("BordersTemplateCombo");
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
		}
		
		protected void setCorrectValue(){
			TemplateBorder actualBorder = getElementAttributes();
			//System.out.println("actual value: "+actualBorder.getLineWidth()+" "+ actualBorder.getStyle()!=null ? actualBorder.getStyle().toString() :"null");
			int index = exampleImages.indexOf(actualBorder);
			if (index != -1) combo.select(index);
			else combo.select(exampleImages.size());
		}
			
		
		protected Command getChangePropertyCommand(Object property, Object newValue, APropertyNode n) {
			Object oldValue = n.getPropertyValue(property);
			if (((oldValue == null && newValue != null) || (oldValue != null && newValue == null) || (newValue != null && !newValue
					.equals(oldValue))) && workbenchPart!= null) {
				SetValueCommand setCommand = new SetValueCommand(n.getDisplayText());
				setCommand.setTarget(n);
				setCommand.setPropertyId(property);
				setCommand.setPropertyValue(newValue);
				return setCommand;
			}
			return null;
		}
		
		
		private void changeAllProperties(CompoundCommand cc, TemplateBorder selectedElement, MLinePen lp){
			Command c = getChangePropertyCommand(JRBasePen.PROPERTY_LINE_COLOR, selectedElement.getColor(), lp);
			if (c != null) cc.add(c);
			c = getChangePropertyCommand(JRBasePen.PROPERTY_LINE_STYLE, selectedElement.getStyle(), lp);
			if (c != null) cc.add(c);
			c = getChangePropertyCommand(JRBasePen.PROPERTY_LINE_WIDTH, selectedElement.getLineWidth(), lp);
			if (c != null) cc.add(c);
		} 
		
		private TemplateBorder getElementAttributes(){
			MLineBox lb = (MLineBox) model.getPropertyValue(MGraphicElementLineBox.LINE_BOX); 
			MLinePen lp = (MLinePen) lb.getPropertyValue(MLineBox.LINE_PEN_TOP);
			Integer lineStyleNum = ((Integer)lp.getPropertyValue(JRBasePen.PROPERTY_LINE_STYLE))-1;
			LineStyleEnum lineStyle = LineStyleEnum.getByValue(lineStyleNum.byteValue());
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
			  if (combo.getSelectionIndex()<exampleImages.size()){
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
			  setCorrectValue();
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
		
		private void onSelection() {
			if (selection.isEmpty())
				return;
			changeProperty();
		}
		
		@SuppressWarnings("unchecked")
		protected Control createControl(Composite parent) {
			control = new Composite(parent, SWT.None);
			GridLayout layout = new GridLayout(2,false);
			layout.marginHeight = 0;
			layout.verticalSpacing = 0;
			control.setLayout(layout);
			Label label = new Label(control, SWT.None);
			label.setText("Border presets:");
			combo = new TableCombo(control, SWT.BORDER | SWT.READ_ONLY);
			combo.setEditable(false);
			combo.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
							onSelection();
					}
			});
			
			GridData comboData = new GridData();
			comboData.grabExcessVerticalSpace = true;
			comboData.grabExcessHorizontalSpace = true;
			comboData.widthHint = 130;
			comboData.minimumHeight = 20;
			combo.setLayoutData(comboData);
			
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
			setCorrectValue();
			return combo;
		}
		
		@Override
		public void setWorkbenchPart(IWorkbenchPart workbenchPart) {
			this.workbenchPart = workbenchPart;
		}
	
		@Override
		public void handleEvent(Event event) {
			switch (event.type) {
			case SWT.FocusIn:
				break;
			case SWT.Selection:
			case SWT.DefaultSelection:
				break;
			}
		}
		
		public void fill(ToolBar parent, int index) {
			toolitem = new ToolItem(parent, SWT.SEPARATOR, index);
			createControl(parent);
			toolitem.setWidth(225);
			toolitem.setControl(control);
		}
		
		public void dispose() {
		combo = null;
		}
		
		public final void fill(Composite parent) {
			createControl(parent);
		}
		
		public final void fill(Menu parent, int index) {
			Assert.isTrue(false, "Can not add to menu"); //$NON-NLS-1$
		}
		
		
		@Override
		public void setSelection(ISelection selection) {
			this.selection = selection;
			if (model != null) {
				model.getPropertyChangeSupport().removePropertyChangeListener(modelListener);
				model = null;
			}
			
		}
		
		private void loadImages() {
			TemplateBorder.setWidth(100);
			for (TemplateBorder border: exampleImages) {
				TableItem ti = new TableItem(combo.getTable(), SWT.READ_ONLY);
				ti.setImage(border.getImage());
			}
			TableItem ti = new TableItem(combo.getTable(), SWT.NONE);
			ti.setImage(TemplateBorder.getCustomImage());
		}		
		

}
