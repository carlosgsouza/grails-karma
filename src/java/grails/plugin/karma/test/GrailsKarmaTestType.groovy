package grails.plugin.karma.test

import org.codehaus.groovy.grails.test.GrailsTestTargetPattern
import org.codehaus.groovy.grails.test.GrailsTestType
import org.codehaus.groovy.grails.test.GrailsTestTypeResult
import org.codehaus.groovy.grails.test.event.GrailsTestEventPublisher

class GrailsKarmaTestType implements GrailsTestType {
	
	Binding buildBinding
	String baseDir
	File karmaExecutable
	File karmaUnitConfig
	
	CommandRunner commandRunner = new CommandRunner()
	
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
		
		this.karmaExecutable = findExecutableOnPath("karma.cmd")
		if(!karmaExecutable.exists()) {
			println "Karma executable not found. Make sure it's on the PATH. Skippping Karma tests"
			return 0
		}
		
		this.karmaUnitConfig = new File("${baseDir}/grails-app/conf/karma/unit.conf.js")
		if(!karmaExecutable.exists()) {
			println "No config file found on ${karmaUnitConfig.absolutePath}"
			return 0
		}
		
		// Since Karma doesn't know the test engine, it's impossible to know from here how many tests will be executed. This number can be anything but 0
		-1
	}

	@Override
	public GrailsTestTypeResult run(GrailsTestEventPublisher eventPublisher) {
		commandRunner.execute(karmaExecutable.absolutePath, "start", karmaUnitConfig.absolutePath)
		
		def reportPath = "${baseDir}/target/test-reports/karma-test-results.xml"
		
		parseReportForResults(reportPath)
	}
	
	def parseReportForResults(reportPath) {
		def reportFile = new File(reportPath)
		if(!reportFile.exists()) {
			println "Could not find report on $reportPath"
			return new GrailsKarmaTestTypeResult(passCount: 0, failCount: 0)
		}
		
		
	}
	
	private static File findExecutableOnPath(String executableName)
	{
		String systemPath = System.getenv("PATH");
		String[] pathDirs = systemPath.split(File.pathSeparator);
   
		File fullyQualifiedExecutable = null;
		for (String pathDir : pathDirs)
		{
			File file = new File(pathDir, executableName);
			if (file.isFile())
			{
				fullyQualifiedExecutable = file;
				break;
			}
		}
		return fullyQualifiedExecutable;
	}
	
	@Override
	public void cleanup() {
	}

}
