(ns problem3.main_test
  (:require [cljs.test :refer-macros [deftest is testing run-tests]]
            [problem3.main :refer [create-grid-unit update-grid-with-rec
                                   add-top-right-keys construct-rec generate-grid
                                   get-corners-of-rec]]
            [problem3.consts :refer [sample-cube sample-cube-2 grid grid-2 sample-grid]]))

(deftest test-construct-rec
  (testing "construct rectangle"
    (is (= (construct-rec [12 "@" 12 11 3 2]) {:index 12
                                               :meta-data "@"
                                               :top-left-x 12
                                               :top-left-y 11
                                               :width 3
                                               :height 2}))))

(def sample-cubes (map construct-rec test-claims))

(deftest test-create-grid-unit
  (testing "create grid value and key"
    (is (= (create-grid-unit 10 10 30 10) {:40-20 1}))))

(deftest test-upgrade-with-rec
  (testing "add rectangle to grid"
    (is (= (update-grid-with-rec {} sample-cube-2) grid))))

(deftest test-generate-grid
  (testing "add rectangle to grid"
    (is (= (generate-grid sample-cubes) sample-grid))))

(deftest test-get-corners-of-rec
  (testing "collect corners of rec"
    (is (= (get-corners-of-rec (add-top-right-keys (construct-rec [13 "@" 12 13 3 3])))
           {:top-left  "12-13"
            :top-right "14-13"
            :bottom-left "12-15"
            :bottom-right "14-15"}))))

(defn run-main-tests [] (run-tests))