(ns roman-numerals
  (:require
   [clojure.string :as string]))

(def numerals
  {"I"    1
   "IV"   4
   "V"    5
   "X"    10
   "XL"   40
   "L"    50
   "C"    100
   "CD"   400
   "D"    500
   "M"    1000})

(def sorted-numerals-decending (reverse (sort-by last (seq numerals))))

(defn find-next-numeral [roman-string]
  (some (fn [[string-numeral :as numeral]]
          (when (string/starts-with? roman-string string-numeral) numeral))
        sorted-numerals-decending))

(defn numeral-counter [roman-string]
  (let [[string-numeral int-val] (find-next-numeral roman-string)]

    (if string-numeral
      (+ (* int-val
            (count (re-seq (re-pattern string-numeral) roman-string)))
         (numeral-counter (string/replace roman-string (re-pattern string-numeral) "")))
      0)))

(numeral-counter "")

