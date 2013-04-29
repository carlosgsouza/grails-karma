package grails.plugin.karma.test

import org.codehaus.groovy.grails.test.GrailsTestTargetPattern
import org.codehaus.groovy.grails.test.GrailsTestType
import org.codehaus.groovy.grails.test.GrailsTestTypeResult
import org.codehaus.groovy.grails.test.event.GrailsTestEventPublisher

class GrailsKarmaTestType implements GrailsTestType {
	
	Binding buildBinding
	File karmaExecutable
	File karmaUnitConfig
	
	FileHelper fileHelper
	JUnitReportParser reportParser
	CommandRunner commandRunner
	
	public GrailsKarmaTestType() {
		this.fileHelper = new FileHelper()
		this.reportParser = new JUnitReportParser()
		this.commandRunner = new CommandRunner()
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
		buildBinding.getVariable("basedir")
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
		
		return numberOfTestFiles
	}
	
	int getNumberOfTestFiles() {
		fileHelper.countJsFiles("$baseDir/test/js-unit/")
	}

	@Override
	public GrailsTestTypeResult run(GrailsTestEventPublisher eventPublisher) {
		try {
			commandRunner.execute(karmaExecutable.absolutePath, "start", karmaUnitConfig.absolutePath, "--no-auto-watch", "--single-run")
		
			def reportPath = "${baseDir}/target/test-reports/karma/unit-test-results.xml"
			return reportParser.parse(reportPath)
			
		} catch(e) {
			println "Unable to execute tests. Failed with message $e.message"
			return new GrailsKarmaTestTypeResult(passCount: 0, failCount: 0)
		}
	}
	
	@Override
	public void cleanup() {
	}

}
