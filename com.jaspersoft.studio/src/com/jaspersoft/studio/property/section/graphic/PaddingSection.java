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

import net.sf.jasperreports.engine.base.JRBaseLineBox;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.report.EditorContributor;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElementLineBox;
import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.property.section.AbstractSection;

/**
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class PaddingSection extends AbstractSection {
	private Spinner bottomPadding;
	private Spinner topPadding;
	private Spinner leftPadding;
	private Spinner rightPadding;
	private Spinner allPadding;

	@Override
	protected void setInputC(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Assert.isTrue(selection instanceof IStructuredSelection);
			Object input = ((IStructuredSelection) selection).getFirstElement();
			Assert.isTrue(input instanceof EditPart);
			Object model = ((EditPart) input).getModel();
			Assert.isTrue(model instanceof MGraphicElementLineBox || model instanceof MStyle);
			model = ((APropertyNode) model).getPropertyValue(MGraphicElementLineBox.LINE_BOX);

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
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		GridLayout layout = new GridLayout(14, false);
		composite.setLayout(layout);

		CLabel label = getWidgetFactory().createCLabel(composite, "Padding:", SWT.RIGHT);
		GridData gd = new GridData();
		gd.widthHint = 100;
		label.setLayoutData(gd);

		CLabel l = new CLabel(composite, SWT.RIGHT);
		l.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/border_frame.gif"));
		l.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));

		allPadding = new Spinner(composite, SWT.BORDER);
		allPadding.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);
		allPadding.setToolTipText("padding");
		allPadding.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseLineBox.PROPERTY_PADDING, new Integer(allPadding.getSelection()));
			}
		});

		l = new CLabel(composite, SWT.RIGHT);
		l.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/border_top.gif"));
		l.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));

		topPadding = new Spinner(composite, SWT.BORDER);
		topPadding.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);
		topPadding.setToolTipText("top padding");
		topPadding.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseLineBox.PROPERTY_TOP_PADDING, new Integer(topPadding.getSelection()));
			}
		});

		l = new CLabel(composite, SWT.RIGHT);
		l.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/border_bottom.png"));
		l.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));

		bottomPadding = new Spinner(composite, SWT.BORDER);
		bottomPadding.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);
		bottomPadding.setToolTipText("bottom padding");
		bottomPadding.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseLineBox.PROPERTY_BOTTOM_PADDING, new Integer(bottomPadding.getSelection()));
			}
		});

		l = new CLabel(composite, SWT.RIGHT);
		l.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/border_left.gif"));
		l.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));

		leftPadding = new Spinner(composite, SWT.BORDER);
		leftPadding.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);
		leftPadding.setToolTipText("left padding");
		leftPadding.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseLineBox.PROPERTY_LEFT_PADDING, new Integer(leftPadding.getSelection()));
			}
		});

		l = new CLabel(composite, SWT.RIGHT);
		l.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/border_right.gif"));
		l.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));

		rightPadding = new Spinner(composite, SWT.BORDER);
		rightPadding.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);
		rightPadding.setToolTipText("right padding");
		rightPadding.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseLineBox.PROPERTY_RIGHT_PADDING, new Integer(rightPadding.getSelection()));
			}
		});

	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
	 */
	public void refresh() {
		isRefreshing = true;
		APropertyNode element = getElement();
		if (element != null) {
			Integer padding = (Integer) element.getPropertyValue(JRBaseLineBox.PROPERTY_PADDING);
			allPadding.setSelection(padding != null ? padding : 0);
			padding = (Integer) element.getPropertyValue(JRBaseLineBox.PROPERTY_BOTTOM_PADDING);
			bottomPadding.setSelection(padding != null ? padding : 0);
			padding = (Integer) element.getPropertyValue(JRBaseLineBox.PROPERTY_TOP_PADDING);
			topPadding.setSelection(padding != null ? padding : 0);
			padding = (Integer) element.getPropertyValue(JRBaseLineBox.PROPERTY_LEFT_PADDING);
			leftPadding.setSelection(padding != null ? padding : 0);
			padding = (Integer) element.getPropertyValue(JRBaseLineBox.PROPERTY_RIGHT_PADDING);
			rightPadding.setSelection(padding != null ? padding : 0);
		}
		isRefreshing = false;
	}
}
