(ns problem2.main (:require [cljs-node-io.core :as io :refer [aslurp aspit]]
                            [cljs.core.async :refer [go chan <! >! buffer put! take!]]
                            [cljs.reader :refer [read-string]]
                            [clojure.string :refer [split]]
                            [clojure.data :refer [diff]]
                            [clojure.set :refer [difference]]))

(defn read-from-file [file]
  (go
    (let [[err datastring] (<! (aslurp file))]
      (if-not err
        (let [data (chan)]
          (let [info (read-string datastring)]
            (put! data info)
            data))
        (println err)))))

(defn meta-string [raw-string]
  "gets how many characters per type {:character -number- }"
  (loop [characters (split raw-string "")
         string-meta {}]
    (let [character-key (keyword (first characters))]
      (cond (empty? characters) string-meta
            (->> character-key string-meta nil?) (recur (rest characters) (assoc string-meta character-key 1))
            :else (recur (rest characters) (update string-meta character-key inc))))))

(defn key-values-in-range [min max object]
  "gets how many keys have a value in the range"
  (let [obj-values (->> object vals set)]
    (reduce #(if (contains? obj-values %2) (conj %1 %2) %1) #{} (range min max))))

(defn checksum-term-value [x] (key-values-in-range 2 4 x))

(defn checksum-term-sumation [all-terms-values]
  (reduce #(let [value (key-values-in-range 2 4 (meta-string %2))]
             {:2 (+ (:2 %1) (if (contains? value 2) 1 0))
              :3 (+ (:3 %1) (if (contains? value 3) 1 0))}) {:2 0 :3 0} (apply vector all-terms-values)))

(defn difference-in-string-position [coll-one coll-two]
  (loop [col-1 coll-one
         col-2 coll-two
         matches []
         difference []]
    ; (println difference)
    (cond
      (or (empty? col-1) (empty? col-2)) (do
                                            (println "both empty" {:matches matches
                                                      :difference difference})
                                            {:matches matches
                                             :difference difference})
      ((comp not =) (first col-1) (first col-2)) (recur (rest col-1) (rest col-2) matches (conj difference (first col-2)))
      :else (recur (rest col-1) (rest col-2) (conj matches (first col-2)) difference))))

(defn diff-chararcter-by-one [data]
  (let [unique-collection (apply sorted-set (map str data))]
    ; (println unique-collection)
    (loop [collection unique-collection
           different-by-one ()]
      (if (= 1 (count collection))
        (do
          ; (println different-by-one)
          different-by-one)
        (let [first-ids (first collection)
              second-ids (second collection)
              chars-differences (difference-in-string-position (-> first-ids (split "")) (-> second-ids (split "")))]

          (if (= 1 (count (:difference chars-differences)))
            (recur (rest collection) (conj different-by-one chars-differences))
            (recur (rest collection) different-by-one)))))))

(defn main []
  (take!
   (read-from-file "src/problem2/data.edn")
   (fn [x] (go
             (let [result (<! x)]
               (let [data (apply vector result)]
                 (println (diff-chararcter-by-one data)))
              ;  (let [answer (checksum-term-sumation result)]
              ;    (println (* (:2 answer) (:3 answer))))
               )))))
