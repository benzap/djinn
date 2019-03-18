(ns djinn.std.ast.vector
  (:require
   [djinn.std.evaluate.protocol :refer [Evaluate evaluate]]))


(defrecord VectorExpression [elements]
  Evaluate
  (evaluate [_ sm]
    (let [*sm (atom sm)
          elements 
          (mapv (fn [elem]
                  (let [[sm value] (evaluate elem @*sm)]
                    (reset! *sm sm)
                    value)) elements)]
      [@*sm elements])))
