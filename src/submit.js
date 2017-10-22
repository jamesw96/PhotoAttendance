(function(){


	

	window.onload = function() {
		document.getElementById("csvbutton").onclick = downloadFile;
		document.getElementById("displaytable").onclick = makeTable;
	};

	function downloadFile() {
	 urlToSend= "ec2-18-216-24-92.us-east-2.compute.amazonaws.com/";
     var req = new XMLHttpRequest();
     req.open("GET", urlToSend, true);
     req.responseType = "blob";
     req.onload = function (event) {
         var blob = req.response;
         var fileName = req.getResponseHeader("fileName") //if you have the fileName header available
         var link=document.createElement('a');
         link.href=window.URL.createObjectURL(blob);
         link.download=fileName;
         link.click();
     };

		req.send();
	 }
	 
	 function makeTable() {
		//make html table 
	 }

})();

