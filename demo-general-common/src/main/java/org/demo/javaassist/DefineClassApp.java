package org.demo.javaassist;

import javassist.ClassPool;
import javassist.CtClass;

import org.apache.log4j.Logger;

public class DefineClassApp {
	
	private static Logger log = Logger.getLogger(DefineClassApp.class);

	public static void main(String[] args) throws Exception {
		log.info("Starting to create a new class on fly.");
		ClassPool pool = ClassPool.getDefault();
		CtClass pointClass = pool.makeClass("Point");
		// write the class to local hard disk.
		pointClass.writeFile("/tmp");
		log.info("Sucessfully written file to disk.");
		
		/* If a CtClass object is converted into a class file by writeFile(), toClass(), or toBytecode()
		, Javassist freezes that CtClass object. A frozen CtClass can be defrost so that modifications
		of the class definition will be permitted. */
		pointClass.defrost();
		pointClass.setName("Pair");
		pointClass.writeFile("/tmp");
		log.info("Sucessfully written changes file to disk.");

		log.info("end ...");
	}

}
