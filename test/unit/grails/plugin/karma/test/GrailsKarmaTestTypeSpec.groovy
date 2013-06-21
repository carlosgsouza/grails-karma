package grails.plugin.karma.test

import groovy.lang.Binding;

import org.codehaus.groovy.grails.test.GrailsTestTargetPattern
import org.codehaus.groovy.grails.test.GrailsTestType
import org.codehaus.groovy.grails.test.GrailsTestTypeResult
import org.codehaus.groovy.grails.test.event.GrailsTestEventPublisher

import spock.lang.Ignore;
import spock.lang.Specification;

class GrailsKarmaTestTypeSpec extends Specification {
	
	def "the folder for js unit tests should be js-unit"() {
		given:
		def testType = new GrailsKarmaTestType("unit")
		
		when:
		def relativeSourcePath = testType.getRelativeSourcePath()
		
		then:
		relativeSourcePath == "js-unit"
	}
	
	def "the folder for js functional tests should be js-functional"() {
		given:
		def testType = new GrailsKarmaTestType("functional")
		
		when:
		def relativeSourcePath = testType.getRelativeSourcePath()
		
		then:
		relativeSourcePath == "js-functional"
	}
	
	def "When the karma executable is not found on the path, the test should not run"() {
		given: 
		def testType = new GrailsKarmaTestType()
		testType.fileHelper = Mock(FileHelper)
		
		when:
		def result = testType.prepare(null, null, null)
		
		then:
		1 * testType.fileHelper.findExecutableOnPath("karma.cmd") >> null
		
		and:
		result == 0
	}

	def "When is a unit test the config file should be unit.conf.js"() {
		given:
		def testType = new GrailsKarmaTestType("unit")
		def existentFile = new File(".")
		Binding bindingMock = Mock()
		
		and:
		testType.fileHelper = Mock(FileHelper)
		
		when:
		def result = testType.prepare(null, null, bindingMock)
		
		then:
		1 * testType.fileHelper.findExecutableOnPath("karma.cmd") >> existentFile
		_ * bindingMock.getVariable("basedir") >> "base_dir"
		1 * testType.fileHelper.open( { it ==~ "base_dir/grails-app/conf/karma/unit.conf.js" } ) >> existentFile
		1 * testType.fileHelper.countJsFiles("base_dir/test/js-unit/") >> 5
		
		and:
		result == 5
	}

	def "When is a functional test the config file should be functional.conf.js"() {
		given:
		def testType = new GrailsKarmaTestType("functional")
		def existentFile = new File(".")
		Binding bindingMock = Mock()
		
		and:
		testType.fileHelper = Mock(FileHelper)
		
		when:
		def result = testType.prepare(null, null, bindingMock)
		
		then:
		1 * testType.fileHelper.findExecutableOnPath("karma.cmd") >> existentFile
		_ * bindingMock.getVariable("basedir") >> "base_dir"
		1 * testType.fileHelper.open( { it ==~ "base_dir/grails-app/conf/karma/functional.conf.js" } ) >> existentFile
		1 * testType.fileHelper.countJsFiles("base_dir/test/js-functional/") >> 15
		
		and:
		result == 15
	}
	
	def "When the karma config File is not found, the test should not run"() {
		given: 
		def testType = new GrailsKarmaTestType()
		def existentFile = new File(".")
		Binding bindingMock = Mock()
		
		and:
		testType.fileHelper = Mock(FileHelper)
		
		when:
		def result = testType.prepare(null, null, bindingMock)
		
		then:
		1 * testType.fileHelper.findExecutableOnPath("karma.cmd") >> existentFile
		1 * bindingMock.getVariable("basedir") >> "base_dir"
		1 * testType.fileHelper.open( { it ==~ "base_dir/.*conf.js" } ) >> new File("this/file/doesnt/exist")
		
		and:
		result == 0
	}
	
	def "should return the number of files in the test folder"() {
		given: 
		def testType = new GrailsKarmaTestType("unit")
		def existingFile = new File(".")
		
		and:
		Binding bindingMock = Mock()
		bindingMock.getVariable("basedir") >> "base_dir"
		
		and:
		testType.fileHelper = Mock(FileHelper)
		
		when:
		def result = testType.prepare(null, null, bindingMock)
		
		then:
		1 * testType.fileHelper.findExecutableOnPath("karma.cmd") >> existingFile
		1 * testType.fileHelper.open( { it ==~ ".*conf.js" } ) >> existingFile
		1 * testType.fileHelper.countJsFiles("base_dir/test/js-unit/") >> 67
		
		and:
		result == 67
	}
	
	def "shoud run the karma executable and parse the unit test reports"() {
		given:
		def test = new GrailsKarmaTestType("unit")
		test.commandRunner = Mock(CommandRunner)
		test.reportParser = Mock(JUnitReportParser)
		test.buildBinding = Mock(Binding)
		
		and:
		test.karmaExecutable = new File("/path/to/karma/executable")
		test.karmaConfigFile = new File("/path/to/karma/unit/config")
		
		and:
		GrailsKarmaTestTypeResult expectedResult = Mock()
		
		GrailsTestEventPublisher unusedEventPublisher = Mock()
		
		when:
		def result = test.run(unusedEventPublisher)
		
		then:
		0 * unusedEventPublisher._
		1 * test.commandRunner.execute(test.karmaExecutable.absolutePath, "start", test.karmaConfigFile.absolutePath, "--no-auto-watch", "--single-run")
		1 * test.reportParser.parse("$test.baseDir/target/test-reports/karma/unit-test-results.xml") >> expectedResult
		
		and:
		result == expectedResult
	}
	
	def "shoud run the karma executable and parse the functional test reports"() {
		given:
		def test = new GrailsKarmaTestType("functional")
		test.commandRunner = Mock(CommandRunner)
		test.reportParser = Mock(JUnitReportParser)
		test.buildBinding = Mock(Binding)
		
		and:
		test.karmaExecutable = new File("/path/to/karma/executable")
		test.karmaConfigFile = new File("/path/to/karma/functional/config")
		
		and:
		GrailsKarmaTestTypeResult expectedResult = Mock()
		
		GrailsTestEventPublisher unusedEventPublisher = Mock()
		
		when:
		def result = test.run(unusedEventPublisher)
		
		then:
		0 * unusedEventPublisher._
		1 * test.commandRunner.execute(test.karmaExecutable.absolutePath, "start", test.karmaConfigFile.absolutePath, "--no-auto-watch", "--single-run")
		1 * test.reportParser.parse("$test.baseDir/target/test-reports/karma/functional-test-results.xml") >> expectedResult
		
		and:
		result == expectedResult
	}
}
