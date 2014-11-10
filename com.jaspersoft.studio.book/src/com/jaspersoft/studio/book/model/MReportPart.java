package com.jaspersoft.studio.book.model;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRPart;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.base.JRBaseSubreport;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignPart;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.part.PartComponent;
import net.sf.jasperreports.engine.part.PartEvaluationTime;
import net.sf.jasperreports.engine.part.StandardPartEvaluationTime;
import net.sf.jasperreports.engine.type.PartEvaluationTimeType;
import net.sf.jasperreports.parts.subreport.StandardSubreportPartComponent;
import net.sf.jasperreports.parts.subreport.SubreportPartComponent;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.book.JRBookActivator;
import com.jaspersoft.studio.book.descriptors.ButtonsPropertyDescriptor;
import com.jaspersoft.studio.book.descriptors.JSSEvaluationComboPropertyDescriptor;
import com.jaspersoft.studio.book.messages.Messages;
import com.jaspersoft.studio.book.widgets.SPPartParametersButton;
import com.jaspersoft.studio.book.widgets.SPPartReturnValuesButton;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.ExpressionEditorSupportUtil;
import com.jaspersoft.studio.help.HelpReferenceBuilder;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.properties.JPropertiesPropertyDescriptor;

public class MReportPart extends APropertyNode {
	
	private static final ImageDescriptor standardReportImgDesc = JRBookActivator.getDefault().getImageDescriptor("/icons/report_loading_preview.png");; //$NON-NLS-1$
	
	public static final String COMPONENT_NAMESPACE = "http://jasperreports.sourceforge.net/jasperreports/parts"; //$NON-NLS-1$
	public static final String COMPONENT_NAMESPACE_PREFIX = "p"; //$NON-NLS-1$
	public static final String COMPONENT_NAME = "subreportPart"; //$NON-NLS-1$

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	public static final String PROPERTY_EVALTIME_TYPE = "part_evaluationtime_type"; //$NON-NLS-1$
	public static final String PROPERTY_EVALTIME_GROUP = "part_evaluationtime_group"; //$NON-NLS-1$
	public static final String COMPONENT_EXPRESSION = "component_expression"; //$NON-NLS-1$
	public static final String PROPERTY_MAP = "property_map"; //$NON-NLS-1$
	
	// The icon descriptor
	private static IIconDescriptor iconDescriptor;
	// Array of property descriptors
	private static IPropertyDescriptor[] descriptors;
	// A map for propery defaults
	private static Map<String, Object> defaultsMap;
	
	public MReportPart(ANode parent, JRPart bookpart, int newIndex) {
		super(parent, newIndex);
		setValue(bookpart);
	}

	@Override
	public JRDesignPart getValue() {
		return (JRDesignPart) super.getValue();
	}
	
	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("reportpart"); //$NON-NLS-1$
		return iconDescriptor;
	}
	
	@Override
	public Object getPropertyValue(Object id) {
		JRDesignPart jrpart = (JRDesignPart) getValue();
		if(jrpart!=null) {
			PartEvaluationTime evalTime = jrpart.getEvaluationTime();
			if(id.equals(JRDesignPart.PROPERTY_COMPONENT)) {
				return jrpart.getComponent();
			} 
			if  (id.equals(JRBaseSubreport.PROPERTY_USING_CACHE)){
				if (jrpart.getComponent() != null && jrpart.getComponent() instanceof StandardSubreportPartComponent){
					StandardSubreportPartComponent component = getSubreportComponent();
					return component.getUsingCache();
				}
			}
			if(id.equals(JRDesignPart.PROPERTY_COMPONENT_KEY)) {
				return jrpart.getComponentKey();
			}
			if(id.equals(JRDesignPart.PROPERTY_EVALUATION_TIME)) {
				return evalTime;
			}
			if(id.equals(JRDesignPart.PROPERTY_PART_NAME_EXPRESSION)){
				return jrpart.getPartNameExpression();
			}
			if(id.equals(JRDesignPart.PROPERTY_PRINT_WHEN_EXPRESSION)){
				return jrpart.getPrintWhenExpression();
			}
			if(id.equals(PROPERTY_EVALTIME_GROUP)){
				if(evalTime!=null){
					return evalTime.getEvaluationGroup();
				}
			}
			if(id.equals(PROPERTY_EVALTIME_TYPE)){
				if(evalTime!=null){
					return evalTime.getEvaluationTimeType();
				}
			}
			if (id.equals(COMPONENT_EXPRESSION)){
				PartComponent component = jrpart.getComponent();
				if (component != null && component instanceof StandardSubreportPartComponent){
					StandardSubreportPartComponent subComponent = (StandardSubreportPartComponent)component;
					return subComponent.getExpression();
				}
			}
			if(id.equals(PROPERTY_MAP)){
				return jrpart.getPropertiesMap().cloneProperties();
			}
		}
		return null;
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignPart jrpart = (JRDesignPart) getValue();
		if(jrpart!=null) {
			if(id.equals(JRDesignPart.PROPERTY_COMPONENT)) {
				jrpart.setComponent((PartComponent) value);
			}
			else if(id.equals(JRDesignPart.PROPERTY_COMPONENT_KEY)) {
				jrpart.setComponentKey((ComponentKey) value);
			}
			else if(id.equals(JRDesignPart.PROPERTY_EVALUATION_TIME)) {
				jrpart.setEvaluationTime((PartEvaluationTime) value);
			}
			else if(id.equals(JRDesignPart.PROPERTY_PART_NAME_EXPRESSION)){
				jrpart.setPartNameExpression((JRExpression) value);
			}
			else if(id.equals(JRDesignPart.PROPERTY_PRINT_WHEN_EXPRESSION)){
				jrpart.setPrintWhenExpression((JRExpression) value);
			}
			else if(id.equals(PROPERTY_EVALTIME_TYPE)){
				jrpart.setEvaluationTime((StandardPartEvaluationTime)value);
			} 
			else if (id.equals(JRBaseSubreport.PROPERTY_USING_CACHE)){
				if (jrpart.getComponent() != null && jrpart.getComponent() instanceof StandardSubreportPartComponent){
					StandardSubreportPartComponent component = getSubreportComponent();
					component.setUsingCache((Boolean)value);
				}
			}
			else if (id.equals(COMPONENT_EXPRESSION)){
				PartComponent component = jrpart.getComponent();
				if (component != null && component instanceof StandardSubreportPartComponent){
					StandardSubreportPartComponent subComponent = getSubreportComponent();
					subComponent.setExpression((JRExpression) value);
					this.getPropertyChangeSupport().firePropertyChange(COMPONENT_EXPRESSION, false, true);
				}
			} else if (id.equals(PROPERTY_MAP)) {
					JRPropertiesMap v = (JRPropertiesMap) value;
					String[] names = jrpart.getPropertiesMap().getPropertyNames();
					for (int i = 0; i < names.length; i++) {
						jrpart.getPropertiesMap().removeProperty(names[i]);
					}
					names = v.getPropertyNames();
					for (int i = 0; i < names.length; i++)
						jrpart.getPropertiesMap().setProperty(names[i], v.getProperty(names[i]));
					this.getPropertyChangeSupport().firePropertyChange(PROPERTY_MAP, false, true);
				}
			} 
	}
	
	public StandardSubreportPartComponent getSubreportComponent(){
		JRDesignPart jrpart = (JRDesignPart) getValue();
		return (StandardSubreportPartComponent)jrpart.getComponent();
	}

	
	@Override
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	@Override
	public String getDisplayText() {
		// Try to produce a name from the subreport component
		JRDesignPart value = getValue();
		if(value!=null){
			PartComponent component = value.getComponent();
			if(component instanceof SubreportPartComponent) {
				JRExpression subreportExp = ((SubreportPartComponent)component).getExpression();
				if(subreportExp!=null){
					return subreportExp.getText();
				}
			}
		}
		// fallback to a generic one
		int index = getParent().getChildren().indexOf(this);	
		return NLS.bind("<Part {0}>",(index+1)); //$NON-NLS-1$
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
		JRExpressionPropertyDescriptor printWhenExpD = new JRExpressionPropertyDescriptor(JRDesignPart.PROPERTY_PRINT_WHEN_EXPRESSION, Messages.MReportPart_printWhen);
		printWhenExpD.setDescription(Messages.MReportPart_printWhenTooltip);
		printWhenExpD.setHelpRefBuilder(new HelpReferenceBuilder("net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#printWhenExpression")); //$NON-NLS-1$
		desc.add(printWhenExpD);
		
		JRExpressionPropertyDescriptor partNameExpression = new JRExpressionPropertyDescriptor(JRDesignPart.PROPERTY_PART_NAME_EXPRESSION, Messages.MReportPart_partName);
		partNameExpression.setDescription(Messages.MReportPart_partNameTooltip);
		partNameExpression.setHelpRefBuilder(new HelpReferenceBuilder("net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#partNameExpression")); //$NON-NLS-1$
		desc.add(partNameExpression);
		
		JSSEvaluationComboPropertyDescriptor evaluationTimeD = new JSSEvaluationComboPropertyDescriptor(PROPERTY_EVALTIME_TYPE, Messages.common_evaluation_time, new String[]{});
		evaluationTimeD.setDescription(Messages.MReportPart_evaluationTimeTooltip);
		desc.add(evaluationTimeD);
		
		JRExpressionPropertyDescriptor componentExpression = new JRExpressionPropertyDescriptor(COMPONENT_EXPRESSION, Messages.MReportPart_componentExpression);
		componentExpression.setDescription(Messages.MReportPart_componentExpressionTooltip);
		componentExpression.setHelpRefBuilder(new HelpReferenceBuilder("net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#partNameExpression")); //$NON-NLS-1$
		desc.add(componentExpression);
		
		JPropertiesPropertyDescriptor propertiesMapD = new JPropertiesPropertyDescriptor(PROPERTY_MAP, com.jaspersoft.studio.messages.Messages.common_properties);
		propertiesMapD.setDescription(com.jaspersoft.studio.messages.Messages.common_properties);
		desc.add(propertiesMapD);
		
		ButtonsPropertyDescriptor returnDescriptor = new ButtonsPropertyDescriptor(JRDesignSubreport.PROPERTY_RETURN_VALUES, SPPartReturnValuesButton.class);
		returnDescriptor.setDescription(Messages.MReportPart_returnDescription);
		desc.add(returnDescriptor);
		
		ButtonsPropertyDescriptor parametersDescriptor = new ButtonsPropertyDescriptor(JRDesignSubreport.PROPERTY_PARAMETERS, SPPartParametersButton.class);
		parametersDescriptor.setDescription(Messages.MReportPart_parametersDescription);
		desc.add(parametersDescriptor);
		
		CheckBoxPropertyDescriptor usingCache = new CheckBoxPropertyDescriptor(JRBaseSubreport.PROPERTY_USING_CACHE, Messages.MReportPart_cacheLabel);
		usingCache.setShowTextOnButton(false);
		usingCache.setDescription(Messages.MReportPart_cacheDescription);
		desc.add(usingCache);
		
		defaultsMap.put(PROPERTY_EVALTIME_TYPE, PartEvaluationTimeType.NOW);
		defaultsMap.put(PROPERTY_EVALTIME_GROUP, null);
		defaultsMap.put(JRDesignPart.PROPERTY_PART_NAME_EXPRESSION, null);
		defaultsMap.put(JRDesignPart.PROPERTY_PRINT_WHEN_EXPRESSION, null);
		
		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#part"); //$NON-NLS-1$
	}
	
	public static JRDesignPart createJRElement(JRDesignExpression exp) {
		JRDesignPart part = new JRDesignPart();
		StandardSubreportPartComponent component = new StandardSubreportPartComponent();
		component.setExpression(exp);
		part.setComponent(component);
		part.setComponentKey(new ComponentKey(COMPONENT_NAMESPACE, COMPONENT_NAMESPACE_PREFIX, COMPONENT_NAME));
		return part;
	}

	public ImageDescriptor getImageDescriptor(){
		return standardReportImgDesc;
	}
	
	@Override
	public Object getAdapter(Class adapter) {
		if(ExpressionContext.class.equals(adapter)){
			return ExpressionEditorSupportUtil.getReportExpressionContext();
		}
		return super.getAdapter(adapter);
	}
}
