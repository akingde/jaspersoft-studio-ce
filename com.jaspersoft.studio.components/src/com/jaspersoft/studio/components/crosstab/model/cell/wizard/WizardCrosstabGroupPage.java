/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.cell.wizard;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.ExpressionEditorSupportUtil;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.variable.MVariable;
import com.jaspersoft.studio.property.descriptor.expression.dialog.JRExpressionEditor;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignVariable;

/**
 * Wizard page where the user can define the name and the expression of a crosstab group.
 * 
 * @author Orlandin Marco
 *
 */
public class WizardCrosstabGroupPage extends WizardPage implements IExpressionContextSetter {
	
	/**
	 * Text area where the group name is inserted
	 */
	private Text grName;
	
	/**
	 * List of the field from where an expression can be quickly created
	 */
	private java.util.List<Object> fList;
	
	/**
	 * Table of the field from where an expression can be quickly created
	 */
	private Table leftTable;
	
	/**
	 * Viewer of the table
	 */
	private TableViewer leftTView;
	
	/**
	 * Context when the expression editor is opened
	 */
	private ExpressionContext expContext;
	
	/**
	 * Button used to switch from the selection of the field to the manual
	 * insert of the expression
	 */
	private Button bfield;
	
	/**
	 * Names already in use in the crosstab and that should be avoided
	 */
	private List<String> alreadyUsedNames;
	
	/**
	 * The new group name
	 */
	private String groupName = "";
	
	/**
	 * The new group expression
	 */
	private String groupExpression = "";
	
	/**
	 * Type of the value
	 */
	private String valueClass = Object.class.getName();
	
	/**
	 * Class that define which label is shown when the field are listed, to show
	 * the correct icon if it is a variable, parameter or field
	 */
	private final class TLabelProvider extends LabelProvider implements ITableLabelProvider {
		
		public Image getColumnImage(Object element, int columnIndex) {
			switch (columnIndex) {
			case 0:
				if (element instanceof JRDesignField)
					return JaspersoftStudioPlugin.getInstance().getImage(MField.getIconDescriptor().getIcon16());
				else if (element instanceof JRDesignVariable)
					return JaspersoftStudioPlugin.getInstance().getImage(MVariable.getIconDescriptor().getIcon16());
				else if (element instanceof JRDesignParameter)
					return JaspersoftStudioPlugin.getInstance().getImage("icons/resources/parameter-report-16.png"); //$NON-NLS-1$
			}
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			switch (columnIndex) {
			case 0:
				if (element instanceof JRDesignField)
					return ((JRDesignField) element).getName();
				else if (element instanceof JRDesignVariable)
					return ((JRDesignVariable) element).getName();
				else if (element instanceof JRDesignParameter)
					return ((JRDesignParameter) element).getName();
			}
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * Create the wizard page 
	 * 
	 * @param crosstab the crosstab, must be not null
	 * @param alreadyUsedNames the already defined group name that are not valid for the new one. Must 
	 * be not null
	 */
	public WizardCrosstabGroupPage(MCrosstab crosstab, List<String> alreadyUsedNames) {
		super("grouppage"); //$NON-NLS-1$
		setTitle(Messages.common_group);
		setDescription(Messages.WizardBandGroupPage_description);
		this.alreadyUsedNames = alreadyUsedNames;
		JRDatasetRun datasetRun = crosstab.getValue().getDataset().getDatasetRun();
		if (datasetRun != null){
			String datasetName = datasetRun.getDatasetName();
			JRDataset dataset = crosstab.getJasperDesign().getDatasetMap().get(datasetName);
			fList = ModelUtils.getReportObjects4Datasource(dataset);
		} else {
			fList = ModelUtils.getReportObjects4Datasource(crosstab.getJasperDesign().getMainDataset());
		}
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		setControl(composite);

		Composite nameArea = new Composite(composite, SWT.NONE);
		nameArea.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout nameAreaLayout = new GridLayout(2, false);
		nameAreaLayout.marginWidth = 0;
		nameArea.setLayout(nameAreaLayout);
		
		Label lbl = new Label(nameArea, SWT.NONE);
		lbl.setText(getGroupNameLabel());

		grName = new Text(nameArea, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		grName.setLayoutData(gd);
		grName.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				String currentText = grName.getText().trim();
				if (currentText.isEmpty()) {//$NON-NLS-1$
					setErrorMessage(getEmptyNameError());
					setPageComplete(false);
				} else if (alreadyUsedNames.contains(currentText)) {
					setErrorMessage(getDuplicatedNameError());
					setPageComplete(false);
				} else {
					setPageComplete(true);
					setErrorMessage(null);
					setMessage(getDescription());
					groupName = currentText;
				}
			}
		});
		grName.setText("");

		//Subclass can here create additional controls
		createAdditionalControls(composite);
		
		bfield = new Button(composite, SWT.RADIO);
		bfield.setText(getReportObjectLabel());
		gd = new GridData(GridData.FILL_HORIZONTAL);
		bfield.setLayoutData(gd);
		bfield.setSelection(true);

		Button bexpr = new Button(composite, SWT.RADIO);
		bexpr.setText(getExpressionLabel());
		gd = new GridData(GridData.FILL_HORIZONTAL);
		bexpr.setLayoutData(gd);

		final Composite cmp = new Composite(composite, SWT.NONE);
		final StackLayout stackLayout = new StackLayout();
		cmp.setLayout(stackLayout);
		gd = new GridData(GridData.FILL_BOTH);
		cmp.setLayoutData(gd);

		final Composite objCmp = createObjectFields(cmp);

		final Composite expCmp = createExpression(cmp);

		stackLayout.topControl = objCmp;

		bfield.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl = objCmp;
				cmp.layout(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		bexpr.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl = expCmp;
				cmp.layout(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), "Jaspersoft.wizard");//$NON-NLS-1$
	}
	
	protected void createAdditionalControls(Composite parent){
		
	}

	private Composite createExpression(Composite cmp) {
		Composite composite = new Composite(cmp, SWT.NONE);
		composite.setLayout(new GridLayout());

		Composite expCompo = new Composite(composite, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 0;
		expCompo.setLayout(layout);
		GridData gd = new GridData(GridData.FILL_BOTH);
		// gd.horizontalSpan = 2;
		expCompo.setLayoutData(gd);

		final Text dsExpr = new Text(expCompo, SWT.BORDER | SWT.MULTI);
		gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 300;
		gd.heightHint = 100;
		dsExpr.setLayoutData(gd);

		final Button dsExprDialog = new Button(expCompo, SWT.PUSH);
		dsExprDialog.setText("..."); //$NON-NLS-1$
		dsExprDialog.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

		dsExprDialog.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				if(!ExpressionEditorSupportUtil.isExpressionEditorDialogOpen()) {
					JRExpressionEditor wizard = new JRExpressionEditor();
					JRDesignExpression mexp = new JRDesignExpression(groupExpression);
					wizard.setValue(mexp);
					wizard.setExpressionContext(expContext);
					WizardDialog dialog = ExpressionEditorSupportUtil.getExpressionEditorWizardDialog(dsExprDialog.getShell(), wizard);
					dialog.create();
					if (dialog.open() == Dialog.OK) {
						mexp = wizard.getValue();
						if (mexp != null) {
							groupExpression = mexp.getText();
							valueClass = Object.class.getName();
							dsExpr.setText(mexp.getText());
						}
					}
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		dsExpr.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				groupExpression = dsExpr.getText();
				valueClass = Object.class.getName();
			}
		});

		leftTView.setInput(fList);
		return composite;
	}

	private Composite createObjectFields(Composite cmp) {
		Composite composite = new Composite(cmp, SWT.NONE);
		composite.setLayout(new GridLayout());

		leftTable = new Table(composite, SWT.V_SCROLL | SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER);
		leftTable.setLayoutData(new GridData(GridData.FILL_BOTH));
		leftTable.setHeaderVisible(true);
		GridData tableData = new GridData(GridData.FILL_BOTH);
		tableData.heightHint = 300;
		leftTable.setLayoutData(tableData);

		TableColumn[] col = new TableColumn[1];
		col[0] = new TableColumn(leftTable, SWT.NONE);
		col[0].setText(Messages.common_report_objects);
		col[0].pack();

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(100, false));
		leftTable.setLayout(tlayout);

		leftTView = new TableViewer(leftTable);
		leftTView.setContentProvider(new ListContentProvider());
		leftTView.setLabelProvider(new TLabelProvider());

		leftTable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection sel = (StructuredSelection) leftTView.getSelection();
				if (!sel.isEmpty()) {
					Object obj = sel.getFirstElement();
					if (obj instanceof JRDesignField) {
						JRDesignField field = (JRDesignField) obj;
						groupExpression = "$F{" + field.getName() + "}";//$NON-NLS-1$ //$NON-NLS-2$
						valueClass = field.getValueClassName();
					} else if (obj instanceof JRDesignVariable) {
						JRDesignVariable variable = (JRDesignVariable) obj;
						groupExpression = "$V{" + variable.getName() + "}";//$NON-NLS-1$ //$NON-NLS-2$
						valueClass = variable.getValueClassName();
					} else if (obj instanceof JRDesignParameter){
						JRDesignParameter parameter = (JRDesignParameter) obj;
						groupExpression = "$P{" + parameter.getName() + "}";//$NON-NLS-1$ //$NON-NLS-2$
						valueClass = parameter.getValueClassName();
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		
		leftTable.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				StructuredSelection sel = (StructuredSelection) leftTView.getSelection();
				if (!sel.isEmpty()) {
					Object obj = sel.getFirstElement();
					if (obj instanceof JRDesignField) {
						JRDesignField field = (JRDesignField) obj;
						grName.setText(field.getName());
					} else if (obj instanceof JRDesignVariable) {
						JRDesignVariable variable = (JRDesignVariable) obj;
						grName.setText(variable.getName());
					} else if (obj instanceof JRDesignParameter){
						JRDesignParameter parameter = (JRDesignParameter) obj;
						grName.setText(parameter.getName());
					}
				}
			}
			
		});
		return composite;
	}
	


	/**
	 * Set the expression context for the expression editor.
	 * 
	 * @param expContext the expression context, should be not null but
	 * it is not mandatory
	 */
	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
	}
	
	/**
	 * Return the name of the new group
	 * 
	 * @return a not null and unique name for the group
	 */
	public String getGroupName(){
		return groupName;
	}
	
	/**
	 * Return the expression of the new group
	 * 
	 * @return an expression for the new group
	 */
	public String getGroupExpression(){
		return groupExpression;
	}
	
	/**
	 * Return the type of the selected element
	 * 
	 * @return a string defining the qualified type of the selected element, if
	 * if used an expression the type returned will be java.lang.Object
	 */
	public String getGroupValueClass(){
		return valueClass;
	}
	
	//Return the strings used in the dialog, to allow it to use in more context
	
	protected String getGroupNameLabel(){
		return Messages.common_group_name;
	}
	
	protected String getReportObjectLabel(){
		return Messages.WizardBandGroupPage_1;
	}
	
	protected String getExpressionLabel(){
		return Messages.WizardBandGroupPage_2;
	}
	
	protected String getDuplicatedNameError(){
		return Messages.WizardBandGroupPage_error_message_unique_name;
	}
	
	protected String getEmptyNameError(){
		return Messages.WizardBandGroupPage_error_message_group_name_not_empty;
	}
}
