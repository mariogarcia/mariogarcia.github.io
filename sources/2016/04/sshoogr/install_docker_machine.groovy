/**
 * 1.SSHOOGR CONFIG
 */
// tag::staticimport[]
import static common.Sudo.*
import static common.Util.*
import static common.Sshoogr.*
// end::staticimport[]

options.trustUnknownHosts = true

/**
 * 2.SHARED VARIABLES
 */
// tag::evaluate[]
evaluate('common/secrets.groovy' as File)
// end::evaluate[]

/**
 * 3.INSTALL DOCKER-MACHINE ON MASTER
 *
 * Installing docker machine in master
 */
remoteSession("$DOCKER_USERNAME@$IP") {
    keyFile = new File(SUPERVISOR_SSH_PRI_KEY)

    prefix(SUDO) {
        exec "pacman --noconfirm -S docker-machine"
    }

    exec "history -c"
}
