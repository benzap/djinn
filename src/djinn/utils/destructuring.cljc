(ns djinn.std.destructuring)


(defn destructure-parameters
  "Destructures into a let-binding vector"
  [parameters arguments]
  (loop [params parameters 
         args arguments
         declet []]
    (cond
      (or (and (empty? params) (not-empty args))
          (and (not-empty params) (empty? args)))
      (throw (ex-info (str "Wrong number of args (" (count arguments) ") passed to function.")
                      {:parameters parameters
                       :arguments arguments}))

      (and (empty? params) (empty? args))
      declet

      (symbol? (first params))
      (recur (rest params) 
             (rest args)
             (conj declet (first params) (first args))))))


;;(destructure-parameters '[a b c] '[1 2 3])


(defn destructure-let-binding [declet]
  (let [partitioned (partition 2 declet)]
        
    (loop [partitioned partitioned modified-declet []]
      (let [[var value] (first partitioned)]
        (cond
          (empty? partitioned)
          modified-declet
  
          (symbol? var)
          (recur (rest partitioned) (conj modified-declet var value)))))))

        
