/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.model;

import java.beans.PropertyChangeEvent;

import org.eclipse.jface.util.IPropertyChangeListener;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.jasper.JSSDrawVisitor;
import com.jaspersoft.studio.preferences.fonts.FontsPreferencePage;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.IDisposeListener;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FilePrefUtil;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.design.JRDesignElement;

/**
 * Root used on report elements. It add to the standard root
 * a listener to refresh the graphical elements when a global property changes.
 * Since  global properties can have impact on the graphical appearance (ie font size) this
 * will get when a property is changed and fire and event to every element in the report that
 * force it to refresh. It is not necessary to use this on a subeditor node, because the event
 * is fired on every jr elements of the main report, so it will arrive to every node listening that
 * element. The refresh is done also when something change in the font storage, since removing/editing
 * a font could reflect on the graphical elements
 * 
 * @author Orlandin Marco
 *
 */
public class MReportRoot extends MRoot {
	
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	/**
	 * Listener used to refresh the elements, the refresh is done into an asynch thread
	 */
	private IPropertyChangeListener preferenceListener = new IPropertyChangeListener() {
		
		public void propertyChange(org.eclipse.jface.util.PropertyChangeEvent event) {
				if (getJasperDesign() != null && needRefresh(event)) {	
					final PropertyChangeEvent changeEvent = new PropertyChangeEvent(getJasperDesign(), MGraphicElement.FORCE_GRAPHICAL_REFRESH, false, true);
					UIUtils.getDisplay().asyncExec(new Runnable() {
						
						@Override
						public void run() {
							//If available force the refresh of the drawer cache
							JSSDrawVisitor visitor = (JSSDrawVisitor)getJasperConfiguration().getValue(JasperReportsConfiguration.KEY_DRAWER);
							if (visitor != null){
								visitor.refreshFontsCache();
							}
							for(JRDesignElement element : ModelUtils.getAllElements(getJasperDesign())){
								element.getEventSupport().firePropertyChange(changeEvent);
							}
						}
					});
				} 
		}
	};
	
	public MReportRoot(JasperReportsConfiguration jConfig, Object value) {
		super(null, value);
		setJasperConfiguration(jConfig);
		JaspersoftStudioPlugin.getInstance().addPreferenceListener(preferenceListener);
		//Add a dispose listener to the jconfig to remove the previous one when the report is closed
		jConfig.addDisposeListener(new IDisposeListener() {
			
			@Override
			public void dispose() {
				JaspersoftStudioPlugin.getInstance().removePreferenceListener(preferenceListener);	
			}
		});
	}
	
	/**
	 * Check if the change on the preferences require a graphical refresh of the elements
	 * 
	 * @param event the event coming from the preferences
	 * @return true if the visual elements should be refreshed, false otherwise
	 */
	protected boolean needRefresh(org.eclipse.jface.util.PropertyChangeEvent event){
		String property = event.getProperty();
		return property.equals(FilePrefUtil.NET_SF_JASPERREPORTS_JRPROPERTIES) || 
							property.equals(FontsPreferencePage.FPP_FONT_LIST);
	}
}
