(ns hacker-rank.polygon
  (:require [clojure.string :as str])
  (:require [clojure.core.reducers :as r]))

(defn sqrt [n]
  (loop [prox 1.0, part 20]
    (if (pos? part)
      (recur (+ (/ n (* 2 prox)) (/ prox 2)) (dec part))
      prox)))

(defn sqr [n] (* n n))

(defn read-polygon []
  (with-open [rdr (clojure.java.io/reader "src/hacker_rank/polygon2.txt")]
    (rest (reduce conj [] (line-seq rdr)))))

(defn read-polygon2 []
  (with-open [rdr (java.io.BufferedReader. *in*)]
    (rest (reduce conj [] (line-seq rdr)))))

(defn read-point [p] (map read-string (str/split p #" ")))

(defn read-points-sorted [points]
  (sort-by (fn [[x y]] (- 0 (Math/atan2 y x))) (map read-point points)))

(defn read-points [points]
  (map read-point points))

(defn close-path [points]
  (conj (into [] points) (first points)))

(defn sides [points]
  (partition 2 1 points))

(defn side-len [[[x1 y1] [x2 y2]]]
  (sqrt (+ (sqr (- x2 x1)) (sqr (- y2 y1)))))

(defn perimeter [ss]
  (reduce
   (fn [acc side] (+ acc (side-len side)))
   0
   ss))

(defn segment-area [[[x1 y1] [x2 y2]]]
  (let [dx (- x2 x1)
        dy2 (/ (Math/abs (+ y2 y1)) 2.0)]
    (* dx dy2)))

(->> (read-polygon)
     (read-points)
     (close-path)
     (sides)
     (map segment-area)
     (reduce + 0)
     (Math/abs)
     (println))

; (map (fn [[x y]] (println (str "(" x "," y "),"))) (read-points (read-polygon)))