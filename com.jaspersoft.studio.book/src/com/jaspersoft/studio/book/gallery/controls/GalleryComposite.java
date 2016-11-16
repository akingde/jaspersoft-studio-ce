/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.gallery.controls;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ResourceTransfer;

import com.jaspersoft.studio.book.gallery.commands.CompoundOperation;
import com.jaspersoft.studio.book.gallery.commands.CreateElementCommand;
import com.jaspersoft.studio.book.gallery.commands.DeleteItemCommand;
import com.jaspersoft.studio.book.gallery.controls.render.DraggableGalleryItemRenderer;
import com.jaspersoft.studio.book.gallery.controls.render.DraggableGroupRenderer;
import com.jaspersoft.studio.book.gallery.controls.render.PageDragSourceEffect;
import com.jaspersoft.studio.book.gallery.interfaces.IElementOpener;
import com.jaspersoft.studio.book.gallery.interfaces.IGalleryElement;
import com.jaspersoft.studio.book.model.IReportPartContainer;

/**
 * Control to show a gallery where it is possible to add\remove elements and order them by 
 * drag and drop. Support the commands to allow and easy undo\redo of the operations. Allow to
 * add a listener that is called each time in the gallery something changes
 * 
 * @author Orlandin Marco
 *
 */
public class GalleryComposite extends Composite {
	
	/**
	 * Stack to execute the command on the gallery. It is shared becose
	 * a command can involve more galleries
	 */
	private IWorkbench executionWorkbench;
	
	/**
	 * Default height for the images in the gallery
	 */
	private static final int GALLERY_HEIGHT = 150;

	/**
	 * Default width for the images in the gallery
	 */
	private static final int GALLERY_WIDTH = 150;
	
	/**
	 * List of listeners to call when something in the gallery changes
	 */
	private List<ModifyListener> modifyListeners = new ArrayList<ModifyListener>();
	
	/**
	 * List of the gallery content
	 */
	private List<IGalleryElement> elements = new ArrayList<IGalleryElement>();
	
	/**
	 * The gallery control
	 */
	private Gallery pageGallery;
	
	/**
	 * The root element of the gallery
	 */
	private GalleryItem elementGroups;
	
	/**
	 * The effect to show during the drag and drop to show a miniature of 
	 * the moved element under the mouse pointer 
	 */
	private PageDragSourceEffect dragSourceEffect;
	
	/**
	 * container where the informations about the drop target effect are placed
	 */
	private DropEffectLocation dropTargetEffect = new DropEffectLocation();
	
	/**
	 * The delete action to delete an entry on the gallery
	 */
	protected MenuItem deleteAction = null;
	
	/**
	 * List of openers that provide the action to add an element to the gallery
	 */
	protected List<IElementOpener> openers = new ArrayList<IElementOpener>();

	private IReportPartContainer partsContainer;
	
	/**
	 * Handler for the right click of the gallery, if the right click is outside an element then
	 * the selected element will be deselected and the delete and update actions (if present) will 
	 * be disabled
	 */
	protected class GalleryRightClick implements MouseListener {

		public GalleryRightClick(){}
		
		@Override
		public void mouseUp(MouseEvent e) {	
		}
		
		@Override
		public void mouseDown(MouseEvent e) {
			boolean allDeselected = (e.button == 3 && pageGallery.getItem(new Point(e.x,e.y))==null);
			if (allDeselected) pageGallery.deselectAll();
			if (deleteAction != null) deleteAction.setEnabled(!allDeselected);
		}
		
		@Override
		public void mouseDoubleClick(MouseEvent e) {
		}
	}
	
	/**
	 * Create the control
	 * 
	 * @param parent the parent container
	 * @param style the style of the main composite
	 */
	public GalleryComposite(IWorkbench executionWorkbench, Composite parent, int style) {
		super(parent, style);
		this.executionWorkbench = executionWorkbench;
		createControl();
	}
	
	/**
	 * Create the gallery and attach to it the various render and drag and drop
	 * support
	 */
	private void createControl() {
		setLayout(new GridLayout(1, false));
		pageGallery = new Gallery(this, SWT.VIRTUAL | SWT.V_SCROLL | SWT.BORDER);
		pageGallery.setLayoutData(new GridData(GridData.FILL_BOTH));
		elementGroups = new GalleryItem(pageGallery, SWT.NONE);

		// Set the drag feedback render
		DraggableGroupRenderer gr = new DraggableGroupRenderer();
		gr.setMinMargin(2);
		gr.setItemSize(GALLERY_WIDTH, GALLERY_HEIGHT);
		gr.setAutoMargin(true);
		gr.setDropTargetLocation(dropTargetEffect);
		pageGallery.setGroupRenderer(gr);
		DraggableGalleryItemRenderer ir = new DraggableGalleryItemRenderer();
		ir.setShowLabels(true);
		pageGallery.setItemRenderer(ir);

		// create the effect on the mouse pointer during the drag and drop
		dragSourceEffect = new PageDragSourceEffect(pageGallery, GALLERY_WIDTH, GALLERY_HEIGHT);

		// Add the drag, the drag operation allow to go only to other galleries
		Transfer[] types = new Transfer[] { GalleryTransfer.getInstance() };
		DragSource galleryDragSource = new DragSource(pageGallery,DND.DROP_MOVE);
		galleryDragSource.setTransfer(types);
		galleryDragSource.setDragSourceEffect(dragSourceEffect);
		galleryDragSource.addDragListener(new DragSourceListener() {

			@Override
			public void dragStart(DragSourceEvent event) {
				GalleryTransfer.getInstance().setSourceGallery(
						GalleryComposite.this);
			}

			@Override
			public void dragSetData(DragSourceEvent event) {
			}

			@Override
			public void dragFinished(DragSourceEvent event) {
				GalleryTransfer.getInstance().setSourceGallery(null);
			}
		});

		// add the drop support, the drop operation accept the drop from other
		// galleries and drop of resources
		DropTarget gallryDropTarget = new DropTarget(pageGallery, DND.DROP_MOVE);
		types = new Transfer[] { GalleryTransfer.getInstance(), ResourceTransfer.getInstance() };
		gallryDropTarget.setTransfer(types);
		gallryDropTarget.addDropListener(new GalleryDropTargetListener());

		// Create the popoup menu for the gallery
		Menu popupMenu = new Menu(pageGallery);
		pageGallery.setMenu(popupMenu);
		pageGallery.addMouseListener(new GalleryRightClick());
		initializeDeleteAction();
	}
	
	/**
	 * Call all the defined modify listeners
	 */
	public void callModifyLiteners(){
		Event e = new Event();
		e.widget = pageGallery;
		e.data = elements;
		ModifyEvent event = new ModifyEvent(e);
		for(ModifyListener listener : modifyListeners){
			listener.modifyText(event);
		}
	}
	
	/**
	 * Create an item inside the gallery. The item is not created with a command
	 * so it can't be undone. Shifts the element currently at that position and any subsequent 
	 * elements to the right (adds one to their indices).

	 * 
	 * @param element the element to create
	 * @param index index of the element or -1 if it must placed in the last position
	 */
	public void createItem(IGalleryElement element, int index){
		GalleryItem item = null;
		if (index != -1){
			item = new GalleryItem(elementGroups, SWT.NONE, index);
			elements.add(index, element);
		} else {
			item = new GalleryItem(elementGroups, SWT.NONE);
			elements.add(element);
		}
		item.setText(element.getTitle());
		item.setData(element);
		Image elementImage = element.getImage();
		item.setImage(elementImage);
		item.setSelectedImage(elementImage);
		callModifyLiteners();
	}
	
	/**
	 * Remove an item from the gallery. The item is not created with a command
	 * so it can't be undone
	 * 
	 * @param index index of the element or -1 if it must placed in the last position
	 * @return the removed element 
	 */
	public IGalleryElement removeItem(int index){
		IGalleryElement removedElement = elements.remove(index);
		elementGroups.remove(index);
		callModifyLiteners();
		return removedElement;
	}
	
	/**
	 * Add an element opener to the gallery. The contextual menu
	 * is rebuild to provide the action for the new opener
	 * 
	 * @param opener a not null new opener
	 */
	public void addElementOpener(IElementOpener opener){
		openers.add(opener);
		//Remove the old items
		Menu menu = pageGallery.getMenu();
		MenuItem[] items = menu.getItems();
		for(int i = 0; i<items.length; i++){
			items[i].dispose();
		}
		//Regenerate the popup menu entris
		initializeAddActions();
		initializeDeleteAction();
	}
	
	/**
	 * Add a listener to the gallery that is called when something inside
	 * changes
	 * 
	 * @param listener a not null listener
	 */
	public void addModifyListener(ModifyListener listener){
		modifyListeners.add(listener);
	}
	
	/**
	 * Remove a modify listener from the list 
	 * 
	 * @param listener a reference to the listener to remove
	 * @return true if the listener was removed, false otherwise
	 */
	public boolean removeModifyListener(ModifyListener listener){
		if (modifyListeners.contains(listener)){
			modifyListeners.remove(listener);
			return true;
		}
		return false;
	}
	
	/**
	 * Return the number of elements inside the gallery
	 * 
	 * @return a not negative number
	 */
	public int getContentSize(){
		return elementGroups.getItemCount();
	}
	
	/**
	 * Return the index of a specific element in the gallery
	 * 
	 * @param container the element
	 * @return the index in the gallery or -1 if it can't be found
	 */
	public int getIndexOf(IGalleryElement container){
		return elements.indexOf(container);
	}
	
	/**
	 * Return an item inside a specific position in the gallery
	 * 
	 * @param index the position of the item
	 * @return a not null item
	 */
	public IGalleryElement getElementAt(int index){
		return elements.get(index);
	}
	
	/**
	 * Get the elements inside the gallery
	 * 
	 * @return a not null list of the elements shown inside the gallery
	 */
	public List<IGalleryElement> getElements(){
		return elements;
	}
	
	/**
	 * Check if the gallery is empty
	 * 
	 * @return true if the gallery is empty, false otherwise
	 */
	public boolean isEmpty(){
		return elements.isEmpty();
	}
	
	/**
	 * Check if there are elements selected in the gallery
	 * 
	 * @return true if there is at least an element selected in the gallery
	 * false otherwise
	 */
	public boolean isSelectionEmpty(){
		return pageGallery.getSelection().length == 0;
	}
	
	/**
	 * Return a list of the elements actually selected inside the gallery
	 * 
	 * @return a not null list of the selected elements
	 */
	public List<IGalleryElement> getSelectedElements(){
		List<IGalleryElement> result = new ArrayList<IGalleryElement>();
		for(GalleryItem item : pageGallery.getSelection()){
			result.add((IGalleryElement)item.getData());
		}
		return result;
	}
	
	/**
	 * Add a list of elements to the gallery.
	 * 
	 * @param elements a not null list of elements.
	 */
	public void addElements(List<IGalleryElement> elements){
		for(int i=0;i<elements.size();i++) {
			this.createItem(elements.get(i), i);
		}
		pageGallery.redraw();
	}
	
	
	/**
	 * Return the location of the current drop target effect
	 * 
	 * @return a not null drop target effect
	 */
	protected DropEffectLocation getDropTargetEffect() {
		return dropTargetEffect;
	}
	
	/**
	 * Create a contextual delete action for an item in the gallery. The item
	 * is removed with a command so this can be undone
	 */
	protected void initializeDeleteAction(){
	    deleteAction = new MenuItem(pageGallery.getMenu(), SWT.NONE);
	    deleteAction.setText("Delete");
	    deleteAction.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent e) {
	    		CompoundOperation cc = new CompoundOperation("Delete Elements");
	    		for(GalleryItem item : pageGallery.getSelection()){
	    			DeleteItemCommand deleteCommand = new DeleteItemCommand(GalleryComposite.this, (IGalleryElement)item.getData());
	    			cc.add(deleteCommand);
	    		}
	    		executeCommand(cc);
	    		pageGallery.redraw();
	    	}
		});   
	}
	
	/**
	 * Create a contextual add action for an item in the gallery. The item
	 * is added with a command so this can be undone
	 */
	protected void initializeAddActions(){
		for (IElementOpener opener : openers){
			MenuItem addAction = new MenuItem(pageGallery.getMenu(), SWT.NONE);
		    addAction.setText(opener.getActionText());
		    addAction.setData(opener);
		    addAction.addSelectionListener(new SelectionAdapter() {
		    	@Override
		    	public void widgetSelected(SelectionEvent e) {
		    		CompoundOperation cc = new CompoundOperation("Add Elements");
		    		IElementOpener opener = (IElementOpener)e.widget.getData();
		    		IGalleryElement[] elements = opener.openResources();
		    		for(IGalleryElement element : elements){
		    			CreateElementCommand createCommand = new CreateElementCommand(GalleryComposite.this, element);
		    			cc.add(createCommand);
		    		}
		    		executeCommand(cc);
		    		pageGallery.redraw();
		    	}
			});   
		}
	}
	
	/**
	 * Return the gallery control
	 * 
	 * @return a not null Gallery control
	 */
	protected Gallery getGallery(){
		return pageGallery;
	}
	
	/**
	 * Remove the drop effect from this gallery and optionally the 
	 * snapshot under the mouse pointer
	 * 
	 * @param removeMouseMoveFeedback true if must be removed also
	 * the snapshot feedback under the mouse pointer, false otherwise
	 */
	protected void clearDropEffect(boolean removeMouseMoveFeedback) {
		if (removeMouseMoveFeedback) dragSourceEffect.disposeImage();
		dropTargetEffect.setItem(null);
		pageGallery.redraw();
	}
	
	
	/**
	 * Execute a new command on the stack
	 * 
	 * @param cmd a not null command to execute
	 */
	public void executeCommand(AbstractOperation operation){
		if (executionWorkbench != null){
			IOperationHistory operationHistory = executionWorkbench.getOperationSupport().getOperationHistory();
			IUndoContext undoContext = executionWorkbench.getOperationSupport().getUndoContext();
			operation.addContext(undoContext);
			try {
				operationHistory.execute(operation, new NullProgressMonitor(), null);
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		} else {
			try {
				operation.execute(new NullProgressMonitor(), null);
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	public void setPartsContainer(IReportPartContainer partsContainer) {
		this.partsContainer = partsContainer;
	}
	
	public IReportPartContainer getPartsContainer() {
		return partsContainer;
	}
}
