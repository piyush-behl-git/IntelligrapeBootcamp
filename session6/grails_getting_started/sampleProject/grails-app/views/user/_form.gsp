<%@ page import="com.ig.bc.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastLogin', 'error')} ">
	<label for="lastLogin">
		<g:message code="user.lastLogin.label" default="Last Login" />
		
	</label>
	<g:field name="lastLogin" type="number" value="${userInstance.lastLogin}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastCreated', 'error')} ">
	<label for="lastCreated">
		<g:message code="user.lastCreated.label" default="Last Created" />
		
	</label>
	<g:datePicker name="lastCreated" precision="day"  value="${userInstance?.lastCreated}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastUpdate', 'error')} ">
	<label for="lastUpdate">
		<g:message code="user.lastUpdate.label" default="Last Update" />
		
	</label>
	<g:datePicker name="lastUpdate" precision="day"  value="${userInstance?.lastUpdate}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'age', 'error')} required">
	<label for="age">
		<g:message code="user.age.label" default="Age" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="age" type="number" value="${userInstance.age}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="user.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${userInstance?.name}"/>
</div>

