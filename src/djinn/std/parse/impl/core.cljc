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
  (parse [this]
    (djinn.std.ast.s-expression/->SExpression
     (map djinn.std.parse.protocol/parse this)))

  clojure.lang.PersistentArrayMap
  (parse [this]
    (djinn.std.ast.map/->MapExpression
     (map (fn [[k v]] [(djinn.std.parse.protocol/parse k)
                       (djinn.std.parse.protocol/parse v)])
          this)))
    
  clojure.lang.PersistentVector
  (parse [this]
    (djinn.std.ast.vector/->VectorExpression
     (map djinn.std.parse.protocol/parse this)))
    
  clojure.lang.PersistentHashSet
  (parse [this]
    (djinn.std.ast.set/->MapSetExpression
     (map djinn.std.parse.protocol/parse this)))

  ;;
  ;; Base Clojure Data Types
  ;;

  clojure.lang.Keyword
  (parse [this] this)

  clojure.lang.Ratio
  (parse [this] this)

  clojure.lang.Symbol
  (parse [this] this)

  ;;
  ;; Base Java Data Types
  ;;

  java.lang.String
  (parse [this] this)

  java.lang.Long
  (parse [this] this)

  java.lang.Double
  (parse [this] this)

  java.lang.Boolean
  (parse [this] this)

  java.util.regex.Pattern
  (parse [this] this)

  ;;
  ;; Default
  ;; 
  java.lang.Object
  (parse [this]
    (println (str "Warning: Unknown Parsed Object [" (type this) "]"))
    this))
