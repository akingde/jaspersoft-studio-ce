package com.jaspersoft.studio.kpi.dialog;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class TitlePage extends AbstractKPIConfigurationPage {

	public static final String PARAMETER_NAME = "title";
	
	private Composite comp = null;
	
	private String getTextFromExpression(JRExpression exp){
		if (exp == null) return "";
		String text = exp.getText();
		if (text.startsWith("\"")) text = text.substring(1);
		if (text.endsWith("\"")) text= text.substring(0, text.length()-1);
		return text;
	}
	
	private void setParameterExpression(String value){
		String expressionText = "\"" + value + "\"";
		JRExpression newExpression = new JRDesignExpression(expressionText);
		JRParameter parameter = jd.getParametersMap().get(PARAMETER_NAME);
		if (parameter != null){
			((JRDesignParameter)parameter).setDefaultValueExpression(newExpression);
		} else {
			JRDesignParameter newParameter = new JRDesignParameter();
			newParameter.setDefaultValueExpression(newExpression);
			newParameter.setName(PARAMETER_NAME);
			try {
				jd.addParameter(newParameter);
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public String getName() {
		return "Title";
	}
	
	@Override
	protected Composite createComposite(Composite parent){
		comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new GridLayout(2, false));
		new Label(comp, SWT.NONE).setText("Title");
		Text titleText = new Text(comp, SWT.BORDER);
		titleText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		JRParameter parameter = jd.getParametersMap().get(PARAMETER_NAME);
		if (parameter != null){
			titleText.setText(getTextFromExpression(parameter.getDefaultValueExpression()));
		}
		titleText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				setParameterExpression(((Text)e.widget).getText());
				
			}
		});
		return comp;
	}
}
