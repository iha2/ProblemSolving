(ns problem3.main_test
  (:require [cljs.test :refer-macros [deftest is testing run-tests]]
            [problem3.main :refer [create-grid-unit update-grid-with-rec add-top-right-keys construct-rec generate-grid get-corners-of-rec]]))

(def sample-cube {:index 1
                  :meta-data "@"
                  :top-left-x 10
                  :top-left-y 10
                  :width 30
                  :height 10})

(def sample-cube-2 {:index 1
                    :meta-data "@"
                    :top-left-x 10
                    :top-left-y 10
                    :width 5
                    :height 5})

(def grid {:12-11 1, :12-10 1, :13-12 1, :13-13 1, :11-11 1, :14-10 1, :12-14 1
           :12-13 1, :13-11 1, :10-13 1, :14-12 1, :11-12 1, :10-10 1, :10-14 1
           :14-14 1, :14-13 1, :10-11 1, :10-12 1, :14-11 1, :13-14 1, :11-13 1
           :13-10 1, :12-12 1, :11-10 1, :11-14 1})

(def grid-2 {:12-11 1, :12-10 1, :13-12 1, :13-13 1, :11-11 1, :14-10 1, :12-14 1
             :12-13 1, :13-11 1, :10-13 1, :14-12 1, :11-12 1, :10-10 1, :10-14 1
             :14-14 1, :14-13 1, :10-11 1, :10-12 1, :14-11 1, :13-14 1, :11-13 1
             :13-10 1, :12-12 1, :11-10 1, :11-14 1})


(deftest test-construct-rec
  (testing "construct rectangle"
    (is (= (construct-rec [12 "@" 12 11 3 2]) {:index 12
                                               :meta-data "@"
                                               :top-left-x 12
                                               :top-left-y 11
                                               :width 3
                                               :height 2}))))

(def test-claims [[12 "@" 13 11 5 2]
                  [13 "@" 12 13 3 3]
                  [14 "@" 12 12 2 4]])

; {:13-12 2, :13-13 2, :17-11 1, :17-12 1, :14-15 1, :16-11 1, :12-14 2, :12-13 2, :13-11 1,
; :14-12 1, :16-12 1, :15-11 1, :12-15 2, :13-15 2, :14-14 1, :14-13 1, :14-11 1, :13-14 2,
; :12-12 1, :15-12 1}

(def sample-grid {:13-11 1, :14-11 1, :15-11 1, :16-11 1, :17-11 1
                  :12-12 1, :13-12 2, :14-12 1, :15-12 1, :16-12 1, :17-12 1
                  :12-13 2, :13-13 2, :14-13 1
                  :12-14 2, :13-14 2, :14-14 1
                  :12-15 2, :13-15 2, :14-15 1})



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

(defn runner [] (run-tests))