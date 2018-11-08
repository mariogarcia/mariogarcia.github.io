def byYear = { v ->
    v.date.format('yyyy')
}

def onlyPublished = { post ->
    post.status == 'published'
}

def createEntry = { post ->
    li {
        span(post.date?.format("MMM dd"))
        a(href: "${content.rootpath}${post.uri}", post.title)
    }
}

html {
    includeGroovy 'header.tpl'
    body {
        main(class: 'wrapper') {
            includeGroovy 'navigation.tpl'
            div(class:"content") {
                posts.groupBy(byYear).each { group ->
                    section(class: "container list") {
                        h1(group.key)
                        ul {
                            group.value.findAll(onlyPublished).each(createEntry)
                        }
                    }
                }
            }
        }
    }
}
