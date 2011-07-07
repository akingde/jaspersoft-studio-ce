package com.jaspersoft.studio.property.dataset.dialog;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.IQueryDesigner;
import com.jaspersoft.studio.data.QueryDesigner;

public class QDesignerFactory {
	private Composite parent;
	private Map<String, IQueryDesigner> languageMap = new HashMap<String, IQueryDesigner>();
	private Map<Class<? extends IQueryDesigner>, IQueryDesigner> classmap = new HashMap<Class<? extends IQueryDesigner>, IQueryDesigner>();

	public QDesignerFactory(Composite parent) {
		this.parent = parent;
		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(
				"com.jaspersoft.studio", "queryDesigner"); //$NON-NLS-1$ //$NON-NLS-2$
		for (IConfigurationElement e : config) {
			try {
				String lang = e.getAttribute("language");//$NON-NLS-1$
				IQueryDesigner qd = (IQueryDesigner) e.createExecutableExtension("QueryDesignerClass"); //$NON-NLS-1$
				addDesigner(lang, qd);
			} catch (CoreException ex) {
				ex.printStackTrace();
				System.out.println(ex.getMessage());
			}
		}
	}

	public void dispose() {
		for (IQueryDesigner qd : languageMap.values())
			qd.dispose();
	}

	public IQueryDesigner getDesigner(String lang) {
		IQueryDesigner qd = languageMap.get(lang);
		if (qd == null)
			qd = addDesigner(lang, new QueryDesigner());
		return qd;
	}

	private IQueryDesigner addDesigner(String lang, IQueryDesigner qd) {
		IQueryDesigner iqd = classmap.get(qd.getClass());
		if (iqd == null) {
			iqd = qd;
			try {

				iqd.createControl(parent);
			} catch (Exception e) {
				e.printStackTrace();
				addDesigner(lang, new QueryDesigner());
			}
			classmap.put(qd.getClass(), iqd);
		}
		languageMap.put(lang, iqd);
		return iqd;
	}
}
