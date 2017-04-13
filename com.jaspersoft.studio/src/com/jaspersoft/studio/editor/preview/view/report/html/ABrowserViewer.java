/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.view.report.html;

import java.util.TimeZone;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.preview.view.report.IURLViewable;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.GlobalPreferencePage;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.viewer.BrowserUtils;

public class ABrowserViewer extends APreview implements IURLViewable {
	public static final String REFRESH_ACTION_ID = "com.jaspersoft.studio.browserViewer.refreshAction"; //$NON-NLS-1$
	private Browser browser;
	private String url;
	private URLContributionItem urlBar;
	private Action refreshAction;
	private StackLayout stackLayout;
	private Composite container;
	private Composite externalBrowserCmp;

	public ABrowserViewer(Composite parent, JasperReportsConfiguration jContext) {
		super(parent, jContext);
		parent.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				if (browser != null) {
					browser.dispose();
				}
			}
		});
	}

	@Override
	protected Control createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		stackLayout = new StackLayout();
		stackLayout.marginHeight = 0;
		stackLayout.marginWidth = 0;
		container.setLayout(stackLayout);
		return container;
	}

	@Override
	public void contribute2ToolBar(IToolBarManager tmanager) {
		super.contribute2ToolBar(tmanager);
		if (!useExternalBrowser()) {
			urlBar = new URLContributionItem(Misc.nvl(url, "")) { //$NON-NLS-1$
				@Override
				protected int computeWidth(Control control) {
					return Math.max(0, getUrlWidth(control));
				}
			};
			tmanager.add(urlBar);
			tmanager.add(getRefreshAction());
		}
	}

	/**
	 * Return the suggested width for the url control, considering also other controls palced after the url. In this way
	 * it is possible to attribute always at the url the maximum size available
	 * 
	 * @param control
	 *          the control of the url
	 * @return a suggested width for the url control
	 */
	protected int getUrlWidth(Control control) {
		// Add the calculation of the toolbar width depending on the available size on the parent
		// minus 40 to leave space to the refresh action
		return control.getParent().getSize().x - 40;
	}

	public void setURL(String url, String urlcookie, String scookie) throws Exception {
		updateUIForBrowser();
		this.url = Misc.nvl(url);
		if (useExternalBrowser()) {
			BrowserUtils.openExternalBrowser(url);
		} else {
			if (urlBar != null) {
				urlBar.setUrl(url);
			}
			if (browser != null) {
				browser.clearSessions();
				if (urlcookie != null && scookie != null)
					Browser.setCookie(scookie, urlcookie);
				browser.setUrl(url, null, new String[] { "Accept-Timezone" + TimeZone.getDefault().getID() });
			}
		}
	}

	public void setURL(String url) throws Exception {
		setURL(url, null, null);
	}

	public void updateUIForBrowser() {
		if (useExternalBrowser()) {
			if (externalBrowserCmp == null) {
				externalBrowserCmp = new Composite(container, SWT.NONE);
				externalBrowserCmp.setLayout(new FillLayout());
				Label messageLbl = new Label(externalBrowserCmp, SWT.NONE);
				messageLbl.setText(Messages.ABrowserViewer_ExternalBrowserPreviewMsg);
			}
			stackLayout.topControl = externalBrowserCmp;
			container.layout();
		} else {
			if (browser == null) {
				try {
					browser = BrowserUtils.getSWTBrowserWidget(container, SWT.NONE);
					browser.setLayoutData(new GridData(GridData.FILL_BOTH));
					browser.setJavascriptEnabled(true);
				} catch (Error e) {
					e.printStackTrace();
				}
			}
			stackLayout.topControl = browser;
			container.layout();
		}
	}

	/*
	 * Returns the refresh action to be added to the toolbar.
	 */
	private Action getRefreshAction() {
		if (refreshAction == null) {
			refreshAction = new Action("", JaspersoftStudioPlugin.getInstance().getImageDescriptor( //$NON-NLS-1$
					JaspersoftStudioPlugin.ICONS_RESOURCES_REFRESH_16_PNG)) {
				@Override
				public void run() {
					browser.refresh();
				}
			};
			refreshAction.setId(REFRESH_ACTION_ID);
		}
		return refreshAction;
	}

	/**
	 * Refreshes the browser if possible.
	 */
	protected void refreshBrowser() {
		updateUIForBrowser();
		if (!useExternalBrowser()) {
			browser.refresh();
		}
	}

	private boolean useExternalBrowser() {
		return JaspersoftStudioPlugin.getInstance().getPreferenceStore()
				.getBoolean(GlobalPreferencePage.JSS_USE_ALWAYS_EXTERNAL_BROWSER);
	}
}
