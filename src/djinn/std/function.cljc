(ns djinn.std.function
  (:require
   [djinn.std.evaluator :refer [evaluate]]
   [djinn.std.state-machine :as state]
   [djinn.std.scope :as scope]))


(defprotocol Invokable
  (invoke [this sm arguments]))


(defrecord Function [sm env forms]
  Invokable
  (invoke [this sm arguments]))
    

(defn new-function [sm forms]
  (->Function sm (scope/new-scope) forms))


(defn invokable? [x]
  (satisfies? Invokable x))
