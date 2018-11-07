html {
    includeGroovy 'header.tpl'
    body {
        main(class: 'wrapper') {
            includeGroovy 'navigation.tpl'

            div(class:"content") {
                posts.groupBy({ v -> v.date.format('yyyy') }).each { group ->
                    section(class: "container list") {
                        h1(group.key)
                        ul {
                            group.value.each { post ->
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
}
