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
package com.jaspersoft.studio.data.ui;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.data.DataAdapterServiceUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignQuery;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import com.jaspersoft.studio.data.AWizardDataEditorComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.JSSWizard;
import com.jaspersoft.studio.wizards.JSSWizardRunnablePage;

/**
 * This is an abstract implementation of the (almost) most simple editor that can be provided by an adapter.
 * The other type of editor could be just a composite, made to provide information of what will
 * happen while pressing next in the wizard page in which it is displayed.
 * 
 * This abstract wizard creates just a composite in which there is a simple label and and a textfield.
 * 
 * @author gtoffoli
 *
 */
public class SimpleQueryWizardDataEditorComposite extends AWizardDataEditorComposite {

	
	/**
	 * Question return code. This variable is used across a thread UI and background process thread.
	 * We assume there will never pop up two identical questions at the same time.
	 */
	private int questionReturnCode = SWT.OK;
	
	private DataAdapterDescriptor dataAdapterDescriptor;
	
	/**
	 *  Convenient object to be passed to the IFieldsProvider.getFields method
	 */
	private JRDesignDataset dataset = null;
	
	private String queryString = "";
	
	
	/**
	 * The wizard page
	 */
	private WizardPage page;
	
	/**
	 * UI component to display the title
	 */
	private Label lblTitle = null;
	
	private String queryLanguage = null;
	
	/**
	 * The styled text UI component, that can be configured
	 * by subclasses.
	 */
	protected StyledText styledText = null;
	
	/**
	 * A simple title to be used to say something like: "Write a query in SQL..."
	 */
	private String title = null;
	
	
	public SimpleQueryWizardDataEditorComposite(Composite parent, WizardPage page) {
		this(parent, page, null);
	}
	
	
	public SimpleQueryWizardDataEditorComposite(Composite parent, WizardPage page, DataAdapterDescriptor dataAdapterDescriptor) {
		super(parent, page);
		this.dataAdapterDescriptor = dataAdapterDescriptor;
		this.page = page;
		init();
		createCompositeContent();
	}
	
	/**
	 * Initializes additional information that are supposed to
	 * be sub-class specific and executed in the constructor 
	 * before the main composite content creation.
	 * This method is called before {@link #createCompositeContent()}. 
	 */
	protected void init() {
		// do nothig - default behavior
	}
	
	/**
	 * Sets layout and creates the content of the main composite.
	 * Created widgets should use <code>this</code> as parent composite. 
	 */
	protected void createCompositeContent(){
	  setLayout(new FormLayout());
		
		lblTitle = new Label(this, SWT.NONE);
		FormData fd_lblTitle = new FormData();
		fd_lblTitle.top = new FormAttachment(0);
		fd_lblTitle.left = new FormAttachment(0);
		fd_lblTitle.right = new FormAttachment(100);
		lblTitle.setLayoutData(fd_lblTitle);
		
		if (getTitle() != null)
		{
			lblTitle.setText(getTitle());
		}
		
		styledText = new StyledText(this, SWT.BORDER);
		FormData fd_styledText = new FormData();
		fd_styledText.bottom = new FormAttachment(100);
		fd_styledText.right = new FormAttachment(100);
		fd_styledText.top = new FormAttachment(lblTitle, 6);
		fd_styledText.left = new FormAttachment(lblTitle, 0, SWT.LEFT);
		styledText.setLayoutData(fd_styledText);
		
		styledText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				queryString = styledText.getText();
			}
		});
		
		queryString = styledText.getText();
	}
	
	public String getQueryString()
	{
		return queryString;
	}
	
	public void setQueryString(String querystring){
		this.queryString=querystring;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
		lblTitle.setText(title);
	}


	/**
	 * The query language
	 * 
	 * @return the query language or null if the language has not been set.
	 */
	@Override
	public String getQueryLanguage() {
		return this.queryLanguage;
	}


	/**
	 * @param queryLanguage the queryLanguage to set
	 */
	public void setQueryLanguage(String queryLanguage) {
		this.queryLanguage = queryLanguage;
	}
	
	
	
  /**
   * Return the fields.
   * 
   * If the dataAdapterDescriptor implements IFieldsProvider, this interface is used to
   * get the fields automatically.
   * 
   * This method is invoked on a thread which is not in the UI event thread, so
   * no UI update should be performed without using a proper async thread.
   * 
   * return the result of IFieldsProvider.getFields() or an empty list of JRField is the DataAdapterDescriptor does not implement the IFieldsProvider interface.
   */ 
  public List<JRDesignField> readFields() throws Exception
  {
  	
  	if (getDataAdapterDescriptor() != null &&
  			getDataAdapterDescriptor() instanceof IFieldsProvider)
  	{
  		questionReturnCode = SWT.OK;
  		try {
  		
  				JRDesignDataset tmpDataset = getDataset();
  				if (tmpDataset.getQuery().getText() == null || tmpDataset.getQuery().getText().length() == 0)
  				{
  					Display.getDefault().syncExec(new Runnable() {
							
							@Override
							public void run() {
								
								
								MessageBox dialog = 
									  new MessageBox(Display.getDefault().getActiveShell(), SWT.ICON_QUESTION | SWT.OK| SWT.CANCEL);
									dialog.setText("No query provided");
									dialog.setMessage("You did not provide any query, so I will not be able to discover any field for you. Continue anyway?");
									questionReturnCode = dialog.open(); 
								
							}
						});
  					
  					if (questionReturnCode != SWT.OK)
  					{
  						throw JSSWizardRunnablePage.USER_CANCEL_EXCEPTION;
  					}
  					
  				}
  			
		  		JasperReportsConfiguration jContext = getJasperReportsConfiguration();
					return ((IFieldsProvider)getDataAdapterDescriptor()).getFields(
							DataAdapterServiceUtil.getInstance(jContext).getService(getDataAdapterDescriptor().getDataAdapter()), 
							jContext,
							dataset);
  		
  		} catch (JRException ex)
  		{
  			// Cleanup of the error. JRException are a very low meaningful exception when working
  			// with data, what the user is interested into is the underline error (i.e. an SQL error).
  			// That's why we rise the real cause, if any instead of rising the highlevel exception...
  			if (ex.getCause() != null && ex.getCause() instanceof Exception)
  			{
  				throw (Exception)ex.getCause();
  			}
  			throw ex;
  		}
  	}
  	return new ArrayList<JRDesignField>();
  	
  }

  
  public JasperReportsConfiguration getJasperReportsConfiguration()
  {
  	if  (getPage() != null && getPage().getWizard() != null && getPage().getWizard() instanceof JSSWizard)
  	{
  			return ((JSSWizard)getPage().getWizard()).getConfig();
  	}
  	
  	return null;
  }


	/**
	 * @return the page
	 */
	public WizardPage getPage() {
		return page;
	}


	/**
	 * @param page the page to set
	 */
	public void setPage(WizardPage page) {
		this.page = page;
	}
	
	
	/**
	 * Convenient way to crate a dataset object to be passed to the IFieldsProvider.getFields method
	 * 
	 * @return JRDesignDataset return a dataset with the proper query and language set...
	 */
	public JRDesignDataset getDataset() {
		if (dataset == null) {
			dataset = new JRDesignDataset(true);
			JRDesignQuery query = new JRDesignQuery();
			query.setLanguage(getQueryLanguage());
			dataset.setQuery(query);
		}
		
		((JRDesignQuery)dataset.getQuery()).setText(getQueryString());
		return dataset;
	}


	/**
	 * @return the dataAdapterDescriptor
	 */
	public DataAdapterDescriptor getDataAdapterDescriptor() {
		return dataAdapterDescriptor;
	}


	/**
	 * @param dataAdapterDescriptor the dataAdapterDescriptor to set
	 */
	public void setDataAdapterDescriptor(DataAdapterDescriptor dataAdapterDescriptor) {
		this.dataAdapterDescriptor = dataAdapterDescriptor;
	}
}
