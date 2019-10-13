import uuid

from sqlalchemy import Column, String, Text
from sqlalchemy.dialects.postgresql import UUID, ARRAY
from sqlalchemy.ext.declarative import declarative_base


# base for all domain models
Base = declarative_base()


class BlogEntry(Base):
    __tablename__ = "blog_entries"
    id = Column(UUID(as_uuid=True), primary_key=True, default=uuid.uuid4)
    title = Column(String)
