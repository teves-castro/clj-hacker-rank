(ns hacker-rank.arraymanipulation
  (:require [clojure.string :as str]))

(defn apply-op [vals [from to op]]
  ; (let [ini (subvec vals 0 from)
  ;       mid (map (fn [x] (+ x op)) (subvec vals from (inc to)))
  ;       end (subvec vals (inc to))]
  ;   (vec (concat ini mid end))))
  (map-indexed (fn [i v] (if (and (>= (inc i) from) (<= (inc i) to)) (+ v op) v)) vals))

(apply-op [0 0 0 0 0] [1 3 1])

(defn arrayManipulation [[[_ n] & queries]]
  (->> queries
       (reduce apply-op (vec (repeat (inc n) 0)))
       (apply max)))

(defn read-lines []
  (with-open [rdr (clojure.java.io/reader "src/hacker_rank/operations.txt")]
    (reduce conj [] (line-seq rdr))))

(defn parse-line [l] (map read-string (str/split l #" ")))

(defn read-ops []
  (->> (read-lines)
       (map parse-line)
       (map vec)
       (take 1000)))

(->> (read-ops)
     (arrayManipulation)
     (time))
