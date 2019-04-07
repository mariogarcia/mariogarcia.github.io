def link = "${config.site_contextPath}${post.uri}"
def image = "${config.site_contextPath}img/latests/${post.summary_image ?: 'computer_science.png'}"

article {
    header {
        a(href: link, class: "image") {
        img(src: image, width: '361') { }
    }
    h3 "${post.title}"
    time {
        yield "${post?.date?.format('yyyy-MM-dd')}"
        }

        include template: "tags-brick.tpl"
    }
    p(class: "summary") {
        yield "${post.summary ?: 'Summary missing'}"
    }
    ul(class: "actions") {
        li {
            a(href: link, class: "button") {
                yield "More"
            }
        }
    }
}
