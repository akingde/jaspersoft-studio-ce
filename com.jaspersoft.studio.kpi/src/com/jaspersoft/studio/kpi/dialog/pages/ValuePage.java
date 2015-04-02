package com.jaspersoft.studio.kpi.dialog.pages;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.type.CalculationEnum;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.kpi.dialog.AbstractKPIConfigurationPage;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.messages.MessagesByKeys;
import com.jaspersoft.studio.swt.events.ExpressionModifiedEvent;
import com.jaspersoft.studio.swt.events.ExpressionModifiedListener;
import com.jaspersoft.studio.swt.widgets.WTextExpression;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ValuePage extends AbstractKPIConfigurationPage {

	private String variableName;
	
	private String pageName;
	
	public ValuePage(String pageName, String variableName){
		this.variableName = variableName;
		this.pageName = pageName;
	}
	
	@Override
	public String getName() {
		return pageName;
	}
	
	private JRDesignVariable getVariable(){
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

	private void updateVariableExpression(JRExpression expression){
		getVariable().setExpression(expression);
	}
	
	@Override
	protected Composite createComposite(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1,false));
		new Label(container,SWT.NONE).setText(Messages.common_expression);
		final WTextExpression expr = new WTextExpression(container, SWT.NONE, 3);
		expr.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		JRExpression exp = getVariable().getExpression();
		expr.setExpression(exp != null ? (JRDesignExpression)exp : null);
		expr.setExpressionContext(getExpressionContext());
		expr.addModifyListener(new ExpressionModifiedListener() {
			@Override
			public void expressionModified(ExpressionModifiedEvent event) {
				JRDesignExpression exp = expr.getExpression();
				updateVariableExpression(exp != null ? (JRExpression)exp.clone() : null);
			}
		});
		
		new Label(container,SWT.NONE).setText(Messages.MVariable_calculation);
		CalculationEnum[] calculations = CalculationEnum.values();
		Combo calculationsCombo = new Combo(container, SWT.READ_ONLY);
		calculationsCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		List<String> items = new ArrayList<String>();
		int selectionIndex = 0;
		CalculationEnum variableCalculation = getVariable().getCalculationValue();
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
				getVariable().setCalculation(calculations[index]);
			}
		});
		return container;
	}
	
	public ExpressionContext getExpressionContext() {
		JasperReportsConfiguration jConfig = new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), null);
		jConfig.setJasperDesign(jd);
		return new ExpressionContext(jd.getMainDesignDataset(), jConfig);
	}
}
