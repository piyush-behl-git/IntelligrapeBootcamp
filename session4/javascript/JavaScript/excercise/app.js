
function loadDate()  {
    alert( new Date());
}
function parseTF()  {
    var time=parseDate(getCurrentDateTime(),"d m, yyyy");
    loadText('timeDisp',time);

}

function parse(h) {
    switch (h) {
        case 'd':
            return zeroPad(date.getDate());
            break;
        case 'm':
            return zeroPad(date.getMonth() + 1);
            break;
        case 'yyyy':
            return date.getFullYear();
            break;
        case 'HH':
            return zeroPad(date.getHours() < 12 ? date.getHours() : date.getHours() - 12);
            break;
        case 'MM':
            return zeroPad(date.getMinutes());
            break;
        case 'ss':
            return zeroPad(date.getSeconds());
            break;
        case 'ap':
            return date.getHours() < 12 ? 'AM' : 'PM';
            break;
    }
}


function parseTWF() {
    var time=parseDate(getCurrentDateTime(),"HH:MM:ss ap");
    loadText('timeDisp',time);
}
function getCurrentDateTime() {
    return new Date();
}


function zeroPad(num) {
    return parseInt(num,10) < 10 ? '0' + num : num;
}


function parseDate(date, format) {
    //d m, yyyy HH:MM:ss d
    return format.replace(
        /(d)|(m)|(yyyy)|(HH)|(MM)|(ss)|(ap)/g,
        function (match) {
            switch (match) {
                case 'd':
                    return zeroPad(date.getDate());
                    break;
                case 'm':
                    return zeroPad(date.getMonth() + 1);
                    break;
                case 'yyyy':
                    return date.getFullYear();
                    break;
                case 'HH':
                    return zeroPad(date.getHours() < 12 ? date.getHours() : date.getHours() - 12);
                    break;
                case 'MM':
                    return zeroPad(date.getMinutes());
                    break;
                case 'ss':
                    return zeroPad(date.getSeconds());
                    break;
                case 'ap':
                    return date.getHours() < 12 ? 'AM' : 'PM';
                    break;
            }
        });
}

load = function () {
   // alert(parseDate(getCurrentDateTime(), "d m, yyyy HH:MM:ss ap"));
   // alert(parseDate(getCurrentDateTime(), "HH:MM:ss ap"));
    showTimeOnPage()
    pid=setInterval("showTimeOnPage(false)",2000);
    setTimeout("window.pid2 = setInterval('showTimeOnPage(true)',2000);",1000)

};

function loadText(id, text) {
     document.getElementById(id).innerHTML=text;
}
function showTimeOnPage(r) {

    var time=parseDate(getCurrentDateTime(),"HH:MM:ss ap");
    loadText('timeDisp',time);
    if(r) parseTF();
    else parseTWF();

}
function anim() {
    showTimeOnPage()
    pid=setInterval("showTimeOnPage(false)",1000);
    setTimeout("setInterval('showTimeOnPage(true)',1000);",1000)
}
function stopInterval() {
    clearInterval(pid)
}

function nameAlert() {
	name="Hello, "+prompt("Please enter your name","Name");
	loadText('nameDiv',name);
    document.getElementById('nameButton').style.display='none';
}
function copyText()  {

    origText = originalText.value;
    document.getElementById('copiedText').value=origText;
}
function loadColor()    {
    (document.getElementById('colorDiv')).style.backgroundColor='grey';
}
function resetColor(id) {
    (document.getElementById('colorDiv')).style.backgroundColor='transparent';
}
function validateUserForm() {
    if(document.forms["userForm"]["age"].value<18)  {
	    alert("Age must be greater than 18");
   	 return false;        
	}
    if(document.forms["myForm"]["name"].value==null)    {
   	 alert("Please enter name")
   	 return false;
    }
     return true;

}
