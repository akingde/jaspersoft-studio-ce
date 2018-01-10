/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignPart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.part.WorkbenchPart;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.plugin.IComponentFactory;
import com.jaspersoft.studio.plugin.IPaletteContributor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class PartComponentFactory implements IComponentFactory {

	private static List<Class<?>> knownClasses;
	
	static {
		knownClasses = new ArrayList<Class<?>>(2);
		knownClasses.add(MReportPart.class);
		knownClasses.add(MReportPartContainer.class);
	}

	
	@Override
	public ANode createNode(ANode parent, Object jrObject, int newIndex) {
		if (jrObject instanceof JRDesignPart) {
			return new MReportPart(parent, (JRDesignPart) jrObject, newIndex);
		}
		return null;
	}

	@Override
	public List<?> getChildren4Element(Object jrObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPaletteContributor getPaletteEntries() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFigure createFigure(ANode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public EditPart createTreeEditPart(EditPart context, Object model) {
		return null;
	}

	@Override
	public Command getStretchToContent(ANode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Command getCreateCommand(ANode parent, ANode child,
			Rectangle location, int newIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Command getReorderCommand(ANode parent, ANode child, int newIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Command getDeleteCommand(ANode parent, ANode child) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Command getOrphanCommand(ANode parent, ANode child) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Action> getActions(WorkbenchPart part) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getActionsID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractVisualEditor getEditor(Object node,
			JasperReportsConfiguration jrContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExpressionContext getElementExpressionContext(Object jrObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Class<?>> getKnownClasses() {
		return knownClasses;
	}

}
