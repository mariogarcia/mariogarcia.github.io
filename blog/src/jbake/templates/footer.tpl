/**
 * VARIABLES
 */
def rootPath = content.rootpath ?: ''
def jsScripts = content.scripts?.split('\\|') // <1>

jsScripts.each { uri -> // <2>
    script(src: uri ==~ 'http.*' ? uri : "${rootPath}${uri}", type="javascript") { }
}
