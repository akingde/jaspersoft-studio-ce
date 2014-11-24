package com.jaspersoft.studio.book.editors;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.XMLContentDescriber;

import com.jaspersoft.studio.book.BookUtils;

public class JRBookDescriber extends XMLContentDescriber {

	@Override
	public int describe(InputStream in, IContentDescription description) throws IOException {
		return BookUtils.validateBook(in, description);
	}

}
