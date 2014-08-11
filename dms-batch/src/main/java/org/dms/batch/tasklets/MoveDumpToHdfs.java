package org.dms.batch.tasklets;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class MoveDumpToHdfs implements Tasklet{
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
		this.copyFromLocalToHdfs();
		return RepeatStatus.FINISHED;
	}

	private void copyFromLocalToHdfs() {		
		try {
			log.info("started: move database dump files to HDFS.");
			FileSystem hdfs = FileSystem.get(new Configuration());
			Path hdfsHomeDir = hdfs.getHomeDirectory();
			Path workingDir = hdfs.getWorkingDirectory();
			log.info("found HDFS home directory as "+ hdfsHomeDir);
			log.info("found HDFS working directory as "+ workingDir);
			Path newFolderPath= new Path("/DmsDataFolder");
			newFolderPath = Path.mergePaths(workingDir, newFolderPath);
			if(hdfs.exists(newFolderPath)) {
			      hdfs.delete(newFolderPath, true); //Delete existing Directory
			}
			hdfs.mkdirs(newFolderPath);     //Create new Directory
			Path localFilePath = new Path("/tmp/tab_system_users.txt");
			Path hdfsFilePath=new Path(newFolderPath+"/tab_system_users.txt");
			hdfs.copyFromLocalFile(localFilePath, hdfsFilePath);
			log.info("finished: moving database dump files to HDFS.");
		} catch (Exception e) {
			log.error("error: moving database dump files to HDFS.",e);
		}		
	}

}
