(ns djinn.std.state-machine
  (:require
   [djinn.std.scope :as scope]))


(defrecord StateMachine
  [scope])
   

(defn new-state-machine []
  {:scope (scope/new-scope)})


(defn set-global-var [sm name value]
  (update-in sm [:scope] scope/set-global-var name value))


(defn set-var [sm name value]
  (update-in sm [:scope] scope/set-var name value))


(defn get-var [sm name]
  (-> sm :scope (scope/get-var name)))


(defn create-scope [sm]
  (update-in sm [:scope] scope/new-scope))


(defn remove-scope [sm]
  (update-in sm [:scope] scope/remove-scope))


#_(-> (new-state-machine)
      (set-var 'a 123))
