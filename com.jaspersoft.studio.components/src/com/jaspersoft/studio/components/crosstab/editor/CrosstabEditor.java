/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.components.crosstab.action.CrosstabCutAction;
import com.jaspersoft.studio.components.crosstab.action.CustomCrosstabDeleteAction;
import com.jaspersoft.studio.components.crosstab.action.EditCrosstabStyleAction;
import com.jaspersoft.studio.components.crosstab.action.RemoveCrosstabStylesAction;
import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.cell.action.CreateColumnCrosstabHeaderAction;
import com.jaspersoft.studio.components.crosstab.model.columngroup.action.CreateColumnGroupAction;
import com.jaspersoft.studio.components.crosstab.model.header.action.CreateCrosstabHeaderAction;
import com.jaspersoft.studio.components.crosstab.model.measure.action.CreateMeasureAction;
import com.jaspersoft.studio.components.crosstab.model.nodata.action.CreateCrosstabWhenNoDataAction;
import com.jaspersoft.studio.components.crosstab.model.parameter.action.CreateCrosstabParameterAction;
import com.jaspersoft.studio.components.crosstab.model.rowgroup.action.CreateRowGroupAction;
import com.jaspersoft.studio.components.crosstab.model.title.action.CreateCrosstabTitleAction;
import com.jaspersoft.studio.editor.gef.parts.JSSGraphicalViewerKeyHandler;
import com.jaspersoft.studio.editor.gef.parts.JasperDesignEditPartFactory;
import com.jaspersoft.studio.editor.gef.parts.MainDesignerRootEditPart;
import com.jaspersoft.studio.editor.gef.rulers.ReportRuler;
import com.jaspersoft.studio.editor.gef.rulers.ReportRulerProvider;
import com.jaspersoft.studio.editor.java2d.JSSScrollingGraphicalViewer;
import com.jaspersoft.studio.editor.name.NamedSubeditor;
import com.jaspersoft.studio.editor.report.MarqueeSelectionOverrider;
import com.jaspersoft.studio.editor.report.ParentSelectionOverrider;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.util.ModelVisitor;
import com.jaspersoft.studio.preferences.RulersGridPreferencePage;
import com.jaspersoft.studio.property.dataset.dialog.ContextualDatasetAction;
import com.jaspersoft.studio.property.dataset.dialog.DatasetAction;
import com.jaspersoft.studio.utils.AContributorAction;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.base.JRBaseElement;

/*
 * The Class CrosstabEditor.
 * 
 * @author Chicu Veaceslav
 */
public class CrosstabEditor extends NamedSubeditor {
	public CrosstabEditor(JasperReportsConfiguration jrContext) {
		super(jrContext);
		setPartName(getDefaultEditorName());
		setPartImage(JaspersoftStudioPlugin.getInstance().getImage(MCrosstab.getIconDescriptor().getIcon16()));
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
			jssViewer.addSelectionOverrider(new ParentSelectionOverrider(IContainer.class, false));
			jssViewer.addSelectionOverrider(new MarqueeSelectionOverrider());
		}
	}

	@Override
	protected List<String> getIgnorePalleteElements() {
		List<String> lst = new ArrayList<String>();
		lst.add(MCrosstab.class.getCanonicalName());
		lst.add("com.jaspersoft.studio.components.chart.model.MChart"); //$NON-NLS-1$
		return lst;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void createDeleteAction(ActionRegistry registry) {
		List<String> selectionActions = getSelectionActions();
		CustomCrosstabDeleteAction deleteAction = new CustomCrosstabDeleteAction(this);
		registry.registerAction(deleteAction);
		selectionActions.add(deleteAction.getId());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void createCutAction(ActionRegistry registry) {
		List<String> selectionActions = getSelectionActions();
		IAction action = new CrosstabCutAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());
	}

	@Override
	protected void createEditorActions(ActionRegistry registry) {
		createDatasetAndStyleActions(registry);

		IAction action = new CreateMeasureAction(this);
		registry.registerAction(action);
		@SuppressWarnings("unchecked")
		List<String> selectionActions = getSelectionActions();
		selectionActions.add(CreateMeasureAction.ID);

		action = new EditCrosstabStyleAction(this);
		registry.registerAction(action);
		selectionActions.add(EditCrosstabStyleAction.ID);

		action = new RemoveCrosstabStylesAction(this);
		registry.registerAction(action);
		selectionActions.add(RemoveCrosstabStylesAction.ID);

		action = new CreateColumnGroupAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateColumnGroupAction.ID);

		action = new CreateColumnCrosstabHeaderAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateColumnCrosstabHeaderAction.ID);

		action = new CreateRowGroupAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateRowGroupAction.ID);

		action = new CreateCrosstabHeaderAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateCrosstabHeaderAction.ID);

		action = new CreateCrosstabWhenNoDataAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateCrosstabWhenNoDataAction.ID);

		action = new CreateCrosstabTitleAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateCrosstabTitleAction.ID);

		action = new CreateCrosstabParameterAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateCrosstabParameterAction.ID);

		action = new DatasetAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new ContextualDatasetAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());
	}

	@Override
	public void contributeItemsToEditorTopToolbar(IToolBarManager toolbarManager) {
		ActionContributionItem item = new ActionContributionItem(getActionRegistry().getAction(DatasetAction.ID));
		act4TextIcon.add(item);
		toolbarManager.add(item);
		// Contributed actions
		List<AContributorAction> contributedActions = JaspersoftStudioPlugin.getExtensionManager().getActions();
		for (AContributorAction a : contributedActions) {
			a.setJrConfig((JasperReportsConfiguration) getGraphicalViewer().getProperty("JRCONTEXT"));
			item = new ActionContributionItem(a);
			act4TextIcon.add(item);
			toolbarManager.add(item);
		}
		toolbarManager.add(new Separator());
		super.contributeItemsToEditorTopToolbar(toolbarManager);
	}

	@Override
	public String getDefaultEditorName() {
		return Messages.CrosstabEditor_crosstab;
	}

	@Override
	public ANode getEditedNode() {
		INode model = getModel();
		if (model != null) {
			ModelVisitor<MCrosstab> mv = new ModelVisitor<MCrosstab>(model) {

				@Override
				public boolean visit(INode n) {
					if (n instanceof MCrosstab) {
						setObject((MCrosstab) n);
						stop();
					}
					return true;
				}

			};
			return mv.getObject();
		}
		return null;
	}

	@Override
	public String getEditorName() {
		ANode node = getEditedNode();
		if (node != null && node.getValue() instanceof JRBaseElement) {
			JRBaseElement el = (JRBaseElement) node.getValue();
			return el.getPropertiesMap().getProperty(MGraphicElement.PROPERTY_ELEMENT_NAME);
		}
		return null;
	}
}
