package grails.plugin.karma.test

import org.codehaus.groovy.grails.test.GrailsTestTargetPattern
import org.codehaus.groovy.grails.test.GrailsTestType
import org.codehaus.groovy.grails.test.GrailsTestTypeResult
import org.codehaus.groovy.grails.test.event.GrailsTestEventPublisher

class GrailsKarmaTestType implements GrailsTestType {
	
	Binding buildBinding
	File karmaExecutable
	File karmaUnitConfig
	
	def fileHelper
	
	CommandRunner commandRunner = new CommandRunner()
	
	public GrailsKarmaTestType() {
		this.fileHelper = new FileHelper()
	}
	
	@Override
	public String getName() {
		"karma"
	}

	@Override
	public String getRelativeSourcePath() {
		"js-unit"
	}
	
	private String getBaseDir() {
		buildBinding.getVariable("baseDir")
	}

	@Override
	public int prepare(GrailsTestTargetPattern[] testTargetPatterns, File compiledClassesDir, Binding buildBinding) {
		this.buildBinding = buildBinding
		
		this.karmaExecutable = fileHelper.findExecutableOnPath("karma.cmd")
		if(!karmaExecutable?.exists()) {
			System.err.println "Karma executable not found. Make sure it's on the PATH. Skippping Karma tests"
			return 0
		}
		
		this.karmaUnitConfig = fileHelper.open("${baseDir}/grails-app/conf/karma/unit.conf.js")
		if(!karmaUnitConfig?.exists()) {
			System.err.println "No config file found on ${karmaUnitConfig.absolutePath}"
			return 0
		}
		
		// Since Karma doesn't know the test engine, it's impossible to know from here how many tests will be executed. This number can be anything but 0
		-1
	}

	@Override
	public GrailsTestTypeResult run(GrailsTestEventPublisher eventPublisher) {
		commandRunner.execute(karmaExecutable.absolutePath, "start", karmaUnitConfig.absolutePath)
		def reportPath = "${baseDir}/target/test-reports/karma/unit-test-results.xml"
		def report = parseReportForResults(reportPath)
		
		new GrailsKarmaTestTypeResult(report)
	}
	
	def parseReportForResults(reportPath) {
		def reportFile = new File(reportPath)
		if(!reportFile.exists()) {
			println "Could not find report on $reportPath"
			return new GrailsKarmaTestTypeResult(passCount: 0, failCount: 0)
		}
		
		new JUnitReportParser(reportFile)
	}
	
	
	@Override
	public void cleanup() {
	}

}
