# example.gyr

Example of how to write and test angular.js code using clojurescript code using [gyr](http://www.github.com/purnam/gyr).

[![Build Status](https://travis-ci.org/purnam/example.gyr.png?branch=master)](https://travis-ci.org/purnam/example.gyr)

## Usage

Clone this project and compile using cljsbuild:

    > git clone https://github.com/purnam/example.gyr.git
    > cd example.gyr
    > git submodule init
    > git submodule update
    > lein cljsbuild auto

#### Examples
Run a server in the `publish` directory

    > python -m SimpleHTTPServer 8000 

And then browse to: `http://localhost:8000/`

The examples can be seen online [here](http://purnam.github.io/example.gyr)

#### Tests    
In a new window in the same directory:

    > karma start

Tests are defined in `test/example/gyr` directory.

## License

Copyright © 2014 Chris Zheng

Distributed under the The MIT License.
