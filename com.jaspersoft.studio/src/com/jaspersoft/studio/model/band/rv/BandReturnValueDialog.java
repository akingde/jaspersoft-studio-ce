/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.band.rv;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.internal.core.search.BasicSearchEngine;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.SelectionDialog;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.dataset.ExpressionWidget;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.utils.EnumHelper;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.design.DesignExpressionReturnValue;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.fill.JRIncrementerFactory;
import net.sf.jasperreports.engine.type.CalculationEnum;

/**
 * Dialog to provide the configuration of a band run return value
 * 
 * @author veaceslav chicu
 * 
 */
public class BandReturnValueDialog extends ATitledDialog implements IExpressionContextSetter {

	protected JasperReportsConfiguration jConfig;
	protected DesignExpressionReturnValue value;
	protected Text incrementText;
	protected Combo calculation;
	protected Combo toVariable;

	protected String[] toVariables;
	private PropertyChangeListener listener = new PropertyChangeListener() {

		@Override
		public void propertyChange(PropertyChangeEvent arg0) {
			validate();
		}
	};

	/**
	 * Create the dialog
	 * 
	 * @param parentShell
	 *          the shell
	 */
	public BandReturnValueDialog(Shell parentShell, JasperReportsConfiguration jConfig,
			DesignExpressionReturnValue value, String[] toVariables) {
		super(parentShell);
		setTitle(Messages.BandReturnValueDialog_0);
		this.jConfig = jConfig;
		this.value = value;
		this.toVariables = toVariables;
		setExpressionContext(new ExpressionContext(jConfig.getJasperDesign().getMainDesignDataset(), jConfig));
		value.getEventSupport().addPropertyChangeListener(listener);
	}

	@Override
	public boolean close() {
		value.getEventSupport().removePropertyChangeListener(listener);
		return super.close();
	}

	private ExpressionContext expContext;
	private ExpressionWidget expr;

	@Override
	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
	}

	public DesignExpressionReturnValue getValue() {
		return value;
	}

	/**
	 * Create the control for the to variable as not editable combo
	 * 
	 * @param container
	 *          the parent container, it will have a gridlayout with two columns
	 */
	protected void createToVariable(Composite container) {
		Label toVariableLabel = new Label(container, SWT.NONE);
		toVariableLabel.setText(Messages.RVPropertyPage_to_variable);

		toVariable = new Combo(container, SWT.READ_ONLY);
		toVariable.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		toVariable.setLayoutData(gd);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(3, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		expr = new ExpressionWidget(container, Messages.common_expression);
		expr.setExpressionContext(expContext);

		createToVariable(container);

		Label calculationLabel = new Label(container, SWT.NONE);
		calculationLabel.setText(Messages.RVPropertyPage_calculation_type);

		calculation = new Combo(container, SWT.READ_ONLY);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		calculation.setLayoutData(gd);
		calculation.setItems(calculations);

		Label incrementLabel = new Label(container, SWT.NONE);
		incrementLabel.setText(Messages.RVPropertyPage_incrementer_factory_class);

		incrementText = new Text(container, SWT.BORDER);
		incrementText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Button incrementButton = new Button(container, SWT.NONE);
		incrementButton.setText("..."); //$NON-NLS-1$
		incrementButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SelectionDialog dialog;
				try {
					dialog = JavaUI.createTypeDialog(getShell(), new ProgressMonitorDialog(getShell()), getIncrementContext(),
							IJavaElementSearchConstants.CONSIDER_ALL_TYPES, false);
					dialog.setTitle(Messages.ClassTypeCellEditor_open_type);
					dialog.setMessage(Messages.ClassTypeCellEditor_dialog_message);
					if (dialog.open() == Window.OK) {
						if (dialog.getResult() != null && dialog.getResult().length > 0) {
							IType bt = (IType) dialog.getResult()[0];
							incrementText.setText(bt.getFullyQualifiedName());
						}
					}
				} catch (JavaModelException e1) {
					e1.printStackTrace();
				}
			}
		});

		// INITIALIZE THE WIDGET WITH THE CONTENT OF THE CONTAINER IF IT IS VALID
		updateContainer();

		expr.bindObject(value, "Expression"); //$NON-NLS-1$

		toVariable.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				value.setToVariable(toVariable.getText());
			}
		});
		incrementText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				value.setIncrementerFactoryClassName(incrementText.getText());
			}
		});
		calculation.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				value.setCalculation(CalculationEnum.getByName(calculation.getText()));
			}
		});

		return container;
	}

	private static final String[] calculations = EnumHelper.getEnumNames(CalculationEnum.values(), NullEnum.NOTNULL);

	/**
	 * Save the value from the widget inside the container
	 */
	protected void updateContainer() {
		expr.setExpression((JRDesignExpression) value.getExpression());

		toVariable.setItems(toVariables);
		if (value.getToVariable() != null) {
			int index = ArrayUtils.indexOf(toVariables, value.getToVariable());
			if (index == ArrayUtils.INDEX_NOT_FOUND)
				index = 0;
			toVariable.select(index);
		} else {
			toVariable.select(0);
			value.setToVariable(toVariable.getText());
		}

		if (value.getCalculation() != null) {
			int index = ArrayUtils.indexOf(CalculationEnum.values(), value.getCalculation());
			if (index == ArrayUtils.INDEX_NOT_FOUND)
				index = 0;
			calculation.select(index);
		}

		if (value.getIncrementerFactoryClassName() != null)
			incrementText.setText(value.getIncrementerFactoryClassName());
		validate();
	}

	/**
	 * Create all the controls and validate the content of the dialog
	 * 
	 * @param parent
	 *          the parent control
	 * @return return the container control
	 */
	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		validate();
		return contents;
	}

	/**
	 * Check if the content of the widget is valid and enable\disable the ok button. Essentially the only validity check
	 * is a name not empty for the from variable
	 */
	protected void validate() {
		Button okButton = getButton(IDialogConstants.OK_ID);
		if (okButton != null)
			okButton.setEnabled(!Misc.isNullOrEmpty(toVariable.getText()) && value.getExpression() != null
					&& !Misc.isNullOrEmpty(value.getExpression().getText()));
	}

	/**
	 * Get the scope to search in the workspace a class that implements the JRIncrementFactory
	 * 
	 * @return a not null java search scope to fine an implementations of JRIncrementFactory
	 */
	private IJavaSearchScope getIncrementContext() {
		IJavaSearchScope searchScope = SearchEngine.createWorkspaceScope();
		IProject prj = ((IFile) jConfig.get(FileUtils.KEY_FILE)).getProject();
		if (prj != null) {
			try {
				IJavaProject jprj = JavaCore.create(prj);
				IType t;
				t = jprj.findType(JRIncrementerFactory.class.getName());
				if (t != null)
					searchScope = BasicSearchEngine.createHierarchyScope(t);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return searchScope;
	}
}
