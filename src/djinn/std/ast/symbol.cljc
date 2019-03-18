(ns djinn.std.ast.symbol
  (:require
   [djinn.std.evaluate.protocol :refer [Evaluate evaluate]]
   [djinn.std.state-machine :as state]
   [djinn.std.scope :as scope]))


(defrecord SymbolExpression [symbol]
  Evaluate
  (evaluate [_ sm]
    (let [result (state/get-var sm symbol)]
      (if-not (= result ::scope/miss)
        [sm result]
        (throw (ex-info (str "Given var '" symbol "' is undefined" {})))))))
