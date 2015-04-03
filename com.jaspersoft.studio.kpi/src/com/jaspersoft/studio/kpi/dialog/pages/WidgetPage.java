package com.jaspersoft.studio.kpi.dialog.pages;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.kpi.dialog.AbstractKPIConfigurationPage;
import com.jaspersoft.studio.kpi.dialog.pages.widget.WidgetDefinition;
import com.jaspersoft.studio.kpi.messages.Messages;

public class WidgetPage extends AbstractKPIConfigurationPage {

	private static final List<WidgetDefinition> widgetsList = new ArrayList<WidgetDefinition>();
	
	public static final String WIDGET_PARAMETER = "widget"; //$NON-NLS-1$
	
	static{
		widgetsList.add(new WidgetDefinition("default", "/resources/widgets/default.png")); //$NON-NLS-1$ //$NON-NLS-2$
		widgetsList.add(new WidgetDefinition("gauge", "/resources/widgets/gauge.png")); //$NON-NLS-1$ //$NON-NLS-2$
		widgetsList.add(new WidgetDefinition("number", "/resources/widgets/number.png")); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	private List<ToolItem> buttonsList = new ArrayList<ToolItem>();
	
	private SelectionAdapter buttonSelected = new SelectionAdapter() {
		
		public void widgetSelected(SelectionEvent e) {
			writeValue( (String)e.widget.getData());
		};
		
	};
	
	private void writeValue(String value){
		JRDesignParameter parameter = getParameter();
		String exp = "\"" + value + "\""; //$NON-NLS-1$ //$NON-NLS-2$
		JRDesignExpression newExpression = new JRDesignExpression(exp);
		parameter.setDefaultValueExpression(newExpression);
	}
	
	@Override
	public String getName() {
		return Messages.WidgetPage_pageLabel;
	}

	@Override
	protected Composite createComposite(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		container.setLayout(new GridLayout(1, false));
		
		ToolBar bar = new ToolBar(container, SWT.FLAT);
		bar.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		
		String selectedWidget = getSelectedWidget();
		if (selectedWidget == null || selectedWidget.isEmpty()) {
			selectedWidget = "default"; //$NON-NLS-1$
			writeValue(selectedWidget);
		}
		for(WidgetDefinition widget : widgetsList){
			ToolItem newButton = new ToolItem(bar, SWT.RADIO );
			newButton.setImage(widget.getImage());
			newButton.setData(widget.getWidgetType());
			if (widget.getWidgetType().equals(selectedWidget)){
				newButton.setSelection(true);
			}
			newButton.addSelectionListener(buttonSelected);
			buttonsList.add(newButton);
		}
		bar.pack ();
		return container;
	}
	
	private String getSelectedWidget(){
		JRDesignParameter parameter = getParameter();
		JRExpression exp = parameter.getDefaultValueExpression();
		if (exp == null) return ""; //$NON-NLS-1$
		String exp_text = exp.getText();
		if (exp_text.startsWith("\"")); exp_text = exp_text.substring(1); //$NON-NLS-1$
		if (exp_text.endsWith("\"")); exp_text = exp_text.substring(0, exp_text.length()-1); //$NON-NLS-1$
		return exp_text;
	}

	private JRDesignParameter getParameter(){
		JRParameter parameter = jd.getParametersMap().get(WIDGET_PARAMETER);
		if (parameter == null){
			JRDesignParameter newParameter = new JRDesignParameter();
			newParameter.setName(WIDGET_PARAMETER);
			try {
				jd.addParameter(newParameter);
			} catch (JRException e) {
				e.printStackTrace();
			} 
			return newParameter;
		}
		return ((JRDesignParameter)parameter);
	}
	
	@Override
	public String getTitle() {
		return Messages.WidgetPage_pageTitle;
	}
}

