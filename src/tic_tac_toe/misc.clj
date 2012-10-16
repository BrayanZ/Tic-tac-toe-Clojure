(ns tic-tac-toe.misc)

(defn count_occurrency [coll element]
  (count (filter #(= element %) coll))
)

(defn opposite_mark [mark]
  (if (= mark "X")
    "O"
    "X"
  )
)

(defn get_columns [board]
  (list
    (map first board)
    (map second board)
    (map last board)
  )
)

(defn get_flatten_position [[row column]]
  (+ (* row 3) column))
