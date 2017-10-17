/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.properties.view;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.views.properties.PropertySheet;

import com.jaspersoft.studio.properties.internal.TabbedPropertySearch;
import com.jaspersoft.studio.properties.view.validation.ValidationError;

public class ErrorsDialog {
	private Shell shell = null;
	private Region region;
	private boolean ignore = false;

	public void createDialog(final TabbedPropertySheetPage tbsp, Point location, List<ValidationError> errors) {
		shell = new Shell(Display.getDefault(), SWT.NO_TRIM | SWT.ON_TOP | SWT.TOOL);
		shell.setBackground(ColorConstants.tooltipBackground);
		shell.setLocation(location);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		shell.setLayout(layout);

		final Table t = new Table(shell, SWT.SINGLE | SWT.HIDE_SELECTION);
		t.setHeaderVisible(false);
		t.setLinesVisible(false);
		t.setBackground(ColorConstants.tooltipBackground);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 300;
		gd.horizontalIndent = 15;
		t.setLayoutData(gd);
		t.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ignore = true;
				TableItem ti = t.getSelection()[0];
				if (ti.getData() instanceof ValidationError) {
					ValidationError ve = (ValidationError) ti.getData();
					if (ve.getProps().isEmpty())
						return;
					if (tbsp == null) {
						IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
						try {
							IViewPart p = page.showView(IPageLayout.ID_PROP_SHEET, null, IWorkbenchPage.VIEW_VISIBLE);
							IPage pg = ((PropertySheet) p).getCurrentPage();
							if (pg instanceof TabbedPropertySheetPage) {
								TabbedPropertySearch.selectElement(ve.getProps().get(0), (TabbedPropertySheetPage) pg);
								close();
								return;
							}
						} catch (PartInitException e1) {
							e1.printStackTrace();
						}
					}
					if (tbsp != null)
						TabbedPropertySearch.selectElement(ve.getProps().get(0), tbsp);
				}
				close();
			}
		});

		for (ValidationError ve : errors) {
			TableItem item1 = new TableItem(t, SWT.NONE);
			item1.setText(ve.getMessage());
			item1.setData(ve);
			if (ve.isWarning())
				item1.setImage(
						PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_DEC_FIELD_WARNING));
			else
				item1.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_DEC_FIELD_ERROR));
		}

		shell.pack();
		t.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (ignore)
					return;
				close();
			}
		});
		t.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.ESC) {
					close();
				}
			}
		});
		region = new Region();
		region.add(getPolygon());
		shell.setRegion(region);
		shell.open();
		t.forceFocus();
	}

	protected void close() {
		if (shell != null)
			shell.close();
		if (region != null)
			region.dispose();
		shell = null;
	}

	public static int wof = 10;
	public static int hof = 8;
	public static int hh = 5;

	int[] getPolygon() {
		Point e = shell.getSize();
		return new int[] { 0, hof + hh, wof, hof, wof, 0, e.x, 0, e.x, e.y, wof, e.y, wof, hof + 2 * hh, 0, hof + hh };
	}
}
