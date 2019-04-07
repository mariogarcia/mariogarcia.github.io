ul(class: "tags") {
    def contextPath = "${config.site_contextPath}"
    post.tags.each { tag ->
        li {
            a(href: "${contextPath}tags/${tag.replace(' ','-')}.html", class: "button primary small tag ${tag.trim()}") {
                yieldEscaped tag
            }
        }
    }
}
