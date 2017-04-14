/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.report;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.FormDialog;
import org.eclipse.ui.forms.IManagedForm;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetWidgetFactory;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;
import com.jaspersoft.studio.property.section.report.util.PHolderUtil;
import com.jaspersoft.studio.property.section.report.util.PageSize;
import com.jaspersoft.studio.property.section.report.util.UnitsWidget;
import com.jaspersoft.studio.property.section.report.util.ValueUnitsWidget;
import com.jaspersoft.studio.swt.widgets.NullableSpinner;
import com.jaspersoft.studio.swt.widgets.NumericText;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.ContextHelpIDs;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.engine.type.PrintOrderEnum;

public final class PageFormatDialog extends FormDialog {

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

	private NullableSpinner cols;

	private Combo pformat;

	private JSSCompoundCommand command;

	private PageFormatWidget pageFormatWidget;

	private UnitsWidget uw;

	private TabbedPropertySheetWidgetFactory toolkit;

	private JasperDesign jd;

	private JasperReportsConfiguration jConfig;

	private MReport jnode;

	private Combo cPrintOrder;

	public PageFormatDialog(Shell shell, ANode node) {
		super(shell);
		jConfig = node.getJasperConfiguration();
		jnode = (MReport) node.getRoot();
		this.jd = node.getJasperDesign();
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(Messages.PageFormatDialog_0);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(newShell, ContextHelpIDs.WIZARD_FORMAT_PAGE);
	}

	@Override
	protected void createFormContent(IManagedForm mform) {
		mform.getForm().setText(Messages.PageFormatDialog_1);

		toolkit = new TabbedPropertySheetWidgetFactory();

		Composite composite = mform.getForm().getBody();
		composite.setLayout(new GridLayout(2, false));
		composite.setBackgroundMode(SWT.INHERIT_FORCE);

		createPageSize(composite);

		createThumbnail(composite);

		createOrientation(composite);

		createMargins(composite);

		createColumns(composite);

		setJasperDesign(jd);

		setTBounds();
	}

	private void createColumns(Composite composite) {
		Group bright = toolkit.createGroup(composite, Messages.PageFormatDialog_2);
		bright.setLayoutData(new GridData(GridData.FILL_BOTH));
		bright.setBackgroundMode(SWT.INHERIT_FORCE);
		bright.setLayout(new GridLayout(3, false));

		new Label(bright, SWT.NONE).setText(Messages.PageFormatDialog_3);

		cols = new NullableSpinner(bright, SWT.BORDER, 0, 0);
		cols.setNullable(false);
		cols.setMinimum(1);
		cols.setMaximum(Integer.MAX_VALUE);
		cols.setValue(1);
		cols.setToolTipText(Messages.PageFormatDialog_4);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		cols.setLayoutData(gd);

		cwidth = new ValueUnitsWidget(jConfig);
		cwidth.createComponent(bright, Messages.PageFormatDialog_5, Messages.PageFormatDialog_6);

		space = new ValueUnitsWidget(jConfig);
		space.createComponent(bright, Messages.PageFormatDialog_7, Messages.PageFormatDialog_8);

		uvWidgets.add(cwidth);
		uvWidgets.add(space);

		SelectionListener spaceListener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cols.setMaximum(getMaxColumnsNumber(false));
				NumericText textControl = (NumericText) e.widget;
				Point currentSelection = textControl.getSelection();
				recalcColumns();
				setTBounds();
				textControl.setFocus();
				textControl.setSelection(currentSelection.x, currentSelection.y);
			}
		};
		SelectionListener colsListener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NumericText textControl = (NumericText) e.widget;
				Point currentSelection = textControl.getSelection();
				recalcColumns();
				setTBounds();
				textControl.setFocus();
				textControl.setSelection(currentSelection.x, currentSelection.y);
				enablePrintOrder();
			}
		};

		cols.addSelectionListener(colsListener);
		cwidth.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				cols.setMaximum(getMaxColumnsNumber(true));

				setTBounds();
			}
		});
		space.addSelectionListener(spaceListener);

		new Label(bright, SWT.NONE).setText(Messages.MReport_print_order);

		cPrintOrder = new Combo(bright, SWT.READ_ONLY | SWT.BORDER);
		String[] items = NamedEnumPropertyDescriptor
				.getEnumItems(PrintOrderEnum.HORIZONTAL.getDeclaringClass().getEnumConstants(), NullEnum.NULL);
		cPrintOrder.setItems(items);
		gd = new GridData();
		gd.horizontalSpan = 2;
		cPrintOrder.setLayoutData(gd);
		
		enablePrintOrder();
	}

	private void recalcColumns() {
		int pagespace = pwidth.getValue() - lmargin.getValue() - rmargin.getValue();
		int nrcolspace = cols.getValueAsInteger() - 1;
		int colspace = nrcolspace * space.getValue();
		int mspace = Math.max(0, nrcolspace > 0 ? colspace / nrcolspace : pagespace);
		int maxspace = Math.max(0, nrcolspace > 0 ? pagespace / nrcolspace : pagespace);
		if (mspace > maxspace)
			mspace = maxspace;

		if (mspace < space.getValue())
			space.setValue(mspace);
		space.setMaxPixels(maxspace);

		int cw = getMaxColumnsWidth();
		cwidth.setValue(cw);

		tmargin.setMaxPixels(pheigh.getValue() - bmargin.getValue());
		bmargin.setMaxPixels(pheigh.getValue() - tmargin.getValue());
		lmargin.setMaxPixels(pwidth.getValue() - rmargin.getValue());
		rmargin.setMaxPixels(pwidth.getValue() - lmargin.getValue());
	}

	private void createMargins(Composite composite) {
		Group bleft = toolkit.createGroup(composite, Messages.PageFormatDialog_9);
		bleft.setLayoutData(new GridData(GridData.FILL_BOTH));
		bleft.setLayout(new GridLayout(3, false));
		bleft.setBackgroundMode(SWT.INHERIT_FORCE);

		tmargin = new ValueUnitsWidget(jConfig);
		tmargin.createComponent(bleft, Messages.PageFormatDialog_10, Messages.PageFormatDialog_11);

		bmargin = new ValueUnitsWidget(jConfig);
		bmargin.createComponent(bleft, Messages.PageFormatDialog_12, Messages.PageFormatDialog_13);

		lmargin = new ValueUnitsWidget(jConfig);
		lmargin.createComponent(bleft, Messages.PageFormatDialog_14, Messages.PageFormatDialog_15);

		rmargin = new ValueUnitsWidget(jConfig);
		rmargin.createComponent(bleft, Messages.PageFormatDialog_16, Messages.PageFormatDialog_17);

		uvWidgets.add(tmargin);
		uvWidgets.add(bmargin);
		uvWidgets.add(lmargin);
		uvWidgets.add(rmargin);

		SelectionListener mlistner = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setWdithMaximum();
				cols.setMaximum(getMaxColumnsNumber(false));
				NumericText textControl = (NumericText) e.widget;
				Point currentSelection = textControl.getSelection();
				recalcColumns();
				setTBounds();
				textControl.setFocus();
				textControl.setSelection(currentSelection.x, currentSelection.y);
			}
		};
		tmargin.addSelectionListener(mlistner);
		bmargin.addSelectionListener(mlistner);
		lmargin.addSelectionListener(mlistner);
		rmargin.addSelectionListener(mlistner);
	}

	private void createOrientation(Composite composite) {
		Group mleft = toolkit.createGroup(composite, Messages.PageFormatDialog_18);
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
				if ((w > h && portrait.getSelection()) || (h > w && !portrait.getSelection())) {
					pwidth.setValue(h);
					pheigh.setValue(w);
					setTBounds();
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		};
		portrait.addSelectionListener(orientationlistner);
	}

	private void createThumbnail(Composite composite) {
		pageFormatWidget = new PageFormatWidget(composite, SWT.NONE);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan = 2;
		pageFormatWidget.setLayoutData(gd);
	}

	private void setTBounds() {
		pageFormatWidget.setCols(cols.getValueAsInteger());

		pageFormatWidget.setTmargin(tmargin.getValue());
		pageFormatWidget.setBmargin(bmargin.getValue());
		pageFormatWidget.setLmargin(lmargin.getValue());
		pageFormatWidget.setRmargin(rmargin.getValue());

		pageFormatWidget.setPheight(pheigh.getValue());
		pageFormatWidget.setPwidth(pwidth.getValue());

		pageFormatWidget.setSpace(space.getValue());
		pageFormatWidget.setCwidth(cwidth.getValue());

		pageFormatWidget.setTBounds();
	}

	private void createPageSize(Composite composite) {
		Composite tleft = toolkit.createComposite(composite);
		tleft.setLayout(new GridLayout(3, false));
		tleft.setLayoutData(new GridData(GridData.FILL_BOTH));

		toolkit.createLabel(tleft, Messages.PageFormatDialog_21);
		pformat = toolkit.createCombo(tleft, SWT.BORDER | SWT.SINGLE | SWT.READ_ONLY);
		pformat.setItems(PageSize.getFormats());
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		pformat.setLayoutData(gd);

		pwidth = new ValueUnitsWidget(jConfig);
		pwidth.createComponent(tleft, Messages.PageFormatDialog_22, Messages.PageFormatDialog_23);
		pwidth.setMaxPixels(5000);

		pheigh = new ValueUnitsWidget(jConfig);
		pheigh.createComponent(tleft, Messages.PageFormatDialog_24, Messages.PageFormatDialog_25);
		pheigh.setMaxPixels(5000);

		uvWidgets.add(pwidth);
		uvWidgets.add(pheigh);

		uw = new UnitsWidget();
		uw.createComponent(tleft, Messages.PageFormatDialog_26, Messages.PageFormatDialog_27, 2);
		uw.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				recalcColumns();
				setAllUnits();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		SelectionListener psizeMListener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setWdithMaximum();
				cols.setMaximum(getMaxColumnsNumber(false));
				NumericText textControl = (NumericText) e.widget;
				Point currentSelection = textControl.getSelection();
				String format = PageSize.deductPageFormat(pwidth.getValue(), pheigh.getValue());
				pformat.select(PageSize.getFormatIndx(format));
				recalcColumns();
				setTBounds();
				textControl.setFocus();
				textControl.setSelection(currentSelection.x, currentSelection.y);
			}

		};
		pwidth.addSelectionListener(psizeMListener);
		pheigh.addSelectionListener(psizeMListener);

		pformat.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				Point p = PageSize.getFormatSize(PageSize.getFormats()[pformat.getSelectionIndex()]);
				// Standard measures are for portrait: should switch if landascape
				if (portrait.getSelection()) {
					pwidth.setValue(p.x);
					pheigh.setValue(p.y);
				} else {
					pwidth.setValue(p.y);
					pheigh.setValue(p.x);
				}
				recalcColumns();
				setTBounds();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}

	private void setAllUnits() {
		String unit = uw.getUnit();
		for (ValueUnitsWidget vuw : uvWidgets)
			vuw.setUnit(unit);
	}

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
		setWdithMaximum();

		space.setValue(jd.getColumnSpacing());

		cols.setValue(jd.getColumnCount());
		cols.setMaximum(getMaxColumnsNumber(false));

		landscape.setSelection(false);
		portrait.setSelection(false);
		if (jd.getOrientationValue().equals(OrientationEnum.LANDSCAPE))
			landscape.setSelection(true);
		else if (jd.getOrientationValue().equals(OrientationEnum.PORTRAIT))
			portrait.setSelection(true);

		String defunit = MReport.getMeasureUnit(jConfig, jd);
		uw.setUnit(defunit);

		pheigh.setUnit(PHolderUtil.getUnit(jd, JasperDesign.PROPERTY_PAGE_HEIGHT, defunit));
		pwidth.setUnit(PHolderUtil.getUnit(jd, JasperDesign.PROPERTY_PAGE_WIDTH, defunit));

		tmargin.setUnit(PHolderUtil.getUnit(jd, JasperDesign.PROPERTY_TOP_MARGIN, defunit));
		bmargin.setUnit(PHolderUtil.getUnit(jd, JasperDesign.PROPERTY_BOTTOM_MARGIN, defunit));
		lmargin.setUnit(PHolderUtil.getUnit(jd, JasperDesign.PROPERTY_LEFT_MARGIN, defunit));
		rmargin.setUnit(PHolderUtil.getUnit(jd, JasperDesign.PROPERTY_RIGHT_MARGIN, defunit));

		cwidth.setUnit(PHolderUtil.getUnit(jd, JasperDesign.PROPERTY_COLUMN_WIDTH, defunit));
		space.setUnit(PHolderUtil.getUnit(jd, JasperDesign.PROPERTY_COLUMN_SPACING, defunit));
	}

	/**
	 * Set the maximum number that can be set on the column width control. Used when the page width or its margin are
	 * changed
	 */
	protected void setWdithMaximum() {
		cwidth.setMaxPixels(Math.max(0, pwidth.getValue() - lmargin.getValue() - rmargin.getValue()));
	}

	@Override
	public boolean close() {
		createCommand();
		return super.close();
	}

	public JSSCompoundCommand getCommand() {
		return command;
	}

	/**
	 * Return the maximum number of columns that can be set.
	 * 
	 * @param realColWidth
	 *          true if this is called when setting the column widht. Having this to true make the columns number
	 *          depending on the current column widht. Otherwise will be the column width depending on the columns number
	 *          (forcing the width to its minimum, 1)
	 * @return the maximum number of columns
	 */
	protected int getMaxColumnsNumber(boolean realColWidth) {
		int colWidth = realColWidth ? cwidth.getValue() : 1;
		float value = (pwidth.getValue() - lmargin.getValue() - rmargin.getValue())
				/ Math.max((colWidth + space.getValue()), 1);
		return (int) Math.floor(value);
	}

	/**
	 * Return the maximum column width that can be set with the current values
	 * 
	 * @return the maximum columns witdh for the current space, page width and margins
	 */
	protected int getMaxColumnsWidth() {
		int colNumber = Math.max(cols.getValueAsInteger(), 1);
		float value = (pwidth.getValue() - lmargin.getValue() - rmargin.getValue() - space.getValue() * (colNumber - 1))
				/ colNumber;
		return (int) Math.floor(value);
	}

	public void createCommand() {
		command = new JSSCompoundCommand(jnode);
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

		if (jd.getColumnCount() != cols.getValueAsInteger())
			command.add(createCommand(JasperDesign.PROPERTY_COLUMN_COUNT, cols.getValueAsInteger()));
		if (jd.getColumnWidth() != cwidth.getValue())
			command.add(createCommand(JasperDesign.PROPERTY_COLUMN_WIDTH, cwidth.getValue()));
		if (jd.getColumnSpacing() != space.getValue())
			command.add(createCommand(JasperDesign.PROPERTY_COLUMN_SPACING, space.getValue()));
		if (jd.getPrintOrderValue() != PrintOrderEnum.getByName(cPrintOrder.getText()))
			command.add(createCommand(JasperDesign.PROPERTY_PRINT_ORDER, NamedEnumPropertyDescriptor
					.getIntValue(PrintOrderEnum.HORIZONTAL, NullEnum.NULL, PrintOrderEnum.getByName(cPrintOrder.getText()))));

		if (jd.getOrientationValue().equals(OrientationEnum.LANDSCAPE) && !landscape.getSelection())
			command.add(createCommand(JasperDesign.PROPERTY_ORIENTATION, OrientationEnum.PORTRAIT));
		else if (jd.getOrientationValue().equals(OrientationEnum.PORTRAIT) && !portrait.getSelection())
			command.add(createCommand(JasperDesign.PROPERTY_ORIENTATION, OrientationEnum.LANDSCAPE));

		boolean changes = false;
		JRPropertiesMap pmap = jd.getPropertiesMap().cloneProperties();
		String defunit = uw.getUnit();
		changes = PHolderUtil.setProperty(changes, pmap, "", defunit, null);

		changes = PHolderUtil.setProperty(changes, pmap, JasperDesign.PROPERTY_PAGE_HEIGHT, pheigh.getUnit(), defunit);
		changes = PHolderUtil.setProperty(changes, pmap, JasperDesign.PROPERTY_PAGE_WIDTH, pwidth.getUnit(), defunit);

		changes = PHolderUtil.setProperty(changes, pmap, JasperDesign.PROPERTY_TOP_MARGIN, tmargin.getUnit(), defunit);
		changes = PHolderUtil.setProperty(changes, pmap, JasperDesign.PROPERTY_BOTTOM_MARGIN, bmargin.getUnit(), defunit);
		changes = PHolderUtil.setProperty(changes, pmap, JasperDesign.PROPERTY_LEFT_MARGIN, lmargin.getUnit(), defunit);
		changes = PHolderUtil.setProperty(changes, pmap, JasperDesign.PROPERTY_RIGHT_MARGIN, rmargin.getUnit(), defunit);

		changes = PHolderUtil.setProperty(changes, pmap, JasperDesign.PROPERTY_COLUMN_WIDTH, cwidth.getUnit(), defunit);
		changes = PHolderUtil.setProperty(changes, pmap, JasperDesign.PROPERTY_COLUMN_SPACING, space.getUnit(), defunit);
		if (changes)
			command.add(createCommand(MGraphicElement.PROPERTY_MAP, pmap));
	}

	private Command createCommand(String property, Object value) {
		SetValueCommand cmd = new SetValueCommand();
		cmd.setTarget(jnode);
		cmd.setPropertyId(property);
		cmd.setPropertyValue(value);
		return cmd;
	}

	protected void enablePrintOrder() {
		int c = cols.getValueAsInteger();
		cPrintOrder.setEnabled(c > 1);
		if (c <= 1)
			cPrintOrder.select(0);
	}
}
