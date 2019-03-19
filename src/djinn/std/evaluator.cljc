(ns djinn.std.evaluator
  (:require
   [djinn.std.evaluate.protocol]
   [djinn.std.evaluate.impl.core]))


(defn evaluate-form
  [sm form]
  (djinn.std.evaluate.protocol/evaluate form sm))


(defn evaluate-forms
  [sm forms]
  (loop [sm sm forms forms result nil]
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
  (evaluate-forms sm form-listing))
