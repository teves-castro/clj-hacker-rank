(ns hacker-rank.stringopermute)

(defn read-strings-file []
  (with-open [rdr (clojure.java.io/reader "src/hacker_rank/strings.txt")]
    (rest (reduce conj [] (line-seq rdr)))))

(defn read-strings []
  (with-open [rdr (java.io.BufferedReader. *in*)]
    (rest (reduce conj [] (line-seq rdr)))))

(defn permute-string [s]
  (->> s
       (partition 2 2)
       (mapcat reverse)
       (apply str)))

(map (comp println permute-string) (read-strings-file))
