package com.jaspersoft.studio.callout;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.gef.figures.FigureFactory;
import com.jaspersoft.studio.editor.gef.figures.ReportPageFigure;
import com.jaspersoft.studio.editor.gef.parts.AJDEditPart;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.ElementEditPolicy;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.FigurePageLayoutEditPolicy;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.FigureSelectionEditPolicy;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.SearchParentDragTracker;
import com.jaspersoft.studio.editor.gef.parts.text.LabelCellEditorLocator;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.utils.SWTResourceManager;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class CalloutEditPart extends AJDEditPart implements PropertyChangeListener {

	private PreferenceListener preferenceListener;

	private final class PreferenceListener implements IPropertyChangeListener {

		public void propertyChange(org.eclipse.jface.util.PropertyChangeEvent event) {
			handlePreferenceChanged(event);
		}

	}

	protected void handlePreferenceChanged(org.eclipse.jface.util.PropertyChangeEvent event) {
		// String p = event.getProperty();

		refreshVisuals();
	}

	@Override
	public void activate() {
		super.activate();
		preferenceListener = new PreferenceListener();
		JaspersoftStudioPlugin.getInstance().addPreferenceListener(preferenceListener);
	}

	@Override
	public void deactivate() {
		if (preferenceListener != null)
			JaspersoftStudioPlugin.getInstance().removePreferenceListener(preferenceListener);
		super.deactivate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	@Override
	protected IFigure createFigure() {
		ANode model = getModel();
		IFigure rect = FigureFactory.createFigure(model);
		setupFigure(rect);
		return rect;
	}

	/**
	 * Instead of the default drag tracker an overridden one is returned, in this way we can control the edit part
	 * targeted from a drag & drop operation, and if the target is isn't an IContainer then it's parent is returned Change
	 * by Orlandin Marco
	 */
	@Override
	public org.eclipse.gef.DragTracker getDragTracker(org.eclipse.gef.Request request) {
		return new SearchParentDragTracker(this);
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new DirectEditPolicy() {

			@Override
			protected void showCurrentEditValue(DirectEditRequest request) {
				getFigure().getUpdateManager().performUpdate();
			}

			@Override
			protected Command getDirectEditCommand(DirectEditRequest request) {
				SetValueCommand cmd = new SetValueCommand();
				cmd.setTarget((IPropertySource) getHost().getModel());
				cmd.setPropertyId(MCallout.PROP_TEXT);
				CellEditor cellEditor = request.getCellEditor();
				cmd.setPropertyValue((String) cellEditor.getValue());
				return cmd;
			}
		});
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ElementEditPolicy());
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new FigureSelectionEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new FigurePageLayoutEditPolicy());
	}

	private CalloutEditManager manager;

	public void performRequest(Request request) {

		if (request.getType() == RequestConstants.REQ_OPEN) {
			if (manager == null) {
				manager = new CalloutEditManager(this, new LabelCellEditorLocator(getFigure()));
			}
			manager.show();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
	 */
	@Override
	public void refreshVisuals() {
		IFigure rect = getFigure();
		setupFigure(rect);
		rect.invalidate();
		rect.repaint();
	}

	protected JasperReportsConfiguration jConfig;

	public JasperReportsConfiguration getjConfig() {
		return jConfig;
	}

	/**
	 * Sets the up figure.
	 * 
	 * @param rect
	 *          the new up figure
	 */
	protected void setupFigure(IFigure rect) {
		ANode model = getModel();
		rect.setToolTip(new Label(model.getToolTip()));

		MCallout m = getModel();

		int x = (Integer) m.getPropertyValue(JRDesignElement.PROPERTY_X) + ReportPageFigure.PAGE_BORDER.left;
		int y = (Integer) m.getPropertyValue(JRDesignElement.PROPERTY_Y) + ReportPageFigure.PAGE_BORDER.top;
		int w = (Integer) m.getPropertyValue(JRDesignElement.PROPERTY_WIDTH);
		int h = (Integer) m.getPropertyValue(JRDesignElement.PROPERTY_HEIGHT);

		String text = (String) m.getPropertyValue(MCallout.PROP_TEXT);
		((CalloutFigure) rect).setText(text);
		rect.setBackgroundColor(SWTResourceManager.getColor((RGB) m.getPropertyValue(MCallout.PROP_BACKGROUND)));
		rect.setForegroundColor(SWTResourceManager.getColor((RGB) m.getPropertyValue(MCallout.PROP_FOREGROUND)));

		rect.setBounds(new Rectangle(x, y, w, h));
	}

	@Override
	public MCallout getModel() {
		return (MCallout) super.getModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		refresh();
		refreshC(getModel());
		refreshVisuals();
	}

	/**
	 * Refresh c.
	 * 
	 * @param n
	 *          the n
	 */
	private void refreshC(ANode n) {
		if (n.getChildren() != null)
			for (INode node : n.getChildren()) {
				EditPart ep = (EditPart) getViewer().getEditPartRegistry().get(node);
				refreshVisuals();
				refreshC((ANode) node);
			}
	}

}