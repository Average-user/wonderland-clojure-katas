(ns doublets.solver
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(def words (-> "words.edn"
               (io/resource)
               (slurp)
               (read-string)))

(defn difference [a b] (count (filter not (map = a b))))

(defn next [record seen current goal]
  (if (= current goal)
    (conj record current)
    (let [ops (->> words
                  (filter #(not (contains? seen %)))
                  (filter #(== (count current) (count %)))
                  (filter #(= 1 (difference current %)))
                  (map #(vector % (difference goal %)))
                  (sort-by second)
                  (map first))]
      (if (empty? ops)
        []
        (letfn [(f [word] (next (conj record current)
                                (conj seen current) word goal))]
          (first (filter (comp not empty?) (map f ops))))))))

(defn doublets [word1 word2] (next [] #{} word1 word2))
