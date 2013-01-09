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
package com.jaspersoft.studio.wizards;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.PlatformUI;

public abstract class JSSWizardPage extends WizardPage implements ContextData {

	protected String contextName;
	
	protected JSSWizardPage(String pageName) {
		super(pageName);
		contextName = null;
	}
	
	
	
	@Override
	protected void setControl(Control newControl) {
		super.setControl(newControl);
		newControl.addListener(SWT.Help, new Listener() {			
			@Override
			public void handleEvent(Event event) {
				performHelp();	
			}
		});
	};

	@Override
	public boolean canFlipToNextPage() {
		IWizard wizard = getWizard();
		if (wizard != null && wizard instanceof JSSWizard)
			return isPageComplete() && ((JSSWizard) wizard).hasNextPage(this) != null;
		return super.canFlipToNextPage();
	}
	
	/**
	 * Set the help data that should be seen in this step
	 */
	@Override
	public void setHelpData(){
		if (contextName != null){
			PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(),"com.jaspersoft.studio".concat("."+contextName));
		}
	}
	
	/**
	 * Set and show the help data if a context, that bind this wizard with the data, is provided
	 */
	@Override
	public void performHelp() {
		if (contextName != null){
			PlatformUI.getWorkbench().getHelpSystem().displayHelp("com.jaspersoft.studio".concat("."+contextName));
		}
	};
	
	/**
	 * When the user move from a page to another the help data of that page is set. This is done to show the correct help when the user move from 
	 * a step to another while the help is opened
	 */
	@Override
	public IWizardPage getNextPage() {
		IWizardPage nextPage = super.getNextPage();
		if (nextPage != null && nextPage instanceof ContextData) ((ContextData)nextPage).setHelpData();
		return nextPage;
	};
	
	
	/**
	 * When the user move from a page to another the help data of that page is set. This is done to show the correct help when the user move from 
	 * a step to another while the help is opened
	 */
	@Override
	public IWizardPage getPreviousPage() {
		IWizardPage prevPage = super.getPreviousPage();
		if (prevPage != null && prevPage instanceof ContextData) ((ContextData)prevPage).setHelpData();
		return prevPage;
	};

	/**
	 * Returns a settings object to store informations to be used between wizard state.
	 * The object is not null only when the wizard used if of type JSSWizard.
	 * 
	 * @return a map if the wizard is not null and of type JSSWizard, null otherwise.
	 */
	public Map<String, Object> getSettings()
	{
		if (getWizard() != null && getWizard() instanceof JSSWizard)
		{
			return ((JSSWizard) getWizard()).getSettings();
		}
		
		return null;
	}
	
	
	
	//If something changes dynamically (besides moving between pages), e.g.
  // the number of pages changes in response to user input, then call
	// when needed: fireChangeEvent();
	// The underline wizard may decide what to do.
	
	// Beging of handling of JSSWizardPageChangeListeners...
	
	private Set<JSSWizardPageChangeListener> listeners = new HashSet<JSSWizardPageChangeListener>(1);
	
  public final void addChangeListener(JSSWizardPageChangeListener l) {
	  synchronized (listeners) {
	  	listeners.add(l);
	  }
  }
  
  public final void removeChangeListener(JSSWizardPageChangeListener l) {
	  synchronized (listeners) {
	  	listeners.remove(l);
	  }
  }
  
  protected final void fireChangeEvent() {
	  Iterator<JSSWizardPageChangeListener> it;
	  synchronized (listeners) {
	  	it = new HashSet<JSSWizardPageChangeListener>(listeners).iterator();
	  }
	  JSSWizardPageChangeEvent ev = new JSSWizardPageChangeEvent(this);
	  while (it.hasNext()) {
	  	it.next().pageChanged(ev);
	  }
  }
  
  // End of Handling of JSSWizardPageChangeListeners...
	
}
