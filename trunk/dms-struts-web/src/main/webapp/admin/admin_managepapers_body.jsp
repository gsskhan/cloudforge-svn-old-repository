<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<!DOCTYPE html>

<div class="ui-widget ui-widget-content ui-corner-all" style="width: 100%;height:100%;min-height:700px">
	<s:actionmessage theme="jquery"/>	
	<s:actionerror theme="jquery" />	
	<br/>
	<sj:tabbedpanel id="localtabs" selectedTab="0" show="'fold'" collapsible="true">
		<sj:tab id="tab1" target="new-paper-upload" label="Upload (New) Paper" />
		<sj:tab id="tab2" target="check-workflow" label="Check Workflows" />
		
		<!-- Form to upload new paper -->
     	<div id="new-paper-upload" align="left" style="font-size: 12px">
     		<p align="center">Select the file, fill out details and press 'Save' to upload new document.</p>
      		<hr/>
      		<s:form action="addNewPaper" enctype="multipart/form-data" method="post">
      				<sj:textfield name="papertitle" label="Paper Title" labelposition="left" requiredLabel="true" required="true"/>
      				<s:file name="fileUpload" label="File" requiredLabel="true" labelposition="left" required="true"/>
      				<s:checkbox name="authrequired" label="Authorization Required" requiredLabel="true" fieldValue="true" value="true"
      							 labelposition="left" required="true" />
      				<s:div>
      					<s:url var="ajaxurl" action="getAuthorizerNamesList">
      					  	<s:param name="creatorname"><s:property value="#session.uname"/></s:param>
      					</s:url>
						<sj:select  
									href="%{ajaxurl}"
									name="authorizerName"
									list="authorizersNameList"
									headerKey="-1"
									headerValue="-- SELECT --"
									required="true"
									labelposition="left"
									label="Authorizer's Name"
									/>
      				</s:div>
      				<s:textfield name="ownername" label="Owner" labelposition="left" readonly="true" required="true" >
      					<s:param name="value"><s:property value="#session.uname"/></s:param>
      				</s:textfield>
      				<sj:submit value="Save" button="true" indicator="indicator" cssStyle="width:100px;"/>
      		</s:form>
     	</div>
     	<!-- Form to check workflow -->
     	<div id="check-workflow" align="center" style="font-size: 12px">
     		<p align="center">System workflows</p>
      			<hr/>
     	</div>
	</sj:tabbedpanel>
</div>
