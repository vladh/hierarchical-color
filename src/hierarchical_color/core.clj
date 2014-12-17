(ns hierarchical-color.core
  (:require [clojure.math.numeric-tower :refer [floor]]
            [clojure.walk :refer :all]
            [com.evocomputing.colors :refer :all])
  (:gen-class))

(def services '(:services (:development (:techplan) (:frontend) (:backend) (:webdev) (:seo) (:ecommerce))
                          (:design      (:web) (:responsive) (:app) (:ux) (:brand))
                          (:strategy    (:market) (:competitive) (:tracking) (:content))
                          (:search      (:seo) (:organic) (:paid) (:social))
                          (:content     (:marketing) (:creation))))

(defn format-color [hue]
  (let [color (create-color {:h hue :s 70 :l 70})]
    (rgb-hexstr color)))

(defn make-color-ranges [node color-range]
  (if (next node)
      (let [partition-size (floor (/ (count color-range) (count (rest node))))
            pairs (map vector (rest node) (partition partition-size color-range))]
        (cons (list (first node) (map format-color color-range)) (map (partial apply make-color-ranges) pairs)))
      (list (first node) (map format-color color-range))))

(defn -main [& args]
  (doall (map println (make-color-ranges services (range 0 361)))))
