/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.actions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.ExternalStylesManager;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.model.style.MStyleTemplate;
import com.jaspersoft.studio.model.style.MStyleTemplateReference;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Action to reload an external template reference file, the difference between an
 * external style template and an external style template reference is the first use
 * expressions in the path, the seconds not
 * 
 * @author Orlandin Marco
 *
 */
public class RefreshTemplateStyleReference extends ACachedSelectionAction {

	/** The Constant ID. */
	public static final String ID = "refresh_template_style_reference"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 */
	public RefreshTemplateStyleReference(IWorkbenchPart part) {
		super(part);
	}

	/**
	 * Initializes this action's text and images.
	 */
	@Override
	protected void init() {
		super.init();
		setText(Messages.RefreshTemplateStyleExpression_title);
		setToolTipText(Messages.RefreshTemplateStyleExpression_tooltip);
		setId(RefreshTemplateStyleReference.ID);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/refresh_style_action.png")); //$NON-NLS-1$
		setEnabled(false);
	}

	/**
	 * Enable only if there is at least one style that can be exported
	 */
	@Override
	protected boolean calculateEnabled() {
		return !getSelectedStyles().isEmpty();
	}

	@Override
	public void run() {
		List<MStyleTemplateReference> references = getSelectedStyles();
		HashSet<MStyleTemplate> refreshedTemplate = new HashSet<MStyleTemplate>();
		JasperReportsConfiguration jconfig = null;
		for(MStyleTemplateReference template : references){
			MStyleTemplate parentTemplate = getParentTemplate(template);
			//if the style is under a template force the update of the cache of that template
			if (template != null && !refreshedTemplate.contains(parentTemplate)){
				ExternalStylesManager.refreshStyleReference(template, parentTemplate);
				refreshedTemplate.add(parentTemplate);
			} else {
				ExternalStylesManager.refreshStyleReference(template, null);
			}
			if (jconfig == null){
				jconfig = template.getJasperConfiguration();
			}
			//Need to manually refresh the child nodes
			template.refreshChildren();
		}
		if (jconfig != null){
			jconfig.refreshCachedStyles();
		}
	}
	
	/**
	 * If the passed style is inside a style template return it, otherwise return null
	 * 
	 * @param template the template reference
	 * @return the parent of the template reference, or null if not found
	 */
	protected MStyleTemplate getParentTemplate(MStyleTemplateReference template){
		ANode parent = template.getParent();
		while(parent != null){
			if (parent instanceof MStyleTemplate){
				return (MStyleTemplate)parent;
			} else if (parent instanceof MStyle){
				break;
			} else {
				parent = parent.getParent();
			}
		}
		return null;
	}
	
	/**
	 * Return the list of all the selected Template styles reference. 
	 * 
	 * @return a not null list of MStyleTemplate
	 */
	private List<MStyleTemplateReference> getSelectedStyles(){
		List<Object> templates = editor.getSelectionCache().getSelectionModelForType(MStyleTemplateReference.class);
		List<MStyleTemplateReference> result = new ArrayList<MStyleTemplateReference>();
		for (Object template : templates){
			result.add((MStyleTemplateReference)template);
		}
		return result;
	}
}
