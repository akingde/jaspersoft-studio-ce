/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.style;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.part.FileEditorInput;

import com.jaspersoft.studio.ExternalStylesManager;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.ReportFactory;
import com.jaspersoft.studio.utils.CacheMap;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.SelectionHelper;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRConditionalStyle;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRReportTemplate;
import net.sf.jasperreports.engine.JRSimpleTemplate;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JRTemplate;
import net.sf.jasperreports.engine.JRTemplateReference;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignReportTemplate;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;

public class StyleTemplateFactory {

	/**
	 * Create a template style node in the JRTX editor, with all the children inside
	 * 
	 */
	public static ANode createNode(ANode parent, Object obj, int index, IFile templateStyleFile,
			JRSimpleTemplate jrst) {
		if (obj instanceof JRDesignStyle) {
			index += jrst.getIncludedTemplatesList().size();
			return new MStyle(parent, (JRDesignStyle) obj, index);
		}
		if (obj instanceof JRTemplateReference) {
			ANode n = new MStyleTemplateReference(parent, (JRTemplateReference) obj, index);
			createTemplateReference(n, ((JRTemplateReference) obj).getLocation(), -1, new HashSet<String>(), false);
		}
		return null;
	}

	/**
	 * Create a template style node in the report editor, with all the children
	 * inside
	 * 
	 */
	public static ANode createTemplate(ANode parent, JRDesignReportTemplate jrObject, int newIndex, IFile file) {
		MStyleTemplate mStyleTemplate = new MStyleTemplate(parent, (JRDesignReportTemplate) jrObject, newIndex);
		JasperReportsConfiguration jConf = parent.getJasperConfiguration();
		IFile project = (IFile) jConf.get(FileUtils.KEY_FILE);
		// Use the style manager to retrieve the styles, so the result is cached
		String str = ExternalStylesManager.evaluateStyleExpression((JRDesignReportTemplate) jrObject, project, jConf);
		if (str != null) {
			createTemplateReference(mStyleTemplate, str, -1, new HashSet<String>(), false);
			return mStyleTemplate;
		}
		return null;
	}

	/**
	 * Create the nodes for a template reference
	 * 
	 * @param parent
	 *            the parent where the node should be created
	 * @param location
	 *            the location of the reference
	 * @param newIndex
	 *            the index of the node
	 * @param loadedResources
	 *            the set of already loaded location, used for nested calls to avoid
	 *            circular loading
	 * @param editable
	 *            flag to mark if the new nodes should be editable or not
	 * @return true if the style was loaded, false otherwise
	 */
	public static boolean createTemplateReference(ANode parent, String location, int newIndex,
			Set<String> loadedResources, boolean editable) {
		if (!loadedResources.contains(location)) {
			JasperReportsConfiguration jConfig = parent.getJasperConfiguration();
			JRTemplate jrst = ExternalStylesManager.getTemplate(jConfig, location);
			if (jrst != null) {
				loadedResources.add(location);
				createTemplate(parent, loadedResources, editable, jrst);
				return true;
			}
		}
		return false;
	}

	/**
	 * Create a node for a JR template and load all it external reosurces
	 * 
	 * @param parent
	 * @param loadedResources
	 * @param editable
	 * @param jrst
	 */
	public static void createTemplate(ANode parent, Set<String> loadedResources, boolean editable, JRTemplate jrst) {
		for (JRTemplateReference s : jrst.getIncludedTemplates()) {
			MStyleTemplateReference p = new MStyleTemplateReference(parent, s, -1);
			p.setEditable(editable);
			createTemplateReference(p, s.getLocation(), -1, loadedResources, editable);
		}
		for (JRStyle s : jrst.getStyles()) {
			APropertyNode n = (APropertyNode) ReportFactory.createNode(parent, s, -2);
			n.setEditable(editable);
		}
	}

	/**
	 * Create the nodes for a styles template container, created in its own editor
	 */
	public static void createTemplateRoot(ANode parent, Set<String> set, IFile file, JRSimpleTemplate jrst) {
		JasperDesign jd = parent.getJasperDesign();
		for (JRTemplateReference s : jrst.getIncludedTemplates()) {
			MStyleTemplateReference p = new MStyleTemplateReference(parent, s, -1);
			p.setEditable(true);
			createTemplateReference(p, s.getLocation(), -1, set, false);
		}
		// Add the normal styles to the JasperDesign
		for (JRStyle s : jrst.getStyles()) {
			APropertyNode n = (APropertyNode) ReportFactory.createNode(parent, s, -2);
			n.setEditable(true);
			if (jd != null) {
				try {
					jd.addStyle(s);
				} catch (JRException e) {
					e.printStackTrace();
				}
			}
		}
		// Add the external styles to the JasperDesign
		for (INode node : parent.getChildren()) {
			if (node instanceof MStyleTemplateReference && jd != null) {
				MStyleTemplateReference externalNode = (MStyleTemplateReference) node;
				JRTemplateReference externalValue = (JRTemplateReference) externalNode.getValue();
				JRDesignReportTemplate jrTemplate = MStyleTemplate.createJRTemplate();
				JRDesignExpression jre = new JRDesignExpression();
				jre.setText("\"" + externalValue.getLocation() + "\"");//$NON-NLS-1$ //$NON-NLS-2$
				jrTemplate.setSourceExpression(jre);
				jd.addTemplate(jrTemplate);
			}
		}
	}

	protected static final File getFile(String location, IFile file) {
		return SelectionHelper.resolveFile(file, location);
	}

	/**
	 * This method try to return a relative path for the style from the current
	 * opened report. If it isn't Possible to find a relative path then the absolute
	 * one is returned
	 * 
	 * @param s
	 *            the container with the style location path
	 * @param fileToBeOpened
	 *            the file currently opened in the jrtx editor
	 * @param file
	 *            the file currently opened in the jrtx editor, in java.io.File
	 *            format
	 * @return an absolute or relative path to the style resource
	 */
	protected static String getStylePath(JRTemplateReference s, File fileToBeOpened, IFile file) {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFile[] fs = root.findFilesForLocationURI(fileToBeOpened.toURI());
		if (fs != null && fs.length > 0) {
			File absoluteFile = getFile(s.getLocation(), fs[0]);
			return absoluteFile != null ? absoluteFile.getAbsolutePath() : s.getLocation();
		} else {
			File absoluteFile = getFile(s.getLocation(), file);
			return absoluteFile != null ? absoluteFile.getAbsolutePath() : s.getLocation();
		}
	}

	/**
	 * This method try to return a relative path for the style from the current
	 * opened report. If it isn't Possible to find a relative path then the absolute
	 * one is returned
	 * 
	 * @param p
	 *            the container with the style location path
	 * @param jConfig
	 *            the configuration of the jrtx file opened in the editor
	 * @return an absolute or relative path to the style resource
	 */
	public static String getStylePath(JRTemplateReference p, JasperReportsConfiguration jConfig) {
		IFile resource = (IFile) jConfig.get(FileUtils.KEY_FILE);
		File file = resource.getLocation().toFile();
		return getStylePath(p, file, resource);
	}

	public static void openEditor(Object obj, IEditorInput editorInput, ANode node) {
		if (obj instanceof JRStyle || obj instanceof JRConditionalStyle) {
			if (node.getParent() instanceof MStyles)
				return;
			if (node instanceof MConditionalStyle)
				node = node.getParent();
			if (node instanceof MStyle)
				node = node.getParent();
		}
		if (node instanceof MStyleTemplate)
			obj = node.getValue();
		else if (node instanceof MStyleTemplateReference) {
			IFile file = getFile(node, ((FileEditorInput) editorInput).getFile());
			JRTemplateReference st = (JRTemplateReference) node.getValue();
			SelectionHelper.openEditor(file, st.getLocation());
		}

		if (obj instanceof JRDesignReportTemplate) {
			if (editorInput instanceof FileEditorInput) {
				JRDesignReportTemplate s = (JRDesignReportTemplate) obj;
				if (s.getSourceExpression() != null)
					SelectionHelper.openEditor((FileEditorInput) editorInput, ExpressionUtil
							.cachedExpressionEvaluationString(s.getSourceExpression(), node.getJasperConfiguration()));
			}
			return;
		}
	}

	private static IFile getFile(ANode node, IFile refFile) {
		List<Object> plist = new ArrayList<>();
		ANode p = node;
		JasperReportsConfiguration jConfig = node.getJasperConfiguration();
		plist.add(p.getValue());
		while (!(p.getParent() instanceof MStyles)) {
			p = p.getParent();
			plist.add(p.getValue());
		}

		MStyleTemplate mst = (MStyleTemplate) p;
		JRDesignReportTemplate drt = (JRDesignReportTemplate) mst.getValue();

		return resolveTemplates(refFile, plist, jConfig, drt);
	}

	private static Map<JasperDesign, String[]> cstyles = new CacheMap<>(2000);

	public static String[] getAllStyles(JasperReportsConfiguration jConf, JRDesignElement jrElement) {
		// IMPROVEMENT: listen for all file changes, and update only when needed
		JasperDesign jd = jConf.getJasperDesign();
		String[] items = cstyles.get(jd);
		if (items == null) {
			// If i have not a JD i return an empty array to avoid exception
			if (jd == null)
				return new String[] {};
			JRStyle[] styles = jd.getStyles();
			List<JRStyle> slist = getStyles(jConf, jd, (IFile) jConf.get(FileUtils.KEY_FILE));
			List<String> itemsList = new ArrayList<>();
			itemsList.add("");
			for (JRStyle style : styles) {
				itemsList.add(style.getName());
			}
			for (JRStyle style : slist) {
				itemsList.add(style.getName());
			}
			items = itemsList.toArray(new String[itemsList.size()]);
			cstyles.put(jd, items);
		}
		return items;
	}

	public static String[] getAllStyles(JasperReportsConfiguration jConf, JRBaseStyle jrStyle) {
		JasperDesign jd = jConf.getJasperDesign();
		// If i have not a JD i return an empty array to avoid exception
		if (jd == null)
			return new String[] {};
		JRStyle[] styles = jd.getStyles();
		List<JRStyle> slist = getStyles(jConf, jd, (IFile) jConf.get(FileUtils.KEY_FILE));
		String actualStyleName = jrStyle.getName();
		List<String> availableStyles = new ArrayList<>();
		availableStyles.add("");
		for (JRStyle style : styles)
			if (!style.getName().equals(actualStyleName))
				availableStyles.add(style.getName());
		for (JRStyle style : slist)
			availableStyles.add(style.getName());
		return availableStyles.toArray(new String[availableStyles.size()]);
	}

	private static List<JRStyle> getStyles(JasperReportsConfiguration jConfig, JasperDesign jd, IFile file) {
		List<JRStyle> list = new ArrayList<>();
		for (JRReportTemplate t : jd.getTemplatesList())
			list.addAll(ExternalStylesManager.getStyles(t, file, jConfig));
		return list;
	}

	protected static IFile resolveTemplates(IFile refFile, List<Object> plist, JasperReportsConfiguration jConfig,
			JRDesignReportTemplate drt) {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		String str = ExpressionUtil.cachedExpressionEvaluationString(drt.getSourceExpression(), jConfig);
		if (str != null) {
			if (refFile == null)
				refFile = ((IFileEditorInput) SelectionHelper.getActiveJRXMLEditor().getEditorInput()).getFile();

			for (int i = plist.size() - 1; i >= 0; i--) {
				Object obj = plist.get(i);
				if (obj instanceof JRDesignReportTemplate) {
					str = ExpressionUtil.cachedExpressionEvaluationString(
							((JRDesignReportTemplate) obj).getSourceExpression(), jConfig);
				} else if (obj instanceof JRTemplateReference) {
					str = ((JRTemplateReference) obj).getLocation();
				}

				File fileToBeOpened = SelectionHelper.resolveFile(refFile, str);
				if (fileToBeOpened != null && fileToBeOpened.exists() && fileToBeOpened.isFile()) {
					IFile[] fs = root.findFilesForLocationURI(fileToBeOpened.toURI());
					if (fs != null && fs.length > 0) {
						refFile = fs[0];
					} else
						break;
				}
			}
			return refFile;
		}
		return null;
	}

}
