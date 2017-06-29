/*******************************************************************************
 * Copyright (C) 2005 - 2016 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.book;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.apache.commons.io.input.ReaderInputStream;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.ITextContentDescriber;

import net.sf.jasperreports.eclipse.util.FileUtils;

/**
 * Content describer for the JasperBook files.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class JasperBookContentDescriber implements ITextContentDescriber {

	@Override
	public int describe(InputStream contents, IContentDescription description) throws IOException {
		return BookUtils.validateBook(contents, description);
	}

	@Override
	public QualifiedName[] getSupportedOptions() {
		return null;
	}

	@Override
	public int describe(Reader contents, IContentDescription description) throws IOException {
		ReaderInputStream contentsIS = null;
		try{
			contentsIS = new ReaderInputStream(contents, FileUtils.UTF8_ENCODING);
			return BookUtils.validateBook(contentsIS, description);
		}
		finally {
			if(contentsIS!=null){
				contentsIS.close();
			}
		}
	}

}
