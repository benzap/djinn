(ns djinn.std.ast.s-expression
  (:require
   [djinn.std.evaluate.protocol :refer [Evaluate evaluate]]))


(defrecord SExpression [*sm elements]
  Evaluate
  (evaluate [_]
    (println elements)))