function XmlHttpPost(url, params, returnFunction) {
    this.Send = function() {
        var Async = (returnFunction == null ? false : true);
        var objHTTP = XmlHttp.create();
        if (Async)objHTTP.onreadystatechange = function() {
            if (objHTTP.readyState == 4)returnFunction(objHTTP.responseText);
        };
        objHTTP.open("POST", url, Async);
        objHTTP.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        objHTTP.send(params);
        if (!Async)return objHTTP.responseText; else return true;
    }
}
function XmlHttpGet(url, returnFunction) {
    this.Send = function() {
        var Async = (returnFunction == null ? false : true);
        var objHTTP = XmlHttp.create();
        if (Async)objHTTP.onreadystatechange = function() {
            if (objHTTP.readyState == 4)returnFunction(objHTTP.responseText);
        };
        objHTTP.open("GET", url, Async);
        objHTTP.send(null);
        if (!Async)return objHTTP.responseText; else return true;
    }
}
function XmlHttp() {
}
;
XmlHttp.create = function() {
    try {
        if (window.XMLHttpRequest) {
            var req = new XMLHttpRequest();
            if (req.readyState == null) {
                req.readyState = 1;
                req.addEventListener("load", function() {
                    req.readyState = 4;
                    if (typeof req.onreadystatechange == "function")req.onreadystatechange();
                }, false);
            }
            ;
            return req;
        }
        ;
        if (window.ActiveXObject)return new ActiveXObject(getControlPrefix() + ".XmlHttp");
    } catch(ex) {
    }
};
function getControlPrefix() {
    if (getControlPrefix.prefix)return getControlPrefix.prefix;
    var prefixes = ["MSXML2","Microsoft","MSXML","MSXML3"];
    var o,o2;
    for (var i = 0; i < prefixes.length; i++) {
        try {
            o = new ActiveXObject(prefixes[i] + ".XmlHttp");
            o2 = new ActiveXObject(prefixes[i] + ".XmlDom");
            return getControlPrefix.prefix = prefixes[i];
        } catch(ex) {
        }
        ;
    }
}