package com.jaspersoft.studio.rcp.intro;

import java.io.PrintWriter;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.intro.config.IIntroContentProviderSite;
import org.eclipse.ui.intro.config.IIntroXHTMLContentProvider;
import org.w3c.dom.Element;

public class IntroXHTMLContentProvider implements IIntroXHTMLContentProvider {

	public void init(IIntroContentProviderSite site) {

	}

	public void createContent(String id, PrintWriter out) {
		out.println("HEllo world!");
	}

	public void createContent(String id, Composite parent, FormToolkit toolkit) {

	}

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void createContent(String id, Element parent) {

	}

}
