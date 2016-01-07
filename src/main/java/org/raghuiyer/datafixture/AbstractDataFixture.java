package org.raghuiyer.datafixture;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public abstract class AbstractDataFixture<T> implements TestRule{
	
	private T fixturedObject;

	protected abstract T loadData(String fixtureFilePath);
	
	protected String getFilePath(Description description) {
		String className = description.getClassName();
		return className.replace(".", "/") + ".json";
	}
	
	public Statement apply(Statement base, Description description) {
		
		try {
			//check if the annotation exists
			FixturedTest fixturedTest = checkIfAnnotationExists(description);
			//fixture exists
			if(fixturedTest != null) {
				String fixtureFilePath = fixturedTest.fixtureFile();
				if(fixtureFilePath == null || "".equals(fixtureFilePath)) {
					fixtureFilePath = this.getFilePath(description);
				}
				
				this.fixturedObject = this.loadData(fixtureFilePath);
			}
			
			base.evaluate();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return base;
	}

	private FixturedTest checkIfAnnotationExists(Description description) {
		return description.getAnnotation(FixturedTest.class);
	}
	
	public T getFixturedObject() {
		return this.fixturedObject;
	}
}
