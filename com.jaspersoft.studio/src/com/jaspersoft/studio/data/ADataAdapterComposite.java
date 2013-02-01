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
package com.jaspersoft.studio.data;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import net.sf.jasperreports.data.DataAdapter;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.swt.widgets.Composite;

public abstract class ADataAdapterComposite extends Composite {
	protected DataBindingContext bindingContext;

	public final static String PREFIX = "com.jaspersoft.studio.doc.";
	
	public ADataAdapterComposite(Composite parent, int style) {
		super(parent, style);
		bindingContext = new DataBindingContext();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	
	public String getHelpContextId() {
		return PREFIX.concat("dataAdapters_wizard_list");
	}

	public void removeBindings() {
		IObservableList bindings = bindingContext.getBindings();
		for (Object o : bindings) {
			bindingContext.removeBinding((Binding) o);
		}
	}

	public void removeDirtyListenersToContext() {
		IObservableList bindings = bindingContext.getBindings();
		for (Object o : bindings) {
			Binding b = (Binding) o;
			b.getTarget().removeChangeListener(listener);
		}
	}

	public void addDirtyListenersToContext() {
		IObservableList bindings = bindingContext.getBindings();
		for (Object o : bindings) {
			Binding b = (Binding) o;
			b.getTarget().addChangeListener(listener);
		}
	}

	@Override
	public void dispose() {
		IObservableList bindings = bindingContext.getBindings();
		for (Object o : bindings) {
			Binding b = (Binding) o;
			b.getTarget().removeChangeListener(listener);
		}
		super.dispose();
	}

	protected IChangeListener listener = new IChangeListener() {

		public void handleChange(ChangeEvent event) {
			pchangesuport.firePropertyChange("dirty", false, true);
		}
	};
	protected DataAdapterDescriptor dataAdapterDesc;

	public void setDataAdapter(DataAdapterDescriptor dataAdapterDesc) {
		this.dataAdapterDesc = dataAdapterDesc;
		DataAdapter dataAdapter = dataAdapterDesc.getDataAdapter();

		removeDirtyListenersToContext();
		removeBindings();

		bindWidgets(dataAdapter);

		// bindingContext.updateTargets();

		addDirtyListenersToContext();
	}

	protected abstract void bindWidgets(DataAdapter dataAdapter);

	public abstract DataAdapterDescriptor getDataAdapter();

	protected PropertyChangeSupport pchangesuport = new PropertyChangeSupport(this);

	public void addModifyListener(PropertyChangeListener listener) {
		pchangesuport.addPropertyChangeListener(listener);
	}

	public void removeModifyListener(PropertyChangeListener listener) {
		pchangesuport.removePropertyChangeListener(listener);
	}

}
