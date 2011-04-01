package com.jaspersoft.studio.wizards;

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.nebula.widgets.gallery.DefaultGalleryItemRenderer;
import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.nebula.widgets.gallery.NoGroupRenderer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Scale;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;


public class ReportTemplatesWizardPage extends WizardPage {
	private java.util.List<URL> urls = new ArrayList<URL>();
	private int template;
	private Gallery gal;
	private GalleryItem itemGroup;

	public URL getTemplate() {
		if (template >= 0 && template < urls.size())
			return urls.get(template);
		return null;
	}

	/**
	 * Create the wizard.
	 */
	public ReportTemplatesWizardPage() {
		super("templatenewreportwizardPage"); //$NON-NLS-1$
		setTitle(Messages.ReportTemplatesWizardPage_title);
		setDescription(Messages.ReportTemplatesWizardPage_description);
	}

	private static final int GALLERY_HEIGHT = 100;
	private static final int GALLERY_WIDTH = 100;

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

		final Scale scale = new Scale(container, SWT.BORDER);
		scale.setMinimum(1);
		scale.setMaximum(50);
		scale.setIncrement(1);
		scale.setPageIncrement(1);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gd.widthHint = 150;
		scale.setLayoutData(gd);

		gal = new Gallery(container, SWT.VIRTUAL | SWT.V_SCROLL | SWT.BORDER);
		final NoGroupRenderer gr = new NoGroupRenderer();
		gr.setMinMargin(2);
		gr.setItemHeight(GALLERY_HEIGHT);
		gr.setItemWidth(GALLERY_WIDTH);
		gr.setAutoMargin(true);
		gal.setGroupRenderer(gr);
		DefaultGalleryItemRenderer ir = new DefaultGalleryItemRenderer();
		ir.setShowLabels(true);
		ir.setShowRoundedSelectionCorners(false);
		gal.setItemRenderer(ir);

		itemGroup = new GalleryItem(gal, SWT.NONE);

		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		gal.setLayoutData(gd);
		gal.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				GalleryItem[] selection = gal.getSelection();
				if (selection != null && selection.length >= 0) {
					Object sdata = selection[0].getData("url");
					if (sdata != null && urls.indexOf(sdata) >= 0)
						template = urls.indexOf(sdata);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		findTemplates();

		scale.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				Display.getCurrent().asyncExec(new Runnable() {

					public void run() {
						double c = 1 + 0.1 * scale.getSelection();
						gr.setItemHeight((int) (GALLERY_HEIGHT * c));
						gr.setItemWidth((int) (GALLERY_WIDTH * c));
					}
				});

			}
		});
	}

	public void findTemplates() {
		java.util.List<String> items = new ArrayList<String>();
		Enumeration<?> en = JaspersoftStudioPlugin.getInstance().getBundle().findEntries("templates", "*.jrxml", true); //$NON-NLS-1$ //$NON-NLS-2$
		while (en.hasMoreElements()) {
			URL obj = (URL) en.nextElement();
			urls.add(obj);

			String filename = obj.getFile().replaceAll("/templates/", "");
			items.add(filename);

			GalleryItem item = new GalleryItem(itemGroup, SWT.NONE);
			item.setData("url", obj);
			String tname = capitalizeFirstLetters(filename.replaceAll(".jrxml", "").replace("_", " "));

			item.setText(tname); //$NON-NLS-1$
			Image itemImage = JaspersoftStudioPlugin.getImage(obj.getFile().replaceAll(".jrxml", ".png"));
			if (itemImage == null)
				itemImage = JaspersoftStudioPlugin.getImage(obj.getFile().replaceAll(".jrxml", ".gif"));
			if (itemImage == null)
				itemImage = JaspersoftStudioPlugin.getImage(obj.getFile().replaceAll(".jrxml", ".jpg"));
			if (itemImage == null)
				itemImage = JaspersoftStudioPlugin.getImage(obj.getFile().replaceAll(".jrxml", ".jpg"));
			if (itemImage == null)
				itemImage = JaspersoftStudioPlugin.getImage("blank_a4.png");

			if (itemImage != null)
				item.setImage(itemImage);

			if (gal.getSelectionCount() <= 0)
				gal.setSelection(new GalleryItem[] { item });
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
}
