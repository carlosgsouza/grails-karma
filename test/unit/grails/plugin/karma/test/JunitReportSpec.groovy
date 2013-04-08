package grails.plugin.karma.test

import groovy.lang.Binding;

import org.codehaus.groovy.grails.test.GrailsTestTargetPattern
import org.codehaus.groovy.grails.test.GrailsTestType
import org.codehaus.groovy.grails.test.GrailsTestTypeResult
import org.codehaus.groovy.grails.test.event.GrailsTestEventPublisher
import org.codehaus.groovy.grails.test.report.junit.JUnitReports;

import spock.lang.Specification;

class JunitReportSpec extends Specification {
	def "should parse a junit report"() {
		given:
		def thisClassFolder = new File(getClass().getResource("").getPath())
		
		def junitReportFile = new File(thisClassFolder, "karma-test-results.xml")
		
		when:
		def report = new JUnitReport(junitReportFile)
		
		then:
		report.tests == 28
		report.failures == 0
		report.errors == 0
	}
}
