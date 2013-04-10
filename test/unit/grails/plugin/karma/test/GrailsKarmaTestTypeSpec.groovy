package grails.plugin.karma.test

import groovy.lang.Binding;

import org.codehaus.groovy.grails.test.GrailsTestTargetPattern
import org.codehaus.groovy.grails.test.GrailsTestType
import org.codehaus.groovy.grails.test.GrailsTestTypeResult
import org.codehaus.groovy.grails.test.event.GrailsTestEventPublisher

import spock.lang.Specification;

class GrailsKarmaTestTypeSpec extends Specification {
	
	def "the folder for js unit tests should be js-unit"() {
		given:
		def testType = new GrailsKarmaTestType()
		
		when:
		def relativeSourcePath = testType.getRelativeSourcePath()
		
		then:
		relativeSourcePath == "js-unit"
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
	
	def "When the karma config gile is not found, the test should not run"() {
		given: 
		def testType = new GrailsKarmaTestType()
		def existantFile = new File(".")
		Binding bindingMock = Mock()
		
		
		and:
		testType.fileHelper = Mock(FileHelper)
		
		when:
		def result = testType.prepare(null, null, bindingMock)
		
		then:
		1 * testType.fileHelper.findExecutableOnPath("karma.cmd") >> existantFile
		1 * bindingMock.getVariable("baseDir") >> "base_dir"
		1 * testType.fileHelper.open( { it ==~ "base_dir/.*conf.js" } ) >> new File("this/file/doesnt/exist")
		
		and:
		result == 0
	}
}
