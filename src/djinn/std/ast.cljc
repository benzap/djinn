(ns djinn.std.ast
  "Breaks down an djinn code structure"
  (:require
   [djinn.std.parse.protocol :as parse.protocol]
   [djinn.std.parse.protocol.impl.core]))


(defn parse
  [sm forms]
  (let [*sm (atom sm)]
    {:statements
     (for [form forms]
       (parse.protocol/parse form *sm))
     :*sm *sm}))


;; (parse {} ['(1 2 3) 1 2 [1 2]])
;; (parse {} '[(a b c) 1 2 {:a a :b b}])
;; (parse {} '[#{a b c}])
;; (parse {} '[true 1.2 1/2 "test" :test a #"test" #{a b c d}])
