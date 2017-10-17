/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.wizards.category;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.nebula.widgets.gallery.NoGroupRenderer;
import org.eclipse.nebula.widgets.gallery.RoundedGalleryItemRenderer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.messages.MessagesByKeys;
import com.jaspersoft.studio.model.subreport.command.wizard.SubreportWizard;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.templates.StudioTemplateManager;
import com.jaspersoft.studio.utils.SWTImageEffects;
import com.jaspersoft.studio.utils.SWTImageEffects.Glow;
import com.jaspersoft.studio.wizards.BuiltInCategories;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSWizard;
import com.jaspersoft.studio.wizards.JSSWizardPage;
import com.jaspersoft.templates.TemplateBundle;
import com.jaspersoft.templates.ValidatedTemplateBundle;
import com.jaspersoft.templates.WizardTemplateBundle;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

/**
 * This page is used to allow the user to select a template bundle. The selected template bundle (TemplateBundle) is
 * stored in the JSSWizard.getSettings() map with the key "template". Any following page can use this information to
 * propose specific defaults (i.e. the new file name...)
 * 
 * @author gtoffoli
 * 
 */
public class ReportTemplatesWizardPage extends JSSWizardPage {

	protected static final String TEMPLATE_KEY = "template";
	
	private static final int GALLERY_HEIGHT = 100;

	private static final int GALLERY_WIDTH = 100;
	
	/**
	 * Symbol to separate the categories names
	 */
	public static final String TEMPLATE_CATEGORY_SEPARATOR = "\n"; //$NON-NLS-1$

	/**
	 * Widget to select the scale factor of the template preview
	 */
	private Scale scale;

	/**
	 * Hashmap to cache the created gallery for a category
	 */
	private HashMap<String, Gallery> cachedGalleries = new HashMap<String, Gallery>();

	/**
	 * Hashmap to cache for every template bundle a list of the category it belong
	 */
	private HashMap<TemplateBundle, HashSet<String>> categoryCache = new HashMap<TemplateBundle, HashSet<String>>();

	/**
	 * List of all the categories key shown, in the order they was loaded
	 */
	private List<TemplateCategory> categoryList;

	/**
	 * Stack layout used to stack the gallery and show only the one connected to the selected Category
	 */
	private StackLayout layout;

	/**
	 * List of all the template bundle available
	 */
	private List<TemplateBundle> bundles;

	/**
	 * Composite where every new gallery is placed
	 */
	private Composite galleryComposite;

	/**
	 * The template bundle actually selected
	 */
	private TemplateBundle selectedTemplate = null;
	
	/**
	 * Set used to keep track of the created template pages for each bundle. On this
	 * pages can be called the method to notify the wizard closed when it happen
	 */
	private HashSet<TemplateBundle> createdBundles = new HashSet<TemplateBundle>();
	
	/**
	 * Mouse wheel listener used to change the zoomfactor when the 
	 * mouse wheel is used when the ctrl key is pressed
	 */
	MouseWheelListener scaleListener = new MouseWheelListener() {
		
		@Override
		public void mouseScrolled(MouseEvent e) {
			if ((e.stateMask & SWT.CTRL) != 0){
				int direction = (e.count > 0) ? 1 : -1;
				scale.setSelection(scale.getSelection()+direction);
				zoomModified();
			}
		}
	};

	/**
	 * Return the wizard template bundle selected by the user
	 * 
	 * @return a wizard template bundle
	 */
	public WizardTemplateBundle getTemplateBundle() {
		return (WizardTemplateBundle)selectedTemplate;
	}

	/**
	 * Create the wizard.
	 */
	public ReportTemplatesWizardPage() {
		super("templatenewreportwizardPage"); //$NON-NLS-1$
		setTitle(Messages.ReportTemplatesWizardPage_title);
		setDescription(Messages.ReportTemplatesWizardPage_description);
	}

	/**
	 * Return the context name for the help of this page
	 */
	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_TEMPLATE_PAGE;
	}

	/**
	 * 
	 * Selection listener for the list of category
	 * 
	 * @author Orlandin Marco
	 * 
	 */
	private class CategoryChooser extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			int selectionIndex = ((Table) e.widget).getSelectionIndex();
			String selectedCategory = categoryList.get(selectionIndex).getCategoryKey();
			showGallery(selectedCategory);
		}

		public void widgetDefaultSelected(SelectionEvent event) {
			String selectedCategory = categoryList.get(0).getCategoryKey();
			showGallery(selectedCategory);
		}
	}

	/**
	 * Show the gallery associated to a precise category key. If the gallery for that key is cached then it is show
	 * directly, otherwise it is created, populated and then cached and shown.
	 * 
	 * @param galleryCategory
	 *          the key of the gallery.
	 */
	private void showGallery(String galleryCategory) {
		Gallery toShow = cachedGalleries.get(galleryCategory);
		if (toShow == null)
			toShow = createGalleryForCategory(galleryCategory);
		layout.topControl = toShow;
		galleryComposite.layout();
		GalleryItem rootItem = toShow.getItem(0);
		if (toShow.getSelectionCount() <= 0 && rootItem.getItemCount() > 0) {
			toShow.setSelection(new GalleryItem[] { rootItem.getItem(0) });
			setPageComplete(validatePage());
		}
		storeSettings();
		setPageComplete(validatePage());
		zoomModified();
	}

	/**
	 * Create a new gallery and return it as parameter. The new gallery already had the listener added
	 * 
	 * @return a new gallery component
	 */
	private Gallery createGalleryComponent() {
		Gallery gal = new Gallery(galleryComposite, SWT.VIRTUAL | SWT.V_SCROLL | SWT.BORDER);
		NoGroupRenderer gr = new NoGroupRenderer();
		gr.setMinMargin(2);
		gr.setItemSize(GALLERY_WIDTH, GALLERY_HEIGHT);
		gr.setAutoMargin(true);
		gal.setGroupRenderer(gr);
		RoundedGalleryItemRenderer ir = new RoundedGalleryItemRenderer();
		ir.setShowLabels(true);
		gal.setItemRenderer(ir);

		gal.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				storeSettings();
				setPageComplete(validatePage());
			}
		});
		gal.addMouseWheelListener(scaleListener);
		gal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				if (validatePage()) {
					getContainer().showPage(getNextPage());
				}
			}
		});
		return gal;
	}
	
	/**
	 * Pre-caches the images used by the new report wizard.
	 * It iterates the report bundles and checks if their preview image is cached.
	 * <p>
	 * 
	 * If not, then it creates and caches it, otherwise it doesn't do nothing.
	 * This operation can be executed during the application startup to speedup the 
	 * first opening of the new Report wizard dialog.
	 * <p>
	 * 
	 * The images are cached into the ResourceManager and disposed only when the application is closed.
	 */
	public static void templateImagesPreCache() {
		final RGB greyColor =  new RGB(192, 192, 192);
		List<TemplateBundle> bundles = StudioTemplateManager.getInstance().getTemplateBundles();
		for (TemplateBundle b : bundles) {
			if (b instanceof WizardTemplateBundle) {
				//use the ResourceManager cache for the images
				WizardTemplateBundle wizardBundle = (WizardTemplateBundle) b;
				Image itemImage = ResourceManager.getImage(wizardBundle.getIcon());

				if (itemImage != null) {
					// Add viewer required effects to the images shown...
					String selectedImageKey = wizardBundle.getTemplateURL().toExternalForm() + "selectedImage"; //$NON-NLS-1$
					Image selectedImg = ResourceManager.getImage(selectedImageKey);
					if (selectedImg == null) {
						selectedImg = new Image(UIUtils.getDisplay(), SWTImageEffects.extendArea(itemImage.getImageData(), 40, null));
						ResourceManager.addImage(selectedImageKey, selectedImg);
					}
					String standardShadowedImgeKey = wizardBundle.getTemplateURL().toExternalForm() + "standardShadowedImg"; //$NON-NLS-1$
					Image standardShadowedImg = ResourceManager.getImage(standardShadowedImgeKey);
					if (standardShadowedImg == null) {
						standardShadowedImg = new Image(UIUtils.getDisplay(), Glow.glow(itemImage.getImageData(),	ResourceManager.getColor(greyColor), 40, 0, 255));
						ResourceManager.addImage(standardShadowedImgeKey, standardShadowedImg);
					}
				}
			}
		}
	}
	

	/**
	 * For a gallery create all the preview of a precise category
	 * 
	 * @param gal
	 *          the gallery
	 * @param categoryName
	 *          key of the category
	 */
	private void craeteItems(final Gallery gal, final String categoryName) {
		final GalleryItem itemGroup = new GalleryItem(gal, SWT.NONE);
		final String universalCategory = categoryList.get(0).getCategoryKey();
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				try {
					getContainer().run(false, false, new IRunnableWithProgress() {

						@Override
						public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
							monitor.beginTask(Messages.ReportTemplatesWizardPage_loadtemplates, IProgressMonitor.UNKNOWN);
							Display.getDefault().syncExec(new Runnable() {

								@Override
								public void run() {
									for (TemplateBundle b : bundles) {
										HashSet<String> bundleCategories = categoryCache.get(b);
										if (canBeShown(b) && (categoryName.equals(universalCategory) || (bundleCategories!=null && bundleCategories.contains(categoryName)))) {
											GalleryItem item = new GalleryItem(itemGroup, SWT.NONE);
											item.setData(TEMPLATE_KEY, b);

											if (b instanceof WizardTemplateBundle) {
												//itemImage is already cached in the ResourceManager by the class JrxmlTemplateBundle
												WizardTemplateBundle jrxmlBundle = (WizardTemplateBundle)b;
												Image itemImage = ResourceManager.getImage(jrxmlBundle.getIcon());

												if (itemImage != null) {
													// Add viewer required effects to the images shown...
													String selectedImageKey = jrxmlBundle.getTemplateURL().toExternalForm()+"selectedImage"; //$NON-NLS-1$
													Image selectedImg = ResourceManager.getImage(selectedImageKey);
													if (selectedImg == null){
														selectedImg = new Image(UIUtils.getDisplay(), SWTImageEffects.extendArea(itemImage.getImageData(), 40, null));
														ResourceManager.addImage(selectedImageKey, selectedImg);
													}
													String standardShadowedImgeKey = jrxmlBundle.getTemplateURL().toExternalForm()+"standardShadowedImg"; //$NON-NLS-1$
													Image standardShadowedImg = ResourceManager.getImage(standardShadowedImgeKey);
													if (standardShadowedImg == null){
														standardShadowedImg = new Image(UIUtils.getDisplay(), Glow.glow(itemImage.getImageData(), ResourceManager.getColor(SWT.COLOR_GRAY), 40, 0, 255));
														ResourceManager.addImage(standardShadowedImgeKey, standardShadowedImg);
													}
													item.setSelectedImage(selectedImg);
													item.setStandardImage(standardShadowedImg);
													item.setImage(standardShadowedImg);
													//The images are cached into the ResourceManager and disposed at the end
												}
												item.setText(b.getLabel());
											}
										}
										if (monitor.isCanceled())
											break;
									}
									if (!bundles.isEmpty())
										selectedTemplate = bundles.get(0);
									GalleryItem rootItem = gal.getItem(0);
									if (gal.getSelectionCount() <= 0 && rootItem.getItemCount() > 0) {
										gal.setSelection(new GalleryItem[] { rootItem.getItem(0) });
										storeSettings();
										setPageComplete(validatePage());
									}
								}
							});
						}
					});
					getContainer().updateButtons();
				} catch (InvocationTargetException e) {
					UIUtils.showError(e);
				} catch (InterruptedException e) {
					UIUtils.showError(e);
				}
			}
		});
	}

	private Gallery createGalleryForCategory(String categoryName) {
		Gallery gal = createGalleryComponent();
		craeteItems(gal, categoryName);
		cachedGalleries.put(categoryName, gal);
		return gal;
	}

	/**
	 * Create contents of the wizard.
	 * 
	 * @param parent
	 */
	public void createControl(Composite parent) {
		
		Composite container = new Composite(parent, SWT.NULL);
		container.setLayout(new GridLayout(2, false));

		
		Label lbl = new Label(container, SWT.NONE);
		lbl.setText(Messages.ReportTemplatesWizardPage_zoom);
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.FILL_HORIZONTAL);
		lbl.setLayoutData(gd);

		scale = new Scale(container, SWT.NONE);
		scale.setMinimum(1);
		scale.setMaximum(50);
		scale.setIncrement(1);
		scale.setPageIncrement(5);

		SashForm sashForm = new SashForm(container, SWT.NONE);
		GridData sashData = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		sashData.widthHint = 700;
		sashForm.setLayoutData(sashData);

		// list = new org.eclipse.swt.widgets.List(sashForm, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
		Table table = new Table(sashForm, SWT.V_SCROLL | SWT.SINGLE | SWT.BORDER);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));

		gd = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gd.widthHint = 150;
		scale.setLayoutData(gd);

		galleryComposite = new Composite(sashForm, SWT.NONE);
		GridData galleryData = new GridData(GridData.FILL_BOTH);
		galleryComposite.setLayoutData(galleryData);
		layout = new StackLayout();
		galleryComposite.setLayout(layout);

		categoryList = new ArrayList<TemplateCategory>();
		for(String builtInCategory : BuiltInCategories.getCategoriesList()){
			//Use a prefix since some category can have a really common name that overlap with 
			//other localization keys
			String key = BuiltInCategories.CATEGORY_PREFIX + builtInCategory;
			String localizedName = builtInCategory;
			if (MessagesByKeys.hasTranslation(key)){
				localizedName = MessagesByKeys.getString(key);
			}
			categoryList.add(new TemplateCategory(builtInCategory, localizedName));
		}
		for (TemplateCategory cat : categoryList) {
			cachedGalleries.put(cat.getCategoryKey(), null);
		}
		bundles = StudioTemplateManager.getInstance().getTemplateBundles();
		findTemplates();
		// initializeBackgroundData();

		sashForm.setWeights(new int[] { 20, 80 });
		scale.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				zoomModified();
			}
		});

		container.addMouseWheelListener(scaleListener);
		//galleryComposite.addMouseWheelListener(scaleListener);

		scale.setSelection(6);
		// Manually fire the event because the invocation
		// of #Scale.selection() does not fire it.
		zoomModified();

		createTableColumn(table);
		showGallery(categoryList.get(0).getCategoryKey());
		setControl(container);
	}

	private void createTableColumn(Table table) {
		table.setHeaderVisible(true);
		TableColumn[] col = new TableColumn[1];
		col[0] = new TableColumn(table, SWT.NONE);
		col[0].setText(Messages.ReportTemplatesWizardPage_categories);

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(100, false));
		table.setLayout(tlayout);

		for (TableColumn c : col)
			c.pack();

		TableViewer tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new ListContentProvider());
		tableViewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				TemplateCategory category = (TemplateCategory)element;
				return category.getCategoryName();
			}
		});
		tableViewer.setInput(categoryList);
		table.addSelectionListener(new CategoryChooser());
		table.setSelection(0);
	}

	@SuppressWarnings("unused")
	private void initializeBackgroundData() {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				for (int i = 1; i < categoryList.size(); i++) {
					String category = categoryList.get(i).getCategoryKey();
					if (cachedGalleries.get(category) == null)
						createGalleryForCategory(category);
				}
			}
		});
	}

	/**
	 * Method that handles the zoom modification (scale widget).
	 */
	private void zoomModified() {
		double c = 1 + 0.1 * scale.getSelection();
		if (layout.topControl != null) {
			NoGroupRenderer gr = (NoGroupRenderer) ((Gallery) layout.topControl).getGroupRenderer();
			gr.setItemSize((int) (GALLERY_WIDTH * c), (int) (GALLERY_HEIGHT * c));
		}
	}

	/*
	 * Template bundle should be shown depending also on the wizard execution context.
	 * For example JasperReports Book templates are not allowed to be used when the new report
	 * being created is supposed to be a sub-report.
	 */
	private boolean canBeShown(TemplateBundle bundle) {
		boolean canShow = true;
		if(getWizard() instanceof JSSWizard && ((JSSWizard)getWizard()).getParentWizard() instanceof SubreportWizard) {
			canShow = bundle.hasSupportForSubreport();
		}
		return canShow;
	}
	
	/**
	 * For every available template it build a list of all the categories and for every template the map of his categories
	 * is build
	 */
	private void findTemplates() {
		// Load all the available templates by invoking the template manager
		for (TemplateBundle b : bundles) {
			if(canBeShown(b)){
				Object templateCategory = b.getProperty(BuiltInCategories.CATEGORY_KEY);
				if (templateCategory != null) {
					String[] strCategoryList = templateCategory.toString().split(TEMPLATE_CATEGORY_SEPARATOR); 
					HashSet<String> categorySet = new HashSet<String>();
	
					for (String cat : strCategoryList) {
						if (!cat.trim().isEmpty()) {
							if (!cachedGalleries.containsKey(cat.toLowerCase())) {
								String categoryLocalizedName = cat;
								if (b instanceof WizardTemplateBundle){
									categoryLocalizedName = ((WizardTemplateBundle)b).getLocalizedString(cat);
								}
								categoryList.add(new TemplateCategory(cat, categoryLocalizedName));
								cachedGalleries.put(cat.toLowerCase(), null);
							}
							categorySet.add(cat);
						}
					}
					categoryCache.put(b, categorySet);
				} else {
					categoryCache.put(b, new HashSet<String>());
				}
			}
		}
	}

	public static String capitalizeFirstLetters(String s) {

		for (int i = 0; i < s.length(); i++) {

			if (i == 0) {
				// Capitalize the first letter of the string.
				s = String.format("%s%s", Character.toUpperCase(s.charAt(0)), s.substring(1)); //$NON-NLS-1$
			}

			// Is this character a non-letter or non-digit? If so
			// then this is probably a word boundary so let's capitalize
			// the next character in the sequence.
			if (!Character.isLetterOrDigit(s.charAt(i))) {
				if (i + 1 < s.length()) {
					s = String.format("%s%s%s", s.subSequence(0, i + 1), Character.toUpperCase(s.charAt(i + 1)), //$NON-NLS-1$
							s.substring(i + 2));
				}
			}

		}

		return s;

	}

	/**
	 * We don't want to proceed until a template has been selected. In this method we check if the user has made her
	 * selection. Also if the template support the validation then the method to validate the configuration is called
	 * and the user can proceed only if the validation doesn't return any error
	 */
	public boolean validatePage() {
		Gallery gal = (Gallery) layout.topControl;
		if (gal.getSelectionCount() == 0){
			return false;
		} else {
			GalleryItem selectedItem = gal.getSelection()[0];
			TemplateBundle selectedBundle = (TemplateBundle)selectedItem.getData(TEMPLATE_KEY);
			if (selectedBundle instanceof ValidatedTemplateBundle){
				ValidatedTemplateBundle validatedBundle = (ValidatedTemplateBundle)selectedBundle;
				List<String> errors = validatedBundle.validateConfiguration();
				if (errors != null && !errors.isEmpty()){
					String errorMessage = "";
					String last = errors.get(errors.size()-1);
					for(String error : errors){
						errorMessage+=error;
						if (error != last){
							errorMessage+="\n";
						}
					}
					setErrorMessage(errorMessage);
					return false;
				}
			}
		}
		setErrorMessage(null);
		return true;
	}

	/**
	 * Store inside the wizard settings the user selection.
	 */
	public void storeSettings() {
		Gallery gal = (Gallery) layout.topControl;
		if (getSettings() == null)
			return;
		if (gal == null)
			return;

		// Clean settings just to be sure no previous dirty 
		// data from next/back clicks are in.
		List<String> keyList = new ArrayList<String>(getSettings().keySet());
		for(String k : keyList) {
			// we need to keep the JasperReportsConfiguration instance
			if(!(JSSWizard.JASPERREPORTS_CONFIGURATION.equals(k))) {
				getSettings().remove(k);
			}
		}
		
		GalleryItem[] selection = gal.getSelection();
		if (selection != null && selection.length > 0) {

			selectedTemplate = (TemplateBundle) selection[0].getData(TEMPLATE_KEY); //$NON-NLS-1$
			createdBundles.add(selectedTemplate);
			getSettings().put(TEMPLATE_KEY, selectedTemplate);
		} else {
			getSettings().remove(TEMPLATE_KEY); 
			selectedTemplate = null;
		}
	}
	
	/**
	 * Notify to all created pages that the wizard ended
	 */
	public void doCancel(){
		for(TemplateBundle bundle : createdBundles){
			if (bundle instanceof WizardTemplateBundle){
				((WizardTemplateBundle)bundle).wizardClosed();
			}
		}
		createdBundles.clear();
	}

}
