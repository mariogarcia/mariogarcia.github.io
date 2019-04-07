// SECTION
model.put("section", "INDEX")

layout 'layout/main.tpl', true, projects: projects, bodyContents: contents {
    section {
        div(class: "posts") {
            published_posts[0..5].each { post ->
                model.put('post', post)
                include template: 'post-brick.tpl'
            }
        }
    }
}
