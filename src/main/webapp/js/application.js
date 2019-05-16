//(function($)
//{
//	/*!
//	| @ Check if an element exists in the DOM
//	| @return boolean
//	*/
//	$.fn.exists = function(){
//
//		return this != null;
//	}
//
//	/*!
//	| @ Check if an input element has value
//	| @return boolean
//	*/
//	$.fn.isEmpty = function(){
//
//		if ( $.trim(this.val()) == '' ) {
//            return true;
//        }
//		else {
//            return false;
//        }
//	};
//
//	/*!
//	| @ Check if an element exists in the DOM and if it has a value.
//	|  This method combines the two aforementioned methods.
//	| @return boolean
//	*/
//	$.fn.isNullOrEmpty = function()	{
//
//		if ( ! this.exists() ){
//            return true;
//        }
//		if ( this.isEmpty() ){
//            return true;
//        }
//		return false;
//	}
//
//
//
//})(jQuery);
//

var farsiKeyCodes = new Array(1662, 1670, 1580, 1581, 1582, 1607, 1593, 1594, 1601, 1602, 1603,
                              1572,
                              1579,
                              1589, 1590, 1711, 1705, 1605, 1606, 1578, 1575, 1604, 1576, 1740, 1587,
                              1588, 1608, 1574, 1571, 1583, 1584, 1585, 1585, 1586, 1591, 1592, 1570, 17281569, 1571, 1573, 1572, 1688, 1610, 1577,
                              1611, 1612, 1613, 1614, 1615, 1617);

var browserDetect = {
	init: function () {
		this.browser = this.searchString(this.dataBrowser) || "An unknown browser";
		this.version = this.searchVersion(navigator.userAgent)
			|| this.searchVersion(navigator.appVersion)
			|| "an unknown version";
		this.OS = this.searchString(this.dataOS) || "an unknown OS";
	},
	searchString: function (data) {
		for (var i=0;i<data.length;i++)	{
			var dataString = data[i].string;
			var dataProp = data[i].prop;
			this.versionSearchString = data[i].versionSearch || data[i].identity;
			if (dataString) {
				if (dataString.indexOf(data[i].subString) != -1)
					return data[i].identity;
			}
			else if (dataProp)
				return data[i].identity;
		}
	},
	searchVersion: function (dataString) {
		var index = dataString.indexOf(this.versionSearchString);
		if (index == -1) return;
		return parseFloat(dataString.substring(index+this.versionSearchString.length+1));
	},
	dataBrowser: [
		{
			string: navigator.userAgent,
			subString: "Chrome",
			identity: "Chrome"
		},
		{ 	string: navigator.userAgent,
			subString: "OmniWeb",
			versionSearch: "OmniWeb/",
			identity: "OmniWeb"
		},
		{
			string: navigator.vendor,
			subString: "Apple",
			identity: "Safari",
			versionSearch: "Version"
		},
		{
			prop: window.opera,
			identity: "Opera",
			versionSearch: "Version"
		},
		{
			string: navigator.vendor,
			subString: "iCab",
			identity: "iCab"
		},
		{
			string: navigator.vendor,
			subString: "KDE",
			identity: "Konqueror"
		},
		{
			string: navigator.userAgent,
			subString: "Firefox",
			identity: "Firefox"
		},
		{
			string: navigator.vendor,
			subString: "Camino",
			identity: "Camino"
		},
		{		// for newer Netscapes (6+)
			string: navigator.userAgent,
			subString: "Netscape",
			identity: "Netscape"
		},
		{
			string: navigator.userAgent,
			subString: "MSIE",
			identity: "Explorer",
			versionSearch: "MSIE"
		},
		{
			string: navigator.userAgent,
			subString: "Gecko",
			identity: "Mozilla",
			versionSearch: "rv"
		},
		{ 		// for older Netscapes (4-)
			string: navigator.userAgent,
			subString: "Mozilla",
			identity: "Netscape",
			versionSearch: "Mozilla"
		}
	],
	dataOS : [
		{
			string: navigator.platform,
			subString: "Win",
			identity: "Windows"
		},
		{
			string: navigator.platform,
			subString: "Mac",
			identity: "Mac"
		},
		{
			   string: navigator.userAgent,
			   subString: "iPhone",
			   identity: "iPhone/iPod"
	    },
		{
			string: navigator.platform,
			subString: "Linux",
			identity: "Linux"
		}
	]

};
browserDetect.init();

var browserName =  browserDetect.browser;     // return value for ie : Explorer, Firefox}
var browserVersion =  browserDetect.version;
var osVersion =  browserDetect.OS;

function browserIsIe() {

    if(browserName == "Explorer")
        return true;
    else
        return false;
}

function browserIsFirefox() {

    if(browserName == "Firefox")
        return true;
    else
        return false;
}

function getElement (el) {

    var element = document.getElementById(el);

    if (element == null)
        element = document.getElementsByName(el);

    if (element == null)
        element = document.all[el]

    return element;
}

function customeValidation(form, errors) {

    //List for errors
    var list = $('#formerrors');

    //Handle non field errors
    if (errors.errors) {
        $.each(errors.errors, function(index, value) {
            list.append('<li>'+value+'</li>\n');
        });
    }
    //Handle field errors
    if (errors.fieldErrors) {
        $.each(errors.fieldErrors, function(index, value) {
            var elem = $('#'+index+'Error');
            if(elem) {
                elem.html(value[0]);
                elem.addClass('errorLabel');
            }
        });
    }
}

var hasClientError = false;
function removeClientErrors () {

    $('#clientErrorList').html('');
    $('#serverErrorList').html('');
    $('#serverMessageList').html('');

    var uiElm = getElement('clientErrorList');
    if( uiElm == null)  {
        return false;
    }
    uiElm.parentNode.style.height = "20px";
    hasClientError = false;
    return true;

//    var uiElm = getElement('clientErrorList');
//    if( uiElm == null)  {
//        return false;
//    }

//    var childLen = uiElm.childNodes.length;
//
//    if(childLen > 1){
//
//        for(var i = 1; i < childLen; i++){
//            var chileEl = uiElm.childNodes[1];
//            uiElm.removeChild(chileEl);
//        }
//    }
//    uiElm.parentNode.style.height = "20px";
//    hasClientError = false;
//    return true;
}

function addClientError (errMessage) {

    if (errMessage == null) {
        errMessage = 'You must fill all marked (*) fields.';
    }

    $('#clientErrorList').append('<li>'+errMessage+'</li>\n');

    var uiElm = getElement('clientErrorList');
    if( uiElm == null)  {
        return false;
    }
    uiElm.parentNode.style.height = parseInt(uiElm.parentNode.style.height) + 18 + "px";
    hasClientError = true;
    return true;

//    var uiElm = getElement('clientErrorList');
//    if( uiElm == null)  {
//        return false;
//    }
//
//    if (errMessage == null) {
//        errMessage = 'You must fill all marked (*) fields.';
//    }
//    var newChild = document.createElement("li");
//
//    if(browserIsIe())
//        newChild.innerText = errMessage;
//    else
//        newChild.textContent = errMessage;
//
//    uiElm.appendChild(newChild);
//    uiElm.parentNode.style.height = parseInt(uiElm.parentNode.style.height) + 18 + "px";
//    hasClientError = true;
//    return true;
}

function isNullOrEmpty(strValue) {
    return strValue == null || strValue.length == 0 || trim(strValue) == '';


}

function trim(str) {
    var temp = "";
    for (var j = 0; j < str.length; j++)
        if (str.substring(j, j + 1) != " ")
            temp += str.substring(j, j + 1);
    return temp;
}

function isCtrlPressed(e) {
    return e.ctrlKey;
}

function isAltPressed(e) {
    return e.altKey;
}

function isShiftPressed(e) {
    return e.shiftKey;
}

function isCopyPressed(e) {
    return e.ctrlKey && getEventKeyCode(e) == 99;
}

function isPastePressed(e) {
    return e.ctrlKey && getEventKeyCode(e) == 118;
}

function isNumericKeysPressed(keyCode) {

    if (keyCode >= 48 && keyCode <= 57) { // Numbers
        return true;
    }

    return false;
}

function ignoreKeys(keyCode) {  // function keys and arrow keys

    if("0,8,9,13,32".indexOf(keyCode) >= 0) // 8 = 'backspace'    9 = 'tab'  13='enter'  32 = 'space'
        return true;

    return false;
}

function getEventKeyCode(e) {
    var keyCode;
    if (window.event)
        keyCode = event.keyCode;
    else
        keyCode = e.which;

    return keyCode;
}

function isNumeric(val) {
    var chkExp = /^\d+$/;
    return chkExp.test(val);
}

function isText(str) {
    var chkExp = /^[a-z][a-z\d]+$/i;
    return chkExp.test(str);
}

function isEmailAddress(emailStr) {
    var matchArray = emailStr.match(/^(.+)@(.+)\.(.+)$/)
    return matchArray != null;

}

function numericOnKeyPress(e) {

    var keyCode = getEventKeyCode(e);
    if("8,13,9,46".indexOf(keyCode) >= 0) {// 8 = backspace , 13 = enter ,  9 = tab ,  46 = '.'
        return true;
    }
    if (isNumericKeysPressed(keyCode))
        return true;

    if (window.event) //IE
        window.event.returnValue = false;
    else              //other browser
        e.preventDefault();

    return false;
}

function isPersianKeysPressed(keyCode) {

    for (var i = 0; i < farsiKeyCodes.length; i++) {
        if (keyCode == farsiKeyCodes[i]) {
            return true;
        }
    }
    return false;
}

function persianOnKeyPress(e) {

    var keyCode = getEventKeyCode(e);
    if (isPersianKeysPressed(keyCode) || ignoreKeys(keyCode))
        return true;
    if (window.event) //IE
        window.event.returnValue = false;
    else              //other browser
        e.preventDefault();

    return false;
}

function persianAndNumberOnKeyPress(e) {

    var keyCode = getEventKeyCode(e);
    if("32,44,45,46,58,59,95".indexOf(keyCode) >= 0) // 32 = 'space'  44= ','   45 = '-'  46 = '.'  58 = ':'  59 = ';'     95 = '_'
        return true;

    if (isPersianKeysPressed(keyCode) || isNumericKeysPressed(keyCode) || ignoreKeys(keyCode))
        return true;

    if (window.event) //IE
        window.event.returnValue = false;
    else              //other browser
        e.preventDefault();

    return false;
}

function isPersianCharacter(chr) {

    for (var i = 0; i < farsiKeyCodes.length; i++) {
        if (chr == farsiKeyCodes[i]) {
            return true;
        }
    }
    return false;
}

function isPersianText(txt){

    var isPersian = true;
    var keyCode = 0;
    for(var i=0; i < txt.length && isPersian; i++){
        keyCode = txt.charCodeAt(i);
        if (!isPersianCharacter(keyCode)){
            if (keyCode != 32)
                isPersian = false;
        }
    }
    return isPersian;
}

function isPersianAndNumberText(txt){

    var isPersianAndNumber = true;
    var keyCode = 0;
    for(var i=0; i<txt.length && isPersianAndNumber; i++){

        keyCode = txt.charCodeAt(i);
        if("32,44,45,46,58,59,95".indexOf(keyCode)>= 0){ // 32 = 'space' 44= ',' 45 = '-'  46 = '.'  58 = ':'  59 = ';'  95 = '_'
            continue;
        }
        if (!isPersianCharacter(keyCode) && !isNumeric(keyCode))
            isPersianAndNumber = false;
    }
    return isPersianAndNumber;
}

function isEnglishKeysPressed(keyCode) {

    if (keyCode >= 65 && keyCode <= 90) {
        return true;
    }
    if (keyCode >= 97 && keyCode <= 122) {
        return true;
    }

    return false;
}

function englishOnKeyPress(e) {

    var keyCode = getEventKeyCode(e);

    if (isEnglishKeysPressed(keyCode) || ignoreKeys(keyCode))
        return true;

    if (window.event) //IE
        window.event.returnValue = false;
    else              //other browser
        e.preventDefault();

    return false;
}

function englishAndNumberOnKeyPress(e) {

    var keyCode = getEventKeyCode(e);

    if("32,44,45,46,58,59,95".indexOf(keyCode)>= 0) // 32 = 'space' 44= ',' 45 = '-'  46 = '.'  58 = ':'  59 = ';'  95 = '_'
        return true;

    if (isEnglishKeysPressed(keyCode) || isNumericKeysPressed(keyCode) || ignoreKeys(keyCode))
        return true;

    if (window.event) //IE
        window.event.returnValue = false;
    else              //other browser
        e.preventDefault();

    return false;
}

function notSpecialCharacterOnKeyPress(e) {

    var keyCode = getEventKeyCode(e);
    if("33,34,35,36,37,38,39,40,41,42,43,47,58,59,60,61,62,63,64,91,92,93,94,123,124,125,126".indexOf(keyCode)>= 0)
        return false;

    if(keyCode >128 && keyCode < 255 )
        return false;
    return true;
}

function isEnglishCharacter(chr) {

    if ((chr >= 65 && chr <= 90) || (chr >= 97 && chr <= 122)) {
        return true;
    }
    return false;
}

function isEnglishText(txt){

    var isEnglish = true;
    var keyCode = 0;
    for(var i=0; i<txt.length && isEnglish; i++){
        keyCode = txt.charCodeAt(i);
        if (!isEnglishCharacter(keyCode)){
            if (keyCode != 32)   // 32 = space character
                isEnglish = false;
        }
    }
    return isEnglish;
}

function isEnglishAndNumberText(txt){

    var isEnglishAndNumber = true;
    var keyCode = 0;
    for(var i=0; i<txt.length && isEnglishAndNumber; i++){

        keyCode = txt.charCodeAt(i);
        if("32,44,45,46,58,59,95".indexOf(keyCode)>= 0){ // 32 = 'space'  44= ','  45 = '-'  46 = '.'   58 = ':'  59 = ';' 95 = '_'
            continue;
        }
        if (!isEnglishCharacter(keyCode) && !isNumeric(keyCode))
            isEnglishAndNumber = false;
    }
    return isEnglishAndNumber;
}

function hasSpecialCharacter(txt){

    var specialCharacter = false;
    var keyCode = 0;
    for(var i=0; i<txt.length && !specialCharacter; i++){

        keyCode = txt.charCodeAt(i);
        if("33,34,35,36,37,38,39,40,41,42,43,47,58,59,60,61,62,63,64,91,92,93,94,123,124,125,126".indexOf(keyCode)>= 0)
            specialCharacter = true;
        if(keyCode >128 && keyCode < 255 )
            specialCharacter = true;
    }
    return specialCharacter;
}

function emailOnKeyPress(e) {

    var keyCode = getEventKeyCode(e);

    if("45,46,64,95".indexOf(keyCode)>= 0) {   // 45 = '-'  46 = '.'   64 = '@'  95 = '_'
        return true;
    }
    if (isEnglishKeysPressed(keyCode) || isNumericKeysPressed(keyCode)) { // english characters & numbers
        return true;
    }

    if (window.event) //IE
        window.event.returnValue = false;
    else            //other browser
        e.preventDefault();

    return false;
}

function doNotLetPaste(e, en, fa, num, space) {

    if (isCopyPressed(e) || isPastePressed(e))
        return false;

    var keyCode = getEventKeyCode(e);
    if (ignoreKeys(keyCode))
        return true;

    if (space && keyCode == 32) //space character
        return true;

    if (en && isEnglishKeysPressed(keyCode)) {
        return true;
    }
    if (fa && isPersianKeysPressed(keyCode)) {
        return true;
    }

    if (num && isNumericKeysPressed(keyCode))
        return true;

    if (window.event) //IE
        window.event.returnValue = false;
    else              //other browser
        e.preventDefault();

    return false;
}

function replaceString(base, find, replace) {
    var temp = "";
    for (var j = 0; j < base.length; j++) {
        var character = base.substring(j, j + 1);
        if (character == find)
            temp += replace;
        else
            temp += character;
    }
    return temp;
}

function getSelectValue(cmbId) {

    var el = getElement(cmbId);
    if (el.selectedIndex != -1) {
        return el.options[el.selectedIndex].value;
    }
    return "";
}

function getSelectText(cmbId) {

    var el = getElement(cmbId);
    if (el.selectedIndex != -1) {
        return el.options[el.selectedIndex].text;
    }
    return "";
}

function getRadioOptionValue(optId) {
    var el;
    if (document.getElementsByTagName)
        el = document.getElementsByName(optId);
    else
        el = document.all[optId];

    if (el.length == 0) return "";
    for (var i = 0; i < el.length; i++) {
        if (el[i].checked)
            return el[i].value;
    }
    return "";
}

function setRadioOptionValue(optId  , value) {
    var el;
    if (document.getElementsByTagName)
        el = document.getElementsByName(optId);
    else
        el = document.all[optId];

    if (el.length == 0) return ;
    for (var i = 0; i < el.length; i++) {
        if (el[i].value == value) {
            el[i].checked = true;
            return;
        }
    }
}

function changeSelectIndex(cmbId, newValue) {
    if (newValue == null) return;
    var el;
    if (document.getElementsByTagName)
        el = document.getElementsByName(cmbId);
    else
        el = document.all[cmbId];

    if (el.length == 0) return;

    var inps = el[0];
    for (var i = 0; i < inps.length; i++) {
        if (inps.options[i].value == newValue) {
            inps.selectedIndex = i;
            break;
        }
    }
}

function checkRadioOption(optName, optvalue) {
    var inps;
    if (document.getElementsByTagName)
        inps = document.getElementsByName(optName);
    else
        inps = document.all[optName];
    for (var i = 0; i < inps.length; i++) {
        if (inps[i].value == optvalue)
            inps[i].checked = true;
    }
}

function jQueryMaskCalendar()
{
    $(function()
    {
        $('.calendar').calendarsPicker({ calendar: $.calendars.instance('persian')
        });
        $('.calendar').mask("9999/99/99");

    }
            );
    try {
        $('.calendar').blur(function()
        {
            isDirtyPage = true;
            suppCountChange = true;

            if (this != '') {
                if (this.value.length != 10) {
                    this.value = '';
                }
                else {
                    if (!isValidDate(this.value)) {
                        this.value = '';
                    }
                }
            }
        });
    } catch(e){

    }
}

function isValidDate(dateStr)
{

    if (dateStr.length == 10 && dateStr.charAt(4) == '/' && dateStr.charAt(7) == '/') {
        var year = dateStr.substring(0, 4);
        var month = dateStr.substring(5, 7);
        var day = dateStr.substring(8, 10);

        if (!isNumeric(year) || !isNumeric(month) || !isNumeric(day))
            return false;
        if (!(month > 0 && month < 13) || !(day > 0 && day <= 31))
            return false;
    }
    else {
        return false;
    }
    return true;
}

function isValidTime(timeStr) {

    if (timeStr.length < 5) {
        return false;
    }
    if (timeStr.length > 5 && timeStr.length != 8) {
        return false;
    }

    if (timeStr.charAt(2) != ':') {
        return false;
    }
    if (timeStr.length == 8 && timeStr.charAt(5) != ':') {
        return false;
    }

    var hour = timeStr.substring(0, 2);
    var min = timeStr.substring(3, 5);
    if (!isNumeric(hour) || !isNumeric(min))
        return false;

    if (!(parseInt(hour) >= 0 && parseInt(hour) < 24) || !(parseInt(min) >= 0 && parseInt(min) <= 60))
        return false;

    if(timeStr.length == 8) {
        var sec = timeStr.substring(7, 8);

        if (!isNumeric(sec))
            return false;
        if (!(parseInt(sec) >= 0 && parseInt(sec) <= 60))
            return false;
    }
    return true;
}

function addDateSeparator(dateStr) {

    return addDateSeparator(dateStr,'yyyy/mm/dd') ;
}

function addDateSeparator(dateStr, dateFormat) {

    var dateFormatStr = 'yyyy/mm/dd';
    if(!isNullOrEmpty(dateFormat)){
        dateFormatStr = dateFormat;
    }
    if (!isNumeric(dateStr)) {
        return dateStr;
    }
    if (dateStr.length != 8) {
        return dateStr;
    }
    var year = dateStr.substring(0, 4);
    var month = dateStr.substring(4, 6);
    var day = dateStr.substring(6, 8);

    if (!(month > 0 && month < 13) || !(day > 0 && day <= 31)){
        return dateStr;
    }
    if(dateFormatStr == "yyyy/mm/dd"){

        return year + "/" + month + "/" + day;
    }
    else if(dateFormatStr == "dd/mm/yyyy"){

        return day + "/" + month + "/" + year;
    }
    else if(dateFormatStr == "mm/dd/yyyy"){

        return month + "/" + day + "/" + year;
    }
    else{
        return dateStr;
    }

    function dateFormatter(cellvalue, options, rowObject) {

        var formattedText = "";
        if(cellvalue != null && cellvalue != 0){
            formattedText = addDateSeparator(cellvalue.toString());
        }
        return formattedText;
    }

    function formatBooleanValues(objValue) {

        var formattedText = "";
        if(objValue != null){
            if(objValue == 0)
                formattedText = '<s:text name="label.no"/>';
            else if(objValue == 1)
                formattedText = '<s:text name="label.yes"/>';
        }
        return formattedText;
    }


    // add option to Specified Index of List
    function addOptionToSpecifiedIndexOfSelectForNewBrowsers(selectObject,selectIndex, label, value) {

        if(selectObject == null){
            return -1;
        }
        var newOption = document.createElement('option');
        newOption.text = label;
        newOption.value = value;
        try {
            if(selectObject.options.length == 0) {
                selectObject.add(newOption);
            }
            else {
                var oldOption = selectObject.options[selectIndex];
                selectObject.add(newOption, oldOption);
            }
            return 0;
        }
        catch(ex) {
            return -1;
        }
    }


    function addOptionToSpecifiedIndexOfSelect(selectObject,selectIndex, newText, newValue) {
        if (selectObject.length == 0) {

            var newOption = new Option(newText, newValue);
            selectObject.options[0] = newOption;
            selectObject.selectedIndex = 0;

        }
        else if (selectObject.selectedIndex != -1) {
            var selText = [];
            var selValues = [];
            var newCount = -1;
            var i;
            for(i = 0; i < selectObject.length; i++) {
                newCount++;
                if (newCount == selectIndex) {
                    selText[newCount] = newText;
                    selValues[newCount] = newValue;
                    newCount++;
                }
                selText[newCount] = selectObject.options[i].text;
                selValues[newCount] = selectObject.options[i].value;
            }
            for(i = 0; i <= newCount; i++) {
                var newOption = new Option(selText[i], selValues[i]);
                selectObject.options[i] = newOption;
            }
        }
    }


    function removeSpecifiedOptionOfSelect(elSelectObject,selectIndex) {

        if (selectIndex < 0 || (selectIndex > elSelectObject.length - 1)) {
            return -1;
        }
        elSelectObject.remove(selectIndex);
        return 0;
    }

    function disableHistory() {
        history.pushState(null, null, null);
        window.addEventListener('popstate', function (event) {
            history.pushState(null, null, null);
        });
    }

// add option to select list
    function appendOptionToSelect(elSelectObject, label, value) {
        var elOptNew = document.createElement('option');
        elOptNew.text = label;
        elOptNew.value = value;

        try {
            elSelectObject.add(elOptNew, null); // standards compliant; doesn't work in IE
        }
        catch(ex) {
            elSelectObject.add(elOptNew); // IE only
        }
    }

// add option to Specified Index of List
    function addOptionToSpecifiedIndexOfSelect(selectObject,selectIndex, label, value) {

        if(selectObject == null){
            return -1;
        }
        var newOption = document.createElement('option');
        newOption.text = label;
        newOption.value = value;
        try {
            if(selectObject.options.length == 0) {
                selectObject.add(newOption);
            }
            else {
                var oldOption = selectObject.options[selectIndex];
                selectObject.add(newOption, oldOption);
            }
            return 0;
        }
        catch(ex) {
            return -1;
        }
    }


    //removed last item from a select element
    function removeLastIndexOfSelectOption(elSelectObject) {
        if (elSelectObject.length > 0) {
            elSelectObject.remove(elSelectObject.length - 1);
        }
    }

    //remove an option from specific position
    function removeSelectedOption(elSelectObject) {
        var i;
        for (i = elSelectObject.length - 1; i >= 0; i--) {
            if (elSelectObject.options[i].selected) {
                elSelectObject.remove(i);
            }
        }
    }

    function removeSpecifiedOptionOfSelect(elSelectObject,selectIndex) {

        if (selectIndex < 0 || (selectIndex > elSelectObject.length - 1)) {
            return -1;
        }
        elSelectObject.remove(selectIndex);
        return 0;
    }

    function controlKeyPress(e,acceptPersianKeys,acceptEnglishKeys,acceptNumbers,acceptIgnoreKeys) {

        if (isCopyPressed(e) || isPastePressed(e))
            return;

        var key = getEventKeyCode(e);
        if (key == spaceCode)return true;//space

        if (acceptIgnoreKeys) {
            if (ignoreKeys(key)) return true;
            if (key == 45 || key == 44 || key == 46) return true; // ','  '.'  '-'
        }

        if (acceptPersianKeys) {
            for (var i = 0; i < farsiKeyCodes.length; i++) {
                if (key == farsiKeyCodes[i]) {
                    return true;
                }
            }
        }

        if (acceptEnglishKeys) {
            if (key >= 65 && key <= 90) {
                return true;
            }
            if (key >= 97 && key <= 122) {
                return true;
            }
        }

        if (acceptNumbers && isNumericKeysPressed(key)) {
            return true;
        }

        if (window.event) //IE
            window.event.returnValue = false;
        else              //other browser
            e.preventDefault();

    }

    function hasValidAlphabetCharacters(str, acceptPersianKeys,acceptEnglishKeys,acceptNumbers, acceptIgnoreKeys) {

        if(str.length == 0){
            return true;
        }
        var isValidChar = false;
        for (var i = 0; i < str.length; i++) {

            isValidChar = false;

            var chr = str.substring(i, i + 1).charCodeAt(0);

            if (chr == spaceCode)
                isValidChar = true;

            if (acceptIgnoreKeys) {

                if (chr == 45 || chr == 44 || chr == 46)   // ','  '.'  '-'
                    isValidChar = true;
            }

            if (acceptPersianKeys) {

                for (var j = 0; j < farsiKeyCodes.length; j++) {
                    if (chr == farsiKeyCodes[j]) {
                        isValidChar = true;
                        break;
                    }
                }
            }

            if (acceptEnglishKeys) {
                if (chr >= 65 && chr <= 90) {
                    isValidChar = true;
                }
                if (chr >= 97 && chr <= 122) {
                    isValidChar = true;
                }
            }

            if (acceptNumbers && isNumericKeysPressed(chr)) {
                isValidChar = true;
            }

            if(!isValidChar){
                return false;
            }
        }
        return isValidChar;
    }

}

