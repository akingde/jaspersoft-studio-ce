/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.fonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.batik.svggen.font.table.NameTable;
import org.apache.batik.svggen.font.table.Table;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.fonts.FontFamily;
import net.sf.jasperreports.engine.fonts.SimpleFontFace;
import net.sf.jasperreports.engine.fonts.SimpleFontFamily;

public class FontImporter {
	public static void analyzeFolder(List<FontFamily> fontFamilies, File destDir, IProgressMonitor monitor) {

		JasperReportsContext jConfig = JasperReportsConfiguration.getDefaultInstance();
		Map<String, FontFamily> fams = new HashMap<String, FontFamily>();
		for (FontFamily ff : fontFamilies)
			fams.put(ff.getName(), ff);
		for (File f : org.apache.commons.io.FileUtils.listFiles(destDir,
				new String[] { "ttf", "TTF", "OTF", "otf", "SVG", "svg", "eot", "EOT", "WOFF", "woff" }, true)) {
			monitor.setTaskName("Analyzing font: " + f.getName());
			String fname = f.getName().trim().toLowerCase();
			if (fname.endsWith(".ttf") || fname.endsWith(".otf")) {
				try {
					Font font = Font.createFont(Font.TRUETYPE_FONT, f);
					FontFamily ff = fams.get(font.getFamily());
					if (ff == null) {
						ff = new SimpleFontFamily(jConfig);
						fontFamilies.add(ff);
						((SimpleFontFamily) ff).setName(font.getFamily());
						((SimpleFontFamily) ff).setPdfEncoding("Identity-H");

						fams.put(font.getFamily(), ff);
					}
					SimpleFontFace fontFace = new SimpleFontFace(jConfig);
					fontFace.setTtf(f.getAbsolutePath());
					try {
						// System.out.println("Reading: " + f.getAbsolutePath());
						// otf fonts are not read, maybe this depends on batik version, we have an old one
						org.apache.batik.svggen.font.Font bfont = org.apache.batik.svggen.font.Font.create(f.getAbsolutePath());

						NameTable nt = bfont.getNameTable();
						// System.out.println(nt.getRecord(Table.nameFontFamilyName));
						// System.out.println(nt.getRecord(Table.nameFullFontName));
						// System.out.println(nt.getRecord(Table.namePostscriptName));

						String subFamily = nt.getRecord(Table.nameFontSubfamilyName).toLowerCase();
						// System.out.println(subFamily);
						if (subFamily.contains("bold") && subFamily.contains("italic"))
							((SimpleFontFamily) ff).setBoldItalicFace(fontFace);
						else if (subFamily.equals("italic"))
							((SimpleFontFamily) ff).setItalicFace(fontFace);
						else if (subFamily.equals("bold"))
							((SimpleFontFamily) ff).setBoldFace(fontFace);
						else
							((SimpleFontFamily) ff).setNormalFace(fontFace);
					} catch (Exception e) {
						e.printStackTrace();
						if (font.isBold() && font.isItalic())
							((SimpleFontFamily) ff).setBoldItalicFace(fontFace);
						else if (font.isBold())
							((SimpleFontFamily) ff).setBoldFace(fontFace);
						else if (font.isItalic())
							((SimpleFontFamily) ff).setItalicFace(fontFace);
						else if (font.isPlain())
							((SimpleFontFamily) ff).setNormalFace(fontFace);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (FontFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (fname.endsWith(".svg")) {
				// here we could parse svg and look for properties
				System.out.println(f.getAbsolutePath());
			} else if (fname.endsWith(".eot")) {
				// here looks like it is a compressed ttf/otf
				System.out.println(f.getAbsolutePath());
			} else if (fname.endsWith(".woff")) {
				// here looks like it is a compressed ttf/otf
				System.out.println(f.getAbsolutePath());
			}
			if (monitor.isCanceled())
				return;
		}

	}
}
