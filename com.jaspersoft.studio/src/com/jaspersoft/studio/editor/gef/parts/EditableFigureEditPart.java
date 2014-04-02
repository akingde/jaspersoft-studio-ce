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
package com.jaspersoft.studio.editor.gef.parts;

import java.util.List;

import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;

import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.part.MultiPageEditorSite;

import com.jaspersoft.studio.editor.IJROBjectEditor;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.ElementEditPolicy;
import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.editor.report.ReportContainer;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGraphicalPropertiesHandler;
import com.jaspersoft.studio.model.INode;

public class EditableFigureEditPart extends FigureEditPart {
	
	/**
	 * Special command used when an element that could  have an external 
	 * editor opened it deleted. Before to delete the element ask to all
	 * its children to close their editor if they are opened. This is useful
	 * when it is deleted an element that has a children with the editor 
	 * opened, to avoid to have an editor on an not existing element
	 * 
	 * @author Orlandin Marco
	 *
	 */
	protected class CloseSubeditorsCommand extends Command{
		
		/**
		 * The command to delete the parent element
		 */
		private Command deleteCommand;
		
		/**
		 * The parent element
		 */
		private INode parent;
		
		/**
		 * Create the command to close the editor (even for its children) of an element that will be deleted
		 * 
		 * @param deleteCommand the command to delete the element
		 * @param parent the element that will be deleted
		 */
		public CloseSubeditorsCommand(Command deleteCommand, INode parent){
			this.deleteCommand = deleteCommand;
			this.parent = parent;
		}
		
		/**
		 * Return a list of children of a model. If the model support it 
		 * it will be initialized (this is done for the element that dosen't normally keep
		 * a list of children inside, like table, crosstab and list element).
		 * 
		 * @param parent the element
		 * @return list of children of the element
		 */
		private List<INode> getChildren(INode parent){
			if (parent instanceof IGraphicalPropertiesHandler){
				return ((IGraphicalPropertiesHandler)parent).initModel();
			} else {
				return parent.getChildren();
			}
		}
		
		/**
		 * It send the event to close the editor recursively to all the children
		 * of the element and then execute the delete command
		 */
		@Override
		public void execute() {
			sendDeleteEvent(getChildren(parent));
			deleteCommand.execute();
		}
		
		/**
		 * Recursively send the close editor event to the passed children and to every
		 * child of them
		 * 
		 * @param children the children
		 */
		private void sendDeleteEvent(List<INode> children){
			if (children == null) return;
			for (INode child : children){
				sendDeleteEvent(getChildren(child));
				if (child.getValue() instanceof JRChangeEventsSupport){
					JRChangeEventsSupport eventElement = (JRChangeEventsSupport)child.getValue();
					eventElement.getEventSupport().firePropertyChange(ReportContainer.CLOSE_EDITOR_PROPERTY, child.getValue(), null);
				}
			}
		}
		
		/**
		 * The undo and execute depends only on the delete command
		 */
		@Override
		public boolean canExecute() {
			return deleteCommand != null && deleteCommand.canExecute();
		}
		
		/**
		 * The undo and execute depends only on the delete command
		 */
		@Override
		public boolean canUndo() {
			return deleteCommand != null && deleteCommand.canUndo();
		}
		
		/**
		 * Simply undo the delete command, the closed editors are not reopened
		 */
		@Override
		public void undo() {
			deleteCommand.undo();
		}
		
	}
	
	/**
	 * Special policy used when the delete of an element is requested, it will use a custom delete command
	 * to close eventually opened editors for a children (or descendant ) element of the deleted one
	 * 
	 * @author Orlandin Marco
	 *
	 */
	protected class CloseSubeditorDeletePolicy extends ElementEditPolicy{
		
		protected Command createDeleteCommand(GroupRequest request) {
			if (getHost() != null && getHost().getParent() != null) {
				Object parent = getHost().getParent().getModel();
				Command deleteCommand = null;
				if (parent != null && parent instanceof ANode) {
					deleteCommand = OutlineTreeEditPartFactory.getDeleteCommand((ANode) parent, (ANode) getHost().getModel());
				}
				
				if (deleteCommand != null){
					deleteCommand = new CloseSubeditorsCommand(deleteCommand, (ANode) getHost().getModel());
				}
				return deleteCommand;
			}
			return null;
		}
		
		public CloseSubeditorDeletePolicy(){
			super();
		}
		
	};
	
	
	@Override
	public void performRequest(Request req) {
		if (RequestConstants.REQ_OPEN.equals(req.getType())) {
			Object value = ((ANode) getModel()).getValue();
			IEditorPart editorPart = ((DefaultEditDomain) getViewer().getEditDomain()).getEditorPart();
			openEditor(value, editorPart, ((ANode) getModel()));
		}
		super.performRequest(req);
	}

	public static void openEditor(final Object val, IEditorPart editor, ANode node) {
		if (editor.getEditorSite() instanceof MultiPageEditorSite) {
			final MultiPageEditorPart mpep = ((MultiPageEditorSite) editor.getEditorSite()).getMultiPageEditor();
			if (mpep instanceof IJROBjectEditor)
				doOpenEditor(val, (IJROBjectEditor) mpep, node);
		} else {
			editor = Workbench.getInstance().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			if (editor instanceof IJROBjectEditor)
				doOpenEditor(val, (IJROBjectEditor) editor, node);
		}
	}

	public static void doOpenEditor(final Object val, final IJROBjectEditor editor, final ANode node) {
		Display.getCurrent().asyncExec(new Runnable() {
			public void run() {
				((IJROBjectEditor) editor).openEditor(val, node);
			}
		});
	}
}
