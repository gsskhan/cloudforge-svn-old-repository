<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<!DOCTYPE html>

<div class="manage-paper-container ui-widget ui-widget-content ui-corner-all">
	<s:actionmessage theme="jquery" />
	<s:actionerror theme="jquery" />
		<!-- Form to find a paper -->
	<div class="find-paper-container ui-widget-content ui-corner-all">
		<h4 align="center">Search papers (Please enter paper number and version)</h4>
		<s:form action="findPaperAction">
				Paper Number:<s:textfield name="searchpapernumber" theme="simple"/>
				<s:submit value="Find Paper" theme="simple" />
		</s:form>
	</div>
	<!-- Form to upload new paper & check pending workflows -->
	<div id="paper-wf-container" align="left" style="font-size: 12px;">
		<div class="paper-container ui-widget-content ui-corner-all">
			<h4 align="center">Please fill-in the form and press 'Save' to upload new document.</h4>
			<hr/><br/><br/>
			<s:form action="addNewPaper" enctype="multipart/form-data" method="post">
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
		<div class="wf-container ui-widget-content ui-corner-all">
			<h4 align="center">Workflow(s) pending for action</h4>
			<hr />
			<div class="report-download">
				<s:url id="pendingwfurl" value="pendingWorkflowActions" />
				<s:a href="%{pendingwfurl}" button="false">Find Workflows</s:a>
			</div>
			<table style="font-size: 10px; width: 100%">
				<thead class="ui-widget-header ui-corner-all">
					<tr align="center">
						<th>PAPER NUMBER</th>
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
	<div class="paper-manage-body ui-widget-content ui-corner-all">
		<h4 align="center">Section for completing paper. Please select the incomplete workflow(s), fill details and submit.</h4>
		<hr />
			<div style="float: left; width: 50%;" align="left">
			<s:form>
				<sj:textfield name="number" label="Paper Number" labelposition="left" readonly="true" />
				<sj:textfield name="uploadedfilename" label="Uploaded Filename" labelposition="left" readonly="true" />
				<sj:textfield name="title" label="Title" labelposition="left" requiredLabel="true" required="true" />
				<sj:textfield name="creatorname" label="Creator" labelposition="left" readonly="true" />
				<sj:datepicker name="creationtime" label="Creation Time" labelposition="left" readonly="true" />
				<sj:textfield name="authorizername" label="Authorizer" labelposition="left" readonly="true" />
				<sj:datepicker name="authorizationtime" label="Authorization Time" labelposition="left" readonly="true" />
				<sj:textfield name="approvername" label="Approver" labelposition="left" readonly="true" />
				<sj:datepicker name="approvaltime" label="Approval Time" labelposition="left" readonly="true" />
				<sj:textfield name="rejectorname" label="Rejector" labelposition="left" readonly="true" />
				<sj:datepicker name="rejectiontime" label="Rejection Time" labelposition="left" readonly="true" />
				<sj:radio name="action" label="Action" list="{'Authorize', 'Approve', 'Reject'}" />
				<sj:textarea name="remarks" label="Remarks" rows="2" cols="45"/>
				<sj:submit value="Process" button="true" cssStyle="width:100px;" />
			</s:form>
			</div>
			<div style="float: right; width: 50%;">
			<div style="background-color:#E1F0FF;margin:2px;">Workflow History</div>
			<table style="font-size: 10px; width: 100%">
				<thead class="ui-widget-header ui-corner-all">
					<tr align="center">
						<th>ASSIGNMENT TIME</th>
						<th>ASSIGNED TO USER</th>
						<th>COMPLETION TIME</th>
						<th>COMPLETED BY USER</th>
						<th>STATUS</th>
					</tr>
				</thead>
				<tbody class="ui-widget-content ui-corner-all">
					<s:iterator value="pendingActionWorkflowsList" var="wf">
						<tr align="center">
							<td><s:property value="paperNumber" /></td>
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
</div>
