from sqlalchemy import create_engine
from sqlalchemy.orm import (scoped_session, sessionmaker)


engine = create_engine("")
session = scoped_session(sessionmaker(autocommit=False,
                                        autoflush=False,
                                        bind=engine))