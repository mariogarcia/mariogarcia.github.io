model.put("section", "ARCHIVE")

layout 'layout/main.tpl', true,
projects: projects,
bodyContents: contents {
    section {
        def last_month=null
        published_posts.each {post ->
            if (last_month) {
                if (post.date.format("MMMM yyyy") != last_month) {
                    yieldUnescaped "</ul>"
                    h4("${post.date.format("MMMM yyyy")}")
                    yieldUnescaped "<ul class='group'>"
                }
            }
            else {
                h4("${post.date.format("MMMM yyyy")}")
                yieldUnescaped "<ul class='group'>"
            }

            li{
                yield "${post.date.format("dd")} - "
                a(href:"${config.site_contextPath}${post.uri}","${post.title}")
            }
            last_month = post.date.format("MMMM yyyy")
        }
        yieldUnescaped "</ul>"
    }
}
