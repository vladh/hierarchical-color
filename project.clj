(defproject hierarchical-color "0.1.0-SNAPSHOT"
  :description "Hierarchical color range generator for Phyramid's service categories."
  :author "vladh"
  :url "http://github.com/vladh/hierarchical-color"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/math.numeric-tower "0.0.4"]
                 [org.clojure/core.incubator "0.1.2"]]
  :main ^:skip-aot hierarchical-color.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
