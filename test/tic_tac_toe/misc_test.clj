(ns tic-tac-toe.misc_test
  (:use clojure.test
        tic-tac-toe.misc))


(deftest test_count_ocurrency 
  (testing "get count of occurrencies of element in a collection"
    (is (= (count_occurrency '(2 3 5  3 4 3) 3) 3))
  )
)

(deftest test_opposite_mark
  (testing "get the opposite mark"
    (is (= (opposite_mark "X") "O"))
  )
)

(deftest test_get_columns 
  (testing "gets the columns of the board"
    (def board [["X" nil "O"]["O" nil "X"][nil "X" "O"]])
    (def expected_board [["X" "O" nil][ nil nil  "X"]["O" "X" "O"]])
    (is (= (get_columns board) expected_board))
  )
)

(deftest 
  test_get_flatten_position
  (testing 
    "gets the position for the flatten board"
    (is (= 0 (get_flatten_position '(0 0))))
    (is (= 1 (get_flatten_position '(0 1))))
    (is (= 2 (get_flatten_position '(0 2))))
    (is (= 3 (get_flatten_position '(1 0))))
    (is (= 4 (get_flatten_position '(1 1))))
    (is (= 5 (get_flatten_position '(1 2))))
    (is (= 6 (get_flatten_position '(2 0))))
    (is (= 7 (get_flatten_position '(2 1))))
    (is (= 8 (get_flatten_position '(2 2))))
    ))

