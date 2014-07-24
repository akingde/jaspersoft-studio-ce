/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.editor.java2d;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.lang.reflect.Method;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This new graphics 2d was created in order to speed up the painting of the elements.
 * Calculate how an element should be painted is a really time expensive operation. This 
 * graphics the first time it is painted store the necessary step and the he can repaint 
 * it from scratch without recalculate it. So and element need to be recalculated only 
 * when some graphical property is changed, otherwise it will be necessary only to repeat
 * the stored paint steps. Essentially this class store a series of step into a stack and 
 * then can execute all of them at the same time.
 * All the methods are stored and called trought reflection
 * 
 * 
 * @author Orlandin Marco
 *
 */
public class StackGraphics2D extends Graphics2D {
	
	/**
	 * The interface of a command that can be inserted inside the stack
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private interface ExecutableCommand{
		/**
		 * Execute the command
		 */
		public void execute();
	}
	
	/**
	 * Many time with a graphics 2d an element is returned, some stuff is done, 
	 * and the it is restored. Since this graphics2d don't execute the every draw
	 * operation when it is requested, but all at the end. The sequentiality of the 
	 * commands is not keep then if the user doest a read and the a restore of a reference.
	 * For this reason when the user uses a get method to take the value of a property and 
	 * then he uses a set method to restore its value, if the value used in the set is the same
	 * reference returned in the get then he want to do a backup and restore operation. So 2 commands
	 * are automatically generated, one to backup the value when he has done the get, and one to 
	 * restore it when he has done the set. Since this commands will be executed with the other commands
	 * in the stack the sequentiality of the backup\restore is preserved. 
	 * 
	 * Actually this operation is done only for getClip\setClip and getTransform\setTransform
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class BackupProperty implements ExecutableCommand{
		
		/**
		 * The name of the property backupped
		 */
		private String propertyName;
		
		/**
		 * The value of the property
		 */
		private Object propertyValue;
		
		/**
		 * The type of the return value of the property
		 */
		private Class<?> returnType;
		
		/**
		 * Create the command
		 *  
		 * @param propertyName method to call to get the property value brought reflection
		 */
		public BackupProperty(String propertyName){
			this.propertyName = propertyName;
			propertyValue = null;
		}
		
		/**
		 * Return the result of the call
		 * 
		 * @return result of the call, could be essentially anything (also void), it depends from the 
		 * called method
		 */
		public Object getReturnValue(){
			return propertyValue;
		}
		
		/**
		 * Return the type of the getReturnedValue, check this to avoid to read a void value
		 * 
		 * @return the return type
 		 */
		public Class<?> getReturnType(){
			return returnType;
		}
		
		/**
		 * Execute the command by calling the method by reflection and saving its result
		 */
		public void execute(){
			try {
				Method method = realDrawer.getClass().getMethod(propertyName);
				returnType = method.getReturnType();
				propertyValue = method.invoke(realDrawer);
			} catch (Exception e) {
			  e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * Same stuff of the backup property, but this time it is called to restore the 
	 * value of a property
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class RestoreProperty implements ExecutableCommand{
		
		/**
		 * Name of the method to call with the reflection to save the  property
		 */
		private String propertyName;
		
		/**
		 * Reference to the backupped property element
		 */
		private BackupProperty propertyInfo;
		
		/**
		 * Create a restore command, it need a backup command already created to 
		 * be executed
		 * 
		 * @param propertyName Name of the method to call with the reflection to save the  property
		 * @param propertyInfo Reference to the backupped property element
		 */
		public RestoreProperty(String propertyName, BackupProperty propertyInfo){
			this.propertyName = propertyName;
			this.propertyInfo = propertyInfo;
		}
		
		/**
		 * Execute the restore
		 */
		public void execute(){
			try {
				Method method = realDrawer.getClass().getMethod(propertyName, propertyInfo.getReturnType());
				method.invoke(realDrawer, propertyInfo.getReturnValue());
			} catch (Exception e) {
			  e.printStackTrace();
			}
		}
	}
	
	/**
	 * Command to call a generic method with any number of parameters. The return 
	 * value is not saved or used. The call is done trough the java reflection
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class CallDefinition implements ExecutableCommand{
		
		/**
		 * Types of the parameters
		 */
		private List<Class<?>> parametersType = new ArrayList<Class<?>>();
		
		/**
		 * Values of the parameters
		 */
		private List<Object> parametersValue = new ArrayList<Object>();
		
		/**
		 * Name of the method to call
		 */
		private String methodName;

		/**
		 * Create the command 
		 * @param name name of the method to call
		 */
		public CallDefinition(String name){
			methodName = name;
		}
		
		/**
		 * Return the name of the method to call
		 * @return name of the method
		 */
		public String getMethodName(){
			return methodName;
		}
		
		/**
		 * Add a new type for a parameter. The number of 
		 * parameters any types must be the same 
		 * 
		 * @param newType new type
		 */
		public void addType(Class<?> newType){
			parametersType.add(newType);
		}
		
		/**
		 * Add a new value for a parameter. The number of 
		 * parameters any types must be the same 
		 * 
		 * @param newValue the new value
		 */
		public void addValue(Object newValue){
			parametersValue.add(newValue);
		}
		
		/**
		 * Return an array of values for the parameters 
		 * @return the values for the parameters, could be empty but not null
		 */
		public Object[] getValues(){
			return parametersValue.toArray(new Object[parametersValue.size()]);
		}
		
		/**
		 * Return an array of types for the parameters 
		 * @return the types for the parameters, could be empty but not null
		 */
		public Class<?>[] getTypes(){
			return parametersType.toArray(new Class<?>[parametersType.size()]);
		}
		
		/**
		 * Execute the specified command with the specified parameters values\types
		 */
		public void execute(){
			try {
				Method method = realDrawer.getClass().getMethod(getMethodName(), getTypes());
				method.invoke(realDrawer, getValues());
			} catch (Exception e) {
			  e.printStackTrace();
			}
		}
	}
	
	/**
	 * Real graphic2D that will be used to draw the stuff specified in the stack
	 */
	private Graphics2D realDrawer;
	
	/**
	 * Trough a reference of an element bind a return command with a restore command. If the 
	 * reference returned by a get is used inside a set then this is a backup\restore operation
	 */
	private HashMap<Object, BackupProperty> statusRestorer = new HashMap<Object, BackupProperty>();
	
	/**
	 * List of command in the stack
	 */
	private List<ExecutableCommand> stack = new ArrayList<StackGraphics2D.ExecutableCommand>();
	
	/**
	 * Create an instance of the class
	 * 
	 * @param realDrawer Real graphic2D that will be used to draw the stuff specified in the stack
	 */
	public StackGraphics2D(Graphics2D realDrawer){
		this.realDrawer = realDrawer;
	}
	
	/**
	 * Set the drawer for this element
	 * 
	 * @param realDrawer Real graphic2D that will be used to draw the stuff specified in the stack, must be not null
	 */
	public void setRealDrawer(Graphics2D realDrawer){
		this.realDrawer = realDrawer;
	}
	
	/**
	 * Execute all the commands in the stack
	 */
	public void paintStack(){
		for(ExecutableCommand definition : stack){
			definition.execute();
		}
	}
	
	/**
	 * Clear the stack
	 */
	public void clearStack(){
		stack.clear();
	}

	/**
	 * Create a clone of an AffineTrasnform
	 * 
	 * @param original original affine transform, must be not null
	 * @return an independent copy of the parameter
	 */
	private AffineTransform copyTransform(AffineTransform original){
		return new AffineTransform(original);
	}
	
	@Override
	public void addRenderingHints(Map<?, ?> arg0) {
		CallDefinition call = new CallDefinition("addRenderingHints");
		call.addType(Map.class);
		call.addValue(arg0);
		stack.add(call);
	}

	@Override
	public void setRenderingHints(Map<?, ?> arg0) {
		CallDefinition call = new CallDefinition("setRenderingHints");
		call.addType(Map.class);
		call.addValue(arg0);
		stack.add(call);
	}
	
	@Override
	public void clip(Shape arg0) {
		CallDefinition call = new CallDefinition("clip");
		call.addType(Shape.class);
		call.addValue(arg0);
		stack.add(call);
	}

	@Override
	public void draw(Shape arg0) {
		CallDefinition call = new CallDefinition("draw");
		call.addType(Shape.class);
		call.addValue(arg0);
		stack.add(call);
	}

	@Override
	public void drawGlyphVector(GlyphVector arg0, float arg1, float arg2) {
		CallDefinition call = new CallDefinition("drawGlyphVector");
		call.addType(GlyphVector.class);
		call.addValue(arg0);
		
		call.addType(float.class);
		call.addValue(arg1);
		
		call.addType(float.class);
		call.addValue(arg2);
		
		stack.add(call);
	}
	

	@Override
	public boolean drawImage(Image arg0, AffineTransform arg1, ImageObserver arg2) {
		CallDefinition call = new CallDefinition("drawImage");
		call.addType(Image.class);
		call.addValue(arg0);
		
		call.addType(AffineTransform.class);
		call.addValue(copyTransform(arg1));
		
		call.addType(ImageObserver.class);
		call.addValue(arg2);
		
		stack.add(call);
		
		return false;
	}

	@Override
	public void drawImage(BufferedImage arg0, BufferedImageOp arg1, int arg2, int arg3) {
		CallDefinition call = new CallDefinition("drawImage");
		call.addType(BufferedImage.class);
		call.addValue(arg0);
		
		call.addType(BufferedImageOp.class);
		call.addValue(arg1);
		
		call.addType(int.class);
		call.addValue(arg2);
		
		call.addType(int.class);
		call.addValue(arg3);
		
		stack.add(call);
	}

	@Override
	public void drawRenderableImage(RenderableImage arg0, AffineTransform arg1) {
		CallDefinition call = new CallDefinition("drawRenderableImage");
		call.addType(RenderableImage.class);
		call.addValue(arg0);
		
		call.addType(AffineTransform.class);
		call.addValue(copyTransform(arg1));
		
		stack.add(call);
	}

	@Override
	public void drawRenderedImage(RenderedImage arg0, AffineTransform arg1) {
		CallDefinition call = new CallDefinition("drawRenderedImage");
		call.addType(RenderedImage.class);
		call.addValue(arg0);
		
		call.addType(AffineTransform.class);
		call.addValue(copyTransform(arg1));
		
		stack.add(call);
	}

	@Override
	public void drawString(String arg0, int arg1, int arg2) {
		CallDefinition call = new CallDefinition("drawString");
		call.addType(String.class);
		call.addValue(arg0);
		
		call.addType(int.class);
		call.addValue(arg1);

		call.addType(int.class);
		call.addValue(arg2);
		
		stack.add(call);
	}

	@Override
	public void drawString(String arg0, float arg1, float arg2) {
		CallDefinition call = new CallDefinition("drawString");
		call.addType(String.class);
		call.addValue(arg0);
		
		call.addType(float.class);
		call.addValue(arg1);

		call.addType(float.class);
		call.addValue(arg2);
		
		stack.add(call);
	}

	@Override
	public void drawString(AttributedCharacterIterator arg0, int arg1, int arg2) {
		CallDefinition call = new CallDefinition("drawString");
		call.addType(AttributedCharacterIterator.class);
		call.addValue(arg0);
		
		call.addType(int.class);
		call.addValue(arg1);

		call.addType(int.class);
		call.addValue(arg2);
		
		stack.add(call);
	}

	@Override
	public void drawString(AttributedCharacterIterator arg0, float arg1, float arg2) {
		CallDefinition call = new CallDefinition("drawString");
		call.addType(AttributedCharacterIterator.class);
		call.addValue(arg0);
		
		call.addType(float.class);
		call.addValue(arg1);

		call.addType(float.class);
		call.addValue(arg2);
		
		stack.add(call);
	}

	@Override
	public void fill(Shape arg0) {
		CallDefinition call = new CallDefinition("fill");
		call.addType(Shape.class);
		call.addValue(arg0);
		
		stack.add(call);
	}

	@Override
	public Color getBackground() {
		return realDrawer.getBackground();
	}

	@Override
	public Composite getComposite() {
		return realDrawer.getComposite();
	}

	@Override
	public GraphicsConfiguration getDeviceConfiguration() {
		return realDrawer.getDeviceConfiguration();
	}

	@Override
	public FontRenderContext getFontRenderContext() {
		return realDrawer.getFontRenderContext();
	}

	@Override
	public Paint getPaint() {
		return realDrawer.getPaint();
	}

	@Override
	public Object getRenderingHint(Key arg0) {
		return realDrawer.getRenderingHint(arg0);
	}

	@Override
	public RenderingHints getRenderingHints() {
		return realDrawer.getRenderingHints();
	}

	@Override
	public Stroke getStroke() {
		return realDrawer.getStroke();
	}

	@Override
	public AffineTransform getTransform() {
		BackupProperty backupCommand = new BackupProperty("getTransform");
		stack.add(backupCommand);
		AffineTransform bindingReference = realDrawer.getTransform();
		statusRestorer.put(bindingReference, backupCommand);
		return bindingReference;
	}

	@Override
	public boolean hit(Rectangle arg0, Shape arg1, boolean arg2) {
		CallDefinition call = new CallDefinition("hit");
		call.addType(Rectangle.class);
		call.addValue(arg0);
		
		call.addType(Shape.class);
		call.addValue(arg1);
		
		call.addType(boolean.class);
		call.addValue(arg2);
		
		stack.add(call);
		return false;
	}

	@Override
	public void rotate(double arg0) {
		CallDefinition call = new CallDefinition("rotate");
		call.addType(double.class);
		call.addValue(arg0);
		
		stack.add(call);
	}

	@Override
	public void rotate(double arg0, double arg1, double arg2) {
		CallDefinition call = new CallDefinition("rotate");
		call.addType(double.class);
		call.addValue(arg0);
		
		call.addType(double.class);
		call.addValue(arg1);
		
		call.addType(double.class);
		call.addValue(arg2);
		
		stack.add(call);
	}

	@Override
	public void scale(double arg0, double arg1) {
		CallDefinition call = new CallDefinition("scale");
		call.addType(double.class);
		call.addValue(arg0);
		
		call.addType(double.class);
		call.addValue(arg1);
		
		stack.add(call);
	}

	@Override
	public void setBackground(Color arg0) {
		CallDefinition call = new CallDefinition("setBackground");
		call.addType(Color.class);
		call.addValue(arg0);
		
		stack.add(call);
	}

	@Override
	public void setComposite(Composite arg0) {
		CallDefinition call = new CallDefinition("setComposite");
		call.addType(Composite.class);
		call.addValue(arg0);
		
		stack.add(call);
	}

	@Override
	public void setPaint(Paint arg0) {
		CallDefinition call = new CallDefinition("setPaint");
		call.addType(Paint.class);
		call.addValue(arg0);
		
		stack.add(call);
	}

	@Override
	public void setRenderingHint(Key arg0, Object arg1) {
		CallDefinition call = new CallDefinition("setRenderingHint");
		call.addType(Key.class);
		call.addValue(arg0);
		
		call.addType(Object.class);
		call.addValue(arg1);
		
		stack.add(call);
	}



	@Override
	public void setStroke(Stroke arg0) {
		CallDefinition call = new CallDefinition("setStroke");
		call.addType(Stroke.class);
		call.addValue(arg0);
		
		stack.add(call);
	}

	@Override
	public void setTransform(AffineTransform arg0) {
		if (!statusRestorer.containsKey(arg0)){
			//It's not a status restoring operation
			CallDefinition call = new CallDefinition("setTransform");
			call.addType(AffineTransform.class);
			call.addValue(copyTransform(arg0));
			stack.add(call);
		} else {
			//status restoring operation
			BackupProperty backup = statusRestorer.get(arg0);
			RestoreProperty call = new RestoreProperty("setTransform", backup);
			stack.add(call);
		}
	}

	@Override
	public void shear(double arg0, double arg1) {
		CallDefinition call = new CallDefinition("shear");
		call.addType(double.class);
		call.addValue(arg0);
		
		call.addType(double.class);
		call.addValue(arg1);
		
		stack.add(call);
	}

	@Override
	public void transform(AffineTransform arg0) {
		CallDefinition call = new CallDefinition("transform");
		call.addType(AffineTransform.class);
		call.addValue(copyTransform(arg0));
		
		stack.add(call);
	}

	@Override
	public void translate(int arg0, int arg1) {
		CallDefinition call = new CallDefinition("translate");
		call.addType(int.class);
		call.addValue(arg0);
		
		call.addType(int.class);
		call.addValue(arg1);
		
		stack.add(call);
	}

	@Override
	public void translate(double arg0, double arg1) {
		CallDefinition call = new CallDefinition("translate");
		call.addType(double.class);
		call.addValue(arg0);
		
		call.addType(double.class);
		call.addValue(arg1);
		
		stack.add(call);

	}

	@Override
	public void clearRect(int arg0, int arg1, int arg2, int arg3) {
		CallDefinition call = new CallDefinition("clearRect");
		call.addType(int.class);
		call.addValue(arg0);
		
		call.addType(int.class);
		call.addValue(arg1);
		
		call.addType(int.class);
		call.addValue(arg2);
		
		call.addType(int.class);
		call.addValue(arg3);
		
		stack.add(call);
	}

	@Override
	public void clipRect(int arg0, int arg1, int arg2, int arg3) {
		CallDefinition call = new CallDefinition("clipRect");
		call.addType(int.class);
		call.addValue(arg0);
		
		call.addType(int.class);
		call.addValue(arg1);
		
		call.addType(int.class);
		call.addValue(arg2);
		
		call.addType(int.class);
		call.addValue(arg3);
		
		stack.add(call);
	}

	@Override
	public void copyArea(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		CallDefinition call = new CallDefinition("copyArea");
		call.addType(int.class);
		call.addValue(arg0);
		
		call.addType(int.class);
		call.addValue(arg1);
		
		call.addType(int.class);
		call.addValue(arg2);
		
		call.addType(int.class);
		call.addValue(arg3);
		
		call.addType(int.class);
		call.addValue(arg4);
		
		call.addType(int.class);
		call.addValue(arg5);
		
		stack.add(call);
	}

	@Override
	public Graphics create() {
		return realDrawer.create();
	}

	@Override
	public void dispose() {
		realDrawer.dispose();
	}

	@Override
	public void drawArc(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		CallDefinition call = new CallDefinition("drawArc");
		call.addType(int.class);
		call.addValue(arg0);
		
		call.addType(int.class);
		call.addValue(arg1);
		
		call.addType(int.class);
		call.addValue(arg2);
		
		call.addType(int.class);
		call.addValue(arg3);
		
		call.addType(int.class);
		call.addValue(arg4);
		
		call.addType(int.class);
		call.addValue(arg5);
		
		stack.add(call);
	}

	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, ImageObserver arg3) {
		CallDefinition call = new CallDefinition("drawImage");
		call.addType(Image.class);
		call.addValue(arg0);
		
		call.addType(int.class);
		call.addValue(arg1);
		
		call.addType(int.class);
		call.addValue(arg2);
		
		call.addType(ImageObserver.class);
		call.addValue(arg3);
		
		stack.add(call);
		return false;
	}

	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, Color arg3, ImageObserver arg4) {
		CallDefinition call = new CallDefinition("drawImage");
		call.addType(Image.class);
		call.addValue(arg0);
		
		call.addType(int.class);
		call.addValue(arg1);
		
		call.addType(int.class);
		call.addValue(arg2);
		
		call.addType(Color.class);
		call.addValue(arg3);
		
		call.addType(ImageObserver.class);
		call.addValue(arg4);
		
		stack.add(call);
		return false;
	}

	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, int arg3, int arg4, ImageObserver arg5) {
		CallDefinition call = new CallDefinition("drawImage");
		call.addType(Image.class);
		call.addValue(arg0);
		
		call.addType(int.class);
		call.addValue(arg1);
		
		call.addType(int.class);
		call.addValue(arg2);
		
		call.addType(int.class);
		call.addValue(arg3);
		
		call.addType(int.class);
		call.addValue(arg4);
		
		call.addType(ImageObserver.class);
		call.addValue(arg5);
		
		stack.add(call);
		return false;
	}

	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, int arg3, int arg4, Color arg5, ImageObserver arg6) {
		CallDefinition call = new CallDefinition("drawImage");
		call.addType(Image.class);
		call.addValue(arg0);
		
		call.addType(int.class);
		call.addValue(arg1);
		
		call.addType(int.class);
		call.addValue(arg2);
		
		call.addType(int.class);
		call.addValue(arg3);
		
		call.addType(int.class);
		call.addValue(arg4);
		
		call.addType(Color.class);
		call.addValue(arg5);
		
		call.addType(ImageObserver.class);
		call.addValue(arg6);
		
		stack.add(call);
		return false;
	}

	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, ImageObserver arg9) {
		CallDefinition call = new CallDefinition("drawImage");
		call.addType(Image.class);
		call.addValue(arg0);
		
		call.addType(int.class);
		call.addValue(arg1);
		
		call.addType(int.class);
		call.addValue(arg2);
		
		call.addType(int.class);
		call.addValue(arg3);
		
		call.addType(int.class);
		call.addValue(arg4);
		
		call.addType(int.class);
		call.addValue(arg5);
		
		call.addType(int.class);
		call.addValue(arg6);
		
		call.addType(int.class);
		call.addValue(arg7);
		
		call.addType(int.class);
		call.addValue(arg8);
		
		call.addType(ImageObserver.class);
		call.addValue(arg9);
		
		stack.add(call);
		return false;
	}

	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, Color arg9, ImageObserver arg10) {
		CallDefinition call = new CallDefinition("drawImage");
		call.addType(Image.class);
		call.addValue(arg0);
		
		call.addType(int.class);
		call.addValue(arg1);
		
		call.addType(int.class);
		call.addValue(arg2);
		
		call.addType(int.class);
		call.addValue(arg3);
		
		call.addType(int.class);
		call.addValue(arg4);
		
		call.addType(int.class);
		call.addValue(arg5);
		
		call.addType(int.class);
		call.addValue(arg6);
		
		call.addType(int.class);
		call.addValue(arg7);
		
		call.addType(int.class);
		call.addValue(arg8);
		
		call.addType(Color.class);
		call.addValue(arg9);
		
		call.addType(ImageObserver.class);
		call.addValue(arg10);
		
		stack.add(call);
		return false;
	}

	@Override
	public void drawLine(int arg0, int arg1, int arg2, int arg3) {
		CallDefinition call = new CallDefinition("drawLine");
		call.addType(int.class);
		call.addValue(arg0);
		
		call.addType(int.class);
		call.addValue(arg1);
		
		call.addType(int.class);
		call.addValue(arg2);
		
		call.addType(int.class);
		call.addValue(arg3);
		
		stack.add(call);
	}

	@Override
	public void drawOval(int arg0, int arg1, int arg2, int arg3) {
		CallDefinition call = new CallDefinition("drawOval");
		call.addType(int.class);
		call.addValue(arg0);
		
		call.addType(int.class);
		call.addValue(arg1);
		
		call.addType(int.class);
		call.addValue(arg2);
		
		call.addType(int.class);
		call.addValue(arg3);
		
		stack.add(call);
	}

	@Override
	public void drawPolygon(int[] arg0, int[] arg1, int arg2) {
		CallDefinition call = new CallDefinition("drawPolygon");
		call.addType(int[].class);
		call.addValue(arg0);
		
		call.addType(int[].class);
		call.addValue(arg1);
		
		call.addType(int.class);
		call.addValue(arg2);
		
		stack.add(call);
	}

	@Override
	public void drawPolyline(int[] arg0, int[] arg1, int arg2) {
		CallDefinition call = new CallDefinition("drawPolyline");
		call.addType(int[].class);
		call.addValue(arg0);
		
		call.addType(int[].class);
		call.addValue(arg1);
		
		call.addType(int.class);
		call.addValue(arg2);
		
		stack.add(call);
	}

	@Override
	public void drawRoundRect(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		CallDefinition call = new CallDefinition("drawRoundRect");
		call.addType(int.class);
		call.addValue(arg0);
		
		call.addType(int.class);
		call.addValue(arg1);
		
		call.addType(int.class);
		call.addValue(arg2);
		
		call.addType(int.class);
		call.addValue(arg3);
		
		call.addType(int.class);
		call.addValue(arg4);
		
		call.addType(int.class);
		call.addValue(arg5);
		
		stack.add(call);
	}

	@Override
	public void fillArc(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		CallDefinition call = new CallDefinition("fillArc");
		call.addType(int.class);
		call.addValue(arg0);
		
		call.addType(int.class);
		call.addValue(arg1);
		
		call.addType(int.class);
		call.addValue(arg2);
		
		call.addType(int.class);
		call.addValue(arg3);
		
		call.addType(int.class);
		call.addValue(arg4);
		
		call.addType(int.class);
		call.addValue(arg5);
		
		stack.add(call);
	}

	@Override
	public void fillOval(int arg0, int arg1, int arg2, int arg3) {
		CallDefinition call = new CallDefinition("fillOval");
		call.addType(int.class);
		call.addValue(arg0);
		
		call.addType(int.class);
		call.addValue(arg1);
		
		call.addType(int.class);
		call.addValue(arg2);
		
		call.addType(int.class);
		call.addValue(arg3);
		
		stack.add(call);
	}

	@Override
	public void fillPolygon(int[] arg0, int[] arg1, int arg2) {
		CallDefinition call = new CallDefinition("fillPolygon");
		call.addType(int[].class);
		call.addValue(arg0);
		
		call.addType(int[].class);
		call.addValue(arg1);
		
		call.addType(int.class);
		call.addValue(arg2);
		
		stack.add(call);
	}

	@Override
	public void fillRect(int arg0, int arg1, int arg2, int arg3) {
		CallDefinition call = new CallDefinition("fillRect");
		call.addType(int.class);
		call.addValue(arg0);
		
		call.addType(int.class);
		call.addValue(arg1);
		
		call.addType(int.class);
		call.addValue(arg2);
		
		call.addType(int.class);
		call.addValue(arg3);
		
		stack.add(call);
	}

	@Override
	public void fillRoundRect(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		CallDefinition call = new CallDefinition("fillRoundRect");
		call.addType(int.class);
		call.addValue(arg0);
		
		call.addType(int.class);
		call.addValue(arg1);
		
		call.addType(int.class);
		call.addValue(arg2);
		
		call.addType(int.class);
		call.addValue(arg3);
		
		call.addType(int.class);
		call.addValue(arg4);
		
		call.addType(int.class);
		call.addValue(arg5);
		
		stack.add(call);
	}

	@Override
	public Shape getClip() {
		BackupProperty backupCommand = new BackupProperty("getClip");
		stack.add(backupCommand);
		Shape bindingReference = realDrawer.getClip();
		statusRestorer.put(bindingReference, backupCommand);
		return bindingReference;
	}

	@Override
	public Rectangle getClipBounds() {
		return realDrawer.getClipBounds();
	}

	@Override
	public Color getColor() {
		return realDrawer.getColor(); 
	}

	@Override
	public Font getFont() {
		return realDrawer.getFont();
	}

	@Override
	public FontMetrics getFontMetrics(Font arg0) {
		return realDrawer.getFontMetrics(arg0);
	}

	@Override
	public void setClip(Shape arg0) {
		if (!statusRestorer.containsKey(arg0)){
			//It's not a status restoring operation
			CallDefinition call = new CallDefinition("setClip");
			call.addType(Shape.class);
			call.addValue(arg0);
			stack.add(call);
		} else {
			//status restoring operation
			BackupProperty backup = statusRestorer.get(arg0);
			RestoreProperty call = new RestoreProperty("setClip", backup);
			stack.add(call);
		}
	}

	@Override
	public void setClip(int arg0, int arg1, int arg2, int arg3) {
		CallDefinition call = new CallDefinition("setClip");
		call.addType(int.class);
		call.addValue(arg0);
		
		call.addType(int.class);
		call.addValue(arg1);
		
		call.addType(int.class);
		call.addValue(arg2);
		
		call.addType(int.class);
		call.addValue(arg3);
		
		stack.add(call);
	}

	@Override
	public void setColor(Color arg0) {
		CallDefinition call = new CallDefinition("setColor");
		call.addType(Color.class);
		call.addValue(arg0);
		
		stack.add(call);
	}

	@Override
	public void setFont(Font arg0) {
		CallDefinition call = new CallDefinition("setFont");
		call.addType(Font.class);
		call.addValue(arg0);
		
		stack.add(call);
	}

	@Override
	public void setPaintMode() {
		CallDefinition call = new CallDefinition("setPaintMode");
		
		stack.add(call);
	}

	@Override
	public void setXORMode(Color arg0) {
		CallDefinition call = new CallDefinition("setXORMode");
		call.addType(Color.class);
		call.addValue(arg0);
		
		stack.add(call);
	}

}
