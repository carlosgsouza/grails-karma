// Karma configuration for unit tests


// base path, that will be used to resolve files and exclude
basePath = '../../../';


// list of files / patterns to load in the browser
files = [
  JASMINE,
  JASMINE_ADAPTER,
  'web-app/js/**/*.js',
  'test/js-unit/**/*.js' // can't touch this!
];

// list of files to exclude
exclude = [
];


// test results reporter to use
// possible values: 'dots', 'progress', 'junit'
// junit is required in order for grails-karma to work
reporters = ['progress', 'junit'];

junitReporter = { 
	    // will be resolved to basePath (in the same way as files/exclude patterns) 
	    outputFile: 'target/test-reports/karma/unit-test-results.xml'
};

// web server port
port = 3560;


// cli runner port
runnerPort = 9100;


// enable / disable colors in the output (reporters and logs)
colors = true;


// level of logging
// possible values: LOG_DISABLE || LOG_ERROR || LOG_WARN || LOG_INFO || LOG_DEBUG
logLevel = LOG_INFO;


// enable / disable watching file and executing tests whenever any file changes
autoWatch = true;


// Start these browsers, currently available:
// - Chrome
// - ChromeCanary
// - Firefox
// - Opera
// - Safari (only Mac)
// - PhantomJS
// - IE (only Windows)
browsers = ['PhantomJS'];

// If browser does not capture in given timeout [ms], kill it
captureTimeout = 5000;


// Continuous Integration mode
// if true, it capture browsers, run tests and exit
singleRun = false;

debugger;

plugins = [
  'testacular-jasmine',
  'testacular-chrome-launcher',
  'testacular-firefox-launcher',
  'testacular-junit-reporter'
];