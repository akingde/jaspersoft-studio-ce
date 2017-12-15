/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.backward;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.backward.wizard.JRRuntimeDialog;
import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;

import net.sf.jasperreports.eclipse.builder.JRDefinition;
import net.sf.jasperreports.eclipse.builder.JasperReportCompiler;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FilePrefUtil;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlWriter;

public class JRVersionPreferencesPages extends FieldEditorOverlayPage {
	public static final String PAGE_ID = "com.jaspersoft.studio.backward.JRVersionPreferencesPages.property"; //$NON-NLS-1$
	public static final String JSS_COMPATIBILITY_SHOW_DIALOG = "com.jaspersoft.studio.compatibility.showdialog"; //$NON-NLS-1$
	public static final String JSS_COMPATIBILITY_VERSION = "com.jaspersoft.studio.compatibility.version"; //$NON-NLS-1$
	public static final String JSS_TIMESTAMP_ONSAVE = "com.jaspersoft.studio.timestamp.onsave"; //$NON-NLS-1$

	public static final String JSS_COMPATIBILITY_COMPILER_OUTPUT = "com.jaspersoft.studio.jr.compile.output"; //$NON-NLS-1$
	private ComboFieldEditor cfeVersion;

	public JRVersionPreferencesPages() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
	}

	@Override
	public void init(IWorkbench workbench) {

	}

	@Override
	protected void createFieldEditors() {
		Composite parent = getFieldEditorParent();

		Composite cmp = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		cmp.setLayout(layout);
		cmp.setLayoutData(new GridData(GridData.FILL_BOTH));

		createJRXML(cmp);
		createJASPER(cmp);
		
		//Eventually create the extensions for the page
		super.createFieldEditors();
	}

	private void fillJasperVersions() {
		String[][] v = getJasperValues();
		// set fEntryNamesAndValues
		try {
			Class<?> cver = cfeVersion.getClass().getSuperclass();
			Field f = cver.getDeclaredField("fEntryNamesAndValues"); //$NON-NLS-1$
			if (f != null) {
				f.setAccessible(true);
				String[][] old = (String[][]) f.get(cfeVersion);
				f.set(cfeVersion, v);
				f = cfeVersion.getClass().getSuperclass().getDeclaredField("fCombo"); //$NON-NLS-1$
				f.setAccessible(true);
				if (f != null) {
					Combo c = (Combo) f.get(cfeVersion);
					if (c != null) {
						String oldKey = null;
						int indx = c.getSelectionIndex();
						if (indx >= 0 && indx < old.length)
							oldKey = old[indx][1];
						c.removeAll();
						for (int i = 0; i < v.length; i++) {
							c.add(v[i][0], i);
							if (oldKey != null && v[i][1].equals(oldKey))
								c.select(i);
						}
						if (c.getSelectionIndex() < 0)
							c.select(1);
					}
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	protected String[][] getJasperValues() {
		List<JRDefinition> jrs = JRRuntimeDialog.getJRDefinitions();
		// Collections.reverse(jrs);
		String[][] v = new String[jrs.size() + 2][2];
		v[0] = new String[] { Messages.JRXmlWriterHelper_1, "last" }; //$NON-NLS-1$
		String lv = JasperDesign.class.getPackage().getImplementationVersion();
		v[1] = new String[] { lv + Messages.JRVersionPreferencesPages_3, lv };
		int i = 2;
		for (JRDefinition jrd : jrs) {
			v[i][0] = jrd.getVersion();
			v[i][1] = jrd.getVersion();
			++i;
		}
		return v;
	}

	protected void createJASPER(Composite parent) {
		final Group gr = new Group(parent, SWT.NONE);
		gr.setText(Messages.JRVersionPreferencesPages_4);
		gr.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		gr.setBackgroundMode(SWT.INHERIT_FORCE);

		cfeVersion = new ComboFieldEditor(JasperReportCompiler.JSS_COMPATIBILITY_COMPILER_VERSION,
				Messages.StudioPreferencePage_versionLabel, getJasperValues(), gr) {
			@Override
			protected void doStore() {
				String oldValue = getPreferenceStore().getString(JasperReportCompiler.JSS_COMPATIBILITY_COMPILER_VERSION);
				super.doStore();
				String newValue = getPreferenceStore().getString(JasperReportCompiler.JSS_COMPATIBILITY_COMPILER_VERSION);
				if ((oldValue == null && newValue == null) || (oldValue != null && oldValue.equals(newValue)))
					return;
				Job job = new Job("Cleaning project") {
					protected IStatus run(IProgressMonitor monitor) {
						monitor.beginTask("Cleaning project", IProgressMonitor.UNKNOWN);
						IResource r = getResource();
						if (r instanceof IFile)
							r = r.getProject();
						if (r instanceof IProject)
							buildProject((IProject) r, monitor);
						else
							for (IProject p : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
								if (p.isOpen())
									buildProject(p, monitor);
							}
						return Status.OK_STATUS;
					}

					private void buildProject(IProject p, IProgressMonitor monitor) {
						try {
							p.build(IncrementalProjectBuilder.CLEAN_BUILD, monitor);
							p.build(IncrementalProjectBuilder.FULL_BUILD, monitor);
						} catch (CoreException e) {
							e.printStackTrace();
						}
					}
				};
				job.setUser(true);
				job.setPriority(Job.BUILD);
				job.schedule();

			}
		};
		addField(cfeVersion);

		new Label(gr, SWT.NONE);

		Link link = new Link(gr, SWT.NONE);
		link.setBackground(gr.getBackground());
		link.setText(Messages.JRVersionPreferencesPages_5);
		link.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		link.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JRRuntimeDialog d = new JRRuntimeDialog(UIUtils.getShell());
				if (d.open() == Dialog.OK)
					fillJasperVersions();
			}
		});

		gr.setLayout(new GridLayout(2, false));
	}

	protected void createJRXML(Composite parent) {
		final Group gr = new Group(parent, SWT.NONE);
		gr.setText(Messages.JRVersionPreferencesPages_6);
		gr.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		addField(new ComboFieldEditor(JSS_COMPATIBILITY_VERSION, Messages.StudioPreferencePage_versionLabel,
				JRXmlWriterHelper.getVersions(), gr));

		addField(new BooleanFieldEditor(JSS_COMPATIBILITY_SHOW_DIALOG, Messages.StudioPreferencePage_showCompDialog, gr));

		Label label = new Label(gr, SWT.WRAP);
		label.setText(Messages.StudioPreferencePage_message1);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);

		label = new Label(gr, SWT.WRAP);
		label.setText(Messages.StudioPreferencePage_message2);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);

		Label separator = new Label(gr, SWT.SEPARATOR | SWT.HORIZONTAL);
		separator.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));

		addField(new BooleanFieldEditor(JSS_TIMESTAMP_ONSAVE, Messages.StudioPreferencePage_TimestampOnSave, gr));
		addField(new BooleanFieldEditor(JRXmlWriter.PREFIX_EXCLUDE_PROPERTIES + "jss", //$NON-NLS-1$
				Messages.StudioPreferencePage_1, gr) {
			private Properties props;

			@Override
			protected void doStore() {
				boolean b = getBooleanValue();
				boolean exists = false;
				Properties newProps = new Properties();
				String pvalue = "com\\.jaspersoft\\.studio\\..*"; //$NON-NLS-1$
				for (Object item : props.keySet()) {
					String key = (String) item;
					String value = null;
					if (!exists && key.equals(getPreferenceName())) {
						if (b) {
							value = pvalue;
							exists = true;
						} else
							continue;
					} else
						value = props.getProperty(key);
					newProps.setProperty(key, value);
				}
				if (b && !exists)
					newProps.setProperty(getPreferenceName(), pvalue);
				getPreferenceStore().setValue(FilePrefUtil.NET_SF_JASPERREPORTS_JRPROPERTIES,
						FileUtils.getPropertyAsString(newProps));
			}

			@Override
			protected void doLoad() {
				Button checkBox = getChangeControl(gr);
				if (checkBox != null) {
					try {
						props = FileUtils.load(getPreferenceStore().getString(FilePrefUtil.NET_SF_JASPERREPORTS_JRPROPERTIES));
						String value = props.getProperty(getPreferenceName());
						boolean b = !Misc.isNullOrEmpty(value);
						checkBox.setSelection(b);
						setWasSelected(b);
					} catch (IOException e) {
						UIUtils.showError(e);
					}
				}
			}

			private void setWasSelected(boolean value) {
				try {
					Field field = super.getClass().getSuperclass().getDeclaredField("wasSelected"); //$NON-NLS-1$
					field.setAccessible(true);
					field.setBoolean(this, value);
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}

			@Override
			protected void doLoadDefault() {
				Button checkBox = getChangeControl(gr);
				if (checkBox != null) {
					checkBox.setSelection(false);
					setWasSelected(false);
				}
			}
		});
		gr.setLayout(new GridLayout(2, false));
	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(JSS_COMPATIBILITY_SHOW_DIALOG, "false"); //$NON-NLS-1$
		store.setDefault(JSS_COMPATIBILITY_VERSION, "last"); //$NON-NLS-1$
		store.setDefault(JasperReportCompiler.JSS_COMPATIBILITY_COMPILER_VERSION, "jrxml"); //$NON-NLS-1$
		store.setDefault(JSS_TIMESTAMP_ONSAVE, "false");
	}

	@Override
	public String getPageId() {
		return PAGE_ID;
	}
}
