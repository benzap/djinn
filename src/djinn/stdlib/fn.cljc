(ns djinn.stdlib.fn
  (:require
   [djinn.std.function :as function]
   [djinn.std.state-machine :as state]))


(defrecord Fn []
  function/Invokable
  (invoke [this sm arguments]
    (let [[arg-list & forms] arguments]
      [sm (function/new sm arg-list forms)])))


(defn import-stdlib-fn [sm]
  (state/set-global-var sm 'fn (->Fn)))
