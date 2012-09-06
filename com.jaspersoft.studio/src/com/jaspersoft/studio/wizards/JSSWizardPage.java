/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.wizards;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardPage;

public abstract class JSSWizardPage extends WizardPage {

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
