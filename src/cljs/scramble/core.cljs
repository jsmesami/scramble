(ns scramble.core
  (:require
    [reagent.core :as reagent]
    [secretary.core :as secretary :include-macros true]
    [accountant.core :as accountant]
    [scramble.routes :refer [current-page]]))


(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))


(defn init! []
  (accountant/configure-navigation!
    {:nav-handler (fn [path]
                    (secretary/dispatch! path))
     :path-exists? (fn [path]
                     (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))
