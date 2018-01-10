/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.report;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IActionBars;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.CompileAction;
import com.jaspersoft.studio.editor.gef.parts.JSSGraphicalViewerKeyHandler;
import com.jaspersoft.studio.editor.gef.parts.JasperDesignEditPartFactory;
import com.jaspersoft.studio.editor.gef.parts.MainDesignerRootEditPart;
import com.jaspersoft.studio.editor.gef.rulers.ReportRuler;
import com.jaspersoft.studio.editor.gef.rulers.ReportRulerProvider;
import com.jaspersoft.studio.editor.gef.ui.actions.RZoomComboContributionItem;
import com.jaspersoft.studio.editor.gef.ui.actions.ViewSettingsDropDownAction;
import com.jaspersoft.studio.editor.java2d.JSSScrollingGraphicalViewer;
import com.jaspersoft.studio.editor.outline.JDReportOutlineView;
import com.jaspersoft.studio.editor.outline.actions.CreateBandAction;
import com.jaspersoft.studio.editor.outline.actions.CreateConditionalStyleAction;
import com.jaspersoft.studio.editor.outline.actions.CreateDatasetAction;
import com.jaspersoft.studio.editor.outline.actions.CreateDetailBandAction;
import com.jaspersoft.studio.editor.outline.actions.CreateDetailBandActionOnDetail;
import com.jaspersoft.studio.editor.outline.actions.CreateGroupAction;
import com.jaspersoft.studio.editor.outline.actions.CreateGroupFooterAction;
import com.jaspersoft.studio.editor.outline.actions.CreateGroupHeaderAction;
import com.jaspersoft.studio.editor.outline.actions.CreateParameterAction;
import com.jaspersoft.studio.editor.outline.actions.CreateParameterSetAction;
import com.jaspersoft.studio.editor.outline.actions.CreateScriptletAction;
import com.jaspersoft.studio.editor.outline.actions.CreateSortFieldAction;
import com.jaspersoft.studio.editor.outline.actions.CreateStyleAction;
import com.jaspersoft.studio.editor.outline.actions.CreateStyleTemplateAction;
import com.jaspersoft.studio.editor.outline.actions.CreateVariableAction;
import com.jaspersoft.studio.editor.outline.actions.DeleteGroupReportAction;
import com.jaspersoft.studio.editor.outline.actions.HideDefaultVariablesAction;
import com.jaspersoft.studio.editor.outline.actions.HideDefaultsParametersAction;
import com.jaspersoft.studio.editor.outline.actions.RefreshTemplateStyleExpression;
import com.jaspersoft.studio.editor.outline.actions.RefreshTemplateStyleReference;
import com.jaspersoft.studio.editor.outline.actions.ResetStyleAction;
import com.jaspersoft.studio.editor.outline.actions.SaveStyleAsTemplateAction;
import com.jaspersoft.studio.editor.outline.actions.SortParametersAction;
import com.jaspersoft.studio.editor.outline.actions.SortVariablesAction;
import com.jaspersoft.studio.editor.outline.actions.field.CreateFieldAction;
import com.jaspersoft.studio.editor.outline.actions.field.CreateFieldsContainerAction;
import com.jaspersoft.studio.editor.outline.actions.field.DeleteFieldsAllGroupAction;
import com.jaspersoft.studio.editor.outline.actions.field.DeleteFieldsGroupAction;
import com.jaspersoft.studio.editor.outline.actions.field.ShowFieldsTreeAction;
import com.jaspersoft.studio.editor.outline.actions.field.SortFieldsAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.plugin.ExtensionManager;
import com.jaspersoft.studio.preferences.RulersGridPreferencePage;
import com.jaspersoft.studio.property.dataset.dialog.ContextualDatasetAction;
import com.jaspersoft.studio.property.dataset.dialog.DatasetAction;
import com.jaspersoft.studio.property.section.report.action.PageFormatAction;
import com.jaspersoft.studio.property.section.report.action.PageRemoveMarginsAction;
import com.jaspersoft.studio.utils.AContributorAction;
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

		Boolean isRulerVisible = jrContext.getPropertyBoolean(RulersGridPreferencePage.P_PAGE_RULERGRID_SHOWRULER);

		graphicalViewer.setProperty(RulerProvider.PROPERTY_RULER_VISIBILITY, isRulerVisible);

		createAdditionalActions();
		graphicalViewer.setKeyHandler(new JSSGraphicalViewerKeyHandler(graphicalViewer));
		if (graphicalViewer instanceof JSSScrollingGraphicalViewer) {
			JSSScrollingGraphicalViewer jssViewer = (JSSScrollingGraphicalViewer) graphicalViewer;
			jssViewer.addSelectionOverrider(new ParentSelectionOverrider(IContainer.class, true));
			jssViewer.addSelectionOverrider(new MarqueeSelectionOverrider());
		}
	}

	@Override
	protected List<String> getIgnorePalleteElements() {
		return null;
	}

	protected JDReportOutlineView getOutlineView() {
		// Rebuild the outline only if it's closed or disposed
		if (outlinePage == null || outlinePage.isDisposed()) {
			TreeViewer viewer = new TreeViewer();
			outlinePage = new JDReportOutlineView(this, viewer) {
				protected void initActions(ActionRegistry registry, IActionBars bars) {
					String id = DeleteGroupReportAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = SortVariablesAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = SortParametersAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = SortFieldsAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = ShowFieldsTreeAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = DeleteFieldsGroupAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = DeleteFieldsAllGroupAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = HideDefaultsParametersAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = HideDefaultVariablesAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = CreateFieldAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = CreateFieldsContainerAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = CreateSortFieldAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = CreateVariableAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = CreateScriptletAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = CreateParameterAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = CreateParameterSetAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = CreateGroupAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = CreateDatasetAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = CreateStyleAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = CreateConditionalStyleAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = SaveStyleAsTemplateAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = RefreshTemplateStyleExpression.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = RefreshTemplateStyleReference.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = ResetStyleAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = CreateStyleTemplateAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = CreateBandAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = CreateDetailBandAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = CreateDetailBandActionOnDetail.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = PageFormatAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = PageRemoveMarginsAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = DatasetAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = CompileAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));
				}
			};
		}
		return outlinePage;
	}

	protected void createEditorActions(ActionRegistry registry) {
		List<String> selectionActions = getSelectionActions();

		// Create the action on the dataset element
		createDatasetAndStyleActions(registry);

		IAction action = new CreateBandAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateBandAction.ID);

		action = new CreateDetailBandAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateDetailBandAction.ID);

		action = new CreateDetailBandActionOnDetail(this);
		registry.registerAction(action);
		selectionActions.add(CreateDetailBandActionOnDetail.ID);

		action = new CreateGroupHeaderAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateGroupHeaderAction.ID);

		action = new CreateGroupFooterAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateGroupFooterAction.ID);

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

		action = new PageRemoveMarginsAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new DatasetAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new ContextualDatasetAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new CompileAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());
	}

	@Override
	public void contributeItemsToEditorTopToolbar(IToolBarManager toolbarManager) {
		this.topToolbarManager = toolbarManager;
		ActionContributionItem item = new ActionContributionItem(getActionRegistry().getAction(CompileAction.ID));
		act4TextIcon.add(item);
		toolbarManager.add(item);
		item = new ActionContributionItem(getActionRegistry().getAction(DatasetAction.ID));
		act4TextIcon.add(item);
		toolbarManager.add(item);
		toolbarManager.add(new Separator());
		toolbarManager.add(getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));
		toolbarManager.add(getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));
		if (zoomItem != null) {
			zoomItem.dispose();
			zoomItem = null;
		}
		GraphicalViewer graphicalViewer = getGraphicalViewer();
		ZoomManager property = (ZoomManager) graphicalViewer.getProperty(ZoomManager.class.toString());
		if (property != null) {
			zoomItem = new RZoomComboContributionItem(property);
			toolbarManager.add(zoomItem);
		}
		toolbarManager.add(new Separator());
		// Contributed actions
		List<AContributorAction> contributedActions = JaspersoftStudioPlugin.getExtensionManager().getActions();
		for (AContributorAction a : contributedActions) {
			a.setJrConfig((JasperReportsConfiguration) getGraphicalViewer().getProperty("JRCONTEXT"));
			item = new ActionContributionItem(a);
			act4TextIcon.add(item);
			toolbarManager.add(item);
		}
		// Global "View" menu items
		toolbarManager.add(new ViewSettingsDropDownAction(getActionRegistry()));
		setTextIcon();
	}

}
