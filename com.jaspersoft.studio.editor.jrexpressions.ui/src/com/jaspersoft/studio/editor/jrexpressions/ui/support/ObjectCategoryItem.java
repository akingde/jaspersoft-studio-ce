/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.jrexpressions.ui.support;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.editor.jrexpressions.ui.JRExpressionsUIPlugin;
import com.jaspersoft.studio.editor.jrexpressions.ui.messages.Messages;
import com.jaspersoft.studio.editor.jrexpressions.ui.support.java.JavaExpressionEditorComposite;

/**
 * The object item representing an available category of elements 
 * that can be used to compose a JR expression.
 * 
 * <p>
 * Example of usage: the navigator tree used inside the {@link JavaExpressionEditorComposite}. 
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class ObjectCategoryItem {

	public static final String ICON_FOLDER_FIELDS = "/resources/icons/fields-16.png"; //$NON-NLS-1$
	public static final String ICON_FOLDER_PARAMETERS = "/resources/icons/parameters-16.png"; //$NON-NLS-1$
	public static final String ICON_FOLDER_VARIABLES = "/resources/icons/variables-16.png"; //$NON-NLS-1$
	public static final String ICON_FOLDER = "/resources/icons/folder.png"; //$NON-NLS-1$
	public static final String ICON_FOLDER_RECENT_EXPRESSIONS = ICON_FOLDER;
	public static final String ICON_FOLDER_FORMULAS = ICON_FOLDER;
	public static final String ICON_CROSSTAB = "/resources/icons/crosstab-16.png"; //$NON-NLS-1$
	public static final String ICON_DATASET = "/resources/icons/dataset-16.png"; //$NON-NLS-1$
	public static final String ICON_FOLDER_RESOURCEKEYS = "/resources/icons/resourcebundles-16-icon.png"; //$NON-NLS-1$

	// Attributes
	private String displayName = null;
	private Image icon = null;
	private Category category=null;
	private Object data; // generic object for application use (i.e: most common case to maintain children)

	/**
	 * Creates a new category item.
	 * 
	 * @param category the kind of the new item
	 */
	public ObjectCategoryItem(Category category) {
		this(category, null);
	}

	/**
	 * Creates a new category item.
	 * 
	 * @param category the kind of the new item
	 * @param customDisplayName a custom display name
	 */
	public ObjectCategoryItem(Category category, String customDisplayName) {
		this(category, customDisplayName, null);
	}

	/**
	 * Creates a new category item.
	 * 
	 * @param category the kind of the new item
	 * @param customDisplayName a custom display name
	 * @param customIconPath a custom icon path
	 */
	public ObjectCategoryItem(Category category, String customDisplayName, String customIconPath) {
		this.category=category;
		this.displayName=(customDisplayName!=null)?customDisplayName:category.getDisplayName();
		if(customIconPath==null){
			customIconPath=category.getIconPath();
		}
		this.icon=ResourceManager.getPluginImage(
				JRExpressionsUIPlugin.PLUGIN_ID, customIconPath);
	}
	
	public Category getCategory(){
		return this.category;
	}
	
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Image getIcon() {
		return icon;
	}

	public void setIcon(Image icon) {
		this.icon = icon;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return displayName;
	}
	
	/**
	 * Enumeration to represent the different kinds of allowed categories
	 * in a navigator tree.
	 * 
	 * @see JavaExpressionEditorComposite Example of usage
	 */
	public enum Category {
		PARAMETERS(Messages.ObjectCategoryItem_CategoryParameters,ICON_FOLDER_PARAMETERS), 
		FIELDS(Messages.ObjectCategoryItem_CategoryFields, ICON_FOLDER_FIELDS), 
		VARIABLES(Messages.ObjectCategoryItem_CategoryVariables, ICON_FOLDER_VARIABLES), 
		BUILT_IN_FUNCTIONS(Messages.ObjectCategoryItem_CategoryBuiltInFunctions, ICON_FOLDER), 
		FUNCTION_CATEGORY(Messages.ObjectCategoryItem_CategoryFunctionCategory,ICON_FOLDER), 
		STATIC_FUNCTION_CATEGORY(Messages.ObjectCategoryItem_CategoryStaticFunctionCategory, ICON_FOLDER), 
		USER_DEFINED_EXPRESSIONS(Messages.ObjectCategoryItem_CategoryUserDefinedExpressions, ICON_FOLDER), 
		RECENT_EXPRESSIONS(Messages.ObjectCategoryItem_CategoryRecentExpressions, ICON_FOLDER), 
		CROSSTAB(Messages.ObjectCategoryItem_CategoryCrosstabs, ICON_CROSSTAB), 
		PDATASET(Messages.ObjectCategoryItem_CategoryPDataset, ICON_DATASET), 
		VDATASET(Messages.ObjectCategoryItem_CategoryVDataset, ICON_DATASET), 
		FDATASET(Messages.ObjectCategoryItem_CategoryFDataset, ICON_DATASET), 
		RESOURCE_KEYS(Messages.ObjectCategoryItem_CategoryResourceKeys, ICON_FOLDER_RESOURCEKEYS); 
		
		private String dislayName;
		private String iconPath;
		
		private Category(String displayName, String iconPath){
			this.dislayName=displayName;
			this.iconPath=iconPath;
		}
		
		/**
		 * @return the display name of the category
		 */
		public String getDisplayName(){
			return dislayName;
		}
		
		/**
		 * @return the path of the icon for the category
		 */
		public String getIconPath(){
			return iconPath;
		}
	}
}
