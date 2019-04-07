// SECTION
model.put("section", "TAGS")

// TAGS LAYOUT
layout 'layout/main.tpl', true, projects: projects, bodyContents: contents {
    section {
        ul(class: "tags") {
            alltags.sort().each { tag ->
                tag = tag.trim()
                def postsCount = posts.findAll { post ->
                    post.status == "published" &&
                        post.tags?.contains(tag)
                }.size()

                li {
                    a(href:"${config.site_contextPath}tags/${tag.replace(' ', '-')}.html", class:"button primary small tag ${tag.trim()}") {
                        yield "$tag ($postsCount)"
                    }
                }
            }
        }
        div {
            hr()
            def last_month = null;
            tag_posts.each { post ->
                if ( post.status == 'published' ) {
                    if (last_month) {
                        if (post?.date?.format("MMMM yyyy") != last_month) {
                            yieldUnescaped "</ul>"
                            h4("${post?.date?.format("MMMM yyyy")}")
                            yieldUnescaped "<ul class='group'>"
                        }
                    }
                    else {
                        h4("${post?.date?.format("MMMM yyyy")}")
                        yieldUnescaped "<ul class='group'>"
                    }
                    li{
                        yield "${post?.date?.format("dd")} - "
                        a(href:"${config.site_contextPath}${post.uri}"){
                            yield post.title
                        }
                    }
                    last_month = post?.date?.format("MMMM yyyy")
                }
            }
            yieldUnescaped "</ul>"
        }
    }
}
