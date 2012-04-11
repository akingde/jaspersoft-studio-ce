/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.gef.parts.band;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.BandTypeEnum;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.gef.figures.BandFigure;
import com.jaspersoft.studio.editor.gef.figures.ReportPageFigure;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.IContainerPart;
import com.jaspersoft.studio.editor.gef.parts.ReportPageEditPart;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.BandMoveEditPolicy;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.BandResizableEditPolicy;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.ElementEditPolicy;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.preferences.DesignerPreferencePage;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.utils.ModelUtils;

/*
 * BandEditPart creates the figure for the band. The figure is actually just the bottom border of the band. This allows
 * to drag this border to resize the band. The PageEditPart sets a specific contraint for the BandEditPart elements in
 * order to make them move only vertically. The BandMoveEditPolicy is responsable for the feedback when the band is
 * dragged.
 * 
 * @author Chicu Veaceslav, Giulio Toffoli
 */
public class BandEditPart extends FigureEditPart implements PropertyChangeListener, IContainerPart {

	private BandPreferenceListener prefChangelistener;

	/*
	 * Preferences listener for band preferences/properties.
	 */
	private final class BandPreferenceListener implements IPropertyChangeListener {
		public void propertyChange(org.eclipse.jface.util.PropertyChangeEvent event) {
			if (event.getProperty().equals(DesignerPreferencePage.P_SHOW_REPORT_BAND_NAMES)) {
				setBandNameShowing((BandFigure) getFigure());
			}
		}
	}

	@Override
	public void activate() {
		super.activate();
		prefChangelistener = new BandPreferenceListener();
		JaspersoftStudioPlugin.getInstance().getPreferenceStore().addPropertyChangeListener(prefChangelistener);
	}

	@Override
	public void deactivate() {
		JaspersoftStudioPlugin.getInstance().getPreferenceStore().removePropertyChangeListener(prefChangelistener);
		super.deactivate();
	}

	/**
	 * Gets the band.
	 * 
	 * @return the band
	 */
	public JRDesignBand getBand() {
		return getModel().getValue();
	}

	@Override
	public MBand getModel() {
		return (MBand) super.getModel();
	}

	public void performRequest(Request req) {
		if (RequestConstants.REQ_OPEN.equals(req.getType())) {
			SetValueCommand cmd = new SetValueCommand();
			cmd.setTarget((IPropertySource) getModel());
			cmd.setPropertyId(JRDesignBand.PROPERTY_HEIGHT);
			cmd.setPropertyValue(ModelUtils.getBandHeight(getBand()));
			getViewer().getEditDomain().getCommandStack().execute(cmd);
		}
		super.performRequest(req);
	}

	/**
	 * Gets the jasper design.
	 * 
	 * @return the jasper design
	 */
	public JasperDesign getJasperDesign() {
		try {
			return ((MBand) getModel()).getJasperDesign();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		JRDesignBand jrBand = getBand();
		BandTypeEnum bandTypeValue = jrBand.getOrigin().getBandTypeValue();
		boolean drawColumns = bandTypeValue.equals(BandTypeEnum.COLUMN_FOOTER)
				|| bandTypeValue.equals(BandTypeEnum.COLUMN_HEADER) || bandTypeValue.equals(BandTypeEnum.GROUP_FOOTER)
				|| bandTypeValue.equals(BandTypeEnum.COLUMN_HEADER) || bandTypeValue.equals(BandTypeEnum.DETAIL);

		BandFigure rect = new BandFigure(drawColumns);
		rect.setForegroundColor(ColorConstants.blue);
		setupBandFigure(rect);
		setBandNameShowing(rect);
		return rect;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ElementEditPolicy());
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new BandMoveEditPolicy() {
			@Override
			protected void showSelection() {
				updateRullers();
			}
		});
		// installEditPolicy(EditPolicy.CONTAINER_ROLE, new BandContainerEditPolicy());
		// installEditPolicy(EditPolicy.LAYOUT_ROLE, new XYLayoutEditPolicy() {
		//
		// @Override
		// protected Command getCreateCommand(CreateRequest request) {
		// // TODO Auto-generated method stub
		// return null;
		// }
		//
		// @Override
		// protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
		// // TODO Auto-generated method stub
		// return null;
		// }
		// });
	}

	public EditPolicy getEditPolicy() {
		return new BandResizableEditPolicy();
	}

	@Override
	public boolean isSelectable() {
		return true;
	}

	/**
	 * Class name.
	 * 
	 * @param cls
	 *          the cls
	 * @return the string
	 */
	public static String className(Object cls) {
		String className = cls.getClass().getName();
		return className.substring(className.lastIndexOf('.') + 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
	 */
	public void refreshVisuals() {
		IFigure rect = getFigure();
		setupBandFigure(rect);
		rect.repaint();
	}

	private void setupBandFigure(IFigure rect) {
		JRDesignBand jrBand = getBand();
		MBand bandNode = (MBand) getModel();
		Rectangle bounds = (bandNode).getBounds();
		JasperDesign jasperDesign = getJasperDesign();
		BandFigure bfig = (BandFigure) rect;
		bfig.setMarginLeft(jasperDesign.getLeftMargin());
		bfig.setMarginRight(jasperDesign.getRightMargin());
		bfig.setColumnNumber(jasperDesign.getColumnCount());
		bfig.setColumnWidth(jasperDesign.getColumnWidth());
		bfig.setColumnSpacing(jasperDesign.getColumnSpacing());

		// int width = jasperDesign.getPageWidth() + PageFigure.PAGE_BORDER.left + 1;
		int width = jasperDesign.getPageWidth() + 1;
		int height = jrBand != null ? jrBand.getHeight() + 1 : 0;
		rect.setBounds(new Rectangle(ReportPageFigure.PAGE_BORDER.left, bounds.y + ReportPageFigure.PAGE_BORDER.top, width,
				height));
		// rect.setBounds(new Rectangle(0, bounds.y + PageFigure.PAGE_BORDER.top, width, height));

		// update tooltip and band text (shown in background)
		bfig.setToolTip(new Label(bandNode.getToolTip()));
		bfig.setBandText(bandNode.getSimpleDisplayName());

		updateRullers();
	}

	protected void updateRullers() {
		EditPart ep = getParent();
		if (ep instanceof ReportPageEditPart)
			((ReportPageEditPart) ep).updateRullers(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if (getParent() != null)
			getParent().refresh();
	}

	public Object getConstraintFor(ChangeBoundsRequest request, GraphicalEditPart child) {
		if (request.getResizeDirection() == PositionConstants.SOUTH
				|| request.getResizeDirection() == PositionConstants.NORTH)
			System.out.println(" Constraint request:  " + request.getSizeDelta() + "  " + request.getResizeDirection()); //$NON-NLS-1$ //$NON-NLS-2$
		return new Rectangle(0, 0, 0, request.getSizeDelta().height);
	}

	/*
	 * Update flag for band name showing.
	 */
	private void setBandNameShowing(BandFigure figure) {
		boolean showBandName = PropertiesHelper.getInstance(getModel().getJasperConfiguration()).getBoolean(
				DesignerPreferencePage.P_SHOW_REPORT_BAND_NAMES, false);
		figure.setShowBandName(showBandName);
	}
}
