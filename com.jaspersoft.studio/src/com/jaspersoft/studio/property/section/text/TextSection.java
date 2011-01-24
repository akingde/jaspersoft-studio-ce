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
package com.jaspersoft.studio.property.section.text;

import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.LineSpacingEnum;
import net.sf.jasperreports.engine.type.RotationEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.EnumHelper;

/**
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class TextSection extends AbstractSection {

	private Button alignRightButton;
	private Button alignCenterButton;
	private Button alignJustifiedButton;
	private Button alignLeftButton;
	private Button alignTopButton;
	private Button alignBottomButton;
	private Button alignMiddleButton;
	private Button alignVJustifiedButton;
	private CCombo rotation;
	private CCombo lineSpace;

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		GridLayout layout = new GridLayout(50, false);
		composite.setLayout(layout);

		CLabel label = getWidgetFactory().createCLabel(composite, Messages.common_text + ":", SWT.RIGHT); //$NON-NLS-1$
		GridData gd = new GridData();
		gd.widthHint = 100;
		label.setLayoutData(gd);

		alignLeftButton = new Button(composite, SWT.FLAT | SWT.TOGGLE);
		alignLeftButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT, HorizontalAlignEnum.LEFT);
			}
		});
		alignLeftButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/left_align.gif")); //$NON-NLS-1$
		alignLeftButton.setToolTipText(Messages.TextSection_align_left_tool_tip);

		alignCenterButton = new Button(composite, SWT.FLAT | SWT.TOGGLE);
		alignCenterButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT, HorizontalAlignEnum.CENTER);
			}
		});
		alignCenterButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/center_align.gif")); //$NON-NLS-1$
		alignCenterButton.setToolTipText(Messages.TextSection_align_center_tool_tip);

		alignJustifiedButton = new Button(composite, SWT.FLAT | SWT.TOGGLE);
		alignJustifiedButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT, HorizontalAlignEnum.JUSTIFIED);
			}
		});
		alignJustifiedButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/justified_align.gif")); //$NON-NLS-1$
		alignJustifiedButton.setToolTipText(Messages.TextSection_align_justified_tool_tip);

		alignRightButton = new Button(composite, SWT.FLAT | SWT.TOGGLE);
		alignRightButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT, HorizontalAlignEnum.RIGHT);
			}
		});
		alignRightButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/right_align.gif")); //$NON-NLS-1$
		alignRightButton.setToolTipText(Messages.TextSection_align_right_tool_tip);

		getWidgetFactory().createCLabel(composite, "", SWT.RIGHT); //$NON-NLS-1$

		alignTopButton = new Button(composite, SWT.FLAT | SWT.TOGGLE);
		alignTopButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT, VerticalAlignEnum.TOP);
			}
		});
		alignTopButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/align-text-top.gif")); //$NON-NLS-1$
		alignTopButton.setToolTipText(Messages.TextSection_align_top_tool_tip);

		alignMiddleButton = new Button(composite, SWT.FLAT | SWT.TOGGLE);
		alignMiddleButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT, VerticalAlignEnum.MIDDLE);
			}
		});
		alignMiddleButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/align-text-middle.gif")); //$NON-NLS-1$
		alignMiddleButton.setToolTipText(Messages.TextSection_align_middle_tool_tip);

		alignVJustifiedButton = new Button(composite, SWT.FLAT | SWT.TOGGLE);
		alignVJustifiedButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT, VerticalAlignEnum.JUSTIFIED);
			}
		});
		alignVJustifiedButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/align-text-middle.gif")); //$NON-NLS-1$
		alignVJustifiedButton.setToolTipText(Messages.TextSection_align_vertical_justified_tool_tip);

		alignBottomButton = new Button(composite, SWT.FLAT | SWT.TOGGLE);
		alignBottomButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT, VerticalAlignEnum.BOTTOM);
			}
		});
		alignBottomButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/align-text-bottom.gif")); //$NON-NLS-1$
		alignBottomButton.setToolTipText(Messages.TextSection_align_bottom_tool_tip);

		rotation = new CCombo(composite, SWT.BORDER | SWT.FLAT | SWT.READ_ONLY);
		rotation.setItems(EnumHelper.getEnumNames(RotationEnum.values(), NullEnum.INHERITED));
		rotation.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseStyle.PROPERTY_ROTATION, new Integer(rotation.getSelectionIndex()));
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		rotation.setToolTipText(Messages.TextSection_rotation_tool_tip);

		lineSpace = new CCombo(composite, SWT.BORDER | SWT.FLAT | SWT.READ_ONLY);
		lineSpace.setItems(EnumHelper.getEnumNames(LineSpacingEnum.values(), NullEnum.INHERITED));
		lineSpace.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseStyle.PROPERTY_LINE_SPACING, new Integer(lineSpace.getSelectionIndex()));
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		lineSpace.setToolTipText(Messages.TextSection_line_spacing_tool_tip);
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
	 */
	public void refresh() {
		isRefreshing = true;
		APropertyNode element = getElement();
		if (element != null) {
			rotation.select(((Integer) element.getPropertyValue(JRBaseStyle.PROPERTY_ROTATION)).intValue());
			lineSpace.select(((Integer) element.getPropertyValue(JRBaseStyle.PROPERTY_LINE_SPACING)).intValue());

			Integer halign = (Integer) element.getPropertyValue(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT);
			if (halign != null) {
				HorizontalAlignEnum val = (HorizontalAlignEnum) EnumHelper.getSetValue(HorizontalAlignEnum.values(), halign, 1,
						true);
				alignLeftButton.setSelection(HorizontalAlignEnum.LEFT.equals(val));
				alignCenterButton.setSelection(HorizontalAlignEnum.CENTER.equals(val));
				alignJustifiedButton.setSelection(HorizontalAlignEnum.JUSTIFIED.equals(val));
				alignRightButton.setSelection(HorizontalAlignEnum.RIGHT.equals(val));
			} else {
				alignLeftButton.setSelection(false);
				alignCenterButton.setSelection(false);
				alignJustifiedButton.setSelection(false);
				alignRightButton.setSelection(false);
			}

			Integer valign = (Integer) element.getPropertyValue(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT);
			if (valign != null) {
				VerticalAlignEnum val = (VerticalAlignEnum) EnumHelper.getSetValue(VerticalAlignEnum.values(), valign, 1, true);
				alignTopButton.setSelection(VerticalAlignEnum.TOP.equals(val));
				alignMiddleButton.setSelection(VerticalAlignEnum.MIDDLE.equals(val));
				alignVJustifiedButton.setSelection(VerticalAlignEnum.JUSTIFIED.equals(val));
				alignBottomButton.setSelection(VerticalAlignEnum.BOTTOM.equals(val));
			} else {
				alignTopButton.setSelection(false);
				alignMiddleButton.setSelection(false);
				alignVJustifiedButton.setSelection(false);
				alignBottomButton.setSelection(false);
			}
		}
		isRefreshing = false;
	}
}
