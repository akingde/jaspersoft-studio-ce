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
package com.jaspersoft.studio.editor.gef.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.CompoundSnapToHelper;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToGuides;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.SnapFeedbackPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.jface.util.IPropertyChangeListener;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.snap.SnapToGuidesAction;
import com.jaspersoft.studio.editor.gef.commands.SetPageConstraintCommand;
import com.jaspersoft.studio.editor.gef.figures.PageFigure;
import com.jaspersoft.studio.editor.gef.figures.layers.GridLayer;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.PageLayoutEditPolicy;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGraphicElement;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.util.ModelVisitor;
import com.jaspersoft.studio.preferences.DesignerPreferencePage;

/*
 * The Class PageEditPart.
 * 
 * @author Chicu Veaceslav
 */
public class PageEditPart extends AJDEditPart implements PropertyChangeListener {
	private PreferenceListener preferenceListener;

	private final class PreferenceListener implements IPropertyChangeListener {

		public void propertyChange(org.eclipse.jface.util.PropertyChangeEvent event) {
			if (event.getProperty().equals(DesignerPreferencePage.P_PAGE_DESIGN_BORDER_STYLE))
				setPrefsBorder(getFigure());
		}
	}

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
				snapStrategies.add(new SnapToGeometry(this));
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

	@Override
	public void activate() {
		super.activate();
		preferenceListener = new PreferenceListener();
		JaspersoftStudioPlugin.getInstance().getPreferenceStore().addPropertyChangeListener(preferenceListener);
	}

	@Override
	public void deactivate() {
		if (preferenceListener != null)
			JaspersoftStudioPlugin.getInstance().getPreferenceStore().removePropertyChangeListener(preferenceListener);
		super.deactivate();
	}

	private void setPrefsBorder(IFigure rect) {
		// String pref = Platform.getPreferencesService().getString(JaspersoftStudioPlugin.getUniqueIdentifier(),
		//				DesignerPreferencePage.P_PAGE_DESIGN_BORDER_STYLE, "shadow", null); //$NON-NLS-1$

		//		if (pref.equals("shadow")) //$NON-NLS-1$
		// rect.setBorder(new ShadowBorder());
		// else
		// rect.setBorder(new SimpleShadowBorder());
	}

	/**
	 * Updates the {@link GridLayer grid} based on properties set on the {@link #getViewer() graphical viewer}:
	 * {@link SnapToGrid#PROPERTY_GRID_VISIBLE}, {@link SnapToGrid#PROPERTY_GRID_SPACING}, and
	 * {@link SnapToGrid#PROPERTY_GRID_ORIGIN}.
	 * <p>
	 * This method is invoked initially when the GridLayer is created, and when any of the above-mentioned properties are
	 * changed on the viewer.
	 */
	protected void refreshGridLayer() {
		boolean visible = false;
		GridLayer grid = ((PageFigure) getFigure()).getGrid();
		Boolean val = (Boolean) getViewer().getProperty(SnapToGrid.PROPERTY_GRID_VISIBLE);
		if (val != null)
			visible = val.booleanValue();
		grid.setOrigin((Point) getViewer().getProperty(SnapToGrid.PROPERTY_GRID_ORIGIN));
		grid.setSpacing((Dimension) getViewer().getProperty(SnapToGrid.PROPERTY_GRID_SPACING));
		grid.setVisible(visible);
		getFigure().repaint();
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractEditPart#register()
	 */
	protected void register() {
		super.register();
		getViewer().addPropertyChangeListener(gridListener);
		refreshGridLayer();
	}

	/**
	 * @see AbstractEditPart#unregister()
	 */
	protected void unregister() {
		getViewer().removePropertyChangeListener(gridListener);
		super.unregister();
	}

	private PropertyChangeListener gridListener = new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent evt) {
			String property = evt.getPropertyName();
			if (property.equals(SnapToGrid.PROPERTY_GRID_ORIGIN) || property.equals(SnapToGrid.PROPERTY_GRID_SPACING)
					|| property.equals(SnapToGrid.PROPERTY_GRID_VISIBLE))
				refreshGridLayer();
		}
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#getModel()
	 */
	@Override
	public Object getModel() {
		Object model = super.getModel();
		if (model instanceof MRoot)
			return ((MRoot) model).getChildren().get(0);
		return model;
	}

	/**
	 * Gets the page.
	 * 
	 * @return the page
	 */
	public ANode getPage() {
		return (ANode) getModel();
	}

	/**
	 * Gets the jasper design.
	 * 
	 * @return the jasper design
	 */
	public JasperDesign getJasperDesign() {
		return getPage().getJasperDesign();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		JasperDesign jd = getJasperDesign();
		PageFigure figure = new PageFigure(jd, true);
		setPrefsBorder(figure);
		setupPageFigure(jd, figure);
		// get current display...
		figure.setOpaque(false);
		figure.setBackgroundColor(ColorConstants.white);
		figure.setLayoutManager(new XYLayout());

		return figure;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new PageLayoutEditPolicy() {
			protected Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart child, Object constraint) {
				Rectangle r = (Rectangle) constraint;
				r.setLocation(r.x - PageFigure.PAGE_BORDER.left, r.y - PageFigure.PAGE_BORDER.top);

				SetPageConstraintCommand cmd = new SetPageConstraintCommand();
				cmd.setContext((ANode) getHost().getModel(), (ANode) child.getModel(), (Rectangle) constraint);

				return cmd;
			}

			@Override
			public Command getCommand(Request request) {
				if (request instanceof ChangeBoundsRequest) {
					if (request.getType() == REQ_MOVE || request.getType() == REQ_MOVE_CHILDREN) {
						for (Object obj : ((ChangeBoundsRequest) request).getEditParts()) {
							if (obj instanceof EditableFigureEditPart)
								return null;
						}
					}
				}
				return super.getCommand(request);
			}

		});
		installEditPolicy("Snap Feedback", new SnapFeedbackPolicy()); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getDragTracker(org.eclipse.gef.Request)
	 */
	@Override
	public DragTracker getDragTracker(Request request) {
		return getRoot().getDragTracker(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
	 */
	protected List<Object> getModelChildren() {
		final List<Object> list = new ArrayList<Object>();
		new ModelVisitor(getPage()) {

			@Override
			public boolean visit(INode n) {
				if (n instanceof IGraphicElement && n.getValue() != null)
					list.add(n);
				return true;
			}
		};
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#addChildVisual(org.eclipse.gef.EditPart, int)
	 */
	@Override
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (childEditPart instanceof IContainerPart) {
			IFigure layer = getLayer(MainDesignerRootEditPart.SECTIONS_LAYER);
			if (layer != null) {
				layer.add(((AbstractGraphicalEditPart) childEditPart).getFigure());
			}
		} else if (childEditPart instanceof FigureEditPart) {
			IFigure layer = getLayer(MainDesignerRootEditPart.ELEMENTS_LAYER);
			if (layer != null) {
				layer.add(((FigureEditPart) childEditPart).getFigure());
			}
		}
		super.addChildVisual(childEditPart, index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
	 */
	public void refreshVisuals() {
		JasperDesign jasperDesign = getJasperDesign();
		PageFigure figure2 = (PageFigure) getFigure();

		setupPageFigure(jasperDesign, figure2);
		for (Object i : getChildren()) {
			if (i instanceof EditPart)
				((EditPart) i).refresh();
		}
		/*
		 * ((GridLayout) figure2.getLayoutManager()).marginHeight = jasperDesign.getTopMargin();
		 * figure2.getLayoutManager().layout(figure2);
		 */
		figure2.repaint();
	}

	/**
	 * Setup page figure.
	 * 
	 * @param jd
	 *          the jasper design
	 * @param figure2
	 *          the figure2
	 */
	private void setupPageFigure(JasperDesign jd, PageFigure figure2) {
		int w = 2000;// jd.getPageWidth() + 20;
		int h = 5000;// designHeight + 10;

		figure2.setSize(w, h);

		getViewer().setProperty("RULER_HOFFSET", PageFigure.PAGE_BORDER.left); //$NON-NLS-1$
		getViewer().setProperty("RULER_VOFFSET", PageFigure.PAGE_BORDER.top); //$NON-NLS-1$
		getViewer().setProperty("RULER_HEND", jd.getPageWidth()); //$NON-NLS-1$
		getViewer().setProperty("RULER_VEND", jd.getPageHeight() - PageFigure.PAGE_BORDER.top); //$NON-NLS-1$

		getViewer().setProperty(SnapToGrid.PROPERTY_GRID_ORIGIN,
				new Point(PageFigure.PAGE_BORDER.left, PageFigure.PAGE_BORDER.top));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent arg0) {
		Object source = arg0.getSource();
		// if (source instanceof IContainerEditPart) {
		refreshChildren();
		refreshVisuals();
		// }
	}

}
