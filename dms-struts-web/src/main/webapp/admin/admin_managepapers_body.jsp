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
		
		<!-- Form to upload new Document -->
     	<div id="new-paper-upload" align="center" style="font-size: 12px">
     		<p align="center">Select the file, fill out details and press 'Save' to upload new document.</p>
      			<hr/>
     	</div>
     	<!-- Form to check workflow -->
     	<div id="check-workflow" align="center" style="font-size: 12px">
     		<p align="center">System workflows</p>
      			<hr/>
     	</div>
	</sj:tabbedpanel>
</div>
