/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.outline;

import java.util.List;

import net.sf.jasperreports.engine.JRReportTemplate;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.type.BandTypeEnum;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.MFrame;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.band.MBandGroupFooter;
import com.jaspersoft.studio.model.band.MBandGroupHeader;
import com.jaspersoft.studio.model.band.command.CreateBandCommand;
import com.jaspersoft.studio.model.band.command.CreateBandDetailCommand;
import com.jaspersoft.studio.model.band.command.CreateBandGroupFooterCommand;
import com.jaspersoft.studio.model.band.command.CreateBandGroupHeaderCommand;
import com.jaspersoft.studio.model.band.command.DeleteBandCommand;
import com.jaspersoft.studio.model.band.command.DeleteBandDetailCommand;
import com.jaspersoft.studio.model.band.command.DeleteBandGroupFooterCommand;
import com.jaspersoft.studio.model.band.command.DeleteBandGroupHeaderCommand;
import com.jaspersoft.studio.model.band.command.ReorderBandCommand;
import com.jaspersoft.studio.model.command.CreateElementCommand;
import com.jaspersoft.studio.model.command.CreateElementGroupCommand;
import com.jaspersoft.studio.model.command.DeleteElementCommand;
import com.jaspersoft.studio.model.command.DeleteElementGroupCommand;
import com.jaspersoft.studio.model.command.OrphanElementCommand;
import com.jaspersoft.studio.model.command.OrphanElementGroupCommand;
import com.jaspersoft.studio.model.command.ReorderElementCommand;
import com.jaspersoft.studio.model.command.ReorderElementGroupCommand;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.dataset.command.CreateDatasetCommand;
import com.jaspersoft.studio.model.dataset.command.DeleteDatasetCommand;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.field.MFields;
import com.jaspersoft.studio.model.field.command.CreateFieldCommand;
import com.jaspersoft.studio.model.field.command.DeleteFieldCommand;
import com.jaspersoft.studio.model.field.command.ReorderFieldCommand;
import com.jaspersoft.studio.model.group.MGroup;
import com.jaspersoft.studio.model.group.MGroups;
import com.jaspersoft.studio.model.group.command.CreateGroupCommand;
import com.jaspersoft.studio.model.group.command.DeleteGroupCommand;
import com.jaspersoft.studio.model.group.command.ReorderGroupCommand;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.model.parameter.MParameters;
import com.jaspersoft.studio.model.parameter.command.CreateParameterCommand;
import com.jaspersoft.studio.model.parameter.command.DeleteParameterCommand;
import com.jaspersoft.studio.model.parameter.command.ReorderParameterCommand;
import com.jaspersoft.studio.model.scriptlet.MScriptlet;
import com.jaspersoft.studio.model.scriptlet.MScriptlets;
import com.jaspersoft.studio.model.scriptlet.command.CreateScriptletCommand;
import com.jaspersoft.studio.model.scriptlet.command.DeleteScriptletCommand;
import com.jaspersoft.studio.model.scriptlet.command.ReorderScriptletCommand;
import com.jaspersoft.studio.model.sortfield.MSortField;
import com.jaspersoft.studio.model.sortfield.MSortFields;
import com.jaspersoft.studio.model.sortfield.command.CreateSortFieldCommand;
import com.jaspersoft.studio.model.sortfield.command.DeleteSortFieldCommand;
import com.jaspersoft.studio.model.sortfield.command.ReorderSortFieldCommand;
import com.jaspersoft.studio.model.style.MConditionalStyle;
import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.model.style.MStyleTemplate;
import com.jaspersoft.studio.model.style.MStyles;
import com.jaspersoft.studio.model.style.command.CreateConditionalStyleCommand;
import com.jaspersoft.studio.model.style.command.CreateStyleCommand;
import com.jaspersoft.studio.model.style.command.CreateStyleTemplateCommand;
import com.jaspersoft.studio.model.style.command.DeleteConditionalStyleCommand;
import com.jaspersoft.studio.model.style.command.DeleteStyleCommand;
import com.jaspersoft.studio.model.style.command.OrphanConditionalStyleCommand;
import com.jaspersoft.studio.model.style.command.ReorderConditionalStyleCommand;
import com.jaspersoft.studio.model.style.command.ReorderStyleCommand;
import com.jaspersoft.studio.model.style.command.ReorderStyleTemplateCommand;
import com.jaspersoft.studio.model.textfield.MPageXofY;
import com.jaspersoft.studio.model.textfield.command.CreatePageXofYCommand;
import com.jaspersoft.studio.model.variable.MVariable;
import com.jaspersoft.studio.model.variable.MVariables;
import com.jaspersoft.studio.model.variable.command.CreateVariableCommand;
import com.jaspersoft.studio.model.variable.command.DeleteVariableCommand;
import com.jaspersoft.studio.model.variable.command.ReorderVariableCommand;

/**
 * A factory for creating OutlineTreeEditPart objects.
 */
public class OutlineTreeEditPartFactory implements EditPartFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart editPart = null;
		if (model instanceof MBand)
			editPart = new AContainerTreeEditPart();
		else if (model instanceof MFrame)
			editPart = new AContainerTreeEditPart();
		else if (model instanceof MElementGroup)
			editPart = new AContainerTreeEditPart();
		// styles
		else if (model instanceof MStyles)
			editPart = new AContainerTreeEditPart();
		else if (model instanceof MStyle)
			editPart = new AContainerTreeEditPart();
		// parameters
		else if (model instanceof MParameters)
			editPart = new AContainerTreeEditPart();
		// variables
		else if (model instanceof MVariables)
			editPart = new AContainerTreeEditPart();
		// fields
		else if (model instanceof MFields)
			editPart = new AContainerTreeEditPart();
		else if (model instanceof MSortFields)
			editPart = new AContainerTreeEditPart();
		else if (model instanceof MGroups)
			editPart = new AContainerTreeEditPart();
		// scriptlets
		else if (model instanceof MScriptlets)
			editPart = new AContainerTreeEditPart();
		else if (model instanceof MReport)
			editPart = new AContainerTreeEditPart();
		else
			editPart = new ATreeEditPart();
		if (editPart != null)
			editPart.setModel(model);
		return editPart;
	}

	/**
	 * Gets the delete command.
	 * 
	 * @param parent
	 *          the parent
	 * @param child
	 *          the child
	 * @return the delete command
	 */
	public static Command getDeleteCommand(ANode parent, ANode child) {
		if (child instanceof MGraphicElement) {
			return new DeleteElementCommand(parent, (MGraphicElement) child);
		} else if (child instanceof MElementGroup) {
			return new DeleteElementGroupCommand(parent, (MElementGroup) child);
		} else if (child instanceof MConditionalStyle) {
			return new DeleteConditionalStyleCommand((MStyle) parent, (MConditionalStyle) child);
		} else if (child instanceof MStyle) {
			return new DeleteStyleCommand((MStyles) parent, (MStyle) child);
		} else if (child instanceof MParameter) {
			JRDesignParameter p = (JRDesignParameter) child.getValue();
			if (!p.isSystemDefined())
				return new DeleteParameterCommand((MParameters) parent, (MParameter) child);
		} else if (child instanceof MField) {
			return new DeleteFieldCommand((MFields) parent, (MField) child);
		} else if (child instanceof MSortField) {
			return new DeleteSortFieldCommand((MSortFields) parent, (MSortField) child);
		} else if (child instanceof MGroup) {
			return new DeleteGroupCommand((MGroups) parent, (MGroup) child);
		} else if (child instanceof MVariable) {
			JRDesignVariable p = (JRDesignVariable) child.getValue();
			if (!p.isSystemDefined())
				return new DeleteVariableCommand((MVariables) parent, (MVariable) child);
		} else if (child instanceof MScriptlet) {
			return new DeleteScriptletCommand((MScriptlets) parent, (MScriptlet) child);
		} else if (child instanceof MDataset) {
			return new DeleteDatasetCommand((MReport) parent, (MDataset) child);
		} else if (child instanceof MBand && child.getValue() != null) {
			if (child instanceof MBandGroupHeader)
				return new DeleteBandGroupHeaderCommand((MReport) parent, (MBandGroupHeader) child);
			if (child instanceof MBandGroupHeader)
				return new DeleteBandGroupFooterCommand((MReport) parent, (MBandGroupFooter) child);
			if (((MBand) child).getBandType().equals(BandTypeEnum.DETAIL))
				return new DeleteBandDetailCommand(parent, (MBand) child);
			return new DeleteBandCommand(parent, (MBand) child);
		}
		return null;
	}

	/**
	 * Gets the reorder command.
	 * 
	 * @param child
	 *          the child
	 * @param parent
	 *          the parent
	 * @param newIndex
	 *          the new index
	 * @return the reorder command
	 */
	public static Command getReorderCommand(ANode child, ANode parent, int newIndex) {
		if (child instanceof MBand) {
			int minIndex = 0;
			int maxIndex = 0;
			List<INode> p = child.getParent().getChildren();
			if (child instanceof MBandGroupFooter) {
				// adjust index
				JRDesignGroup g = ((MBandGroupFooter) child).getJrGroup();
				for (int i = 0; i < p.size(); i++) {
					ANode n = (ANode) p.get(i);
					if (n instanceof MBandGroupFooter && ((MBandGroupFooter) n).getJrGroup().equals(g)) {
						if (minIndex == 0)
							minIndex = i;
						maxIndex = i;
					}
				}
				if (newIndex >= minIndex && newIndex <= maxIndex)
					return new ReorderBandCommand((MBandGroupFooter) child, newIndex - minIndex);
			}
			if (child instanceof MBandGroupHeader) {
				JRDesignGroup g = ((MBandGroupHeader) child).getJrGroup();
				// adjust index
				for (int i = 0; i < p.size(); i++) {
					ANode n = (ANode) p.get(i);
					if (n instanceof MBandGroupHeader && ((MBandGroupHeader) n).getJrGroup().equals(g)) {
						if (minIndex == 0)
							minIndex = i;
						maxIndex = i;
					}
				}
				if (newIndex >= minIndex && newIndex <= maxIndex)
					return new ReorderBandCommand((MBandGroupHeader) child, newIndex - minIndex);
			}
			if (child instanceof MBand && ((MBand) child).getBandType().equals(BandTypeEnum.DETAIL)) {
				// adjust index
				for (int i = 0; i < p.size(); i++) {
					ANode n = (ANode) p.get(i);
					if (n instanceof MBand && ((MBand) n).getBandType().equals(BandTypeEnum.DETAIL)) {
						if (minIndex == 0)
							minIndex = i;
						maxIndex = i;
					}
				}
				if (newIndex >= minIndex && newIndex <= maxIndex)
					return new ReorderBandCommand((MBand) child, (MReport) parent, newIndex - minIndex);
			}
		} else if (child instanceof MGraphicElement) {
			return new ReorderElementCommand((MGraphicElement) child, parent, newIndex);
		} else if (child instanceof MElementGroup) {
			return new ReorderElementGroupCommand((MElementGroup) child, parent, newIndex);
		} else if (child instanceof MConditionalStyle) {
			if (parent instanceof MStyle)
				return new ReorderConditionalStyleCommand((MConditionalStyle) child, (MStyle) parent, newIndex);
		} else if (child instanceof MStyle) {
			if (parent instanceof MStyles) {
				JRReportTemplate[] templates = child.getJasperDesign().getTemplates();
				if (templates != null && templates.length > 0) {
					if (newIndex > templates.length - 1) {
						// adjust newIndex templates are first
						newIndex -= templates.length;
						return new ReorderStyleCommand((MStyle) child, (MStyles) parent, newIndex);
					}
				} else {
					return new ReorderStyleCommand((MStyle) child, (MStyles) parent, newIndex);
				}
			}
		} else if (child instanceof MStyleTemplate) {
			if (parent instanceof MStyles) {
				JRReportTemplate[] templates = child.getJasperDesign().getTemplates();
				if (templates != null && templates.length > 0 && newIndex < templates.length - 1) {
					return new ReorderStyleTemplateCommand((MStyleTemplate) child, (MStyles) parent, newIndex);
				}
			}
		} else if (child instanceof MParameter) {
			if (parent instanceof MParameters) {
				JRDesignParameter p = (JRDesignParameter) child.getValue();
				if (!p.isSystemDefined()) {
					return new ReorderParameterCommand((MParameter) child, (MParameters) parent, newIndex);
				}
			}
		} else if (child instanceof MField) {
			if (parent instanceof MFields) {
				return new ReorderFieldCommand((MField) child, (MFields) parent, newIndex);
			}
		} else if (child instanceof MSortField) {
			if (parent instanceof MSortFields) {
				return new ReorderSortFieldCommand((MSortField) child, (MSortFields) parent, newIndex);
			}
		} else if (child instanceof MGroup) {
			if (parent instanceof MGroups) {
				return new ReorderGroupCommand((MGroup) child, (MGroups) parent, newIndex);
			}
		} else if (child instanceof MVariable) {
			if (parent instanceof MVariables) {
				JRDesignVariable p = (JRDesignVariable) child.getValue();
				if (!p.isSystemDefined()) {
					return new ReorderVariableCommand((MVariable) child, (MVariables) parent, newIndex);
				}
			}
		} else if (child instanceof MScriptlet) {
			if (parent instanceof MScriptlets) {
				return new ReorderScriptletCommand((MScriptlet) child, (MScriptlets) parent, newIndex);
			}
		}
		return null;
	}

	/**
	 * Gets the creates the command.
	 * 
	 * @param parent
	 *          the parent
	 * @param child
	 *          the child
	 * @param location
	 *          the location
	 * @param newIndex
	 *          the new index
	 * @return the creates the command
	 */
	public static Command getCreateCommand(ANode parent, ANode child, Point location, int newIndex) {
		if (child instanceof MPageXofY) {
			if (parent instanceof MElementGroup)
				return new CreatePageXofYCommand((MElementGroup) parent, (MPageXofY) child, newIndex);
			if (parent instanceof MBand)
				return new CreatePageXofYCommand((MBand) parent, (MPageXofY) child, newIndex);
			if (parent instanceof MFrame)
				return new CreatePageXofYCommand((MFrame) parent, (MPageXofY) child, newIndex);
			if (parent instanceof MReport)
				return new CreatePageXofYCommand(parent, (MPageXofY) child, location, newIndex);
		} else if (child instanceof MGraphicElement) {
			if (parent instanceof MElementGroup)
				return new CreateElementCommand((MElementGroup) parent, (MGraphicElement) child, newIndex);
			if (parent instanceof MBand)
				return new CreateElementCommand((MBand) parent, (MGraphicElement) child, newIndex);
			if (parent instanceof MFrame)
				return new CreateElementCommand((MFrame) parent, (MGraphicElement) child, newIndex);
			if (parent instanceof MReport)
				return new CreateElementCommand(parent, (MGraphicElement) child, location, newIndex);
		} else if (child instanceof MElementGroup) {
			if (parent instanceof MElementGroup)
				return new CreateElementGroupCommand(parent, (MElementGroup) child, newIndex);
			if (parent instanceof MBand)
				return new CreateElementGroupCommand(parent, (MElementGroup) child, newIndex);
			if (parent instanceof MFrame)
				return new CreateElementGroupCommand(parent, (MElementGroup) child, newIndex);
		} else if (child instanceof MConditionalStyle) {
			if (parent instanceof MStyle && !(parent instanceof MConditionalStyle))
				return new CreateConditionalStyleCommand((MStyle) parent, (MConditionalStyle) child, location, newIndex);
		} else if (child instanceof MStyle) {
			if (parent instanceof MStyles)
				return new CreateStyleCommand((MStyles) parent, (MStyle) child, location, newIndex);
		} else if (child instanceof MStyleTemplate) {
			if (parent instanceof MStyles)
				return new CreateStyleTemplateCommand((MStyles) parent, (MStyleTemplate) child, location, 0);
		} else if (child instanceof MParameter) {
			if (parent instanceof MParameters) {
				JRDesignParameter p = (JRDesignParameter) child.getValue();
				if (p == null || !p.isSystemDefined())
					return new CreateParameterCommand((MParameters) parent, (MParameter) child, location, newIndex);
			}
		} else if (child instanceof MField) {
			if (parent instanceof MFields)
				return new CreateFieldCommand((MFields) parent, (MField) child, location, newIndex);
		} else if (child instanceof MSortField) {
			if (parent instanceof MSortFields)
				return new CreateSortFieldCommand((MSortFields) parent, (MSortField) child, location, newIndex);
		} else if (child instanceof MGroup) {
			if (parent instanceof MGroups)
				return new CreateGroupCommand((MGroups) parent, (MGroup) child, location, newIndex);
			if (parent instanceof MReport)
				return new CreateGroupCommand((MReport) parent, (MGroup) child, location, newIndex);
		} else if (child instanceof MVariable) {
			if (parent instanceof MVariables) {
				JRDesignVariable p = (JRDesignVariable) child.getValue();
				if (p == null || !p.isSystemDefined())
					return new CreateVariableCommand((MVariables) parent, (MVariable) child, location, newIndex);
			}
		} else if (child instanceof MScriptlet) {
			if (parent instanceof MScriptlets)
				return new CreateScriptletCommand((MScriptlets) parent, (MScriptlet) child, location, newIndex);
		} else if (child instanceof MDataset) {
			if (parent instanceof MReport) {
				return new CreateDatasetCommand((MReport) parent, (MDataset) child, location, newIndex);
			}
		} else if (child instanceof MBand) {
			if (parent instanceof MBandGroupHeader)
				return new CreateBandGroupHeaderCommand((MBandGroupHeader) parent);
			if (parent instanceof MBandGroupFooter)
				return new CreateBandGroupFooterCommand((MBandGroupFooter) parent);
			if (parent instanceof MBand && ((MBand) parent).getBandType().equals(BandTypeEnum.DETAIL))
				return new CreateBandDetailCommand((MBand) parent, (MBand) child);
			if (parent instanceof MBand && parent.getValue() == null)
				return new CreateBandCommand((MBand) parent, (MBand) child);
		}
		return null;
	}

	/**
	 * Gets the orphan command.
	 * 
	 * @param parent
	 *          the parent
	 * @param child
	 *          the child
	 * @return the orphan command
	 */
	public static Command getOrphanCommand(ANode parent, ANode child) {
		if (child instanceof MGraphicElement)
			return new OrphanElementCommand(parent, (MGraphicElement) child);
		if (child instanceof MElementGroup)
			return new OrphanElementGroupCommand(parent, (MElementGroup) child);
		if (child instanceof MConditionalStyle)
			if (parent instanceof MStyle)
				return new OrphanConditionalStyleCommand((MStyle) parent, (MConditionalStyle) child);
		return UnexecutableCommand.INSTANCE;
	}
}
