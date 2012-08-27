package net.jaspersoft.templates;

import java.io.InputStream;
import java.util.List;

public interface ReportBundle {

	/**
	 * The list of all the resources available in this bundle.
	 * 
	 * @return
	 */
	public List<String> getResourceNames();
	
	/**
	 * This method allows to get the data of a particular resource which is part of
	 * this bundle (like an image or a jrtx file) which can then be stored in the proper way.
	 * The user is in charge to close each stream.
	 * 
	 * @param name
	 * @return
	 */
	public InputStream getResource(String name);
	
}
