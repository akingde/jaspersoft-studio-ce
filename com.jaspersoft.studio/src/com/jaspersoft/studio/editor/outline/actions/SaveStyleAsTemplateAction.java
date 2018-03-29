/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.editor.style.wizard.StyleTemplateExportWizard;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MPage;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.style.MConditionalStyle;
import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.model.style.MStyleTemplate;
import com.jaspersoft.studio.model.style.MStyles;
import com.jaspersoft.studio.model.style.command.CreateStyleTemplateCommand;
import com.jaspersoft.studio.model.style.command.SimpleDeleteStyleCommand;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Pair;
import net.sf.jasperreports.eclipse.util.StringUtils;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignReportTemplate;
import net.sf.jasperreports.engine.design.JRDesignStyle;

/**
 * Action to open the wizard to export one or more JRStyle as an external
 * template style file. This also propose a question to substitute the exported
 * styles with the template reference. This action should be used only on the
 * report editor (and subeditor), it is not suited for the Style template
 * editor.
 * 
 * @author Orlandin Marco
 * 
 */
public class SaveStyleAsTemplateAction extends ACachedSelectionAction {

	/** The Constant ID. */
	public static final String ID = "export_style_as_template"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *            The part for this action
	 */
	public SaveStyleAsTemplateAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}

	/**
	 * Initializes this action's text and images.
	 */
	@Override
	protected void init() {
		super.init();
		setText(Messages.ExportStyleAsTemplateAction_actionName);
		setToolTipText(Messages.ExportStyleAsTemplateAction_actionTooltip);
		setId(SaveStyleAsTemplateAction.ID);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/export_style.png")); //$NON-NLS-1$
		setEnabled(false);
		setLazyEnablementCalculation(true);
	}

	/**
	 * Enable only if there is at least one style that can be exported
	 */
	@Override
	protected boolean calculateEnabled() {
		List<Object> elements = editor.getSelectionCache().getSelectionModelForType(MStyle.class);
		if (elements.size() == getSelectedObjects().size()) {
			for (Object rawStyle : elements) {
				MStyle style = (MStyle) rawStyle;
				if (!style.isEditable() || (style instanceof MConditionalStyle))
					return false;
			}
		} else
			return false;
		return true;
	}

	@Override
	public void run() {
		Pair<List<MStyle>, List<JRStyle>> styles = getSelectedStyles();
		StyleTemplateExportWizard importWizard = new StyleTemplateExportWizard(styles.getValue());
		ISelection selection = getSelection();
		StructuredSelection structured = null;
		if (selection instanceof StructuredSelection) {
			structured = (StructuredSelection) selection;
		} else {
			structured = new StructuredSelection();
		}
		importWizard.init(PlatformUI.getWorkbench(), structured);
		WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), importWizard);
		if (dialog.open() == Dialog.OK) {
			// Propose the question to substitute the styles
			if (MessageDialog.openQuestion(UIUtils.getShell(), Messages.SaveStyleAsTemplateAction_replaceTitle,
					Messages.SaveStyleAsTemplateAction_replaceMessage)) {
				// generate the commands to substitute the styles
				JSSCompoundCommand cmd = new JSSCompoundCommand(Messages.SaveStyleAsTemplateAction_replaceTitle,
						styles.getKey().get(0)); // $NON-NLS-1$
				JasperReportsConfiguration jconfig = styles.getKey().get(0).getJasperConfiguration();
				ANode root = getRoot(styles.getKey().get(0));
				Map<String, List<ANode>> usedStyles = root.getUsedStyles();

				for (MStyle style : styles.getKey()) {
					Command deleteCommand = getDeleteCommand(style);
					if (deleteCommand != null) {
						cmd.add(deleteCommand);
						String styleName = style.getValue().getName();
						// update the elements using the previously internal style to use the external
						// one instead
						List<ANode> elementsUsingStyle = usedStyles.get(styleName);
						if (elementsUsingStyle != null) {
							for (ANode element : elementsUsingStyle) {
								SetValueCommand setStyleCommand = new SetValueCommand();
								setStyleCommand.setTarget((IPropertySource) element);
								setStyleCommand.setPropertyValue(styleName);
								setStyleCommand.setPropertyId(JRDesignElement.PROPERTY_PARENT_STYLE);
								cmd.add(setStyleCommand);
							}
						}
					}

				}
				IFile templateFile = importWizard.getReportFile();
				Command createCommand = getCreateCommand(templateFile, styles.getKey().get(0).getParent());
				if (createCommand != null) {
					cmd.add(createCommand);
				}

				execute(cmd);
				if (jconfig != null) {
					jconfig.refreshCachedStyles();
				}
			} else {
				// The styles are not replace, open the editor on the new template
				importWizard.openStyleEditor();
			}
		}
	}

	private ANode getRoot(ANode currentNode) {
		if (currentNode instanceof MPage) {
			return getRoot(((MPage) currentNode).getRealParent());
		} else if (currentNode instanceof MRoot) {
			return currentNode;
		} else {
			return getRoot(currentNode.getParent());
		}
	}

	/**
	 * Generate the command to create the template style node
	 * 
	 * @param templateFile
	 *            the file of the template. Must be a jrtx file
	 * @param parent
	 *            the parent where the new node to create. Must be an MStyle
	 * @return the command to create the template reference node on the parent.
	 */
	protected Command getCreateCommand(IFile templateFile, ANode parent) {
		JRDesignReportTemplate jrTemplate = MStyleTemplate.createJRTemplate();
		jrTemplate.setSourceExpression(
				getFileExpression(templateFile, parent.getJasperConfiguration().getAssociatedReportFile()));
		MStyleTemplate templateModel = new MStyleTemplate(null, jrTemplate, -1);
		return new CreateStyleTemplateCommand((MStyles) parent, templateModel, 0);
	}

	/**
	 * Generate the expression to reference the jrtx file of a template
	 * 
	 * @param file
	 *            the file to reference, must be a jrtx
	 * @param contextFile
	 *            the context of the report, should be the report where the file is
	 *            imported
	 * @return the expression to reference the style
	 */
	protected JRDesignExpression getFileExpression(IFile file, IFile contextFile) {
		String filepath = null;
		// try to convert to relative
		if (contextFile != null && file.getProject().equals(contextFile.getProject())) {
			filepath = file.getProjectRelativePath().toPortableString().replaceAll(file.getProject().getName() + "/", //$NON-NLS-1$
					""); //$NON-NLS-1$
		} else {
			filepath = file.getRawLocationURI().toASCIIString();
		}
		// Change the standard separator with an universal one
		String fileExpressionText = StringUtils.replaceBackslashWithDoubleBackslash(
				filepath.replace(System.getProperty("file.separator").charAt(0), '/')); //$NON-NLS-1$
		JRDesignExpression jrFileExpression = new JRDesignExpression();
		jrFileExpression.setText("\"" + fileExpressionText + "\"");//$NON-NLS-1$ //$NON-NLS-2$
		return jrFileExpression;
	}

	/**
	 * Return the command to delete a style
	 * 
	 * @param style
	 *            the style to delete
	 * @return the command to delete the style
	 */
	protected Command getDeleteCommand(MStyle style) {
		return new SimpleDeleteStyleCommand((MStyles) style.getParent(), (JRDesignStyle) style.getValue());
	}

	/**
	 * Return the list of all the selected JRStyle. The conditional and external
	 * styles are not exported
	 * 
	 * @return a not null list of JRStyle
	 */
	private Pair<List<MStyle>, List<JRStyle>> getSelectedStyles() {
		List<?> objects = getSelectedObjects();
		if (objects == null || objects.isEmpty()) {
			return new Pair<List<MStyle>, List<JRStyle>>(new ArrayList<MStyle>(), new ArrayList<JRStyle>());
		}
		List<JRStyle> styles = new ArrayList<JRStyle>();
		List<MStyle> modelStyles = new ArrayList<MStyle>();
		for (Object obj : objects) {
			if (obj instanceof EditPart) {
				ANode n = (ANode) ((EditPart) obj).getModel();
				if (n instanceof MStyle && !styles.contains(n.getValue())) {
					styles.add((JRStyle) n.getValue());
					modelStyles.add((MStyle) n);
				}
			}
		}
		return new Pair<List<MStyle>, List<JRStyle>>(modelStyles, styles);
	}
}
