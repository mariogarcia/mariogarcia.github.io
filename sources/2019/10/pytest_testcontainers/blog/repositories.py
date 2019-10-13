from blog.models import BlogEntry

class BlogEntryRepository:
    def __init__(self, session):
        self.session = session

    def find_by_id(self, id):
        return self.session.query(BlogEntry).filter_by(id=id).first()

    def find_all_by_title_starts_with(self, title_starts_with):
        by_startswith = BlogEntry.title.startswith(title_starts_with)

        return self.session.query(BlogEntry).filter(by_startswith).all()

    def save(self, blog_entry):
        self.session.add(blog_entry)
        self.session.flush()

        return blog_entry
