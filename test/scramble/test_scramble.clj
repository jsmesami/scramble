(ns scramble.test-scramble
  (:require
    [clojure.test :refer :all]
    [scramble.core :refer [scramble?]]))


(deftest test-scramble
  (testing "Test scramble function"
    (are [result args] (= result (apply scramble? args))
      true ["rekqodlw" "world"]
      true ["cedewaraaossoqqyt" "codewars"]
      false ["katas" "steak"])))
