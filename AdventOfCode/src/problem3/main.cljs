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
                          (merge-with + update-grid-x grid-value)))
                      update-grid-y width-vals)))
          grid (range (:height rec))))

(defn generate-grid [grid-data]
  (reduce #(update-grid-with-rec %1 %2) {} grid-data))

(defn part1 [claims]
  (count (filter #(> (second %1) 1) (->> claims generate-grid))))

(defn get-corners-of-rec [rec]
  {:top-left (str (:top-left-x rec) "-" (:top-right-y rec))
   :top-right (str (+ (dec (:width rec)) (:top-left-x rec)) "-" (:top-right-y rec))
   :bottom-left (str (:top-left-x rec) "-" (+ (dec (:height rec)) (:top-left-y rec)))
   :bottom-right (str (+ (dec (:width rec)) (:top-left-x rec)) "-" (+ (dec (:height rec)) (:top-left-y rec)))})

(defn do-any-corners-overlap?
  [grid-state rec]
  (println [((->> rec :top-left keyword) grid-state)
            ((->> rec :top-right keyword) grid-state)
            ((->> rec :bottom-left keyword) grid-state)
            ((->> rec :bottom-right keyword) grid-state)])
  (reduce #(if (> %2 1) (reduced true) %1) false
          [((->> rec :top-left keyword) grid-state)
           ((->> rec :top-right keyword) grid-state)
           ((->> rec :bottom-left keyword) grid-state)
           ((->> rec :bottom-right keyword) grid-state)]))

(defn add-top-right-keys [rec]
  (merge rec {:top-right-y (:top-left-y rec)
              :top-right-x (+ (:top-left-x rec) (dec (:width rec)))}))

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
                     (println grided-claims)
                     (println (first-pure-rec (->> claims generate-grid) claims)))))))))