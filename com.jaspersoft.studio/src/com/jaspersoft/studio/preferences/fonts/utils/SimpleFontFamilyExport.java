/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.preferences.fonts.utils;

import java.util.Locale;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.fonts.FontFace;
import net.sf.jasperreports.engine.fonts.FontFamily;
import net.sf.jasperreports.engine.fonts.SimpleFontFamily;
import net.sf.jasperreports.engine.util.JRDataUtils;

public class SimpleFontFamilyExport implements FontFamily {
	private SimpleFontFamily delegate;

	public SimpleFontFamilyExport(SimpleFontFamily sff) {
		super();
		this.delegate = sff;
	}

	/**
			 * 
			 */
	private FontFace normalFace;
	private FontFace boldFace;
	private FontFace italicFace;
	private FontFace boldItalicFace;

	/**
			 * 
			 */
	public String getName() {
		return delegate.getName();
	}

	/**
			 * 
			 */
	public void setName(String name) {
		delegate.setName(name);
	}

	/**
			 * 
			 */
	public void setNormal(String normal) {
		normalFace = createFontFace(normal);
	}

	/**
			 * 
			 */
	public void setBold(String bold) {
		boldFace = createFontFace(bold);
	}

	/**
			 * 
			 */
	public void setItalic(String italic) {
		italicFace = createFontFace(italic);
	}

	/**
			 * 
			 */
	public void setBoldItalic(String boldItalic) {
		boldItalicFace = createFontFace(boldItalic);
	}

	/**
			 * 
			 */
	public FontFace getNormalFace() {
		return normalFace;
	}

	/**
			 * 
			 */
	public FontFace getBoldFace() {
		return boldFace;
	}

	/**
			 * 
			 */
	public FontFace getItalicFace() {
		return italicFace;
	}

	/**
			 * 
			 */
	public FontFace getBoldItalicFace() {
		return boldItalicFace;
	}

	/**
			 * 
			 */
	public String getNormalPdfFont() {
		return delegate.getNormalPdfFont();
	}

	/**
			 * 
			 */
	public void setNormalPdfFont(String normalPdfFont) {
		delegate.setNormalPdfFont(normalPdfFont);
	}

	/**
			 * 
			 */
	public String getBoldPdfFont() {
		return delegate.getBoldPdfFont();
	}

	/**
			 * 
			 */
	public void setBoldPdfFont(String boldPdfFont) {
		delegate.setBoldPdfFont(boldPdfFont);
	}

	/**
			 * 
			 */
	public String getItalicPdfFont() {
		return delegate.getItalicPdfFont();
	}

	/**
			 * 
			 */
	public void setItalicPdfFont(String italicPdfFont) {
		delegate.setItalicPdfFont(italicPdfFont);
	}

	/**
			 * 
			 */
	public String getBoldItalicPdfFont() {
		return delegate.getBoldItalicPdfFont();
	}

	/**
			 * 
			 */
	public void setBoldItalicPdfFont(String boldItalicPdfFont) {
		delegate.setBoldItalicPdfFont(boldItalicPdfFont);
	}

	/**
			 * 
			 */
	public String getPdfEncoding() {
		return delegate.getPdfEncoding();
	}

	/**
			 * 
			 */
	public void setPdfEncoding(String pdfEncoding) {
		delegate.setPdfEncoding(pdfEncoding);
	}

	/**
			 * 
			 */
	public Boolean isPdfEmbedded() {
		return delegate.isPdfEmbedded();
	}

	/**
			 * 
			 */
	public void setPdfEmbedded(Boolean isPdfEmbedded) {
		delegate.setPdfEmbedded(isPdfEmbedded);
	}

	/**
			 * 
			 */
	public String getDefaultExportFont() {
		return delegate.getDefaultExportFont();
	}

	/**
			 * 
			 */
	public void setDefaultExportFont(String defaultExportFont) {
		delegate.setDefaultExportFont(defaultExportFont);
	}

	/**
			 * 
			 */
	public Map<String, String> getExportFonts() {
		return delegate.getExportFonts();
	}

	/**
			 * 
			 */
	public void setExportFonts(Map<String, String> exportFonts) {
		delegate.setExportFonts(exportFonts);
	}

	/**
			 * 
			 */
	public String getExportFont(String key) {
		String exportFont = getExportFonts() == null ? null : (String) getExportFonts().get(key);
		return exportFont == null ? getDefaultExportFont() : exportFont;
	}

	/**
			 * 
			 */
	public Set<String> getLocales() {
		return delegate.getLocales();
	}

	/**
			 * 
			 */
	public void setLocales(Set<String> locales) {
		delegate.setLocales(locales);
	}

	/**
			 * 
			 */
	public boolean supportsLocale(Locale locale) {
		return getLocales() == null || getLocales().contains(JRDataUtils.getLocaleCode(locale));
	}

	private static FontFace createFontFace(String value) {
		return new SimpleFontFaceExport(value);
	}

}
