/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.jaspersoft.studio.ConfigurationManager;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.KeyValue;
import net.sf.jasperreports.eclipse.util.Misc;

public class GlobalPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	public static final String LOG_FILE = "com.jaspersoft.studio.log.file"; //$NON-NLS-1$
	public static final String LOG4j_FILE = "com.jaspersoft.studio.log.log4jfile"; //$NON-NLS-1$
	public static final String LOG_ENABLE = "com.jaspersoft.studio.log.enable"; //$NON-NLS-1$
	public static final String JSS_JETTY_PORT = "com.jaspersoft.studio.jetty.port"; //$NON-NLS-1$
	public static final String JSS_USE_SECURE_STORAGE = "com.jaspersoft.studio.secure.storage"; //$NON-NLS-1$
	public static final String JSS_ENABLE_INTERNAL_CONSOLE = "com.jaspersoft.studio.jss.console"; //$NON-NLS-1$
	public static final String JSS_USE_ALWAYS_EXTERNAL_BROWSER = "com.jaspersoft.studio.jss.browser.external"; //$NON-NLS-1$
	private BooleanFieldEditor enableLoggers;
	private FileFieldEditor logFile;

	private static File fini;
	private static File defaultLogProperties;
	private static File defaultLog4jProperties;

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
				System.out.println("Fini: " + fini.toString()); //$NON-NLS-1$
				defaultLogProperties = new File(ConfigurationManager.getAppDataFolder("config"), "log.properties"); //$NON-NLS-1$ //$NON-NLS-2$
				defaultLog4jProperties = new File(ConfigurationManager.getAppDataFolder("config"), "log4j-config.properties"); //$NON-NLS-1$ //$NON-NLS-2$
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
		if (!defaultLog4jProperties.exists()) {
			defaultLog4jProperties.getParentFile().mkdirs();
			defaultLog4jProperties.createNewFile();
			File tmp = getLog4JTemplate();
			if (tmp != null)
				try {
					FileUtils.copyFile(tmp, getDefaultLog4jProperties());
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
		enableJSSConsole.getDescriptionControl(getFieldEditorParent())
				.setToolTipText(Messages.GlobalPreferencePage_JSSConsoleFieldTooltip);
		addField(enableJSSConsole);

		enableLoggers = new BooleanFieldEditor(LOG_ENABLE, Messages.GlobalPreferencePage_5, getFieldEditorParent()) {
			/*
			 * (non-Javadoc) Method declared on FieldEditor.
			 */
			protected void doStore() {
				super.doStore();
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
					String fLog4jName = log4jFile.getStringValue();
					if (Misc.isNullOrEmpty(fLog4jName)) {
						fLog4jName = getDefaultLog4jProperties().toString();
						File tmp = getLog4JTemplate();
						if (tmp != null)
							try {
								FileUtils.copyFile(tmp, new File(fLog4jName));
							} catch (IOException e) {
								e.printStackTrace();
								return;
							}
					}
					String javaLogPath = getJavaLogPath();
					KeyValue<String, String>[] kv = new KeyValue[javaLogPath != null ? 4 : 3];
					kv[0] = new KeyValue<String, String>("-Djava.util.logging.config.file", fname); //$NON-NLS-1$
					kv[1] = new KeyValue<String, String>("-Dlog4j.configuration", fLog4jName); //$NON-NLS-1$
					kv[2] = new KeyValue<String, String>("-Dorg.apache.commons.logging.Log", //$NON-NLS-1$
							"org.apache.commons.logging.impl.Jdk14Logger"); //$NON-NLS-1$
					if (javaLogPath != null)
						kv[3] = new KeyValue<String, String>("-Dorg.apache.commons.logging.diagnostics.dest", javaLogPath); //$NON-NLS-1$

					ConfigurationManager.changeVMArgs(kv);
					try {
						File f = new File(fname);
						FileUtils.writeStringToFile(f, tLogPreview.getText());
						LogManager.getLogManager().readConfiguration(FileUtils.openInputStream(f));
					} catch (SecurityException e) {
						UIUtils.showError(e);
					} catch (IOException e) {
						UIUtils.showError(e);
					}
					try {
						File f = new File(fLog4jName);
						FileUtils.writeStringToFile(f, tLog4jPreview.getText());
						LogManager.getLogManager().readConfiguration(FileUtils.openInputStream(f));
					} catch (SecurityException e) {
						UIUtils.showError(e);
					} catch (IOException e) {
						UIUtils.showError(e);
					}
				} else {
					LogManager.getLogManager().reset();

					getPreferenceStore().putValue(LOG_FILE, getPreferenceStore().getDefaultString(LOG_FILE));
					getPreferenceStore().putValue(LOG4j_FILE, getPreferenceStore().getDefaultString(LOG4j_FILE));
					KeyValue<String, String>[] kv = new KeyValue[4];
					kv[0] = new KeyValue<String, String>("-Djava.util.logging.config.file", null); //$NON-NLS-1$
					kv[1] = new KeyValue<String, String>("-Dorg.apache.commons.logging.diagnostics.dest", null); //$NON-NLS-1$
					kv[2] = new KeyValue<String, String>("-Dorg.apache.commons.logging.Log", null); //$NON-NLS-1$
					kv[3] = new KeyValue<String, String>("-Dlog4j.configuration", null); //$NON-NLS-1$

					ConfigurationManager.changeVMArgs(kv);
				}
				if (!getBooleanValue())
					Logger.getAnonymousLogger().info("LOGGER ENDED"); //$NON-NLS-1$
				if (getBooleanValue())
					Logger.getAnonymousLogger().info("LOGGER STARTED"); //$NON-NLS-1$
			}
		};
		enableLoggers.getDescriptionControl(getFieldEditorParent()).setToolTipText(Messages.GlobalPreferencePage_15);
		addField(enableLoggers);

		tabFolder = new CTabFolder(getFieldEditorParent(), SWT.FLAT | SWT.TOP);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		gd.widthHint = 600;
		tabFolder.setLayoutData(gd);

		createJavaLogging(tabFolder);
		createLog4jLogging(tabFolder);
		tabFolder.setSelection(0);

		showLogFile();
		showLog4jFile();
		enableLogging(getPreferenceStore().getBoolean(LOG_ENABLE));
	}

	private void createJavaLogging(CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText(Messages.GlobalPreferencePage_16);

		cmpJavaLog = new Composite(tabFolder, SWT.NONE);
		cmpJavaLog.setLayout(new GridLayout(3, false));

		logFile = new FileFieldEditor(LOG_FILE, Messages.GlobalPreferencePage_17, cmpJavaLog);
		addField(logFile);
		logFile.getTextControl(cmpJavaLog).addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if (!refresh) {
					try {
						String lfile = logFile.getTextControl(cmpJavaLog).getText();
						File file = new File(lfile);
						if (file.exists())
							tLogPreview.setText(FileUtils.readFileToString(file));
						else if (lfile.equals(getDefaultLogProperties().toString())) {
							File tmp = getTemplate();
							if (tmp != null)
								tLogPreview.setText(FileUtils.readFileToString(tmp));
						} else
							tLogPreview.setText(Messages.GlobalPreferencePage_20);
					} catch (IOException ex) {
						tLogPreview.setText(ex.getLocalizedMessage() + "\n" + ex.toString()); //$NON-NLS-1$
						ex.printStackTrace();
					}
				}
			}
		});

		tLogPreview = new Text(cmpJavaLog, SWT.BORDER | SWT.MULTI | SWT.WRAP);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		tLogPreview.setLayoutData(gd);

		bptab.setControl(cmpJavaLog);
	}

	private String getJavaLogPath() {
		try {
			Properties props = net.sf.jasperreports.eclipse.util.FileUtils.load(tLogPreview.getText());
			Object obj = props.get("java.util.logging.FileHandler.pattern"); //$NON-NLS-1$
			if (obj instanceof String)
				return (String) obj;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void createLog4jLogging(CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText(Messages.GlobalPreferencePage_19);

		cmpLog4j = new Composite(tabFolder, SWT.NONE);
		cmpLog4j.setLayout(new GridLayout(3, false));

		log4jFile = new FileFieldEditor(LOG4j_FILE, Messages.GlobalPreferencePage_0, cmpLog4j);
		addField(log4jFile);
		log4jFile.getTextControl(cmpLog4j).addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if (!refresh) {
					try {
						String lfile = log4jFile.getTextControl(cmpLog4j).getText();
						File file = new File(lfile);
						if (file.exists())
							tLog4jPreview.setText(FileUtils.readFileToString(file));
						else if (lfile.equals(getDefaultLog4jProperties().toString())) {
							File tmp = getTemplate();
							if (tmp != null)
								tLog4jPreview.setText(FileUtils.readFileToString(tmp));
						} else
							tLog4jPreview.setText(Messages.GlobalPreferencePage_20);
					} catch (IOException ex) {
						tLog4jPreview.setText(ex.getLocalizedMessage() + "\n" + ex.toString()); //$NON-NLS-1$
						ex.printStackTrace();
					}
				}
			}
		});

		tLog4jPreview = new Text(cmpLog4j, SWT.BORDER | SWT.MULTI | SWT.WRAP);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		tLog4jPreview.setLayoutData(gd);

		bptab.setControl(cmpLog4j);
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
							tLogPreview.setText(Messages.GlobalPreferencePage_20);
					} catch (IOException e) {
						tLogPreview.setText(e.getLocalizedMessage() + "\n" + e.toString()); //$NON-NLS-1$
						e.printStackTrace();
					}
				}
			} else
				tLogPreview.setText(""); //$NON-NLS-1$
		} finally {
			refresh = false;
		}
	}

	public void showLog4jFile() {
		if (refresh)
			return;
		refresh = true;
		try {
			if (enableLoggers.getBooleanValue()) {
				String lfile = getPreferenceStore().getString(LOG4j_FILE);
				if (Misc.isNullOrEmpty(lfile)) {
					lfile = getDefaultLog4jProperties().toString();
					getPreferenceStore().putValue(LOG4j_FILE, lfile);
				}
				log4jFile.load();
				if (!Misc.isNullOrEmpty(lfile)) {
					try {
						File file = new File(lfile);
						if (file.exists())
							tLog4jPreview.setText(FileUtils.readFileToString(file));
						else if (lfile.equals(getDefaultLog4jProperties().toString())) {
							File tmp = getTemplate();
							if (tmp != null)
								tLog4jPreview.setText(FileUtils.readFileToString(tmp));
						} else
							tLog4jPreview.setText(Messages.GlobalPreferencePage_20);
					} catch (IOException e) {
						tLog4jPreview.setText(e.getLocalizedMessage() + "\n" + e.toString()); //$NON-NLS-1$
						e.printStackTrace();
					}
				}
			} else
				tLog4jPreview.setText(""); //$NON-NLS-1$
		} finally {
			refresh = false;
		}
	}

	private static File getDefaultLogProperties() {
		if (defaultLogProperties == null)
			initVars();
		if (defaultLogProperties == null) {
			String homeDir = System.getProperty("user.home"); //$NON-NLS-1$
			defaultLogProperties = new File(homeDir, "log.properties"); //$NON-NLS-1$
			if (!defaultLogProperties.exists())
				try {
					initDefaultLogProperties();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return defaultLogProperties;
	}

	private static File getDefaultLog4jProperties() {
		if (defaultLog4jProperties == null)
			initVars();
		if (defaultLog4jProperties == null) {
			String homeDir = System.getProperty("user.home"); //$NON-NLS-1$
			defaultLog4jProperties = new File(homeDir, "log4j-config.properties"); //$NON-NLS-1$
			if (!defaultLog4jProperties.exists())
				try {
					initDefaultLogProperties();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return defaultLog4jProperties;
	}

	private static File template;

	private static File getTemplate() {
		if (template == null) {
			try {
				String path = JaspersoftStudioPlugin.getInstance().getFileLocation("/resources/log.properties"); //$NON-NLS-1$
				if (!Misc.isNullOrEmpty(path)) {
					template = FileUtils.toFile(new URL("file://" + path)); //$NON-NLS-1$

					// Bundle bundle = JaspersoftStudioPlugin.getInstance().getBundle();
					// template = bundle.getDataFile("resources/log.properties");
				}
			} catch (IOException e) {
				UIUtils.showError(e);
			}
		}
		return template;
	}

	private static File log4jTemplate;

	private static File getLog4JTemplate() {
		if (log4jTemplate == null) {
			try {
				String path = JaspersoftStudioPlugin.getInstance().getFileLocation("/resources/log4j-config.properties"); //$NON-NLS-1$
				if (!Misc.isNullOrEmpty(path)) {
					log4jTemplate = FileUtils.toFile(new URL("file://" + path)); //$NON-NLS-1$

					// Bundle bundle = JaspersoftStudioPlugin.getInstance().getBundle();
					// template = bundle.getDataFile("resources/log.properties");
				}
			} catch (IOException e) {
				UIUtils.showError(e);
			}
		}
		return log4jTemplate;
	}

	public void enableLogging(boolean enable) {
		tabFolder.setEnabled(enable);
		logFile.setEnabled(enable, cmpJavaLog);
		log4jFile.setEnabled(enable, cmpLog4j);
		tLogPreview.setEnabled(enable);
		tLog4jPreview.setEnabled(enable);
		if (!enable) {
			IPreferenceStore pstore = getPreferenceStore();
			String d = pstore.getDefaultString(LOG_FILE);
			pstore.putValue(LOG_FILE, d);
			logFile.setStringValue(d);

			d = pstore.getDefaultString(LOG4j_FILE);
			pstore.putValue(LOG4j_FILE, d);
			log4jFile.setStringValue(d);
		}
	}

	private boolean refresh = false;
	private Composite cmpJavaLog;
	private FileFieldEditor log4jFile;
	private Text tLog4jPreview;
	private Composite cmpLog4j;
	private CTabFolder tabFolder;

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		super.propertyChange(event);
		if (event.getProperty().equals("field_editor_value")) { //$NON-NLS-1$
			if (event.getSource() == enableLoggers) {
				enableLogging((Boolean) event.getNewValue());
				showLogFile();
				showLog4jFile();
			} else if (event.getSource() == logFile)
				;// showLogFile();
			else if (event.getSource() == log4jFile)
				;// showLog4jFile();
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
