package com.jaspersoft.studio.model.datasource.xml;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.utils.ResourceManager;

/**
 * ANode representing an XML document attribute node.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class XMLAttributeNode extends XMLNode{

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	@Override
	public ImageDescriptor getImagePath() {
		return ResourceManager.getPluginImageDescriptor(
				JaspersoftStudioPlugin.PLUGIN_ID, "icons/resources/attribute_obj.gif");
	}

	@Override
	public String getDisplayText() {
		return super.getDisplayText();
	}
	
}
