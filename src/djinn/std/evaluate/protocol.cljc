(ns djinn.std.evaluate.protocol
  (:refer-clojure :exclude [macroexpand]))


(defprotocol Evaluate
  (evaluate [this sm])
  (macroexpand [this sm]))
