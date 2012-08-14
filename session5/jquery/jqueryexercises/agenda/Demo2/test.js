
CUSTOM SELECTOR
1. Add class 'odd' to all the odd rows.
2. Add class 'even' to all the even rows.

DOM TRAVERSAL
Add class 'table-heading' to table header.

chaining

1. Get the cell containing Henry and then get its siblings (not just the next
   sibling). Add the Class:

2. Get the cell containing Henry, get its parent, and then find all cells inside it
   that are greater than 0 (where 0 is the first cell). Add the Class:

3. Get the cell containing Henry, get its parent, find all cells inside it, and then
   filter those to exclude the one containing Henry. Add the Class:

4. Get the cell containing Henry, get its parent, find the second cell among the
   children, add the Class, cancel the last .find(), find the third cell among the
   children, and add the Class:




































//Answer
//CUSTOM SELECTOR
1. $("tr:odd").addClass('odd')
2. $("tr:even").addClass('even')

//DOM TRAVERSAL
1. $("th").addClass('table-heading')

//Chaining
1. $("td:contains('Henry')").siblings()
2. $("td:contains('Henry')").parent().find('td:gt(0)')
3. $("td:contains('Henry')").parent().find('td').filter(':contains("Henry")')
4. $("td:contains('Henry')").parent().find('td:not(:contains("Henry"))')
