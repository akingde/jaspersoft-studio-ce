/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * Licensed under commercial Jaspersoft Subscription License Agreement
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.model.command;

import java.util.List;

import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.nebula.widgets.gallery.NoGroupRenderer;
import org.eclipse.nebula.widgets.gallery.RoundedGalleryItemRenderer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Scale;

import com.jaspersoft.studio.components.customvisualization.CustomVisualizationActivator;
import com.jaspersoft.studio.components.customvisualization.ui.ComponentDescriptor;
import com.jaspersoft.studio.components.customvisualization.ui.UIManager;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.JSSWizardPage;

public class CVCTypeWizardPage extends JSSWizardPage {
	private static final int GALLERY_HEIGHT = 100;
	private static final int GALLERY_WIDTH = 100;
	private List<ComponentDescriptor> modules;
	private ComponentDescriptor module;
	private Scale zoomFactor;
	private Gallery chartsGallery;
	private GalleryItem itemGroup;

	protected CVCTypeWizardPage(List<ComponentDescriptor> modules) {
		super("cvcwizard"); //$NON-NLS-1$
		setTitle("Custom Visualisation Components");
		setDescription("Select one Custom Visualisation Component from the list.");
		this.modules = modules;
	}

	public ComponentDescriptor getModule() {
		return module;
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		setControl(composite);

		zoomFactor = new Scale(composite, SWT.NONE);
		zoomFactor.setMinimum(1);
		zoomFactor.setMaximum(50);
		zoomFactor.setIncrement(1);
		zoomFactor.setPageIncrement(5);
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gd.widthHint = 150;
		zoomFactor.setLayoutData(gd);

		chartsGallery = new Gallery(composite, SWT.VIRTUAL | SWT.V_SCROLL
				| SWT.BORDER);
		final NoGroupRenderer gr = new NoGroupRenderer();
		gr.setMinMargin(2);
		gr.setItemSize(GALLERY_WIDTH, GALLERY_HEIGHT);
		gr.setAutoMargin(true);
		gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 500;
		chartsGallery.setLayoutData(gd);
		chartsGallery.setGroupRenderer(gr);
		RoundedGalleryItemRenderer ir = new RoundedGalleryItemRenderer();
		ir.setShowLabels(true);
		// ir.setShowRoundedSelectionCorners(false);
		// ir.setSelectionForegroundColor(getShell().getDisplay().getSystemColor(
		// SWT.COLOR_BLUE));
		chartsGallery.setItemRenderer(ir);

		itemGroup = new GalleryItem(chartsGallery, SWT.NONE);

		fillTableb4j(chartsGallery, itemGroup);

		chartsGallery.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				if (e.item instanceof GalleryItem) {
					module = (ComponentDescriptor) ((GalleryItem) e.item)
							.getData();
					getContainer().updateButtons();
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		setTableSelection();
		zoomFactor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				double c = 1 + 0.1 * zoomFactor.getSelection();
				gr.setItemSize((int) (GALLERY_WIDTH * c),
						(int) (GALLERY_HEIGHT * c));
			}
		});
	}

	private void setTableSelection() {
		for (GalleryItem ti : itemGroup.getItems()) {
			if (ti.getData() == module) {
				chartsGallery.setSelection(new GalleryItem[] { ti });
				break;
			}
		}
	}

	private void fillTableb4j(Gallery table, GalleryItem rootItem) {
		table.setRedraw(false);

		GalleryItem tib = new GalleryItem(rootItem, SWT.NONE);
		tib.setText("Blank");
		setGallyeryItemImageInfo(tib, "icons/blank_a4.png"); //$NON-NLS-1$ 

		JasperReportsConfiguration jConf = getConfig();
		for (ComponentDescriptor cd : modules) {
			GalleryItem ti = new GalleryItem(rootItem, SWT.NONE);
			ti.setText(cd.getLabel());

			setGallyeryItemImageInfo(ti, cd, jConf); //$NON-NLS-1$

			ti.setData(cd);
		}
		table.setRedraw(true);
	}

	private static void setGallyeryItemImageInfo(GalleryItem item,
			ComponentDescriptor cd, JasperReportsConfiguration jConf) {
		if (!Misc.isNullOrEmpty(cd.getThumbnail())) {
			Image img = UIManager.getThumbnail(cd, jConf);
			if (img != null) {
				item.setSelectedImage(img);
				item.setStandardImage(img);
				item.setImage(img);
				return;
			}
		}
		setGallyeryItemImageInfo(item, "icons/blank_a4.png");
	}

	private static void setGallyeryItemImageInfo(GalleryItem item,
			String imagePath) {
		Image img = CustomVisualizationActivator.getDefault().getImage(
				imagePath);
		if (img != null) {
			item.setSelectedImage(img);
			item.setStandardImage(img);
			item.setImage(img);
		}
	}

	@Override
	protected String getContextName() {
		return "";
	}
}
