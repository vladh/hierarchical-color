(ns hierarchical-color.core
  (:require [clojure.math.numeric-tower :refer [floor]]
            [com.evocomputing.colors :refer :all])
  (:gen-class))

(def services '(:services (:development (:techplan) (:frontend) (:backend) (:webdev) (:seo) (:ecommerce))
                          (:design      (:web) (:responsive) (:app) (:ux) (:brand))
                          (:strategy    (:market) (:competitive) (:tracking) (:content))
                          (:search      (:seo) (:organic) (:paid) (:social))
                          (:content     (:marketing) (:creation))))

(defn format-color [hue]
  (let [color (create-color {:h hue :s 80 :l 65})]
    (rgb-hexstr color)))

(defn make-color-ranges [node color-range]
  (let [formatted-color-range (map format-color color-range)
        new-node (list (first node) formatted-color-range)]
    (if (next node)
        (let [partition-size (floor (/ (count color-range) (count (rest node))))
              pairs (map vector (rest node) (partition partition-size color-range))]
          (cons new-node (map (partial apply make-color-ranges) pairs)))
        new-node)))

(defn -main [& args]
  (let [color-ranges (make-color-ranges services (range 0 361 4))]
    (doseq [category color-ranges] (doseq [item category] (println item)))))
