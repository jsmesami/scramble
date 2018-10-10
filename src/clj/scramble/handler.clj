(ns scramble.handler
  (:require
    [compojure.core :refer [routes]]
    [compojure.route :refer [not-found]]
    [scramble.api :refer [api-routes]]
    [scramble.site :refer [site-routes]]
    [scramble.middleware :refer [wrap-middleware]]))


(def app
  (routes
    (wrap-middleware #'site-routes)
    api-routes
    (not-found "Not Found")))
