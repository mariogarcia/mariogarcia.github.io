def contextPath = "${config.site_contextPath}"

div(id: "sidebar") {
    div(class: "inner") {

//        comment "Search"
//        section(id: "search", class: "alt") {
//            form(method: "post", action: "#") {
//                input(type: "text", name: "query", id: "query", placeholder: "Search") {}
//            }
//        }

        comment "Menu"
        nav(id: "menu") {
            header(class: "major") {
                h2 "Menu"
            }
            ul {
                li {
                    a(href: "${contextPath}index.html", "Latests entries")
                }
                li {
                    a(href: "${contextPath}archive.html", "Archive")
                }
//                li {
//                    a(href: "${contextPath}stats.html", "Statistics")
//                }
//                li {
//                    span(class: "opener", "Awesome Links")
//                    ul {
//                        li {
//                            a(href: "${contextPath}links/groovy.html", "Groovy")
//                        }
//                    }
//
//                }
                li {
                    a(href: "${contextPath}about.html", "About")
                }
            }
        }
    }
}
