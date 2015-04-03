package com.jaspersoft.studio.kpi.dialog.pages;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.type.CalculationEnum;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.kpi.dialog.AbstractKPIConfigurationPage;
import com.jaspersoft.studio.kpi.messages.Messages;
import com.jaspersoft.studio.messages.MessagesByKeys;
import com.jaspersoft.studio.property.descriptor.pattern.dialog.PatternEditor;
import com.jaspersoft.studio.swt.events.ExpressionModifiedEvent;
import com.jaspersoft.studio.swt.events.ExpressionModifiedListener;
import com.jaspersoft.studio.swt.widgets.WTextExpression;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ValuePage extends AbstractKPIConfigurationPage {

	public static final String VALUE_VARIABLE_NAME = "value"; //$NON-NLS-1$
	
	public static final String TARGET_VARIABLE_NAME = "target"; //$NON-NLS-1$
	
	public static final String VALUE_FORMATTED_PARAMETER = "valuePattern"; //$NON-NLS-1$
	
	public static final String TARGET_FORMATTED_PARAMETER = "targetPattern"; //$NON-NLS-1$
	
	@Override
	public String getName() {
		return Messages.ValuePage_pageLabel;
	}
	
	private JRDesignVariable getVariable(String variableName){
		JRVariable variable = jd.getVariablesMap().get(variableName);
		if (variable == null){
			JRDesignVariable newVariable = new JRDesignVariable();
			newVariable.setName(variableName);
			try {
				jd.addVariable(newVariable);
			} catch (JRException e) {
				e.printStackTrace();
			} 
			return newVariable;
		}
		return ((JRDesignVariable)variable);
	}

	private void updateVariableExpression(JRExpression expression, String variableName){
		JRDesignVariable variable = getVariable(variableName);
		JRExpression valueExpression = null;
		JRExpression initialValueExpression = null;
		if (expression != null){
			valueExpression = (JRExpression)expression.clone();
			initialValueExpression = (JRExpression)expression.clone();
		}
		variable.setExpression(valueExpression);
		variable.setInitialValueExpression(initialValueExpression);
	}
	
	@Override
	protected Composite createComposite(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout mainLayout = new GridLayout(1,false);
		mainLayout.verticalSpacing = 10;
		container.setLayout(mainLayout);
		createExpressionGroup(container, Messages.ValuePage_valueLabel, VALUE_VARIABLE_NAME, VALUE_FORMATTED_PARAMETER);
		createExpressionGroup(container, Messages.ValuePage_targetLabel, TARGET_VARIABLE_NAME, TARGET_FORMATTED_PARAMETER);
		return container;
	}
	
	private void createExpressionGroup(Composite parent, String groupName, final String variableName, final String patternField){
		Group container = new Group(parent, SWT.NONE);
		container.setText(groupName);
		container.setLayout(new GridLayout(2, false));
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		new Label(container,SWT.NONE).setText(Messages.common_expression);
		final WTextExpression expr = new WTextExpression(container, SWT.NONE, 3);
		expr.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		JRExpression exp = getVariable(variableName).getExpression();
		expr.setExpression(exp != null ? (JRDesignExpression)exp : null);
		expr.setExpressionContext(getExpressionContext());
		expr.addModifyListener(new ExpressionModifiedListener() {
			@Override
			public void expressionModified(ExpressionModifiedEvent event) {
				JRDesignExpression exp = expr.getExpression();
				updateVariableExpression(exp, variableName);
			}
		});
		
		new Label(container,SWT.NONE).setText(Messages.MVariable_calculation);
		CalculationEnum[] calculations = CalculationEnum.values();
		Combo calculationsCombo = new Combo(container, SWT.READ_ONLY);
		calculationsCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		List<String> items = new ArrayList<String>();
		int selectionIndex = 0;
		CalculationEnum variableCalculation = getVariable(variableName).getCalculationValue();
		for(CalculationEnum calcEnum : calculations){
			if (calcEnum.equals(variableCalculation)){
				selectionIndex = items.size();
			}
			items.add(MessagesByKeys.getString(calcEnum.getName()));
		}
		calculationsCombo.setData(calculations);
		calculationsCombo.setItems(items.toArray(new String[items.size()]));
		calculationsCombo.select(selectionIndex);
		calculationsCombo.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				Combo combo = (Combo)e.widget;
				int index = combo.getSelectionIndex();
				CalculationEnum[] calculations = (CalculationEnum[])combo.getData();
				getVariable(variableName).setCalculation(calculations[index]);
			}
		});
		
		new Label(container,SWT.NONE).setText(Messages.common_pattern);
		Composite patternContainer = new Composite(container, SWT.NONE);
		patternContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout patternLayout = new GridLayout(2,false);
		patternLayout.marginWidth = 0;
		patternLayout.marginHeight = 0;
		patternContainer.setLayout(patternLayout);

		final Text pattern = new Text(patternContainer, SWT.BORDER);
		pattern.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		pattern.setText(getPattern(patternField));
		pattern.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				setPattern(((Text)e.widget).getText(), patternField);
			}
		});
		
		Button btn = new Button(patternContainer, SWT.PUSH);
		btn.setText("..."); //$NON-NLS-1$
		btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PatternEditor wizard = new PatternEditor();
				wizard.setValue(pattern.getText());
				WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					pattern.setText(wizard.getValue());
				}
			}
		});
	}
	
	private void setPattern(String pattern, String patternParameter){
		JRDesignParameter element = getParameter(patternParameter);
		JRDesignExpression expression = new JRDesignExpression("\"" + pattern + "\""); //$NON-NLS-1$ //$NON-NLS-2$
		element.setDefaultValueExpression(expression);
	}
	
	private String getPattern(String patternParameter){
		JRDesignParameter element = getParameter(patternParameter);
		String pattern = ""; //$NON-NLS-1$
		if (element.getDefaultValueExpression() != null && element.getDefaultValueExpression().getText()!=null){
			pattern = element.getDefaultValueExpression().getText();
			if (pattern.startsWith("\"") && pattern.endsWith("\"")){ //$NON-NLS-1$ //$NON-NLS-2$
				pattern = pattern.substring(1, pattern.length()-1);
			}
		}
		return pattern;
	}
	
	private JRDesignParameter getParameter(String parameterName){
		JRParameter parameter = jd.getParametersMap().get(parameterName);
		if (parameter == null){
			JRDesignParameter newParameter = new JRDesignParameter();
			newParameter.setName(parameterName);
			try {
				jd.addParameter(newParameter);
			} catch (JRException e) {
				e.printStackTrace();
			} 
			return newParameter;
		}
		return ((JRDesignParameter)parameter);
	}
	
	
	public ExpressionContext getExpressionContext() {
		JasperReportsConfiguration jConfig = new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), null);
		jConfig.setJasperDesign(jd);
		return new ExpressionContext(jd.getMainDesignDataset(), jConfig);
	}
	
	@Override
	public String getTitle() {
		return Messages.ValuePage_pageTitle;
	}
}
