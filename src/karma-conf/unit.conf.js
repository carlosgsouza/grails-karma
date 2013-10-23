// Karma configuration for unit tests
module.exports = function(config) {
	config.set({
		// base path, that will be used to resolve files and exclude
		basePath: '../../../',
		
		//frameworks to use
		frameworks: ['jasmine'],
		
		// list of files / patterns to load in the browser
		files: [
			'web-app/js/**/*.js',
			'test/js-unit/**/*.js' // can't touch this!
		],
		
		// list of files to exclude
		exclude: [
		],
		
		
		// test results reporter to use
		// possible values: 'dots', 'progress', 'junit', 'growl', 'coverage'
		// junit is required in order for grails-karma to work
		reporters: ['dots', 'progress', 'junit'],
		
		junitReporter: { 
			    // will be resolved to basePath (in the same way as files/exclude patterns) 
			    outputFile: 'target/test-reports/karma/unit-test-results.xml'
		},
		
		// web server port
		port: 3560,
		
		
		// enable / disable colors in the output (reporters and logs)
		colors: true,
		
		
		// level of logging
		// possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
		logLevel: config.LOG_INFO,
		
		
		// enable / disable watching file and executing tests whenever any file changes
		autoWatch: false,
		
		
		// Start these browsers, currently available:
		// - Chrome
		// - ChromeCanary
		// - Firefox
		// - Opera
		// - Safari (only Mac)
		// - PhantomJS
		// - IE (only Windows)
		browsers: ['Chrome'],
		
		// If browser does not capture in given timeout [ms], kill it
		captureTimeout: 30000,
		
		
		// Continuous Integration mode
		// if true, it capture browsers, run tests and exit
		singleRun: true,
		
		plugins: [
			'karma-jasmine',
			'karma-chrome-launcher',
			'karma-firefox-launcher',
			'karma-junit-reporter'
		]
	});
};
