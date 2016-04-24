package common

@Grapes([
    @Grab('com.aestasit.infrastructure.sshoogr:sshoogr:0.9.25'),
    @Grab('commons-codec:commons-codec:1.10')
])
import com.aestasit.infrastructure.ssh.DefaultSsh

class Sshoogr extends DefaultSsh { }
