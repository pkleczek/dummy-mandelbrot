(ns mandelbrot.core)

(defrecord Complex [real imag])

(def limit 100)
(def size {:x 800 :y 600})
(def space {:min (Complex. -2 -1) :max (Complex. 1 1) })

(defn module
  [{:keys [real imag]}]
  (Math/sqrt (+ (* real real) (* imag imag))))

(defn add [{r1 :real i1 :imag} {r2 :real i2 :imag}]
  (Complex. (+ r1 r2) (+ i1 i2)))

(defn pixel-to-point [x y]
  (let [min-x (get-in space [:min :real])
        max-y (get-in space [:max :imag])
        width (+ (Math/abs min-x) (-> space :max :real Math/abs))
        height (+ (-> space :min :imag Math/abs) (Math/abs max-y))
        x-pos (+ min-x (* x (/ width (:x size))))
        y-pos (- max-y (* y (/ height (:y size))))]
        (Complex. x-pos y-pos)))

(defn pow [{a :real b :imag}]
  (Complex. (- (* a a) (* b b)) (* 2 a b)))


(defn is-in-set [point]
  (loop [z (Complex. 0 0) iter 0]
    (if (< (module z) 2)
      (if (< iter limit)
        (recur (add (pow z) point) (inc iter))
        true)
      false)))
