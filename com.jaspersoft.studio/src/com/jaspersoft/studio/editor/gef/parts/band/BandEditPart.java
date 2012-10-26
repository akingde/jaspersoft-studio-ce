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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.BandTypeEnum;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.CompoundSnapToHelper;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToGuides;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.snap.SnapToGuidesAction;
import com.jaspersoft.studio.editor.gef.figures.BandFigure;
import com.jaspersoft.studio.editor.gef.figures.ReportPageFigure;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.FrameFigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.IContainerPart;
import com.jaspersoft.studio.editor.gef.parts.ReportPageEditPart;
import com.jaspersoft.studio.editor.gef.parts.SnapToGeometryThreshold;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.ColoredRectangle;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.ElementEditPolicy;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.JSSSnapFeedBackPolicy;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.PageLayoutEditPolicy;
import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.command.CreateElementCommand;
import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.preferences.DesignerPreferencePage;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.property.dataset.dialog.IDatasetDialogSupport;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.SWTResourceManager;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/*
 * BandEditPart creates the figure for the band. The figure is actually just the bottom border of the band. This allows
 * to drag this border to resize the band. The PageEditPart sets a specific contraint for the BandEditPart elements in
 * order to make them move only vertically. The BandMoveEditPolicy is responsable for the feedback when the band is
 * dragged.
 * 
 * @author Chicu Veaceslav, Giulio Toffoli
 */
public class BandEditPart extends FigureEditPart implements PropertyChangeListener, IContainerPart, IContainer,
		IDatasetDialogSupport {

	private BandPreferenceListener prefChangelistener;
	private JasperReportsConfiguration jConfig;
	public static final RGB DEFAULTCOLOR = new RGB(170, 168, 255);

	/*
	 * Preferences listener for band preferences/properties.
	 */
	private final class BandPreferenceListener implements IPropertyChangeListener {
		public void propertyChange(org.eclipse.jface.util.PropertyChangeEvent event) {
			if (event.getProperty().equals(DesignerPreferencePage.P_SHOW_REPORT_BAND_NAMES)) {
				setBandNameShowing(getFigure());
			} else if (event.getProperty().equals(DesignerPreferencePage.P_CONTAINER_MARGIN_COLOR)) {
				setBandMarginColor(getFigure());
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

	@Override
	public BandFigure getFigure() {
		return (BandFigure) super.getFigure();
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
			int bandHeight = ModelUtils.getBandHeight(getBand());
			if (bandHeight > 0) {
				SetValueCommand cmd = new SetValueCommand();
				cmd.setTarget((IPropertySource) getModel());
				cmd.setPropertyId(JRDesignBand.PROPERTY_HEIGHT);
				cmd.setPropertyValue(bandHeight);
				getViewer().getEditDomain().getCommandStack().execute(cmd);
			}
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

	/**
	 * A different drag tracker will be used to allow to do a drag selection without selecting the marquee tool
	 */
	@Override
	public DragTracker getDragTracker(Request request) {
		return new SameBandEditPartsTracker(this);
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
				|| bandTypeValue.equals(BandTypeEnum.GROUP_HEADER) || bandTypeValue.equals(BandTypeEnum.COLUMN_HEADER)
				|| bandTypeValue.equals(BandTypeEnum.DETAIL);

		BandFigure rect = new BandFigure(drawColumns);
		rect.setForegroundColor(ColorConstants.blue);
		setupBandFigure(rect);
		setBandNameShowing(rect);
		setBandMarginColor(rect);
		return rect;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class key) {
		if (key == SnapToHelper.class) {
			List<SnapToHelper> snapStrategies = new ArrayList<SnapToHelper>();
			Boolean val = (Boolean) getViewer().getProperty(RulerProvider.PROPERTY_RULER_VISIBILITY);
			Boolean stg = (Boolean) getViewer().getProperty(SnapToGuidesAction.ID);
			if (val != null && val.booleanValue() && stg != null && stg.booleanValue())
				snapStrategies.add(new SnapToGuides(this));
			val = (Boolean) getViewer().getProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED);
			if (val != null && val.booleanValue()) {

				SnapToGeometryThreshold snapper = new SnapToGeometryThreshold(this);
				snapper.setThreshold(2.0);
				snapStrategies.add(snapper);
			}
			val = (Boolean) getViewer().getProperty(SnapToGrid.PROPERTY_GRID_ENABLED);
			if (val != null && val.booleanValue()) {
				snapStrategies.add(new SnapToGrid(this));
			}

			if (snapStrategies.size() == 0)
				return null;
			if (snapStrategies.size() == 1)
				return snapStrategies.get(0);

			SnapToHelper ss[] = new SnapToHelper[snapStrategies.size()];
			for (int i = 0; i < snapStrategies.size(); i++)
				ss[i] = snapStrategies.get(i);
			return new CompoundSnapToHelper(ss);
		}
		return super.getAdapter(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ElementEditPolicy());
		installEditPolicy("Snap Feedback", new JSSSnapFeedBackPolicy());
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new BandMoveEditPolicy() {
			@Override
			protected void showSelection() {
				updateRulers();
			}

		});
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new PageLayoutEditPolicy() {

			@Override
			protected Command getCreateCommand(ANode parent, Object obj, Rectangle constraint, int index) {
				Rectangle rect = ((Rectangle) constraint).getCopy();
				rect = rect.getTranslated(-ReportPageFigure.PAGE_BORDER.left, -ReportPageFigure.PAGE_BORDER.right);
				return super.getCreateCommand(parent, obj, rect, index);
			}

			@Override
			protected Command createAddCommand(EditPart child, Object constraint) {
				Rectangle rect = ((Rectangle) constraint).getCopy();
				rect = rect.getTranslated(-ReportPageFigure.PAGE_BORDER.left, -ReportPageFigure.PAGE_BORDER.right);
				if (child.getModel() instanceof MGraphicElement) {
					MGraphicElement cmodel = (MGraphicElement) child.getModel();
					MBand mband = getModel();
					if (cmodel.getParent() instanceof MBand && cmodel.getParent() == mband) {
						return super.createChangeConstraintCommand(child, rect);
					} else {
						CompoundCommand c = new CompoundCommand();

						c.add(OutlineTreeEditPartFactory.getOrphanCommand(cmodel.getParent(), cmodel));
						c.add(new CreateElementCommand(mband, cmodel, CreateElementCommand.fixLocation(rect, mband,
								cmodel.getValue()), -1));
						return c;
					}
				}
				return null;
			}

			private RectangleFigure targetFeedback;

			/**
			 * Show the feedback during drag and drop
			 */
			protected void showLayoutTargetFeedback(Request request) {
				super.showLayoutTargetFeedback(request);
				getLayoutTargetFeedback(request);
			}

			/**
			 * Erase the feedback from a ban when no element is dragged into it
			 */
			protected void eraseLayoutTargetFeedback(Request request) {
				super.eraseLayoutTargetFeedback(request);
				if (targetFeedback != null) {
					removeFeedback(targetFeedback);
					targetFeedback = null;
				}
			}

			/**
			 * Paint the figure to give the feedback, a blue border overlapping the band border
			 * 
			 * @param request
			 * @return feedback figure
			 */
			protected IFigure getLayoutTargetFeedback(Request request) {
				if (request.getType().equals(RequestConstants.REQ_CREATE) && request instanceof CreateRequest) {
					CreateRequest cbr = (CreateRequest) request;
					if (cbr.getNewObject() instanceof Collection<?>) {
						Collection<?> c = (Collection<?>) cbr.getNewObject();
						if (!c.isEmpty() && c.iterator().next() instanceof MStyle)
							return null;
					}
				} else if (request instanceof ChangeBoundsRequest) {
					ChangeBoundsRequest cbr = (ChangeBoundsRequest) request;
					List<EditPart> lst = cbr.getEditParts();
					for (EditPart ep : lst)
						if (((ANode) ep.getModel()).getParent() == getModel())
							return null;
				}
				if (targetFeedback == null) {
					targetFeedback = new ColoredRectangle(FrameFigureEditPart.addElementColor,2.0f);
					targetFeedback.setFill(false);
					IFigure hostFigure = getHostFigure();
					Rectangle bounds = hostFigure.getBounds();
					if (hostFigure instanceof HandleBounds)
						bounds = ((HandleBounds) hostFigure).getHandleBounds();
					Rectangle rect = new PrecisionRectangle(bounds);
					getHostFigure().translateToAbsolute(rect);
					getFeedbackLayer().translateToRelative(rect);

					targetFeedback.setBounds(rect);
					addFeedback(targetFeedback);
				}
				return targetFeedback;
			}

		});
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

		if (getSelected() == 1)
			updateRulers();
		else {
			List<?> selected = getViewer().getSelectedEditParts();
			if (selected.isEmpty())
				updateRulers();
			else
				for (Object obj : selected) {
					if (obj instanceof FigureEditPart) {
						FigureEditPart figEditPart = (FigureEditPart) obj;
						if (figEditPart.getModel().getParent() == bandNode)
							figEditPart.updateRulers();
					}
				}
		}
	}

	@Override
	public void updateRulers() {
		EditPart ep = getParent();
		if (ep instanceof ReportPageEditPart)
			((ReportPageEditPart) ep).updateRullers();
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
		if (jConfig == null)
			jConfig = getModel().getJasperConfiguration();
		boolean showBandName = jConfig.getPropertyBoolean(DesignerPreferencePage.P_SHOW_REPORT_BAND_NAMES, true);
		figure.setShowBandName(showBandName);
	}

	public Dimension getContaierSize() {
		// TODO Auto-generated method stub
		return null;
	}

	private void setBandMarginColor(BandFigure bfigure) {
		if (jConfig == null)
			jConfig = getModel().getJasperConfiguration();
		String mcolor = jConfig.getProperty(DesignerPreferencePage.P_CONTAINER_MARGIN_COLOR, "");
		bfigure.setMarginsColor(SWTResourceManager.getColor(StringConverter.asRGB(mcolor, DEFAULTCOLOR)));
	}
}
