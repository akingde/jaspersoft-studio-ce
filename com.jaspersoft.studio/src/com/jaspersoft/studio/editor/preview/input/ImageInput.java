/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.util.Map;

import javax.imageio.ImageIO;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog;

import com.jaspersoft.studio.editor.preview.view.control.VParameters;
import com.jaspersoft.studio.messages.Messages;

public class ImageInput extends ADataInput {
	private Text txt;
	private Button btn;

	public boolean isForType(Class<?> valueClass) {
		return Image.class.isAssignableFrom(valueClass);
	}

	@Override
	public void createInput(Composite parent, final IParameter param, final Map<String, Object> params) {
		super.createInput(parent, param, params);
		if (isForType(param.getValueClass())) {
			final Composite cmp = new Composite(parent, SWT.NONE);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalIndent = 8;
			cmp.setLayoutData(gd);
			GridLayout layout = new GridLayout(2, false);
			layout.marginHeight = 0;
			layout.marginWidth = 0;
			cmp.setLayout(layout);

			txt = new Text(cmp, SWT.BORDER);
			txt.setToolTipText(VParameters.createToolTip(param));
			txt.addFocusListener(focusListener);
			txt.addTraverseListener(keyListener);
			txt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			setMandatory(param, txt);

			btn = new Button(cmp, SWT.NONE);
			btn.setText(Messages.ImageInput_selectimage);
			btn.setToolTipText(param.getDescription());
			btn.addFocusListener(focusListener);
			btn.addTraverseListener(keyListener);
			btn.setAlignment(SWT.LEFT);
			btn.addSelectionListener(new SelectionListener() {

				public void widgetSelected(SelectionEvent e) {
					FilteredResourcesSelectionDialog fd = new FilteredResourcesSelectionDialog(
							Display.getCurrent().getActiveShell(), false, ResourcesPlugin.getWorkspace().getRoot(),
							IResource.FILE);
					fd.setInitialPattern("*.png");//$NON-NLS-1$
					if (fd.open() == Dialog.OK) {
						IFile file = (IFile) fd.getFirstResult();
						Image image;
						try {
							image = ImageIO.read(file.getContents());
							txt.setText(Misc.nvl(file.getProjectRelativePath().toOSString()));
							updateModel(image);
							setButtonImage(btn, image);
						} catch (Exception e1) {
							UIUtils.showError(e1);
						}
					}
				}

				public void widgetDefaultSelected(SelectionEvent e) {

				}
			});
			updateInput();
			setNullable(param, btn);
		}
	}

	public void updateInput() {
		Object value = params.get(param.getName());
		if (value != null && value instanceof String)
			txt.setText((String) value);
		else if (value != null && value instanceof Image)
			setButtonImage(btn, (Image) value);
		else
			txt.setText(value == null ? "" : value.toString());

		setDecoratorNullable(param);
	}

	public static void setButtonImage(final Button txt, Image image) {
		org.eclipse.swt.graphics.Image img = new org.eclipse.swt.graphics.Image(txt.getDisplay(),
				convertAWTImageToSWT(image).scaledTo(50, 50));

		txt.setImage(img);
	}

	public static ImageData convertAWTImageToSWT(Image image) {
		if (image == null) {
			throw new IllegalArgumentException(Messages.ImageInput_nullimage);
		}
		int w = image.getWidth(null);
		int h = image.getHeight(null);
		if (w == -1 || h == -1) {
			return null;
		}
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return convertToSWT(bi);
	}

	/**
	 * Converts a buffered image to SWT <code>ImageData</code>.
	 * 
	 * @param bufferedImage
	 *            the buffered image (<code>null</code> not permitted).
	 * 
	 * @return The image data.
	 */
	public static ImageData convertToSWT(BufferedImage bufferedImage) {
		if (bufferedImage.getColorModel() instanceof DirectColorModel) {
			DirectColorModel colorModel = (DirectColorModel) bufferedImage.getColorModel();
			PaletteData palette = new PaletteData(colorModel.getRedMask(), colorModel.getGreenMask(),
					colorModel.getBlueMask());
			ImageData data = new ImageData(bufferedImage.getWidth(), bufferedImage.getHeight(),
					colorModel.getPixelSize(), palette);
			WritableRaster raster = bufferedImage.getRaster();
			int[] pixelArray = new int[3];
			for (int y = 0; y < data.height; y++) {
				for (int x = 0; x < data.width; x++) {
					raster.getPixel(x, y, pixelArray);
					int pixel = palette.getPixel(new RGB(pixelArray[0], pixelArray[1], pixelArray[2]));
					data.setPixel(x, y, pixel);
				}
			}
			return data;
		} else if (bufferedImage.getColorModel() instanceof IndexColorModel) {
			IndexColorModel colorModel = (IndexColorModel) bufferedImage.getColorModel();
			int size = colorModel.getMapSize();
			byte[] reds = new byte[size];
			byte[] greens = new byte[size];
			byte[] blues = new byte[size];
			colorModel.getReds(reds);
			colorModel.getGreens(greens);
			colorModel.getBlues(blues);
			RGB[] rgbs = new RGB[size];
			for (int i = 0; i < rgbs.length; i++) {
				rgbs[i] = new RGB(reds[i] & 0xFF, greens[i] & 0xFF, blues[i] & 0xFF);
			}
			PaletteData palette = new PaletteData(rgbs);
			ImageData data = new ImageData(bufferedImage.getWidth(), bufferedImage.getHeight(),
					colorModel.getPixelSize(), palette);
			data.transparentPixel = colorModel.getTransparentPixel();
			WritableRaster raster = bufferedImage.getRaster();
			int[] pixelArray = new int[1];
			for (int y = 0; y < data.height; y++) {
				for (int x = 0; x < data.width; x++) {
					raster.getPixel(x, y, pixelArray);
					data.setPixel(x, y, pixelArray[0]);
				}
			}
			return data;
		}
		return null;
	}

}
