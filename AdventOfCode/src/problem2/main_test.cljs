(ns problem2.main_test
  (:require [cljs.test :refer-macros [deftest is testing run-tests]]
            [problem2.main :refer [difference-in-string-position diff-chararcter-by-one]]))

(def test_set [
               "vybruvapdgixszyckwtfqjonsie"
               "mbruvapxghslyyckwtfqjonsie"
               "mbruvapdghslzyckwtkujonsie"
               "rwruvapdghxlzyckwtfqjcnsie"
               "obruvapdgtxlzyckwtfqionsie"
               "lbruvapdghxqzyckwtfqjfnsie"
               "mbrunapdghxlzyccatfqjonsie"
               "mbruvapdghxlzyokltfqjdnsie"
               "ybruvapdghxlzmckwtfqjmnsie"
               "mbruwaadghxdzyckwtfqjonsie"
               "mbruvapdghxlkyckwtxqjonjie"
               "mbruvaqdghxlzyckwtfqjrnnie"
               "mbruvaqdghxlzyckwtfqjrnnix"])

; (deftest test-difference-in-string-position
;   (testing "string differences"
; (is (= (difference-in-string-position "lbruvapdghxqzyckwtfqjfnsie" "lbruvapdghxqzyckrtfqjfnsie")
;        {:matches '("e" "i" "s" "n" "f" "j" "q" "f" "t" "k" "c" "y" "z" "q" "x" "h" "g" "d" "p" "a" "v" "u" "r" "b" "l"), :difference '("r")}))))

(deftest test-diff-chararcter-by-one
  (testing "string differences"
    (is (= (diff-chararcter-by-one test_set) ["m" "b" "r" "u" "v" "a" "q" "d" "g" "h" "x" "l" "z" "y" "c" "k" "w" "t" "f" "q" "j" "r" "n" "n" "i"]))))

(defn runner [] (run-tests))