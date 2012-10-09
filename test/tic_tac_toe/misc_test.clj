(ns tic-tac-toe.core-test
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
    (def expected_board [["X" nil nil][ nil nil  "X"]["O" "X" "O"]])
    (is (= (get_columns board) expected_board))
  )
)

