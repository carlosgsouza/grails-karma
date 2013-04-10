package grails.plugin.karma.test

import groovy.lang.Binding;

import org.codehaus.groovy.grails.test.GrailsTestTargetPattern
import org.codehaus.groovy.grails.test.GrailsTestType
import org.codehaus.groovy.grails.test.GrailsTestTypeResult
import org.codehaus.groovy.grails.test.event.GrailsTestEventPublisher
import org.codehaus.groovy.grails.test.report.junit.JUnitReports;

import spock.lang.Specification;

class JunitReportParserSpec extends Specification {
	def "should parse a junit report"() {
		given:
		def thisClassFolder = new File(getClass().getResource("").getPath())
		def junitReportFile = new File(thisClassFolder, "karma-test-results.xml")
		
		when:
		def result = new JUnitReportParser().parse(junitReportFile)
		
		then:
		result.failCount == 5
		result.passCount == 23
	}
}


