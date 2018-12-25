(ns problem3.visualize (:require [reagent.core :as r :refer [adapt-react-class]]
                                 ["d3" :as d3]))

(defonce viz-state (r/atom {:claims '()
                            :grid {}}))


(defn canvas [ratom]
  (let [width  "100%"
        height "100%"]
    [:div
     {:id "canvas"}
     [:svg
      {:width  width
       :height height}]]))

(defn viz [] (canvas viz-state))

; Visualize rectangle space
(defn start []
  (r/render [viz]
            (.getElementById js/document "app")))

(defn ^:export init []
  (start))