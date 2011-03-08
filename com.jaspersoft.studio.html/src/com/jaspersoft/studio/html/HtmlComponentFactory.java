package com.jaspersoft.studio.html;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.components.html.HtmlComponent;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.part.WorkbenchPart;

import com.jaspersoft.studio.IComponentFactory;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.html.command.CreateHtmlCommand;
import com.jaspersoft.studio.html.model.MHtml;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.MFrame;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.band.MBand;

public class HtmlComponentFactory implements IComponentFactory {
	public ANode createNode(ANode parent, Object jrObject, int newIndex) {
		if (jrObject instanceof JRDesignComponentElement) {
			if (((JRDesignComponentElement) jrObject).getComponent() instanceof HtmlComponent) {
				return new MHtml(parent, (JRDesignComponentElement) jrObject, newIndex);
			}
		}
		return null;
	}

	public IFigure createFigure(ANode node) {
		if (node instanceof MHtml)
			return new ComponentFigure();
		return null;
	}

	public List<?> getChildren4Element(Object jrObject) {

		return null;
	}

	public List<Class<?>> getPaletteEntries() {
		List<Class<?>> list = new ArrayList<Class<?>>();
		list.add(MHtml.class);
		return list;
	}

	public Command getCreateCommand(ANode parent, ANode child, Rectangle location, int newIndex) {
		if (child instanceof MHtml) {
			if (parent instanceof MElementGroup)
				return new CreateHtmlCommand((MElementGroup) parent, (MGraphicElement) child, newIndex);
			if (parent instanceof MBand)
				return new CreateHtmlCommand((MBand) parent, (MGraphicElement) child, newIndex);
			if (parent instanceof MFrame)
				return new CreateHtmlCommand((MFrame) parent, (MGraphicElement) child, newIndex);
			if (parent instanceof MReport)
				return new CreateHtmlCommand(parent, (MGraphicElement) child, location, newIndex);

			if (parent instanceof IGroupElement) {
				return new CreateHtmlCommand(parent, (MGraphicElement) child, location, newIndex);
			}
		}
		return null;
	}

	public Command getDeleteCommand(ANode parent, ANode child) {
		return null;
	}

	public Command getReorderCommand(ANode parent, ANode child, int newIndex) {
		return null;
	}

	public List<Action> getActions(WorkbenchPart part) {
		return null;
	}

	public List<String> getActionsID() {
		return null;
	}

	public EditPart createEditPart(EditPart context, Object model) {
		return null;
	}

	public Command getOrphanCommand(ANode parent, ANode child) {
		return null;
	}

	public AbstractVisualEditor getEditor(Object node) {
		return null;
	}
}
