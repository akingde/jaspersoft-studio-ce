define('${modulename}',['d3'], function (d3) {

	 return function (instanceData) {	
		
	 	var w = instanceData.width,
                    h = instanceData.height;

                var margin = 20;
                var diameter = Math.min(w,h) - margin;

		var svg = d3.select("#" + instanceData.id).insert("svg")
		    .attr("id", instanceData.id + "svg")
		    .attr("width", w)
		    .attr("height", h);
		    
		    
 		var circle = svg.append("circle")
                           .attr("cx", w/2)
                           .attr("cy", h/2)
                           .attr("r", diameter/2);
		
	};
		
});

