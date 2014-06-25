<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<!DOCTYPE html>

<div class="ui-widget ui-widget-content ui-corner-all" style="width: 100%;height:100%;min-height:700px;">
	<s:actionmessage theme="jquery"/>	
	<s:actionerror theme="jquery" />	
	<br/>
	<sj:tabbedpanel id="localtabs" selectedTab="0" show="'fold'" collapsible="true">
		<sj:tab id="tab1" target="newUploadDiv" label="Upload (New) Paper" />
		
		<!-- Form to upload new Document -->
     	<div id="newUploadDiv" align="center" style="font-size: 12px">
     		<p align="center">Select the file, fill out details and press "Save" to upload new document.</p>
      			<hr/>
     	</div>
	</sj:tabbedpanel>
</div>
