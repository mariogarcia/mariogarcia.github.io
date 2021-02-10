yieldUnescaped '<!DOCTYPE html>'
html(lang:'en'){
    head {
        include template: "header.tpl"
    }

    body(class:"is-preload") {
        div(id: "wrapper") {
            div(id: "main") {
                div(class: "inner") {
                    header(id: "header") {
                        a(href: "${config?.site_contextPath}index.html", class: "logo") {
                            strong {
                                yield "${config?.blog_title?.toUpperCase()}"
                            }
                            yield " - ${section ?: content?.title?.toUpperCase()}"
                        }
                        ul(class: "icons") {
                            li {
                                a(href: "${config?.site_contextPath}feed.xml", class: "icon fa-rss") {
                                    span(class: "label") {
                                        yieldUnescaped "Twitter"
                                    }
                                }
                            }
                            li {
                                a(href: "${config.social_twitter}", class: "icon fa-twitter") {
                                    span(class: "label") {
                                        yieldUnescaped "Twitter"
                                    }
                                }
                            }

                            li {
                                a(href: "${config.social_github}", class: "icon fa-github") {
                                    span(class: "label") {
                                        yieldUnescaped "Github"
                                    }
                                }
                            }
                        }
                    }

                    bodyContents()
                }
            }

            include template: "menu.tpl"
            include template: "footer.tpl"
        }
    }
}
newLine()
