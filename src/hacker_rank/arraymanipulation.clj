(ns hacker-rank.arraymanipulation
  (:require [clojure.string :as str]))

(set! *warn-on-reflection* true)

(defn apply_operation [array [lower upper value]]
  (let [updated (update array lower + value)]
    (if (< upper (count array))
      (update updated (inc upper) - value)
      updated)))

(defn process-operations [n initial-ops]
  (reduce
   apply_operation
   (->> n inc range vec)
   initial-ops))

(defn calculate-max [array]
  (->> array
       (reduce
        (fn [[acc m] cur]
          (let [tmp (+ acc cur)]
            [tmp (max tmp m)]))
        [0 0])
       second))

; Complete the arrayManipulation function below.
(defn arrayManipulation [[[_ n] & queries]]
  (->> queries
       (process-operations (inc n))
       calculate-max))

(defn apply-op [vals [from to op]]
  ; (let [ini (subvec vals 0 from)
  ;       mid (map (fn [x] (+ x op)) (subvec vals from (inc to)))
  ;       end (subvec vals (inc to))]
  ;   (vec (concat ini mid end))))
  (map-indexed (fn [i v] (if (and (>= (inc i) from) (<= (inc i) to)) (+ v op) v)) vals))

(defn arrayManipulation' [[[_ n] & queries]]
  (->> queries
       (reduce apply-op (vec (repeat (inc n) 0)))
       (apply max)))

(defn read-lines []
  (with-open [rdr (clojure.java.io/reader "src/hacker_rank/operations-large.txt")]
    (reduce conj [] (line-seq rdr))))

(defn parse-line [l] (map read-string (str/split l #" ")))

(defn read-ops []
  (->> (read-lines)
       (map parse-line)
      ;  (take 2)
       (map vec)))

(->> (read-ops)
     (arrayManipulation)
     (time))

; (arrayManipulation [[10000000 100000] [7253005 9867484 26205]])

; (->> 10000000 range)