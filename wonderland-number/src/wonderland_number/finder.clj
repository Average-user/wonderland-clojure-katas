(ns wonderland-number.finder)

(defn same-digits? [a b]
  (= (sort (str a)) (sort (str b))))

(defn wonderland-number []
  (letfn [(f [x] (every? #(same-digits? x %) (map #(* x %) [2 3 4 5 6])))]
    (first (filter f (range 100000 1000000)))))
  
