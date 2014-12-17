(ns hierarchical-color.core
  (:require [clojure.math.numeric-tower :refer [floor]]
            [com.evocomputing.colors :refer :all])
  (:gen-class))

(def services '(:services (:development (:techplan) (:frontend) (:backend) (:webdev) (:cms) (:ecommerce))
                          (:design      (:web) (:responsive) (:app) (:ux) (:brand))
                          (:strategy    (:market) (:competitive) (:tracking) (:content))
                          (:search      (:seo) (:organic) (:paid) (:social))
                          (:content     (:marketing) (:creation))))

(def color-hue-min 0)
(def color-hue-max 280)
(def color-hue-step 4)
(def color-saturation 80)
(def color-luminosity 65)

(defn format-color [hue]
  (let [color (create-color {:h hue :s color-saturation :l color-luminosity})]
    (rgb-hexstr color)))

(defn make-color-ranges [node color-range]
  (let [formatted-color-range (map format-color color-range)
        new-node (list (first node) formatted-color-range)]
    (if (next node)
        (let [partition-size (floor (/ (count color-range) (count (rest node))))
              pairs (map vector (rest node) (partition partition-size color-range))]
          (cons new-node (map (partial apply make-color-ranges) pairs)))
        (list new-node))))

(defn print-all-color-ranges [node]
  (println (first (first node)) (first (last (first node))))
  (if (next node)
      (doall (map print-all-color-ranges (rest node)))))

(defn -main [& args]
  (let [color-ranges (make-color-ranges services (range color-hue-min color-hue-max color-hue-step))]
    (print-all-color-ranges color-ranges)))
