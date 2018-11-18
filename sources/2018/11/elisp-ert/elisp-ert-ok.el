;;; package --- Summary
;;; Commentary:
;;; Code:
(require 'seq)
(require 'ert)

;;; tag::correct[]
(defun sort-by-string-length (list &optional asc-desc)
  "Sort a LIST of strings by their length.
Apply direction by ASC-DESC value which could be 'asc' or 'desc'."
  (progn
    (fset 'direction
          (if (equal asc-desc "desc")
              '>
            '<))
    (sort list (lambda (a b)
                 (direction (length a) (length b))))))

;;; end::correct[]

;;; tag::correct_test[]
(ert-deftest test-sort-by-default () ;; <1>
  (should (equal (sort-by-string-length '("a" "aaa" "aa")) '("a" "aa" "aaa"))))

(ert-deftest test-sort-by-desc () ;; <2>
  (should (equal (sort-by-string-length '("a" "aaa" "aa") "desc") '("aaa" "aa" "a"))))
;;; end::correct_test[]

(provide 'elisp-ert-ok)

;;; elisp-ert-ok.el ends here
