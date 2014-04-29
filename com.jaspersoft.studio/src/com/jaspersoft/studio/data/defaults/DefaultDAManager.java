package com.jaspersoft.studio.data.defaults;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;

public class DefaultDAManager {

	public void init() {
		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(
				JaspersoftStudioPlugin.PLUGIN_ID, "defaultDA"); //$NON-NLS-1$  
		for (IConfigurationElement e : config) {
			try {
				Object o = e.createExecutableExtension("ClassFactory"); //$NON-NLS-1$
				if (o instanceof IDefaultDA)
					nodeFactory.add((IDefaultDA) o);
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	private List<IDefaultDA> nodeFactory = new ArrayList<IDefaultDA>();

	public List<DataAdapterDescriptor> getDefaultDAs() {
		List<DataAdapterDescriptor> res = new ArrayList<DataAdapterDescriptor>();
		for (IDefaultDA f : nodeFactory) {
			List<DataAdapterDescriptor> r = f.getDefaultDAs();
			if (r != null && !r.isEmpty())
				res.addAll(r);
		}
		return res;
	}

}
