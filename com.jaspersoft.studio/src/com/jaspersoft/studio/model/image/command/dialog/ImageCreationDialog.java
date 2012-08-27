package com.jaspersoft.studio.model.image.command.dialog;

import java.text.DecimalFormat;

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignImage;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog;
import org.eclipse.ui.progress.WorkbenchJob;

import com.jaspersoft.studio.plugin.IEditorContributor;
import com.jaspersoft.studio.swt.widgets.WTextExpression;
import com.jaspersoft.studio.utils.ImageUtils;
import com.jaspersoft.studio.utils.UIUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Dialog proposed when the image component is to be created.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class ImageCreationDialog extends Dialog {
	// Image preview job information
	private static final int IMAGE_PREVIEW_JOB_DELAY=500;
	private ImagePreviewJob imagePreviewJob;
	
	// All widgets stuff
	private Text txtResourcePath;
	private Text txtFilesystemPath;
	private Text txtURL;
	private Button btnWorkspaceResource;
	private Button btnAbsolutePath;
	private Button btnNoImage;
	private Button btnUrlRemote;
	private Button btnCustomExpression;
	private StackLayout grpOptionsLayout;
	private Composite cmpWorkspaceResourceSelection;
	private Composite cmpFilesystemResourceSelection;
	private Composite cmpNoImage;
	private Composite cmpCustomExpression;
	private Composite cmpURL;
	private Composite cmpImgPreview;
	private Composite cmpNoImgPreview;
	private Group grpOptions;
	private JasperReportsConfiguration jConfig;
	private JRDesignElement jrElement;
	private Label imagePreview;
	private Group grpImagePreview;
	private StackLayout grpImagePreviewLayout;
	private Label lblImageSize;
	private Label lblImageDimension;
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public ImageCreationDialog(Shell parentShell) {
		super(parentShell);
		imagePreviewJob=new ImagePreviewJob();
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Create new image element");
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(1, true));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Group grpImageCreationMode = new Group(container, SWT.NONE);
		grpImageCreationMode.setText("Image creation mode");
		grpImageCreationMode.setLayout(new GridLayout(1, false));
		grpImageCreationMode.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1));
		
		btnWorkspaceResource = new Button(grpImageCreationMode, SWT.RADIO);
		btnWorkspaceResource.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeImageCreationMode(cmpWorkspaceResourceSelection);
			}
		});
		btnWorkspaceResource.setText("Workspace resource (an element inside the workspace)");
		
		btnAbsolutePath = new Button(grpImageCreationMode, SWT.RADIO);
		btnAbsolutePath.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeImageCreationMode(cmpFilesystemResourceSelection);
			}
		});
		btnAbsolutePath.setText("Absolute Path in the filesystem (use only for quick testing, never use in real reports)");

		btnUrlRemote = new Button(grpImageCreationMode, SWT.RADIO);
		btnUrlRemote.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeImageCreationMode(cmpURL);
			}
		});
		btnUrlRemote.setText("URL (a remote URL referring to an image, will be the expression value)");
		
		btnNoImage = new Button(grpImageCreationMode, SWT.RADIO);
		btnNoImage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeImageCreationMode(cmpNoImage);
			}
		});
		btnNoImage.setText("No image (just create an image element, expression will be modified later)");
		
		btnCustomExpression = new Button(grpImageCreationMode, SWT.RADIO);
		btnCustomExpression.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeImageCreationMode(cmpCustomExpression);
			}
		});
		btnCustomExpression.setText("Custom expression (enter an expression for the image using the expression editor)");
		
		createOptionsPanel(container);
		
		createImagePreviewPanel(container);

		// As default no image radio button selected
		btnNoImage.setSelection(true);
		changeImageCreationMode(this.cmpNoImage);
		
		return area;
	}

	/*
	 * Creates the panel with the different options container.
	 * A stack layout will be used.
	 */
	private void createOptionsPanel(Composite container) {
		grpOptions = new Group(container, SWT.NONE);
		grpOptions.setText("Options");
		grpOptionsLayout = new StackLayout();
		grpOptions.setLayout(grpOptionsLayout);
		grpOptions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
		createWSSelectionContainer();
		createFSSelectionContainer();
		createNoImageContainer();
		createCustomExprContainer();
		createURLOptionsContainer();
	}

	/*
	 * Creates the composite container for the workspace image selection. 
	 */
	private void createWSSelectionContainer() {
		cmpWorkspaceResourceSelection = new Composite(grpOptions, SWT.NONE);
		cmpWorkspaceResourceSelection.setLayout(new GridLayout(2, false));
		
		Label lblSelectImageFromWS = new Label(cmpWorkspaceResourceSelection, SWT.NONE);
		lblSelectImageFromWS.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblSelectImageFromWS.setText("Select an image resource from the workspace");
		
		txtResourcePath = new Text(cmpWorkspaceResourceSelection, SWT.BORDER);
		txtResourcePath.setEnabled(false);
		txtResourcePath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelectWsRes = new Button(cmpWorkspaceResourceSelection, SWT.NONE);
		btnSelectWsRes.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectImageFromWorkspace();
			}
		});
		btnSelectWsRes.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnSelectWsRes.setText("Browse ...");
	}

	/*
	 * Creates the composite container for the filesystem image selection.
	 */
	private void createFSSelectionContainer() {
		cmpFilesystemResourceSelection = new Composite(grpOptions, SWT.NONE);
		cmpFilesystemResourceSelection.setLayout(new GridLayout(2, false));
		
		Label lblSelectImageFromFilesystem = new Label(cmpFilesystemResourceSelection, SWT.NONE);
		lblSelectImageFromFilesystem.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		lblSelectImageFromFilesystem.setText("Select an image resource from the filesystem");
		
		txtFilesystemPath = new Text(cmpFilesystemResourceSelection, SWT.BORDER);
		txtFilesystemPath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtFilesystemPath.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				loadImagePreview();
			}
		});
		
		Button btnSelectFilesystemRes = new Button(cmpFilesystemResourceSelection, SWT.NONE);
		btnSelectFilesystemRes.setText("Browse ...");
		btnSelectFilesystemRes.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectImageFromFilesystem();
			}
		});
	}

	/*
	 * Creates the empty composite for no image selection.
	 */
	private void createNoImageContainer() {
		cmpNoImage = new Composite(grpOptions, SWT.NONE);
	}

	/*
	 * Creates the composite container for the custom expression editing.
	 */
	private void createCustomExprContainer() {
		cmpCustomExpression = new Composite(grpOptions, SWT.NONE);
		GridLayout cmpCustomExpressionlayout = new GridLayout();
		cmpCustomExpression.setLayout(cmpCustomExpressionlayout);
		
		WTextExpression customExpression=new WTextExpression(cmpCustomExpression, SWT.NONE, "Enter a custom expression for the image:", WTextExpression.LABEL_ON_TOP);
		customExpression.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}

	/*
	 * Create the composite container for the URL image selection.
	 */
	private void createURLOptionsContainer() {
		cmpURL = new Composite(grpOptions, SWT.NONE);
		cmpURL.setLayout(new GridLayout(1, false));
		
		Label lblNewLabel = new Label(cmpURL, SWT.NONE);
		lblNewLabel.setText("Enter a URL referring to an external image:");
		
		txtURL = new Text(cmpURL, SWT.BORDER);
		txtURL.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtURL.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				loadImagePreview();
			}
		});
	}

	/*
	 * Create the image preview panel.
	 */
	private void createImagePreviewPanel(Composite container) {
		grpImagePreview = new Group(container, SWT.NONE);
		grpImagePreviewLayout = new StackLayout();
		grpImagePreview.setLayout(grpImagePreviewLayout);
		grpImagePreview.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 2));
		grpImagePreview.setText("Image preview");
		
		cmpImgPreview = new Composite(grpImagePreview, SWT.NONE);
		cmpImgPreview.setLayout(new GridLayout(1, false));
		
		lblImageDimension = new Label(cmpImgPreview, SWT.NONE);
		lblImageDimension.setText("Dimension: ");
		lblImageDimension.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblImageSize = new Label(cmpImgPreview, SWT.NONE);
		lblImageSize.setText("Size: ");
		lblImageSize.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		imagePreview = new Label(cmpImgPreview, SWT.NONE);
		imagePreview.setText("IMAGE HERE");
		imagePreview.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		cmpNoImgPreview = new Composite(grpImagePreview, SWT.NONE);
		cmpNoImgPreview.setLayout(new GridLayout(1, false));
		
		Label lblNoPreviewAvailable = new Label(cmpNoImgPreview, SWT.NONE);
		lblNoPreviewAvailable.setText("<No preview image available>");
		lblNoPreviewAvailable.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		lblNoPreviewAvailable.setAlignment(SWT.CENTER);
	}

	/*
	 * When a new image creation mode is selected, shows the 
	 * dedicated options panel and hide the image preview one.
	 */
	private void changeImageCreationMode(Control newTopControl){
		// Shows no preview panel and hide the image preview one
		Image currImgPreview = imagePreview.getImage();
		if(currImgPreview!=null) currImgPreview.dispose();
		grpImagePreviewLayout.topControl=cmpNoImgPreview;
		grpImagePreview.layout();
		// Change the top control for the options panel
		grpOptionsLayout.topControl=newTopControl;
		grpOptions.layout();
	}
	
	/*
	 * Popup the dialog to select the image from workspace.
	 */
	private void selectImageFromWorkspace() {
		FilteredResourcesSelectionDialog fd = new FilteredResourcesSelectionDialog(Display.getCurrent().getActiveShell(),
				false, ResourcesPlugin.getWorkspace().getRoot(), IResource.FILE);
		fd.setInitialPattern("*.png");//$NON-NLS-1$
		if (fd.open() == Dialog.OK) {
			IFile file = (IFile) fd.getFirstResult();
			IFile contextfile = (IFile) jConfig.get(IEditorContributor.KEY_FILE);
			String filepath = null;
			if (contextfile != null && file.getProject().equals(contextfile.getProject()))
				filepath = file.getProjectRelativePath().toPortableString().replaceAll(file.getProject().getName() + "/", "");
			else
				filepath = file.getRawLocationURI().toASCIIString();

			JRDesignExpression jre = new JRDesignExpression();
			if (file.getFileExtension().equals("svg"))
				jre.setText("net.sf.jasperreports.renderers.BatikRenderer.getInstanceFromLocation(\"" + filepath + "\")");//$NON-NLS-1$ //$NON-NLS-2$
			else
				jre.setText("\"" + filepath + "\"");//$NON-NLS-1$ //$NON-NLS-2$
			((JRDesignImage) jrElement).setExpression(jre);
			
			txtResourcePath.setText(filepath);
			try {
				IFileStore imgFileStore = EFS.getStore(file.getLocationURI());
				loadImagePreview(file.getLocation().toOSString(), imgFileStore);
			} catch (CoreException e) {
				UIUtils.showError(e);
			}
		}
		else{
			// no image selected
			txtResourcePath.setText("");
			grpImagePreviewLayout.topControl=cmpNoImgPreview;
			grpImagePreview.layout();
		}
	}
	
	/*
	 * Popup the dialog to select the image from the filesystem.
	 */
	private void selectImageFromFilesystem() {
		FileDialog fd = new FileDialog(Display.getDefault()
				.getActiveShell());
		fd.setFilterExtensions(new String[]{"*.png","*.jpeg; *.jpg","*.gif","*.*"});
		String selection = fd.open();
		if(selection!=null){
			JRDesignExpression jre = new JRDesignExpression();
			if (selection.endsWith(".svg"))
				jre.setText("net.sf.jasperreports.renderers.BatikRenderer.getInstanceFromLocation(\"" + selection + "\")");//$NON-NLS-1$ //$NON-NLS-2$
			else
				jre.setText("\"" + selection + "\"");//$NON-NLS-1$ //$NON-NLS-2$
			((JRDesignImage) jrElement).setExpression(jre);
			
			// After the text modification the image preview job will be invoked...
			txtFilesystemPath.setText(selection);
		}
	}
	
	/*
	 * Loads the preview image panel with the specified image file information.
	 */
	private void loadImagePreview(String imgLocation, IFileStore imgFileStore) {
		Image oldPreviewImg = imagePreview.getImage();
		Image originalImg=null;
		try{
			originalImg = new Image(getShell().getDisplay(),imgLocation);
		}
		catch (SWTException ex){
			// Unable to load the image (most cases not valid location)
			// Only catch exception...and show no image preview panel
			grpImagePreviewLayout.topControl=cmpNoImgPreview;
			grpImagePreview.layout();
		}
		if(originalImg!=null){
			int imgHeight = originalImg.getImageData().height;
			int imgWidth = originalImg.getImageData().width;
			String sizeInfo="<No size info available>";
			sizeInfo=(DecimalFormat.getNumberInstance().format(
					imgFileStore.fetchInfo().getLength()))+" bytes";
		
			// Gets a resized image for the preview area
			Image resizedImg = ImageUtils.resize(originalImg,Math.min(imgWidth, 200), Math.min(imgHeight, 200));
			imagePreview.setImage(resizedImg);
			lblImageDimension.setText("Dimension: "+imgWidth+"x"+imgHeight+"px");
			lblImageSize.setText("Size: "+sizeInfo);
			grpImagePreviewLayout.topControl=cmpImgPreview;
			grpImagePreview.layout(true);
	
			// Dispose unused images
			if(originalImg!=null){
				originalImg.dispose();			
			}
			if(oldPreviewImg!=null){
				oldPreviewImg.dispose();
			}
		}
	}
	
	/*
	 * Cancels a possible existing image preview job
	 * and schedules a new one.
	 */
	private void loadImagePreview(){
		// TODO: we should add a check for allowed image extensions.
		imagePreviewJob.cancel();
		imagePreviewJob.schedule(IMAGE_PREVIEW_JOB_DELAY);
	}
	
	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(640, 640);
	}

	/**
	 * Configure required information for the correct dialog functioning.
	 * 
	 * @param jrElement
	 * @param jConfig
	 */
	public void configureDialog(JRDesignElement jrElement, JasperReportsConfiguration jConfig) {
		this.jrElement=jrElement;
		this.jConfig=jConfig;
	}

	@Override
	public int open() {
		if(jConfig==null || jrElement==null){
			throw new RuntimeException("Cannot open the dialog before the configureDialog method has been invoked.");
		}
		return super.open();
	}
	
	/*
	 * Job that is responsible to load an image from an URL or 
	 * a local filesystem path.
	 */
	private final class ImagePreviewJob extends WorkbenchJob{

		public ImagePreviewJob(){
			super("Image preview job");
			setSystem(true);
		}
		
		@Override
		public IStatus runInUIThread(IProgressMonitor monitor) {
			monitor.beginTask("Retrieving image...", IProgressMonitor.UNKNOWN);
			if(btnAbsolutePath.getSelection()){
				// filesystem path...
				String imagePath = txtFilesystemPath.getText();
				IFileStore imgFileStore = EFS.getLocalFileSystem().getStore(new Path(imagePath));
				loadImagePreview(imagePath, imgFileStore);
			}
			else if(btnUrlRemote.getSelection()) {
				// URL
				
			}
			monitor.done();
			return Status.OK_STATUS;
		}
	}

	@Override
	public boolean close() {
		if(imagePreviewJob!=null){
			imagePreviewJob.cancel();
			imagePreviewJob=null;
		}
		return super.close();
	}
	
}
