(ns problem3.main (:require [cljs-node-io.core :as io :refer [aslurp aspit]]
                            [cljs.core.async :refer [go chan <! >! buffer put! take!]]
                            [cljs.reader :refer [read-string]]))

(defn construct-cube [properties]
  {:index (nth properties 0)
   :meta-data (nth properties 1)
   :top-left-x (nth properties 2)
   :top-left-y (nth properties 3)
   :width (nth properties 4)
   :height (nth properties 5)})

(defn create-grid-unit [top-left-y top-left-x x y]
  (let [new-x (+ top-left-x x)
        new-y (+ top-left-y y)
        unique-key (keyword (str new-x "-" new-y))]
    {unique-key 1}))

(defn update-grid-with-cube [grid cube]
  (reduce (fn [update-grid-y height]
            (let [top-left-x (:top-left-x cube)
                  top-left-y (:top-left-y cube)
                  width-vals (range (:width cube))]
              (reduce (fn [update-grid-x width]
                        (let [grid-value (create-grid-unit top-left-y top-left-x width height)]
                          (if (nil? ((-> grid-value keys first) update-grid-x)) nil ((-> grid-value keys first) update-grid-x))
                          (merge-with + update-grid-x grid-value)))
                      update-grid-y width-vals)))
          grid (range (:height cube))))

(defn generate-grid [grid-data]
  (reduce #(update-grid-with-cube %1 %2) {} grid-data))



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
                  ;  (println (merge-with +  {:a 12 :b 12} {:a 12 :b 12}) )
                   (println (count (filter #(> (second %1) 1) (->> (map construct-cube cljs-answer) generate-grid))))))))))