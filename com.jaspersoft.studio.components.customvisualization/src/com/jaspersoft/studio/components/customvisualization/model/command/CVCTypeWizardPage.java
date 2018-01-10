/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.model.command;

import java.util.Collections;
import java.util.Comparator;
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
import com.jaspersoft.studio.components.customvisualization.ui.UIManager;
import com.jaspersoft.studio.components.customvisualization.ui.framework.CVCWidgetsDescriptor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.JSSWizardPage;

import net.sf.jasperreports.eclipse.util.Misc;

public class CVCTypeWizardPage extends JSSWizardPage {
	private static final int GALLERY_HEIGHT = 100;
	private static final int GALLERY_WIDTH = 100;
	private List<CVCWidgetsDescriptor> modules;
	private CVCWidgetsDescriptor module;
	private Scale zoomFactor;
	private Gallery chartsGallery;
	private GalleryItem itemGroup;

	protected CVCTypeWizardPage(List<CVCWidgetsDescriptor> modules) {
		super("cvcwizard"); //$NON-NLS-1$
		setTitle("Custom Visualisation Components");
		setDescription("Select one Custom Visualisation Component from the list.");
		this.modules = modules;
	}

	public CVCWidgetsDescriptor getModule() {
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

		// Set the default zoom value to 1. In order to activate it, we will
		// force a refresh at the end of this method.
		zoomFactor.setSelection(1);

		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gd.widthHint = 150;
		zoomFactor.setLayoutData(gd);

		chartsGallery = new Gallery(composite, SWT.VIRTUAL | SWT.V_SCROLL | SWT.BORDER);
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

				System.out.println("Selection changed..." + e.item);

				if (e.item != null && e.item instanceof GalleryItem) {
					module = (CVCWidgetsDescriptor) ((GalleryItem) e.item).getData();
				} else {
					// Empty selection...
					module = null;
				}

				getContainer().updateButtons();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		setTableSelection();

		SelectionAdapter sa = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				double c = 1 + 0.1 * zoomFactor.getSelection();
				gr.setItemSize((int) (GALLERY_WIDTH * c), (int) (GALLERY_HEIGHT * c));
			}
		};

		zoomFactor.addSelectionListener(sa);

		// Kick an immediate refresh to apply the default zoom.
		sa.widgetSelected(null);

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
		Collections.sort(modules, new Comparator<CVCWidgetsDescriptor>() {

			@Override
			public int compare(CVCWidgetsDescriptor o1, CVCWidgetsDescriptor o2) {
				return o1.getLocalizedString(o1.getLabel()).compareTo(o2.getLocalizedString(o2.getLabel()));
			}
		});

		for (CVCWidgetsDescriptor cd : modules) {
			GalleryItem ti = new GalleryItem(rootItem, SWT.NONE);
			ti.setText(cd.getLocalizedString(cd.getLabel()));

			setGallyeryItemImageInfo(ti, cd, jConf); // $NON-NLS-1$

			ti.setData(cd);
		}
		table.setRedraw(true);
	}

	private static void setGallyeryItemImageInfo(GalleryItem item, CVCWidgetsDescriptor cd,
			JasperReportsConfiguration jConf) {
		if (!Misc.isNullOrEmpty(cd.getThumbnail())) {
			Image img = UIManager.getThumbnail(cd);
			if (img != null) {
				item.setSelectedImage(img);
				item.setStandardImage(img);
				item.setImage(img);
				return;
			}
		}
		setGallyeryItemImageInfo(item, "icons/blank_a4.png");
	}

	private static void setGallyeryItemImageInfo(GalleryItem item, String imagePath) {
		Image img = CustomVisualizationActivator.getDefault().getImage(imagePath);
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

	/**
	 * We allow the user to click finish only if at least a component has been
	 * selected.
	 */
	@Override
	public boolean isPageComplete() {
		return this.chartsGallery.getSelectionCount() > 0;
	}

}
