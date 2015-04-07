(ns mandelbrot.core)

(def limit 100)

(defrecord Complex [real imag])

(defn module
  [{:keys [real imag]}]
  (Math/sqrt (+ (* real real) (* imag imag))))
