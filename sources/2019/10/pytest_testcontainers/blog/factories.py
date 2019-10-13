import uuid

from blog.models import BlogEntry
from blog.repositories import BlogEntryRepository

class Factories:
    def __init__(self, session):
        self.session = session

    def build_blog_entry(self, **kwargs):
        return BlogEntry(
            title=kwargs.get('title', "Pytest introduction")
        )

    def create_blog_entry(self, **kwargs):
        entry = self.build_blog_entry(**kwargs)
        repos = BlogEntryRepository(self.session)

        return repos.save(entry)