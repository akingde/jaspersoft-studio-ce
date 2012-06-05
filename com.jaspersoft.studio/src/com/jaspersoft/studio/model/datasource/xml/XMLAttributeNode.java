package com.jaspersoft.studio.model.datasource.xml;

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

	private static final long serialVersionUID = 1L;

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
