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

(deftest pixel-to-point-resolution
  (testing "Start of pane should resolve to the beginnig of the plane"
    (are [x y expected] (= (pixel-to-point x y) expected)
      0 0 (->Complex -2 1)
      800 600 (->Complex 1 -1))))

(deftest adding-complex-numbers
  (testing "Corresponding parts of complex number should be added"
    (is (= (add (->Complex 1 12) (->Complex -5 9)) (->Complex -4 21)))))

(deftest is-in-set-gives-correct-result
  (testing "Calculate if crucial points are in Mandelbrot set"
    (are [a b expected] (= (is-in-set (->Complex a b)) expected)
      0 0 true
      0 1 true
      1 0 false
      -2 0 false)))
