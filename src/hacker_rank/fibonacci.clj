(ns hacker-rank.fibonacci)

(defn read-ints-file []
  (with-open [rdr (clojure.java.io/reader "src/hacker_rank/numbers.txt")]
    (map read-string (rest (reduce conj [] (line-seq rdr))))))

(defn read-int []
  (with-open [rdr (java.io.BufferedReader. *in*)]
    (read-string (first (line-seq rdr)))))

(defn read-ints []
  (with-open [rdr (java.io.BufferedReader. *in*)]
    (map read-string (rest (reduce conj [] (line-seq rdr))))))

(def fib
  (memoize
   (fn [n]
     (cond
       (= 0 n) 0N
       (= 1 n) 1N
       :else (+ (fib (- n 1N)) (fib (- n 2N)))))))

(->> (read-ints-file)
     (map fib)
     (map #(mod % 100000007))
     (map int)
     (map println))

(print
 (reduce
  (fn [acc c] (str acc c "\n"))
  ""
  (->> (read-ints-file)
       (map fib)
       (map #(mod % 100000007))
       (map int))))
