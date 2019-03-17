(ns djinn.std.evaluator
  (:require
   [djinn.std.ast] ;; Includes djinn.evaluate.protocol/Evaluate protocols
   [djinn.std.evaluate.protocol]
   [djinn.std.evaluate.impl.core]))


(defn evaluate-form
  [ast]
  (djinn.std.evaluate.protocol/evaluate ast))


(defn evaluate
  [{:keys [statements] :as ast}]
  (for [form statements]
    (evaluate-form form)))

