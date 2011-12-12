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

import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.EnumHelper;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class AlignSection extends AbstractSection {
	private Composite composite;

	private ToolItem alignRightButton;
	private ToolItem alignCenterButton;
	private ToolItem alignJustifiedButton;
	private ToolItem alignLeftButton;
	private ToolItem alignTopButton;
	private ToolItem alignBottomButton;
	private ToolItem alignMiddleButton;

	// private ToolItem alignVJustifiedButton;

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent = new Composite(parent, SWT.NONE);
		parent.setLayout(new RowLayout(SWT.VERTICAL));
		parent.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));

		composite = createNewRow(parent);

		CLabel label = getWidgetFactory().createCLabel(composite, Messages.AlignSection_common_align + ":", SWT.RIGHT); //$NON-NLS-2$
		RowData rd = new RowData();
		rd.width = 100;
		label.setLayoutData(rd);

		ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.WRAP | SWT.LEFT);
		toolBar.setBackground(composite.getBackground());

		alignLeftButton = new ToolItem(toolBar, SWT.CHECK);
		alignLeftButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Object value = alignLeftButton.getSelection() ? HorizontalAlignEnum.LEFT : null;
				changeProperty(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT, value);

			}
		});
		alignLeftButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/left_align.gif")); //$NON-NLS-1$
		alignLeftButton.setToolTipText(Messages.TextSection_align_left_tool_tip);

		alignCenterButton = new ToolItem(toolBar, SWT.CHECK);
		alignCenterButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Object value = alignCenterButton.getSelection() ? HorizontalAlignEnum.CENTER : null;
				changeProperty(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT, value);
			}
		});
		alignCenterButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/center_align.gif")); //$NON-NLS-1$
		alignCenterButton.setToolTipText(Messages.TextSection_align_center_tool_tip);

		alignJustifiedButton = new ToolItem(toolBar, SWT.CHECK);
		alignJustifiedButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Object value = alignJustifiedButton.getSelection() ? HorizontalAlignEnum.JUSTIFIED : null;
				changeProperty(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT, value);
			}
		});
		alignJustifiedButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/justified_align.gif")); //$NON-NLS-1$
		alignJustifiedButton.setToolTipText(Messages.TextSection_align_justified_tool_tip);

		alignRightButton = new ToolItem(toolBar, SWT.CHECK);
		alignRightButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Object value = alignRightButton.getSelection() ? HorizontalAlignEnum.RIGHT : null;
				changeProperty(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT, value);
			}
		});
		alignRightButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/right_align.gif")); //$NON-NLS-1$
		alignRightButton.setToolTipText(Messages.TextSection_align_right_tool_tip);

		new ToolItem(toolBar, SWT.SEPARATOR);

		alignTopButton = new ToolItem(toolBar, SWT.CHECK);
		alignTopButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Object value = alignTopButton.getSelection() ? VerticalAlignEnum.TOP : null;
				changeProperty(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT, value);
			}
		});
		alignTopButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/formatting/edit-vertical-alignment-top.png")); //$NON-NLS-1$
		alignTopButton.setToolTipText(Messages.TextSection_align_top_tool_tip);

		alignMiddleButton = new ToolItem(toolBar, SWT.CHECK);
		alignMiddleButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Object value = alignMiddleButton.getSelection() ? VerticalAlignEnum.MIDDLE : null;
				changeProperty(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT, value);
			}
		});
		alignMiddleButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/formatting/edit-vertical-alignment-middle.png")); //$NON-NLS-1$
		alignMiddleButton.setToolTipText(Messages.TextSection_align_middle_tool_tip);

		// alignVJustifiedButton = new ToolItem(toolBar, SWT.CHECK);
		// alignVJustifiedButton.addSelectionListener(new SelectionAdapter() {
		// public void widgetSelected(SelectionEvent e) {
		// Object value = alignVJustifiedButton.getSelection() ? VerticalAlignEnum.JUSTIFIED : null;
		// changeProperty(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT, value);
		// }
		// });
		//		alignVJustifiedButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/align-text-middle.gif")); //$NON-NLS-1$
		// alignVJustifiedButton.setToolTipText(Messages.TextSection_align_vertical_justified_tool_tip);

		alignBottomButton = new ToolItem(toolBar, SWT.CHECK);
		alignBottomButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Object value = alignBottomButton.getSelection() ? VerticalAlignEnum.BOTTOM : null;
				changeProperty(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT, value);
			}
		});
		alignBottomButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/formatting/edit-vertical-alignment.png")); //$NON-NLS-1$
		alignBottomButton.setToolTipText(Messages.TextSection_align_bottom_tool_tip);

	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
	 */
	public void refresh() {
		isRefreshing = true;
		APropertyNode element = getElement();
		if (element != null) {
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
				// alignVJustifiedButton.setSelection(VerticalAlignEnum.JUSTIFIED.equals(val));
				alignBottomButton.setSelection(VerticalAlignEnum.BOTTOM.equals(val));
			} else {
				alignTopButton.setSelection(false);
				alignMiddleButton.setSelection(false);
				// alignVJustifiedButton.setSelection(false);
				alignBottomButton.setSelection(false);
			}
		}
		isRefreshing = false;
	}

	@Override
	public boolean isDisposed() {
		return composite.isDisposed();
	}
}
