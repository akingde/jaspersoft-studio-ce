/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.utils;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.wb.swt.ResourceManager;

/**
 * Utility class for managing image related stuff.
 * <p>
 * Methods in this class could manage OS resources like fonts, colors besides images. When adding new methods, please
 * add any useful notes regarding the disposal of these resources.
 * 
 * @author mrabbi
 * 
 */
public class ImageUtils {

	private static final List<String> IMG_FILE_EXTENSIONS;

	static {
		// A list of the most common image file extension
		IMG_FILE_EXTENSIONS = new ArrayList<String>();
		IMG_FILE_EXTENSIONS.add("png");
		IMG_FILE_EXTENSIONS.add("gif");
		IMG_FILE_EXTENSIONS.add("jpg");
		IMG_FILE_EXTENSIONS.add("jpeg");
		IMG_FILE_EXTENSIONS.add("bmp");
		IMG_FILE_EXTENSIONS.add("tiff");
	}

	private ImageUtils() {
	}

	/**
	 * Gets a new resized image from the specified original one.
	 * <p>
	 * Please note that the source image is not disposed or directly modified. The rescaled image returned is not cached
	 * and it is up to the caller to dispose it when no longer needed in its code.
	 * 
	 * @param originalImage
	 *          the original image
	 * @param width
	 *          the new width of the image
	 * @param height
	 *          the new height of the image
	 * @return the resized image
	 */
	public static Image resize(Image originalImage, int width, int height) {
		// Sanity checks
		Assert.isNotNull(originalImage, "The image to resize can not be null.");
		Assert.isTrue(!originalImage.isDisposed(), "The image to resize is disposed.");
		Assert.isTrue(width > 1, "Please specify a valid width value for the new image.");
		Assert.isTrue(height > 1, "Please specify a valid height value for the new image.");
		// Perform resize operation using anti-alias and interpolation settings
		Image scaled = new Image(Display.getDefault(), width, height);
		GC gc = new GC(scaled);
		try {
			gc.setAntialias(SWT.ON);
			gc.setInterpolation(SWT.HIGH);
			gc.drawImage(originalImage, 0, 0, originalImage.getBounds().width, originalImage.getBounds().height, 0, 0, width,
					height);
		} finally {
			gc.dispose();
		}
		return scaled;
	}

	/**
	 * Resize an image keeping its ratio. The resized edge is the bigger one, and the other is resized as well to keep the
	 * proportion
	 * 
	 * @param size
	 *          the size of the bigger edge of the image, must be a positive value
	 * @param source
	 *          the source image, must be not null and not disposed. The image is not disposed by this method
	 * @return the result image data where the width or height (depends which was the bigger one) is of the specified size
	 */
	public static ImageData resizeKeepingRatio(int size, Image source) {
		// Sanity checks
		Assert.isNotNull(source, "The image to resize can not be null.");
		Assert.isTrue(!source.isDisposed(), "The image to resize is disposed.");
		Assert.isTrue(size > 0, "Please specify a valid size value for the new image.");
		int width = source.getImageData().width;
		int height = source.getImageData().height;
		boolean needResize = false;
		if (width > height) {
			if (width != size) {
				height = (int) Math.round((double) (size * height) / (double) width);
				width = size;
				needResize = true;
			}
		} else {
			if (height != size) {
				width = (int) Math.round((double) (size * width) / (double) height);
				height = size;
				needResize = true;
			}
		}
		if (needResize) {
			// Perform resize operation using anti-alias and interpolation settings
			Image scaled = new Image(Display.getDefault(), width, height);
			GC gc = new GC(scaled);
			try {
				gc.setAntialias(SWT.ON);
				gc.setInterpolation(SWT.HIGH);
				gc.drawImage(source, 0, 0, source.getBounds().width, source.getBounds().height, 0, 0, width, height);
			} finally {
				gc.dispose();
			}
			ImageData result = scaled.getImageData();
			scaled.dispose();
			return result;
		} else {
			return source.getImageData();
		}
	}

	/**
	 * Gets a new resized image from the specified original one. The new image must be bigger of the original one and the
	 * original will not resized but padded with a specified color. The original image will be centered on the new one. If
	 * the new image has the same size of the original for both width and height then the original image is returned
	 * <p>
	 * Please note that the source image is not disposed or directly modified. The rescaled image returned is not cached
	 * and it is up to the caller to dispose it when no longer needed in its code.
	 * 
	 * @param originalImage
	 *          the original image, not null and not disposed
	 * @param width
	 *          the new width of the image, must be greater or equals of the original
	 * @param height
	 *          the new height of the image, must be greater or equal of the original
	 * @param backGround
	 *          the background color used to pad the image
	 * @return the resized image
	 */
	public static Image padImage(Image originalImage, int width, int height, RGB backGround) {
		// Sanity checks
		Assert.isNotNull(originalImage, "The image to resize can not be null.");
		Assert.isTrue(!originalImage.isDisposed(), "The image to resize is disposed.");
		Assert.isTrue(width >= originalImage.getImageData().width, "Please specify a valid width value for the new image.");
		Assert.isTrue(height >= originalImage.getImageData().height,
				"Please specify a valid height value for the new image.");
		int originalHeight = originalImage.getImageData().height;
		int originalWidth = originalImage.getImageData().width;
		if (width == originalWidth && height == originalHeight)
			return originalImage;
		// Perform resize operation using anti-alias and interpolation settings
		Image scaled = new Image(Display.getDefault(), width, height);
		GC gc = new GC(scaled);
		try {
			gc.setAntialias(SWT.ON);
			gc.setInterpolation(SWT.HIGH);
			gc.setBackground(ResourceManager.getColor(backGround));
			gc.fillRectangle(0, 0, width, height);
			int destX = (width - originalImage.getImageData().width) / 2;
			int destY = (height - originalImage.getImageData().height) / 2;
			gc.drawImage(originalImage, 0, 0, originalWidth, originalHeight, destX, destY, originalHeight, originalWidth);
		} finally {
			gc.dispose();
		}
		return scaled;
	}

	/**
	 * Add text to an image padding the image to the right
	 */
	public static Image addTextToImage(Image originalImage, String text) {
		// Sanity checks
		Assert.isNotNull(originalImage, "The image to resize can not be null.");
		Assert.isTrue(!originalImage.isDisposed(), "The image to resize is disposed.");
		int originalHeight = originalImage.getImageData().height;
		int originalWidth = originalImage.getImageData().width;
		// Perform resize operation using anti-alias and interpolation settings
		Image scaled = new Image(Display.getDefault(), 1, 1);
		GC gc = new GC(scaled);
		Point size = gc.textExtent(text);
		scaled.dispose();
		gc.dispose();
		scaled = new Image(Display.getDefault(), originalWidth + size.x + 5, originalHeight);
		gc = new GC(scaled);
		try {
			gc.setAntialias(SWT.ON);
			gc.setInterpolation(SWT.HIGH);
			int destY = (originalImage.getImageData().height - size.y) / 2;
			gc.drawImage(originalImage, 0, 0, originalWidth, originalHeight, size.x + 3, 0, originalHeight, originalWidth);
			gc.drawText(text, 2, destY, true);
			//int whitePixel = scaled.getImageData().palette.getPixel(new RGB(255,255,255));
			//scaled.getImageData().transparentPixel = whitePixel;
		} finally {
			gc.dispose();
		}
		return scaled;
	}

	
	
	/**
	 * Checks if the specified extension is a valid one for a potential image file.
	 * 
	 * @param extension
	 * @return <code>true</code> if it is a valid extension, <code>false</code> otherwise
	 */
	public static boolean hasValidFileImageExtension(String extension) {
		for (String ext : IMG_FILE_EXTENSIONS) {
			if (ext.equalsIgnoreCase(extension))
				return true;
		}
		return false;
	}

	/**
	 * @return the list of allowed file extensions for images
	 */
	public static List<String> getAllowedImageFileExtensions() {
		return IMG_FILE_EXTENSIONS;
	}

	public static BufferedImage convertToAWT(ImageData data) {
		ColorModel colorModel = null;
		PaletteData palette = data.palette;
		if (palette.isDirect) {
			colorModel = new DirectColorModel(data.depth, palette.redMask, palette.greenMask, palette.blueMask);
			BufferedImage bufferedImage = new BufferedImage(colorModel, colorModel.createCompatibleWritableRaster(data.width,
					data.height), false, null);
			for (int y = 0; y < data.height; y++) {
				for (int x = 0; x < data.width; x++) {
					int pixel = data.getPixel(x, y);
					RGB rgb = palette.getRGB(pixel);
					bufferedImage.setRGB(x, y, rgb.red << 16 | rgb.green << 8 | rgb.blue);
				}
			}
			return bufferedImage;
		} else {
			RGB[] rgbs = palette.getRGBs();
			byte[] red = new byte[rgbs.length];
			byte[] green = new byte[rgbs.length];
			byte[] blue = new byte[rgbs.length];
			for (int i = 0; i < rgbs.length; i++) {
				RGB rgb = rgbs[i];
				red[i] = (byte) rgb.red;
				green[i] = (byte) rgb.green;
				blue[i] = (byte) rgb.blue;
			}
			if (data.transparentPixel != -1) {
				colorModel = new IndexColorModel(data.depth, rgbs.length, red, green, blue, data.transparentPixel);
			} else {
				colorModel = new IndexColorModel(data.depth, rgbs.length, red, green, blue);
			}
			BufferedImage bufferedImage = new BufferedImage(colorModel, colorModel.createCompatibleWritableRaster(data.width,
					data.height), false, null);
			WritableRaster raster = bufferedImage.getRaster();
			int[] pixelArray = new int[1];
			for (int y = 0; y < data.height; y++) {
				for (int x = 0; x < data.width; x++) {
					int pixel = data.getPixel(x, y);
					pixelArray[0] = pixel;
					raster.setPixel(x, y, pixelArray);
				}
			}
			return bufferedImage;
		}
	}

	/**
	 * Convert an AWT BufferedImage to an swt imagedata
	 * 
	 * @param bufferedImage
	 *          the awt buffered image
	 * @return the converted image data
	 */
	public static ImageData convertToSWT(BufferedImage bufferedImage) {
		if (bufferedImage.getColorModel() instanceof DirectColorModel) {
			DirectColorModel colorModel = (DirectColorModel) bufferedImage.getColorModel();
			PaletteData palette = new PaletteData(colorModel.getRedMask(), colorModel.getGreenMask(),
					colorModel.getBlueMask());
			ImageData data = new ImageData(bufferedImage.getWidth(), bufferedImage.getHeight(), colorModel.getPixelSize(),
					palette);
			for (int y = 0; y < data.height; y++) {
				for (int x = 0; x < data.width; x++) {
					int rgb = bufferedImage.getRGB(x, y);
					int pixel = palette.getPixel(new RGB((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF));
					data.setPixel(x, y, pixel);
					if (colorModel.hasAlpha()) {
						data.setAlpha(x, y, (rgb >> 24) & 0xFF);
					}
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
			ImageData data = new ImageData(bufferedImage.getWidth(), bufferedImage.getHeight(), colorModel.getPixelSize(),
					palette);
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
	
	public static boolean arrayContains(Object[] arr, Object targetValue) {
		for(Object s: arr){
			if(s.equals(targetValue))
				return true;
		}
		return false;
	}
	
	/**
	 * Generate a grayed out image of a source image
	 */
	public static ImageData createGrayImage(Image baseImage) {
		if (baseImage == null) return null;
		ImageData data = (ImageData)baseImage.getImageData().clone();
		PaletteData palette = data.palette;
		for(int i=0; i<data.width; i++) {
			for(int j=0; j<data.height; j++) {
				RGB color = palette.getRGB(data.getPixel(i, j));
				int newColor = ((color.blue + color.green + color.red) % 104) + 128;

				RGB grayedColor = new RGB(newColor, newColor, newColor);
				if (!palette.isDirect &&  !arrayContains(palette.colors, grayedColor)) {
					RGB[] newPalette = Arrays.copyOf(palette.colors, palette.colors.length + 1);
					newPalette[newPalette.length - 1] = grayedColor;
					palette.colors = newPalette;
				}
				data.setPixel(i, j, palette.getPixel(grayedColor));
			}
		}
		return data;
	}
}
