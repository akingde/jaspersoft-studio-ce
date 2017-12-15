/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.java2d;

/*
 * A factory for creating Renderer objects.
 */
public final class RendererFactory {

	/** The image renderer. */
	private static ImageRenderer imageRenderer;

	/**
	 * A.
	 * 
	 * @return the image renderer
	 */
	public static synchronized ImageRenderer a() // getImageRenderer
	{
		if (imageRenderer == null)
			imageRenderer = createRenderer();
		return imageRenderer;
	}

	/**
	 * Creates a new Renderer object.
	 * 
	 * @return the image renderer
	 */
	private static ImageRenderer createRenderer() {
//		try {
//			if ((Platform.getOS().equals(Platform.OS_WIN32)) && (Platform.getOSArch().equals(Platform.ARCH_X86))) {
//				return new Win32ImageRenderer();
//			} 
//
//			// else if ((Platform.getOS().equals(Platform.OS_LINUX)) && (Platform.getWS().equals(Platform.WS_GTK))) {
//			// return new LinuxImageRenderer();
//			// }
//		} catch (NoClassDefFoundError e) {
//			e.printStackTrace();
//		} catch (UnsatisfiedLinkError e) {
//			e.printStackTrace();
//		}
		return new GenericImageRenderer();
	}
}
