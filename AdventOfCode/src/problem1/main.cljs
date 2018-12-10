(ns problem1.main (:require [cljs-node-io.core :as io :refer [aslurp aspit]]
                            [cljs.core.async :refer [go chan <! >! buffer put!]]
                            [cljs.reader :refer [read-string]]))

(defn read-from-file [file]
  (go
    (let [[err datastring] (<! (aslurp file))]
      (if-not err
          (let [data (chan)]
           (do
             (put! data (read-string datastring))
              data))
          (println err)))))

; (defn get-problem-result []
;   (go 
;    (loop 
;     (let (<! fil)))))

(defn get-result [a-result]
  (reduce + 0 (apply vector a-result)))

(defn main []
  (go (println (read-from-file "public/data/data.edn"))))
