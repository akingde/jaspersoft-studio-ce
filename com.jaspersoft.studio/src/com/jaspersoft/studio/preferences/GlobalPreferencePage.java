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
import java.net.URL;
import java.util.logging.LogManager;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.apache.commons.io.FileUtils;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
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

import com.jaspersoft.studio.ConfigurationManager;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.util.KeyValue;
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

	private static void initVars() {
		if (fini == null) {
			try {
				fini = ConfigurationManager.getApplicationConfigurationFile();
				System.out.println("Fini: " + fini.toString());
				defaultLogProperties = new File(ConfigurationManager.getAppDataFolder("config"), "log.properties");
				initDefaultLogProperties();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void initDefaultLogProperties() throws IOException {
		if (!defaultLogProperties.exists()) {
			defaultLogProperties.getParentFile().mkdirs();
			defaultLogProperties.createNewFile();
			File tmp = getTemplate();
			if (tmp != null)
				try {
					FileUtils.copyFile(tmp, getDefaultLogProperties());
				} catch (IOException e) {
					UIUtils.showError(e);
					return;
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
		lbl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1));

		IntegerFieldEditor port = new IntegerFieldEditor(JSS_JETTY_PORT, Messages.GlobalPreferencePage_port,
				getFieldEditorParent());
		port.setValidRange(0, 49151);
		addField(port);

		Label separator = new Label(getFieldEditorParent(), SWT.SEPARATOR | SWT.HORIZONTAL);
		separator.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1));

		Label browserTitle = new Label(getFieldEditorParent(), SWT.NONE);
		browserTitle.setText(Messages.GlobalPreferencePage_EmbeddedBrowserSection);
		browserTitle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1));

		BooleanFieldEditor useAlwaysExternalBrowser = new BooleanFieldEditor(JSS_USE_ALWAYS_EXTERNAL_BROWSER,
				Messages.GlobalPreferencePage_UseExternalBrowserCheckbox, getFieldEditorParent());
		addField(useAlwaysExternalBrowser);

		Label separator2 = new Label(getFieldEditorParent(), SWT.SEPARATOR | SWT.HORIZONTAL);
		separator2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1));

		Label securityTitle = new Label(getFieldEditorParent(), SWT.NONE);
		securityTitle.setText(Messages.GlobalPreferencePage_title);
		securityTitle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1));

		BooleanFieldEditor useSecStorage = new BooleanFieldEditor(JSS_USE_SECURE_STORAGE,
				Messages.GlobalPreferencePage_flagDescription, getFieldEditorParent());
		addField(useSecStorage);

		Label separator3 = new Label(getFieldEditorParent(), SWT.SEPARATOR | SWT.HORIZONTAL);
		separator3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1));

		Label debuggingTitle = new Label(getFieldEditorParent(), SWT.NONE);
		debuggingTitle.setText(Messages.GlobalPreferencePage_LoggingPrefs);
		debuggingTitle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1));

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
						fname = getDefaultLogProperties().toString();
						File tmp = getTemplate();
						if (tmp != null)
							try {
								FileUtils.copyFile(tmp, new File(fname));
							} catch (IOException e) {
								e.printStackTrace();
								return;
							}
					}
					KeyValue<String, String>[] kv = new KeyValue[3];
					kv[0] = new KeyValue<String, String>("-Djava.util.logging.config.file", fname);
					kv[1] = new KeyValue<String, String>("-Dorg.apache.commons.logging.diagnostics.dest", "/tmp/studio-common.log");
					kv[2] = new KeyValue<String, String>("-Dorg.apache.commons.logging.Log",
							"org.apache.commons.logging.impl.Jdk14Logger");

					cfg = ConfigurationManager.buildCommandLineVMarg(kv);
					try {
						File f = new File(fname);
						FileUtils.writeStringToFile(f, tLogPreview.getText());
						LogManager.getLogManager().readConfiguration(FileUtils.openInputStream(f));
					} catch (SecurityException e) {
						UIUtils.showError(e);
					} catch (IOException e) {
						UIUtils.showError(e);
					}
				} else {
					LogManager.getLogManager().reset();

					getPreferenceStore().putValue(LOG_FILE, getPreferenceStore().getDefaultString(LOG_FILE));
					KeyValue<String, String>[] kv = new KeyValue[3];
					kv[0] = new KeyValue<String, String>("-Djava.util.logging.config.file", null);
					kv[1] = new KeyValue<String, String>("-Dorg.apache.commons.logging.diagnostics.dest", null);
					kv[2] = new KeyValue<String, String>("-Dorg.apache.commons.logging.Log", null);

					cfg = ConfigurationManager.buildCommandLineVMarg(kv);
				}
				ConfigurationManager.writeConfigurationFile(cfg);
			}
		};
		enableLoggers.getDescriptionControl(getFieldEditorParent()).setToolTipText("Enable logging to file.");
		addField(enableLoggers);

		logFile = new FileFieldEditor(LOG_FILE, "Log File", getFieldEditorParent());
		addField(logFile);
		logFile.getTextControl(getFieldEditorParent()).addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if (!refresh)
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
		if (refresh)
			return;
		refresh = true;
		try {
			if (enableLoggers.getBooleanValue()) {
				String lfile = getPreferenceStore().getString(LOG_FILE);
				if (Misc.isNullOrEmpty(lfile)) {
					lfile = getDefaultLogProperties().toString();
					getPreferenceStore().putValue(LOG_FILE, lfile);
				}
				logFile.load();
				if (!Misc.isNullOrEmpty(lfile)) {
					try {
						File file = new File(lfile);
						if (file.exists())
							tLogPreview.setText(FileUtils.readFileToString(file));
						else if (lfile.equals(getDefaultLogProperties().toString())) {
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
		} finally {
			refresh = false;
		}
	}

	private static File getDefaultLogProperties() {
		if (defaultLogProperties == null)
			initVars();
		if (defaultLogProperties == null) {
			String homeDir = System.getProperty("user.home");
			defaultLogProperties = new File(homeDir, "log.properties");
			if (!defaultLogProperties.exists())
				try {
					initDefaultLogProperties();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return defaultLogProperties;
	}

	private static File template;

	private static File getTemplate() {
		if (template == null) {
			try {
				String path = JaspersoftStudioPlugin.getInstance().getFileLocation("/resources/log.properties");
				if (!Misc.isNullOrEmpty(path)) {
					template = FileUtils.toFile(new URL("file://" + path));

					// Bundle bundle = JaspersoftStudioPlugin.getInstance().getBundle();
					// template = bundle.getDataFile("resources/log.properties");
				}
			} catch (IOException e) {
				UIUtils.showError(e);
			}
		}
		return template;

	}

	public void enableLogging(boolean enable) {
		logFile.setEnabled(enable, getFieldEditorParent());
		tLogPreview.setEnabled(enable);
		if (!enable) {
			IPreferenceStore pstore = getPreferenceStore();
			String d = pstore.getDefaultString(LOG_FILE);
			pstore.putValue(LOG_FILE, d);
			logFile.setStringValue(d);
		}
	}

	private boolean refresh = false;

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
