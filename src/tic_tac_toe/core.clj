(ns tic-tac-toe.core)

(defn create_board []
  [[nil nil nil] [nil nil nil] [nil nil nil]]
)

(defn check_cell [board [row column] ]
  (not (nil? (nth (flatten board) (+ row (* column (+ row 1))))))
)

(defn mark_field [board row column mark]
  (let [row_line (nth board row)]
    (assoc board row (assoc row_line column mark))
  )
)

(defn empty_board? [board]
  (= board (create_board))
)

(defn full_board? [board]
  (not (some true? (map #(some nil? %) board)))
 )

(defn winner_in_line [line]
  (if (and (apply = line) (not= nil (first line)))
    (first line)
    nil
  )
)

(defn get_diagonal_1 [board]
  (vector (nth (first board) 0) (nth (second board) 1) (nth (last board) 2 ))
)

(defn get_diagonal_2 [board]
  (vector (last (first board)) (second (second board)) (first (last board)))
)

(defn winner? [board]
  (cond
    (not= (first (filter #(not (nil? %)) (map winner_in_line  board))) nil)
      (first (filter #(not (nil? %)) (map winner_in_line  board)))
    (not (nil? (winner_in_line (map first board)))) 
      (winner_in_line (map first board))
    (not (nil? (winner_in_line (map second board))))
      (winner_in_line (map second board))
    (not (nil? (winner_in_line (map last board))))
      (winner_in_line (map last  board))
    (not (nil? (winner_in_line (get_diagonal_1 board))))
      (winner_in_line (get_diagonal_1 board))
    (not (nil? (winner_in_line (get_diagonal_2 board))))
      (winner_in_line (get_diagonal_2 board))
  )
)

