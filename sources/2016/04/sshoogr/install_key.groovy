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

AUTHORIZED_KEYS_FILE = "/home/$DOCKER_USERNAME/.ssh/authorized_keys"
DOCKER_PUBLIC_KEY = new File(SUPERVISOR_SSH_PUB_KEY)
DOCKER_PRIVATE_KEY = new File(SUPERVISOR_SSH_PRI_KEY)
DOCKER_CREDENTIALS = [keyFile: DOCKER_PRIVATE_KEY]

remoteSession("$DOCKER_TUPLE@$IP") {
    exec "mkdir -p ~/.ssh"
    exec "touch $AUTHORIZED_KEYS_FILE"

    remoteFile(AUTHORIZED_KEYS_FILE).text = DOCKER_PUBLIC_KEY.text
}
