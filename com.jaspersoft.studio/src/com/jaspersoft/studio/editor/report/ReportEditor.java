/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.editor.report;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IActionBars;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.gef.parts.JasperDesignEditPartFactory;
import com.jaspersoft.studio.editor.gef.parts.MainDesignerRootEditPart;
import com.jaspersoft.studio.editor.gef.rulers.ReportRuler;
import com.jaspersoft.studio.editor.gef.rulers.ReportRulerProvider;
import com.jaspersoft.studio.editor.outline.JDReportOutlineView;
import com.jaspersoft.studio.editor.outline.actions.CreateBandAction;
import com.jaspersoft.studio.editor.outline.actions.CreateConditionalStyleAction;
import com.jaspersoft.studio.editor.outline.actions.CreateDatasetAction;
import com.jaspersoft.studio.editor.outline.actions.CreateFieldAction;
import com.jaspersoft.studio.editor.outline.actions.CreateGroupAction;
import com.jaspersoft.studio.editor.outline.actions.CreateParameterAction;
import com.jaspersoft.studio.editor.outline.actions.CreateScriptletAction;
import com.jaspersoft.studio.editor.outline.actions.CreateSortFieldAction;
import com.jaspersoft.studio.editor.outline.actions.CreateStyleAction;
import com.jaspersoft.studio.editor.outline.actions.CreateStyleTemplateAction;
import com.jaspersoft.studio.editor.outline.actions.CreateVariableAction;
import com.jaspersoft.studio.editor.outline.actions.DeleteGroupReportAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.plugin.ExtensionManager;
import com.jaspersoft.studio.preferences.RulersGridPreferencePage;
import com.jaspersoft.studio.property.dataset.dialog.DatasetAction;
import com.jaspersoft.studio.property.section.report.PageFormatAction;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/*
 * The Class ReportEditor.
 * 
 * @author Chicu Veaceslav
 */
public class ReportEditor extends AbstractVisualEditor {

	/** The Constant ID. */
	public static final String ID = ReportEditor.class.getName();

	/**
	 * Instantiates a new report editor.
	 */
	public ReportEditor(JasperReportsConfiguration jrContext) {
		super(jrContext);
		setPartName(Messages.common_main_report);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#configureGraphicalViewer()
	 */
	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		getGraphicalViewer().getControl().setBackground(ColorConstants.button);

		GraphicalViewer graphicalViewer = getGraphicalViewer();
		MainDesignerRootEditPart rootEditPart = new MainDesignerRootEditPart();

		graphicalViewer.setRootEditPart(rootEditPart);
		// set EditPartFactory
		graphicalViewer.setEditPartFactory(new JasperDesignEditPartFactory());

		// set rulers providers
		RulerProvider provider = new ReportRulerProvider(new ReportRuler(true, RulerProvider.UNIT_PIXELS));
		graphicalViewer.setProperty(RulerProvider.PROPERTY_HORIZONTAL_RULER, provider);

		provider = new ReportRulerProvider(new ReportRuler(false, RulerProvider.UNIT_PIXELS));
		graphicalViewer.setProperty(RulerProvider.PROPERTY_VERTICAL_RULER, provider);

		Boolean isRulerVisible = JaspersoftStudioPlugin.getInstance().getPreferenceStore()
				.getBoolean(RulersGridPreferencePage.P_PAGE_RULERGRID_SHOWRULER);

		graphicalViewer.setProperty(RulerProvider.PROPERTY_RULER_VISIBILITY, isRulerVisible);

		createAdditionalActions();
		graphicalViewer.setKeyHandler(new GraphicalViewerKeyHandler(graphicalViewer));
	}

	@Override
	protected List<String> getIgnorePalleteElements() {
		return null;
	}

	protected JDReportOutlineView getOutlineView() {
		// if (outlinePage == null) {
		TreeViewer viewer = new TreeViewer();
		//viewer.setSelectionManager(new JSelectionManager());
		outlinePage = new JDReportOutlineView(this, viewer) {
			protected void initActions(ActionRegistry registry, IActionBars bars) {
				String id = DeleteGroupReportAction.ID;
				bars.setGlobalActionHandler(id, registry.getAction(id));

				id = CreateFieldAction.ID;
				bars.setGlobalActionHandler(id, registry.getAction(id));

				id = CreateSortFieldAction.ID;
				bars.setGlobalActionHandler(id, registry.getAction(id));

				id = CreateVariableAction.ID;
				bars.setGlobalActionHandler(id, registry.getAction(id));

				id = CreateScriptletAction.ID;
				bars.setGlobalActionHandler(id, registry.getAction(id));

				id = CreateParameterAction.ID;
				bars.setGlobalActionHandler(id, registry.getAction(id));

				id = CreateGroupAction.ID;
				bars.setGlobalActionHandler(id, registry.getAction(id));

				id = CreateDatasetAction.ID;
				bars.setGlobalActionHandler(id, registry.getAction(id));

				id = CreateStyleAction.ID;
				bars.setGlobalActionHandler(id, registry.getAction(id));

				id = CreateConditionalStyleAction.ID;
				bars.setGlobalActionHandler(id, registry.getAction(id));

				id = CreateStyleTemplateAction.ID;
				bars.setGlobalActionHandler(id, registry.getAction(id));

				id = CreateBandAction.ID;
				bars.setGlobalActionHandler(id, registry.getAction(id));

				id = PageFormatAction.ID;
				bars.setGlobalActionHandler(id, registry.getAction(id));

				id = DatasetAction.ID;
				bars.setGlobalActionHandler(id, registry.getAction(id));
			}
		};
		// }
		return outlinePage;
	}

	protected void createEditorActions(ActionRegistry registry) {
		IAction action = new CreateFieldAction(this);
		registry.registerAction(action);
		List<String> selectionActions = getSelectionActions();
		selectionActions.add(CreateFieldAction.ID);

		action = new CreateSortFieldAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateSortFieldAction.ID);

		action = new CreateVariableAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateVariableAction.ID);

		action = new CreateScriptletAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateScriptletAction.ID);

		action = new CreateParameterAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateParameterAction.ID);

		action = new CreateGroupAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateGroupAction.ID);

		action = new CreateDatasetAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateDatasetAction.ID);

		action = new CreateStyleAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateStyleAction.ID);

		action = new CreateConditionalStyleAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateConditionalStyleAction.ID);

		action = new CreateStyleTemplateAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateStyleTemplateAction.ID);

		action = new CreateBandAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateBandAction.ID);

		ExtensionManager m = JaspersoftStudioPlugin.getExtensionManager();
		List<Action> lst = m.getActions(this);
		for (Action act : lst) {
			action = act;
			registry.registerAction(action);
			selectionActions.add(act.getId());
		}

		action = new DeleteGroupReportAction(this);
		registry.registerAction(action);
		selectionActions.add(DeleteGroupReportAction.ID);

		action = new PageFormatAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new DatasetAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());
	}
}
