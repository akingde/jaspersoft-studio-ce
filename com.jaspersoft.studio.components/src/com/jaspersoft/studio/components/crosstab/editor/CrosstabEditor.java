/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.components.crosstab.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.jface.action.IAction;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.columngroup.action.CreateColumnGroupAction;
import com.jaspersoft.studio.components.crosstab.model.header.action.CreateCrosstabHeaderAction;
import com.jaspersoft.studio.components.crosstab.model.measure.action.CreateMeasureAction;
import com.jaspersoft.studio.components.crosstab.model.nodata.action.CreateCrosstabWhenNoDataAction;
import com.jaspersoft.studio.components.crosstab.model.rowgroup.action.CreateRowGroupAction;
import com.jaspersoft.studio.editor.gef.parts.JasperDesignEditPartFactory;
import com.jaspersoft.studio.editor.gef.parts.MainDesignerRootEditPart;
import com.jaspersoft.studio.editor.gef.rulers.ReportRuler;
import com.jaspersoft.studio.editor.gef.rulers.ReportRulerProvider;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.preferences.RulersGridPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/*
 * The Class CrosstabEditor.
 * 
 * @author Chicu Veaceslav
 */
public class CrosstabEditor extends AbstractVisualEditor {
	public CrosstabEditor(JasperReportsConfiguration jrContext) {
		super(jrContext);
		setPartName(Messages.CrosstabEditor_crosstab);
		setPartImage(JaspersoftStudioPlugin.getImage(MCrosstab
				.getIconDescriptor().getIcon16()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#configureGraphicalViewer()
	 */
	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();

		GraphicalViewer graphicalViewer = getGraphicalViewer();
		MainDesignerRootEditPart rootEditPart = new MainDesignerRootEditPart();

		graphicalViewer.setRootEditPart(rootEditPart);
		// set EditPartFactory
		graphicalViewer.setEditPartFactory(new JasperDesignEditPartFactory());

		// set rulers providers
		RulerProvider provider = new ReportRulerProvider(new ReportRuler(true,
				RulerProvider.UNIT_PIXELS));
		graphicalViewer.setProperty(RulerProvider.PROPERTY_HORIZONTAL_RULER,
				provider);

		provider = new ReportRulerProvider(new ReportRuler(false,
				RulerProvider.UNIT_PIXELS));
		graphicalViewer.setProperty(RulerProvider.PROPERTY_VERTICAL_RULER,
				provider);

		Boolean isRulerVisible = JaspersoftStudioPlugin
				.getInstance()
				.getPreferenceStore()
				.getBoolean(RulersGridPreferencePage.P_PAGE_RULERGRID_SHOWRULER);

		graphicalViewer.setProperty(RulerProvider.PROPERTY_RULER_VISIBILITY,
				isRulerVisible);

		createAdditionalActions();
		graphicalViewer.setKeyHandler(new GraphicalViewerKeyHandler(
				graphicalViewer));
	}

	@Override
	protected List<String> getIgnorePalleteElements() {
		List<String> lst = new ArrayList<String>();
		lst.add(MCrosstab.class.getCanonicalName());
		lst.add("com.jaspersoft.studio.components.chart.model.MChart"); //$NON-NLS-1$
		return lst;
	}

	@Override
	protected void createEditorActions(ActionRegistry registry) {
		IAction action = new CreateMeasureAction(this);
		registry.registerAction(action);
		@SuppressWarnings("unchecked")
		List<String> selectionActions = getSelectionActions();
		selectionActions.add(CreateMeasureAction.ID);

		action = new CreateColumnGroupAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateColumnGroupAction.ID);

		action = new CreateRowGroupAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateRowGroupAction.ID);

		action = new CreateCrosstabHeaderAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateCrosstabHeaderAction.ID);

		action = new CreateCrosstabWhenNoDataAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateCrosstabWhenNoDataAction.ID);

	}

}
