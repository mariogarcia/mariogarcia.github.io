package common

import groovy.util.logging.Log

@Log
class Util {

    /**
     * Generates a temporal key pair with a given name.
     *
     * @param keyFileName the path of the private key, including the
     * private key file name
     * @return the directory where the
     */
    static File generateKeyPair(String keyFileName) {
        log.info("checking key file directory")
        File keyFileDirectory = new File(keyFileName).parentFile
        keyFileDirectory.mkdirs()

        log.info("building ssh-keygen executable")
        String shFileContent  = "ssh-keygen -t rsa -f $keyFileName -N ''"
        File shTemporalFile   = File.createTempFile("sshoogr",".sh")

        shTemporalFile.text = shFileContent
        shTemporalFile.executable = true

        log.info("generating key pair at ${keyFileName}...")
        def process = shTemporalFile.absolutePath.execute()
        def result  = process.waitFor()
        log.info("generating key pair returned: $result")

        log.info("returning where keys are located")
        return keyFileDirectory
    }
}
