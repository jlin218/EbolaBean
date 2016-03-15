var request;
function doJson(){
	var settings = new Object();
	settings.method = "POST";
	settings.contentType = "application/x-www-form-urlencoded; charset=UTF-8";
	settings.url = "/beans/ebola/hi";
	settings.async = true;
	settings.cache = false;
	settings.success = processEbola;
	$.ajax(settings);
}
function processEbola(data){
	var JsonData = JSON.parse(data);
	var cases1 = {name:"world", data:[]};
	var cate1 = [];
	jQuery.each(JsonData, function(){
		cate1.push(this.date);
		cases1["data"].push(this.cases);
	});	
	getMap(cate1, cases1);
}

function getMap(categ, cases){
	var categg = {categories: categ};
	var test2= categg;
	var test = [cases];
	$(function () {
	    $('#container2').highcharts({
	        title: {text: 'Ebola',x: -20 },
	        subtitle: { text: 'Source: WorldClimate.com',x: -20},
	        yAxis: { title: {text: 'Cases' },plotLines: [{
	                value: 10,
	                width: 1,
	                color: '#808080'}]},
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        },
	        xAxis: test2,
	        series: test
	    })
	});
}


function doRequest(method, url, id, action){
	var settings = new Object();
	settings.method  = method;
	settings.contentType = "application/x-www-form-urlencoded; charset=UTF-8";
	settings.url = url;
	settings.data = "action="+action+"&id="+id;
	settings.async = true;
	settings.cache = false;
//	settings.contentType = "application/json; charset=UTF-8";
//	settings.data = JSON.stringify({
//		action: "jsonJson",
//		id: id
//	});
//	settings.success = processJSON;


//	settings.success = processText;
//	settings.success = processXml;
	settings.success = processJson;
	
	$.ajax(settings);
//	request =  new XMLHttpRequest();
//	request.onreadystatechange = demoCallBack; 
//	var requestURL = url+"?" +"action="+action+"&id="+id+"&dummy="+new Date().getTime();
//	request.open("GET",requestURL,true);
//	console.log(+"jo"+requestURL);
//	request.send(null); 
}
//function requestDoPost(url, id, action){
//	
//	request =  new XMLHttpRequest();
//	request.onreadystatechange = demoCallBack;
//	var data= "action="+action+"&id="+id;
//	request.open("POST",url,true);
//	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//	console.log(url);
//	console.log(data);
//	request.send(data);
//}
function processText(data){
	console.log("hi");
	$("#img1").hide();
	var index = data.indexOf(":");
	var show = data;

	if (index!=-1){
		show = data.substring(0,index);
		var array = data.substring(index+1).split(",");
		$("input[name='id']").val(array[0]);
		$("input[name='name']").val(array[1]);
		$("input[name='price']").val(array[2]);
		$("input[name='make']").val(array[3]);
		$("input[name='expire']").val(array[4]);
//		document.forms[0].id.value=array[0];
//		document.forms[0].name.value=array[1];
//		document.forms[0].price.value=array[2];
//		document.forms[0].make.value=array[3];
//		document.forms[0].expire.value=array[4];
	}else{
		$("input[name='name']").val("");
		$("input[name='price']").val("");
		$("input[name='make']").val("");
		$("input[name='expire']").val("");
//		document.forms[0].name.value="";
//		document.forms[0].price.value="";
//		document.forms[0].make.value="";
//		document.forms[0].expire.value="";
	}
	
	
//	var node = document.createTextNode(show);
//	var oldNode =document.getElementsByClassName("error")[0];
//	if (oldNode.hasChildNodes()){
//		oldNode.removeChild(oldNode.childNodes[0]);
//	}
//	oldNode.appendChild(node);
//	
	$(".error:eq(0)").text(show);
}

function processXml(data){
	$("#img1").hide();
	$("span:eq(0)").text($(data).find("text").text());
//	var itemExists = data.getElementsByTagName("result")[0];	
//	var hasMoreData = itemExists.getElementsByTagName("hasMoreData")[0].firstChild.nodeValue;
//	var show=document.createTextNode(itemExists.getElementsByTagName("text")[0].firstChild.nodeValue);
	console.log("hello333");
	if ($(data).find("hasMoreData").text() =="true"){
		$("input[name='id']").val($(data).find("id").text());
		$("input[name='name']").val($(data).find("name").text());
		$("input[name='price']").val($(data).find("price").text());
		$("input[name='make']").val($(data).find("make").text());
		$("input[name='expire']").val($(data).find("expire").text());
//		document.forms[0].id.value=itemExists.getElementsByTagName("id")[0].firstChild.nodeValue;
//		document.forms[0].name.value = itemExists.getElementsByTagName("name")[0].firstChild.nodeValue;
//		document.forms[0].price.value = itemExists.getElementsByTagName("price")[0].firstChild.nodeValue;
//		document.forms[0].make.value = itemExists.getElementsByTagName("make")[0].firstChild.nodeValue;
//		document.forms[0].expire.value = itemExists.getElementsByTagName("expire")[0].firstChild.nodeValue;
	}
//	var oldNode = document.getElementsByClassName("error")[0];
//	if(oldNode.hasChildNodes()){
//		oldNode.removeChild(oldNode.childNodes[0]);
//	}
//	oldNode.appendChild(show);
	console.log("hello");

}

function processJson(data){
	$("#img1").hide();
	console.log(data);
	var jsonData = JSON.parse(data);
//	var show = jsonData[0].text;
//	var node = document.createTextNode(show);
//	var oldNode = document.getElementsByClassName("error")[0];
//	if (oldNode.hasChildNodes()){
//		oldNode.removeChild(oldNode.childNodes[0]);
//	}
//	oldNode.appendChild(node);
//	
	
	

	$(".error:eq(0)").text(jsonData[0].text);
	if(jsonData[0].hasMoreData){
		$("input[name='id']").val(jsonData[1].id);
		$("input[name='name']").val(jsonData[1].name);
		$("input[name='price']").val(jsonData[1].price);
		$("input[name='make']").val(jsonData[1].make);
		$("input[name='expire']").val(jsonData[1].expire);
		
//		document.forms[0].id.value=jsonData[1].id;
//		document.forms[0].name.value=jsonData[1].name;
//		document.forms[0].price.value=jsonData[1].price;
//		document.forms[0].make.value=jsonData[1].make;
//		document.forms[0].expire.value=jsonData[1].expire;
	}
}
function demoCallBack(){
	
	if(request.readyState==4){
		if(request.status==200){
			var text = request.responseText;
			var dom =request.responseXML;
//			processText(request.responseText);
			//console.log(request.responseText);
//			processJson(request.responseText);
			processXml(request.responseXML);
			$("#img1").css("display","none");
//			document.getElementById("img1").style.display="none";
		}else{
			alert("Error! Status Code = "+request.status);
		}
	}
}