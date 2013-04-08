package grails.plugin.karma.test

import org.codehaus.groovy.grails.test.GrailsTestTargetPattern
import org.codehaus.groovy.grails.test.GrailsTestType
import org.codehaus.groovy.grails.test.GrailsTestTypeResult
import org.codehaus.groovy.grails.test.event.GrailsTestEventPublisher

class GrailsKarmaTestType implements GrailsTestType {
	
	@Override
	public String getName() {
		"karma"
	}

	@Override
	public String getRelativeSourcePath() {
		""
	}

	@Override
	public int prepare(GrailsTestTargetPattern[] testTargetPatterns, File compiledClassesDir, Binding buildBinding) {
		// Since Karma doesn't know the test engine, it's impossible to know from here how many tests will be executed. This number can be anything but 0
		-1
	}

	@Override
	public GrailsTestTypeResult run(GrailsTestEventPublisher eventPublisher) {
		// TODO execute tests here
		new GrailsKarmaTestTypeResult()
	}

	@Override
	public void cleanup() {
	}

}
