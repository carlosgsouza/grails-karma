package grails.plugin.karma.test

import groovy.lang.Binding;

import org.codehaus.groovy.grails.test.GrailsTestTargetPattern
import org.codehaus.groovy.grails.test.GrailsTestType
import org.codehaus.groovy.grails.test.GrailsTestTypeResult
import org.codehaus.groovy.grails.test.event.GrailsTestEventPublisher

import spock.lang.Specification;

class FileHelperSpec extends Specification {
	
	def "Should count the number of js files inside a folder. Files with other extensions should be ignored."() {
		given:
		def pathForFolderWithJsFiles = "resources/unit/a_folder_with_js_files"
		
		when:
		def result = new FileHelper().countJsFiles(pathForFolderWithJsFiles)
		
		then:
		result == 2
	}
	
	def "Should count the number of js files inside nested folders. Files with other extensions or subfolders should be ignored."() {
		given:
		def pathForFolderWithJsFiles = "resources/unit/a_folder_with_folders_with_js_files"
		
		when:
		def result = new FileHelper().countJsFiles(pathForFolderWithJsFiles)
		
		then:
		result == 3
	}
}
