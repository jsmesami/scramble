(ns scramble.api
  (:require
    [compojure.api.sweet :refer [context defapi GET]]
    [ring.util.http-response :refer [ok]]
    [scramble.core :refer [scramble?]]))


(defapi api-routes
  (context "/api" []
    :tags ["api"]
    (GET "/scramble" []
      :return {:result Boolean}
      :query-params [str1 :- String, str2 :- String]
      :summary (:doc (meta #'scramble?))
      (ok {:result (scramble? str1 str2)}))))
