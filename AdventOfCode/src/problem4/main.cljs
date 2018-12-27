(ns problem4.main (:require [cljs.core.async :refer [go take! <! >! put! chan]]
                            [cljs.reader :refer [read-string]]
                            [cljs-node-io.core :as io :refer [aslurp aspit]]))


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

(defn main []
  (take!
   (read-from-file "src/problem4/data.edn")
   (fn [x] (go (let [result (<! x)]
                 (println result))))))