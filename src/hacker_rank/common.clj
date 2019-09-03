(ns hacker-rank.common)

(defn read-lines-file [file]
  (with-open [rdr (clojure.java.io/reader file)]
    (rest (reduce conj [] (line-seq rdr)))))

(defn read-lines []
  (with-open [rdr (java.io.BufferedReader. *in*)]
    (rest (reduce conj [] (line-seq rdr)))))