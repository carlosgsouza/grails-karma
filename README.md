grails-karma
============
Run javascript tests in the Grails build!

The main goal for Karma is to bring a productive testing environment to developers. Grails-karma allows Grails developers to use this spectacular tool in their application so they can write unit and functional tests in Javascript and execute them in the build.

## Instalation
First, install and configure Karma as described in [Karma's official documentation](http://karma-runner.github.io/0.10/index.html).

After you have karma running on your machine, you can install the grails-karma plugin by adding this dependency to your BuildConfig.groovy

> code here

or using the install-plugin command

> grails install-plugin grails-karma

When installed, grails-karma will create the following files in your project tree
> config files

the following directories will also be created:
> js-unit and functional

For grails-karma to function properly, please make sure you have karma on your path.

## Configuration
grails-karma itself doesn't need to be configured. All configuration is made using karma configuration files under the conf/karma directory. These files have a very useful default configuration. Please refer to the karma official documentation(http://karma-runner.github.io/0.10/index.html) for more information.

You may configure your karma tests they way you want by changing the configuration files under conf/karma. However, make sure you don't change any of the following configuration parameters used by the plugin:
> test-report

## Usage
To run the karma plugin, use 
> grails test-app karma:unit
> grails test-app karma:functional

or simply grails test-app

By default, grails-karma will look for javascript tests under the directories js-unit and js-functional and for javascript code under the directory …
