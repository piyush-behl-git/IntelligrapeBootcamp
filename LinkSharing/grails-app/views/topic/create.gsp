<html>
<head>
    <title>Create Topic</title>
</head>

<body>
<div data-role="page" class="type-home" data-content-theme="c" data-theme="c">
    <div data-role="header" data-theme="b">
        <h1 class="ui-title" role="heading" aria-level="1">Enter Topic Details</h1>
    </div>

    <div id="topicStatusDiv"></div>
    <g:form controller="topic" action="save">
        <ul id="topicDetailsList" data-role="listview" data-theme="c">
            <li data-role="fieldcontain">
                <label for="name">Topic Name</label>
                <g:textField name="name" value=""/>
            </li>
            <li data-role="fieldcontain">
                <label for="visibility">Visibility</label>
                <g:select id="visibility" from="['PUBLIC', 'PRIVATE']" name="visibility" value=""/>
            </li>
            <li>
               <g:submitButton name="create" value="Create"/>
            </li>
        </ul>
    </g:form>
</div>
</body>
</html>