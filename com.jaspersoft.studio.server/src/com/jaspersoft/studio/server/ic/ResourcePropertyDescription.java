/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.ic;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.export.AExporter;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.properties.dialog.RepositoryDialog;
import com.jaspersoft.studio.server.selector.SelectServerWizard;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.FilePropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * Descriptor used to browse a resource on the server
 * 
 * @author Orlandin Marco
 *
 */
public class ResourcePropertyDescription extends FilePropertyDescription {
	
	public ResourcePropertyDescription() {
		super();
	}
	
	public ResourcePropertyDescription(String name, String label, String description, boolean mandatory, String defaultValue) {
		super(name, label, description, mandatory, defaultValue);
	}
	
	/**
	 * Create the toolbutton where to open the resource selection dialog
	 * 
	 * @param parent the parent of the button
	 * @param wiProp the {@link IWItemProperty} to handle the setValue operation if the dialog is closed 
	 * correctly
	 */
	protected void createToolbarButton(Composite parent, final IWItemProperty wiProp){
		ToolBar toolBar = new ToolBar(parent, SWT.NONE);
		ToolItem b = new ToolItem(toolBar, SWT.FLAT);
		b.setImage(getButtonImage());
		b.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				if (wiProp.isRefresh())
					return;
				wiProp.setRefresh(true);
				try{
					MServerProfile profile = getServerProfile();
					if (profile != null){
						RepositoryDialog rd = new RepositoryDialog(UIUtils.getShell(), profile) {

							@Override
							public boolean isResourceCompatible(AMResource r) {
								return r.getValue().getWsType().equals(ResourceDescriptor.TYPE_INPUT_CONTROL);
							}
						};
						if (rd.open() == Dialog.OK) {
							AMResource rs = rd.getResource();
							if (rs != null){
								wiProp.setValue(rs.getValue().getUriString(), null);
							} else {
								wiProp.setValue(null, null);
							}
						} 
					}
					
				} finally {
					wiProp.setRefresh(false);
				}
			}

		});
		GridData data = new GridData();
		data.verticalAlignment = SWT.TOP;
		toolBar.setLayoutData(data);
		if (isReadOnly()){
			toolBar.setEnabled(false);	
		}
	}
	
	private MServerProfile getServerProfile(){
		JasperDesign jDesign = jConfig.getJasperDesign();
		String servURL = jDesign.getProperty(AExporter.PROP_SERVERURL);
		String servUser = jDesign.getProperty(AExporter.PROP_USER);

		MServerProfile msp = ServerManager.getServerByUrl(servURL, servUser);
		if (msp == null) {
			SelectServerWizard wizard = new SelectServerWizard();
			WizardDialog w = new WizardDialog(UIUtils.getShell(), wizard);
			if (w.open() == Dialog.OK) {
				msp = wizard.getValue();
				try {
					jDesign.setProperty(AExporter.PROP_SERVERURL, msp.getValue().getUrl());
					jDesign.setProperty(AExporter.PROP_USER,
							msp.getValue().getUser() + (msp.getValue().getOrganisation() != null
									? "|" + msp.getValue().getOrganisation() : "")); //$NON-NLS-1$ //$NON-NLS-2$
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		}
		return msp;
	}
	
	@Override
	public ItemPropertyDescription<String> clone(){
		ResourcePropertyDescription result = new ResourcePropertyDescription();
		result.defaultValue = defaultValue;
		result.description = description;
		result.jConfig = jConfig;
		result.label = label;
		result.mandatory = mandatory;
		result.name = name;
		result.readOnly = readOnly;
		result.fallbackValue = fallbackValue;
		return result;
	}
	
	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		ResourcePropertyDescription fileDesc = new ResourcePropertyDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), cpd.getDefaultValue());
		fileDesc.setjConfig(jConfig);
		fileDesc.setReadOnly(cpd.isReadOnly());
		fileDesc.setFallbackValue(cpd.getFallbackValue());
		return fileDesc;
	}
}
