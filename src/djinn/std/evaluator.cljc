(ns djinn.std.evaluator
  (:require
   [djinn.std.ast] ;; Includes djinn.evaluate.protocol/Evaluate protocols
   [djinn.std.evaluate.protocol]
   [djinn.std.evaluate.impl.core]
   [djinn.std.ast :as std.ast]))


(defn evaluate-form
  [sm form]
  (djinn.std.evaluate.protocol/evaluate form sm))


(defn evaluate-ast
  [sm ast]
  (loop [sm sm forms ast result nil]
    (if-not (empty? forms)
      (let [[sm result] (evaluate-form sm (first forms))]
        (recur sm (rest forms) result))
      [sm result])))


(defn evaluate-arguments
  [sm arguments]
  (loop [sm sm arguments arguments return-arguments []]
    (if-not (empty? arguments)
      (let [[sm result] (evaluate-form sm (first arguments))]
        (recur sm (rest arguments) (conj return-arguments result)))
      [sm return-arguments])))


(defn evaluate
  [sm form-listing]
  (let [ast (std.ast/parse form-listing)]
    (evaluate-ast sm ast)))
