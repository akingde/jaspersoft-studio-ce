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
	private static final String BOLDITALIC = "BOLDITALIC";
	private static final String ITALIC = "ITALIC";
	private static final String BOLD = "BOLD";
	private static final String NORMAL = "normal";
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
		setTitle("Font Family");
		setDescription("Configure font family");
		this.fontFamily = (SimpleFontFamily) fontFamily;
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		setControl(composite);

		new Label(composite, SWT.NONE).setText("Family Name");

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
		gr.setText("Font Details");
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		gr.setLayoutData(gd);
		gr.setLayout(new GridLayout(3, false));

		normal = createFileField(gr, "Normal", NORMAL);
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
		label
				.setText("Optionally, it is possible to provide other True Type Fonts for the BOLD, ITALIC, BOLD-ITALIC versions of the font family.");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		// gd.horizontalSpan = 2;
		gd.widthHint = 300;
		label.setLayoutData(gd);

		new Label(gr, SWT.NONE);

		bold = createFileField(gr, "Bold", BOLD);
		italic = createFileField(gr, "Italic", ITALIC);
		bolditalic = createFileField(gr, "Bold Italic", BOLDITALIC);

		gr = new Group(composite, SWT.NONE);
		gr.setText("PDF Details");
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		gr.setLayoutData(gd);
		gr.setLayout(new GridLayout(2, false));

		label = new Label(gr, SWT.NONE);
		label.setText("These settings are used when reports are exported to PDF");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);

		new Label(gr, SWT.NONE).setText("PDF Encoding");
		pdfenc = new Combo(gr, SWT.SINGLE | SWT.BORDER);
		pdfenc.setItems(ModelUtils.getPDFEncodings());
		pdfenc.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				fontFamily.setPdfEncoding(pdfenc.getItem(pdfenc.getSelectionIndex()));
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		embedepdf = new Button(gr, SWT.CHECK);
		embedepdf.setText("Embed this font in PDF document");
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
		button.setText("Browse ...");
		button.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(Display.getCurrent().getActiveShell(), SWT.OPEN);
				fd.setText("Find True Type Font");
				fd.setFilterExtensions(new String[] { "*.TTF", "*.ttf" });
				String selected = fd.open();
				if (selected != null) {
					if (type.equals(NORMAL)) {
						fontFamily.setNormal(selected);
						txt.setText(fontFamily.getNormalFace().getName());
					} else if (type.equals(BOLD)) {
						fontFamily.setBold(selected);
						txt.setText(fontFamily.getBoldFace().getName());
					} else if (type.equals(ITALIC)) {
						fontFamily.setItalic(selected);
						txt.setText(fontFamily.getItalicFace().getName());
					} else if (type.equals(BOLDITALIC)) {
						fontFamily.setBoldItalic(selected);
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

		pdfenc.select(ModelUtils.getPDFEncodingIndex(fontFamily.getPdfEncoding()));
		if (fontFamily.isPdfEmbedded() != null)
			embedepdf.setSelection(fontFamily.isPdfEmbedded());
	}

	@Override
	public boolean canFlipToNextPage() {
		return isPageComplete();
	}
}
