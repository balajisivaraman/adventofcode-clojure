(ns com.balajisivaraman.aoc.year2019)

(defn- calc-fuel
  [input]
  (-> input
      (/ 3)
      (Math/floor)
      (- 2)))

(defn day01a
  [input]
  (->> input
       (map calc-fuel)
       (reduce + 0)))

(defn- calc-additional-fuel
  ([input] (calc-additional-fuel input 0))
  ([input acc]
   (let [required-fuel (calc-fuel input)]
     (if (< required-fuel 0)
      acc
      (calc-additional-fuel required-fuel (+ acc required-fuel))))))

(defn day01b
  [input]
  (->> input
       (map calc-additional-fuel)
       (reduce + 0)))
