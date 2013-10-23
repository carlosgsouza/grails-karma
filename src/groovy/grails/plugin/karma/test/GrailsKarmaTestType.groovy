package grails.plugin.karma.test

import java.util.logging.Logger

import org.codehaus.groovy.grails.test.GrailsTestTargetPattern
import org.codehaus.groovy.grails.test.GrailsTestType
import org.codehaus.groovy.grails.test.GrailsTestTypeResult
import org.codehaus.groovy.grails.test.event.GrailsTestEventPublisher

class GrailsKarmaTestType implements GrailsTestType {
	def log;
	String testPhase
	Binding buildBinding
	File karmaExecutable
	File karmaConfigFile
	
	FileHelper fileHelper
	JUnitReportParser reportParser
	CommandRunner commandRunner
	
	public GrailsKarmaTestType(String testPhase) {
		this.fileHelper = new FileHelper()
		this.reportParser = new JUnitReportParser()
		this.commandRunner = new CommandRunner()
		this.testPhase = testPhase
	}
	
	@Override
	public String getName() {
		"karma"
	}

	@Override
	public String getRelativeSourcePath() {
		(this.testPhase && this.testPhase == "unit") ? "js-unit" : "js-functional" 
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
		
		this.karmaConfigFile = fileHelper.open("${baseDir}/grails-app/conf/karma/${karmaConfigFileName}")
		if(!karmaConfigFile?.exists()) {
			System.err.println "No config file found on ${karmaConfigFile.absolutePath}"
			return 0
		}
		println "Running JS ${testPhase} tests with this config file: ${karmaConfigFile.absolutePath}"
		return numberOfTestFiles
	}
	
	String getKarmaConfigFileName(){
		(this.testPhase && this.testPhase == "unit") ? "unit.conf.js" : "functional.conf.js"
	}
	
	int getNumberOfTestFiles() {
		fileHelper.countJsFiles("$baseDir/test/${relativeSourcePath}/")
	}

	@Override
	public GrailsTestTypeResult run(GrailsTestEventPublisher eventPublisher) {
		try {
			commandRunner.execute(karmaExecutable.absolutePath, "start", karmaConfigFile.absolutePath, "--no-auto-watch", "--single-run")
			def reportPath = "${baseDir}/target/test-reports/karma/${testPhase}-test-results.xml"
			return reportParser.parse(reportPath)
			
		} catch(e) {
			println "Unable to execute tests. Failed with message $e.message"
			new GrailsKarmaTestTypeResult(failCount: 1, passCount: 0)
		}
	}
	
	@Override
	public void cleanup() {
	}

}
