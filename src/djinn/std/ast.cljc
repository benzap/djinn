(ns djinn.std.ast
  "Breaks down an EDN data structure"
  (:require
   [djinn.std.parse.protocol :as parse.protocol]
   [djinn.std.parse.protocol.impl.core]))


(defn parse
  [sm forms]
  (let [*sm (atom sm)]
    (for [form forms]
      (parse.protocol/parse form *sm))))


;; (parse {} ['(1 2 3) 1 2 [1 2]])
