/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.preferences.fonts.wizard;

import net.sf.jasperreports.engine.fonts.FontFace;
import net.sf.jasperreports.engine.fonts.FontFamily;
import net.sf.jasperreports.engine.fonts.SimpleFontFamily;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.ModelUtils;

public class FontFamilyPage extends WizardPage {
	private static final String BOLDITALIC = "BOLDITALIC"; //$NON-NLS-1$
	private static final String ITALIC = "ITALIC"; //$NON-NLS-1$
	private static final String BOLD = "BOLD"; //$NON-NLS-1$
	private static final String NORMAL = "normal"; //$NON-NLS-1$
	private SimpleFontFamily fontFamily;
	private Text dsname;
	private Button embedepdf;
	private Combo pdfenc;
	private Text bold;
	private Text italic;
	private Text bolditalic;
	private Text normal;

	public FontFamilyPage(FontFamily fontFamily) {
		super("fontfamilypage"); //$NON-NLS-1$
		setTitle(Messages.FontFamilyPage_dialogTitle);
		setDescription(Messages.FontFamilyPage_dialogSubtitle);
		this.fontFamily = (SimpleFontFamily) fontFamily;
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		setControl(composite);

		new Label(composite, SWT.NONE).setText(Messages.FontFamilyPage_familyNameLabel);

		dsname = new Text(composite, SWT.BORDER);
		dsname.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				String dstext = dsname.getText();
				if (dstext == null || dstext.trim().equals("")) {//$NON-NLS-1$
					setErrorMessage(Messages.WizardDatasetNewPage_validation_not_null);
					setPageComplete(false);
				} else {
					setPageComplete(true);
					setErrorMessage(null);
					setMessage(getDescription());
					fontFamily.setName(dstext);
				}
			}
		});
		dsname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Group gr = new Group(composite, SWT.NONE);
		gr.setText(Messages.FontFamilyPage_fontDetailsGroup);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		gr.setLayoutData(gd);
		gr.setLayout(new GridLayout(3, false));

		normal = createFileField(gr, Messages.FontFamilyPage_normalLabel, NORMAL);
		normal.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				String dstext = normal.getText();
				if (dstext == null || dstext.trim().equals("")) {//$NON-NLS-1$
					setErrorMessage(Messages.WizardDatasetNewPage_validation_not_null);
					setPageComplete(false);
				} else {
					setPageComplete(true);
					setErrorMessage(null);
					setMessage(getDescription());
				}
			}
		});

		// put a label here
		new Label(gr, SWT.NONE);
		Label label = new Label(gr, SWT.WRAP);
		label.setText(Messages.FontFamilyPage_hintText);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		// gd.horizontalSpan = 2;
		gd.widthHint = 300;
		label.setLayoutData(gd);

		new Label(gr, SWT.NONE);

		bold = createFileField(gr, Messages.FontFamilyPage_boldLabel, BOLD);
		italic = createFileField(gr, Messages.FontFamilyPage_italicLabel, ITALIC);
		bolditalic = createFileField(gr, Messages.FontFamilyPage_boldItalicLabel, BOLDITALIC);

		gr = new Group(composite, SWT.NONE);
		gr.setText(Messages.FontFamilyPage_pdfGroup);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		gr.setLayoutData(gd);
		gr.setLayout(new GridLayout(2, false));

		label = new Label(gr, SWT.NONE);
		label.setText(Messages.FontFamilyPage_pdfHintText);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);

		new Label(gr, SWT.NONE).setText(Messages.FontFamilyPage_pdfEncodingLabel);
		pdfenc = new Combo(gr, SWT.SINGLE | SWT.BORDER);
		pdfenc.setItems(ModelUtils.getPDFEncodings());
		pdfenc.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				String pdfencod = ModelUtils.getPDFEncoding2key(pdfenc.getItem(pdfenc.getSelectionIndex()));
				fontFamily.setPdfEncoding(pdfencod.isEmpty() ? null : pdfencod);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		pdfenc.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				String pdfencod = pdfenc.getText();
				if (pdfencod.isEmpty())
					pdfencod = null;
				else
					pdfencod = ModelUtils.getPDFEncoding2key(pdfencod);
				fontFamily.setPdfEncoding(pdfencod);
			}
		});

		embedepdf = new Button(gr, SWT.CHECK);
		embedepdf.setText(Messages.FontFamilyPage_pdfEmbeddedLabel);
		embedepdf.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				fontFamily.setPdfEmbedded(embedepdf.getSelection());
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), "Jaspersoft.wizard"); //$NON-NLS-1$
		fillWidgets();
	}

	private Text createFileField(Composite composite, String name, final String type) {
		new Label(composite, SWT.NONE).setText(name);

		final Text txt = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		txt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Button button = new Button(composite, SWT.PUSH);
		button.setText(Messages.FontFamilyPage_browseButton);
		button.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(Display.getCurrent().getActiveShell(), SWT.OPEN);
				fd.setText(Messages.FontFamilyPage_browseDialogTitle);
				fd.setFilterExtensions(new String[] { "*.TTF", "*.ttf", "*.eot", "*.EOT", "*.svg", "*.SVG", "*.woff", "*.WOFF" }); //$NON-NLS-1$ //$NON-NLS-2$
				String selected = fd.open();
				if (selected != null) {
					if (type.equals(NORMAL)) {
						fontFamily.setNormal(selected);
						if (fontFamily.getNormalFace() != null)
							fontFamily.setNormalPdfFont(fontFamily.getNormalFace().getName());
						txt.setText(fontFamily.getNormalFace().getName());
					} else if (type.equals(BOLD)) {
						fontFamily.setBold(selected);
						if (fontFamily.getBoldFace() != null)
							fontFamily.setBoldItalicPdfFont(fontFamily.getBoldFace().getName());
						txt.setText(fontFamily.getBoldFace().getName());
					} else if (type.equals(ITALIC)) {
						fontFamily.setItalic(selected);
						if (fontFamily.getItalicFace() != null)
							fontFamily.setItalicPdfFont(fontFamily.getItalicFace().getName());
						txt.setText(fontFamily.getItalicFace().getName());
					} else if (type.equals(BOLDITALIC)) {
						fontFamily.setBoldItalic(selected);
						if (fontFamily.getBoldItalicFace() != null)
							fontFamily.setBoldItalicPdfFont(fontFamily.getBoldItalicFace().getName());
						txt.setText(fontFamily.getBoldItalicFace().getName());
					}
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		return txt;
	}

	private void fillWidgets() {
		dsname.setText(fontFamily.getName());
		setPageComplete(fontFamily.getName() != null);

		FontFace normalFace = fontFamily.getNormalFace();
		if (normalFace != null)
			normal.setText(normalFace.getName());
		else
			setPageComplete(false);

		FontFace boldFace = fontFamily.getBoldFace();
		if (boldFace != null)
			bold.setText(boldFace.getName());
		FontFace italicFace = fontFamily.getItalicFace();
		if (italicFace != null)
			italic.setText(italicFace.getName());
		FontFace boldItalicFace = fontFamily.getBoldItalicFace();
		if (boldItalicFace != null)
			bolditalic.setText(boldItalicFace.getName());

		String pdfEncoding = fontFamily.getPdfEncoding();
		int pdfEncodingIndex = ModelUtils.getPDFEncodingIndex(ModelUtils.getKey4PDFEncoding(pdfEncoding));
		pdfenc.select(pdfEncodingIndex >= 0 ? pdfEncodingIndex : 0);
		if (pdfEncodingIndex < 0 && pdfEncoding != null)
			pdfenc.setText(pdfEncoding);

		if (fontFamily.isPdfEmbedded() != null)
			embedepdf.setSelection(fontFamily.isPdfEmbedded());
	}

	@Override
	public boolean canFlipToNextPage() {
		return isPageComplete();
	}
}
