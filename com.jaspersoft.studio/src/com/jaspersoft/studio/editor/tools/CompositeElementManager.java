/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.client.utils.URIBuilder;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.jaspersoft.studio.ConfigurationManager;
import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.callout.MCallout;
import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.editor.defaults.CustomStyleResolver;
import com.jaspersoft.studio.editor.defaults.DefaultManager;
import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.editor.tools.ICompositeElementModifyListener.OPERATION_TYPE;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.plugin.IPaletteContributor;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.util.Pair;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRChild;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRXmlUtils;
import net.sf.jasperreports.engine.xml.JRXmlDigesterFactory;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 * The composite elements manager allow to load and save composite elements. Essentially the composite elements are set
 * of design elements with some properties already set inside. A custom composite element is so a report file, but to
 * avoid to mess up with the file association it use a different extension
 * 
 * @author Orlandin Marco
 *
 */
public class CompositeElementManager {

	/**
	 * The Composite Elements Manager is global and it is accessible only trough this class
	 */
	public static final CompositeElementManager INSTANCE = new CompositeElementManager();

	/**
	 * Template for the note that explain how to use the a composite element
	 */
	private static final String NOTE_TEMPLATE = "callouts.1.fg=0,0,0\ncallouts.1.text={0}\ncallouts.1.bounds={1},0,650,40\ncallouts.1.bg=255,255,0\n"; //$NON-NLS-1$

	/**
	 * Storage name for the defined composite elements
	 */
	private static final String ELEMENTS_STORAGE_KEY = "compositeElements"; //$NON-NLS-1$

	/**
	 * name of the xml file containing the definitions of all the composite elements saved, a definition of each element
	 * is composed by name, description, path of the report containing its controlsf and the path to the icons to use the
	 * palette
	 */
	private static final String INDEX_FILE_NAME = "index.xml"; //$NON-NLS-1$

	/**
	 * Attribute name for each composite element entry in the xml file
	 */
	private static final String PROPERTY_NAME = "name"; //$NON-NLS-1$

	/**
	 * Attribute path of the icon (smaller one) for each composite element entry in the xml file
	 */
	private static final String PROPERTY_ICON_SMALL = "icon_small"; //$NON-NLS-1$

	/**
	 * Attribute path of the icon (bigger one) for each composite element entry in the xml file
	 */
	private static final String PROPERTY_ICON_BIG = "icon_big"; //$NON-NLS-1$

	/**
	 * Attribute description for each composite element entry in the xml file
	 */
	private static final String PROPERTY_DESCRIPTION = "description"; //$NON-NLS-1$

	/**
	 * Attribute palette group for each composite element entry in the xml file
	 */
	private static final String PROPERTY_GROUP = "group_id"; //$NON-NLS-1$

	/**
	 * Key for the property that list all the resources need by the composite element
	 */
	private static final String REQUIRED_RESOURCES = "requiredResources"; //$NON-NLS-1$

	/**
	 * Attribute path of the report for each composite element entry in the xml file
	 */
	private static final String PROPERTY_PATH = "path"; //$NON-NLS-1$

	/**
	 * Name of the tag for each composite element entry in the xml file
	 */
	private static final String XML_TAG_NAME = "compositeElement"; //$NON-NLS-1$

	/**
	 * The default extension of the file where the content of the composite element is saved
	 */
	public static final String COMPOSITE_ELEMENT_EXTENSION = ".jssce"; //$NON-NLS-1$

	/**
	 * List of the available composite elements
	 */
	private List<MCompositeElement> availableElements = new ArrayList<MCompositeElement>();

	/**
	 * Map to keep cached a composite element definition once its jssce file is loaded. The key is the path of the element
	 * definition, the value is a band that contains all its components
	 */
	private HashMap<String, Pair<JRBand, JasperDesign>> cachedElementssMap = new HashMap<String, Pair<JRBand, JasperDesign>>();

	/**
	 * List of listeners used to notify when the composite elements set changes
	 */
	private List<ICompositeElementModifyListener> listeners = new ArrayList<ICompositeElementModifyListener>();

	/**
	 * Resource listener used to see when a composite element is changed, and when this happen remove it from the cache to
	 * have it reloaded updated
	 */
	private IResourceChangeListener resourceDeletedListener = new IResourceChangeListener() {

		@Override
		public void resourceChanged(IResourceChangeEvent event) {
			List<IFile> resourcesDeleted = new ArrayList<IFile>();
			if (event.getType() == IResourceChangeEvent.POST_CHANGE && !cachedElementssMap.isEmpty()) {
				iterateResourceDelta(event.getDelta(), resourcesDeleted);
				for (IFile resource : resourcesDeleted) {
					String resourceString = resource.getRawLocation().toOSString();
					cachedElementssMap.remove(resourceString);
				}
			}
		}
	};

	/**
	 * Constructor, since it's private the class can be only accessed by the INSTANCE method
	 */
	private CompositeElementManager() {
		loadElements();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceDeletedListener);
	};

	/**
	 * Return a list of all the available compoisite elements
	 * 
	 * @return a not null list of composite elements
	 */
	public List<MCompositeElement> getAvailableElements() {
		return availableElements;
	}

	/**
	 * Check if a name for a composite element is already used by another one
	 * 
	 * @param name
	 *          a not null name
	 * @return true if the name is already in use, false otherwise
	 */
	public boolean isNameAlreadyUsed(String name) {
		for (MCompositeElement element : availableElements) {
			if (element.getName().equals(name))
				return true;
		}
		return false;
	}

	/**
	 * Export a list of composite element by copying and moving its resources into the target folder, also a partial index
	 * file is generated in the same folder for the moved elements
	 * 
	 * @param elementsToExport
	 *          a not null list of the composite elements that will be exported
	 * @param targetFolder
	 *          the folder where all the exported files will be placed
	 */
	public void exportCompositeElement(List<MCompositeElement> elementsToExport, File targetFolder) {
		// create the new index file
		File indexFile = new File(targetFolder, INDEX_FILE_NAME);
		Document document = null;
		Element root = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// Add the new entry on the index
		FileOutputStream oStream = null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.newDocument();
			root = document.createElement("elements"); //$NON-NLS-1$
			document.appendChild(root);
			String baseFolderName = "CompositeElement";
			int index = 1;
			for (MCompositeElement elementToExport : elementsToExport) {
				
				//Create the folder for this composite element
				File destinationFolder = new File(targetFolder, baseFolderName + index);
				destinationFolder.mkdirs();
				index++;
				
				File elementDefinition = new File(elementToExport.getPath());
				if (elementDefinition.exists()) {
					FileUtils.copyFile(elementDefinition, new File(destinationFolder, elementDefinition.getName()));

					boolean hasSmallIcon = false;
					String iconSmallPath = elementToExport.getIconPathSmall();
					if (iconSmallPath != null) {
						File iconSmallFile = new File(iconSmallPath);
						if (iconSmallFile.exists()) {
							FileUtils.copyFile(iconSmallFile, new File(destinationFolder, iconSmallFile.getName()));
							hasSmallIcon = true;
						}
					}

					boolean hasBigIcon = false;
					String iconBigPath = elementToExport.getIconPathBig();
					if (iconBigPath != null) {
						File iconBigFile = new File(iconBigPath);
						if (iconBigFile.exists()) {
							FileUtils.copyFile(iconBigFile, new File(destinationFolder, iconBigFile.getName()));
							hasBigIcon = true;
						}
					}

					// copy the resources
					File elementResourceDir = new File(elementDefinition.getParentFile(), elementToExport.getName());
					if (elementResourceDir.exists()) {
						try {
							FileUtils.copyDirectory(elementResourceDir, new File(destinationFolder, elementResourceDir.getName()));
						} catch (IOException e) {
							e.printStackTrace();
							JaspersoftStudioPlugin.getInstance().logError(e);
						}
					}

					try {
						Element newNode = document.createElement(XML_TAG_NAME);
						newNode.setAttribute(PROPERTY_NAME, elementToExport.getName());
						newNode.setAttribute(PROPERTY_PATH, destinationFolder.getName() + "/" + elementDefinition.getName());
						newNode.setAttribute(PROPERTY_DESCRIPTION, elementToExport.getDescription());
						newNode.setAttribute(PROPERTY_GROUP, elementToExport.getGroupId());
						if (hasSmallIcon) {
							newNode.setAttribute(PROPERTY_ICON_SMALL, destinationFolder.getName() + "/" + new File(iconSmallPath).getName());
						}
						if (hasBigIcon) {
							newNode.setAttribute(PROPERTY_ICON_BIG, destinationFolder.getName() + "/" + new File(iconBigPath).getName());
						}
						root.appendChild(newNode);
					} catch (Exception ex) {
						ex.printStackTrace();
						JaspersoftStudioPlugin.getInstance().logError(ex);
					}
				}
			}
			// just to be sure if an index is already present delete it
			indexFile.delete();
			// Write the file only if there s at least an entry
			indexFile.createNewFile();
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			oStream = new FileOutputStream(indexFile);
			StreamResult result = new StreamResult(oStream);
			indexFile.createNewFile();
			transformer.transform(source, result);
		} catch (Exception ex) {
			ex.printStackTrace();
			JaspersoftStudioPlugin.getInstance().logError(ex);
		} finally {
			net.sf.jasperreports.eclipse.util.FileUtils.closeStream(oStream);
		}
	}

	/**
	 * Load the list of composite elements stored into a specific folder, used when the elements are imported from an
	 * external location
	 * 
	 * @param contentFolder
	 *          the folder where the elements to import are contained
	 * @return a not null list of elements found
	 */
	public List<MCompositeElement> loadCompositeElements(File contentFolder) {
		File indexFile = new File(contentFolder, INDEX_FILE_NAME);
		List<MCompositeElement> elementsFound = new ArrayList<MCompositeElement>();
		if (indexFile != null && indexFile.exists()) {
			try {
				String xmlContent = readFile(indexFile);
				Document document = JRXmlUtils.parse(new InputSource(new StringReader(xmlContent)));

				NodeList adapterNodes = document.getElementsByTagName(XML_TAG_NAME);
				for (int i = 0; i < adapterNodes.getLength(); ++i) {
					try {
						Node adapterNode = adapterNodes.item(i);
						if (adapterNode.getNodeType() == Node.ELEMENT_NODE
								&& adapterNode.getAttributes().getNamedItem(PROPERTY_NAME) != null) {
							MCompositeElement loadedElement = createElementFromNode(adapterNode, contentFolder);
							if (loadedElement != null) {
								elementsFound.add(loadedElement);
							}
						}
					} catch (Exception ex) {
						ex.printStackTrace();
						JaspersoftStudioPlugin.getInstance().logError(ex);
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JaspersoftStudioPlugin.getInstance().logError(ex);
			}
		}
		return elementsFound;
	}

	/**
	 * Delete a composite element. This both remove all the physical of the composite element resources from the disk and
	 * its entry on the index file. It also notify the listeners that the set of elements is changed
	 * 
	 * @param elementToRemove
	 *          a not null model of the composite element to remove
	 */
	public void deleteCompositeElement(MCompositeElement elementToRemove) {
		cachedElementssMap.remove(elementToRemove.getPath());
		availableElements.remove(elementToRemove);

		// Update the index file
		File indexFile = ConfigurationManager.getStorageResource(ELEMENTS_STORAGE_KEY, INDEX_FILE_NAME);
		if (indexFile.exists()) {
			try {
				String xmlContent = readFile(indexFile);
				Document document = JRXmlUtils.parse(new InputSource(new StringReader(xmlContent)));
				NodeList adapterNodes = document.getElementsByTagName(XML_TAG_NAME);
				for (int i = 0; i < adapterNodes.getLength(); ++i) {
					try {
						Node adapterNode = adapterNodes.item(i);
						if (adapterNode.getNodeType() == Node.ELEMENT_NODE
								&& adapterNode.getAttributes().getNamedItem(PROPERTY_NAME) != null) {
							String name = adapterNode.getAttributes().getNamedItem(PROPERTY_NAME).getNodeValue();
							if (name.equals(elementToRemove.getName())) {
								document.getDocumentElement().removeChild(adapterNode);
								break;
							}
						}
					} catch (Exception ex) {
						ex.printStackTrace();
						JaspersoftStudioPlugin.getInstance().logError(ex);
					}
				}
				indexFile.delete();
				if (adapterNodes.getLength() > 0) {
					// Write the file only if there s at least an entry
					indexFile.createNewFile();
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(document);
					StreamResult result = new StreamResult(new FileOutputStream(indexFile));
					indexFile.createNewFile();
					transformer.transform(source, result);
				}
				deleteElementResources(elementToRemove);
			} catch (Exception ex) {
				ex.printStackTrace();
				JaspersoftStudioPlugin.getInstance().logError(ex);
			}
		} else {
			// Index file not available, delete the resources if they are found
			deleteElementResources(elementToRemove);
		}
		// Notify the change to the listeners
		firePropertyChange(elementToRemove, null, OPERATION_TYPE.DELETE);
	}

	/**
	 * Check recursively all the resources need by the elements inside the composite element. At the moment the only
	 * searched resources are the images. When a resource is found then it is copied inside the storage folder for this
	 * composite element and in the element inside the composite element the path is updated to point the correct location
	 * 
	 * @param newElement
	 *          the element that is actually inspecting
	 * @param jConfig
	 *          a not null jasper reports configuration of the source report
	 * @param dataset
	 *          the dataset of the element that is currently added part of the composite element
	 * @param band
	 *          the band where the components of the composite element are placed
	 * @param resourcesDir
	 *          the directory where the resources of the composite element will be placed
	 * @param foundResources
	 *          the set of resources found during the various iteration, used to avoid to add twice or more the same
	 *          resource
	 */
	private void checkResources(JRChild newElement, JasperReportsConfiguration jConfig, JRDesignDataset dataset,
			JRBand band, File resourcesDir, HashSet<String> foundResources) {
		if (newElement instanceof JRDesignImage) {
			JRExpression exp = ((JRDesignImage) newElement).getExpression();
			String expression = ExpressionUtil.cachedExpressionEvaluationString(exp, jConfig, dataset);
			if (expression != null) {
				//Handle the case of a uri pointing to a file
				URI resourceURI = null;
				try{
					resourceURI = new URI(expression);
				} catch (Exception ex){
					//If it is not a uuri try to build it with the file protocol
					URIBuilder builder = new URIBuilder();
					builder.setScheme("file");
					builder.setPath(expression);
					try {
						resourceURI = builder.build();
					} catch (URISyntaxException e) {
					}
				}
				if (resourceURI != null) {
					resourcesDir.mkdir();
					File dest = new File(resourcesDir, FilenameUtils.getName(resourceURI.getPath()));
					JRDesignImage newImage = (JRDesignImage) newElement;
					try {
						if (!dest.exists()) {
							FileUtils.copyURLToFile(resourceURI.toURL(), dest);
						}
						newImage.setExpression(new JRDesignExpression("\"" + dest.getAbsolutePath() + "\"")); //$NON-NLS-1$ //$NON-NLS-2$
						String requiredResources = band.getPropertiesMap().getProperty(REQUIRED_RESOURCES);
						if (!foundResources.contains(dest.getName())) {
							if (requiredResources == null) {
								band.getPropertiesMap().setProperty(REQUIRED_RESOURCES, dest.getName());
							} else {
								requiredResources += ";" + dest.getName(); //$NON-NLS-1$
								band.getPropertiesMap().setProperty(REQUIRED_RESOURCES, requiredResources);
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
						JaspersoftStudioPlugin.getInstance().logError(e);
					}
				}
			}
		}
		if (newElement instanceof JRElementGroup) {
			for (JRChild childElement : ((JRElementGroup) newElement).getChildren()) {
				checkResources(childElement, jConfig, dataset, band, resourcesDir, foundResources);
			}
		}
	}

	/**
	 * Create a JasperDesign for the composite element starting from the list of the elements that define it
	 * 
	 * @param name
	 *          the name of the composite element
	 * @param elementContents
	 *          a not null list of MGraphicalElement, that are the elements that will be used to create the new composite
	 *          element
	 * @param resourcesDir
	 *          the directory where the resources of the composite element will be placed, like for example images
	 * @return a not null jasperdesign with the elements that define the composite element in the title band
	 */
	private JasperDesign createDesign(String name, List<Object> elementContents, File resourcesDir) {
		Integer leftOffset = null;
		Integer topOffset = null;
		for (Object element : elementContents) {
			MGraphicElement gElement = (MGraphicElement) element;
			int elementX = gElement.getValue().getX();
			int elementY = gElement.getValue().getY();
			if (leftOffset == null || leftOffset > elementX) {
				leftOffset = elementX;
			}
			if (topOffset == null || topOffset > elementY) {
				topOffset = elementY;
			}
		}
		JasperDesign jd = new JasperDesign();
		jd.setJasperReportsContext(JasperReportsConfiguration.getDefaultInstance());

		jd.setName(name);
		jd.setColumnFooter(null);
		jd.setColumnHeader(null);
		jd.setPageFooter(null);
		jd.setPageHeader(null);
		jd.setSummary(null);
		jd.setBackground(null);
		jd.setLeftMargin(0);
		jd.setRightMargin(0);
		jd.setTopMargin(0);
		jd.setBottomMargin(0);
		JRDesignBand band = new JRDesignBand();
		int maxHeight = 0;
		int maxWidth = 0;
		for (Object element : elementContents) {
			MGraphicElement mOriginalElement = (MGraphicElement) element;
			JRDesignElement originalElement = mOriginalElement.getValue();
			JRDesignElement newElement = (JRDesignElement) originalElement.clone();

			// Merge the styles attributes
			CustomStyleResolver.copyInheritedAttributes(mOriginalElement, newElement);
			newElement.setStyle(null);
			newElement.setStyleNameReference(null);

			newElement.setX(originalElement.getX() - leftOffset);
			newElement.setY(originalElement.getY() - topOffset);

			// Calculate the band size
			if (newElement.getWidth() + newElement.getX() > maxWidth) {
				maxWidth = newElement.getWidth() + newElement.getX();
			}
			if (newElement.getHeight() + newElement.getY() > maxHeight) {
				maxHeight = newElement.getHeight() + newElement.getY();
			}

			// Copy the resources
			JRDesignDataset jrd = ModelUtils.getFirstDatasetInHierarchy(mOriginalElement);
			checkResources(newElement, mOriginalElement.getJasperConfiguration(), jrd, band, resourcesDir,
					new HashSet<String>());

			band.addElement(newElement);
		}
		jd.setTitle(band);
		band.setHeight(maxHeight);
		jd.setPageWidth(maxWidth);
		String noteValue = MessageFormat.format(NOTE_TEMPLATE,
				new Object[] { Messages.ToolManager_noteText, maxWidth + 5 });
		jd.setProperty(MCallout.PROP_CALLOUT, noteValue); // $NON-NLS-1$

		return jd;
	}

	/**
	 * Add a composite element and search for resources needed by it, like images and copy that resources also inside the
	 * storage
	 * 
	 * @param name
	 *          the name of the composite element, must be unique
	 * @param description
	 *          the description of the composite element
	 * @param groupID
	 *          the id of the palette group where this element should be placed
	 * @param iconSmall
	 *          the small icon for the composite element, typically 16x16
	 * @param iconBig
	 *          the big icon for the composite element, typically 32x32
	 * @param elementContents
	 *          a not null list of MGraphicalElement, that are the elements that will be used to create the new composite
	 *          element
	 */
	public void addCompositeElement(String name, String description, String groupID, ImageData iconSmall,
			ImageData iconBig, List<Object> elementContents) {
		File storage = ConfigurationManager.getStorage(ELEMENTS_STORAGE_KEY);
		File resourcesDir = new File(storage, name);
		JasperDesign jd = createDesign(name, elementContents, resourcesDir);
		// If the addition went good it search also for the resources
		if (!addCompositeElement(name, description, groupID, iconSmall, iconBig, jd)) {
			try {
				FileUtils.deleteDirectory(resourcesDir);
			} catch (IOException e) {
				e.printStackTrace();
				JaspersoftStudioPlugin.getInstance().logError(e);
			}
		}
	}

	/**
	 * Return the name used for the icon image 32x32. The name contains a timestamp since the palette system cache the
	 * images, but in the composite elements the icon can be edited, so each time need to have a different name to avoid
	 * to reuse the one in the cache
	 * 
	 * @param compositeElementName
	 *          the name of the composite element
	 * @return an unique name for the big icon of that composite element
	 */
	private String getImageBigName(String compositeElementName) {
		return compositeElementName + "-big" + System.currentTimeMillis() + ".png"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Return the name used for the icon image 16x16. The name contains a timestamp since the palette system cache the
	 * images, but in the composite elements the icon can be edited, so each time need to have a different name to avoid
	 * to reuse the one in the cache
	 * 
	 * @param compositeElementName
	 *          the name of the composite element
	 * @return an unique name for the small icon of that composite element
	 */
	private String getImageSmallName(String compositeElementName) {
		return compositeElementName + "-small" + System.currentTimeMillis() + ".png"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Edit a composite element, changing its metadata
	 * 
	 * @param oldElement
	 *          the element to edit
	 * @param newName
	 *          the name of the composite element, must be unique
	 * @param newDescription
	 *          the description of the composite element
	 * @param newGroup
	 *          the id of the palette group where this element should be placed
	 * @param iconSmall
	 *          the small icon for the composite element, typically 16x16
	 * @param iconBig
	 *          the big icon for the composite element, typically 32x32
	 */
	public void editCompositeElement(MCompositeElement oldElement, String newName, String newDescription, String newGroup,
			ImageData iconSmall, ImageData iconBig) {
		File storage = ConfigurationManager.getStorage(ELEMENTS_STORAGE_KEY);
		try {
			String oldName = oldElement.getName();

			// delete the old images if present
			if (oldElement.getIconPathSmall() != null) {
				File oldIconSmall = new File(oldElement.getIconPathSmall()); // $NON-NLS-1$
				oldIconSmall.delete();
			}

			if (oldElement.getIconPathBig() != null) {
				File oldIconBig = new File(oldElement.getIconPathBig()); // $NON-NLS-1$
				oldIconBig.delete();
			}

			// Write the report on disk
			String oldReportName = oldName + COMPOSITE_ELEMENT_EXTENSION;
			String newReportName = newName + COMPOSITE_ELEMENT_EXTENSION;
			File oldReportFile = new File(storage, oldReportName);
			if (oldReportFile.exists()) {
				oldReportFile.renameTo(new File(storage, newReportName));
			}

			// write the 16x16 icon
			String imageSmallName = getImageSmallName(newName);
			if (iconSmall != null) {
				writeImage(new File(storage, imageSmallName), iconSmall);
			}

			// write the 32x32 icon
			String imageBigName = getImageBigName(newName);
			if (iconBig != null) {
				writeImage(new File(storage, imageBigName), iconBig);
			}

			// Load the index file
			File indexFile = new File(storage, INDEX_FILE_NAME);
			if (indexFile.exists()) {
				String xmlContent = readFile(indexFile);
				Document document = JRXmlUtils.parse(new InputSource(new StringReader(xmlContent)));
				NodeList adapterNodes = document.getElementsByTagName(XML_TAG_NAME);
				for (int i = 0; i < adapterNodes.getLength(); ++i) {
					try {
						Node adapterNode = adapterNodes.item(i);
						if (adapterNode.getNodeType() == Node.ELEMENT_NODE
								&& adapterNode.getAttributes().getNamedItem(PROPERTY_NAME) != null) {
							String name = adapterNode.getAttributes().getNamedItem(PROPERTY_NAME).getNodeValue();
							if (name.equals(oldName)) {
								Element element = (Element) adapterNode;
								element.setAttribute(PROPERTY_NAME, newName);
								element.setAttribute(PROPERTY_DESCRIPTION, newDescription);
								element.setAttribute(PROPERTY_GROUP, newGroup);
								element.setAttribute(PROPERTY_PATH, newReportName);
								if (iconSmall != null) {
									element.setAttribute(PROPERTY_ICON_SMALL, imageSmallName);
								} else {
									element.removeAttribute(PROPERTY_ICON_SMALL);
								}
								if (iconBig != null) {
									element.setAttribute(PROPERTY_ICON_BIG, imageBigName);
								} else {
									element.removeAttribute(PROPERTY_ICON_BIG);
								}
								break;
							}
						}
					} catch (Exception ex) {
						ex.printStackTrace();
						JaspersoftStudioPlugin.getInstance().logError(ex);
					}
				}
				indexFile.delete();
				if (adapterNodes.getLength() > 0) {
					// Write the file only if there s at least an entry
					indexFile.createNewFile();
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(document);
					StreamResult result = new StreamResult(new FileOutputStream(indexFile));
					indexFile.createNewFile();
					transformer.transform(source, result);
				}

				// Remove the old composite element from the cache
				cachedElementssMap.remove(oldElement.getPath());
				loadElements();
				firePropertyChange(oldElement, getElemenetByName(newName), OPERATION_TYPE.EDIT);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JaspersoftStudioPlugin.getInstance().logError(ex);
		}
	}

	/**
	 * Search in the loaded composite element one with a specific name and return it
	 * 
	 * @param name
	 *          the name of the element
	 * @return the element with the searched name or null if it can't be found
	 */
	public MCompositeElement getElemenetByName(String name) {
		for (MCompositeElement element : availableElements) {
			if (element.getName().equals(name)) {
				return element;
			}
		}
		return null;
	}

	/**
	 * Add a composite elements to the set of elements and also take the content of a folder and move and rename it to be
	 * used as resource folder of the added element
	 * 
	 * @param name
	 *          the name of the composite element, must be unique
	 * @param description
	 *          the description of the composite element
	 * @param iconSmall
	 *          the small icon for the composite element, typically 16x16
	 * @param iconBig
	 *          the big icon for the composite element, typically 32x32
	 * @param jd
	 *          the jasperdesign containing the element of the composite element. The element must be contained in the
	 *          title band
	 * @param the
	 *          content of this folder will be used as resources for the new element, if null or not existing this is not
	 *          used
	 * @return true if the operation succeed, false otherwise
	 */
	public boolean addCompositeElement(String name, String description, String groupID, ImageData iconSmall,
			ImageData iconBig, JasperDesign jd, File resourceDir) {
		boolean result = addCompositeElement(name, description, groupID, iconSmall, iconBig, jd);
		if (result && resourceDir != null && resourceDir.exists()) {
			try {
				FileUtils.copyDirectory(resourceDir, getResourceDir(name));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return result;
	}

	/**
	 * Add a new composite element to the set and notify the listeners that the set of composite elements is changed
	 * 
	 * @param name
	 *          the name of the composite element, must be unique
	 * @param description
	 *          the description of the composite element
	 * @param iconSmall
	 *          the small icon for the composite element, typically 16x16
	 * @param iconBig
	 *          the big icon for the composite element, typically 32x32
	 * @param jd
	 *          the jasperdesign containing the element of the composite element. The element must be contained in the
	 *          title band
	 * @return true if the operation succeed, false otherwise
	 */
	public boolean addCompositeElement(String name, String description, String groupID, ImageData iconSmall,
			ImageData iconBig, JasperDesign jd) {
		Assert.isTrue(!isNameAlreadyUsed(name), "The name must be unique"); //$NON-NLS-1$
		File storage = ConfigurationManager.getStorage(ELEMENTS_STORAGE_KEY);
		try {
			// Write the report on disk
			String reportName = name + COMPOSITE_ELEMENT_EXTENSION;
			File reportFile = new File(storage, reportName);
			String contents = JRXmlWriterHelper.writeReport(JasperReportsConfiguration.getDefaultInstance(), jd,
					JRXmlWriterHelper.LAST_VERSION);
			BufferedWriter writer = new BufferedWriter(new FileWriter(reportFile));
			writer.write(contents);
			writer.close();

			// write the 16x16 icon
			String imageSmallName = getImageSmallName(name);
			if (iconSmall != null) {
				writeImage(new File(storage, imageSmallName), iconSmall);
			}

			// write the 32x32 icon
			String imageBigName = getImageBigName(name);
			if (iconBig != null) {
				writeImage(new File(storage, imageBigName), iconBig);
			}

			// Load the index file
			File indexFile = new File(storage, INDEX_FILE_NAME);
			Document document = null;
			Element root = null;
			if (indexFile.exists()) {
				String xmlContent = readFile(indexFile);
				document = JRXmlUtils.parse(new InputSource(new StringReader(xmlContent)));
				root = document.getDocumentElement();
			} else {
				// No index file defined, must create it
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				document = builder.newDocument();
				root = document.createElement("elements"); //$NON-NLS-1$
				document.appendChild(root);
			}

			// Add the new entry on the index
			FileOutputStream oStream = null;
			try {
				Element newNode = document.createElement(XML_TAG_NAME);
				newNode.setAttribute(PROPERTY_NAME, name);
				newNode.setAttribute(PROPERTY_PATH, reportName);
				newNode.setAttribute(PROPERTY_DESCRIPTION, description);
				newNode.setAttribute(PROPERTY_GROUP, groupID);
				if (iconSmall != null) {
					newNode.setAttribute(PROPERTY_ICON_SMALL, imageSmallName);
				}
				if (iconBig != null) {
					newNode.setAttribute(PROPERTY_ICON_BIG, imageBigName);
				}
				root.appendChild(newNode);
				indexFile.delete();
				// Write the file only if there s at least an entry
				indexFile.createNewFile();
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(document);
				oStream = new FileOutputStream(indexFile);
				StreamResult result = new StreamResult(oStream);
				indexFile.createNewFile();
				transformer.transform(source, result);
				MCompositeElement newElement = createElementFromNode(newNode);
				availableElements.add(newElement);
				firePropertyChange(null, newElement, OPERATION_TYPE.ADD);
			} catch (Exception ex) {
				ex.printStackTrace();
				JaspersoftStudioPlugin.getInstance().logError(ex);
			} finally {
				net.sf.jasperreports.eclipse.util.FileUtils.closeStream(oStream);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JaspersoftStudioPlugin.getInstance().logError(ex);
			return false;
		}
		return true;
	}

	/**
	 * Get the command used to create the requested composite element inside the report
	 * 
	 * @param parent
	 *          the parent of the composite element
	 * @param element
	 *          the composite element to create
	 * @param location
	 *          the location where the composite element should be created
	 * @param newIndex
	 *          the index of the composite element
	 * @return the command to create the composite element inside the parent in the requested location
	 */
	public Command getCommand(ANode parent, MCompositeElement element, Rectangle location, int newIndex) {
		JSSCompoundCommand cmd = new JSSCompoundCommand(parent) {

			@Override
			public void execute() {
				// Before to execute the commands disable the use of defaults, since
				// the custom composite elements are not affected by defaults
				boolean defaultValue = DefaultManager.INSTANCE.isDisabled();
				// DefaultManager.INSTANCE.setDisabled(true);
				super.execute();
				DefaultManager.INSTANCE.setDisabled(defaultValue);
			}

		};
		Pair<JRBand, JasperDesign> compositeElement = getElementContainer(element.getPath());
		JRBand elementContent = compositeElement.getKey();
		for (JRChild child : elementContent.getChildren()) {
			JRDesignElement designElement = (JRDesignElement) child;
			if (child instanceof JRDesignElement) {
				MGraphicElement model = new MGraphicElement();
				model.setValue(designElement.clone());
				Rectangle relativeLocation = null;
				// The location is null when dragging in the outline
				if (location == null) {
					relativeLocation = new Rectangle(0, 0, 0, 0);
				} else {
					relativeLocation = new Rectangle(location);
				}
				relativeLocation.x += designElement.getX();
				relativeLocation.y += designElement.getY();
				relativeLocation.width = designElement.getWidth();
				relativeLocation.height = designElement.getHeight();
				Command createCommand = OutlineTreeEditPartFactory.getCreateCommand(parent, model, relativeLocation, newIndex);
				if (createCommand != null) {
					cmd.add(createCommand);
				}
			}
		}
		// Look for the resources
		/*
		 * String resources = toolContainer.getPropertiesMap().getProperty(REQUIRED_RESOURCES); if (resources != null){
		 * String[] resArray = resources.split(";"); for(String res : resArray){
		 * 
		 * } } if (!requiredResources.isEmpty()){ IFile currentProjectFile = (IFile)
		 * parent.getJasperConfiguration().get(net.sf.jasperreports.eclipse.util.FileUtils.KEY_FILE); IProject project =
		 * currentProjectFile.getProject(); project.getfi }
		 */
		return cmd;
	}

	/**
	 * Add a listener that is notified when the composite elements set changes
	 * 
	 * @param listener
	 *          a not null listener
	 */
	public void addModifyListener(ICompositeElementModifyListener listener) {
		if (!listeners.contains(listener))
			listeners.add(listener);
	}

	/**
	 * Load the list of the composite element in the folder storage. Called when the class is loaded
	 */
	private void loadElements() {
		availableElements.clear();
		File indexFile = ConfigurationManager.getStorageResource(ELEMENTS_STORAGE_KEY, INDEX_FILE_NAME);
		if (indexFile != null && indexFile.exists()) {
			try {
				String xmlContent = readFile(indexFile);
				Document document = JRXmlUtils.parse(new InputSource(new StringReader(xmlContent)));

				NodeList adapterNodes = document.getElementsByTagName(XML_TAG_NAME);
				for (int i = 0; i < adapterNodes.getLength(); ++i) {
					try {
						Node adapterNode = adapterNodes.item(i);
						if (adapterNode.getNodeType() == Node.ELEMENT_NODE
								&& adapterNode.getAttributes().getNamedItem(PROPERTY_NAME) != null) {
							MCompositeElement loadedElement = createElementFromNode(adapterNode);
							if (loadedElement != null)
								availableElements.add(loadedElement);
						}
					} catch (Exception ex) {
						ex.printStackTrace();
						JaspersoftStudioPlugin.getInstance().logError(ex);
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JaspersoftStudioPlugin.getInstance().logError(ex);
			}
		}
	}

	/**
	 * From a node of the xml index file it build an MCopositeElement, the resources are searched inside a specified
	 * folder
	 * 
	 * @param adapterNode
	 *          a not null node
	 * @param locationFolder
	 *          the folder where the element data (definition, icons and resource folder) are searched
	 * @return an MCompositeElement build with the informations inside the node
	 */
	private static MCompositeElement createElementFromNode(Node adapterNode, File locationFolder) {
		String name = adapterNode.getAttributes().getNamedItem(PROPERTY_NAME).getNodeValue();
		String path = adapterNode.getAttributes().getNamedItem(PROPERTY_PATH).getNodeValue();
		File contentFile = new File(locationFolder, path);
		if (contentFile.exists()) {
			// Get the icons
			String absoluteIconPathSmall = null;
			String absoluteIconPathBig = null;
			Node iconNode = adapterNode.getAttributes().getNamedItem(PROPERTY_ICON_SMALL);
			String iconPath = iconNode != null ? iconNode.getNodeValue() : null;
			if (iconPath != null) {
				File resource = new File(locationFolder, iconPath);
				absoluteIconPathSmall = resource.getAbsolutePath();
			}
			iconNode = adapterNode.getAttributes().getNamedItem(PROPERTY_ICON_BIG);
			iconPath = iconNode != null ? iconNode.getNodeValue() : null;
			if (iconPath != null) {
				File resource = new File(locationFolder, iconPath);
				absoluteIconPathBig = resource.getAbsolutePath();
			}

			// get the description
			Node descriptionNode = adapterNode.getAttributes().getNamedItem(PROPERTY_DESCRIPTION);
			String description = descriptionNode != null && descriptionNode.getNodeValue() != null
					? descriptionNode.getNodeValue() : "A user defined composite element"; //$NON-NLS-1$

			// get the group id
			Node groupIDNode = adapterNode.getAttributes().getNamedItem(PROPERTY_GROUP);
			String groupID = groupIDNode != null ? groupIDNode.getNodeValue() : IPaletteContributor.KEY_COMMON_TOOLS;

			return new MCompositeElement(name, description, groupID, contentFile.getAbsolutePath(), absoluteIconPathSmall,
					absoluteIconPathBig);
		} else {
			return null;
		}
	}

	/**
	 * From a node of the xml index file it build an MCopositeElement, the resources are searched inside the composite
	 * elements storage
	 * 
	 * @param adapterNode
	 *          a not null node
	 * @return an MCompositeElement build with the informations inside the node
	 */
	private static MCompositeElement createElementFromNode(Node adapterNode) {
		File contentFolder = ConfigurationManager.getStorage(ELEMENTS_STORAGE_KEY);
		return createElementFromNode(adapterNode, contentFolder);
	}

	/**
	 * Write an image data on the disk as a PNG image
	 * 
	 * @param destination
	 *          the destination, must be not null
	 * @param image
	 *          the image to write, must be not null
	 */
	private void writeImage(File destination, ImageData image) {
		ImageLoader loader = new ImageLoader();
		loader.data = new ImageData[] { image };
		loader.save(destination.getAbsolutePath(), SWT.IMAGE_PNG);
	}

	/**
	 * Delete all the physical resources of a composite element, so it's images and its report file
	 * 
	 * @param elementToRemove
	 *          a not null composite element to remove
	 */
	private void deleteElementResources(MCompositeElement elementToRemove) {
		// Delete the physical resources
		File contentFile = new File(elementToRemove.getPath());
		contentFile.delete();

		if (elementToRemove.getIconPathSmall() != null) {
			File iconSmall = new File(elementToRemove.getIconPathSmall());
			iconSmall.delete();
		}

		if (elementToRemove.getIconPathBig() != null) {
			File iconBig = new File(elementToRemove.getIconPathBig());
			iconBig.delete();
		}

		// Delete laso the other resources
		File elementResourceDir = new File(contentFile.getParentFile(), elementToRemove.getName());
		if (elementResourceDir.exists()) {
			try {
				FileUtils.deleteDirectory(elementResourceDir);
			} catch (IOException e) {
				e.printStackTrace();
				JaspersoftStudioPlugin.getInstance().logError(e);
			}
		}
	}

	/**
	 * Read the xml source file and convert it into a string
	 * 
	 * @param path
	 *          the path of the file
	 * @return the XML file
	 */
	private static String readFile(File file) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			StringBuilder sb = new StringBuilder();

			while ((line = br.readLine()) != null) {
				sb.append(line.trim());
			}
			br.close();
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Load the report of a composite element file and return the title band, containing the component of the composite
	 * element
	 * 
	 * @param defaultFile
	 *          a not null report file containing the composite element definition
	 */
	private Pair<JRBand, JasperDesign> loadElementModel(File defaultFile) {
		InputStream in = null;
		Pair<JRBand, JasperDesign> result = null;
		try {
			in = new ByteArrayInputStream(FileUtils.readFileToByteArray(defaultFile));
			JasperReportsConfiguration jConfig = getDefaultJRConfig();
			JasperDesign design = new JRXmlLoader(jConfig, JRXmlDigesterFactory.createDigester(jConfig)).loadXML(in);
			jConfig.setJasperDesign(design);
			JRBand band = design.getTitle();
			result = new Pair<JRBand, JasperDesign>(band, design);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			net.sf.jasperreports.eclipse.util.FileUtils.closeStream(in);
		}
		return result;
	}

	/**
	 * Return the title band of the composite element definition identified by the provided path. If the band was already
	 * requested before and it is cached then it is returned from cache, otherwise it is loaded, returned and cached
	 * 
	 * @param path
	 *          the path of the composite element definition file, must be not null
	 * @return the band containing the controls of the composite element or null if there is an error loading them
	 */
	private Pair<JRBand, JasperDesign> getElementContainer(String path) {
		if (cachedElementssMap.containsKey(path)) {
			return cachedElementssMap.get(path);
		} else {
			File contentFile = new File(path);
			if (contentFile.exists()) {
				Pair<JRBand, JasperDesign> result = loadElementModel(contentFile);
				if (result != null) {
					cachedElementssMap.put(path, result);
					return result;
				}
			}
		}
		return null;
	}

	/**
	 * Return the default JasperReports configuration
	 * 
	 * @return a not null jasperreports configuration
	 */
	private JasperReportsConfiguration getDefaultJRConfig() {
		return new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), null);
	}

	/**
	 * Notify all the listeners that the set of composite elements is changed
	 * 
	 * @param newElement
	 *          the element that has changed inside the composite elements set
	 * @param operation
	 *          the operation on that element
	 */
	protected void firePropertyChange(MCompositeElement oldElement, MCompositeElement newElement,
			OPERATION_TYPE operation) {
		for (ICompositeElementModifyListener listener : listeners) {
			listener.elementChanged(oldElement, newElement, operation);
		}
	}

	/**
	 * Recursive method called when some resource changes, it search for edited composite elements inside the delta
	 * hierarchy
	 * 
	 * @param delta
	 *          actual level of the delta hierarchy
	 * @param editedResources
	 *          the list of edited resources actually found
	 */
	private void iterateResourceDelta(IResourceDelta delta, List<IFile> editedResources) {
		if (delta.getKind() == IResourceDelta.CHANGED
				&& delta.getResource().getName().toLowerCase().endsWith(COMPOSITE_ELEMENT_EXTENSION)
				&& delta.getResource() instanceof IFile) {
			editedResources.add((IFile) delta.getResource());
		}
		for (IResourceDelta affectedResource : delta.getAffectedChildren()) {
			iterateResourceDelta(affectedResource, editedResources);
		}
	}

	/**
	 * Return a resource folder for a specific element name, if the folder doesn't exist then it is created
	 * 
	 * @param the
	 *          element name for which the folder is requested
	 * @return an existing resource folder
	 */
	public File getResourceDir(String name) {
		File storage = ConfigurationManager.getStorage(ELEMENTS_STORAGE_KEY);
		File result = new File(storage, name);
		result.mkdirs();
		return result;
	}
}
