def rootPath = content.rootpath ?: ''

html {
    includeGroovy 'header.tpl'
    body {
        main(class: 'wrapper') {
            includeGroovy 'navigation.tpl'
            div(class:"content") {
                section(class: "container list") {
                    h1("Tag: $tag")
                    ul {
                        tag_posts.each { post ->
                            li {
                                span(post.date?.format("MMM dd"))
                                a(href: "${content.rootpath}${post.uri}", post.title)
                            }
                        }
                    }
                }
            }
        }
    }
}
