(ns hacker-rank.chocolate)

; (1 2 3)
; (1 1) - (0 2 3)

; (2 1) - (0 0 3)
; (2 2) - (1 1 3)

; (3 1) - (0 0 0)
; (3 2) - (1 1 1)
; (3 3) - (1 2 2)

; (3 k) -> (x y z) = ((min x k-1) (min y k-1) k-1)

(defn play [[r c] {p :p [x y z] :s}]
  (let [m (- c 1)
        p' (if (= p 1) 2 1)
        x' (Math/min m x)
        y' (Math/min m y)
        z' (Math/min m z)]
    (cond
      (= r 1) {:p p' :s [x' y z]}
      (= r 2) {:p p' :s [x' y' z]}
      (= r 3) {:p p' :s [x' y' z']})))

(defn moves-for-row [r x] (map (fn [n] [r n]) (range 1 (inc x))))

(defn moves [[x y z]]
  (concat
   (moves-for-row 1 x)
   (moves-for-row 2 y)
   (moves-for-row 3 z)))

(defn game [{p :p [x y z] :s :as g}]
  (let [next (map #(play % g) (moves [1 x y z]))]
    next))

(game {:p 1 :s [1 2 3]})