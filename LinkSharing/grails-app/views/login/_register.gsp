<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>
<g:form controller="login" action="registrationHandler" method="get" name="registrationForm">
    <ul data-role="listview" data-inset="true">
        <li data-role="list-divider" role="heading">
            Register
        </li>
        <li data-role="fieldcontain">
            <label for="fullName">Full Name</label>
            <g:textField name="fullName" placeholder="Full Name" value="" id="fullName"/>

        </li>

            <li data-role="fieldcontain">
                <label for="datepicker">Date Of Birth</label>
                <input type="date" id="datepicker" name="dateOfBirth" value="" default="none" placeholder="Date Of Birth" data-role="datebox"
                       data-options='{"mode":"slidebox"}' readonly="readonly"/>
            </li>

        <li data-role="fieldcontain">
            <label for="male">Male</label>
            <select name="male" id="male" data-role="slider">
                <option value="0">No</option>
                <option value="1">Yes</option>
            </select>
        </li>
        <li data-role="fieldcontain">
            <label for="email">Email</label>
            <g:field type="email" name="email" placeholder="Email" value="" id="email" required="true"/>
        </li>
        <li data-role="fieldcontain">
            <label for="password">Password</label>
            <g:passwordField name="password" id="password" value="" placeholder="Password" required="true"/>

        </li>
        <li data-role="fieldcontain">
            <label for="confirmPassword">Confirm Password</label>
            <g:passwordField name="confirmPassword" id="confirmPassword" placeholder="Confirm Password" value=""/>
        </li>
        <li data-role="fieldcontain">
            <g:submitButton name="Register" class="save" value="Register" data-transition="fade"/>
        </li>

    </ul>
</g:form>