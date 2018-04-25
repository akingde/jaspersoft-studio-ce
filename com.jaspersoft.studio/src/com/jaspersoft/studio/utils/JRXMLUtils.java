/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.jdt.internal.ui.javaeditor.JarEntryEditorInput;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.ide.FileStoreEditorInput;

import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileExtension;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.xml.JRXmlWriter;

/**
 * This class maintains a list of utilities methods to manage JRXML files and
 * streams.
 * 
 * @author Massimo Rabbi
 * 
 */
@SuppressWarnings("restriction")
public class JRXMLUtils {

	/**
	 * Gets a JRXML input stream from an existing one, that can be either a .jasper
	 * file or .jrxml.
	 * <p>
	 * Others file extension are not meant to return a valid JRXML input stream.
	 * 
	 * @param jrContext
	 *            the JasperReports context
	 * @param in
	 *            the original input stream
	 * @param fileExtension
	 *            the file extension
	 * @param encoding
	 *            the file encoding
	 * @param version
	 *            the JR version
	 * @return a valid JRXML input stream, <code>null</code> if not possible
	 * @throws JRException
	 */
	public static InputStream getJRXMLInputStream(JasperReportsContext jrContext, InputStream in, String fileExtension,
			String encoding, String version) throws JRException {
		if (fileExtension != null && fileExtension.equals(FileExtension.JASPER)) {
			// get JRXML from the .jasper
			JasperReport report = (JasperReport) JRLoader.loadObject(in);
			String str;
			try {
				str = JRXmlWriterHelper.writeReport(jrContext, report, JRXmlWriterHelper.fixencoding(encoding),
						version);
				return new ByteArrayInputStream(str.getBytes());
			} catch (Exception e) {
				UIUtils.showError(
						"Something goes wrong while trying to create a JRXML input stream from a .jasper one.", e);
			}
		}
		return in;
	}

	/**
	 * Gets the {@link JasperDesign} out of the specified input stream, using the
	 * JasperReports context as possible additional information.
	 * 
	 * @param jrContext
	 *            the JasperReports context
	 * @param in
	 *            the report input stream
	 * @param fileExtension
	 *            the original file extension
	 * @return the {@link JasperDesign} of the report
	 * @throws JRException
	 */
	public static JasperDesign getJasperDesign(JasperReportsConfiguration jrContext, InputStream in,
			String fileExtension) throws JRException {
		if (fileExtension != null) {
			if (fileExtension.equals(FileExtension.JASPER)) {
				JasperReport jr = (JasperReport) JRLoader.loadObject(in);
				return JRXmlLoader.load(new ByteArrayInputStream(JRXmlWriter.writeReport(jr, "UTF-8").getBytes()));
			}
			if (fileExtension.equals(FileExtension.JRXML))
				return JRXmlLoader.load(jrContext, in);
		} else {
			BufferedInputStream bis = new BufferedInputStream(in);
			bis.mark(Integer.MAX_VALUE);
			try {
				return JRXmlLoader.load(jrContext, bis);
			} catch (Exception e) {
				try {
					bis.reset();
				} catch (IOException e1) {
					UIUtils.showError(e1);
				}
				JasperReport jr = (JasperReport) JRLoader.loadObject(bis);
				return JRXmlLoader.load(new ByteArrayInputStream(JRXmlWriter.writeReport(jr, "UTF-8").getBytes()));
			} finally {
				FileUtils.closeStream(bis);
			}
		}
		return null;
	}

	/**
	 * Gets a JRXML input stream from an existing one, that can be either a .jasper
	 * file or .jrxml.
	 * <p>
	 * Others file extension are not meant to return a valid JRXML input stream.
	 * 
	 * @param jrContext
	 *            the JasperReports context
	 * @param editorInput
	 *            the editor input used to retrieve the file extension
	 * @param encoding
	 *            the file encoding
	 * @param in
	 *            the original input stream
	 * @param version
	 *            the JR version
	 * @return a valid JRXML input stream, <code>null</code> if not possible
	 * @throws JRException
	 */
	public static InputStream getXML(JasperReportsConfiguration jrContext, IEditorInput editorInput, String encoding,
			InputStream in, String version) throws JRException {
		String fileExtension = getFileExtension(editorInput);
		InputStream jrxmlInputStream = getJRXMLInputStream(jrContext, in, fileExtension, encoding, version);
		return jrxmlInputStream != null ? jrxmlInputStream : in;
	}

	/**
	 * Tries to guess the file extension based upon on the specified editor input.
	 * 
	 * @param editorInput
	 *            the editor input used to retrieve the file extension
	 * @return the extension of file currently handled by the specified editor input
	 */
	public static String getFileExtension(IEditorInput editorInput) {
		String fileExtention = ""; //$NON-NLS-1$
		if (editorInput instanceof FileStoreEditorInput) {
			String path = ((FileStoreEditorInput) editorInput).getURI().getPath();
			fileExtention = path.substring(path.lastIndexOf(".") + 1, path.length()); //$NON-NLS-1$
		} else if (editorInput instanceof IFileEditorInput) {
			fileExtention = ((IFileEditorInput) editorInput).getFile().getFileExtension();
		} else if (editorInput instanceof JarEntryEditorInput) {
			fileExtention = ((JarEntryEditorInput) editorInput).getStorage().getFullPath().getFileExtension();
		}
		return fileExtention;
	}

}
