/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.selection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.SelectionManager;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.band.BandEditPart;
import com.jaspersoft.studio.editor.java2d.J2DScrollingGraphicalViewer;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.preferences.bindings.BindingsPreferencePersistence;

import net.sf.jasperreports.eclipse.IKeyboardEvent;
import net.sf.jasperreports.eclipse.JasperReportsPlugin;

/**
 * Class that extend the default SelectionManager to change the behavior when a selection of more elements 
 * is done. Also it allow to handle a group selection
 * 
 * @author Orlandin Marco
 *
 */
public class JSelectionManager extends SelectionManager {
	
	private static EditPart lastSelected = null;
	
	/**
	 * The current selection, this list is a reference to the same list used in the superclass.
	 * The superclass selection list is private but it is initialized in the interalInitialize method,
	 * keeping a copy of the reference allow to modify the content of the list from this class
	 */
	private List<Object> selection;
	
	/**
	 * The edit part shown when the user is doing a group selection
	 */
	private GroupSelectionEditPart selectionPart;
	
	/**
	 * The keyboard handler used to show or hide a group selection
	 */
	private IKeyboardEvent keyboardEvent = new IKeyboardEvent() {
		
		@Override
		public void keyUp(int keycode) {
			if (getGroupSelectionPart().isActive() && !BindingsPreferencePersistence.isPressed(GroupSelectionEditPart.BINDING_KEY_ID)) {
				setSelection(new StructuredSelection(selection));
			}
		}
		
		@Override
		public void keyDown(int keycode) {
			if (BindingsPreferencePersistence.isPressed(GroupSelectionEditPart.BINDING_KEY_ID)) {
				setSelection(new StructuredSelection(selection));
			}
			
		}
	};
	
	/**
	 * Initialize the selection manager adding the keyboard listener and coyping the selection
	 * list reference
	 */
	@Override
	public void internalInitialize(EditPartViewer viewer, List selection, Runnable notifier) {
		super.internalInitialize(viewer, selection, notifier);
		this.selection = selection;
		JasperReportsPlugin.addKeyListener(keyboardEvent);
	}
	
	/**
	 * Take a node from the selection check if for its model the refresh is ignored
	 * or not
	 * 
	 * @param selection actual selection
	 * @return true if the refresh is ignored, false otherwise
	 */
	private boolean isRefreshIgnored(List<?> selection){
		for (Object item : selection){
			if (item instanceof EditPart) {
				EditPart part = (EditPart)item;
				if (part.getModel() instanceof ANode){
					ANode mainNode = JSSCompoundCommand.getMainNode((ANode)part.getModel());
					if (mainNode != null) return JSSCompoundCommand.isRefreshEventsIgnored(mainNode);
				}
			}
		}
		return false;
	}
	
	/**
	 * Before to call the deselect check if the refresh is ignored or not, since it case a lot
	 * of updates
	 */
	@Override
	public void deselect(EditPart editpart) {
		if (!JSSCompoundCommand.isRefreshEventsIgnored((ANode)editpart.getModel())){
			super.deselect(editpart);
		} 
	}
	
	/**
	 * Remove from the current selection the editpart that are not selectable
	 * 
	 * @param orderedSelection the current selection
	 * @return a subset of the selection with only the selectable edit parts
	 */
	private IStructuredSelection removeUnselectableParts(List<?> orderedSelection){
		List<EditPart> result = new ArrayList<EditPart>();
		
		for (Object obj : orderedSelection) {
			EditPart part = (EditPart) obj;
			if (part.isSelectable()) result.add(part);
		}
		
		return new StructuredSelection(result);
	}
	
	/**
	 * Return the group selection edit part. The part is created in a lazy way so if it was not
	 * created it is created now and returned, otherwise it is simply returned
	 * 
	 * @return a not null {@link GroupSelectionEditPart}
	 */
	protected GroupSelectionEditPart getGroupSelectionPart() {
		if (selectionPart == null) {
			//initialize the zoom manager of the edit part
			ZoomManager zoomManager = (ZoomManager)getViewer().getProperty(ZoomManager.class.toString());
			selectionPart = new GroupSelectionEditPart(this, zoomManager);
		}
		return selectionPart;
	}
	
	/**
	 * Sets the selection with a group selection 
	 * 
	 * @param newSelection the new selection
	 */
	public void setGroupSelection(ISelection newSelection) {
		if (!(newSelection instanceof IStructuredSelection))
			return;

		List<?> orderedSelection = ((IStructuredSelection) newSelection).toList();

		getViewer().setFocus(null);
		
		List<Object> clearedSelection = new ArrayList<Object>();
		for(Object selectedElement : orderedSelection) {
			if (!(selectedElement instanceof BandEditPart)) {
				clearedSelection.add(selectedElement);
			}
		}
		
		selection.clear();
		selection.addAll(clearedSelection);

		if (!clearedSelection.isEmpty()) {
			EditPart firstPart = (EditPart)clearedSelection.get(0);
			IFigure layer = LayerManager.Helper.find(firstPart).getLayer(LayerConstants.HANDLE_LAYER);
			getGroupSelectionPart().updateBounds(true);
			layer.add(getGroupSelectionPart().getFigure());
			getGroupSelectionPart().createSelectionHandles(layer);
		}
		fireSelectionChanged();
	}
	
	/**
	 * Erase the feedback on the passed selection
	 * 
	 * @param selection the selection from where the feedback must be remvoed
	 */
	public void eraseFeedback(List<?> selection) {
		EditPart part;
		// Fix for 458416: adjust the focus through the viewer only (to give
		// AbstractEditPartViewer a change to update its focusPart field).
		// AbstractEditPartViewer#setFocus() should call back setFocus(null)
		// here, so both focus part values should stay in sync.
		getViewer().setFocus(null);
		for (int i = 0; i < selection.size(); i++) {
			part = (EditPart) selection.get(i);
			part.setSelected(EditPart.SELECTED_NONE);
		}
	}
	
	/**
	 * Append an edit part to the current selection. Check also if the group key is pressed
	 */
	@Override
	public void appendSelection(EditPart editpart) {
		getGroupSelectionPart().clear();
		if(BindingsPreferencePersistence.isPressed(GroupSelectionEditPart.BINDING_KEY_ID)) {
			List<Object> newSelectionList = new ArrayList<Object>(selection);
			if (!newSelectionList.contains(editpart)) {
				newSelectionList.add(editpart);
			}
			IStructuredSelection newSelection = removeUnselectableParts(newSelectionList);
			if (newSelection.size() > 1) {
				eraseFeedback(selection);
				setGroupSelection(newSelection);
				return;
			}	
		}
		super.appendSelection(editpart);
	}
	
	@Override
	public void deselectAll() {
		getGroupSelectionPart().clear();
		super.deselectAll();
	}
	
	/**
	 * Check if for the current selection should be shown a group selection. This is done looking if the current
	 * selection is bigger than one element and if the key for the group selection is pressed
	 */
	protected boolean isGroupSelection(IStructuredSelection selection) {
		return BindingsPreferencePersistence.isPressed(GroupSelectionEditPart.BINDING_KEY_ID) && selection.size() > 1;
	}
	
	/**
	 * Sets the selection, override the original method to store and give the status of selected primary
	 * to the item that was primary before the setSelection. If the element isn't in the new selected items
	 * the default behavior will be used (the primary element will be the last on the list of the new selected 
	 * items). It also handle the case where a group selection is done.
	 * 
	 * @param newSelection
	 *            the new selection
	 * @since 3.2
	 */
	@Override
	public void setSelection(ISelection newSelection) {
		getGroupSelectionPart().clear();
		if (!(newSelection instanceof IStructuredSelection))
			return;
		List<?> orderedSelection = ((IStructuredSelection) newSelection).toList();
		//Beofre to set a selection that causes a lot of events check if the refresh is ignored
		if (isRefreshIgnored(orderedSelection)) 
			return;
		EditPart focusedEditPart = null;
		if (!orderedSelection.isEmpty()) {
			Iterator<?> itr = orderedSelection.iterator();
			EditPart part = (EditPart) itr.next();
			if (part.getViewer() != null){
				focusedEditPart = part.getViewer().getFocusEditPart();
				//Search a focused element into the selection
				if (!(focusedEditPart instanceof BandEditPart) && (focusedEditPart instanceof FigureEditPart)){
					lastSelected = focusedEditPart;
				} 
				//If not found the last selected element will be chosen if it is in the selection
				if (lastSelected != null && !orderedSelection.contains(lastSelected)){
					lastSelected = null;
				} 
				IStructuredSelection cleanSelection = removeUnselectableParts(orderedSelection);
				if (isGroupSelection(cleanSelection)) {
					eraseFeedback(orderedSelection);
					setGroupSelection(cleanSelection);
					//If even the last selected element wasn't in the selection the first element of the selection will be chosen
					if (lastSelected == null){
						lastSelected = (EditPart)orderedSelection.get(0);
					}
					
					return;
				} else {
					super.setSelection(cleanSelection);
				}
				//If even the last selected element wasn't in the selection the first element of the selection will be chosen
				if (lastSelected == null){
					lastSelected = (EditPart)orderedSelection.get(0);
				}
				
				if (lastSelected.isSelectable()) lastSelected.setSelected(EditPart.SELECTED_PRIMARY);
				itr = orderedSelection.iterator();
				while (itr.hasNext()) {
						part = (EditPart) itr.next();
						if (part.isSelectable() && part != lastSelected) part.setSelected(EditPart.SELECTED);
				}
			}
		}else {
			super.deselectAll();
		}
	}
	
	/**
	 *  Tipically the getSelection method when the current selection is empty it returns a 
	 *   new StructuredSelection(viewer.getContents()); . If the viewer is the graphical editor
	 *   (J2DScrollingGraphicalViewer) the contents is the page of the report, this trigger a series
	 *   of problems. One example is the deselect all will select the page of the report in some case.
	 *   One of this case is when changing order to a graphical editpart from the outline. This will trigger
	 *   a deselectall for the edit parts, the selection synchronizer will then try to synchronize the selection
	 *   (now empty) between the outline and graphical editor. But the graphical editor will instead try to select
	 *   the page element. Doing this will also trigger another selection in the outline (due to the selection synchronizer) and
	 *   this can also move the scroll area of the outline. It is necessary in this case avoid to return the page as default
	 *   element when the selection is empty
	 */
	public ISelection getSelection() {
		if (selection.isEmpty() && getViewer() instanceof J2DScrollingGraphicalViewer) {
			return StructuredSelection.EMPTY;
		}
		return super.getSelection();
	}
}
