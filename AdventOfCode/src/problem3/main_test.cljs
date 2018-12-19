(ns problem3.main_test
  (:require [cljs.test :refer-macros [deftest is testing run-tests]]
            [problem3.main :refer [create-grid-unit update-grid-with-cube]]))

(def sample-cube {:index 1
                  :meta-data "@"
                  :top-left-x 10
                  :top-left-y 10
                  :width 30
                  :height 10})

(deftest test-create-grid-unit
  (testing "create grid value and key"
    (is (= (create-grid-unit 10 10 30 10) {:40-20 1}))))

(deftest test-update-grid-with-cube
  (testing "create full grid"
  (is (= (update-grid-with-cube {} sample-cube) {:40-20 1}))))

(defn runner [] (run-tests))