# purnam-angular-example

Example of how to write and test angular.js code using clojurescript code using [purnam](http://www.github.com/zcaudate/purnam).

## Usage

Clone this project and compile using cljsbuild:

    > git clone https://github.com/zcaudate/purnam-angular-example.git
    > cd purnam-angular-example
    > lein cljsbuild auto

#### Examples
Run a server in the `resources/public`

    > python -m SimpleHTTPServer 8000 

And then browse to: `http://localhost:8000/`

The examples can be seen here:
http://docs.caudate.me/purnam-angular-example/

#### Tests    
In a new window in the same directory:

    > karma start

Tests are defined in `test/purnam_angular_example`.

## Tutorial

Coming Soon....

## License

Copyright © 2013 Chris Zheng

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
