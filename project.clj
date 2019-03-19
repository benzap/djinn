(defproject djinn "0.1.0-SNAPSHOT"
  :description "Sandboxed subset of clojure in clojure(script) for scripting"
  :url "http://github.com/benzap/djinn"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/tools.reader "1.3.2"]]

  :plugins [[cider/cider-nrepl "0.21.1"]
            [lein-cljsbuild "1.1.7"]
            [lein-ancient "0.6.15"]
            [lein-doo "0.1.10"]]

  :repositories [["clojars" {:sign-releases false}]]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :cljsbuild {:builds {:dev
                       {:source-paths ["src"]
                        :compiler {:output-dir "resources/public/js/compiled/out"
                                   :output-to "resources/public/js/compiled/djinn.js"
                                   :optimizations :whitespace
                                   :pretty-print true
                                   :source-map "resources/public/js/compiled/djinn.js.map"}}
                       :prod
                       {:source-paths ["src"]
                        :compiler {:output-to "resources/public/js/compiled/djinn.min.js"
                                   :optimizations :advanced
                                   :pretty-print false}}
                       :test
                       {:id "test"
                        :source-paths ["src" "test"]
                        :compiler {:output-to "resources/public/js/compiled/test/test-runner.js"
                                   :output-dir "resources/public/js/compiled/test/out"
                                   :main djinn.test-runner
                                   :target :nodejs
                                   :optimizations :none}}}}

  :doo {:build "test"
        :alias {:default [:node]}}

  :aliases {"project-version" ["run" "-m" "djinn.utils.version/print-project-version"]}

  :profiles
  {:dev
   {:main djinn.commandline
    :source-paths ["src" "dev" "test"]
    :dependencies [[org.clojure/tools.namespace "0.2.11"]]
    :repl-options {:init-ns djinn.dev.user
                   :port 9006}}
   
   :uberjar
   {:jvm-opts ["-Dclojure.compiler.direct-linking=true"]
    :main djinn.commandline
    :aot [djinn.core djinn.commandline]}})
