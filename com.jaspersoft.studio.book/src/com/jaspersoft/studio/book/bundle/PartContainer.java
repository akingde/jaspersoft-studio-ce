/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.bundle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Define a part of a Report Book
 * 
 * @author Orlandin Marco
 * 
 */
public class PartContainer {

	/**
	 * URL of the part file
	 */
	private URL partPath = null;

	/**
	 * Jasper design of the part file. If is saved to avoid to reaload it and it
	 * is loaded only when requested the first time
	 */
	private JasperDesign loadedPart = null;

	/**
	 * Create the part but dosen't load the design, it store only the path to
	 * the resource
	 * 
	 * @param parthPathTextual
	 *            textual path of the resource
	 */
	public PartContainer(String parthPathTextual) {
		File partFile = new File(parthPathTextual);
		if (partFile.exists()) {
			try {
				partPath = partFile.toURI().toURL();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Create the part but dosen't load the design, it store only the path to
	 * the resource
	 * 
	 * @param partPath
	 *            url of the resource
	 */
	public PartContainer(URL partPath) {
		this.partPath = partPath;
	}

	/**
	 * If the jasperdesign was loaded before then it is return from the cache
	 * otherwise it is loaded, cached and returned
	 * 
	 * @return a jasperdesign of null if the url of the part is not valid or
	 *         something goes wrong while loading the jrxml
	 */
	public JasperDesign getJasperDesign() {
		if (loadedPart != null)
			return loadedPart;
		else if (partPath != null) {
			InputStream is = null;
			try {
				is = partPath.openStream();
				loadedPart = JRXmlLoader.load(
						JasperReportsConfiguration.getDefaultInstance(), is);
				return loadedPart;
			} catch (FileNotFoundException ex) {
				// the URL is pointing to a non-existing file
			} catch (Exception ex) {
				closeInputStream(is);
				ex.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Close an input stream if it is not null
	 * 
	 * @param is
	 *            stream to close
	 */
	private void closeInputStream(InputStream is) {
		try {
			if (is != null)
				is.close();
		} catch (IOException e) {
		}
	}

	/**
	 * Remove the jasperdesign loaded from the cache
	 */
	public void clearDesign() {
		loadedPart = null;
	}

	/**
	 * Clone the current PartContainer. The jasperdesign if it was loaded before
	 * is deep copied
	 */
	public PartContainer clone() {
		PartContainer newPart = new PartContainer(partPath);
		if (loadedPart != null) {
			try {
				newPart.loadedPart = ModelUtils.copyJasperDesign(
						JasperReportsConfiguration.getDefaultInstance(),
						loadedPart);
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
		return newPart;
	}
}
