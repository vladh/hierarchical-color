(defproject hierarchical-color "0.1.0-SNAPSHOT"
  :url "http://github.com/vladh/hierarchical-color"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/math.numeric-tower "0.0.4"]]
  :main ^:skip-aot hierarchical-color.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
