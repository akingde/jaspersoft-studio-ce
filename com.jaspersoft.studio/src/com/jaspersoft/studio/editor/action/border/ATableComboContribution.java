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
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElementLineBox;
import com.jaspersoft.studio.model.MLineBox;
import com.jaspersoft.studio.model.MLinePen;
import com.jaspersoft.studio.property.SetValueCommand;

/**
 * Component that represent a combo with inside the preview of some border presets. This component should 
 * be placed in the toolbar when an element that can have borders is selected
 * 
 * @author Orlandin Marco
 *
 */
public class ATableComboContribution extends ContributionItem implements ISelectionContributionItem, Listener {
		
		/**
		 * The item that will placed in the toolbard
		 */
		private ToolItem toolitem;
		
		/**
		 * The composite that will displayed in the toolbar, it contains a label and the combo
		 */
		private Composite control;
		
		/**
		 * The combo with the border presets inside
		 */
		private TableCombo combo;

		/**
		 * The workbench
		 */
		private IWorkbenchPart workbenchPart;
		
		/**
		 * The selected element
		 */
		private ISelection selection;
		
		/**
		 * The list of available presets
		 */
		private List<TemplateBorder> exampleImages;
		
		/**
		 * The model of the selected element
		 */
		protected MGraphicElementLineBox model;
		
		/**
		 * A listener to uniform in the toolbar change done by the property tab
		 */
		private ModelListener modelListener = new ModelListener();
		
		private class ModelListener implements PropertyChangeListener {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				setCorrectValue();
			}
		}
		
		/**
		 * Create the class and some presets
		 */
		public ATableComboContribution(){
			super("BordersTemplateCombo"); //$NON-NLS-1$
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
		
		/**
		 * When an element is selected if it use a preset it will be automatically 
		 * selected in the comb. If no preset is used for the element the Custom 
		 * image will be used
		 */
		protected void setCorrectValue(){
			TemplateBorder actualBorder = getElementAttributes();
			int index = exampleImages.indexOf(actualBorder);
			if (index != -1) combo.select(index);
			else combo.select(exampleImages.size());
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
					.equals(oldValue))) && workbenchPart!= null) {
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
		 * @param cc CommandCompound where all the command to change a single property are putted
		 * @param selectedElement selected preset
		 * @param lp element to change
		 */
		private void changeAllProperties(CompoundCommand cc, TemplateBorder selectedElement, MLinePen lp){
			Command c = getChangePropertyCommand(JRBasePen.PROPERTY_LINE_COLOR, selectedElement.getColor(), lp);
			if (c != null) cc.add(c);
			c = getChangePropertyCommand(JRBasePen.PROPERTY_LINE_STYLE, selectedElement.getStyle(), lp);
			if (c != null) cc.add(c);
			c = getChangePropertyCommand(JRBasePen.PROPERTY_LINE_WIDTH, selectedElement.getLineWidth(), lp);
			if (c != null) cc.add(c);
		} 
		
		/**
		 * Return a TemplateBorder that represent the border of the element selected
		 * @return
		 */
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
		 * Change the property of all the linepen of an element
		 */
		private void changeProperty() {
			  if (combo.getSelectionIndex()<exampleImages.size()){
					TemplateBorder selectedElement = exampleImages.get(combo.getSelectionIndex());
					CompoundCommand cc = new CompoundCommand("Change border"); //$NON-NLS-1$
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
		
		/**
		 * Crate the  control 
		 * @param parent
		 * @return a composite with a label and the combo preview inside
		 */
		@SuppressWarnings("unchecked")
		protected Control createControl(Composite parent) {
			control = new Composite(parent, SWT.None);
			GridLayout layout = new GridLayout(2,false);
			layout.marginHeight = 0;
			layout.verticalSpacing = 0;
			control.setLayout(layout);
			Label label = new Label(control, SWT.None);
			label.setText(Messages.ATableComboContribution_presets_label);
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
			return control;
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
		
		/**
		 * Insert the element inside the toolbar
		 */
		@Override
		public void fill(ToolBar parent, int index) {
			toolitem = new ToolItem(parent, SWT.SEPARATOR, index);
			createControl(parent);
			toolitem.setWidth(225);
			toolitem.setControl(control);
		}
		
		public void dispose() {
			combo = null;
			control = null;
		}
		
		public final void fill(Composite parent) {
			createControl(parent);
		}
		
		public final void fill(Menu parent, int index) {
			Assert.isTrue(false, Messages.ATableComboContribution_error_message); 
		}
		
		/**
		 * Called when an element is selected, it add a change listener to element to keep uniformed 
		 * the toolbar and the properties tab
		 */
		@Override
		public void setSelection(ISelection selection) {
			this.selection = selection;
			if (model != null) {
				model.getPropertyChangeSupport().removePropertyChangeListener(modelListener);
				model = null;
			}
			
		}
		
		/**
		 * Load the imege for every preset and insert them into the combo element
		 */
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
