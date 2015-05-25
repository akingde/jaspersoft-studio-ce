package com.jaspersoft.studio.book.model;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JROrigin;
import net.sf.jasperreports.engine.JRPart;
import net.sf.jasperreports.engine.JRSection;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.type.BandTypeEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.book.descriptors.GroupNameValidator;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.ExpressionEditorSupportUtil;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSTextPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSValidatedTextPropertyDescriptor;
import com.jaspersoft.studio.utils.ModelUtils;

public class MReportPartContainer extends APropertyNode {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	public static final String PROPERTY_CONTAINER_TYPE = "property_partcontainer_type";
	
	public static final String JSSPROPERTY_GROUPLABEL_PREFIX = "com.jaspersoft.studio.book.group.";
	public static final String JSSPROPERTY_GROUPLABEL_HEADER_POSTFIX = ".header";
	public static final String JSSPROPERTY_GROUPLABEL_FOOTER_POSTFIX = ".footer";
	
	
	// The icon descriptor
	private static IIconDescriptor iconDescriptor;
	// Array of property descriptors
	private static IPropertyDescriptor[] descriptors;
	// A map for propery defaults
	private static Map<String, Object> defaultsMap;
	
	private JRDesignGroup jrgroup;
	
	private static GroupNameValidator validator;

	public MReportPartContainer(ANode parent, JRSection jrsection, int newIndex){
		super(parent,newIndex);
		setValue(jrsection);
	}
	
	/**
	 * Update the reference into the static validator when the actual group is 
	 * edited
	 */
	public void updateValidator(){
		validator.setTargetNode(this);
	}

	@Override
	protected void postDescriptors(IPropertyDescriptor[] descriptors) {
		super.postDescriptors(descriptors);
		//Set into the validator the actual reference
		updateValidator();
	}
	
	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("reportpartcontainer"); //$NON-NLS-1$
		return iconDescriptor;
	}
	
	@Override
	public Object getPropertyValue(Object id) {
		JRDesignSection jrsection = getValue();
		if(jrsection!=null){
			if(JRDesignSection.PROPERTY_PARTS.equals(id)){
				return jrsection.getPartsList();
			}
			if(PROPERTY_CONTAINER_TYPE.equals(id)){
				JROrigin origin = jrsection.getOrigin();
				if(origin!=null){
					// Valid return values should be only:
					//	- GROUP_HEADER
					// 	- GROUP_FOOTER
					//	- DETAIL
					return origin.getBandTypeValue();
				}
				else{
					return BandTypeEnum.UNKNOWN;
				}
			} else if(JRDesignGroup.PROPERTY_NAME.equals(id) && getJrgroup() != null){
				return getJrgroup().getName();
			} else if (JRDesignGroup.PROPERTY_EXPRESSION.equals(id) && getJrgroup() != null){
				return getJrgroup().getExpression();
			}
		}
		return null;
	}
	
	@Override
	public JRDesignSection getValue() {
		return (JRDesignSection) super.getValue();
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignSection jrsection = getValue();
		if(jrsection!=null){
			if(JRDesignSection.PROPERTY_PARTS.equals(id)){
				// Remove all old parts
				JRPart[] parts = jrsection.getParts();
				for(JRPart p : parts){
					jrsection.removePart(p);
				}
				// Add new ones
				List<JRPart> newParts = (List<JRPart>) value;
				for(JRPart p : newParts){
					jrsection.addPart(p);
				}
			} else if(JRDesignGroup.PROPERTY_NAME.equals(id) && getJrgroup() != null){
				getJrgroup().setName((String)value);
				this.getPropertyChangeSupport().firePropertyChange(JRDesignGroup.PROPERTY_NAME, false, true);
			} else if (JRDesignGroup.PROPERTY_EXPRESSION.equals(id) && getJrgroup() != null){
				getJrgroup().setExpression((JRExpression)value);
				this.getPropertyChangeSupport().firePropertyChange(JRDesignGroup.PROPERTY_EXPRESSION, false, true);
			}
		}
	}

	@Override
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	public boolean isGroupHeader() {
		BandTypeEnum type = (BandTypeEnum) getPropertyValue(PROPERTY_CONTAINER_TYPE);
		return BandTypeEnum.GROUP_HEADER.equals(type) && jrgroup!=null;
	}
	
	public boolean isGroupFooter() {
		BandTypeEnum type = (BandTypeEnum) getPropertyValue(PROPERTY_CONTAINER_TYPE);
		return BandTypeEnum.GROUP_FOOTER.equals(type) && jrgroup!=null;
	}
	
	public boolean isDetail() {
		BandTypeEnum type = (BandTypeEnum) getPropertyValue(PROPERTY_CONTAINER_TYPE);
		return BandTypeEnum.DETAIL.equals(type);
	}
	
	/**
	 * We want to display a meaningful name for this part.
	 * The name of the group is more than logical, but a better value for the label.
     * We use a custom properties for this
	 */
	@Override
	public String getDisplayText() {
		if(isDetail()){
			return "Content";
		}
		else if(isGroupFooter()){
			String grpName = jrgroup.getName();
			return ModelUtils.getReportPropertyValue(
					getJasperDesign(), 
					JSSPROPERTY_GROUPLABEL_PREFIX+grpName+JSSPROPERTY_GROUPLABEL_FOOTER_POSTFIX,
					jrgroup.getName());
		}
		else if(isGroupHeader()){
			String grpName = jrgroup.getName();
			return ModelUtils.getReportPropertyValue(
					getJasperDesign(), 
					JSSPROPERTY_GROUPLABEL_PREFIX+grpName+JSSPROPERTY_GROUPLABEL_HEADER_POSTFIX,
					jrgroup.getName());			
		}
		return "<UNDEFINED>";
	}

	@Override
	public Map<String, Object> getDefaultsMap() {
		return defaultsMap;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1, Map<String, Object> defaultsMap1) {
		descriptors=descriptors1;
		defaultsMap=defaultsMap1;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		validator = new GroupNameValidator();
		validator.setTargetNode(this);
		
		if(!isDetail()) {
			JSSTextPropertyDescriptor nameD = new JSSValidatedTextPropertyDescriptor(JRDesignGroup.PROPERTY_NAME, Messages.common_name, validator);
			nameD.setDescription(Messages.MGroup_name_description);
			desc.add(nameD);
	
			JRExpressionPropertyDescriptor expressionD = new JRExpressionPropertyDescriptor(JRDesignGroup.PROPERTY_EXPRESSION,Messages.common_expression);
			expressionD.setDescription(Messages.MGroup_expression_description);
			desc.add(expressionD);
		}
	}
	
	
	public void setJRGroup(JRGroup group){
		this.jrgroup = (JRDesignGroup)group;
	}
	
	public JRDesignGroup getJrgroup() {
		return jrgroup;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(JRDesignSection.PROPERTY_PARTS)){
			if (evt.getNewValue() == null && evt.getOldValue() != null){
				for(INode node : getChildren()){
					if (node.getValue() == evt.getOldValue()){
						removeChild((ANode)node);
						break;
					}
				}
			}
			if (evt.getNewValue() != null && evt.getOldValue() == null){
				JRDesignSection jrsection = getValue();
				int partIndex = jrsection.getPartsList().indexOf(evt.getNewValue());
				new MReportPart(this, (JRPart)evt.getNewValue(), partIndex);
			}
		}
		super.propertyChange(evt);
	}

	@Override
	public Object getAdapter(Class adapter) {
		if(ExpressionContext.class.equals(adapter)){
			return ExpressionEditorSupportUtil.getReportExpressionContext();
		}
		return super.getAdapter(adapter);
	}

}
