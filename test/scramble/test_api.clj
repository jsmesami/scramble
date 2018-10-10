(ns scramble.test-api
  (:require
    [cheshire.core :as cheshire]
    [clojure.test :refer :all]
    [ring.mock.request :refer [request]]
    [ring.util.codec :refer [form-encode]]
    [scramble.handler :refer [app]]))


(defn parse-body
  [body]
  (cheshire/parse-string (slurp body) true))


(defn GET
  [path params]
  (app (request :get (str path "?" (form-encode params)))))


(defn check-response
  [status result params]
  (let [response (GET "/api/scramble" params)
        body     (parse-body (:body response))]
    (and (= (:status response) status)
         (= (:result body) result))))


(deftest test-api
  (testing "Test requests to /api/scramble"
    (are [status result params] (true? (check-response status result params))
      200 true {:str1 "rekqodlw" :str2 "world"}
      200 true {:str1 "cedewaraaossoqqyt" :str2 "codewars"}
      200 false {:str1 "katas" :str2 "steak"}
      400 nil {:str1 "a"}
      400 nil {:str2 "b"}
      400 nil {})))
