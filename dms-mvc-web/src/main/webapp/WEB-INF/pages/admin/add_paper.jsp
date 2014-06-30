<form action="upload-new-paper" method="post" enctype="multipart/form-data"
		class="form-horizontal" style="font-size: 10px;">
	<div class="form-group" style="width: 40%">	
		<label for="paper-title-id" class="control-label">Paper Title:</label> 
		<input type="text" name="uname" id="paper-title-id" required="required" class="form-control" />	
		
		<label for="fileupload" class="control-label">Select file:</label> 
		<input id="fileupload" type="file" name="newfile" class="form-control" >
				
		<input type="text" name="username" value="${sUserName}" hidden="false">
		<br>			
		<input type="submit" value="Upload" class="btn btn-primary btn-sm" />
	</div>
</form>