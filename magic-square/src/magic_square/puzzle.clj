(ns magic-square.puzzle
  (:require [clojure.math.combinatorics :as combo]))

(def values [1.0 1.5 2.0 2.5 3.0 3.5 4.0 4.5 5.0])

(defn magic-square [values]
  (letfn [(f       [x] (map (partial reduce +) x))
          (s       [x] (= 1 (count (set (apply concat (map #(% x) [f columns diag]))))))
          (columns [x] (f (apply mapv vector x)))
          (diag    [x] (f (partition 3 (map #(get-in (vec (map vec x)) %)
                                            [[0 0] [1 1] [2 2] [0 2] [1 1] [2 0]]))))]
   (vec (map vec (partition 3 (first (filter #(s (partition 3 %))
                                             (combo/permutations values))))))))
                                        
