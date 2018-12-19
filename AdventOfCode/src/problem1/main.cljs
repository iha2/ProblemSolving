(ns problem1.main (:require [cljs-node-io.core :as io :refer [aslurp aspit]]
                            [cljs.core.async :refer [go chan <! >! buffer put! take!]]
                            [cljs.reader :refer [read-string]]))

(defn read-from-file [file]
  (go
    (let [[err datastring] (<! (aslurp file))] s
         (if-not err
           (let [data (chan)]
             (let [info (read-string datastring)]
               (put! data info)
               data))
           (do err)))))


(defn resulting-frequency [a-result]
  (reduce + 0 (apply vector a-result)))

(defn duplicate-frequency [collection]
  (loop [coll collection
         last-freq 0
         frequencies #{}]
    (let [current-freq (+ last-freq (first coll))]
      (if (contains? frequencies current-freq)
        current-freq
        (recur (rest coll) current-freq (conj frequencies current-freq))))))

(defn main []
  (take!
   (read-from-file "src/problem1/data.edn")
   (fn [x] (go
             (let [result (<! x)]
               (println (resulting-frequency (apply vector result)))
               (println (duplicate-frequency (cycle (apply vector result)))))))))
