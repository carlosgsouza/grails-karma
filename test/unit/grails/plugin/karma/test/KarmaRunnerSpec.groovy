package grails.plugin.karma.test

import groovy.lang.Binding;

import org.codehaus.groovy.grails.test.GrailsTestTargetPattern
import org.codehaus.groovy.grails.test.GrailsTestType
import org.codehaus.groovy.grails.test.GrailsTestTypeResult
import org.codehaus.groovy.grails.test.event.GrailsTestEventPublisher

import spock.lang.Specification;

class KarmaRunnerSpec extends Specification {
	
	def "should run the karma command using the provided paths to the karma command and to the configuration file"() {
		given:
		CommandRunner commandRunnerMock = Mock()
		
		and:
		def pathToKarma = "/path/to/karma"
		def pathToConfigFile = "/path/to/config/file.js"
		
		and:
		def theKarmaRunner = new KarmaRunner(pathToKarma, commandRunnerMock)
		
		when:
		def result = theKarmaRunner.execute(pathToConfigFile)
		
		then:
		1 * commandRunnerMock.execute(pathToKarma, "start", pathToConfigFile)
	}
	
}
