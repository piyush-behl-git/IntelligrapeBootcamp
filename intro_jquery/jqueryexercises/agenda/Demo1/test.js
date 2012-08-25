CSS Selector
Add class "horizontal" to all the LI elements of the UL element having id 'selected-plays'.
change background color, border, padding of the div with id 'sampleDiv'.

Attribute selector
Add class "pdflink" to all the hyperlinks which are pointing to pdf file.
Add class "mailto" to all the mail hyperlinks(clicking which will open your mail client with TO field filled in).
Make all the external links to open in a new window when clicked.
Add class 'mysite' to all the hyperlinks having domain mysite.com.



































//Answer
1. $("#selected-plays > li").addClass("horizontal")
2. $("#sampleDiv").css({'border':'1px solid #000','backgroundColor':'red','padding':'5px'});
3. $("a[href$='.pdf']").addClass("pdflink")
4. $("a[href^='mailto']").addClass("mailto")
5. $("a").attr('target','blank')
6. $("a[href*='mysite.com']").addClass('mysite')
