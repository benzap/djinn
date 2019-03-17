(ns djinn.std.parse.protocol.impl.core
  "Implementation of Parser protocol for clojure's core data types."
  (:require
   [djinn.std.parse.protocol]
   [djinn.std.ast.s-expression]
   [djinn.std.ast.map]
   [djinn.std.ast.vector]
   [djinn.std.ast.set]
   [djinn.std.ast.symbol]))


(extend-protocol djinn.std.parse.protocol/Parse
  ;;
  ;; Base Clojure Data Collections
  ;;

  clojure.lang.PersistentList
  (parse [this *sm]
    (djinn.std.ast.s-expression/->SExpression
     *sm
     (map #(djinn.std.parse.protocol/parse % *sm) this)))

  clojure.lang.PersistentArrayMap
  (parse [this *sm]
    (djinn.std.ast.map/->MapExpression
     *sm
     (map (fn [[k v]] [(djinn.std.parse.protocol/parse k *sm)
                       (djinn.std.parse.protocol/parse v *sm)]))))
    
  clojure.lang.PersistentVector
  (parse [this *sm]
    (djinn.std.ast.vector/->VectorExpression
     *sm
     (map #(djinn.std.parse.protocol/parse % *sm) this)))
    
  clojure.lang.PersistentHashSet
  (parse [this *sm]
    (djinn.std.ast.set/->MapSetExpression
     *sm
     (map #(djinn.std.parse.protocol/parse % *sm) this)))

  ;;
  ;; Base Clojure Data Types
  ;;

  clojure.lang.Keyword
  (parse [this *sm] this)

  clojure.lang.Ratio
  (parse [this *sm] this)

  clojure.lang.Symbol
  (parse [this *sm]
    (djinn.std.ast.symbol/->SymbolExpression *sm this))

  ;;
  ;; Base Java Data Types
  ;;

  java.lang.String
  (parse [this *sm] this)

  java.lang.Long
  (parse [this *sm] this)

  java.lang.Double
  (parse [this *sm] this)

  java.lang.Boolean
  (parse [this *sm] this)

  java.util.regex.Pattern
  (parse [this *sm] this)

  ;;
  ;; Default
  ;; 
  java.lang.Object
  (parse [this *sm]
    (println (str "Warning: Unknown Parsed Object [" (type this) "]"))
    this))
