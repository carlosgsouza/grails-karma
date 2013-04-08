package grails.plugin.karma.test

import groovy.lang.Binding;

import org.codehaus.groovy.grails.test.GrailsTestTargetPattern
import org.codehaus.groovy.grails.test.GrailsTestType
import org.codehaus.groovy.grails.test.GrailsTestTypeResult
import org.codehaus.groovy.grails.test.event.GrailsTestEventPublisher

import spock.lang.Specification;

class GrailsKarmaTestTypeSpec extends Specification {
	
//	def  "execute should print output to System.out"() {
//		given:
//		def karmaTestType = new GrailsKarmaTestType()
//		
//		and: "we replace stdOut and stdErr with streams we can read from"
//		def stdOut = System.out
//		def stdOutMock = new ByteArrayOutputStream()
//		System.setOut(new PrintStream(stdOutMock))
//		
//		def stdErr = System.err
//		def stdErrMock = new ByteArrayOutputStream()
//		System.setErr(new PrintStream(stdErrMock))
//		
//		when:
//		// TODO for god sake, this makes me wanna call my mother to hit whoever did this
//		karmaTestType.execute("C:/apps/cygwin/bin/echo.exe 'hello world'") 
//		
//		and:
//		def out = stdOutMock.toString()
//		def err = stdErrMock.toString()
//		
//		then:
//		out == "hello world\n"
//		err.isEmpty()
//		
//		cleanup:
//		System.setErr(new PrintStream(stdErr))
//		System.setOut(new PrintStream(stdOut))
//	}
	
	def "should run"() {
		given:
		Binding buildBinding = Mock()
		def karmaTestType = new GrailsKarmaTestType(baseDir: "C:/opt/workspace-gg/cmaas-portal")
		
		when:
		karmaTestType.run()
		
		then:
		true
	}
}
