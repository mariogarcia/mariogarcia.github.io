/**
 * 1.SSHOOGR CONFIG
 */
import static common.Sudo.*
import static common.Util.*
import static common.Sshoogr.*

// <1>
options.trustUnknownHosts = true

/**
 * 2.SHARED VARIABLES
 */
evaluate('common/secrets.groovy' as File)

/**
 * 3.OWN VARIABLES
 */
PREFIX = "echo $RPI_ROOT_PASSWORD | su -c '"
SUFFIX = "'"

/**
 * 4.CHANGE DEFAULT CREDENTIALS
 *
 * This step is done without online connection to avoid
 * any attack at this point due the fact that machines have
 * default usernames and passwords.
 */
remoteSession("$DEF_RPI_USER_TUPLE@$IP"){
    prefix("echo $DEF_RPI_ROOT_PASSWORD | su -c '") {
        suffix(SUFFIX){
            exec "echo \"$RPI_ROOT_TUPLE\" | chpasswd -m"
        }
    }

    prefix(PREFIX) {
        suffix(SUFFIX) {
            exec "pacman --noconfirm -S sudo"
        }
    }

    prefix(PREFIX) {
        suffix(SUFFIX) {
            exec "useradd -m $DOCKER_USERNAME"
            exec "echo \"$DOCKER_TUPLE\" | chpasswd -m"
            exec "echo \"${noPasswd(DOCKER_USERNAME)}\" >> /etc/sudoers"
        }
    }
}

/**
 * 3.INSTALL SSH KEY
 *
 * Once machines have been initialized, they should be accessed via
 * ssh key instead of using username and password. In order to do that
 * we need to install an authorized key in every one of them.
 */

AUTHORIZED_KEYS_FILE = "/home/$DOCKER_USERNAME/.ssh/authorized_keys"
DOCKER_PUBLIC_KEY = new File(SUPERVISOR_SSH_PUB_KEY)
DOCKER_PRIVATE_KEY = new File(SUPERVISOR_SSH_PRI_KEY)
DOCKER_CREDENTIALS = [keyFile: DOCKER_PRIVATE_KEY]

remoteSession("$DOCKER_TUPLE@$IP") {
    exec "mkdir -p ~/.ssh"
    exec "touch $AUTHORIZED_KEYS_FILE"

    remoteFile(AUTHORIZED_KEYS_FILE).text = DOCKER_PUBLIC_KEY.text
}

/**
 * 5.DELETE DEFAULT USER
 *
 */
remoteSession("$DOCKER_USERNAME@$IP") {
    keyFile = DOCKER_PRIVATE_KEY

    exec "history -c"

    prefix(SUDO) {
        exec "userdel -fr $DEF_RPI_USER_USERNAME"
        exec "pacman --noconfirm -Syu"  // <1>
        exec "reboot now"
    }
}
