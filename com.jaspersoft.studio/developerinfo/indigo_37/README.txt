This folder is meant to contain all the necessary stuff to prepare an up-and-running
development environment where to build Jaspersoft Studio source code.
Here is a set of basic step you can take to have a fully working IDE.

1) 	Download an Eclipse Indigo (3.7.2) for RCP & RAP Developers.
2)	Install SVN plugin Subclipse.
3) 	Use the indigo37install.p2f file to have all the required features installed.
	You can do it by using the menu File->Import->Install->Install Software Items from File.
	Select the "indigo37install.p2f" file and follow the steps.
4)	Next step after the restart is to import the needed SVN projects.
	To do this just select the menu File->Import->Team->Team Project Set and use the
	specified "jss_teamprojectset.psf" file.
5)	After import, just build and start coding!