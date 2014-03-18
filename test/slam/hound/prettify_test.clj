(ns slam.hound.prettify-test
  (:require [clojure.test :refer [deftest is testing]]
            [slam.hound.prettify :refer [prettify]]))

(deftest ^:unit test-prettify
  (testing "always inserts newlines inside short requires"
    (is (= "(ns foo
  (:require [clojure.java.io :as io]
            [clojure.pprint :refer [pprint]]))\n"
           (prettify
             '(ns foo
                (:require [clojure.java.io :as io]
                          [clojure.pprint :refer [pprint]]))))))
  (testing "does not print :refer vectors in :miser mode"
    (is (= "(ns foo
  (:require [my.very.sequipedalian.namespace :refer [alpha beta gamma
                                                     delta epsilon]]))\n"
           (prettify
             '(ns foo
                (:require [my.very.sequipedalian.namespace
                           :refer [alpha beta gamma delta epsilon]])))))))
