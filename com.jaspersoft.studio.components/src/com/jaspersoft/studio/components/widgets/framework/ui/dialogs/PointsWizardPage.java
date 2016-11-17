/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.widgets.framework.ui.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

import net.sf.jasperreports.customizers.shape.Point;
import net.sf.jasperreports.customizers.shape.ShapeTypeEnum;

/**
 * Wizard page to define the points that compose a polyline. A preview of 
 * the result is shown while inserting points
 * 
 * @author Orlandin Marco
 *
 */
public class PointsWizardPage extends JSSHelpWizardPage {

	/**
	 * The list of points defined
	 */
	private List<Point> definedPoints;
	
	/**
	 * The viewer where the defined points are show
	 */
	private TableViewer pointsTable;
	
	/**
	 * Button to edit an existing point
	 */
	private Button editButton;
	
	/**
	 * Button to delete an existing point
	 */
	private Button deleteButton;
	
	/**
	 * Area where the preview is shown
	 */
	private Composite paintArea;
	
	/**
	 * The currently selected point
	 */
	private Point currentSelectedPoint = null;
	
	private ShapeTypeEnum editedShape;
	
	/**
	 * Composite where the preview is painted. The previewed element
	 * is scaled when it is bigger then the preview area to fit into it
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class PaintArea extends Composite {
		
		private float scaleFactor = 1.0f;
		
		private int xOffset = 0;
		
		private int yOffset = 0;
		
		public PaintArea(Composite parent, int style) {
			super(parent, style);
			addPaintListener(new PaintListener() {
				
				@Override
				public void paintControl(PaintEvent e) {
					scaleFactor = 1.0f;
					xOffset = yOffset = 0;
					if (definedPoints.size() < 2){
						return;
					}
					GC context = e.gc;
					Rectangle bounds = paintArea.getBounds();
					bounds.width -= 6;
					bounds.height -= 6;
					
					int points[] = new int[definedPoints.size() * 2];
					int index = 0;
					Integer top = null;
					Integer bottom = null;
					Integer left = null;
					Integer right = null;
					for(Point point : definedPoints){
						points[index] = point.getX();
						points[index + 1] = point.getY();
						index += 2;
						
						if (top == null || point.getY() < top){
							top = point.getY();
						}
						
						if (bottom == null || point.getY() > bottom){
							bottom = point.getY();
						}
						
						if (left == null || point.getX() < left){
							left = point.getX();
						}
						
						if (right == null || point.getX() > right){
							right = point.getX();
						}
					}
					
					int linesWidth = right - left;
					int linesHeight = bottom - top;
					
					if (linesWidth > bounds.width || linesHeight > bounds.height){
						float scaleFactorX = new Float(bounds.width) / new Float(linesWidth);
						float scaleFactorY = new Float(bounds.height) / new Float(linesHeight);
						scaleFactor = Math.min(scaleFactorX, scaleFactorY);
						xOffset = Math.round((((bounds.width - scaleFactor * linesWidth))/2) -  left * scaleFactor) + 3;
						yOffset = Math.round(((bounds.height - (scaleFactor * linesHeight))/2) - top * scaleFactor) + 3;
					} else {
						xOffset = Math.round(((bounds.width - linesWidth))/2) - left;
						yOffset = Math.round(((bounds.height - linesHeight))/2) - top;						
					}
					for(int i = 0; i + 1 < points.length; i += 2){
						float currentPointX = (new Float(points[i]) * scaleFactor) + xOffset;
						float currentPointY = (new Float(points[i + 1]) * scaleFactor) + yOffset;
						points[i] = Math.round(currentPointX);
						points[i + 1] = Math.round(currentPointY);
					}
			
					if (editedShape == ShapeTypeEnum.POLYLINE){
						context.drawPolyline(points);	
					} else {
						context.drawPolygon(points);
					}
				}
			});
			
			//Create a mouse listener to allow to add a point from the contextual menu
			//of the preview area
			addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseDown(final MouseEvent e) {
					if (e.button == 3){
						if (paintArea.getMenu() != null){
							paintArea.getMenu().dispose();
						}
						Menu menu = new Menu(paintArea);
						MenuItem item = new MenuItem(menu, SWT.NONE);
						item.setText("Add Point");
						item.addSelectionListener(new SelectionAdapter() {
							public void widgetSelected(SelectionEvent selEvent) {
								int newX = Math.round((e.x - xOffset) / scaleFactor);
								int newY = Math.round((e.y - yOffset) / scaleFactor);
								Point newPoint = new Point(newX, newY);
								definedPoints.add(newPoint);
								pointsTable.refresh();
								paintArea.redraw();
							};
						});
						menu.setLocation(paintArea.toDisplay(e.x, e.y));
						menu.setVisible(true);
					}
				}
				
			});
		}
		
	}

	/**
	 * Create the page with an empty set of points
	 */
	public PointsWizardPage(ShapeTypeEnum editedShape) {
		super("pointsDefinitionPage");
		definedPoints = new ArrayList<Point>();
		this.editedShape = editedShape;
	}
	
	/**
	 * Create the page with a preinitialized set of point
	 * 
	 * @param editedShape the defined shape, used to draw correctly a polygon or a polyline
	 * @param currentPoints the current set of points
	 */
	public PointsWizardPage(ShapeTypeEnum editedShape, List<Point> currentPoints) {
		this(editedShape);
		if (currentPoints != null){
			definedPoints = new ArrayList<Point>(currentPoints);	
		} else {
			definedPoints = new ArrayList<Point>();
		}
	}


	@Override
	public void createControl(Composite parent) {
  	Composite container = new Composite(parent,  SWT.NONE);
  	container.setLayout(new GridLayout(2, false));
  	container.setLayoutData(new GridData(GridData.FILL_BOTH));
  	createTable(container);
  	createPreviewArea(container);
  	setTitle("Define the shape points");
  	setMessage("Define the points that compose the shape lines");
  	setControl(container);

	}

	@Override
	protected String getContextName() {
		return null;
	}

  /**
   * Create the table control
   * 
   * @param parent the composite where the control is created
   */
  protected void createTable(Composite parent){
  	Composite cmp = new Composite(parent, SWT.NONE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		cmp.setLayoutData(gd);
		GridLayout cmpLayout = new GridLayout(2, false);
		cmpLayout.horizontalSpacing = 3;
		cmpLayout.verticalSpacing = 0;
		cmpLayout.marginWidth = 0;
		cmpLayout.marginHeight = 0;
		cmp.setLayout(cmpLayout);

		Table tbl = new Table(cmp, SWT.BORDER | SWT.SINGLE);
		tbl.setLinesVisible(false);
		tbl.setHeaderVisible(true);
		TableColumn col = new TableColumn(tbl, SWT.NONE);
		col.setText("Shape Points");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 150;
		gd.widthHint = 200;
		tbl.setLayoutData(gd);

		pointsTable = new TableViewer(tbl);
		pointsTable.setContentProvider(new ListContentProvider());
		pointsTable.setLabelProvider(new LabelProvider(){
			
			@Override
			public String getText(Object element) {
				Point point = (Point)element;
				StringBuilder text = new StringBuilder();
				text.append("X: ");
				text.append(point.getX());
				text.append(" Y: ");
				text.append(point.getY());
				return text.toString();
			}
			
		});
		pointsTable.setInput(definedPoints);

		Composite bcmp = new Composite(cmp, SWT.NONE);
		GridLayout bcmpLayout = new GridLayout(1, false);
		bcmpLayout.horizontalSpacing = 0;
		bcmpLayout.verticalSpacing = 2;
		bcmpLayout.marginWidth = 0;
		bcmpLayout.marginHeight = 0;
		bcmp.setLayout(bcmpLayout);
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 100;
		bcmp.setLayoutData(gd);
		col.setWidth(300);
		
		createNewButton(bcmp);
		editButton = createEditButton(bcmp);
		deleteButton= createDeleteButton(bcmp);
		
		//Selection change listener used to update the button enable status and to update
		//the dynamic ui if the selected customizer has a custom ui definition
		pointsTable.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection selection = (StructuredSelection)event.getSelection();
				if (selection.size() > 0){
					currentSelectedPoint = (Point)selection.getFirstElement();									
					updateButtonEnablemenet(currentSelectedPoint);
				} else {
					updateButtonEnablemenet(null);
				}
			}
		});
		
		//Listener to treat the doubleclick a selection plus edit button pressed
		pointsTable.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				editAction();
			}
		});
		
  }
  
	/**
	 * Update the buttons basing it on the selection
	 * 
	 * @param definition the currently selected point or null if nothing is selected
	 */
	protected void updateButtonEnablemenet(Point definition){
		if (definition == null){
			editButton.setEnabled(false);
			deleteButton.setEnabled(false);
		} else {
			deleteButton.setEnabled(true);
			editButton.setEnabled(true);
		}
	}
  
	/**
	 * Create the button to add a new point
	 * 
	 * @param parent composite where the button should be placed
	 */
	protected void createNewButton(Composite parent){
		GridData buttonsData = new GridData(GridData.FILL_HORIZONTAL);
		Button newButton = new Button(parent, SWT.PUSH);
		newButton.setLayoutData(buttonsData);
		newButton.setText("New Point");
		newButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				ProvidePointDialog creationDialog = new ProvidePointDialog(getShell());
				if (creationDialog.open() == Dialog.OK){
					Point newPoint = new Point(creationDialog.getX(), creationDialog.getY());
					definedPoints.add(newPoint);
					pointsTable.refresh();
					paintArea.redraw();
				}
			}
		});	
	}
	
	/**
	 * Create the button to edit the entry of a selected point
	 * 
	 * @param parent the parent where the button will be created
	 * @return the created button
	 */
	protected Button createEditButton(Composite parent){
		GridData buttonsData = new GridData(GridData.FILL_HORIZONTAL);
		Button editButton = new Button(parent, SWT.PUSH);
		editButton.setLayoutData(buttonsData);
		editButton.setText("Edit Point");
		editButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				editAction();
			}
		});
		editButton.setEnabled(false);
		return editButton;
	}
	
	/**
	 * Create the button to delete the entry of a selected point
	 * 
	 * @param parent the parent where the button will be created
	 * @return the created button
	 */
	protected Button createDeleteButton(Composite parent){
		GridData buttonsData = new GridData(GridData.FILL_HORIZONTAL);
		Button deleteButton = new Button(parent, SWT.PUSH);
		deleteButton.setLayoutData(buttonsData);
		deleteButton.setText("Delete Point");
		deleteButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selection = (StructuredSelection)pointsTable.getSelection();
				if (selection != null && selection.size() > 0){
					Point definition = (Point)selection.getFirstElement();
					definedPoints.remove(definition);
					pointsTable.refresh();	
					paintArea.redraw();
				} 
			}
		});
		deleteButton.setEnabled(false);
		return deleteButton;
	}
	
	/**
	 * Open the dialog to edit the selected point
	 */
	protected void editAction(){
		StructuredSelection selection = (StructuredSelection)pointsTable.getSelection();
		if (!selection.isEmpty()){	
			Point editElement = (Point)selection.getFirstElement();

			ProvidePointDialog dialog = new ProvidePointDialog(getShell(), editElement.getX(), editElement.getY());
			if (dialog.open() == Dialog.OK){
				editElement.setX(dialog.getX());
				editElement.setY(dialog.getY());
				pointsTable.refresh();
				paintArea.redraw();
			}					
		}
	}
	
	/**
	 * Crate the area where the preview is shown
	 * 
	 * @param parent the parent of the area
	 */
	protected void createPreviewArea(Composite parent){
		Group container = new Group(parent, SWT.NONE);
		container.setText("Preview");
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		paintArea = new PaintArea(container, SWT.NONE);
		paintArea.setBackground(ColorConstants.white);
		paintArea.setForeground(ColorConstants.black);
		GridData paintAreaData = new GridData(GridData.FILL_HORIZONTAL);
		paintAreaData.verticalAlignment = SWT.CENTER;
		paintAreaData.grabExcessVerticalSpace = true;
		paintArea.setLayoutData(paintAreaData);
	}

	/**
	 * Return the define points
	 * 
	 * @return a not null list of the defined points
	 */
	public List<Point> getPoints(){
		return definedPoints;
	}	
	
	@Override
	public IWizardPage getNextPage() {
		return null;
	}
}
