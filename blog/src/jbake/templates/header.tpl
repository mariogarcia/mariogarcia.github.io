/**
 * VARIABLES
 */
def cssLinks = content?.css?.split('\\|') // <1>

/**
- * TEMPLATE
- */

meta(charset:"utf-8") newLine()
meta(name:"viewport", content:"width=device-width, initial-scale=1.0 user-scalable=no") newLine()
title("${config.blog_title}") newLine()
link(rel:"stylesheet", href:"${config.site_contextPath}css/main.css") newLine()
link(rel:"stylesheet", href:"${config.site_contextPath}css/zenburn.css") newLine()

cssLinks.each { uri ->
    link(href: (uri ==~ 'http.*' ? uri : "${config.contextPath}${uri}"), rel:'stylesheet') { }
}
newLine()
