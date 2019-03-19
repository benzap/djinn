(ns djinn.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests doo-all-tests]]
   [djinn.core-test]))


(doo-tests
 'djinn.core-test)
