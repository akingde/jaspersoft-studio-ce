/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.jface.dialogs;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.progress.WorkbenchJob;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.ImageUtils;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.repo.RepositoryUtil;

/**
 * Dialog proposed when an image needs to be selected.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class FilePreviewSelectionDialog extends FileSelectionDialog {
	// Image preview job information
	private static final int IMAGE_PREVIEW_JOB_DELAY = 500;
	private ImagePreviewJob filePreviewJob;

	private Composite cmpFilePreview;
	private Composite cmpNoFilePreview;
	private Label filePreview;
	private Group grpFilePreview;
	private StackLayout grpFilePreviewLayout;
	private Label lblFileSize;
	private Label lblFileDimension;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public FilePreviewSelectionDialog(Shell parentShell) {
		super(parentShell);
		filePreviewJob = new ImagePreviewJob();
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Control area = super.createDialogArea(parent);

		createFilePreviewPanel(container);

		// This will allow to show the preview now that the preview area is created
		changeFileSelectionMode(cmpNoFile);
		return area;
	}

	/*
	 * Create the image preview panel.
	 */
	private void createFilePreviewPanel(Composite container) {
		grpFilePreview = new Group(container, SWT.NONE);
		grpFilePreviewLayout = new StackLayout();
		grpFilePreview.setLayout(grpFilePreviewLayout);
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 2);
		gd.heightHint = 200;
		grpFilePreview.setLayoutData(gd);
		grpFilePreview.setText(Messages.ImageSelectionDialog_ImagePreviewGroupTitle);

		cmpFilePreview = new Composite(grpFilePreview, SWT.NONE);
		cmpFilePreview.setLayout(new GridLayout(1, false));

		lblFileDimension = new Label(cmpFilePreview, SWT.NONE);
		lblFileDimension.setText(Messages.ImageSelectionDialog_Dimension);
		lblFileDimension.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		lblFileSize = new Label(cmpFilePreview, SWT.NONE);
		lblFileSize.setText(Messages.ImageSelectionDialog_Size);
		lblFileSize.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		filePreview = new Label(cmpFilePreview, SWT.NONE);
		filePreview.setText("IMAGE HERE"); //$NON-NLS-1$
		filePreview.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		cmpNoFilePreview = new Composite(grpFilePreview, SWT.NONE);
		cmpNoFilePreview.setLayout(new GridLayout(1, false));

		Label lblNoPreviewAvailable = new Label(cmpNoFilePreview, SWT.NONE);
		lblNoPreviewAvailable.setText(Messages.ImageSelectionDialog_NoPreviewAvailable);
		lblNoPreviewAvailable.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		lblNoPreviewAvailable.setAlignment(SWT.CENTER);
	}

	@Override
	protected IFile selectFileFromWorkspace() {
		IFile file = super.selectFileFromWorkspace();
		if (file != null) {
			try {
				IFileStore imgFileStore = EFS.getStore(file.getLocationURI());
				loadFilePreview(file.getLocation().toOSString(), imgFileStore);
			} catch (CoreException e) {
				UIUtils.showError(e);
			}
		} else {
			grpFilePreviewLayout.topControl = cmpNoFilePreview;
			grpFilePreview.layout();
		}
		return file;
	}

	@Override
	public void handleTxtFilesystemPathChange() {
		super.handleTxtFilesystemPathChange();
		loadImagePreview();
	}

	@Override
	public void handleTxtUrlChange() {
		loadImagePreview();
	}

	/*
	 * When a new image selection mode is selected, shows the dedicated options
	 * panel and hide the image preview one.
	 */
	public void changeFileSelectionMode(Control newTopControl) {
		super.changeFileSelectionMode(newTopControl);
		// Check if the preview area was already created, since this method
		// can be called by the superclass during the controls creation
		if (filePreview != null) {
			// Shows no preview panel and hide the image preview one
			Image currImgPreview = filePreview.getImage();
			if (currImgPreview != null)
				currImgPreview.dispose();
			grpFilePreviewLayout.topControl = cmpNoFilePreview;
			grpFilePreview.layout();
		}
	}

	/*
	 * Loads the preview image panel with the specified image file information.
	 */
	private void loadFilePreview(String imgLocation, IFileStore imgFileStore) {
		Image oldPreviewImg = filePreview.getImage();
		Image originalImg = null;
		try {
			originalImg = new Image(getShell().getDisplay(), imgLocation);
		} catch (SWTException ex) {
			// Unable to load the image (most cases not valid location)
			// Only catch exception...and show no image preview panel
			grpFilePreviewLayout.topControl = cmpNoFilePreview;
			grpFilePreview.layout();
		}
		if (originalImg != null) {
			int imgHeight = originalImg.getImageData().height;
			int imgWidth = originalImg.getImageData().width;
			String sizeInfo = Messages.ImageSelectionDialog_NoSizeInfoAvailable;
			sizeInfo = (DecimalFormat.getNumberInstance().format(imgFileStore.fetchInfo().getLength()))
					+ Messages.ImageSelectionDialog_bytes;

			// Gets a resized image for the preview area
			Image resizedImg = ImageUtils.resize(originalImg, Math.min(imgWidth, 200), Math.min(imgHeight, 200));
			filePreview.setImage(resizedImg);
			lblFileDimension.setText(Messages.ImageSelectionDialog_Dimension + imgWidth + "x" + imgHeight + "px"); //$NON-NLS-2$ //$NON-NLS-3$
			lblFileSize.setText(Messages.ImageSelectionDialog_Size + sizeInfo);
			grpFilePreviewLayout.topControl = cmpFilePreview;
			grpFilePreview.layout(true);

			// Dispose unused images
			originalImg.dispose();
			if (oldPreviewImg != null) {
				oldPreviewImg.dispose();
			}
		}
	}

	/*
	 * Loads the preview image panel with the specified remote URL information.
	 */
	private void loadPreviewRemoteImage(String imageURLText) {
		Image oldPreviewImg = filePreview.getImage();
		HttpURLConnection con = null;
		InputStream imageIS = null;
		try {
			URL imageURL = new URL(imageURLText);
			con = (HttpURLConnection) imageURL.openConnection();
			imageIS = con.getInputStream();
			int imageLength = con.getContentLength();
			Image remoteImg = new Image(getShell().getDisplay(), imageIS);

			String sizeInfo = Messages.ImageSelectionDialog_NoSizeInfoAvailable;
			sizeInfo = (DecimalFormat.getNumberInstance().format(imageLength)) + Messages.ImageSelectionDialog_bytes;
			// Gets a resized image for the preview area
			int imgWidth = remoteImg.getImageData().width;
			int imgHeight = remoteImg.getImageData().height;
			Image resizedImg = ImageUtils.resize(remoteImg, Math.min(imgWidth, 200), Math.min(imgHeight, 200));
			filePreview.setImage(resizedImg);
			lblFileDimension.setText(Messages.ImageSelectionDialog_Dimension + imgWidth + "x" + imgHeight + "px"); //$NON-NLS-2$ //$NON-NLS-3$
			lblFileSize.setText(Messages.ImageSelectionDialog_Size + sizeInfo);
			grpFilePreviewLayout.topControl = cmpFilePreview;
			grpFilePreview.layout(true);

			// Dispose unused images
			remoteImg.dispose();
			if (oldPreviewImg != null) {
				oldPreviewImg.dispose();
			}
		} catch (Exception e) {
			grpFilePreviewLayout.topControl = cmpNoFilePreview;
			grpFilePreview.layout();
		} finally {
			FileUtils.closeStream(imageIS);
		}
	}

	/*
	 * Cancels a possible existing image preview job and schedules a new one.
	 */
	public void loadImagePreview() {
		// TODO: we should add a check for allowed image extensions.
		filePreviewJob.cancel();
		filePreviewJob.schedule(IMAGE_PREVIEW_JOB_DELAY);
	}

	/*
	 * Job that is responsible to load an image from an URL or a local filesystem
	 * path.
	 */
	private final class ImagePreviewJob extends WorkbenchJob {

		public ImagePreviewJob() {
			super(Messages.ImageSelectionDialog_JobImgPreview);
			setSystem(true);
		}

		@Override
		public IStatus runInUIThread(IProgressMonitor monitor) {
			if (FilePreviewSelectionDialog.this.getDialogArea() != null
					&& !FilePreviewSelectionDialog.this.getDialogArea().isDisposed()) {
				monitor.beginTask(Messages.ImageSelectionDialog_JobImgPreviewRetrieving, IProgressMonitor.UNKNOWN);
				if (btnAbsolutePath.getSelection()) {
					// filesystem path...
					String imagePath = txtFilesystemPath.getText();
					IFileStore imgFileStore = EFS.getLocalFileSystem().getStore(new Path(imagePath));
					loadFilePreview(imagePath, imgFileStore);
					// Change the standard separator with an universal one
					FilePreviewSelectionDialog.super.handleTxtFilesystemPathChange();
				} else if (btnUrlRemote.getSelection()) {
					// URL
					String imageURLText = txtURL.getText();
					fileExpressionText = imageURLText;
					loadPreviewRemoteImage(imageURLText);
				} else if (!Misc.isNullOrEmpty(fileExpressionText)) {
					try {
						String t = fileExpressionText;
						if (t.startsWith("\""))
							t = t.substring(1);
						if (t.endsWith("\""))
							t = t.substring(0, t.length() - 1);
						byte[] imgByte = RepositoryUtil.getInstance(jConfig).getBytesFromLocation(t);
						if (imgByte != null) {
							Image oldPreviewImg = filePreview.getImage();

							BufferedInputStream inputStreamReader = new BufferedInputStream(
									new ByteArrayInputStream(imgByte));
							ImageData imageData = new ImageData(inputStreamReader);
							Image img = new Image(getDisplay(), imageData);

							String sizeInfo = (DecimalFormat.getNumberInstance().format(imgByte.length))
									+ Messages.ImageSelectionDialog_bytes;
							// Gets a resized image for the preview area
							int imgWidth = img.getImageData().width;
							int imgHeight = img.getImageData().height;
							Image resizedImg = ImageUtils.resize(img, Math.min(imgWidth, 200),
									Math.min(imgHeight, 200));
							filePreview.setImage(resizedImg);
							lblFileDimension.setText(
									Messages.ImageSelectionDialog_Dimension + imgWidth + "x" + imgHeight + "px"); //$NON-NLS-2$ //$NON-NLS-3$
							lblFileSize.setText(Messages.ImageSelectionDialog_Size + sizeInfo);
							grpFilePreviewLayout.topControl = cmpFilePreview;
							grpFilePreview.layout(true);

							// Dispose unused images
							img.dispose();
							if (oldPreviewImg != null)
								oldPreviewImg.dispose();
						}
					} catch (JRException e) {
						e.printStackTrace();
					}
				}
				monitor.done();
				return Status.OK_STATUS;
			} else {
				return Status.CANCEL_STATUS;
			}
		}

	}

	@Override
	public boolean close() {
		if (filePreviewJob != null) {
			filePreviewJob.cancel();
			filePreviewJob = null;
		}
		return super.close();
	}

}
