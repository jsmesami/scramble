(ns scramble.core)


(defn scramble?
  "Return true if a portion of str1 characters can be rearranged to match str2."
  [str1 str2]
  {:pre [(seqable? str1) (seqable? str2)]}
  (clojure.set/subset? (set str2) (set str1)))
