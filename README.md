# djinn - Subset of clojure as a scripting language

The answer to using a subset of clojure within a natively compiled
clojure application.

*djinn is early alpha, and is not fully functional*

## Example

```clojure

(require '[djinn.core :as djinn])


(djinn/eval
  (def x 10)
  
  (defn hello [x] (println "Hello " x "!") :done)
  
  (hello "djinn")) ;; => :done
  ;; <stdout>: Hello djinn!\n
  
```

