(ns problem3.main (:require [cljs-node-io.core :as io :refer [aslurp aspit]]
                            [cljs.core.async :refer [go chan <! >! buffer put! take!]]
                            [cljs.reader :refer [read-string]]))

(defn construct-cube [properties]
  (print properties)
  {:index (nth properties 0)
   :meta-data (nth properties 1)
   :top-left-x (nth properties 2)
   :top-left-y (nth properties 3)
   :width (nth properties 4)
   :height (nth properties 5)})


(defn update-length [state]
  "should return - {:top-x [:112 :113 :114...] :top-3 [:312 :313 :314...]}"
  {:top-x (reduce #(conj %1 (keyword (+ (:top-left-x state) %2))) [] (range (:width state)))
   :top-y (reduce #(conj %1 (keyword (+ (:top-left-y state) %2))) [] (range (:height state)))})

(defn bottom-right [properties]
  {:bottom-right-x (+ (:width properties) (:top-left-x properties))
   :bottom-right-y (+ (:height properties) (:top-left-y properties))})

(defn bottom-right-hash [properties]
  (Math/SQRT2 (:bottom-left-y properties) (:bottom-left-x properties)))

(defn top-left-hash [properties]
  (Math/SQRT2 (:top-left-y properties) (:top-left-x properties)))

(defn nested-square [square-prop-one square-prop-two]
  (if (< (top-left-hash square-prop-two) (bottom-right-hash square-prop-one))
    (* (- (:bottom-right-y square-prop-one) (:top-left-y square-prop-two)) (- (:bottom-right-x square-prop-one) (:top-left-x square-prop-two)))
    0))



(defn sort-squares [square-collection]
  ; (println (->> square-collection first construct-cube))
; #(let [cube (construct-cube %)] (fn [x] (println (:top-left-y cube) (:top-left-x cube)) (Math/SQRT2 (:top-left-y cube) (:top-left-x cube))) construct-cube)  
  (sort-by (fn [x] (->> x construct-cube (fn [x] (Math/SQRT2 (:top-left-y x) (:top-left-x x))))) square-collection))

; (generate-cube [properties]
;                (reduce ))

(defn search-for-overlaps [sorted-squares]
  (loop [squares sorted-squares
         last-square (first sorted-squares)
         current-square (second sorted-squares)
         overlap-data ((construct-cube last-square))]))


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
                 (let [cljs-answer (take 10 (apply vector result))]
                   (println (->> cljs-answer sort-squares))))))))