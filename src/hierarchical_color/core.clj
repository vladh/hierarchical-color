(ns hierarchical-color.core
  (:require [clojure.math.numeric-tower :refer [floor]]
            [clojure.walk :refer :all])
  (:gen-class))

(def services '(:services (:development (:techplan) (:frontend) (:backend) (:webdev) (:seo) (:ecommerce))
                          (:design      (:web) (:responsive) (:app) (:ux) (:brand))
                          (:strategy    (:market) (:competitive) (:tracking) (:content))
                          (:search      (:seo) (:organic) (:paid) (:social))
                          (:content     (:marketing) (:creation))))

(defn make-color-ranges [node color-range]
  (if (next node)
      (let [partition-size (floor (/ (count color-range) (count (rest node))))
            pairs (map vector (rest node) (partition partition-size color-range))]
        (cons (list (first node) color-range) (map (partial apply make-color-ranges) pairs)))
      (list (first node) color-range)))

(defn -main [& args]
  (doall (map println (make-color-ranges services (range 0 360)))))
