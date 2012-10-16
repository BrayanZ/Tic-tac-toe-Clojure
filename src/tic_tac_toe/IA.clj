(ns tic-tac-toe.IA
  (:use tic-tac-toe.core
        tic-tac-toe.misc))

(def corners '((0 0) (0 2) (2 0) (2 2)))
(def center '(1 1))

(defn get_corner []
  (nth
    corners
    (rand-int 4))
  )

(defn can_win_line? [line mark]
  (and (= (count_occurrency line mark) 2) (= (count_occurrency line nil) 1))
)

(defn first_move? [board]
  (empty_board? board)
)

(defn second_move? [board]
  (let [c (count (filter nil? (flatten board)))]
    (if (= c 8)
     true
     false
    )
  )
)

(defn can_win_any_line? [lines mark]
  (let [line (first lines)]
    (cond 
      (empty? line)
        false
      (can_win_line? line mark)
        true
      :else
        (recur (rest lines) mark )
    )
  )
)

(defn can_win_row? [board mark]
  (can_win_any_line? board mark)
)

(defn can_win_column? [board mark] 
  (let [ new_board (get_columns board)]
    (can_win_any_line? new_board mark)
  )
)

(defn can_win_diagonal? [board mark] 
  (let[diagonals (list (get_diagonal_1 board) (get_diagonal_2 board))]
    (can_win_any_line? diagonals mark)
  )
)

(defn can_win? [board mark] 
  (or
    (can_win_row? board mark)
    (can_win_column? board mark)
    (can_win_diagonal? board mark)
  )
)

(defn can_defend? [board mark]
  (can_win? board (opposite_mark mark))
)

(defn get_second_move [board]
 (if (marked_cell? board center)
   (get_corner)
   center
 )
)

(defn win_row [board mark]
  (loop  [new_board board]
    (let [row (first new_board) ]
      (cond 
        (empty? row)
          nil
        (can_win_line? row mark)
          (list (.indexOf board row) (.indexOf row nil))
        :else
          (recur (rest new_board) )
      )
    )
  )
)

(defn win_column [board mark] 
  (let [ new_board (get_columns board)]
    (loop [board new_board]
      (let [column (first board)]
        (cond
          (empty? column)
            nil
          (can_win_line? column mark)
            (list (.indexOf column nil) (.indexOf new_board column))
          :else
            (recur (rest board))
        )
      )
    )
  )
)


(defn win_diagonal [board mark] 
  (let[diagonals (list (get_diagonal_1 board) (get_diagonal_2 board))]
    (loop [new_diagonals diagonals]
      (let [diagonal (first new_diagonals)]
        (cond
          (empty? diagonal)
            nil
          (can_win_line? diagonal mark)
            (let [diagonal_number (.indexOf diagonals diagonal)]
              (if (= diagonal_number 0)
                (list (.indexOf diagonal nil) (.indexOf diagonal nil))
                (list (.indexOf diagonal nil) (- 2 (.indexOf diagonal nil)))
                )
              )
          :else
            (recur (rest new_diagonals))
         )
      )
    )
  )
)

(defn win_move [board mark] 
  (cond 
    (can_win_row? board mark)
      (win_row board mark)
    (can_win_column? board mark)
      (win_column board mark)
    (can_win_diagonal? board mark)
      (win_diagonal board mark)
  )
)

(defn defend_move [board mark]
  (win_move board (opposite_mark mark))
)

(defn random_move [board]
  (let [row (rand-int 3)
        column (rand-int 3)]
    (if (marked_cell? board [row column])
      (recur board)
      [row column]
    )
  )
)

(defn get_best_move [board mark]
  (cond
    (first_move? board)
      (get_corner)
    (second_move? board)
      (get_second_move board)
    (can_win? board mark)
      (win_move board mark)
    (can_defend? board mark)
      (defend_move board mark)
    :else
      (random_move board)
  )
)

(defn IA_move [board mark]
  (let [position (get_best_move board mark)]
    (mark_field board position mark))
)
