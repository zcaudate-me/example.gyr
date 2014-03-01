(defproject example.gyr "0.0.0-SNAPSHOT"
  :description "gyr angularjs example"
  :url "http://www.github.com/purnam/example.gyr"
  :license {:name "The MIT License"
            :url "http://opensource.org/licencses/MIT"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [im.chit/gyr "0.3.1"]]
  :profiles {:dev {:dependencies [[org.clojure/clojurescript "0.0-2138"]]
                   :plugins [[lein-cljsbuild "1.0.0"]]}}
  :cljsbuild
  {:builds  [{:source-paths ["src" "test"],
              :id "unit-test",
              :compiler {:pretty-print true,
                         :output-to "publish/js/test-angular-example.js",
                         :optimizations :whitespace}}
             {:source-paths ["src"],
              :id "run",
              :compiler {:pretty-print true,
                         :output-to "publish/js/angular-example.js",
                         :optimizations :whitespace}}]})
