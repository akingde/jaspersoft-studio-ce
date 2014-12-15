define([], function () {

	 return function (instanceData) {	
		
	 			var svg = window.document.createElementNS("http://www.w3.org/2000/svg", "svg");
				svg.setAttribute("xmlns:xlink", "http://www.w3.org/1999/xlink");
				svg.setAttributeNS(null, "height",  instanceData.height);
				svg.setAttributeNS(null, "width",  instanceData.width);
				   
				var shape = window.document.createElementNS("http://www.w3.org/2000/svg", "rect");
				
				shape.setAttributeNS(null, "y", 0);
				shape.setAttributeNS(null, "x", 0);
				shape.setAttributeNS(null, "height",  instanceData.height);
				shape.setAttributeNS(null, "width",  instanceData.width);
				shape.setAttributeNS(null, "style", "stroke:#000000; fill: #ff0000");
				
				svg.appendChild(shape);
				
				window.document.getElementById(instanceData.id).appendChild(svg);
	 			
		
	};
		
});

