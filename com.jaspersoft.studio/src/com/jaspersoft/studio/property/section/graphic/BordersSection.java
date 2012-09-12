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
package com.jaspersoft.studio.property.section.graphic;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.base.JRBaseLineBox;
import net.sf.jasperreports.engine.base.JRBasePen;
import net.sf.jasperreports.engine.base.JRBasePrintText;
import net.sf.jasperreports.engine.export.draw.BoxDrawer;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.java2d.J2DGraphics;
import com.jaspersoft.studio.editor.java2d.J2DLightweightSystem;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.ILineBox;
import com.jaspersoft.studio.model.MGraphicElementLineBox;
import com.jaspersoft.studio.model.MLineBox;
import com.jaspersoft.studio.model.MLinePen;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.descriptor.color.ColorLabelProvider;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPLineStyleEnum;
import com.jaspersoft.studio.utils.UIUtils;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class BordersSection extends AbstractSection {

	private final class LineBoxDrawer extends BoxDrawer {
		public LineBoxDrawer(JasperReportsContext jasperReportsContext) {
			super(jasperReportsContext);
		}

		public void drawBox(Graphics2D graphics2d, JRLineBox box, JRPrintElement element) {
			drawBox(graphics2d, box, element, 0, 0);
		}
	}

	private ToolItem allBorder;
	private ToolItem topBorder;
	private ToolItem bottomBorder;
	private ToolItem leftBorder;
	private ToolItem rightBorder;
	private StackLayout stackLayout;
	private Control ac;
	private Control at;
	private Control ab;
	private Control al;
	private Control ar;
	private Group rightPanel;

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		GridLayout layout = new GridLayout(3, false);
		composite.setLayout(layout);

		CLabel label = getWidgetFactory().createCLabel(composite, "", SWT.RIGHT); //$NON-NLS-1$
		GridData gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		gd.verticalSpan = 2;
		label.setLayoutData(gd);

		createBorderPreview(composite);

		rightPanel = new Group(composite, SWT.NONE);
		rightPanel.setText(Messages.BordersSection_border_and_padding);
		rightPanel.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.verticalSpan = 2;
		rightPanel.setLayoutData(gd);

		stackLayout = new StackLayout();
		rightPanel.setLayout(stackLayout);

		ac = createStyle(rightPanel, JRBaseLineBox.PROPERTY_PADDING);
		at = createStyle(rightPanel, JRBaseLineBox.PROPERTY_TOP_PADDING);
		ab = createStyle(rightPanel, JRBaseLineBox.PROPERTY_BOTTOM_PADDING);
		al = createStyle(rightPanel, JRBaseLineBox.PROPERTY_LEFT_PADDING);
		ar = createStyle(rightPanel, JRBaseLineBox.PROPERTY_RIGHT_PADDING);

		ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.WRAP);
		toolBar.setBackground(composite.getBackground());
		toolBar.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));

		createButtons(toolBar);

		stackLayout.topControl = ac;
		allBorder.setSelection(true);
	}

	private void createBorderPreview(Composite composite) {
		square = new Canvas(composite, SWT.BORDER | SWT.NO_REDRAW_RESIZE | SWT.NO_BACKGROUND);
		GridData gd = new GridData(GridData.FILL_VERTICAL | GridData.HORIZONTAL_ALIGN_CENTER
				| GridData.VERTICAL_ALIGN_CENTER);
		gd.widthHint = 120;
		square.setLayoutData(gd);

		LightweightSystem lws = new J2DLightweightSystem();
		lws.setControl(square);

		borderPreview = new RectangleFigure() {
			private LineBoxDrawer bd = new LineBoxDrawer(jasperReportsContext);

			@Override
			public void paint(Graphics graphics) {
				try {
					if (graphics instanceof J2DGraphics) {
						Graphics2D graphics2d = ((J2DGraphics) graphics).getGraphics2D();

						Rectangle b = getBounds();

						JRPrintElement pe = new JRBasePrintText(null);
						pe.setX(b.x + 10);
						pe.setY(b.y + 10);
						pe.setWidth(b.width - 20);
						pe.setHeight(b.height - 20);
						if (getElement() instanceof ILineBox && getElement() != null)
							bd.drawBox(graphics2d, ((ILineBox) getElement()).getBoxContainer().getLineBox(), pe);
					} else {
						graphics.drawRectangle(0, 0, 100, 100);
					}
				} catch (Exception e) {
					// when a font is missing exception is thrown by DrawVisitor
					// FIXME: maybe draw something, else?
					e.printStackTrace();
				}
			}
		};

		lws.setContents(borderPreview);
	}

	private Control createStyle(Composite parent, final String property) {
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);

		getWidgetFactory().createCLabel(composite, Messages.common_padding + ":", SWT.RIGHT); //$NON-NLS-1$

		final Spinner padding = new Spinner(composite, SWT.BORDER | SWT.FLAT);
		padding.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);
		padding.setToolTipText(Messages.BordersSection_padding_tool_tip);
		padding.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(property, property, new Integer(padding.getSelection()));
			}
		});
		pMap.put(property, padding);

		getWidgetFactory().createCLabel(composite, Messages.common_pen_color + ":", SWT.RIGHT); //$NON-NLS-1$

		ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.WRAP | SWT.LEFT);
		toolBar.setBackground(composite.getBackground());

		ToolItem lineColor = new ToolItem(toolBar, SWT.RADIO);
		lineColor.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ColorDialog cd = new ColorDialog(composite.getShell());
				cd.setText(Messages.common_line_color);
				cd.setRGB((RGB) getElement().getPropertyValue(JRBasePen.PROPERTY_LINE_COLOR));
				RGB newColor = cd.open();
				changeProperty(property, JRBasePen.PROPERTY_LINE_COLOR, newColor);
			}
		});
		lineColorMap.put(property + "." + JRBasePen.PROPERTY_LINE_COLOR, lineColor); //$NON-NLS-1$

		getWidgetFactory().createCLabel(composite, Messages.common_pen_style + ":"); //$NON-NLS-1$

		createLineStyle(property, composite);

		getWidgetFactory().createCLabel(composite, Messages.common_pen_width + ":", SWT.RIGHT); //$NON-NLS-1$

		final Spinner lineWidth = new Spinner(composite, SWT.BORDER | SWT.FLAT);
		lineWidth.setValues(0, 0, 5000, 1, 1, 1);
		lineWidth.setToolTipText(Messages.BordersSection_width_tool_tip);
		lineWidth.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int selection = lineWidth.getSelection();
				int digits = lineWidth.getDigits();
				Float newValue = new Float(selection / Math.pow(10, digits));
				changeProperty(property, JRBasePen.PROPERTY_LINE_WIDTH, newValue);
			}
		});
		lineWidthMap.put(property + "." + JRBasePen.PROPERTY_LINE_WIDTH, lineWidth); //$NON-NLS-1$
		return composite;
	}

	private void createLineStyle(final String prop, final Composite composite) {
		SPLineStyleEnum linestyle = new SPLineStyleEnum(composite, this, prop) {
			@Override
			public void propertyChange(AbstractSection section, String property, Integer value) {
				((BordersSection) section).changeProperty(prop, property, value);
			}
		};
		lineStyleMap.put(prop + "." + JRBasePen.PROPERTY_LINE_STYLE, linestyle); //$NON-NLS-1$
	}

	private void createButtons(ToolBar toolBar) {
		allBorder = new ToolItem(toolBar, SWT.RADIO);
		allBorder.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (allBorder.getSelection()) {
					topBorder.setSelection(false);
					bottomBorder.setSelection(false);
					leftBorder.setSelection(false);
					rightBorder.setSelection(false);

					stackLayout.topControl = ac;
					rightPanel.layout();
				}
			}
		});
		allBorder.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/border_frame.gif")); //$NON-NLS-1$
		allBorder.setToolTipText(Messages.BordersSection_all_borders_tool_tip);

		topBorder = new ToolItem(toolBar, SWT.RADIO);
		topBorder.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (topBorder.getSelection()) {
					allBorder.setSelection(false);
					bottomBorder.setSelection(false);
					leftBorder.setSelection(false);
					rightBorder.setSelection(false);

					stackLayout.topControl = at;
					rightPanel.layout();
				}
			}
		});
		topBorder.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/border_top.gif")); //$NON-NLS-1$
		topBorder.setToolTipText(Messages.BordersSection_top_border_tool_tip);

		bottomBorder = new ToolItem(toolBar, SWT.RADIO);
		bottomBorder.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (bottomBorder.getSelection()) {
					allBorder.setSelection(false);
					topBorder.setSelection(false);
					leftBorder.setSelection(false);
					rightBorder.setSelection(false);

					stackLayout.topControl = ab;
					rightPanel.layout();
				}
			}
		});
		bottomBorder.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/border_bottom.gif")); //$NON-NLS-1$
		bottomBorder.setToolTipText(Messages.BordersSection_bottom_border_tool_tip);

		leftBorder = new ToolItem(toolBar, SWT.RADIO);
		leftBorder.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (leftBorder.getSelection()) {
					allBorder.setSelection(false);
					topBorder.setSelection(false);
					bottomBorder.setSelection(false);
					rightBorder.setSelection(false);

					stackLayout.topControl = al;
					rightPanel.layout();
				}
			}
		});
		leftBorder.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/border_left.gif")); //$NON-NLS-1$
		leftBorder.setToolTipText(Messages.BordersSection_left_border_tool_tip);

		rightBorder = new ToolItem(toolBar, SWT.RADIO);
		rightBorder.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (rightBorder.getSelection()) {
					allBorder.setSelection(false);
					topBorder.setSelection(false);
					bottomBorder.setSelection(false);
					leftBorder.setSelection(false);

					stackLayout.topControl = ar;
					rightPanel.layout();
				}
			}
		});
		rightBorder.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/border_right.gif")); //$NON-NLS-1$
		rightBorder.setToolTipText(Messages.BordersSection_right_border_tool_tip);
	}

	public void changeProperty(String prop, String property, Object newValue) {
		if (!isRefreshing) {
			CompoundCommand cc = new CompoundCommand("Change padding");
			for (APropertyNode m : getElements()) {
				MLineBox lb = (MLineBox) m.getPropertyValue(MGraphicElementLineBox.LINE_BOX);
				Command c = null;
				if (prop.equals(property))
					c = changeProperty(property, newValue, lb);
				else {
					if (prop.equals(JRBaseLineBox.PROPERTY_PADDING)) {
						MLinePen lp = (MLinePen) lb.getPropertyValue(MLineBox.LINE_PEN);
						c = changeProperty(property, newValue, lp);
					} else if (prop.equals(JRBaseLineBox.PROPERTY_TOP_PADDING)) {
						MLinePen lp = (MLinePen) lb.getPropertyValue(MLineBox.LINE_PEN_TOP);
						c = changeProperty(property, newValue, lp);
					} else if (prop.equals(JRBaseLineBox.PROPERTY_BOTTOM_PADDING)) {
						MLinePen lp = (MLinePen) lb.getPropertyValue(MLineBox.LINE_PEN_BOTTOM);
						c = changeProperty(property, newValue, lp);
					} else if (prop.equals(JRBaseLineBox.PROPERTY_LEFT_PADDING)) {
						MLinePen lp = (MLinePen) lb.getPropertyValue(MLineBox.LINE_PEN_LEFT);
						c = changeProperty(property, newValue, lp);
					} else if (prop.equals(JRBaseLineBox.PROPERTY_RIGHT_PADDING)) {
						MLinePen lp = (MLinePen) lb.getPropertyValue(MLineBox.LINE_PEN_RIGHT);
						c = changeProperty(property, newValue, lp);
					}
				}
				if (c != null)
					cc.add(c);
			}
			CommandStack cs = getEditDomain().getCommandStack();
			cs.execute(cc);
		}
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
	 */
	public void refresh() {
		isRefreshing = true;
		APropertyNode m = getElement();
		if (m != null) {
			MLineBox lb = (MLineBox) m.getPropertyActualValue(MGraphicElementLineBox.LINE_BOX);
			refreshPadding(lb);
			refreshLinePen(lb, MLineBox.LINE_PEN, JRBaseLineBox.PROPERTY_PADDING);
			refreshLinePen(lb, MLineBox.LINE_PEN_TOP, JRBaseLineBox.PROPERTY_TOP_PADDING);
			refreshLinePen(lb, MLineBox.LINE_PEN_BOTTOM, JRBaseLineBox.PROPERTY_BOTTOM_PADDING);
			refreshLinePen(lb, MLineBox.LINE_PEN_LEFT, JRBaseLineBox.PROPERTY_LEFT_PADDING);
			refreshLinePen(lb, MLineBox.LINE_PEN_RIGHT, JRBaseLineBox.PROPERTY_RIGHT_PADDING);
		}
		//
		if (square != null)
			square.redraw();
		isRefreshing = false;
	}

	private Map<String, Spinner> pMap = new HashMap<String, Spinner>();

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
	 */
	public void refreshPadding(MLineBox lb) {
		if (lb != null) {
			Set<String> keys = pMap.keySet();
			for (String key : keys) {
				Integer padding = (Integer) lb.getPropertyActualValue(key);
				Spinner spinner = pMap.get(key);
				if (spinner != null && !spinner.isDisposed())
					spinner.setSelection(padding != null ? padding : 0);
			}
		}
	}

	private Map<String, Spinner> lineWidthMap = new HashMap<String, Spinner>();
	private Map<String, SPLineStyleEnum> lineStyleMap = new HashMap<String, SPLineStyleEnum>();
	private Map<String, ToolItem> lineColorMap = new HashMap<String, ToolItem>();

	public void refreshLinePen(MLineBox lb, String property, String mapProperty) {
		if (lb != null) {
			MLinePen lp = (MLinePen) lb.getPropertyActualValue(property);

			Float propertyValue = (Float) lp.getPropertyActualValue(JRBasePen.PROPERTY_LINE_WIDTH);
			Spinner lineWidth = lineWidthMap.get(mapProperty + "." + JRBasePen.PROPERTY_LINE_WIDTH); //$NON-NLS-1$
			if (lineWidth != null && !lineWidth.isDisposed()) {
				UIUtils.setSpinnerSelection(lineWidth, null, (int) ((propertyValue == null) ? 0 : propertyValue.doubleValue()
						* Math.pow(10, 1)));
			}

			SPLineStyleEnum lineStyle = lineStyleMap.get(mapProperty + "." + JRBasePen.PROPERTY_LINE_STYLE); //$NON-NLS-1$
			if (lineStyle != null && !isDisposed()) {
				int ls = ((Integer) lp.getPropertyActualValue(JRBasePen.PROPERTY_LINE_STYLE)).intValue();
				lineStyle.setData(ls);
			}

			RGB backcolor = (RGB) lp.getPropertyActualValue(JRBasePen.PROPERTY_LINE_COLOR);
			ToolItem lineColor = lineColorMap.get(mapProperty + "." + JRBasePen.PROPERTY_LINE_COLOR); //$NON-NLS-1$
			if (lineColor != null && !lineColor.isDisposed())
				lineColor.setImage(colorLabelProvider.getImage(backcolor));
		}
	}

	private ColorLabelProvider colorLabelProvider = new ColorLabelProvider(null);
	private RectangleFigure borderPreview;
	private Canvas square;

	@Override
	public boolean isDisposed() {
		return rightBorder.isDisposed();
	}

}
