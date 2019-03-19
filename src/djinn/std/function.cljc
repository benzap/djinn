(ns djinn.std.function
  (:require
   [djinn.std.evaluator :refer [evaluate]]
   [djinn.std.state-machine :as state]
   [djinn.std.scope :as scope]))


(defprotocol Invokable
  (invoke [this sm arguments]))


(defrecord Function [closure arg-list forms]
  Invokable
  (invoke [this sm arguments]
    (let [sm (-> sm
                 state/create-scope
                 (state/merge-scope closure) ;; Closure Scope
                 state/create-scope)         ;; Function Scope
          ;; TODO: Bind arguments to arg-list variables
          [sm result] (evaluate sm forms)]
      
      ;; Remove closure scope and function scope
      [(-> sm state/remove-scope state/remove-scope) result])))


(defn new [sm arg-list forms]
  (let [closure (state/get-closure-environment sm)]
    (->Function sm {:closure closure :arg-list arg-list} forms)))


(defn invokable? [x]
  (satisfies? Invokable x))
