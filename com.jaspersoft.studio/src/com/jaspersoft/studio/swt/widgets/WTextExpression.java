package com.jaspersoft.studio.swt.widgets;

import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.property.descriptor.expression.dialog.JRExpressionEditor;
import com.jaspersoft.studio.utils.Misc;

/** 
 * Expression widget re-usable in custom dialogs and wizards.
 * The text of the expression is represented inside the textbox. 
 * The button enables the use of the expression editor (shown in a separate dialog).
 * An additional label can be specified, and based upon the <code>showMode</code> flag of the constructor
 * it will be drawn on top of the textbox and button, or on the their left.
 * <p>
 * <b>ADDITIONAL NOTE</b>: the widget has a default internal {@link GridLayout} of 2 columns, but in case the <code>LABEL_ON_LEFT</code> flag is specified
 * 3 columns are used instead.
 *   
 * @author mrabbi
 *
 */
public class WTextExpression extends Composite {

	/** No label specified */
	public static final int LABEL_NONE=0x0000;
	/** Label painted on the left of the expression box */
	public static final int LABEL_ON_LEFT=0x0001;	
	/** Label painted on top of the expression box and button */
	public static final int LABEL_ON_TOP=0x0002;
	/** Number of lines for the text expression widget */
	public static final int TEXT_LINE_NUMBERS=3;
	
	private static final String BUTTON_ICON_PATH="icons/resources/expressionedit-16.png"; //$NON-NLS-1$
	private int customTextLinesNumber=-1;
	
	// Widgets
	private JRDesignExpression expression;
	private Text textExpression;
	private Button btnEditExpression;
	private Label label;
	
	/**
	 * Creates the new widget made only by a textbox and a button.
	 * 
	 * @param parent parent composite
	 * @param style widget style
	 */
	public WTextExpression(Composite parent, int style) {
		this(parent,style,null,LABEL_NONE,-1);
	}
	
	/**
	 * Creates the new widget made only by a textbox and a button.
	 * 
	 * @param parent parent composite
	 * @param style widget style
	 * @param number of text lines to show
	 */
	public WTextExpression(Composite parent, int style, int linesNum) {
		this(parent,style,null,LABEL_NONE,linesNum);
	} 
	
	/**
	 * Creates the new widget depending on the specified flag <code>showMode</code> 
	 * and using the <code>textLabel</code> as additional input.
	 * <p>
	 * Please note that if <code>textLabel</code> is <code>null</code> or <code>showMode</code>
	 * uses the default value of <code>LABEL_NONE</code>, the label is not created. 
	 * 
	 * @param parent parent composite
	 * @param style widget style
	 * @param textLabel the information label associated to the widget
	 * @param showMode flag to specify the label position
	 * 
	 */
	public WTextExpression(Composite parent, int style, String textLabel, int showMode) {
		this(parent, style, textLabel, showMode,-1);
	}
	
	/**
	 * Creates the new widget depending on the specified flag <code>showMode</code> 
	 * and using the <code>textLabel</code> as additional input.
	 * The number of lines is used for the height hint computation of the text widget that will contain the expression.
	 * A valid value must be greater than zero, otherwise the default value ({@link #TEXT_LINE_NUMBERS}) is used.
	 * <p>
	 * Please note that if <code>textLabel</code> is <code>null</code> or <code>showMode</code>
	 * uses the default value of <code>LABEL_NONE</code>, the label is not created. 
	 * 
	 * @param parent parent composite
	 * @param style widget style
	 * @param textLabel the information label associated to the widget
	 * @param showMode flag to specify the label position
	 * @param number of text lines to show
	 * 
	 */
	public WTextExpression(Composite parent, int style, String textLabel, int showMode, int linesNum) {
		super(parent, style);
		this.customTextLinesNumber=linesNum;
		
		int columnsNum=2;
		if(showMode==LABEL_ON_LEFT && textLabel!=null){
			columnsNum=3;
		}
		
		GridLayout widgetLayout=new GridLayout(columnsNum,false);
		widgetLayout.marginHeight=0;
		widgetLayout.marginWidth=0;
		setLayout(widgetLayout);
		
		if (columnsNum==3){
			label = new Label(this, SWT.NONE);
			label.setLayoutData(new GridData(SWT.FILL,SWT.TOP,false,false,1,1));
			label.setText(textLabel);
		}
		else if (columnsNum==2 && showMode==LABEL_ON_TOP && textLabel!=null){
			// Draws also the information label on the top
			label=new Label(this, SWT.NONE);
			label.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,2,1));
			label.setText(textLabel);			
		}
		
		textExpression = new Text(this, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		GC gcText=new GC(textExpression);
		FontMetrics textFM = gcText.getFontMetrics();
		int heightHint=textFM.getHeight()*getTextLinesNumber();
		gcText.dispose();
		GridData gdTextExpression=new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gdTextExpression.heightHint=heightHint;
		textExpression.setLayoutData(gdTextExpression);
		textExpression.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent e) {
				String text = textExpression.getText();
				if(text.isEmpty()){
					setExpression(null);
				}
				else {
					setExpression(new JRDesignExpression(text));	
				}				
			}
		});
				
		btnEditExpression = new Button(this, SWT.FLAT);
		btnEditExpression.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		btnEditExpression.setImage(JaspersoftStudioPlugin.getImage(BUTTON_ICON_PATH));
		btnEditExpression.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				JRExpressionEditor wizard = new JRExpressionEditor();
				wizard.setValue(expression);
				WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					String newExpText = wizard.getValue().getText();
					setExpression(new JRDesignExpression(newExpText));
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}

	/** 
	 * Sets the expression for the widget. 
	 * 
	 * @param exp the expression to set
	 */
	public void setExpression(JRDesignExpression exp){
		this.expression=exp;
		
		// PAY ATTENTION: Checks are needed in order to avoid notification
		// loop due to the modifyEvent raised after a setText call.
		if (exp!=null && !exp.getText().equals(textExpression.getText())){
			this.textExpression.setText(exp.getText());
			this.textExpression.setToolTipText(exp.getText());			
		}
		else if (exp==null && !textExpression.getText().isEmpty()){
			this.textExpression.setText(""); //$NON-NLS-1$
			this.textExpression.setToolTipText("");			 //$NON-NLS-1$
		}

	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		this.textExpression.setEnabled(enabled);
		this.btnEditExpression.setEnabled(enabled);
		if (this.label!=null){
			this.label.setEnabled(enabled);
		}
	}
	
	/** 
	 * Returns the currently set expression.
	 * 
	 * @return the {@link JRDesignExpression} instance set
	 */
	public JRDesignExpression getExpression(){
		return this.expression;
	}
	
	/**
	 * Gets the currently set number of lines for the widget.
	 * This value is used for the calculation of the text expression height hint.
	 * Default value is {@value #TEXT_LINE_NUMBERS}.
	 * 
	 * @return the number of lines 
	 */
	protected int getTextLinesNumber(){
		if(customTextLinesNumber>0)
			return customTextLinesNumber;
		return TEXT_LINE_NUMBERS;
	}
	
	/**
	 * Returns the text contained inside the widget text-box
	 * that represents the actual {@link JRDesignExpression} instance.
	 * 
	 * @return the text representation of the expression, an empty string if the expression is <code>null</code>
	 */
	public String getText(){
		return this.expression==null ? "" : Misc.nvl(this.getExpression().getText());
	}
}
