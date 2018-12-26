(ns problem3.grid)

(defn construct-rec [properties]
  {:index (nth properties 0)
   :meta-data (nth properties 1)
   :top-left-x (nth properties 2)
   :top-left-y (nth properties 3)
   :width (nth properties 4)
   :height (nth properties 5)})

(defn construct-recs [claims] (map construct-rec claims))

(defn grid-width-and-height [recs]
  (let [left-most (reduce #(if (= (max (:top-left-x %1) (:top-left-x %2)) (:top-left-x %1))
                             %1
                             %2) recs)
        bottom-most (reduce #(if (= (max (:top-left-y %1) (:top-left-y %2)) (:top-left-y %1))
                               %1
                               %2) recs)]
    {:width (+ (:top-left-x left-most) (:width left-most))
     :height (+ (:top-left-y bottom-most) (:height bottom-most))}))

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

(defn get-corners-of-rec [rec]
  {:top-left (str (:top-left-x rec) "-" (:top-right-y rec))
   :top-right (str (+ (dec (:width rec)) (:top-left-x rec)) "-" (:top-right-y rec))
   :bottom-left (str (:top-left-x rec) "-" (+ (dec (:height rec)) (:top-left-y rec)))
   :bottom-right (str (+ (dec (:width rec)) (:top-left-x rec)) "-" (+ (dec (:height rec)) (:top-left-y rec)))})

(defn do-any-corners-overlap?
  [grid-state rec]
  (reduce #(if (> %2 1) (reduced true) %1) false
          [((->> rec :top-left keyword) grid-state)
           ((->> rec :top-right keyword) grid-state)
           ((->> rec :bottom-left keyword) grid-state)
           ((->> rec :bottom-right keyword) grid-state)]))

(defn add-top-right-keys [rec]
  (merge rec {:top-right-y (:top-left-y rec)
              :top-right-x (+ (:top-left-x rec) (dec (:width rec)))}))

(defn generate-grid [grid-data]
  (reduce #(update-grid-with-rec %1 %2) {} grid-data))