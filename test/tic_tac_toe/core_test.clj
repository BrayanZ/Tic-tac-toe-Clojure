(ns tic-tac-toe.core-test
  (:use clojure.test
        tic-tac-toe.core))

(deftest test_check_cell 
  (testing "verify if a cell is marked"
    (def board [[nil nil nil ][nil nil "X" ][nil nil nil ]])
    (is (= (check_cell board '(1 2)) true))
  )

  (testing "verify if a cell isn't marked"
    (def board [[nil nil nil ][nil nil "X" ][nil nil nil ]])
    (is (= (check_cell board '(1 1)) false))
  )
)

(deftest test_create_board
  (testing "creates a board."
    (is (= (create_board) [[nil nil nil] [nil nil nil] [nil nil nil]]))
  )
)

(deftest test_mark_field 
  (testing "marks a field" 
    (is (= (mark_field (create_board)  1 2 "x") [[nil nil nil] [nil nil "x"] [nil nil nil]]))
  )
)


(deftest test_full_board
  (testing "checks if the board is full"
    (def board [["X" "O" "X"]["O" "X" "O"]["O" "X" "O"]])
    (is (= (full_board? board) true))
  )
  (testing "checks a not full board"
    (def board [["X" "O" nil]["O" "X" "O"]["O" "X" "O"]])
    (is (= (full_board? board) false))
  )
)

(deftest test_winner
  (testing "checks if some player won"
    (def board [["X" "X" "X"]["O" "X" "O"]["O" "X" "O"]])
    (is (= (winner? board) "X"))
  )
  (testing "no winner"
    (def board [["X" "O" "X"]["O" "X" "O"]["O" "X" "O"]])
    (is (= (winner? board) nil))
  )
  (testing "Empty board, no winner"
    (is (= (winner? (create_board)) nil))
  )
)

(deftest test_winner_in_line
  (testing "checks if there are a winner in the row"
    (def line ["X" "X" "X"])
    (is (= (winner_in_line line) "X"))
  )
)

(deftest test_get_diagonal_1
  (testing "return the diagonal 1"
    (def board [["X" "O" "X"]["O" "X" "O"]["O" "X" "O"]])
    (is (= (get_diagonal_1 board) ["X" "X" "O"]))
  )
)

(deftest test_get_diagonal_2
  (testing "return the diagonal 2"
    (def board [["X" "O" "X"]["O" "X" "O"]["O" "X" "O"]])
    (is (= (get_diagonal_2 board) ["X" "X" "O"]))
  )
)

(deftest test_empty_board
  (testing "is empty the board"
    (is (= (empty_board? (create_board)) true))
))
