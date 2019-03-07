/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data;

import java.io.File;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationUpdater;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.messages.Messages;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataFile;
import net.sf.jasperreports.data.FileDataAdapter;
import net.sf.jasperreports.data.StandardRepositoryDataLocation;
import net.sf.jasperreports.data.http.StandardHttpDataLocation;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.ui.validator.NotEmptyFileValidator;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JasperReportsContext;

public abstract class AFileDataAdapterComposite extends ADataAdapterComposite {

	protected Text textFileName;

	private Button btnBrowse;

	public AFileDataAdapterComposite(Composite parent, int style, JasperReportsContext jrContext) {
		super(parent, style, jrContext);
	}

	protected void createFileNameWidgets(Composite parent) {
		Label lblNewLabel = new Label(parent, SWT.NONE);
		lblNewLabel.setText(Messages.XLSXDataAdapterComposite_0);
		lblNewLabel.setToolTipText(Messages.AFileDataAdapterComposite_0);

		textFileName = new Text(parent, SWT.BORDER);
		GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd.horizontalIndent = 8;
		gd.widthHint = 500;
		textFileName.setLayoutData(gd);
		textFileName.setToolTipText(Messages.AFileDataAdapterComposite_0);
		textFileName.addModifyListener(e -> {
			String tt = textFileName.getText();
			if (!tt.isEmpty())
				tt += "\n\n";
			tt += Messages.AFileDataAdapterComposite_0;
			textFileName.setToolTipText(tt);
		});

		btnBrowse = new Button(parent, SWT.PUSH);
		GridData gd_btnBrowse = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnBrowse.widthHint = 100;
		btnBrowse.setLayoutData(gd_btnBrowse);
		btnBrowse.setText(Messages.AFileDataAdapterComposite_2);
		/*
		 * UI ELEMENTS LISTENERS
		 */
		// browse and select the Excel file
		btnBrowse.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (textFileName.getText().matches("^(?i)(https?)://.*$")) { //$NON-NLS-1$
					FileDataAdapter fda = getFileDataAdapter();
					DataFile dataFile = fda.getDataFile();
					HttpParametersDialog d = new HttpParametersDialog(getShell(),
							(StandardHttpDataLocation) dataFile.clone());
					if (d.open() == Dialog.OK) {
						fda.setDataFile(d.getDataFile());
						fireFileChanged(!Misc.isNullOrEmpty(((StandardHttpDataLocation) dataFile).getUrl()));

					}
				} else {
					String old = textFileName.getText();
					IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
					FileDialog fd = new FileDialog(UIUtils.getShell());
					fd.setFileName(textFileName.getText());
					fd.setFilterPath(root.getLocation().toOSString());
					fd.setFilterExtensions(getFileExtensions()); // $NON-NLS-1$
																	// //$NON-NLS-2$
					String selection = fd.open();
					if (selection != null) {
						IFile contextfile = (IFile) getJrContext().getValue(FileUtils.KEY_FILE);

						IFile[] resource = root.findFilesForLocationURI(new File(selection).toURI());
						if (contextfile != null && resource != null && resource.length > 0
								&& contextfile.getProject().equals(resource[0].getProject()))
							selection = resource[0].getProjectRelativePath().toOSString();
						textFileName.setText(selection);
						if (!selection.equals(textFileName))
							fireFileChanged(!Misc.isNullOrEmpty(old));
					}
				}
			}
		});
	}

	protected void fireFileChanged(boolean showWarning) {
		pchangesuport.firePropertyChange("datafile", true, false);
	}

	protected abstract String[] getFileExtensions();

	protected void doBindFileNameWidget(DataAdapter dataAdapter) {
		NotEmptyFileValidator nefValidator = new NotEmptyFileValidator(getJrContext());
		Binding binding = bindingContext.bindValue(SWTObservables.observeText(textFileName, SWT.Modify),
				PojoObservables.observeValue(new DAProxy((FileDataAdapter) dataAdapter), "dataFile"), //$NON-NLS-1$
				new UpdateValueStrategy().setAfterConvertValidator(nefValidator), null);
		nefValidator.setBinding(binding);
		ControlDecorationSupport.create(binding, SWT.TOP | SWT.LEFT, null, new ControlDecorationUpdater());
	}

	private FileDataAdapter getFileDataAdapter() {
		return (FileDataAdapter) dataAdapterDesc.getDataAdapter();
	}

	class DAProxy {
		private FileDataAdapter da;

		public DAProxy(FileDataAdapter da) {
			this.da = da;
		}

		public void setDataFile(String str) {
			DataFile dataFile = da.getDataFile();
			if (str.matches("^(?i)(https?)://.*$")) { //$NON-NLS-1$
				btnBrowse.setText(Messages.AFileDataAdapterComposite_5);
				DataFile dl = da.getDataFile();
				if (dataFile == null || !(dataFile instanceof StandardHttpDataLocation)) {
					dl = new StandardHttpDataLocation();
					da.setDataFile(dl);
				}
				((StandardHttpDataLocation) dl).setUrl(str);
			} else {
				btnBrowse.setText(Messages.AFileDataAdapterComposite_2);
				DataFile dl = da.getDataFile();
				if (dataFile == null || !(dataFile instanceof StandardRepositoryDataLocation)) {
					dl = new StandardRepositoryDataLocation();
					da.setDataFile(dl);
				}
				((StandardRepositoryDataLocation) dl).setLocation(str);
			}
			btnBrowse.getParent().layout();
		}

		public String getDataFile() {
			DataFile df = da.getDataFile();
			if (df instanceof StandardRepositoryDataLocation) {
				btnBrowse.setText(Messages.AFileDataAdapterComposite_2);
				return ((StandardRepositoryDataLocation) df).getLocation();
			}
			if (df instanceof StandardHttpDataLocation) {
				btnBrowse.setText(Messages.AFileDataAdapterComposite_5);
				return ((StandardHttpDataLocation) df).getUrl();
			}
			return ""; //$NON-NLS-1$
		}
	}

}
