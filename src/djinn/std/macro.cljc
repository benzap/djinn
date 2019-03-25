(ns djinn.std.macro
  (:refer-clojure :exclude [macroexpand])
  (:require
   [djinn.std.fn]
   [djinn.std.evaluate.protocol :refer [Evaluate macroexpand]]))


(defrecord Macro [closure arg-list forms]
  djinn.std.fn/IFn
  (invoke [this sm arguments]))


(defn macro? [x]
  (instance? Macro x))
