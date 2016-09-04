/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components.widgets.framework.ui.dialogs;

import java.util.Arrays;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.jasperreports.chartcustomizers.utils.ShapeDefinition.FigureShape;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

/**
 * Wizard page to define the shape of the item
 * 
 * @author Orlandin Marco
 *
 */
public class ShapeWizardPage extends JSSHelpWizardPage {

	/**
	 * Table where the possible shapes are shown
	 */
	private TableViewer viewer;
	
	/**
	 * The current shape used to preinitialize the fields
	 */
	private FigureShape currentShape = null;
	
	public ShapeWizardPage() {
		super("shapeDefinitionPage");
		setTitle("Figure shape");
		setMessage("Define the shape of the figure");
	}
	
	protected ShapeWizardPage(FigureShape currentShape) {
		this();
		this.currentShape = currentShape;
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer = new TableViewer(container);
		viewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer.setContentProvider(new ListContentProvider());
		viewer.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				return ((FigureShape)element).toString();
			}
		});
		viewer.setInput(Arrays.asList(FigureShape.values()));
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection selection = (StructuredSelection)viewer.getSelection();
				if (selection != null && !selection.isEmpty()){
					currentShape = (FigureShape)selection.getFirstElement();
				} else {
					currentShape = null;
				}
				getContainer().updateButtons();
			}
		});
		if (currentShape != null){
			viewer.setSelection(new StructuredSelection(currentShape));
		}
		setControl(container);
	}
	
	@Override
	public boolean isPageComplete() {
		return currentShape != null;
	}

	@Override
	protected String getContextName() {
		return null;
	}

	/**
	 * Return the points page if the shape is a polyline or the sides
	 * page if it is a polygon
	 */
	@Override
	public IWizardPage getNextPage() {
		if (currentShape.equals(FigureShape.POLYLINE)){
			return ((ShapeDefinitionWizard)getWizard()).getPointsDefinitionPage();
		} else if (currentShape.equals(FigureShape.POLYGON)){ 
			return ((ShapeDefinitionWizard)getWizard()).getSidesDefinitionPage();
		}
		return null;
	}
	
	public FigureShape getShape(){
		return currentShape;
	}
}
