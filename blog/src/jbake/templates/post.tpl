def rootPath = content.rootpath ?: ''
def tags(post) {
    ul(class: 'tags') {
        post.tags.each { tag ->
            li(class: 'kc') {
                a(href: "${post.rootpath ?: ''}tags/${tag}.html", "$tag")
            }
        }
    }
}

html {
    includeGroovy 'header.tpl'
    body {
        main(class: 'wrapper') {
            includeGroovy 'navigation.tpl'
            div(class:"content") {
                section(class: "container post") {
                    article {
                        header {
                            h1(content.title)
                            h2(content.date?.format('dd MMMM yyyy'))
                            tags(content)
                        }

                        yieldUnescaped(content.body)
                    }
                }
            }
        }
    }
}
