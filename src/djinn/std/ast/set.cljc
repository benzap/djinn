(ns djinn.std.ast.set
  (:require
   [djinn.std.evaluate.protocol :refer [Evaluate evaluate]]))


(defrecord MapSetExpression [*sm elements]
  Evaluate
  (evaluate [_]
    (into #{} (map evaluate elements))))
