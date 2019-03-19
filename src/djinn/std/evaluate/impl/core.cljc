(ns djinn.std.evaluate.impl.core
  "Implementation of Evaluate protocol for clojure's base types"
  (:require
   [djinn.std.evaluate.protocol :refer [Evaluate evaluate]]
   [djinn.std.evaluator :refer [evaluate-arguments]]
   [djinn.std.state-machine :as state]
   [djinn.std.scope :as scope]
   [djinn.std.fn :as std.fn]))


(defn evaluate-list [this sm]
  (let [[fsym & arguments] this
        [sm fvar] (evaluate fsym sm)]
    (cond
      (std.fn/invokable? fvar)
      (std.fn/invoke fvar sm arguments)

      (or (ifn? fvar) (fn? fvar))
      (let [[sm arguments] (evaluate-arguments sm arguments)]
        [sm (apply fvar arguments)])

      :else
      (throw (ex-info "First argument of s-exp is not invokable." {:var fvar :sym fsym})))))


(defn evaluate-map [this sm]
  (let [*sm (atom sm)] ;; TODO: switch to loop
    (->> this
         (map (fn [[k v]] 
                (let [[sm k] (evaluate k @*sm)
                      [sm v] (evaluate v sm)]
                  (reset! *sm sm)
                  [k v])))
         (into {})
         (conj [@*sm]))))


(defn evaluate-vector [this sm]
  (let [*sm (atom sm) ;; TODO: switch to loop
        this
        (mapv (fn [elem]
                (let [[sm value] (evaluate elem @*sm)]
                  (reset! *sm sm)
                  value)) this)]
    [@*sm this]))


(defn evaluate-set [this sm]
  (let [*sm (atom sm) ;; TODO: switch to loop
        this
        (map (fn [elem]
               (let [[sm value] (evaluate elem @*sm)]
                 (reset! *sm sm)
                 value)) this)]
    [@*sm (set this)]))


(defn evaluate-symbol [this sm]
  (let [val (state/get-var sm this)]
    (if-not (scope/undefined? val)
      [sm val]
      (throw (ex-info (str "Unable to resolve symbol: " this " in this context") {:symbol this})))))


(extend-protocol djinn.std.evaluate.protocol/Evaluate

  ;; Use Default for everything
  ;; Notes: protocols appear to be broken in cljs
  ;; FIXME: extend base clojure types in cljs such that it works
  #?(:clj java.lang.Object :cljs default)
  (evaluate [this sm]
    (cond
      (list? this) (evaluate-list this sm)
      (seq? this) (evaluate-list this sm)
      (map? this) (evaluate-map this sm)
      (vector? this) (evaluate-map this sm)
      (set? this) (evaluate-set this sm)
      (symbol? this) (evaluate-symbol this sm)
      :else [sm this])))
