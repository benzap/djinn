(ns djinn.std.ast.symbol
  (:require
   [djinn.std.evaluate.protocol :refer [Evaluate evaluate]]))


(defrecord SymbolExpression [*sm symbol]
  Evaluate
  (evaluate [_]
    (println symbol)))
