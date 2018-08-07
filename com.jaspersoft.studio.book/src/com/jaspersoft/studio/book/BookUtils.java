/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.ITextContentDescriber;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.ide.IDE;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.jaspersoft.studio.book.editors.JRBookEditor;
import com.jaspersoft.studio.utils.XMLUtils;

import net.sf.jasperreports.eclipse.builder.JasperReportsNature;

/**
 * Utility class containing generic methods for dealing with the book reports.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class BookUtils {

	public static final int BOOK_VALID = ITextContentDescriber.VALID;
	public static final int BOOK_INVALID = ITextContentDescriber.INVALID;

	/**
	 * Scans the workspace for possible book reports and sets the default editor to
	 * the {@link JRBookEditor} one.
	 * 
	 * @throws CoreException
	 */
	public static void scanWSForBookReports() throws CoreException {
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		for (IProject proj : projects) {
			if (proj.isOpen() && proj.hasNature(JasperReportsNature.NATURE_ID)) {
				checkContainerMembersForDefaultEditor(proj);
			}
		}
	}

	/*
	 * Verifies the children of a generic resource if they can be book reports.
	 */
	private static void checkContainerMembersForDefaultEditor(IContainer resource) throws CoreException {
		IResource[] members = resource.members();
		for (IResource r : members) {
			if (r instanceof IFile) {
				checkFileResourceForDefaultEditor((IFile) r);
			} else if (r instanceof IContainer) {
				checkContainerMembersForDefaultEditor((IContainer) r);
			}
		}
	}

	/**
	 * Checks the specified file and if necessary sets the default editor
	 * information.
	 * 
	 * @param fileResource
	 *            the file to be checked
	 */
	public static void checkFileResourceForDefaultEditor(IFile fileResource) {
		String fileExtension = fileResource.getFileExtension();
		IEditorDescriptor defaultEditor = IDE.getDefaultEditor(fileResource);
		if ("jrxml".equals(fileExtension) && defaultEditor != null
				&& !JRBookEditor.BOOK_EDITOR_ID.equals(defaultEditor.getId())) {
			if (BookUtils.isValidJRBook(fileResource)) {
				IDE.setDefaultEditor(fileResource, JRBookEditor.BOOK_EDITOR_ID);
			}
		}
	}

	/**
	 * Checks if the specified file is a JasperReports Book.
	 * 
	 * @param file
	 *            the file to check
	 * @return <code>true</code> if the file is a JasperReports Book,
	 *         <code>false</code> otherwise
	 */
	public static boolean isValidJRBook(IFile file) {
		try {
			IContentDescription contentDescription = file.getContentDescription();
			InputStream contents = file.getContents();
			int validationResult = validateBook(contents, contentDescription);
			contents.close();
			return BOOK_VALID == validationResult;
		} catch (CoreException e) {
			JRBookActivator.getDefault().logError(e);
			return false;
		} catch (IOException e) {
			JRBookActivator.getDefault().logError(e);
			return false;
		}
	}

	/**
	 * Validate an input stream as possible JasperReports Book.
	 * 
	 * @param in
	 *            the file input stream
	 * @param description
	 *            the file description
	 * @return <code>VALID</code> if the file is JasperReports Book,
	 *         <code>INVALID</code> otherwise
	 * @throws IOException
	 */
	public static int validateBook(InputStream in, IContentDescription description) throws IOException {
		try {
			// Preliminary check on empty inputstream to avoid ParseException
			if (in.markSupported()) {
				try {
					in.mark(0);
					int firstRead = in.read();
					if (firstRead == -1) {
						return BOOK_INVALID;
					}
				} finally {
					in.reset();
				}
			}
			Document document = XMLUtils.parseNoValidation(in);
			if (document != null) {
				if (document.getDocumentElement() != null)
					document.getDocumentElement().normalize();
				NodeList bookParts = document.getElementsByTagName("part");
				if (bookParts != null && bookParts.getLength() > 0) {
					return BOOK_VALID;
				}
			}
		} catch (ParserConfigurationException e) {
			JRBookActivator.getDefault().logError(e);
		} catch (SAXException e) {
			JRBookActivator.getDefault().logError(e);
		}
		return BOOK_INVALID;
	}
}
