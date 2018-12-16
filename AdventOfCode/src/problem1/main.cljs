(ns problem1.main (:require [cljs-node-io.core :as io :refer [aslurp aspit]]
                            [cljs.core.async :refer [go chan <! >! buffer put! take!]]
                            [cljs.reader :refer [read-string]]))

(defn read-from-file [file]
  (go
    (let [[err datastring] (<! (aslurp file))]s
      (if-not err
        (let [data (chan)]
          (let [info (read-string datastring)]
            (put! data info)
            data))
        (do err)))))


(defn get-result [a-result]
  (reduce + 0 (apply vector a-result)))

(defn main []
  (take!
   (read-from-file "src/problem2/data.edn")
   (fn [x] (go
             (let [result (<! x)]
               (println result))))))
