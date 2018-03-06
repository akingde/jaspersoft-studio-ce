package com.jaspersoft.studio.editor.action.imports.svg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;

// import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.anim.dom.SVGOMElement;
import org.apache.batik.anim.dom.SVGOMEllipseElement;
import org.apache.batik.anim.dom.SVGOMGElement;
import org.apache.batik.anim.dom.SVGOMImageElement;
import org.apache.batik.anim.dom.SVGOMPathElement;
import org.apache.batik.anim.dom.SVGOMRectElement;
//import org.apache.batik.dom.svg.SVGOMSVGElement;
import org.apache.batik.anim.dom.SVGOMSVGElement;
import org.apache.batik.anim.dom.SVGOMTSpanElement;
import org.apache.batik.anim.dom.SVGOMTextElement;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.DocumentLoader;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgent;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.dom.GenericText;
import org.apache.batik.parser.AWTPathProducer;
import org.apache.batik.parser.AWTTransformProducer;
import org.apache.batik.parser.PathParser;
import org.apache.batik.parser.TransformListParser;
import org.apache.batik.util.Base64DecodeStream;
import org.apache.batik.util.XMLResourceDescriptor;
import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.css.CSSValue;
import org.w3c.dom.css.RGBColor;
import org.w3c.dom.svg.SVGPaint;
import org.w3c.dom.svg.SVGRect;
import org.w3c.dom.svg.SVGSVGElement;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRCommonText;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignEllipse;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JRDesignLine;
import net.sf.jasperreports.engine.design.JRDesignRectangle;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.fill.JRMeasuredText;
import net.sf.jasperreports.engine.fill.JRTextMeasurer;
import net.sf.jasperreports.engine.fonts.FontUtil;
import net.sf.jasperreports.engine.type.LineDirectionEnum;
import net.sf.jasperreports.engine.type.RotationEnum;
import net.sf.jasperreports.engine.util.JRStyledText;
import net.sf.jasperreports.engine.util.JRStyledTextParser;
import net.sf.jasperreports.engine.util.JRStyledTextUtil;
import net.sf.jasperreports.engine.util.JRTextMeasurerUtil;

public class SVGDocumentLoader {

  private static final double degree = 180 / Math.PI;
  
  private static final double radian = Math.PI / 180;
	
  private static final String IMPORTED_IMAGE_PREFIX = "imported_image_"; //$NON-NLS-1$
  
  private Document svgDocument;
  
  private JasperReportsConfiguration jConfig;

  /**
   * Creates an SVG Document given a URI.
   *
   * @param uri Path to the file.
   * @throws Exception Something went wrong parsing the SVG file.
   */
  public SVGDocumentLoader(String uri, JasperReportsConfiguration jConfig) throws IOException {
    setSVGDocument( createSVGDocument( uri ) );
    this.jConfig = jConfig;
  }
  
  protected String createSpacesString(int numberOfSpaces) {
	  char[] chars = new char[numberOfSpaces];
	  Arrays.fill(chars,' ');
	  return new String(chars);
  }
  
  private boolean isBold(CSSStyleDeclaration style) {
	  String fontWeight = style.getPropertyValue("font-weight"); //$NON-NLS-1$
	  if (fontWeight != null && !fontWeight.trim().isEmpty()) {
		  if (fontWeight.equalsIgnoreCase("bold") || fontWeight.equalsIgnoreCase("bolder")) { //$NON-NLS-1$ //$NON-NLS-2$
			  return true;
		  }
		  if (StringUtils.isNumeric(fontWeight)) {
			  try {
				  int weight = Integer.parseInt(fontWeight);
				  return weight > 500;
			  } catch (Exception ex) {
				  ex.printStackTrace();
			  }
		  }
	  } 
	  return false;
  }
  
  private float getStrokeWidth(CSSStyleDeclaration style) {
	  String strokeWidth = style.getPropertyValue("stroke-width"); //$NON-NLS-1$
	  if (StringUtils.isNumeric(strokeWidth)) {
		  try {
			  return Float.parseFloat(strokeWidth);
		  } catch (Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  return 0f;
  }
  
  
  private boolean isItalic(CSSStyleDeclaration style) {
	  String fontWeight = style.getPropertyValue("font-style"); //$NON-NLS-1$
	  if (fontWeight != null && !fontWeight.trim().isEmpty()) {
		  if (fontWeight.equalsIgnoreCase("oblique") || fontWeight.equalsIgnoreCase("italic")) { //$NON-NLS-1$ //$NON-NLS-2$
			  return true;
		  }
	  } 
	  return false;
  }
  
  /** 
   * Returns the color of the given attribute in the given element. 
   * 
   * @param element An element with color attributes. 
   * @param attribute The name of the attribute (fill, stroke, etc.) 
   * 
   * @return An instance of Color. 
   */ 
  public Color getColor(CSSStyleDeclaration style, String attribute) { 
	  SVGPaint svgPaint = (SVGPaint) style.getPropertyCSSValue(attribute); 
	  Color returnColor = null;
      if (svgPaint != null && svgPaint.getPaintType() == SVGPaint.SVG_PAINTTYPE_RGBCOLOR) { 
          final RGBColor rgb = svgPaint.getRGBColor(); 
          final float red = rgb.getRed().getFloatValue(CSSValue.CSS_PRIMITIVE_VALUE); 
          final float green = rgb.getGreen().getFloatValue(CSSValue.CSS_PRIMITIVE_VALUE); 
          final float blue = rgb.getBlue().getFloatValue(CSSValue.CSS_PRIMITIVE_VALUE); 
          returnColor = new Color(red / 255, green / 255, blue / 255); 
      }
      return returnColor; 
  } 
  
  @SuppressWarnings("unused")
  private int getAwtFontStyle(boolean isBold, boolean isItalic) {
	  int result = Font.PLAIN;
	  if (isBold) {
		  result = result | Font.BOLD;
	  }
	  if (isItalic) {
		  result = result | Font.ITALIC;
	  }
	  return result;
  }
  
  private double getRotation(AffineTransform transform)  {
      /**
       * scaleX = sqrt(a^2+c^2)
       * scaleY = sqrt(b^2+d^2)
       * rotation = tan^-1(c/d) = tan^-1(-b/a) it will not work sometimes 
       * rotation = a / scaleX  = d / scaleY
       */
	  double a = transform.getScaleX();
	  double c = transform.getShearX();
	  double scaleX = Math.sqrt(Math.pow(a,2) + Math.pow(c, 2));

      double sign = Math.atan(-c / a);
      double rad  = Math.acos(a / scaleX);
      double deg  = rad * degree;
      double rotation;
      if (deg > 90 && sign > 0) {
           rotation = (360 - deg) * radian;
       } else if (deg < 90 && sign < 0)  {
           rotation = (360 - deg) * radian;
       }  else {
           rotation = rad;
       }
      return rotation * degree;
  }
  
  private JRMeasuredText getTextSize(JRDesignStaticText newStaticText) {
	  String text = newStaticText.getText();
	  JRTextMeasurer measurer = JRTextMeasurerUtil.getInstance(jConfig).createTextMeasurer(newStaticText, null);
	  Map<Attribute, Object> attributes = new HashMap<>(); 
	  FontUtil.getInstance(jConfig).getAttributesWithoutAwtFont(attributes, newStaticText);
	  JRStyledText styledText = JRStyledTextParser.getInstance().getStyledText(attributes, text, !JRCommonText.MARKUP_NONE.equals(newStaticText.getMarkup()), Locale.getDefault());
	  styledText = JRStyledTextUtil.getInstance(jConfig).resolveFonts(styledText, Locale.getDefault());
	  JRMeasuredText measuredText = measurer.measure(styledText, 0, 2000, false);
	  return measuredText;
  }
  
  private JRDesignStaticText generateText(String text, CSSStyleDeclaration style, int x, int y, List<Double> singleCharX, SVGRect boundingBox, AffineTransform transform) {
	  JRDesignStaticText newStaticText = new JRDesignStaticText(); 
	  float fontSize = 9f;
	  String fontSizeText = style.getPropertyValue("font-size"); //$NON-NLS-1$
	  if (fontSizeText != null && !fontSizeText.trim().isEmpty()) {
		  if (fontSizeText.endsWith("px")) { //$NON-NLS-1$
			  fontSizeText = fontSizeText.substring(0, fontSizeText.length() - 2);
		  }
		  fontSize = Float.valueOf(fontSizeText);
	  } 

	  newStaticText.setFontSize(fontSize);
	  String fontFamily = style.getPropertyValue("font-family"); //$NON-NLS-1$
	  if (fontFamily != null && !fontFamily.trim().isEmpty()) {
		  newStaticText.setFontName(fontFamily);
	  } else {
		  fontFamily = "sans-serif"; //$NON-NLS-1$
	  }
	  
	  Color foregroundColor = getColor(style, "fill"); //$NON-NLS-1$
	  if (foregroundColor != null) {
		  newStaticText.setForecolor(foregroundColor);
	  }
	  
	  boolean isBold = isBold(style);
	  newStaticText.setBold(isBold);
	  boolean isItalic = isItalic(style);
	  newStaticText.setItalic(isItalic);
	  
	  double rotation = getRotation(transform);
	  if (rotation != 0d) {
		  if (rotation > 0d && rotation <= 90d) {
			  newStaticText.setRotation(RotationEnum.RIGHT);
		  } else if (rotation > 90d && rotation <= 180d) {
			  newStaticText.setRotation(RotationEnum.UPSIDE_DOWN);
		  } else if (rotation > 180d && rotation <= 270d) {
			  newStaticText.setRotation(RotationEnum.LEFT);
		  }
	  }
	  
	  newStaticText.setWidth(10000);
	  newStaticText.setHeight(10000);
	  newStaticText.setText(text);
	  
	  JRMeasuredText measuredText = getTextSize(newStaticText);
	  	  
	  int width = (int)Math.ceil(measuredText.getTextWidth()+1);
	  int height = (int)Math.ceil(measuredText.getTextHeight()+1);
	 /* 
	   	  Font font = new Font(fontFamily, getAwtFontStyle(isBold, isItalic), (int)Math.ceil(fontSize));
	  FontRenderContext context = new FontRenderContext(transform, false, false);
	  LineMetrics metrics = font.getLineMetrics(text, context);
	   int height = (int)Math.ceil(metrics.getHeight());
	   if (singleCharX != null && singleCharX.size() > 1) {
		  double lastCharWidth =  font.getStringBounds(text.substring(text.length()-1), context).getWidth() + 1;
		  width = (int)(Math.abs(Math.ceil(singleCharX.get(0) - singleCharX.get(singleCharX.size() - 1))) + lastCharWidth) ;
		  Rectangle2D bounds = new Rectangle2D.Float(x, y, width, height);
		  bounds = transform.createTransformedShape(bounds).getBounds2D();
		  width = (int)Math.ceil(bounds.getWidth());
	  } else {
		  Rectangle2D bounds = new Rectangle2D.Float(boundingBox.getX(), boundingBox.getY(), boundingBox.getWidth(), boundingBox.getHeight());
		  bounds = transform.createTransformedShape(bounds).getBounds2D();
		  width = (int)Math.ceil(bounds.getWidth());
	  }*/
	  
	  
	  if (newStaticText.getRotationValue() == RotationEnum.LEFT || newStaticText.getRotationValue() == RotationEnum.RIGHT) {
		  newStaticText.setHeight(width);
		  newStaticText.setWidth(height);
		  newStaticText.setY(y);
		  newStaticText.setX(x - newStaticText.getWidth() + 1);
	  } else {
		  newStaticText.setHeight(height);
		  newStaticText.setWidth(width);
		  newStaticText.setY(y - newStaticText.getHeight() + 1);
		  newStaticText.setX(x);
	  }
	  //newStaticText.setY(y - newStaticText.getHeight() + 1);
	  return newStaticText;
  }
  
  protected int getSplitPoint(String text, int startPoint) {
	  char[] chars = text.toCharArray();
	  for(int i = startPoint ; i < chars.length ; i++) {
		  char c = chars[i];
		  if (c == ' ') {
			  return i;
		  } else if (c == '.' || c == ',') {
			  return i+1;
		  }
	  }
	  return -1;
  }
  
  private double parseCoordinates(String value) {
	  if (value == null || value.trim().isEmpty()) return 0;
	  if (value.contains(" ")) { //$NON-NLS-1$
		  value = value.split(" ")[0]; //$NON-NLS-1$
	  }
	  return Double.parseDouble(value);
  }
  private List<Double> parseCoordinatesList(String value) {
	  if (value == null || value.trim().isEmpty()) return null;
	  String[] stringValues = value.split(" "); //$NON-NLS-1$
	  List<Double> result = new ArrayList<>();
	  for(String stringValue : stringValues) {
		  try {
			  result.add(Double.parseDouble(stringValue));
		  }catch (Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  return result;
  }
  
  public List<JRDesignStaticText> parseSpan(SVGOMTSpanElement textNode, SVGRect textBoundingBox, AffineTransform previousTransform) {
	  NodeList nodes = textNode.getChildNodes();
	  AffineTransform newTransform = getTransofrm(textNode);
	  previousTransform.concatenate(newTransform);
      List<JRDesignStaticText> result = new ArrayList<>();
      CSSStyleDeclaration textNodeStyle = ((SVGSVGElement)svgDocument.getDocumentElement()).getComputedStyle(textNode, null);  
	  for (int i = 0;  i < nodes.getLength();  i++) {
          Node node = nodes.item(i);
          if (node instanceof GenericText) {
        	 String text = node.getNodeValue();
        	 if (text != null) {
        		double x = parseCoordinates(textNode.getAttribute("x")); //$NON-NLS-1$
        		double y = parseCoordinates(textNode.getAttribute("y")); //$NON-NLS-1$
        		Point2D.Double point = new  Point2D.Double(x, y);
        		Point2D coordinates =  previousTransform.transform(point, null);
        		List<Double> xList = parseCoordinatesList(textNode.getAttribute("x")); //$NON-NLS-1$
        		JRDesignStaticText jrText = generateText(text, textNodeStyle, (int)coordinates.getX(), (int)coordinates.getY(), xList, textBoundingBox, previousTransform);
        		if (jrText != null) { 
        			result.add(jrText);
        		}
        	 }
          }
      }
	  return result;
  }

  public List<JRDesignStaticText> parseText(SVGOMTextElement textNode, AffineTransform previousTransform) {
	  NodeList nodes = textNode.getChildNodes();
	  AffineTransform newTransform = getTransofrm(textNode);
	  previousTransform.concatenate(newTransform);
      List<JRDesignStaticText> result = new ArrayList<>();
      SVGRect boundingBox = textNode.getBBox();
      //textNode.getAttribute("style");
      //textNode.setComputedStyleMap(null, null);
      CSSStyleDeclaration textNodeStyle = ((SVGSVGElement)svgDocument.getDocumentElement()).getComputedStyle(textNode, null);   
     // textNodeStyle.getPropertyValue("fill");
      //textNode.getAttribute("fill")
    //  textNode.getStyle().getPropertyValue("fill")
	  for (int i = 0;  i < nodes.getLength();  i++) {
          Node node = nodes.item(i);
          if (node instanceof GenericText) {
        	 String text = node.getNodeValue();
        	 if (text != null) {
        		double x = parseCoordinates(textNode.getAttribute("x")); //$NON-NLS-1$
        		double y = parseCoordinates(textNode.getAttribute("y")); //$NON-NLS-1$
        		Point2D.Double point = new  Point2D.Double(x, y);
        		Point2D coordinates =  previousTransform.transform(point, null);
        		JRDesignStaticText jrText = generateText(text, textNodeStyle, (int)coordinates.getX(), (int)coordinates.getY(), null, boundingBox, previousTransform);
        		if (jrText != null) { 
        			result.add(jrText);
        		}
        	 }
          } else if (node instanceof SVGOMTSpanElement) {
        	  result.addAll(parseSpan((SVGOMTSpanElement)node, boundingBox, new AffineTransform(previousTransform)));
          }
      }
	  return result;
  }
  
  private JRDesignRectangle parseRectangle(SVGOMRectElement rect, AffineTransform previousTransform) {
	  AffineTransform newTransform = getTransofrm(rect);
	  previousTransform.concatenate(newTransform);
	  double x = Double.parseDouble(rect.getAttribute("x")); //$NON-NLS-1$
	  double y = Double.parseDouble(rect.getAttribute("y")); //$NON-NLS-1$
	  double width = Double.parseDouble(rect.getAttribute("width")); //$NON-NLS-1$
	  double height = Double.parseDouble(rect.getAttribute("height")); //$NON-NLS-1$
	  Rectangle2D rectFigure = new Rectangle2D.Double(x, y, width, height);
	  rectFigure = (Rectangle2D)previousTransform.createTransformedShape(rectFigure).getBounds2D();
	  JRDesignRectangle rectangle = new JRDesignRectangle();
	  rectangle.setX((int)rectFigure.getX());
	  rectangle.setY((int)rectFigure.getY());
	  rectangle.setWidth((int)rectFigure.getWidth());
	  rectangle.setHeight((int)rectFigure.getHeight());
	  
	  CSSStyleDeclaration nodeStyle = ((SVGSVGElement)svgDocument.getDocumentElement()).getComputedStyle(rect, null);  
	  Color backgroundColor = getColor(nodeStyle, "fill"); //$NON-NLS-1$
	  if (backgroundColor != null) {
		  rectangle.setBackcolor(backgroundColor);
	  }
	  
	  float strokeWidth = getStrokeWidth(nodeStyle);
	  rectangle.getLinePen().setLineWidth(strokeWidth);
	  Color strokeColor = getColor(nodeStyle, "stroke"); //$NON-NLS-1$
	  if (strokeColor != null) {
		  rectangle.getLinePen().setLineColor(strokeColor);
	  }
	  
	  return rectangle;
  }
 
  
  private AffineTransform getTransofrm(SVGOMElement element) {
	  TransformListParser transformMatrix = new TransformListParser();
      AWTTransformProducer matrixProducer = new AWTTransformProducer();
      transformMatrix.setTransformListHandler(matrixProducer);
      transformMatrix.parse(element.getAttribute("transform")); //$NON-NLS-1$
      return matrixProducer.getAffineTransform();
  }
  
  
  private JRDesignImage parseImage(SVGOMImageElement imageElement, AffineTransform previousTransform) {
	  AffineTransform newTransofrm = getTransofrm(imageElement);
	  previousTransform.concatenate(newTransofrm);
	  double x = Double.parseDouble(imageElement.getAttribute("x")); //$NON-NLS-1$
	  double y = Double.parseDouble(imageElement.getAttribute("y")); //$NON-NLS-1$
	  double width = Double.parseDouble(imageElement.getAttribute("width")); //$NON-NLS-1$
	  double height = Double.parseDouble(imageElement.getAttribute("height")); //$NON-NLS-1$
	  Rectangle2D rectFigure = new Rectangle2D.Double(x, y, width, height);
	  rectFigure = (Rectangle2D)previousTransform.createTransformedShape(rectFigure).getBounds2D();
	  JRDesignImage jrImage = new JRDesignImage(jConfig.getJasperDesign());
	  jrImage.setX((int)rectFigure.getX());
	  jrImage.setY((int)rectFigure.getY());
	  jrImage.setWidth((int)rectFigure.getWidth());
	  jrImage.setHeight((int)rectFigure.getHeight());
	  
	  String sourceData = imageElement.getAttributeNS("http://www.w3.org/1999/xlink", "href"); //$NON-NLS-1$ //$NON-NLS-2$
	  String[] splitData = sourceData.split(","); //$NON-NLS-1$
	  String extension;
	  if (splitData[0].contains("png")) { //$NON-NLS-1$
		  extension = "png"; //$NON-NLS-1$
	  } else if (splitData[0].contains("bmp")) { //$NON-NLS-1$
		  extension = "bmp"; //$NON-NLS-1$
	  } else if (splitData[0].contains("ico")) { //$NON-NLS-1$
		  extension = "ico"; //$NON-NLS-1$
	  } else {
		  extension = "jpg"; //$NON-NLS-1$
	  }
	  ByteArrayInputStream imageBytes = new ByteArrayInputStream(splitData[1].getBytes());
	  Base64DecodeStream decodeStream = new Base64DecodeStream(imageBytes);
	  // create a buffered image
	  BufferedImage image;
	  try {       
		  image = ImageIO.read(decodeStream);
		  // write the image to a file
		  IFile mfile = (IFile) jConfig.get(FileUtils.KEY_FILE);
		  IContainer parent = mfile.getParent();
		  int counter = 1;
		  String filename;
		  IFile destFile;
		  do{
			  filename = IMPORTED_IMAGE_PREFIX + counter + "." + extension; //$NON-NLS-1$
			  destFile = parent.getFile(new Path(filename));
			  counter++;
		  } while (destFile.exists());
		  ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		  ByteArrayInputStream fileInputStream = null;
		  try {
			ImageIO.write(image, extension, outStream);
			fileInputStream = new ByteArrayInputStream(outStream.toByteArray());
			destFile.create(fileInputStream, true, new NullProgressMonitor());
		} catch (CoreException e) {
			e.printStackTrace();
		} finally {
			FileUtils.closeStream(outStream);
			FileUtils.closeStream(fileInputStream);
		}
		  jrImage.setExpression(new JRDesignExpression("\"" + destFile.getName() + "\"")); //$NON-NLS-1$ //$NON-NLS-2$
	  } catch (IOException e) {
		  e.printStackTrace();
	  } finally { 
		  FileUtils.closeStream(decodeStream);
		  FileUtils.closeStream(imageBytes);	
	  }
	  return jrImage;
  }
  
  private JRDesignEllipse parseEllipse(SVGOMEllipseElement ellipseElement, AffineTransform previousTransform) {
	  JRDesignEllipse result = new JRDesignEllipse(jConfig.getJasperDesign());
	  AffineTransform newTransofrm = getTransofrm(ellipseElement);
	  previousTransform.concatenate(newTransofrm);
	  double cx = Double.parseDouble(ellipseElement.getAttribute("cx")); //$NON-NLS-1$
	  double cy = Double.parseDouble(ellipseElement.getAttribute("cy")); //$NON-NLS-1$
	  double rx = Double.parseDouble(ellipseElement.getAttribute("rx")); //$NON-NLS-1$
	  double ry = Double.parseDouble(ellipseElement.getAttribute("ry")); //$NON-NLS-1$
	  Ellipse2D ellipseFigure = new Ellipse2D.Double(cx - rx , cy - ry,  rx * 2, ry * 2);
	  Rectangle2D rectFigure = (Rectangle2D)previousTransform.createTransformedShape(ellipseFigure).getBounds2D();
	  result.setX((int)rectFigure.getX());
	  result.setY((int)rectFigure.getY());
	  result.setWidth((int)rectFigure.getWidth());
	  result.setHeight((int)rectFigure.getHeight());
	  
	  CSSStyleDeclaration nodeStyle = ((SVGSVGElement)svgDocument.getDocumentElement()).getComputedStyle(ellipseElement, null);  
	  Color backgroundColor = getColor(nodeStyle, "fill"); //$NON-NLS-1$
	  if (backgroundColor != null) {
		  result.setBackcolor(backgroundColor);
	  }
	  
	  float strokeWidth = getStrokeWidth(nodeStyle);
	  result.getLinePen().setLineWidth(strokeWidth);
	  Color strokeColor = getColor(nodeStyle, "stroke"); //$NON-NLS-1$
	  if (strokeColor != null) {
		  result.getLinePen().setLineColor(strokeColor);
	  }
	  
	  return result;
  }
  
  private List<JRDesignElement> parsePath(SVGOMPathElement pathElement, AffineTransform previousTransform) {
	  List<JRDesignElement> result = new ArrayList<>();
	  AffineTransform newTransofrm = getTransofrm(pathElement);
	  previousTransform.concatenate(newTransofrm);
	  AWTPathProducer pathProducer = new AWTPathProducer();
	  PathParser pathParser = new PathParser();
	  pathParser.setPathHandler(pathProducer);
      pathParser.parse(pathElement.getAttribute("d")); //$NON-NLS-1$
	  Shape awtShape = pathProducer.getShape();
	  PathIterator iterator = awtShape.getPathIterator(previousTransform);
	  float[] lastCoords = null;
	  float[] firstCoords = null;
	  while(!iterator.isDone()) {
		  float[] coords = new float[6];
		  int segType = iterator.currentSegment(coords);
		  if (lastCoords != null) {
			  JRDesignLine line = new JRDesignLine();
			  double x1 = lastCoords[0];
			  double y1 = lastCoords[1];
			  double x2;
			  double y2;
			  if (segType == PathIterator.SEG_CLOSE) {
				  x2 = firstCoords[0];
				  y2 = firstCoords[1];
			  } else { 
				  x2 = coords[0];
				  y2 = coords[1];
			  }
			 int xLineStart;
			  int lineWdith;
			  LineDirectionEnum direction = null;
			  if (x1 < x2) {
				  xLineStart = (int)Math.round(x1);
				  lineWdith = (int)Math.round(x2 - x1);
			  } else {
				  xLineStart = (int)Math.round(x2);
				  lineWdith = (int)Math.round(x1 - x2);
			  }
			  int yLineStart;
			  int lineHeight;
			  if (y1 < y2) {
				  yLineStart = (int)Math.round(y1);
				  lineHeight = (int)Math.round(y2 - y1);
			  } else {
				  yLineStart = (int)Math.round(y2);
				  lineHeight = (int)Math.round(y1 - y2);
				  direction = LineDirectionEnum.BOTTOM_UP;
			  }
			  line.setX(xLineStart);
			  line.setY(yLineStart );
			  line.setWidth(lineWdith);
			  line.setHeight(lineHeight);
			  line.setDirection(direction);
			  result.add(line);
		  }
		  lastCoords = coords;
		  if (firstCoords == null) {
			  firstCoords = coords;
		  }
		  iterator.next();
	  }
	  return result;
  }
  
  private List<JRDesignElement> parseElement(SVGOMElement element, AffineTransform previousTransform) {
	  List<JRDesignElement> result = new ArrayList<>();
	  if (element instanceof SVGOMTextElement) {
		  List<JRDesignStaticText> texts = parseText((SVGOMTextElement)element, new AffineTransform(previousTransform));
		  result.addAll(texts);
	  } else if (element instanceof SVGOMRectElement) {;
		  JRDesignRectangle rect = parseRectangle((SVGOMRectElement)element, new AffineTransform(previousTransform));
		  if (rect != null) {
			  result.add(rect);
		  }
	  } else if (element instanceof SVGOMGElement) {
		  result.addAll(parseGroup((SVGOMGElement)element, new AffineTransform(previousTransform)));
	  } else if (element instanceof SVGOMImageElement) {
		  SVGOMImageElement imageNode = (SVGOMImageElement)element;
		  JRDesignImage image = parseImage(imageNode, new AffineTransform(previousTransform));
		  if (image != null) {
			  result.add(image);
		  }
	  } else if (element instanceof SVGOMEllipseElement) {
		  SVGOMEllipseElement ellipseNode = (SVGOMEllipseElement)element;
		  JRDesignEllipse ellipse = parseEllipse(ellipseNode, new AffineTransform(previousTransform));
		  if (ellipse != null) {
			  result.add(ellipse);
		  }
	  } else if (element instanceof SVGOMPathElement) {
		  List<JRDesignElement> lines = parsePath((SVGOMPathElement)element, new AffineTransform(previousTransform));
		  result.addAll(lines);
	  } else{
		  System.out.println("unhandled");
	  }
	  return result;
  }
  
  
  public List<JRDesignElement> parseGroup(SVGOMGElement group, AffineTransform previousTransofrm) {
	  AffineTransform newTransform = getTransofrm(group);
	  previousTransofrm.concatenate(newTransform);
	  NodeList nodes = group.getChildNodes();
      List<JRDesignElement> result = new ArrayList<>();
	  for (int i = 0;  i < nodes.getLength();  i++) {
          Node node = nodes.item(i);
          if (node instanceof SVGOMElement) {
        	  result.addAll(parseElement((SVGOMElement)node, previousTransofrm));
          }
      }
	  return result;
  }
  
  
  /**
   * Finds all the path nodes and converts them to MetaPost code.
   */
  public List<JRDesignElement> run() {
      SVGOMSVGElement rootElement = getSVGDocumentRoot();
      NodeList nodes = rootElement.getChildNodes();
      AffineTransform startingStransform = new AffineTransform();
      List<JRDesignElement> result = new ArrayList<>();
      for (int i = 0;  i < nodes.getLength();  i++) {
          Node node = nodes.item(i);
          if (node instanceof SVGOMGElement) {
        	  result.addAll(parseElement((SVGOMGElement)node, startingStransform));
          }
      }
      int minX = Integer.MAX_VALUE;
      int minY = Integer.MAX_VALUE;
      for(JRDesignElement element : result) {
    	  if (element.getX() < minX) {
    		  minX = element.getX();
    	  }
    	  if (element.getY() < minY) {
    		  minY = element.getY();
    	  }
      }
      for(JRDesignElement element : result) {
    	  element.setX(element.getX() - minX);
    	  element.setY(element.getY() - minY);
      }
      return result;
  }

  /**
   * Returns a list of elements in the SVG document with names that
   * match PATH_ELEMENT_NAME.
   * 
   * @return The list of "path" elements in the SVG document.
   */
/*  private NodeList getPathElements() {
    return getSVGDocumentRoot().getElementsByTagName( PATH_ELEMENT_NAME );
  }
*/
  /**
   * Returns an SVGOMSVGElement that is the document's root element.
   * 
   * @return The SVG document typecast into an SVGOMSVGElement.
   */
  private SVGOMSVGElement getSVGDocumentRoot() {
    return (SVGOMSVGElement)getSVGDocument().getDocumentElement();
  }

  /**
   * This will set the document to parse. This method also initializes
   * the SVG DOM enhancements, which are necessary to perform SVG and CSS
   * manipulations. The initialization is also required to extract information
   * from the SVG path elements.
   *
   * @param document The document that contains SVG content.
   */
  public void setSVGDocument( Document document ) {
    initSVGDOM( document );
    this.svgDocument = document;
  }

  /**
   * Returns the SVG document parsed upon instantiating this class.
   * 
   * @return A valid, parsed, non-null SVG document instance.
   */
  public Document getSVGDocument() {
    return this.svgDocument;
  }

  /**
   * Enhance the SVG DOM for the given document to provide CSS- and SVG-specific
   * DOM interfaces.
   * 
   * @param document The document to enhance.
   * @link http://wiki.apache.org/xmlgraphics-batik/BootSvgAndCssDom
   */
  private void initSVGDOM( Document document ) {
    UserAgent userAgent = new UserAgentAdapter();
    DocumentLoader loader = new DocumentLoader( userAgent );
    BridgeContext bridgeContext = new BridgeContext( userAgent, loader );
    bridgeContext.setDynamicState( BridgeContext.DYNAMIC );

    // Enable CSS- and SVG-specific enhancements.
    (new GVTBuilder()).build( bridgeContext, document );
  }

  /**
   * Use the SAXSVGDocumentFactory to parse the given URI into a DOM.
   * 
   * @param uri The path to the SVG file to read.
   * @return A Document instance that represents the SVG file.
   * @throws Exception The file could not be read.
   */
  private Document createSVGDocument( String uri ) throws IOException {
    String parser = XMLResourceDescriptor.getXMLParserClassName();
    SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory( parser );
    return factory.createDocument( uri );
  }
}