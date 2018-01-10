/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.utils.browser;

import java.io.IOException;
import java.net.InetSocketAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.launching.SocketUtil;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.editor.preview.view.report.html.ABrowserViewer;
import com.jaspersoft.studio.utils.Callback;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.HttpUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.eclipse.viewer.BrowserUtils;
import net.sf.jasperreports.engine.JRRuntimeException;

public class BrowserInfo {

	public static void initUserAgent(JasperReportsConfiguration c) {
		String userAgent = c.getProperty(HttpUtils.USER_AGENT);
		if (!Misc.isNullOrEmpty(userAgent))
			HttpUtils.USER_AGENT_JASPERSOFT_STUDIO = userAgent;
		c.getPrefStore().addPropertyChangeListener(new IPropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent event) {
				if (event.getProperty().equals(HttpUtils.USER_AGENT)) {
					String ua = (String) event.getNewValue();
					if (!Misc.isNullOrEmpty(ua))
						HttpUtils.USER_AGENT_JASPERSOFT_STUDIO = ua;
					else
						HttpUtils.USER_AGENT_JASPERSOFT_STUDIO = "JaspersoftStudio";
				}
			}
		});
	}

	public static String getUserAgent() {
		return HttpUtils.USER_AGENT_JASPERSOFT_STUDIO;
	}

	public static void findUserAgent(Callback<String> callback) {
		final int port = SocketUtil.findFreePort();
		Server server = new Server(new InetSocketAddress("localhost", port));
		try {
			server.setHandler(new AbstractHandler() {

				@Override
				public void handle(String target, Request baseRequest, HttpServletRequest request,
						HttpServletResponse response) throws IOException, ServletException {
					response.setContentType("text/html");
					response.setStatus(HttpServletResponse.SC_OK);
					final String ua = baseRequest.getHeader("User-Agent");
					response.getWriter().println(ua);
					UIUtils.getDisplay().asyncExec(() -> {
						callback.completed(ua);
					});
					baseRequest.setHandled(true);
				}
			});
			server.start();
			UIUtils.getDisplay().syncExec(() -> runBrowser("http://localhost:" + port + "/index.html"));
		} catch (Exception e) {
			throw new JRRuntimeException(e);
		} finally {
			try {
				Thread.sleep(10000);
				server.stop();
				server.destroy();
			} catch (Exception e) {
				UIUtils.showError(e);
			}
		}
	}

	protected static void runBrowser(String url) {
		Dialog d = new Dialog(UIUtils.getShell()) {

			@Override
			protected Control createDialogArea(Composite parent) {
				try {
					BrowserUtils.getSWTBrowserWidget(parent, SWT.NONE).setUrl(url);
					UIUtils.getDisplay().asyncExec(() -> {
						close();
					});
				} catch (Throwable e) {
					UIUtils.getDisplay().syncExec(() -> {
						if (ABrowserViewer.useExternalBrowser())
							BrowserUtils.openExternalBrowser(url);
						else
							BrowserUtils.openLink(url);
					});
				}
				return super.createDialogArea(parent);
			}
		};
		d.open();
	}

}
