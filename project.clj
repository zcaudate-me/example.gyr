(defproject purnam-angular-example "0.0.0-SNAPSHOT"
  :description "purnam angularjs example"
  :url "http://www.github.com/zcaudate/purnam-angular-example"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-1978"]
                 [im.chit/purnam "0.1.7"]]
  :cljsbuild
  {:builds  [{:source-paths ["src" "test"],
              :id "unit-test",
              :compiler {:pretty-print true,
                         :output-to "resources/public/js/test-angular-example.js",
                         :optimizations :whitespace}}
             {:source-paths ["src"],
              :id "run",
              :compiler {:pretty-print true,
                         :output-to "resources/public/js/angular-example.js",
                         :optimizations :whitespace}}]})
