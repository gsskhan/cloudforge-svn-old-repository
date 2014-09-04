package com.citi.retail.optima.etl.batch.messaging.test;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.citi.retail.optima.etl.batch.messaging.InternalMessageCreator;
import com.citi.retail.optima.etl.common.exception.OptimaRetailBaseException;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/retail.optima.etl.batch.test.xml" })
public class InternalMessageCreatorTest {

	@Autowired
	InternalMessageCreator internalMessageCreator;

	@Test
	public void testCreateOutgoingCompletionMessage() throws OptimaRetailBaseException
	{

			internalMessageCreator.createAndOutgoingCompletionMessage(675);
	
	}
}
