(ns djinn.std.ast.s-expression
  (:require
   [djinn.std.evaluate.protocol :refer [Evaluate evaluate]]
   [djinn.std.state-machine :as state]
   [djinn.std.scope :as scope]
   [djinn.std.function :as std.function]))


(defrecord SExpression [elements]
  Evaluate
  (evaluate [_ sm]
    (let [[fsym & arguments] elements
          fvar (state/get-var sm fsym)]
      (cond
        (scope/undefined? fvar)
        (throw (ex-info "First argument of s-exp is not defined" {:value fsym}))
        (std.function/invokable? fvar)
        (std.function/invoke fvar sm arguments)
        (ifn? fvar)
        [sm (apply fvar arguments)]
        :else
        (throw (ex-info "First argument of s-exp is not invokable." {:value fsym}))))))
         
