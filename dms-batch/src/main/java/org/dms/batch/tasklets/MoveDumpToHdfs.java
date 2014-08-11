package org.dms.batch.tasklets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.DFSClient;
import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class MoveDumpToHdfs implements Tasklet{
	
	private Logger log = Logger.getLogger(this.getClass());
	
	private String hdfsUrl ="hdfs://localhost:9000";
	private String destinationFilename = "/user/gsskhan/input/tab_system_users.txt";
	private String sourceFilename = "/tmp/tab_system_users.txt";
	
	@Override
	public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
		this.copyFromLocalToHdfs();
		return RepeatStatus.FINISHED;
	}

	private void copyFromLocalToHdfs() {		
		try {
			log.info("started: move database dump files to HDFS.");
			/*FileSystem hdfs = FileSystem.get(new Configuration());
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
			hdfs.copyFromLocalFile(localFilePath, hdfsFilePath);*/
			
			Configuration conf = new Configuration();
			conf.set("fs.defaultFS", this.hdfsUrl);
			DFSClient client = new DFSClient(new URI(this.hdfsUrl), conf);
			OutputStream out = null;
	        InputStream in = null;
	        try {
	            if (client.exists(destinationFilename)) {
	                System.out.println("File already exists in hdfs: " + destinationFilename);
	                //return;
	            }
	            out = new BufferedOutputStream(client.create(destinationFilename, true));
	            in = new BufferedInputStream(new FileInputStream(sourceFilename));
	            byte[] buffer = new byte[1024];
	 
	            int len = 0;
	            while ((len = in.read(buffer)) > 0) {
	                out.write(buffer, 0, len);
	            }
	        } finally {
	            if (client != null) {
	                client.close();
	            }
	            if (in != null) {
	                in.close();
	            }
	            if (out != null) {
	                out.close();
	            }
	        }
			log.info("finished: moving database dump files to HDFS.");
		} catch (Exception e) {
			log.error("error: moving database dump files to HDFS.",e);
		}		
	}

}
