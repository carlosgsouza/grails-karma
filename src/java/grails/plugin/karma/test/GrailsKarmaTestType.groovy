package grails.plugin.karma.test

import org.codehaus.groovy.grails.test.GrailsTestTargetPattern
import org.codehaus.groovy.grails.test.GrailsTestType
import org.codehaus.groovy.grails.test.GrailsTestTypeResult
import org.codehaus.groovy.grails.test.event.GrailsTestEventPublisher

class GrailsKarmaTestType implements GrailsTestType {
	
	Binding buildBinding
	String baseDir
	
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
		this.buildBinding = buildBinding
		this.baseDir = buildBinding.grailsSettings.baseDir
		
		// Since Karma doesn't know the test engine, it's impossible to know from here how many tests will be executed. This number can be anything but 0
		-1
	}

	@Override
	public GrailsTestTypeResult run(GrailsTestEventPublisher eventPublisher) {
		def karmaExec = "C:/Users/carlosags/AppData/Roaming/npm/karma.cmd"
		def karmaConf = "${baseDir}/grails-app/conf/karma/unit.conf.js"
			
		def cmd = "$karmaExec start $karmaConf"
		
		execute(cmd)
		
		def karmaReport = new XmlSlurper().parse("${baseDir}/target/test-reports/karma-test-results.xml")
		def pass = Integer.valueOf karmaReport.testsuite[0].@tests.toString()
		def fail = Integer.valueOf karmaReport.testsuite[0].@failures.toString()
		
		new GrailsKarmaTestTypeResult(passCount: pass, failCount: fail)
	}
	
	def execute(cmd) {
		def process = cmd.execute()
		process.consumeProcessOutput(System.out, System.err)
		process.waitFor()
	}

	@Override
	public void cleanup() {
	}

}
