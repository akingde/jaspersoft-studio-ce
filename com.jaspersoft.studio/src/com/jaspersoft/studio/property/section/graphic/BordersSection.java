/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.section.graphic;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRBoxContainer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.base.JRBaseLineBox;
import net.sf.jasperreports.engine.base.JRBasePen;
import net.sf.jasperreports.engine.base.JRBasePrintText;
import net.sf.jasperreports.engine.export.draw.BoxDrawer;
import net.sf.jasperreports.engine.type.LineStyleEnum;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.java2d.J2DGraphics;
import com.jaspersoft.studio.editor.java2d.J2DLightweightSystem;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElementLineBox;
import com.jaspersoft.studio.model.MLineBox;
import com.jaspersoft.studio.model.MLinePen;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.color.ColorLabelProvider;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.EnumHelper;

/**
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class BordersSection extends AbstractSection {

	private final class LineBoxDrawer extends BoxDrawer {

		public void drawBox(Graphics2D graphics2d, JRLineBox box, JRPrintElement element) throws JRException {
			drawBox(graphics2d, box, element, 0, 0);
		}
	}

	private Button allBorder;
	private Button topBorder;
	private Button bottomBorder;
	private Button leftBorder;
	private Button rightBorder;
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
		GridLayout layout = new GridLayout(7, false);
		composite.setLayout(layout);

		CLabel label = getWidgetFactory().createCLabel(composite, "Box:", SWT.RIGHT);
		GridData gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		gd.verticalSpan = 2;
		gd.widthHint = 100;
		label.setLayoutData(gd);

		createBorderPreview(composite);

		rightPanel = new Group(composite, SWT.NONE);
		rightPanel.setText("Border && Padding");
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

		createButtons(composite);

		stackLayout.topControl = ac;
		allBorder.setSelection(true);
	}

	private void createBorderPreview(Composite composite) {
		GridData gd;
		Canvas square = new Canvas(composite, SWT.BORDER | SWT.NO_REDRAW_RESIZE | SWT.NO_BACKGROUND);
		gd = new GridData(GridData.FILL_VERTICAL | GridData.HORIZONTAL_ALIGN_CENTER | GridData.VERTICAL_ALIGN_CENTER);
		gd.horizontalSpan = 5;
		gd.widthHint = 120;
		square.setLayoutData(gd);

		LightweightSystem lws = new J2DLightweightSystem();
		lws.setControl(square);

		borderPreview = new RectangleFigure() {
			private LineBoxDrawer bd = new LineBoxDrawer();

			@Override
			public void paint(Graphics graphics) {
				try {
					Graphics2D graphics2d = ((J2DGraphics) graphics).getGraphics2D();

					Rectangle b = getBounds();

					JRPrintElement pe = new JRBasePrintText(null);
					pe.setX(b.x + 10);
					pe.setY(b.y + 10);
					pe.setWidth(b.width - 20);
					pe.setHeight(b.height - 20);

					bd.drawBox(graphics2d, ((JRBoxContainer) getElement().getValue()).getLineBox(), pe);
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

		getWidgetFactory().createCLabel(composite, "Padding:", SWT.RIGHT);

		final Spinner padding = new Spinner(composite, SWT.BORDER);
		padding.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);
		padding.setToolTipText("padding");
		padding.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(property, property, new Integer(padding.getSelection()));
			}
		});
		pMap.put(property, padding);

		getWidgetFactory().createCLabel(composite, "Pen Color:", SWT.RIGHT);

		Button lineColor = new Button(composite, SWT.FLAT);
		lineColor.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ColorDialog cd = new ColorDialog(composite.getShell());
				cd.setText("Line color");
				cd.setRGB((RGB) getElement().getPropertyValue(JRBasePen.PROPERTY_LINE_COLOR));
				RGB newColor = cd.open();
				changeProperty(property, JRBasePen.PROPERTY_LINE_COLOR, newColor);
			}
		});
		GridData gd = new GridData();
		gd.widthHint = 30;
		lineColor.setLayoutData(gd);
		lineColorMap.put(property + "." + JRBasePen.PROPERTY_LINE_COLOR, lineColor);

		getWidgetFactory().createCLabel(composite, "Pen Style:");

		final CCombo lineStyle = new CCombo(composite, SWT.BORDER | SWT.FLAT | SWT.READ_ONLY);
		lineStyle.setItems(EnumHelper.getEnumNames(LineStyleEnum.values(), NullEnum.INHERITED));
		lineStyle.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeProperty(property, JRBasePen.PROPERTY_LINE_STYLE, new Integer(lineStyle.getSelectionIndex()));
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		lineStyle.setToolTipText("Line style");
		lineStyleMap.put(property + "." + JRBasePen.PROPERTY_LINE_STYLE, lineStyle);

		getWidgetFactory().createCLabel(composite, "Pen Width:", SWT.RIGHT);

		final Spinner lineWidth = new Spinner(composite, SWT.BORDER);
		lineWidth.setValues(0, 0, 5000, 1, 1, 1);
		lineWidth.setToolTipText("width");
		lineWidth.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int selection = lineWidth.getSelection();
				int digits = lineWidth.getDigits();
				Float newValue = new Float(selection / Math.pow(10, digits));
				changeProperty(property, JRBasePen.PROPERTY_LINE_WIDTH, newValue);
			}
		});
		lineWidthMap.put(property + "." + JRBasePen.PROPERTY_LINE_WIDTH, lineWidth);
		return composite;
	}

	private void createButtons(Composite composite) {
		allBorder = new Button(composite, SWT.FLAT | SWT.TOGGLE);
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
		allBorder.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/border_frame.gif"));
		allBorder.setToolTipText("all borders");

		topBorder = new Button(composite, SWT.FLAT | SWT.TOGGLE);
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
		topBorder.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/border_top.gif"));
		topBorder.setToolTipText("top border");

		bottomBorder = new Button(composite, SWT.FLAT | SWT.TOGGLE);
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
		bottomBorder.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/border_bottom.gif"));
		bottomBorder.setToolTipText("bottom border");

		leftBorder = new Button(composite, SWT.FLAT | SWT.TOGGLE);
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
		leftBorder.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/border_left.gif"));
		leftBorder.setToolTipText("left border");

		rightBorder = new Button(composite, SWT.FLAT | SWT.TOGGLE);
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
		rightBorder.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/border_right.gif"));
		rightBorder.setToolTipText("right border");
	}

	public void changeProperty(String prop, String property, Object newValue) {
		if (!isRefreshing) {
			APropertyNode m = (APropertyNode) getElement();
			MLineBox lb = (MLineBox) m.getPropertyValue(MGraphicElementLineBox.LINE_BOX);
			if (prop.equals(property))
				changeProperty(property, newValue, lb);
			else {
				if (prop.equals(JRBaseLineBox.PROPERTY_PADDING)) {
					MLinePen lp = (MLinePen) lb.getPropertyValue(MLineBox.LINE_PEN);
					changeProperty(property, newValue, lp);
				} else if (prop.equals(JRBaseLineBox.PROPERTY_TOP_PADDING)) {
					MLinePen lp = (MLinePen) lb.getPropertyValue(MLineBox.LINE_PEN_TOP);
					changeProperty(property, newValue, lp);
				} else if (prop.equals(JRBaseLineBox.PROPERTY_BOTTOM_PADDING)) {
					MLinePen lp = (MLinePen) lb.getPropertyValue(MLineBox.LINE_PEN_BOTTOM);
					changeProperty(property, newValue, lp);
				} else if (prop.equals(JRBaseLineBox.PROPERTY_LEFT_PADDING)) {
					MLinePen lp = (MLinePen) lb.getPropertyValue(MLineBox.LINE_PEN_LEFT);
					changeProperty(property, newValue, lp);
				} else if (prop.equals(JRBaseLineBox.PROPERTY_RIGHT_PADDING)) {
					MLinePen lp = (MLinePen) lb.getPropertyValue(MLineBox.LINE_PEN_RIGHT);
					changeProperty(property, newValue, lp);
				}
			}
		}
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
	 */
	public void refresh() {
		isRefreshing = true;
		APropertyNode m = (APropertyNode) getElement();
		MLineBox lb = (MLineBox) m.getPropertyValue(MGraphicElementLineBox.LINE_BOX);
		refreshPadding(lb);
		refreshLinePen(lb, MLineBox.LINE_PEN, JRBaseLineBox.PROPERTY_PADDING);
		refreshLinePen(lb, MLineBox.LINE_PEN_TOP, JRBaseLineBox.PROPERTY_TOP_PADDING);
		refreshLinePen(lb, MLineBox.LINE_PEN_BOTTOM, JRBaseLineBox.PROPERTY_BOTTOM_PADDING);
		refreshLinePen(lb, MLineBox.LINE_PEN_LEFT, JRBaseLineBox.PROPERTY_LEFT_PADDING);
		refreshLinePen(lb, MLineBox.LINE_PEN_RIGHT, JRBaseLineBox.PROPERTY_RIGHT_PADDING);
		//
		// if (borderPreview != null)
		// borderPreview.repaint();
		isRefreshing = false;
	}

	private Map<String, Spinner> pMap = new HashMap<String, Spinner>();

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
	 */
	public void refreshPadding(MLineBox lb) {
		Set<String> keys = pMap.keySet();
		for (String key : keys) {
			Integer padding = (Integer) lb.getPropertyValue(key);
			pMap.get(key).setSelection(padding != null ? padding : 0);
		}
	}

	private Map<String, Spinner> lineWidthMap = new HashMap<String, Spinner>();
	private Map<String, CCombo> lineStyleMap = new HashMap<String, CCombo>();
	private Map<String, Button> lineColorMap = new HashMap<String, Button>();

	public void refreshLinePen(MLineBox lb, String property, String mapProperty) {
		MLinePen lp = (MLinePen) lb.getPropertyValue(property);

		Float propertyValue = (Float) lp.getPropertyValue(JRBasePen.PROPERTY_LINE_WIDTH);
		Spinner lineWidth = lineWidthMap.get(mapProperty + "." + JRBasePen.PROPERTY_LINE_WIDTH);
		if (propertyValue != null)
			lineWidth.setSelection((int) (propertyValue.doubleValue() * Math.pow(10, 1)));
		else
			lineWidth.setSelection(0);

		CCombo lineStyle = lineStyleMap.get(mapProperty + "." + JRBasePen.PROPERTY_LINE_STYLE);
		lineStyle.select(((Integer) lp.getPropertyValue(JRBasePen.PROPERTY_LINE_STYLE)).intValue());

		RGB backcolor = (RGB) lp.getPropertyValue(JRBasePen.PROPERTY_LINE_COLOR);
		Button lineColor = lineColorMap.get(mapProperty + "." + JRBasePen.PROPERTY_LINE_COLOR);
		if (backcolor != null)
			lineColor.setImage(colorLabelProvider.getImage(backcolor));
		else
			lineColor.setImage(null);

	}

	private ColorLabelProvider colorLabelProvider = new ColorLabelProvider(null);
	private RectangleFigure borderPreview;

}
