(ns djinn.stdlib.fn
  (:require
   [djinn.std.fn]
   [djinn.std.state-machine :as state]))


(defrecord Fnn [])


(extend-protocol djinn.std.fn/IFn
  Fnn
  (invoke [this sm arguments]
    (let [[arg-list & forms] arguments]
      [sm (djinn.std.fn/new sm arg-list forms)])))


(defn import-stdlib-fn [sm]
  (state/set-global-var sm 'fn (->Fnn)))
