(ns scramble.form
  (:require
    [ajax.core :refer [GET]]
    [clojure.string :refer [blank?]]
    [reagent.core :as reagent]))


(defn input
  [id label action {:keys [form field-errors]}]
  [:div.form-control
   [:label
    (str label ":")
    [:input {:type      "text"
             :id        id
             :value     (get form id)
             :on-change #(action id (-> % .-target .-value))}]]
   [:small.error (get field-errors id)]])


(defn submit-button
  [label action {form :form}]
  [:div.form-control
   [:button {:type     "submit"
             :disabled (->> (vals form) (some blank?))
             :on-click #(do (.preventDefault %)
                            (action form))}
    label]])


(defn result-message
  [{result :result}]
  (case result
    true [:h2.success "It's a scramble!"]
    false [:h2 "It's not a scramble :("]
    nil))


(defn error-message
  [{message :error}]
  (when message
    [:h3.error message]))


(defn make-update
  [state validator]
  (fn [id value]
    (let [error (validator value)]
      (if error
        (swap! state assoc-in [:field-errors id] error)
        (do (swap! state dissoc :result :field-errors :error)
            (swap! state assoc-in [:form id] value))))))


(defn make-submit
  [state]
  (fn [form]
    (GET "/api/scramble"
         {:params form
          :handler #(swap! state merge %)
          :error-handler #(swap! state assoc :error (:status-text %))})))


(defn form []
  (let [initial {:form {:str1 "" :str2 ""}}
        state (reagent/atom initial)
        validator #(when-not (re-matches #"[a-z]*" %) "Only lowercase letters a-z are valid.")
        update! (make-update state validator)
        submit! (make-submit state)]
    (fn []
      [:form.scramble-form
       [input :str1 "String 1" update! @state]
       [input :str2 "String 2" update! @state]
       [submit-button "Ask" submit! @state]
       [result-message @state]
       [error-message @state]])))
