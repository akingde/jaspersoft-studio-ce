/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.data.adapter.IReportDescriptor;
import com.jaspersoft.studio.data.wizard.ListInstallationPage;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.utils.Encrypter;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

/**
 * Show a list of checkboxes where every box is a server connection found inside a server 
 * configuration file of iReport
 * 
 * @author Orlandin Marco
 *
 */
public class ShowServersPage extends JSSHelpWizardPage {

	/**
	 * Configuration of iReport where the servers are searched
	 */
	private IReportDescriptor selectedInstallation;
	
	/**
	 * List of all the checkboxes
	 */
	private List<Button> selectedElements;
	
	/**
	 * composite where the checkboxes are placed
	 */
	private Composite content;
	
	public ShowServersPage() {
		super("IReportDatasourceList");
		selectedElements = new ArrayList<Button>();
		setTitle("Select the Server Connections");
		setDescription("Select one or more connections to JasperReport Server to import");
	}
	
	/**
	 * When the page became visible the configuration is read from the previous page and the 
	 * checkboxes are created
	 */
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		
		selectedInstallation = ((ListInstallationPage)getPreviousPage()).getSelection();
		//Clear the old elements if someone is doing back and the next
		for(Button button : selectedElements){
			button.dispose();
		}
		selectedElements.clear();
		content.layout();
		
		Properties prop = selectedInstallation.getServerConnection();
		Integer connectionIndex = 0;
		
		if (prop != null){
			Encrypter enc = new Encrypter("54fj245vn3vfdsmce4mg0jvs");
			String connectionString = prop.getProperty("server." + connectionIndex + ".url");
			while(connectionString != null){
				ServerProfile srv = new ServerProfile();
				srv.setUrl(connectionString); 
				String name = prop.getProperty("server." + connectionIndex + ".name");
				srv.setName(name);
				String username = prop.getProperty("server." + connectionIndex + ".username");
				srv.setUser(username); 
				srv.setSupportsDateRanges(true);
				
				String password = prop.getProperty("server." + connectionIndex + ".password.enc");
				//getBrandingProperties().getProperty("irplugin.encrypt.passwords.key", "54fj245vn3vfdsmce4mg0jvs")
				password = enc.decrypt(password);
				srv.setPass(password);
				
				Button checkButton = new Button(content, SWT.CHECK);
				checkButton.setText(name + " ("+connectionString+")");
				checkButton.setData(srv);
				selectedElements.add(checkButton);
				
				connectionIndex++;
				connectionString = prop.getProperty("server." + connectionIndex + ".url");
			}
			content.layout();
		}
	}
	
	/**
	 * Return a list of the selected servers connection
	 * 
	 * @return a not null list of ServerProfile, every one represent an element to import
	 */
	public List<ServerProfile> getSelectedAdapter(){
		List<ServerProfile> result = new ArrayList<ServerProfile>();
		for(Button element : selectedElements){
			if (element.getSelection()) result.add((ServerProfile)element.getData());
		}
		return result;
	}
	
	
	@Override
	public void createControl(Composite parent) {
		Composite mainComposite = new Composite(parent, SWT.NONE);
		mainComposite.setLayout(new GridLayout(1,false));
		setControl(mainComposite);
		
		Label titleLabel = new Label(mainComposite, SWT.NONE);
		titleLabel.setText("Select the connections to import");
		
		ScrolledComposite scrollComp = new ScrolledComposite(mainComposite, SWT.V_SCROLL);
		scrollComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		scrollComp.setLayout(new GridLayout(1,false));
		scrollComp.setExpandHorizontal(true);
		scrollComp.setExpandVertical(true);
		content = new Composite(scrollComp, SWT.NONE);
		content.setLayout(new GridLayout(1,false));
		content.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		content.layout();
		scrollComp.setContent(content);

	}

	@Override
	protected String getContextName() {
		return null;
	}

}
