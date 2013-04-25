package grails.plugin.karma.test

import java.io.File;

/**
 * This class abstracts file operations so they can be mocked for unit tests
 * 
 * @author carlos
 */
class FileHelper {
	
	File findExecutableOnPath(String executableName)
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
	
	File open(String path) {
		new File(path)
	}
	
	int countJsFiles(String path) {
		def jsUnitTestsDir = new File(path)
		
		def count = 0
		jsUnitTestsDir.eachFileRecurse {
			if(it?.name.toLowerCase() ==~ /.*js/) {
				count++
			}
		}
		count
	}
	
}
