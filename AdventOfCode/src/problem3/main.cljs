(ns problem3.main (:require [cljs-node-io.core :as io :refer [aslurp aspit]]
                            [cljs.core.async :refer [go chan <! >! buffer put! take!]]
                            [cljs.reader :refer [read-string]]))

(defn construct-rec [properties]
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

(defn update-grid-with-rec [grid rec]
  (reduce (fn [update-grid-y height]
            (let [top-left-x (:top-left-x rec)
                  top-left-y (:top-left-y rec)
                  width-vals (range (:width rec))]
              (reduce (fn [update-grid-x width]
                        (let [grid-value (create-grid-unit top-left-y top-left-x width height)]
                          (if (nil? ((-> grid-value keys first) update-grid-x)) nil ((-> grid-value keys first) update-grid-x))
                          (merge-with + update-grid-x grid-value)))
                      update-grid-y width-vals)))
          grid (range (:height rec))))

(defn generate-grid [grid-data]
  (reduce #(update-grid-with-rec %1 %2) {} grid-data))

(defn part1 [claims]
  (count (filter #(> (second %1) 1) (->> claims generate-grid))))

(defn get-corners-of-rec [rec]
  {:top-left {:x (:top-left-x rec) :y (:top-right-y rec)}
   :top-right {:x (+ (:width rec) (:top-left-x rec)) :y (:top-right-y rec)}})

(defn get-corners-of-rec [rec]
  {:top-left {:x (:top-left-x rec) :y (:top-right-y rec)}
   :top-right {:x (+ (:width rec) (:top-left-x rec)) :y (:top-right-y rec)}
   :bottom-left {:x (:top-left-x rec) :y (+ (:height rec) height)
                 }
   :bottom-right {:x (+ (:width rec) (:top-left-x rec)) :y (+ (+ (:width rec) (:top-left-y rec)))}})


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
                     (println (filter #(let [bottom-right (keyword (str (+ (:width %1) (:top-left-x %1)) "-" (+ (:height %1) (:top-left-y %1))))
                                             bottom-left (keyword (str (:top-left-x %1) "-" (+ (:height %1) (:top-left-y %1))))
                                             top-right (keyword (str (+ (:width %1) (:top-left-x %1)) "-" (:top-left-y %1)))]
                                         (if (nil? (bottom-right grided-claims))
                                           false)) claims)))))))))