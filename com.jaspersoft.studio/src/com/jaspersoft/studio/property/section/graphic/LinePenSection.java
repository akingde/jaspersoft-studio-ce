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

import net.sf.jasperreports.engine.base.JRBasePen;
import net.sf.jasperreports.engine.type.LineStyleEnum;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.editor.report.EditorContributor;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElementLinePen;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.color.ColorLabelProvider;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.EnumHelper;

/**
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class LinePenSection extends AbstractSection {

	private Composite composite;
	private Button lineColor;
	private CCombo lineStyle;
	private Spinner lineWidth;

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		Assert.isTrue(selection instanceof IStructuredSelection);
		Object input = ((IStructuredSelection) selection).getFirstElement();
		Assert.isTrue(input instanceof EditPart);
		Object model = ((EditPart) input).getModel();
		Assert.isTrue(model instanceof MGraphicElementLinePen);
		model = ((MGraphicElementLinePen) model).getPropertyValue(MGraphicElementLinePen.LINE_PEN);

		EditorContributor provider = (EditorContributor) part.getAdapter(EditorContributor.class);
		if (provider != null)
			setEditDomain(provider.getEditDomain());
		if (getElement() != model) {
			if (getElement() != null)
				getElement().getPropertyChangeSupport().removePropertyChangeListener(this);
			setElement((APropertyNode) model);
			getElement().getPropertyChangeSupport().addPropertyChangeListener(this);
		}
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		composite = new Composite(parent, SWT.NONE);
		composite.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		GridLayout layout = new GridLayout(6, false);
		composite.setLayout(layout);

		CLabel label = getWidgetFactory().createCLabel(composite, "Color:", SWT.RIGHT);
		GridData gd = new GridData();
		gd.widthHint = 100;
		label.setLayoutData(gd);

		lineColor = new Button(composite, SWT.FLAT);
		lineColor.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ColorDialog cd = new ColorDialog(composite.getShell());
				cd.setText("Line color");
				cd.setRGB((RGB) getElement().getPropertyValue(JRBasePen.PROPERTY_LINE_COLOR));
				RGB newColor = cd.open();
				changeProperty(JRBasePen.PROPERTY_LINE_COLOR, newColor);
			}
		});
		gd = new GridData();
		gd.widthHint = 30;
		lineColor.setLayoutData(gd);

		getWidgetFactory().createCLabel(composite, "Style:");
		lineStyle = new CCombo(composite, SWT.BORDER | SWT.FLAT);
		lineStyle.setItems(EnumHelper.getEnumNames(LineStyleEnum.values(), NullEnum.INHERITED));
		lineStyle.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBasePen.PROPERTY_LINE_STYLE, new Integer(lineStyle.getSelectionIndex()));
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		lineStyle.setToolTipText("Line style");

		label = getWidgetFactory().createCLabel(composite, "Size:", SWT.RIGHT);

		lineWidth = new Spinner(composite, SWT.BORDER);
		lineWidth.setValues(0, 0, 100, 3, 1, 10);
		lineWidth.setToolTipText("width");
		lineWidth.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				changeProperty(JRBasePen.PROPERTY_LINE_WIDTH, new Float(lineWidth.getSelection() / Math.pow(10, 3)));
			}
		});
	}

	private ColorLabelProvider colorLabelProvider = new ColorLabelProvider(null);

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
	 */
	public void refresh() {
		isRefreshing = true;
		APropertyNode element = getElement();
		RGB backcolor = (RGB) element.getPropertyValue(JRBasePen.PROPERTY_LINE_COLOR);
		if (backcolor != null)
			lineColor.setImage(colorLabelProvider.getImage(backcolor));
		else
			lineColor.setImage(null);

		lineStyle.select(((Integer) element.getPropertyValue(JRBasePen.PROPERTY_LINE_STYLE)).intValue());
		Float propertyValue = (Float) element.getPropertyValue(JRBasePen.PROPERTY_LINE_WIDTH);
		if (propertyValue != null)
			lineWidth.setSelection((int) (propertyValue.doubleValue() * Math.pow(10, 3)));
		else
			lineWidth.setSelection(0);

		isRefreshing = false;
	}
}
