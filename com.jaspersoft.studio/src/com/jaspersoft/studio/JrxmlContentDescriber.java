/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.input.ReaderInputStream;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescriber;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.ITextContentDescriber;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.jaspersoft.studio.utils.XMLUtils;

import net.sf.jasperreports.eclipse.util.FileUtils;

/**
 * Content describer for the standard JRXML files.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class JrxmlContentDescriber implements ITextContentDescriber {

	@Override
	public int describe(InputStream contents, IContentDescription description) throws IOException {
		return validateStandardJRXML(contents);
	}

	@Override
	public QualifiedName[] getSupportedOptions() {
		return null;
	}

	@Override
	public int describe(Reader contents, IContentDescription description) throws IOException {
		ReaderInputStream contentsIS = null;
		try {
			contentsIS = new ReaderInputStream(contents, FileUtils.UTF8_ENCODING);
			return validateStandardJRXML(contentsIS);
		} finally {
			if (contentsIS != null) {
				contentsIS.close();
			}
		}
	}

	/**
	 * Checks if the input file represents a standard JRXML file.
	 * 
	 * @param file
	 *          the input file to check
	 * @return <code>true</code> if the file is a standard JRXML, <code>false</code> otherwise
	 */
	public static boolean isStandardJRXML(InputStream contents) {
		try {
			int validationResult = validateStandardJRXML(contents);
			return ITextContentDescriber.VALID == validationResult;
		} catch (IOException e) {
			JaspersoftStudioPlugin.getInstance().logError(e);
			return false;
		} finally {
			FileUtils.closeStream(contents);
		}
	}

	/**
	 * Checks if the input file represents a standard JRXML file.
	 * 
	 * @param file
	 *          the input file to check
	 * @return <code>true</code> if the file is a standard JRXML, <code>false</code> otherwise
	 */
	public static boolean isStandardJRXML(IFile file) {
		try {
			return isStandardJRXML(file.getContents());
		} catch (CoreException e) {
			JaspersoftStudioPlugin.getInstance().logError(e);
		}
		return false;
	}

	/**
	 * Validates the input stream and verifies it is a standard JRXML.
	 * 
	 * @param in
	 *          the XML input stream
	 * @param description
	 *          content description
	 * @return {@link IContentDescriber#VALID} if XML is representing a standard JRXML, {@link IContentDescriber#INVALID}
	 *         otherwise (i.e. JasperBook)
	 * @throws IOException
	 */
	public static int validateStandardJRXML(InputStream in) throws IOException {
		// This piece of code is a slightly modified version of
		// the original method com.jaspersoft.studio.book.BookUtils#validateBook()
		try {
			// Preliminary check on empty inputstream to avoid ParseException
			if(in.markSupported()) {
				try {
					in.mark(0);
					int firstRead = in.read();
					if(firstRead==-1) {
						return INVALID;
					}
				} finally{
					in.reset();
				}
			}
			Document document = XMLUtils.parseNoValidation(in);
			document.getDocumentElement().normalize();
			NodeList bookParts = document.getElementsByTagName("part"); //$NON-NLS-1$
			if (bookParts == null || bookParts.getLength() == 0) {
				return VALID;
			}
		} catch (ParserConfigurationException e) {
			JaspersoftStudioPlugin.getInstance().logError(e);
		} catch (SAXException e) {
			JaspersoftStudioPlugin.getInstance().logError(e);
		}
		return INVALID;
	}
}
