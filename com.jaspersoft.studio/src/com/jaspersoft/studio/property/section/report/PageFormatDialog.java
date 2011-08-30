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
package com.jaspersoft.studio.property.section.report;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.OrientationEnum;

import org.eclipse.core.resources.IFile;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.forms.FormDialog;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.jaspersoft.studio.editor.gef.figures.borders.ShadowBorder;
import com.jaspersoft.studio.editor.java2d.J2DLightweightSystem;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.preferences.DesignerPreferencePage;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.property.section.report.util.PageSize;
import com.jaspersoft.studio.property.section.report.util.UnitsWidget;
import com.jaspersoft.studio.property.section.report.util.ValueUnitsWidget;

final class PageFormatDialog extends FormDialog {
	private JasperDesign jd;
	private MReport jnode;
	private IFile file;

	public PageFormatDialog(Shell shell, ANode node, IFile file) {
		super(shell);

		jnode = (MReport) node.getRoot();
		this.jd = node.getJasperDesign();
		this.file = file;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(Messages.PageFormatDialog_0);
	}

	@Override
	protected void createFormContent(IManagedForm mform) {
		mform.getForm().setText(Messages.PageFormatDialog_1);

		FormToolkit toolkit = mform.getToolkit();

		mform.getForm().getBody().setLayout(new GridLayout(2, false));

		createPageSize(mform, toolkit);

		createThumbnail(mform);

		createOrientation(mform, toolkit);

		createMargins(mform, toolkit);

		createColumns(mform, toolkit);

		setJasperDesign(jd);

		uw.setUnit(new PropertiesHelper(file.getProject()).getString(DesignerPreferencePage.P_PAGE_DEFAULT_UNITS));
		setAllUnits();
	}

	private void createColumns(IManagedForm mform, FormToolkit toolkit) {
		Group bright = new Group(mform.getForm().getBody(), SWT.NONE);
		bright.setText(Messages.PageFormatDialog_2);
		bright.setBackground(mform.getForm().getBody().getBackground());
		bright.setLayoutData(new GridData(GridData.FILL_BOTH));
		bright.setLayout(new GridLayout(3, false));

		toolkit.createLabel(bright, Messages.PageFormatDialog_3);
		cols = new Spinner(bright, SWT.BORDER);
		cols.setValues(1, 1, Integer.MAX_VALUE, 0, 1, 10);
		cols.setToolTipText(Messages.PageFormatDialog_4);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		cols.setLayoutData(gd);

		cwidth = new ValueUnitsWidget();
		cwidth.createComponent(bright, Messages.PageFormatDialog_5, Messages.PageFormatDialog_6);

		space = new ValueUnitsWidget();
		space.createComponent(bright, Messages.PageFormatDialog_7, Messages.PageFormatDialog_8);

		uvWidgets.add(cwidth);
		uvWidgets.add(space);

		SelectionListener listener = new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				setTBounds();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		};
		cols.addSelectionListener(listener);
		cwidth.addSelectionListener(listener);
		space.addSelectionListener(listener);
	}

	private void createMargins(IManagedForm mform, FormToolkit toolkit) {
		Group bleft = new Group(mform.getForm().getBody(), SWT.NONE);
		bleft.setText(Messages.PageFormatDialog_9);
		bleft.setBackground(mform.getForm().getBody().getBackground());
		bleft.setLayoutData(new GridData(GridData.FILL_BOTH));
		bleft.setLayout(new GridLayout(3, false));

		tmargin = new ValueUnitsWidget();
		tmargin.createComponent(bleft, Messages.PageFormatDialog_10, Messages.PageFormatDialog_11);

		bmargin = new ValueUnitsWidget();
		bmargin.createComponent(bleft, Messages.PageFormatDialog_12, Messages.PageFormatDialog_13);

		lmargin = new ValueUnitsWidget();
		lmargin.createComponent(bleft, Messages.PageFormatDialog_14, Messages.PageFormatDialog_15);

		rmargin = new ValueUnitsWidget();
		rmargin.createComponent(bleft, Messages.PageFormatDialog_16, Messages.PageFormatDialog_17);

		uvWidgets.add(tmargin);
		uvWidgets.add(bmargin);
		uvWidgets.add(lmargin);
		uvWidgets.add(rmargin);

		SelectionListener mlistner = new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				setTBounds();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		};
		tmargin.addSelectionListener(mlistner);
		bmargin.addSelectionListener(mlistner);
		lmargin.addSelectionListener(mlistner);
		rmargin.addSelectionListener(mlistner);
	}

	private void createOrientation(IManagedForm mform, FormToolkit toolkit) {
		Group mleft = new Group(mform.getForm().getBody(), SWT.NONE);
		mleft.setText(Messages.PageFormatDialog_18);
		mleft.setBackground(mform.getForm().getBody().getBackground());
		mleft.setLayoutData(new GridData(GridData.FILL_BOTH));
		mleft.setLayout(new GridLayout(2, false));
		portrait = toolkit.createButton(mleft, Messages.PageFormatDialog_19, SWT.RADIO);
		landscape = toolkit.createButton(mleft, Messages.PageFormatDialog_20, SWT.RADIO);

		portrait.setSelection(true);

		SelectionListener orientationlistner = new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				// change width with height
				int w = pwidth.getValue();
				int h = pheigh.getValue();
				pwidth.setValue(h);
				pheigh.setValue(w);
				setTBounds();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		};
		portrait.addSelectionListener(orientationlistner);
	}

	private void createThumbnail(IManagedForm mform) {
		square = new Canvas(mform.getForm().getBody(), SWT.NO_REDRAW_RESIZE | SWT.NO_BACKGROUND);
		square.setBackground(mform.getForm().getBody().getBackground());
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan = 2;
		square.setLayoutData(gd);

		lws = new J2DLightweightSystem();
		lws.setControl(square);
		parent = new Figure();
		parent.setLayoutManager(new XYLayout());
		lws.setContents(parent);

		borderPreview = new RectangleFigure() {

			@Override
			public void paint(Graphics graphics) {

				Dimension psize = parent.getSize();
				float zoom = Math.max((float) pwidth.getValue() / (float) (psize.width + 10), (float) pheigh.getValue()
						/ (float) (psize.height + 10));

				int x = getBounds().x + 10 + Math.round(lmargin.getValue() / zoom);
				int y = getBounds().y + 10 + Math.round(tmargin.getValue() / zoom);
				int w = getBounds().width - 20 - Math.round((rmargin.getValue()) / zoom)
						- Math.round(lmargin.getValue() / zoom);
				int h = getBounds().height - 20 - Math.round((bmargin.getValue()) / zoom)
						- Math.round(tmargin.getValue() / zoom);
				graphics.setForegroundColor(ColorConstants.blue);
				graphics.setBackgroundColor(ColorConstants.blue);
				graphics.setLineWidthFloat(0.1f);
				graphics.drawRectangle(x, y, w, h);

				int xl = x;
				for (int i = 1; i < cols.getSelection(); i++) {
					xl += Math.round(cwidth.getValue() / zoom);
					graphics.drawLine(xl, y, xl, y + h);
					xl += Math.round(space.getValue() / zoom);
					graphics.drawLine(xl, y, xl, y + h);
				}
				paintBorder(graphics);
			}
		};
		borderPreview.setBorder(new ShadowBorder());
		parent.add(borderPreview);

		Display.getCurrent().asyncExec(new Runnable() {

			public void run() {
				setTBounds();
			}
		});

	}

	private void setTBounds() {
		Dimension psize = parent.getSize();

		float zoom = Math.max((float) pwidth.getValue() / (float) (psize.width + 10), (float) pheigh.getValue()
				/ (float) (psize.height + 10));

		int w = Math.max(22, Math.round(pwidth.getValue() / zoom));
		int h = Math.max(22, Math.round(pheigh.getValue() / zoom));
		borderPreview.setSize(w, h);
		int x = psize.width / 2 - w / 2;
		int y = psize.height / 2 - h / 2;

		borderPreview.setLocation(new org.eclipse.draw2d.geometry.Point(x, y));
		parent.invalidate();
		square.redraw();
		lws.getUpdateManager().performUpdate();
	}

	private void createPageSize(IManagedForm mform, FormToolkit toolkit) {
		Composite tleft = new Composite(mform.getForm().getBody(), SWT.NONE);
		tleft.setLayout(new GridLayout(3, false));
		tleft.setBackground(mform.getForm().getBody().getBackground());
		tleft.setLayoutData(new GridData(GridData.FILL_BOTH));

		toolkit.createLabel(tleft, Messages.PageFormatDialog_21);
		pformat = new CCombo(tleft, SWT.BORDER | SWT.SINGLE | SWT.READ_ONLY);
		pformat.setItems(PageSize.getFormats());
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		pformat.setLayoutData(gd);

		pwidth = new ValueUnitsWidget();
		pwidth.createComponent(tleft, Messages.PageFormatDialog_22, Messages.PageFormatDialog_23);

		pheigh = new ValueUnitsWidget();
		pheigh.createComponent(tleft, Messages.PageFormatDialog_24, Messages.PageFormatDialog_25);

		uvWidgets.add(pwidth);
		uvWidgets.add(pheigh);

		uw = new UnitsWidget();
		uw.createComponent(tleft, Messages.PageFormatDialog_26, Messages.PageFormatDialog_27, 2);
		uw.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				setAllUnits();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		SelectionListener psizeListener = new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				String format = PageSize.deductPageFormat(pwidth.getValue(), pheigh.getValue());
				pformat.select(PageSize.getFormatIndx(format));
				setTBounds();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		};
		pwidth.addSelectionListener(psizeListener);
		pheigh.addSelectionListener(psizeListener);

		pformat.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				Point p = PageSize.getFormatSize(PageSize.getFormats()[pformat.getSelectionIndex()]);
				pwidth.setValue(p.x);
				pheigh.setValue(p.y);
				setTBounds();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}

	private void setAllUnits() {
		for (ValueUnitsWidget vuw : uvWidgets)
			vuw.setUnit(uw.getUnit());
	}

	private List<ValueUnitsWidget> uvWidgets = new ArrayList<ValueUnitsWidget>();
	private ValueUnitsWidget pheigh;
	private ValueUnitsWidget pwidth;
	private ValueUnitsWidget tmargin;
	private ValueUnitsWidget bmargin;
	private ValueUnitsWidget lmargin;
	private ValueUnitsWidget rmargin;
	private ValueUnitsWidget cwidth;
	private ValueUnitsWidget space;
	private Button portrait;
	private Button landscape;
	private Spinner cols;
	private CCombo pformat;
	private CompoundCommand command;
	private Figure parent;
	private RectangleFigure borderPreview;
	private Canvas square;
	private LightweightSystem lws;
	private UnitsWidget uw;

	private void setJasperDesign(JasperDesign jd) {
		pheigh.setValue(jd.getPageHeight());
		pwidth.setValue(jd.getPageWidth());
		String format = PageSize.deductPageFormat(jd.getPageWidth(), jd.getPageHeight());
		pformat.select(PageSize.getFormatIndx(format));

		tmargin.setValue(jd.getTopMargin());
		bmargin.setValue(jd.getBottomMargin());
		lmargin.setValue(jd.getLeftMargin());
		rmargin.setValue(jd.getRightMargin());

		cwidth.setValue(jd.getColumnWidth());
		space.setValue(jd.getColumnSpacing());
		cols.setSelection(jd.getColumnCount());

		if (jd.getOrientationValue().equals(OrientationEnum.LANDSCAPE))
			landscape.setSelection(true);
		else if (jd.getOrientationValue().equals(OrientationEnum.PORTRAIT))
			portrait.setSelection(true);
	}

	@Override
	public boolean close() {
		createCommand();
		return super.close();
	}

	public CompoundCommand getCommand() {
		return command;
	}

	public void createCommand() {
		command = new CompoundCommand();
		if (jd.getPageHeight() != pheigh.getValue())
			command.add(createCommand(JasperDesign.PROPERTY_PAGE_HEIGHT, pheigh.getValue()));
		if (jd.getPageWidth() != pwidth.getValue())
			command.add(createCommand(JasperDesign.PROPERTY_PAGE_WIDTH, pwidth.getValue()));

		if (jd.getTopMargin() != tmargin.getValue())
			command.add(createCommand(JasperDesign.PROPERTY_TOP_MARGIN, tmargin.getValue()));
		if (jd.getBottomMargin() != bmargin.getValue())
			command.add(createCommand(JasperDesign.PROPERTY_BOTTOM_MARGIN, bmargin.getValue()));
		if (jd.getLeftMargin() != lmargin.getValue())
			command.add(createCommand(JasperDesign.PROPERTY_LEFT_MARGIN, lmargin.getValue()));
		if (jd.getRightMargin() != rmargin.getValue())
			command.add(createCommand(JasperDesign.PROPERTY_RIGHT_MARGIN, rmargin.getValue()));

		if (jd.getColumnCount() != cols.getSelection())
			command.add(createCommand(JasperDesign.PROPERTY_COLUMN_COUNT, cols.getSelection()));
		if (jd.getColumnWidth() != cwidth.getValue())
			command.add(createCommand(JasperDesign.PROPERTY_COLUMN_WIDTH, cwidth.getValue()));
		if (jd.getColumnSpacing() != space.getValue())
			command.add(createCommand(JasperDesign.PROPERTY_COLUMN_SPACING, space.getValue()));

		if (jd.getOrientationValue().equals(OrientationEnum.LANDSCAPE) && !landscape.getSelection())
			command.add(createCommand(JasperDesign.PROPERTY_ORIENTATION, OrientationEnum.PORTRAIT));
		else if (jd.getOrientationValue().equals(OrientationEnum.PORTRAIT) && !portrait.getSelection())
			command.add(createCommand(JasperDesign.PROPERTY_ORIENTATION, OrientationEnum.LANDSCAPE));
	}

	private Command createCommand(String property, Object value) {
		SetValueCommand cmd = new SetValueCommand();
		cmd.setTarget(jnode);
		cmd.setPropertyId(property);
		cmd.setPropertyValue(value);
		return cmd;
	}
}