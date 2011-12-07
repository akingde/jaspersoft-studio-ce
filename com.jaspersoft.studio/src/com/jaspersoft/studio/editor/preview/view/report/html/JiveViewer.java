package com.jaspersoft.studio.editor.preview.view.report.html;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.preview.view.report.IURLViewable;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

public class JiveViewer extends APreview implements IURLViewable {

	public JiveViewer(Composite parent, PropertiesHelper ph) {
		super(parent, ph);
	}

	@Override
	protected Control createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		composite.setLayout(layout);

		browser = new Browser(composite, SWT.NONE);
		browser.setLayoutData(new GridData(GridData.FILL_BOTH));
		return composite;
	}

	@Override
	public void contribute2ToolBar(IToolBarManager tmanager) {
		urlcontributionbar = new URLContributionItem(browser.getUrl());
		tmanager.add(urlcontributionbar);
	}

	private Browser browser;
	private URLContributionItem urlcontributionbar;

	public void setURL(String url) throws Exception {
		browser.setUrl(url);
		if (urlcontributionbar != null)
			urlcontributionbar.setUrl(url);
	}
}