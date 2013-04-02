package com.jaspersoft.studio.editor.preview.view.report.html;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.preview.view.report.IURLViewable;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ABrowserViewer extends APreview implements IURLViewable {
	protected Browser browser;

	public ABrowserViewer(Composite parent, JasperReportsConfiguration jContext) {
		super(parent, jContext);
	}

	@Override
	protected Control createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		composite.setLayout(layout);
		try {
			browser = new Browser(composite, SWT.NONE);
			browser.setLayoutData(new GridData(GridData.FILL_BOTH));
			browser.setJavascriptEnabled(true);
		} catch (Error e) {
			e.printStackTrace();
		}
		return composite;
	}

	@Override
	public void contribute2ToolBar(IToolBarManager tmanager) {
		tmanager.add(new URLContributionItem(url));
		tmanager.add(new Action("", JaspersoftStudioPlugin.getInstance().getImageDescriptor(
				JaspersoftStudioPlugin.ICONS_RESOURCES_REFRESH_16_PNG)) {
			@Override
			public void run() {
				browser.refresh();
			}
		});
	}

	protected String url;

	public void setURL(String url) throws Exception {
		this.url = url;
		browser.setUrl(url);
	}
}
