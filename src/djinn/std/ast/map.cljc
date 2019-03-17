(ns djinn.std.ast.map
  (:require
   [djinn.std.evaluate.protocol :refer [Evaluate evaluate]]))


(defrecord MapExpression [*sm m]
  Evaluate
  (evaluate [_]
    (println m)))
