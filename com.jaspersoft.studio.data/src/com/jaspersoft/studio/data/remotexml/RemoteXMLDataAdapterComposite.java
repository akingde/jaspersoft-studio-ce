
package com.jaspersoft.studio.data.remotexml;

import java.util.Locale;
import java.util.TimeZone;

import net.sf.jasperreports.data.xml.RemoteXmlDataAdapter;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.jface.dialogs.LocaleDialog;
import com.jaspersoft.studio.jface.dialogs.TimeZoneDialog;
import com.jaspersoft.studio.property.descriptor.pattern.dialog.PatternEditor;
import com.jaspersoft.studio.utils.Misc;

public class RemoteXMLDataAdapterComposite extends Composite {

	private RemoteXMLDataAdapterDescriptor remoteXmlDataAdapterDesc = null;
	private Text textFileUrl;
	private Text textDatePattern;
	private Text textNumberPattern;
	private Text textLocale;
	private Text textTimeZone;
	private Locale locale = null;
	private TimeZone timeZone = null;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RemoteXMLDataAdapterComposite(Composite parent, int style) {
		
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("XML URL:");
		
		textFileUrl = new Text(composite, SWT.BORDER);
		textFileUrl.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		composite_1.setLayout(new FillLayout(SWT.VERTICAL));
		
		@SuppressWarnings("unused")
		Label separator = new Label(composite_1, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		Composite composite_3 = new Composite(this, SWT.NONE);
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		GridLayout gl_composite_3 = new GridLayout(3, false);
		gl_composite_3.marginWidth = 0;
		gl_composite_3.marginHeight = 0;
		composite_3.setLayout(gl_composite_3);
		
		Label lblNewLabel_2 = new Label(composite_3, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_2.setText("Date pattern" + " :");
		
		textDatePattern = new Text(composite_3, SWT.BORDER);
		textDatePattern.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnCreateDatePattern = new Button(composite_3, SWT.NONE);
		GridData gd_btnCreateDatePattern = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnCreateDatePattern.widthHint = 100;
		btnCreateDatePattern.setLayoutData(gd_btnCreateDatePattern);
		btnCreateDatePattern.setText("Create");
		
		Label lblNewLabel_3 = new Label(composite_3, SWT.NONE);
		lblNewLabel_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_3.setText("Number pattern" + " :");
		
		textNumberPattern = new Text(composite_3, SWT.BORDER);
		textNumberPattern.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnCreateNumberPattern = new Button(composite_3, SWT.NONE);
		GridData gd_btnCreateNumberPattern = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnCreateNumberPattern.widthHint = 100;
		btnCreateNumberPattern.setLayoutData(gd_btnCreateNumberPattern);
		btnCreateNumberPattern.setText("Create");
		
		Group grpLocaleTime = new Group(this, SWT.SHADOW_NONE);
		grpLocaleTime.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		grpLocaleTime.setText("Locale" + " / " + "Time zone");
		GridLayout gl_grpLocaleTime = new GridLayout(3, false);
		gl_grpLocaleTime.marginWidth = 0;
		gl_grpLocaleTime.marginHeight = 0;
		grpLocaleTime.setLayout(gl_grpLocaleTime);
		
		Label lblNewLabel_4 = new Label(grpLocaleTime, SWT.NONE);
		lblNewLabel_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_4.setText("Locale" + " :");
		
		textLocale = new Text(grpLocaleTime, SWT.BORDER);
		textLocale.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textLocale.setEnabled(false);
		
		Button btnSelectLocale = new Button(grpLocaleTime, SWT.NONE);
		GridData gd_btnSelectLocale = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnSelectLocale.widthHint = 100;
		btnSelectLocale.setLayoutData(gd_btnSelectLocale);
		btnSelectLocale.setText("Select" + "...");
		
		Label lblNewLabel_5 = new Label(grpLocaleTime, SWT.NONE);
		lblNewLabel_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_5.setText("Time zone" + " :");
		
		textTimeZone = new Text(grpLocaleTime, SWT.BORDER);
		textTimeZone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textTimeZone.setEnabled(false);
		
		Button btnSelectTimeZone = new Button(grpLocaleTime, SWT.NONE);
		GridData gd_btnSelectTimeZone = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnSelectTimeZone.widthHint = 100;
		btnSelectTimeZone.setLayoutData(gd_btnSelectTimeZone);
		btnSelectTimeZone.setText("Select" + "...");

		// UI elements listener
		
		btnCreateDatePattern.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PatternEditor wizard = new PatternEditor();
				wizard.setNumberPatterns(false);
				wizard.setValue(textDatePattern.getText());
				WizardDialog dialog = new WizardDialog(getShell(), wizard);
				dialog.create();

				if (dialog.open() == Dialog.OK) {
					String val = wizard.getValue();
					textDatePattern.setText(val);
				}
			}
		});
		
		btnCreateNumberPattern.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PatternEditor wizard = new PatternEditor();
				wizard.setDatePatterns(false);
				wizard.setValue(textNumberPattern.getText());
				WizardDialog dialog = new WizardDialog(getShell(), wizard);
				dialog.create();

				if (dialog.open() == Dialog.OK) {
					String val = wizard.getValue();
					textNumberPattern.setText(val);
				}
			}
		});
		
		btnSelectLocale.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LocaleDialog localeDialog = new LocaleDialog(getShell(), locale);
				localeDialog.create();
				
				if (localeDialog.open() == Dialog.OK) {
					locale = localeDialog.getLocale();
					textLocale.setText(locale.getDisplayName());
				}
			}
		});
		
		btnSelectTimeZone.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TimeZoneDialog timeZoneDialog = new TimeZoneDialog(getShell(), timeZone);
				timeZoneDialog.create();
				
				if (timeZoneDialog.open() == Dialog.OK) {
					timeZone = timeZoneDialog.getTimeZone();
					if(timeZone != null) {
						textTimeZone.setText(timeZone.getID());
					}
				}
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void setDataAdapter(RemoteXMLDataAdapterDescriptor dataAdapter) 
	{
		this.remoteXmlDataAdapterDesc = dataAdapter;
		
		RemoteXmlDataAdapter remoteXmlDataAdapter = (RemoteXmlDataAdapter)remoteXmlDataAdapterDesc.getDataAdapter();
		
		textFileUrl.setText(Misc.nvl( remoteXmlDataAdapter.getFileName(),""));
		textDatePattern.setText( Misc.nvl(remoteXmlDataAdapter.getDatePattern(), "") );
		textNumberPattern.setText( Misc.nvl(remoteXmlDataAdapter.getNumberPattern(), "") );
		
		locale = remoteXmlDataAdapter.getLocale();
		if (locale != null) {
			textLocale.setText(locale.getDisplayName());
		} else {
			textLocale.setText("Default" + " ( " + Locale.getDefault().getDisplayName() + " )");
			locale = Locale.getDefault();
		}
		
		timeZone = remoteXmlDataAdapter.getTimeZone();
		if (timeZone != null) {
			textTimeZone.setText(timeZone.getID());
		} else {
			textTimeZone.setText("Default" + " ( " + TimeZone.getDefault().getID() + " )");
			timeZone = TimeZone.getDefault();
		}
	}

	public DataAdapterDescriptor getDataAdapter() 
	{
		if (remoteXmlDataAdapterDesc == null) remoteXmlDataAdapterDesc = new RemoteXMLDataAdapterDescriptor();
		
		RemoteXmlDataAdapter remoteXmlDataAdapter = (RemoteXmlDataAdapter)remoteXmlDataAdapterDesc.getDataAdapter();
		
		remoteXmlDataAdapter.setFileName(textFileUrl.getText());
		remoteXmlDataAdapter.setDatePattern(textDatePattern.getText());
		remoteXmlDataAdapter.setNumberPattern(textNumberPattern.getText());
		remoteXmlDataAdapter.setLocale(locale);
		remoteXmlDataAdapter.setTimeZone(timeZone);
		
		return remoteXmlDataAdapterDesc;
	}

	public String getHelpContextId() {
		return "";
	}
}
