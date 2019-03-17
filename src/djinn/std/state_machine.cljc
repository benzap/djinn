(ns djinn.std.state-machine
  (:require
   [djinn.std.environment :as environment]))


(defrecord StateMachine
  [environments])
   

(defn new-state-machine []
  {:environments [(environment/new-environment)]})
