package com.jaspersoft.studio.editor.preview.jive;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

/**
 * Should be implemented by those clients that wants to provide 
 * facility methods to interact with Jetty when using Jive.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public interface JiveJettyUtilitiesProvider {

	void startJetty(IProject project);
	
	void stopJetty(IProject project);
	
	void restartJetty(IProject project);
	
	String getURL(IFile file, String uuid);
	
}
