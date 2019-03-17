(ns djinn.std.state-machine
  (:require
   [djinn.std.scope :as scope]))


(defrecord StateMachine
  [scope])
   

(defn new-state-machine []
  {:scope (scope/new-scope)})
