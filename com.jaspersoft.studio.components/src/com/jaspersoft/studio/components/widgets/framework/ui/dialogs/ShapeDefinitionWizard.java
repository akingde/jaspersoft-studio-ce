/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components.widgets.framework.ui.dialogs;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.jasperreports.chartcustomizers.utils.ShapeDefinition;
import com.jaspersoft.jasperreports.chartcustomizers.utils.ShapeDefinition.FigureShape;

/**
 * Wizard to define the shape of an element
 * 
 * @author Orlandin Marco
 *
 */
public class ShapeDefinitionWizard extends Wizard {

	/**
	 * Page for the shape type
	 */
	private ShapeWizardPage page0;
	
	/**
	 * Page for the polyline points, shown only if the type of the first page is polyline
	 */
	private PointsWizardPage page1;
	
	/**
	 * Page for the polygon sides, shown only if the type of the first page is polygon
	 */
	private SidesWizardPage page2;
	
	/**
	 * The edited shape
	 */
	private ShapeDefinition editedShape;
	
	/**
	 * Create the wizard
	 * 
	 * @param editedShape shape used to pre-initialize the controls, it can be null
	 */
	public ShapeDefinitionWizard(ShapeDefinition editedShape) {
		this.editedShape = editedShape;
	}
	
	@Override
	public void addPages() {
		page0 = new ShapeWizardPage(editedShape != null ? editedShape.getShape() : null);
		page1 = new PointsWizardPage(editedShape != null ? editedShape.getPoints() : null);
		page2 = new SidesWizardPage(editedShape != null ? editedShape.getPoints() : null);
		
		addPage(page0);
		addPage(page1);
		addPage(page2);
	}
	
	@Override
	public boolean performFinish() {
		return true;
	}

	protected ShapeWizardPage getShapeDefinitionPage(){
		return page0;
	}
	
	protected PointsWizardPage getPointsDefinitionPage(){
		return page1;
	}
	
	protected SidesWizardPage getSidesDefinitionPage(){
		return page2;
	}
	
	/**
	 * Return the shape encoded into a string 
	 * 
	 * @return the shape definition string
	 */
	public String getEncodedShapeDefinition(){
		ShapeDefinition definition = new ShapeDefinition();
		
		definition.setShape(page0.getShape());
		
		if(definition.getShape().equals(FigureShape.POLYLINE)){
			definition.setPoints(page1.getPoints());		
		} else if (definition.getShape().equals(FigureShape.POLYGON)){
			definition.setPoints(page2.getPoints());
		}
		return ShapeDefinition.encode(definition);
	}
}
