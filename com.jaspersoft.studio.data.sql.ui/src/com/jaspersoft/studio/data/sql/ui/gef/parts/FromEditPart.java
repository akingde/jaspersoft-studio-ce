/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.ui.gef.parts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.wb.swt.SWTResourceManager;

import com.jaspersoft.studio.data.sql.QueryWriter;
import com.jaspersoft.studio.data.sql.model.ISubQuery;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.MUnion;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.subquery.MQueryTable;
import com.jaspersoft.studio.data.sql.ui.gef.command.SetSilentValuesCommand;
import com.jaspersoft.studio.data.sql.ui.gef.layout.GraphLayoutManager;
import com.jaspersoft.studio.data.sql.ui.gef.policy.FromContainerEditPolicy;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.NoSelectionEditPolicy;
import com.jaspersoft.studio.model.AMapElement;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.ModelVisitor;
import com.jaspersoft.studio.property.SetValueCommand;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;

public class FromEditPart extends AbstractGraphicalEditPart {

	public static final Insets INSETS = new Insets(10, 10, 10, 10);

	@Override
	protected IFigure createFigure() {
		RoundedRectangle fig = new FromFigure();
		fig.setLayoutManager(xyLayout);
		fig.setBackgroundColor(SWTResourceManager.getColor(248, 248, 255));
		fig.setOpaque(true);
		return fig;
	}

	@Override
	public MFrom getModel() {
		return (MFrom) super.getModel();
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.CONTAINER_ROLE, new FromContainerEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new XYLayoutEditPolicy() {
			private RoundedRectangle targetFeedback;

			@Override
			protected void showLayoutTargetFeedback(Request request) {
				if (targetFeedback == null) {
					targetFeedback = new RoundedRectangle();
					targetFeedback.setFill(false);

					IFigure hostFigure = getHostFigure();
					Rectangle bounds = hostFigure.getBounds();
					if (hostFigure instanceof HandleBounds)
						bounds = ((HandleBounds) hostFigure).getHandleBounds();
					Rectangle rect = new PrecisionRectangle(bounds);
					getHostFigure().translateToAbsolute(rect);
					getFeedbackLayer().translateToRelative(rect);

					targetFeedback.setBounds(rect.shrink(1, 1));
					targetFeedback.setBorder(new LineBorder(ColorConstants.lightBlue, 2));
					addFeedback(targetFeedback);
				}
			}

			@Override
			protected void eraseLayoutTargetFeedback(Request request) {
				super.eraseLayoutTargetFeedback(request);
				if (targetFeedback != null) {
					removeFeedback(targetFeedback);
					targetFeedback = null;
				}
			}

			@Override
			protected Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart child,
					Object constraint) {
				Rectangle b = getHostFigure().getBounds();
				Rectangle r = (Rectangle) constraint;
				if (b.x + r.x + INSETS.left < INSETS.left)
					return null;

				if (b.y + r.y + INSETS.top < INSETS.top)
					return null;

				Point delta = request.getMoveDelta();
				if (child instanceof TableEditPart) {
					SetValueCommand cmd = new SetValueCommand();
					cmd.setPropertyId(MFromTable.PROP_X);
					cmd.setPropertyValue(new Point(b.x + r.x + INSETS.left, b.y + r.y + INSETS.top));
					cmd.setTarget(((TableEditPart) child).getModel());

					return cmd;
				} else if (child instanceof FromEditPart) {
					FromEditPart fep = (FromEditPart) child;
					CompoundCommand c = new CompoundCommand("Moving Tables");
					List<EditPart> children = child.getChildren();
					moveTables(c, children, fep, delta);
					return c;
				}
				return null;
			}

			@Override
			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}

			@Override
			protected EditPolicy createChildEditPolicy(EditPart child) {
				if (child instanceof TableEditPart || child instanceof FromEditPart)
					return new NoSelectionEditPolicy() {
						@Override
						protected IFigure createDragSourceFeedbackFigure() {
							// Use a ghost rectangle for feedback
							// Label r = new Label();
							// FigureUtilities.makeGhostShape(r);
							// r.setLineStyle(Graphics.LINE_SOLID);
							// r.setForegroundColor(ColorConstants.black);
							// r.setOpaque(true);
							// r.setBorder(new LineBorder(ColorConstants.red));
							// r.setBackgroundColor(ColorConstants.white);
							// r.setBounds(getInitialFeedbackBounds());
							//
							// r.validate();
							// addFeedback(r);
							// return r;
							return super.createDragSourceFeedbackFigure();
						}

						@Override
						protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
							super.showChangeBoundsFeedback(request);
							// Label r = (Label) getDragSourceFeedbackFigure();
							// Rectangle b = r.getBounds();
							// r.setText(b.x + "," + b.y);
						}
					};
				return super.createChildEditPolicy(child);
			}

			@Override
			protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
				return createChangeConstraintCommand(null, child, constraint);
			}
		});
	}

	public static void moveTables(CompoundCommand c, List<EditPart> children, FromEditPart fep, Point delta) {
		if (children.isEmpty()) {
			Point p = fep.readPoint(fep.getModel());
			if (p == null)
				return;
			p = new Point(delta.x + p.x, delta.y + p.y);

			SetValueCommand cmd = new SetValueCommand();
			cmd.setPropertyId(MFromTable.PROP_X);
			cmd.setPropertyValue(p);
			cmd.setTarget(fep.getModel());

			c.add(cmd);
		} else
			for (EditPart ep : children) {
				APropertyNode pn = null;
				if (ep instanceof TableEditPart)
					pn = ((TableEditPart) ep).getModel();
				else if (ep instanceof FromEditPart) {
					moveTables(c, ep.getChildren(), (FromEditPart) ep, delta);
					continue;
				}
				if (pn != null) {
					Point p = fep.readPoint(pn);
					if (p == null)
						return;
					p = new Point(delta.x + p.x, delta.y + p.y);

					SetValueCommand cmd = new SetValueCommand();
					cmd.setPropertyId(MFromTable.PROP_X);
					cmd.setPropertyValue(p);
					cmd.setTarget(pn);

					c.add(cmd);
				}
			}
	}

	@Override
	protected List<?> getModelChildren() {
		final List<ANode> list = new ArrayList<>();
		final MFrom mfrom = getModel();
		new ModelVisitor<ANode>(mfrom) {

			@Override
			public boolean visit(INode n) {
				if (list.contains(n))
					return false;
				if (n instanceof MUnion || n instanceof ISubQuery)
					return false;
				if (n instanceof MFromTable) {
					if (n.getValue() instanceof MQueryTable) {
						List<MFrom> lst = getFrom((ANode) n, mfrom);
						if (!lst.isEmpty())
							list.addAll(lst);
						// return false;
					} else {
						if (n.getValue() instanceof MSqlTable) {
							String name = getTblName((MFromTable) n);
							if (name != null) {
								boolean exists = false;
								for (ANode en : list) {
									if (en instanceof MFromTable) {
										String ename = getTblName((MFromTable) en);
										if (en != null && name.equals(ename)) {
											exists = true;
											break;
										}
									}
								}
								if (exists)
									return true;
							}
						}
						list.add((ANode) n);
					}
				}
				return true;
			}

			private String getTblName(MFromTable n) {
				if (n.getValue() instanceof MSqlTable) {
					MSqlTable tbl = n.getValue();
					return tbl.getValue() + " AS " + n.getAlias();
				}
				return null;
			}
		};
		return list;
	}

	private List<MFrom> getFrom(ANode n, final ANode ignore) {
		final List<MFrom> list = new ArrayList<>();
		new ModelVisitor<MFrom>(n) {

			@Override
			public boolean visit(INode n) {
				if (n instanceof MFrom && n != ignore) {
					list.add((MFrom) n);
					return false;
				}
				return true;
			}
		};
		return list;
	}

	protected void refreshVisuals() {
		RoundedRectangle f = (RoundedRectangle) getFigure();
		MFrom mfrom = getModel();
		f.setToolTip(new Label(QueryWriter.writeSubQuery(mfrom.getParent())));
	}

	@Override
	public void refresh() {
		refreshChildren();
		List<?> children = getModelChildren();
		AbstractGraphicalEditPart parent = (AbstractGraphicalEditPart) getParent();
		Point p = getMinPoint(children);

		if (children.isEmpty()) {
			if (parent instanceof FromEditPart) {
				if (p == null) {
					setupLayoutManager();
					p = new Point(20, 20);

					Point pmin = ((FromEditPart) parent).getMinPoint(children);
					if (pmin != null) {
						p.translate(pmin);

						MFrom m = getModel();
						SetSilentValuesCommand c = new SetSilentValuesCommand(false);
						c.add(m, MFromTable.PROP_X, p.x);
						c.add(m, MFromTable.PROP_Y, p.y);
						getViewer().getEditDomain().getCommandStack().execute(c);
					}
				}
				setupPoint(p, (FromEditPart) parent, ((FromEditPart) parent).getModelChildren());
			}
		} else {
			setupLayoutManager();
			if (parent instanceof FromEditPart)
				setupPoint(p, (FromEditPart) parent, ((FromEditPart) parent).getModelChildren());
		}

		if (p != null) {
			Rectangle r = new Rectangle(p.x, p.y, -1, -1);
			parent.setLayoutConstraint(this, figure, r);
		}
		refreshVisuals();
	}

	private void setupPoint(Point p, FromEditPart fep, List<?> children) {
		if (p == null)
			return;
		Point pmin = fep.getMinPoint(children);
		if (pmin != null) {
			int count = getFromLevel();
			p.translate(pmin.getNegated());
			p.translate(-INSETS.left * count, -INSETS.top * count);
		}
	}

	public int getFromLevel() {
		int count = 1;
		EditPart eparent = getParent();
		while (eparent != null && eparent instanceof FromEditPart) {
			count++;
			eparent = eparent.getParent();
		}
		return count - 2;
	}

	private Point pmin;

	public Point getMinPoint() {
		if (pmin == null)
			pmin = getMinPoint(getModelChildren());
		return pmin;
	}

	public Point getMinPoint(List<?> childs) {
		Point pmin = null;
		if (childs.isEmpty())
			pmin = readPoint(getModel());
		else {
			for (Object item : childs) {
				if (item instanceof APropertyNode) {
					Point p = null;
					if (item instanceof MFrom)
						p = getMinPoint(((MFrom) item).getChildren());
					else
						p = readPoint((APropertyNode) item);
					if (p == null)
						continue;
					if (pmin == null)
						pmin = new Point(p.x, p.y);
					else {
						pmin.x = Math.min(pmin.x, p.x);
						pmin.y = Math.min(pmin.y, p.y);
					}
				}
			}
		}
		if (pmin != null)
			return new Point(pmin.x - INSETS.left, pmin.y - INSETS.top);
		return null;
	}

	public Point readPoint(APropertyNode item) {
		Integer x = null;
		Integer y = null;
		Object pv = item.getPropertyValue(MFromTable.PROP_X);
		if (pv != null && pv instanceof Integer)
			x = (Integer) pv;
		else if (pv != null && pv instanceof Point)
			return null;
		else if (!(item instanceof MFrom) || (item instanceof MFrom && item.getChildren().isEmpty()))
			layout = false;

		pv = item.getPropertyValue(MFromTable.PROP_Y);
		if (pv != null && pv instanceof Integer)
			y = (Integer) pv;
		if (x != null && y != null)
			return new Point(x, y);
		return null;
	}

	private boolean layout = true;
	private boolean isRunning = false;
	private XYLayout xyLayout = new XYLayout();
	private GraphLayoutManager grLayout = new GraphLayoutManager(this);

	protected void setupLayoutManager() {
		if (layout)
			return;
		layout = true;
		if (isRunning)
			return;
		isRunning = true;
		if (Misc.nvl(new ModelVisitor<Boolean>(getModel()) {
			@Override
			public boolean visit(INode n) {
				if (n instanceof MFromTable && !(n.getValue() instanceof MQueryTable)
						&& ((AMapElement) n).getPropertyActualValue(MFromTable.PROP_X) == null) {
					setObject(Boolean.FALSE);
					stop();
				}
				return false;
			}
		}.getObject(), Boolean.FALSE)) {
			figure.setLayoutManager(xyLayout);
			isRunning = false;
		} else {
			figure.setLayoutManager(grLayout);
			UIUtils.getDisplay().asyncExec(new Runnable() {
				private void addToMap(Object aep, Map<AMapElement, Rectangle> map) {
					if (aep instanceof TableEditPart) {
						TableEditPart t = (TableEditPart) aep;
						Rectangle b = t.getFigure().getBounds();
						map.put(t.getModel(), b);
					} else if (aep instanceof FromEditPart) {
						FromEditPart f = (FromEditPart) aep;
						if (f.getModel().getChildren().isEmpty()) {
							Rectangle b = f.getFigure().getBounds();
							map.put(f.getModel(), b);
						} else
							for (Object obj : f.getChildren())
								addToMap(obj, map);
					}
				}

				@Override
				public void run() {
					Map<AMapElement, Rectangle> map = new HashMap<>();
					for (Object p : getChildren())
						addToMap(p, map);
					SetSilentValuesCommand c = new SetSilentValuesCommand(true);
					for (AMapElement key : map.keySet()) {
						if (key instanceof MFrom && !key.getChildren().isEmpty()) {
							c.add(key, MFromTable.PROP_X, null);
							c.add(key, MFromTable.PROP_Y, null);
							continue;
						}

						Rectangle b = map.get(key);
						c.add(key, MFromTable.PROP_X, b.x);
						c.add(key, MFromTable.PROP_Y, b.y);
					}
					figure.setLayoutManager(xyLayout);
					layout = true;
					if (getViewer() != null)
						getViewer().getEditDomain().getCommandStack().execute(c);
					isRunning = false;
				}
			});
		}
	}

	class FromFigure extends RoundedRectangle {
		@Override
		public Insets getInsets() {
			return INSETS;
		}

		@Override
		public void setBounds(Rectangle rect) {
			if (Math.abs(rect.x) > 10000 || Math.abs(rect.y) > 10000)
				return;
			super.setBounds(rect);
		}

		@Override
		public Dimension getMinimumSize(int wHint, int hHint) {
			return new Dimension(INSETS.getWidth(), INSETS.getHeight());
		}

		@Override
		public IFigure getToolTip() {
			IFigure t = super.getToolTip();
			if (t instanceof Label) {
				Label l = (Label) t;
				MFrom mdl = getModel();
				l.setText(mdl.getDisplayText());
//				Rectangle b = getBounds().getCopy();
//				l.setText(l.getText() + "\n" + b);
//				this.translateToAbsolute(b);
//				l.setText(l.getText() + "\n" + b);
//				this.translateToRelative(b);
//				l.setText(l.getText() + "\n" + b);
//				l.setText(l.getText() + "\nX" + mdl.getPropertyValue(MFromTable.PROP_X));
//				l.setText(l.getText() + "\nY" + mdl.getPropertyValue(MFromTable.PROP_Y));
//
//				l.setText(l.getText() + "\nLayour: " + getLayoutManager());
//
//				l.setText(l.getText() + "\nChildren Model: " + mdl.getChildren().size());
//				l.setText(l.getText() + "\nChildren Figures: " + getChildren().size());
//				for (Figure f : (List<Figure>) getChildren()) {
//					l.setText(l.getText() + "\n" + f.getBounds());
//					l.setText(l.getText() + "\n" + ((Label) f.getToolTip()).getText() + "\n------");
//				}
			}
			return t;
		}

	}

}
