;;; package --- Summary
;;; Commentary:
;;; Code:
(require 'seq)
(require 'ert)

;;; tag::incorrect[]
(defun sort-by-string-length (list &optional asc-desc)
  "Sort a LIST of strings by their length.
Apply direction by ASC-DESC value which could be 'asc' or 'desc'."
  (progn
    (fset 'direction
          (if (equal asc-desc "asc") '> '<))
    (sort list (lambda (a b)
                 (direction (length a) (length b))))))
;;; end::incorrect[]

;;; tag::incorrect_test[]
(ert-deftest test-sort-by-default () ;; <1>
  (should
   (equal (sort-by-string-length '("a" "aaa" "aa")) '("a" "aa" "aaa"))))

(ert-deftest test-sort-by-desc () ;; <2>
  (should (equal (sort-by-string-length '("a" "aaa" "aa") "desc") '("aaa" "aa" "a"))))
;;; end::incorrect_test[]

(provide 'elisp-ert-ko)

;;; elisp-ert-ko.el ends here
