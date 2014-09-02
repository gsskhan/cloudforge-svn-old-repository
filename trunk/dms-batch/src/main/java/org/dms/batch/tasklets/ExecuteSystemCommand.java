package org.dms.batch.tasklets;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.dms.batch.core.OSValidator;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.Resource;

public class ExecuteSystemCommand implements Tasklet{
	
	private Logger log = Logger.getLogger(this.getClass());
	
	private Resource windowsCommandFilePath;
	private Resource unixCommandFilePath;

	@Override
	public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
		if (OSValidator.isWindows()) {
			File file = windowsCommandFilePath.getFile();
			this.runCommand("cmd /c "+file.getCanonicalPath());
		} else if (OSValidator.isUnix()) {
			File file = unixCommandFilePath.getFile();
			this.runCommand("sh " +file.getCanonicalPath());
		} else {
			throw new RuntimeException("program cannot execute commands for OS - " + OSValidator.OS);
		}
		return RepeatStatus.FINISHED;
	}

	private void runCommand(String command) throws IOException, InterruptedException {
		log.info("executing command: " + command);
		Process p = Runtime.getRuntime().exec(command);
		p.waitFor();
		BufferedReader brIn = new BufferedReader(new InputStreamReader(p.getInputStream()));
		BufferedReader brErr = new BufferedReader(new InputStreamReader(p.getErrorStream()));

		String result = "";
		log.info("command execution result:");
		while ((result = brIn.readLine()) != null) {
			log.info(result);
		}
		log.info("command execution error(if any):");
		while ((result = brErr.readLine()) != null) {
			log.warn(result);
		}
	}

	public Resource getWindowsCommandFilePath() {
		return windowsCommandFilePath;
	}
	public void setWindowsCommandFilePath(Resource windowsCommandFilePath) {
		this.windowsCommandFilePath = windowsCommandFilePath;
	}
	public Resource getUnixCommandFilePath() {
		return unixCommandFilePath;
	}
	public void setUnixCommandFilePath(Resource unixCommandFilePath) {
		this.unixCommandFilePath = unixCommandFilePath;
	}

}
