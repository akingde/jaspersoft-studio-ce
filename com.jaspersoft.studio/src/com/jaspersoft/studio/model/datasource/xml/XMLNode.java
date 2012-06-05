package com.jaspersoft.studio.model.datasource.xml;

import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.design.JRDesignField;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.utils.ResourceManager;

/**
 * ANode representing an XML document node.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class XMLNode extends ANode {

	private static final long serialVersionUID = 1L;
	private String nodeName;
	private String xpathExpression;

	public ImageDescriptor getImagePath() {
		return ResourceManager.getPluginImageDescriptor(
				JaspersoftStudioPlugin.PLUGIN_ID,"icons/resources/element_obj.gif");
	}

	public String getDisplayText() {
		return this.nodeName;
	}

	public Image getImage(){
		return ResourceManager.getImage(getImagePath());
	}

	public String getName(){
		return this.nodeName;
	}
	
	public void setName(String nodeName){
		this.nodeName=nodeName;
	}
	
	public String getXPathExpression(){
		return this.xpathExpression;
	}
	
	public void setXPathExpression(String xpathExp){
		this.xpathExpression=xpathExp;
	}

	@Override
	public Object getAdapter(Class adapter) {
		if(adapter==JRDesignField.class || adapter==JRField.class){
			JRDesignField field=new JRDesignField();
			field.setName(nodeName);
			field.setDescription(xpathExpression);
			field.setValueClass(String.class);
			return field;
		}
		return super.getAdapter(adapter);
	}
	
}
