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

(defn day01b
  [input]
  (let [calc-additional-fuel
        (fn [input]
          (loop [required-fuel (calc-fuel input)
                 acc 0]
            (if (< required-fuel 0)
              acc
              (recur (calc-fuel required-fuel) (+ acc required-fuel)))))]
    (->> input
         (map calc-additional-fuel)
         (reduce + 0))))
