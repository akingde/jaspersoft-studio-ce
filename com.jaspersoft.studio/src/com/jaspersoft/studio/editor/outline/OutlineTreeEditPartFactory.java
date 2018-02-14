/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.callout.MCallout;
import com.jaspersoft.studio.callout.command.CreateCalloutCommand;
import com.jaspersoft.studio.callout.command.DeleteCalloutCommand;
import com.jaspersoft.studio.callout.pin.MPin;
import com.jaspersoft.studio.callout.pin.command.DeletePinCommand;
import com.jaspersoft.studio.editor.expression.EditElementExpressionCommand;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.outline.actions.field.ShowFieldsTreeAction;
import com.jaspersoft.studio.editor.outline.actions.field.SortFieldsAction;
import com.jaspersoft.studio.editor.outline.part.ContainerTreeEditPart;
import com.jaspersoft.studio.editor.outline.part.DatasetElementsTreeEditPart;
import com.jaspersoft.studio.editor.outline.part.NotDragableContainerTreeEditPart;
import com.jaspersoft.studio.editor.outline.part.NotDragableTreeEditPart;
import com.jaspersoft.studio.editor.outline.part.OpenableContainerTreeEditPart;
import com.jaspersoft.studio.editor.outline.part.OpenableNotDraggableContainerTreeEditPart;
import com.jaspersoft.studio.editor.outline.part.TreeEditPart;
import com.jaspersoft.studio.editor.tools.CompositeElementManager;
import com.jaspersoft.studio.editor.tools.MCompositeElement;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.IDragable;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MPage;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.band.MBandGroupFooter;
import com.jaspersoft.studio.model.band.MBandGroupHeader;
import com.jaspersoft.studio.model.band.command.CreateBandCommand;
import com.jaspersoft.studio.model.band.command.CreateBandGroupFooterCommand;
import com.jaspersoft.studio.model.band.command.CreateBandGroupHeaderCommand;
import com.jaspersoft.studio.model.band.command.DeleteBandCommand;
import com.jaspersoft.studio.model.band.command.DeleteBandDetailCommand;
import com.jaspersoft.studio.model.band.command.DeleteBandGroupFooterCommand;
import com.jaspersoft.studio.model.band.command.DeleteBandGroupHeaderCommand;
import com.jaspersoft.studio.model.band.command.ReorderBandCommandByIndex;
import com.jaspersoft.studio.model.command.CreateE4ObjectCommand;
import com.jaspersoft.studio.model.command.CreateElementCommand;
import com.jaspersoft.studio.model.command.CreateElementGroupCommand;
import com.jaspersoft.studio.model.command.CreateFieldInEditorCommand;
import com.jaspersoft.studio.model.command.DeleteElementCommand;
import com.jaspersoft.studio.model.command.DeleteElementGroupCommand;
import com.jaspersoft.studio.model.command.NoActionCommand;
import com.jaspersoft.studio.model.command.OrphanElementCommand;
import com.jaspersoft.studio.model.command.OrphanElementGroupCommand;
import com.jaspersoft.studio.model.command.ReorderElementCommand;
import com.jaspersoft.studio.model.command.ReorderElementGroupCommand;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.dataset.command.CreateDatasetCommand;
import com.jaspersoft.studio.model.dataset.command.DeleteDatasetCommand;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.field.MFields;
import com.jaspersoft.studio.model.field.MFieldsContainer;
import com.jaspersoft.studio.model.field.command.CreateFieldCommand;
import com.jaspersoft.studio.model.field.command.CreateFieldsContainerCommand;
import com.jaspersoft.studio.model.field.command.DeleteFieldCommand;
import com.jaspersoft.studio.model.field.command.DeleteFieldsContainerCommand;
import com.jaspersoft.studio.model.field.command.DoNothingCommand;
import com.jaspersoft.studio.model.field.command.ReorderFieldCommand;
import com.jaspersoft.studio.model.frame.MFrame;
import com.jaspersoft.studio.model.group.MGroup;
import com.jaspersoft.studio.model.group.MGroups;
import com.jaspersoft.studio.model.group.command.CreateGroupCommand;
import com.jaspersoft.studio.model.group.command.CreateMainGroupCommand;
import com.jaspersoft.studio.model.group.command.DeleteGroupCommand;
import com.jaspersoft.studio.model.group.command.ReorderGroupCommand;
import com.jaspersoft.studio.model.image.MImage;
import com.jaspersoft.studio.model.image.command.CreateImageCommand;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.model.parameter.MParameterSystem;
import com.jaspersoft.studio.model.parameter.MParameters;
import com.jaspersoft.studio.model.parameter.command.CreateParameterCommand;
import com.jaspersoft.studio.model.parameter.command.DeleteParameterCommand;
import com.jaspersoft.studio.model.parameter.command.ReorderParameterCommand;
import com.jaspersoft.studio.model.scriptlet.MScriptlet;
import com.jaspersoft.studio.model.scriptlet.MScriptlets;
import com.jaspersoft.studio.model.scriptlet.MSystemScriptlet;
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
import com.jaspersoft.studio.model.style.MStylesTemplate;
import com.jaspersoft.studio.model.style.command.CreateConditionalStyleCommand;
import com.jaspersoft.studio.model.style.command.CreateStyleCommand;
import com.jaspersoft.studio.model.style.command.CreateStyleTemplateCommand;
import com.jaspersoft.studio.model.style.command.DeleteConditionalStyleCommand;
import com.jaspersoft.studio.model.style.command.DeleteStyleCommand;
import com.jaspersoft.studio.model.style.command.DeleteStyleTemplateCommand;
import com.jaspersoft.studio.model.style.command.OrphanConditionalStyleCommand;
import com.jaspersoft.studio.model.style.command.ReorderConditionalStyleCommand;
import com.jaspersoft.studio.model.style.command.ReorderStyleCommand;
import com.jaspersoft.studio.model.style.command.ReorderStyleTemplateCommand;
import com.jaspersoft.studio.model.subreport.MSubreport;
import com.jaspersoft.studio.model.subreport.command.CreateSubreportCommand;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.model.textfield.MPageXofY;
import com.jaspersoft.studio.model.textfield.MPercentage;
import com.jaspersoft.studio.model.textfield.command.CreatePageXofYCommand;
import com.jaspersoft.studio.model.textfield.command.CreatePercentageCommand;
import com.jaspersoft.studio.model.variable.MVariable;
import com.jaspersoft.studio.model.variable.MVariableSystem;
import com.jaspersoft.studio.model.variable.MVariables;
import com.jaspersoft.studio.model.variable.command.CreateVariableCommand;
import com.jaspersoft.studio.model.variable.command.DeleteVariableCommand;
import com.jaspersoft.studio.model.variable.command.ReorderVariableCommand;
import com.jaspersoft.studio.plugin.ExtensionManager;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JRReportTemplate;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.type.BandTypeEnum;

/*
 * A factory for creating OutlineTreeEditPart objects.
 */
public class OutlineTreeEditPartFactory implements EditPartFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,
	 * java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		ExtensionManager m = JaspersoftStudioPlugin.getExtensionManager();
		EditPart editPart = m.createTreeEditPart(context, model);
		if (editPart == null) {
			if (model instanceof MCallout || model instanceof MPin)
				return null;
			if (model instanceof IDragable) {
				if (model instanceof IContainerEditPart) {
					editPart = new ContainerTreeEditPart();
				} else if (model instanceof MTextField) {
					editPart = createTextFieldTreeEditPart();
				} else if (model instanceof MSubreport) {
					editPart = new OpenableContainerTreeEditPart();
				} else if (model instanceof MImage) {
					editPart = new OpenableContainerTreeEditPart();
				} else if (model instanceof MGraphicElement) {
					editPart = new ContainerTreeEditPart();
				} else if (model instanceof MField || model instanceof MParameter || model instanceof MVariable) {
					editPart = new DatasetElementsTreeEditPart();
				} else {
					editPart = new TreeEditPart();
				}
			} else {
				if (model instanceof MStyleTemplate) {
					editPart = new OpenableNotDraggableContainerTreeEditPart();
				} else if (model instanceof IContainerEditPart) {
					editPart = new NotDragableContainerTreeEditPart();
				} else if (model instanceof MGraphicElement) {
					editPart = new NotDragableContainerTreeEditPart();
				} else {
					editPart = new NotDragableTreeEditPart();
				}
			}
		}
		if (editPart != null)
			editPart.setModel(model);
		return editPart;
	}

	/**
	 * Create the edit part for a text field element, allowing to open the
	 * expression editor with a double click on the outline
	 */
	protected EditPart createTextFieldTreeEditPart() {
		return new OpenableContainerTreeEditPart() {

			@Override
			public void performRequest(Request req) {
				if (RequestConstants.REQ_OPEN.equals(req.getType())) {
					Command cmd = null;
					MTextField textfield = (MTextField) getModel();
					cmd = new EditElementExpressionCommand(textfield, JRDesignTextField.PROPERTY_EXPRESSION) {
						@Override
						public boolean canExecute() {
							return super.canExecute() && this.showDialog() == Window.OK;
						}
					};
					getViewer().getEditDomain().getCommandStack().execute(cmd);
				}
			}

			@Override
			public boolean understandsRequest(Request req) {
				return RequestConstants.REQ_OPEN.equals(req.getType());
			}
		};
	}

	/**
	 * Gets the delete command.
	 * 
	 * @param parent
	 *            the parent
	 * @param child
	 *            the child
	 * @return the delete command
	 */
	public static Command getDeleteCommand(ANode parent, ANode child) {
		ExtensionManager m = JaspersoftStudioPlugin.getExtensionManager();
		Command c = m.getDeleteCommand(parent, child);
		if (c != null)
			return c;

		if (child instanceof MCallout)
			return new DeleteCalloutCommand(parent, (MCallout) child);
		if (child instanceof MPin)
			return new DeletePinCommand((MCallout) child.getParent(), (MPin) child);

		if (child instanceof MGraphicElement && !(parent instanceof MPage)) {
			if (child.getValue() != null)
				return new DeleteElementCommand((MGraphicElement) child);
		} else if (child instanceof MElementGroup) {
			return new DeleteElementGroupCommand(parent, (MElementGroup) child);
		} else if (child instanceof MConditionalStyle) {
			return new DeleteConditionalStyleCommand((MStyle) parent, (MConditionalStyle) child);
		} else if (child instanceof MStyleTemplate) {
			if (parent instanceof MStyles)
				return new DeleteStyleTemplateCommand((MStyles) parent, (MStyleTemplate) child);
		} else if (child instanceof MStyle) {
			if (parent instanceof MStyles)
				return new DeleteStyleCommand((MStyles) parent, (JRDesignStyle) child.getValue());
		} else if (child instanceof MParameter) {
			JRDesignParameter p = (JRDesignParameter) child.getValue();
			if (!p.isSystemDefined()) {
				MParameter srcNode = (MParameter) child;
				JasperReportsConfiguration jConfig = srcNode.getJasperConfiguration();
				return new DeleteParameterCommand(jConfig, (JRDesignDataset) parent.getValue(), srcNode.getValue(),
						false);
			}
		} else if (child instanceof MField) {
			MField srcNode = (MField) child;
			JasperReportsConfiguration jConfig = srcNode.getJasperConfiguration();
			return new DeleteFieldCommand(jConfig, (JRDesignDataset) parent.getValue(), srcNode.getValue(), false);
		} else if (child instanceof MFieldsContainer) {
			MFieldsContainer srcNode = (MFieldsContainer) child;
			JasperReportsConfiguration jConfig = srcNode.getJasperConfiguration();
			return new DeleteFieldsContainerCommand(jConfig, (JRDesignDataset) parent.getValue(), srcNode.getKey(),
					parent, DeleteFieldsContainerCommand.DELETE);
		} else if (child instanceof MSortField) {
			return new DeleteSortFieldCommand((MSortFields) parent, (MSortField) child);
		} else if (child instanceof MGroup) {
			return new DeleteGroupCommand((MGroups) parent, (MGroup) child);
		} else if (child instanceof MVariable) {
			JRDesignVariable p = (JRDesignVariable) child.getValue();
			if (!p.isSystemDefined()) {
				MVariable srcNode = (MVariable) child;
				JasperReportsConfiguration jConfig = srcNode.getJasperConfiguration();
				return new DeleteVariableCommand(jConfig, (JRDesignDataset) parent.getValue(), srcNode.getValue(),
						false);
			}
		} else if (child instanceof MScriptlet) {
			return new DeleteScriptletCommand((MScriptlets) parent, (MScriptlet) child);
		} else if (child instanceof MDataset && parent instanceof MReport) {
			return new DeleteDatasetCommand((MReport) parent, (MDataset) child);
		} else if (child instanceof MBand && child.getValue() != null) {
			if (child instanceof MBandGroupHeader)
				return new DeleteBandGroupHeaderCommand((MReport) parent, (MBandGroupHeader) child);
			if (child instanceof MBandGroupFooter)
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
	 *            the child
	 * @param parent
	 *            the parent
	 * @param newIndex
	 *            the new index
	 * @return the reorder command
	 */
	public static Command getReorderCommand(ANode child, ANode parent, int newIndex) {
		// System.out.println("reorder: " + parent + " - " + child);
		ExtensionManager m = JaspersoftStudioPlugin.getExtensionManager();
		Command c = m.getReorderCommand(child, parent, newIndex);
		if (c != null)
			return c;

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
					return new ReorderBandCommandByIndex((MBandGroupFooter) child, newIndex - minIndex);
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
					return new ReorderBandCommandByIndex((MBandGroupHeader) child, newIndex - minIndex);
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
					return new ReorderBandCommandByIndex((MBand) child, (MReport) parent, newIndex - minIndex);
			}
		} else if (child instanceof MGraphicElement && !(parent instanceof MPage)) {
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
					return new ReorderParameterCommand((MParameter) child, (MParameters<?>) parent, newIndex);
				}
			}
		} else if (child instanceof MFieldsContainer) {
			if (parent instanceof MField)
				parent = parent.getParent();
			if (parent instanceof MFields && parent != child.getParent())
				return new DoNothingCommand();
			return null;
		} else if (child instanceof MField) {
			if (ShowFieldsTreeAction.isFieldsTree(parent.getJasperConfiguration())) {
				if (parent instanceof MField)
					parent = parent.getParent();
				if (parent instanceof MFields && parent != child.getParent())
					return new DoNothingCommand();
				return null;
			}
			if (parent instanceof MFields)
				return new ReorderFieldCommand((MField) child, (MFields) parent, newIndex);
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
				int system = -1;
				for (INode n : parent.getChildren()) {
					if (n instanceof MSystemScriptlet) {
						system++;
						continue;
					}
					break;
				}
				newIndex -= system + 1;

				return new ReorderScriptletCommand((MScriptlet) child, (MScriptlets) parent, newIndex);
			}
		}
		return null;
	}

	public static Command getCreateCommand(ANode parent, ANode child, Rectangle location, int newIndex) {
		return getCreateCommand(parent, child, location, newIndex, null, false);
	}

	public static Command getCreateCommand(ANode parent, ANode child, Rectangle location, int newIndex, Request request,
			boolean typeAdd) {
		ExtensionManager m = JaspersoftStudioPlugin.getExtensionManager();
		Command c = m.getCreateCommand(parent, child, location, newIndex);
		if (c != null)
			return c;
		if (child instanceof MCallout)
			return new CreateCalloutCommand(parent, (MCallout) child, location, newIndex);
		if (child instanceof MFieldsContainer) {
			if (typeAdd && ShowFieldsTreeAction.isFieldsTree(parent.getJasperConfiguration())) {
				if (parent instanceof MField)
					parent = parent.getParent();
				if (parent instanceof MFields) {
					List<ANode> lst = new ArrayList<ANode>();
					lst.add((MFields) child);
					return new CreateFieldsContainerCommand((MFields) parent, lst, typeAdd);
				}
			} else {
				if (parent instanceof MField) {
					List<ANode> lst = new ArrayList<ANode>();
					lst.add((MField) parent);
					return new CreateFieldsContainerCommand(lst);
				}
				if (parent instanceof MFieldsContainer) {
					List<ANode> lst = new ArrayList<ANode>();
					lst.add((MFieldsContainer) parent);
					return new CreateFieldsContainerCommand(lst);
				}
			}
			if (child.getValue() != null && (parent instanceof MGraphicElement || parent instanceof MReport
					|| parent instanceof MBand || parent instanceof MFrame)) {
				return new CreateE4ObjectCommand(child, parent, location, newIndex);
			}
			return null;
		} else if (child instanceof MField) {
			if (typeAdd && ShowFieldsTreeAction.isFieldsTree(parent.getJasperConfiguration())) {
				if (parent instanceof MField)
					parent = parent.getParent();
				if (parent instanceof MFields) {
					List<ANode> lst = new ArrayList<ANode>();
					lst.add((MField) child);
					return new CreateFieldsContainerCommand((MFields) parent, lst, typeAdd);
				}
			}
			if (parent instanceof MFields) {
				if (SortFieldsAction.areFieldsSorted(parent.getJasperConfiguration())
						|| ShowFieldsTreeAction.isFieldsTree(parent.getJasperConfiguration())) {
					return null;
				}
				return new CreateFieldCommand((MFields) parent, (MField) child, newIndex);
			} else if (parent instanceof MField) {
				if (SortFieldsAction.areFieldsSorted(parent.getJasperConfiguration())
						|| ShowFieldsTreeAction.isFieldsTree(parent.getJasperConfiguration())) {
					return null;
				}
				if (parent.getParent() != null) {
					newIndex = parent.getParent().getChildren().indexOf(parent);
					return new CreateFieldCommand((MFields) parent.getParent(), (MField) child, newIndex);
				}
			} else if (child.getValue() != null) {
				ANode targetNode = null;
				if (parent instanceof MReport || parent instanceof MBand || parent instanceof MFrame) {
					targetNode = parent;
				} else if (parent instanceof MGraphicElement) {
					targetNode = parent.getParent();
				}
				if (targetNode != null) {
					int index = newIndex;
					int createdElements = 1;
					if (request instanceof CreateRequest) {
						CreateRequest cRequest = (CreateRequest) request;
						// System.out.println(cRequest.getLocation());
						Object newObject = cRequest.getNewObject();
						createdElements = 0;
						if (newObject instanceof Collection<?>) {
							for (Object createdElement : ((Collection<?>) newObject)) {
								if (createdElement instanceof MField) {
									createdElements++;
								}
							}
						}
						EditPart targetEditPart = targetNode.getFigureEditPart();
						if (targetEditPart instanceof FigureEditPart) {
							IFigure hostFigure2 = ((FigureEditPart) targetEditPart).getFigure();
							Point location2 = cRequest.getLocation().getCopy();
							hostFigure2.translateToRelative(location2);
							Dimension newLocation = location2.getDifference(hostFigure2.getBounds().getTopLeft());
							index = ModelUtils.getBetweenIndex(targetNode,
									new Point(newLocation.width, newLocation.height));
						}
					}
					CreateFieldInEditorCommand cmd = new CreateFieldInEditorCommand((MField) child, targetNode,
							location, index);
					cmd.setCreatedFields(createdElements);
					return cmd;
				}
			}
		} else if (child instanceof MParameterSystem) {
			if (child instanceof MParameter) {
				if (parent instanceof MParameters) {
					JRDesignParameter p = (JRDesignParameter) child.getValue();
					if (p == null || !p.isSystemDefined()) {
						return new CreateParameterCommand((MParameters<?>) parent, (MParameter) child, newIndex);
					}
				} else if (parent instanceof MParameter) {
					JRDesignParameter p = (JRDesignParameter) child.getValue();
					if ((p == null || !p.isSystemDefined()) && parent.getParent() != null) {
						MParameters<?> paramterParent = (MParameters<?>)parent.getParent();
						if (paramterParent.getValue() instanceof JRDesignDataset) {
							newIndex = parent.getParent().getChildren().indexOf(parent);
							return new CreateParameterCommand((MParameters<?>) parent.getParent(), (MParameter) child,
									newIndex);
						}
					}
				}
			}
			if (child.getValue() != null && (parent instanceof MGraphicElement || parent instanceof MReport
					|| parent instanceof MBand || parent instanceof MFrame)) {
				return new CreateE4ObjectCommand(child, parent, location, newIndex);
			}
		} else if (child instanceof MVariableSystem) {
			if (parent instanceof MVariables) {
				JRDesignVariable p = (JRDesignVariable) child.getValue();
				if (p == null || !p.isSystemDefined()) {
					return new CreateVariableCommand((MVariables) parent, (MVariable) child, newIndex);
				}
			} else if (parent instanceof MVariable) {
				JRDesignVariable p = (JRDesignVariable) child.getValue();
				if ((p == null || !p.isSystemDefined()) && parent.getParent() != null) {
					newIndex = parent.getParent().getChildren().indexOf(parent);
					return new CreateVariableCommand((MVariables) parent.getParent(), (MVariable) child, newIndex);
				}
			}
			if (child.getValue() != null && (parent instanceof MGraphicElement || parent instanceof MReport
					|| parent instanceof MBand || parent instanceof MFrame)) {
				return new CreateE4ObjectCommand(child, parent, location, newIndex);
			}
		} else {
			if (child instanceof MConditionalStyle) {
				if (parent instanceof MStyle && parent.getValue() instanceof JRDesignStyle)
					return new CreateConditionalStyleCommand((MStyle) parent, (MConditionalStyle) child, newIndex);
				return null;
			} else if (child instanceof MStyle) {
				if (parent instanceof MStyles)
					return new CreateStyleCommand((MStyles) parent, (MStyle) child, newIndex);
				if (parent instanceof MGraphicElement && child.getValue() != null && !(parent instanceof IContainer)) {
					SetValueCommand cmd = new SetValueCommand();
					cmd.setTarget((IPropertySource) parent);
					cmd.setPropertyId(JRDesignElement.PROPERTY_PARENT_STYLE);
					JRStyle style = (JRStyle) child.getValue();
					cmd.setPropertyValue(style.getName());
					return cmd;
				}
				if (parent instanceof MStylesTemplate && ((MStylesTemplate) parent).isEditable()) {
					return new com.jaspersoft.studio.editor.style.command.CreateStyleCommand((MStylesTemplate) parent,
							(MStyle) child, -1);
				}
				if (parent instanceof MReport && location != null) {
					MGraphicElement element = ModelUtils.getElement4Point(parent, new Point(location.x, location.y));
					if (element != null) {
						SetValueCommand cmd = new SetValueCommand();
						cmd.setTarget(element);
						cmd.setPropertyId(JRDesignElement.PROPERTY_PARENT_STYLE);
						JRStyle style = (JRStyle) child.getValue();
						cmd.setPropertyValue(style.getName());
						return cmd;
					}
				}
			}
		}
		// If it is a custom tool require the command to the toolmanger
		if (child instanceof MCompositeElement) {
			return CompositeElementManager.INSTANCE.getCommand(parent, (MCompositeElement) child, location, newIndex);
		} else if (child instanceof MPageXofY) {
			if (parent instanceof MElementGroup)
				return new CreatePageXofYCommand((MElementGroup) parent, (MPageXofY) child, location, newIndex);
			if (parent instanceof MBand)
				return new CreatePageXofYCommand((MBand) parent, (MPageXofY) child, location, newIndex);
			if (parent instanceof MFrame)
				return new CreatePageXofYCommand((MFrame) parent, (MPageXofY) child, location, newIndex);
			if (parent instanceof MReport)
				return new CreatePageXofYCommand(parent, (MPageXofY) child, location, newIndex);
		} else if (child instanceof MPercentage) {
			if (parent instanceof MElementGroup)
				return new CreatePercentageCommand((MElementGroup) parent, (MPercentage) child, location, newIndex);
			if (parent instanceof MBand)
				return new CreatePercentageCommand((MBand) parent, (MPercentage) child, location, newIndex);
			if (parent instanceof MFrame)
				return new CreatePercentageCommand((MFrame) parent, (MPercentage) child, location, newIndex);
			if (parent instanceof MReport)
				return new CreatePercentageCommand(parent, (MPercentage) child, location, newIndex);
		} else if (child instanceof MSubreport) {
			if (parent instanceof MElementGroup)
				return new CreateSubreportCommand((MElementGroup) parent, (MGraphicElement) child, location, newIndex);
			if (parent instanceof MBand)
				return new CreateSubreportCommand((MBand) parent, (MGraphicElement) child, location, newIndex);
			if (parent instanceof MFrame)
				return new CreateSubreportCommand((MFrame) parent, (MGraphicElement) child, location, newIndex);
			if (parent instanceof MReport)
				return new CreateSubreportCommand(parent, (MGraphicElement) child, location, newIndex);

			if (parent instanceof IGroupElement) {
				return new CreateSubreportCommand(parent, (MGraphicElement) child, location, newIndex);
			}
		} else if (child instanceof MImage) {
			if (parent instanceof MElementGroup)
				return new CreateImageCommand((MElementGroup) parent, (MGraphicElement) child, location, newIndex);
			if (parent instanceof MBand)
				return new CreateImageCommand((MBand) parent, (MGraphicElement) child, location, newIndex);
			if (parent instanceof MFrame)
				return new CreateImageCommand((MFrame) parent, (MGraphicElement) child, location, newIndex);
			if (parent instanceof MReport)
				return new CreateImageCommand(parent, (MGraphicElement) child, location, newIndex);

			if (parent instanceof IGroupElement) {
				return new CreateElementCommand(parent, (MGraphicElement) child, location, newIndex);
			}
		} else if (child instanceof MGraphicElement) {
			if (parent instanceof MElementGroup)
				return new CreateElementCommand((MElementGroup) parent, (MGraphicElement) child, location, newIndex);
			if (parent instanceof MBand)
				return new CreateElementCommand((MBand) parent, (MGraphicElement) child, location, newIndex);
			if (parent instanceof MFrame)
				return new CreateElementCommand((MFrame) parent, (MGraphicElement) child, location, newIndex);
			if (parent instanceof MReport)
				return new CreateElementCommand(parent, (MGraphicElement) child, location, newIndex);

			if (parent instanceof IGroupElement) {
				return new CreateElementCommand(parent, (MGraphicElement) child, location, newIndex);
			}
		} else if (child instanceof MElementGroup) {
			if (parent instanceof MElementGroup)
				return new CreateElementGroupCommand(parent, (MElementGroup) child, newIndex);
			if (parent instanceof MBand)
				return new CreateElementGroupCommand(parent, (MElementGroup) child, newIndex);
			if (parent instanceof MFrame)
				return new CreateElementGroupCommand(parent, (MElementGroup) child, newIndex);
		} else if (child instanceof MConditionalStyle && ((APropertyNode) child).isEditable()) {
			if (parent instanceof MStyle && !(parent instanceof MConditionalStyle))
				return new CreateConditionalStyleCommand((MStyle) parent, (MConditionalStyle) child, newIndex);
		} else if (child instanceof MStyleTemplate && ((APropertyNode) child).isEditable()) {
			if (parent instanceof MStyles)
				return new CreateStyleTemplateCommand((MStyles) parent, (MStyleTemplate) child, 0);
		} else if (child instanceof MSortField) {
			if (parent instanceof MSortFields) {
				JRDesignDataset ds = (JRDesignDataset) parent.getValue();
				if ((ds.getVariablesList().size() + ds.getFieldsList().size()) >= parent.getChildren().size())
					return new CreateSortFieldCommand((MSortFields) parent, (MSortField) child, newIndex);
			}
		} else if (child instanceof MGroup) {
			if (parent instanceof MGroups)
				return new CreateGroupCommand((MGroups) parent, (MGroup) child, newIndex);
			if (parent instanceof MReport)
				return new CreateMainGroupCommand((MReport) parent, (MGroup) child, newIndex);
		} else if (child instanceof MScriptlet) {
			if (parent instanceof MScriptlets)
				return new CreateScriptletCommand((MScriptlets) parent, (MScriptlet) child, newIndex);
		} else if (child instanceof MDataset) {
			if (parent instanceof MReport) {
				return new CreateDatasetCommand((MReport) parent, (MDataset) child, newIndex);
			}
		} else if (child instanceof MBand) {
			if (parent instanceof MBandGroupHeader)
				return new CreateBandGroupHeaderCommand((MBandGroupHeader) parent);
			if (parent instanceof MBandGroupFooter)
				return new CreateBandGroupFooterCommand((MBandGroupFooter) parent);
			if (parent instanceof MBand && parent.getValue() == null)
				return new CreateBandCommand((MBand) parent, (MBand) child);
		}
		return null;
	}

	/**
	 * Gets the orphan command.
	 * 
	 * @param parent
	 *            the parent
	 * @param child
	 *            the child
	 * @return the orphan command
	 */
	public static Command getOrphanCommand(ANode parent, ANode child) {
		ExtensionManager m = JaspersoftStudioPlugin.getExtensionManager();
		Command c = m.getOrphanCommand(parent, child);
		if (c != null)
			return c;

		if (child instanceof MField) {
			if (ShowFieldsTreeAction.isFieldsTree(parent.getJasperConfiguration())) {
				if (parent instanceof MField)
					parent = parent.getParent();
				if (parent instanceof MFields)
					return new DoNothingCommand();
				return null;
			}
			return new NoActionCommand();
		} else if (child instanceof MFieldsContainer)
			return new DoNothingCommand();
		if (child instanceof MParameterSystem)
			return new NoActionCommand();
		// return new OrphanParameterCommand(parent, (MParameter) child);
		if (child instanceof MVariableSystem)
			return new NoActionCommand();
		// This condition must be placed before the MStyle check, since
		// MConditionalStyle is also
		// an mstyle and so it will be catched in the mstyle branch
		if (child instanceof MConditionalStyle)
			if (parent instanceof MStyle)
				return new OrphanConditionalStyleCommand((MStyle) parent, (MConditionalStyle) child);
		if (child instanceof MStyle)
			return new NoActionCommand();

		if (child instanceof MGraphicElement)
			return new OrphanElementCommand(parent, (MGraphicElement) child);
		if (child instanceof MElementGroup)
			return new OrphanElementGroupCommand(parent, (MElementGroup) child);
		return UnexecutableCommand.INSTANCE;
	}
}
