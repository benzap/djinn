# clojinn - Clojure-in-clojure Scripting.

The answer to using clojure within a natively compiled clojure
application.

## Example

```clojure

(require '[clojinn.core :as clji])


(clji/eval
  (def x 10)
  
  (defn hello [x] (println "Hello " x "!"))
  
  (hello "clojinn")) ;; => nil
  ;; <stdout>: Hello clojinn!\n
  
```

