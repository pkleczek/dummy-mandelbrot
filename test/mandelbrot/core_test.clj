(ns mandelbrot.core-test
  (:require [clojure.test :refer :all]
            [mandelbrot.core :refer :all]))

(deftest module-of-complex-number-are
  (testing "Test module calculation"
    (are [a b expected] (= (module (->Complex a b)) expected)
      1 0 1.0
      0 1 1.0
      0 0 0.0
      (Math/sqrt 2) (Math/sqrt 14) 4.0)))
