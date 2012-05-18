package com.jaspersoft.studio.editor.jrexpressions.ui.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.expressions.functions.CategoryKeys;
import net.sf.jasperreports.expressions.functions.util.FunctionsLibraryUtil;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CaretEvent;
import org.eclipse.swt.custom.CaretListener;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.xtext.validation.Issue;

import com.google.inject.Injector;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.ExpressionContextUtils;
import com.jaspersoft.studio.editor.expression.ExpressionEditorComposite;
import com.jaspersoft.studio.editor.expression.ExpressionStatus;
import com.jaspersoft.studio.editor.expression.IExpressionStatusChangeListener;
import com.jaspersoft.studio.editor.jrexpressions.ui.JRExpressionsActivator;
import com.jaspersoft.studio.editor.jrexpressions.ui.support.ObjectCategoryItem.Category;

/**
 * Standard implementation of the main editing area for JasperReports
 * expressions provided by Jaspersoft Studio.
 * 
 * <p>
 * The composite is made of a {@link StyledText} widget that contains the
 * expression text. A tree containing the main categories of items that can be
 * used (i.e: parameters, fields, etc.) and an additional details panel that is
 * populated once the user select a specific category.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class DefaultExpressionEditorComposite extends ExpressionEditorComposite {

	// Expression stuff
	private JRDesignExpression expression;
	private ExpressionContext exprContext;

	// Widgets stuff
	private StyledText editorArea;
	private StyledTextXtextAdapter2 xtextAdapter;
	private TreeViewer objectsNavigator;
	private Composite objectCategoryDetailsCmp;
	private StackLayout detailsPanelStackLayout;
	private List<IExpressionStatusChangeListener> statusChangeListeners;

	// Support data structures and classes
	private EditingAreaHelper editingAreaInfo;
	private String currentWidgetText;
	private Map<String, ObjectCategoryDetailsPanel> detailPanels; // Cache map
																	// of the
																	// detail
																	// panels
	private ObjectCategoryItem builtinFunctionsItem;
	private ObjectCategoryItem parametersCategoryItem;
	private ObjectCategoryItem fieldsCategoryItem;
	private ObjectCategoryItem variablesCategoryItem;

	/**
	 * Creates the expression editor composite.
	 * 
	 * @param parent
	 *            the parent of the newly created composite
	 * @param style
	 *            style information of the newly created composite
	 */
	public DefaultExpressionEditorComposite(Composite parent, int style) {
		super(parent, style);
		detailPanels = new HashMap<String, ObjectCategoryDetailsPanel>();
		statusChangeListeners = new ArrayList<IExpressionStatusChangeListener>();

		GridLayout gdl = new GridLayout(1, true);
		this.setLayout(gdl);

		final SashForm mainSashForm = new SashForm(this, SWT.VERTICAL);
		GridData gdMainSash = new GridData(SWT.FILL, SWT.FILL, true, true);
		mainSashForm.setLayoutData(gdMainSash);

		createEditorArea(mainSashForm);

		final SashForm subSashForm = new SashForm(mainSashForm, SWT.HORIZONTAL);
		GridData gdsash = new GridData(SWT.FILL, SWT.FILL, true, true);
		subSashForm.setLayoutData(gdsash);

		createObjectsNavigator(subSashForm);
		createCustomPanel(subSashForm);

		subSashForm.setWeights(new int[] { 25, 75 });
		mainSashForm.setWeights(new int[] { 20, 80 });
	}

	/*
	 * Creates the editor area (styled text widget) and support information.
	 */
	private void createEditorArea(Composite parent) {
		Composite editorContainer = new Composite(parent, SWT.NONE);
		GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, false);
		layoutData.heightHint = 100;
		editorContainer.setLayoutData(layoutData);
		GridLayout layout = new GridLayout(1, false);
		layout.marginWidth = 0;
		editorContainer.setLayout(layout);

		editorArea = new StyledText(editorContainer, SWT.BORDER
				| SWT.BORDER_SOLID | SWT.MULTI | SWT.WRAP);
		GridData editorAreaGD=new GridData(SWT.FILL, SWT.FILL, true, true);
		editorAreaGD.widthHint=500;
		editorArea.setLayoutData(editorAreaGD);
		editorArea.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				currentWidgetText = editorArea.getText();
				synchCurrentFunctionDetails();
				updateExpressionStatus();
			}
		});
		editorArea.addCaretListener(new CaretListener() {

			public void caretMoved(CaretEvent event) {
				currentWidgetText = editorArea.getText();
				synchCurrentFunctionDetails();
			}
		});
		xtextAdapter = new StyledTextXtextAdapter2(getInjector());
		xtextAdapter.adapt(editorArea);

		editingAreaInfo = new EditingAreaHelper(xtextAdapter, editorArea);
		editingAreaInfo
				.addCategorySelectionListener(new ObjectCategorySelectionListener() {

					public void select(ObjectCategorySelectionEvent event) {
						performCategorySelection(event.selectedCategory);
					}
				});
	}

	/*
	 * Creates the categories tree navigator.
	 */
	private void createObjectsNavigator(Composite parent) {
		objectsNavigator = new TreeViewer(parent, SWT.BORDER);
		Tree tree = objectsNavigator.getTree();
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		objectsNavigator
				.setContentProvider(new ObjectsNavigatorContentProvider());
		objectsNavigator.setLabelProvider(new ObjectsNavigatorLabelProvider());

		objectsNavigator
				.addSelectionChangedListener(new ISelectionChangedListener() {

					public void selectionChanged(SelectionChangedEvent event) {
						Object selItem = ((IStructuredSelection) event
								.getSelection()).getFirstElement();
						if (selItem instanceof ObjectCategoryItem) {
							updateDetailsPanel((ObjectCategoryItem) selItem);
						}
					}
				});
	}

	/*
	 * Creates the additional panel that will contain details on the selected
	 * category.
	 */
	private void createCustomPanel(Composite parent) {
		objectCategoryDetailsCmp = new Composite(parent, SWT.NONE);
		objectCategoryDetailsCmp.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
				true, true));
		detailsPanelStackLayout = new StackLayout();
		objectCategoryDetailsCmp.setLayout(detailsPanelStackLayout);
	}

	@Override
	public void setExpressionContext(ExpressionContext exprContext) {
		this.exprContext = exprContext;
		this.xtextAdapter.configureExpressionContext(this.exprContext);
		refreshExpressionContextUI();
	}

	@Override
	public JRExpression getExpression() {
		if ("".equals(currentWidgetText)) {
			expression = null;
		} else {
			expression = new JRDesignExpression(currentWidgetText);
		}
		return expression;
	}

	@Override
	public void setExpression(JRExpression expression) {
		this.expression = (JRDesignExpression) expression;
		if (this.expression == null) {
			editorArea.setText("");
		} else {
			editorArea.setText(expression.getText());
		}
		updateExpressionStatus();
	}

	/*
	 * Get the right injector from the Activator plugin class. The injector
	 * should be language specific.
	 * 
	 * FIXME - Implement the injector selection based on the current report
	 * language
	 */
	private Injector getInjector() {
		JRExpressionsActivator activator = JRExpressionsActivator.getInstance();
		return activator
				.getInjector(JRExpressionsActivator.COM_JASPERSOFT_STUDIO_EDITOR_JREXPRESSIONS_JAVAJREXPRESSION);
	}

	/*
	 * Update the composite UI once the expression context is set.
	 */
	private void refreshExpressionContextUI() {
		// Builds the list of main categories
		List<ObjectCategoryItem> rootCategories = new ArrayList<ObjectCategoryItem>();
		if (exprContext != null) {
			if (exprContext.getDatasets().size() > 0) {
				parametersCategoryItem = new ObjectCategoryItem(
						Category.PARAMETERS);
				parametersCategoryItem.setData(ExpressionContextUtils
						.getAllParameters(exprContext));
				fieldsCategoryItem = new ObjectCategoryItem(Category.FIELDS);
				fieldsCategoryItem.setData(ExpressionContextUtils
						.getAllFields(exprContext));
				variablesCategoryItem = new ObjectCategoryItem(
						Category.VARIABLES);
				variablesCategoryItem.setData(ExpressionContextUtils
						.getAllVariables(exprContext));
				rootCategories.add(parametersCategoryItem);
				rootCategories.add(fieldsCategoryItem);
				rootCategories.add(variablesCategoryItem);
			}

			int i = 0;
			for (JRDesignCrosstab crosstab : exprContext.getCrosstabs()) {
				i++;
				String crosstabKey = crosstab.getKey();
				if (crosstabKey == null)
					crosstabKey = "";

				ObjectCategoryItem tmpCrossTabItem = new ObjectCategoryItem(
						Category.CROSSTAB, Category.CROSSTAB.getDisplayName()
								+ i + ") " + crosstabKey);
				tmpCrossTabItem.setData(crosstab);
				rootCategories.add(tmpCrossTabItem);
			}

		}
		builtinFunctionsItem = new ObjectCategoryItem(
				Category.BUILT_IN_FUNCTIONS);
		// Get all categories for builtin functions
		List<ObjectCategoryItem> functionCategories = new ArrayList<ObjectCategoryItem>();
		for (String categoryKey : FunctionsLibraryUtil.getCategories()) {
			String displayName = categoryKey;
			String categoryDisplayName = CategoryKeys.getCategoryDisplayName(
					categoryKey, null);
			if (categoryDisplayName != null) {
				displayName = categoryDisplayName;
			}
			ObjectCategoryItem objectCategoryItem = new ObjectCategoryItem(
					Category.FUNCTION_CATEGORY, displayName);
			objectCategoryItem.setData(categoryKey);
			functionCategories.add(objectCategoryItem);
		}
		if (!functionCategories.isEmpty()) {
			builtinFunctionsItem
					.setData(functionCategories
							.toArray(new ObjectCategoryItem[functionCategories
									.size()]));
		}

		rootCategories.add(builtinFunctionsItem);

		rootCategories.add(new ObjectCategoryItem(
				Category.USER_DEFINED_EXPRESSIONS));
		rootCategories.add(new ObjectCategoryItem(Category.RECENT_EXPRESSIONS));

		objectsNavigator.setInput(rootCategories
				.toArray(new ObjectCategoryItem[rootCategories.size()]));
		objectsNavigator.expandAll();
		objectsNavigator.setSelection(new StructuredSelection(objectsNavigator
				.getTree().getItem(0).getData()), true);
	}

	public void addExpressionStatusChangeListener(
			IExpressionStatusChangeListener listener) {
		statusChangeListeners.add(listener);
	}

	public void removeExpressionStatusChangeListener(
			IExpressionStatusChangeListener listener) {
		statusChangeListeners.remove(listener);
	}

	public void notifyExpressionStatusChanged(ExpressionStatus status) {
		for (IExpressionStatusChangeListener l : statusChangeListeners) {
			l.statusChanged(status);
		}
	}

	/* Listeners utility methods */

	/*
	 * Updates the details panel once the selected tree category item changes. A
	 * StackLayout is used in order to cache the details composites. If needed
	 * the control is created, otherwise it is simply set as top control.
	 */
	private void updateDetailsPanel(ObjectCategoryItem selItem) {
		String key = selItem.getCategory().getDisplayName() + "_"
				+ selItem.getDisplayName();
		ObjectCategoryDetailsPanel currentControl = detailPanels.get(key);
		if (currentControl == null) {
			// First time, must create control
			currentControl = new ObjectCategoryDetailsPanel(
					objectCategoryDetailsCmp, SWT.NONE);
			currentControl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
					true));
			currentControl.setExpressionContext(exprContext);
			currentControl.setEditingAreaInfo(editingAreaInfo);

			detailPanels.put(key, currentControl);
		}

		// Ensure all other controls are not visible
		Control[] children = objectCategoryDetailsCmp.getChildren();
		for (int i = 0; i < children.length; i++) {
			if (children[i] != currentControl) {
				children[i].setVisible(false);
			}
		}
		// Make the current selected one visible
		currentControl.setVisible(true);
		currentControl.refreshPanelUI(selItem);
		detailsPanelStackLayout.topControl = currentControl;
		objectCategoryDetailsCmp.layout();
	}

	/*
	 * Forces the category selection in the related tree. If no category is
	 * specified, the following selection order is applied: 1) Fields, if any 2)
	 * Variables, if any 3) Parameters
	 */
	private void performCategorySelection(Category category) {
		if (category == null) {
			if (ExpressionContextUtils.getAllFields(exprContext).size() > 0) {
				objectsNavigator.setSelection(new StructuredSelection(
						fieldsCategoryItem), true);
			} else if (ExpressionContextUtils.getAllVariables(exprContext)
					.size() > 0) {
				objectsNavigator.setSelection(new StructuredSelection(
						variablesCategoryItem), true);
			} else {
				objectsNavigator.setSelection(new StructuredSelection(
						parametersCategoryItem), true);
			}
		}
		// Choose the right category
		for (TreeItem item : objectsNavigator.getTree().getItems()) {
			Object cat = item.getData();
			if (cat instanceof ObjectCategoryItem
					&& ((ObjectCategoryItem) cat).getCategory()
							.equals(category)) {
				objectsNavigator.setSelection(null);
				objectsNavigator.setSelection(new StructuredSelection(cat),
						true);
				return;
			}
		}
	}

	/*
	 * Tries to select a specific function, depending on the currently
	 * "selected" one in the editing area (i.e: based on cursor position). This
	 * involves also the update of the details panel area.
	 */
	private void synchCurrentFunctionDetails() {
		if (editingAreaInfo.isUpdate())
			return;
		final String functName = editingAreaInfo
				.getCurrentLibraryFunctionName();
		if (functName != null) {
			objectsNavigator.setSelection(new StructuredSelection(
					builtinFunctionsItem), true);
		} else {
			Object selElement = ((IStructuredSelection) objectsNavigator
					.getSelection()).getFirstElement();
			objectsNavigator.setSelection(new StructuredSelection(selElement),
					true);
		}
	}

	/*
	 * Check and update the status of the current expression being edited.
	 */
	private void updateExpressionStatus() {
		if (editorArea.getText().equals("")) {
			// Do not care about empty expression(s)
			ExpressionStatus exprStatus = ExpressionStatus.INFO;
			exprStatus
					.setShortDescription("The current expression has no validation issues.");
			notifyExpressionStatusChanged(exprStatus);
			return;
		}

		List<Issue> validationIssues = xtextAdapter.getXtextValidationIssues();
		if (validationIssues != null && !validationIssues.isEmpty()) {
			ExpressionStatus exprStatus = ExpressionStatus.ERROR;
			for (Issue vi : validationIssues) {
				exprStatus.getMessages().add(vi.getMessage());
			}
			exprStatus
					.setShortDescription("The current expression is not valid. Please verify it!");
			notifyExpressionStatusChanged(exprStatus);
		} else {
			ExpressionStatus exprStatus = ExpressionStatus.INFO;
			exprStatus
					.setShortDescription("The current expression has no validation issues.");
			notifyExpressionStatusChanged(exprStatus);
		}
	}

}
