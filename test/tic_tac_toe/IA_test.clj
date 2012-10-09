(ns tic-tac-toe.IA-test
  (:use clojure.test
        tic-tac-toe.IA
        tic-tac-toe.core))

(deftest test_play_to_win
  (testing "IA plays to win in column"
   (def board [["O" nil nil][nil "O" "X" ][nil nil "X"]])
   (def expected_board [["O" nil "X"][nil "O" "X" ][nil nil "X"]])
   (is (= (IA_move board "X") expected_board))
  )

  (testing "IA plays to win in row"
   (def board [["O" nil nil][nil "O" nil ][nil "X"  "X"]])
   (def expected_board [["O" nil nil][nil "O" nil ]["X" "X" "X"]])
   (is (= (IA_move board "X") expected_board))
  )

  (testing "IA plays to win in diagonal"
   (def board [[ nil nil "O"][nil "X" "O" ][nil nil "X"]])
   (def expected_board [[ "X"  nil "O"][nil "X" "O" ][nil nil "X"]])
   (is (= (IA_move board "X") expected_board))
  )
)

(deftest test_play_to_defend
  (testing "IA plays to defend"
   (def board [[nil nil nil][nil "O" nil ]["X" "O" "X"]])
   (def expected_board [[nil "X" nil][nil "O" nil ]["X" "O" "X"]])
   (is (= (IA_move board "X") expected_board))
  )
)

(deftest test_first_IA_move
  (testing "IA moves when empty board"
    (def board (create_board))
    (def expected_board1 [["X" nil nil][nil nil  nil ][nil nil nil]])
    (def expected_board2 [[nil  nil "X"][nil nil  nil ][nil nil nil]])
    (def expected_board3 [[nil  nil nil][nil nil  nil ]["X" nil nil]])
    (def expected_board4 [[nil nil nil][nil nil  nil ][nil nil "X"]])
    (def new_board (IA_move board "X"))
    (is (or
          (= new_board expected_board1)
          (= new_board expected_board2)
          (= new_board expected_board3)
          (= new_board expected_board4)
        )
    )
  )

  (testing "IA moves on corner  when X played on center cell"
    (def board [[nil nil nil][nil "X" nil ][nil nil nil]])
    (def expected_board1 [["O" nil nil][nil "X" nil ][nil nil nil]])
    (def expected_board2 [[nil nil "O"][nil "X" nil ][nil nil nil]])
    (def expected_board3 [[nil  nil nil][nil "X" nil ]["O" nil nil]])
    (def expected_board4 [[nil nil nil][nil "X" nil ][nil nil "O"]])
    (def new_board (IA_move board "O"))
    (is (or
          (= new_board expected_board1)
          (= new_board expected_board2)
          (= new_board expected_board3)
          (= new_board expected_board4)
        )
    )
  )

  (testing "IA moves when X played on a corner"
    (def board [[nil nil nil]["X" nil  nil ][nil nil nil]])
    (def expected_board [[nil nil nil]["X" "O" nil ][nil nil nil]])
    (is (= (IA_move board "O") expected_board))
  )
)

(deftest test_random_move
  (testing "IA moves to random position"
    (def board [["O" nil nil]["X" nil nil][nil nil nil]])
    (is (not= board (IA_move board "X")))
  )
)

(deftest test_can_win_line 
  (testing "can win with 2 marks and a nil"
    (is (= (can_win_line? ["X" nil "X"] "X") true))
  )
  (testing "can't win without the two marks"
    (is (= (can_win_line? ["X" nil nil] "X") false))
  )
)
