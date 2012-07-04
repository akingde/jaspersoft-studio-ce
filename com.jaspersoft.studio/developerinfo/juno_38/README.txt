This folder is meant to contain all the necessary stuff to prepare an up-and-running
development environment where to build Jaspersoft Studio source code.
Here is a set of basic step you can take to have a fully working IDE.

1) 	Download an Eclipse SDK Juno version 3.8. There is no official release for 3.8.
	However this version introduces various bug fixes to the existing Indigo version.
	Link: http://download.eclipse.org/eclipse/downloads/eclipse3x.html
2)	Install SVN plugin Subclipse.
3) 	Use the juno38install.p2f file to have all the required features installed.
	You can do it by using the menu File->Import->Install->Install Software Items from File.
	Select the "juno38install.p2f" file and follow the steps.
	This will install the following features:
	- Eclipse Platform SDK 3.8
	- Eclipse SDK 3.8
	- GEF SDK 3.8
	- Xtext SDK 2.3
	- Xtext JFace Integration 1.0.0
4)	You should now add the following plugins in order to compile everything:
	- Apache Xerces 2.9.0 related plugins: you can download everything you need from the
	Eclipse Orbit project download page. 
	It can be found here: http://download.eclipse.org/tools/orbit/downloads/
	If you perform a quick check you see that these are the needed bundles for version 2.9.0: 
		1. javax.xml (version 1.3.4)
		2. org.apache.xerces (version 2.9.0)
		3. org.apache.xml.resolver (version 1.2.0)
		4. org.apache.xml.serializer (version 2.7.1)
	More info on http://wiki.eclipse.org/Orbit/Xerces_in_Eclipse.
	You should also import the deltapack for 3.8 in you current platform in order to be able
	to build the JSS product.
		
5)	Next step after the restart is to import the needed SVN projects.
	To do this just select the menu File->Import->Team->Team Project Set and use the
	specified "jss_teamprojectset.psf" file.
6)	After import, just build and start coding!