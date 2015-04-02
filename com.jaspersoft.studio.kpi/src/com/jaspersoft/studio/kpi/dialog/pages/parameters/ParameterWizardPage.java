package com.jaspersoft.studio.kpi.dialog.pages.parameters;

import java.util.ArrayList;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.kpi.messages.MessagesByKeys;
import com.jaspersoft.studio.property.combomenu.ComboItem;
import com.jaspersoft.studio.property.combomenu.ComboMenuViewer;
import com.jaspersoft.studio.property.section.widgets.SPRWPopUpCombo;
import com.jaspersoft.studio.swt.widgets.WTextExpression;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ParameterWizardPage extends WizardPage {

	private ParameterDefinition modifiedElement;
	
	private JasperDesign jd;
	
	private Text name;
	
	private ComboMenuViewer typeCombo;
	
	private WTextExpression expression;
	
	public ParameterDefinition regenerateParameter(){
		String expressionText = "";
		JRExpression exp = expression.getExpression();
		if (exp != null) expressionText = exp.getText();
		return new ParameterDefinition(name.getText(), (String)typeCombo.getSelectionValue(), expressionText);
	}
	
	public ParameterWizardPage(ParameterDefinition modifiedElement, JasperDesign jd) {
		super("parameterPage");
		this.modifiedElement = modifiedElement;
		this.jd = jd;
		if (modifiedElement != null){
			setTitle("Parameter Editing");
			setMessage("In this page you can edit the selected parameter");
		} else {
			setTitle("Parameter Creation");
			setMessage("In this page you can create a new parameter");
		}
	}
	public ParameterDefinition getDefinition(){
		return modifiedElement;
	}
	
	public ExpressionContext getExpressionContext() {
		JasperReportsConfiguration jConfig = new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), null);
		jConfig.setJasperDesign(jd);
		return new ExpressionContext(jd.getMainDesignDataset(), jConfig);
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2,false));
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		new Label(container, SWT.NONE).setText("Name");
		name = new Text(container, SWT.BORDER);
		name.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	
		new Label(container, SWT.NONE).setText("Type");
		ArrayList<ComboItem> itemsList = new ArrayList<ComboItem>();
		int index = 0;
		for(String type : ParameterDefinition.getParameterTypes()){
			itemsList.add(new ComboItem(MessagesByKeys.getString(type), true,  null, index, type, type));
			index++;
		}
		
		//Creating the combo popup
		typeCombo = new ComboMenuViewer(container, SWT.NORMAL, SPRWPopUpCombo.getLongest(itemsList));
		typeCombo.getControl().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		typeCombo.setItems(itemsList);
		typeCombo.select(0);
		
		Label expLabel = new Label(container, SWT.NONE);
		expLabel.setText("Expression");
		GridData labelData = new GridData();
		labelData.verticalAlignment = SWT.TOP;
		expLabel.setLayoutData(labelData);
		expression = new WTextExpression(container, SWT.NONE, 5);
		GridData expData = new GridData(GridData.FILL_HORIZONTAL);
		expData.heightHint = 100;
		expression.setLayoutData(expData);
		expression.setExpressionContext(getExpressionContext());
		
		if (modifiedElement != null){
			name.setText(modifiedElement.getName());
			expression.setExpression(new JRDesignExpression(modifiedElement.getExpression()));
			String name = modifiedElement.getName();
			int nameIndex = ParameterDefinition.getParameterTypes().indexOf(name);
			if (nameIndex != -1){
				typeCombo.select(nameIndex);
			} else {
				typeCombo.select(0);
			}
		}
		
		name.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				validate();
			}
		});
		validate();
		setControl(container);
	}
	
	private void validate(){
		if (name.getText().isEmpty()){
			setPageComplete(false);
			setErrorMessage("The name can't be empty");
		} else if (jd.getParametersMap().containsKey(name.getText())){
			setPageComplete(false);
			setErrorMessage("There is already another parameter with the same name");
		} else {
			setPageComplete(true);
			setErrorMessage(null);
		}
	}
}
