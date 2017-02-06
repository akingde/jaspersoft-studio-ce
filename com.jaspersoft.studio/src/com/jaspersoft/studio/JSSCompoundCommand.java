/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.ui.IEditorPart;

import com.jaspersoft.studio.editor.AbstractJRXMLEditor;
import com.jaspersoft.studio.editor.action.copy.SelectElementCommand;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MLockableRefresh;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.utils.SelectionHelper;

import net.sf.jasperreports.engine.design.JRDesignElement;

/**
 * Special compound command that disable the refresh of the editor and of the selection
 * during the execution of the commands, this is done for performance improvements and
 * avoid unnecessary selections
 * 
 * @author Orlandin Marco
 *
 */
public class JSSCompoundCommand extends CompoundCommand {
	
	public static final String REFRESH_UI_EVENT = "refreshUIEvent";

	/**
	 * Node used to go back into the model until the mpage or an mreport (
	 * or more generally an MLockableRefresh) is found. In this class, for every
	 * editor is located the flag to set to avoid unnecessary refresh. If this 
	 * node is null this command works exactly like a compound command
	 */
	private ANode referenceNode = null;
	
	/**
	 * Boolean flag used to know if the editor selection should be preserved
	 * between the execution of the inner commands
	 */
	private boolean restoreSelection = false;
	
	/**
	 * Command used to set the selection ad the end of the inner commands execution.
	 * This is used only when the restoreSelection flag is set to true
	 */
	private SelectElementCommand restoreSelectionCommand = null;
	
	/**
	 * Create an instance of the from a compound command. All the commands inside the compound
	 * command, and eventually its name, will be also present in the new JSSCompoundCommand
	 * 
	 * @param cmd a not null compound command
	 * @param referenceNode a node of the model manipulated by the inner commands.
	 * Can be null, in this case this command works like a standard compound command
	 */
	public JSSCompoundCommand(CompoundCommand cmd, ANode node){
		setLabel(cmd.getLabel());
		for(Object item : cmd.getCommands()){
			add((Command)item);
		}
		this.referenceNode = node;
	}
	
	/**
	 * Create an instance of the class
	 * 
	 * @param referenceNode a node of the model manipulated by the inner commands.
	 * Can be null, in this case this command works like a standard compound command
	 */
	public JSSCompoundCommand(ANode node){
		super();
		this.referenceNode = node;
	}
	
	/**
	 * Create an instance of the class with a name
	 * 
	 * @param referenceNode a node of the model manipulated by the inner commands.
	 * Can be null, in this case this command works like a standard compound command
	 */
	public JSSCompoundCommand(String name, ANode node){
		super(name);
		this.referenceNode = node;
	}
	
	/**
	 * Set the reference node with the value of the parameter
	 * 
	 * @param referenceNode new value for the reference node
	 */
	protected void setReferenceNode(ANode referenceNode){
		this.referenceNode = referenceNode;
	}
	
	/**
	 * Set the reference node node if and only if the actual value
	 * is null. the passed node is used to get the root is possible and
	 * that is used as reference node. This is done because maybe the node
	 * will be deleted by another command in the meantime, so during the execution
	 * it could be outside the hierarchy. The root instead is never deleted
	 * 
	 * @param referenceNode the reference node
	 */
	public void setReferenceNodeIfNull(ANode referenceNode){
		if (this.referenceNode == null) {
			ANode newReferenceNode = referenceNode;
			if (newReferenceNode != null){
				ANode root = (ANode)newReferenceNode.getRoot();
				if (root != null){
					newReferenceNode = root;
				}
			}
			this.referenceNode = newReferenceNode;
		}
	}
	
	/**
	 * Set the reference node node if and only if the actual value
	 * is null and the value is an ANode. the passed node is used to get the root 
	 * is possible and that is used as reference node. This is done because maybe the node
	 * will be deleted by another command in the meantime, so during the execution
	 * it could be outside the hierarchy. The root instead is never deleted
	 * 
	 * @param referenceNode the reference node
	 */
	public void setReferenceNodeIfNull(Object referenceNode){
		if (this.referenceNode == null && referenceNode instanceof ANode){
			ANode newReferenceNode = (ANode)referenceNode;
			if (newReferenceNode != null){
				ANode root = (ANode)newReferenceNode.getRoot();
				if (root != null){
					newReferenceNode = root;
				}
			}
			this.referenceNode = newReferenceNode;
		} 
	}
	
	/**
	 * Return true if for the passed model has the ignore refresh flag active.
	 * If the node dosen't bring to the root of the model then it fallback using the
	 * model inside the opened editor
	 * 
	 * @param node a node of the model
	 * @return true if the refresh must be ignored, false otherwise
	 */
	public static boolean isRefreshEventsIgnored(ANode node){
		ANode mainNode = getMainNode(node);
		if (mainNode == null) mainNode = getMainNode(SelectionHelper.getOpenedRoot());
		if (mainNode instanceof MLockableRefresh) return ((MLockableRefresh)mainNode).isRefreshEventIgnored();
		else return false;
	}
	
	/**
	 * Take a node and go back into it until it find a MLockableRefresh node
	 * 
	 * @param startNode starting node of the model
	 * @return a MLockableNode or null if it can not be found
	 */
	public static ANode getMainNode(INode startNode){
		if (startNode != null){
			if (startNode instanceof MLockableRefresh) return (MLockableRefresh)startNode;
			else if (startNode instanceof MRoot) {
				//I'm on the root, need to go down
				return getMainNode(startNode.getChildren().get(0));
			} else { 
				INode node = startNode.getParent();
				if (node instanceof MRoot) return null;
				else return getMainNode(node);
			}
		}
		return null;
	}
	
	/**
	 * Return the actual reference node
	 * 
	 * @return an ANode, could be null
	 */
	public ANode getReferenceNode(){
		return referenceNode;
	}
	
	/**
	 * Take a node and go back into it until it find a MLockableRefresh node. During
	 * the search the reference node it is updated so when it will be found it will be 
	 * substitute the actual reference node, to speedup the next research
	 * 
	 * @return a MLockableNode or null if it can not be found
	 */
	protected ANode getNode(){
		if (referenceNode != null){
			if (referenceNode instanceof MLockableRefresh) return referenceNode;
			else if (referenceNode instanceof MRoot) {
				//I'm on the root, need to go down
				referenceNode = (ANode)referenceNode.getChildren().get(0);
				return getNode();
			} else {
				referenceNode = referenceNode.getParent();
				if (referenceNode instanceof MRoot) {
					referenceNode = null;
					return null;
				}
				else {
					return getNode();
				}
			}
		}
		return null;
	}
	
	/**
	 * Create a fake command to force the refresh of the editor and outline panels, this override
	 * the disable refresh flag, so calling this the editor area is always updated
	 */
	protected void refreshVisuals(){
		try{
			ANode report = getNode();
			 if (report != null){
				 PropertyChangeEvent event = new PropertyChangeEvent(report.getJasperDesign(), REFRESH_UI_EVENT, null, null);
				 report.getPropertyChangeSupport().firePropertyChange(event);
			 }
		} catch (Exception ex){
			ex.printStackTrace();
			JaspersoftStudioPlugin.getInstance().logError(ex);
		}
	}
	
	/**
	 * Create a fake command to force the refresh of the editor and outline panel on the passed node, this override
	 * the disable refresh flag, so calling this the editor area is always updated
	 */
	public static void forceRefreshVisuals(ANode node){
			if (node != null && node.getValue() != null){
				PropertyChangeEvent event = new PropertyChangeEvent(node.getValue(), REFRESH_UI_EVENT, null, null);
				node.getPropertyChangeSupport().firePropertyChange(event);
			}
	}
	
	/**
	 * Set to ignore or not the refresh on the root of the model of the actual reference node
	 * 
	 * @param value true if the refresh should be disabled, false otherwise
	 */
	protected void setIgnoreEvents(boolean value){
		ANode rootNode = getNode();
		if (rootNode != null){
			if (rootNode instanceof MLockableRefresh) ((MLockableRefresh)rootNode).setIgnoreEvents(value, this);
		}
	}
	
	/**
	 * Get if the events are ignored or not
	 * 
	 * @return true if the refresh should be disabled, false otherwise
	 */
	protected boolean isIgnoreEvents(){
		ANode rootNode = getNode();
		if (rootNode != null){
			if (rootNode instanceof MLockableRefresh) return ((MLockableRefresh)rootNode).isRefreshEventIgnored();
		}
		return false;
	}
	
	/**
	 * If the restoreSelection flag is enable this call will backup the selection
	 * of the current editor an create the command to restore it
	 */
	protected void backupSelection(){
		if (restoreSelection ){
			AbstractJRXMLEditor editor = (AbstractJRXMLEditor)SelectionHelper.getActiveJRXMLEditor();
			if (editor != null){
				IEditorPart designEditor = editor.getActiveInnerEditor();
				if (designEditor instanceof AbstractVisualEditor){
					AbstractVisualEditor visaulEditor = (AbstractVisualEditor)designEditor;
					GraphicalViewer viewer = visaulEditor.getGraphicalViewer();
					if (viewer != null){
						List<?> selectedParts = viewer.getSelectedEditParts();
						ArrayList<JRDesignElement> oldSelection = new ArrayList<JRDesignElement>();
						for(Object obj : selectedParts){
							EditPart part = (EditPart)obj;
							if (part.getModel() instanceof ANode){
								ANode model = (ANode)part.getModel();
								if (model.getValue() instanceof JRDesignElement){
									oldSelection.add((JRDesignElement)model.getValue());
								}
							}
						}
						restoreSelectionCommand = new SelectElementCommand(oldSelection);
					}
				}
			}
		}
	}
	
	/**
	 * If the command to restore the seleciton is available then it 
	 * is executed
	 */
	protected void restoreSelection(){
		if (restoreSelection && restoreSelectionCommand != null){
			restoreSelectionCommand.execute();
		}
	}
	
	/**
	 * Override of the execute command, disable the refresh before the first command
	 * and enable it at the end
	 */
	@Override
	public void execute() {
		if (size() > 0){
			backupSelection();
			List<?> commands = getCommands(); 
			setIgnoreEvents(true);
			for (int i = 0; i < size(); i++) {
				Command cmd = (Command) commands.get(i);
				cmd.execute();
			}
			setIgnoreEvents(false);
			//Since there could be multiple compound command executed at the same time, the refresh
			//is not forced if the actual command is not the last lock removed. In other words the refresh
			//is disable until the last command has finished
			if (!isIgnoreEvents()) refreshVisuals();
			restoreSelection();
		}
	}
	
	/**
	 * Override of the undo command, disable the refresh before the first command
	 * and enable it at the end
	 */
	@Override
	public void undo() {
		if (size() > 0){
			List<?> commands = getCommands();
			setIgnoreEvents(true);
			for (int i = size() - 1; i >= 0; i--){
				((Command) commands.get(i)).undo();
			}
			setIgnoreEvents(false);
			if (!isIgnoreEvents()) refreshVisuals();
			if (restoreSelectionCommand != null){
				restoreSelectionCommand.undo();
				restoreSelectionCommand = null;
			}
		}
	}
	
	/**
	 * Override of the red command, disable the refresh before the first command
	 * and enable it at the end
	 */
	@Override
	public void redo() {
		if (size() > 0){
			backupSelection();
			List<?> commands = getCommands();
			setIgnoreEvents(true);
			for (int i = 0; i < size(); i++){
				((Command) commands.get(i)).redo();
			}
			setIgnoreEvents(false);
			if (!isIgnoreEvents()) refreshVisuals();
			restoreSelection();
		}
	}
	
	/**
	 * Add a command to the head of the list
	 * 
	 * @param command command to add
	 */
	public void addFirst(Command command){
		List<Command> commands = getCommands();
		commands.add(0, command);
	}
	
	/**
	 * Add a list of command to the commands to executed
	 * 
	 * @param commands not null list of commands to add
	 */
	public void addAll(List<Command> commands){
		getCommands().addAll(commands);
	}
	
	/**
	 * Return a list of the internal commands
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Command> getCommands() {
		return (List<Command>)super.getCommands();
	}
	
	/**
	 * Set the selection restore flag. The selection restore works
	 * by storing the selection of the current editor before the execution
	 * of the inner commands and restoring it after the commands are executed.
	 * By default this is not done. Also the backup and the selection of the elements
	 * relay on their JRValue and not on the nodes
	 * 
	 * @param enabled true if the selection backup and restore should be enabled, 
	 * false otherwise
	 */
	public void enableSelectionRestore(boolean enabled){
		this.restoreSelection = enabled;
	}
}




