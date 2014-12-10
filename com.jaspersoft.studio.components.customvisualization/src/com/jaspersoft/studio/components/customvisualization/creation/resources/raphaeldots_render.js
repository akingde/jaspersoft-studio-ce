define(['raphael','g.raphael','g.dot'], function (Raphael, empty, empty) {

	 return function (instanceData) {	
		
	 			
				 var series0 = instanceData.series[0];

                    var chartData = [];

                    for (var index = 0; index < series0.length; ++index) {
                    
                        var record = series0[index];
                        
                        chartData.push({x: record.x, y: record.y, value: record.value});
                    }    
                   

                    var r = Raphael(instanceData.id);

                    // find unique row values...(y)
                    var rowLabels = [];
                    var colLabels = [];

		    var i=0, j=0;
                    for (var index in chartData) {
                    	var record = chartData[index];
                    	
                        if (typeof record.x != 'undefined' && !contains(rowLabels, record.x ))
                        {
                            rowLabels[i]=record.x;
                            i++;
                        }
                      
                    }
                    
                    
                    for (var index in chartData) {
                    	var record = chartData[index];
                        if (typeof record.y != 'undefined' &&  !contains(colLabels, record.y ))
                        {
                            colLabels[j] = record.y;
                            j++;
                        }
                    }
   
   					colLabels.sort();
                    // Inverse sorting
                    rowLabels.sort();
                    rowLabels.reverse();

                    
                    var  xs = [];

                    for (i=0; i<rowLabels.length; ++i)
                    {   
                        for (j=0; j<colLabels.length; ++j)
                        {
                            xs.push(j);
                        }
                    }


                    var  ys = [];

                    for (i=0; i<rowLabels.length; ++i)
                    {   
                        for (j=0; j<colLabels.length; ++j)
                        {
                        	ys.push( rowLabels.length-i );
                        }
                    }

                    var  axisy = rowLabels;
                    var  axisx = colLabels;
			    
                    var data = [];
                    
                    for (i=0; i<rowLabels.length; ++i)
                    {   
                        for (j=0; j<colLabels.length; ++j)
                        {
                            var y = colLabels[j];
                            var x = rowLabels[rowLabels.length-i-1];
                            data[i*colLabels.length + j] = 0;

                            // Locate the value of x,y in the main dataset...
                            for (var index in chartData) {
                            	var record = chartData[index];
                                if (record.x == x && record.y == y)
                                {
                                    data[i*colLabels.length + j] = record.value;
                                    break;
                                }
                            }
                        }
                    }
                    
                 r.dotchart(10, 10, 
                           instanceData.width-20,
                          instanceData.height-20, xs, ys, data, {symbol: "o", max: 10, heat: true, axis: "0 0 1 1", axisxstep: colLabels.length-1, axisystep: rowLabels.length-1, axisxlabels: axisx, axisxtype: " ", axisytype: " ", axisylabels: axisy}).hover(function () {
                    this.marker = this.marker || r.tag(this.x, this.y, this.value, 0, this.r + 2).insertBefore(this);
                    this.marker.show();
                }, function () {
                    this.marker && this.marker.hide();
                });

                function contains(a, obj) {
                    var i = a.length;
                    while (i--) {
                       if (a[i] === obj) {
                           return true;
                       }
                    }
                    return false;
                }      

		
	};
		
});

