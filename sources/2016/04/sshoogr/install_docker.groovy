/**
 * 1.SSHOOGR CONFIG
 */
import static common.Sudo.*
import static common.Util.*
import static common.Sshoogr.*

options.trustUnknownHosts = true

/**
 * 2.SHARED VARIABLES
 */
evaluate('common/secrets.groovy' as File)

DOCKER_PRIVATE_KEY = new File(SUPERVISOR_SSH_PRI_KEY)
/**
 * 3.INSTALL DOCKER
 *
 * Installing docker engine in all machines
 */
remoteSession("$DOCKER_USERNAME@$IP") {
    keyFile = DOCKER_PRIVATE_KEY

    prefix(SUDO) {
        exec "pacman --noconfirm -S docker"
        exec "usermod -a -G docker $DOCKER_USERNAME"
        exec "systemctl enable docker"
        exec "systemctl start docker"
    }

    exec "history -c"
}
