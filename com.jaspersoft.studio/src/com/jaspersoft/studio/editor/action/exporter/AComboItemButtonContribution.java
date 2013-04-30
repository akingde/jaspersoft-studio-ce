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
package com.jaspersoft.studio.editor.action.exporter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.type.LineStyleEnum;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.widgets.ILayoutExtension;

import com.jaspersoft.studio.editor.action.border.TemplateBorder;
import com.jaspersoft.studio.editor.toolitems.ISelectionContributionItem;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MGraphicElementLineBox;
import com.jaspersoft.studio.property.combomenu.ComboItemAction;
import com.jaspersoft.studio.property.combomenu.ComboMenuViewer;

public class AComboItemButtonContribution extends ContributionItem implements ISelectionContributionItem, Listener {

	private ToolItem toolitem;
	
	private ComboMenuViewer combo;
	
	private IWorkbenchPart workbenchPart;
	
	private ISelection selection;
	
	private List<TemplateBorder> exampleImages;
	
	protected MGraphicElement model;
	
	
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
					//changeProperty();
				}
			});
			if (selection != null) {
				StructuredSelection ss = (StructuredSelection) selection;
				for (Iterator<Object> it = ss.iterator(); it.hasNext();) {
					Object obj = it.next();
					if (obj instanceof EditPart)
						obj = ((EditPart) obj).getModel();
					if (obj instanceof MGraphicElementLineBox) {
						model = (MGraphicElement) obj;
					}
				}
			}
			return combo.getControl();
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
		
		private int computeMaximumWidth(Control c) {
			if (c instanceof Composite) {
				Layout layout = ((Composite) c).getLayout();
				if (layout instanceof ILayoutExtension)
					return ((ILayoutExtension) layout).computeMaximumWidth(
							(Composite) c, true);
			}
			return c.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x;
		}

		
		/**
		* @see org.eclipse.jface.action.IContributionItem#fill(org.eclipse.swt.widgets.ToolBar, int)
		*/
		public void fill(ToolBar parent, int index) {
			toolitem = new ToolItem(parent, SWT.SEPARATOR, index);
			Control control = createControl(parent);
			toolitem.setWidth(computeMaximumWidth(combo.getControl()));
			toolitem.setControl(control);
		}
		
		private void onSelection() {
			if (selection.isEmpty())
				return;
			//changeProperty();
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
		}
		
		@Override
			public void setWorkbenchPart(IWorkbenchPart workbenchPart) {
			this.workbenchPart = workbenchPart;
		}

}
