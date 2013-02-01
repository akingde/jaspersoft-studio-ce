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

public abstract class JSSWizardPage extends JSSHelpWizardPage {
	
	protected JSSWizardPage(String pageName) {
		super(pageName);
	}
	

	@Override
	public boolean canFlipToNextPage() {
		IWizard wizard = getWizard();
		if (wizard != null && wizard instanceof JSSWizard)
			return isPageComplete() && ((JSSWizard) wizard).hasNextPage(this) != null;
		return super.canFlipToNextPage();
	}

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
