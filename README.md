# djinn - Clojure-in-clojure Scripting.

The answer to using clojure within a natively compiled clojure
application.

## Example

```clojure

(require '[djinn.core :as djinn])


(djinn/eval
  (def x 10)
  
  (defn hello [x] (println "Hello " x "!") :done)
  
  (hello "djinn")) ;; => :done
  ;; <stdout>: Hello djinn!\n
  
```

