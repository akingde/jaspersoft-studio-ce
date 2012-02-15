/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
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

	public ADataAdapterComposite(Composite parent, int style) {
		super(parent, style);
		bindingContext = new DataBindingContext();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public String getHelpContextId() {
		return "";
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
