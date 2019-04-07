

script(src:"${config.site_contextPath}js/jquery.min.js"){} newLine()
script(src:"${config.site_contextPath}js/browser.min.js"){} newLine()
script(src:"${config.site_contextPath}js/breakpoints.min.js"){} newLine()
script(src:"${config.site_contextPath}js/util.js"){} newLine()
script(src:"${config.site_contextPath}js/main.js"){} newLine()
script(src:"${config.site_contextPath}js/highlight.pack.js"){} newLine()
script(type: "text/javascript") {
    yieldUnescaped """
        document.addEventListener('DOMContentLoaded', (event) => {
            document.querySelectorAll('pre code').forEach((block) => {
                hljs.highlightBlock(block);
            });
        });
    """
}

content.scripts?.split('\\|')?.each { uri -> // <1>
    script(src: uri ==~ 'http.*'
        ? uri
        : "${config.site_contextPath}${uri}", type="javascript") {}
}
