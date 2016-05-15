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
 * 4.INSTALL FIREWALL
 */
remoteSession("$DOCKER_USERNAME@$IP") {
    keyFile = DOCKER_PRIVATE_KEY

    prefix(SUDO) {
        exec "pacman --noconfirm -S ufw"  // <1>
        exec "ufw allow ssh"              // <2>
        exec "systemctl enable ufw"       // <3>
        exec "systemctl start ufw"        // <4>
    }
}
