(ns hacker-rank.differentways
  (:require [hacker-rank.common :as c])
  (:require [clojure.string :as str]))

(defn read-lines []
  (with-open [rdr (java.io.BufferedReader. *in*)]
    (rest (reduce conj [] (line-seq rdr)))))

(defn parse-line [l] (map read-string (str/split l #" ")))

(def fac
  (memoize
   (fn [n]
     (loop [k n f 1N]
       (if (< k 1N)
         f
         (recur (dec k) (* f k)))))))

(def comb
  (memoize
   (fn [n k] (/ (fac n) (* (fac k) (fac (- n k)))))))

(print
 (->>
  (c/read-lines-file "src/hacker_rank/lemurs.txt")
  (map parse-line)
  (map #(apply comb %))
  (map #(mod % 100000007))
  (map long)
  (reduce #(str %1 %2 "\n") "")))
