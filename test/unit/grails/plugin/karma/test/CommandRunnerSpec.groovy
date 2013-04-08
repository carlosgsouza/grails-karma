package grails.plugin.karma.test

import groovy.lang.Binding;

import org.codehaus.groovy.grails.test.GrailsTestTargetPattern
import org.codehaus.groovy.grails.test.GrailsTestType
import org.codehaus.groovy.grails.test.GrailsTestTypeResult
import org.codehaus.groovy.grails.test.event.GrailsTestEventPublisher

import spock.lang.Specification;

class CommandRunnerSpec extends Specification {
	
	def "should execute a process, consutme its output and wait for its completion"() {
		given:
		String commandStringMock = GroovyMock()
		Process startedProcessMock = GroovyMock()
		
		and:
		def theCommand = new CommandRunner()
				
		when:
		theCommand.execute(commandStringMock)
		
		then:
		1 * commandStringMock.execute() >> startedProcessMock
		
		and:
		1 * startedProcessMock.consumeProcessOutput(System.out, System.err)
		1 * startedProcessMock.waitFor()
	}
	
}
