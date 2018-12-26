(ns problem3.visualize (:require [reagent.core :as r :refer [adapt-react-class]]
                                 [problem3.grid :refer [construct-rec generate-grid grid-width-and-height]]
                                 ["d3" :as d3]))

(def test-claims [[12 "@" 13 11 5 2]
                  [13 "@" 12 13 3 3]
                  [14 "@" 12 12 2 4]
                  [15 "@" 15 14 2 4]])

(defonce viz-state (let [recs (map construct-rec test-claims)]
                     (r/atom {:claims recs
                              :grid (merge (grid-width-and-height recs) {:values recs})})))

(defn grid-width [ratom]
  (->> @ratom :grid :width))

(defn grid-height [ratom]
  (->> @ratom :grid :height))

(defn grid-data [ratom]
  (->> @ratom :grid :values))

(defn canvas [ratom]
  (let [width  (grid-width ratom)
        height (grid-height ratom)]
    [:div
     {:id "canvas"}
     [:svg
      {:width  width
       :height height}]]))

(defn setup-canvas [ratom]
  (-> (.select d3 "#canvas svg")
      (.attr "width" (->> @ratom :grid :width))
      (.attr "height" (->> @ratom :grid :height))
      (.append "g")
      (.attr "class" "grid")))

(defn build-recs [ratom]
  
(-> (.select d3 "#canvas svg .grid")
    (.selectAll "rect")
    (.data (clj->js (grid-data ratom)))
    .enter
    (.append "rect")
    (.attr "x" #(:top-left-x (js->clj %1 :keywordize-keys true)))
    (.attr "y" #(:top-left-y (js->clj %1 :keywordize-keys true)))
    (.attr "width" #(:width (js->clj %1 :keywordize-keys true)))
    (.attr "height" #(:height (js->clj %1 :keywordize-keys true)))))

(defn update-recs [ratom]
  (-> (.select d3 "#canvas svg .grid")
      (.selectAll "rect")))

(defn container-enter [ratom]
  (setup-canvas ratom)
  (build-recs ratom))

(defn viz-render [ratom]
  (canvas ratom))

(defn container-did-mount [ratom]
  (container-enter ratom))

(defn viz-did-mount [ratom]
  (container-did-mount ratom))

(defn viz [ratom]
  (r/create-class
   {:reagent-render      #(viz-render ratom)
    :component-did-mount #(viz-did-mount ratom)}))

(defn viz-container []
  (viz viz-state))

; Visualize rectangle space
(defn start []
  (r/render [viz-container]
            (.getElementById js/document "app")))

(defn ^:export init []
  (start))