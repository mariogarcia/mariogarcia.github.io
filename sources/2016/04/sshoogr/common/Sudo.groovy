package common

class Sudo {
    static String noPasswd(final String username) {
        return "$username ALL=(ALL) NOPASSWD: ALL"
    }
}
