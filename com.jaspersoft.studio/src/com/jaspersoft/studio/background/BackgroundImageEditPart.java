/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.background;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.DragEditPartsTracker;
import org.eclipse.ui.IEditorPart;

import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.editor.gef.parts.ReportPageEditPart;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.utils.SelectionHelper;

/**
 * 
 * Edit part for the background figure, define a special edit policy that allow the editing of the 
 * image only when the editor is set in edit background mode
 * 
 * @author Orlandin Marco
 *
 */
public class BackgroundImageEditPart extends AbstractGraphicalEditPart  implements PropertyChangeListener{

	@Override
	protected IFigure createFigure() {
		MBackgrounImage model = (MBackgrounImage)getModel();
		BackgroundImageFigure figure = new BackgroundImageFigure(model);
		figure.setSize(model.getDefaultWidth(), model.getDefaultHeight());
		return figure;
	}
	
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
	
	
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new ResizableEditPolicy(){
			
			@Override
			protected Command getResizeCommand(ChangeBoundsRequest request) {
				MBackgrounImage model = (MBackgrounImage)getModel();
				getFeedbackLayer();
				Rectangle newBounds = getFeedbackSize(request);
				CompoundCommand cc = new CompoundCommand();
				SetValueCommand setCommand = new SetValueCommand();
				setCommand.setTarget(model);
				setCommand.setPropertyId(MBackgrounImage.PROPERTY_WIDTH);
				setCommand.setPropertyValue(newBounds.width);
				cc.add(setCommand);
				setCommand = new SetValueCommand();
				setCommand.setTarget(model);
				setCommand.setPropertyId(MBackgrounImage.PROPERTY_HEIGHT);
				setCommand.setPropertyValue(newBounds.height);
				cc.add(setCommand);
				return cc;
			}
			
			@Override
			protected Command getMoveCommand(ChangeBoundsRequest request) {
				MBackgrounImage model = (MBackgrounImage)getModel();
				Rectangle newBounds = getFeedbackSize(request);
				CompoundCommand cc = new CompoundCommand();
				SetValueCommand setCommand = new SetValueCommand();
				setCommand.setTarget(model);
				setCommand.setPropertyId(MBackgrounImage.PROPERTY_X);
				setCommand.setPropertyValue(newBounds.x);
				cc.add(setCommand);
				setCommand = new SetValueCommand();
				setCommand.setTarget(model);
				setCommand.setPropertyId(MBackgrounImage.PROPERTY_Y);
				setCommand.setPropertyValue(newBounds.y);
				cc.add(setCommand);
				return cc;
			}
			
			private Rectangle getFeedbackSize(ChangeBoundsRequest request){
				MBackgrounImage model = (MBackgrounImage)getModel();
				Rectangle oldBounds = model.getBounds();
				PrecisionRectangle rect2 = new PrecisionRectangle(new Rectangle(request.getMoveDelta().x, request
						.getMoveDelta().y, request.getSizeDelta().width, request.getSizeDelta().height));
				getHostFigure().translateToRelative(rect2);

				oldBounds.translate(rect2.x, rect2.y);
				oldBounds.resize(rect2.width, rect2.height);
				return oldBounds;
			}
			
			@Override
			protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
				if (!isImageEditable()) return;
				IFigure feedback = getDragSourceFeedbackFigure();

				PrecisionRectangle rect = new PrecisionRectangle(getInitialFeedbackBounds().getCopy());
				getHostFigure().translateToAbsolute(rect);
				rect.translate(request.getMoveDelta());
				rect.resize(request.getSizeDelta());

				// Calculate changes for the figure...
				String s = "";
				int scaleH = 0;
				int scaleW = 0;
				Rectangle oldBounds = getFeedbackSize(request);
				s += oldBounds.x + ", " + oldBounds.y + ", " + oldBounds.width + ", " + oldBounds.height;
				if (oldBounds.width != 0)
					scaleW = rect.width / oldBounds.width - 1;
				if (oldBounds.height != 0)
					scaleH = rect.height / oldBounds.height - 1;
				feedback.translateToRelative(rect);

				((BackgroundFeedbackFigure) feedback).setText(s);

				feedback.setBounds(rect.resize(-scaleW, -scaleH));
			}
			
			protected IFigure createDragSourceFeedbackFigure() {
				// Use a ghost rectangle for feedback
				RectangleFigure r = new BackgroundFeedbackFigure();
				r.setLineStyle(Graphics.LINE_DOT);
				r.setForegroundColor(ColorConstants.black);
				r.setBounds(getInitialFeedbackBounds().resize(-1, -1));
				addFeedback(r);
				return r;
			}
			
			@Override
			protected void showSelection() {
				if (isImageEditable()) addSelectionHandles();
			}
		});

	}
	
	/**
	 * Check if the editor is set in background edit mode
	 * 
	 * @return
	 */
	private boolean isImageEditable(){
		IEditorPart currentEditor = SelectionHelper.getActiveJRXMLEditor();
		if (currentEditor instanceof JrxmlEditor){
			return ((JrxmlEditor)currentEditor).getReportContainer().isBackgroundImageEditable();
		}
		return false;
	}

	/**
	 * Overridden to return a default <code>DragTracker</code> for
	 * GraphicalEditParts.
	 * 
	 * @see org.eclipse.gef.EditPart#getDragTracker(Request)
	 */
	public DragTracker getDragTracker(Request request) {
		if (!isImageEditable()) return null;
		return new DragEditPartsTracker(this){
			@Override
			protected EditPart getTargetEditPart() {
				EditPart currentPart = super.getTargetEditPart();
				while(currentPart != null && !(currentPart instanceof ReportPageEditPart)){
					currentPart = currentPart.getParent();
				}
				return currentPart;
			}
		};
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
	}
}
