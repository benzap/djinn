(ns djinn.std.ast.set
  (:require
   [djinn.std.evaluate.protocol :refer [Evaluate evaluate]]))


(defrecord MapSetExpression [elements]
  Evaluate
  (evaluate [_ sm]
    (let [*sm (atom sm)
          elements
          (map (fn [elem]
                 (let [[sm value] (evaluate elem @*sm)]
                   (reset! *sm sm)
                   value)) elements)]
      [@*sm (set elements)])))
