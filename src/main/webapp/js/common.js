String.prototype.startsWith = function (str){
    return this.indexOf(str) == 0;
};

String.prototype.trim = function(){
	return this.replace(/^\s+|\s+$/g, "");
};

String.prototype.toCamel = function(){
	return this.replace(/(\-[a-z])/g, function($1){return $1.toUpperCase().replace('-','');});
};

String.prototype.toDash = function(){
	return this.replace(/([A-Z])/g, function($1){return "-"+$1.toLowerCase();});
};

String.prototype.toUnderscore = function(){
	return this.replace(/([A-Z])/g, function($1){return "_"+$1.toLowerCase();});
};

String.prototype.startsWith = function (str){
    return this.indexOf(str) == 0;
};

String.prototype.contains = function(str) { 
	return this.indexOf(str) != -1; 
};

Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";
 
    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var d = this;
     
    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "dd": return d.getDate().zf(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            default: return $1;
        }
    });
};
 
String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};

function animateValidation(item) {
	item.css("background-color", "red");
	item.animate({ backgroundColor: "whilte"}, "slow");
	item.focus();
}

var useSelect2 = true;
//useSelect2 = false;

(function($) {
	$.fn.validate = function() {
		
		/**
		 * select2(http://ivaynberg.github.io/select2/)는
		 * select를 치한하여 select2 UI가 표현되는데
		 * 내부적으로 
		 * .select2-input,.select2-offscreen class를 가지는 input 태그가 생성됩니다.
		 */		
		
		var inputField = jQuery("div.control-group")
                        .find("label.required")
                        .parent()
                        .find("input:not('.select2-input,.select2-offscreen'),textarea,select").not(":disabled");
			    
		for (var i = 0; i < inputField.length; i++) {
	        var item = jQuery(inputField[i]);
            var parent = item.parents("div.control-group");

            if (parent.is(":visible")) {

                console.log("validation", item);

                var isEmpty = false;

                console.log("tinymce", item.attr("tinymce"));

                if (item.attr("tinymce") == "true") {
                    console.log(tinymce.EditorManager.activeEditor.getContent());

                    if (tinymce.EditorManager.activeEditor.getContent() == "") {
                        isEmpty = true;
                    }

                    if (isEmpty) {
                        animateValidation(parent.find("label"));
                        return false;
                    }

                } else {
                    if (item.val().trim() == "") {
                        isEmpty = true;
                    }

                    if (isEmpty) {
                        if (item.hasClass("select2-offscreen")) {
                            animateValidation(parent.find("label"));
                        } else {
                            animateValidation(item);
                        }
                        return false;
                    }
                }
            }
	    }
        console.log("end required validation");

		inputField = jQuery("div.control-group").find("label").parent().find("input.url").not(":disabled");
	    
		for (var i = 0; i < inputField.length; i++) {
	        var item = jQuery(inputField[i]);
	        
	        var value = item.val().trim();
        	if (value != "") {
        		if (value.startsWith("http://") || value.startsWith("https://")) {        			
        		} else {
        			alert("http://나 https://로 시작해야 합니다.");
	        		animateValidation(item);
	        		return false;
        		}
        	}
	    }
		
		inputField = jQuery("div.control-group").find("label").parent().find("textarea").not(":disabled");
		
		for (var i = 0; i < inputField.length; i++) {
	        var item = jQuery(inputField[i]);
	        
	        if (!item.hasClass("notCheckTag")) {
	        
	    		var tag = item.val().match(/<[^>]+>/);
	    		if (tag != null) {
	        		for (var j = 0; j < tag.length; j++) {
	        			console.log(tag[j]);
	        			
	        			if (tag[j] == "<strong>" 
	        				|| tag[j] == "</strong>"
	        				|| tag[j] == "<b>" 
	        				|| tag[j] == "</b>" 
	        				|| tag[j] == "<u>"
	        				|| tag[j] == "</u>"
	        				|| tag[j] == "<i>"
	        				|| tag[j] == "</i>") {
	        				
	        			} else {
	        				animateValidation(item);
	        				alert("strong, b, i, u 태그외에 다른 태그는 사용할 수 없습니다.");
	                		return false;
	        			}
	        		}
	    		}
	        }
	    }
		
		return true;
	}
	
	$.fn.serializeObject = function() {
		var o = {};
	    var a = this.serializeArray();
	    
	    $.each(a, function() {
	    	var currentName = this.name;

    		// abc[1].def
    		var indexRegExp = /(.*)\[(\d)\]\.(.*)/;
    		var resultArray = indexRegExp.exec(currentName);
    		
	    	if (resultArray && resultArray.length == 4) {
	    		var parentName = resultArray[1];
	    		var index = parseInt(resultArray[2], 10);
	    		var fieldName = resultArray[3];
	    		
	    		if (o[parentName] == undefined) {		            
		            o[parentName] = new Array();		            
		        }
	    		
	    		var item = o[parentName][index];
	    		item = item || {};
	    		item[fieldName] = this.value;	
	    		o[parentName][index] = item;	    		
	    	} else {
	    	    if (o[currentName] !== undefined) {
		            if (!o[currentName].push) {
		                o[currentName] = [o[currentName]];
		            }
		            o[currentName].push(this.value || '');
		        } else {
		            o[currentName] = this.value || '';
		        }
	    	}    
	    });
	    return o;
    }
	
	 $.fn.onEnterKey = function(callback) {
         $(this).keypress(function(event) {
                 code = event.keyCode ? event.keyCode : event.which;
                 if ( code == 13 ) {
                 	callback();
                     return false;
                 }
         });
	 }
	 
	 $.fn.exists = function() {
		return jQuery(this).length > 0;
	 }
})(jQuery);

var directory = "/" + jQuery.url().segment(1) + "/";

function toggleSelectedItems(option) {
	option = jQuery.extend({name:"itemCheckbox", id:"itemCheckbox"}, option);
	jQuery("input:checkbox[name='" + option.name + "']").prop("checked", jQuery("#" + option.id).is(":checked"));
}

function deleteItem(id) {
	if (!confirm("삭제하시겠습니까?")) {
		return;
	}
	
	jQuery.ajax({
		url: directory + id 
		, contentType: "application/json; charset=utf-8"
		, type: "DELETE"
		, success: function(data) {
			successHandler(data);
		}	
	});
}

function deleteItemById(url, id) {
	if (!confirm("삭제하시겠습니까?")) {
		return;
	}
	
	jQuery.ajax({
		url: url + "/" + id 
		, contentType: "application/json; charset=utf-8"
		, type: "DELETE"
		, success: function(data) {
			successHandler(data);
		}	
	});
}

function deleteCheckedItem() {

	if (!confirm("삭제하시겠습니까?")) {
		return;
	}
	
	var checkbox = jQuery("input:checkbox:checked.itemCheckbox");
	
	for (var i = 0; i < checkbox.size(); i++) {
		jQuery.ajax({
			url: directory + jQuery(checkbox.get(i)).val() 
			, type: "DELETE"
			, async: false
			, success: function() {
			}
		});
		
		if (i == checkbox.size() - 1) {
			location.reload();
		}
	}
}

function saveItem(url, data, validator, _successHandler, _waitingHandler) {
	if (url.split("/").length == 3) {
		url = url.substring(0, url.lastIndexOf('/'));
	}
	
	var f = jQuery("#addForm");
	
	if (!f.validate()) {
		return;
	}
	
	
	if (validator) {
		if (!validator()) {
			return;
		}
	}
		
	if (_waitingHandler) {
        _waitingHandler();
    } else {
        jQuery("#saveButton").prop("disabled", true);
	    pleaseWait();
    }
	
	var type = "POST";
	
	if (data.id) {
		type = "PUT";
		url += "/" + data.id;
	}
	
	var tinymceSelector = "textarea[tinymce]";
	var tinymceItem = jQuery(tinymceSelector);
	if (tinymceItem.exists()) {
		jQuery(tinymceSelector).val(tinymce.EditorManager.activeEditor.getContent());	
	}	
	
	var formJSon = f.serializeObject();
	console.log(formJSon);
	
	jQuery.ajax({
		url: url
		, contentType: "application/json; charset=utf-8"
		, type: type
		, data: JSON.stringify(formJSon)
		, success: function(data) {
            if (_successHandler) {
                _successHandler(data);
            } else {
                successHandler(data);
            }
		}
		, error: function() {
			jQuery.unblockUI();
		}
	});
}

function editItem(id) {
	var f = jQuery("#addForm");
	
	if (!f.validate()) {
		return;
	}
	
	jQuery("#saveButton").prop('disabled', true);
	
	pleaseWait();
	
	try {
		_editItem(id);
	} catch (e) {		
	}
	
	jQuery.ajax({
		url: directory + id
		, contentType: "application/json; charset=utf-8"
		, type: "PUT"
		, data: JSON.stringify(f.serializeObject())
		, success: function(data) {
			successHandler(data);
		}
		, error: function() {
			jQuery.unblockUI();
		}
	});
}

function successMessageHandler(data) {
	var isError = false;
	
	if (data.message) {
		if (data.message != "OK") {
			alert(data.message);
			isError = true;
		}
	}
}

function successHandler(data) {
	successMessageHandler(data);
	
//	if (!isError) {
//		alert("처리되었습니다.");
//	}
	
	jQuery("body").scrollTop(0);
	location.reload();
}

function showModalForm() {
	jQuery("#addModal").modal("show").draggable({
	    handle: ".modal-header"
    	, drag: function(event, ui){
	    	ui.position.top -= jQuery(this).parent().scrollTop();
	    }
	});
}

function showForm(_url, _data, initiator, validator) {
	_data = _data || {};
	
	jQuery.ajax({
		url: _url
		, data: _data
		, success: function(html) {
			jQuery(html).appendTo("body").on("hidden", function() {
				jQuery(this).modal("hide").remove();
			}).modal("show").draggable({
			    handle: ".modal-header"
		    	, drag: function(event, ui){
			    	ui.position.top -= jQuery(this).parent().scrollTop();
			    }	
			});
			
			jQuery("#saveButton").on("click", function() {
				saveItem(_url, _data, validator);				
			});
			
			init();
			
			if (initiator) {
                console.log("@@@intitiator");
				initiator();
			}
		}
	});
}

function getCodeName(data, key) {
	try {
		return getCodeItem(key.toUnderscore().toUpperCase(), data[key]).itemName;
	} catch (e) {
		return data[key];	
	}
}

function stringify(data) {
	return JSON.stringify(data, null, 4);
}

function createDynamicChart(option) {
	var defaultSeriesType = "line";
	
	if (option.defaultSeriesType) {
		defaultSeriesType = option.defaultSeriesType;
	}
	
	var xAxisFormat = "%m/%d";
	
	if (option.xAxisFormat) {
		xAxisFormat = option.xAxisFormat;
	}
	
	var tooltipFormat = "%Y-%m-%d";
	
	if (option.tooltipFormat) {
		tooltipFormat = option.tooltipFormat;
	}
	
	var legend = false;
	
	if (option.legend) {
		legend = option.legend;
	}
	
	var _chartOption = {
	    chart: {
	       renderTo: option.name + "_chart" 
	       , type: defaultSeriesType
	       , marginRight: 10
	    }
		, title: {
			text: option.title
		}
		, xAxis: {
			type: 'datetime'
			, labels: {
				formatter: function() {
					return Highcharts.dateFormat(xAxisFormat, this.value);
				}
			}
		}, yAxis: {
			title: {
				text: option.yAxisTitle
			}
			, min: 0
			, labels: {
			   align: 'right'
              , formatter: function() {
                  return Highcharts.numberFormat(this.value, 0);
              }
			}                  
		}, tooltip: {
			formatter: function() {
				return ''+ Highcharts.dateFormat(tooltipFormat, this.x) + ' : '+ this.y +' 개';
			}
		}, legend: {
			enabled: legend
		}, exporting: {
			enabled: false
		}, plotOptions: {
			column: {
				pointWidth: 20
			}
            , line: {
                lineWidth: 2
	              , states: {
	                  hover: {
	                      lineWidth: 3
	                  }
	              }, marker: {
	                  enabled: false,
	                  states: {
	                      hover: {
	                          enabled: true,
	                          symbol: 'circle',
	                          radius: 5,
	                          lineWidth: 1
	                      }
	                  }    
	              }
            }
		}
	};
	
	if (option.series) {
		if (jQuery.isArray(option.series)) {
			_chartOption.series = option.series;
		} else {
			_chartOption.series = [option.series];
		}
		console.log(_chartOption);
	} else {
		_chartOption.series = [{
    	  	name: "Random data"
    		, data: (function() {
		          var data = [];
		          var time = new Date().getTime();
		          
		          for (var i = -190; i <= 0; i++) {
		             data.push({
		                x: time + (i * 5) * 1000,
		                y: 0
		             });
		          }
		          return data;
    		 })()
      	}];
	}
	
	return new Highcharts.Chart(_chartOption);
}

var numberFormatOption = {format:"#,###"};
var decimalFormatOption = {format:"#,###.00"};

function _createSelectBox(option, data) {
	var content = "<select id='" + option.id + "' name='" + option.name + "'";
	
	if (option.multiple) {
		content += " multiple";
	}
	
	if (option.style) {
		content += " style='" + option.style + "'>";
	} else {
		content += ">";
	}	

	if (option.placeholder) {
		var placeholderValue = "";
		if (option.placeholderValue) {
			placeholderValue = option.placeholderValue;
		}
		content += "<option value=\"" + placeholderValue + "\">" + option.placeholder + "</option>";
	}
	
	for (var i = 0; i < data.length; i++) {
		var item = data[i];
		var selected = "";
		
		if (option.selectedValue && item[option.value] == option.selectedValue) {
			selected = "selected";
		}
		
		var optionText = item[option.text];
		
		if (jQuery.isArray(option.text)) {
			optionText = "";
			
			for (var j = 0; j < option.text.length; j++) {
				console.log(option.text[j]);
				
				if (j != 0) {
					optionText += " ";
				}
				
				optionText += item[option.text[j]]
				
				if (option.text[j] == "seqName") {
					optionText += ".";
				}
			}
		}
		
		content += "<option value='" + item[option.value] + "' " + selected + ">" + optionText + "</option>";
	}
	content += "</select>";

	jQuery("#" + option.containerId).empty().append(content);
	jQuery("#" + option.containerId + " select option");
	jQuery("#" + option.containerId + " select").on("change", function(index) {
		if (option.changeEvent) {
			option.changeEvent();
		}
	});
	
	if (option.callback) {
		option.callback();
	}
	
	if (useSelect2) {
		jQuery("select").select2();
	}
}

function createSelectBox(option) {
	jQuery.ajax({
		url: option.api
		, data: option.params
		, dataType: "json"
		, async: false
		, success: function(data) {	
			_createSelectBox(option, data.list);
		}	
	});	
}

function createCodeSelectBox(groupCode, _option) {
	var name = getCamelExpression(groupCode);
	var option = {
		  id: "addForm_" + name
		, containerId: name + "Container"
		, name: name
		, value: "itemCode"
		, text: "itemName"
	};
	
	option = jQuery.extend(option, _option||{})
	
	console.log(option);
	
	_createSelectBox(option, getCodeItemList(groupCode));
}

function getCamelExpression(text) {
	var text = _.reduce(text.split("_"), function(memo, value) {
		return memo + value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase(); 
	}, "");
	
	return text.substring(0, 1).toLowerCase() + text.substring(1);
}

function getCodeItemList(groupCode) {
	return code.codeMap[groupCode];
}

function getCodeItem(groupCode, itemCode) {
	return _.filter(code.codeMap[groupCode], function(item) {
		return item.itemCode == itemCode; 	
	})[0];
}

/**
 * http://play.daumcorp.com/pages/viewpage.action?pageId=105945521
 * 
 * http://i1.daumcdn.net/svc/image/U03/storyBall/518C3CDC0221B00003
 * -> http://i1.daumcdn.net/thumb/T480x416/U03/storyBall/518C3CDC0221B00003 
 */
function getThumbnail(url, size) {
	return url.replace(/\/svc\/image/, "/thumb/" + size);
}

function getSourceImage(url) {
	return url.replace(/\/thumb\/(.*?)\//, "/svc/image/");
}

function isThumbnail(url) {
	if (url.indexOf("daumcdn.net/thumb") > 0) {
		return true;
	}
	
	return false;
}

function pleaseWait() {	
	setTimeout(showPleaseWait, 1000);
} 

function showPleaseWait() {
	jQuery.blockUI({ 
		message: "<div class=\"progress progress-striped active\"><div class=\"bar\" style=\"width: 100%;\"></div></div>"
		, css:{ 
			fontSize: 'medium'
		    , border: 'none' 
		    , padding: '0px' 
		    , backgroundColor: '#000000' 
		    , '-webkit-border-radius': '10px'
			, '-moz-border-radius': '10px'
		    , opacity: 0.9
		    , color: '#fff' 
		}
		, baseZ: 2000
		, overlayCSS:  { 
	        backgroundColor: '#ffffff'
	        , opacity: 0.0
	        , cursor: 'wait' 
	    }
	});
}

function attachViewer() {
	jQuery("img.viewable").css({"cursor":"pointer"}).off("click").on("click", function() {
		var url = jQuery(this).attr("src");
		if (isThumbnail(url)) {
			var sourceUrl = getSourceImage(url);
			jQuery("#imageViewer").empty().html("<img src='" + url + "'>").append("<br>").append(url).append("<hr>").append("<img src='" + getSourceImage(url) + "'><br>" + sourceUrl);
		} else {
			jQuery("#imageViewer").empty().html("<img src='" + jQuery(this).attr("src") + "'>").append("<br>").append(url);
		}
		
		var event = jQuery.event.fix(window.event);
		
		var _top = event.pageY;
				
		jQuery("#imageViewerModal").css({
			top: _top
		}).modal().draggable({
		    handle: ".modal-header"
		    , drag: function(event, ui){
		    	ui.position.top -= jQuery(this).parent().scrollTop();
		    }
		});
	});
}

function showHelpViewer() {
	var event = jQuery.event.fix(window.event);
	var _top = event.pageY;
	
	jQuery("#helpViewerModal").css({
		top: _top
	}).modal().draggable({
	    handle: ".modal-header"
	    , drag: function(event, ui){
	    	ui.position.top -= jQuery(this).parent().scrollTop();
	    }
	});
}

function init() {
	var title = "";
	var titleArray = new Array();
	
	if (useSelect2) {
		jQuery("select").select2();
	}
	
	var numberSpan = jQuery("span.number");
	numberSpan.each(function() {
		if (jQuery(this).html().trim() !== "0") {
			jQuery(this).formatNumber(numberFormatOption);
		}
	});
		
	jQuery("label.required").each(function() {
		var item = jQuery(this);
		item.prepend("<i class=\"icon-ok-sign\"></i>&nbsp");
	});
	
	jQuery("div.controls.datetime")
		.addClass("input-append")
		.css("display", "block")
		.append("<span class=\"add-on\"><i data-time-icon=\"icon-time\" data-date-icon=\"icon-calendar\" class=\"icon-calendar\"></i></span>")
		.each(function() {
			var datetimepickerOption = {
			    format: "yyyy-MM-dd hh:mm:ss"
			    , language: "ko-KR"
			};
			jQuery(this).datetimepicker(datetimepickerOption);
		});
		
	jQuery("div.controls.date").each(function() {
		var datetimepickerOption = {
		    format: "yyyy-MM-dd"
		    , language: "ko-KR"
		    , pickTime: false
		};
		jQuery(this).datetimepicker(datetimepickerOption);
	});
	
	jQuery("div.controls.time").each(function() {
		var item = jQuery(this);
		item.append("<span class=\"add-on\"><i data-time-icon=\"icon-time\" data-date-icon=\"icon-calendar\"></i></span>");
		item.css("display", "block");
		item.addClass("input-append");
		item.addClass("date");
		
		var datetimepickerOption = {
			format: "hh:mm:ss"
		    , language: "ko-KR"
		    , pickDate: false
		};
		
		item.datetimepicker(datetimepickerOption);
	});
	
	jQuery(":input[name*='imageUrl'],:input[name*='thumbUrl'],:input[name*='commentImageUrl']").each(function() {
		var item = jQuery(this);
		item.css("width", "370px");
		var id = item.attr("id") + "_uploadButton";
		
		var controlsDiv = item.parents("div.controls").empty();
		var inlineDiv = jQuery("<div class='inline'/>").appendTo(controlsDiv);
		inlineDiv.append(item);
		controlsDiv.append("<div id='" + id + "' class='inline' style='margin-left:5px'/>");
		
		coca.addImageUpload(id, function(url) {
			jQuery("#" + id).parents("div.controls").children("img").remove().end().append("<img src='" + url + "' class='viewable'>").find("input").val(url);
			attachViewer();
		});
				
		if (item.val() != "") {
			item.parents("div.controls").append("<img src='" + item.val() + "' class='viewable'>");
		}
		
		item.on("change", function() {
			var url = item.val();
			console.log("change");
			if (url == "") {
				jQuery(this).parents("div.controls").children("img").remove();
			} else {
				jQuery(this).parents("div.controls").children("img").remove().end().append("<img src='" + url + "' class='viewable'>");
			}
			attachViewer();
		});
	});
	
	attachViewer();
	
	var tinymceSelector = "textarea[tinymce]";
	var tinymceItem = jQuery(tinymceSelector);
	if (tinymceItem.exists()) {
		tinymce.init(jQuery.extend(_tinymceOption, {selector:tinymceSelector}));
	}	
	
	if (jQuery("#uploadImage").exists()) {		
		coca.addImageUpload("uploadImage", function(url) {
			tinymce.EditorManager.activeEditor.setContent(tinymce.EditorManager.activeEditor.getContent() + "<img src='" + url + "'>");
		});
	}
	
	jQuery("a.help").on("click", function() {
		showHelpViewer();
	});
	
	jQuery("div.nav li.dropdown li a").each(function() {
		var item = jQuery(this);
		if (location.href.indexOf(item.attr("href")) > 0) {
			item.parents("li.dropdown").css({
				"border-bottom": "solid 3px red"
			});
		}
	});
	
	// https://github.com/twbs/bootstrap/issues/6942		
	jQuery("[rel='tooltip']").tooltip({
        placement: 'bottom'
    }).on('show', function(e) {
        e.stopPropagation();
    }).on('hidden', function(e) {
        e.stopPropagation();
    });
	
	setTimeout(function() {
		initHeader();
	}, 300);
}

var _initHeaderCompleted = false;

function initHeader() {
	if (_initHeaderCompleted) {
		return;
	}

	var headerDiv = jQuery("#header");
	if (headerDiv.size() == 1) {
		headerDiv.css({
			position: "fixed"
		});
		
		var globalAlertDiv = jQuery("#globalAlert");
		headerDiv.prepend(globalAlertDiv.html())
		.css({
			"background-color": "white"
		});
		
		globalAlertDiv.hide();
	
		var content = "<table class=\"table table-hover table-condensed\" style=\"width:1170px;margin-bottom:0px\">";
		content += "<thead id=\"fixedTableHeader\">";
		content += jQuery("#tableHeader").html();
		content += "</thead>";
		content += "</table>";
		
		jQuery("#newTableHeader").html(content);
		
		jQuery("#tableHeader th").each(function(index) {
			jQuery("#fixedTableHeader th:eq(" + index + ")").css({
				"width": jQuery(this).css("width")
				, "border-top": "1px solid #dddddd"
			});			 
		});
				
		jQuery("#tableBody").css({
			"padding-top": jQuery("#header").height() - 30
		});
	}
	
	_initHeaderCompleted = true;
}

jQuery(function() {
	init();
});

var tinymceOption = {
	selector: "textarea",
	theme: "modern",
	plugins: [
		"advlist autolink lists charmap print preview hr anchor pagebreak",
		"searchreplace wordcount visualblocks visualchars code fullscreen",
		"insertdatetime media nonbreaking save contextmenu directionality",
		"emoticons paste textcolor image link"
	],
	statusbar : false,
	menubar : false,
	width : 600,	
	statusbar : false,
	toolbar1: "styleselect | forecolor backcolor | alignleft aligncenter alignright alignjustify | print preview code searchreplace | link image |",
	image_advtab: true,
	style_formats: [
        {title: 'h1', block: 'h1', styles: {}},
		{title: 'h2', block: 'h2', styles: {}},
		{title: 'h3', block: 'h3', styles: {}},        
		{title: 'bold', inline: 'b'},
		{title: 'italic', inline: 'i'},
		{title: 'underline', inline: 'u'}, 
		{title: '12px', inline: 'span', styles: {fontSize: '12px'}},
		{title: '14px', inline: 'span', styles: {fontSize: '14px'}},
		{title: '16px', inline: 'span', styles: {fontSize: '16px'}},
		{title: '20px', inline: 'span', styles: {fontSize: '20px'}}
    ],
};

var minTinymceOption = {
    selector: "textarea",
    theme: "modern",
    plugins: [
        "advlist autolink lists charmap print preview hr anchor pagebreak",
        "searchreplace wordcount visualblocks visualchars code fullscreen",
        "insertdatetime media nonbreaking save directionality",
        "emoticons paste textcolor"
    ],
    statusbar : false,
    menubar : false,
    width : 400,
    height: 50,
    statusbar : false,
    toolbar1: "forecolor code",
    image_advtab: true
};

var _tinymceOption = tinymceOption;

function showConentCountHourLogChart(chartOption) {
	chartOption.api = "/contentCountHourLog"
	chartOption.params.mode = "chart";	
	chartOption.params.day = 30;
	showChart(chartOption);
}

function showChart(chartOption) {
	jQuery("#chartViewer").empty().append("<div id='contentCountDayLog_chart' style='width:900px;height:400px'></div>");
	
	var event = jQuery.event.fix(window.event);
	console.log(event);
	
	jQuery.getJSON(chartOption.api, chartOption.params, function(data) {
		var _chartOption = {
			name: "contentCountDayLog"
			, defaultSeriesType: "column"
		};
		
		_chartOption = jQuery.extend(true, _chartOption, chartOption);

		_chartOption.series = data;
		
		createDynamicChart(_chartOption);
				
		var _top = event.pageY;
		
		jQuery("#chartViewerModal")
		.css({
			top: _top
		})
		.modal().draggable({
		    handle: ".modal-header"
	    	, drag: function(event, ui){
		    	ui.position.top -= jQuery(this).parent().scrollTop();
		    }
		});
	});
}

function _createEpisodeSelectBox(option) {
	var _option = {
	    	api: "/storyEpisode/" + option.mode
	    	, params: {
	    		storyId: jQuery("#_storyId").val()
	    		, count: 200
	   		}
	    	, containerId: "episodeSelector"
	    	, id: "addForm_episodeId"
	    	, name: "episodeId"
	    	, value: "episodeId"
	    	, text: ["seqName", "name"]
			, placeholder: "=== 회차 선택 ==="
			, changeEvent: function() {				
			}
			, callback: function() {
			}
	};
	
	_option =  jQuery.extend(_option, option);
	
	if (_option.mode == "listForNotMappedWithAuthor") {
		_option.params = jQuery.extend(_option.params, {
			authorId: jQuery("#addForm_authorId").val()
			, authorMapId: jQuery("#addForm_id").val()
		});
	} else if (_option.mode == "listForNotMappedWithPackage") {
		_option.params = jQuery.extend(_option.params, {
			packageId: jQuery("#addForm_packageId").val()
			, packageMapId: jQuery("#addForm_id").val()
		});
	} else if (_option.mode == "listForNotMappedWithProfileItem") {
		_option.params = jQuery.extend(_option.params, {
			itemId: jQuery("#addForm_itemId").val()
			, itemMapId: jQuery("#addForm_id").val()
		});	
		
		_option.id = "objectId";
		_option.name = "objectId";
	} else if (_option.mode == "list") {
		
	}
	
	createSelectBox(_option);
}

function createEpisodeSelectBox(mode) {
	_createEpisodeSelectBox({"mode":mode});
}

function createProfileItemSelectBox() {
	var _option = {
    	api: "/profileItem/listForNotMappedWithStory"
    	, params: {
   			profileId: jQuery("#_profileId").val()
   		}
    	, containerId: "profileItemSelector"
    	, id: "addForm_itemId"
    	, name: "itemId"
    	, value: "itemId"
    	, text: "name"
		, placeholder: "===맞춤 한작품 항목선택==="
		, changeEvent: function() {				
		}
		, callback: function() {
		}
	}
	
	createSelectBox(_option);
}

function send() {
	jQuery("#searchForm").submit();
}

function publish() {
	var _date = jQuery("#addForm_publishDatetime").val();
	var message = "발행일시는 " + _date + "입니다.\n";
	message += _date.substring(0, 15) + "0:00 쯤에 서비스 상태로 변경됩니다.\n";
	message += "발행하시겠습니까?";
	
	if (confirm(message)) {
		var id = jQuery("#addForm_episodeId").val();
		jQuery("#addForm_mode").val("PUBLISH");
		saveItem('/storyEpisode', {
			  episodeId: id	
			, id: id	
		});
	}
}

function forcedPublish() {
	var _date = jQuery("#addForm_publishDatetime").val();
	var message = "발행일시는 " + _date + "입니다.\n";
	message += "발행일시를 무시하고 강제로 서비스 상태로 변경됩니다.\n";
	message += "발행하시겠습니까?";
	
	if (confirm(message)) {
		var id = jQuery("#addForm_episodeId").val();
		jQuery("#addForm_mode").val("FORCED_PUBLISH");
		saveItem('/storyEpisode', {
				  episodeId: id	
				, id: id
		});
	}
}

var fixHelper = function(e, ui) {
    ui.children().each(function() {
        jQuery(this).width(jQuery(this).width());
    });
    return ui;
};

function setSortable() {
	jQuery("#sortableItems").sortable({        
        helper: fixHelper
		, update: function(event, ui) {	
			updateComponentIndex();
			saveDispOrder();
			jQuery("#message").html("순서가 변경되었습니다.").show().fadeOut(2000);
		}
		, disabled: false
		, handle: 'td.id'
    });
}

jQuery(function() {
	setSortable();
	
	updateComponentIndex();
	
	setTimeout(function() {
		saveDispOrder();
	}, 500);
});

var _idField = "id";
var _dispOrderField = "dispOrder";

function updateComponentIndex() {
	jQuery("#sortableItems td." + _dispOrderField).each(function(index) {
		jQuery(this).html(index + 1);
	})
}

function saveDispOrder() {
	var option = [];
	
	jQuery("#sortableItems tr.component").each(function() {
		var item = {};
		item[_idField] = jQuery(this).find("td.id").html()
		item[_dispOrderField] = jQuery(this).find("td." + _dispOrderField).html().trim();
		
		option.push(item);
	});

	if (option.length == 0) {
		return;
	}
	
	jQuery.ajax({
		url: directory + "dispOrder"
		, contentType: "application/json; charset=utf-8"
		, type: "POST"
		, data: JSON.stringify(option)
		, success: function(data) {
		}
		, error: function() {			
		}
	});
}

function preview(url) {
	window.open(url, '', 'menubar=0,titlebar=0,toolbar=0,width=400,height=600');
}

jQuery(function() {
	jQuery("#q").on("keypress", function(e) {
		if (e.which == 13) {
			send();
		}
	});
});

function sendEpisodeUrl(url) {
	jQuery.getJSON("/admin/sendMyPeopleMessage"
	, {
		message:url
	}
	, function(data) {	
	});
}

function sendEpisodeUrlForAuthor(url) {
	jQuery.getJSON("/admin/sendMyPeopleMessage"
	, {
		message: url
		, mode: "author"
	}
	, function(data) {	
	});
}

function loginAsAuthor(userId) {
	location.href = "/admin/loginAsAuthor/" + userId
}

function logoutAsAuthor() {
	location.href = "/admin/logoutAsAuthor";
}

function getColor(source, target, x, y, callback) {
    var url = jQuery("#" + source).val();
    console.log("url", url);
    jQuery.getJSON("/dominantColor", {
        url: url
        , x: x
        , y: y
    }, function(data) {
        jQuery("#" + target).val(data);

        if (callback) {
            callback(data);
        }
    })
}

function getColorAndSetBackground(index) {
    getColorAndSetBackgroundEx("addForm_imageUrl" + index, "addForm_imageBgColor" + index, 1, 1);
}

function getColorAndSetBackgroundEx(source, target, x, y) {
    getColor(source, target, x, y, function(color) {
        setColor(source, target, color);
    });
}

function setColor(source, target, color) {
    jQuery("#" + source).parents("div.control-group").css("backgroundColor", color);
}

function setColorAndBackgroundEx(source, target) {
    var color = jQuery("#" + target).val();
    console.log("color", color);
    setColor(source, target, color);
    setColorForTinyMce(color);
}

function setColorForTinyMce(color) {
    setTimeout(function() {
        try {
            console.log("setColorForTinyMce:" + color);
            console.log(tinymce.EditorManager.activeEditor.getBody());
            tinymce.EditorManager.activeEditor.getBody().style.backgroundColor = color;
        } catch (e) {
            console.log("error", e);
        }
    }, 100);
}

function setColorAndBackground(index) {
    setColorAndBackgroundEx("addForm_imageUrl" + index, "addForm_imageBgColor" + index);
}