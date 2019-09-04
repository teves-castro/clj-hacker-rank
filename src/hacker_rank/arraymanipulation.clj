(ns hacker-rank.arraymanipulation
  (:require [clojure.string :as str]))

(defn apply_operation [m [lower upper value]]
  (let [afterUpper (inc upper)]
    (assoc m
           lower (+ (m lower 0) value)
           afterUpper (- (m afterUpper 0) value))))

(defn calculate-max [ops]
  (->> ops
       (reduce-kv
        (fn [[acc m] _ cur]
          (let [tmp (+ acc cur)]
            [tmp (max tmp m)]))
        [0 0])
       second))

(defn arrayManipulation [[[n _] & ops]]
  (->> ops
       (reduce apply_operation (sorted-map))
       calculate-max))

(defn read-lines []
  (with-open [rdr (clojure.java.io/reader "src/hacker_rank/operations-large.txt")]
    (reduce conj [] (line-seq rdr))))

(defn parse-line [l] (map read-string (str/split l #" ")))

(defn read-ops []
  (->> (read-lines)
       (map parse-line)
       (map vec)))

(->> (read-ops)
     (arrayManipulation)
     (time))

(time (arrayManipulation [[10000000 1] [7253005 9867484 26205]]))

; (->> 10000000 range)
