/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.MReportRoot;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.utils.jasper.ResourceChangeEvent;
import com.jaspersoft.studio.utils.jasper.ResourceChangeEvent.RESOURCE_TYPE;

/**
 * Check if a changed resource is a tamplate style and in case fire on the {@link JasperReportsConfiguration} the
 * event to refresh the template styles of the report
 */
public class TemplateStyleVisitor implements IResourceDeltaVisitor {

	private JrxmlEditor editor;
	
	public TemplateStyleVisitor(JrxmlEditor editor) {
		this.editor = editor;
	}
	
	@Override
	public boolean visit(IResourceDelta delta) throws CoreException {
		if (delta.getResource() instanceof IFile) {
			IFile resource = (IFile)delta.getResource();
			String extension = resource.getFileExtension();
			if (extension != null && extension.toLowerCase().equals("jrtx")) {
				try {
					JasperReportsConfiguration jConfig = ((MReportRoot)editor.getModel()).getJasperConfiguration();
					ResourceChangeEvent resourceChangeEvent = new ResourceChangeEvent(jConfig, resource, RESOURCE_TYPE.TEMPLATE_STYLE);
					jConfig.getPropertyChangeSupport().firePropertyChange(resourceChangeEvent);
				} catch (Exception ex) {
					JaspersoftStudioPlugin.getInstance().logError(ex);
					ex.printStackTrace();
				}
			}
			
		} 
		return true;
	}

}
