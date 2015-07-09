/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.preferences;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.util.Util;
import org.eclipse.osgi.service.datalocation.Location;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.osgi.framework.Bundle;

import com.jaspersoft.studio.ConfigurationManager;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.Misc;

public class GlobalPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	public static final String LOG_FILE = "com.jaspersoft.studio.log.file";
	public static final String LOG_ENABLE = "com.jaspersoft.studio.log.enable";
	public static final String JSS_JETTY_PORT = "com.jaspersoft.studio.jetty.port"; //$NON-NLS-1$
	public static final String JSS_USE_SECURE_STORAGE = "com.jaspersoft.studio.secure.storage"; //$NON-NLS-1$
	public static final String JSS_ENABLE_INTERNAL_CONSOLE = "com.jaspersoft.studio.jss.console"; //$NON-NLS-1$
	public static final String JSS_USE_ALWAYS_EXTERNAL_BROWSER = "com.jaspersoft.studio.jss.browser.external"; //$NON-NLS-1$
	private BooleanFieldEditor enableLoggers;
	private FileFieldEditor logFile;

	private static File fini;
	private static File defaultLogProperties;

	private Text tLogPreview;

	public GlobalPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		initVars();
	}

	/**
	 * Where the path is cached
	 */
	private static String cachedPath = null;

	/**
	 * Get the path and cache it values
	 */
	private static void intializePath() {
		String path = null;
		Location configArea = Platform.getInstallLocation();
		if (configArea != null) {
			String product = Platform.getProduct().getProperty("appName"); //$NON-NLS-1$ 
			path = configArea.getURL().toExternalForm();
			if (Util.isMac())
				path += product + ".app/Contents/MacOS/";
			path += product + ".ini"; //$NON-NLS-1$
		}
		cachedPath = path;
	}

	/**
	 * 
	 * Return the path of the configuration file and cache it. Typically this file is inside the install location of the
	 * application
	 * 
	 * @return String represented a Path in URL format to the configuration file
	 */
	public static String getInstallationPath() {
		if (cachedPath == null)
			intializePath();
		return cachedPath;
	}

	private static void initVars() {
		System.out.println("INITVARS");
		if (fini == null) {
			try {
				fini = new File(new URI(getInstallationPath()));
				System.out.println("Fini: " + fini.toString());
				defaultLogProperties = new File(fini.getParent(), "log.properties");
				if (!getDefultLogProperties().exists()) {
					getDefultLogProperties().getParentFile().mkdirs();
					getDefultLogProperties().createNewFile();
					File tmp = getTemplate();
					if (tmp != null)
						try {
							FileUtils.copyFile(tmp, getDefultLogProperties());
						} catch (IOException e) {
							e.printStackTrace();
							return;
						}
				}
				System.out.println("DP: " + getDefultLogProperties().toString() + " " + getDefultLogProperties().exists());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		createNoteComposite(getFont(), parent, "", Messages.GlobalPreferencePage_NoteMsg); //$NON-NLS-1$
		return contents;
	}

	@Override
	protected void createFieldEditors() {
		Label lbl = new Label(getFieldEditorParent(), SWT.NONE);
		lbl.setText(Messages.GlobalPreferencePage_jettyServerTitle);
		lbl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));

		IntegerFieldEditor port = new IntegerFieldEditor(JSS_JETTY_PORT, Messages.GlobalPreferencePage_port,
				getFieldEditorParent());
		port.setValidRange(0, 49151);
		addField(port);

		Label separator = new Label(getFieldEditorParent(), SWT.SEPARATOR | SWT.HORIZONTAL);
		separator.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));

		Label browserTitle = new Label(getFieldEditorParent(), SWT.NONE);
		browserTitle.setText(Messages.GlobalPreferencePage_EmbeddedBrowserSection);
		browserTitle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));

		BooleanFieldEditor useAlwaysExternalBrowser = new BooleanFieldEditor(JSS_USE_ALWAYS_EXTERNAL_BROWSER,
				Messages.GlobalPreferencePage_UseExternalBrowserCheckbox, getFieldEditorParent());
		addField(useAlwaysExternalBrowser);

		Label separator2 = new Label(getFieldEditorParent(), SWT.SEPARATOR | SWT.HORIZONTAL);
		separator2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));

		Label securityTitle = new Label(getFieldEditorParent(), SWT.NONE);
		securityTitle.setText(Messages.GlobalPreferencePage_title);
		securityTitle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));

		BooleanFieldEditor useSecStorage = new BooleanFieldEditor(JSS_USE_SECURE_STORAGE,
				Messages.GlobalPreferencePage_flagDescription, getFieldEditorParent());
		addField(useSecStorage);

		Label separator3 = new Label(getFieldEditorParent(), SWT.SEPARATOR | SWT.HORIZONTAL);
		separator3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));

		Label debuggingTitle = new Label(getFieldEditorParent(), SWT.NONE);
		debuggingTitle.setText(Messages.GlobalPreferencePage_LoggingPrefs);
		debuggingTitle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));

		BooleanFieldEditor enableJSSConsole = new BooleanFieldEditor(JSS_ENABLE_INTERNAL_CONSOLE,
				Messages.GlobalPreferencePage_JSSConsoleFieldLabel, getFieldEditorParent());
		enableJSSConsole.getDescriptionControl(getFieldEditorParent()).setToolTipText(
				Messages.GlobalPreferencePage_JSSConsoleFieldTooltip);
		addField(enableJSSConsole);

		enableLoggers = new BooleanFieldEditor(LOG_ENABLE, "Enable Loggers", getFieldEditorParent()) {
			/*
			 * (non-Javadoc) Method declared on FieldEditor.
			 */
			protected void doStore() {
				super.doStore();
				String cfg = null;
				if (getBooleanValue()) {
					String fname = logFile.getStringValue();
					if (Misc.isNullOrEmpty(fname)) {
						fname = getDefultLogProperties().toString();
						File tmp = getTemplate();
						if (tmp != null)
							try {
								FileUtils.copyFile(tmp, new File(fname));
							} catch (IOException e) {
								e.printStackTrace();
								return;
							}
					}
					cfg = ConfigurationManager.buildCommandLineVMarg("-Djava.util.logging.config.file", fname);
				} else
					cfg = ConfigurationManager.buildCommandLineVMarg("-Djava.util.logging.config.file", null);
				try {
					if (fini.exists())
						FileUtils.copyFile(fini, new File(fini.toString() + ".bak"));
					// create a backup first
					if (!fini.exists()) {
						fini.getParentFile().mkdirs();
						fini.createNewFile();
					}
					FileUtils.writeStringToFile(fini, cfg);
				} catch (IOException e) {
					UIUtils.showError(e);
				}
			}
		};
		enableLoggers.getDescriptionControl(getFieldEditorParent()).setToolTipText("Enable logging to file.");
		addField(enableLoggers);

		logFile = new FileFieldEditor(LOG_FILE, "Log File", getFieldEditorParent());
		addField(logFile);
		logFile.getTextControl(getFieldEditorParent()).addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				showLogFile();
			}
		});

		tLogPreview = new Text(getFieldEditorParent(), SWT.BORDER | SWT.MULTI | SWT.WRAP);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		tLogPreview.setLayoutData(gd);

		showLogFile();
		enableLogging(getPreferenceStore().getBoolean(LOG_ENABLE));
	}

	public void showLogFile() {
		if (enableLoggers.getBooleanValue()) {
			String lfile = getPreferenceStore().getString(LOG_FILE);
			if (Misc.isNullOrEmpty(lfile)) {
				lfile = getDefultLogProperties().toString();
				getPreferenceStore().putValue(LOG_FILE, lfile);
			}
			if (!Misc.isNullOrEmpty(lfile)) {
				try {
					File file = new File(lfile);
					if (file.exists())
						tLogPreview.setText(FileUtils.readFileToString(file));
					else if (lfile.equals(getDefultLogProperties().toString())) {
						File tmp = getTemplate();
						if (tmp != null)
							tLogPreview.setText(FileUtils.readFileToString(tmp));
					} else
						tLogPreview.setText("File Not Found");
				} catch (IOException e) {
					tLogPreview.setText(e.getLocalizedMessage() + "\n" + e.toString());
					e.printStackTrace();
				}
			}
		} else
			tLogPreview.setText("");
	}

	private static File getDefultLogProperties() {
		if (defaultLogProperties == null)
			initVars();
		return defaultLogProperties;
	}

	private static File template;

	private static File getTemplate() {
		if (template == null) {
			Bundle bundle = JaspersoftStudioPlugin.getInstance().getBundle();
			URL fileURL = bundle.getEntry("resources/log.properties");
			try {
				template = new File(FileLocator.resolve(fileURL).toURI());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return template;

	}

	public void enableLogging(boolean enable) {
		logFile.setEnabled(enable, getFieldEditorParent());
		tLogPreview.setEnabled(enable);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		super.propertyChange(event);
		if (event.getProperty().equals("field_editor_value")) { //$NON-NLS-1$
			if (event.getSource() == enableLoggers) {
				enableLogging((Boolean) event.getNewValue());
				showLogFile();
			} else if (event.getSource() == logFile)
				showLogFile();
		}
	}

	public static void getDefaults(IPreferenceStore store) {
		initVars();
		store.setDefault(JSS_JETTY_PORT, 0);
		store.setDefault(JSS_USE_SECURE_STORAGE, false);
		store.setDefault(JSS_ENABLE_INTERNAL_CONSOLE, false);
		store.setDefault(JSS_USE_ALWAYS_EXTERNAL_BROWSER, false);
		store.setDefault(LOG_ENABLE, false);
	}

	@Override
	public void init(IWorkbench workbench) {

	}

}
