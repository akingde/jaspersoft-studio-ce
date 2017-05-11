/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.xmla;

import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.text.MessageFormat;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.messages.Messages;
import com.jaspersoft.studio.data.secret.DataAdaptersSecretsProvider;
import com.jaspersoft.studio.swt.widgets.WSecretText;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.xmla.XmlaDataAdapter;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.util.SecretsUtil;

public class XmlaDataAdapterComposite extends ADataAdapterComposite {

	private Text xmlaUri;
	private Text textUsername;
	private WSecretText textPassword;
	private Combo cube;
	private Combo catalog;
	private Combo datasource;
	private DataSourceTreeElement[] dstes;
	private DataSourceTreeElement[] catalogs;
	private XmlaDataAdapter adapter;

	/**
	 * Inner class used only to store the result of a connection operation
	 * 
	 * @author Orlandin Marco
	 * 
	 */
	private class ReturnResponse {

		/**
		 * Return code of the connection operation
		 */
		private int returnCode;

		/**
		 * Flag to store if the operation was successful or not
		 */
		private boolean successful;

		/**
		 * Exception raised during the operation, null if there wasen't
		 * exceptions
		 */
		private Exception ex;

		/**
		 * Create an instance of the class
		 * 
		 * @param successfull
		 *            Flag to store if the operation was successful or not
		 * @param returnCode
		 *            Return code of the connection operation
		 */
		public ReturnResponse(boolean successfull, int returnCode) {
			this.successful = successfull;
			this.returnCode = returnCode;
			ex = null;
		}

		/**
		 * Create an instance of the class, the return code is always -1 and the
		 * successful flag is false, since there was an exception
		 * 
		 * @param ex
		 *            Exception raised during the operation
		 */
		public ReturnResponse(Exception ex) {
			this.successful = false;
			this.returnCode = -1;
			this.ex = ex;
		}

		/**
		 * Return if there was an exception
		 * 
		 * @return true if there was an exception, false otherwise
		 */
		public boolean isException() {
			return ex != null;
		}

		/**
		 * Return the exception raised
		 * 
		 * @return return the exception, or null if isException is false
		 */
		public Exception getException() {
			return ex;
		}

		/**
		 * Check if the connection operation is successful
		 * 
		 * @return true if the connection is working good, false otherwise
		 */
		public boolean isSuccessfull() {
			return this.successful;
		}

		/**
		 * Return the return code of the connection operation
		 * 
		 * @return an integer representing the return code of the operation. It
		 *         is -1 in case of exception since the procedure was
		 *         interrupted before try the connection
		 */
		public int getReturnCode() {
			return returnCode;
		}
	}

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public XmlaDataAdapterComposite(Composite parent, int style,
			JasperReportsContext jrContext) {

		super(parent, style, jrContext);
		setLayout(new GridLayout(3, false));

		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setText(Messages.XmlaDataAdapterComposite_0);

		xmlaUri = new Text(this, SWT.BORDER);
		xmlaUri.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Button bGetMetadata = new Button(this, SWT.PUSH);
		bGetMetadata.setText(Messages.XmlaDataAdapterComposite_1);

		new Label(this, SWT.NONE).setText(Messages.XmlaDataAdapterComposite_2);
		datasource = new Combo(this, SWT.READ_ONLY | SWT.BORDER);
		datasource.setItems(new String[] {});
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		datasource.setLayoutData(gd);

		new Label(this, SWT.NONE).setText(Messages.XmlaDataAdapterComposite_3);
		catalog = new Combo(this, SWT.READ_ONLY | SWT.BORDER);
		catalog.setItems(new String[] {});
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		catalog.setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL).setLayoutData(gd);

		new Label(this, SWT.NONE).setText(Messages.XmlaDataAdapterComposite_4);

		cube = new Combo(this, SWT.READ_ONLY | SWT.BORDER);
		cube.setItems(new String[] {});
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		cube.setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL).setLayoutData(gd);

		Label lblUsername = new Label(this, SWT.NONE);
		lblUsername.setText(Messages.JDBCDataAdapterComposite_username);

		textUsername = new Text(this, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		textUsername.setLayoutData(gd);

		Label lblPassword = new Label(this, SWT.NONE);
		lblPassword.setText(Messages.JDBCDataAdapterComposite_password);

		textPassword = new WSecretText(this, SWT.BORDER | SWT.PASSWORD);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		textPassword.setLayoutData(gd);

		bGetMetadata.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String url = xmlaUri.getText();
				String username = textUsername.getText();
				String password = textPassword.getText();
				boolean firstConnectionSuccessfull = false;
				//Try first to use the provided username and password for the connection
				if (!username.isEmpty() && !password.isEmpty()){
					ReturnResponse response = validateUsernamePassword(url, username, password);
					boolean loginSuccesfull = response.isSuccessfull();
					if (loginSuccesfull) {
						try {
							MetadataDiscover discover = new MetadataDiscover(url, username, password);
							handleMetaDataChanged(discover);
							firstConnectionSuccessfull = true;
							UIUtils.showInformation(Messages.XmlaDataAdapterComposite_successTitle, Messages.XmlaDataAdapterComposite_successText);
						} catch (Exception e1) {}
					}
				}
				if (!firstConnectionSuccessfull){
					//If the first attempt was not successful need to ask the connection data because with the provided ones the connection was unsuccessful
					AuthenticationDialog dialog = new AuthenticationDialog(getShell(), url, textUsername.getText(), getPassword());
					if (dialog.open() == Dialog.OK){
						ReturnResponse response = validateUsernamePassword(url, dialog.getUsername(), dialog.getPassword());
						boolean loginSuccesfull = response.isSuccessfull();
						if (loginSuccesfull) {
							try {
								MetadataDiscover discover = new MetadataDiscover(url, dialog.getUsername(), dialog.getPassword());
								handleMetaDataChanged(discover);
								textUsername.setText(dialog.getUsername());
								textPassword.setText(dialog.getPassword());
								UIUtils.showInformation(Messages.XmlaDataAdapterComposite_successTitle, Messages.XmlaDataAdapterComposite_successText);
							} catch (Exception e1) {
								String message = Messages.XmlaDataAdapterComposite_failedText + "\n" + MessageFormat.format(Messages.XmlaDataAdapterComposite_errorCode, new Object[]{e1.getMessage()}); //$NON-NLS-1$
								UIUtils.showWarning(Messages.XmlaDataAdapterComposite_failedTitle,message);
								JaspersoftStudioPlugin.getInstance().logError(message, e1);
								e1.printStackTrace();
							}
						} else {
							String message;
							if (response.isException()) {
								message = Messages.XmlaDataAdapterComposite_failedText + "\n" + MessageFormat.format(Messages.XmlaDataAdapterComposite_errorException, new Object[] { response.getException().getMessage() }); //$NON-NLS-1$
							} else {
								message = Messages.XmlaDataAdapterComposite_failedText + "\n" + MessageFormat.format(Messages.XmlaDataAdapterComposite_errorCode, new Object[] { response.getReturnCode() }); //$NON-NLS-1$
							}
							UIUtils.showWarning(Messages.XmlaDataAdapterComposite_failedTitle,message);
						}
					}
				}
			}
		});

		datasource.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleDatasourceChanged();
			}

		});

		catalog.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleCatalogChanged();
			}
		});
	}

	@Override
	protected void bindWidgets(DataAdapter dataAdapter) {
		bindingContext.bindValue(
				SWTObservables.observeText(xmlaUri, SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "xmlaUrl")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeSelection(datasource),
				PojoObservables.observeValue(dataAdapter, "datasource")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeSelection(catalog),
				PojoObservables.observeValue(dataAdapter, "catalog")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeSelection(cube),
				PojoObservables.observeValue(dataAdapter, "cube")); //$NON-NLS-1$
		bindingContext.bindValue(
				SWTObservables.observeText(textUsername, SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "username")); //$NON-NLS-1$
		bindingContext.bindValue(
				SWTObservables.observeText(textPassword, SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "password")); //$NON-NLS-1$
	}

	public DataAdapterDescriptor getDataAdapter() {
		if (dataAdapterDesc == null)
			dataAdapterDesc = new XmlaDataAdapterDescriptor();

		XmlaDataAdapter dataAdapter = (XmlaDataAdapter) dataAdapterDesc
				.getDataAdapter();

		dataAdapter.setXmlaUrl(xmlaUri.getText());
		dataAdapter.setDatasource(datasource.getText());
		dataAdapter.setCatalog(catalog.getText());
		dataAdapter.setCube(cube.getText());

		dataAdapter.setUsername(textUsername.getText());
		// configure widget if not done yet
		if (!textPassword.isWidgetConfigured()) {
			textPassword.loadSecret(DataAdaptersSecretsProvider.SECRET_NODE_ID,
					textPassword.getText());
		} else {
			// rely on back-compatibility and use clear text
			dataAdapter.setPassword(textPassword.getText());
		}

		return dataAdapterDesc;
	}

	@Override
	public void setDataAdapter(DataAdapterDescriptor dataAdapterDesc) {
		removeDirtyListenersToContext();
		adapter = (XmlaDataAdapter) dataAdapterDesc.getDataAdapter();
		if (dstes == null) {
			datasource
					.setItems(new String[] { Misc.nvl(adapter.getDatasource()) });
			datasource.select(0);

			catalog.setItems(new String[] { Misc.nvl(adapter.getCatalog()) });
			catalog.select(0);

			cube.setItems(new String[] { Misc.nvl(adapter.getCube()) });
			cube.select(0);
		} else {
			handleDatasourceChanged();
		}

		// The super call bind the widgets,it is called a the end to avoid it to
		// used uninitialized
		// widgets
		super.setDataAdapter(dataAdapterDesc);
		addDirtyListenersToContext();
	}

	private int getIndex(String[] array, String value, int notFoundValue) {
		int i = 0;
		for (String item : array) {
			if (item.equals(value))
				return i;
			i++;
		}
		return notFoundValue;
	}

	protected void handleMetaDataChanged(MetadataDiscover discover) {
		dstes = discover.getDatasources();
		if (dstes == null || dstes.length == 0)
			return;

		String[] dsources = new String[dstes.length];
		for (int i = 0; i < dstes.length; i++)
			dsources[i] = dstes[i].getDataSourceInfo();
		int selectionIndex = 0;
		if (adapter != null) {
			selectionIndex = getIndex(dsources, adapter.getDatasource(), 0);
		}
		datasource.setItems(dsources);
		datasource.select(selectionIndex);
		handleDatasourceChanged();
	}

	protected void handleDatasourceChanged() {
		if (dstes == null)
			return;
		int ind = datasource.getSelectionIndex();
		catalogs = dstes[ind].getChildren();
		if (catalogs == null || catalogs.length == 0)
			return;

		String[] scat = new String[catalogs.length];
		for (int i = 0; i < catalogs.length; i++)
			scat[i] = ((CatalogElement) catalogs[i]).toString();
		int selectionIndex = 0;
		if (adapter != null) {
			selectionIndex = getIndex(scat, adapter.getCatalog(), 0);
		}
		catalog.setItems(scat);
		catalog.select(selectionIndex);
		handleCatalogChanged();
	}

	protected void handleCatalogChanged() {
		if (dstes == null)
			return;
		int ind = catalog.getSelectionIndex();
		DataSourceTreeElement[] cubes = catalogs[ind].getChildren();
		if (cubes == null || cubes.length == 0)
			return;

		String[] scubes = new String[cubes.length];
		for (int i = 0; i < cubes.length; i++)
			scubes[i] = ((CubeElement) cubes[i]).toString();
		int selectionIndex = 0;
		if (adapter != null) {
			selectionIndex = getIndex(scubes, adapter.getCube(), 0);
		}
		cube.setItems(scubes);
		cube.select(selectionIndex);
	}

	/**
	 * Try to extract the password from the secret storage, and the uuid is
	 * taken from the password field. If the secret storage is not enabled or
	 * the uuid is not valid then the uuid value itself is returned as password
	 * 
	 * @return the password inside the password control
	 */
	private String getPassword() {
		SecretsUtil sInstance = SecretsUtil.getInstance(getJrContext());
		String secret = sInstance.getSecret(
				DataAdaptersSecretsProvider.SECRET_NODE_ID,
				textPassword.getText());
		return secret;
	}

	/**
	 * Set a custom swt validation dialog for an url
	 * 
	 * @param url
	 *            the url of the server
	 * @return true if the operation was aborted, false otherwise
	 */
	private ReturnResponse validateUsernamePassword(final String url, final String username, final String password) {
		try {
			/**
			 * Set the dialog as authenticator
			 */
			Authenticator.setDefault(new Authenticator() {

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password.toCharArray());
				}
			});
			URL endpoint = new URL(url);
			HttpURLConnection urlConnection = (HttpURLConnection) endpoint
					.openConnection();
			int code = urlConnection.getResponseCode();
			ReturnResponse response = new ReturnResponse((code == 405 || code == 500) , code);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResponse(e);
		}
	}

	@Override
	public String getHelpContextId() {
		return PREFIX.concat("adapter_xmla"); //$NON-NLS-1$
	}

	@Override
	public void performAdditionalUpdates() {
		if (JaspersoftStudioPlugin.shouldUseSecureStorage()) {
			textPassword.persistSecret();
			// update the "password" replacing it with the UUID key saved in
			// secure
			// preferences
			XmlaDataAdapter dataAdapter = (XmlaDataAdapter) dataAdapterDesc
					.getDataAdapter();
			dataAdapter.setPassword(textPassword.getUUIDKey());
		}
	}
}
