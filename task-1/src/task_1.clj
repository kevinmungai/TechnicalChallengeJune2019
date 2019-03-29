(ns task-1
  (:require [clojure.java.io :as io]))

(defn open-file-and-check
  "this function opens the file with the given regex"
  [file-path regex]
  (when (.isFile file-path)
    (with-open [reader (io/reader file-path)]
      (->> reader
           line-seq
           str
           (re-find (re-pattern regex))))))

(defn list-files-with-a
  "list all the files with the letter a in the given folder"
  [folder-path]
  (let [files (file-seq (io/file folder-path))]
    (filter #(open-file-and-check % "[aA]") files)))

(defn -main
  [& args]
  (println "Welcome to the program\nPlease put in the folder path to find the letter \"a\"")
  (let [folder (read-line)]
    (list-files-with-a folder)))
