/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.part;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.background.MBackgrounImage;
import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.editor.outline.actions.SortParametersAction;
import com.jaspersoft.studio.editor.outline.actions.SortVariablesAction;
import com.jaspersoft.studio.editor.outline.editpolicy.ElementEditPolicy;
import com.jaspersoft.studio.editor.outline.editpolicy.ElementTreeEditPolicy;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.editor.report.ReportContainer;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MLockableRefresh;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.parameter.MParameterSystem;
import com.jaspersoft.studio.model.parameter.MParameters;
import com.jaspersoft.studio.model.sortfield.MSortField;
import com.jaspersoft.studio.model.variable.MVariableSystem;
import com.jaspersoft.studio.model.variable.MVariables;
import com.jaspersoft.studio.preferences.DesignerPreferencePage;
import com.jaspersoft.studio.utils.SelectionHelper;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignVariable;

/*
 * The Class ATreeEditPart.
 */
public class TreeEditPart extends AbstractTreeEditPart implements PropertyChangeListener {

	private IResource associatedFile;

	/**
	 * In some cases a feedback can be shown on the edit part on the main editor
	 * that has the same model of the target one. This is the edit part on the
	 * main editor that has the last added feedback.
	 */
	protected EditPart lastEditorFeedback = null;

	public TreeEditPart() {
	}

	/**
	 * Remove the widget of the node, before it check it was not already removed
	 * 
	 * @param childEditPart
	 *            the node to dispose
	 */
	@Override
	protected void removeChildVisual(EditPart childEditPart) {
		TreeEditPart treeEditPart = (TreeEditPart) childEditPart;
		// dispose the old widget if any
		if (treeEditPart.getWidget() != null) {
			treeEditPart.getWidget().dispose();
		}
		treeEditPart.setWidget(null);
	}

	/**
	 * If the request is an add this search an edit part with the same model of
	 * the target one on the main editor and paint a feedback on it. Before to
	 * pain the feedback any previous feedback is removed. All the checks are
	 * done to be sure that the visual editor exist
	 * 
	 * @param request
	 *            the current request
	 */
	protected void showTargetFeedbackOnEditor(Request request) {
		if (RequestConstants.REQ_ADD.equals(request.getType())) {
			EditPart targetPart = getTargetEditPart(request);
			IEditorPart currentEditor = SelectionHelper.getActiveJRXMLEditor();
			if (currentEditor instanceof JrxmlEditor) {
				JrxmlEditor jrxmlEditor = (JrxmlEditor) currentEditor;
				ReportContainer reportContainer = jrxmlEditor.getReportContainer();
				IEditorPart activeEditor = reportContainer.getActiveEditor();
				if (targetPart != null && activeEditor instanceof AbstractVisualEditor) {
					AbstractVisualEditor reportEditor = (AbstractVisualEditor) activeEditor;
					EditPart editorPart = (EditPart) reportEditor.getGraphicalViewer().getEditPartRegistry()
							.get(targetPart.getModel());
					if (editorPart != null) {
						eraseTargetFeedback(request);
						editorPart.showTargetFeedback(request);
						lastEditorFeedback = editorPart;
					}
				}
			}
		}
	}

	/**
	 * If there is a feedback on an editor part then it is removed, otherwise it
	 * dosen't do anything
	 * 
	 * @param request
	 *            the current request
	 */
	protected void eraseTargetFeedbackOnEditor(Request request) {
		if (lastEditorFeedback != null) {
			lastEditorFeedback.eraseTargetFeedback(request);
			lastEditorFeedback = null;
		}
	}

	@Override
	protected void addChild(EditPart child, int index) {
		if (child != null)
			super.addChild(child, index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#activate()
	 */
	@Override
	public void activate() {
		super.activate();
		if (getModel() != null)
			((ANode) getModel()).getPropertyChangeSupport().addPropertyChangeListener(this);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#deactivate()
	 */
	@Override
	public void deactivate() {
		if (getModel() != null)
			((ANode) getModel()).getPropertyChangeSupport().removePropertyChangeListener(this);
		super.deactivate();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractTreeEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ElementEditPolicy());
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new ElementTreeEditPolicy() {
			@Override
			protected Command getMoveCommand(ChangeBoundsRequest req) {
				EditPart parent = getHost().getParent();
				//during the drag and drop the edit parts list can be null inside the request,
				//so we need to check that both the parent and the request is valid. 
				//If the node is a variables or parameters node it check if they are sorted and in that
				//case deny the drag and drop
				if (parent != null && req.getEditParts() != null) {
					Object model = parent.getModel();
					if (model != null){
						if (model.getClass().equals(MVariables.class)){
							MVariables variables = (MVariables)model;
							if (SortVariablesAction.areVariablesSorted(variables.getJasperConfiguration())){
								return UnexecutableCommand.INSTANCE;
							}
						} else if (model.getClass().equals(MParameters.class)){
							MParameters<?> variables = (MParameters<?>)model;
							if (SortParametersAction.areParametersSorted(variables.getJasperConfiguration())){
								return UnexecutableCommand.INSTANCE;
							}
						}
					}
					ChangeBoundsRequest request = new ChangeBoundsRequest(REQ_MOVE_CHILDREN);
					if (req.getEditParts().size() > 1)
						request.setEditParts(req.getEditParts());
					else
						request.setEditParts(getHost());
					request.setLocation(req.getLocation());
					return parent.getCommand(request);
				}
				return UnexecutableCommand.INSTANCE;
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractTreeEditPart#refreshVisuals()
	 */
	@Override
	protected void refreshVisuals() {
		if (getWidget() instanceof Tree)
			return;
		UIUtils.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				TreeItem item = (TreeItem) getWidget();
				ANode node = (ANode) getModel();
				refreshItem(item, node);
			}
		});
	}

	/**
	 * Refresh a specific tree item
	 */
	private void refreshItem(TreeItem item, ANode node) {
		if (node != null && checkTreeItem(item)) {
			if (node.getImagePath() != null) {
				Image image = JaspersoftStudioPlugin.getInstance().getImage(node.getImagePath());
				if (image != null) {
					if (node.getBackground() != null)
						image.setBackground(node.getBackground());
					else {
						if (item != null && item.getParent() != null && item.getParent().getBackground() != null)
							image.setBackground(item.getParent().getBackground());
					}
					setWidgetImage(item, image);
				}
			}
			if (item != null) {
				Color backGround = node.getBackground();
				if (backGround != null) {
					item.setBackground(backGround);
				}
				Color foreGround = node.getForeground();
				if (foreGround != null)
					item.setForeground(foreGround);
			}
			String displayText = node.getDisplayText();
			if (displayText != null) {
				displayText = displayText.replaceAll("(\\r|\\n)+", " ");
				if (displayText.length() > 30 && !(node instanceof MField) && !(node instanceof MSortField))
					displayText = displayText.substring(0, 30) + " ..."; //$NON-NLS-1$
				setWidgetText(item, displayText);
			} else
				setWidgetText(item, "Unknown");
		}
	}

	protected final boolean checkTreeItem(TreeItem widget) {
		return !(widget == null || widget.isDisposed());
	}

	protected void setWidgetText(TreeItem item, String text) {
		if (checkTreeItem(item))
			item.setText(text);
	}

	protected void setWidgetImage(TreeItem item, Image image) {
		if (checkTreeItem(item))
			item.setImage(image);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
	 */
	@Override
	protected List<?> getModelChildren() {
		List<Object> list = new ArrayList<Object>();
		ANode modelNode = (ANode) getModel();
		if (modelNode != null && modelNode.showChildren()) {
			// Check if the default parameters and variables should be hidden
			JasperReportsConfiguration jConfig = modelNode.getJasperConfiguration();
			boolean showDefaults = jConfig != null ? jConfig.getPropertyBoolean(DesignerPreferencePage.P_SHOW_VARIABLES_DEFAULTS, Boolean.TRUE) : true;
			
			//when the node are the variables or the parameters apply special code to show or hide the system defaults and to sort them
			if (modelNode.getClass().equals(MVariables.class)){
				list.addAll(getVariables(jConfig, (MVariables)modelNode, showDefaults));
			} else if (modelNode.getClass().equals(MParameters.class)){
				list.addAll(getParameters(jConfig, (MParameters<?>)modelNode, showDefaults));
			} else {
				for (INode node : modelNode.getChildren()) {
					// The background is never shown inside the outline
					if (!node.getClass().equals(MBackgrounImage.class)) {
						list.add(node);
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * Get the list of the variables, they could be ordered or the system default can be hidden
	 * 
	 * @param jConfig the {@link JasperReportsConfiguration} of the current report, must be not null
	 * @param parentNode the {@link MVariables} node, must be not null
	 * @param showDefaults true if the defaults should be shown, false otherwise
	 * @return a not null list of the variables to show in the correct order
	 */
	protected List<INode> getVariables(JasperReportsConfiguration jConfig, MVariables parentNode, boolean showDefaults) {
		List<INode> result = new ArrayList<INode>();
		List<INode> children = new ArrayList<INode>(parentNode.getChildren());
		if (SortVariablesAction.areVariablesSorted(jConfig)){
			
			Collections.sort(children, new Comparator<INode>() {
				
				@Override
				public int compare(INode o1, INode o2) {
					MVariableSystem var1 = (MVariableSystem)o1;
					MVariableSystem var2 = (MVariableSystem)o2;
					String nameVar1 = (String)var1.getPropertyActualValue(JRDesignVariable.PROPERTY_NAME);
					String nameVar2 = (String)var2.getPropertyActualValue(JRDesignVariable.PROPERTY_NAME);
					return nameVar1.toLowerCase().compareTo(nameVar2.toLowerCase());
				}
			});
		}
		for (INode node : children) {
			if (showDefaults) {
				result.add(node);
			} else if (!node.getClass().equals(MVariableSystem.class)) {
				result.add(node);
			}
		}
		
		return children;
	}
	
	/**
	 * Get the list of the parameters, they could be ordered or the system default can be hidden
	 * 
	 * @param jConfig the {@link JasperReportsConfiguration} of the current report, must be not null
	 * @param parentNode the {@link MParameters} node, must be not null
	 * @param showDefaults true if the defaults should be shown, false otherwise
	 * @return a not null list of the parameters to show in the correct order
	 */
	protected List<INode> getParameters(JasperReportsConfiguration jConfig, MParameters<?> parentNode, boolean showDefaults) {
		List<INode> result = new ArrayList<INode>();
		List<INode> children = new ArrayList<INode>(parentNode.getChildren());
		if (SortParametersAction.areParametersSorted(jConfig)){
			Collections.sort(children, new Comparator<INode>() {
				
				@Override
				public int compare(INode o1, INode o2) {
					MParameterSystem var1 = (MParameterSystem)o1;
					MParameterSystem var2 = (MParameterSystem)o2;
					String nameVar1 = (String)var1.getPropertyActualValue(JRDesignParameter.PROPERTY_NAME);
					String nameVar2 = (String)var2.getPropertyActualValue(JRDesignParameter.PROPERTY_NAME);
					return nameVar1.toLowerCase().compareTo(nameVar2.toLowerCase());
				}
			});
		}
		for (INode node : children) {
			if (showDefaults) {
				result.add(node);
			} else if (!node.getClass().equals(MParameterSystem.class)) {
				result.add(node);
			}
		}
		
		return children;
	}


	/**
	 * Map of EditPart that need a refresh, they can be queued when the refresh
	 * is disabled refreshed at the end. Using an hashset avoid to refresh the
	 * same part more than one time
	 */
	private static HashSet<EditPart> nodeToRefresh = new HashSet<EditPart>();

	/**
	 * Cache the node to check the refresh event
	 */
	private MLockableRefresh refreshReferenceNode = null;

	/**
	 * Return the node to check the refresh event and cache it
	 * 
	 * @return the node to check if the refresh events are enabled or not
	 */
	private MLockableRefresh getLockReferenceNode() {
		if (refreshReferenceNode == null) {
			EditPart root = (EditPart) getRoot().getChildren().get(0);
			ANode modelNode = (ANode) root.getModel();
			refreshReferenceNode = (MLockableRefresh) JSSCompoundCommand.getMainNode(modelNode);
		}
		return refreshReferenceNode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.
	 * PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(JSSCompoundCommand.REFRESH_UI_EVENT)) {
			refreshCached();
			refresh();
			return;
		}
		// FIXME: maybe compare the source of the event with the model of the
		// current part to avoid
		// to refresh this part for an event not generated from the contained jr
		// element
		MLockableRefresh refrenceNode = getLockReferenceNode();
		if (refrenceNode != null && refrenceNode.isRefreshEventIgnored()) {
			nodeToRefresh.add(this);
		} else {
			refresh();
		}
	}

	/**
	 * Refresh all the cached node, avoid to refresh the node that will be
	 * delete (parent null)
	 */
	private void refreshCached() {
		// The refresh should be executed inside the graphic thread to avoid
		// invalid thread access exception, since it involve the painting of
		// editparts and so swt stuff
		UIUtils.getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				synchronized (getLockReferenceNode()) {
					try {
						for (EditPart part : nodeToRefresh) {
							// Check if the part model has a parent, if not the
							// part
							// will be probably removed so avoid to refresh it
							if (((ANode) part.getModel()).getParent() != null) {
								part.refresh();
							}
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					nodeToRefresh.clear();
				}
			}
		});
	}

	@Override
	public Object getAdapter(Class key) {
		if (key == IResource.class || key == IFile.class) {
			if (associatedFile == null) {
				associatedFile = getAssociatedFile();
			}
			return associatedFile;
		}
		return super.getAdapter(key);
	}

	/**
	 * Returns the file associated.
	 * <p>
	 * Given the current edit part belonging to the active JRXML editor (report
	 * designer) the related file is returned.
	 * 
	 * @return the associated file resource
	 */
	public IResource getAssociatedFile() {
		IEditorInput edinput = null;
		if (getViewer() != null && getViewer().getEditDomain() instanceof DefaultEditDomain) {
			IEditorPart ip = ((DefaultEditDomain) getViewer().getEditDomain()).getEditorPart();
			edinput = ip.getEditorInput();
		} else {
			IEditorPart ep = SelectionHelper.getActiveJRXMLEditor();
			if (ep != null)
				edinput = ep.getEditorInput();
		}
		if (edinput instanceof IFileEditorInput) {
			return ((IFileEditorInput) edinput).getFile();
		}
		return null;
	}

}
