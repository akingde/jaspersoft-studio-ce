/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.style;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.ExternalStylesManager;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRTempalteStyleExpressionPropertyDescriptor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRReportTemplate;
import net.sf.jasperreports.engine.design.JRDesignReportTemplate;

/**
 * The Class MStyleTemplate. It will also listen on the change of the style expression to reload the style children when
 * it changes
 * 
 * @author Chicu Veaceslav & Orlandin Marco
 */
public class MStyleTemplate extends APropertyNode implements IPropertySource, ICopyable {
	
	/**
	 * Annotation used on the style expression to override the original expression in studio
	 */
	public static final String PATH_ANNOTATION = "@path"; 

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Icon used when the style can not be resolved
	 */
	private static ImageDescriptor styleNotFoundImage = JaspersoftStudioPlugin.getInstance()
			.getImageDescriptor("icons/resources/no_style_error.png");

	/**
	 * Array of the descriptors of the element
	 */
	private static IPropertyDescriptor[] descriptors;

	/**
	 * Timeout time to wait between the end of an expression change and the refresh of the 
	 * element content. Used to avoid to many refresh when the user write
	 */
	private static final int UPDATE_DELAY=500;

	/**
	 * The job that update the styles content in background
	 */
	private UpdateStyleJob updateStyleJob;
	
	/**
	 * Event used to ask the refresh of the node children, necessary when there is a resource
	 * change event
	 */
	public static final String FORCE_UPDATE_CHILDREN = "forceUpdateChildren";

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		JRTempalteStyleExpressionPropertyDescriptor sourceExpression = new JRTempalteStyleExpressionPropertyDescriptor(
				JRDesignReportTemplate.PROPERTY_SOURCE_EXPRESSION, Messages.MStyleTemplate_source_expression);
		sourceExpression.setDescription(Messages.MStyleTemplate_source_expression_description);
		desc.add(sourceExpression);

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#template");
	}

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("styletemplate"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Empty constructor used by the palett builder, do not remove
	 */
	public MStyleTemplate() {

	}

	/**
	 * Instantiates a new m style template.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrstyle
	 *          the jrstyle
	 * @param newIndex
	 *          the new index
	 */
	public MStyleTemplate(ANode parent, JRReportTemplate jrstyle, int newIndex) {
		super(parent, newIndex);
		super.setValue(jrstyle);
		setEditable(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		JRDesignReportTemplate jt = (JRDesignReportTemplate) getValue();
		if (jt != null && jt.getSourceExpression() != null && jt.getSourceExpression().getText() != null) {
			String expString = jt.getSourceExpression().getText();
			int indexStartComments = expString.indexOf("/*");
			int indexEndComments = indexStartComments != -1 ? expString.indexOf("*/", indexStartComments) : -1;
			while (indexStartComments != -1 && indexEndComments != -1){
				expString = expString.substring(0, indexStartComments) + expString.substring(indexEndComments + 2);
				expString = expString.trim();
				indexStartComments = expString.indexOf("/*");
				indexEndComments = indexStartComments != -1 ? expString.indexOf("*/", indexStartComments) : -1;
 			}
			return getIconDescriptor().getTitle() + "(" + expString + ")";
		}
		return getIconDescriptor().getTitle();
	}

	/**
	 * Return the image for this element, the image change if the style can not be resolved, in this way we can show
	 * something like an error decorator if the expression of the style is not solvable
	 */
	public ImageDescriptor getImagePath() {
		JRDesignReportTemplate jt = (JRDesignReportTemplate) getValue();
		if (jt != null && jt.getSourceExpression() != null && jt.getSourceExpression().getText() != null
				&& (ExternalStylesManager.isNotValuable(this) ||  !ExternalStylesManager.isTemplateValid(this))) {
			return styleNotFoundImage;
		}
		return getIconDescriptor().getIcon16();
	}

	/**
	 * Return the textual tooltip of the style. If its expression can not be solved an error message is also shown
	 */
	@Override
	public String getToolTip() {
		JRDesignReportTemplate jt = (JRDesignReportTemplate) getValue();
		if (jt != null && jt.getSourceExpression() != null && jt.getSourceExpression().getText() != null
				&& ExternalStylesManager.isNotValuable(this)) {
			return "The resource can not be found, fix the expression and reload the style to use it";
		} else
			return getIconDescriptor().getToolTip();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignReportTemplate jrTemplate = (JRDesignReportTemplate) getValue();
		if (id.equals(JRDesignReportTemplate.PROPERTY_SOURCE_EXPRESSION))
			return ExprUtil.getExpression(jrTemplate.getSourceExpression());

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignReportTemplate jrTemplate = (JRDesignReportTemplate) getValue();
		if (id.equals(JRDesignReportTemplate.PROPERTY_SOURCE_EXPRESSION)){
			//remove the old cached expression
			ExternalStylesManager.removeCachedStyle(getJasperConfiguration(), (JRReportTemplate)getValue());
			jrTemplate.setSourceExpression(ExprUtil.setValues(jrTemplate.getSourceExpression(), value));
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// If the expression change try to reload the style
		if (evt.getPropertyName().equals(JRDesignReportTemplate.PROPERTY_SOURCE_EXPRESSION)) {
			performUpdate();
		} else if (evt.getPropertyName().equals(FORCE_UPDATE_CHILDREN)){
			//asked for the refresh of the children
			refreshChildren();
		}
		super.propertyChange(evt);
	}

	/**
	 * Since the style don't see when its children are updated (because the the relation between style template and its
	 * inner styles is done only by our model, not by the jr structure). So when we add children to a style JR don't fire
	 * any event. Because of this to have a graphical Refresh we must fire the event manually to have the update and see
	 * the children
	 */
	private void fireChildrenChangeEvent() {
		// Need to be executed inside the graphic thread, but in synch way otherwise it could happen strange behavior
		// when two styles are resolved at the same time
		UIUtils.getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				PropertyChangeEvent event = new PropertyChangeEvent(getActualStyle(), "refresh", null, null);
				getPropertyChangeSupport().firePropertyChange(event);
			}
		});
	}

	/**
	 * Refresh the children of a template style by reloading them from the external styles cache
	 * 
	 * @return true if the style resource was found, false otherwise
	 */
	public boolean refreshChildren() {
		JasperReportsConfiguration jConf = getJasperConfiguration();
		if (jConf != null) {
			
			IFile project = (IFile) jConf.get(FileUtils.KEY_FILE);
			JRDesignReportTemplate jrTemplate = (JRDesignReportTemplate) getValue();

			// Clear the old children
			for (INode child : new ArrayList<INode>(getChildren())) {
				((ANode) child).setParent(null, -1);
			}
			getChildren().clear();

			String path = ExternalStylesManager.evaluateStyleExpression(jrTemplate, project, jConf);
			if (path != null) {
				boolean result = StyleTemplateFactory.createTemplateReference(this, path, -1, new HashSet<String>(), false);
				fireChildrenChangeEvent();
				return result;
			}
		}
		return false;
	}

	/**
	 * Job to update the panel UI when expression text changes or when caret is moved. This job is supposed to be delayed
	 * in order not to call UI-update events too often (avoiding flickering effects).
	 */
	private class UpdateStyleJob extends Job {

		public UpdateStyleJob() {
			super("RefreshStyles");
			setSystem(true);
		}

		@Override
		public IStatus run(final IProgressMonitor monitor) {
			UIUtils.getDisplay().syncExec(new Runnable() {

				@Override
				public void run() {
					JasperReportsConfiguration jConf = getJasperConfiguration();
					if (getParent() != null && getValue() != null && jConf != null) {
						refreshChildren();
					}
					monitor.done();
				}
			});
			return Status.OK_STATUS;
		}
	}

	/**
	 * This reference, used by some inner class
	 * 
	 * @return this reference
	 */
	private MStyleTemplate getActualStyle() {
		return this;
	}

	/**
	 * Create and schedule the background update thread and start it. If there was another thread created it means that
	 * the old one is no more necessary, so it is cancelled
	 */
	private void performUpdate() {
		if (updateStyleJob == null){
			updateStyleJob = new UpdateStyleJob();
		}
		updateStyleJob.cancel();
		updateStyleJob.schedule(UPDATE_DELAY);
	}

	/**
	 * Creates the jr template.
	 * 
	 * @return the jR design report template
	 */
	public static JRDesignReportTemplate createJRTemplate() {
		JRDesignReportTemplate jrDesignReportTemplate = new JRDesignReportTemplate();
		return jrDesignReportTemplate;
	}

	public ICopyable.RESULT isCopyable2(Object parent) {
		if (parent instanceof MStyles)
			return ICopyable.RESULT.COPYABLE;
		return ICopyable.RESULT.CHECK_PARENT;
	}

	@Override
	public boolean isCuttable(ISelection currentSelection) {
		return true;
	}

	@Override
	public void setValue(Object value) {
		super.setValue(value);
		performUpdate();
	}
}
