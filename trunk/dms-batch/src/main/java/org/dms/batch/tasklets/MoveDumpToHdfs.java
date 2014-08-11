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
	
	private String hdfsUrl ="hdfs://localhost:9000";
	private String sourceFilename = "/tmp/tab_system_users.txt";
	
	@Override
	public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
		this.copyFromLocalToHdfs();
		return RepeatStatus.FINISHED;
	}

	private void copyFromLocalToHdfs() {		
		try {
			log.info("started: move database dump files to HDFS.");
			Configuration conf = new Configuration();
			conf.set("fs.defaultFS", this.hdfsUrl);
			FileSystem hdfs = FileSystem.get(conf);
			Path hdfsHomeDir = hdfs.getHomeDirectory();
			Path workingDir = hdfs.getWorkingDirectory();
			log.info("found HDFS home "+ hdfsHomeDir+", working directory "+workingDir);
			Path newFolderPath= new Path("/dms");
			newFolderPath = Path.mergePaths(workingDir, newFolderPath);
			if(hdfs.exists(newFolderPath)) {
			      hdfs.delete(newFolderPath, true); //Delete existing Directory
			}
			hdfs.mkdirs(newFolderPath); //Create new Directory
			log.info("created hdfs path "+ newFolderPath);
			Path localFilePath = new Path(sourceFilename);
			Path hdfsFilePath=new Path(newFolderPath+"/tab_system_users.txt");
			hdfs.copyFromLocalFile(localFilePath, hdfsFilePath);
			log.info("moved file to hdfs path "+ hdfsFilePath);
			log.info("finished: moving database dump files to HDFS.");
		} catch (Exception e) {
			log.error("error: moving database dump files to HDFS.",e);
		}		
	}

}
