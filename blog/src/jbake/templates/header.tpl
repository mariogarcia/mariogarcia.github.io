/**
 * VARIABLES
 */
def rootPath = content.rootpath ?: ''
def cssLinks = content.css?.split('\\|') // <1>

/**
 * TEMPLATE
 */
head {
    link(href: "${rootPath}css/asciidoctor.css", rel:'stylesheet')
    link(href: "${rootPath}css/style.css", rel:'stylesheet')
    link(href: 'https://use.fontawesome.com/releases/v5.2.0/css/all.css', rel: 'stylesheet')
    link(href: 'https://fonts.googleapis.com/css?family=Fira+Mono:400,700', rel: 'stylesheet')
    link(href: 'https://cdn.rawgit.com/necolas/normalize.css/master/normalize.css', rel: 'stylesheet')

    cssLinks.each { uri ->
        link(href: (uri ==~ 'http.*' ? uri : "${rootPath}${uri}"), rel:'stylesheet') { }
    }

    title(content.title ?: config.site_title)
}
