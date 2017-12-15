/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.crosstab.command;

import net.sf.jasperreports.crosstabs.JRCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.JRCrosstabRowGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.crosstab.command.wizard.CrosstabWizard;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.command.CreateElementCommand;
import com.jaspersoft.studio.model.frame.MFrame;

/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateCrosstabCommand extends CreateElementCommand {

	/**
	 * Instantiates a new creates the element command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	public CreateCrosstabCommand(MElementGroup destNode, MGraphicElement srcNode, int index) {
		super(destNode, srcNode, index);
	}

	/**
	 * Instantiates a new creates the element command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	public CreateCrosstabCommand(MFrame destNode, MGraphicElement srcNode, int index) {
		super(destNode, srcNode, index);
	}

	/**
	 * Instantiates a new creates the element command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	public CreateCrosstabCommand(MBand destNode, MGraphicElement srcNode, int index) {
		super(destNode, srcNode, index);
	}

	/**
	 * Instantiates a new creates the element command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param position
	 *          the position
	 * @param index
	 *          the index
	 */
	public CreateCrosstabCommand(ANode destNode, MGraphicElement srcNode, Rectangle position, int index) {
		super(destNode, srcNode, position, index);
	}
	
	private int computeCrosstabWidth(MCrosstab value){
		JRDesignCrosstab crosstab = value.getValue();
		JRCrosstabColumnGroup firstColumnGroup = crosstab.getColumnGroups()[0];
		JRCrosstabRowGroup firstRowGroup = crosstab.getRowGroups()[0];
		int width = firstColumnGroup.getHeader().getWidth() + firstColumnGroup.getTotalHeader().getWidth() + firstRowGroup.getTotalHeader().getWidth();
		return width;
	}
	
	private int computeCrosstabHeight(MCrosstab value){
		JRDesignCrosstab crosstab = value.getValue();
		JRCrosstabColumnGroup firstColumnGroup = crosstab.getColumnGroups()[0];
		JRCrosstabRowGroup firstRowGroup = crosstab.getRowGroups()[0];
		int height = firstRowGroup.getHeader().getWidth() + firstRowGroup.getTotalHeader().getWidth() + firstColumnGroup.getTotalHeader().getWidth();
		return height;
	}

	/**
	 * Creates the object.
	 */
	@Override
	protected void createObject() {
		if (jrElement == null) {
			// here put a wizard
			CrosstabWizard wizard = new CrosstabWizard();
			wizard.setConfig(jConfig, false);
			WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), wizard);
			dialog.create();
			if (dialog.open() == Dialog.OK) {
				srcNode = wizard.getCrosstab();
				if (location == null) location = new Rectangle(0, 0, 0, 0);
				location.setWidth(computeCrosstabWidth(((MCrosstab)srcNode)));
				location.setHeight(computeCrosstabHeight(((MCrosstab)srcNode)));
				addCommands(wizard.getCommands());
				if (srcNode.getValue() == null)
					jrElement = srcNode.createJRElement(jasperDesign);
				else
					jrElement = (JRDesignElement) srcNode.getValue();
				if (jrElement != null)
					setElementBounds();
			}
		}
	}

}
