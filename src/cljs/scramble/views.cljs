(ns scramble.views
  (:require
    [scramble.form :refer [form]]))


(defn home []
  [:div#home
   [:h2 "Is it a scramble?"]
   [form]])
