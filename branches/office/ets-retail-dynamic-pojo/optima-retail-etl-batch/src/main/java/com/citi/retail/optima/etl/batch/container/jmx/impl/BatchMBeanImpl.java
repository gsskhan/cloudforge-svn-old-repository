package com.citi.retail.optima.etl.batch.container.jmx.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.integration.jms.JmsMessageDrivenEndpoint;
import org.springframework.util.Assert;

import com.citi.retail.optima.etl.batch.container.jmx.BatchMBean;

/**
 * This class is the implementation for the batch module OprationBean. This
 * class provides methods to start, stop and running status of module.
 * 
 * @author mk67854
 * 
 */
public class BatchMBeanImpl implements BatchMBean, InitializingBean,
		DisposableBean {

	private static final Log LOGGER = LogFactory.getLog(BatchMBeanImpl.class);

	private boolean running = false;
	private JmsMessageDrivenEndpoint batchJmsAdapter;

	public void setBatchJmsAdapter(JmsMessageDrivenEndpoint batchJmsAdapter) {
		this.batchJmsAdapter = batchJmsAdapter;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		//Assert.notNull(this.batchJmsAdapter,
		//		"Inbound message adapter cannot be null!");
	}

	@Override
	public void destroy() throws Exception {
		stop();
	}

	@Override
	public synchronized void start() throws Exception {
		if (!this.running) {
			// do recovery if batch did not shutdown properly before
			recoveryProcess();

			// set running status to true
			this.running = true;

			// starts all inbound adapters
			if (this.batchJmsAdapter != null) {
				this.batchJmsAdapter.start();
			}
		} else {
			LOGGER.info("module is running already ...");
		}
	}

	@Override
	public synchronized void stop() throws Exception {
		if (this.running) {
			// set running status to false
			this.running = false;

			// stop all inbound adapters
			if (this.batchJmsAdapter != null) {
				this.batchJmsAdapter.stop();
			}

			// wait for current running tasks to finish
			awaitTermination();
		} else {
			LOGGER.info("module is not running ...");
		}
	}

	@Override
	public boolean isAlive() throws Exception {
		return this.running;
	}

	private void recoveryProcess() {
		// TODO
	}

	private void awaitTermination() {
		// TODO
	}
}