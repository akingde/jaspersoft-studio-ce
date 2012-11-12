----------------------------------------
IMPORTANTE NOTE: Actually there is no official support for the Eclipse 4.2 platform.
These instructions are meant to be used by the community in order to improve this support
and help the Jaspersoft Studio Team.

Please refer to the following links to obtain more info: 
Project forum: http://jasperforge.org/plugins/espforum/browse.php?group_id=400&forumid=254
Project tracker: http://jasperforge.org/projects/jaspersoftstudio/tracker/myview

Also use the forum to discuss about this topic and the tracker to submit bugs/patches that can
be useful to improve the code.
----------------------------------------
This folder is meant to contain all the necessary stuff to prepare an up-and-running
development environment where to build Jaspersoft Studio source code.
Here is a set of basic step you can take to have a fully working IDE.

1) 	Download an Eclipse Juno (4.2) for RCP and RAP developers
2)	Install SVN plugin Subclipse.
3) 	Use the juno42install.p2f file to have all the required features installed.
	You can do it by using the menu File->Import->Install->Install Software Items from File.
	Select the "juno38install.p2f" file and follow the steps.
	N.B: You should also import the deltapack for 4.2 in you current platform in order to be able
	to build the JSS product.
4)	Next step after the restart is to import the needed SVN projects.
	To do this just select the menu File->Import->Team->Team Project Set and use the
	specified "jss_teamprojectset.psf" file.
5)	After import, just build and start coding!