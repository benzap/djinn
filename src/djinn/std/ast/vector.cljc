(ns djinn.std.ast.vector
  (:require
   [djinn.std.evaluate.protocol :refer [Evaluate evaluate]]))


(defrecord VectorExpression [*sm elements]
  Evaluate
  (evaluate [_]
    (mapv evaluate elements)))
