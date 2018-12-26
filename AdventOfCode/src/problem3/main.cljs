(ns problem3.main (:require [cljs-node-io.core :as io :refer [aslurp aspit]]
                            [cljs.core.async :refer [go chan <! >! buffer put! take!]]
                            [cljs.reader :refer [read-string]]
                            [problem3.grid :refer [get-corners-of-rec construct-rec
                                                   generate-grid do-any-corners-overlap?
                                                   add-top-right-keys]]))


(defn part1 [claims]
  (count (filter #(> (second %1) 1) (->> claims generate-grid))))

(defn first-pure-rec [grid-state recs]
  (reduce #(if (do-any-corners-overlap? grid-state (->> %1 add-top-right-keys get-corners-of-rec))
             %2
             (reduced %1)) recs))


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
   (read-from-file "src/problem3/data.edn")
   (fn [x] (go (let [result (<! x)]
                 (let [cljs-answer (apply vector result)]
                   (let [claims (->> (map construct-rec cljs-answer))
                         grided-claims (part1 claims)]
                     (println (first-pure-rec (->> claims generate-grid) claims)))))))))
