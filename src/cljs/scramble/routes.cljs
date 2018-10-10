(ns scramble.routes
  (:require
    [reagent.core :as reagent]
    [secretary.core :as secretary :include-macros true]
    [scramble.views :refer [home]]))


(defonce page (reagent/atom #'home))


(defn current-page []
  [:div [@page]])


(secretary/defroute "/" []
  (reset! page #'home))
