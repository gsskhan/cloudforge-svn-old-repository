<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<!DOCTYPE html>

<div class="ui-widget ui-widget-content ui-corner-all" style="width: 100%;height:100%;min-height:700px">
	<s:actionmessage theme="jquery" />
	<s:actionerror theme="jquery" />
	<!-- Form to upload new paper & check pending workflows -->
	<div id="paper-wf-container" align="left" style="font-size: 12px;">
		<div style="float: left; width: 40%; height: 250px; overflow: scroll; margin-left: 2px;" class="ui-widget-content ui-corner-all">
			<p align="center">Please fill-in the form and press 'Save' to upload new document.</p>
			<hr />
			<s:form action="addNewPaper" enctype="multipart/form-data"
				method="post">
				<sj:textfield name="papertitle" label="Paper Title" labelposition="left" requiredLabel="true" required="true" />
				<s:file name="fileUpload" label="File" requiredLabel="true" labelposition="left" required="true" />
				<s:checkbox name="authrequired" label="Authorization Required" requiredLabel="true" fieldValue="true" value="true" labelposition="left" required="true" />
				<s:div>
					<s:url var="ajaxurl" action="getAuthorizerNamesList">
						<s:param name="creatorname">
							<s:property value="#session.uname" />
						</s:param>
					</s:url>
					<sj:select href="%{ajaxurl}" name="authorizerName"
						list="authorizersNameList" headerKey="-1"
						headerValue="-- SELECT --" required="true" labelposition="left"
						label="Authorizer's Name" />
				</s:div>
				<s:textfield name="ownername" label="Owner" labelposition="left" readonly="true" required="true">
					<s:param name="value">
						<s:property value="#session.uname" />
					</s:param>
				</s:textfield>
				<sj:submit value="Save" button="true" indicator="indicator"
					cssStyle="width:100px;" />
			</s:form>
		</div>
		<div style="float: right; width: 59%; height: 250px; vertical-align: top; text-align: left; overflow: scroll;" class="ui-widget-content ui-corner-all">
			<p align="center">Workflow(s) pending for action</p>
			<hr />
			<div class="report-download">
				<s:url id="pendingwfurl" value="pendingWorkflowActions" />
				<s:a href="%{pendingwfurl}" button="false">Find Workflows</s:a>
			</div>
			<table style="font-size: 10px; width: 100%">
				<thead class="ui-widget-header ui-corner-all">
					<tr align="center">
						<th>PAPER NUMBER</th>
						<th>PAPER VERSION</th>
						<th>PAPER TITLE</th>
						<th>PENDING FOR ACTION BY</th>
						<th>COMMENTS</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody class="ui-widget-content ui-corner-all">
					<s:iterator value="pendingActionWorkflowsList" var="wf">
						<tr align="center">
							<td><s:property value="paperNumber" /></td>
							<td><s:property value="paperVersion" /></td>
							<td><s:property value="paperTitle" /></td>
							<td><s:property value="pendingActionFromUsername" /></td>
							<td><s:property value="comments" /></td>
							<td><s:url id="url" action="">
									<s:param name="wfid">
										<s:property value="workflowId" />
									</s:param>
								</s:url> <s:a href="%{url}">Launch</s:a></td>
							<td>
						<tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
		<div style="clear: both;"></div>
	</div>
	<!-- Form to check workflow -->
	<div id="check-workflow" align="center" style="font-size: 12px">
		<p align="center">Work on workflow</p>
		<hr />
	</div>
</div>
