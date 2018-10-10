(ns scramble.site
  (:require
    [compojure.core :refer [GET defroutes]]
    [compojure.route :refer [resources]]
    [hiccup.page :refer [include-js include-css html5]]
    [config.core :refer [env]]))


(def mount-target
  [:div#app
   [:h3 "Loading..."]])


(defn head []
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1"}]
   (include-css (if (env :dev) "/css/site.css" "/css/site.min.css"))])


(defn loading-page []
  (html5
    (head)
    [:body {:class "body-container"}
     mount-target
     (include-js "/js/app.js")]))


(defroutes site-routes
  (GET "/" [] (loading-page))
  (resources "/"))
