(ns problem1.main (:require [cljs-node-io.core :as io :refer [aslurp aspit]]
                            [cljs.core.async :refer [go chan <! >! buffer put! take!]]
                            [cljs.reader :refer [read-string]]))

(defn read-from-file [file]
  (go
    (let [[err datastring] (<! (aslurp file))]
      (if-not err
        (let [data (chan)]
          (do
            (let [info (read-string datastring)]
              (put! data info)
              println data)
            data))
        (println err)))))

; (defn get-problem-result []
;   (go 
;    (loop 
;     (let (<! fil)))))

(defn get-result [a-result]
  (reduce + 0 (apply vector a-result)))

(defn main []
  (take!
   (read-from-file "public/data/data.edn")
   (fn [x] (go (let [result (<! x)]
                 (println (get-result (apply vector result))))))))
