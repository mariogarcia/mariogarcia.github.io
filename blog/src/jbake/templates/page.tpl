def rootPath = content.rootpath ?: ''

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
                        }

                        yieldUnescaped(content.body)
                    }
                }
            }
        }
    }
}
