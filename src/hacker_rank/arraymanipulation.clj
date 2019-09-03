(ns hacker-rank.arraymanipulation
  (:require [clojure.string :as str]))

(set! *warn-on-reflection* true)

(defn apply_operation [[lower upper value] array]
  (let [updated (update array lower + value)]
    (if (< upper (count array))
      (update updated (inc upper) - value)
      updated)))

(defn process-operations [initial-ops n]
  (reduce
   (fn [acc cur] (apply_operation cur acc))
   (vec (int-array (inc n)))
   initial-ops))

(defn calculate-max [array]
  (second (reduce
           (fn [[acc max] cur]
             (let [tmp (+ acc cur)]
               [tmp (if (> tmp max) tmp max)]))
           [0 0]
           array)))

; Complete the arrayManipulation function below.
(defn arrayManipulation' [[[_ n] & queries]]
  (calculate-max (process-operations queries (inc n))))

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
       (map vec)))

(->> (read-ops)
     (arrayManipulation')
     (time))
