/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.fonts.wizard;

import java.io.File;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.fonts.SimpleFontFace;

public class FontFaceFragment {

	private SimpleFontFace fontFace;
	
	private static String lastLocation;

	protected FontFaceFragment(SimpleFontFace fontFace) {
		this.fontFace = fontFace;
	}

	public Composite createDialogArea(Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		cmp.setLayout(new GridLayout(3, false));

		Text txt = createFileField(cmp, Messages.FontFaceFragment_1, "ttf");   //$NON-NLS-2$
		txt.setText(Misc.nvl(fontFace.getTtf()));
		txt.setToolTipText(Misc.nvl(fontFace.getTtf()));
		txt = createFileField(cmp, Messages.FontFaceFragment_3, "eot");   //$NON-NLS-2$
		txt.setText(Misc.nvl(fontFace.getEot()));
		txt.setToolTipText(Misc.nvl(fontFace.getEot()));
		txt = createFileField(cmp, Messages.FontFaceFragment_5, "svg");   //$NON-NLS-2$
		txt.setText(Misc.nvl(fontFace.getSvg()));
		txt.setToolTipText(Misc.nvl(fontFace.getSvg()));
		txt = createFileField(cmp, Messages.FontFaceFragment_7, "woff");   //$NON-NLS-2$
		txt.setText(Misc.nvl(fontFace.getWoff()));
		txt.setToolTipText(Misc.nvl(fontFace.getWoff()));

		new Label(cmp, SWT.NONE).setText(Messages.FontFaceFragment_0);

		final Combo txtPdf = new Combo(cmp, SWT.BORDER);
		txtPdf.setItems(new String[] { "Courier", "Courier-Bold", "Courier-BoldOblique", "Courier-Oblique", "Helvetica", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				"Helvetica-Bold", "Helvetica-BoldOblique", "Helvetica-Oblique", "Symbol", "Times-Roman", "Times-Bold", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
				"Times-BoldItalic", "Times-Italic", "ZapfDingbats", "STSong-Light", "Mhei-Medium", "MSung-Light", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
				"HeiseiKakuGo-W5", "HeiseiMin-W3", "HYGoThic-Medium", "HYSMyeongJo-Medium" }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		txtPdf.setText(Misc.nvl(fontFace.getPdf()));
		txtPdf.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				String fname = txtPdf.getText();
				if(fname.trim().isEmpty())
					fname = null;
				fontFace.setPdf(fname);
			}
		});
		return cmp;
	}

	private Text createFileField(Composite composite, String name, final String type) {
		new Label(composite, SWT.NONE).setText(name + " (." + type + ")"); //$NON-NLS-1$ //$NON-NLS-2$

		final Text txt = new Text(composite, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 300;
		txt.setLayoutData(gd);
		txt.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				String selected = txt.getText();
				if (selected.trim().isEmpty())
					selected = null;
				if (type.equals("ttf")) //$NON-NLS-1$
					fontFace.setTtf(selected, false);
				else if (type.equals("eot")) //$NON-NLS-1$
					fontFace.setEot(selected);
				else if (type.equals("svg")) //$NON-NLS-1$
					fontFace.setSvg(selected);
				else if (type.equals("woff")) //$NON-NLS-1$
					fontFace.setWoff(selected);
			}
		});

		Button button = new Button(composite, SWT.PUSH);
		button.setText(Messages.FontFamilyPage_browseButton);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(Display.getDefault().getActiveShell(), SWT.OPEN);
				fd.setText(Messages.FontFamilyPage_browseDialogTitle);
				setupLastLocation(fd);
				String font = null;
				if (type.equals("ttf")) //$NON-NLS-1$
					font = fontFace.getTtf();
				else if (type.equals("eot")) //$NON-NLS-1$
					font = fontFace.getEot();
				else if (type.equals("svg")) //$NON-NLS-1$
					font = fontFace.getSvg();
				else if (type.equals("woff")) //$NON-NLS-1$
					font = fontFace.getWoff();
				if (font != null)
					fd.setFilterPath(font.substring(0, font.lastIndexOf(File.separatorChar)));
				fd.setFilterExtensions(new String[] { "*." + type + ";*." + type.toUpperCase(), "*.*" }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$  
				String selected = fd.open();
				setLastLocation(fd, selected);
				if (selected != null) {
					selected = selected.trim();
					if (type.equals("ttf")) //$NON-NLS-1$
						fontFace.setTtf(selected, false);
					else if (type.equals("eot")) //$NON-NLS-1$
						fontFace.setEot(selected);
					else if (type.equals("svg")) //$NON-NLS-1$
						fontFace.setSvg(selected);
					else if (type.equals("woff")) //$NON-NLS-1$
						fontFace.setWoff(selected);
					txt.setText(Misc.nvl(selected));
					txt.setToolTipText(Misc.nvl(selected));
				}
			}
		});
		return txt;
	}

	public static String setupLastLocation(FileDialog dialog) {
		if (lastLocation == null)
			lastLocation = ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString();
		dialog.setFilterPath(lastLocation);
		return lastLocation;
	}

	public static void setLastLocation(FileDialog dialog, String selected) {
		if (!Misc.isNullOrEmpty(selected))
			lastLocation = selected.substring(0, selected.lastIndexOf(File.separatorChar));
		else if (!Misc.isNullOrEmpty(dialog.getFileName()))
			lastLocation = dialog.getFileName();
	}
}
