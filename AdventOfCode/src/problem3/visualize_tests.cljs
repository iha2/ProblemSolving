(ns problem3.visualize_tests (:require [problem3.visualize :refer [grid-width]]
                                       [reagent.core :as r]
                                       [clojure.test :refer [use-fixtures testing is deftest run-tests]]
                                       [problem3.grid :refer [grid-width-and-height construct-rec]]
                                       [problem3.constants :refer [test-claims]]))

(def test-state (r/atom {}))

(defn init-state [] (let [recs (map construct-rec test-claims)]
                       (reset! test-state {:claims recs
                                           :grid (merge (grid-width-and-height recs) {:value {}})})))

(deftest test-grid-width
  (testing "grid width"
    (is (= (grid-width test-state) 18))))

(defn run-visualize-tests []
  (init-state)
  (run-tests))