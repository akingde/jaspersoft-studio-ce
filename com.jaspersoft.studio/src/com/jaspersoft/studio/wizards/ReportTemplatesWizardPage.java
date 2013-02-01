/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.wizards;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.nebula.widgets.gallery.NoGroupRenderer;
import org.eclipse.nebula.widgets.gallery.RoundedGalleryItemRenderer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.templates.JrxmlTemplateBundle;
import com.jaspersoft.studio.templates.StudioTemplateManager;
import com.jaspersoft.studio.utils.SWTImageEffects;
import com.jaspersoft.studio.utils.SWTImageEffects.Glow;
import com.jaspersoft.templates.TemplateBundle;

/**
 * This page is used to allow the user to select a template bundle.
 * The selected template bundle (TemplateBundle)  is stored in the JSSWizard.getSettings() map with the key "template".
 * Any following page can use this information to propose specific defaults (i.e. the new file name...)
 * 
 * @author gtoffoli
 *
 */
public class ReportTemplatesWizardPage extends JSSWizardPage {
	private java.util.List<URL> urls = new ArrayList<URL>();
	private java.util.List<TemplateBundle> templateBundles = new ArrayList<TemplateBundle>();

	
	private Gallery gal;
	private GalleryItem itemGroup;
	private List<Image> templateImages;
	
	private TemplateBundle selectedTemplate = null;

	public TemplateBundle getTemplateBundle() {
		return selectedTemplate;
	}

	/**
	 * Create the wizard.
	 */
	public ReportTemplatesWizardPage() {
		super("templatenewreportwizardPage"); //$NON-NLS-1$
		setTitle(Messages.ReportTemplatesWizardPage_title);
		setDescription(Messages.ReportTemplatesWizardPage_description);
		templateImages=new ArrayList<Image>();
		contextName = ContextHelpIDs.WIZARD_TEMPLATE_PAGE;
	}

	private static final int GALLERY_HEIGHT = 100;
	private static final int GALLERY_WIDTH = 100;
	private NoGroupRenderer gr;
	private Scale scale;

	/**
	 * Create contents of the wizard.
	 * 
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
		container.setLayout(new GridLayout(2, false));

		Label lbl = new Label(container, SWT.NONE);
		lbl.setText("Zoom:");
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.FILL_HORIZONTAL);
		lbl.setLayoutData(gd);

		scale = new Scale(container, SWT.NONE);
		scale.setMinimum(1);
		scale.setMaximum(50);
		scale.setIncrement(1);
		scale.setPageIncrement(5);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gd.widthHint = 150;
		scale.setLayoutData(gd);

		gal = new Gallery(container, SWT.VIRTUAL | SWT.V_SCROLL | SWT.BORDER);
		gr = new NoGroupRenderer();
		gr.setMinMargin(2);
		gr.setItemSize(GALLERY_WIDTH, GALLERY_HEIGHT);
		gr.setAutoMargin(true);
		gal.setGroupRenderer(gr);
		RoundedGalleryItemRenderer ir = new RoundedGalleryItemRenderer();
		ir.setShowLabels(true);
		gal.setItemRenderer(ir);

		itemGroup = new GalleryItem(gal, SWT.NONE);

		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		gal.setLayoutData(gd);
		gal.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				storeSettings();
				setPageComplete(validatePage());
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		gal.addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent e) {
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				getContainer().showPage(getNextPage());
			}
		});
		
		findTemplates();

		scale.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				zoomModified();
			}
		});
		
		scale.setSelection(6);
		// Manually fire the event because the invocation 
		// of #Scale.selection() does not fire it.
		zoomModified();
	}
	
	/*
	 * Method that handles the zoom modification (scale widget). 
	 */
	private void zoomModified(){
			double c = 1 + 0.1 * scale.getSelection();
			gr.setItemSize((int) (GALLERY_WIDTH * c), (int) (GALLERY_HEIGHT * c));
	}

	
	
	public void findTemplates() {

		// Load all the available templates by invoking the template manager
		List<TemplateBundle> bundles = StudioTemplateManager.getInstance().getTemplateBundles();
		
		for (TemplateBundle b : bundles)
		{
			GalleryItem item = new GalleryItem(itemGroup, SWT.NONE);
			item.setData("template", b);
			urls.add(null);
			templateBundles.add(b);
			
			if (b instanceof JrxmlTemplateBundle)
			{
				Image itemImage = ((JrxmlTemplateBundle)b).getIcon();
				
				if (itemImage != null)
				{
					
						// Add viewer required effects to the images shown...
						Image selectedImg =new Image(itemImage.getDevice(), SWTImageEffects.extendArea(itemImage.getImageData(), 40, null));
						Image standardShadowedImg=new Image(itemImage.getDevice(), Glow.glow(itemImage.getImageData(), ResourceManager.getColor(SWT.COLOR_GRAY), 40, 0, 255));
						item.setSelectedImage(selectedImg);
						item.setStandardImage(standardShadowedImg);
						item.setImage(standardShadowedImg);
						
						// Save image references, so they can later be disposed
						templateImages.add(selectedImg);
						templateImages.add(standardShadowedImg);
						//item.setImage(itemImage);
				}
				
				item.setText( b.getLabel()); //$NON-NLS-1$
				
				if (gal.getSelectionCount() <= 0)
				{
					gal.setSelection(new GalleryItem[] { item });
					storeSettings();
					setPageComplete(validatePage());
				}
			
			}
		}
		
	}


	public static String capitalizeFirstLetters(String s) {

		for (int i = 0; i < s.length(); i++) {

			if (i == 0) {
				// Capitalize the first letter of the string.
				s = String.format("%s%s", Character.toUpperCase(s.charAt(0)), s.substring(1));
			}

			// Is this character a non-letter or non-digit? If so
			// then this is probably a word boundary so let's capitalize
			// the next character in the sequence.
			if (!Character.isLetterOrDigit(s.charAt(i))) {
				if (i + 1 < s.length()) {
					s = String.format("%s%s%s", s.subSequence(0, i + 1), Character.toUpperCase(s.charAt(i + 1)),
							s.substring(i + 2));
				}
			}

		}

		return s;

	}

	@Override
	public void dispose() {
		super.dispose();
		// Dispose all images used for template thumbnails
		for(Image img : templateImages){
			img.dispose();
		}
		templateImages.clear();
		templateImages=null;
	}


	/**
	 * We don't want to proceed until a template has been selected...
	 * In this method we check if the user has made her selection
	 */
	public boolean validatePage()
	{
		if (gal.getSelectionCount() == 0) return false;
		return true;
	}
	
	
	/**
	 * Store inside the wizard settings the user selection.
	 */
	public void storeSettings()
	{
		if (getSettings() == null) return;
		if (gal == null) return;
		
		GalleryItem[] selection = gal.getSelection();
		
		if (selection != null && selection.length > 0) {
			
			selectedTemplate = (TemplateBundle) selection[0].getData("template");
			getSettings().put("template",  selectedTemplate );
		}
		else
		{
			getSettings().remove("template");
			selectedTemplate = null;
		}
	}
	
	
}
